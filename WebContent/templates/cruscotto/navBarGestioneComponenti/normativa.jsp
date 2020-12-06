<%@ taglib prefix="s" uri="/struts-tags" %>
	<!-- BEGIN CONTENT -->

			<!-- BEGIN PAGE HEAD -->
			<div class="page-head">
				<!-- BEGIN PAGE TITLE -->
				<div class="page-title">
                    <!-- <h1>ACL Engine <small>gestione autorizzazioni applicative</small></h1> -->
					<h1>Normativa <!-- <small>- Processo</small> --></h1>
				</div>
                <!-- BEGIN PAGE BREADCRUMB -->
                <ul class="page-breadcrumb breadcrumb">
                    <li>
                        Gestione componenti
                        <i class="fa fa-circle"></i>
                    </li>
                    <li>
                        Normativa
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
                            <div class="col-lg-4 col-xs-12">
                                <div class="form-group">
                                    <label for="form_control_2" style="top: 0; font-size: 13px; color: #888888; opacity: 1; width: 100%; position: absolute;">Tipo Normativa <i class="fa fa-asterisk obbligatorio"></i></label><br>
                                   <s:select class="form-control input-medium select2me" 
                              					data-placeholder="TipoNormativaEnum" 
                                                id="comboTipoNormativa" 
                              					list="@it.tecnet.crs.util.TipoNormativaEnum@values()"
                              					listKey="getCodice()"
												listValue="getDescrizione()"
  												
  												onchange="ricaricaNormativa()"/>
                                </div>
                            </div>
                            <div class="col-lg-6 col-xs-12" style="padding-top: 18px;">
                                <div class="actions add-action">
                                    <a data-toggle="modal" onclick="showAsideNormativa();return false;" class="btn btn-default btn-sm">
                                    <i class="fa fa-plus"></i> Nuovo </a>
                                </div>
                                <div class="actions add-action">
                                    <a data-toggle="modal" onclick="modificaNormativa();" class="btn btn-default btn-sm">
                                    <i class="fa fa-pencil"></i> Modifica </a>
                                </div>
                                <div class="actions add-action">
                                    <a data-toggle="modal" onclick="getCheckBoxID('eliminaNormativa');" class="btn btn-default btn-sm del">
                                    <i class="fa fa-trash"></i> Elimina </a>
                                </div>
                                <div class="actions add-action" style="margin-left: 15px">
                                  <div class="md-checkbox-inline">
                        		  	<div class="md-checkbox">
                           				<input type="checkbox" id="getStoricoNormativa" class="md-check" onclick="ricaricaNormativa()" >
                           				<label for="getStoricoNormativa" style="margin-left: 5px">
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
                        <div class="portlet-body" id="divNormativaTable">
                        
                        	<table class="table table-striped table-bordered table-advance table-hover" id="circolariInpsTable">
						         <thead>
						            <tr>
						               <th>
						               </th>
						               
						               <th>
						                  Data Emissione
						               </th>
						               <th>
						               	  N. Circolare
						               </th>
						               <th>
						               	  Oggetto
						               </th>
						               <th>
						               	  Desc. Sintetica
						               </th>
						               <th>
						               	  Desc. Dettaglio
						               </th>
						               <th>
						               	  Direz.Emittente1
						               </th>
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
			
            <!-- MODAL CIRCOLARI INPS -->
            <div class="modalCircolariInps">
            	<s:include value="sideBar/circolariInps_aside.jsp" />	
            </div>
            
            <!-- MODAL NOTE DECRETI -->
            <div class="modalNoteDecreti">
            	<s:include value="sideBar/noteDecreti_aside.jsp" />	
            </div>
            
            <!-- MODAL MESSAGGI INPS -->
            <div class="modalMessaggiInps">
            	<s:include value="sideBar/messaggiInps_aside.jsp" />	
            </div>
            
            <!-- MODAL LEGGI DECRETI -->
            <div class="modalLeggiDecreti">
            	<s:include value="sideBar/leggiDecreti_aside.jsp" />	
            </div>
            
			<!-- END PAGE CONTENT INNER -->
	<!-- END CONTENT -->
	
	<!-- MODALE CONFERMA ELIMINA AREA -->
  	<div class="modal fade" tabindex="-1" id="deleteNormativaModal" aria-hidden="true">
	  <div class="modal-dialog"  style="width: 500px !important; height: 200px !important;">
	    <div class="modal-content">
	      <div class="modal-header">
	        <div class="caption font-green">
                   <span class="caption-subject bold uppercase">ELIMINA NORMATIVA</span>
               </div>
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
	          <span aria-hidden="true">&times;</span>
	        </button>
	      </div>
	      <div class="modal-body">
	        <p>Si sta eliminando la normativa selezionata. Procede con l' operazione?</p>
	      </div>
	      <div class="modal-footer">
	        <button type="button" data-dismiss="modal" class="btn default">No</button>
	        <button type="button" class="btn blue" onclick="eliminaNormativa();">Si</button>
	      </div>
	    </div>
	  </div>
	</div>
	
	<!-- MODALE PER GESTIONE MESSAGGI DA DARE ALL'UENTE IN CASO DI ERRORI/WARNING -->
         <div class="modal fade" tabindex="-1" id="normativaModal" aria-hidden="true">
		  <div class="modal-dialog"  style="width: 550px !important; height: 200px !important;">
		    <div class="modal-content">
		      <div class="modal-header">
		        <div class="caption font-green">
		                  <span class="caption-subject bold uppercase">GESTIONE NORMATIVA</span>
		              </div>
		        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
		          <span aria-hidden="true">&times;</span>
		        </button>
		      </div>
		      <div class="modal-body" id="messageNormativaDiv">
		        <p></p>
		      </div>
		      <div class="modal-footer">
		        <button type="button" data-dismiss="modal" class="btn default">Chiudi</button>
		      </div>
		    </div>
		  </div>
		</div>

<script>
	jQuery(document).ready(function() {
		ComponentsPickers.init();
    });
</script>
