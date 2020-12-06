<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page import="it.tecnet.crs.web.beans.AppUser;"%>
<%
   AppUser user= (AppUser)request.getSession().getAttribute("AppUser");
   		
   %> 
<!-- BEGIN CONTENT -->
<!-- BEGIN PAGE CONTENT INNER -->
<div class="row margin-top-10">
	<div class="col-md-12">

                 <!-- BEGIN PROCESS TABLE PORTLET-->
                 <div class="portlet">
	        		
                     <div class="portlet-body">
                         <!--<div class="table-scrollable">-->
                             <table class="table table-striped table-bordered table-advance table-hover" id="areaprocesso_table">
                                 <thead>
                                     <tr>
                                         <th style="text-align: center;">
                                         </th>
                                         <th>
                                             Ordinamento
                                         </th>
                                         <th>
                                             Descrizione
                                         </th>
                                         <th>
                                             Area
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
                         <!--</div>-->
                     </div>
                 </div>
                 <!-- END PROCESS TABLE PORTLET-->

	</div>
</div>

	<!-- END CONTENT -->
	
<script>
	jQuery(document).ready(function() {
		ComponentsPickers.init();
    });
</script>
