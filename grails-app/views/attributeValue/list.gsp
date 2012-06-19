
<%@ page import="com.castaway.rigging.AttributeValue" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="mainRigging" />
        <title>AttributeValue List</title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><g:link class="create" action="create">New Attribute Value</g:link></span>
        </div>
        <div class="body">
            <h1>Attribute Value List</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="list">
                <table>
                    <thead>
                        <tr>
                        
                   	        <g:sortableColumn property="attribute" title="Attribute" />
                        
                   	        <g:sortableColumn property="attributeValueDescription" title="Attribute Value Description" />
                        
                   	        <g:sortableColumn property="attributeValueName" title="Attribute Value Name" />
                        
                   	        <g:sortableColumn property="skuPart" title="Sku Part" />
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${attributeValueInstanceList}" status="i" var="attributeValueInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td>${fieldValue(bean:attributeValueInstance, field:'attribute')}</td>
                        
                            <td>${fieldValue(bean:attributeValueInstance, field:'attributeValueDescription')}</td>
                        
                            <td><g:link action="show" id="${attributeValueInstance.id}">${fieldValue(bean:attributeValueInstance, field:'attributeValueName')}</g:link></td>
                        
                            <td>${fieldValue(bean:attributeValueInstance, field:'skuPart')}</td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${attributeValueInstanceTotal}" />
            </div>
        </div>
    </body>
</html>
