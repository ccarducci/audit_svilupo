<%@ taglib prefix="s" uri="/struts-tags" %>
<div class="portlet">
   <div class="portlet-body">
      <div class="row" style="margin-bottom: 40px;">
         <div class="col-lg-4">
            <div style="margin: 5px 0 15px; color: #697882; font-size: 20px; font-weight: 400;">Non Conformità - Dettagli </div>
         </div>
          <div class="col-lg-4">
         
         </div>
      </div>
      
      <div class="row">
         
      </div>
      
      
      <div class="row" style="margin-bottom: 40px;">
            
           
            <div class="col-md-3 col-xs-12" style="margin-bottom: 15px;">
             <s:set var="desc" value="%{nc.descrizione}" />
               <span class="help-block" style="margin-top: 0;"> Descrizione </span>
               
                  <textarea type="text" id="dataNotif" class="form-control" value="<s:property value="desc" />" readonly><s:property value="desc" /></textarea>
            </div>
            
             
            
            <div class="col-md-3 col-xs-12" style="margin-bottom: 15px;">
            <s:set var="cod" value="%{nc.codice}" />
               <span class="help-block" style="margin-top: 0;"> Codice </span>
                  <input type="text" id="dataProt" class="form-control" value="<s:property value="cod" />" readonly>
              
            </div>
            
            <div class="col-md-3 col-xs-12" style="margin-bottom: 15px;">
            <s:set var="vnc" value="%{nc.valoreINCC}" />
               <span class="help-block" style="margin-top: 0;"> Valore  (INCC) </span>
                  <input type="text" id="dataProt" class="form-control" value="<s:property value="vnc" />" readonly>
              
            </div>
            
          </div>  
          
           <div class="row" style="margin-bottom: 40px;">
            
            <div class="col-md-3 col-xs-12" style="margin-bottom: 15px;">
             <s:set var="di" value="%{nc.dataInizio}" />
               <span class="help-block" style="margin-top: 0;"> Data Inizio </span>
               
                  <input type="text" id="dataNotif" class="form-control" value="<s:property value="di" />" readonly>
                 
               
            </div>
            
            
            	<div class="col-md-3 col-xs-12" style="margin-bottom: 15px;">
             		<s:set var="df" value="%{nc.dataFine}" />
               		<span class="help-block" style="margin-top: 0;"> Data Fine </span>
               		
                  		<input type="text" id="dataNotif" class="form-control" value="<s:property value="df" />" readonly>
               		
            	</div>
        
    
           
            <div class="col-md-3 col-xs-12" style="margin-bottom: 15px;">
            <s:set var="qnt" value="%{nc.quantita}" />
               <span class="help-block" style="margin-top: 0;"> Quantita </span>
                  <input type="text" id="dataProt" class="form-control" value="<s:property value="qnt" />" readonly>
              
            </div>
            
             
            
            
            
            </div>
            
            <div class="row" style="margin-bottom: 40px;">
            	
            	<div class="col-md-3 col-xs-12" style="margin-bottom: 15px;">
            		<s:set var="p" value="%{nc.peso}" />
               		<span class="help-block" style="margin-top: 0;"> Peso </span>
                  		<input type="text" id="dataProt" class="form-control" value="<s:property value="p" />" readonly>
             	</div>
             	
             	<div class="col-md-3 col-xs-12" style="margin-bottom: 15px;">
            		<s:set var="vps" value="%{nc.valorepesato}" />
               		<span class="help-block" style="margin-top: 0;"> Valore Pesato </span>
                  		<input type="text" id="dataProt" class="form-control" value="<s:property value="vps" />" readonly>
             	</div>
             	
             	<div class="col-md-3 col-xs-12" style="margin-bottom: 15px;">
            		<s:set var="ncvm" value="%{nc.ncConValoreMinimo}" />
               		<span class="help-block" style="margin-top: 0;"> Descrizione NC con il valore minimo </span>
                  		<input type="text" id="dataProt" class="form-control" value="<s:property value="ncvm" />" readonly>
             	</div>
            </div>
            
             <div class="row" style="margin-bottom: 40px;">
            	<div class="col-md-3 col-xs-12" style="margin-bottom: 15px;">
            		<s:set var="min" value="%{nc.minimo}" />
               		<span class="help-block" style="margin-top: 0;"> Minimo </span>
                  		<input type="text" id="dataProt" class="form-control" value="<s:property value="min" />" readonly>
             	</div>
             	
             	<div class="col-md-3 col-xs-12" style="margin-bottom: 15px;">
            		<s:set var="max" value="%{nc.massimo}" />
               		<span class="help-block" style="margin-top: 0;"> Massimo </span>
                  		<input type="text" id="dataProt" class="form-control" value="<s:property value="max" />" readonly>
             	</div>
             	
             	<div class="col-md-3 col-xs-12" style="margin-bottom: 15px;">
            		<s:set var="dncvm" value="%{nc.ncConValoreMassimo}" />
               		<span class="help-block" style="margin-top: 0;"> Descrizione NC con il valore massimo </span>
                  		<input type="text" id="dataProt" class="form-control" value="<s:property value="dncvm" />" readonly>
             	</div>
            
         	</div>
         	
         	 <div class="row" style="margin-bottom: 40px;">
         	
         	
         	
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