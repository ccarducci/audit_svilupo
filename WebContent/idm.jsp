<%@page import="java.util.Hashtable,org.apache.commons.lang3.StringUtils,java.util.Enumeration"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<META HTTP-EQUIV="HTTP_INPS_ACCOUNT_WINDOWS" CONTENT="pippo">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<% 
	String x= request.getParameter("test") ;
	if(x==null || !x.equals("666")){
		response.sendRedirect("/CruscottoAuditAtpoWebWeb/");
	}
	

%>
	
	<!-- <input type="hidden" name="actionMode" value="login"  >  
	Matricola: <input type="text" name="INPS-matricola" value="123" ><br>
	Email: <input type="text" name="INPS-email" value="inps@inps.it" ><br>
	Nominativo: <input type="text" name="INPS-nominativo" value="ROSSI MARIO" ><br>
	Nome: <input type="text" name="INPS-nome" value="MARIO" ><br>
	Cognome: <input type="text" name="INPS-cognome" value="ROSSI" ><br>
	Cod Sede: <input type="text" name="INPS-codice-sede" value="445" ><br>
	Cod Sede SAP: <input type="text" name="INPS-codice-sede-SAP" value="4456" ><br>
	Ruoli: <input type="text" name="INPS-Ruoli" value="cn=A7660:P11617,dc=inps,dc=it" ><br>
	Ruoli2: <input type="text" name="INPS-Ruoli" value="cn=A124:P346,dc=inps,dc=it" ><br>  
-->	

<div style="width: 100%, display: table; border-collapse: separate; border-spacing: 15px;">
    <div style="display:table-row; ">
        <span style="display:table-cell;" ></span>
        <span style="display:table-cell;" >USERNAME</span>
        <span style="display:table-cell;" >RUOLO</span>
        <span style="display:table-cell;" ></span>
    </div>
    
    <form style="display:table-row;" method="get" action="/CruscottoAuditAtpoWebWeb/index">
    	<input type="hidden" name="INPS-nome" value="MARIO" ><br>
		<input type="hidden" name="INPS-cognome" value="ROSSI" ><br>
        <span style="display:table-cell;">
        	<label style="word-wrap:break-word">dirigente.01</label>
        	<input name="INPS-account-windows" type="hidden" value="dirigente.01">
        </span>
        <span style="display:table-cell;">
        	<label style="word-wrap:break-word">Dirigente</label>
    		<input name="INPS-Ruoli" type="checkbox" value="cn=A7776:P11978,dc=inps,dc=it" checked />
    		<label style="word-wrap:break-word">Delegato01</label>
    		<input name="INPS-Ruoli" type="checkbox" value="cn=A7776:P11979,dc=inps,dc=it" />
    		<label style="word-wrap:break-word">Auditors01</label>
    		<input name="INPS-Ruoli" type="checkbox" value="cn=A7776:P11980,dc=inps,dc=it" />
        </span>
        <span style="display:table-cell;">
        	<input type="submit" value="login">
        </span>
    </form>
    
    <form style="display:table-row;" method="get" action="/CruscottoAuditAtpoWebWeb/index">
        <input type="hidden" name="INPS-nome" value="MARIO" ><br>
		<input type="hidden" name="INPS-cognome" value="ROSSI" ><br>
        <span style="display:table-cell;">
        	<label style="word-wrap:break-word">delegato.01</label>
        	<input name="INPS-account-windows" type="hidden" value="delegato.01">
        </span>
        <span style="display:table-cell;">
        	<label style="word-wrap:break-word">Delegato01</label>
    		<input name="INPS-Ruoli" type="checkbox" value="cn=A7776:P11979,dc=inps,dc=it"  checked/>
    		<label style="word-wrap:break-word">Auditors01</label>
    		<input name="INPS-Ruoli" type="checkbox" value="cn=A7776:P11980,dc=inps,dc=it" />
        </span>
        <span style="display:table-cell;">
        	<input type="submit" value="login">
        </span>
    </form>
    
    <form style="display:table-row;" method="get" action="/CruscottoAuditAtpoWebWeb/index">
        <input type="hidden" name="INPS-nome" value="MARIO" ><br>
		<input type="hidden" name="INPS-cognome" value="ROSSI" ><br>
        <span style="display:table-cell;">
        	<label style="word-wrap:break-word">auditors.01</label>
        	<input name="INPS-account-windows" type="hidden" value="auditors.01">
        </span>
        <span style="display:table-cell;">
        	<label style="word-wrap:break-word">Auditors01</label>
    		<input name="INPS-Ruoli" type="checkbox" value="cn=A7776:P11980,dc=inps,dc=it" checked/>
        </span>
        <span style="display:table-cell;">
        	<input type="submit" value="login">
        </span>
    </form>
    
     <form style="display:table-row;" method="get" action="/CruscottoAuditAtpoWebWeb/index">
        <input type="hidden" name="INPS-nome" value="Elio" ><br>
		<input type="hidden" name="INPS-cognome" value="Scardaci" ><br>
        <span style="display:table-cell;">
        	<label style="word-wrap:break-word">escardaci</label>
        	<input name="INPS-account-windows" type="hidden" value="escardaci">
        </span>
        <span style="display:table-cell;">
        	<label style="word-wrap:break-word">Auditors01</label>
    		<input name="INPS-Ruoli" type="checkbox" value="cn=A7776:P11980,dc=inps,dc=it" checked/>
        </span>
        <span style="display:table-cell;">
        	<input type="submit" value="login">
        </span>
    </form>
     <form style="display:table-row;" method="get" action="/CruscottoAuditAtpoWebWeb/index">
        <input type="hidden" name="INPS-nome" value="Elio" ><br>
		<input type="hidden" name="INPS-cognome" value="Scardaci" ><br>
        <span style="display:table-cell;">
        	<label style="word-wrap:break-word">escardaci</label>
        	<input name="INPS-account-windows" type="hidden" value="escardaci">
        </span>
        <span style="display:table-cell;">
        	<label style="word-wrap:break-word">Dirigente</label>
    		<input name="INPS-Ruoli" type="checkbox" value="cn=A7776:P11978,dc=inps,dc=it" checked/>
        </span>
        <span style="display:table-cell;">
        	<input type="submit" value="login">
        </span>
    </form>
    
    
     <form style="display:table-row;" method="get" action="/CruscottoAuditAtpoWebWeb/index">
        <input type="hidden" name="INPS-nome" value="Elio" ><br>
		<input type="hidden" name="INPS-cognome" value="Scardaci" ><br>
        <span style="display:table-cell;">
        	<label style="word-wrap:break-word">escardaci</label>
        	<input name="INPS-account-windows" type="hidden" value="escardaci">
        </span>
        <span style="display:table-cell;">
        	<label style="word-wrap:break-word">Amministratore</label>
    		<input name="INPS-Ruoli" type="checkbox" value="cn=A7776:P12106,dc=inps,dc=it" checked/>
        </span>
        <span style="display:table-cell;">
        	<input type="submit" value="login">
        </span>
    </form>
    
</div>

		
<table>

	<%
		Enumeration headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String key = (String) headerNames.nextElement();
            String value = request.getHeader(key);
	 %>
	<tr>          
		<td><%=key%></td>
		<td><%=value%></td>       
	</tr>    
	<%}%>
</table>

<table>
	<%String account = request.getHeader("INPS-account-windows"); %>
	<p>account:<%=account %></p>
	<span>INPS-Ruoli</span>
	<%
		Enumeration roles =  request.getHeaders("INPS-Ruoli"); 
       while (roles.hasMoreElements()){
				String value = (String)roles.nextElement();
				String[] ruolo = value.split(",|:|=");
	 %>
	<tr>          
		<td><%=ruolo[1]%></td>
		<td><%=ruolo[2]%></td>       
	</tr>    
	<%}%>
</table>

</body>
</html>