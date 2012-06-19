<html>
    <head>
        <title><g:layoutTitle default="Castaway Clothing Rigging" /></title>
        <link rel="stylesheet" href="${createLinkTo(dir:'css',file:'main.css')}" />
        <link rel="shortcut icon" href="${createLinkTo(dir:'images',file:'favicon.ico')}" type="image/x-icon" />
        <g:layoutHead />
        <g:javascript library="application" />				
    </head>
    <body>
        
        <table>
            <tr>
                <td colspan="2">
	                <div id="spinner" class="spinner" style="display:none;">
	                <img src="${createLinkTo(dir:'images',file:'spinner.gif')}" alt="Spinner" />
	                </div>
	                <div class="logo"><img src="${createLinkTo(dir:'images',file:'')}" alt="Castaway Clothing Banner Pic Here" /></div>   
	            </td>
            </tr>
			<tr>	
				<td>
				    <g:layoutBody />	
				</td>	
			</tr>
        </table>
    </body>	
</html>