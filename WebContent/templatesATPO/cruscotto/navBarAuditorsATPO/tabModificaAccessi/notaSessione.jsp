<%@ taglib prefix="s" uri="/struts-tags" %>
<!-- BEGIN SAMPLE TABLE PORTLET-->
<div class="portlet">
   <div class="portlet-body">
       
      		<div class="row">
           		<div class="col-md-12" style="margin-top: 5px;">
               
                	<div class="actions add-action">
                    <div class="form-group">
                      <div class="col-md-12 col-xs-12" style="margin-bottom: 15px;">
             				<s:set var="nota" value="%{notaSessione.trim()}" />
               				
               
                  		<textarea  rows="8" cols="200" id="notaSessione" class="form-control"><s:property value="nota" /></textarea>
            		</div>          
                                  
                       	 		
                    </div>
                </div>
             </div>
             </div>
             <div class="row">
             	<div class="col-md-6" style="margin-top: 5px;">
                	<div class="actions add-action">
                		<div class="form-group">
                			<div class="col-md-12 col-xs-12" style="margin-bottom: 15px;">
                   		<a data-toggle="modal" href="#" onclick="salvaNota()" class="btn blue-madison">
                            <i class="fa fa-pencil"></i> Salva </a>
                     </div>
                     </div>
                     </div>
                     <div class="actions add-action">
                		<div class="form-group">
                			<div class="col-md-12 col-xs-12" style="margin-bottom: 15px;">
                   		<a data-toggle="modal" href="#" onclick="showConfirmDel()" class="btn btn-default">
                            <i class="fa fa-pencil"></i> Elimina </a>
                     </div>
                     </div>
                     </div>
               </div>
              
            </div>
        </div>
       
    </div>
<!-- MODALE CONFERMA ELIMINA AREA -->
  	<div class="modal fade" tabindex="-1" id="deleteNotaSess" aria-hidden="true">
	  <div class="modal-dialog"  style="width: 500px !important; height: 200px !important;">
	    <div class="modal-content">
	      <div class="modal-header">
	        <div class="caption font-green">
                   <span class="caption-subject bold uppercase">NOTA</span>
               </div>
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
	          <span aria-hidden="true">&times;</span>
	        </button>
	      </div>
	      <div class="modal-body">
	        <p>Sei sicuro di voler eliminare la nota ?</p>
	      </div>
	      <div class="modal-footer">
	        <button type="button" data-dismiss="modal" class="btn default">No</button>
	        <button type="button" class="btn blue" onclick="eliminaNotaSessione();">Si</button>
	      </div>
	    </div>
	  </div>
	</div>
	