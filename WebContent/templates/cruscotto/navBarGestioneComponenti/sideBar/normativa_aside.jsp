<%@ taglib prefix="s" uri="/struts-tags" %>
	
	
	<s:hidden id="idDatiTipo" value="0"/>
	
	<!-- BEGIN SIDEBAR DX -->
    <aside class="menu-sx-levelone-facility add">
        <div class="portlet light">
            <div class="portlet-title">
                <div class="caption font-green">
                    <i class="icon-pin font-green"></i>
                    <span class="caption-subject bold uppercase" id="titoloModaleNormativa"></span>
                </div>
            </div>
            <div class="portlet-body form">
                <form role="form" id="normativaAsideForm">
                    <div class="form-body">
                        <div class="row" style="margin-bottom: 10px;">
                            <div class="col-md-3">
                                <div class="form-group">
                                   <div class="input-group input-medium date date-picker" data-date-format="dd/mm/yyyy">
                                         <span class="help-block">Data Emissione <i class="fa fa-asterisk obbligatorio"></i></span>
                                     
                                         <input id="dataEmissione" class="form-control form-control-inline input-medium date-picker" size="16" type="text" >
                                    </div>
                                   
                                </div>
                            </div>
                            <div class="col-md-3">
                                <div class="form-group form-md-line-input">
                                    <input type="text" class="form-control" id="codice" value="" style="height: 28px;">
                                    <label for="form_control_1">Codice <i class="fa fa-asterisk obbligatorio"></i></label>
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="form-group form-md-line-input">
                                    <input type="text" class="form-control" id="oggetto" value="" style="height: 28px;">
                                    <label for="form_control_1">Oggetto <i class="fa fa-asterisk obbligatorio"></i></label>
                                </div>
                            </div>
                        </div>
                        
                        <div class="row" style="margin-bottom: 10px;">
                            <div class="col-md-4">
                                <div class="form-group form-md-line-input">
                                    <input type="text" class="form-control" id="descrizione" value="" style="height: 28px;">
                                    <label for="form_control_1">Descrizione <i class="fa fa-asterisk obbligatorio"></i></label>
                                </div>
                            </div>
                        
                            <div class="col-md-3">
                                <div class="form-group">
                                   <div class="input-group input-medium ">
                                        <span class="help-block">Data Inizio <i class="fa fa-asterisk obbligatorio"></i></span>
                                     
                                         <input id="dataInizio" class="form-control form-control-inline input-medium date-picker" size="16" type="text" >
                                    </div>
                                   
                                </div>
                            </div>
                            <div class="col-md-3">
                                <div class="form-group">
                                   <div class="input-group input-medium ">
                                      
                                      <input id="dataFine" class="form-control form-control-inline input-medium date-picker" size="16" type="text" >
                                    </div>
                                    <span class="help-block">Data Fine <i class="fa fa-asterisk obbligatorio"></i></span>
                                </div>
                            </div>
                        </div>
                       
                       <!-- *************************************************************** -->
                       <!-- 			CAMPI CUSTOMIZZATI PER TIPO NORMATIVA 1 			-->
                       <!-- *************************************************************** -->
                       <div id="asideTipo1" style="display: none">
                       
                       		<div class="row" style="margin-bottom: 10px;">
				                <div class="col-md-4">
				                    <div class="form-group form-md-line-input">
				                        <input type="text" class="form-control" id="emittente1" value="" style="height: 28px;">
				                        <label for="form_control_1">Direz. Emittente 1 </label>
				                    </div>
				                </div>
				           
				                <div class="col-md-4">
				                    <div class="form-group form-md-line-input">
				                        <input type="text" class="form-control" id="emittente2" value="" style="height: 28px;">
				                        <label for="form_control_1">Direz. Emittente 2 </label>
				                    </div>
				                </div>
				                <div class="col-md-4">
				                    <div class="form-group form-md-line-input">
				                        <input type="text" class="form-control" id="emittente3" value="" style="height: 28px;">
				                        <label for="form_control_1">Direz. Emittente 3 </label>
				                    </div>
				                </div>
			            	</div>
			            
			            	
				            <div class="row" style="margin-bottom: 10px;">
				                
				                <div class="col-md-4">
				                    <div class="form-group form-md-line-input">
				                        <input type="text" class="form-control" id="emittente4" value="" style="height: 28px;">
				                        <label for="form_control_1">Direz. Emittente 4 </label>
				                    </div>
				                </div>
				                <div class="col-md-4">
				                    <div class="form-group form-md-line-input">
				                        <input type="text" class="form-control" id="emittente5" value="" style="height: 28px;">
				                        <label for="form_control_1">Direz. Emittente 5 </label>
				                    </div>
				                </div>
				                <div class="col-md-4">
				                    <div class="form-group form-md-line-input">
				                        <input type="text" class="form-control" id="emittente6" value="" style="height: 28px;">
				                        <label for="form_control_1">Direz. Emittente 6 </label>
				                    </div>
				                </div>
				            </div>
				             
				            <div class="row">
				            	<div class="col-md-8">
					            	<div class="form-group form-md-line-input">
				                        <input type="text" class="form-control" id="sommario" value="" style="height: 28px;">
				                        <label for="form_control_1">Sommario </label>
				                    </div>
				                </div>
				            </div>
                       </div>
                       
                       
                       <!-- *************************************************************** -->
                       <!-- 			CAMPI CUSTOMIZZATI PER TIPO NORMATIVA 2 			-->
                       <!-- *************************************************************** -->
                       <div id="asideTipo2" style="display: none">
			            
				            <div class="row">
				            	<div class="col-md-8">
					            	<div class="form-group form-md-line-input">
				                        <input type="text" class="form-control" id="riferimenti" value="" style="height: 28px;">
				                        <label for="form_control_1">Riferimenti </label>
				                    </div>
				                </div>
				            </div>
                       </div>
                       
                       
                       <!-- *************************************************************** -->
                       <!-- 			CAMPI CUSTOMIZZATI PER TIPO NORMATIVA 4 			-->
                       <!-- *************************************************************** -->
                       <div id="asideTipo4" style="display: none">
                       
                       		<div class="row" style="margin-bottom: 10px;">
				                <div class="col-md-4">
				                    <div class="form-group form-md-line-input">
				                        <input type="text" class="form-control" id="articolo" value="" style="height: 28px;">
				                        <label for="form_control_1">Articolo - Comma </label>
				                    </div>
				                </div>
				                <div class="col-md-4">
				                    <div class="form-group form-md-line-input">
				                        <input type="text" class="form-control" id="descrizioneCompleta" value="" style="height: 28px;">
				                        <label for="form_control_1">Descrizione Completa </label>
				                    </div>
				                </div>
				                <div class="col-md-4">
				                    <div class="form-group form-md-line-input">
				                        <input type="text" class="form-control" id="descrizioneSintetica" value="" style="height: 28px;">
				                        <label for="form_control_1">Descrizione Sintetica </label>
				                    </div>
				                </div>
			            	</div>
			            
				            <div class="row" style="margin-bottom: 10px;">
				                
				                <div class="col-md-4">
				                    <div class="form-group form-md-line-input">
				                        <input type="text" class="form-control" id="annoGUI" value="" style="height: 28px;">
				                        <label for="form_control_1">Anno G.U.I. </label>
				                    </div>
				                </div>
				                <div class="col-md-4">
				                    <div class="form-group form-md-line-input">
				                        <input type="text" class="form-control" id="numeroGUI" value="" style="height: 28px;">
				                        <label for="form_control_1">Numero G.U.I. </label>
				                    </div>
				                </div>
				            </div>
                       </div>
                       
                       
                       <!-- *************************************************************** -->
                       <!-- 			CAMPI CUSTOMIZZATI PER TIPO NORMATIVA TEST 			-->
                       <!-- *************************************************************** -->
                       <div id="asideTipoTest" style="display: none">
			            
				            <div class="row">
				            	<div class="col-md-8">
					            	<div class="form-group form-md-line-input">
				                        <input type="text" class="form-control" id="riferimenti" value="" style="height: 28px;">
				                        <label for="form_control_1">Riferimenti </label>
				                    </div>
				                </div>
				            </div>
                       </div>
                       
                        
                    </div>
                    <div class="form-actions noborder" style="position: absolute; bottom: 10px;">
                        <button type="button" class="btn blue" onclick="salvaNormativa();">Salva</button>
                        <button type="button" class="btn default" onclick="slideAsideNew('');">Annulla</button>
                    </div>
                </form>
            </div>
        </div>
    </aside>
    
    <!-- END SIDEBAR DX -->
    