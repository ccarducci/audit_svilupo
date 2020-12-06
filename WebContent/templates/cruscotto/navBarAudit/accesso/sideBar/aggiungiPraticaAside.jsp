<%@ taglib prefix="s" uri="/struts-tags"%>
<aside class="menu-sx-levelone-facility add">
        <div class="portlet light">
            <div class="portlet-title">
                <div class="caption font-green">
                    <i class="icon-pin font-green"></i>
                    <span class="caption-subject bold uppercase">AGGIUNGI PRATICA</span>
                </div>
            </div>
            <div class="portlet-body">
                <table class="table table-striped table-bordered table-advance table-hover" id="praticheSessioneTable">
                    <thead>
                        <tr>
                          <th style="width: 80px; text-align: center;"></th>
                           <th>Sede</th>
                           <th>Fascicolo</th>
                           <th>Esito</th>
                           <th>Parte</th>
                           <th>Data Notifica</th>
                           <th>Data Apertura</th>
                        </tr>
                    </thead>
                    <tbody>
                       
                    </tbody>
                </table>
                <div class="form-actions noborder">
                    <button type="button" class="btn blue" onclick="getCheckBoxID('addPraticaToCampione')">Aggiungi</button>
                    <button type="button" class="btn default" onclick="slideAsideNew('');">Chiudi</button>
                </div>
            </div>
            <!-- END SAMPLE TABLE PORTLET-->
        </div>
    </aside>