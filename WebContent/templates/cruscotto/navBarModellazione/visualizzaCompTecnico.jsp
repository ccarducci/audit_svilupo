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
                                    
                                            <!-- BEGIN Portlet PORTLET-->
                                            <div class="portlet light accessori">

                                                <div class="portlet-body">
                                                    <div class="tab-content">
														  <div class="page-head" style="margin-bottom: 30px">
                        										<div class="page-title">
                            										<h1> Componente Tecnico</h1>
                        										</div>
                    									  </div>

                                                        <div class="tab-pane active" id="datiGenerali">

                                                                <div class="portlet">
                                                                	<div class="portlet-body">
                                                                	<s:set var="idCt" value="%{compTecnico.id}" />
																	<input type="hidden" value="<s:property value="idCt"/>" id="upIdCT">
                                                                    	<div class="row" style="margin-bottom: 30px;">
                                                                        	<div class="col-md-3 col-xs-12" style="margin-bottom: 15px;">
                                                                            	<div class="form-group form-md-line-input">
                                                                             	<s:set var="descr" value="%{compTecnico.descrizione.trim()}" />
                                                                               	 <input type="text" class="form-control" id="upDescrCT"  value="<s:property value="descr"/>" readonly/>
                                                                                	<label for="upDescrDom">Descrizione</label>
                                                                            </div>
                                                                        </div>
                                                                        <div class="col-md-3 col-xs-12" style="margin-bottom: 15px;">
                                                                            <div class="form-group form-md-line-input">
                                                                              <s:set var="cod" value="%{compTecnico.codice.trim()}" />
                                                                                <input type="text" class="form-control" value="<s:property value="cod"/>"  id="upCodCT" maxlength="5" readonly/>
                                                                                <label for="upCodDom">Codice </label>
                                                                            </div>
                                                                        </div>
                                                                                                      
                                                                        
                                                                        <div class="col-md-3" style="margin-bottom: 15px;">
                                                                        <s:set var="dataInizioCtUp" value="%{compTecnico.dataInizio}" />
            																<span class="help-block">Data Inizio</span>
            																<input id="upDataInizioCt" value="<s:date name="dataInizioCtUp" format="dd/MM/yyyy" />"  class="form-control form-control-inline input-medium date-picker" type="text" size="16" readonly >
																		</div>
                                                                        
                                                                                    
                                                                        <div class="col-md-3" style="margin-bottom: 15px;">
                                                                        <s:set var="dataFineCtUp" value="%{compTecnico.dataFine}" />
            																<span class="help-block">Data Fine</span>
            																<input id="upDataFineCt" value="<s:date name="dataFineCtUp" format="dd/MM/yyyy" />"  class="form-control form-control-inline input-medium date-picker" type="text" size="16" readonly>
																		</div>

                                                                    </div>
                                                                    
                                                                    <div class="row" style="margin-bottom: 30px;">
                                                                        	<div class="col-md-3 col-xs-12" style="margin-bottom: 15px;">
                                                                            	<div class="form-group form-md-line-input">
                                                                             	<s:set var="aut" value="%{compTecnico.autore.trim()}" />
                                                                               	 <input type="text" class="form-control" id="aut"  value="<s:property value="aut"/>" readonly/>
                                                                                	<label for="upDescrDom">Autore</label>
                                                                            </div>
                                                                        </div>
                                                                        <div class="col-md-3" style="margin-bottom: 15px;">
                                                                        <s:set var="dataP" value="%{compTecnico.dataPubblicazione}" />
            																<span class="help-block">Data Pubblicazione</span>
            																<input id="upDataFineCt" value="<s:date name="dataP" format="dd/MM/yyyy" />"  class="form-control form-control-inline input-medium date-picker" type="text" size="16" readonly>
																		</div>
                                                                                                      
                                                                        
                                                                      <div class="col-md-3 col-xs-12" style="margin-bottom: 15px;">
                                                                      	<div class="form-group form-md-line-input">
                                                                        <s:set var="versione" value="%{compTecnico.versione}" />
            																<input type="text" class="form-control" value="<s:property value="versione"/>"  id="upVer" readonly/>
																				<label for="upDescrDom">Versione</label>
																		</div>
                                                                        </div>
                                                                                    
                                                                     
																		<div class="col-md-3 col-xs-12" style="margin-bottom: 15px;">
                                                                            	<div class="form-group form-md-line-input">
                                                                             	  <s:set var="tipo" value="%{tipoCompTecn}" />
                                                                               	 <input type="text" class="form-control" value="<s:property value="tipo"/>"  id="upti" readonly/>
                                                                                	<label for="upDescrDom">Tipo</label>
                                                                            </div>
                                                                        </div>
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
                                  										<a onclick="scaricaAllegatoCompTec()" class="btn btn-default btn-sm del" download>
                                    									<i class="fa fa-download"></i> Scarica </a>
                                								</div>
                                							</div>
                                						</div>
                                					</div>
                                					<div class="row">
                                        				<div class="col-md-12 ">
                                        					<div class="portlet-body">
                                								<table class="table table-striped table-bordered table-advance table-hover" id="allegatiCompTecTable">
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
																	
																	
                                                                   

                                                                  
                                                                    </div>
                                                                </div>

                                                            </div>
                                                        </div>

                                                    </div>
                                               

                                         
                                        </div>
                                    </div>
                                </div>
                            </div>
                        
