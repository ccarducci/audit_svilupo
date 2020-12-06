<%@ page import="java.util.*" %>
<%@ page import="it.tecnet.crs.audit.web.dto.RisultatoRegolaCampagnaCampioneDto" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<div class="portlet-body">
  			<s:iterator value="%{mapRisultati}" var="regole">
 				<s:set var="regolaKey" value="#regole.key"/>
    			<s:set var="regolaValue" value="#regole.value"/>
    														
        		
        		<table  class="table table-striped table-bordered table-advance table-hover" id=percentualeNotificaVerbale>
    				<thead >    														
    					<tr> 
      						<th colspan="5" style="background-color: #225B92; color:white; text-align:center">
      							<s:property value="#regolaValue[0].descrizione"/>
      						</th>
   						</tr>
					</thead>
					<tbody>
    					<tr>
							
							<th></th>
      						<th>RES1</th>

      						<th>RES2</th>

      						<th>RES3</th>

      						<th>RES4</th>
    					</tr>
						<s:iterator value="%{regolaValue}" var="r">
						<tr>
            				<td><s:property value="#r.tipo"/></td>									
            				<td><s:property value="#r.res1"/></td>
            				<td><s:property value="#r.res2"/></td>
            				<td><s:property value="#r.res3"/></td>
            				<td><s:property value="#r.res4"/></td>
         				</tr>
						</s:iterator>

  					</tbody>
		</table>
   </s:iterator>
  
</div>

 
 

  
  
   
