<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page import="it.tecnet.crs.web.beans.AppUser;"%>
<%
   AppUser user= (AppUser)request.getSession().getAttribute("AppUser");
   String ruoloAttivo=user.getRuoloAttivo();
%> 
			<!-- BEGIN PAGE HEAD -->
			<div class="page-head">
				<!-- BEGIN PAGE TITLE -->
				<div class="page-title">
                    <!-- <h1>ACL Engine <small>gestione autorizzazioni applicative</small></h1> -->
					<h1>Accessi <!-- <small>- Processo</small> --></h1>
				</div>
                <!-- BEGIN PAGE BREADCRUMB -->
                <ul class="page-breadcrumb breadcrumb">
                    <li>
                        Auditors
                        <i class="fa fa-circle"></i>
                    </li>
                    <li>
                        Accessi
                    </li>
                </ul>
                <!-- END PAGE BREADCRUMB -->
				<!-- END PAGE TITLE -->
                
			</div>
			<!-- END PAGE HEAD -->
			
			<!-- BEGIN PAGE CONTENT INNER -->
			<div class="row margin-top-10">
				<div class="col-md-12">
                    
                    <!-- BEGIN SAMPLE TABLE PORTLET-->
                    <div class="portlet">
				        <div class="row">
                            <div class="col-lg-6 col-lg-offset-4 col-xs-12" style="padding-left: 70px;">
<%
if(ruoloAttivo.toLowerCase().indexOf("auditors") != -1){
%>
                                <div class="actions add-action">
                                <!-- cruscottoScript.js -->
                                <a href="#" onclick="getCheckBoxID('accessi');"
                                      class="btn btn-default btn-sm">
                                    <i class="fa fa-pencil"></i> Modifica </a>
                                </div>
                                <div class="actions add-action">
                                    <a href="#" onclick="reloadTable('accessi')" class="btn btn-default btn-sm">
                                    <i class="fa fa-refresh"></i> Aggiorna </a>
                                </div>
<%
}else{
%>
								<div class="actions add-action" style="padding-left: 60px;">
                                    <a href="#" onclick="reloadTable('accessi')" class="btn btn-default btn-sm">
                                    <i class="fa fa-refresh"></i> Aggiorna </a>
                                </div>
<%
}
%>
                                
                            </div>
                        </div>
                        <div class="portlet-body">
                                <table class="table table-striped table-bordered table-advance table-hover" id="accessi_table">
                                    <thead>
                                        <tr>
                                            <th style="text-align: center;">
                                                <div class="form-group form-md-checkboxes">
                                                    <div class="md-checkbox-inline">
                                                        <div class="md-checkbox">
                                                           
                                                        </div>
                                                    </div>
                                                </div>
                                            </th>
                                            <th>
                                                Campagna
                                            </th>
                                            <th>
                                                Audit
                                            </th>
                                            <th>
                                                Sede
                                            </th>
                                            <th>
                                                Data inizio
                                            </th>
                                            <th>
                                                Data fine
                                            </th>
                                            <!-- 
                                            <th>
                                                Stato
                                            </th>
                                            <th>
                                                Dirigente
                                            </th>
                                             -->
                                        </tr>
                                    </thead>
                                    <tbody>
                                  
                                    </tbody>
                                </table>
                        </div>
                    </div>
                    <!-- END SAMPLE TABLE PORTLET-->

				</div>
			</div>
			
            <!-- MODAL -->
            <div class="modal-custom">
            </div>
            
	
	<!-- END CONTENT -->