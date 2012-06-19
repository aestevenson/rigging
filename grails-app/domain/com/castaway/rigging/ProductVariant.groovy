package com.castaway.rigging

import java.io.Serializable;

class ProductVariant implements Serializable {
	
	/**
	 * attributes added to support IDE 'mockDomain'
	 */
	Long id
	Long version
	
	Product product
	String fullSKU
	
	static hasMany = [attributeValues:AttributeValue]
	                  
    static constraints = {
		fullSKU(unique:true)
		product()
    }
	
	String toString() {
	    "${product.productName}-fullSKU:${fullSKU},id:${id}"
	}
	
	static mapping = {
	    sort attributeValues:'attribute.skuOrder'
	}
}
