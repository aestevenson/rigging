package com.castaway.rigging

import grails.test.*

class ProductVariantTests extends GrailsUnitTestCase {
    protected void setUp() {
        super.setUp()
    }

    protected void tearDown() {
        super.tearDown()
    }

    void testConstraints() {
    	mockDomain ProductVariant
        def mockVariant = new ProductVariant(fullSKU:"123456")
    	def productVariant = new ProductVariant(fullSKU:"123456")
    	mockForConstraintsTests(ProductVariant,[mockVariant,productVariant])
    	assertFalse productVariant.validate()
    	assertEquals "unique", productVariant.errors['fullSKU']
    }
    
    void testWithAttributeValues() {
        def att1 = new AttributeValue()
        def att2 = new AttributeValue()
        def attValues = [att1, att2]
                         
        mockDomain ProductVariant
        def productVariant = new ProductVariant(fullSKU:"123456")
        mockForConstraintsTests(ProductVariant,[productVariant])
                         
        productVariant.addToAttributeValues(attValues)
        productVariant.attributeValues = attValues
    }
}

