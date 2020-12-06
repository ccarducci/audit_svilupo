function showAsideNewCt(){
	sizeModalCustom();
	$('.modalAddCompT').css("display", "block");
	jQuery('.menu-sx-levelone-facility.add').animate({marginRight:'0'}, 400);
	if(contOne){
		jQuery('.menu-sx-levelone-facility.add').animate({marginRight:'0'}, 400);
		contOne = false;
	}else{
		slideAsideNew('');
		$('.modalAddCompT').css("display", "none");
		contOne = true;
	}
}

function salvaCompTecnico(){

	var descrizione= $("#descrCT").val();
	var codice= $("#codCT").val();
	var dataInizio= $("#dataInizioCT").val();
	var dataFine= $("#dataFineCT").val();
	var autore=$("#autoreCT").val();
	var dataPubblicazione=$("#dataPubblicazioneCT").val();
	var versione=$("#versioneCT").val();
	var tipo= $("#selectTipoCompTec").find('option:selected').attr('value');


	if(descrizione.trim() =="" || codice.trim() =="" || dataInizio.trim() =="" || tipo.trim()=="" ){
		var settings = {
				theme : 'teal',
				sticky : false,
				horizontalEdge : 'top',
				verticalEdge : 'right',
				life : 3000
		};
		$.notific8('zindex', 11500);
		$.notific8('Questi* campi sono obbligatori ', settings);
		return;
	}

	var checkDate=checkDateIFct(dataInizio, dataFine);
	if(!checkDate){
		return;
	}

	$.ajax( {
		type : 'POST',

		url : '/CruscottoAuditAtpoWebWeb/jsonATPO/checkCodiceCompTec',
		data : {
		'codiceCompTecnico':codice,
		'idCompTecnico':-1

	},

	success : function(data) {

		if(data.codEsistente == true){
			var settings = {
					theme : 'teal',
					sticky : false,
					horizontalEdge : 'top',
					verticalEdge : 'right',
					life : 3000
			};
			$.notific8('zindex', 11500);
			$.notific8('Codice già esistente ', settings);
			return;
		}else{
			$.ajax( {
				type : 'POST',

				url : '/CruscottoAuditAtpoWebWeb/jsonATPO/salvaCompTecnico',
				data : {
				'compTecnico.descrizione' : descrizione,
				'compTecnico.codice' : codice,
				'compTecnico.dataInizio' : dataInizio,
				'compTecnico.dataFine' : dataFine,
				'compTecnico.autore':autore,
				'compTecnico.versione':versione,
				'compTecnico.dataPubblicazione':dataPubblicazione,
				'compTecnico.idTipoCompTecnico':tipo

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
				$.notific8('Salvataggio eseguito ', settings);



				$("#descrCT").val("");
				$("#codCT").val("");
				$("#dataInizioCT").val("");
				$("#dataFineCT").val("");
				$("#autoreCT").val("");
				$("#dataPubblicazioneCT").val("");
				$("#versioneCT").val("");
				$("#selectTipoCompTec").val("");


				$('#compTecniciTable').DataTable().ajax.reload();
				slideAside('');
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
		$.notific8('Errore durante controllo del codice ', settings);
		return;
	}
	});


}

function selectAllCT(){
	var checkAll = $("#checkAllCT").prop('checked');
	if (checkAll) {
		$(".chkCompTec").prop("checked", true);
	} else {
		$(".chkCompTec").prop("checked", false);
	}
	return;
}

function confirmEliminaCompTec(){
	var checkedIds = $(".chkCompTec:checked").map(function() {
		return this.value;

	}).toArray();
	// controlla checkbox selezionati
	if(!checkAtLeastOneSelection(checkedIds)){
		return
	}else{
		$('#deleteCtModal').modal();
	}

}

function eliminaCT(){
	var checkedIds = $(".chkCompTec:checked").map(function() {
		return this.value;

	}).toArray();
	var d=[];
	var arrayLength=checkedIds.length;
	for (var i = 0; i < arrayLength; i++) {
		var id= checkedIds[i];
		d.push(id);
	}

	$.ajax( {
		type : 'POST',
		dataType:'json',
		//AuAuditAction.java
		url : '/CruscottoAuditAtpoWebWeb/jsonATPO/eliminaCompTec',
		data :{ 
		'listId' : d.toString()


	},


	success : function(data) {

		$('#compTecniciTable').DataTable().ajax.reload();
		$("#checkAllCT").prop("checked", false);
		$('#deleteCtModal').modal('hide');

	},error: function(data){

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
//VISUALIZZA STORICO COMPONENTI TECNICI
function getStoricoCompTec(){
	document.getElementById('getStoricoCT').onchange = function() {
		if(document.getElementById('getStoricoCT').checked) {
			document.getElementById('checkAllCT').checked=false;
			var table = $('#compTecniciTable').DataTable();
			table.destroy();
			initStoricoCompTecTable();
			document.getElementById('getStoricoCT').checked=true;
		}else{
			document.getElementById('checkAllCT').checked=false;
			var table = $('#compTecniciTable').DataTable();
			table.destroy();
			initCompTecnici();
			document.getElementById('getStoricoCT').checked=false;
		}
	}



}

function modificaCompTec(){
	var checkedIds = $(".chkCompTec:checked").map(function() {
		return this.value;

	}).toArray();
	// controlla checkbox selezionati
	if(!checkSelection(checkedIds)){
		return;
	}else{
		var id= checkedIds[0];

		loadDiv('/CruscottoAuditAtpoWebWeb/modificaCompTecnico?idCompTecnico='+id, 'appView', null, 'initDatePicker' );

	}
	return;
}

function salvaModificaCompTec(){
	var id=$("#upIdCT").val();
	var descrizione= $("#upDescrCT").val();
	var codice= $("#upCodCT").val();
	var dataInizio= $("#upDataInizioCt").val();
	var dataFine= $("#upDataFineCt").val();
	var autore=$("#upAutoreCT").val();
	var dataPubblicazione=$("#upDataPCT").val();
	var versione=$("#upVersioneCT").val();
	var tipo= $("#selectupTipoCompTec").find('option:selected').attr('value');

	if(descrizione.trim() =="" || codice.trim() =="" || dataInizio.trim() =="" || tipo.trim()=="" ){
		var settings = {
				theme : 'teal',
				sticky : false,
				horizontalEdge : 'top',
				verticalEdge : 'right',
				life : 3000
		};
		$.notific8('zindex', 11500);
		$.notific8('Questi* campi sono obbligatori ', settings);
		return;
	}

	var checkDate=checkDateIFct(dataInizio, dataFine);
	if(!checkDate){

		return;
	}

	//controllo codice
	$.ajax( {
		type : 'POST',

		url : '/CruscottoAuditAtpoWebWeb/jsonATPO/checkCodiceCompTec',
		data : {

		'codiceCompTecnico':codice,
		'idCompTecnico':id

	},

	success : function(data) {
		if(data.codEsistente == true){
			var settings = {
					theme : 'teal',
					sticky : false,
					horizontalEdge : 'top',
					verticalEdge : 'right',
					life : 3000
			};
			$.notific8('zindex', 11500);
			$.notific8('Codice già esistente ', settings);
			return;
		}else{
			$.ajax( {
				type : 'POST',

				url : '/CruscottoAuditAtpoWebWeb/jsonATPO/salvaCompTecnico',
				data : {
				'compTecnico.id':id,
				'compTecnico.descrizione' : descrizione,
				'compTecnico.codice' : codice,
				'compTecnico.dataInizio' : dataInizio,
				'compTecnico.dataFine' : dataFine,
				'compTecnico.autore':autore,
				'compTecnico.versione':versione,
				'compTecnico.dataPubblicazione':dataPubblicazione,
				'compTecnico.idTipoCompTecnico':tipo

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
				$.notific8('Modifica eseguita con successo ', settings);


				$('#compTecniciTable').DataTable().ajax.reload();

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
				$.notific8('Errore durante il salvataggio della modifica ', settings);



				return;
			}
			});
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
		$.notific8('Errore durante controllo del codice ', settings);
		return;
	}
	});
}
/*
function showAsideAllegatiCt(){
	sizeModalCustom();
	$('.modalAddAllegatoCompT').css("display", "block");
	jQuery('.menu-sx-levelone-facility.add').animate({marginRight:'0'}, 400);
	if(contOne){
		jQuery('.menu-sx-levelone-facility.add').animate({marginRight:'0'}, 400);
		contOne = false;
	}else{
		slideAsideNew('');
		$('.modalAddAllegatoCompT').css("display", "none");
		contOne = true;
	}
	return;
}*/

function allegaDocCt(){
	var checkedIds = $(".chkAllDom:checked").map(function() {
		return this.value;

	}).toArray();
	// controlla checkbox selezionati
	if(!checkSelection(checkedIds)){
		return;
	}else{
		var id= checkedIds[0];

		$.ajax( {
			type : 'POST',
			dataType:'json',
			//AuAuditAction.java
			url : '/CruscottoAuditAtpoWebWeb/jsonATPO/addAllegatoCompTec',
			data :{ 
			'idFile' : id
		},
		success : function(data) {
			$('#addAllegatiCtTable').DataTable().ajax.reload();
			$('#allegatiCompTecTable').DataTable().ajax.reload();

			var settings = {
					theme: 'teal',
					sticky: false,
					horizontalEdge: 'top',
					verticalEdge: 'right',
					life: 3000
			};
			$.notific8('zindex', 11500);
			$.notific8('Documento allegato correttamente ', settings);
			return; 

		},error: function(data){

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

	}
	return;


}

function showConfirmModalAllCt(){

	var checkedIds = $(".chkDocCt:checked").map(function() {
		return this.value;

	}).toArray();
	// controlla checkbox selezionati
	if(!checkAtLeastOneSelection(checkedIds)){
		return
	}else{
		$('#deleteAllegatiCT').modal();
	}


	return;
}



function eliminaAllegatoCompTec(){
	var checkedIds = $(".chkDocCt:checked").map(function() {
		return this.value;

	}).toArray();
	var d=[];
	var arrayLength=checkedIds.length;
	for (var i = 0; i < arrayLength; i++) {
		var idAssDoc= checkedIds[i].split("-");
		var id=idAssDoc[0];//0 = id associazione doc ct
		d.push(id);
	}

	$.ajax( {
		type : 'POST',
		dataType:'json',
		//AuAuditAction.java
		url : '/CruscottoAuditAtpoWebWeb/jsonATPO/eliminaAllegatoCt',
		data :{ 
		'listId' : d.toString()


	},

	success : function(data) {

		$('#allegatiCompTecTable').DataTable().ajax.reload();
		$('#addAllegatiCtTable').DataTable().ajax.reload();
		$("#checkAllAllCT").prop("checked", false);
		$('#deleteAllegatiCT').modal('hide');
		var settings = {
				theme: 'teal',
				sticky: false,
				horizontalEdge: 'top',
				verticalEdge: 'right',
				life: 3000
		};
		$.notific8('zindex', 11500);
		$.notific8('Allegati cancellati correttamente ', settings);
		return; 

	},error: function(data){

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

function selectAllAllegCT(){
	var checkAll = $("#checkAllAllCT").prop('checked');
	if (checkAll) {
		$(".chkDocCt").prop("checked", true);
	} else {
		$(".chkDocCt").prop("checked", false);
	}
	return;

}

function scaricaAllegatoCompTec(){
	var checkedIds = $(".chkDocCt:checked").map(function() {
		return this.value;
	}).toArray();

	// controlla checkbox selezionati
	if(!checkSelection(checkedIds)){
		return;
	}

	var idAssDoc= checkedIds[0].split("-");
	var idDoc= idAssDoc[1];


	$.ajax( {
		type : 'POST',
		dataType:'json',
		//AuAuditAction.java
		url : '/CruscottoAuditAtpoWebWeb/jsonATPO/downloadDocumento',
		data :{ 
		'idDocumento' : idDoc
	},
	success : function(data) {

		$("input[name='ids']:checkbox").removeAttr('checked');

		var prefix=data.prefix.trim();
		var name=data.nomeDocumento.trim();
		var b64=data.base64File.trim();

		var type=base64MimeType(prefix)


		//es: download("data:text/plain;base64,"+data.base64File, "test.txt");
		download(prefix+b64, name, data.toBlob, type);
		return;


	},error: function(data){
		Metronic.stopPageLoading();
		$("input[name='ids']:checkbox").removeAttr('checked');
		Metronic.stopPageLoading();
		var settings = {
				theme: 'teal',
				sticky: false,
				horizontalEdge: 'top',
				verticalEdge: 'right',
				life: 3000
		};
		$.notific8('zindex', 11500);
		$.notific8('Errore durante il download ', settings);
		return; 
	}
	});
	return;
}

//CONTROLLA CONGRUENZA DATE_____________________________________________________
function checkDateIFct(dataInizio, dataFine){
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

