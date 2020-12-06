<%@ taglib prefix="s" uri="/struts-tags" %>
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
                        Auditors
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
                                                <!-- TAB 1 -->
                                                <!-- cruscottoScript.js -->
                                                    <a href="#portlet_tab1" onclick="reloadTabMenu('datiGenerali')" oc data-toggle="tab">
                                                    Dati generali </a>
                                                </li>
                                                 <li> <!-- TAB 2 -->
                                                    <a href="#portlet_tab2" data-toggle="tab">
                                                    Dati Contesto </a>
                                                </li>
                                                <li> <!-- TAB 3 -->
                                                    <a href="#portlet_tab3" data-toggle="tab">
                                                    Pratiche </a>
                                               </li>
                                                <li> <!-- TAB 4 -->
                                                    <a id="elio" href="#portlet_tab4" data-toggle="tab">
                                                    Questionario </a>
                                                </li>
                                                <li> <!-- TAB 5 -->
                                                    <a href="#tabNonConfAccess"  onclick="loadDiv('/CruscottoAuditAtpoWebWeb/getTabNonConfAccessi', 'tabNonConfAccess', null, 'initNonConformitaAccessi');" data-toggle="tab">
                                                    Non Conformità </a>
                                                </li>
                                                <li> <!-- TAB 6 -->
                                                    <a href="#tabRiskAccess" onclick="loadDiv('/CruscottoAuditAtpoWebWeb/getTabRischiAccessi', 'tabRiskAccess', null, 'initRischiAccessi');" data-toggle="tab">
                                                    Rischi </a>
                                                </li>
                                                <li class="dropdown dropdown-report">
											      <a class="dropdown-toggle" data-toggle="dropdown">Report <span class="caret"></span></a>
											      <ul class="dropdown-menu dropdown-report">
											        <li><a id="tab7a" onclick="selectReportSubTab(this.id);" >Report</a></li>
											        <li><a id="tab7b" onclick="selectReportSubTab(this.id);" >Allegato report</a></li>
											        <li><a id="tab7c" onclick="selectReportSubTab(this.id);" >Piano miglioramento</a></li>
											        <li><a id="tab7d" onclick="selectReportSubTab(this.id);" >ERM</a></li>                        
											      </ul>
											    </li>
                                                <li> <!-- TAB 8 -->
                                                    <a href="#portlet_tab6" data-toggle="tab">
                                                    Note </a>
                                                </li>
                                            </ul>
                                        </div>
                                        <div class="portlet-body">
                                            <div class="tab-content">
                                            	
	<!-- ***************************************** TAB 1 (DATI GENERALI) ******************************************-->

                                                <div class="tab-pane active" id="portlet_tab1">
                                                    <!-- CONTENUTO TAB  -->
                                                  </div>
    <!-- **************************************** TAB 2(DATI CONTESTO) ***************************************** -->
												  <div class="tab-pane" id="portlet_tab2">
                                                    <!-- CONTENUTO TAB  -->
                                                  </div>
                                                    
                                                
	<!-- *************************************** TAB 3 (PRATICHE)************************************************************* -->
                                                
                                              <!-- BEGIN SAMPLE TABLE PORTLET-->
                                                <div class="tab-pane" id="portlet_tab3">
                                                    <div class="portlet">
                                                        <div class="portlet-body">
                                                           
                                                            <div class="row">
                                                                <div class="col-lg-4">
                                                                    <div style="margin: 5px 0 15px; color: #697882; font-size: 20px; font-weight: 400;">Pratiche</div>
                                                                </div>
                                                                <div class="col-lg-4" style="margin-top: 5px;">
                                                                    <div class="actions add-action">
                                                                    <!-- cruscottoScript.js -->
                                                                     <a href="#" onclick="getCheckBoxID('pratica')"
                                     								 class="btn btn-default btn-sm">
                                                                        <i class="fa fa-pencil"></i> Modifica </a>
                                                                    </div>
                                                                    <div class="actions add-action">
                                                                    <!-- cruscottoScript.js -->
                                                                        <a href="#" onclick="reloadTable('pratiche')" class="btn btn-default btn-sm">
                                                                        <i class="fa fa-refresh"></i> Aggiorna </a>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                            
                                                            
                                                            <table class="table table-striped table-bordered table-advance table-hover" id="pratiche">
                                                                <thead>
                                                                    <tr>
                                                                        <th style="width: 80px; text-align: center;">
                                                                            <div class="form-group form-md-checkboxes">
                                                                                <div class="md-checkbox-inline">
                                                                                    <div class="md-checkbox">
                                                                                        
                                                                                    </div>
                                                                                </div>
                                                                            </div>
                                                                        </th>
                                                                        <th>
                                                                            Protocollo
                                                                        </th>
                                                                        <th>
                                                                            Azienda
                                                                        </th>
                                                                        <th>
                                                                            Partita IVA
                                                                        </th>
                                                                        <th>
                                                                            Data Ispezione
                                                                        </th>
                                                                        <th>
                                                                            Data Notifica
                                                                        </th>
                                                                        <th>
                                                                            Tipo Notifica
                                                                        </th>
                                                                        <th>
                                                                            Importo accertato
                                                                        </th>
                                                                    </tr>
                                                                </thead>
                                                                <tbody>
                                                                <!-- pratiche.js -->
                                                                </tbody>
                                                            </table>
                                                        </div>
                                                    </div>
                                                </div>
                                                    <!-- END SAMPLE TABLE PORTLET-->

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
                                            <div class="tab-pane" id="portlet_tab7a">
                                                    <!-- CONTENUTO TAB  -->
                                                    REPORT
                                            </div>
                                            <div class="tab-pane" id="portlet_tab7b">
                                                    <!-- CONTENUTO TAB  -->
                                                    ALLEGATO REPORT
                                            </div>
                                            <div class="tab-pane" id="portlet_tab7c">
                                                    <!-- CONTENUTO TAB  -->
                                                    PIANO MIGLIORAMENTI
                                            </div>
                                            <div class="tab-pane" id="portlet_tab7d">
                                                    <!-- CONTENUTO TAB  -->
                                                    ERM
                                            </div>
                                            
     <!--*************************************** TAB 7 (MULTISELEZIONE)****************************************-->                                            
                                            <div class="tab-pane" id="portlet_tab8">
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


