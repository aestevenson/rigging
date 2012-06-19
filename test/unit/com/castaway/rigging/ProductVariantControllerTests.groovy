package com.castaway.rigging

import grails.test.*
import com.castaway.rigging.setUp.BasicControllerUnitTestSetUp
import com.castaway.rigging.product.ProductGeneratorService

class ProductVariantControllerTests extends BasicControllerUnitTestSetUp {
    
    def productGeneratorService = new ProductGeneratorService()
    
    protected void setUp() {
        super.setUp()
        mockDomain(ProductVariant, [
            new ProductVariant(product:harborPantsProduct, fullSKU:'99', attributeValues:[thirtyEightWaist, red])
		])
    }

    protected void tearDown() {
        
        super.tearDown()
    }

    void testProductVariantSelection1by1() {
		harborPantsProduct.attributes=[waistSize, color]
        mockDomain(Product, [harborPantsProduct])
		mockDomain(Attribute, [waistSize, color])
        mockDomain(AttributeValue, [thirtyTwoWaist,red])
        controller.productGeneratorService = productGeneratorService
        controller.params.put("attributes", new HashMap())
        controller.params.attributes.put("${waistSize.id}",["${this.thirtyTwoWaist.id}"])
        controller.params.attributes.put("${color.id}",["${this.red.id}"])
        controller.params.id=harborPantsProduct.id
        def returned = controller.productVariantSelection()     
        assertEquals(harborPantsProduct, controller.session.product)
        assertEquals(1,controller.session.productVariantInstanceList.size())
        def productVariantInstance = controller.session.productVariantInstanceList.get(0)
        assertNotNull(productVariantInstance?.attributeValues.find{it.attributeValueName==thirtyTwoWaist.attributeValueName})
        assertNotNull(productVariantInstance?.attributeValues.find{it.attributeValueName==thirtyTwoWaist.attributeValueName})
    }
    
    
    
    void testProductVariantSelection3by2by1by3() {
		harborPantsProduct.attributes=[waistSize, color, pantLength, embroideryType]
		mockDomain(Product, [harborPantsProduct])
        mockDomain(AttributeValue, [red, blue, thirtyTwoWaist, thirtyFourWaist, thirtySixWaist, thirtyTwoInseam,crab, merMaid, jollyRoger])
        controller.productGeneratorService = productGeneratorService
        controller.params.put("attributes", new HashMap())
        controller.params.attributes.put("${waistSize.id}",["${thirtyTwoWaist.id}", "${thirtyFourWaist.id}", "${thirtySixWaist.id}"])
        controller.params.attributes.put("${color.id}",["${red.id}", "${blue.id}"])
        controller.params.attributes.put("${pantLength.id}",["${thirtyTwoInseam.id}"])
        controller.params.attributes.put("${embroideryType.id}",["${crab.id}", "${merMaid.id}", "${jollyRoger.id}"])
        controller.params.id=harborPantsProduct.id
        def returned = controller.productVariantSelection()     
        assertEquals(harborPantsProduct, controller.session.product)
        assertEquals(18,controller.session.productVariantInstanceList.size())
    }
	
	void testSaveProductVariantList_SkuIsUnchangedWhenNotChecked() {
		def productVariant1 = new ProductVariant(product:harborPantsProduct, fullSKU:'10', attributeValues:[thirtyTwoWaist, red])
		def productVariant2 = new ProductVariant(product:harborPantsProduct, fullSKU:'11', attributeValues:[thirtyFourWaist, red])
		controller.session.productVariantInstanceList = [productVariant1, productVariant2] 
		def skuMap = new HashMap()
		skuMap.put '10', '3'
		controller.params.put("productVariantSKUs", skuMap)
		def returned = controller.saveProductVariantList()
		def foundVariant = controller.session.productVariantInstanceList.find{it.fullSKU=='10'}
		assertNotNull foundVariant
		assertNotNull foundVariant.attributeValues.find{it==thirtyTwoWaist}
	}
	
	void testSaveProductVariantList_SkuIsChangedWhenChecked() {
		def productVariant1 = new ProductVariant(product:harborPantsProduct, fullSKU:'10', attributeValues:[thirtyTwoWaist, red])
		def productVariant2 = new ProductVariant(product:harborPantsProduct, fullSKU:'11', attributeValues:[thirtyTwoWaist, red])
		controller.session.productVariantInstanceList = [productVariant1, productVariant2] 
		def skuMap = new HashMap()
		skuMap.put '10', '3'
		controller.params.put("productVariantSKUs", skuMap)
		controller.params.selectedProductVariants = ['10']
		def returned = controller.saveProductVariantList()
		assertNull controller.session.productVariantInstanceList.find{it.fullSKU=='10'}
		def changedVariant = controller.session.productVariantInstanceList.find{it.fullSKU=='3'}
		assertNull changedVariant  //it was saved and removed from the session
		assertEquals 1, controller.session.productVariantInstanceList.size()
		assertNotNull controller.session.productVariantInstanceList.find{it.fullSKU=='11'}
		def savedProductVariant =  ProductVariant.findByFullSKU('3')  //verify that SKU '3' product variant was saved
		assertNotNull savedProductVariant
        assertNotNull savedProductVariant.attributeValues.find{it==thirtyTwoWaist} //verify that the saved PV was the one with a 32 waist
	}
	
	void testSaveProductVariantList_SkuIsSavedWhenChecked() {

		def productVariant1 = new ProductVariant(product:harborPantsProduct, fullSKU:'10', attributeValues:[thirtyTwoWaist, red])
		def productVariant2 = new ProductVariant(product:harborPantsProduct, fullSKU:'11', attributeValues:[thirtyTwoWaist, red])
		controller.session.productVariantInstanceList = [productVariant1, productVariant2] 
		def skuMap = new HashMap()
		controller.params.put("productVariantSKUs", skuMap)
		controller.params.selectedProductVariants = ['10']
		def returned = controller.saveProductVariantList()
		assertNull controller.session.productVariantInstanceList.find{it.fullSKU=='10'}
	}
	
	void testSaveProductVariantList_SkuIsNotSavedOrRemovedFromSessionWhenSKUisNotUnique() {
		def productVariant1 = new ProductVariant(product:harborPantsProduct, fullSKU:'99', attributeValues:[thirtyTwoWaist, red]) //Duplicate SKU
		def productVariant2 = new ProductVariant(product:harborPantsProduct, fullSKU:'11', attributeValues:[thirtyTwoWaist, red])
		controller.session.productVariantInstanceList = [productVariant1, productVariant2] 
		def skuMap = new HashMap()
		controller.params.put("productVariantSKUs", skuMap)
		controller.params.selectedProductVariants = ['99']
		def returned = controller.saveProductVariantList()
		def notSavedVariant = controller.session.productVariantInstanceList.find{it.fullSKU=='99'}
		assertNotNull notSavedVariant  //it was not saved nor removed from session as it has the same SKU as another PV
		assertFalse notSavedVariant.validate()
		assertEquals 2, controller.session.productVariantInstanceList.size()
		assertNotNull controller.session.productVariantInstanceList.find{it.fullSKU=='11'}
		def variant =  ProductVariant.findByFullSKU('99')  //verify that SKU '99' product variant was NOT saved
		assertNull "The PV with a 99 SKU should be the 38 waist not the (new)32 waist", variant.attributeValues.find{it.attributeValueName==thirtyTwoWaist.attributeValueName}
	}
	
	
	
}
