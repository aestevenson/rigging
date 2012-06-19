
<%@ page import="com.castaway.rigging.ProductVariant" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="mainRigging" />
        <title>Edit ProductVariant</title>
    </head>
    <body>
        <div class="body">
            <h1>Edit ProductVariant</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${productVariantInstance}">
            <div class="errors">
                <g:renderErrors bean="${productVariantInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form method="post" >
                <input type="hidden" name="id" value="${productVariantInstance?.id}" />
                <input type="hidden" name="version" value="${productVariantInstance?.version}" />
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="attributeValues">Attribute Values:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:productVariantInstance,field:'attributeValues','errors')}">
                                    <g:select name="attributeValues"
from="${com.castaway.rigging.AttributeValue.list()}"
size="5" multiple="yes" optionKey="id"
value="${productVariantInstance?.attributeValues}" />

                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="fullSKU">Full SKU:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:productVariantInstance,field:'fullSKU','errors')}">
                                    <input type="text" id="fullSKU" name="fullSKU" value="${fieldValue(bean:productVariantInstance,field:'fullSKU')}" />
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="productVariantName">Product Variant Name:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:productVariantInstance,field:'productVariantName','errors')}">
                                    <input type="text" id="productVariantName" name="productVariantName" value="${fieldValue(bean:productVariantInstance,field:'productVariantName')}"/>
                                </td>
                            </tr> 
                        
                        </tbody>
                    </table>
                </div>
                <div class="buttons">
                    <span class="button"><g:actionSubmit class="save" value="Update" /></span>
                    <span class="button"><g:actionSubmit class="delete" onclick="return confirm('Are you sure?');" value="Delete" /></span>
                </div>
            </g:form>
        </div>
    </body>
</html>
