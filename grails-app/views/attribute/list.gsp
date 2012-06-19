
<%@ page import="com.castaway.rigging.Attribute" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="mainRigging" />
        <title>Attribute List</title>
    </head>
    <body>
        <div class="body">
            <h1>Attribute List</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="list">
                <table>
                    <thead>
                        <tr>
                                               
                   	        <g:sortableColumn property="attributeName" title="Attribute Name" />
                        
                   	        <g:sortableColumn property="attributeDescription" title="Attribute Description" />
                        
                   	        <g:sortableColumn property="skuOrder" title="Sku Order" />
                   	        
                   	        <g:sortableColumn property="skuPartLength" title="Sku Length" />
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${attributeInstanceList}" status="i" var="attributeInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td><g:link action="show" id="${attributeInstance.id}">${fieldValue(bean:attributeInstance, field:'attributeName')}</g:link></td>
                        
                            <td>${fieldValue(bean:attributeInstance, field:'attributeDescription')}</td>
                        
                            <td>${fieldValue(bean:attributeInstance, field:'skuOrder')}</td>
                            
                            <td>${fieldValue(bean:attributeInstance, field:'skuPartLength')}</td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${attributeInstanceTotal}" />
            </div>
        </div>
    </body>
</html>
