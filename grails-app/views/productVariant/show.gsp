
<%@ page import="com.castaway.rigging.ProductVariant" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>Show ProductVariant</title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><g:link class="list" action="list">ProductVariant List</g:link></span>
            <span class="menuButton"><g:link class="create" action="create">New ProductVariant</g:link></span>
        </div>
        <div class="body">
            <h1>Show ProductVariant</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="dialog">
                <table>
                    <tbody>

                    
                        <tr class="prop">
                            <td valign="top" class="name">Id:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:productVariantInstance, field:'id')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Attribute Values:</td>
                            
                            <td  valign="top" style="text-align:left;" class="value">
                                <ul>
                                <g:each var="a" in="${productVariantInstance.attributeValues}">
                                    <li><g:link controller="attributeValue" action="show" id="${a.id}">${a?.encodeAsHTML()}</g:link></li>
                                </g:each>
                                </ul>
                            </td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Full SKU:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:productVariantInstance, field:'fullSKU')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Product Variant Name:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:productVariantInstance, field:'productVariantName')}</td>
                            
                        </tr>
                    
                    </tbody>
                </table>
            </div>
            <div class="buttons">
                <g:form>
                    <input type="hidden" name="id" value="${productVariantInstance?.id}" />
                    <span class="button"><g:actionSubmit class="edit" value="Edit" /></span>
                    <span class="button"><g:actionSubmit class="delete" onclick="return confirm('Are you sure?');" value="Delete" /></span>
                </g:form>
            </div>
        </div>
    </body>
</html>
