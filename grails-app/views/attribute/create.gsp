
<%@ page import="com.castaway.rigging.Attribute" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="mainRigging" />
        <title>Create Attribute</title>         
    </head>
    <body>
        <div class="body">
            <h1>Create Attribute</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${attributeInstance}">
            <div class="errors">
                <g:renderErrors bean="${attributeInstance}" as="list" />
            </div>
            </g:hasErrors>

            <g:form action="save" method="post" >
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
                                    <input type="integer" id="skuPartLength" name="skuPartLength"  value="${fieldValue(bean:attributeInstance, field: 'skuPartLength')}"/>
                                </td>
                            </tr>
                        
                        </tbody>
                    </table>
                </div>
                <div class="buttons">
                    <span class="button"><input class="save" type="submit" value="Create" /></span>
                </div>
            </g:form> </td></tr></table>
        </div>
    </body>
</html>
