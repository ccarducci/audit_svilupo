<aside class="menu-sx-levelone-facility update">

	<input type="hidden" id="idVerbaleAside" name="idVerbaleAside" value="">
	<input type="hidden" id="idNcvAside" name="idNcv" value="">
    <input type="hidden" id="vCompAside" name="vComp" value="">
    <input type="hidden" id="rischioAside" name="rischio" value="">
    <input type="hidden" id="valoreCalcolatoAside" name="valoreCalcolato" value="">

        <div class="portlet light">
            <div class="portlet-title">
                <div class="caption font-green">
                    <i class="icon-pin font-green"></i>
                    <span class="caption-subject bold uppercase">MODIFICA INDICATORI</span>
                </div>
            </div>
            <div class="portlet-body form">
                <form role="form">
                    <div class="form-body">
                        <div class="row" style="margin-bottom: 60px;">
                            <div class="col-md-6 col-xs-12" style="margin-bottom: 15px;">
                                <div class="form-group form-md-line-input">
                                    <input type="text" class="form-control" id="valoreRealeAside" value="" style="height: 38px;">
                                    <label for="form_control_1">Valore reale <i class="fa fa-asterisk obbligatorio"></i></label>
                                </div>
                            </div>
                            <div class="col-md-6 col-xs-12" style="margin-bottom: 15px;">
                                <div class="form-group form-md-line-input">
                                    <input type="text" class="form-control" id="noteAside" value="" style="height: 38px;">
                                    <label for="form_control_2">Note <i class="fa fa-asterisk obbligatorio"></i></label>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="form-actions noborder" style="position: absolute; bottom: 10px;">
                    <!-- modificaPraticheAccessi.js -->
                        <button type="button" class="btn blue"onclick="salvaNonConformitaVerbale();slideAsideNew('');">Salva</button>
                        
                        <button type="button" class="btn default" onclick="slideAsideNew('');">Annulla</button>
                    </div>
                </form>
            </div>
        </div>
    </aside>