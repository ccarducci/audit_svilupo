<%@ taglib prefix="s" uri="/struts-tags" %>

<!-- BEGIN PAGE CONTENT INNER -->
			<div class="row margin-top-10">
				<div class="col-md-12">
                    
                    <!-- BEGIN SAMPLE TABLE PORTLET-->
                    <div class="portlet">
				        <div class="row">
                          
                            <div class="col-lg-8 col-lg-offset-2 col-xs-12" style="padding-left: 70px;">
                                <div class="actions add-action">
                                    <a data-toggle="modal" onclick="showAsideAddDominiSottoProcesso()" class="btn btn-default btn-sm">
                                    <i class="fa fa-plus"></i> Aggiungi </a>
                                </div>
                              
                                <div class="actions add-action">
                                    <a data-toggle="modal" onclick="showConfirmRemoveDominiSottoProcesso()" class="btn btn-default btn-sm del">
                                    <i class="fa fa-trash"></i> Elimina </a>
                                </div>
                                <div class="actions add-action">
                                    <a data-toggle="modal" onclick="getCheckBoxID('visualizzaDominiFase')" class="btn btn-default btn-sm del">
                                    <i class="fa fa-search"></i> Visualizza </a>
                                </div>
                            </div>
                        </div>
                        <div class="portlet-body">
                                <table class="table table-striped table-bordered table-advance table-hover" id="dominiSottoProcessoTable">
                                    <thead>
                                    	<th style="text-align: center;">
                                    		<div class="form-group form-md-checkboxes">
                     							<div class="md-checkbox-inline">
                        							<div class="md-checkbox">
                           								<input type="checkbox" id="checkAllDominiSottoProcesso" class="md-check" onclick="selectAllDominiSottoProcesso()">
                           									<label for="checkAllDominiSottoProcesso">
                           										<span></span>
                           										<span class="check"></span>
                           										<span class="box"></span>
                           									</label>
                        							</div>
                     							</div>
                  							</div>
                                    	
                                    	</th>	
                                    	<th>Descrizione</th>
                                        <th>Data inizio</th>     
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
            <div class="modalAddDomSottoProcesso">
            	 <s:include value="sidebar/newDominiSottoProcessoAside.jsp" />	
            </div>
        
        
            
			<!-- END PAGE CONTENT INNER -->
	<!-- END CONTENT -->
	
	<!-- MODALE CONFERMA ELIMINA AREA -->
  	<div class="modal fade" tabindex="-1" id="deleteDomModalSottoProcesso" aria-hidden="true">
	  <div class="modal-dialog"  style="width: 500px !important; height: 200px !important;">
	    <div class="modal-content">
	      <div class="modal-header">
	        <div class="caption font-green">
                   <span class="caption-subject bold uppercase">ELIMINA DOMINIO</span>
               </div>
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
	          <span aria-hidden="true">&times;</span>
	        </button>
	      </div>
	      <div class="modal-body">
	        <p>Si sta eliminando il dominio selezionato. Procedere con l' operazione?</p>
	      </div>
	      <div class="modal-footer">
	        <button type="button" data-dismiss="modal" class="btn default">No</button>
	        <button type="button" class="btn blue" onclick="eliminaAssociazioneDomSottoProcesso();">Si</button>
	      </div>
	    </div>
	  </div>
	</div>
	

    

