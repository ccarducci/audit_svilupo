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
					<h1>Gestione Tipologiche <!-- <small>- Generale</small> --></h1>
				</div>
                <!-- BEGIN PAGE BREADCRUMB -->
                <ul class="page-breadcrumb breadcrumb">
                    <li>
                        Area Amministrativa
                        <i class="fa fa-circle"></i>
                    </li>
                    <li>
                        Gestione Tipologiche
                    </li>
                </ul>
                <!-- END PAGE BREADCRUMB -->
				<!-- END PAGE TITLE -->
                
			</div>
			<!-- END PAGE HEAD -->
			
			 <div class="portlet light">
      <div class="portlet-title">
         <div class="caption font-green"><i class="icon-pin font-green"></i>
            <span class="caption-subject bold uppercase">MODIFICA TIPOLOGICA</span>
         </div>
      </div>
      <div class="portlet-body form">
         <form role="form">
         	
            <div class="form-body">
               <label style="color:red;" id="info" hidden>I campi contrassegnati da *  sono obbligatori</label>
                <input type="hidden" id="idTplTipologica" value="<s:property value="%{tipologica.idTplTipologica}" />">
                <div class="row" style="margin-bottom: 40px;">
                  <div class="col-md-4" style="margin-bottom: 15px;">
                     <div class="form-group form-md-line-input"><input type="text"
                        class="form-control" id="tipo" value="<s:property value="%{tipologica.tipo}" />" style="height: 38px;">
                        <label for="nome">Tipo <i class="fa fa-asterisk obbligatorio"></i></label>
                        
                     </div>
                  </div>
                 
                  <div class="col-md-4" style="margin-bottom: 15px;">
                     <div class="form-group form-md-line-input"><input type="text"
                        class="form-control" id="codifica" value="<s:property value="%{tipologica.codifica}" />" style="height: 38px;">
                        <label for="cognome">Codifica <i class="fa fa-asterisk obbligatorio"></i></label>
                     </div>
                  </div>
                  <div class="col-md-4" style="margin-bottom: 15px;">
                     <div class="form-group form-md-line-input"><input type="text"
                        class="form-control" id="descrizione" value="<s:property value="%{tipologica.descrizione}" />" style="height: 38px;">
                        <label for="username">Descrizione <i class="fa fa-asterisk obbligatorio"></i></label>
                     </div>
                  </div>
                   
               </div>
               <div class="row">
                 <div class="col-md-4" style="margin-bottom: 15px;"></div>
                  <div class="col-md-4" style="margin-bottom: 15px;"></div>
	                  	<div id="bottoniAudit" class="col-md-4" style="margin-bottom: 15px;">
	                  		<button type="button" class="btn blue" onclick="saveNewTipologica()">Salva</button>
	                  		<!-- 
	               			<button type="button" class="btn default" onclick="slideAsideNew(''); hideInfo();">Annulla</button>
	               			 -->
	                  </div>
                  
               </div>
            </div>
         </form>
      </div>
   </div>
	