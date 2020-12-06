<%@ taglib prefix="s" uri="/struts-tags" %>
<div class="portlet">
   <div class="portlet-body">
      <div class="row" style="margin-bottom: 40px;">
         <div class="col-lg-12">
          <s:set var="desc" value="%{r.descrizione}" />
            <div style="margin: 5px 0 15px; color: #697882; font-size: 20px; font-weight: 400;">Rischi - <s:property value="desc" /></div>
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
                                <table class="table table-striped table-bordered table-advance table-hover" id="statoEsprRischio">
                                    <thead>
                                        <tr>
                                            <th>
                                            </th>
                                            <th>
                                               Espressione di rischio
                                            </th>
                                            <th>
                                                Num
                                            </th>
                                            <th>
                                                % su totale 
                                            </th>
                                            <th>
                                               % su pratiche soggette 
                                            </th>
                                            <th>
                                               Totale importo
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
               <a href="#" onclick="loadDiv('/CruscottoAuditAtpoWebWeb/getTabRischiAccessi', 'tabRiskAccess', null, 'initRischiAccessi');" data-toggle="tab" class="btn btn-default btn-sm">  Indietro </a>
            </div>
         </div>
      </div>
         
     
   </div>
</div>