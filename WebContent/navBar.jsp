<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page import="it.tecnet.crs.web.beans.AppUser;"%>

<%
   AppUser user= (AppUser)request.getSession().getAttribute("AppUser");
   String ruoloAttivo=user.getRuoloAttivo();
%> 
 
<div class="page-sidebar-wrapper">
	
<div class="page-sidebar"><!-- BEGIN SIDEBAR MENU -->
<ul class="page-sidebar-menu" data-keep-expanded="false"
	data-auto-scroll="true" data-slide-speed="200">
	<!-- <li  id="catalogo">
		
		<a href="#" onclick="clickMenu('catalogo'); loadDiv('/CruscottoAuditAtpoWebWeb/index.html', 'appView', null, null );">
		<i class="icon-home"></i> <span class="title">Catalogo</span> </a>
	</li> -->






	
<%
if(ruoloAttivo.toLowerCase().indexOf("dirigente") != -1 | ruoloAttivo.toLowerCase().indexOf("delegato") != -1 ){
%>

	<li id="audit">
		<a href="javascript:;"> 
			<i class="icon-list"></i> 
			<span class="title">Organizzazione Audit</span> 
			<span class="arrow "></span> 
		</a>
		<ul class="sub-menu">
		
		<li id="generale">
		<!-- file js: cruscottoScript.js -->
			
			<a href="#" onclick="clickMenu('audit-generale'); loadDiv('/CruscottoAuditAtpoWebWeb/auAudit', 'appView', null, 'initAuditGenerale' );">
             Generale</a>
         </li>
             
			<li id="campagna">
				<a href="#" onclick="clickMenu('audit-campagna'); loadDiv('/CruscottoAuditAtpoWebWeb/initCampagna', 'appView', null, 'initAuditCampagna' );">
             	Campagna</a>
			
			</li>
			
			<li id="sedi">
				<!-- file js: cruscottoScript.js -->
				<!--  <a href="#" onclick="clickMenu('audit-campagna'); loadDiv('/CruscottoAuditAtpoWebWeb/auditCampagna', 'appView', null, 'initAudit' );">
            	 Generale</a>		-->
				<a href="#" onclick="clickMenu('audit-sedi'); loadDiv('/CruscottoAuditAtpoWebWeb/initAccessoAudit', 'appView', null, 'initAuditAccesso' );">
             	Sedi</a>
			
			</li>
		</ul>
	</li>
<%
}
%>



<%
if(ruoloAttivo.toLowerCase().indexOf("amministratore") != -1 ){
%>

	<li id="areaamministrativa">
		<a href="javascript:;"> 
			<i class="icon-badge"></i> 
			<span class="title">Area Amministrativa</span> 
			<span class="arrow "></span>  
		</a>
		<ul class="sub-menu">
		
		<li id="gestioneutenze">
		<!-- file js: cruscottoScript.js -->
			
			<a href="#" onclick="clickMenu('areaamministrativa-gestioneutenze'); loadDiv('/CruscottoAuditAtpoWebWeb/usersList', 'appView', null, 'initUserGenerale' );">
             Gestione Utenze</a>
         </li>
         
         
          <li id="gestionesogliecolori">
			<a href="#" onclick="clickMenu('areaamministrativa-gestionesogliecolori'); loadDiv('/CruscottoAuditAtpoWebWeb/sogliecoloriList', 'appView', null, 'initIsncGenerale' );">
             Gestione Soglie e Colori</a>
         </li>   
          <li id="gestionetipologiche">
			<a href="#" onclick="clickMenu('areaamministrativa-gestionetipologiche'); loadDiv('/CruscottoAuditAtpoWebWeb/tipologicheList', 'appView', null, 'initTipologicheGenerale' );">
             Gestione Tipologiche</a>
         </li>   
          <li id="gestionecaricamentopratiche">
			<a href="#" onclick="clickMenu('areaamministrativa-gestionecaricamentopratiche'); loadDiv('/CruscottoAuditAtpoWebWeb/caricamentopraticheList', 'appView', null, 'initCaricamentoPraticheGenerale' );">
             Caricamento Pratiche Sede</a>
         </li>  
         
		</ul>
	</li>
<%
}
%>






<%
if(ruoloAttivo.toLowerCase().indexOf("dirigente") != -1 | ruoloAttivo.toLowerCase().indexOf("auditors") != -1 | ruoloAttivo.toLowerCase().indexOf("delegato") != -1){
%>
	<li id="auditors" data-value="vigilanza">
			<a href="javascript:;">
			<i class="icon-notebook"></i>
				<span class="title">Auditors</span>
				<span class="arrow "></span>
			</a>
				<ul class="sub-menu">
					<li id="accessi">
					<!-- file js: cruscottoScript.js -->
						<a href="#" onclick="clickMenu('auditors-accessi'); loadDiv('/CruscottoAuditAtpoWebWeb/accessi', 'appView', null, 'initAuditors' );">
                          Accessi</a>
                        </li>
						<%
						if(ruoloAttivo.toLowerCase().indexOf("dirigente") != -1 | ruoloAttivo.toLowerCase().indexOf("delegato") != -1 ){
						%>

						<li id="reports">
						<a href="#" onclick="clickMenu('auditors-reports'); loadDiv('/CruscottoAuditAtpoWebWeb/reports', 'appView', null, null );">
                          Reports</a>
                        </li>   
                                              
						<%
						}
						%>
					</ul>
	 </li>
	 

<%
}
%>

<%
if(ruoloAttivo.toLowerCase().indexOf("dirigente") != -1 | ruoloAttivo.toLowerCase().indexOf("delegato") != -1 ){
%>
   
	<li id="modellazione">
		<a href="javascript:;"> 
			<i class="icon-layers"></i> 
			<span class="title">Modellazione</span> 
			<span class="arrow "></span> 
		</a>
		<ul class="sub-menu">
<%
if(ruoloAttivo.toLowerCase().indexOf("dirigente") != -1){
%>
					
			<li id="area">
			<!-- cruscottoScript.js -->
        		<a href="#" onclick="clickMenu('modellazione-area');loadDiv('/CruscottoAuditAtpoWebWeb/getAreaList', 'appView', null, 'initArea' );">Area</a>
			</li>

<%
}
%>
           <li id="processo">
				<a href="#" onclick="clickMenu('modellazione-processo');loadDiv('/CruscottoAuditAtpoWebWeb/getProcessiList', 'appView', null, 'initProcesso' );return false;">Processo</a>
           </li>
                    
			<li id="fase">
				<a href="#" onclick="clickMenu('modellazione-fase');loadDiv('/CruscottoAuditAtpoWebWeb/getSottoProcessiList', 'appView', null, 'initSottoProcesso' );return false;"> Fase</a>
			</li>
			
			<li id="attivitaComponente">
				<a href="#" onclick="clickMenu('modellazione-attivitaComponente');loadDiv('/CruscottoAuditAtpoWebWeb/getAttivitaComponenteList', 'appView', null, 'initAttivitaComponente' );return false;"> Attivita Componente</a>
			</li>
			
			<li id="attivitaDettaglio">
				<a href="#" onclick="clickMenu('modellazione-attivitaDettaglio');loadDiv('/CruscottoAuditAtpoWebWeb/getAttivitaDettaglioList', 'appView', null, 'initAttivitaDettaglio' );return false;"> Attivita Dettaglio</a>
			</li>
	</ul>
	</li>
	
<%
}
%>


<%
if(ruoloAttivo.toLowerCase().indexOf("hidden") != -1){
%>

	<li id="gestioneProfili">
		<a href="javascript:;"> <i class="icon-users"></i> 
			<span class="title">Gestione Profili</span>
			<span class="arrow"></span>
		</a>
		<ul class="sub-menu">
						
			<li id="profilo">
				<a href="#" onclick="clickMenu('gestioneProfili-profilo'); loadDiv('/CruscottoAuditAtpoWebWeb/getProfili', 'appView', null, 'initProfilo');">Profilo</a>
			</li>
			<li id="associazione">
				<a href="#" onclick="clickMenu('gestioneProfili-associazione'); loadDiv('/CruscottoAuditAtpoWebWeb/getAssociazioni', 'appView', null, 'initAssociazione');">Associazione</a>
			</li>
		</ul>
	</li>
<%
}
%>	

	
<%
if(ruoloAttivo.toLowerCase().indexOf("dirigente") != -1 | ruoloAttivo.toLowerCase().indexOf("delegato") != -1 ){
%>

	<li id="componenti">
		<a href="javascript:;"> <i class="icon-settings"></i> 
			<span class="title">Gestione componenti</span>
			<span class="arrow"></span>
		</a>
		<ul class="sub-menu">
						
			<li id="normativa">
			<!-- cruscottoScript.js -->
				<a href="#" onclick="clickMenu('componenti-normativa');loadDiv('/CruscottoAuditAtpoWebWeb/getNormativaList', 'appView', null, 'initCircolariINps' );return false;"> Normativa</a>
			</li>
			<li id="documenti"><a href="#" onclick="clickMenu('componenti-documenti'); loadDiv('/CruscottoAuditAtpoWebWeb/getDocumentiMedia', 'appView', null, 'initDocMedia');return false;"> Documenti/Media</a></li>
			<li id="regole"><a href="#"> Regole e modelli</a></li>
			<li id="domini"><a href="#" onclick="clickMenu('componenti-domini'); loadDiv('/CruscottoAuditAtpoWebWeb/getDomini', 'appView', null, 'initDomini');return false;"> Domini</a></li>
			<li id="dizionari"><a href="#" onclick="clickMenu('componenti-dizionari'); loadDiv('/CruscottoAuditAtpoWebWeb/getTassonomie', 'appView', null, 'initTassonomie' );return false;"> Tassonomie</a></li>
			<li id="compTecnici"><a href="#" onclick="clickMenu('componenti-compTecnici'); loadDiv('/CruscottoAuditAtpoWebWeb/getCompTecnici', 'appView', null, 'initCompTecnici' );return false;"> Componenti tecnici</a></li>
		</ul>
	</li>
	
<%
}
%>

	
	
</ul>
<!-- END SIDEBAR MENU --></div>
</div>