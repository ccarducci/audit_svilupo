<%@ taglib prefix="s" uri="/struts-tags" %>
<div class="portlet">
   <div class="portlet-body">
      <div class="row" style="margin-bottom: 40px;">
         <div class="col-lg-4">
            <div style="margin: 5px 0 15px; color: #697882; font-size: 20px; font-weight: 400;">Rischio - Dettagli </div>
         </div>
          <div class="col-lg-4">
         
         </div>
      </div>
      
      <div class="row">
         
      </div>
      
      
      <div class="row" style="margin-bottom: 40px;">
            
           
            <div class="col-md-6 col-xs-12" style="margin-bottom: 15px;">
             <s:set var="desc" value="%{r.descrizione}" />
               <span class="help-block" style="margin-top: 0;"> Descrizione </span>
               
                  <textarea type="text" id="dataNotif" class="form-control" value="<s:property value="desc" />" readonly><s:property value="desc" /></textarea>
            </div>
            
             
            
            <div class="col-md-3 col-xs-12" style="margin-bottom: 15px;">
            <s:set var="cod" value="%{r.codice}" />
               <span class="help-block" style="margin-top: 0;"> Codice </span>
                  <input type="text" id="dataProt" class="form-control" value="<s:property value="cod" />" readonly>
              
            </div>
            
                                                                                                                                                                                         
            
          </div>  
          
          <div class="row" style="margin-bottom: 40px;">
         		<div class="col-lg-4">
            		<div style="margin: 5px 0 15px; color: #697882; font-size: 20px; font-weight: 400;">Pratiche </div>
         		</div>
          
      	 </div>
          
           <div class="row" style="margin-bottom: 40px;">
            
            <div class="col-md-3 col-xs-12" style="margin-bottom: 15px;">
            <s:set var="tot" value="%{r.num}" />
               <span class="help-block" style="margin-top: 0;"> Quantità </span>
                  <input type="text"  class="form-control" value="<s:property value="tot" />" readonly>
              
            </div>
            
            <div class="col-md-3 col-xs-12" style="margin-bottom: 15px;">
            <s:set var="stp" value="%{r.suTotPerc}" />
               <span class="help-block" style="margin-top: 0;"> Su tot % </span>
                  <input type="text"  class="form-control" value="<s:property value="stp" />" readonly>
              
            </div>
            
            <div class="col-md-3 col-xs-12" style="margin-bottom: 15px;">
            		<s:set var="spsp" value="%{r.suPsPerc}" />
               		<span class="help-block" style="margin-top: 0;"> Su PS% </span>
                  		<input type="text"  class="form-control" value="<s:property value="spsp" />" readonly>
             	</div>
            
            </div>
            
            <div class="row" style="margin-bottom: 40px;">
            	
            	<div class="col-md-3 col-xs-12" style="margin-bottom: 15px;">
             		<s:set var="spt" value="%{r.pesoRischio}" />
               		<span class="help-block" style="margin-top: 0;"> Peso Rischio </span>
               
                  <input type="text"  class="form-control" value="<s:property value="spt" />" readonly>
            	</div>
             	
             	<div class="col-md-3 col-xs-12" style="margin-bottom: 15px;">
            		<s:set var="icni" value="%{r.importo}" />
               		<span class="help-block" style="margin-top: 0;"> Importo  </span>
                  		<input type="text"  class="form-control" value="<s:property value="icni" />" readonly>
             	</div>
             	
             	
            </div>

         	
         	<div class="row" style="margin-bottom: 40px;">
         
          <div class="col-lg-4">
         <div class="actions add-action">
               <!-- accessi.js -->
               <a href="#" onclick="loadDiv('/CruscottoAuditAtpoWebWeb/getTabRischiAccessi', 'tabRiskAccess', null, 'initRischiAccessi');" data-toggle="tab" class="btn btn-default btn-sm">  Indietro </a>
            </div>
         </div>
      </div>
         
     
   </div>
</div>