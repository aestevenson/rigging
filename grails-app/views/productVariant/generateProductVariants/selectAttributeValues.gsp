<%@ page import="com.castaway.rigging.Product" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="mainRigging" />
        <title>Generate Product Variants</title>         
    </head>
    <body>
        <div class="body">
                <h1>Generate Product Variants For Product:<b> ${productInstance.productName}</b> </h1>
                <h1>Product SKU Part: ${productInstance.skuPart}</h1>
                <g:if test="${flash.message}">
                    <div class="message">${flash.message}</div>
                </g:if>
                <g:form action="generateProductVariants">
                     
                    <table>
	                    <th>Value Name</th>
	                    <th>Sku Part Order</th>
	                    <g:each in="${productInstance.attributes.sort{it.skuOrder}}" status="i" var="attributeInstance">
	 
	                        <tr>
		                        <td style="font-weight: bold;">${attributeInstance.attributeName}</td>
		                        <td style="font-weight: bold;">${attributeInstance.skuOrder}</td>
	                        </tr>
	                        <tr>
	                            <td colspan="2">
		                            <g:select name="attributes.${attributeInstance.attributeName}" 
		                            from="${attributeInstance.attributeValues.sort{it.skuPart}}"
		                            multiple="multiple"
		                            optionKey="id"
		                            optionValue="${attributeValueName}"/>
	                            </td>
	                        </tr>
	                     </g:each>
                     </table>
                    <g:submitButton class="cancel" event="cancel" value="Cancel"/>
                    <g:submitButton class="edit" event="next" value="Next"/>
                </g:form>
        </div>
    </body>
</html>