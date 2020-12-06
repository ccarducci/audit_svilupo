<%@ taglib prefix="s" uri="/struts-tags" %>

    <aside class="menu-sx-levelone-facility addCompT">

        <div class="portlet light">
            <div class="portlet-title">
                <div class="caption font-green">
                    <i class="icon-pin font-green"></i>
                    <span class="caption-subject bold uppercase">Componenti tecnici</span>
                </div>
            </div>
            <div class="portlet-body form">

                <form>
                   <table class="table table-striped table-bordered table-advance table-hover" id="compTecAttCompTableDaAssociare">
                                    <thead>
                                     	
                                    	<th style="text-align: center;">
                                    		
                                    	
                                    	</th>
                                    
                                        <th>Descrizione</th>
                                        <th>Codice</th>
                                        <th>Autore</th>      
                                    	<th>Data pubblicazione</th>
                                        <th>Versione</th> 
                                        <th>Tipo</th>   
                                    
                                                         
                                    	</thead>
                                    <tbody>
                                    </tbody>
                                </table>

                    <div class="form-actions noborder" style="position: relative bottom: 10px;">
                        <!-- modificaPraticheAccessi.js -->
                        <button type="button" class="btn default" onclick="associaCompTecToAttComp()">Associa</button>

                        <button type="button" class="btn default" onclick="slideAsideNew('');">Esci</button>
                    </div>
                </form>
            </div>
        </div>
    </aside>