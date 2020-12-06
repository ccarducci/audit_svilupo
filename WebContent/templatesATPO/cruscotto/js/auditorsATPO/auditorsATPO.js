
//checkbox in modificaAccessiPraticaATPO.jsp , tab: dati fasi
function enablePrestErogata(){

	var checkbox = document.getElementById('chkPrestEconomica');
	if (checkbox.checked == true)
	{
		var settings = {
				theme : 'teal',
				sticky : false,
				horizontalEdge : 'top',
				verticalEdge : 'right',
				life : 6000
		};
		$.notific8('zindex', 11500);
		$.notific8('Inserire la sorte, primo pagamento ed arretrati', settings);
		$("#impPrestErogata").attr('disabled', false);
		return;

	}else{
		$("#impPrestErogata").attr('disabled',true);  
		$("#impPrestErogata").val("");
	}
}

//TAB FASI

function showPage(){
	$('.head-fase').empty();
	var selectBox = document.getElementById("tipo-report-isc");
	
	
	if (selectBox.options[selectBox.selectedIndex] != undefined){
		var selectedValue = selectBox.options[selectBox.selectedIndex].value;
	
		
		if(selectedValue == 'acquisizioneIstanza'){
			loadDiv('/CruscottoAuditAtpoWebWeb/getAcquisizioneIstanza', 'acquisizioneIstanza', null, 'initAcquisizione');
		}
		if(selectedValue == 'autotutelaResGiudizio'){
			loadDiv('/CruscottoAuditAtpoWebWeb/getAutotutelaResInGiudizio', 'autotutelaResGiudizio', null, null);
		}
		if(selectedValue == 'gestioneIstruttoria'){
			loadDiv('/CruscottoAuditAtpoWebWeb/getGestioneIstruttoria', 'gestioneIstruttoria', null, 'initGestioneIstruttoria');
		}
		if(selectedValue == 'peritale'){
			loadDiv('/CruscottoAuditAtpoWebWeb/getPeritale', 'peritale', null, 'initDatePicker');
		}
		if(selectedValue == 'postPeritale'){
			//a seconda del giudizio fase dati può presentare 2 maschere 
			$.ajax( {
				type : 'GET',
				// AuAuditAction.java
				url : '/CruscottoAuditAtpoWebWeb/jsonATPO/getGiudizioFaseDati',
	
	
				success : function(data) {
				var codGiudizio= data.giudiziofaseDati;
				if(codGiudizio == "1" || codGiudizio =="2" || codGiudizio =="3" ){
					loadDiv('/CruscottoAuditAtpoWebWeb/getPostPeritale', 'postPeritale', null, 'initDatePicker');
					return;
				}else if(codGiudizio =="nd"){
					var settings = {
							theme : 'teal',
							sticky : false,
							horizontalEdge : 'top',
							verticalEdge : 'right',
							life : 6000
					};
					$.notific8('zindex', 11500);
					$.notific8('Impossibile mostrare fase post post peritale perchè "giudizio" in spese/prestazioni non è definito', settings);
					return;
				}else if(codGiudizio== "4"){
					
					loadDiv('/CruscottoAuditAtpoWebWeb/getPostPeritaleB', 'postPeritale', null, 'initDatePicker');
				}
	
	
			},
			error : function(data) {
	
	
				var settings = {
						theme : 'teal',
						sticky : false,
						horizontalEdge : 'top',
						verticalEdge : 'right',
						life : 3000
				};
				$.notific8('zindex', 11500);
				$.notific8('Errore durante la verifica della pagina da mostrare ', settings);
				return;
			}
			});
	
	
		}
		if(selectedValue == 'esecuzioneProvvedimenti'){
			//a seconda del giudizio fase dati può presentare 2 maschere 
			$.ajax( {
				type : 'GET',
				// AuAuditAction.java
				url : '/CruscottoAuditAtpoWebWeb/jsonATPO/getGiudizioFaseDatiEsP',
	
	
				success : function(data) {
				var codGiudizio= data.giudiziofaseDati;
				if(codGiudizio == "1" || codGiudizio =="2" || codGiudizio =="3" ){
					loadDiv('/CruscottoAuditAtpoWebWeb/getEsecuzioneProvvedimento', 'esProvvedimenti', null, 'initDatePicker');
					return;
				}else if(codGiudizio =="nd"){
					var settings = {
							theme : 'teal',
							sticky : false,
							horizontalEdge : 'top',
							verticalEdge : 'right',
							life : 6000
					};
					$.notific8('zindex', 11500);
					$.notific8('Impossibile mostrare fase Esecuzione provvedimenti perchè "giudizio" in spese/prestazioni non è definito', settings);
					return;
	
					return;
				}else if(codGiudizio== "4"){
					loadDiv('/CruscottoAuditAtpoWebWeb/getEsecuzioneProvvedimentoB', 'esProvvedimenti', null, 'initDatePicker');
				}
	
	
			},
			error : function(data) {
	
	
				var settings = {
						theme : 'teal',
						sticky : false,
						horizontalEdge : 'top',
						verticalEdge : 'right',
						life : 3000
				};
				$.notific8('zindex', 11500);
				$.notific8('Errore durante la verifica della pagina da mostrare ', settings);
				return;
			}
			});
		}
		if(selectedValue == 'riepilogoFascicolo'){
			loadDiv('/CruscottoAuditAtpoWebWeb/getRiepilogoFascicolo', 'riepFascicolo', null, 'initDocMancanteTable');
	
		}
	}
}

function resetFasi(){

	document.getElementById("tipo-report-isc").selectedIndex=-1;
	showPage();

}

//DATI FASI____________________________________________________________________________________________________________

function salvaDatiFasi(){

	$('#confirm').modal('hide');

	//var impSpeseLegali= $("#impSpeseLegali").val();
	//var impsSepseCTU= $("#impsSepseCTU").val();
	var impPrestErogata= $("#impPrestErogata").val();

	if(document.querySelector('input[name="radio2"]:checked') == null){
		var settings = {
				theme: 'teal',
				sticky: false,
				horizontalEdge: 'top',
				verticalEdge: 'right',
				life: 5000
		};
		$.notific8('zindex', 11500);
		$.notific8('Selezione "Giudizio" obbligatoria', settings);
		return; 
	}else{
		var giudizio= document.querySelector('input[name="radio2"]:checked').value;
	}

	if(document.getElementById('chkPrestEconomica').checked) {
		var chkPrestEconomica="s";
	} else {
		var chkPrestEconomica="n";
	}


	if(impPrestErogata == null || impPrestErogata == ""){
		impPrestErogata= null;
	}else{
		var impPrestEr= checkDecimal(impPrestErogata);
		if(!isNumeric(impPrestEr)) {
			var settings = {
					theme: 'teal',
					sticky: false,
					horizontalEdge: 'top',
					verticalEdge: 'right',
					life: 5000
			};
			$.notific8('zindex', 11500);
			$.notific8('Importo prestazione Erogata non valida', settings);
			return; 
		} 


	}


	$.ajax( {
		type : 'GET',
		// AuAuditAction.java
		url : '/CruscottoAuditAtpoWebWeb/jsonATPO/salvaFaseDatiATPO',
		data : {
		'faseDati.giudizio' : giudizio,
		'faseDati.importoPrestazioneErogata' : impPrestEr,
		'faseDati.prestazioneEconomica':chkPrestEconomica

	},

	success : function(data) {

		var settings = {
				theme : 'teal',
				sticky : false,
				horizontalEdge : 'top',
				verticalEdge : 'right',
				life : 3000
		};
		$.notific8('zindex', 11500);
		$.notific8('Salvataggio eseguito con successo ', settings);

		$('input[name="radio2"]').attr('disabled', 'disabled');

		return;

	},
	error : function(data) {


		var settings = {
				theme : 'teal',
				sticky : false,
				horizontalEdge : 'top',
				verticalEdge : 'right',
				life : 3000
		};
		$.notific8('zindex', 11500);
		$.notific8('Errore durante il salvataggio ', settings);
		return;
	}
	});
}
/*
 * Acquisizione istanza _________________________________________________________________________
 */ 

 function initAcquisizione(){

	 calcolaIntervalloDpDn('true');
	 calcolaIntDnDa('true')	;
	 initDatePicker();

}


function salvaAcquisizioneDati(){
	 
	 
	var dataNotif = $('#dataNotif').val();
	var dataProt = $('#dataProt').val();
	var voceTitolario =  $("#voceTit").find('option:selected').attr('value');
	var protConIMG =  $("#tipo-report-iscP").find('option:selected').attr('value');
	var intTempProt = $('#intTempProt').val();
	var acquSisco = $('#acquSisco').val();
	var intNotifSisco = $('#intNotifSisco').val();

	if(dataProt == null && voceTitolario == ''){
		var settings = {
				theme : 'teal',
				sticky : false,
				horizontalEdge : 'top',
				verticalEdge : 'right',
				life : 3000
		};
		$.notific8('zindex', 11500);
		$.notific8('Voce titolario obbligatorio ', settings);
		return;
	}

	if(dataProt != "" && (voceTitolario == "" || protConIMG == "" )){
		var settings = {
				theme : 'teal',
				sticky : false,
				horizontalEdge : 'top',
				verticalEdge : 'right',
				life : 3000
		};
		$.notific8('zindex', 11500);
		$.notific8('Questi * campi sono obbligatori ', settings);
		return;
	}

	if(!checkDateAtpo(dataNotif, dataProt)){
		return;
	}

	if(!checkDateAtpo(dataNotif,acquSisco)){
		return;
	}

//	calcolaIntervalloDpDn('true');
//	calcolaIntDnDa('true');
//	intTempProt = $('#intTempProt').val();
//	intNotifSisco = $('#intNotifSisco').val();

	$.ajax( {
		type : 'GET',
		// AuAuditAction.java
		url : '/CruscottoAuditAtpoWebWeb/jsonATPO/salvaAcquisizioneIstanza',
		data : {
		'acquisizioneIstanza.dataNotifica' : dataNotif,
		'acquisizioneIstanza.dataProtocollo' : dataProt,
		'acquisizioneIstanza.voceTitolarioErrata' : voceTitolario,
		'acquisizioneIstanza.intervalloNotificaProtocollo' : intTempProt,
		'acquisizioneIstanza.protocolloConImg' : protConIMG,
		'acquisizioneIstanza.dataAcquisizioneSISCO' : acquSisco,
		'acquisizioneIstanza.intervalloNotificaSISCO':intNotifSisco
	},

	success : function(data) {
		var settings = {
				theme : 'teal',
				sticky : false,
				horizontalEdge : 'top',
				verticalEdge : 'right',
				life : 3000
		};
		$.notific8('zindex', 11500);
		$.notific8('Salvataggio eseguito con successo', settings);
		return;

	},
	error : function(data) {
		document.getElementById("info").style.visibility = "hidden";
		var settings = {
				theme : 'teal',
				sticky : false,
				horizontalEdge : 'top',
				verticalEdge : 'right',
				life : 3000
		};
		$.notific8('zindex', 11500);
		$.notific8('Errore durante il salvataggio ', settings);
		return;
	}
	});

}

function checkProtConImg(){
	var date = $('#dataProt').val();
	var protImg =  $("#tipo-report-iscP").find('option:selected').attr('value');
	if(date == "" && protImg != ""){
		$("#tipo-report-iscP").val($(this)[0].initialValue);
		
	}
	return;
}
function checkDataProtocollo(){
	var dataprot = $('#dataProt').val();
	if(dataprot.trim() == ''){
		var settings = {
				theme : 'teal',
				sticky : false,
				horizontalEdge : 'top',
				verticalEdge : 'right',
				life : 3000
		};
		$.notific8('zindex', 11500);
		$.notific8("Data protocollo non presente", settings);
		$("#tipo-report-iscP").val($(this)[0].initialValue);
		return;
	}
}


var intDpDn = 0
function calcolaIntervalloDpDn(ccf){
	var timestamp = Math.floor(Date.now());
	if (timestamp - intDpDn < 300){
		return;
	}
	intDpDn = timestamp;

	var dataNotifica = $('#dataNotif').val().trim();
	var dataProtocollo = $('#dataProt').val().trim();


	if((dataProtocollo != "" && dataProtocollo !=undefined) && (dataNotifica != "" && dataNotifica !=undefined) ){
		document.getElementById("voceTit").disabled = false;
		document.getElementById("tipo-report-iscP").disabled = false;

		var di= dataNotifica.split("/");
		var df= dataProtocollo.split("/");
		var ggDi=di[0];
		var mmDi=di[1];
		var yyyyDi=di[2];
		var ggDf=df[0];
		var mmDf=df[1];
		var yyyyDf=df[2];

		var notifica=false; 
		if(yyyyDf < yyyyDi){
			notifica=true;

		}
		if(yyyyDf == yyyyDi && mmDf < mmDi){
			notifica=true;

		}
		if(yyyyDf == yyyyDi && mmDf == mmDi && ggDf < ggDi){
			notifica=true;

		}
		if(notifica == true){
			document.getElementById("intTempProt").value ="";
			var settings = {
					theme : 'teal',
					sticky : false,
					horizontalEdge : 'top',
					verticalEdge : 'right',
					life : 3000
			};
			$.notific8('zindex', 11500);
			$.notific8('Date protocollo e Data notifica incongruenti', settings);
			return ;
		}

//		var oneDay = 24*60*60*1000; // hours*minutes*seconds*milliseconds
//		var firstDate = new Date(yyyyDi,mmDi,ggDi);
//		var secondDate = new Date(yyyyDf,mmDf,ggDf);

//		var diffDays = Math.round(Math.abs((firstDate.getTime() - secondDate.getTime())/(oneDay)));

//		var b1=new Date(dataProtocollo);
//		var b2=new Date(dataNotifica);

		return $.ajax( {
			type : 'GET',
			// AuAuditAction.java
			url : '/CruscottoAuditAtpoWebWeb/jsonATPO/getBetweenDays',
			data : {
			'd1' : dataNotifica.replace(/\//g,"-"),
			'd2' : dataProtocollo.replace(/\//g,"-"),
			'calcoloConFestivita' : ccf
			
			},
		
			success : function(data) {
				
				document.getElementById("intTempProt").value =data.days;
				data.event={timeStamp: Math.floor(Date.now())};
				
			},
			error : function(data) {
				document.getElementById("info").style.visibility = "hidden";
				var settings = {
						theme : 'teal',
						sticky : false,
						horizontalEdge : 'top',
						verticalEdge : 'right',
						life : 3000
				};
				$.notific8('zindex', 11500);
				$.notific8('Errore nel calcolo dei giorni ', settings);
				document.getElementById("intTempProt").value =0;
				
			}
		});




	}else{
		document.getElementById("intTempProt").value =0;
	}
}

var intDnDa = 0
function calcolaIntDnDa(ccf){
	var timestamp = Math.floor(Date.now());
	if (timestamp - intDnDa < 300){
		return;
	}
	intDnDa = timestamp;


	var dataNotifica = $('#dataNotif').val().trim();
	var dataSisco = $('#acquSisco').val().trim();

	if((dataSisco !="" && dataSisco !=undefined) && (dataNotifica!="" && dataNotifica !=undefined) ){

		var di= dataNotifica.split("/");
		var df= dataSisco.split("/");
		var ggDi=di[0];
		var mmDi=di[1];
		var yyyyDi=di[2];
		var ggDf=df[0];
		var mmDf=df[1];
		var yyyyDf=df[2];

		var notifica=false; 
		if(yyyyDf < yyyyDi){
			notifica=true;

		}
		if(yyyyDf == yyyyDi && mmDf < mmDi){
			notifica=true;

		}
		if(yyyyDf == yyyyDi && mmDf == mmDi && ggDf < ggDi){
			notifica=true;

		}
		if(notifica == true){
			document.getElementById("intNotifSisco").value ="";
			var settings = {
					theme : 'teal',
					sticky : false,
					horizontalEdge : 'top',
					verticalEdge : 'right',
					life : 3000
			};
			$.notific8('zindex', 11500);
			$.notific8('data acquisizione in sisco e Data notifica incongruenti', settings);
			return ;
		}

		return $.ajax( {
			type : 'GET',
			// AuAuditAction.java
			url : '/CruscottoAuditAtpoWebWeb/jsonATPO/getBetweenDays',
			data : {
			'd1' : dataNotifica.replace(/\//g,"-"),
			'd2' : dataSisco.replace(/\//g,"-"),
			'calcoloConFestivita' : ccf
		},
		
		success : function(data) {
			
			document.getElementById("intNotifSisco").value =data.days;
			data.event={timeStamp: Math.floor(Date.now())};
			
		},
		error : function(data) {
			document.getElementById("info").style.visibility = "hidden";
			var settings = {
					theme : 'teal',
					sticky : false,
					horizontalEdge : 'top',
					verticalEdge : 'right',
					life : 3000
			};
			$.notific8('zindex', 11500);
			$.notific8('Errore nel calcolo dei giorni ', settings);
			document.getElementById("intNotifSisco").value =0;
			
		}
		});


	}else{
		document.getElementById("intNotifSisco").value =0;
	}
}
function initCalendarAcquSisco(){
	var date = $('#acquSisco').val();

	$('#acquSisco').datepicker( {
		format : 'dd/mm/yyyy',
		autoclose : true
	}).focus();

}

function initCalendarDataNotifica(){
	var date = $('#dataNotif').val();

	$('#dataNotif').datepicker( {
		format : 'dd/mm/yyyy',
		autoclose : true
	}).focus();


}

function initDatePicker(){

	
	
//	$('.date-picker > input').each(function() {
//		 //console.log(this);
//		  if(!$(this).is('[readonly]')){
//			  $(this).datepicker({
//					rtl: Metronic.isRTL(),
//					orientation: "left",
//					format : 'dd/mm/yyyy',
//					autoclose: true
//				});
//		  }
//	});
	
	$('input.date-picker').each(function() {
		 //console.log(this);
		  if(!$(this).is('[readonly]')){
			  $(this).datepicker({
					rtl: Metronic.isRTL(),
					orientation: "left",
					format : 'dd/mm/yyyy',
					autoclose: true
				});
		  }
	});
	
//	$('.date-picker').datepicker({
//		rtl: Metronic.isRTL(),
//		orientation: "left",
//		format : 'dd/mm/yyyy',
//		autoclose: true
//	});
	
	if ( $('#tipo-report-isc').val() === "esecuzioneProvvedimenti"){
		let soggRichPagamento  = $("#soggRichPagament");
		if ($( "#condannaPagSpeseLegali" === undefined )){
			let selectedCondannaSpeseLegali = $( "#condannaPagSpeseLegali option:selected" ).val();
			if ( selectedCondannaSpeseLegali.trim() === '2' || selectedCondannaSpeseLegali.trim() === '3'){
				soggRichPagamento.attr('disabled','disabled');
				soggRichPagamento.val('');
			}else{
				soggRichPagamento.removeAttr('disabled');
			}
		}
	}
}


//autotutela resistenza giudizio______________________________________________________________________________

function salvaAutotutelaResistenzaGiudizio(){


	var udienza =  $("#terminiPrimaUdienza").find('option:selected').attr('value');
	var parere =  $("#parereOptions").find('option:selected').attr('value');

	if((parere == "" && udienza == "") || (parere == undefined && udienza == undefined)){
		var settings = {
				theme : 'teal',
				sticky : false,
				horizontalEdge : 'top',
				verticalEdge : 'right',
				life : 3000
		};
		$.notific8('zindex', 11500);
		$.notific8('Tutti i campi sono obbligatori', settings);
		return;
	}


	if((parere != "1" && parere != "3" && udienza == "") || (parere != "1" && parere != "3" && udienza == undefined) || (parere == "" && udienza !="") || (parere == undefined && udienza !=undefined)){
		var settings = {
				theme : 'teal',
				sticky : false,
				horizontalEdge : 'top',
				verticalEdge : 'right',
				life : 3000
		};
		$.notific8('zindex', 11500);
		$.notific8('Tutti i campi sono obbligatori', settings);
		return;
	}


	$.ajax( {
		type : 'GET',

		url : '/CruscottoAuditAtpoWebWeb/jsonATPO/salvaAutotutelaResistenzaGiudizio',
		data : {
		'autotuResGiudizio.terminiPrimaUdienza' : udienza,
		'autotuResGiudizio.parere' : parere

	},

	success : function(data) {

		var settings = {
				theme : 'teal',
				sticky : false,
				horizontalEdge : 'top',
				verticalEdge : 'right',
				life : 3000
		};
		$.notific8('zindex', 11500);
		$.notific8('Salvataggio riuscito con successo', settings);
		return;

	},
	error : function(data) {

		document.getElementById("info").style.visibility = "hidden";
		var settings = {
				theme : 'teal',
				sticky : false,
				horizontalEdge : 'top',
				verticalEdge : 'right',
				life : 3000
		};
		$.notific8('zindex', 11500);
		$.notific8('Errore durante il salvataggio ', settings);
		return;
	}
	});
}

/*
 *  termini prima udienza modificale se Parere  = “si, autotutela” scelta da tendina (tab. T-Tipologiche Tipo “V023”
 */
function checkDefTermini(){
	var eccNc = $("#parereOptions").find('option:selected').attr('value');
	if(eccNc == "2"){
		document.getElementById("terminiPrimaUdienza").disabled = false;

	}else{
		document.getElementById("terminiPrimaUdienza").disabled = true;

		document.getElementById("terminiPrimaUdienza").selectedIndex=-1

	}
}

//GESTIONE ISTRUTTORIA__________________________________________________________________________________________________

function initGestioneIstruttoria(){

	calcolaIntervalloTemporale('false');
	initDatePicker();
}

function salvaGestioneIstruttoria(){


	
	var eccNonRilevabili =  $("#eccNonRilevabili").find('option:selected').attr('value');
	var litispendenza =  $("#litispendenza").find('option:selected').attr('value');
	var decadenza =  $("#decadenza").find('option:selected').attr('value');
	var istanzaATP =  $("#corrispAtp").find('option:selected').attr('value');
	var verificaCorrettezza =  $("#verifica").find('option:selected').attr('value');
	var oggDomanda =  $("#oggDomanda").find('option:selected').attr('value');
	var carenzaInteresse =  $("#carenza").find('option:selected').attr('value');
	var dataCost = $('#dataCostGiud').val();
	var giudTelematica =  $("#costGiudizio").find('option:selected').attr('value');
	var dataUdienza = $('#dataUdienza').val();
	var intervallo = $('#intTempCostInGiud').val();
	var altreEccP =  $("#eccProcess").find('option:selected').attr('value')

	if(dataCost !="" && giudTelematica ==""){
		var settings = {
				theme : 'teal',
				sticky : false,
				horizontalEdge : 'top',
				verticalEdge : 'right',
				life : 5000
		};
		$.notific8('zindex', 11500);
		$.notific8('Costituzione in giudizio telematica obbligatorio perchè è presente la data di costituzione in giudizio', settings);
		return;
	}
	

	if(eccNonRilevabili =="" || (litispendenza=="" || litispendenza == undefined) || (decadenza =="" || decadenza == undefined) || (istanzaATP =="" || istanzaATP == undefined) || 
			(verificaCorrettezza =="" || verificaCorrettezza == undefined)|| (oggDomanda =="" || oggDomanda ==undefined )|| 
			(carenzaInteresse =="" || carenzaInteresse == undefined)|| 
			(dataCost !="" && giudTelematica == "")|| (altreEccP =="" || altreEccP ==undefined)){
		var settings = {
				theme : 'teal',
				sticky : false,
				horizontalEdge : 'top',
				verticalEdge : 'right',
				life : 3000
		};
		$.notific8('zindex', 11500);
		$.notific8('Questi * campi sono obbligatori', settings);
		return;
	}


	//controllo date
	/*
	if(!checkDateAtpo(dataCost, dataUdienza)){
		var settings = {
				theme : 'teal',
				sticky : false,
				horizontalEdge : 'top',
				verticalEdge : 'right',
				life : 3000
		};
		$.notific8('zindex', 11500);
		$.notific8('Date incongruenti', settings);
		return;
	}
	*/
	let checkDate = checkDateAtpoWitOutAlert(dataCost, dataUdienza);
	if( !checkDate ){
		bootbox.confirm({
		    title: "Attenzione!",
		    message:  "Attenzione la Data di Costituzione in Giudizio è maggiore della Data prima udienza, si intende comunque salvare?",
		    buttons: {
		        cancel: {
		            label: '<i class="fa fa-times"></i> No'
		        },
		        confirm: {
		            label: '<i class="fa fa-check"></i> Si'
		        }
		    },
		    callback: function (result) {
		        if(result)return salvaGestioneIstruttoriaAjax(eccNonRilevabili, 
																litispendenza,  
																decadenza,  
																istanzaATP, 
																verificaCorrettezza, 
																oggDomanda, 
																carenzaInteresse, 
																dataCost, 
																giudTelematica, 
																dataUdienza, 
																intervallo, 
																altreEccP);
		    }
		});
	}else{
		return salvaGestioneIstruttoriaAjax(eccNonRilevabili, 
											litispendenza,  
											decadenza,  
											istanzaATP, 
											verificaCorrettezza, 
											oggDomanda, 
											carenzaInteresse, 
											dataCost, 
											giudTelematica, 
											dataUdienza, 
											intervallo, 
											altreEccP);
	}
	return;
}

function salvaGestioneIstruttoriaAjax(eccNonRilevabili, litispendenza,  decadenza,  istanzaATP, verificaCorrettezza, oggDomanda, carenzaInteresse, dataCost, giudTelematica, dataUdienza, intervallo, altreEccP){
	return $.ajax( {
		type : 'GET',
		async: false,
		url : '/CruscottoAuditAtpoWebWeb/jsonATPO/salvaGestioneIstruttoria',
		data : {
		'gestioneIStruttoria.eccezioniNonRilevabili' : eccNonRilevabili,
		'gestioneIStruttoria.litispendenza' : litispendenza,
		'gestioneIStruttoria.decadenza' : decadenza,
		'gestioneIStruttoria.corrispAtpDomAmmInv' : istanzaATP,
		'gestioneIStruttoria.verificaDicEsPagSpese' : verificaCorrettezza,
		'gestioneIStruttoria.indeterminatezzaOggDom' : oggDomanda,
		'gestioneIStruttoria.carenzaInteresseAdAgire' : carenzaInteresse,
		'gestioneIStruttoria.dataCostitGiudizio' : dataCost,
		'gestioneIStruttoria.costGiudTelematica' : giudTelematica,
		'gestioneIStruttoria.dataPrimaUdienza' : dataUdienza,
		'gestioneIStruttoria.intervalloCostGiudUdienza' : intervallo,
		'gestioneIStruttoria.altreEccProcessuali' : altreEccP

	},

	success : function(data) {

		var settings = {
				theme : 'teal',
				sticky : false,
				horizontalEdge : 'top',
				verticalEdge : 'right',
				life : 3000
		};
		$.notific8('zindex', 11500);
		$.notific8('Salvataggio eseguito con successo', settings);
		

	},
	error : function(data) {

		document.getElementById("info").style.visibility = "hidden";
		var settings = {
				theme : 'teal',
				sticky : false,
				horizontalEdge : 'top',
				verticalEdge : 'right',
				life : 3000
		};
		$.notific8('zindex', 11500);
		$.notific8('Errore durante il salvataggio ', settings);
		
	}
	});
}

function checkDataCostituzioneGiudizio(){
	var dataCost = $('#dataCostGiud').val();
	if(dataCost == ''){
		var settings = {
				theme : 'teal',
				sticky : false,
				horizontalEdge : 'top',
				verticalEdge : 'right',
				life : 3000
		};
		$.notific8('zindex', 11500);
		$.notific8('Data di cosituzione in giudizio non presente', settings);
		$("#costGiudizio").val($(this)[0].initialValue);
		return;
	}
}
//se eccezioni non rilevabili=No è possibile modificare i relativi dati
function checkEccNonRilevabili(){
	var eccNc = $("#eccNonRilevabili").find('option:selected').text().trim();
	if(eccNc == "No"){
		document.getElementById("litispendenza").disabled = true;
		document.getElementById("litispendenza").selectedIndex=4;
		document.getElementById("decadenza").disabled = true;
		document.getElementById("decadenza").selectedIndex=4;
		document.getElementById("corrispAtp").disabled = true;
		document.getElementById("corrispAtp").selectedIndex=4;
		document.getElementById("verifica").disabled = true;
		document.getElementById("verifica").selectedIndex=4;
		document.getElementById("oggDomanda").disabled = true;
		document.getElementById("oggDomanda").selectedIndex=4;
		document.getElementById("carenza").disabled = true;
		document.getElementById("carenza").selectedIndex=4;
		document.getElementById("eccProcess").disabled = true;
		document.getElementById("eccProcess").selectedIndex=4;
		document.getElementById("costGiudizio").disabled = true;
		document.getElementById("costGiudizio").selectedIndex=4;
		//document.getElementById("dataCostGiud").value = "";
		//document.getElementById("dataCostGiudButton").disabled = true;
		//document.getElementById("dataUdienza").value = "";
		//document.getElementById("dataPUdButton").disabled = true;
		//document.getElementById("intTempCostInGiud").value = "";
		document.getElementById("costGiudizio").selectedIndex = 2;





	}else{
		document.getElementById("litispendenza").disabled = false;
		document.getElementById("litispendenza").selectedIndex=-1;
		document.getElementById("decadenza").disabled = false;
		document.getElementById("decadenza").selectedIndex=-1;
		document.getElementById("corrispAtp").disabled = false;
		document.getElementById("corrispAtp").selectedIndex=-1;
		document.getElementById("verifica").disabled = false;
		document.getElementById("verifica").selectedIndex=-1;
		document.getElementById("oggDomanda").disabled = false;
		document.getElementById("oggDomanda").selectedIndex=-1;
		document.getElementById("carenza").disabled = false;
		document.getElementById("carenza").selectedIndex=-1;
		document.getElementById("eccProcess").disabled = false;
		document.getElementById("eccProcess").selectedIndex=-1;
		document.getElementById("costGiudizio").disabled=false;
		document.getElementById("costGiudizio").selectedIndex=-1

	}

}

function checkCostInGiudizio(){
	var date = $('#dataCostGiud').val();
	var giudTelematica =  $("#costGiudizio").find('option:selected').attr('value');
	if(date == "" && giudTelematica != ""){
		$("#costGiudizio").val($(this)[0].initialValue);
	}
}

function initCalendarDataCostGiud(){
	var date = $('#dataCostGiud').val();

	$('#dataCostGiud').datepicker( {
		format : 'dd/mm/yyyy',
		autoclose : true
	}).focus();


}

function initCalendarDataUdienza(){
	var date = $('#dataUdienza').val();

	$('#dataUdienza').datepicker( {
		format : 'dd/mm/yyyy',
		autoclose : true
	}).focus();


}

var cGdu=0;
function calcolaIntervalloTemporale(ccf){
	var timestamp = Math.floor(Date.now());
	if (timestamp - cGdu < 300){
		return;
	}
	cGdu = timestamp;
	var dataCostGiudizio = $('#dataCostGiud').val().trim();
	var dataUdienza = $('#dataUdienza').val().trim();	

	if(dataCostGiudizio != "" && dataUdienza!=""){
		var di= dataCostGiudizio.split("/");
		var df= dataUdienza.split("/");
		var ggDi=di[0];
		var mmDi=di[1];
		var yyyyDi=di[2];
		var ggDf=df[0];
		var mmDf=df[1];
		var yyyyDf=df[2];

		var notifica=false; 
		if(yyyyDf < yyyyDi){
			notifica=true;

		}
		if(yyyyDf == yyyyDi && mmDf < mmDi){
			notifica=true;

		}
		if(yyyyDf == yyyyDi && mmDf == mmDi && ggDf < ggDi){
			notifica=true;

		}
		if(notifica == true){
			document.getElementById("intTempCostInGiud").value ="";
			/*
			var settings = {
					theme : 'teal',
					sticky : false,
					horizontalEdge : 'top',
					verticalEdge : 'right',
					life : 3000
				};
				$.notific8('zindex', 11500);
				$.notific8('Data costituzione in giudizio e Data prima udienza incongruenti', settings);
				return ;
				*/
		}

		return $.ajax( {
			type : 'GET',
			// AuAuditAction.java
			url : '/CruscottoAuditAtpoWebWeb/jsonATPO/getBetweenDays',
			data : {
			'd1' : dataCostGiudizio.replace(/\//g,"-"),
			'd2' : dataUdienza.replace(/\//g,"-"),
			'calcoloConFestivita' : ccf

		},

		success : function(data) {

			//diffDays=data.days;
			document.getElementById("intTempCostInGiud").value =data.days;

		},
		error : function(data) {

			var settings = {
					theme : 'teal',
					sticky : false,
					horizontalEdge : 'top',
					verticalEdge : 'right',
					life : 3000
			};
			$.notific8('zindex', 11500);
			$.notific8('Errore nel calcolo dei giorni ', settings);
			document.getElementById("intTempCostInGiud").value =0;

		}
		});

	}else{
		document.getElementById("intTempCostInGiud").value =0;
	}
}



//PERITALE _______________________________________________________________________________________________
function salvaPeritale(){

	var dataComInizioOpPer = $('#dataComInizioOpPer').val();
	var dataBozza = $('#dataBozza').val();
	var dataProtBozza = $('#dataProtBozza').val();
	var dataComunicazione = $('#dataComunicazione').val();
	var dataProtCTU = $('#dataProtCTU').val();
	var dataTermDissSisco = $('#dataTermDissSisco').val();
	var dataTermDissDecr = $('#dataTermDissDecr').val();
	var intTempDepCTU = $('#intTempDepCTU').val();

	var recOp =  $("#recOp").find('option:selected').attr('value');
	var presMedDoc =  $("#presMedDoc").find('option:selected').attr('value');
	var medicoINPS =  $("#medicoINPS").find('option:selected').attr('value');
	var presCTPop =  $("#presCTPop").find('option:selected').attr('value');
	var bozzaConImg =  $("#bozzaConImg").find('option:selected').attr('value');
	var parereBozzaCtu =  $("#parereBozzaCtu").find('option:selected').attr('value');
	var ossBozza =  $("#ossBozza").find('option:selected').attr('value');
	var ctuDefImgAtti =  $("#ctuDefImgAtti").find('option:selected').attr('value');
	var parereDissAccettazione =  $("#parereDissAccettazione").find('option:selected').attr('value');
	var ossParDef =  $("#ossParDef").find('option:selected').attr('value');



	if(presCTPop =="" || recOp == "" || presMedDoc == "" || medicoINPS == ""  || bozzaConImg == "" || parereBozzaCtu == "" || ossBozza == "" || ctuDefImgAtti == "" || parereDissAccettazione == "" || ossParDef == "" ){
		var settings = {
				theme : 'teal',
				sticky : false,
				horizontalEdge : 'top',
				verticalEdge : 'right',
				life : 3000
		};
		$.notific8('zindex', 11500);
		$.notific8('Questi campi* sono obbligatori', settings);
		return;
	}



	//controllo date
	if(!checkDateAtpoPeritale(dataComunicazione,dataProtCTU )){
		return;
	}

	$.ajax( {
		type : 'GET',

		url : '/CruscottoAuditAtpoWebWeb/jsonATPO/salvaPeritale',
		data : {
		'peritale.dataComOpPerCTU' : dataComInizioOpPer,
		'peritale.dataArrBozza' : dataBozza,
		'peritale.dataProtBozza' : dataProtBozza,
		'peritale.dataComDepCTUDefCanc' : dataComunicazione,
		'peritale.dataProtCTUDef' : dataProtCTU,
		'peritale.dataTermDissSisco' : dataTermDissSisco,
		'peritale.dataTermDissDecr' : dataTermDissDecr,
		'peritale.intTempComDepCTUtoCTUdef' : intTempDepCTU,
		'peritale.recInfoOpPeritali' : recOp,
		'peritale.assCTUMedicoInps' : medicoINPS,
		'peritale.ctpINPSopPeritali' : presCTPop,
		'peritale.ctuBozzaImgAtti' : bozzaConImg,
		'peritale.parereBozzaCtu' : parereBozzaCtu,
		'peritale.ossBozza' : ossBozza,
		'peritale.ctuDefImgAtti' : ctuDefImgAtti,
		'peritale.parereDissAccetfascitazione' : parereDissAccettazione,
		'peritale.ossParDef' : ossParDef,
		'peritale.presMedicoInpsDoc' : presMedDoc

	},

	success : function(data) {

		var settings = {
				theme : 'teal',
				sticky : false,
				horizontalEdge : 'top',
				verticalEdge : 'right',
				life : 3000
		};
		$.notific8('zindex', 11500);
		$.notific8('Salvataggio eseguito con successo', settings);
		return;

	},
	error : function(data) {

		document.getElementById("info").style.visibility = "hidden";
		var settings = {
				theme : 'teal',
				sticky : false,
				horizontalEdge : 'top',
				verticalEdge : 'right',
				life : 3000
		};
		$.notific8('zindex', 11500);
		$.notific8('Errore durante il salvataggio ', settings);
		return;
	}
	});



}

var dcCdP=0
function calcolaIntTempPeritale(ccf){
	var timestamp = Math.floor(Date.now());
	if (timestamp - dcCdP < 300){
		return;
	}
	dcCdP = timestamp;

	var dataInizio = $('#dataComunicazione').val();
	var dataFine = $('#dataProtCTU').val();

	if(dataFine !="" && dataInizio!=""){
		var di= dataInizio.split("/");
		var df= dataFine.split("/");
		var ggDi=di[0];
		var mmDi=di[1];
		var yyyyDi=di[2];
		var ggDf=df[0];
		var mmDf=df[1];
		var yyyyDf=df[2];

		var notifica=false; 
		if(yyyyDf < yyyyDi){
			notifica=true;

		}
		if(yyyyDf == yyyyDi && mmDf < mmDi){
			notifica=true;

		}
		if(yyyyDf == yyyyDi && mmDf == mmDi && ggDf < ggDi){
			notifica=true;

		}
		if(notifica == true){
			document.getElementById("intTempDepCTU").value ="";
			var settings = {
					theme : 'teal',
					sticky : false,
					horizontalEdge : 'top',
					verticalEdge : 'right',
					life : 3000
			};
			$.notific8('zindex', 11500);
			$.notific8('Date comunicazione deposito CTU def e Data protocollo CTU def incongruenti', settings);
			return ;
		}
		
		return $.ajax( {
			type : 'GET',
			// AuAuditAction.java
			url : '/CruscottoAuditAtpoWebWeb/jsonATPO/getBetweenDays',
			data : {
			'd1' : dataInizio.replace(/\//g,"-"),
			'd2' : dataFine.replace(/\//g,"-"),
			'calcoloConFestivita' : ccf
			
			},
		
			success : function(data) {
				
				
				document.getElementById("intTempDepCTU").value =data.days;
				
			},
			error : function(data) {
				
				var settings = {
						theme : 'teal',
						sticky : false,
						horizontalEdge : 'top',
						verticalEdge : 'right',
						life : 3000
				};
				$.notific8('zindex', 11500);
				$.notific8('Errore nel calcolo dei giorni ', settings);
				document.getElementById("intTempDepCTU").value =0;
				
			}
		});


	}else{
		document.getElementById("intTempDepCTU").value =0;
	}
}
function initDataComInizioOpPer(){
	var date = $('#dataComInizioOpPer').val();
	$('#dataComInizioOpPer').datepicker( {
		format : 'dd/mm/yyyy',
		autoclose : true
	}).focus();
}

function initDataBozza(){
	var date = $('#dataBozza').val();
	$('#dataBozza').datepicker( {
		format : 'dd/mm/yyyy',
		autoclose : true
	}).focus();
}

function initDataProtBozza(){
	var date = $('#dataProtBozza').val();
	$('#dataProtBozza').datepicker( {
		format : 'dd/mm/yyyy',
		autoclose : true
	}).focus();
}

function initDataComunicazione(){
	var date = $('#dataComunicazione').val();
	$('#dataComunicazione').datepicker( {
		format : 'dd/mm/yyyy',
		autoclose : true
	}).focus();
}

function initDataProtCTU(){
	var date = $('#dataProtCTU').val();
	$('#dataProtCTU').datepicker( {
		format : 'dd/mm/yyyy',
		autoclose : true
	}).focus();
}

function initDataTermDissSisco(){
	var date = $('#dataTermDissSisco').val();
	$('#dataTermDissSisco').datepicker( {
		format : 'dd/mm/yyyy',
		autoclose : true
	}).focus();
}

function initDataTermDissDecr(){
	var date = $('#dataTermDissDecr').val();
	$('#dataTermDissDecr').datepicker( {
		format : 'dd/mm/yyyy',
		autoclose : true
	}).focus();
}

//POST PERITALE _________________________________________________________________________________

function salvaPostPeritale(){

	var dataDepositoDecOmologa = $('#dataDepositoDecOmologa').val();
	var dataNotificaDecOmologa = $('#dataNotificaDecOmologa').val();
	var dataProtDecOmologaNotif = $('#dataProtDecOmologaNotif').val();
	var intTempNotifOmgProtOmg = $('#intTempNotifOmgProtOmg').val();
	var codChiusCorretto =  $("#codChiusCorretto").find('option:selected').attr('value');
	var codChiusuraInserito = $("#codChiusuraInserito").find('option:selected').attr('value');
	var speseP = $('#spesePagate').val();
	var speseDecrO = $('#speseDecrOmologa').val();
	var corrispDecrOmgEctuDe = $("#corrispDecrOmgEctuDe").find('option:selected').attr('value');
	var codPagamentoSpeseLegali =  $("#codPagamentoSpeseLegali").find('option:selected').attr('value');
	var codPagamentoSpeseLegaliCorretto =   $("#codPagamentoSpeseLegaliC").find('option:selected').attr('value');
	var recDatiPratica =  $("#recDatiPratica").find('option:selected').attr('value');
	var dataTrasmissDecrLPS =  $('#dataTrasmissDecrLPS').val();
	var omologaAllegata =  $('#omologaAllegata').find('option:selected').attr('value');
	var intTempNotifDecrOmgAdecrLps = $('#intTempNotifDecrOmgAdecrLps').val();

	if(codChiusCorretto == "" || codChiusuraInserito == "" || corrispDecrOmgEctuDe == ""  || codPagamentoSpeseLegali == "" || codPagamentoSpeseLegaliCorretto == "" 
		|| recDatiPratica == "" || omologaAllegata == ""  ){


		var settings = {
				theme : 'teal',
				sticky : false,
				horizontalEdge : 'top',
				verticalEdge : 'right',
				life : 3000
		};
		$.notific8('zindex', 11500);
		$.notific8('Questi campi* sono obbligatori', settings);
		return;
	}

	if(codChiusCorretto == "2" && speseDecrO == "" ){
		var settings = {
				theme : 'teal',
				sticky : false,
				horizontalEdge : 'top',
				verticalEdge : 'right',
				life : 3000
		};
		$.notific8('zindex', 11500);
		$.notific8('Spese decreto omologa obbligatorio perchè il codice chiusura corretto è parzialmente favorevole', settings);
		return;
	}

	if( speseDecrO != "" && speseP === ""){
		var settings = {
				theme : 'teal',
				sticky : false,
				horizontalEdge : 'top',
				verticalEdge : 'right',
				life : 3000
		};
		$.notific8('zindex', 11500);
		$.notific8('Spese legali pagate obbligatorie perchè sono state valorizzate le Spese decreto omologa', settings);
		return;
    }
	
	if(speseDecrO!=""){
		if(!isNumericDecimal(speseDecrO)){
			return; 
		} else{
			var  speseDecrOm=isNumericDecimal(speseDecrO);
		} 
	}

	if(speseP!=""){
		if(!isNumericDecimal(speseP)){
			return; 
		}else{
			var  spesePagate=isNumericDecimal(speseP);
		}
	}

	//controllo date
	if(!checkDateAtpoPeritale(dataNotificaDecOmologa,dataProtDecOmologaNotif )){
		return;
	}


	return $.ajax( {
		type : 'GET',

		url : '/CruscottoAuditAtpoWebWeb/jsonATPO/salvaPostPeritale',
		data : {
		'postPeritale.dataDepositoDecOmologa' : dataDepositoDecOmologa,
		'postPeritale.dataNotificaDecOmologa' : dataNotificaDecOmologa,
		'postPeritale.dataProtDecOmologaNotif' : dataProtDecOmologaNotif,
		'postPeritale.intTempNotifOmgProtOmg' : intTempNotifOmgProtOmg,
		'postPeritale.codChiusuraCorretto' : codChiusCorretto,
		'postPeritale.codChiusuraInserito' : codChiusuraInserito,
		'postPeritale.spesePagate' : spesePagate,
		'postPeritale.speseDecrOmologa' : speseDecrOm,
		'postPeritale.corrispDecrOmgEctuDef' : corrispDecrOmgEctuDe,
		'postPeritale.codPagamentoSpeseLegali' : codPagamentoSpeseLegali,
		'postPeritale.codPagamentoSpeseLegaliCorretto' :codPagamentoSpeseLegaliCorretto ,
		'postPeritale.recDatiPratica' : recDatiPratica,
		'postPeritale.dataTrasmissDecrLPS' : dataTrasmissDecrLPS,
		'postPeritale.omologaAllegata' : omologaAllegata,
		'postPeritale.intTempNotifDecrOmgAdecrLps' :intTempNotifDecrOmgAdecrLps 


	},

	success : function(data) {

		var settings = {
				theme : 'teal',
				sticky : false,
				horizontalEdge : 'top',
				verticalEdge : 'right',
				life : 3000
		};
		$.notific8('zindex', 11500);
		$.notific8('Salvataggio eseguito con successo', settings);
		return;

	},
	error : function(data) {


		var settings = {
				theme : 'teal',
				sticky : false,
				horizontalEdge : 'top',
				verticalEdge : 'right',
				life : 3000
		};
		$.notific8('zindex', 11500);
		$.notific8('Errore durante il salvataggio ', settings);
		return;
	}
	});

}
function initDataDepositoDecOmologa(){
	var date = $('#dataDepositoDecOmologa').val();
	$('#dataDepositoDecOmologa').datepicker( {
		format : 'dd/mm/yyyy',
		autoclose : true
	}).focus();
}

function initDataNotificaDecOmologa(){
	var date = $('#dataNotificaDecOmologa').val();
	$('#dataNotificaDecOmologa').datepicker( {
		format : 'dd/mm/yyyy',
		autoclose : true
	}).focus();
}

function initDataProtDecOmologaNotif(){
	var date = $('#dataProtDecOmologaNotif').val();
	$('#dataProtDecOmologaNotif').datepicker( {
		format : 'dd/mm/yyyy',
		autoclose : true
	}).focus();
}

function initDataTrasmissDecrLPS(){
	var date = $('#dataTrasmissDecrLPS').val();
	$('#dataTrasmissDecrLPS').datepicker( {
		format : 'dd/mm/yyyy',
		autoclose : true
	}).focus();
}

var dOdPdco=0;
function calcolaIntTempNotifOmg(ccf){
	var timestamp = Math.floor(Date.now());

	if (timestamp - dOdPdco < 300){
		return;
	}
	dOdPdco = timestamp;

	var dataInizio = $('#dataNotificaDecOmologa').val();
	var dataFine = $('#dataProtDecOmologaNotif').val();

	if(dataFine !="" && dataInizio!=""){
		var di= dataInizio.split("/");
		var df= dataFine.split("/");
		var ggDi=di[0];
		var mmDi=di[1];
		var yyyyDi=di[2];
		var ggDf=df[0];
		var mmDf=df[1];
		var yyyyDf=df[2];

		var notifica=false; 
		if(yyyyDf < yyyyDi){
			notifica=true;

		}
		if(yyyyDf == yyyyDi && mmDf < mmDi){
			notifica=true;

		}
		if(yyyyDf == yyyyDi && mmDf == mmDi && ggDf < ggDi){
			notifica=true;

		}
		if(notifica == true){
			document.getElementById("intTempNotifOmgProtOmg").value ="";
			var settings = {
					theme : 'teal',
					sticky : false,
					horizontalEdge : 'top',
					verticalEdge : 'right',
					life : 3000
			};
			$.notific8('zindex', 11500);
			$.notific8('Data notifica decreto omologa e data protocollo decr omologa notificato incongruenti', settings);
			return ;

		}
		return $.ajax( {
			type : 'GET',
			// AuAuditAction.java
			url : '/CruscottoAuditAtpoWebWeb/jsonATPO/getBetweenDays',
			data : {
			'd1' : dataInizio.replace(/\//g,"-"),
			'd2' : dataFine.replace(/\//g,"-"),
			'calcoloConFestivita' : ccf
			
			},
		
			success : function(data) {
				
				//diffDays=data.days;
				document.getElementById("intTempNotifOmgProtOmg").value =data.days;
				
			},
			error : function(data) {
				
				var settings = {
						theme : 'teal',
						sticky : false,
						horizontalEdge : 'top',
						verticalEdge : 'right',
						life : 3000
				};
				$.notific8('zindex', 11500);
				$.notific8('Errore nel calcolo dei giorni ', settings);
				document.getElementById("intTempNotifOmgProtOmg").value =0;
				
			}
		});

	}else{
		document.getElementById("intTempNotifOmgProtOmg").value = 0;
	}
}

var dtdLpsDndO=0;
function calcolaIntTempDataTrasmLps(ccf){
	var timestamp = Math.floor(Date.now());
	if (timestamp - dtdLpsDndO < 300){
		return;
	}
	dtdLpsDndO = timestamp;
	var dataFine = $('#dataTrasmissDecrLPS').val();
	var dataInizio1 = $('#dataDepositoDecOmologa').val();
	var dataInizio2 = $('#dataNotificaDecOmologa').val();

	if(dataInizio1 != "" && dataFine !=""){
		var di= dataInizio1.split("/");
		var df= dataFine.split("/");
		var ggDi=di[0];
		var mmDi=di[1];
		var yyyyDi=di[2];
		var ggDf=df[0];
		var mmDf=df[1];
		var yyyyDf=df[2];

		var notifica=false; 
		if(yyyyDf < yyyyDi){
			notifica=true;

		}
		if(yyyyDf == yyyyDi && mmDf < mmDi){
			notifica=true;

		}
		if(yyyyDf == yyyyDi && mmDf == mmDi && ggDf < ggDi){
			notifica=true;

		}
		if(notifica == true){
			document.getElementById("intTempNotifDecrOmgAdecrLps").value ="";
			/*
			var settings = {
					theme : 'teal',
					sticky : false,
					horizontalEdge : 'top',
					verticalEdge : 'right',
					life : 3000
			};
			$.notific8('zindex', 11500);
			$.notific8('Data deposito decreto omologa e Data trasmissione decreto alla LPS incongruenti ', settings); */
			return ;
		}
		return $.ajax( {
			type : 'GET',
			// AuAuditAction.java
			url : '/CruscottoAuditAtpoWebWeb/jsonATPO/getBetweenDays',
			data : {
			'd1' : dataInizio1.replace(/\//g,"-"),
			'd2' : dataFine.replace(/\//g,"-"),
			'calcoloConFestivita' : ccf
			
			},
		
			success : function(data) {
				
				
				document.getElementById("intTempNotifDecrOmgAdecrLps").value =data.days;
				
			},
			error : function(data) {
				
				var settings = {
						theme : 'teal',
						sticky : false,
						horizontalEdge : 'top',
						verticalEdge : 'right',
						life : 3000
				};
				$.notific8('zindex', 11500);
				$.notific8('Errore nel calcolo dei giorni ', settings);
				document.getElementById("intTempNotifDecrOmgAdecrLps").value =0;
				
			}
		});

		//document.getElementById("intTempNotifDecrOmgAdecrLps").value =diffDays
	}

	else if(dataInizio2 != "" && dataFine !=""){
		var di= dataInizio2.split("/");
		var df= dataFine.split("/");
		var ggDi=di[0];
		var mmDi=di[1];
		var yyyyDi=di[2];
		var ggDf=df[0];
		var mmDf=df[1];
		var yyyyDf=df[2];

		var notifica=false; 
		if(yyyyDf < yyyyDi){
			notifica=true;

		}
		if(yyyyDf == yyyyDi && mmDf < mmDi){
			notifica=true;

		}
		if(yyyyDf == yyyyDi && mmDf == mmDi && ggDf < ggDi){
			notifica=true;

		}
		if(notifica == true){
			document.getElementById("intTempNotifDecrOmgAdecrLps").value ="";
			var settings = {
					theme : 'teal',
					sticky : false,
					horizontalEdge : 'top',
					verticalEdge : 'right',
					life : 3000
			};
			$.notific8('zindex', 11500);
			$.notific8('Data deposito decreto omologa e Data notifica decreto omologa incongruenti ', settings);
			return ;

		}
		return $.ajax( {
			type : 'GET',
			// AuAuditAction.java
			url : '/CruscottoAuditAtpoWebWeb/jsonATPO/getBetweenDays',
			data : {
			'd1' : dataInizio2.replace(/\//g,"-"),
			'd2' : dataFine.replace(/\//g,"-"),
			'calcoloConFestivita' : ccf
			
			},
		
			success : function(data) {
				
				
				document.getElementById("intTempNotifDecrOmgAdecrLps").value =data.days;
				
			},
			error : function(data) {
				
				var settings = {
						theme : 'teal',
						sticky : false,
						horizontalEdge : 'top',
						verticalEdge : 'right',
						life : 3000
				};
				$.notific8('zindex', 11500);
				$.notific8('Errore nel calcolo dei giorni ', settings);
				document.getElementById("intTempNotifDecrOmgAdecrLps").value =0;
				
			}
		});


	}else{
		document.getElementById("intTempNotifDecrOmgAdecrLps").value =0;
	}
}

//POST PERITALE B
function salvaPostPeritaleB(){

	var dataDissenso=$('#dataTermD').val();
	var datadepDissenso = $('#dataDepD').val();
	var intTemp = $('#intTempDissens').val();
	var comDepDiss = $('#comunDepDiss').find('option:selected').attr('value');
	var dataDepRic =  $("#dataDepRic").val();
	var dataDefP = $("#dataDefP").val();
	var tipoDiss=$('#tipoDiss').find('option:selected').attr('value');
	let spesePagate = $('#spesePagate').val();
	let codChiusuraCorretto = $('#codChiusuraCorretto').val();
	let codChiusuraInserito = $('#codChiusuraInserito').val();
	
	if(tipoDiss == "" || comDepDiss =="" ){
		var settings = {
				theme : 'teal',
				sticky : false,
				horizontalEdge : 'top',
				verticalEdge : 'right',
				life : 3000
		};
		$.notific8('zindex', 11500);
		$.notific8('Questi * campi sono obbligatori ', settings);
		return ;
	}

	if (spesePagate !== undefined && spesePagate !== ''){
		if(!isNumericDecimal(spesePagate)){
			return;
		}else{
			spesePagate=isNumericDecimal(spesePagate);
			console.log("spesePagate: " + spesePagate);
		}
	}
	
	var dataFine=$("#dataTermD").val();

	if(!checkDateAtpo(datadepDissenso, dataFine)){

		return;
	}

	$.ajax( {
		type : 'GET',

		url : '/CruscottoAuditAtpoWebWeb/jsonATPO/salvaPostPeritaleB',
		data : {
		'postPeritale.dataDepDiss' : datadepDissenso,
		'postPeritale.intTempDepDiss' : intTemp,
		'postPeritale.comDepDissUffLegale' : comDepDiss,
		'postPeritale.dataDepRicPrimoG' : dataDepRic,
		'postPeritale.dataDefPratica' : dataDefP,
		'postPeritale.tipoDissenso' :tipoDiss,
		'dataDissenso':dataDissenso,
		'postPeritale.spesePagate' : spesePagate,
		'codChiusuraCorretto' : codChiusuraCorretto,
		'codChiusuraInserito' : codChiusuraInserito
	},

	success : function(data) {

		var settings = {
				theme : 'teal',
				sticky : false,
				horizontalEdge : 'top',
				verticalEdge : 'right',
				life : 3000
		};
		$.notific8('zindex', 11500);
		$.notific8('Salvataggio eseguito con successo', settings);
		return;

	},
	error : function(data) {


		var settings = {
				theme : 'teal',
				sticky : false,
				horizontalEdge : 'top',
				verticalEdge : 'right',
				life : 3000
		};
		$.notific8('zindex', 11500);
		$.notific8('Errore durante il salvataggio ', settings);
		return;
	}
	});


}
var dtdDd=0;
function calcolaIntTempPerB(ccf){
	var timestamp = Math.floor(Date.now());
	if (timestamp - dtdDd < 300){
		return;
	}
	dtdDd = timestamp;
	var dataFine = $('#dataTermD').val();
	var dataInizio = $('#dataDepD').val();


	if(dataFine !="" && dataInizio!=""){
		var di= dataInizio.split("/");
		var df= dataFine.split("/");
		var ggDi=di[0];
		var mmDi=di[1];
		var yyyyDi=di[2];
		var ggDf=df[0];
		var mmDf=df[1];
		var yyyyDf=df[2];

		var notifica=false; 
		if(yyyyDf < yyyyDi){
			notifica=true;

		}
		if(yyyyDf == yyyyDi && mmDf < mmDi){
			notifica=true;

		}
		if(yyyyDf == yyyyDi && mmDf == mmDi && ggDf < ggDi){
			notifica=true;

		}
		if(notifica == true){
			document.getElementById("intTempDissens").value ="";
			var settings = {
					theme : 'teal',
					sticky : false,
					horizontalEdge : 'top',
					verticalEdge : 'right',
					life : 3000
			};
			$.notific8('zindex', 11500);
			$.notific8('Data deposito dissenso e Data termine dissenso in SISCO incongruenti', settings);

			return ;
		}
		$.ajax( {
			type : 'GET',
			// AuAuditAction.java
			url : '/CruscottoAuditAtpoWebWeb/jsonATPO/getBetweenDays',
			data : {
			'd1' : dataInizio.replace(/\//g,"-"),
			'd2' : dataFine.replace(/\//g,"-"),
			'calcoloConFestivita' : ccf

		},

		success : function(data) {

			//diffDays=data.days;
			document.getElementById("intTempDissens").value =data.days;
			return;
		},
		error : function(data) {

			var settings = {
					theme : 'teal',
					sticky : false,
					horizontalEdge : 'top',
					verticalEdge : 'right',
					life : 3000
			};
			$.notific8('zindex', 11500);
			$.notific8('Errore nel calcolo dei giorni ', settings);
			document.getElementById("intTempDissens").value =0;
			return;
		}
		});

	}else{
		document.getElementById("intTempDissens").value =0;
	}
}
//ESECUZIONE PROVVEDIMENTI _____________________________________________________________________-

function changeCondannaPagSpeseLegali(){
	let soggRichPagamento  = $("#soggRichPagament");
	let selectedCondannaSpeseLegali = $( "#condannaPagSpeseLegali option:selected" ).val().trim();
	if ( selectedCondannaSpeseLegali === '2' || selectedCondannaSpeseLegali === '3'){
		soggRichPagamento.attr('disabled','disabled');
		soggRichPagamento.val('')
	}else{
		soggRichPagamento.removeAttr('disabled');
	}
}

function verifPagCtuEffOnChange(){
	impSpeseCtuPagateDissOnKeyUp();
}

function setObbligatorieta(label,obbligatori){
	if( obbligatori ){
        $('#' + label ).addClass( "fa-asterisk" );
        $('#' + label).addClass( "obbligatorio" ); 
    }else{
        $('#' + label).removeClass( "fa-asterisk" ); 
        $('#' + label).removeClass( "obbligatorio" );
    } 
}

function impSpeseCtuPagateOnKeyUp(){
	let impSpesePagate =  $('#impSpeseCtuPagate').val();
	if( impSpesePagate != ""  && impSpesePagate > 0 ){
		setObbligatorieta('iImpSpeseCtuDovute' , true );
    }else{
    	setObbligatorieta('iImpSpeseCtuDovute' , false );
    } 
}
		
function impSpeseCtuPagateDissOnKeyUp(){
	let impSpesePagate =  $('#impSpeseCtuPagate').val();
	if( $('#verifPagCtuEff').val() === '1' && impSpesePagate != ""  /* && impSpesePagate > 0 */ ){
		setObbligatorieta('iImpSpeseCtuDovute' , true );
    }else{
    	setObbligatorieta('iImpSpeseCtuDovute' , false );
    } 
}

function speseDecrOmologaOnKeyUp(){
	let importo =  $('#speseDecrOmologa').val();
	if( importo != "" ){
		setObbligatorieta('iSpesePagate' , true );
    }else{
    	setObbligatorieta('iSpesePagate' , false );
    } 
}

function impSpeseMensileRataEuroOnKeyUp(){	
	let dataAccesso =  $('#dataAccesso').val(); 
	let importo =  $('#importoRataMensile').val();
	let liqPrestLps1	= $('#liqPrestLps').val(); 
	if( !checkDateAtpoWitOutAlert(liqPrestLps1, dataAccesso) && $('#liqPrestLps').val() != '' &&  importo != ""  /* && impSpesePagate > 0 */ ){
		setObbligatorieta('iImpRataDovuta' , true );
    }else{
    	setObbligatorieta('iImpRataDovuta' , false );
    } 
}

function salvaEsecuzioneProvvedimenti(){


	var presDecrOmgFasc =  $("#presDecrOmgF").find('option:selected').attr("value");
	var dataDecrLiqCtu  = $('#dataDecrLiqCtu').val();	
	var	dataPresaInCaricoDecrOmgLps = $('#dataPresaInCaricoDecrOmgLps').val();  
	var intDepDecrOmgDRecLiquid=$('#ggNotifDecrOmgLiqP').val();
	var liqPrestLps	= $('#liqPrestLps').val(); 
	var importoRataMensile = $('#importoRataMensile').val();
	
	var recDatiLiq	= $('#recDatiLiq').val();		
	var ggNotifDecrOmgLiqPres = $('#ggNotifDecrOmgLiqPres').val();
	var ggTrasmDecrLpsDecrOmglps = $('#ggTrasmDecrLpsDecrOmglps').val();
	var interessiLegaliPagati = $('#interessiLegaliPagati').val();
	var interessiLegaliDovuti = $('#interessiLegaliDovuti').val();
	var dataDecCalcoloIntLegali = $('#dataDecCalcoloIntLegali').val();	
	var dataDecPrestInserita = $('#dataDecPrestInserita').val();	
	var dataCorrDecPrestazione = $('#dataCorrDecPrestazione').val();		
	var prestCorrisp  =  $("#prestCorrisp").find('option:selected').attr("value");
	var importoRataDovuta = $('#importoRataDovuta').val();
	var condannaPagCtuAtpo = $('#condannaPagCtuAtpo').val();
	var dataFattura = $('#dataFattura').val();  
	var dataLiqCtuAtpo = $('#dataLiqCtuAtpo').val();
	var intTempFattElettrpagCtuAtpo = $('#intTempFattElettrpagCtuAtpo').val();
	var antSpeseCtu   =  $("#antSpeseCtu").find('option:selected').attr("value");
	var impSpeseCtuPagate = $('#impSpeseCtuPagate').val();
	var impSpeseCtuDovute = $('#impSpeseCtuDovute').val();

	var datalettInvPagSpeseLegali = $('#datalettInvPagSpeseLegali').val(); 
	var dataLetteraRecuperoSpeseCtu = $('#dataLetteraRecuperoSpeseCtu').val();
	//var condannaPagSpeseLegali = $("#condannaPagSpeseLegali").find('option:selected').attr("value");
	var condannaPagSpeseLegali =  $( "#condannaPagSpeseLegali option:selected" ).val().trim();
	var antSpeseCtu  =  $("#antSpeseCtu").find('option:selected').attr("value");
	var soggRichPagamento  =  $("#soggRichPagament").find('option:selected').attr("value");
	var dataArriviNotula = $('#dataArriviNotula').val();
	var dataPagSpseLegaliAvvCparte = $('#dataPagSpseLegaliAvvCparte').val();
	var intTdepDecromgPagSpeseL = $('#intTdepDecromgPagSpeseL').val();
	var presDecrSentMancPagPrest =  $("#presDecrSentMancPagPre").find('option:selected').attr("value");
	var costoGiudizioMancPagPrest = $('#costoGiudizioMancPagPrest').val();
	var dataLimCalcImpatto = $('#dataLimCalcImp').val();

	let dataAccesso =  $('#dataAccesso').val(); 
	if( liqPrestLps != null &&  !checkDateAtpoWitOutAlert(liqPrestLps, dataAccesso) &&  importoRataMensile != "" &&  importoRataDovuta == '' ){
		let settings2 = {
				theme : 'teal',
				sticky : false,
				horizontalEdge : 'top',
				verticalEdge : 'right',
				life : 3000
		};
		$.notific8('zindex', 11500);
		$.notific8('Se imp. mens. rata Euro e Data liq. pres. da LPS sono valorizzate devi valorizzare anche Imp. Rata. Dov. ( anche 0 )', settings2);
		return;
    }
	
	
	let isSoggRichPagamentoMandatory = 0;
	let selectedCondannaSpeseLegali = $( "#condannaPagSpeseLegali option:selected" ).val().trim();
	if ( selectedCondannaSpeseLegali !== '2' && selectedCondannaSpeseLegali !== '3'
		){
		if (  soggRichPagamento === undefined || soggRichPagamento.trim() === ''  ){
			isSoggRichPagamentoMandatory = 1;
		}
		
	}
	

	
	if(presDecrOmgFasc.trim()=="" || prestCorrisp.trim()=="" || isSoggRichPagamentoMandatory === 1 /*soggRichPagamento.trim()=="" */ || presDecrSentMancPagPrest.trim()=="" || condannaPagCtuAtpo.trim()=="" || antSpeseCtu.trim()=="" || condannaPagSpeseLegali.trim()==""){
		let settings1 = {
				theme : 'teal',
				sticky : false,
				horizontalEdge : 'top',
				verticalEdge : 'right',
				life : 3000
		};
		$.notific8('zindex', 11500);
		$.notific8('Questi* campi sono obbligatori', settings1);
		return;
	}

	let impSpesePagate =  $('#impSpeseCtuPagate').val();
	if( $('#impSpeseCtuPagate').val() != "" && impSpesePagate > 0 ){
        if ($('#impSpeseCtuDovute').val() == null || $('#impSpeseCtuDovute').val() == "" ){
    		let settings2 = {
    				theme : 'teal',
    				sticky : false,
    				horizontalEdge : 'top',
    				verticalEdge : 'right',
    				life : 3000
    		};
    		$.notific8('zindex', 11500);
    		$.notific8('Se l\'imp. spese CTU pagate è valorizzato e\' maggiore di 0 devi valorizzare anche l\'mp. spese CTU dovute.( anche 0 )', settings2);
    		return;
        } 
    }

	//precetto
	var noPrecetto;
	if(document.getElementById('siNoPrecetto').checked) {
		noPrecetto="v";
	}else{
		noPrecetto="f";
	}

	var checkSpeseL;
	if(document.getElementById('speseLegaliPrecetto').checked) {
		checkSpeseL="v";
	}else{
		checkSpeseL="f";
	}

	var dataSpeseL=$('#dataSpeseLegaliP').val();	 
	var dataComSpeseL=$('#dataComunicazionePrecettoSpeseL').val();
	var costoSpeseL= $('#costoSpeseLegaliPrecetto').val();

	var checkSpeseCtu; 
	if(document.getElementById('speseCTUPrecetto').checked) {
		checkSpeseCtu="v";
	}else{
		checkSpeseCtu="f";
	}
	var dataSpeseCtu=$('#dataSpeseCt').val();
	var dataComSpeseCtu=$('#dataComPrecSpeseCtu').val();
	var costoSpeseCtu=$('#costoPrecSpeseCtu').val();


	var checkPrestazione;
	if(document.getElementById('prestazionePrecetto').checked) {
		checkPrestazione="v";
	}else{
		checkPrestazione="f";
	}

	var dataPrestazione=$('#dataP').val(); 
	var dataComPrestazione=$('#dataComunicazionePrecettoPrestazione').val();
	var costoPrestazione= $('#costoPrecettoPrestazione').val();

	//pignoramento
	var siNoPignoramento;
	if(document.getElementById('siNoPignoramento').checked) {
		siNoPignoramento="v";
	}else{
		siNoPignoramento="f";
	}

	var idSpeseLegaliPignoramento;
	if(document.getElementById('idSpeseLegaliPignoramento').checked) {
		idSpeseLegaliPignoramento="v";
	}else{
		idSpeseLegaliPignoramento="f";
	}

	var dataSpeseLPig=$('#dataSpeseLPig').val(); 
	var costoPignSpeseL=$('#costoPignSpeseL').val(); 

	var spesePignCtu;
	if(document.getElementById('spesePignCtu').checked) {
		spesePignCtu="v";
	}else{
		spesePignCtu="f";
	}
	var dataSpeseCtuPig=$('#dataSpeseCtuPig').val(); 

	var costoPignSpeseCtu=$('#costoPignSpeseCtu').val(); 

	var prestazionePign;
	if(document.getElementById('prestazionePign').checked) {
		prestazionePign="v";
	}else{
		prestazionePign="f";
	}
	var dataPrestazionePig=$('#dataPrestazionePig').val(); 
	var costoPignPrestazione=$('#costoPignPrestazione').val(); 

	//esecuzione provvedimenti controlli
	if(importoRataMensile!=""){
		if(!isNumericDecimal(importoRataMensile)){
			return; 
		} else{
			var  importoRataMensile=isNumericDecimal(importoRataMensile);
		} 
	}

	
	if(interessiLegaliPagati!=""){
		if(!isNumericDecimal(interessiLegaliPagati)){
			return; 
		}  else{
			var  interessiLegaliPagati=isNumericDecimal(interessiLegaliPagati);
		} 
	}
	if(interessiLegaliDovuti!=""){
		if(!isNumericDecimal(interessiLegaliDovuti)){
			return; 
		} else{
			var  interessiLegaliDovuti=isNumericDecimal(interessiLegaliDovuti);
		} 
	}
	if(importoRataDovuta!=""){
		if(!isNumericDecimal(importoRataDovuta)){
			return; 
		} else{
			var  importoRataDovuta=isNumericDecimal(importoRataDovuta);
		} 
	}
	if(impSpeseCtuPagate!=""){
		if(!isNumericDecimal(impSpeseCtuPagate)){
			return; 
		}  else{
			var  impSpeseCtuPagate=isNumericDecimal(impSpeseCtuPagate);
		} 
	}
	if(impSpeseCtuDovute!=""){
		if(!isNumericDecimal(impSpeseCtuDovute)){
			return; 
		} else{
			var  impSpeseCtuDovute=isNumericDecimal(impSpeseCtuDovute);
		} 
	}
	if(costoGiudizioMancPagPrest!=""){
		if(!isNumericDecimal(costoGiudizioMancPagPrest)){
			return; 
		}else{
			var  costoGiudizioMancPagPrest=isNumericDecimal(costoGiudizioMancPagPrest);
		} 
	}

	//precetto controlli
	if(costoSpeseL!=""){
		if(!isNumericDecimal(costoSpeseL)){
			return; 
		} else{
			var  costoSpeseL=isNumericDecimal(costoSpeseL);
		} 
	}

	if(costoSpeseCtu !=""){
		if(!isNumericDecimal(costoSpeseCtu)){
			return; 
		} else{
			var  costoSpeseCtu=isNumericDecimal(costoSpeseCtu);
		} 
	}

	if(costoPrestazione !=""){
		if(!isNumericDecimal(costoPrestazione)){
			return; 
		} else{
			var  costoPrestazione=isNumericDecimal(costoPrestazione);
		} 
	}

	//pignoramento controlli
	if(costoPignSpeseL !=""){
		if(!isNumericDecimal(costoPignSpeseL)){
			return; 
		} else{
			var  costoPignSpeseL=isNumericDecimal(costoPignSpeseL);
		} 
	}
	if(costoPignSpeseCtu !=""){
		if(!isNumericDecimal(costoPignSpeseCtu)){
			return; 
		} else{
			var  costoPignSpeseCtu=isNumericDecimal(costoPignSpeseCtu);
		} 
	}
	if(costoPignPrestazione !=""){
		if(!isNumericDecimal(costoPignPrestazione)){
			return; 
		} else{
			var  costoPignPrestazione=isNumericDecimal(costoPignPrestazione);
		} 
	}




	$.ajax( {
		type : 'GET',

		url : '/CruscottoAuditAtpoWebWeb/jsonATPO/salvaEsecuzioneProvvedimenti',
		data : {
		'esecuzioneProvvedimenti.presDecrOmgFasc' : presDecrOmgFasc,
		'esecuzioneProvvedimenti.dataDecrLiqCtu' : dataDecrLiqCtu,
		'esecuzioneProvvedimenti.dataPresaInCaricoDecrOmgLps' : dataPresaInCaricoDecrOmgLps,
		'esecuzioneProvvedimenti.intDepDecrOmgDRecLiquid':intDepDecrOmgDRecLiquid,
		'esecuzioneProvvedimenti.dataLiqPrestLps' : liqPrestLps,
		'esecuzioneProvvedimenti.importoRataMensile' : importoRataMensile,
		'esecuzioneProvvedimenti.recDatiLiq' : recDatiLiq,
		'esecuzioneProvvedimenti.ggNotifDecrOmgLiqPres' : ggNotifDecrOmgLiqPres,
		'esecuzioneProvvedimenti.ggTrasmDecrLpsDecrOmglps' : ggTrasmDecrLpsDecrOmglps,
		'esecuzioneProvvedimenti.interessiLegaliPagati' : interessiLegaliPagati,
		'esecuzioneProvvedimenti.interessiLegaliDovuti' : interessiLegaliDovuti,
		'esecuzioneProvvedimenti.dataDecCalcoloIntLegali' :dataDecCalcoloIntLegali ,
		'esecuzioneProvvedimenti.dataDecPrestInserita' : dataDecPrestInserita,
		'esecuzioneProvvedimenti.dataCorrDecPrestazione' : dataCorrDecPrestazione,
		'esecuzioneProvvedimenti.prestCorrisp' : prestCorrisp,
		'esecuzioneProvvedimenti.importoRataDovuta' : importoRataDovuta,
		'esecuzioneProvvedimenti.condannaPagCtuAtpo' : condannaPagCtuAtpo,
		'esecuzioneProvvedimenti.dataFattura' : dataFattura,
		'esecuzioneProvvedimenti.dataLiqCtuAtpo' : dataLiqCtuAtpo,
		'esecuzioneProvvedimenti.intTempFattElettrpagCtuAtpo' : intTempFattElettrpagCtuAtpo,
		'esecuzioneProvvedimenti.antSpeseCtu' : antSpeseCtu,
		'esecuzioneProvvedimenti.impSpeseCtuPagate' : impSpeseCtuPagate,
		'esecuzioneProvvedimenti.impSpeseCtuDovute' : impSpeseCtuDovute,
		'esecuzioneProvvedimenti.datalettInvPagSpeseLegali' : datalettInvPagSpeseLegali,
		'esecuzioneProvvedimenti.dataLetteraRecuperoSpeseCtu' : dataLetteraRecuperoSpeseCtu,
		'esecuzioneProvvedimenti.condannaPagSpeseLegali' : condannaPagSpeseLegali,
		'esecuzioneProvvedimenti.soggRichPagamento' : soggRichPagamento ,
		'esecuzioneProvvedimenti.dataArriviNotula' : dataArriviNotula,
		'esecuzioneProvvedimenti.dataPagSpseLegaliAvvCparte' : dataPagSpseLegaliAvvCparte,
		'esecuzioneProvvedimenti.intTdepDecromgPagSpeseL' : intTdepDecromgPagSpeseL,
		'esecuzioneProvvedimenti.presDecrSentMancPagPrest' :presDecrSentMancPagPrest,
		'esecuzioneProvvedimenti.costoGiudizioMancPagPrest' :costoGiudizioMancPagPrest,
		'esecuzioneProvvedimenti.noPrecetto': noPrecetto,
		'esecuzioneProvvedimenti.speseLegaliFlagPrec': checkSpeseL,
		'esecuzioneProvvedimenti.dataSpeseLegaliPrec': dataSpeseL,
		'esecuzioneProvvedimenti.dataComPreSl': dataComSpeseL,
		'esecuzioneProvvedimenti.costoPreSl': costoSpeseL,
		'esecuzioneProvvedimenti.speseCtuFlagPrec': checkSpeseCtu,
		'esecuzioneProvvedimenti.dataSpeseCtuPrec': dataSpeseCtu,
		'esecuzioneProvvedimenti.dataComPreSctu': dataComSpeseCtu,
		'esecuzioneProvvedimenti.costoPreSctu': costoSpeseCtu,
		'esecuzioneProvvedimenti.prestazioneFlagPrec': checkPrestazione,
		'esecuzioneProvvedimenti.dataPrestazione': dataPrestazione,
		'esecuzioneProvvedimenti.dataComPrePrest': dataComPrestazione,
		'esecuzioneProvvedimenti.costoPrePrest': costoPrestazione,
		'esecuzioneProvvedimenti.noPignoramento': siNoPignoramento,
		'esecuzioneProvvedimenti.speseLegaliFlagPign': idSpeseLegaliPignoramento,
		'esecuzioneProvvedimenti.dataSpeseLegaliPign': dataSpeseLPig,
		'esecuzioneProvvedimenti.costoPignSl': costoPignSpeseL,
		'esecuzioneProvvedimenti.speseCtuFlagPign': spesePignCtu,
		'esecuzioneProvvedimenti.dataSpeseCtuPign': dataSpeseCtuPig,
		'esecuzioneProvvedimenti.costoPignSctu': costoPignSpeseCtu,
		'esecuzioneProvvedimenti.prestazioneFlagPign': prestazionePign,
		'esecuzioneProvvedimenti.dataPignoramentoPres': dataPrestazionePig,
		'esecuzioneProvvedimenti.costoPignPrest': costoPignPrestazione,
		'esecuzioneProvvedimenti.dataLimiteCalcImpatto':dataLimCalcImpatto,



	},

	success : function(data) {

		console.log("success");
		var settings = {
				theme : 'teal',
				sticky : false,
				horizontalEdge : 'top',
				verticalEdge : 'right',
				life : 3000
		};
		$.notific8('zindex', 11500);
		$.notific8('Salvataggio eseguito con successo', settings);
		return;

	},
	error : function(data) {

		document.getElementById("info").style.visibility = "hidden";
		var settings = {
				theme : 'teal',
				sticky : false,
				horizontalEdge : 'top',
				verticalEdge : 'right',
				life : 3000
		};
		$.notific8('zindex', 11500);
		$.notific8('Errore durante il salvataggio ', settings);
		return;
	}
	});

}


function salvaEsecuzioneProvvedimentiB(){


	var dataDecrLiqCtu =  $("#dataDecrLiqCtu").val();
	var condannaPagCtu  = $('#condannaPagCtuAtpo').find('option:selected').attr("value");
	var	dataFattura = $('#dataFattura').val();  
	var dataLiqCtu=$('#dataLiqCtuAtpo').val();
	var condannaPrimoGrado	= $('#condannaPagCtuPrg').find('option:selected').attr("value");
	var varificaPagCtuEff =  $('#verifPagCtuEff').find('option:selected').attr("value");
	let impSpeseCtuPagate = $("#impSpeseCtuPagate").val();
	let impSpeseCtuDovute = $("#impSpeseCtuDovute").val();
	

	
	if(condannaPagCtu =="" || condannaPrimoGrado =="" || varificaPagCtuEff==""  ){
		var settings = {
				theme : 'teal',
				sticky : false,
				horizontalEdge : 'top',
				verticalEdge : 'right',
				life : 3000
		};
		$.notific8('zindex', 11500);
		$.notific8('Questi * campi sono obbligatori ', settings);
		return;
	}

	/*
	if (impSpeseCtuDovute !== undefined && impSpeseCtuDovute !== ''){
		if(!isNumericDecimal(impSpeseCtuDovute)){
			return;
		}else{
			impSpeseCtuDovute = isNumericDecimal(impSpeseCtuDovute);
			console.log("impSpeseCtuDovute: " + impSpeseCtuDovute);
		}
	}
	*/
	
	if( $('#verifPagCtuEff').val() === '1' && $('#impSpeseCtuPagate').val() != "" /* && impSpeseCtuPagate > 0  */){
        if ($('#impSpeseCtuDovute').val() == null || $('#impSpeseCtuDovute').val() == "" ){
        	let settings1 = {
        			theme : 'teal',
        			sticky : false,
        			horizontalEdge : 'top',
        			verticalEdge : 'right',
        			life : 3000
        	};
    		$.notific8('zindex', 11500);
    		$.notific8('Se "Verifica pagamento CTU già effettuato" ("SI") e l\'importo spese CTU pagate è valorizzato e maggiore di 0 devi valorizzare anche l\'mporto spese CTU dovute.( va bene anche 0 )', settings1);
    		return;
        } 
    }

	if(impSpeseCtuPagate != ""){
		if(!isNumericDecimal(impSpeseCtuPagate)){
			return; 
		} 
	}
	
	if(impSpeseCtuDovute != ""){
		if(!isNumericDecimal(impSpeseCtuDovute)){
			return; 
		} 
	}	
	
	$.ajax( {
		type : 'GET',

		url : '/CruscottoAuditAtpoWebWeb/jsonATPO/salvaEsecuzioneProvvedimenti',
		data : {
		'esecuzioneProvvedimenti.dataDecrLiqCtu' : dataDecrLiqCtu,
		'esecuzioneProvvedimenti.condannaPagCtuAtpo' : condannaPagCtu,
		'esecuzioneProvvedimenti.dataFattura' : dataFattura,
		'esecuzioneProvvedimenti.dataLiqCtuAtpo':dataLiqCtu,
		'esecuzioneProvvedimenti.condannaPagCtu1g' : condannaPrimoGrado,
		'esecuzioneProvvedimenti.verPagCtuEff' : varificaPagCtuEff,
		'esecuzioneProvvedimenti.impSpeseCtuDovute' :  impSpeseCtuDovute,
		'esecuzioneProvvedimenti.impSpeseCtuPagate' :  impSpeseCtuPagate,
	},

	success : function(data) {

		console.log("success");
		var settings = {
				theme : 'teal',
				sticky : false,
				horizontalEdge : 'top',
				verticalEdge : 'right',
				life : 3000
		};
		$.notific8('zindex', 11500);
		$.notific8('Salvataggio eseguito con successo', settings);
		return;

	},
	error : function(data) {

		document.getElementById("info").style.visibility = "hidden";
		var settings = {
				theme : 'teal',
				sticky : false,
				horizontalEdge : 'top',
				verticalEdge : 'right',
				life : 3000
		};
		$.notific8('zindex', 11500);
		$.notific8('Errore durante il salvataggio ', settings);
		return;
	}
	});

}

var dddoRdl=0;
function setIntTempDepDecrRecLiq(ccf){
	if (event.timeStamp - dddoRdl < 300){
		return;
	}
	dddoRdl = event.timeStamp;

	var dataInizio = $('#dataDepDecrO').val();
	var dataFine = $('#recDatiLiq').val();

	if(dataInizio == ""){
		var settings = {
				theme : 'teal',
				sticky : false,
				horizontalEdge : 'top',
				verticalEdge : 'right',
				life : 3000
		};
		$.notific8('zindex', 11500);
		$.notific8('Data deposito decreto omologa non definita. Vedere fase post peritale.', settings);
		document.getElementById("ggNotifDecrOmgLiqP").value =0;
	}


	if(dataFine !="" && dataInizio!=""){
		var di= dataInizio.split("/");
		var df= dataFine.split("/");
		var ggDi=di[0];
		var mmDi=di[1];
		var yyyyDi=di[2];
		var ggDf=df[0];
		var mmDf=df[1];
		var yyyyDf=df[2];

		var notifica=false; 
		if(yyyyDf < yyyyDi){
			notifica=true;

		}
		if(yyyyDf == yyyyDi && mmDf < mmDi){
			notifica=true;

		}
		if(yyyyDf == yyyyDi && mmDf == mmDi && ggDf < ggDi){
			notifica=true;

		}
		if(notifica == true){
			document.getElementById("ggNotifDecrOmgLiqP").value ="";
			var settings = {
					theme : 'teal',
					sticky : false,
					horizontalEdge : 'top',
					verticalEdge : 'right',
					life : 3000
			};
			$.notific8('zindex', 11500);
			$.notific8('Data deposito decreto omologa e data registrazione dati liquidazione incongruenti', settings);
			return ;
		}
		return $.ajax( {
			type : 'GET',
			// AuAuditAction.java
			url : '/CruscottoAuditAtpoWebWeb/jsonATPO/getBetweenDays',
			data : {
			'd1' : dataInizio.replace(/\//g,"-"),
			'd2' : dataFine.replace(/\//g,"-"),
			'calcoloConFestivita' : ccf
			
			},
		
			success : function(data) {
				
				
				document.getElementById("ggNotifDecrOmgLiqP").value =data.days;
				
			},
			error : function(data) {
				
				var settings = {
						theme : 'teal',
						sticky : false,
						horizontalEdge : 'top',
						verticalEdge : 'right',
						life : 3000
				};
				$.notific8('zindex', 11500);
				$.notific8('Errore nel calcolo dei giorni ', settings);
				document.getElementById("ggNotifDecrOmgLiqP").value =0;
				
			}
		});		

	}else{
		document.getElementById("ggNotifDecrOmgLiqP").value =0;
	}
}

var lpldpcdo=0;
function calcolaIntTempNotifDecrALiqPres(ccf){
	var timestamp = Math.floor(Date.now());
	if (timestamp - lpldpcdo < 300){
		return;
	}
	lpldpcdo = timestamp;
	var dataFine = $('#dataPresaInCaricoDecrOmgLps').val();
	var dataInizio = $('#dataTrasmisLps').val();

	if(dataFine !="" && dataInizio!=""){
		var di= dataInizio.split("/");
		var df= dataFine.split("/");
		var ggDi=di[0];
		var mmDi=di[1];
		var yyyyDi=di[2];
		var ggDf=df[0];
		var mmDf=df[1];
		var yyyyDf=df[2];

		var notifica=false; 
		if(yyyyDf < yyyyDi){
			notifica=true;

		}
		if(yyyyDf == yyyyDi && mmDf < mmDi){
			notifica=true;

		}
		if(yyyyDf == yyyyDi && mmDf == mmDi && ggDf < ggDi){
			notifica=true;

		}
		if(notifica == true){
			document.getElementById("ggTrasmDecrLpsDecrOmglps").value ="";
			var settings = {
					theme : 'teal',
					sticky : false,
					horizontalEdge : 'top',
					verticalEdge : 'right',
					life : 3000
			};
			$.notific8('zindex', 11500);
			$.notific8('Data presa in carico decreto omologa da LPS e data liquidazione prestazione incongruenti', settings);
			return ;
		}
		return $.ajax( {
			type : 'GET',
			// AuAuditAction.java
			url : '/CruscottoAuditAtpoWebWeb/jsonATPO/getBetweenDays',
			data : {
			'd1' : dataInizio.replace(/\//g,"-"),
			'd2' : dataFine.replace(/\//g,"-"),
			'calcoloConFestivita' : ccf
			
			},
		
			success : function(data) {
				
				
				document.getElementById("ggTrasmDecrLpsDecrOmglps").value =data.days;
				
			},
			error : function(data) {
				
				var settings = {
						theme : 'teal',
						sticky : false,
						horizontalEdge : 'top',
						verticalEdge : 'right',
						life : 3000
				};
				$.notific8('zindex', 11500);
				$.notific8('Errore nel calcolo dei giorni ', settings);
				document.getElementById("ggTrasmDecrLpsDecrOmglps").value =0;
				return;
			}
		});		




	}else{
		document.getElementById("ggTrasmDecrLpsDecrOmglps").value =0;
	}
}


var dndolplps=0;
function calcolaIntTempTrasmLpsOmologa(ccf){
	
	/* **************  */
	impSpeseMensileRataEuroOnKeyUp();
	/* **************  */
	
	var timestamp = Math.floor(Date.now());
	if (timestamp - dndolplps < 300){
		return;
	}
	dndolplps = timestamp;

	var dataNotifDecrO =$('#dataNotifDecrO').val();
	var dataDepDecrO = $('#dataDepDecrO').val();

	var dataInizio="";
	var dataFine="";
	if(dataNotifDecrO != ""){
		dataInizio = $('#dataNotifDecrO').val();
		dataFine = $('#liqPrestLps').val();
	}else if(dataDepDecrO != ""){
		dataInizio = $('#dataDepDecrO').val();
		dataFine = $('#liqPrestLps').val();

	}/*else{
		var settings = {
				theme : 'teal',
				sticky : false,
				horizontalEdge : 'top',
				verticalEdge : 'right',
				life : 3000
		};
		$.notific8('zindex', 11500);
		$.notific8('Data notifica decreto omologa e data deposito decreto omologa non definite. Vedere fase post peritale', settings);
		document.getElementById("ggNotifDecrOmgLiqPres").value = 0;
	}
	*/

	if(dataFine !="" && dataInizio!=""){
		var di= dataInizio.split("/");
		var df= dataFine.split("/");
		var ggDi=di[0];
		var mmDi=di[1];
		var yyyyDi=di[2];
		var ggDf=df[0];
		var mmDf=df[1];
		var yyyyDf=df[2];

		var notifica=false; 
		if(yyyyDf < yyyyDi){
			notifica=true;

		}
		if(yyyyDf == yyyyDi && mmDf < mmDi){
			notifica=true;

		}
		if(yyyyDf == yyyyDi && mmDf == mmDi && ggDf < ggDi){
			notifica=true;

		}
		if(notifica == true){
			document.getElementById("ggNotifDecrOmgLiqPres").value ="";
			var settings = {
					theme : 'teal',
					sticky : false,
					horizontalEdge : 'top',
					verticalEdge : 'right',
					life : 3000
			};
			$.notific8('zindex', 11500);
			$.notific8('Data trasmissione decreto alla LPS e data presa in carico decr omologa da LPS incongruenti', settings);
			return ;
		}

		return $.ajax( {
			type : 'GET',
			// AuAuditAction.java
			url : '/CruscottoAuditAtpoWebWeb/jsonATPO/getBetweenDays',
			data : {
			'd1' : dataInizio.replace(/\//g,"-"),
			'd2' : dataFine.replace(/\//g,"-"),
			'calcoloConFestivita' : ccf
			
			},
		
			success : function(data) {
				
				document.getElementById("ggNotifDecrOmgLiqPres").value =data.days;
				
			},
			error : function(data) {
				
				var settings = {
						theme : 'teal',
						sticky : false,
						horizontalEdge : 'top',
						verticalEdge : 'right',
						life : 3000
				};
				$.notific8('zindex', 11500);
				$.notific8('Errore nel calcolo dei giorni ', settings);
				document.getElementById("ggNotifDecrOmgLiqPres").value =0;
				
			}
		});		

	}else{
		document.getElementById("ggNotifDecrOmgLiqPres").value =0;
	}
}

var dpslacpDd=0;
function calcolaIntTempAvvCParte(ccf){
	var timestamp = Math.floor(Date.now());
	if (timestamp - dpslacpDd < 300){
		return;
	}
	dpslacpDd = timestamp;
	var dataFine=$('#dataPagSpseLegaliAvvCparte').val();
	var dataInizio = $('#dataDepDecrO').val();

	if(dataInizio == ""){
		var settings = {
				theme : 'teal',
				sticky : false,
				horizontalEdge : 'top',
				verticalEdge : 'right',
				life : 3000
		};
		$.notific8('zindex', 11500);
		$.notific8('Data deposito decreto omologa non definita. Vedere fase post peritale', settings);
		document.getElementById("intTdepDecromgPagSpeseL").value =0;
	}

	if(dataFine != "" && dataInizio!= ""){
		var di= dataInizio.split("/");
		var df= dataFine.split("/");
		var ggDi=di[0];
		var mmDi=di[1];
		var yyyyDi=di[2];
		var ggDf=df[0];
		var mmDf=df[1];
		var yyyyDf=df[2];

		var notifica=false; 
		if(yyyyDf < yyyyDi){
			notifica=true;

		}
		if(yyyyDf == yyyyDi && mmDf < mmDi){
			notifica=true;

		}
		if(yyyyDf == yyyyDi && mmDf == mmDi && ggDf < ggDi){
			notifica=true;

		}
		if(notifica == true){
			document.getElementById("intTdepDecromgPagSpeseL").value ="";
			var settings = {
					theme : 'teal',
					sticky : false,
					horizontalEdge : 'top',
					verticalEdge : 'right',
					life : 3000
			};
			$.notific8('zindex', 11500);
			$.notific8('Data deposito decreto omologa e data pagamento spese legali Avv. c/parte incongruenti', settings);
			return ;
		}
		return $.ajax( {
			type : 'GET',
			// AuAuditAction.java
			url : '/CruscottoAuditAtpoWebWeb/jsonATPO/getBetweenDays',
			data : {
			'd1' : dataInizio.replace(/\//g,"-"),
			'd2' : dataFine.replace(/\//g,"-"),
			'calcoloConFestivita' : ccf
			
			},
		
			success : function(data) {
				
				
				document.getElementById("intTdepDecromgPagSpeseL").value =data.days;
				
			},
			error : function(data) {
				
				var settings = {
						theme : 'teal',
						sticky : false,
						horizontalEdge : 'top',
						verticalEdge : 'right',
						life : 3000
				};
				$.notific8('zindex', 11500);
				$.notific8('Errore nel calcolo dei giorni ', settings);
				document.getElementById("intTdepDecromgPagSpeseL").value =0;
				
			}
		});		


	}else{
		document.getElementById("intTdepDecromgPagSpeseL").value =0;
	}
}


var fedlctu=0;
function calcolaGGdFatturaDLiqCtu(ccf){
	var timestamp = Math.floor(Date.now());
	if (timestamp - fedlctu < 300){
		return;
	}
	fedlctu = timestamp;

	var dataInizio = $('#dataFattura').val();
	var dataFine = $('#dataLiqCtuAtpo').val();


	if(dataFine !="" && dataInizio!=""){
		var di= dataInizio.split("/");
		var df= dataFine.split("/");
		var ggDi=di[0];
		var mmDi=di[1];
		var yyyyDi=di[2];
		var ggDf=df[0];
		var mmDf=df[1];
		var yyyyDf=df[2];

		var notifica=false; 
		if(yyyyDf < yyyyDi){
			notifica=true;

		}
		if(yyyyDf == yyyyDi && mmDf < mmDi){
			notifica=true;

		}
		if(yyyyDf == yyyyDi && mmDf == mmDi && ggDf < ggDi){
			notifica=true;

		}
		if(notifica == true){
			document.getElementById("intTempFattElettrpagCtuAtpo").value ="";
			var settings = {
					theme : 'teal',
					sticky : false,
					horizontalEdge : 'top',
					verticalEdge : 'right',
					life : 3000
			};
			$.notific8('zindex', 11500);
			$.notific8('Data fattura elettronica e data pagamento CTU ATPO incongruenti', settings);

			return ;
		}
		return $.ajax( {
			type : 'GET',
			// AuAuditAction.java
			url : '/CruscottoAuditAtpoWebWeb/jsonATPO/getBetweenDays',
			data : {
			'd1' : dataInizio.replace(/\//g,"-"),
			'd2' : dataFine.replace(/\//g,"-"),
			'calcoloConFestivita' : ccf

		},

		success : function(data) {

			//diffDays=data.days;
			document.getElementById("intTempFattElettrpagCtuAtpo").value =data.days;
			return;
		},
		error : function(data) {

			var settings = {
					theme : 'teal',
					sticky : false,
					horizontalEdge : 'top',
					verticalEdge : 'right',
					life : 3000
			};
			$.notific8('zindex', 11500);
			$.notific8('Errore nel calcolo dei giorni ', settings);
			document.getElementById("intTempFattElettrpagCtuAtpo").value =0;
			return;
		}
		});		


	}else{
		document.getElementById("intTempFattElettrpagCtuAtpo").value =0;
	}
}



function initCalendarEsP(calendar){

	var date = $('#'+calendar).val();

	$('#'+calendar).datepicker( {
		format : 'dd/mm/yyyy',
		autoclose : true
	}).focus();
}

//PRECETTO ____________________________________________________________________________________

//checkbox no disabilita tutto 

function changeStatePrecetto(){
//	document.getElementById('siNoPrecetto').onchange = function() {



		if(document.getElementById('siNoPrecetto').checked) {
			//checkbox
			document.getElementById('speseLegaliPrecetto').disabled = true;
			document.getElementById('speseLegaliPrecetto').checked=false;
			document.getElementById('speseCTUPrecetto').disabled = true;
			document.getElementById('speseCTUPrecetto').checked = false;
			document.getElementById('prestazionePrecetto').disabled = true;
			document.getElementById('prestazionePrecetto').checked = false;

			//spese legali
			document.getElementById('buttonDataComPrecetto').disabled = true;
			document.getElementById("costoSpeseLegaliPrecetto").disabled = true;
			document.getElementById("buttonSpeseL").disabled = true;
			document.getElementById("dataSpeseLegaliP").value ="";
			document.getElementById("dataComunicazionePrecettoSpeseL").value ="";
			document.getElementById("costoSpeseLegaliPrecetto").value ="";

			//spese CTU
			document.getElementById('buttonDataComPrecSpeseCtu').disabled = true;
			document.getElementById("costoPrecSpeseCtu").disabled = true;
			document.getElementById("buttonDataSpeseCtu").disabled = true;
			document.getElementById("costoPrecSpeseCtu").value ="";
			document.getElementById("dataSpeseCt").value ="";
			document.getElementById("dataComPrecSpeseCtu").value ="";

			//prestazione
			document.getElementById('buttonDataComunicazionePrecetto').disabled = true;
			document.getElementById("costoPrecettoPrestazione").disabled = true;
			document.getElementById("buttonDataPrestazione").disabled = true;
			document.getElementById("dataP").value ="";
			document.getElementById("dataComunicazionePrecettoPrestazione").value ="";
			document.getElementById("costoPrecettoPrestazione").value ="";

		}else{
			document.getElementById('speseLegaliPrecetto').disabled = false;
			document.getElementById('speseCTUPrecetto').disabled = false;
			document.getElementById('prestazionePrecetto').disabled = false;

			if(document.getElementById('speseLegaliPrecetto').checked) {
				document.getElementById('buttonDataComPrecetto').disabled = false;
				document.getElementById("costoSpeseLegaliPrecetto").disabled = false;
				document.getElementById("buttonSpeseL").disabled = false;
			}else{
				document.getElementById('buttonDataComPrecetto').disabled = true;
				document.getElementById("costoSpeseLegaliPrecetto").disabled = true;
				document.getElementById("buttonSpeseL").disabled = true;
				document.getElementById("dataSpeseLegaliP").value ="";
				document.getElementById("dataComunicazionePrecettoSpeseL").value ="";
				document.getElementById("costoSpeseLegaliPrecetto").value ="";

			}

			if(document.getElementById('speseCTUPrecetto').checked) {
				document.getElementById('buttonDataComPrecSpeseCtu').disabled = false;
				document.getElementById("costoPrecSpeseCtu").disabled = false;
				document.getElementById("buttonDataSpeseCtu").disabled = false;
			}else{
				document.getElementById('buttonDataComPrecSpeseCtu').disabled = true;
				document.getElementById("costoPrecSpeseCtu").disabled = true;
				document.getElementById("buttonDataSpeseCtu").disabled = true;
				document.getElementById("costoPrecSpeseCtu").value ="";
				document.getElementById("dataSpeseCt").value ="";
				document.getElementById("dataComPrecSpeseCtu").value ="";
			}
			if(document.getElementById('prestazionePrecetto').checked) {
				document.getElementById('buttonDataComunicazionePrecetto').disabled = false;
				document.getElementById("costoPrecettoPrestazione").disabled = false;
				document.getElementById("buttonDataPrestazione").disabled = false;
			}else{
				document.getElementById('buttonDataComunicazionePrecetto').disabled = true;
				document.getElementById("costoPrecettoPrestazione").disabled = true;
				document.getElementById("buttonDataPrestazione").disabled = true;
				document.getElementById("dataP").value ="";
				document.getElementById("dataComunicazionePrecettoPrestazione").value ="";
				document.getElementById("costoPrecettoPrestazione").value ="";
			}
		}

//	};


}

function changeStateSpeseLegali(){
	//document.getElementById('speseLegaliPrecetto').onchange = function() {
		if(document.getElementById('speseLegaliPrecetto').checked) {
			document.getElementById('buttonDataComPrecetto').disabled = false;
			document.getElementById("costoSpeseLegaliPrecetto").disabled = false;
			document.getElementById("buttonSpeseL").disabled = false;
		}else{
			document.getElementById('buttonDataComPrecetto').disabled = true;
			document.getElementById("costoSpeseLegaliPrecetto").disabled = true;
			document.getElementById("buttonSpeseL").disabled = true;
			document.getElementById("dataSpeseLegaliP").value ="";
			document.getElementById("dataComunicazionePrecettoSpeseL").value ="";
			document.getElementById("costoSpeseLegaliPrecetto").value ="";
		}



	//};

}

function changeStateSpeseCtu(){
	//document.getElementById('speseCTUPrecetto').onchange = function() {
		if(document.getElementById('speseCTUPrecetto').checked) {
			document.getElementById('buttonDataComPrecSpeseCtu').disabled = false;
			document.getElementById("costoPrecSpeseCtu").disabled = false;
			document.getElementById("buttonDataSpeseCtu").disabled = false;
		}else{
			document.getElementById('buttonDataComPrecSpeseCtu').disabled = true;
			document.getElementById("costoPrecSpeseCtu").disabled = true;
			document.getElementById("buttonDataSpeseCtu").disabled = true;
			document.getElementById("costoPrecSpeseCtu").value ="";
			document.getElementById("dataSpeseCt").value ="";
			document.getElementById("dataComPrecSpeseCtu").value ="";
		}

	//};
}
function changeStateprestazionePrecetto(){
//	document.getElementById('prestazionePrecetto').onchange = function() {
		if(document.getElementById('prestazionePrecetto').checked) {
			document.getElementById('buttonDataComunicazionePrecetto').disabled = false;
			document.getElementById("costoPrecettoPrestazione").disabled = false;
			document.getElementById("buttonDataPrestazione").disabled = false;
		}else{
			document.getElementById('buttonDataComunicazionePrecetto').disabled = true;
			document.getElementById("costoPrecettoPrestazione").disabled = true;
			document.getElementById("buttonDataPrestazione").disabled = true;
			document.getElementById("dataP").value ="";
			document.getElementById("dataComunicazionePrecettoPrestazione").value ="";
			document.getElementById("costoPrecettoPrestazione").value ="";
		}


	//};
}

//PIGNORAMENTO___________________________________________________________________

function changeStatePignoramento(){
	//document.getElementById('siNoPignoramento').onchange = function() {



		if(document.getElementById('siNoPignoramento').checked) {
			document.getElementById('idSpeseLegaliPignoramento').disabled = true;
			document.getElementById('idSpeseLegaliPignoramento').checked = false;
			document.getElementById('buttonDataSpeseL').disabled = true;
			document.getElementById('costoPignSpeseL').disabled = true;
			document.getElementById("dataSpeseLPig").value ="";
			document.getElementById("costoPignSpeseL").value ="";

			document.getElementById('spesePignCtu').disabled = true;
			document.getElementById('spesePignCtu').checked = false;
			document.getElementById("buttonDataSpesePignCtu").disabled = true;
			document.getElementById("costoPignSpeseCtu").disabled = true;
			document.getElementById("dataSpeseCtuPig").value ="";
			document.getElementById("costoPignSpeseCtu").value ="";

			document.getElementById('prestazionePign').disabled = true;
			document.getElementById('prestazionePign').checked = false;
			document.getElementById("buttonDataPrestazionePign").disabled = true;
			document.getElementById("costoPignPrestazione").disabled = true;
			document.getElementById("dataPrestazionePig").value ="";
			document.getElementById("costoPignPrestazione").value ="";


		}else{
			document.getElementById('spesePignCtu').disabled = false;
			document.getElementById('idSpeseLegaliPignoramento').disabled = false;
			document.getElementById('prestazionePign').disabled = false;

			if(document.getElementById('spesePignCtu').checked) {
				document.getElementById('buttonDataSpesePignCtu').disabled = false;
				document.getElementById("costoPignSpeseCtu").disabled = false;


			}else{
				document.getElementById('buttonDataSpesePignCtu').disabled = true;
				document.getElementById("costoPignSpeseCtu").disabled = true;


			}

			if(document.getElementById('idSpeseLegaliPignoramento').checked) {
				document.getElementById('buttonDataSpeseL').disabled = false;
				document.getElementById("costoPignSpeseL").disabled = false;

			}else{
				document.getElementById('buttonDataSpeseL').disabled = true;
				document.getElementById("costoPignSpeseL").disabled = true;

			}
			if(document.getElementById('prestazionePign').checked) {
				document.getElementById('buttonDataPrestazionePign').disabled = false;
				document.getElementById("costoPignPrestazione").disabled = false;


			}else{
				document.getElementById('buttonDataPrestazionePign').disabled = true;
				document.getElementById("costoPignPrestazione").disabled = true;


			}
		}

//	};


}


function changeStateSpeseLegaliPignoramento(){
//	document.getElementById('idSpeseLegaliPignoramento').onchange = function() {

		if(document.getElementById('idSpeseLegaliPignoramento').checked) {
			document.getElementById('buttonDataSpeseL').disabled = false;
			document.getElementById("costoPignSpeseL").disabled = false;
		}else{

			document.getElementById('buttonDataSpeseL').disabled = true;
			document.getElementById('costoPignSpeseL').disabled = true;
			document.getElementById("dataSpeseLPig").value ="";
			document.getElementById("costoPignSpeseL").value ="";
		}



//	};

}

function changeStateSpeseCtuPignoramento(){
	//document.getElementById('spesePignCtu').onchange = function() {
		if(document.getElementById('spesePignCtu').checked) {
			document.getElementById('buttonDataSpesePignCtu').disabled = false;
			document.getElementById("costoPignSpeseCtu").disabled = false;
		}else{
			document.getElementById('buttonDataSpesePignCtu').disabled = true;
			document.getElementById("costoPignSpeseCtu").disabled = true;
			document.getElementById("dataSpeseCtuPig").value ="";
			document.getElementById("costoPignSpeseCtu").value ="";
		}


//	};
}
function changeStateprestazionePignoramento(){
	//document.getElementById('prestazionePign').onchange = function() {
		if(document.getElementById('prestazionePign').checked) {
			document.getElementById('buttonDataPrestazionePign').disabled = false;
			document.getElementById("costoPignPrestazione").disabled =false;
		}else{
			document.getElementById('buttonDataPrestazionePign').disabled = true;
			document.getElementById("costoPignPrestazione").disabled =true;
			document.getElementById("dataPrestazionePig").value ="";
			document.getElementById("costoPignPrestazione").value ="";
		}

	//};
}

//RIEPILOGO FASCICOLO_______________________________________________________________________________________


function salvaRiepilogoFascicolo(){


	var totalRecords =$("#docMancanteTable").DataTable().page.info().recordsTotal;
	var fascicolo = $('#fascicoloElettronico').find('option:selected').attr("value");


	if(fascicolo == '3' && totalRecords !== 0){
		var settings = {
				theme: 'teal',
				sticky: false,
				horizontalEdge: 'top',
				verticalEdge: 'right',
				life: 5000
		};
		$.notific8('zindex', 11500);
		$.notific8('Prima del salvataggio è necessario eliminare le documentazioni mancanti dalla tabella. ', settings);
		return;
	}

	if( fascicolo != '1' && fascicolo != '2' && fascicolo != '3'){
		let settings2 = {
				theme: 'teal',
				sticky: false,
				horizontalEdge: 'top',
				verticalEdge: 'right',
				life: 5000
		};
		$.notific8('zindex', 11500);
		$.notific8('Prima del salvataggio è necessario selezionare il fasciclo. ', settings2);
		return;
	}

	$.ajax( {
		type : 'GET',

		url : '/CruscottoAuditAtpoWebWeb/jsonATPO/salvaRiepilogoFascicolo',
		data : {
		'fascicolo.fascicoloElettronico' : fascicolo,


	},

	success : function(data) {

		var settings = {
				theme : 'teal',
				sticky : false,
				horizontalEdge : 'top',
				verticalEdge : 'right',
				life : 3000
		};
		$.notific8('zindex', 11500);
		$.notific8('Salvataggio eseguito con successo', settings);
		return;

	},
	error : function(data) {

		document.getElementById("info").style.visibility = "hidden";
		var settings = {
				theme : 'teal',
				sticky : false,
				horizontalEdge : 'top',
				verticalEdge : 'right',
				life : 3000
		};
		$.notific8('zindex', 11500);
		$.notific8('Errore durante il salvataggio ', settings);
		return;
	}
	});


}

function aggiungiDocMancante(){
	var dettDocMancante = $('#dettDocMancante').find('option:selected').attr("value");

	$.ajax( {
		type : 'GET',

		url : '/CruscottoAuditAtpoWebWeb/jsonATPO/aggiungiDocMancante',
		data : {
		'dettaglioDocMancante' : dettDocMancante


	},

	success : function(data) {

		var settings = {
				theme : 'teal',
				sticky : false,
				horizontalEdge : 'top',
				verticalEdge : 'right',
				life : 3000
		};
		$.notific8('zindex', 11500);
		$.notific8('Doc mancante aggiunta', settings);
		return;

	},
	error : function(data) {

		document.getElementById("info").style.visibility = "hidden";
		var settings = {
				theme : 'teal',
				sticky : false,
				horizontalEdge : 'top',
				verticalEdge : 'right',
				life : 3000
		};
		$.notific8('zindex', 11500);
		$.notific8('Errore in fase di aggiornamento', settings);
		return;
	}
	});
}

function eliminaDocMancante(){
	var checkedIds = $(".docManc:checked").map(function() {
		return this.value;

	}).toArray();
	// controlla checkbox selezionati
	if(!checkAtLeastOneSelec(checkedIds)){
		return
	}else{
		$('#deleteDocManc').modal();
	}

}
function eliminaDettDocManc(){
	var checkedIds = $(".docManc:checked").map(function() {
		return this.value;

	}).toArray();

	var d=[];
	var arrayLength=checkedIds.length;
	for (var i = 0; i < arrayLength; i++) {
		var f= checkedIds[i];
		d.push(f.trim());
	}

	$.ajax( {
		type : 'POST',
		dataType:'json',
		//AuAuditAction.java
		url : '/CruscottoAuditAtpoWebWeb/jsonATPO/eliminaDocMancante',
		data :{ 
		'listaDocMancante' : d.toString()


	},


	success : function(data) {



		$('#docMancanteTable').DataTable().ajax.reload();
		$("#checkAll").prop("checked", false);
		$('#deleteDocManc').modal('hide');

	},error: function(data){
		Metronic.stopPageLoading();
		var settings = {
				theme: 'teal',
				sticky: false,
				horizontalEdge: 'top',
				verticalEdge: 'right',
				life: 3000
		};
		$.notific8('zindex', 11500);
		$.notific8('Errore durante la cancellazione ', settings);
		return; 
	}
	});

}

function checkDocMancante(){
	//gives the total number of records in table
	var totalRecords =$("#docMancanteTable").DataTable().page.info().recordsTotal;
	var fascicolo = $('#fascicoloElettronico').find('option:selected').attr("value");


	if(fascicolo == 'R55'  && totalRecords !== 0){
		var settings = {
				theme: 'teal',
				sticky: false,
				horizontalEdge: 'top',
				verticalEdge: 'right',
				life: 5000
		};
		$.notific8('zindex', 11500);
		$.notific8('Prima del salvataggio è necessario eliminare le documentazioni mancanti dalla tabella ', settings);
	}


}
function checkFascicoli(){
	var fascicolo = $('#fascicoloElettronico').find('option:selected').attr("value");


	if(fascicolo == 'R55'){
		var settings = {
				theme: 'teal',
				sticky: false,
				horizontalEdge: 'top',
				verticalEdge: 'right',
				life: 5000
		};
		$.notific8('zindex', 11500);
		$.notific8('Fascicolo completo ', settings);
		return;
	}else{
		slideAsideNew('add');
	}
}

//chiamato da rischi in report-> piano miglioramento
function getEsprRischioPM(){
	var idMRischio =$("#rischiPianoMigl").find('option:selected').attr("id");
	loadDiv('/CruscottoAuditAtpoWebWeb/getTableEsprRischioPM?idRischio='+idMRischio,'esprRischiTable', null, 'initReportEsprRischiTablePM');

}


/*
 * op generali
 */

//controllo date
function checkDateAtpo(dataInizio, dataFine){
	var di= dataInizio.split("/");
	var df= dataFine.split("/");
	var ggDi=di[0];
	var mmDi=di[1];
	var yyyyDi=di[2];
	var ggDf=df[0];
	var mmDf=df[1];
	var yyyyDf=df[2];

	if(yyyyDf < yyyyDi){
		alertDate();
		return;
	}
	if(yyyyDf == yyyyDi && mmDf < mmDi){
		alertDate();
		return;
	}
	if(yyyyDf == yyyyDi && mmDf == mmDi && ggDf < ggDi){
		alertDate();
		return;
	}


	return true;
}

function checkDateAtpoWitOutAlert(dataInizio, dataFine){
	var di= dataInizio.split("/");
	var df= dataFine.split("/");
	var ggDi=di[0];
	var mmDi=di[1];
	var yyyyDi=di[2];
	var ggDf=df[0];
	var mmDf=df[1];
	var yyyyDf=df[2];

	if(yyyyDf < yyyyDi){
		return false;
	}
	if(yyyyDf == yyyyDi && mmDf < mmDi){
		return false;
	}
	if(yyyyDf == yyyyDi && mmDf == mmDi && ggDf < ggDi){
		return false;
	}
	
	return true;
}

function checkDateAtpoPeritale(dataInizio, dataFine){
	var di= dataInizio.split("/");
	var df= dataFine.split("/");
	var ggDi=di[0];
	var mmDi=di[1];
	var yyyyDi=di[2];
	var ggDf=df[0];
	var mmDf=df[1];
	var yyyyDf=df[2];

	if(yyyyDf < yyyyDi){
		alertDate();
		return;
	}
	if(yyyyDf == yyyyDi && mmDf < mmDi){
		alertDate();
		return;
	}
	if(yyyyDf == yyyyDi && mmDf == mmDi && ggDf < ggDi){
		alertDate();
		return;
	}


	return true;
}

function alertDate(){
	var settings = {
			theme: 'teal',
			sticky: false,
			horizontalEdge: 'top',
			verticalEdge: 'right',
			life: 3000
	};
	$.notific8('zindex', 11500);
	$.notific8('Date incongruenti ', settings);
}
//controllo inserimento decimali

function checkDecimal(number){
	 
	if(number.indexOf(",")>= 0){
		var decimal= number.split(",");
		var int=decimal[0];
		var double= decimal[1];
		var number= int+"."+double;
		return number;
	}
	return number;
}


//controllo inserimento numeri
function isNumeric(n) {
	return !isNaN(parseFloat(n)) && isFinite(n);
}


function isNumericDecimal(n){
	if(n.indexOf(",") >=0){
		var decimal= n.split(",");
		var int=decimal[0];
		var double= decimal[1];

		if(!isNumeric(int)){
			var settings = {
					theme: 'teal',
					sticky: false,
					horizontalEdge: 'top',
					verticalEdge: 'right',
					life: 3000
			};
			$.notific8('zindex', 11500);
			$.notific8('importo errato. Scrivere gli importi con la virgola e senza lettere', settings);
			return;
		}
		if(!isNumeric(double)){
			var settings = {
					theme: 'teal',
					sticky: false,
					horizontalEdge: 'top',
					verticalEdge: 'right',
					life: 3000
			};
			$.notific8('zindex', 11500);
			$.notific8('importo errato. Scrivere gli importi con la virgola e senza lettere', settings);
			return;
		}
		
		return n;
	}else if(n.indexOf(".")>=0){

		var decimal= n.split(".");
		var int=decimal[0];
		var double= decimal[1];
		var num= int+","+double;
		if(!isNumeric(int)){
			var settings = {
					theme: 'teal',
					sticky: false,
					horizontalEdge: 'top',
					verticalEdge: 'right',
					life: 3000
			};
			$.notific8('zindex', 11500);
			$.notific8('importo errato. Scrivere gli importi con la virgola e senza lettere', settings);
			return;
		}
		if(!isNumeric(double)){
			var settings = {
					theme: 'teal',
					sticky: false,
					horizontalEdge: 'top',
					verticalEdge: 'right',
					life: 3000
			};
			$.notific8('zindex', 11500);
			$.notific8('importo errato. Scrivere gli importi con la virgola e senza lettere', settings);
			return;
		}

		return num;


	}else if(!isNumeric(n)){
		var settings = {
				theme: 'teal',
				sticky: false,
				horizontalEdge: 'top',
				verticalEdge: 'right',
				life: 3000
		};
		$.notific8('zindex', 11500);
		$.notific8('Importo errato. Scrivere gli importi con la virgola e senza lettere', settings);
		return;
	}
	return n;
}
function selectAll(){


	var checkAll = $("#checkAll").prop('checked');
	if (checkAll) {
		$(".docManc").prop("checked", true);
	} else {
		$(".docManc").prop("checked", false);
	}


}
//controlla quanti checkbox vengono premuti
function checkAtLeastOneSelec(checkBoxSelected){
	if(!checkBoxSelected[0]){
		var settings = {
				theme: 'teal',
				sticky: false,
				horizontalEdge: 'top',
				verticalEdge: 'right',
				life: 5000
		};
		$.notific8('zindex', 11500);
		$.notific8('Nessun elemento selezionato ', settings);

		return false;
	}
	return true;
}

//chiamato da non conformita in report-> piano miglioramento
function getVarComportamentali(){
	var idMNonConf =$("#nonConformitaPianoMigl").find('option:selected').attr("id");
	loadDiv('/CruscottoAuditAtpoWebWeb/getTableVariantiComportamentali?idMNonConf='+idMNonConf,'variantiComportamentaliTable', null, 'initReportVarCompT');

}

function filtraPratiche(){
	var table = $('#praticheATPO').DataTable();
	table.destroy();
	initTableAccessiPraticheATPO();
}

function activeTooltip(){
	$('[data-toggle="m-tooltip"]').tooltip();

}

function filtraFasePratNonConf(){
	var table = $('#nonConfPraticheAtpo').DataTable();
	table.destroy();
	initTableNonConfPraticaAtpo();
}

function filtraFaseAccNonConf(){
	var table = $('#nonConformAccessi').DataTable();
	table.destroy();
	initNonConformitaAccessi();
}

//TAB NOTA
function salvaNota(){
	var nota = $('#notaSessione').val();
	$.ajax( {
		type : 'POST',
		dataType:'json',
		//AuAuditAction.java
		url : '/CruscottoAuditAtpoWebWeb/jsonATPO/salvaNotaSessione',
		data :{ 
		'notaSessione' : nota.toString()


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
		$.notific8('Nota salvata correttamente', settings);
		return; 

	},error: function(data){
		Metronic.stopPageLoading();
		var settings = {
				theme: 'teal',
				sticky: false,
				horizontalEdge: 'top',
				verticalEdge: 'right',
				life: 3000
		};
		$.notific8('zindex', 11500);
		$.notific8('Errore durante il salvataggio ', settings);
		return; 
	}
	});
	return;
}
//SHOW MODAL DELETE
function showConfirmDel(){
	$('#deleteNotaSess').modal();
	return;
}
function eliminaNotaSessione(){
	var nota = "";
	$.ajax( {
		type : 'POST',
		dataType:'json',
		//AuAuditAction.java
		url : '/CruscottoAuditAtpoWebWeb/jsonATPO/eliminaNotaSessione',
		data :{ 
		'notaSessione' : nota.toString()


	},
	success : function(data) {
		document.getElementById('notaSessione').value = '';
		var settings = {
				theme: 'teal',
				sticky: false,
				horizontalEdge: 'top',
				verticalEdge: 'right',
				life: 3000
		};
		$.notific8('zindex', 11500);
		$.notific8('Nota eliminata correttamente', settings);
		
		$('#deleteNotaSess').modal('hide');
		return; 

	},error: function(data){
		Metronic.stopPageLoading();
		var settings = {
				theme: 'teal',
				sticky: false,
				horizontalEdge: 'top',
				verticalEdge: 'right',
				life: 3000
		};
		$.notific8('zindex', 11500);
		$.notific8('Errore durante la cancellazione ', settings);
		return; 
	}
	});
	
}
