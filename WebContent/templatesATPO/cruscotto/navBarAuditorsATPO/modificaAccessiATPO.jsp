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
					<h1>Accessi <small>Sede di <s:property value="%{nomeSede}" /></small></h1>
				</div>
                <!-- BEGIN PAGE BREADCRUMB -->
                <ul class="page-breadcrumb breadcrumb">
                    <li>
                        Auditors ATPO
                        <i class="fa fa-circle"></i>
                    </li>
                      <li>
                    <a href="#" onclick="clickMenu('auditors-accessi'); loadDiv('/CruscottoAuditAtpoWebWeb/accessi', 'appView', null, 'initAuditors' );">
                          Accessi</a>
                        
					   <i class="fa fa-circle"></i>
                    </li>
                    <li>
                        Modifica Accessi
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
                                                    <a href="#datiGenerali" onclick="loadDiv('/CruscottoAuditAtpoWebWeb/modificaAccessoATPO?idSessione=<%=user.getIdSessione()%>', 'appView', null, null );" oc data-toggle="tab">
                                                    Dati generali </a> 
                                                </li>
                                                 <li> 
                                                    <a href="#datiContestoTab" onclick="selectTabAccessi('datiContesto')" data-toggle="tab">
                                                    Dati Contesto </a> 
                                                </li>
                                                <li> <!-- TAB 3 -->
                                                    <a href="#praticheAccessiTab" onclick="selectTabAccessi('pratiche')" data-toggle="tab">
                                                    Pratiche </a>
                                                </li>
                                                <!-- li> 
                                                    <a id="elio" href="#portlet_tab4" data-toggle="tab">
                                                    Questionario </a>
                                                </li-->
                                                <li> <!-- TAB 5 -->
                                                    <a href="#tabNonConfAccess"  onclick="selectTabAccessi('nonConformita')" data-toggle="tab">
                                                    Non Conformità </a>
                                                </li>
                                                <li> <!-- TAB 6 -->
                                                    <a href="#tabRiskAccess" onclick="selectTabAccessi('rischi')" data-toggle="tab">
                                                    Rischi </a>
                                                </li>
                                                <li class="dropdown dropdown-report">
											      <a class="dropdown-toggle" data-toggle="dropdown">Report <span class="caret"></span></a>
											      <ul class="dropdown-menu dropdown-report">
											        <li><a id="tabReportAccessi" onclick="selectReportSubTab(this.id);" >Report</a></li>
											        <li><a id="tabAllegatoReportAccessi" onclick="selectReportSubTab(this.id);" >Allegato report</a></li>
											         <li><a id="pm" onclick="selectReportSubTab('this.id'); loadDiv('/CruscottoAuditAtpoWebWeb/getTabReportPianoMiglioramentoWord', 'pianoMiglioramento', null, null);" >Piano miglioramento</a></li>
											        <li><a id="tab7d" onclick="selectReportSubTab(this.id);" >ERM</a></li>                        
											      </ul>
											    </li>
											    <li>
											    <a href="#tabNota" onclick="selectTabAccessi('nota')" data-toggle="tab">
                                                    Note </a>
											    </li>
											     <li>
											    <a href="#tabMediaTempi" onclick="selectTabAccessi('mediaTempi')" data-toggle="tab">
                                                    Media tempi </a>
											    </li>
                                            </ul>
                                        </div>
                                        
                                        
                                        <div class="portlet-body">
                                            <div class="tab-content">
                                            
                   <!-- ***************************************** TAB 1 (DATI GENERALI) ******************************************-->

                                                <div class="tab-pane active" id="datiGenerali">
                                                
                                                	<div class="portlet">
					                                    <div class="portlet-body">
					
						 									 <div class="row" style="margin-bottom: 30px;">
						                                         
						                                         	<div class="col-md-3 col-xs-3" style="margin-bottom: 15px;">
						                                                <div class="form-group form-md-line-input">
						                                                   <s:set var="datainizio" value="%{ssessione.dataInizio}" />
						                                                   <input type="text" class="form-control" readonly id="form_control_1" value="<s:date name="datainizio" format="dd/MM/yyyy" />" style="height: 38px;"> 
						                                                      <label for="form_control_1">Data inizio</label>
						                                                </div>
						                                         	</div>
						                                         	
						                                         	<div class="col-md-3 col-xs-3" style="margin-bottom: 15px;">
						                                                <div class="form-group form-md-line-input">
						                                                   <s:set var="datafine" value="%{ssessione.dataFine}" />
						                                                   <input type="text" class="form-control" readonly id="form_control_1" value="<s:date name="datafine" format="dd/MM/yyyy" />" style="height: 38px;"> 
						                                                      <label for="form_control_1">Data fine</label>
						                                                </div>
						                                         	</div>
						                                         	
						                                         	<div class="col-md-3 col-xs-2" style="margin-bottom: 15px;">
							                                         	<s:set var="statoEsameSessione" value="%{ssessione.statoEsameSessione}" />
							                                         	
							                                         	<s:if test='%{#statoEsameSessione == @it.tecnet.crs.util.SessioneUtil@ESAME_SESSIONE_APERTO}'>
					  												        	<s:set var="controlstatoEsameSessioneDisabled" value="%{'disabled=\"\"'}" />
					  												    </s:if>
					  												    
					  												    <s:if test='%{#statoEsameSessione == @it.tecnet.crs.util.SessioneUtil@ESAME_SESSIONE_CHIUSO}'>
					  												        	<s:set var="sessioneChiusa" value="%{'disabled'}" />
					  												    </s:if>
					  												    
							                                         	
							                                   			<label for="selectStato"  style="top: 0; font-size: 13px; color: #888888; opacity: 1;">Stato sessione</label>
							                                   				<select class="form-control input-medium select2me" data-placeholder="<s:property value="statoEsameSessione" />" 
							                                   				<s:property value="controlstatoEsameSessioneDisabled" />  id="selectStato" onchange="riapriSessione(this)">
							                              							<s:if test='%{#statoEsameSessione == @it.tecnet.crs.util.SessioneUtil@ESAME_SESSIONE_APERTO}'>
																						<option id="statoEsameSessione" selected value="<s:property value="@it.tecnet.crs.util.SessioneUtil@ESAME_SESSIONE_APERTO" />">Aperta</option>
										  											 	<option id="statoEsameSessione" value="<s:property value="@it.tecnet.crs.util.SessioneUtil@ESAME_SESSIONE_CHIUSO" />">Chiusa</option>
																					</s:if>
							                              			                <s:if test='%{#statoEsameSessione == @it.tecnet.crs.util.SessioneUtil@ESAME_SESSIONE_CHIUSO}'>
																						<option id="statoEsameSessione"  value="<s:property value="@it.tecnet.crs.util.SessioneUtil@ESAME_SESSIONE_APERTO" />">Aperta</option>
										  											 	<option id="statoEsameSessione" selected value="<s:property value="@it.tecnet.crs.util.SessioneUtil@ESAME_SESSIONE_CHIUSO" />">Chiusa</option>
																					</s:if>
						                       	 							</select>
						                                         	</div>
						                                         	
						                                         	<div class="col-md-3 col-xs-3" style="margin-bottom: 15px;">
						                                         		<div class="col-md-2 col-xs-3" style="margin-bottom: 15px;">
							                                         		<div class="form-group form-md-line-input">
																			 	<button id="salvaSatoEsameSessione" type="button" class="btn blue"   <s:property value="sessioneChiusa" />
																			 	onclick="salvaSatoEsameSessione()">Calcola Indicatori Sessione</button>
							                                             	</div>
							                                         	</div>
					                                       			</div>
					                                       			
					                                       	 </div>
					
					                                         <div class="row" style="margin-bottom: 30px;">
					                                          
					                                            <div class="col-md-4 col-xs-12" style="margin-bottom: 15px;">
					                                                <div class="form-group form-md-line-input">
					                                                   <s:set var="numeroPratiche" value="%{ssessione.numeroPratiche}" />
					                                                   <input type="text" class="form-control" readonly id="form_control_1" value="<s:property value="numeroPratiche" />" style="height: 38px;"> 
					                                                      <label for="form_control_1">Numero pratiche campione</label>
					                                                </div>
					                                         	</div> 
					                                         	
					                                             <div class="col-md-4 col-xs-12" style="margin-bottom: 15px;">
					                                                <div class="form-group form-md-line-input">
					                                                   <s:set var="numPEs" value="%{ssessione.numeroPraticheEsaminate}" />
					                                                   <input type="text" class="form-control" readonly id="form_control_1" value="<s:property value="numPEs" />" style="height: 38px;"> 
					                                                      <label for="form_control_1">Numero pratiche esaminate</label>
					                                                </div>
					                                             </div>
					                                             
					                                         
					                                             
					                                         </div>
					                                          
					                                         <div class="row" style="margin-bottom: 30px;">
					                                          
						                                          <div class="col-md-4 col-xs-12" style="margin-bottom: 15px;">
						                                                <div class="form-group form-md-line-input">
						                                                   <s:set var="min" value="%{ssessione.minimo}" />
						                                                   <input  type="text" class="form-control" readonly id="form_control_1"  value="<s:property value="min" />" style="height: 38px;">
						                                                   <label for="form_control_1">INCC minimo</label>
						                                                </div>
						                                             </div>
						                                          
						                                          <div class="col-md-4 col-xs-12" style="margin-bottom: 15px;">
						                                                <div class="form-group form-md-line-input">
						                                                   <s:set var="max" value="%{ssessione.massimo}" />
						                                                   <input  type="text" class="form-control" readonly id="form_control_1"  value="<s:property value="max" />" style="height: 38px;">
						                                                   <label for="form_control_1">INCC massimo</label>
						                                                </div>
						                                             </div>
						                                          
						                                          
						                                             <div class="col-md-4 col-xs-12" style="margin-bottom: 15px;">
						                                                <div class="form-group form-md-line-input">
						                                                   <s:set var="media" value="%{ssessione.media}" />
						                                                   <input  type="text" class="form-control" readonly id="form_control_1"  value="<s:property value="media" />" style="height: 38px;">
						                                                   <label for="form_control_1">INCC medio</label>
						                                                </div>
						                                             </div>
						                                             
						                                              
						                                     </div>
					                                          
					                                         <div class="row" style="margin-bottom: 30px;">
					                                         <!-- 
					                                           <div class="col-md-4 col-xs-12" style="margin-bottom: 15px;">
					                                                <div class="form-group form-md-line-input">
					                                                   <s:set var="devStand" value="%{ssessione.devStandard}" />
					                                                   <input type="text" class="form-control" readonly id="form_control_1" value="<s:property value="devStand" />" style="height: 38px;">
					                                                   <label for="form_control_1">Dev. Standard</label>
					                                                </div>
					                                             </div>
					                                           -->
					                                          
					                                          	<div class="col-md-4 col-xs-12" style="margin-bottom: 15px;">
					                                                <div class="form-group form-md-line-input">
					                                                   <s:set var="incc" value="%{ssessione.INCC}" />
					                                                   <input type="text" class="form-control" readonly id="form_control_1" value="<s:property value="incc" />" style="height: 38px;">
					                                                   <label for="form_control_1">INCC sessione</label>
					                                                </div>
					                                             </div>
					                                          
					                                          
					                                             <div class="col-md-4 col-xs-12" style="margin-bottom: 15px;">
					                                                <div class="form-group form-md-line-input">
					                                                   <s:set var="inccDesc" value="%{ssessione.INCCDescrizione}" />
					                                                   <input type="text" class="form-control" readonly id="form_control_1" value="<s:property value="inccDesc" />" style="height: 38px;">
					                                                   <label for="form_control_1">Descrizione INCC sessione</label>
					                                                </div>
					                                             </div>
					                                             
					                                            <div class="col-md-4 col-xs-3" style="margin-bottom: 15px;">
																      <div class="form-group form-md-line-input">
																         <s:set var="dataAggiDatiSess" value="%{ssessione.dataAggiornamentoDatiSessione}" />
																         <input type="text" class="form-control" readonly id="form_control_1" value="<s:date name="dataAggiDatiSess" format="dd/MM/yyyy" />" style="height: 38px;"> 
																            <label for="form_control_1">Data aggiornamento dati sessione</label>
																      </div>
																</div>
					                                             
					                                             
					                                         </div>
					                                         

					                                         
					                                         <div class="row" style="margin-bottom: 30px;">
					                                         
					                                         
						                                         <div class="col-md-4 col-xs-3" style="margin-bottom: 15px;">
						                                                <div class="form-group form-md-line-input">
						                                                   <s:set var="numDissAmm" value="%{ssessione.numDissAmm}" />
						                                                   <input  type="text" class="form-control" readonly id="numDissAmm"  value="<s:property value="numDissAmm" />" style="height: 38px;">
						                                                   <label for="numDissAmm">Numero dissensi amministrativi</label>
						                                                </div>
						                                         </div>
						                                         
						                                         <div class="col-md-4 col-xs-3" style="margin-bottom: 15px;">
						                                                <div class="form-group form-md-line-input">
						                                                   <s:set var="numDissSan" value="%{ssessione.numDissSan}" />
						                                                   <input  type="text" class="form-control" readonly id="numDissSan"  value="<s:property value="numDissSan" />" style="height: 38px;">
						                                                   <label for="numDissSan">Numero dissensi sanitari</label>
						                                                </div>
						                                         </div>
						                                         
						                                    </div>
						                                    
						                                    <div class="row" style="margin-bottom: 30px;"> 
						                                          
						                                         <div class="col-md-4 col-xs-3" style="margin-bottom: 15px;">
						                                                <div class="form-group form-md-line-input">
						                                                   <s:set var="percnumDissAmm" value="%{ssessione.percnumDissAmm}" />
						                                                   <input  type="text" class="form-control" readonly id="percnumDissAmm"  value="<s:property value="percnumDissAmm" />" style="margin: 3;height: 38px;">
						                                                   <label for="percnumDissAmm">% numero dissensi amministrativi su totale dissensi (amministrativi + sanitari)</label>
						                                                </div>
						                                         </div>
						                                         
						                                         <div class="col-xs-4 col-sm-3" style="margin-bottom: 30px;">
						                                                <div class="form-group form-md-line-input">
						                                                	<s:set var="percnumDissSan" value="%{ssessione.percnumDissSan}" />
						                                                   	<input  type="text" class="form-control" readonly id="percnumDissSan"  value="<s:property value="percnumDissSan" />" style="margin: 3;height: 40px;">
						                                                	<label for="email_id" class="control-label">% numero dissensi sanitari su totale dissensi (amministrativi + sanitari)</label>
						                                                </div>
						                                         </div>
						                                         
					                                         </div>
					                                         
					                                    </div>
                                                
                                                  </div>
                                              </div>
    				<!-- **************************************** TAB 2 (DATI CONTESTO) ***************************************** -->
    				
												  <div class="tab-pane" id="datiContestoTab">
                                                    <!-- CONTENUTO TAB  -->
                                                  </div>
                                                
                  <!-- ******************************************* TAB 3 (PRATICHE) ********************************************* -->
                  
                                                <div class="tab-pane" id="praticheAccessiTab">
                                                    
                                                </div>
                                                
	                    <!--*************************************** TAB 4 (QUESTIONARIO)****************************************-->
	                    
	                                          	<div class="tab-pane" id="portlet_tab4">
	                                                    <!-- CONTENUTO TAB  -->
	                                            </div>
	   	 				<!--*************************************** TAB 5 (NON CONFORMITA')****************************************-->
	   	 				
	                                            <div class="tab-pane" id="tabNonConfAccess">
	                                                    <!-- CONTENUTO TAB  -->
	                                            </div>
	     				<!--*************************************** TAB 6 (RISCHI)****************************************-->     
	     				                                       
	                                            <div class="tab-pane" id="tabRiskAccess">
	                                                    <!-- CONTENUTO TAB  -->
	                                            </div>  
	                                                
	                    <!--*************************************** TAB 7 (REPORT)****************************************-->                                            
	                                            <div class="tab-pane" id="portlet_tabReportAccessi">
	                                                    <!-- CONTENUTO TAB  REPORT -->
	                                                    
	                                                    <div class="form-group form-md-line-input">
															<button  type="button" class="btn blue"  onclick="generaReportAccessi()">Genera Report Accesso</button>
			                                            </div>
	                                                    
	                                            </div>
	                                            
	                    <!--*************************************** TAB 8 (ALLEGATO REPORT)****************************************-->                                            
	                                            <div class="tab-pane" id="portlet_tabAllegatoReportAccessi">
	                                                    <!-- CONTENUTO TAB  REPORT -->
	                                                    
	                                                    <div class="form-group form-md-line-input">
															<button  type="button" class="btn blue"  onclick="generaAllegatoReportAccessi()">Genera Allegato Report Accesso</button>
			                                            </div>
	                                                    
	                                            </div>
	                                            
	                                            
	                                            
	                                            <div class="tab-pane" id="pianoMiglioramento">
	                                                  
	                                            </div>
	                                            <div class="tab-pane" id="portlet_tab7d">
	                                                    <!-- CONTENUTO TAB ERM -->
	                                                    <div class="form-group form-md-line-input">
															<button  type="button" class="btn blue"  onclick="generaReportErm()">Genera ERM</button>
			                                            </div>
	                                                    
	                                            </div> 
	                                            <div class="tab-pane" id="tabNota">
	                                                    
	                                                    
	                                            </div> 
                                                <div class="tab-pane" id="tabMediaTempi">
	                                                    
	                                                    
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
            
	