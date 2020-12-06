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
					<h1>Reports <!-- <small>- Processo</small> --></h1>
				</div>
                <!-- BEGIN PAGE BREADCRUMB -->
                <ul class="page-breadcrumb breadcrumb">
                    <li>
                        Auditors
                        <i class="fa fa-circle"></i>
                    </li>
                    <li>
                        reports
                    </li>
                </ul>
                <!-- END PAGE BREADCRUMB -->
				<!-- END PAGE TITLE -->
                
			</div>
			<!-- END PAGE HEAD -->
			
			<!-- BEGIN PAGE CONTENT INNER -->
			<div class="row margin-top-10">
			<select id="comboReports" class="form-control input-xlarge select2me" data-placeholder="Tutti" onchange="showGReport();">
            	<option value=""></option>
                <option value="acquisizioneistanza"> Acquisizione istanza</option>
                <option value="giudizio">Autotutela/Resistenza in giudizio</option>
                <option value="istruttoria">Gestione istruttoria</option>
                <option value="peritale">Peritale</option>
                <option value="pperitaleA">Post peritale</option>
                <option value="pperitaleB">Post peritale dissenso</option>
                <option value="provvedimenti">Esecuzione provvedimenti</option>
                <option value="provvedimentiB">Esecuzione provvedimenti dissenso</option>
                <option value="fascicolo">Riepilogo fascicolo</option>
           </select>
           <div class="d-flex flex-column">
	           <div class="d-flex justify-content-center">
	           	    <div style="width: 100% !important;height: 200px !important;" id="graficoFase">
	           	    	
	           	     </div>
					
	           </div>
			
           </div>
           	<!-- ><div class="col-md-12">
                    
                 
                    <div class="portlet">
				        <div class="portlet-body">
				        	<div class="row">
				        		<div class="col-md-4">
				        			<canvas id="bar-chart" width="800px" height="600px"></canvas>		
				        		</div>
				        		<div class="col-md-4">
				        			<canvas id="bar-chart-2" width="800px" height="600px"></canvas>
				        		</div>
				        		<div class="col-md-4">
				        			<canvas id="bar-chart-3" width="800px" height="600px"></canvas>
				        		</div>
				        	</div>
				        	<div class="row">
				        		<div class="col-md-4">
				        			<canvas id="bar-chart-4" width="800px" height="600px"></canvas>		
				        		</div>
				        		<div class="col-md-4">
				        			<canvas id="bar-chart-5" width="800px" height="600px"></canvas>
				        		</div>
				        		<div class="col-md-4">
				        			<canvas id="bar-chart-6" width="800px" height="600px"></canvas>
				        		</div>
				        	</div>
				        	<div class="row">
				        		<div class="col-md-4">
				        			<canvas id="bar-chart-7" width="800px" height="600px"></canvas>		
				        		</div>
				        	</div>
                        </div>
                    </div>
                  

				</div> -->
			</div>
			
            <!-- MODAL -->
            <div class="modal-custom">
            </div>
            
	
	<!-- END CONTENT -->