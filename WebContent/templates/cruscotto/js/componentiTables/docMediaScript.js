function salvaDocumento(){
	var descrizione= $("#descrFile").val();
	var codice= $("#codFile").val();
	var dataInizio= $("#dataInizioDoc").val();
	var dataFine= $("#dataFineDoc").val();
	var filePath = $("#fileDoc").val(); 
	var titolo=$("#titoloDoc").val(); 
	var autore=$("#autoreDoc").val(); 
	var dataPubblicazione=$("#dataPubblicazioneDoc").val(); 
	var versione=$("#versDoc").val(); 
	var tipoDocumento =  $("#selectTipoDocumento").find('option:selected').attr('value');



	if(descrizione.trim() =="" || titolo.trim() =="" || dataPubblicazione.trim() == "" || codice.trim() =="" || dataInizio.trim() ==""  || filePath.trim() == "" || tipoDocumento.trim() ==""){
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
	var fileNameAndExtension = $('#fileDoc')[0].files[0]['name'];
	var checkDate=checkDateIF(dataInizio, dataFine);
	if(!checkDate){

		return;
	}

	$.ajax( {
		type : 'POST',

		url : '/CruscottoAuditAtpoWebWeb/jsonATPO/checkCodiceDocMedia',
		data : {
		'codiceDocMedia':codice,
		'idDocumento':-1

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
			var files = document.getElementById('fileDoc').files;

			if (files.length > 0) {
				Metronic.startPageLoading();
				var reader = new FileReader();
				//converte in base64
				reader.readAsDataURL(files[0]);
				reader.onload = function () {

					var f=reader.result;	
					//console.log(f);
					$.ajax( {
						type : 'POST',

						url : '/CruscottoAuditAtpoWebWeb/jsonATPO/salvaDocMedia',
						data : {
						'docMedia.descrizione' : descrizione,
						'docMedia.codice' : codice,
						'docMedia.dataInizio' : dataInizio,
						'docMedia.dataFine' : dataFine,
						'base64File': f,
						'docMedia.nomeFile':fileNameAndExtension,
						'docMedia.titolo': titolo,
						'docMedia.autore': autore,
						'docMedia.dataPubblicazione': dataPubblicazione,
						'docMedia.versione': versione,
						'docMedia.idCrsTplDocMedia':tipoDocumento


					},

					success : function(data) {
						Metronic.stopPageLoading();
						var settings = {
								theme : 'teal',
								sticky : false,
								horizontalEdge : 'top',
								verticalEdge : 'right',
								life : 3000
						};
						$.notific8('zindex', 11500);
						$.notific8('Salvataggio eseguito ', settings);



						$("#descrFile").val("");
						$("#codFile").val("");
						$("#dataInizioDoc").val("");
						$("#dataFineDoc").val("");
						$("#fileDoc").val("");


						$('#docMediaTable').DataTable().ajax.reload();
						slideAside('');
						return;

					},
					error : function(data) {
						Metronic.stopPageLoading();
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
				};

			}


		}


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
		$.notific8('Errore durante controllo del codice ', settings);
		return;
	}
	});


}

function salvaModificaDocumento(){
	var id=$("#upIdDoc").val();
	var descrizione= $("#upDescrFile").val();
	var codice= $("#upCodFile").val();
	var dataInizio= $("#upDataInizioDoc").val();
	var dataFine= $("#upDataFineDoc").val();
	var titolo= $("#upTitolo").val();
	var autore= $("#upAutore").val();
	var dataPubblicazione= $("#upDataPubb").val();
	var versione= $("#upVersione").val();
	var tipoDocumento= $("#selectTipoDocumentoUp").find('option:selected').attr('value');
	

	if(descrizione.trim() == "" || tipoDocumento.trim()== "" || codice.trim() == "" || dataInizio.trim() == "" || titolo.trim() =="" || dataPubblicazione.trim() == "" ){
	 
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

	var checkDate=checkDateIF(dataInizio, dataFine);
	if(!checkDate){
		var settings = {
				theme : 'teal',
				sticky : false,
				horizontalEdge : 'top',
				verticalEdge : 'right',
				life : 3000
		};
		$.notific8('zindex', 11500);
		$.notific8('Date incongruenti ', settings);
		return;
	}

	//controllo codice
	$.ajax( {
		type : 'POST',

		url : '/CruscottoAuditAtpoWebWeb/jsonATPO/checkCodiceDocMedia',
		data : {
		'codiceDocMedia':codice,
		'idDocumento' :id

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

				url : '/CruscottoAuditAtpoWebWeb/jsonATPO/salvaModificaDocMedia',
				data : {
				'docMedia.id':id,
				'docMedia.descrizione' : descrizione,
				'docMedia.codice' : codice,
				'docMedia.dataInizio' : dataInizio,
				'docMedia.dataFine' : dataFine,
				'docMedia.titolo': titolo,
				'docMedia.autore': autore,
				'docMedia.dataPubblicazione': dataPubblicazione,
				'docMedia.versione': versione,
				'docMedia.idCrsTplDocMedia':tipoDocumento
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

				$("#upDescrFile").val("");
				$("#upCodFile").val("");
				$("#upDataInizioDoc").val("");
				$("#upDataFineDoc").val("");
				$("#upIdDoc").val("");


				$('#docMediaTable').DataTable().ajax.reload();
				slideAsideNew('');
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

				slideAsideNew('');

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








function eliminaDocumento(){
	var checkedIds = $(".chkDocM:checked").map(function() {
		return this.value;

	}).toArray();
	var d=[];
	var arrayLength=checkedIds.length;
	for (var i = 0; i < arrayLength; i++) {
		var idCod= checkedIds[i].split("-");
		var id=idCod[0];
		d.push(id);
	}

	$.ajax( {
		type : 'POST',
		dataType:'json',
		//AuAuditAction.java
		url : '/CruscottoAuditAtpoWebWeb/jsonATPO/eliminaDocMedia',
		data :{ 
		'listId' : d.toString()


	},


	success : function(data) {

		$('#docMediaTable').DataTable().ajax.reload();
		$("#checkAllDoc").prop("checked", false);
		$('#deleteDocModal').modal('hide');

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

function manageFiltro(){
	document.getElementById('addFiltro').onchange = function() {
		if(document.getElementById('addFiltro').checked) {
			document.getElementById('checkAllDoc').checked=false;
			var table = $('#docMediaTable').DataTable();
			table.destroy();
			initDocMediaFiltro();
			document.getElementById('addFiltro').checked=true;
		}else{
			document.getElementById('checkAllDoc').checked=false;
			var table = $('#docMediaTable').DataTable();
			table.destroy();
			initDocMedia();
			document.getElementById('addFiltro').checked=false;
		}
	}
}
function base64MimeType(encoded) {
	var result = null;
	if (typeof encoded !== 'string') {
		return result;
	}
	var mime = encoded.match(/data:([a-zA-Z0-9]+\/[a-zA-Z0-9-.+]+).*,.*/);
	if (mime && mime.length) {
		result = mime[1];
	}
	return result;
}
function scaricaDocumento(){

	var checkedIds = $(".chkDocM:checked").map(function() {
		return this.value;
	}).toArray();

	// controlla checkbox selezionati
	if(!checkSelection(checkedIds)){
		return;
	}
	Metronic.startPageLoading();
	var idCod= checkedIds[0].split("-");
	var idDoc=idCod[0];

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
		$("#checkAllDoc").prop("checked", false);
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

Element.prototype.remove = function() {
	this.parentElement.removeChild(this);
}
NodeList.prototype.remove = HTMLCollection.prototype.remove = function() {
	for(var i = this.length - 1; i >= 0; i--) {
		if(this[i] && this[i].parentElement) {
			this[i].parentElement.removeChild(this[i]);
		}
	}
}
function download(dataurl, filename, blob, type) {
	var isFirefox = typeof InstallTrigger !== 'undefined';
	var isIE = /*@cc_on!@*/false || !!document.documentMode;
	var isEdge = !isIE && !!window.StyleMedia;
	var isChrome = !!window.chrome && (!!window.chrome.webstore || !!window.chrome.runtime);



	var a = document.createElement("a");
	a.id="export";
	document.body.appendChild(a); //required in FF, optional for Chrome
	a.href = dataurl;
	if(isChrome || isFirefox){
		a.download = filename;
		a.target="_self" ; //required in FF, optional for Chrome

	}

	if(isIE || isEdge) {// IE
		var byteArray = new Uint8Array(blob);
		var blob = new Blob([byteArray],{ type: type });
		$("#export").click(function(){
			window.navigator.msSaveOrOpenBlob(blob, filename);

		});

	}
	a.click();
	document.getElementById("export").remove();
	return false;


}

function checkDateIF(dataInizio, dataFine){
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

//premendo il checkbox sull head vengono selezionate tutte le righe
function selectAllDoc(){


	var checkAll = $("#checkAllDoc").prop('checked');
	if (checkAll) {
		$(".chkDocM").prop("checked", true);
	} else {
		$(".chkDocM").prop("checked", false);
	}


}

function closeAsideNewDoc(){
	$("#descrFile").val("");
	$("#codFile").val("");
	$("#dataInizioDoc").val("");
	$("#dataFineDoc").val("");
	$("#fileDoc").val("");
	slideAsideNew('');

}

function closeAsideUpDoc(){
	$("#upDescrFile").val("");
	$("#upCodFile").val("");
	$("#upDataInizioDoc").val("");
	$("#upDataFineDoc").val("");
	$("#upIdDoc").val("");
	slideAsideNew('');

}

