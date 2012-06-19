package com.castaway.rigging

class Product implements Serializable {
	
	/**
	 * id and version added to support IDE 'mockDomain'
	 */
	Long id
	Long version
	
	String productName
	String skuPart
	String productDescription
	String externalLink
	
	static hasMany = [attributes:Attribute]

    static constraints = {
		productName(size:2..100, unique: true)
		skuPart(unique:true, blank:false)
    	externalLink(url:true, blank:true, nullable:true)
    	productDescription()
	}
	
	static mapping = {
//	    attributes sort:'skuOrder'   //cannot do this GRAILS-4089
	}
	
	
	def boolean equals(Object o) {
	    if (o instanceof Product) {
	        if (o.id == this.id) {
	            return true
	        }
	    }
	    else return false
	}
	
	def String toString() {
	    productName
	}
}
