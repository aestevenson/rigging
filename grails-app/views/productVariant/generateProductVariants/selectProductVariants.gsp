
<%@ page import="com.castaway.rigging.ProductVariant" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="mainRigging" />
        <g:javascript library="jquery"/>
        <title>Select Final Products</title>
    </head>
    <body>
        <div class="body">
            <h1>Product Variant List</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
	        <g:form action="generateProductVariants">
	            <div class="list">
	                <table>
	                    <thead>
	                        <tr>
	                            <th>Select</th>
	                   	        <th>Status</th>
	                   	        <th>Full SKU</th> 
	                   	        <g:each in="${productInstance.attributes.sort{it.skuOrder}}" status="j" var="productAttribute">
	                   	            <th>${productAttribute.attributeName}</th>
	                   	        </g:each>
	                        </tr>
	                    </thead>
	                    <tbody>
	                    <div id='productVariantTableEltId'>
	                    <g:each in="${productVariantInstanceList}" status="i" var="productVariantInstance">
	                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
	                            <td>
                                    <g:checkBox name="selectedProductVariants" value="${productVariantInstance.fullSKU}"/>
                                </td>
		                        <td>
		                            <g:if test="${productVariantInstance.validate()}">
		                                <img id="ok" title="SKU is unique" src="${createLinkTo(dir: 'images', file: 'yesChrome.png')}" alt="valid"/>
		                            </g:if>
		                            <g:else>
		                                <img id="ok" title="SKU already exists" src="${createLinkTo(dir: 'images', file: 'noChrome.png')}" alt="invalid"/>
		                            </g:else>                               
		                        
		                        </td>
		                        <td><g:textField name="productVariantSKUs.${productVariantInstance.fullSKU}" value="${productVariantInstance.fullSKU}"/></td>
		                        <g:each var="a" in="${productVariantInstance.attributeValues}">
		                            <td>${a?.attributeValueName}</td>
		                        </g:each>
	                        </tr>
	                    </g:each>
	                    </div>
	                    </tbody>
	                </table>
	                <div class="buttons">
	                    <span class="button"><input onClick="checkNone()" value="Select None"/></span>
	                    <span class="button"><input onClick="checkAll()" value="Select All"/></span>
	                    <span class="button"><g:submitButton event="cancel" value="Cancel"/></span>
	                    <span class="button"><g:submitButton event="previous" value="Previous"/></span>
	                    <span class="button"><g:submitButton event="next" value="Next"/></span>
	                </div>
	            </div>
	        </g:form>
        </div>
    </body>
</html>
