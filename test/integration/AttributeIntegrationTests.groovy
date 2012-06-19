import grails.test.*
import com.castaway.rigging.AttributeValue
import com.castaway.rigging.Attribute

class AttributeIntegrationTests extends GrailsUnitTestCase {
    
	def attributeValue1 
	def attributeValue2
	
	protected void setUp() {
        super.setUp()
    }

    protected void tearDown() {
        super.tearDown()
    }

    void test_saveAttributeWithOneAttributeValue() {
		def attribute = new Attribute(attributeName:'attributeTest', skuOrder:3, skuPartLength:3).save()
    	attributeValue1 = new AttributeValue(attributeValueName:'attributeValueName1', skuPart:'123')
    	attributeValue2 = new AttributeValue(attributeValueName:'attributeValueName2', skuPart:'abc', attributeDescription:'Description of the attribute here')
   		attribute.addToAttributeValues(attributeValue1)
   		if (!attribute.save())
   			attribute.errors.each{println it}
		
		def foundAttribute = Attribute.get(attribute.id);
		assertEquals 'attributeTest', foundAttribute.attributeName
    }
    
    void test_saveAttributeWithTwoAttributeValues() {
		def attribute = new Attribute(attributeName:'attributeTest', skuOrder:3, skuPartLength:3).save()
    	attributeValue1 = new AttributeValue(attributeValueName:'attributeValueName1', skuPart:'123')
    	attributeValue2 = new AttributeValue(attributeValueName:'attributeValueName2', skuPart:'abc')
   		attribute.addToAttributeValues(attributeValue1)
   		attribute.addToAttributeValues(attributeValue2)
   		def newAttribute =  attribute.save()
   		assertNotNull newAttribute
   		assertEquals 2, newAttribute.attributeValues.size()
   		assertFalse attribute.hasErrors()
   		assertEquals 'attributeTest' ,attributeValue2.attribute.attributeName
    }
    
    void test_saveAttributeWithOneAttributeValueWithTooLongSku() {
		def attribute = new Attribute(attributeName:'attributeTestThree', skuOrder:3, skuPartLength:3).save()
    	attributeValue1 = new AttributeValue(attributeValueName:'attributeTestThree Value 1', skuPart:'1234')
   		attribute.addToAttributeValues(attributeValue1)
   		attribute.errors.each{println it}
   		assertNull(attribute.save()) 
   	}
    
    void test_saveAttributeWithOneAttributeValueWithCorrectSkuLength() {
		def attribute = new Attribute(attributeName:'attributeTestThree', skuOrder:3, skuPartLength:3).save()
    	attributeValue1 = new AttributeValue(attributeValueName:'attributeTestThree Value 1', skuPart:'123')
   		attribute.addToAttributeValues(attributeValue1)
   		assertNotNull(attribute.save())
    }
}
