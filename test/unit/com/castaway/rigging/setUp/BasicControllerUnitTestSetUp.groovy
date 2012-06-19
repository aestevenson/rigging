package com.castaway.rigging.setUp;

import grails.test.ControllerUnitTestCase;
import com.castaway.rigging.Attribute;
import com.castaway.rigging.AttributeValue;
import com.castaway.rigging.Product;


class BasicControllerUnitTestSetUp extends ControllerUnitTestCase {
    
    def color
	def waistSize
	def pantLength
	def skirtSize
	def embroideryType
    def red
	def blue
	def green
	def khakhi
	
    def thirtyTwoWaist
	def thirtyFourWaist
	def thirtySixWaist
	def thirtyEightWaist
	def fortyWaist
	def fortyTwoWaist
	
    def thirtyTwoInseam
	def thirtyFourInseam
	def thirtySixInseam
	def thirtyEightInseam
	def fortyInseam
	
    def twoSkirt
	def fourSkirt
	def sixSkirt
	def eightSkirt
	
    def crab
	def merMaid
	def jollyRoger
	def martini
	def keg
	def aaronInitials
	def castaway
	
    def harborPantsProduct
    
    protected void setUp() {
        super.setUp()
		mockDomain(AttributeValue)
        color = new Attribute(id:1, version: 1,attributeName:'Color', attributeDescription:'how do you describe color to a blind person?', skuOrder:4, skuPartLength:2)
        waistSize = new Attribute(id:2, version: 1,attributeName:'Waist Size', attributeDescription:'', skuOrder:2, skuPartLength:2)
        pantLength = new Attribute(id:3, version: 1,attributeName:'Pant Length', attributeDescription:'', skuOrder:3, skuPartLength:3)
        skirtSize = new Attribute(id:4, version: 1,attributeName:'Skirt Size', attributeDescription:'', skuOrder:2, skuPartLength:2)
        embroideryType = new Attribute(id:5, version: 1,attributeName:'Embroidery Type', attributeDescription:'', skuOrder:5, skuPartLength:3)
		
		red = new AttributeValue(id:1, version:1,attributeValueName:'red', attribute:color, skuPart:'01')
        blue = new AttributeValue(id:2, version:1,attributeValueName:'blue', attribute:color,skuPart:'02')
        green = new AttributeValue(id:3, version:1,attributeValueName:'green', attribute:color,skuPart:'03')
        khakhi = new AttributeValue(id:4, version:1,attributeValueName:'khakhi', attribute:color,skuPart:'04')
        
        thirtyTwoWaist = new AttributeValue(id:5, version:1,attributeValueName:'32', attribute:waistSize,skuPart:'32')
        thirtyFourWaist = new AttributeValue(id:6, version:1,attributeValueName:'34', attribute:waistSize,skuPart:'34')
        thirtySixWaist = new AttributeValue(id:7, version:1,attributeValueName:'36', attribute:waistSize,skuPart:'36')
        thirtyEightWaist = new AttributeValue(id:8, version:1,attributeValueName:'38', attribute:waistSize,skuPart:'38')
        fortyWaist = new AttributeValue(id:9, version:1,attributeValueName:'40', attribute:waistSize,skuPart:'40')
        fortyTwoWaist = new AttributeValue(id:10, version:1,attributeValueName:'42', attribute:waistSize,skuPart:'42') 
        
        thirtyTwoInseam = new AttributeValue(id:11, version:1,attributeValueName:'32', attribute:pantLength,skuPart:'001')
        thirtyFourInseam = new AttributeValue(id:12, version:1,attributeValueName:'34', attribute:pantLength,skuPart:'002')
        thirtySixInseam = new AttributeValue(id:13, version:1,attributeValueName:'36', attribute:pantLength,skuPart:'003')
        thirtyEightInseam = new AttributeValue(id:14, version:1,attributeValueName:'38', attribute:pantLength,skuPart:'004')
        fortyInseam = new AttributeValue(id:1, version:15,attributeValueName:'40', attribute:pantLength,skuPart:'005')
        
        twoSkirt = new AttributeValue(id:16, version:1,attributeValueName:'2', attribute:skirtSize,skuPart:'60')
        fourSkirt = new AttributeValue(id:17, version:1,attributeValueName:'4', attribute:skirtSize,skuPart:'61')
        sixSkirt = new AttributeValue(id:18, version:1,attributeValueName:'6', attribute:skirtSize,skuPart:'62')
        eightSkirt = new AttributeValue(id:19, version:1,attributeValueName:'8', attribute:skirtSize,skuPart:'63')
        
        crab = new AttributeValue(id:20, version:1,attributeValueName:'Crab', attribute:embroideryType,skuPart:'100')
        merMaid = new AttributeValue(id:21, version:1,attributeValueName:'Mermaid', attribute:embroideryType,skuPart:'101')
        jollyRoger = new AttributeValue(id:22, version:1,attributeValueName:'Jolly Roger', attribute:embroideryType,skuPart:'102')
        martini = new AttributeValue(id:23, version:1,attributeValueName:'Martini', attribute:embroideryType,skuPart:'103')
        keg = new AttributeValue(id:24, version:1,attributeValueName:'Keg', attribute:embroideryType,skuPart:'104')
        aaronInitials = new AttributeValue(id:25, version:1,attributeValueName:"Aaron's Initials", attribute:embroideryType,skuPart:'105')
        castaway = new AttributeValue(id:26, version:1,attributeValueName:'Castaway Logo', attribute:embroideryType,skuPart:'106')
		
		harborPantsProduct = new Product(id:1, version:1, productName:'Harbor Pants', skuPart:'099')
		
    }
    
    

}
