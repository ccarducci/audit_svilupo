//salva i verbali in fase notifica 

function salvaNotifica(){
	

	//li prendo dall input type text
	var dataFineIspezione = $('#dataFineIspezioneFN').val().trim();
	var PEC = $('#pecFN').val().trim();
	var destinatario = $("#destinatarioNotifica").find('option:selected').text().trim();
	var modalitaNotifica=$("#modalitaNotifica").find('option:selected').text().trim();
	var dataNotifica= $('#dataNotificaFN').val().trim();
	var dataNotificaNonModificabile= $('#dataNotificaFNonModificabile').val().trim();
	var chiNotifica=$("#chiNotifica").find('option:selected').text().trim();
	var dataProtocollo = $('#dataProtocollo').val().trim();
	var datainserimentoDataNotifica= $('#dataInsDataNot').val().trim();
	var dataChiusuraVerbale=  $('#dataChiuVerb').val().trim();
	var dataConsegnaFascicolo=  $('#dataConsFasc').val().trim();
	//var dataTrasmFascicolo=  $('#dataTrsFasc').val().trim();
//	var inserimentoDataNotifica=$("#inserimentoDataNotifica").find('option:selected').text().trim();
	var tipologiaverbaleIspettivo=$("#tipVerbaleIspettivo").find('option:selected').text().trim();

	
	if(destinatario == "" || chiNotifica==""  ||tipologiaverbaleIspettivo==""){
		var settings = {
				theme: 'teal',
				sticky: false,
				horizontalEdge: 'top',
				verticalEdge: 'right',
				life: 3000
		};

		$.notific8('zindex', 11500);
		$.notific8('Questi campi* sono obbligatori ', settings);
		return; 
	}
	
	
	//1 controllo data notifica verbale > data protocollo;
	if(!checkDate(dataProtocollo,dataNotifica)){
		var settings = {
				theme: 'teal',
				sticky: false,
				horizontalEdge: 'top',
				verticalEdge: 'right',
				life: 3000
		};

		$.notific8('zindex', 11500);
		$.notific8('Data notifica verbale deve essere maggiore di data protocollo ', settings);
		return; 
	}
	


// 2 Data consegna fascicolo > data protocollo, Data fine ispezione, Data chiusura verbale;
	if(!checkDate(dataProtocollo,dataConsegnaFascicolo)){
		var settings = {
				theme: 'teal',
				sticky: false,
				horizontalEdge: 'top',
				verticalEdge: 'right',
				life: 3000
		};

		$.notific8('zindex', 11500);
		$.notific8('Data consegna fascicolo deve essere maggiore di data protocollo ', settings);
		return; 
	}
	if(!checkDate(dataFineIspezione,dataConsegnaFascicolo)){
		var settings = {
				theme: 'teal',
				sticky: false,
				horizontalEdge: 'top',
				verticalEdge: 'right',
				life: 3000
		};

		$.notific8('zindex', 11500);
		$.notific8('Data consegna fascicolo deve essere maggiore di data fine ispezione ', settings);
		return; 
	}
	if(!checkDate(dataChiusuraVerbale,dataConsegnaFascicolo)){
		var settings = {
				theme: 'teal',
				sticky: false,
				horizontalEdge: 'top',
				verticalEdge: 'right',
				life: 3000
		};

		$.notific8('zindex', 11500);
		$.notific8('Data consegna fascicolo deve essere maggiore di data chiusura verbale ', settings);
		return; 
	}
// 3 se è presente Data notifica verbale <= data inserimento data notifica, non è modificabile;
	var check=false;
	
	if(dataNotifica !="" && dataNotificaNonModificabile !=dataNotifica){
		var check=checkDate(dataNotifica,datainserimentoDataNotifica);
		if(!check){
			var settings = {
					theme: 'teal',
					sticky: false,
					horizontalEdge: 'top',
					verticalEdge: 'right',
					life: 3000
			};

			$.notific8('zindex', 11500);
			$.notific8('Non è possibile modificare data notifica verbale perchè minore di data inserimento data notifica ', settings);
			$('#dataNotificaFNonModificabile').val(dataNotificaNonModificabile);
		}
	}

$.ajax( {
	type : 'POST',
	//AuditorsAction.java
	url : '/CruscottoAuditAtpoWebWeb/json/salvaFaseNotifica',
	data :{ 

	'faseNotifica.dataFineIspezione' : dataFineIspezione,
	'faseNotifica.indirizzoPecReale' : PEC,
	'faseNotifica.destinatarioReale': destinatario,
	'faseNotifica.modalitaNotifica' : modalitaNotifica,
	'faseNotifica.dataNotifica' : dataNotifica,
	'faseNotifica.chiNotifica': chiNotifica,
	'faseNotifica.dataProtocollo' : dataProtocollo,
	'faseNotifica.dataInsDataNotifica' : datainserimentoDataNotifica,
	'faseNotifica.dataChiusuraVerb': dataChiusuraVerbale,
	'faseNotifica.dataConsegnaFasc' : dataConsegnaFascicolo,
	//'faseNotifica.dataTrasmisFascicolo' : dataTrasmFascicolo,
	//'faseNotifica.inserimentoDataNotifica': inserimentoDataNotifica,
	'faseNotifica.tipologiaVerbaleIspettivo':tipologiaverbaleIspettivo
},


success : function(data) {

	var settings = {
			theme: 'teal',
			sticky: false,
			horizontalEdge: 'top',
			verticalEdge: 'right',
			life: 3000
	};

	$.notific8('zindex', 11500);
	$.notific8('Modifiche salvate con successo ', settings);
	

	return; 
	

},error: function(data){
	var settings = {
			theme: 'teal',
			sticky: false,
			horizontalEdge: 'top',
			verticalEdge: 'right',
			life: 3000
	};
	Metronic.stopPageLoading();
	$.notific8('zindex', 11500);
	$.notific8('Errore durante il salvataggio ', settings);
	return; 
}
});

}


//salva i verbali in fase definzione

function salvaDefinizione(){
	

	//li prendo dall input type text
	var esitoReg = $("#esitoReg").find('option:selected').text().trim();
	var dataEsitoReg= $('#dataEsitoReg').val().trim();
	var erroreEsito = $("#erroreEsito").find('option:selected').text().trim();
	var preObblSol = $("#preObblSol").find('option:selected').text().trim();
	var cSospensione = $('#cSospensione').val().trim();
	var modComDisc =$("#modComDisc").find('option:selected').text().trim();
	var dataInvioComDisc = $('#dataInvioComDisc').val().trim();
	var dataTrasmissione = $('#dataTrasmissione').val();
	var gestContrAff =$("#gestContrAff").find('option:selected').text().trim();
	var fallimento =$("#fallimento").find('option:selected').text().trim();
	//var dataFall = $('#dataFall').val().trim();
	var credPrescritto =$("#credPrescritto").find('option:selected').text().trim();
	var impCredito = $('#impCredito').val().trim();
	var impSanz = $('#impSanz').val().trim();
	var presFasc =$("#presFasc").find('option:selected').text().trim();
	var dataConsFascCart =$("#dataConsFascCart").val().trim();
	var dataUdVerStPass = $('#dataUdVerStPass').val().trim();
	var dataScContApp =$("#dataScContApp").val().trim();
	var esRegCorretto=$("#esRegCorretto").find('option:selected').text().trim();
	var dataDecadenzaRuolo=$("#datadecruolo").val().trim();
	var dataNotificaverbaleDef=$("#dataNotificaverbaleDef").val().trim();

	if(esitoReg == "" || erroreEsito=="" || preObblSol =="" ||gestContrAff=="" || fallimento == "" || credPrescritto=="" || presFasc ==""){
		var settings = {
				theme: 'teal',
				sticky: false,
				horizontalEdge: 'top',
				verticalEdge: 'right',
				life: 3000
		};

		$.notific8('zindex', 11500);
		$.notific8('Questi campi* sono obbligatori ', settings);
		return; 
	}
	
	//obbl. se presente un esito reg
	if(esitoReg != "" && esRegCorretto ==""){
		var settings = {
				theme: 'teal',
				sticky: false,
				horizontalEdge: 'top',
				verticalEdge: 'right',
				life: 3000
		};

		$.notific8('zindex', 11500);
		$.notific8('Esito Regolarizzazione corretto è obbligatorio ', settings);
		return; 
	}
	
	
	
	//se presente esito; Data esito regolarizzazione > Data Notifica; 
	if(esitoReg !=""){
		var check=checkDate(dataNotificaverbaleDef,dataEsitoReg);
		
		if(!check){
		
			var settings = {
					theme: 'teal',
					sticky: false,
					horizontalEdge: 'top',
					verticalEdge: 'right',
					life: 3000
			};

			$.notific8('zindex', 11500);
			$.notific8('Data esito regolarizzazione fascicolo deve essere di data .. ', settings);
			return; 
		}
	}
	//Data comunicazione disconoscimento RDL > Data esito regolarizzazione;
	if((dataInvioComDisc !="" && dataEsitoReg !="") ){
		var check=checkDate(dataEsitoReg,dataInvioComDisc);
		
		if(!check){
		
			var settings = {
					theme: 'teal',
					sticky: false,
					horizontalEdge: 'top',
					verticalEdge: 'right',
					life: 3000
			};

			$.notific8('zindex', 11500);
			$.notific8('Data comunicazione disconoscimento RDL deve essere maggiore di data  esito regolarizzazione ', settings);
			return; 
		}
	}
	
	// Data trasmissione UL13 al legale > Data esito regolarizzazione;
	if((dataTrasmissione !="" && dataEsitoReg !="") ){
		var check=checkDate(dataEsitoReg,dataTrasmissione);
		
		if(!check){
		
			var settings = {
					theme: 'teal',
					sticky: false,
					horizontalEdge: 'top',
					verticalEdge: 'right',
					life: 3000
			};

			$.notific8('zindex', 11500);
			$.notific8('Data trasmissione UL13 al legale deve essere maggiore di data  esito regolarizzazione ', settings);
			return; 
		}
	}
	
	// Data udienza Verifica stato passivo > Data trasmissione UL13 al legale;
	if((dataUdVerStPass !="" && dataTrasmissione !="") ){
		var check=checkDate(dataTrasmissione,dataUdVerStPass);
		
		if(!check){
		
			var settings = {
					theme: 'teal',
					sticky: false,
					horizontalEdge: 'top',
					verticalEdge: 'right',
					life: 3000
			};

			$.notific8('zindex', 11500);
			$.notific8('Data udienza Verifica stato passivo deve essere maggiore di data trasmissione UL13 al legale ', settings);
			return; 
		}
	}
	//Data esito regolarizzazione > Data Notifica; 
	if((dataNotificaverbaleDef !="" && dataEsitoReg !="") ){
		var check=checkDate(dataNotificaverbaleDef,dataEsitoReg);
		
		if(!check){
		
			var settings = {
					theme: 'teal',
					sticky: false,
					horizontalEdge: 'top',
					verticalEdge: 'right',
					life: 3000
			};

			$.notific8('zindex', 11500);
			$.notific8('Data esito regolarizzazione deve essere maggiore della data di notifica: '+dataNotificaverbaleDef, settings);
			return; 
		}
	}
	
	
	
	$.ajax( {
		type : 'POST',
		//AuditorsAction.java
		url : '/CruscottoAuditAtpoWebWeb/json/salvaFaseDefinizione',
		data :{ 

		'faseDefinizione.esitoRegolarizzazione' : esitoReg,
		'faseDefinizione.dataEsitoRegolarizzazione' : dataEsitoReg,
		'faseDefinizione.erroreEsito': erroreEsito,
		'faseDefinizione.presenzaObbligati' : preObblSol,
		'faseDefinizione.codiceSospensione': cSospensione,
		'faseDefinizione.modComunDisconosc': modComDisc,
		'faseDefinizione.dataInvioComunDisconosc' : dataInvioComDisc,
		'faseDefinizione.dataTrasmissione' : dataTrasmissione,
		'faseDefinizione.gestContributiAffini' : gestContrAff,
		'faseDefinizione.fallimento' : fallimento,
		//'faseDefinizione.dataFallimento': dataFall,
		'faseDefinizione.creditoPrescritto' : credPrescritto,
		'faseDefinizione.importoCredito': impCredito,
		'faseDefinizione.importoSanzioni': impSanz,
		'faseDefinizione.presenzaFascCartaceo' : presFasc,
		'faseDefinizione.dataConsegnaFascCartaceo' : dataConsFascCart,
		'faseDefinizione.dataUdienzaStatoPassivo' : dataUdVerStPass,
		'faseDefinizione.dataScadContrAppalto' : dataScContApp,
		'faseDefinizione.esitoRegolarizzazioneCorretto' : esRegCorretto,
		'faseDefinizione.dataDecadenzaRuolo' : dataDecadenzaRuolo


	},


	success : function(data) {
		var settings = {
				theme: 'teal',
				sticky: false,
				horizontalEdge: 'top',
				verticalEdge: 'right',
				life: 3000
		};
		Metronic.stopPageLoading();
		$.notific8('zindex', 11500);
		$.notific8('Modifiche salvate con successo ', settings);
		return; 
		
	},error: function(data){
		console.log("error def");
		var settings = {
				theme: 'teal',
				sticky: false,
				horizontalEdge: 'top',
				verticalEdge: 'right',
				life: 3000
		};
		Metronic.stopPageLoading();
		$.notific8('zindex', 11500);
		$.notific8('Errore durante il salvataggio ', settings);
		return; 
	}
	});

}

function salvaNonConformitaVerbale(){

	var idNcv=$('#idNcvAside').val();
	var vcomp=$('#vCompAside').val();
	var rischio=$('#rischioAside').val();
	var vc=$('#valoreCalcolatoAside').val();
	var vr=$('#valoreRealeAside').val();
	var note=$('#noteAside').val();

	$.ajax( {
		type : 'POST',
		//TableIndicatori.java
		url : '/CruscottoAuditAtpoWebWeb/salvaNonConformitaVerbale',
		data :{ 

		'idNCV':idNcv,
		'vComp':vcomp,
		'rischio':rischio,
		'valoreCalcolato':vc,
		'valoreReale':vr,
		'note':note


	},


	success : function(data) {
		Metronic.stopPageLoading();




		return; 


	},error: function(data){
		var settings = {
				theme: 'teal',
				sticky: false,
				horizontalEdge: 'top',
				verticalEdge: 'right',
				life: 3000
		};
		Metronic.stopPageLoading();
		$.notific8('zindex', 11500);
		$.notific8('Errore durante il salvataggio ', settings);
		return; 
	}
	});

}




//inizializza datepicker 
function setDatePicker(inputDate){
	var date=$('#'+inputDate).val();

	$('#'+inputDate).datepicker({
		format: 'dd/mm/yyyy',

		autoclose: true



	}).focus();

}

function abilitaPulsanti(abilita){
	if(abilita == 'Y'){
		$('#salvaDatiFasi').removeAttr('disabled')
		
	}else{
		$('#salvaDatiFasi').attr("disabled", true);
		
	}
}

function riapriPratica(src){
	bootbox.confirm("Vuoi riaprire la pratica?", function(result) {
		if (result){
			// $(src).attr("disabled", "disabled");
			Metronic.startPageLoading();

			// CHIAMO PER RIAPRIRE
			$.ajax( {
				type : 'POST',
				url : '/CruscottoAuditAtpoWebWeb/json/riapriPratica',
				data :{ 	
			},
			success : function(data) {
				Metronic.stopPageLoading();
				if (data.status > 0 ){
					var settings = {
							theme: 'teal',
							sticky: false,
							horizontalEdge: 'top',
							verticalEdge: 'right',
							life: 3000
					};
					$.notific8('zindex', 11500);
					$.notific8(data.message, settings);
					$(src).removeAttr('disabled');
					// RIAPERTURA ANDATA MALE NON MODIFICO I PULSANTI
				}else{
					var settings = {
							theme: 'teal',
							sticky: false,
							horizontalEdge: 'top',
							verticalEdge: 'right',
							life: 3000
					};
					$.notific8('zindex', 11500);
					$.notific8('Pratica riaperta correttamente!', settings);
					// RIAPERTURA ANDATA BENE
					// ApriPratica Disabilitato, LavoraPratica Abilitato , CalcolaIndicatoriPratica Disabilitato
					$('#ApriPratica').attr("disabled", "disabled");
					$('#LavoraPratica').removeAttr('disabled');
					$('#CalcolaIndicatoriPratica').attr("disabled", "disabled");
					$('#statusPratica').val('Aperta');
					// Abilito
					$('#salvaDatiFasi').removeAttr('disabled');
					$('#salvaDatiF').removeAttr('disabled');
					$('#chkPrestEconomica').removeAttr('disabled');
					if($('#chkPrestEconomica').is(":checked")){
						$('#impPrestErogata').removeAttr('disabled');
					}else{
						$('#impPrestErogata').attr("disabled", "disabled");
					}
				}
				return; 
			},
			error: function(xhr, ajaxOptions, thrownError){

				Metronic.stopPageLoading();
				$.notific8('zindex', 11500);
				$.notific8('Errore durante la riapertura della pratica ', settings);
				return; 
			}	
			});

			// enableControlForPraticaA();
			// Metronic.stopPageLoading();
		}
	}); 
}

function calcolaIndicatoriPratica(src,tipoOperazione){

	if ( tipoOperazione == 'LavoraPratica'){
		bootbox.confirm("Vuoi lavorare la pratica?", function(result) {
			if(result ){
				$(src).attr("disabled", "disabled");
				Metronic.startPageLoading();
	
				$.ajax( {
					type : 'POST',
					url : '/CruscottoAuditAtpoWebWeb/json/inLavorazionePratica',
					data :{ 	
				},
				success : function(data) {
					Metronic.stopPageLoading();
					$(src).removeAttr("disabled"); 
					if (data.status > 0 ){
						var settings = {
								theme: 'teal',
								sticky: false,
								horizontalEdge: 'top',
								verticalEdge: 'right',
								life: 3000
						};
						$.notific8('zindex', 11500);
						$.notific8(data.message, settings);
					}else{
						var settings = {
								theme: 'teal',
								sticky: false,
								horizontalEdge: 'top',
								verticalEdge: 'right',
								life: 3000
						};
						$.notific8('zindex', 11500);
						$.notific8('Operazione eseguita correttamente!', settings);
						// ApriPratica Abilitato, LavoraPratica Disabilitato , CalcolaIndicatoriPratica Abilitato
						$('#ApriPratica').removeAttr('disabled');
						$('#LavoraPratica').attr("disabled", "disabled");
						$('#CalcolaIndicatoriPratica').removeAttr('disabled');
						$('#statusPratica').val('In Lavorazione');
						// Disabilito
						$('#salvaDatiFasi').removeAttr('disabled');
						// ABILITO
						$('#salvaDatiF').removeAttr('disabled');
						$('#chkPrestEconomica').removeAttr('disabled');
						if($('#chkPrestEconomica').is(":checked")){
							$('#impPrestErogata').removeAttr('disabled');
						}else{
							$('#impPrestErogata').attr("disabled", "disabled");
						}						
					}
					return; 
				},
				error: function(data){
					Metronic.stopPageLoading();
					$.notific8('zindex', 11500);
					$.notific8('Errore durante il salvataggio ', settings);
					return; 
				}	
				});
			}
		});
	}
	if ( tipoOperazione == 'CalcolaIndicatoriPratica'){
		bootbox.confirm("Vuoi calcolare gli indicatori della pratica? Questa operazione eliminerà gli indicatori calcolati precedentemente!", function(result) {
			if(result ){
				$(src).attr("disabled", "disabled");
				Metronic.startPageLoading();
	
				$.ajax( {
					type : 'POST',
					url : '/CruscottoAuditAtpoWebWeb/json/calcolaIndicatoriPratica',
					data :{ 	
				},
				success : function(data) {
					Metronic.stopPageLoading();
					$(src).removeAttr("disabled"); 
					if (data.status > 0 ){
						var settings = {
								theme: 'teal',
								sticky: false,
								horizontalEdge: 'top',
								verticalEdge: 'right',
								life: 3000
						};
						$.notific8('zindex', 11500);
						$.notific8(data.message, settings);

					}else{
						var settings = {
								theme: 'teal',
								sticky: false,
								horizontalEdge: 'top',
								verticalEdge: 'right',
								life: 3000
						};
						$.notific8('zindex', 11500);
						$.notific8('Indicatori Calcolati correttamente!', settings);
						// ApriPratica Abilitato, LavoraPratica Abilitato , CalcolaIndicatoriPratica Disabilitato
						$('#ApriPratica').removeAttr('disabled');
						$('#LavoraPratica').removeAttr('disabled');
						$('#CalcolaIndicatoriPratica').attr("disabled", "disabled");
						$('#statusPratica').val('Chiusa');
						// Disabilito
						$('#salvaDatiFasi').attr("disabled", "disabled");
						// ABILITO
						$('#salvaDatiF').attr("disabled", "disabled");
						$('#chkPrestEconomica').attr("disabled", "disabled");
						$('#impPrestErogata').attr("disabled", "disabled");
					}
					return; 
				},
				error: function(data){
	
					Metronic.stopPageLoading();
					$.notific8('zindex', 11500);
					$.notific8('Errore durante il salvataggio ', settings);
					return; 
				}	
				});
			}
		});
	}
}