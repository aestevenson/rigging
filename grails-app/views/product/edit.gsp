
<%@ page import="com.castaway.rigging.Product" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="mainRigging" />
        <title>Edit Product</title>
    </head>
    <body>
        <div class="body">
            <h1>Edit Product</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${productInstance}">
            <div class="errors">
                <g:renderErrors bean="${productInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form method="post" >
                <input type="hidden" name="id" value="${productInstance?.id}" />
                <input type="hidden" name="version" value="${productInstance?.version}" />
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="productDescription">Product Description:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:productInstance,field:'productDescription','errors')}">
                                    <input type="text" id="productDescription" name="productDescription" value="${fieldValue(bean:productInstance,field:'productDescription')}"/>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="productName">Product Name:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:productInstance,field:'productName','errors')}">
                                    <input type="text" id="productName" name="productName" value="${fieldValue(bean:productInstance,field:'productName')}"/>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="skuPart">Sku Part:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:productInstance,field:'skuPart','errors')}">
                                    <input type="text" id="skuPart" name="skuPart" value="${fieldValue(bean:productInstance,field:'skuPart')}" />
                                </td>
                            </tr> 
                            
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="attributes">Attributes:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:productInstance,field:'attributes','errors')}">
									<ul>
										<g:each var="a" in="${productInstance?.attributes?}">
										    <li><g:link controller="attribute" action="show" id="${a.id}">${a?.encodeAsHTML()}</g:link></li>
										</g:each>
									</ul>
									<g:link controller="attribute" params="['product.id':productInstance?.id]" action="create">Manage Product Attributes</g:link>
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
