<%@ taglib prefix="s" uri="/struts-tags"%>
<aside class="menu-sx-levelone-facility add">
   <div class="portlet light">
      <div class="portlet-title">
         <div class="caption font-green"><i class="icon-pin font-green"></i>
            <span class="caption-subject bold uppercase">NUOVA CAMPAGNA</span>
         </div>
      </div>
      <div class="portlet-body form">
         <form role="form">
            <div class="form-body">
               <div class="row" style="margin-bottom: 40px;">
                  <div class="col-md-6" style="margin-bottom: 15px;">
                     <div class="form-group">
                        <label for="form_control_2"
                           style="top: 0; font-size: 13px; color: #888888; opacity: 1; width: 100%; position: absolute;">Audit
                        <i class="fa fa-asterisk obbligatorio"></i></label><br>
                        <select class="form-control input-medium select2me" data-placeholder="Audit" id="select">
                           <option selected hidden></option>
                           <s:iterator value="listAudit" var="list">
                              <option id="<s:property value="#list.idAudit" />" value="<s:property value="#list.idAudit" />">
                              <s:property value="#list.nome" />
                              </option>
                           </s:iterator>
                        </select>
                     </div>
                  </div>
                  <div class="col-md-6" style="margin-bottom: 15px;">
                     <div class="form-group form-md-line-input"><input type="text"
                        class="form-control" id="nomeCampagna" value="" style="height: 38px;">
                        <label for="form_control_2">Campagna<i class="fa fa-asterisk obbligatorio"></i></label>
                     </div>
                  </div>
               </div>
               <div class="row" style="margin-bottom: 40px;">
                  <div class="col-md-3" style="margin-bottom: 15px;">
                     <div class="form-group">
                        <div class="input-group input-medium date date-picker"
                           data-date-format="dd-mm-yyyy" data-date-start-date="+0d">
                          <span class="help-block"> Data inizio<i class="fa fa-asterisk obbligatorio"></i> </span>
                            <input id="dataInizio" class="form-control form-control-inline input-medium date-picker" size="16" type="text" >
                        </div>
                       
                     </div>
                  </div>
                  <div class="col-md-3" style="margin-bottom: 15px;">
                     <div class="form-group">
                        <div class="input-group input-medium date date-picker"
                           data-date-format="dd-mm-yy" data-date-start-date="+0d">
                            <span class="help-block"> Data fine</span>
                         
                           <input id="dataFine" class="form-control form-control-inline input-medium date-picker" size="16" type="text" >
                           
                        </div>
                    
                     </div>
                  </div>
                  <div class="col-md-3" style="margin-bottom: 15px;">
                     <div class="form-group">
                        <div class="input-group input-medium date date-picker"
                           data-date-format="dd-mm-yyyy" data-date-start-date="+0d">
                           <span class="help-block"> Data Inizio Osservazione<i class="fa fa-asterisk obbligatorio"></i></span>
                         
                          <input id="inizioOsservazione" class="form-control form-control-inline input-medium date-picker" size="16" type="text" >
                        </div>
                    
                     </div>
                  </div>
                  <div class="col-md-3" style="margin-bottom: 15px;">
                     <div class="form-group">
                        <div class="input-group input-medium date date-picker"
                           data-date-format="dd-mm-yyyy" data-date-start-date="+0d">
                            <span class="help-block"> Data fine Osservazione<i class="fa fa-asterisk obbligatorio"></i> </span>
                         
                             <input id="fineOsservazione" class="form-control form-control-inline input-medium date-picker" size="16" type="text" >
                        </div>
                       
                     </div>
                  </div>
               </div>
            </div>
            <div class="row" style="margin-bottom: 40px;">
                  <div class="col-md-12" style="margin-bottom: 15px;">
                  <label style="color:red;" id="info">Quest* campi sono obbligatori</label>
                  </div>
            </div>
            <div class="form-actions noborder"
               style="position: absolute; bottom: 10px;">
               <button type="button" class="btn blue" onclick="salvaCampagna()">Salva</button>
               <button type="button" class="btn default" onclick="annullaSalvataggio()">Annulla</button>
            </div>
         </form>
      </div>
   </div>
</aside>
<!-- END SIDEBAR DX -->