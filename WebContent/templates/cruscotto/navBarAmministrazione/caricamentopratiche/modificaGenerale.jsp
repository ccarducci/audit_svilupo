<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page import="it.tecnet.crs.web.beans.AppUser"%>

<%
   AppUser user= (AppUser)request.getSession().getAttribute("AppUser");
   String ruoloAttivo=user.getRuoloAttivo();
%> 

			<!-- BEGIN PAGE HEAD -->
			
<%@page import="java.util.List"%>
<%@page import="it.tecnet.crs.jpa.model.CrsRuolo"%>
<%@page import="it.tecnet.crs.jpa.model.AuCampagna"%>



<%@page import="it.tecnet.crs.jpa.model.CrsBatchCaricamentoPratiche"%>
<%@page import="it.tecnet.crs.jpa.model.AuSede"%><div class="page-head">
				<!-- BEGIN PAGE TITLE -->
				<div class="page-title">
                    <!-- <h1>ACL Engine <small>gestione autorizzazioni applicative</small></h1> -->
					<h1>Gestione Caricamento Pratiche <!-- <small>- Generale</small> --></h1>
				</div>
                <!-- BEGIN PAGE BREADCRUMB -->
                <ul class="page-breadcrumb breadcrumb">
                    <li>
                        Area Amministrativa
                        <i class="fa fa-circle"></i>
                    </li>
                    <li>
                        Gestione Caricamento Pratiche
                    </li>
                </ul>
                <!-- END PAGE BREADCRUMB -->
				<!-- END PAGE TITLE -->
                
			</div>
			<!-- END PAGE HEAD -->
			
			 <div class="portlet light">
      <div class="portlet-title">
         <div class="caption font-green"><i class="icon-pin font-green"></i>
            <span class="caption-subject bold uppercase">MODIFICA CARICAMENTO PRATICHE</span>
         </div>
      </div>
      <div class="portlet-body form">
         <form role="form">
         	
            <div class="form-body">
               <label style="color:red;" id="info" hidden>I campi contrassegnati da *  sono obbligatori</label>
                <input type="hidden" id="idBcp" value="<s:property value="%{caricamentopratica.idBcp}" />">
                <div class="row" style="margin-bottom: 40px;">
                  <div class="col-md-4"  >
                     <div class="form-group">
                        	<label for="form_control_2"
                           style="top: 0; font-size: 13px; color: #888888; opacity: 1; width: 100%;">Campagna
                        	<i class="fa fa-asterisk obbligatorio"></i></label><br>
                        	
                        	<div class="">
									<select id="idCampagna" class="form-control select2me">
									
									<%  
										CrsBatchCaricamentoPratiche bat = (CrsBatchCaricamentoPratiche)request.getAttribute("caricamentopratica"); 
										List campagne = (List)request.getSession().getAttribute("campagneList");
										for(int n = 0 ;n <campagne.size();n++){
										AuCampagna campagna=(AuCampagna)campagne.get(n);
										%>
										<option  id="<%=campagna.getIdCampagna() %>" value="<%=campagna.getIdCampagna() %>" 
										<%if(campagna.getIdCampagna()==bat.getIdCampagna()){ %>
										selected="selected"
										<%} %>
										 ><%=campagna.getNome() %></option>
										<%
										}
										
									%>
									
										
          							</select>
							</div>
                    
                  	</div>
                  </div>
                 
                  <div class="col-md-4" >
                     <div class="form-group">
                       <label for="form_control_2"
                           style="top: 0; font-size: 13px; color: #888888; opacity: 1; width: 100%;">Sede
                        	<i class="fa fa-asterisk obbligatorio"></i></label><br>
                        <div class="">
									<select id="codiceSede" class="form-control select2me">
									
									<%  
										List sedi = (List)request.getSession().getAttribute("sediList");
										for(int n = 0 ;n <sedi.size();n++){
										AuSede sede=(AuSede)sedi.get(n);
										%>
										<option  id="<%=sede.getCodiceSede() %>" value="<%=sede.getCodiceSede() %>" 
										<%if(sede.getCodiceSede().trim().equalsIgnoreCase(bat.getCodiceSede())){ %>
										selected="selected"
										<%} %>
										 ><%=sede.getNomeSede() %></option>
										<%
										}
										
									%>
									
									
										
          							</select>
							</div>
                     </div>
                  </div>
                  <div class="col-md-4" style="margin-bottom: 15px;">
                     <div class="form-group form-md-line-input"><input type="text"
                        class="form-control" id="annoConclusione" value="<s:property value="%{caricamentopratica.annoConclusione}" />" style="height: 38px;">
                        <label for="annoConclusione">Anno conclusione <i class="fa fa-asterisk obbligatorio"></i></label>
                     </div>
                  </div>
                   
               </div>
               <div class="row">
                 <div class="col-md-4" style="margin-bottom: 15px;"></div>
                  <div class="col-md-4" style="margin-bottom: 15px;"></div>
	                  	<div id="bottoniAudit" class="col-md-4" style="margin-bottom: 15px;">
	                  		<button type="button" class="btn blue" onclick="saveNewCaricamentoPratiche()">Salva</button>
	                  		<!-- 
	               			<button type="button" class="btn default" onclick="slideAsideNew(''); hideInfo();">Annulla</button>
	               			 -->
	                  </div>
                  
               </div>
            </div>
         </form>
      </div>
   </div>
	