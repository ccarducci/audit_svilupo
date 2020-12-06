<%@ taglib prefix="s" uri="/struts-tags" %>

<aside class="menu-sx-levelone-facility aside-dx-cruscottoAudit new-varianteNot">
        <div class="portlet light">
            <div class="portlet-title">
                <div class="caption font-green">
                    <i class="icon-pin font-green"></i>
                    <span class="caption-subject bold uppercase">NUOVA VARIANTE COMPORTAMENTALE - <span style="color: #b7b7b7; font-size: 14px;">Audit <s:property value="%{idAudit}" /></span></span>
                </div>
            </div>
            <div class="portlet">
                <div class="portlet-body form">
                    <form role="form">
                        <div class="form-body">
                            <div class="row" style="margin-bottom: 40px;">
                                <div class="col-md-4" style="margin-bottom: 15px;">
                                    <div class="form-group">
                                        <label for="faseVC" style="top: 0; font-size: 13px; color: #888888; opacity: 1; width: 100%; position: absolute;">Non conformita <i class="fa fa-asterisk obbligatorio"></i></label><br>
                                        <select id="faseVC" class="form-control input-medium select2me" data-placeholder="Scegli Non conformita">
                                           <option value=""></option>
                                            <s:iterator value="nonConformita" var="ncl">
											
                    							<option id="<s:property value="#ncl.idMNonConf" />" value="<s:property value="#snd.idMNonConf" />">
                        							<s:property value="#ncl.descrizione" />
                   								</option>
                														
            								</s:iterator>
                                        </select>
                                    </div>
                                </div>
                                <div class="col-md-4" style="margin-bottom: 15px;">
                                    <div class="form-group form-md-line-input">
                                        <input type="text" class="form-control" id="descrizioneVC" value="" placeholder="" style="height: 38px;">
                                        <label for="descrizioneVC">Descrizione <i class="fa fa-asterisk obbligatorio"></i></label>
                                    </div>
                                </div>
                                <div class="col-md-4" style="margin-bottom: 15px;">
                                    <div class="form-group form-md-line-input">
                                        <input type="text" class="form-control" id="codiceVC" maxlength="4" value="" placeholder="" style="height: 38px;">
                                        <label for="codiceVC">Codice <i class="fa fa-asterisk obbligatorio"></i></label>
                                    </div>
                                </div>
                            </div>
                            <div class="row" style="margin-bottom: 40px;">
                                <div class="col-md-4" style="margin-bottom: 15px;">
                                    <div class="form-group form-md-line-input">
                                        <input type="text" class="form-control" id="pesoVC" value="" placeholder="" style="height: 38px;">
                                        <label for="pesoVC">Peso VC <i class="fa fa-asterisk obbligatorio"></i></label>
                                    </div>
                                </div>
                                <div class="col-md-4" style="margin-bottom: 15px;">
                                    <div class="form-group">
                                        <div class="input-group input-medium date date-picker" data-date-format="dd-mm-yyyy" data-date-start-date="+0d">
                                              <span class="help-block">
                                        Data inizio </span>
                                           
                                         
                                                     <input id="diVC" class="form-control form-control-inline input-medium date-picker" size="16" type="text" >
                                        </div>
                                        <!-- /input-group -->
                                     
                                    </div>
                                </div>
                                <div class="col-md-4" style="margin-bottom: 15px;">
                                    <div class="form-group">
                                        <div class="input-group input-medium date date-picker" data-date-format="dd-mm-yyyy" data-date-start-date="+0d">
                                             <span class="help-block">
                                        Data fine </span>
                                          
                                         <input id="dfVC" class="form-control form-control-inline input-medium date-picker" size="16" type="text" >
                                        </div>
                                        <!-- /input-group -->
                                      
                                    </div>
                                </div>
                            </div>
                        </div>
                    </form>
                </div>
                <div class="form-actions noborder" style="position: absolute; bottom: 50px;">
                    <button type="button" class="btn blue" onclick="saveNewVR()">Salva</button>
                    <button type="button" class="btn default" onclick="slideAsideNew('');">Annulla</button>
                </div>
            </div>
            <!-- END SAMPLE TABLE PORTLET-->
        </div>
    </aside>