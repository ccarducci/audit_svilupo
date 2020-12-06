<%@ taglib prefix="s" uri="/struts-tags" %>
    <!-- BEGIN SAMPLE TABLE PORTLET-->
    <div class="portlet">
        <div class="portlet-body">
        	<div class="row" style="margin-top: 25px;">
            	<div class="col-md-12 col-xs-12" style="margin-bottom: 15px;">
            		<div style="margin: 5px 0 15px; color: #697882; font-size: 18px; font-weight: 400;">Tipologia di pratiche presenti nel campione:</div>
            	</div>
        	</div>
        	<div class="row" style="margin-top: 15px;">
        		<div class="col-md-2 col-xs-12" style="margin-bottom: 15px;">
                    <s:set var="numeroFav" value="%{numeroFavorevole}" />
                    <div class="form-group form-md-line-input">
                        <input type="text" class="form-control" value="<s:property value="numeroFav"/>" style="height: 38px;text-align: center; background: #e5e5e5;" readonly>
                        <label for="form_control_1" style="margin-top: -18px; font-weight: bold; text-align: center">
                            Favorevole:
                        </label>
                    </div>
                </div>
                <div class="col-md-2 col-xs-12" style="margin-bottom: 15px;">
                    <s:set var="numeroParz" value="%{numeroParzFavorevole}" />
                    <div class="form-group form-md-line-input">
                        <input type="text" class="form-control" value="<s:property value="numeroParz"/>" style="height: 38px;text-align: center; background: #e5e5e5;" readonly>
                        <label for="form_control_1" style="margin-top: -18px; font-weight: bold; text-align: center">
                            Parzialmente favorevole:
                        </label>
                    </div>
                </div>
                <div class="col-md-2 col-xs-12" style="margin-bottom: 15px;">
                    <s:set var="numeroSfav" value="%{sfavorevole}" />
                    <div class="form-group form-md-line-input">
                        <input type="text" class="form-control" value="<s:property value="numeroSfav"/>" style="height: 38px;text-align: center; background: #e5e5e5;" readonly>
                        <label for="form_control_1" style="margin-top: -18px; font-weight: bold; text-align: center">
                            Sfavorevole:
                        </label>
                    </div>
                </div>
                <div class="col-md-2 col-xs-12" style="margin-bottom: 15px;">
                    <s:set var="numeroDiss" value="%{dissenso}" />
                    <div class="form-group form-md-line-input">
                        <input type="text" class="form-control" value="<s:property value="numeroDiss"/>" style="height: 38px;text-align: center; background: #e5e5e5;" readonly>
                        <label for="form_control_1" style="margin-top: -18px; font-weight: bold; text-align: center">
                           Dissenso:
                        </label>
                    </div>
                </div>
                
        	</div>
        	<hr style="margin-bottom: 10px;">
        	<div class="row" >
            	<div class="col-md-12 col-xs-12" >
            		<div style="margin: 5px 0 15px; color: #697882; font-size: 18px; font-weight: 400;">Dati di contesto:</div>
            	</div>
        	</div>
        	
        	<!-- DATI CONTESTO - ROW 1 INIZIO -->
        	<div class="row" style="margin-top: 40px;">
        		
        	    <div class="col-md-2 col-xs-12" >
                    <s:set var="funzionari" value="%{c.funzionari}" />
                    <div class="form-group form-md-line-input">
                        <input type="text" class="form-control" value="<s:property value="funzionari"/>" style="height: 38px; text-align: center; background: #e5e5e5;" readonly>
                        <label for="form_control_1" style="margin-top: -18px; font-weight: bold; text-align: center">
                            <!--<span style="background-color: #7FB0DA"> n. funzionari </span> -->
                            n. funzionari
                        </label>
                    </div>
                </div>
        	
        		<div class="col-md-2 col-xs-12" style="margin-bottom: 15px;">
                    <s:set var="mediciInps" value="%{c.mediciInps}" />
                    <div class="form-group form-md-line-input">
                        <input type="text" class="form-control" value="<s:property value="mediciInps"/>" style="height: 38px;text-align: center; background: #e5e5e5;" readonly>
                        <label for="form_control_1" style="margin-top: -18px; font-weight: bold; text-align: center">
                            n. medici INPS
                        </label>
                    </div>
                </div>
        	
                <div class="col-md-2 col-xs-12" style="margin-bottom: 15px;">
                    <s:set var="aperte" value="%{c.aperte}" />
                    <div class="form-group form-md-line-input">
                        <input type="text" class="form-control" value="<s:property value="aperte"/>" style="height: 38px;text-align: center; background: #e5e5e5;" readonly>
                        <label for="form_control_1" style="margin-top: -18px; font-weight: bold; text-align: center">
                            Istanze aperte
                        </label>
                    </div>
                </div>
        	
                <div class="col-md-2 col-xs-12" style="margin-bottom: 15px;">
                    <s:set var="ggMediApertura" value="%{c.ggMediApertura}" />
                    <div class="form-group form-md-line-input">
                        <input type="text" class="form-control" value="<s:property value="ggMediApertura"/>" style="height: 38px;text-align: center; background: #e5e5e5;" readonly>
                        <label for="form_control_1" style="margin-top: -18px; font-weight: bold; text-align: center">
                            Gg medi di apertura 
                        </label>
                    </div>
                </div>
                
                <div class="col-md-2 col-xs-12" style="margin-bottom: 15px;">
                    <s:set var="ggMediChiusura" value="%{c.ggMediChiusura}" />
                    <div class="form-group form-md-line-input">
                        <input type="text" class="form-control" value="<s:property value="ggMediChiusura"/>" style="height: 38px;text-align: center; background: #e5e5e5;" readonly>
                        <label for="form_control_1" style="margin-top: -18px; font-weight: bold; text-align: center">
                            Gg medi chiusura
                        </label>
                    </div>
                </div>
                
        	</div>
        	<!-- DATI CONTESTO - ROW 1 FINE -->
        	
        	<!-- DATI CONTESTO - ROW 2 INIZIO -->
            <div class="row" style="margin-top: 40px;">

                <div class="col-md-2 col-xs-12" style="margin-bottom: 15px;">
                    <s:set var="numeroPraticheDef" value="%{c.numeroPraticheDef}" />
                    <div class="form-group form-md-line-input">
                        <input type="text" class="form-control" value="<s:property value="numeroPraticheDef"/>" style="height: 38px;text-align: center;background: #e5e5e5;" readonly>
                        <label for="form_control_1" style="margin-top: -18px; font-weight: bold; text-align: center">
                            n. pratiche definite
                        </label>
                    </div>
                </div>
                
                <div class="col-md-4 col-xs-12" style="margin-bottom: 15px;">
                    <s:set var="dataInizioOsservazione" value="%{c.dataInizioOsservazione}" />
                    <s:set var="dataFineOsservazione" value="%{c.dataFineOsservazione}" />
                    <div class="form-group form-md-line-input">
                        <input type="text" class="form-control" value="<s:property value="dataInizioOsservazione"/>" style="height: 38px;text-align: center; width:47%; margin-right:1%;display: inline; background: #e5e5e5;" readonly>
                        <input type="text" class="form-control" value="<s:property value="dataFineOsservazione"/>" style="height: 38px;text-align: center; width:47%;display: inline; background: #e5e5e5;" readonly>
                        <label for="form_control_1" style="margin-top: -18px; font-weight: bold; text-align: center">
                            Periodo di riferimento
                        </label>
                    </div>
                </div>
                
            </div>
            <!-- DATI CONTESTO - ROW 2 FINE -->
            
            <!-- DATI CONTESTO - ROW 3 INIZIO -->
            <div class="row" style="margin-top: 40px;">

                <div class="col-md-4 col-xs-12" style="margin-bottom: 15px;">
                    <s:set var="dissensoDom" value="%{c.dissensoDom}" />
                    <s:set var="dissensoRev" value="%{c.dissensoRev}" />
                    <div class="form-group form-md-line-input">
                        <input type="text" class="form-control" value="<s:property value="dissensoDom"/>" style="height: 38px;text-align: center; width:47%; margin-right:1%;display: inline; background: #e5e5e5;" readonly>
                        <input type="text" class="form-control" value="<s:property value="dissensoRev"/>%" style="height: 38px;text-align: center; width:47%; display: inline; background: #e5e5e5;" readonly>
                        <label for="form_control_1" style="margin-top: -18px; font-weight: bold; text-align: center">
                            Dissenso 
                        </label>
                    </div>
                </div>

                <div class="col-md-4 col-xs-12" style="margin-bottom: 15px;">
                    <s:set var="sfavDom" value="%{c.sfavDom}" />
                    <s:set var="sfavRev" value="%{c.sfavRev}" />
                    <div class="form-group form-md-line-input">
                        <input type="text" class="form-control" value="<s:property value="sfavDom"/>" style="height: 38px;text-align: center; width:47%; margin-right:1%;display: inline; background: #e5e5e5;" readonly>
                        <input type="text" class="form-control" value="<s:property value="sfavRev"/>%" style="height: 38px;text-align: center; width:47%; display: inline; background: #e5e5e5;" readonly>
                        <label for="form_control_1" style="margin-top: -18px; font-weight: bold; text-align: center">
                            Sfavorevole all'Istituto 
                        </label>
                    </div>
                </div>

                <div class="col-md-4 col-xs-12" style="margin-bottom: 15px;">
                    <s:set var="favDom" value="%{c.favDom}" />
                    <s:set var="favRev" value="%{c.favRev}" />
                    <div class="form-group form-md-line-input">
                        <input type="text" class="form-control" value="<s:property value="favDom"/>" style="height: 38px;text-align: center; width:47%; margin-right:1%;display: inline; background: #e5e5e5;" readonly>
                        <input type="text" class="form-control" value="<s:property value="favRev"/>%" style="height: 38px;text-align: center; width:47%; display: inline; background: #e5e5e5;" readonly>
                        <label for="form_control_1" style="margin-top: -18px; font-weight: bold; text-align: center">
                            Favorevole all'Istituto 
                        </label>
                    </div>
                </div>

            </div>
            <!-- DATI CONTESTO - ROW 3 FINE -->
            
            <!-- DATI CONTESTO - ROW 4 INIZIO -->
            <div class="row" style="margin-top: 40px;">

                <div class="col-md-4 col-xs-12" style="margin-bottom: 15px; font-weight: bold; text-align: center">
                    <s:set var="parzFavDom" value="%{c.parzFavDom}" />
                    <s:set var="parzFavRev" value="%{c.parzFavRev}" />
                    <div class="form-group form-md-line-input">
                        <input type="text" class="form-control" value="<s:property value="parzFavDom"/>" style="height: 38px;text-align: center; width:47%; margin-right:1%;display: inline; background: #e5e5e5;" readonly>
                        <input type="text" class="form-control" value="<s:property value="parzFavRev"/>%" style="height: 38px;text-align: center; width:47%; display: inline; background: #e5e5e5;" readonly>
                        <label for="form_control_1" style="margin-top: -18px;font-weight: bold; text-align: center">
                            Parzialmente favorevole
                        </label>
                    </div>
                </div>
                
                <div class="col-md-4 col-xs-12" style="margin-bottom: 15px;">
                    <s:set var="cessMatContendDom" value="%{c.cessMatContendDom}" />
                    <s:set var="cessMatContendRev" value="%{c.cessMatContendRev}" />
                    <div class="form-group form-md-line-input">
                        <input type="text" class="form-control" value="<s:property value="cessMatContendDom"/>" style="height: 38px;text-align: center; width:47%; margin-right:1%;display: inline; background: #e5e5e5;" readonly>
                        <input type="text" class="form-control" value="<s:property value="cessMatContendRev"/>%" style="height: 38px;text-align: center; width:47%; display: inline; background: #e5e5e5;" readonly>
                        <label for="form_control_1" style="margin-top: -18px; font-weight: bold; text-align: center">
                            Cessata materia del contendere
                        </label>
                    </div>
                </div>

                <div class="col-md-4 col-xs-12" style="margin-bottom: 15px;">
                    <s:set var="defAutAtpDom" value="%{c.defAutAtpDom}" />
                    <s:set var="defAutAtpRev" value="%{c.defAutAtpRev}" />
                    <div class="form-group form-md-line-input">
                        <input type="text" class="form-control" value="<s:property value="defAutAtpDom"/>" style="height: 38px;text-align: center; width:47%; margin-right:1%;display: inline; background: #e5e5e5;" readonly>
                        <input type="text" class="form-control" value="<s:property value="defAutAtpRev"/>%" style="height: 38px;text-align: center; width:47%; display: inline; background: #e5e5e5;" readonly>
                        <label for="form_control_1" style="margin-top: -18px; font-weight: bold; text-align: center">
                            Definizione automatica ATP
                        </label>
                    </div>
                </div>

           	</div>
            <!-- DATI CONTESTO - ROW 4 FINE -->
            
            <!-- DATI CONTESTO - ROW 5 INIZIO -->
            <div class="row" style="margin-top: 40px;">

                <div class="col-md-4 col-xs-12" style="margin-bottom: 15px;">
                    <s:set var="estintaDom" value="%{c.estintaDom}" />
                    <s:set var="estintaRev" value="%{c.estintaRev}" />
                    <div class="form-group form-md-line-input">
                        <input type="text" class="form-control" value="<s:property value="estintaDom"/>" style="height: 38px;text-align: center; width:47%; display: inline; background: #e5e5e5;" readonly>
                        <input type="text" class="form-control" value="<s:property value="estintaRev"/>%" style="height: 38px;text-align: center; width:47%; margin-right:1%;display: inline; background: #e5e5e5;" readonly>
                        <label for="form_control_1" style="margin-top: -18px; font-weight: bold; text-align: center">
                            Estinta 
                        </label>
                    </div>
                </div>
                
                <div class="col-md-4 col-xs-12" style="margin-bottom: 15px;">
                    <s:set var="inammissDom" value="%{c.inammissDom}" />
                    <s:set var="inammissRev" value="%{c.inammissRev}" />
                    <div class="form-group form-md-line-input">
                        <input type="text" class="form-control" value="<s:property value="inammissDom"/>" style="height: 38px;text-align: center; width:47%; margin-right:1%;display: inline; background: #e5e5e5;" readonly>
                        <input type="text" class="form-control" value="<s:property value="inammissRev"/>%" style="height: 38px;text-align: center; width:47%; display: inline; background: #e5e5e5;" readonly>
                        <label for="form_control_1" style="margin-top: -18px; font-weight: bold; text-align: center">
                            Inammissibilità 
                        </label>
                    </div>
                </div>

                <div class="col-md-4 col-xs-12" style="margin-bottom: 15px;">
                    <s:set var="acqErrDom" value="%{c.acqErrDom}" />
                     <s:set var="acqErrRev" value="%{c.acqErrRev}" />
                    <div class="form-group form-md-line-input">
                        <input type="text" class="form-control" value="<s:property value="acqErrDom"/>" style="height: 38px;text-align: center; width:47%; margin-right:1%;display: inline; background: #e5e5e5;" readonly>
                        <input type="text" class="form-control" value="<s:property value="acqErrRev"/>%" style="height: 38px;text-align: center; width:47%; display: inline; background: #e5e5e5;" readonly>
                        <label for="form_control_1" style="margin-top: -18px; font-weight: bold; text-align: center">
                             Acquisite erroneamente
                        </label>
                    </div>
                </div>

           	</div>
            <!-- DATI CONTESTO - ROW 5 FINE -->
             
             
            <!-- DATI CONTESTO - ROW 6 INIZIO -->
            <div class="row" style="margin-top: 40px;">
            
                <div class="col-md-4 col-xs-12" style="margin-bottom: 15px;">
                    <s:set var="abbandonoDom" value="%{c.abbandonoDom}" />
                    <s:set var="abbandonoRev" value="%{c.abbandonoRev}" />
                    <div class="form-group form-md-line-input">
                        <input type="text" class="form-control" value="<s:property value="abbandonoDom"/>" style="height: 38px;text-align: center; width:47%; margin-right:1%; display: inline; background: #e5e5e5;" readonly>
                        <input type="text" class="form-control" value="<s:property value="abbandonoRev"/>%" style="height: 38px;text-align: center; width:47%; display: inline; background: #e5e5e5;" readonly>
                        
                        <label for="form_control_1" style="margin-top: -18px; font-weight: bold; text-align: center">
                            Altro 
                        </label>
                    </div>
                </div>

                <div class="col-md-2 col-xs-12" style="margin-bottom: 15px;">
                    <s:set var="dissensoInps" value="%{c.dissensoInps}" />
                    <div class="form-group form-md-line-input">
                        <input type="text" class="form-control" value="<s:property value="dissensoInps"/>" style="height: 38px;text-align: center; background: #e5e5e5;" readonly>
                        <label for="form_control_1" style="margin-top: -18px; font-weight: bold; text-align: center">
                            Sentenze fav di 1° grado (dissenso INPS)
                        </label>
                    </div>
                </div>
                
                <div class="col-md-2 col-xs-12" style="margin-bottom: 15px;">
                    <s:set var="ctu" value="%{c.ctu}" />
                    <div class="form-group form-md-line-input">
                        <input type="text" class="form-control" value="<s:property value="ctu"/>" style="height: 38px;text-align: center; background: #e5e5e5;" readonly>
                        <label for="form_control_1" style="margin-top: -18px; font-weight: bold; text-align: center">
                            CTU
                        </label>
                    </div>
                </div>

                <div class="col-md-2 col-xs-12" style="margin-bottom: 15px;">
                    <s:set var="visitePeritali" value="%{c.visitePeritali}" />
                    <div class="form-group form-md-line-input">
                        <input type="text" class="form-control" value="<s:property value="visitePeritali"/>" style="height: 38px;text-align: center; background: #e5e5e5;" readonly>
                        <label for="form_control_1" style="margin-top: -18px; font-weight: bold; text-align: center">
                            VISITE PERITALI (**)
                        </label>
                    </div>
                </div>
                
                <div class="col-md-2 col-xs-12" style="margin-bottom: 15px;">
                    <s:set var="percCvpSuNrCtu" value="%{c.percCvpSuNrCtu}" />
                    <div class="form-group form-md-line-input">
                        <input type="text" class="form-control" value="<s:property value="percCvpSuNrCtu"/>%" style="height: 38px;text-align: center; background: #e5e5e5;" readonly>
                        <label for="form_control_1" style="margin-top: -18px; font-weight: bold; text-align: center">
                           % VP su nr CTU
                        </label>
                    </div>
                </div>

           	</div>
            <!-- DATI CONTESTO - ROW 6 FINE -->
             
           
           <!-- DATI CONTESTO - ROW 7 INIZIO -->
            <div class="row" style="margin-top: 15px;">
                
                <div class="col-md-2 col-xs-12" style="margin-bottom: 15px;">
                    <s:set var="percCvpSuNrMeidici" value="%{c.percCvpSuNrMeidici}" />
                    <div class="form-group form-md-line-input">
                        <input type="text" class="form-control" value="<s:property value="percCvpSuNrMeidici"/>%" style="height: 38px;text-align: center;background: #e5e5e5;" readonly>
                        <label for="form_control_1" style="margin-top: -18px; font-weight: bold; text-align: center">
                            % VP su nr MEDICI
                            
                        </label>
                    </div>
                </div>
                <div class="col-md-2 col-xs-12" style="margin-bottom: 15px;">
                    <s:set var="percParereConcorde" value="%{c.percParereConcorde}" />
                    <div class="form-group form-md-line-input">
                        <input type="text" class="form-control" value="<s:property value="percParereConcorde"/>%" style="height: 38px;text-align: center;background: #e5e5e5;" readonly>
                        <label for="form_control_1" style="margin-top: -18px; font-weight: bold; text-align: center">
                            % parerI "concorde" su CTU sfavorevoli
                        </label>
                    </div>
                </div>
            </div>
            <!-- DATI CONTESTO - ROW 7 FINE -->
    </div>