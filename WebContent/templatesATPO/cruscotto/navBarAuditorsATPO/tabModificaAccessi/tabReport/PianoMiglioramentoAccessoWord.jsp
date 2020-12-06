<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page import="it.tecnet.crs.session.DatiUtente"%>
<%@ page import="org.apache.struts2.ServletActionContext" %>
<%@ page import="java.util.*" %>

<%
   HttpServletRequest r = ServletActionContext.getRequest();
   DatiUtente user= (DatiUtente)r.getSession().getAttribute("DatiUtente");
%>

<!-- CONTENUTO TAB  REPORT -->
<div class="form-group form-md-line-input">
	<button  type="button" class="btn blue"  onclick="generateReportPianoMiglioramentoWord()">Genera Word Piani Miglioramento</button>
</div>
 
