package com.castaway.rigging

import grails.test.*

class ProductTests extends GrailsUnitTestCase {
    protected void setUp() {
        super.setUp()
    }

    void test_ProductNameCannotBeNull()
    {
    	mockForConstraintsTests(Product)
    	def testProduct = new Product()
    	assertFalse testProduct.validate()
		assertEquals "nullable", testProduct.errors["productName"]
    }
    
    void test_getProductWith2Attributes() {
        def att1 = new Attribute(id:1, version:1, attributeName:'attribute 1', skuOrder:1, skuPartLength:2)
        def att2 = new Attribute(id:2, version: 2, attributeName:'attribute 2', skuOrder:99, skuPartLength:2)
        def product = new Product(id:1, version:1, productName:'prod', skuPart:"1",attributes:[att1, att2] )
        mockDomain(Attribute, [att1, att2])
        mockDomain(Product, [product])
        def prod = Product.get(1)
    }
	
	void test_validateProductWithSKUPartBlankDescription() {
		def product = new Product(id:1, version:1, productName:'prod', skuPart:"1", productDescription:"" )
		mockForConstraintsTests(Product, [product])
		assertTrue(product.validate())
	}
	
	void test_validateProductWithSKUPartNullDescription() {
		def product = new Product(id:1, version:1, productName:'prod', skuPart:"1" )
		mockForConstraintsTests(Product, [product])
		assertFalse(product.validate())
		assertEquals("nullable",product.errors["productDescription"])
	}
    

    protected void tearDown() {
        super.tearDown()
    }

}
