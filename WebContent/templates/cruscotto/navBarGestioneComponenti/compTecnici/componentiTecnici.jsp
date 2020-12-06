<%@ taglib prefix="s" uri="/struts-tags" %>
	<!-- BEGIN CONTENT -->

			<!-- BEGIN PAGE HEAD -->
			<div class="page-head">
				<!-- BEGIN PAGE TITLE -->
				<div class="page-title">
                    <!-- <h1>ACL Engine <small>gestione autorizzazioni applicative</small></h1> -->
					<h1>Componenti tecnici <!-- <small>- Processo</small> --></h1>
				</div>
                <!-- BEGIN PAGE BREADCRUMB -->
                <ul class="page-breadcrumb breadcrumb">
                    <li>
                        Gestione componenti
                        <i class="fa fa-circle"></i>
                    </li>
                    <li>
                        Componenti tecnici
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
                          
                            <div class="col-lg-8 col-lg-offset-2 col-xs-12" style="padding-left: 70px;">
                                <div class="actions add-action">
                                    <a data-toggle="modal" onclick="initDatePicker();slideAsideNew('add')" class="btn btn-default btn-sm">
                                    <i class="fa fa-plus"></i> Nuovo </a>
                                </div>
                                <div class="actions add-action">
                                    <a data-toggle="modal" onclick="modificaCompTec()" class="btn btn-default btn-sm">
                                    <i class="fa fa-pencil"></i> Modifica </a>
                                </div>
                                <div class="actions add-action">
                                    <a data-toggle="modal" onclick="confirmEliminaCompTec();" class="btn btn-default btn-sm del">
                                    <i class="fa fa-trash"></i> Elimina </a>
                                </div>
                                <div class="actions add-action" style="margin-left: 15px">
                                  <div class="md-checkbox-inline">
                        		  	<div class="md-checkbox">
                           				<input type="checkbox" id="getStoricoCT" class="md-check" onclick="getStoricoCompTec()" >
                           				<label for="getStoricoCT" style="margin-left: 5px">
                           					<span></span>
                           					<span class="check"></span>
                           					<span class="box"></span>
                           					Storico
                           				</label>
                        			</div>
                     			 </div>
                               </div>
                                
                            </div>
                        </div>
                        <div class="portlet-body">
                                <table class="table table-striped table-bordered table-advance table-hover" id="compTecniciTable">
                                    <thead>
                                     	
                                    	<th style="text-align: center;">
                                    		<div>
                     							<div class="md-checkbox-inline">
                        							<div class="md-checkbox">
                           								<input type="checkbox" id="checkAllCT" class="md-check" onclick="selectAllCT()">
                           									<label for="checkAllCT">
                           										<span></span>
                           										<span class="check"></span>
                           										<span class="box"></span>
                           									</label>
                        							</div>
                     							</div>
                  							</div>
                                    	
                                    	</th>
                                    
                                        <th>Descrizione</th>
                                        <th>Codice</th>
                                        <th>Autore</th>      
                                    	<th>Data pubblicazione</th>
                                        <th>Versione</th> 
                                        <th>Tipo</th>          
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
            <s:include value="sideBar/newCompTecnicoAside.jsp" />	
			<!-- END PAGE CONTENT INNER -->
	<!-- END CONTENT -->
	
	<!-- MODALE CONFERMA ELIMINA COMP TECNICO -->
  	<div class="modal fade" tabindex="-1" id="deleteCtModal" aria-hidden="true">
	  <div class="modal-dialog"  style="width: 500px !important; height: 200px !important;">
	    <div class="modal-content">
	      <div class="modal-header">
	        <div class="caption font-green">
                   <span class="caption-subject bold uppercase">ELIMINA COMPONENTE TECNICNO</span>
               </div>
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
	          <span aria-hidden="true">&times;</span>
	        </button>
	      </div>
	      <div class="modal-body">
	        <p>Si sta eliminando il componente tecnico selezionato. Procedere con l' operazione?</p>
	      </div>
	      <div class="modal-footer">
	        <button type="button" data-dismiss="modal" class="btn default">No</button>
	        <button type="button" class="btn blue" onclick="eliminaCT();">Si</button>
	      </div>
	    </div>
	  </div>
	</div>
	

    

