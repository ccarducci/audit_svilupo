<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page import="it.tecnet.crs.web.beans.AppUser"%>

<%
   AppUser user= (AppUser)request.getSession().getAttribute("AppUser");
   String ruoloAttivo=user.getRuoloAttivo();
%> 

			<!-- BEGIN PAGE HEAD -->
			
<%@page import="java.util.List"%>
<%@page import="it.tecnet.crs.jpa.model.CrsRuolo"%><div class="page-head">
				<!-- BEGIN PAGE TITLE -->
				<div class="page-title">
                    <!-- <h1>ACL Engine <small>gestione autorizzazioni applicative</small></h1> -->
					<h1>Gestione Utenze <!-- <small>- Generale</small> --></h1>
				</div>
                <!-- BEGIN PAGE BREADCRUMB -->
                <ul class="page-breadcrumb breadcrumb">
                    <li>
                        Area Amministrativa
                        <i class="fa fa-circle"></i>
                    </li>
                    <li>
                        Gestione Utenze
                    </li>
                </ul>
                <!-- END PAGE BREADCRUMB -->
				<!-- END PAGE TITLE -->
                
			</div>
			<!-- END PAGE HEAD -->
			
			 <div class="portlet light">
      <div class="portlet-title">
         <div class="caption font-green"><i class="icon-pin font-green"></i>
            <span class="caption-subject bold uppercase">MODIFICA UTENTE</span>
         </div>
      </div>
      <div class="portlet-body form">
         <form role="form">
         	
            <div class="form-body">
               <label style="color:red;" id="info" hidden>I campi contrassegnati da *  sono obbligatori</label>
               <div class="row" style="margin-bottom: 40px;">
               <input type="hidden" id="idUtente" value="<s:property value="%{utente.idUtente}" />">
               
                  <div class="col-md-4" style="margin-bottom: 15px;">
                     <div class="form-group form-md-line-input"><input type="text"
                        class="form-control" id="nome" value="<s:property value="%{utente.nome}" />" style="height: 38px;">
                        <label for="nome">Nome <i class="fa fa-asterisk obbligatorio"></i></label>
                        
                     </div>
                  </div>
                 
                  <div class="col-md-4" style="margin-bottom: 15px;">
                     <div class="form-group form-md-line-input"><input type="text"
                        class="form-control" id="cognome" value="<s:property value="%{utente.cognome}" />" style="height: 38px;">
                        <label for="cognome">Cognome <i class="fa fa-asterisk obbligatorio"></i></label>
                     </div>
                  </div>
                  <div class="col-md-4" style="margin-bottom: 15px;">
                     <div class="form-group form-md-line-input"><input type="text"
                        class="form-control" id="username" value="<s:property value="%{utente.username}" />" style="height: 38px;">
                        <label for="username">Username <i class="fa fa-asterisk obbligatorio"></i></label>
                     </div>
                  </div>
                   
               </div>
               <div class="row" style="margin-bottom: 40px;">
				
                 
                  <div class="col-md-4" style="margin-bottom: 15px;">
                     <div class="form-group form-md-line-input"><input type="text"
                        class="form-control" id="email" value="<s:property value="%{utente.email}" />" style="height: 38px;">
                        <label for="email">Email </label>
                     </div>
                  </div>
				  <div class="col-md-4" style="margin-bottom: 15px;">
                     <div class="form-group">
                        <label for="form_control_2"
                           style="top: 0; font-size: 13px; color: #888888; opacity: 1; width: 100%; position: absolute;">Attivo
                        <i class="fa fa-asterisk obbligatorio"></i></label><br>
                        <s:set var="attivo" value="%{utente.attivo}" />
                         <% String attivo = (String)request.getAttribute("utente.attivo"); %>
                        <select class="form-control input-medium select2me"  data-placeholder="Attivo" id="attivo">
                           
                           <option value="A"  
                           <%if(attivo.equalsIgnoreCase("A")){ %>
                           selected="selected"
                           <%} %>
 							>SI</option>
                           <option value="N"
                           <%if(attivo.equalsIgnoreCase("N")){ %>
                           selected="selected"
                           <%} %>
                           	
 							
                            >NO</option>
                        </select>
                    
                  		</div>    
                  	</div> 
                  	  <div class="col-md-4" style="margin-bottom: 15px;">
                  	<div class="form-group">
                        	<label for="form_control_2"
                           style="top: 0; font-size: 13px; color: #888888; opacity: 1; width: 100%;">Ruoli
                        	<i class="fa fa-asterisk obbligatorio"></i></label><br>
	                        <div id="statoRadioButton">
		                       
								<div class="md-radio-list" id="rolechecks">	
									
									<% List ruoliUpdate = (List)request.getAttribute("ruoliUpdate"); 
										List ruoli = (List)request.getSession().getAttribute("validRolesList");
										for(int n = 0 ;n <ruoli.size();n++){
										CrsRuolo crsruolo=(CrsRuolo)ruoli.get(n);
										
										
									%>
									<input type="checkbox" descr="<%=crsruolo.getDescrizione() %>" id="<%=crsruolo.getIdRuolo() %>" class="md-check "  
									    <%
									    for(int m  =0 ; m < ruoliUpdate.size();m++ ){
									    Integer rol = (Integer)ruoliUpdate.get(m);
									    if(rol ==crsruolo.getIdRuolo()) {
									    %>
									   	checked="checked"
									    <%
									    }
									   }
									   %> 
									   
									    >
									    
										<span style="margin-left: 10px;"><%=crsruolo.getDescrizione() %> </span>
									    <br/>
									
									
									<%} %>
									
								
	                              	
		                    </div>
                    
                  		</div>
                  	</div>
                  	  </div>            
               </div>
               <div class="row">
                 <div class="col-md-4" style="margin-bottom: 15px;"></div>
                  <div class="col-md-4" style="margin-bottom: 15px;"></div>
	                  	<div id="bottoniAudit" class="col-md-4" style="margin-bottom: 15px;">
	                  		<button type="button" class="btn blue" onclick="saveNewUser()">Salva</button>
	                  		<!-- 
	               			<button type="button" class="btn default" onclick="slideAsideNew(''); hideInfo();">Annulla</button>
	               			 -->
	                  </div>
                  
               </div>
            </div>
         </form>
      </div>
   </div>
	