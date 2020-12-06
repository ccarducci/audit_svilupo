<%@ taglib prefix="s" uri="/struts-tags" %>
<!-- BEGIN SAMPLE TABLE PORTLET-->
<div class="portlet">
   <div class="portlet-body">
       <div class="row">
           <div class="col-lg-4">
               <div style="margin: 5px 0 15px; color: #697882; font-size: 20px; font-weight: 400;">Pratiche</div>
           </div>
           <div class="col-lg-4" style="margin-top: 5px;">
               <div class="actions add-action">
                   <a data-toggle="modal" href="#" onclick="getCheckBoxID('modificaAccessoATPO')" class="btn btn-default btn-sm">
                            <i class="fa fa-pencil"></i> Modifica </a>
               </div>
               <div class="actions add-action">
                   <a href="#" onclick="reloadTable('praticheATPO')" class="btn btn-default btn-sm">
                    <i class="fa fa-refresh"></i> Aggiorna </a>
                </div>
            </div>
        </div>
        <table class="table table-striped table-bordered table-advance table-hover" id="praticheATPO">
            <thead>
                <tr>
                    <th style="width: 80px; text-align: center;">
                        
                    </th>
                    <th>
                       Cod Sede
                    </th>
                    <th>
                        Sede
                    </th>
                    <th>
                        Fascicolo
                    </th>
                    <th>
                        Parte
                    </th>
                   
                </tr>
            </thead>
            <tbody>
                
            </tbody>
        </table>
    </div>
</div>