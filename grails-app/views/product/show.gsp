
<%@ page import="com.castaway.rigging.Product" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="mainRigging" />
        <title>Show Product</title>
    </head>
    <body>
        <div class="body">
            <h1>Show Product</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="dialog">
                <table>
                    <tbody>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Id:</td>
                            <td valign="top" class="value">${fieldValue(bean:productInstance, field:'id')}</td>
                        </tr>
                        
                        <tr class="prop">
                            <td valign="top" class="name">Product Name:</td>
                            <td valign="top" class="value">${fieldValue(bean:productInstance, field:'productName')}</td>
                        </tr>
                                        
                        <tr class="prop">
                            <td valign="top" class="name">Sku Part:</td>
                            <td valign="top" class="value">${fieldValue(bean:productInstance, field:'skuPart')}</td>
                        </tr>
                        
                        <tr class="prop">
                            <td valign="top" class="name">Product Description:</td>
                            <td valign="top" class="value">${fieldValue(bean:productInstance, field:'productDescription')}</td>
                        </tr>
                        <tr class="prop">
                            <td valign="top" class="name">Product Attributes:</td>
                            <td valign="top" class="value">
                                <ul>
                                    <g:each var="a" in="${productInstance.attributes.sort{it.skuOrder}}">
                                        <li style="list-style-type: circle;"><g:link controller="attribute" action="show" id="${a.id}">${a?.attributeName}</g:link></li>
                                    </g:each>
                                </ul>
                            </td>
                            
                        </tr>

                    
                    </tbody>
                </table>
            </div>
            <div class="buttons">
                <g:form>
                    <input type="hidden" name="id" value="${productInstance?.id}" />
                    <span class="button"><g:actionSubmit class="edit" value="Edit" /></span>
                    <span class="button"><g:actionSubmit class="delete" onclick="return confirm('Are you sure?');" value="Delete" /></span>
                	<span class="button"><g:actionSubmit class="edit" value="Manage Product Attributes"/></span>
                </g:form>
                <g:form name="generateProductVariants" controller="productVariant">
                    <input type="hidden" name="id" value="${productInstance?.id}" />
                    <span class="button"><g:actionSubmit class="edit" value="Generate Product Variants"/></span>
                </g:form>
            </div>
        </div>
    </body>
</html>
