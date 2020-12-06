<%@ taglib prefix="s" uri="/struts-tags" %>
	<!-- BEGIN CONTENT -->
			
			<!-- BEGIN PAGE CONTENT INNER -->
			<div class="row margin-top-10">
				<div class="col-md-12">
                    
                    <!-- BEGIN SAMPLE TABLE PORTLET-->
                    <div class="portlet">
				       
                        <div class="portlet-body">
                                <table class="table table-striped table-bordered table-advance table-hover" id="normativa_table">
                                    <thead>
                                    	<th style="text-align: center;">
                                         </th>	
                                        	<s:iterator value="labelList" var="header">
	   											<th><s:property value="#header.label1" /></th>
	   											<th><s:property value="#header.label2" /></th>
	   											<th><s:property value="#header.label3" /></th>
	   											<th><s:property value="#header.label4" /></th>
	   											<th><s:property value="#header.label5" /></th>
	   											<th><s:property value="#header.label6" /></th>
	   											<th><s:property value="#header.label7" /></th>
	   											<th><s:property value="#header.label8" /></th>
	   											<th><s:property value="#header.label9" /></th>
	   											<th><s:property value="#header.label10" /></th>
	   											<th><s:property value="#header.label11" /></th>
	   											<th><s:property value="#header.label12" /></th>
	   											<th><s:property value="#header.label13" /></th>
	   											<th><s:property value="#header.label14" /></th>
	   											<th><s:property value="#header.label15" /></th>
	   											<th><s:property value="#header.label16" /></th>
	   											<th><s:property value="#header.label17" /></th>
	   											<th><s:property value="#header.label18" /></th>
	   											<th><s:property value="#header.label19" /></th>
	   											<th><s:property value="#header.label20" /></th>
	   											<th><s:property value="#header.label21" /></th>
	   											<th><s:property value="#header.label22" /></th>
                          		 			</s:iterator>
                                                                    
                                    	</thead>
                                    <tbody>
                                    </tbody>
                                </table>
                        </div>
                    </div>
                    <!-- END SAMPLE TABLE PORTLET-->

				</div>
			</div>

            
			<!-- END PAGE CONTENT INNER -->
	<!-- END CONTENT -->
	


<script>
	jQuery(document).ready(function() {
		ComponentsPickers.init();
    });
</script>
