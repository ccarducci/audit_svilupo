<%@ taglib prefix="s" uri="/struts-tags" %>
<!-- BEGIN SIDEBAR DX -->
<aside class="menu-sx-levelone-facility add">
   <div class="portlet light">
      <div class="portlet-title">
         <div class="caption font-green"><i class="icon-pin font-green"></i>
            <span class="caption-subject bold uppercase">NUOVO COMPONENTE TECNICO</span>
         </div>
      </div>
      <div class="portlet-body form">
         <form>
                    <div class="form-body">
                        <div class="row" style="margin-bottom: 60px;">
                            <div class="col-md-3 col-xs-12" style="margin-bottom: 15px;">
                                <div class="form-group form-md-line-input">
                                    <input type="text" class="form-control" id="descrCT" maxlength="200" />
                                    <label for="form_control_1">Descrizione <i class="fa fa-asterisk obbligatorio"></i></label>
                                </div>
                            </div>
                            <div class="col-md-3 col-xs-12" style="margin-bottom: 15px;">
                                <div class="form-group form-md-line-input">
                                    <input type="text" class="form-control" id="codCT" maxlength="5" />
                                    <label for="form_control_1">Codice <i class="fa fa-asterisk obbligatorio"></i></label>
                                </div>
                            </div>
                            
                  			
                  			<div class="col-md-3" style="margin-bottom: 15px;">
            					<span class="help-block">Data Inizio<i class="fa fa-asterisk obbligatorio"></i></span>
            					<input id="dataInizioCT"   class="form-control form-control-inline input-medium date-picker" type="text" size="16" >
							</div>
                  			
                  			<div class="col-md-3" style="margin-bottom: 15px;">
            					<span class="help-block">Data Fine</span>
            					<input id="dataFineCT"   class="form-control form-control-inline input-medium date-picker" type="text" size="16" >
							</div>
                  			
                  			
                            
                        </div>
                        <div class="row" style="margin-bottom: 60px;">
                            <div class="col-md-3 col-xs-12" style="margin-bottom: 15px;">
                                <div class="form-group form-md-line-input">
                                    <input type="text" class="form-control" id="autoreCT" maxlength="200" />
                                    <label for="form_control_1">Autore</label>
                                </div>
                            </div>
                            <div class="col-md-3" style="margin-bottom: 15px;">
            					<span class="help-block">Data Pubblicazione</span>
            					<input id="dataPubblicazioneCT"   class="form-control form-control-inline input-medium date-picker" type="text" size="16" >
							</div>
                            <div class="col-md-3 col-xs-12" style="margin-bottom: 15px;">
                                <div class="form-group form-md-line-input">
                                    <input type="text" class="form-control" id="versioneCT" maxlength="5" />
                                    <label for="form_control_1">Versione</label>
                                </div>
                            </div>
                            
                  			<div class="col-md-3" style="margin-bottom: 15px;">
                                <div class="form-group">
                                    <label for="form_control_2" style="top: 0; font-size: 13px; color: #888888; opacity: 1; width: 100%; position: absolute;">Tipo <i class="fa fa-asterisk obbligatorio"></i></label><br>
                                   <select class="form-control input-medium select2me" id="selectTipoCompTec">
                           				<option selected hidden></option>
                           					<s:iterator value="listCompTec" var="list">
                              					<option id="<s:property value="#list.id" />" value="<s:property value="#list.id" />">
                              						<s:property value="#list.descrizione" />
                              					</option>
                          		 			</s:iterator>
                       	 			</select>
                                </div>
                            </div>
                  			
                  			
                            
                        </div>
                        
                        
                    </div>
                    <div class="form-actions noborder" style="position: absolute; bottom: 10px;">
                    <!-- modificaPraticheAccessi.js -->
                       <button type="button" class="btn default" onclick="salvaCompTecnico();">Salva</button>
                        
                        <button type="button" class="btn default" onclick="slideAsideNew('')">Annulla</button>
                    </div>
               	</form>
        
      </div>
   </div>
</aside>