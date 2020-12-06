<%@ taglib prefix="s" uri="/struts-tags" %>
	<!-- BEGIN CONTENT -->

			<!-- BEGIN PAGE HEAD -->
			<div class="page-head">
				<!-- BEGIN PAGE TITLE -->
				<div class="page-title">
                    <!-- <h1>ACL Engine <small>gestione autorizzazioni applicative</small></h1> -->
					<h1>Documenti/Media <!-- <small>- Processo</small> --></h1>
				</div>
                <!-- BEGIN PAGE BREADCRUMB -->
                <ul class="page-breadcrumb breadcrumb">
                    <li>
                        Gestione componenti
                        <i class="fa fa-circle"></i>
                    </li>
                    <li>
                        documenti/media
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
                                    <a data-toggle="modal" onclick="initDatePicker();getCheckBoxID('showUpDocAside');" class="btn btn-default btn-sm">
                                    <i class="fa fa-pencil"></i> Modifica </a>
                                </div>
                                <div class="actions add-action">
                                    <a data-toggle="modal" onclick="getCheckBoxID('eliminaDocumento');" class="btn btn-default btn-sm del">
                                    <i class="fa fa-trash"></i> Elimina </a>
                                </div>
                               
                                 <div class="actions add-action">
                                  
                                    <a onclick="scaricaDocumento()" class="btn btn-default btn-sm del" download>
                                    <i class="fa fa-download"></i> Scarica </a>
                                </div>
                                 <div class="actions add-action" style="margin-left: 15px">
                                  <div class="md-checkbox-inline">
                        		  	<div class="md-checkbox">
                           				<input type="checkbox" id="addFiltro" class="md-check" onclick="manageFiltro()" >
                           				<label for="addFiltro" style="margin-left: 5px">
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
                                <table class="table table-striped table-bordered table-advance table-hover" id="docMediaTable">
                                    <thead>
                                    	<th style="text-align: center;">
                                    		<div class="form-group form-md-checkboxes">
                     							<div class="md-checkbox-inline">
                        							<div class="md-checkbox">
                           								<input type="checkbox" id="checkAllDoc" class="md-check" onclick="selectAllDoc()">
                           									<label for="checkAllDoc">
                           										<span></span>
                           										<span class="check"></span>
                           										<span class="box"></span>
                           									</label>
                        							</div>
                     							</div>
                  							</div>
                                    	
                                    	</th>	
                                        <th>Oggetto</th>
                                        <th>Data inizio</th>      
                                        <th>Data fine</th> 
                                                         
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
         <s:include value="sideBar/newDocAside.jsp" />	
            	<s:include value="sideBar/updateDocAside.jsp" />	
            
			<!-- END PAGE CONTENT INNER -->
	<!-- END CONTENT -->
	
	<!-- MODALE CONFERMA ELIMINA AREA -->
  	<div class="modal fade" tabindex="-1" id="deleteDocModal" aria-hidden="true">
	  <div class="modal-dialog"  style="width: 500px !important; height: 200px !important;">
	    <div class="modal-content">
	      <div class="modal-header">
	        <div class="caption font-green">
                   <span class="caption-subject bold uppercase">ELIMINA DOCUMENTO</span>
               </div>
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
	          <span aria-hidden="true">&times;</span>
	        </button>
	      </div>
	      <div class="modal-body">
	        <p>Si sta eliminando il documento selezionato. Procedere con l' operazione?</p>
	      </div>
	      <div class="modal-footer">
	        <button type="button" data-dismiss="modal" class="btn default">No</button>
	        <button type="button" class="btn blue" onclick="eliminaDocumento();">Si</button>
	      </div>
	    </div>
	  </div>
	</div>
	

    

