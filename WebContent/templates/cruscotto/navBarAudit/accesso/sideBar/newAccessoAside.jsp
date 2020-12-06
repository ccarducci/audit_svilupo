<%@ taglib prefix="s" uri="/struts-tags"%>

<aside class="menu-sx-levelone-facility add">
        <div class="portlet light">
            <div class="portlet-title">
                <div class="caption font-green">
                    <i class="icon-pin font-green"></i>
                    <span class="caption-subject bold uppercase">NUOVO ACCESSO</span>
                </div>
            </div>
            <div class="portlet-body form">
                <form role="form">
                    <div class="form-body">
                        <div class="row" style="margin-bottom: 35px;">
                            <div class="col-md-4" style="margin-bottom: 15px;">
                                <div class="form-group">
                                    <label for="form_control_2" style="top: 0; font-size: 13px; color: #888888; opacity: 1; width: 100%; position: absolute;">Campagna <i class="fa fa-asterisk obbligatorio"></i></label><br>
                                   <select class="form-control input-medium select2me" data-placeholder="Audit" id="selectCampagna">
                           				<option selected hidden></option>
                           					<s:iterator value="campagnaDtoList" var="list">
                              					<option id="<s:property value="#list.idCampagna" />" value="<s:property value="#list.idCampagna" />">
                              						<s:property value="#list.nome" />
                              					</option>
                          		 			</s:iterator>
                       	 			</select>
                                </div>
                            </div>
                            <div class="col-md-4" style="margin-bottom: 15px;">
                                <div class="form-group">
                                     <label for="form_control" style="top: 0; font-size: 13px; color: #888888; opacity: 1; width: 100%; position: absolute;">Sede <i class="fa fa-asterisk obbligatorio"></i></label><br>
                                   <select class="form-control input-medium select2me" data-placeholder="" id="selectSede">
                           				<option selected hidden></option>
                           					<s:iterator value="listSede" var="listSede">
                              					<option id="<s:property value="#listSede.nomeSede" />" value="<s:property value="#listSede.nomeSede" />">
                              						<s:property value="#listSede.nomeSede" />
                              					</option>
                          		 			</s:iterator>
                       	 			</select>
                                </div>
                            </div>
                           
                        </div>
                        <div class="row" style="margin-bottom: 30px;">
                            <div class="col-md-4" style="margin-bottom: 15px;">
                                <div class="form-group">
                                    <div class="input-group input-medium date date-picker" data-date-format="dd-mm-yyyy" data-date-start-date="+0d">
                                        <span class="help-block">
                                    Data inizio<i class="fa fa-asterisk obbligatorio"></i> </span>
                                       <input id="AccessoDataInizio" class="form-control form-control-inline input-medium date-picker" size="16" type="text" >
                                        
                                    </div>
                                    <!-- /input-group -->
                                  
                                </div>
                            </div>
                            <div class="col-md-4" style="margin-bottom: 15px;">
                                <div class="form-group">
                                    <div class="input-group input-medium date date-picker" data-date-format="dd-mm-yyyy" data-date-start-date="+0d">
                                       <!-- /input-group -->
                                    <span class="help-block">
                                    Data fine </span>
                                         <input id="AccessoDataFine" class="form-control form-control-inline input-medium date-picker" size="16" type="text" >
                                    </div>
                                 
                                </div>
                            </div>
                            <div class="col-md-4" style="margin-bottom: 15px;"></div>                            
                        </div>
                         
                        <div class="row" style="margin-bottom: 40px;">
                            
                            <div class="col-md-4" style="margin-bottom: 15px;"></div>
                            <div class="col-md-4" style="margin-bottom: 15px;"></div>
                        </div>
                    </div>
                    <div class="form-actions noborder" style="position: absolute; bottom: 10px;">
                        <button type="button" class="btn blue" onclick="salvaAccessoAudit()">Salva</button>
                        <!-- accessoAudit.js -->
                        <button type="button" class="btn default" onclick="slideAsideNew(''); stopLoading();">Annulla</button>
                    </div>
                </form>
            </div>
        </div>
    </aside>