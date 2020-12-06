<%@ taglib prefix="s" uri="/struts-tags" %>  
<!-- BEGIN SIDEBAR DX -->
<aside class="menu-sx-levelone-facility add" style="margin-top: -270px;">
   <div class="portlet light">
      <div class="portlet-title">
         <div class="caption font-green"><i class="icon-pin font-green"></i>
            <span class="caption-subject bold uppercase">Dettaglio documentazione mancante</span>
         </div>
      </div>
      <div class="portlet-body form">
         <form role="form">
            <div class="form-body">
              
              
               <div class="row" style="margin-bottom: 30px;">
      		
      		<div class="col-md-4 col-xs-12" style="margin-bottom: 15px;">
               <div class="form-group" style="margin-bottom: 0;">
                  <label for="form_control_2" style="font-size: 13px; color: #888888;">Dettaglio documentazione mancante</label><br>
                  <s:set var="dettDocMancante" value="%{fascicolo.dettDocMancante}" />
                   <s:set var="codDettDocMancante" value="%{codDocManc}" />
                  <select id="dettDocMancante" class="form-control select2me">
                     <option selected value="<s:property value="codDettDocMancante"/>"><s:property value="dettDocMancante"/></option>
                    <s:iterator value="dettDocMancate" var="opzdettDocMancate">
                			
                    			<option id="<s:property value="#opzdettDocMancate.idTplTopologiche" />" value="<s:property value="#opzdettDocMancate.codifica" />">
                        			<s:property value="#opzdettDocMancate.descrizione" />
                   				</option>
                			
            		</s:iterator>
                  </select>
               </div>
          
            
      	</div>
               </div>
               
              
               <div class="row">
             		  <div class="col-md-1 col-xs-12" style="margin-bottom: 15px;">
                  		<button type="button" class="btn blue" onclick="aggiungiDocMancante()">Aggiungi</button>
               	     </div>
               	      <div class="col-md-1 col-xs-12" style="margin-bottom: 15px;">
               			<button type="button" class="btn default" onclick="slideAsideNew(''); loadDiv('/CruscottoAuditAtpoWebWeb/getRiepilogoFascicolo', 'riepFascicolo', null, 'initDocMancanteTable');">Chiudi</button>
                	</div>
               </div>
               </div>
            
         </form>
      
      </div>
   </div>
</aside>