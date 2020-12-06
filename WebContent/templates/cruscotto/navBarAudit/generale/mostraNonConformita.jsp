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
			<!-- BEGIN PAGE HEAD -->
			<div class="page-head">
				<!-- BEGIN PAGE TITLE -->
				<div class="page-title">
                    <!-- <h1>ACL Engine <small>gestione autorizzazioni applicative</small></h1> -->
					<h1>Non Conformità <!-- <small>- Modifica Generale</small> --></h1>
				</div>
                <!-- BEGIN PAGE BREADCRUMB -->
                <ul class="page-breadcrumb breadcrumb">
                    <li>
                        Audit
                        <i class="fa fa-circle"></i>
                    </li>
                    <li>
                    <a href="#" onclick="loadDiv('/CruscottoAuditAtpoWebWeb/auAudit', 'appView', null, 'initAuditGenerale' );">
                        Generale</a>
					   <i class="fa fa-circle"></i>
                    </li>
                    <li>
                    <!-- generale.js -->
                     <a href="#" onclick="loadDiv('/CruscottoAuditAtpoWebWeb/modificaAuditGenerale?idAudit=<%=user.getIdAudit()%>', 'appView', null, 'initAuditGeneraleModificaTables' );">
                        Modifica Generale </a>
                         <i class="fa fa-circle"></i> </li>
                   
                     <li> Non Conformità 
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
                            <!--<div class="table-scrollable">-->
                            <table class="table table-striped table-bordered table-advance table-hover" id="non_conformita_table">
                        		<thead>
                           			<tr>
                             			<th style="display:none"> </th>
                             			<th>Non Conformità</th>
                                		<th>Variante Comportamentale</th>
                               			<th>Rischio</th>
                               			<th>Rischio Economico</th>
                            		</tr>
                        		</thead>
                        
                        		<tbody>
                        		</tbody>
              				</table>
                    
                         
                        </div>
                    </div>
                    <!-- END SAMPLE TABLE PORTLET-->

				</div>
			</div>
			
<!-- END CONTAINER -->
<!-- BEGIN FOOTER -->
