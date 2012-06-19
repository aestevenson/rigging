package com.castaway.rigging

import grails.test.*

class AttributeValueTests extends GrailsUnitTestCase {
    protected void setUp() {
        super.setUp()
    }

    protected void tearDown() {
        super.tearDown()
    }
    
    void test_SkuSizeNotEqual() {
    	def parentAttribute = new Attribute(id:1, version:1, skuPartLength:3)
    	def attributeValue = new AttributeValue(id:1, version:1, attributeValueName:'testName', attribute:parentAttribute, skuPart:'ab')
    	mockForConstraintsTests(AttributeValue,[attributeValue])
    	def valid = attributeValue.validate()
    	assertFalse valid
    	attributeValue.errors.each{println it}
    	assertEquals "validator", attributeValue.errors['attribute']
    }
    
    void test_SkuSizeEqual() {
    	def parentAttribute = new Attribute(skuPartLength:3)
    	def attributeValue = new AttributeValue(attributeValueName:'testName', attribute:parentAttribute, skuPart:'abc')
    	mockForConstraintsTests(AttributeValue,[attributeValue])
    	def valid = attributeValue.validate()
    	assertTrue valid
    }
    
    void test_NameNotDuplicateSameAttribute() {
    	def parentAttribute = new Attribute(attributeName:"""men's size""",skuPartLength:3)
		def attributeValue = new AttributeValue(attributeValueName:'32', attribute:parentAttribute, skuPart:'123')    	
    	mockForConstraintsTests(AttributeValue, [attributeValue])
		def attributeValueDup = new AttributeValue(attributeValueName:'32', attribute:parentAttribute, skuPart:'123')
    	assertFalse attributeValueDup.validate()
    	assertEquals 'unique', attributeValueDup.errors["attributeValueName"]
    }
    
    void test_NameNotDuplicateSameParentAttribute() {
    	def parentAttribute = new Attribute(attributeName:"""men's size""",skuPartLength:3)
		def attributeValue = new AttributeValue(attributeValueName:'32', attribute:parentAttribute, skuPart:'123')    	
    	mockForConstraintsTests(AttributeValue, [attributeValue])
		def attributeValueDup = new AttributeValue(attributeValueName:'32', attribute:parentAttribute, skuPart:'123')
    	assertFalse attributeValueDup.validate()
    	assertEquals 'unique', attributeValueDup.errors["attributeValueName"]
    }
    
    void test_NameDuplicateDifferentParentAttribute() {
    	def parentAttribute1 = new Attribute(id:1, version:2,attributeName:"""men's size""",skuPartLength:3)
		def attributeValue = new AttributeValue(attributeValueName:'32', attribute:parentAttribute1, skuPart:'123')    	
    	mockForConstraintsTests(AttributeValue, [attributeValue])
		def parentAttribute2 = new Attribute(id:2, version:1,attributeName:"""women's size""",skuPartLength:3)
    	def attributeValueDup = new AttributeValue(attributeValueName:'32', attribute:parentAttribute2, skuPart:'123')
		attributeValueDup.validate()
		if (attributeValueDup.hasErrors()) {
			println "--Unintended Attribute Value errors: ----"
			attributeValueDup.errors.each{println it}
			println "--end---"
		}
		assertTrue attributeValueDup.validate()
    	assertNull attributeValueDup.errors["attributeValueName"]
    }
    
    void test_SkuNotDuplicateSameParentAttribute() {
    	def parentAttribute = new Attribute(attributeName:"""men's size""",skuPartLength:3)
		def attributeValue = new AttributeValue(attributeValueName:'32', attribute:parentAttribute, skuPart:'123')    	
    	mockForConstraintsTests(AttributeValue, [attributeValue])
		def attributeValueDup = new AttributeValue(attributeValueName:'34', attribute:parentAttribute, skuPart:'123')
    	assertFalse attributeValueDup.validate()
    	assertEquals 'unique', attributeValueDup.errors["skuPart"]
    }
    
    void test_SkuDuplicateDifferentParentAttribute() {
    	def parentAttribute1 = new Attribute(id:1, version:1, attributeName:"""men's size""",skuPartLength:3)
		def attributeValue = new AttributeValue(attributeValueName:'32', attribute:parentAttribute1, skuPart:'123')    	
    	mockForConstraintsTests(AttributeValue, [attributeValue])
		def parentAttribute2 = new Attribute(id:2, version:1, attributeName:"""women's size""",skuPartLength:3)
    	def attributeValueDup = new AttributeValue(attributeValueName:'34', attribute:parentAttribute2, skuPart:'123')
    	assertTrue attributeValueDup.validate()
    	assertNull attributeValueDup.errors["skuPart"]
    }
    
}