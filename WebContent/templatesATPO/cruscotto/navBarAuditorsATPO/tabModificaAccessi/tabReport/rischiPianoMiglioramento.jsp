<%@ taglib prefix="s" uri="/struts-tags" %>
<div class="portlet">
    <div class="portlet-body">
        <div class="row" style="margin-bottom: 30px;">
            <!-- DROPDOWN NON CONFORMITA -->
            <div class="col-md-8 col-xs-12" style="margin-bottom: 15px;">
                <div class="form-group form-md-line-input">
                    <s:set var="destinatarioNotifica" value="%{faseNotifica.destinatarioReale}" />
                    <select id="rischiPianoMigl" onchange="getEsprRischioPM();" class="form-control select2me" <s:property value="controlComboDisabled" /> >
                    <option value="" disabled selected>Seleziona Rischio</option>
                    <s:iterator value="rischi" var="r">
                        <option id="<s:property value=" #r.idMRischio " />" value="<s:property value=" #r.idMRischio " />">

                            <s:property value="#r.descrizioneRischio" />
                        </option>
                    </s:iterator>
                    </select>

                </div>
            </div>
        </div>
        <div class="row" id="esprRischiTable" style="margin-bottom: 30px;">
            <!-- TABELLA M-RisEspr -->

        </div>
    </div>
</div>