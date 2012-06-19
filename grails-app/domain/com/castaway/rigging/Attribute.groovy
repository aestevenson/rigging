package com.castaway.rigging

class Attribute implements Serializable {
	
	/**
	 * attributes added to support IDE 'mockDomain'
	 */
	Long id
	Long version
	
	String attributeName
	String attributeDescription
	int skuOrder
	int skuPartLength
	
	static hasMany = [attributeValues:AttributeValue]

    static constraints = {
		attributeName(blank:false)
		attributeDescription(nullable:true)
		skuOrder(range:2..50, nullable:false, min:1)
		skuPartLength(nullable:false)
		attributeValues(validator: { val, obj ->
			if (val.any{it.skuPart.length() != obj.skuPartLength})
				return ['attributevalue.skulength.invalid.length'] 
		})
    }
	
	static mapping = {
	    sort "skuOrder"
	}
	
	String toString() {
		attributeName
	}
	
	def boolean equals(Object o) {
        if (o instanceof Attribute) {
            if (o.id == this.id) {
                return true
            }
        }
        else return false
    }

}
