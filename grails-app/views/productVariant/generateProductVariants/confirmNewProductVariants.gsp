
<%@ page import="com.castaway.rigging.ProductVariant" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="mainRigging" />
        <title>Select Final Products</title>
    </head>
    <body>
        <div class="body">
            <h1>Confirm Select Product Variants</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
	        <g:form action="generateProductVariants">
	            <div class="list">
	                <br/>
	                <b>Valid Product Variants</b>
	                <table>
	                    <thead>
	                        <tr>
	                   	        <th>Full SKU</th> 
	                   	        <g:each in="${productInstance.attributes.sort{it.skuOrder}}" status="j" var="productAttribute">
	                   	            <th>${productAttribute.attributeName}</th>
	                   	        </g:each>
	                        </tr>
	                    </thead>
	                    <tbody>
	                    <g:each in="${goodProductVaraiants}" status="i" var="productVariantInstance">
	                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
		                        <td>${productVariantInstance.fullSKU}</td>
		                        <g:each var="a" in="${productVariantInstance.attributeValues}">
		                            <td>${a?.attributeValueName}</td>
		                        </g:each>
	                        </tr>
	                    </g:each>
	                    </tbody>
	                </table>
	                <br/>
	                <g:if test="${badProductVariants.size() > 0}">	                
		                <b>Invalid Product Variants</b>
		                <table>
	                        <thead>
	                            <tr>
	                                <th>Full SKU</th> 
	                                <g:each in="${productInstance.attributes.sort{it.skuOrder}}" status="j" var="productAttribute">
	                                    <th>${productAttribute.attributeName}</th>
	                                </g:each>
	                            </tr>
	                        </thead>
	                        <tbody>
	                        <g:each in="${badProductVariants}" status="i" var="productVariantInstance">
	                            <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
	                                <td>${productVariantInstance.fullSKU}</td>
	                                <g:each var="a" in="${productVariantInstance.attributeValues}">
	                                    <td>${a?.attributeValueName}</td>
	                                </g:each>
	                            </tr>
	                        </g:each>
	                        </tbody>
	                    </table>
	                </g:if>
	                <div class="buttons">
	                    <span class="button"><g:submitButton event="cancel" value="Cancel"/></span>
	                    <span class="button"><g:submitButton event="previous" value="Previous"/></span>
	                    <span class="button"><g:submitButton event="accept" value="Accept"/></span>
	                    <span class="button"><g:submitButton title="Save and Finish Product Variant Generation" 
	                    event="finish" value="Finished"/></span>
	                </div>
	            </div>
	        </g:form>
        </div>
    </body>
</html>
