
<%@ page import="com.castaway.rigging.ProductVariant" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="mainRigging" />
        <title>Select Final Products</title>
    </head>
    <body>
        <div class="body">
            <h1>Product Variant List</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
	        <g:form action="saveProductVariantList" method="post" >
	            <div class="list">
	                <table>
	                    <thead>
	                        <tr>
	                   	        <th>Status</th>
	                   	        <th colspan="2">Full SKU</th> 
	                   	        <g:each in="${session.product.attributes.sort{it.skuOrder}}" status="j" var="productAttribute">
	                   	            <th>${productAttribute.attributeName}</th>
	                   	        </g:each>
	                        </tr>
	                    </thead>
	                    <tbody>
	                    <g:each in="${session.productVariantInstanceList}" status="i" var="productVariantInstance">
	                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
		                        <td>
		                            <g:if test="${productVariantInstance.validate()}">
		                                <img id="ok" src="${createLinkTo(dir: 'images', file: 'yesChrome.png')}" alt="valid"/>
		                            </g:if>
		                            <g:else>
		                                <img id="ok" src="${createLinkTo(dir: 'images', file: 'noChrome.png')}" alt="invalid"/>
		                            </g:else>                               
		                        
		                        </td>
		                        <td>
		                           <g:checkBox name="selectedProductVariants" value="${productVariantInstance.fullSKU}"/>
	                            </td>
		                        <td><g:textField name="productVariantSKUs.${productVariantInstance.fullSKU}" value="${productVariantInstance.fullSKU}"/></td>
		                        <g:each var="a" in="${productVariantInstance.attributeValues}">
		                            <td>${a?.attributeValueName}</td>
		                        </g:each>
	                        </tr>
	                    </g:each>
	                    </tbody>
	                </table>
	                <div class="buttons">
	                    <span class="button"><input value="Select None"/></span>
	                    <span class="button"><input value="Select All"/></span>
	                    <span class="button"><input class="save" type="submit" value="Save" /></span>
	                </div>
	            </div>
	        </g:form>
        </div>
    </body>
</html>
