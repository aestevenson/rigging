
<%@ page import="com.castaway.rigging.AttributeValue" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="mainRigging" />
        <title>Edit AttributeValue</title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><g:link class="list" action="list">AttributeValue List</g:link></span>
            <span class="menuButton"><g:link class="create" action="create">New AttributeValue</g:link></span>
        </div>
        <div class="body">
            <h1>Edit AttributeValue</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${attributeValueInstance}">
            <div class="errors">
                <g:renderErrors bean="${attributeValueInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form method="post" >
                <input type="hidden" name="id" value="${attributeValueInstance?.id}" />
                <input type="hidden" name="version" value="${attributeValueInstance?.version}" />
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="attribute">Attribute:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:attributeValueInstance,field:'attribute','errors')}">
                                    <g:select optionKey="id" from="${com.castaway.rigging.Attribute.list()}" name="attribute.id" value="${attributeValueInstance?.attribute?.id}" ></g:select>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="attributeValueDescription">Attribute Value Description:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:attributeValueInstance,field:'attributeValueDescription','errors')}">
                                    <input type="text" id="attributeValueDescription" name="attributeValueDescription" value="${fieldValue(bean:attributeValueInstance,field:'attributeValueDescription')}"/>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="attributeValueName">Attribute Value Name:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:attributeValueInstance,field:'attributeValueName','errors')}">
                                    <input type="text" id="attributeValueName" name="attributeValueName" value="${fieldValue(bean:attributeValueInstance,field:'attributeValueName')}"/>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="skuPart">Sku Part:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:attributeValueInstance,field:'skuPart','errors')}">
                                    <input type="text" id="skuPart" name="skuPart" value="${fieldValue(bean:attributeValueInstance,field:'skuPart')}" />
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
