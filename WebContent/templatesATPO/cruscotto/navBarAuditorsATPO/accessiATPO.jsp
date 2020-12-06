<%@ taglib prefix="s" uri="/struts-tags" %>

			<!-- BEGIN PAGE HEAD -->
			<div class="page-head">
				<!-- BEGIN PAGE TITLE -->
				<div class="page-title">
                    <!-- <h1>ACL Engine <small>gestione autorizzazioni applicative</small></h1> -->
					<h1>Accessi  <!-- <small>- Processo</small> --></h1>
				</div>
                <!-- BEGIN PAGE BREADCRUMB -->
                <ul class="page-breadcrumb breadcrumb">
                    <li>
                        Auditors ATPO
                        <i class="fa fa-circle"></i>
                    </li>
                    <li>
                        Accessi
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
				        <div class="row">
                            <div class="col-lg-6 col-lg-offset-4 col-xs-12">
                                <div class="actions add-action">
                                    <a href="modifica-accessi-atpo.html" class="btn btn-default btn-sm">
                                    <i class="fa fa-pencil"></i> Modifica </a>
                                </div>
                                <div class="actions add-action">
                                    <a href="javascript:;" class="btn btn-default btn-sm">
                                    <i class="fa fa-refresh"></i> Aggiorna </a>
                                </div>
                            </div>
                        </div>
                        <div class="portlet-body">
                            <!--<div class="table-scrollable">-->
                                <table class="table table-striped table-bordered table-advance table-hover" id="accessiATPO_table">
                                    <thead>
                                        <tr>
                                            <th style="text-align: center;">
                                                <div class="form-group form-md-checkboxes">
                                                    <div class="md-checkbox-inline">
                                                        <div class="md-checkbox">
                                                            <input type="checkbox" id="checkbox0" class="md-check">
                                                            <label for="checkbox0">
                                                                <span></span>
                                                                <span class="check"></span>
                                                                <span class="box"></span>
                                                            </label>
                                                        </div>
                                                    </div>
                                                </div>
                                            </th>
                                            <th>
                                                Campagna
                                            </th>
                                            <th>
                                                Audit
                                            </th>
                                            <th>
                                                Sede
                                            </th>
                                            <th>
                                                Data inizio
                                            </th>
                                            <th>
                                                Data fine
                                            </th>
                                            <th>
                                                Stato
                                            </th>
                                            <th>
                                                Dirigente
                                            </th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        
                                    </tbody>
                                </table>
                            <!--</div>-->
                        </div>
                    </div>
                    <!-- END SAMPLE TABLE PORTLET-->

				</div>
			</div>
			
            <!-- MODAL -->
            <div class="modal-custom">
            </div>
            
		