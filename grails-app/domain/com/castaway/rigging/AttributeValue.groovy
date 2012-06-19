package com.castaway.rigging

import java.io.Serializable;

class AttributeValue implements Serializable {
	
	/**
	 * attributes added to support IDE 'mockDomain'
	 */
	Long id
	Long version
	
	String attributeValueName
	String skuPart
	String attributeValueDescription
	
	static belongsTo = [attribute:Attribute]

    static constraints = {
		attributeValueName(nullable:false, blank:false, unique:'attribute')
		skuPart(unique:'attribute')
		attributeValueDescription(blank:true, nullable:true)
		attribute(validator: {attribute, attributeValue ->
			return attribute.skuPartLength == attributeValue.skuPart.length() })
    }
	
	String toString() {
		attributeValueName+':'+skuPart
	}
	
}
