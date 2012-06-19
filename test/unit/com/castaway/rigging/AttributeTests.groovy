package com.castaway.rigging

import grails.test.*

class AttributeTests extends GrailsUnitTestCase {
    
	/**
	 * Currently there is an eclipse ide plugin issue preventing this from passing in
	 * Eclipse, related to the "mockDomain" method.  This should pass successfully
	 * when executed from the 'grails test-app'.
	 */
	
	def attributeValue1
	def attributeValue2
	
	protected void setUp() {
        super.setUp()
    	attributeValue1 = new AttributeValue(attributeValueName:'attributeValueName1', skuPart:'123')
    	attributeValue2 = new AttributeValue(attributeValueName:'attributeValueName2', skuPart:'abc')

    }

    protected void tearDown() {
        super.tearDown()
    }
    
    void test_validAttribute() {
		def values = [attributeValue1, attributeValue2]
		mockDomain Attribute
    	def testAttribute = new Attribute(id:1,attributeName:'name', skuOrder:2, skuPartLength:3)    	
		testAttribute.addToAttributeValues(attributeValue1)
		mockForConstraintsTests(Attribute, [testAttribute])
		assertTrue testAttribute.validate()
    }

    void test_skuPartNotNull() {
    	mockForConstraintsTests(Attribute)
    }
}
