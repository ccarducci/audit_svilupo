<%@ taglib prefix="s" uri="/struts-tags" %>

    <aside class="menu-sx-levelone-facility addDom">

        <div class="portlet light">
            <div class="portlet-title">
                <div class="caption font-green">
                    <i class="icon-pin font-green"></i>
                    <span class="caption-subject bold uppercase">Domini</span>
                </div>
            </div>
            <div class="portlet-body form">

                <form>
                    <table class="table table-striped table-bordered table-advance table-hover" id="addDominiAttivitaDettaglioTable">
                        <thead>
                            <th>
                            </th>
                            <th>Descrizione</th>
                            <th>Data inizio</th> 

                        </thead>
                        <tbody>
                        </tbody>
                    </table>

                    <div class="form-actions noborder" style="position: relative bottom: 10px;">
                        <!-- modificaPraticheAccessi.js -->
                        <button type="button" class="btn default" onclick="associaDomToAttivitaDettaglio()">Associa</button>

                        <button type="button" class="btn default" onclick="slideAsideNew('');">Esci</button>
                    </div>
                </form>
            </div>
        </div>
    </aside>