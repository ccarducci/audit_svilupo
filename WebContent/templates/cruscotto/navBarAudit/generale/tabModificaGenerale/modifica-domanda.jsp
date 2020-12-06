<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page import="it.tecnet.crs.session.DatiUtente" %> 
<%@ page import="javax.servlet.http.HttpServletRequest" %> 
<%@ page import="org.apache.struts2.ServletActionContext" %>
<%@ page import="java.util.*" %>

	<!-- BEGIN CONTENT -->
	<%
    	HttpServletRequest r = ServletActionContext.getRequest();
		DatiUtente user= (DatiUtente)r.getSession().getAttribute("DatiUtente");
	%>
   
    
    <div>
	 
			<!-- BEGIN PAGE HEAD -->
			<div class="page-head">
				<!-- BEGIN PAGE TITLE -->
				<div class="page-title">
                    <!-- <h1>ACL Engine <small>gestione autorizzazioni applicative</small></h1> -->
					<h1>Modifica domanda <span style="color: #b7b7b7; font-size: 14px;">- Domanda <s:property value="%{domandaDto.idDomanda}" /></span></h1>
				</div>
                <!-- BEGIN PAGE BREADCRUMB -->
                <ul class="page-breadcrumb breadcrumb">
                    <li>
                        Organizzazione Audit
                        <i class="fa fa-circle"></i>
                    </li>
                    <li>
                    <a href="#" onclick="loadDiv('/CruscottoAuditAtpoWebWeb/auAudit', 'appView', null, 'initAuditGenerale' );">
                        Generale </a>
                         <i class="fa fa-circle"></i>
                        
                    </li> 
                    <li>
                    <a href="#" onclick="loadDiv('/CruscottoAuditAtpoWebWeb/modificaAuditGenerale?idAudit=<%=user.getIdAudit()%>', 'appView', null, 'initAuditGeneraleModificaTables' );loadDiv('/CruscottoAuditAtpoWebWeb/questionario', 'portlet_tab5_q', null, 'initQuestionario' );">
                        Modifica domanda </a>
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
                        <div class="portlet-body">
                            <div class="row">
                                <div class="col-md-12 ">
                                    <!-- BEGIN Portlet PORTLET-->
                                    <div class="portlet light accessori">
                                        <div class="portlet">
                                            <div class="portlet-body form">
                                                <form role="form">
                                                    <div class="form-body">
                                                        <div class="row" style="margin-bottom: 40px;">
                                                            <div class="col-md-3" style="margin-bottom: 15px;">
                                                                <div class="form-group form-md-line-input">
                                                                <s:set var="descrizioneUpd" value="%{domandaDto.descrizione}" />
                                                                    <input type="text" class="form-control" id="descrizioneUpd" value="<s:property value="descrizioneUpd"/>" style="height: 38px;">
                                                                    <label for="descrizioneUpd">Descrizione</label>
                                                                </div>
                                                            </div>
                                                            <div class="col-md-3" style="margin-bottom: 15px;">
                                                                <div class="form-group form-md-line-input">
                                                                <s:set var="pesoUpD" value="%{domandaDto.peso}" />
                                                                    <input type="text" class="form-control" id="pesoUpD" value="<s:property value="pesoUpD"/>" style="height: 38px;">
                                                                    <label for="pesoUpD">Peso</label>
                                                                </div>
                                                            </div>
                                                            <div class="col-md-3" style="margin-bottom: 15px;">
                                                                <div class="form-group form-md-line-input">
                                                                <s:set var="valoreMaxRUpD" value="%{domandaDto.valMaxRsp}" />
                                                                    <input type="text" class="form-control" id="valoreMaxRUpD" value="<s:property value="valoreMaxRUpD"/>" style="height: 38px;">
                                                                    <label for="valoreMaxRUpD">Valore Max risposta</label>
                                                                </div>
                                                            </div>
                                                            <div class="col-md-3" style="margin-bottom: 15px;">
                                    							<div class="form-group">
                                        							<label for="rischioAUp" style="top: 0; font-size: 13px; color: #888888; opacity: 1; width: 100%; position: absolute;">Rischio associato <i class="fa fa-asterisk obbligatorio"></i></label><br>
                                        							<s:set var="descrRischioAUp" value="%{domandaDto.descrizioneRischio}" />
                                        							<s:set var="codRischioAUp" value="%{domandaDto.codiceRischio}" />
                                        							
                                        							<select class="form-control input-medium select2me" id="rischioAUp" data-placeholder="Scegli Rischio">
                                          								<option id="<s:property value="domandaDto.idRischio"/>">
                                          									<s:property value="codRischioAUp"/>-<s:property value="descrRischioAUp"/>
                                          								</option>
                                            							<s:iterator value="rischiAssociatiUpD" var="raup">
											
                    														<option id="<s:property value="#raup.idMRischio" />" value="<s:property value="#raup.idMRischio" />">
                        														<s:property value="#raup.codiceRischio" /> - <s:property value="#raup.descrizioneRischio" />
                   															</option>
                														
            															</s:iterator>	
                                        							</select>
                                    							</div>
                                							</div>
                                                        </div>
                                                        <div class="row" style="margin-bottom: 30px;">
                                                            <div class="col-md-3" style="margin-bottom: 15px;">
                                                                <div class="form-group form-md-line-input">
                                                                <s:set var="pesoPercupD" value="%{domandaDto.pesoPErcentuale}" />
                                                                    <input type="text" class="form-control" id="pesoPercupD" value="<s:property value="pesoPercupD"/>" style="height: 38px;">
                                                                    <label for="pesoPercupD">Peso %</label>
                                                                </div>
                                                            </div>
                                                            <div class="col-md-3" style="margin-bottom: 15px;">
                                                                <div class="form-group form-md-line-input">
                                                                 <s:set var="controlloProcessoUpD" value="%{domandaDto.controlloProcesso}" />
                                                                    <input type="text" class="form-control" id="controlloProcessoUpD" value="<s:property value="controlloProcessoUpD"/>" style="height: 38px;">
                                                                    <label for="controlloProcessoUpD">Controllo processo</label>
                                                                </div>
                                                            </div>
                                                            <div class="col-md-3" style="margin-bottom: 15px;">
                                                            	<label for="dataInizioUpD" style="top: 0;margin-bottom: 0;font-size: 13px;color: #888888;opacity: 1;"> Data inizio</label>
                                                                <div class="form-group">
                                                                    <div class="input-group input-medium date date-picker" data-date-format="dd-mm-yyyy" data-date-start-date="+0d">
                                            							 <s:set var="dataInizioUpD" value="%{domandaDto.dataInizio}" />
                                            						
                                            						<input id="dataInizioUpD"  value="<s:property value="dataInizioUpD"/>" class="form-control form-control-inline input-medium date-picker" size="16" type="text" >
                                        							</div>
                                        							
                                                                </div>
                                                            </div>
                                                            
                                                            
                                                            
                                                            <div class="col-md-3" style="margin-bottom: 15px;">
                                                            <label for="dataFineUpD" style="top: 0;margin-bottom: 0;font-size: 13px;color: #888888;opacity: 1;"> Data Fine</label>
                                                                <div class="form-group">
                                                                    <div class="input-group input-medium date date-picker" data-date-format="dd-mm-yyyy" data-date-start-date="+0d">
                                            							<s:set var="dataFineUpD" value="%{domandaDto.dataFine}" />
                                            						
                                            						   <input id="dataFineUpD" class="form-control form-control-inline input-medium date-picker"  value="<s:property value="dataFineUpD"/>" size="16" type="text" >
                                        							</div>
                                        						
                                                                </div>
                                                            </div>
                                                        </div>
                                                        <p><s:property value="dataFineUpD"/></p>
                                                    </div>
                                                </form>
                                            </div>
                                            <div class="form-actions noborder">
                                                <button type="button" class="btn blue" onclick="salvaModificaDomanda()">Salva</button>
                                                <button type="button" class="btn default" onclick="loadDiv('/CruscottoAuditAtpoWebWeb/modificaAuditGenerale?idAudit=<%=user.getIdAudit()%>', 'appView', null, 'initAuditGeneraleModificaTables' );">Annulla</button>
                                            </div>
                                        </div>
                                        <hr style="border-color: #989898;">
                                        <div class="portlet-body">
                                            <div class="row">
                                                <div class="col-lg-8 col-lg-offset-3" style="padding-top: 5px; padding-bottom: 10px;">
                                                    <div class="actions add-action">
                                                        <a data-toggle="modal" href="javascript:slideAsideInps('add-answer');" class="btn btn-default btn-sm" style="width:150px;">
                                                        <i class="fa fa-plus"></i> Nuova risposta </a>
                                                    </div>
                                                    <div class="actions add-action">
                                                        <a data-toggle="modal" href="#" onclick="getCheckBoxID('modificaRisposta')" class="btn btn-default btn-sm" style="width:150px;">
                                                        <i class="fa fa-edit"></i> Modifica risposta </a>
                                                    </div>
                                                    <div class="actions add-action">
                                                        <a data-toggle="modal" href="#" class="btn btn-default btn-sm del" style="width:150px;" onclick="getCheckBoxID('eliminaRisposta');">
                                                        <i class="fa fa-trash"></i> Elimina risposta </a>
                                                    </div>
                                                </div>
                                            </div>
                                            <table class="table table-striped table-bordered table-advance table-hover" id="risposteDomandaTable">
                                                <thead>
                                                    <tr>
                                                        <th style="text-align: center;">
                                                            <div class="form-group form-md-checkboxes">
                                                                <div class="md-checkbox-inline">
                                                                    <div class="md-checkbox">
                                                                        <input type="checkbox" id="checkAllRsp" class="md-check" onclick="checkAllRisp()">
                                                                        <label for="checkAllRsp">
                                                                            <span></span>
                                                                            <span class="check"></span>
                                                                            <span class="box"></span>
                                                                        </label>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </th>
                                                        <th>
                                                            Descrizione
                                                        </th>
                                                        <th>
                                                            Valore risposta
                                                        </th>
                                                        <th>
                                                            Copertura percentuale
                                                        </th>
                                                        <th>
                                                            Data inizio
                                                        </th>
                                                        <th>
                                                            Data fine
                                                        </th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                   
                                                </tbody>
                                            </table>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!-- END SAMPLE TABLE PORTLET-->

				</div>
			</div>
			
            <div class="modal fade" tabindex="-1" id="deleteRispostaModal" aria-hidden="true">
			  <div class="modal-dialog"  style="width: 500px !important; height: 200px !important;">
			    <div class="modal-content">
			      <div class="modal-header">
			        <div class="caption font-green">
	                    <span class="caption-subject bold uppercase">Eliminare le risposte selezionate?</span>
	                </div>
			        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
			          <span aria-hidden="true">&times;</span>
			        </button>
			      </div>
			      <!-- <div class="modal-body">
			        <p>Si sta eliminando la risposta selezionata. Procedere con l' operazione?</p>
			      </div>-->
			      <div class="modal-footer">
			        <button type="button" data-dismiss="modal" class="btn default">No</button>
			        <!-- generale.js -->
			        <button type="button" class="btn blue" onclick="eliminaRsp();">Si</button>
			      </div>
			    </div>
			  </div>
			</div> 
            
             <s:include value="sidebar/newRispostaAside.jsp" />	
             <s:include value="sidebar/updateRispostaAside.jsp" />	
            
            
			<!-- END PAGE CONTENT INNER -->
		</div>

    
    
    
