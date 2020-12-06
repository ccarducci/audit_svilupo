//ASIDE NEW DOCUMENTI/MEDIA________________________________________________________________
function showAsideNewDom(){
	sizeModalCustom();
	$('.modalDomini').css("display", "block");
	jQuery('.menu-sx-levelone-facility.add').animate({marginRight:'0'}, 400);
	if(contOne){
		jQuery('.menu-sx-levelone-facility.add').animate({marginRight:'0'}, 400);
		contOne = false;
	}else{
		slideAsideNew('');
		$('.modalDomini').css("display", "none");
		contOne = true;
	}
}


//ASIDE MOSTRA ALLEGATI________________________________________________________________

function showAsideAllegati(){
	sizeModalCustom();
	$('.modalAddAllegato').css("display", "block");
	jQuery('.menu-sx-levelone-facility.add').animate({marginRight:'0'}, 400);
	if(contOne){
		jQuery('.menu-sx-levelone-facility.add').animate({marginRight:'0'}, 400);
		contOne = false;
	}else{
		slideAsideNew('');
		$('.modalAddAllegato').css("display", "none");
		contOne = true;
	}
}


function closeAsideDom(){
	
	$("#descrDom").val("");
	$("#codDom").val("");
	$("#dataInizioDom").val("");
	$("#dataFineDom").val("");
	slideAsideNew('');
	
}


//SALVA NUOVO DOMINIO________________________________________________________________
function salvaDominio(){
	var descrizione= $("#descrDom").val();
	var codice= $("#codDom").val();
	var dataInizio= $("#dataInizioDom").val();
	var dataFine= $("#dataFineDom").val();


	if(descrizione.trim() =="" || codice.trim() =="" || dataInizio.trim() ==""  ){
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

	var checkDate=checkDateIFDom(dataInizio, dataFine);
	if(!checkDate){

		return;
	}

	$.ajax( {
		type : 'POST',

		url : '/CruscottoAuditAtpoWebWeb/jsonATPO/checkCodiceDominio',
		data : {
		'codiceDominio':codice,
		'idDominio':-1

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

				url : '/CruscottoAuditAtpoWebWeb/jsonATPO/salvaDominio',
				data : {
				'dominio.descrizione' : descrizione,
				'dominio.codice' : codice,
				'dominio.dataInizio' : dataInizio,
				'dominio.dataFine' : dataFine

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



				$("#descrDom").val("");
				$("#codDom").val("");
				$("#dataInizioDom").val("");
				$("#dataFineDom").val("");

				$('#dominiTable').DataTable().ajax.reload();
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


//MODIFICA DOMINIO________________________________________________________________
function modificaDominio(){
	var checkedIds = $(".chkDom:checked").map(function() {
		return this.value;

	}).toArray();
	// controlla checkbox selezionati
	if(!checkSelection(checkedIds)){
		return;
	}else{
		var id= checkedIds[0];

		loadDiv('/CruscottoAuditAtpoWebWeb/modificaDominio?idDominio='+id, 'appView', null, 'initDatePicker' );

	}
	return;
}

function salvaModificaDominio(){
	var id=$("#upIdDom").val();
	var descrizione= $("#upDescrDom").val();
	var codice= $("#upCodDom").val();
	var dataInizio= $("#upDataInizioDom").val();
	var dataFine= $("#upDataFineDom").val();

	if(descrizione.trim() == "" || codice.trim() == "" || dataInizio.trim() == ""  ){
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

	var checkDate=checkDateIFDom(dataInizio, dataFine);
	if(!checkDate){

		return;
	}

	//controllo codice
	$.ajax( {
		type : 'POST',

		url : '/CruscottoAuditAtpoWebWeb/jsonATPO/checkCodiceDominio',
		data : {

		'codiceDominio':codice,
		'idDominio':id

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

				url : '/CruscottoAuditAtpoWebWeb/jsonATPO/salvaDominio',
				data : {
				'dominio.id':id,
				'dominio.descrizione' : descrizione,
				'dominio.codice' : codice,
				'dominio.dataInizio' : dataInizio,
				'dominio.dataFine' : dataFine

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


				$('#dominiTable').DataTable().ajax.reload();

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
				$.notific8('Errore durante il salvataggio della modifica ', settings);



				return;
			}
			});
		}
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
		$.notific8('Errore durante controllo del codice ', settings);
		return;
	}
	});
}

//ELIMINA DOMINIO________________________________________________________________
function eliminaDominio(){
	var checkedIds = $(".chkDom:checked").map(function() {
		return this.value;

	}).toArray();
	// controlla checkbox selezionati
	if(!checkAtLeastOneSelection(checkedIds)){
		return
	}else{
		$('#deleteDominioModal').modal();
	}
}

function confirmEliminaDominio(){
	var checkedIds = $(".chkDom:checked").map(function() {
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
		url : '/CruscottoAuditAtpoWebWeb/jsonATPO/eliminaDominio',
		data :{ 
		'listId' : d.toString()


	},


	success : function(data) {

		$('#dominiTable').DataTable().ajax.reload();
		$("#checkAllDom").prop("checked", false);
		$('#deleteDominioModal').modal('hide');

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

//GET DOMINI CON DATA FINE< DATA ODIERNA___________________________________
function getStoricoDomini(){
	document.getElementById('getStorico').onchange = function() {
		if(document.getElementById('getStorico').checked) {
			document.getElementById('checkAllDom').checked=false;
			var table = $('#dominiTable').DataTable();
			table.destroy();
			initDominiFiltro();
			document.getElementById('getStorico').checked=true;
		}else{
			document.getElementById('checkAllDom').checked=false;
			var table = $('#dominiTable').DataTable();
			table.destroy();
			initDomini();
			document.getElementById('getStorico').checked=false;
		}
	}

}

//SELEZIONA TUTTI I DOMINI_________________________
function selectAllDom(){
	var checkAll = $("#checkAllDom").prop('checked');
	if (checkAll) {
		$(".chkDom").prop("checked", true);
	} else {
		$(".chkDom").prop("checked", false);
	}


}



/*
 * A L L E G A T I   D O M I N I O_______________________________
 */


//ASIDE TABELLA DOCUMENTI DA ALLEGARE
function aggiungiAllegato(){

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
			url : '/CruscottoAuditAtpoWebWeb/jsonATPO/addAllegatoDominio',
			data :{ 
			'idFile' : id
		},
		success : function(data) {

			$('#addAllegatiDominioTable').DataTable().ajax.reload();
			$('#allagatiDominioTable').DataTable().ajax.reload();
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

//MOSTRA MODALE ELIMINA ALLEGATO__________________________________________________
function showRemoveModal(){
	var checkedIds = $(".chkDocDom:checked").map(function() {
		return this.value;

	}).toArray();
	// controlla checkbox selezionati
	if(!checkAtLeastOneSelection(checkedIds)){
		return
	}else{
		$('#deleteAllegatiModal').modal();
	}

}


//ELIMINA ALLEGATO__________________________________________________
function eliminaAllegato(){
	var checkedIds = $(".chkDocDom:checked").map(function() {
		return this.value;

	}).toArray();
	var d=[];
	var arrayLength=checkedIds.length;
	for (var i = 0; i < arrayLength; i++) {
		var idAssDoc= checkedIds[i].split("-");
		var id=idAssDoc[0];//0 = id associazione doc dominio
		d.push(id);
	}

	$.ajax( {
		type : 'POST',
		dataType:'json',
		//AuAuditAction.java
		url : '/CruscottoAuditAtpoWebWeb/jsonATPO/eliminaAllegato',
		data :{ 
		'listId' : d.toString()


	},


	success : function(data) {

		$('#allagatiDominioTable').DataTable().ajax.reload();
		$('#addAllegatiDominioTable').DataTable().ajax.reload();
		$("#checkAllAll").prop("checked", false);
		$('#deleteAllegatiModal').modal('hide');
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


//SCARICA ALLEGATO_______________________________________________________
function scaricaAllegato(){
	var checkedIds = $(".chkDocDom:checked").map(function() {
		return this.value;
	}).toArray();

	// controlla checkbox selezionati
	if(!checkSelection(checkedIds)){
		return;
	}
	Metronic.startPageLoading();
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
		Metronic.stopPageLoading();
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
	Metronic.stopPageLoading();
}

//CONTROLLA CONGRUENZA DATE_____________________________________________________
function checkDateIFDom(dataInizio, dataFine){
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



//SELEZIONA TUTTI GLI ALLEGATI________________
function selectAllAll(){
	var checkAll = $("#checkAllAll").prop('checked');
	if (checkAll) {
		$(".chkDocDom").prop("checked", true);
	} else {
		$(".chkDocDom").prop("checked", false);
	}
}

//FORMATTA DATA dd/mm/yyyy;______________________________
function formatDate(data){
	if(data !=null){
		var dateArray=data.split('T');
		var format= dateArray[0].split("-");
		var yyyy=format[0];
		var mm= format[1];
		var dd= format[2];
		var date= dd+"/"+mm+"/"+yyyy;
		return date;
	}
	return "";

}

function showAsideNewDocumentiValori(operation){
	
	var codiceDominio = $('#upCodDom').val();
	
	$('#codiceDominio').val(codiceDominio);
	
	if(operation == 'I'){
		$("#idDominiValori").val("0");
		$("#codiceValore").val("");
		$("#descrizione").val("");
		$("#dataInizioVal").val("");
		$("#dataFineVal").val("");
	}
	
	sizeModalCustom();
	$('.modalAddDominiValori').css("display", "block");
	jQuery('.menu-sx-levelone-facility.add').animate({marginRight:'0'}, 400);
	if(contOne){
		jQuery('.menu-sx-levelone-facility.add').animate({marginRight:'0'}, 400);
		contOne = false;
	}else{
		slideAsideNew('');
		$('.modalAddDominiValori').css("display", "none");
		contOne = true;
	}
}

function salvaDominiValori(operation){
	
	var idDominiValori = $("#idDominiValori").val();
	var codiceDominio = $("#codiceDominio").val();
	var codiceValore = $("#codiceValore").val();
	var descrizione= $("#descrizione").val();
	
	var dataInizio= $("#dataInizioVal").val();
	var dataFine= $("#dataFineVal").val();


	if(descrizione.trim() == "" || codiceValore.trim() == "" || codiceDominio.trim() == "" || dataInizio.trim() == ""){
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

	// controllo dataFine maggiore di dataInizio
	if(dataFine != ''){
		
		if(!checkDate(dataInizio, dataFine)){
			return;
		}
		
	}

	$.ajax( {
		type : 'POST',

		url : '/CruscottoAuditAtpoWebWeb/jsonATPO/checkCodiceDominiValori',
		data : {
		'codiceValore': codiceValore,
		'idDominiValori': idDominiValori

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
			$.notific8('Codice Valore già esistente ', settings);
			return;
		}else{
			$.ajax( {
				type : 'POST',

				url : '/CruscottoAuditAtpoWebWeb/jsonATPO/salvaDominiValori',
				data : {
				'dominiValori.idDominiValori' : idDominiValori,
				'dominiValori.codiceDominio' : codiceDominio,
				'dominiValori.codiceValore' : codiceValore,
				'dominiValori.descrizione' : descrizione,
				'dominiValori.dataInizio' : dataInizio,
				'dominiValori.dataFine' : dataFine

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

				$("#formDominiValori").trigger('reset');

				$('#dominiValoriTable').DataTable().ajax.reload();
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

function modificaDominiValori(){
	var checkedIds = $(".chkDominiValori:checked").map(function() {
		return this.value;

	}).toArray();
	// controlla checkbox selezionati
	if(!checkSelection(checkedIds)){
		return;
	}
	
	var idDominiValori = checkedIds[0];
	var values = new Array(); 

	$.each($("#checkboxTable_"+idDominiValori).closest("td").siblings("td"), 
			function () {
		values.push($(this).text());
		
	});
	
	// scrive nell'aside i valori da modificare
	$("#idDominiValori").val(idDominiValori);
	$("#codiceDominio").val(values[0]);
	$("#codiceValore").val(values[1]);
	$("#descrizione").val(values[2]);
	$("#dataInizioVal").val(values[3]);
	$("#dataFineVal").val(values[4]);
	
	initDatePicker();
	showAsideNewDocumentiValori('U');
	checkedIds=[];
	
	
	return;
}

function selectAllDomVal(){
	var checkAll = $("#checkAllDomVal").prop('checked');
	if (checkAll) {
		$(".chkDominiValori").prop("checked", true);
	} else {
		$(".chkDominiValori").prop("checked", false);
	}
}


function showRemoveModalDomVal(){
	var checkedIds = $(".chkDominiValori:checked").map(function() {
		return this.value;

	}).toArray();
	// controlla checkbox selezionati
	if(!checkAtLeastOneSelection(checkedIds)){
		return
	}else{
		$('#deleteDominivaloriModal').modal();
	}

}

function eliminaDominiValori(){
	var checkedIds = $(".chkDominiValori:checked").map(function() {
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
		url : '/CruscottoAuditAtpoWebWeb/jsonATPO/eliminaDominiValori',
		data :{ 
		'listId' : d.toString()
	},


	success : function(data) {

		$('#dominiValoriTable').DataTable().ajax.reload();
		$("#checkAllDomVal").prop("checked", false);
		$('#deleteDominivaloriModal').modal('hide');

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
