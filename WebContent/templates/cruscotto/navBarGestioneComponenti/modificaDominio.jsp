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
                            <h1>Modifica Dominio</h1>
                        </div>
                        <!-- BEGIN PAGE BREADCRUMB -->
                        <ul class="page-breadcrumb breadcrumb">
                            <li>
                                Gestione componenti
                                <i class="fa fa-circle"></i>
                            </li>
                            <li>
                                <a href="#" onclick="clickMenu('componenti-domini'); loadDiv('/CruscottoAuditAtpoWebWeb/getDomini', 'appView', null, 'initDomini');">
                          Domini</a>

                                <i class="fa fa-circle"></i>
                            </li>
                            <li>
                                Modifica dominio
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
                                                            <a href="#datiGenerali" onclick="loadDiv('/CruscottoAuditAtpoWebWeb/modificaDominio?idDominio=<%=user.getIdDominio()%>', 'appView', null, null );" oc data-toggle="tab">
                                                    			Dati generali </a>
                                                        </li>
                                                        <li>
                                                            <a href="#allegati" onclick="loadDiv('/CruscottoAuditAtpoWebWeb/getAllegati?idDominio=<%=user.getIdDominio()%>', 'allegati', null, 'initAllegatiTables');" data-toggle="tab">
                                                    			Allegati </a>
                                                        </li>
                                                        <li>
                                                            <a href="#valori" onclick="loadDiv('/CruscottoAuditAtpoWebWeb/getDominiValori', 'valori', null, 'initDominiValoriTables');" data-toggle="tab">
                                                    			Valori </a>
                                                        </li>

                                                    </ul>
                                                </div>

                                                <div class="portlet-body">
                                                    <div class="tab-content">

                                                        <!-- ***************************************** TAB 1 (DATI GENERALI) ******************************************-->

                                                        <div class="tab-pane active" id="datiGenerali">

                                                            <div class="portlet">
                                                                <div class="portlet-body">
                                                                <s:set var="idDom" value="%{dominio.id}" />
																<input type="hidden" value="<s:property value="idDom"/>" id="upIdDom">
                                                                    <div class="row" style="margin-bottom: 30px;">
                                                                        <div class="col-md-3 col-xs-12" style="margin-bottom: 15px;">
                                                                            <div class="form-group form-md-line-input">
                                                                             <s:set var="descr" value="%{dominio.descrizione.trim()}" />
                                                                                <input type="text" class="form-control" id="upDescrDom"  value="<s:property value="descr"/>" />
                                                                                <label for="upDescrDom">Descrizione<i class="fa fa-asterisk obbligatorio"></i></label>
                                                                            </div>
                                                                        </div>
                                                                        <div class="col-md-3 col-xs-12" style="margin-bottom: 15px;">
                                                                            <div class="form-group form-md-line-input">
                                                                              <s:set var="cod" value="%{dominio.codice.trim()}" />
                                                                                <input type="text" class="form-control" value="<s:property value="cod"/>"  id="upCodDom" maxlength="5" />
                                                                                <label for="upCodDom">Codice <i class="fa fa-asterisk obbligatorio"></i></label>
                                                                            </div>
                                                                        </div>
                                                                       
                                                                        
                                                                        <div class="col-md-3" style="margin-bottom: 15px;">
                                                                        <s:set var="dataInizioDominioUp" value="%{dominio.dataInizio}" />
            																<span class="help-block">Data Inizio<i class="fa fa-asterisk obbligatorio"></i></span>
            																<input id="upDataInizioDom" value="<s:date name="dataInizioDominioUp" format="dd/MM/yyyy" />"   class="form-control form-control-inline input-medium date-picker" type="text" size="16" >
																		</div>
                                                                        
                                                                        <div class="col-md-3" style="margin-bottom: 15px;">
                                                                         <s:set var="dataFineDominioUp" value="%{dominio.dataFine}" />
            																<span class="help-block">Data Fine</span>
            																<input id="upDataFineDom" value="<s:date name="dataFineDominioUp" format="dd/MM/yyyy" />"   class="form-control form-control-inline input-medium date-picker" type="text" size="16" >
																		</div>
                                                                        
                                                                       

                                                                    </div>

                                                                    <div class="row" style="margin-bottom: 30px;">
                                                                    <div class="col-md-3" style="margin-bottom: 15px;">
																		 <div class="form-actions noborder" style=" bottom: 10px;">
                   															 <!-- modificaPraticheAccessi.js -->
                       														<button type="button" class="btn blue" onclick="salvaModificaDominio();">Salva</button>
                         												</div>
                                                                    </div>
																	</div>
                                                                </div>

                                                            </div>
                                                        </div>
                                                        <!-- **************************************** TAB 2 (DATI CONTESTO) ***************************************** -->

                                                        <div class="tab-pane" id="allegati">
                                                            <!-- CONTENUTO TAB  -->
                                                        </div>
                                                        
                                                        <!-- **************************************** TAB 3 (VALORI) ***************************************** -->

                                                        <div class="tab-pane" id="valori">
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