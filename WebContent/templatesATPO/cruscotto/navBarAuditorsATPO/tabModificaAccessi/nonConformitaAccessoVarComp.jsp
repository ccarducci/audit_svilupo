<%@ taglib prefix="s" uri="/struts-tags" %>
<div class="portlet">
   <div class="portlet-body">
      <div class="row" style="margin-bottom: 40px;">
         <div class="col-lg-12">
         <s:set var="desc" value="%{nc.descrizione}" />
            <div style="margin: 5px 0 15px; color: #697882; font-size: 20px; font-weight: 400;">Non Conformità - <s:property value="desc" /></div>
         </div>
          <div class="col-lg-4">
         
         </div>
      </div>
      
      <div class="row">
         
      </div>
      
      
      <div class="row" style="margin-bottom: 40px;">
            
      </div>  
          
          
      <div class="row" style="margin-bottom: 40px;">
     
      <div class="col-lg-12">
         <div class="portlet-body">
                            <!--<div class="table-scrollable">-->
                                <table class="table table-striped table-bordered table-advance table-hover" id="nonConfAccVarComp">
                                    <thead>
                                        <tr>
                                            <th>
                                            </th>
                                            <th>
                                                Variante comportamentale
                                            </th>
                                            <th>
                                                Num
                                            </th>
                                            <th>
                                               % su pratiche soggette 
                                            </th>
                                            <th>
                                                % pesata 
                                            </th>
                                            <th>
                                               Colore
                                            </th>
                                            
                                        </tr>
                                    </thead>
                                    <tbody>
                                        
                                    </tbody>
                                </table>
                            <!--</div>-->
                        </div>
      </div>
            
      </div> 
          
         	
         	<div class="row" style="margin-bottom: 40px;">
         
          <div class="col-lg-4">
         <div class="actions add-action">
               <!-- accessi.js -->
               <a href="#" onclick="nonConformitaTable()" class="btn btn-default btn-sm">  Indietro </a>
            </div>
         </div>
      </div>
         
     
   </div>
</div>