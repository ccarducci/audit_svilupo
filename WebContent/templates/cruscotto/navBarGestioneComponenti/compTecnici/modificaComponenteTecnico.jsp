<%@ taglib prefix="s" uri="/struts-tags" %>
    <%@ page import="it.tecnet.crs.session.DatiUtente"%>
        <%@ page import="org.apache.struts2.ServletActionContext" %>
            <%@ page import="java.util.*" %>
                <%
    	HttpServletRequest r = ServletActionContext.getRequest();
		DatiUtente user= (DatiUtente)r.getSession().getAttribute("DatiUtente");
%>
                    <!-- BEGIN CONTENT -->

                    <!-- BEGIN PAGE HEAD -->
                    <div class="page-head">
                        <!-- BEGIN PAGE TITLE -->
                        <div class="page-title">
                            <!-- <h1>ACL Engine <small>gestione autorizzazioni applicative</small></h1> -->
                            <h1>Modifica componente tecnico</h1>
                        </div>
                        <!-- BEGIN PAGE BREADCRUMB -->
                        <ul class="page-breadcrumb breadcrumb">
                            <li>
                                Gestione componenti
                                <i class="fa fa-circle"></i>
                            </li>
                            <li>
                                <a href="#" onclick="clickMenu('componenti-compTecnici');  loadDiv('/CruscottoAuditAtpoWebWeb/getCompTecnici', 'appView', null, 'initCompTecnici' );">
                         		 Componente tecnico</a>

                                <i class="fa fa-circle"></i>
                            </li>
                            <li>
                                Modifica componente tecnico
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
                                                <div class="portlet-title tabbable-line">
                                                    <ul class="nav nav-tabs">
                                                        <li class="active">
                                                            <a href="#datiGeneraliCompTec" onclick="loadDiv('/CruscottoAuditAtpoWebWeb/modificaCompTecnico?idCompTecnico=<%=user.getIdComponenteTecnico()%>', 'appView', null, null );" oc data-toggle="tab">
                                                    			Dati generali </a>
                                                        </li>
                                                        <li>
                                                            <a href="#allegatiCT" onclick="loadDiv('/CruscottoAuditAtpoWebWeb/getAllegatiCT?idCompTecnico=<%=user.getIdComponenteTecnico()%>', 'allegatiCT', null, 'initAllegatiCtTables');" data-toggle="tab">
                                                    			Allegati </a>
                                                        </li>

                                                    </ul>
                                                </div>

                                                <div class="portlet-body">
                                                    <div class="tab-content">

                                                        <!-- ***************************************** TAB 1 (DATI GENERALI) ******************************************-->

                                                        <div class="tab-pane active" id="datiGeneraliCompTec">

                                                            <div class="portlet">
                                                                <div class="portlet-body">
                                                                <s:set var="idCt" value="%{compTecnico.id}" />
																<input type="hidden" value="<s:property value="idCt"/>" id="upIdCT">
                                                                    <div class="row" style="margin-bottom: 30px;">
                                                                        <div class="col-md-3 col-xs-12" style="margin-bottom: 15px;">
                                                                            <div class="form-group form-md-line-input">
                                                                             <s:set var="descr" value="%{compTecnico.descrizione.trim()}" />
                                                                                <input type="text" class="form-control" id="upDescrCT"  value="<s:property value="descr"/>" />
                                                                                <label for="upDescrDom">Descrizione<i class="fa fa-asterisk obbligatorio"></i></label>
                                                                            </div>
                                                                        </div>
                                                                        <div class="col-md-3 col-xs-12" style="margin-bottom: 15px;">
                                                                            <div class="form-group form-md-line-input">
                                                                              <s:set var="cod" value="%{compTecnico.codice.trim()}" />
                                                                                <input type="text" class="form-control" value="<s:property value="cod"/>"  id="upCodCT" maxlength="5" />
                                                                                <label for="upCodDom">Codice <i class="fa fa-asterisk obbligatorio"></i></label>
                                                                            </div>
                                                                        </div>
                                                                                                      
                                                                        
                                                                        <div class="col-md-3" style="margin-bottom: 15px;">
                                                                        <s:set var="dataInizioCtUp" value="%{compTecnico.dataInizio}" />
            																<span class="help-block">Data Inizio<i class="fa fa-asterisk obbligatorio"></i></span>
            																<input id="upDataInizioCt" value="<s:date name="dataInizioCtUp" format="dd/MM/yyyy" />"  class="form-control form-control-inline input-medium date-picker" type="text" size="16" >
																		</div>
                                                                        
                                                                                    
                                                                        <div class="col-md-3" style="margin-bottom: 15px;">
                                                                        <s:set var="dataFineCtUp" value="%{compTecnico.dataFine}" />
            																<span class="help-block">Data Fine</span>
            																<input id="upDataFineCt" value="<s:date name="dataFineCtUp" format="dd/MM/yyyy" />"  class="form-control form-control-inline input-medium date-picker" type="text" size="16" >
																		</div>

                                                                    </div>
                                                                    
                                                                      <div class="row" style="margin-bottom: 30px;">
                                                                        <div class="col-md-3 col-xs-12" style="margin-bottom: 15px;">
                                                                            <div class="form-group form-md-line-input">
                                                                             <s:set var="autore" value="%{compTecnico.autore.trim()}" />
                                                                                <input type="text" class="form-control" id="upAutoreCT"  value="<s:property value="autore"/>" />
                                                                                <label for="upDescrDom">Autore</label>
                                                                            </div>
                                                                        </div>
                                                                        <div class="col-md-3 col-xs-12" style="margin-bottom: 15px;">
                                                                            <div class="form-group form-md-line-input">
                                                                              <s:set var="dataP" value="%{compTecnico.dataPubblicazione}" />
                                                                               	<input id="upDataPCT" value="<s:date name="dataP" format="dd/MM/yyyy" />"  class="form-control form-control-inline input-medium date-picker" type="text" size="16" >
                                                                                <label for="upDataPCT">Data Pubblicazione</label>
                                                                            </div>
                                                                        </div>
                                                                                                      
                                                                        
                                                                        <div class="col-md-3" style="margin-bottom: 15px;">
                                                                        <s:set var="versione" value="%{compTecnico.versione}" />
            																<span class="help-block">Versione</span>
            																 <input type="text" class="form-control" value="<s:property value="versione"/>"  id="upVersioneCT"/>
																		</div>
                                                                        
                                                                                    
                                                                        <div class="col-md-3" style="margin-bottom: 15px;">
                                											<div class="form-group">
                                    											<label for="form_control_2" style="top: 0; font-size: 13px; color: #888888; opacity: 1; width: 100%; position: absolute;">Tipo <i class="fa fa-asterisk obbligatorio"></i></label><br>
                                   												<select class="form-control input-medium select2me" id="selectupTipoCompTec">
                           															 <s:set var="tipoCompTec" value="%{tipoCompTecnico}" />
                           															<option selected id="<s:property value="#tipoCompTec.id"/>" value="<s:property value="#tipoCompTec.id" />"><s:property value="#tipoCompTec.descrizione" /></option>
                           															<s:iterator value="listCompTec" var="list">
                              															<option id="<s:property value="#list.id" />" value="<s:property value="#list.id" />">
                              															<s:property value="#list.descrizione" />
                              															</option>
                          		 													</s:iterator>
                       	 														</select>
                                											</div>
                            											</div>

                                                                    </div>
                                                                    
                                                                    
                                                                    

                                                                    <div class="row" style="margin-bottom: 30px;">
                                                                    <div class="col-md-3" style="margin-bottom: 15px;">
																		 <div class="form-actions noborder" style=" bottom: 10px;">
                   															 <!-- modificaPraticheAccessi.js -->
                       														<button type="button" class="btn blue" onclick="salvaModificaCompTec();">Salva</button>
                         												</div>
                                                                    </div>
																	</div>
                                                                </div>

                                                            </div>
                                                        </div>
                                                        <!-- **************************************** TAB 2 (DATI CONTESTO) ***************************************** -->

                                                        <div class="tab-pane" id="allegatiCT">
                                                            <!-- CONTENUTO TAB  -->
                                                        </div>

                                                    </div>
                                                </div>

                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <!-- END SAMPLE TABLE PORTLET-->

                        </div>
                    </div>

                    <!-- MODAL -->
                    <div class="modal-custom">
                    </div>