package com.castaway.rigging.product

import grails.test.GrailsUnitTestCase
import grails.test.*
import com.castaway.rigging.*

class ProductGeneratorServiceTests extends GrailsUnitTestCase {
    
    def product1
    
    def attribute1
    def attribute2
    def color
    
    def attributeValue1_1
    def attributeValue1_2
    
    def attributeValue2_1
    def attributeValue2_2
    
    def attributeValue3_1
    def attributeValue3_2
    
    def color2 = new Attribute(attributeName:'Color', attributeDescription:'how do you describe color to a blind person?', skuOrder:4, skuPartLength:2)
    def waistSize = new Attribute(attributeName:'Waist Size', attributeDescription:'', skuOrder:2, skuPartLength:2)
    def pantLength = new Attribute(attributeName:'Pant Length', attributeDescription:'', skuOrder:3, skuPartLength:3)
    def skirtSize = new Attribute(attributeName:'Skirt Size', attributeDescription:'', skuOrder:2, skuPartLength:2)
    def embroideryType = new Attribute(attributeName:'Embroidery Type', attributeDescription:'', skuOrder:5, skuPartLength:3)
    
    def red = new AttributeValue(attributeValueName:'red', attribute:color2,skuPart:'01')
    def blue = new AttributeValue(attributeValueName:'blue', attribute:color2,skuPart:'02')
    def green = new AttributeValue(attributeValueName:'green', attribute:color2,skuPart:'03')
    def khakhi = new AttributeValue(attributeValueName:'khakhi', attribute:color2,skuPart:'04')
    
    def thirtyTwoWaist = new AttributeValue(attributeValueName:'32', attribute:waistSize,skuPart:'32')
    def thirtyFourWaist = new AttributeValue(attributeValueName:'34', attribute:waistSize,skuPart:'34')
    def thirtySixWaist = new AttributeValue(attributeValueName:'36', attribute:waistSize,skuPart:'36')
    def thirtyEightWaist = new AttributeValue(attributeValueName:'38', attribute:waistSize,skuPart:'38')
    def fortyWaist = new AttributeValue(attributeValueName:'40', attribute:waistSize,skuPart:'40')
    def fortyTwoWaist = new AttributeValue(attributeValueName:'42', attribute:waistSize,skuPart:'42') 
    
    def thirtyTwoInseam = new AttributeValue(attributeValueName:'32', attribute:pantLength,skuPart:'001')
    def thirtyFourInseam = new AttributeValue(attributeValueName:'34', attribute:pantLength,skuPart:'002')
    def thirtySixInseam = new AttributeValue(attributeValueName:'36', attribute:pantLength,skuPart:'003')
    def thirtyEightInseam = new AttributeValue(attributeValueName:'38', attribute:pantLength,skuPart:'004')
    def fortyInseam = new AttributeValue(attributeValueName:'40', attribute:pantLength,skuPart:'005')
     
    def twoSkirt = new AttributeValue(attributeValueName:'2', attribute:skirtSize,skuPart:'60')
    def fourSkirt = new AttributeValue(attributeValueName:'4', attribute:skirtSize,skuPart:'61')
    def sixSkirt = new AttributeValue(attributeValueName:'6', attribute:skirtSize,skuPart:'62')
    def eightSkirt = new AttributeValue(attributeValueName:'8', attribute:skirtSize,skuPart:'63')
     
    def crab = new AttributeValue(attributeValueName:'Crab', attribute:embroideryType,skuPart:'100')
    def merMaid = new AttributeValue(attributeValueName:'Mermaid', attribute:embroideryType,skuPart:'101')
    def jollyRoger = new AttributeValue(attributeValueName:'Jolly Roger', attribute:embroideryType,skuPart:'102')
    def martini = new AttributeValue(attributeValueName:'Martini', attribute:embroideryType,skuPart:'103')
    def keg = new AttributeValue(attributeValueName:'Keg', attribute:embroideryType,skuPart:'104')
    def aaronInitials = new AttributeValue(attributeValueName:"Aaron's Initials", attribute:embroideryType,skuPart:'105')
    def castaway = new AttributeValue(attributeValueName:'Castaway Logo', attribute:embroideryType,skuPart:'106')
    
    def service = new ProductGeneratorService()
    
    /**
     * 
     */
    protected void setUp() {
        super.setUp()
        product1 = new Product(id:1, version:1, productName:'Harbor Pants', productDescription:'product description 1', skuPart:'111');
        
        attribute1 = new Attribute(id:1, version:1, attributeName:'waist size', skuOrder:'2', skuPartLength:'3')
        attribute2 = new Attribute(id:2, version:1, attributeName:'pant length', skuOrder:'3', skuPartLength:'3')
        color = new Attribute(id:3, version:1, attributeName:'color', skuOrder:'4', skuPartLength:'2')
        
        attributeValue1_1 = new AttributeValue(id:1, version:2, attributeValueName:'32', skuPart:'032', attribute:attribute1)
        attributeValue1_2 = new AttributeValue(id:2, version:2, attributeValueName:'34', skuPart:'034', attribute:attribute1)
        
        attributeValue2_1 = new AttributeValue(id:3, version:2, attributeValueName:'40', skuPart:'040', attribute:attribute2)
        attributeValue2_2 = new AttributeValue(id:4, version:2, attributeValueName:'42', skuPart:'042', attribute:attribute2)
        
        attributeValue3_1 = new AttributeValue(id:5, version:1, attributeValueName:'red', skuPart:'01', attribute:color)
        attributeValue3_2 = new AttributeValue(id:5, version:1, attributeValueName:'blue', skuPart:'02', attribute:color)
        
        attribute1.attributeValues = [attributeValue1_1, attributeValue1_2]
        attribute2.attributeValues = [attributeValue2_1, attributeValue2_2]
                                      
        mockDomain(Product, [product1])
        mockDomain(Attribute, [attribute1, attribute2])
        mockDomain(AttributeValue, [attributeValue1_1,attributeValue1_2, attributeValue2_1, attributeValue2_2])
		mockDomain(ProductVariant)
    }

    protected void tearDown() {
        super.tearDown()
    }

	//Begin Product Variant Generation Tests
	void testGenerateProducts_1_1() {
        def productMapping = [attributeValue1_1, attributeValue2_1]
        def products = service.generateProductVariants(product1, productMapping)
        assertNotNull("Service method should return a List of product variants", products)
        assertEquals("2 product variants should be generated", 1, products.size())
        
    }
    
    void testGenerateProductVariants_2_2() {
        def productMapping = [[attributeValue1_1, attributeValue1_2],[attributeValue2_1, attributeValue2_2]]
        
        def products = service.generateProductVariants(product1, productMapping)
        assertNotNull("Service method should return a List of product variants", products)
        assertEquals("4 product variants should be generated", 4, products.size())
    }
    
    void testGenerateProductVariants_2_0() {
        def productMapping = [[attributeValue1_1, attributeValue1_2],[]]
        
        def products = service.generateProductVariants(product1, productMapping)
        assertNotNull("Service method should return a List of product variants", products)
        assertEquals("0 products should be generated", 0, products.size())
    }
    
    void testGenerateProductVariants_2_2_2() {
        def productMapping = [[attributeValue1_1, attributeValue1_2],
                              [attributeValue2_1, attributeValue2_2],
                              [attributeValue3_1, attributeValue3_2]]
        
        def products = service.generateProductVariants(product1, productMapping)
        assertNotNull("Service method should return a List of product variants", products)
        assertEquals(8, products.size())
    }
    
    void testGenerateProductVariants_4by5by5by7() {
        def productMapping = [[red, blue, green, khakhi],//4
                              [thirtyTwoWaist, thirtyFourWaist, thirtySixWaist, thirtyEightWaist, fortyWaist],//5
                              [thirtyTwoInseam,thirtyFourInseam, thirtySixInseam, thirtyEightInseam,fortyInseam],//5   
                              [crab,merMaid ,jollyRoger,martini,keg,aaronInitials,castaway]]//7
        def productVariants = service.generateProductVariants(product1, productMapping)
        assertNotNull("Service method should return a List of product variants", productVariants)
        assertEquals("${4*5*5*7} products should be generated", (4*5*5*7), productVariants.size())
        //SKUs generated in the service are not guaranteed to be unique relative to SKUs in the DB
		//, but since we are controlling the 'test' "db" (which is empty) they are guaranteed to be unique relative to each other
        assertFalse("All product Variants should be valid",productVariants.any{!it.validate()}) 
        assertTrue(productVariants.every{it.attributeValues.size()==productMapping.size()})
        assertTrue( productVariants.every{it.attributeValues.every{attVal-> attVal.attributeValueName}} )
    }
    
    void testGenerateProductVariants_4by5by5by1() {
        def productMapping = [[red, blue, green, khakhi],//4
                              [thirtyTwoWaist, thirtyFourWaist, thirtySixWaist, thirtyEightWaist, fortyWaist],//5
                              [thirtyTwoInseam,thirtyFourInseam, thirtySixInseam, thirtyEightInseam,fortyInseam],//5   
                              [crab]]//1
        def productVariants = service.generateProductVariants(product1, productMapping)
        assertNotNull("Service method should return a List of product variants", productVariants)
        assertEquals("${4*5*5*1} products should be generated", (4*5*5*1), productVariants.size())
        assertTrue(productVariants.every{it.attributeValues.size()==productMapping.size()})
        assertTrue(productVariants.every{it.attributeValues.find{attVal->attVal.attribute.attributeName==embroideryType.attributeName}.attributeValueName==crab.attributeValueName})
        assertFalse(productVariants.any{it.attributeValues.find{attVal->attVal.attribute.attributeName==embroideryType.attributeName}.attributeValueName!=crab.attributeValueName})
    }
	
	void testGenerateProductVariants_1by1() {
		def productMapping = [
		    [red],//1
		    [thirtyTwoWaist]//1
		]
		def productVariants = service.generateProductVariants(product1, productMapping)
		assertNotNull("Service method should return a List of product variants", productVariants)
		assertEquals("${1*1} products should be generated", (1*1), productVariants.size())
		assertTrue(productVariants.every{it.attributeValues.size()==productMapping.size()})
	}
	
	void testGenerateProductVariants_2by3by1by3() {
		def productMapping = [[red, blue],//2
		[thirtyTwoWaist, thirtyFourWaist, thirtySixWaist],//3
		[thirtyTwoInseam],//1
		[crab, merMaid, jollyRoger]]//3
		def productVariants = service.generateProductVariants(product1, productMapping)
		assertNotNull("Service method should return a List of product variants", productVariants)
		assertEquals("${4*5*5*1} products should be generated", (2*3*1*3), productVariants.size())
		assertTrue(productVariants.every{it.attributeValues.size()==productMapping.size()})
	}
	
	
	// Begin SKU Generation tests
	void testSKUGeneration_2_attributeValues() {
		def someSkirtProduct = new Product(productName:"Preppy Skirt", skuPart:'123')
		def productVariant = new ProductVariant(product:someSkirtProduct, attributeValues:[red, sixSkirt])
		def skirtWithSKU = service.generateDefaultSKU(productVariant)
		assertEquals('1236201', skirtWithSKU.fullSKU)
	}
	
	
	void testSKUGeneration_0_attributeValues() {
		def someSkirtProduct = new Product(productName:"Preppy Skirt", skuPart:'123')
		def productVariant = new ProductVariant(product:someSkirtProduct)
		def skirtWithSKU = service.generateDefaultSKU(productVariant)
		assertEquals('123', skirtWithSKU.fullSKU)
	}
	   
}
