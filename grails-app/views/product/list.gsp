
<%@ page import="com.castaway.rigging.Product" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="mainRigging" />
        <title>Product List</title>
        
    </head>
    <body>
        <div class="body">
            <h1>Product List</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="list">
                <table>
                    <thead>
                        <tr>
                   	        <g:sortableColumn property="productName" title="Product Name" />
                   	        <g:sortableColumn property="skuPart" title="Sku Part" />
                   	    	<th>Product Attributes</th>
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${productInstanceList}" status="i" var="productInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td><g:link action="show" id="${productInstance.id}">${fieldValue(bean:productInstance, field:'productName')}</g:link></td>
                            <td>${fieldValue(bean:productInstance, field:'skuPart')}</td>
                            <td>
                            	<ul>
	                                <g:each var="a" in="${productInstance.attributes.sort{it.skuOrder}}">
	                                    <li>${a?.attributeName}</li>
	                                </g:each>
                                </ul>
                           </td>
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${productInstanceTotal}" />
            </div>
        </div>
    </body>
</html>
