package com.castaway.rigging.product

import com.castaway.rigging.*

class ProductGeneratorService {

    boolean transactional = true

    def generateProductVariants(Product product, List<AttributeValue[]> selectedAttributeValues) {
    
        def attributeValueList=[]
        
        selectedAttributeValues.each { groupedAttributeValues ->
            if (attributeValueList.isEmpty()) {
                groupedAttributeValues.each {attValue->
					attributeValueList << [attValue]
                }
            } 
            else {
                def anotherTemp = []
				attributeValueList.each{tempIt->
                    groupedAttributeValues.each {nextValueSet ->
                        def copyOfGroup = tempIt.clone()
                        copyOfGroup << nextValueSet                   
                        anotherTemp << copyOfGroup
                    }
                }
				attributeValueList=anotherTemp
            }
        }
        createProductsFromAttributes(product, attributeValueList).collect{generateDefaultSKU(it)}
    }
    
    def generateDefaultSKU(ProductVariant productVariant) {
		def SKU = productVariant.product.skuPart
		def sortedAttributeValues = productVariant.attributeValues?.sort{it.attribute.skuOrder}
		sortedAttributeValues.each{SKU += it.skuPart}
		productVariant.fullSKU=SKU
		return productVariant
    }
    
    private def createProductsFromAttributes(Product product, List attributeValueGroups) {
        
        def products = []
        attributeValueGroups.each { attributeValues ->
            def prod = new ProductVariant(product:product, attributeValues:attributeValues)
            products << prod
        }
        return products
    }
    
}