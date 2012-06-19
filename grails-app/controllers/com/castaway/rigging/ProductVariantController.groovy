

package com.castaway.rigging

class ProductVariantController {
    
    def productGeneratorService
    
    def index = { redirect(action:list,params:params) }

    // the delete, save and update actions only accept POST requests
    static allowedMethods = [delete:'POST', save:'POST', update:'POST']

                             
    def list = {
        params.max = Math.min( params.max ? params.max.toInteger() : 10,  100)
        [ productVariantInstanceList: ProductVariant.list( params ), productVariantInstanceTotal: ProductVariant.count() ]
    }

    def show = {
        def productVariantInstance = ProductVariant.get( params.id )

        if(!productVariantInstance) {
            flash.message = "ProductVariant not found with id ${params.id}"
            redirect(action:list)
        }
        else { return [ productVariantInstance : productVariantInstance ] }
    }

    def delete = {
        def productVariantInstance = ProductVariant.get( params.id )
        if(productVariantInstance) {
            try {
                productVariantInstance.delete()
                flash.message = "ProductVariant ${params.id} deleted"
                redirect(action:list)
            }
            catch(org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "ProductVariant ${params.id} could not be deleted"
                redirect(action:show,id:params.id)
            }
        }
        else {
            flash.message = "ProductVariant not found with id ${params.id}"
            redirect(action:list)
        }
    }

    def edit = {
        def productVariantInstance = ProductVariant.get( params.id )

        if(!productVariantInstance) {
            flash.message = "ProductVariant not found with id ${params.id}"
            redirect(action:list)
        }
        else {
            return [ productVariantInstance : productVariantInstance ]
        }
    }

    def update = {
        def productVariantInstance = ProductVariant.get( params.id )
        if(productVariantInstance) {
            if(params.version) {
                def version = params.version.toLong()
                if(productVariantInstance.version > version) {
                    
                    productVariantInstance.errors.rejectValue("version", "productVariant.optimistic.locking.failure", "Another user has updated this ProductVariant while you were editing.")
                    render(view:'edit',model:[productVariantInstance:productVariantInstance])
                    return
                }
            }
            productVariantInstance.properties = params
            if(!productVariantInstance.hasErrors() && productVariantInstance.save()) {
                flash.message = "ProductVariant ${params.id} updated"
                redirect(action:show,id:productVariantInstance.id)
            }
            else {
                render(view:'edit',model:[productVariantInstance:productVariantInstance])
            }
        }
        else {
            flash.message = "ProductVariant not found with id ${params.id}"
            redirect(action:edit,id:params.id)
        }
    }

    def create = {
        def productVariantInstance = new ProductVariant()
        productVariantInstance.properties = params
        return ['productVariantInstance':productVariantInstance]
    }

    def save = {
        def productVariantInstance = new ProductVariant(params)
        if(!productVariantInstance.hasErrors() && productVariantInstance.save()) {
            flash.message = "ProductVariant ${productVariantInstance.id} created"
            redirect(action:show,id:productVariantInstance.id)
        }
        else {
            render(view:'create',model:[productVariantInstance:productVariantInstance])
        }
    }
	
	def generateProductVariantsFlow = {
		start {
		    action {
				[productInstance:Product.get(params.id)]
			}
			on ("success").to("selectAttributeValues")
		}
		selectAttributeValues {
			on("next", selectAttributeValues_next).to("selectProductVariants")
			on("cancel").to("finishBeforeStart")
		}
		selectProductVariants {
			on("cancel").to("finish")
			on("previous").to("selectAttributeValues")
			on("next", selectProductVariants_next).to("confirmNewProductVariants")
		}
		confirmNewProductVariants {
			on("cancel").to("finish")
			on("previous").to("selectProductVariants")
			on("accept", confirmNewProductVariants_acceptSave).to("areAllRecordsProcessed")
			on("finish", confirmNewProductVariants_finishSave).to("finish")
		}
		areAllRecordsProcessed { //action state
			action {
				if (flow.productVariantInstanceList.size() == 0) {
					allRowsProcessed()
				} else {
					processRemainingRows()
				}
			}
			on("allRowsProcessed").to("finish")
			on("processRemainingRows").to("selectProductVariants")
			
		}
		finish {
			redirect(controller:"productVariant", action:"list")
		}
		finishBeforeStart {
			redirect(controller:"product", action:"list")
		}
		
	}
    
    def selectAttributeValues_next = {
        def product = flow.productInstance
		def attributes = params.attributes
        def attributeValues = []
        def missingAttribute = false
        if (!product) {return error()}
        if (!attributes) {return error()}
        if (attributes.size() != product.attributes.size()) {
            flash.message = "Please select at least one value for each Attribute"
            error()
        }
		attributes.each {attributeId, attributeValueIdsSelected -> 
			def intIdArray = this.modifyParamSelectionIntoIntArray(attributeValueIdsSelected)
            attributeValues.add(AttributeValue.getAll(intIdArray))
		}
        if (missingAttribute) {
            flash.message = "Please select a value for each attribute"
            error()
        }
        def productVariantInstanceList = productGeneratorService.generateProductVariants(product, attributeValues)
        flash.message = "${productVariantInstanceList.size()} Product Variants Generated for Product:${product.productName}"
        [productVariantInstanceList:productVariantInstanceList]
    }

	def selectProductVariants_next = {
		def newSkus = params.productVariantSKUs
		//Get the list of variants that are to be updated from the session
		def variantListToBeUpdated = 
				flow.productVariantInstanceList.findAll{modifyParamSelectionIntoStringArray(params.selectedProductVariants).any{selVar->it.fullSKU==selVar.toString()}}
		//From the list of ProductVariants that are to be saved/updated, change the SKUs to what the user entered
		newSkus.each{oldSKU,newSKU-> 
			if (oldSKU != newSKU) {
				variantListToBeUpdated.find{it.fullSKU==oldSKU}?.fullSKU=newSKU
			}
		}
		def goodSKUs = []
		def badSKUs = []
		variantListToBeUpdated.each{it.validate()?goodSKUs<<it:badSKUs<<it}
		[goodProductVaraiants:goodSKUs, badProductVariants:badSKUs]
    }
    
	def confirmNewProductVariants_acceptSave = {
		def goodProductVariants = flow.goodProductVaraiants
		def productVariantInstanceList = flow.productVariantInstanceList
		def SKUsToRemove = []
		goodProductVariants.each{
			if (it.save()){
				//TODO evaluate performance
				SKUsToRemove.add(it.fullSKU)
			}
			else {
				error("Unexpected Error")	
			}
		}
		// Get all products in the master list of product variants in the web flow where the product's SKU is not equal to every SKUs in the saved SKU list
		productVariantInstanceList = productVariantInstanceList.findAll{ masterProductVariant-> SKUsToRemove.every{ savedSKU-> masterProductVariant.fullSKU != savedSKU } }
		[productVariantInstanceList: productVariantInstanceList]
	}

    def confirmNewProductVariants_finishSave = {
        def goodProductVariants = flow.goodProductVaraiants
        def productVariantInstanceList = flow.productVariantInstanceList
        def SKUsToRemove = []
        goodProductVariants.each {
            if (!it.save()) {
                error("Unexpected Error")   
            }
        }
    }

	
    /**
     * In 'g:select multiple=multiple' elements, different objects are created in params depending on if
     * one or multiple selections are chosen.  If one element is chosen, the param is a String.  If multiple elements
     * are chosen the param is a List.                         
     */
    //TODO turn these two into a single method
    def modifyParamSelectionIntoIntArray(def selectedIds) {
		def intArray = []
		if (selectedIds.class == String.class) {
			intArray.add(Integer.valueOf(selectedIds))
		}
		else {
			selectedIds.each{intArray.add(Integer.valueOf(it))}
		}
		return intArray;
	}
	
    def modifyParamSelectionIntoStringArray(def selectedIds) {
        def stringArray = []
        if (selectedIds?.class == String.class) {
			stringArray.add(selectedIds)
        }
        else {
            selectedIds.each{stringArray.add(it)}
        }
        return stringArray;
    }
	
}
