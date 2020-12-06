<%@ taglib prefix="s" uri="/struts-tags" %>
<!-- BEGIN SAMPLE TABLE PORTLET-->
<div class="portlet">
   <div class="portlet-body">
       <div class="row">
           <div class="col-lg-3">
               <div style="margin: 5px 0 15px; color: #697882; font-size: 20px; font-weight: 400;">Pratiche</div>
           </div>
      </div>
      <div class="row">
           <div class="col-md-12" style="margin-top: 5px;">
               
                <div class="actions add-action">
                    <div class="form-group">
                                
                                   <select class="form-control input-medium select2me" id="selectEsitoPratica" onchange="filtraPratiche();">
                           				<option value="" disabled selected>Filtra esito</option>
                           					<option  value="1">
                              						Dissenso
                              				</option>
                              				<option  value="2">
                              						Favorevole all'istituto
                              				</option>
                              				<option value="3">
                              						Sfavorevole all'istituto
                              				</option>
                              				<option  value="4">
                              						Parzialmente favorevole
                              				</option>
                              				<option  value="0">
                              						Tutte
                              				</option>
                          		 			
                       	 			</select>
                       	 		
                                </div>
                </div>
                <div class="actions add-action">
                    <div class="form-group">
                                
                                   <select class="form-control input-medium select2me" id="selectStatoPratica" onchange="filtraPratiche();">
                           				<option value="" disabled selected>Filtra stato</option>
                           					<option  value="1">
                              						Chiuso
                              				</option>
                              				<option  value="2">
                              						Aperto
                              				</option>
                              				<option value="3">
                              						Lavorazione
                              				</option>
                              				<option  value="0">
                              						Tutti
                              				</option>
                          		 			
                       	 			</select>
                       	 		
                                </div>
                </div>
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
                        Ricorrente
                    </th>
                    <th>
                        Stato
                    </th>
                    <th>
                        Esito
                    </th>
                   
                </tr>
            </thead>
            <tbody>
                
            </tbody>
        </table>
    </div>
</div>