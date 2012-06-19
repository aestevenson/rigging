package com.castaway.rigging;

import grails.test.ControllerUnitTestCase;
import grails.test.*

class ProductControllerTests extends ControllerUnitTestCase {
    
	def attribute1
	def attribute2 
	def attribute3
	
	def product1
		
	protected void setUp() {
        super.setUp()
		attribute1 = new Attribute(id:1, attributeName:'testAtt1',skuOrder:2, skuPartLength:3)
        attribute2 = new Attribute(id:2, attributeName:'testAtt2',skuOrder:2, skuPartLength:3)
        attribute3 = new Attribute(id:3, attributeName:'testAtt3',skuOrder:2, skuPartLength:3)
        
        product1 = new Product(id:1, version:1, productName:'test',skuPart:123,productDescription:'product description' )
    }

    protected void tearDown() {
        super.tearDown()
    }

    void testManageProductAttributes() {
    	mockDomain(Attribute, [attribute1, attribute2, attribute3])
    	mockDomain(Product, [product1])
    	this.controller.params.id=1
   		def returned = this.controller.manageProductAttributes()
   		assertEquals product1.productName, returned?.productInstance?.productName
   		assertNotNull returned?.attributeInstanceList
   		assertEquals 3,returned?.attributeInstanceList?.size()
    }
    
    void testManageProductAttributes_productWith1Attribute() {
    	mockDomain(Attribute, [attribute1, attribute2, attribute3])
    	mockDomain(Product, [product1])
    	product1.attributes = [attribute1]
    	this.controller.params.id=1
   		def returned = this.controller.manageProductAttributes()
   		assertEquals product1.productName, returned?.productInstance?.productName
   		assertNotNull returned?.attributeInstanceList
   		assertEquals 3,returned?.attributeInstanceList?.size()
   		assertEquals 1,returned?.productInstance.attributes.size()
   		assertNotNull returned?.productInstance.attributes.find {it.attributeName=="testAtt1"}
    }
    
    void testSaveProductWith0Attributes() {
        def pc = new ProductController()
        mockDomain(Attribute,[attribute1,attribute2])
        mockDomain(Product, [product1])
        pc.params.id=1
        pc.params.attributeCheckBoxes = null
        pc.saveAttributes()
        assertFalse "Place Holder Error" == pc.flash.message
        assertEquals pc.show, pc.redirectArgs.action
        assertTrue Product.get(1).attributes.size()==0
    }
    
    void testSaveProductWith1Attributes() {
    	def pc = new ProductController()
    	mockDomain(Attribute,[attribute1,attribute2])
    	mockDomain(Product, [product1])
    	def ids = [1,2]
    	pc.params.id=1
    	pc.params.attributeCheckBoxes = ids
    	pc.saveAttributes()
    	assertFalse "Place Holder Error" == pc.flash.message
    	assertEquals pc.show, pc.redirectArgs.action
    	assertTrue Product.get(1).attributes.size()==2
    }
}
