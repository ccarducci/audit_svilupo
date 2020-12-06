<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page import="it.tecnet.crs.web.beans.AppUser"%>

<%
   AppUser user= (AppUser)request.getSession().getAttribute("AppUser");
   String ruoloAttivo=user.getRuoloAttivo();
%> 

			<!-- BEGIN PAGE HEAD -->
			
<%@page import="java.util.List"%>
<%@page import="it.tecnet.crs.jpa.model.CrsRuolo"%>
<%@page import="it.tecnet.crs.jpa.model.AuTplIsnc"%>
<%@page import="it.tecnet.crs.jpa.model.AuAudit"%><div class="page-head">
				<!-- BEGIN PAGE TITLE -->
				<div class="page-title">
                    <!-- <h1>ACL Engine <small>gestione autorizzazioni applicative</small></h1> -->
					<h1>Gestione Soglie e Colori <!-- <small>- Generale</small> --></h1>
				</div>
                <!-- BEGIN PAGE BREADCRUMB -->
                <ul class="page-breadcrumb breadcrumb">
                    <li>
                        Area Amministrativa
                        <i class="fa fa-circle"></i>
                    </li>
                    <li>
                        Gestione Soglie e Colori
                    </li>
                </ul>
                <!-- END PAGE BREADCRUMB -->
				<!-- END PAGE TITLE -->
                
			</div>
			<!-- END PAGE HEAD -->
			
			 <div class="portlet light">
      <div class="portlet-title">
         <div class="caption font-green"><i class="icon-pin font-green"></i>
            <span class="caption-subject bold uppercase">MODIFICA SOGLIE E COLORI</span>
         </div>
      </div>
      <div class="portlet-body form">
        <form role="form">
         	 <input type="hidden" id="idTplIsnc" value="<s:property value="%{isnc.idTplIsnc}" />">
            <div class="form-body">
               <label style="color:red;" id="info" hidden>I campi contrassegnati da *  sono obbligatori</label>
               <div class="row" style="margin-bottom: 40px;">
                  <div class="col-md-4" style="margin-bottom: 15px;">
                     <div class="form-group form-md-line-input"><input type="text"
                        class="form-control" id="soglia" value="<s:property value="%{isnc.soglia}" />" style="height: 38px;">
                        <label for="soglia">Soglia <i class="fa fa-asterisk obbligatorio"></i></label>
                        
                     </div>
                  </div>
                 
                  <div class="col-md-4" style="margin-bottom: 15px;">
                     <div class="form-group form-md-line-input">
                     <input id="dataInizio" class="form-control form-control-inline input-medium date-picker" value="<s:property value="%{isnc.dataInizio}" />" size="16" type="text" >
                        <label for="dataInizio">Data Inizio <i class="fa fa-asterisk obbligatorio"></i></label>
                     </div>
                  </div>
                  <div class="col-md-4" style="margin-bottom: 15px;">
                     <div class="form-group form-md-line-input">
                     	<input id="dataFine" class="form-control form-control-inline input-medium date-picker" size="16" type="text" value="<s:property value="%{isnc.dataFine}" />" >
                        <label for="dataFine">Data Fine </label>
                     </div>
                  </div>
                   
               </div>
               <div class="row" style="margin-bottom: 40px;">
				
                 
                 
				  <div class="col-md-4" style="margin-bottom: 15px;">
                     <div class="form-group">
                        <label for="form_control_2"
                           style="top: 0; font-size: 13px; color: #888888; opacity: 1; width: 100%; ">Colore
                        <i class="fa fa-asterisk obbligatorio"></i></label><br>
                        <select class="form-control input-medium select2me" data-placeholder="Colore" id="colore">
                        
                        
                        <%  
										AuTplIsnc bat = (AuTplIsnc)request.getAttribute("isnc"); 
										
										
									%>
                        
                           <option value="Rosso"  <%if(bat.getColore().equalsIgnoreCase("Rosso")){%>selected="selected" <%} %>>Rosso</option>
                           <option value="Verde" <%if(bat.getColore().equalsIgnoreCase("Verde")){%>selected="selected" <%} %>>Verde</option>
                           <option value="Giallo" <%if(bat.getColore().equalsIgnoreCase("Giallo")){%>selected="selected" <%} %>>Giallo</option>
                           <option value="Arancio" <%if(bat.getColore().equalsIgnoreCase("Arancio")){%>selected="selected" <%} %>>Arancio</option>
                           <option value="Bianco" <%if(bat.getColore().equalsIgnoreCase("Bianco")){%>selected="selected" <%} %>>Bianco</option>
                           
                           
                           
                           
                           
                           
                        </select>
                    
                  		</div>  
                  		    
                  	</div> 
                  	<div class="col-md-4" style="margin-bottom: 15px;">
                  	<div class="form-group">
                        	<label for="form_control_2"
                           style="top: 0; font-size: 13px; color: #888888; opacity: 1; width: 100%;">Audit
                        	<i class="fa fa-asterisk obbligatorio"></i></label><br>
	                        <div class="">
									<select id="idAudit" class="form-control select2me">
									
									
									<%  
										List sedi = (List)request.getSession().getAttribute("auditList");
										for(int n = 0 ;n <sedi.size();n++){
										AuAudit sede=(AuAudit)sedi.get(n);
										%>
										<option  id="<%=sede.getIdAudit() %>" value="<%=sede.getIdAudit() %>" 
										<%if(sede.getIdAudit() == bat.getIdAudit()){ %>
										selected="selected"
										<%} %>
										 ><%=sede.getNome() %></option>
										<%
										}
										
									%>
									
									
          							</select>
							</div>
                    
                  		</div>
                  	</div>
                  	  </div>           
               </div>
               <div class="row">
                 <div class="col-md-4" style="margin-bottom: 15px;"></div>
                  <div class="col-md-4" style="margin-bottom: 15px;"></div>
	                  	<div id="bottoniAudit" class="col-md-4" style="margin-bottom: 15px;">
	                  		<button type="button" class="btn blue" onclick="saveNewIsnc()">Salva</button>
	                  </div>
                  
               </div>
            </div>
         </form>
      </div>
   </div>
	