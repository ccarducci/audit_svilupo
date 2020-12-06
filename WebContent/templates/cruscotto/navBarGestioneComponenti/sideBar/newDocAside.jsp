<%@ taglib prefix="s" uri="/struts-tags" %>

<aside class="menu-sx-levelone-facility add" style="margin-top: -270px;">

        <div class="portlet light">
            <div class="portlet-title">
                <div class="caption font-green">
                    <i class="icon-pin font-green"></i>
                    <span class="caption-subject bold uppercase">Nuovo documento/Media</span>
                </div>
            </div>
            <div class="portlet-body form">
            
            <form action="nuovoFile" method="POST" enctype="multipart/form-data">
					
             
                    <div class="form-body">
                        <div class="row" style="margin-bottom: 60px;">
                            <div class="col-md-3 col-xs-12" style="margin-bottom: 15px;">
                                <div class="form-group form-md-line-input">
                                    <input type="text" class="form-control" id="descrFile" maxlength="200" />
                                    <label for="form_control_1">Oggetto<i class="fa fa-asterisk obbligatorio"></i></label>
                                </div>
                            </div>
                            <div class="col-md-3 col-xs-12" style="margin-bottom: 15px;">
                                <div class="form-group form-md-line-input">
                                    <input type="text" class="form-control" id="codFile" maxlength="5" />
                                    <label for="form_control_1">Codice<i class="fa fa-asterisk obbligatorio"></i></label>
                                </div>
                            </div>
                  			 <div class="col-md-3" style="margin-bottom: 15px;">
            					<span class="help-block">Data Inizio<i class="fa fa-asterisk obbligatorio"></i></span>
            					<input id="dataInizioDoc"   class="form-control form-control-inline input-medium date-picker" type="text" size="16" >
							</div>
                  			
                  			 <div class="col-md-3" style="margin-bottom: 15px;">
            					<span class="help-block">Data fine</span>
            					<input id="dataFineDoc"   class="form-control form-control-inline input-medium date-picker" type="text" size="16" >
							</div>
                        </div>
                        <div class="row" style="margin-bottom: 60px;">
                        <div class="col-md-3 col-xs-12" style="margin-bottom: 15px;">
                                <div class="form-group form-md-line-input">
                                    <input type="text" class="form-control" id="titoloDoc" maxlength="200" />
                                    <label for="form_control_1">Titolo<i class="fa fa-asterisk obbligatorio"></i></label>
                                </div>
                            </div>
                            <div class="col-md-3 col-xs-12" style="margin-bottom: 15px;">
                                <div class="form-group form-md-line-input">
                                    <input type="text" class="form-control" id="autoreDoc" maxlength="200" />
                                    <label for="form_control_1">Autore </label>
                                </div>
                            </div>
                  			
                  			 <div class="col-md-3" style="margin-bottom: 15px;">
            					<span class="help-block">Data Pubblicazione<i class="fa fa-asterisk obbligatorio"></i></span>
            					<input id="dataPubblicazioneDoc"   class="form-control form-control-inline input-medium date-picker" type="text" size="16" >
							</div>
                  			
                  			
                  			
                  			
                  			<div class="col-md-3 col-xs-12" style="margin-bottom: 15px;">
                                <div class="form-group form-md-line-input">
                                    <input type="text" class="form-control" id="versDoc" maxlength="200" />
                                    <label for="form_control_1">Versione </label>
                                </div>
                            </div>
                        </div>
                         <div class="row" style="margin-bottom: 60px;">
                         	<div class="col-md-3 col-xs-12" style="margin-bottom: 15px;">
                                <div class="form-group form-md-line-input">
                                <label for="fileDoc">Seleziona file<i class="fa fa-asterisk obbligatorio"></i></label>
                                    <input type="file" id="fileDoc" value=""/>
                                    
                                </div>
                            </div>
                            <div class="col-md-3" style="margin-bottom: 15px;">
                                <div class="form-group">
                                    <label for="form_control_2" style="top: 0; font-size: 13px; color: #888888; opacity: 1; width: 100%; position: absolute;">Tipo documento <i class="fa fa-asterisk obbligatorio"></i></label><br>
                                   <select class="form-control input-medium select2me" id="selectTipoDocumento">
                           				<option selected hidden></option>
                           					<s:iterator value="tplDocMedia" var="list">
                              					<option id="<s:property value="#list.id" />" value="<s:property value="#list.id" />">
                              						<s:property value="#list.descrizione" />
                              					</option>
                          		 			</s:iterator>
                       	 			</select>
                                </div>
                            </div>
                         </div>
                    </div>
                    <div class="form-actions noborder" style="position: relative; bottom: 10px;">
                    <!-- modificaPraticheAccessi.js -->
                       <button type="button" class="btn default" onclick="salvaDocumento();">Salva</button>
                        
                        <button type="button" class="btn default" onclick="closeAsideNewDoc()">Annulla</button>
                    </div>
               	</form>
            </div>
        </div>
    </aside>