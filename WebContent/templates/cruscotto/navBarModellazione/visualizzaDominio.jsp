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

                                                <div class="portlet-body">
                                                    <div class="tab-content">
														  <div class="page-head" style="margin-bottom: 30px">
                        										<div class="page-title">
                            										<h1> Dominio</h1>
                        										</div>
                    									  </div>

                                                        <div class="tab-pane active" id="datiGenerali">

                                                            <div class="portlet">
                                                                <div class="portlet-body">
                                                                    <s:set var="idDom" value="%{dominio.id}" />
                                                                    <input type="hidden" value="<s:property value=" idDom "/>" id="upIdDom">
                                                                    <div class="row" style="margin-bottom: 30px;">
                                                                        <div class="col-md-3 col-xs-12" style="margin-bottom: 15px;">
                                                                            <div class="form-group form-md-line-input">
                                                                                <s:set var="descr" value="%{dominio.descrizione.trim()}" />
                                                                                <input type="text" class="form-control" id="upDescrDom" value="<s:property value="descr"/>" readonly/>
                                                                                <label for="upDescrDom">Descrizione</label>
                                                                            </div>
                                                                        </div>
                                                                        <div class="col-md-3 col-xs-12" style="margin-bottom: 15px;">
                                                                            <div class="form-group form-md-line-input">
                                                                                <s:set var="cod" value="%{dominio.codice.trim()}" />
                                                                                <input type="text" class="form-control" value="<s:property value="cod"/>" id="upCodDom" maxlength="5" readonly/>
                                                                                <label for="upCodDom">Codice </label>
                                                                            </div>
                                                                        </div>

                                                                        <div class="col-md-3" style="margin-bottom: 15px;">
                                                                            <s:set var="dataInizioDominioUp" value="%{dominio.dataInizio}" />
                                                                            <span class="help-block">Data Inizio</span>
                                                                            <input id="upDataInizioDom" value="<s:date name="dataInizioDominioUp" format="dd/MM/yyyy" />" class="form-control form-control-inline input-medium date-picker" type="text" size="16" readonly>
                                                                        </div>

                                                                        <div class="col-md-3" style="margin-bottom: 15px;">
                                                                            <s:set var="dataFineDominioUp" value="%{dominio.dataFine}" />
                                                                            <span class="help-block">Data Fine</span>
                                                                            <input id="upDataFineDom" value="<s:date name="dataFineDominioUp" format="dd/MM/yyyy" />" class="form-control form-control-inline input-medium date-picker" type="text" size="16" readonly>
                                                                        </div>

                                                                    </div>
																	
												<div class="row" style="margin-bottom: 30px">
													<div class="col-md-4 ">
                                        					<div class="page-head">
                                                                <div class="page-title">
                            										<h1> Allegati</h1>
                        										</div>
                                							</div>
                                					</div>
                                        				<div class="col-md-3 ">
                                        					<div class="portlet-body">
                                                                <div class="actions add-action">
                                  										<a onclick="scaricaAllegato()" class="btn btn-default btn-sm del" download>
                                    									<i class="fa fa-download"></i> Scarica </a>
                                								</div>
                                							</div>
                                						</div>
                                					</div>
                                					<div class="row">
                                        				<div class="col-md-12 ">
                                        					<div class="portlet-body">
                                                                <table class="table table-striped table-bordered table-advance table-hover" id="allagatiDominioTable">
                                                                            <thead>
                                                                                <th style="text-align: center;">
                                                                                </th>
                                                                                <th>Nome file</th>
                                                                                <th>Oggetto</th>
                                                                                <th>Data inizio</th>

                                                                            </thead>
                                                                            <tbody>
                                                                            </tbody>
                                                                        </table>
                                                                    </div>
                                        									</div>
                                        								</div>
																	
																	
                                                                   

                                                                    <div class="row" style="margin-bottom: 30px;">
                                                                        <div class="col-md-3" style="margin-bottom: 15px;">
                                                                            <div class="form-actions noborder" style=" bottom: 10px;">
                                                                                <!-- modificaPraticheAccessi.js -->

                                                                            </div>
                                                                        </div>
                                                                    </div>
                                                                </div>

                                                            </div>
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