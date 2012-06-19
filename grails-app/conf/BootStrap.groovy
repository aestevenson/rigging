import com.castaway.rigging.*
import grails.util.Environment

class BootStrap {

    def init = { servletContext ->

        switch (Environment.current) {
        
            case Environment.DEVELOPMENT:
                // createAdminUserIfRequired()
            println "Seeding Data"
            creator()
            println "Data seed Complete"
            break;
        
            case Environment.PRODUCTION:
                println "No special configuration required"
                    break;
        }
    }
    
    def destroy = {
    }
    
    
    void creator() {
        def color = new Attribute(attributeName:'Color', attributeDesccription:'how do you describe color to a blind person?', skuOrder:4, skuPartLength:2).save()
        def waistSize = new Attribute(attributeName:'Waist Size', attributeDescription:'', skuOrder:2, skuPartLength:2).save()
        def pantLength = new Attribute(attributeName:'Pant Length', attributeDescription:'', skuOrder:3, skuPartLength:3).save()
        def skirtSize = new Attribute(attributeName:'Skirt Size', attributeDescription:'', skuOrder:2, skuPartLength:2).save()
        def embroideryType = new Attribute(attributeName:'Embroidery Type', attributeDescription:'', skuOrder:5, skuPartLength:3).save()
        
        def red = new AttributeValue(attributeValueName:'red', attribute:color,skuPart:'01').save()
        def blue = new AttributeValue(attributeValueName:'blue', attribute:color,skuPart:'02').save()
        def green = new AttributeValue(attributeValueName:'green', attribute:color,skuPart:'03').save()
        def khakhi = new AttributeValue(attributeValueName:'khakhi', attribute:color,skuPart:'04').save()
        
        def thirtyTwoWaist = new AttributeValue(attributeValueName:'32', attribute:waistSize,skuPart:'32').save()
        def thirtyFourWaist = new AttributeValue(attributeValueName:'34', attribute:waistSize,skuPart:'34').save()
        def thirtySixWaist = new AttributeValue(attributeValueName:'36', attribute:waistSize,skuPart:'36').save()
        def thirtyEightWaist = new AttributeValue(attributeValueName:'38', attribute:waistSize,skuPart:'38').save()
        def fortyWaist = new AttributeValue(attributeValueName:'40', attribute:waistSize,skuPart:'40').save()
        def fortyTwoWaist = new AttributeValue(attributeValueName:'42', attribute:waistSize,skuPart:'42').save() 
        
        def thirtyTwoInseam = new AttributeValue(attributeValueName:'32', attribute:pantLength,skuPart:'001').save()
        def thirtyFourInseam = new AttributeValue(attributeValueName:'34', attribute:pantLength,skuPart:'002').save()
        def thirtySixInseam = new AttributeValue(attributeValueName:'36', attribute:pantLength,skuPart:'003').save()
        def thirtyEightInseam = new AttributeValue(attributeValueName:'38', attribute:pantLength,skuPart:'004').save()
        def fortyInseam = new AttributeValue(attributeValueName:'40', attribute:pantLength,skuPart:'005').save()
         
        def twoSkirt = new AttributeValue(attributeValueName:'2', attribute:skirtSize,skuPart:'60').save()
        def fourSkirt = new AttributeValue(attributeValueName:'4', attribute:skirtSize,skuPart:'61').save()
        def sixSkirt = new AttributeValue(attributeValueName:'6', attribute:skirtSize,skuPart:'62').save()
        def eightSkirt = new AttributeValue(attributeValueName:'8', attribute:skirtSize,skuPart:'63').save()
         
        def crab = new AttributeValue(attributeValueName:'Crab', attribute:embroideryType,skuPart:'100').save()
        def merMaid = new AttributeValue(attributeValueName:'Mermaid', attribute:embroideryType,skuPart:'101').save()
        def jollyRoger = new AttributeValue(attributeValueName:'Jolly Roger', attribute:embroideryType,skuPart:'102').save()
        def martini = new AttributeValue(attributeValueName:'Martini', attribute:embroideryType,skuPart:'103').save()
        def keg = new AttributeValue(attributeValueName:'Keg', attribute:embroideryType,skuPart:'104').save()
        def aaronInitials = new AttributeValue(attributeValueName:"Aaron's Initials", attribute:embroideryType,skuPart:'105').save()
        def castaway = new AttributeValue(attributeValueName:'Castaway Logo', attribute:embroideryType,skuPart:'106').save()
    }
    
} 