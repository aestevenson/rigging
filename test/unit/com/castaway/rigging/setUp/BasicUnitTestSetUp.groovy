package com.castaway.rigging.setUp;

import grails.test.GrailsUnitTestCase;
import com.castaway.rigging.Attribute;
import com.castaway.rigging.AttributeValue;


class BasicUnitTestSetUp extends GrailsUnitTestCase {
    
    def color = new Attribute(attributeName:'Color', attributeDescription:'how do you describe color to a blind person?', skuOrder:4, skuPartLength:2)
    def waistSize = new Attribute(attributeName:'Waist Size', attributeDescription:'', skuOrder:2, skuPartLength:2)
    def pantLength = new Attribute(attributeName:'Pant Length', attributeDescription:'', skuOrder:3, skuPartLength:3)
    def skirtSize = new Attribute(attributeName:'Skirt Size', attributeDescription:'', skuOrder:2, skuPartLength:2)
    def embroideryType = new Attribute(attributeName:'Embroidery Type', attributeDescription:'', skuOrder:5, skuPartLength:3)
    
    def red = new AttributeValue(attributeValueName:'red', attribute:color,skuPart:'01')
    def blue = new AttributeValue(attributeValueName:'blue', attribute:color,skuPart:'02')
    def green = new AttributeValue(attributeValueName:'green', attribute:color,skuPart:'03')
    def khakhi = new AttributeValue(attributeValueName:'khakhi', attribute:color,skuPart:'04')
    
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
    
    protected void setUp() {
        super.setUp()
        
    }
    
    

}
