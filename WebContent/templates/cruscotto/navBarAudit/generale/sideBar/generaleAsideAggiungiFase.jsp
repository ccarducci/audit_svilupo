<%@ taglib prefix="s" uri="/struts-tags" %>  

 <aside class="menu-sx-levelone-facility height-600 add" style="margin-top: -325px;">
        <div class="portlet light">
            <div class="portlet-title">
                <div class="caption font-green">
                    <i class="icon-pin font-green"></i>
                    <span class="caption-subject bold uppercase">AGGIUNGI</span>
                </div>
            </div>
            <div class="portlet">
                <div class="row">
                    <div class="col-lg-4">
                        <div class="form-group">
                            <label for="form_control_2" style="top: 0; font-size: 13px; color: #888888; opacity: 1; width: 100%; position: absolute;"><i class="fa fa-asterisk obbligatorio"></i></label><br>
                        
                         <!-- crsInit.js/showFasiDaAggiungereAsideTable -->
                           <select class="form-control input-medium select2me" data-placeholder="Processo" onchange="showFasiDaAggiungereAsideTable(value)">
                               <option value=""></option>
                                <s:iterator value="listMenu" var="list">                       
                                	<option id="<s:property value="#list.idProcesso" />" value=" <s:property value="#list.idProcesso" />"> <s:property value="#list.descrizione" /></option>
                               </s:iterator> 
                            </select>
                            
                           
                        </div>
                    </div>
                </div>
                
              <div class="portlet-body" style="overflow-y: auto; overflow-x: hidden; height: 430px;">
                    <table class="table table-striped table-bordered table-advance table-hover" id="fasi_da_aggiungere">
                        <thead>
                           <tr>
                              <th style="width: 80px; text-align: center;"></th>
                              <th>Descrizione processo</th>
                              <th>Descrizione fase</th>
                              <th>Input</th>
                                <th>Output</th>
                               <th>UOC</th>
                            </tr>
                        </thead>
                        
                        <tbody>
                        </tbody>
              		</table>
                    
                </div> 
                <div class="form-actions noborder" style= bottom: 50px;">
                <!-- generale.js -->
                    <button type="button" class="btn blue" onclick="addFasiAllAudit()">Salva</button>
                    <button type="button" class="btn default" onclick="slideAsideNew('');">Chiudi</button>
                </div>
            </div>
            <!-- END SAMPLE TABLE PORTLET-->
            
            
        </div>
    </aside>