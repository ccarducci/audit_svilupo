<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page import="it.tecnet.crs.session.DatiUtente"%>
<%@ page import="org.apache.struts2.ServletActionContext" %>
<%@ page import="java.util.*" %>
<%
   HttpServletRequest r = ServletActionContext.getRequest();
   DatiUtente user= (DatiUtente)r.getSession().getAttribute("DatiUtente");
   %>
<s:set var="dataAggiornamentoDatiSessione" value="%{dataAggiornamentoDatiSessione}" />
<div class="row">
   <div class="col-md-12">
      <!-- BEGIN SAMPLE TABLE PORTLET-->
      <div class="portlet">
         <div class="portlet-body">
            <div class="row">
               <div class="col-md-12 ">
                  <!-- BEGIN Portlet PORTLET-->
                  <div class="portlet light accessori">
                     <div class="portlet-title tabbable-line">
                        <ul class="nav nav-tabs">
                           <li class="active">
                              <!-- TAB 1 -->
                              <!-- cruscottoScript.js -->
                              <a href="#reportNonConf" oc data-toggle="tab">
                              Non conformità </a>
                           </li>
                           <li>
                              <!-- TAB 2 -->
                              <s:if test="#dataAggiornamentoDatiSessione != null">
                              		<a href="#rischiPianoMiglioramento" onclick=" loadDiv('/CruscottoAuditAtpoWebWeb/getTabRischiPianoMiglioramento', 'rischiPianoMiglioramento', null, null);" data-toggle="tab">
                             		 Rischi </a>
                               </s:if>
                               <s:else>
                               	<a href="#reportNonConf" oc data-toggle="tab">
                             		 Rischi </a>
                               </s:else>
                           </li>
                        </ul>
                     </div>
                     <div class="portlet-body">
                        <div class="tab-content">
                           <!-- ***************************************** TAB 1 (NON CONFORMITA) ******************************************-->
                           <div class="tab-pane active" id="reportNonConf">
                           	 <s:set var="dataAggiornamentoDatiSessione" value="%{dataAggiornamentoDatiSessione}" />
                           	
                           	 	<div class="portlet">
                                 <div class="portlet-body">
                                    <div class="row" style="margin-bottom: 30px;">
                                       <!-- DROPDOWN NON CONFORMITA -->	
                                        <s:if test="#dataAggiornamentoDatiSessione != null">
                                       
                                       	<div class="col-md-8 col-xs-12" style="margin-bottom: 15px;">
                                       		<div class="form-group form-md-line-input">
                                             <s:set var="destinatarioNotifica" value="%{faseNotifica.destinatarioReale}" />
                  								
                  								<select id="nonConformitaPianoMigl" onchange="getVarComportamentali();" class="form-control select2me" <s:property value="controlComboDisabled" /> >
                    								<option value="" disabled selected>Seleziona non conformità</option>
                    								
														<s:iterator value="nonConformita" var="nc">
                    										<option  id="<s:property value="#nc.idMNonConf" />" 
                    												value="<s:property value="#nc.idMNonConf" />"
                    												>
                    												
                        									<s:property value="#nc.descrizione" />
                   											</option>
            											</s:iterator>
                  								</select>
                                                
                                                            
                                             </div>
                                       </div>
                                       </s:if>
                                       <s:else>
                                       <div class="col-md-8 col-xs-12" style="margin-bottom: 15px;">
                                       		<h3>Data aggiornamento dati sessione assente</h3>
                                       	</div>
                                       </s:else>
                                    </div>
                                    <div class="row" id="variantiComportamentaliTable" style="margin-bottom: 30px;">
                                       <!-- TABELLA VARIANTI COMPORTSAMENTALI -->
                                       
                                       
                                    </div>
                                 </div>
                              </div>
                        
                              
                           </div>
                           <!-- **************************************** TAB 2(RISCHI) ***************************************** -->
                           
                           <div class="tab-pane" id="rischiPianoMiglioramento">
                              <!-- CONTENUTO TAB  -->
                           </div>
                        </div>
                     </div>
                  </div>
               </div>
            </div>
         </div>
      </div>
      <!-- END SAMPLE TABLE PORTLET-->
   </div>
</div>
