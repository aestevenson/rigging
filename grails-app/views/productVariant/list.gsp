
<%@ page import="com.castaway.rigging.ProductVariant" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="mainRigging" />
        <title>ProductVariant List</title>
    </head>
    <body>
        <div class="body">
            <h1>ProductVariant List</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="list">
                <table>
                    <thead>
                        <tr>                       
                   	        <g:sortableColumn property="fullSKU" title="Full SKU" />
                   	        <g:sortableColumn property="product" title="Product Name" />
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${productVariantInstanceList}" status="i" var="productVariantInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                            <td><g:link action="show" id="${productVariantInstance.id}">${fieldValue(bean:productVariantInstance, field:'fullSKU')}</g:link></td>
                            <td>${fieldValue(bean:productVariantInstance, field:'product.productName')}</td>
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${productVariantInstanceTotal}" />
            </div>
        </div>
    </body>
</html>
