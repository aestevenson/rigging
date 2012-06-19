
<%@ page import="com.castaway.rigging.Attribute" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="mainRigging" />
        <title>Show Attribute</title>
    </head>
    <body>
        <div class="body">
            <h1>Show Attribute</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="dialog">
                <table>
                    <tbody>
                        <tr class="prop">
                            <td valign="top" class="name">Id:</td>
                            <td valign="top" class="value">${fieldValue(bean:attributeInstance, field:'id')}</td>
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Attribute Name:</td>
                            <td valign="top" class="value">${fieldValue(bean:attributeInstance, field:'attributeName')}</td>
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Attribute Description:</td>
                            <td valign="top" class="value">${fieldValue(bean:attributeInstance, field:'attributeDescription')}</td>
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Sku Order:</td>
                            <td valign="top" class="value">${fieldValue(bean:attributeInstance, field:'skuOrder')}</td>
                        </tr>
                        
                        <tr class="prop">
                            <td valign="top" class="name">Sku Length:</td>
                            <td valign="top" class="value">${fieldValue(bean:attributeInstance, field:'skuPartLength')}</td>
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Attribute Values:</td>
                            <td  valign="top" style="text-align:left;" class="value">
                                <ul>
                                <g:each var="a" in="${attributeInstance.attributeValues}">
                                    <li><g:link controller="attributeValue" action="show" id="${a.id}">${a?.encodeAsHTML()}</g:link></li>
                                </g:each>
                                </ul>
                            </td>
                            
                        </tr>
                    
                    </tbody>
                </table>
            </div>
            <div class="buttons">
                <g:form>
                    <input type="hidden" name="id" value="${attributeInstance?.id}" />
                    <span class="button"><g:actionSubmit class="edit" value="Edit" /></span>
                    <span class="button"><g:actionSubmit class="delete" onclick="return confirm('Are you sure?');" value="Delete" /></span>
                </g:form>
            </div>
        </div>
    </body>
</html>
