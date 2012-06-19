<%@ page import="com.castaway.rigging.Product" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="mainRigging" />
        <title>Manage Product Attributes</title>         
    </head>
    <body>
        <div class="body">
                <h1>Manage Product Attributes</h1>
            
            	<g:form action="saveAttributes">	
            	<input type="hidden" name="id" value="${productInstance?.id}" />
	            <g:if test="${flash.message}">
	            <div class="message">${flash.message}</div>
	            </g:if>
	            <g:hasErrors bean="${productInstance}">
	            <div class="errors">
	                <g:renderErrors bean="${productInstance}" as="list" />
	            </div>
	            </g:hasErrors>
                <table>
                    <thead>
                        <tr>
                            <th>Select</th>
                            <g:sortableColumn property="skuOrder" title="Sku Order" />
                   	        <g:sortableColumn property="attributeName" title="Attribute Name" />
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${attributeInstanceList}" status="i" var="attributeInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
							<td><g:checkBox name="attributeCheckBoxes" value="${attributeInstance.id}" checked="${productInstance?.attributes?.contains(attributeInstance)}"/></td>
							<td>${fieldValue(bean:attributeInstance, field:'skuOrder')}</td>                           
                            <td><g:link action="show" controller="attribute" id="${attributeInstance.id}">${fieldValue(bean:attributeInstance, field:'attributeName')}</g:link></td>
                        </tr>
                    </g:each>
                    </tbody>
                </table>
                <div class="buttons">
                    <span class="button"><input class="save" type="submit" value="Save Attributes" /></span>
                </div>
            </g:form>
        </div>
    </body>
</html>