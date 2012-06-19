
<%@ page import="com.castaway.rigging.Attribute" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="mainRigging" />
        <title>Edit Attribute</title>
    </head>
    <body>
        <div class="body">
            <h1>Edit Attribute</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${attributeInstance}">
            <div class="errors">
                <g:renderErrors bean="${attributeInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form method="post" >
                <input type="hidden" name="id" value="${attributeInstance?.id}" />
                <input type="hidden" name="version" value="${attributeInstance?.version}" />
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="attributeName">Attribute Name:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:attributeInstance,field:'attributeName','errors')}">
                                    <input type="text" id="attributeName" name="attributeName" value="${fieldValue(bean:attributeInstance,field:'attributeName')}"/>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="attributeDescription">Attribute Description:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:attributeInstance,field:'attributeDescription','errors')}">
                                    <input type="text" id="attributeDescription" name="attributeDescription" value="${fieldValue(bean:attributeInstance,field:'attributeDescription')}"/>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="skuOrder">Sku Order:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:attributeInstance,field:'skuOrder','errors')}">
                                    <g:select from="${2..50}" id="skuOrder" name="skuOrder" value="${attributeInstance?.skuOrder}" ></g:select>
                                </td>
                            </tr> 
                            
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="skuPartLength">Sku Part Length:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:attributeInstance,field:'skuPartLength','errors')}">
                                    <input type="integer" id="skuPartLength" name="skuPartLength" value="${attributeInstance?.skuPartLength}" />
                                </td>
                            </tr> 
                            
                            
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="attributeValues">Attribute Values:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:attributeInstance,field:'attributeValues','errors')}">
                                    
									<ul>
										<g:each var="a" in="${attributeInstance?.attributeValues?}">
										    <li><g:link controller="attributeValue" action="show" id="${a.id}">${a?.encodeAsHTML()}</g:link></li>
										</g:each>
									</ul>
									<g:link controller="attributeValue" params="['attribute.id':attributeInstance?.id]" action="create">Add AttributeValue</g:link>
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
