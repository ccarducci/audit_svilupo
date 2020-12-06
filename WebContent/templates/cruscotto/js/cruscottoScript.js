function logout(){
	$.ajax( {
		type : 'GET',
		// AuAuditAction.java
		url : '/CruscottoAuditAtpoWebWeb/logOut',

		success : function(data) {

		return;

	},
	error : function(data) {

		return;
	}
	});


}



//serve come variabile globale per aprire e chiudere l' aside
var contOne = true;


//carica il div e chiama la funzione di callback per popolare la tabella
function loadDiv(action, targetDiv, formId, callbackFunction) {
	$('#' + targetDiv).addClass("active");
	var params = null;
	var actionOld = action;
	if (action.indexOf('?') != -1) {
		action = action.substring(0, action.indexOf('?'));

		if (actionOld.indexOf('?') + 1 < actionOld.length)
			params = actionOld.substring(actionOld.indexOf('?') + 1,
					actionOld.length);

	}

	$.ajax( {
		type : 'POST',
		url : action + '?',
		data : formId == undefined || formId == null ? params : params + '&'+ $("#" + formId).serialize(),
				// dataType: 'json',
				success : function(data) {
		// document.getElementById(target).innerHTML = data;
		$('#' + targetDiv).html(data);
		if (callbackFunction != undefined && callbackFunction != null) {
			window[callbackFunction]();
		}

	}
	});

}


//serve come indice/colore per i link della navbar laterale
function clickMenu(idLi) {
	var active = idLi.split("-");
	var pnt = active[0];
	var child = active[1];

	$(".page-sidebar-menu li").removeClass("active");
	$(".page-sidebar-menu li").removeClass("open");
	$("#" + pnt).addClass("active");
	if(child){
		$("#" + child).addClass("active");
	}
}


//Controlla quale e quanti checkbox ha selezionato l'utente
function getCheckBoxID(caller){


	switch(caller){
	// caso: modifica accessi
	case 'accessi':
		var checkedIds = $(".chk:checked").map(function() {
			return this.value;

		}).toArray();

		// controlla checkbox selezionati
		if(!checkSelection(checkedIds)){
			return;
		}
		// setto il tag hidden con l'idSessione
		var sessionId=  checkedIds[0];
		$("#sessionID").val(sessionId);

		//recupero il tipo di auditors dalla navbar

		var tipoAuditors=(document.getElementById("tipo_"+sessionId).getAttribute('value'));

		if(tipoAuditors =='vigilanza'){
			//class: AuditorsAction.java
			loadDiv('/CruscottoAuditAtpoWebWeb/modificaAccesso?idSessione='+sessionId, 'appView', null, 'initTablesModificaAuditor' );
		}else if(tipoAuditors =='atpo'){
			//class: AccessiAtpoAction.java
			//loadDiv('/CruscottoAuditAtpoWebWeb/modificaAccessoATPO?idSessione='+sessionId, 'appView', null, 'initTablesModificaAuditorATPO' );
			loadDiv('/CruscottoAuditAtpoWebWeb/modificaAccessoATPO?idSessione='+sessionId, 'appView', null, null );
		}



		checkedIds=[];
		break;

		// caso: modifica pratiche (modifica-accessi tab 2)
	case 'pratica':


		var checkedIds = $(".chk:checked").map(function() {
			return this.value;

		}).toArray();

		// controlla checkbox selezionati
		if(!checkSelection(checkedIds)){
			return
		}
		// prendo l'id della pratica(idVerbale)
		var idVerbale=  checkedIds[0];
		// AuditorsAction.java
		loadDiv('/CruscottoAuditAtpoWebWeb/modificaPratica?idVerbale='+idVerbale, 'appView', null, 'initTablesIndicatori' ); 

		checkedIds=[];
		break;






		//richiamata da area.jsp per modificare un' area
	case'modificaArea':
		var checkedIds = $(".chkArea:checked").map(function() {
			return this.value;

		}).toArray();
		// controlla checkbox selezionati
		if(!checkSelection(checkedIds)){
			return
		}


		var idArea = checkedIds[0];
		var values = new Array(); 

		$.each($("#checkboxTable_"+idArea).closest("td").siblings("td"), 
				function () {
			values.push($(this).text());
			// console.log(i++ +" "+ $(this).text());
		});

		// scrive nell'aside i valori da modificare
		$("#idArea").val(idArea);
		$("#descrizione").val(values[0]);
		$("#dataInizio").val(values[1]);
		$("#dataFine").val(values[2]);
		$("#dataFineOrig").val(values[2]);
//		$("#stato").val(values[3]);
//		if(values[3] == 'Attiva'){
//		$('#statoAttivo').attr('checked', 'checked');
//		}else{
//		$('#statoDisattivo').attr('checked', 'checked');
//		}


		showAsideArea('MOD');
		checkedIds=[];

		loadDiv('/CruscottoAuditAtpoWebWeb/getProcessiTableAside', 'divProcessiTable', null, 'initAreaProcesso' );

		break;

	case'auditGenerale':
		var checkedIds = $(".chkGen:checked").map(function() {
			return this.value;

		}).toArray();
		// controlla checkbox selezionati
		if(!checkSelection(checkedIds)){
			return
		}

		var idAudit= checkedIds[0];		
		loadDiv('/CruscottoAuditAtpoWebWeb/modificaAuditGenerale?idAudit='+idAudit, 'appView', null, 'initAuditGeneraleModificaTables' );
		break;
	case'modificaUtente':
		var checkedIds = $(".chkGen:checked").map(function() {
			return this.value;

		}).toArray();
		// controlla checkbox selezionati
		if(!checkSelection(checkedIds)){
			return
		}

		var idAudit= checkedIds[0];		
		loadDiv('/CruscottoAuditAtpoWebWeb/modificaUtenteLoad?idUtente='+idAudit, 'appView', null, null );
		break;		
	case'modificaTipologica':
		var checkedIds = $(".chkGen:checked").map(function() {
			return this.value;

		}).toArray();
		// controlla checkbox selezionati
		if(!checkSelection(checkedIds)){
			return
		}

		var idAudit= checkedIds[0];		
		loadDiv('/CruscottoAuditAtpoWebWeb/modificaTipologicaLoad?idTplTipologica='+idAudit, 'appView', null, null );
		break;	
	case'modificaIsnc':
		var checkedIds = $(".chkGen:checked").map(function() {
			return this.value;

		}).toArray();
		// controlla checkbox selezionati
		if(!checkSelection(checkedIds)){
			return
		}

		var idAudit= checkedIds[0];		
		loadDiv('/CruscottoAuditAtpoWebWeb/modificaIsncLoad?idTplIsnc='+idAudit, 'appView', null, 'initDatePicker' ); 
		break;	
	case'modificaCaricamentoPratica':
		var checkedIds = $(".chkGen:checked").map(function() {
			return this.value;

		}).toArray();
		// controlla checkbox selezionati
		if(!checkSelection(checkedIds)){
			return
		}

		var idAudit= checkedIds[0];		
		loadDiv('/CruscottoAuditAtpoWebWeb/modificaCaricamentoPraticaLoad?idBcp='+idAudit, 'appView', null, '' );
		break;		
		//richiamata da area.jsp per eliminare un' area
	case'eliminaArea':
		var checkedIds = $(".chkArea:checked").map(function() {
			return this.value;

		}).toArray();

		// controlla checkbox selezionati
		if(!checkSelection(checkedIds)){
			return
		}else{

			$('#deleteModal').modal();

			break;

		}

		//richiamata da area.jsp per attivare un' area
	case'attivaArea':
		var checkedIds = $(".chkArea:checked").map(function() {
			return this.value;

		}).toArray();
		// controlla checkbox selezionati
		if(!checkSelection(checkedIds)){
			return
		}


		var idArea = checkedIds[0];
		var values = new Array(); 

		$.each($("#checkboxTable_"+idArea).closest("td").siblings("td"), 
				function () {
			values.push($(this).text());
			// console.log(i++ +" "+ $(this).text());
		});

		var stato =  values[3];

		attivaArea(idArea, stato);
		checkedIds=[];

		break;

		//richiamata da processo.jsp per eliminare un processo
	case 'eliminaProcesso':
		var checkedIds = $(".chkProcesso:checked").map(function() {
			return this.value;

		}).toArray();

		// controlla checkbox selezionati
		if(!checkSelection(checkedIds)){
			return
		}else{

			$('#deleteProcessoModal').modal();

			break;

		}

		//richiamata da processo.jsp per pubblicare un processo
	case 'pubblicaProcesso':
		var checkedIds = $(".chkProcesso:checked").map(function() {
			return this.value;

		}).toArray();

		var idProcesso = checkedIds[0];

		var values = new Array(); 

		$.each($("#checkboxTable_"+idProcesso).closest("td").siblings("td"), 
				function () {
			values.push($(this).text());
		});

		var pubblica =  values[5];

		// controlla checkbox selezionati
		if(!checkSelection(checkedIds)){
			return
		}else{

			if(pubblica == 'SI'){
				$('#messageProcessoDiv').html("<b>Attenzione!</b><br>Il Processo selezionato è già stato Pubblicato");
				$('#processoModal').modal();

				checkedIds=[];
				return;

			}else{

				$('#pubblicaProcessoModal').modal();
			}

			checkedIds=[];
			break;

		}

		//richiamata da processo.jsp per disattivare un processo
	case 'disattivaProcesso':
		var checkedIds = $(".chkProcesso:checked").map(function() {
			return this.value;

		}).toArray();
		// controlla checkbox selezionati
		if(!checkSelection(checkedIds)){
			return
		}


		var idProcesso = checkedIds[0];
		var values = new Array(); 

		$.each($("#checkboxTable_"+idProcesso).closest("td").siblings("td"), 
				function () {
			values.push($(this).text());
		});

		var stato =  values[4];

		//gestisciProcesso(idProcesso, stato, 'Disattiva');
		checkedIds=[];

		break;


	case 'eliminaAudit':
		var checkedIds = $(".chkGen:checked").map(function() {
			return this.value;

		}).toArray();
		if(!checkSelection(checkedIds)){
			return
		}else{

			checkIfAuditHasChildren();
		}
		break;
	case 'eliminaUtente':
		var checkedIds = $(".chkGen:checked").map(function() {
			return this.value;

		}).toArray();
		if(!checkSelection(checkedIds)){
			return
		}else{
			$('#deleteUtenteModal').modal();
			//checkIfAuditHasChildren();
		}
		break;
	case 'eliminaCaricamentoPratiche':
		var checkedIds = $(".chkGen:checked").map(function() {
			return this.value;

		}).toArray();
		if(!checkSelection(checkedIds)){
			return
		}else{
			$('#deleteCaricamentoPraticheModal').modal();
			//checkIfAuditHasChildren();
		}
		break;
	case 'eliminaTipologica':
		var checkedIds = $(".chkGen:checked").map(function() {
			return this.value;

		}).toArray();
		if(!checkSelection(checkedIds)){
			return
		}else{
			$('#deleteTipologicaModal').modal();
			//checkIfAuditHasChildren();
		}
		break;
	case 'eliminaIsnc':
		var checkedIds = $(".chkGen:checked").map(function() {
			return this.value;

		}).toArray();
		if(!checkSelection(checkedIds)){
			return
		}else{
			$('#deleteIsncModal').modal();
			//checkIfAuditHasChildren();
		}
		break;
	
		

	case 'eliminaFase':
		var checkedIds = $(".chkFasiAssegnate:checked").map(function() {
			return this.value;

		}).toArray();
		if(!checkSelection(checkedIds)){
			return
		}else{

			$('#deleteFasiModal').modal();
		}
		break;

	case'processoGenerale':
		var checkedIds = $(".chkProcesso:checked").map(function() {
			return this.value;

		}).toArray();
		// controlla checkbox selezionati
		if(!checkSelection(checkedIds)){
			return
		}


		var idProcesso = checkedIds[0];

		loadDiv('/CruscottoAuditAtpoWebWeb/modificaProcesso?idProcesso='+idProcesso+'&idClasse=1&idTipo=1', 'appView', null, null);
		break;

		//richiamata da sottoProcesso.jsp per attivare un sotto processo
	case 'attivaSottoProcesso':
		var checkedIds = $(".chkFaseProcesso:checked").map(function() {
			return this.value;

		}).toArray();
		// controlla checkbox selezionati
		if(!checkSelection(checkedIds)){
			return
		}


		var idSottoProcesso = checkedIds[0];
		var values = new Array(); 

		$.each($("#checkboxTable_"+idSottoProcesso).closest("td").siblings("td"), 
				function () {
			values.push($(this).text());
		});

		var stato =  values[5];

		gestisciSottoProcesso(idSottoProcesso, stato, 'Attiva');
		checkedIds=[];

		break;

		//richiamata da sottoProcesso.jsp per disattivare un sotto processo
	case 'disattivaSottoProcesso':
		var checkedIds = $(".chkFaseProcesso:checked").map(function() {
			return this.value;

		}).toArray();
		// controlla checkbox selezionati
		if(!checkSelection(checkedIds)){
			return
		}


		var idSottoProcesso = checkedIds[0];
		var values = new Array(); 

		$.each($("#checkboxTable_"+idSottoProcesso).closest("td").siblings("td"), 
				function () {
			values.push($(this).text());
		});

		var stato =  values[5];

		gestisciSottoProcesso(idSottoProcesso, stato, 'Disattiva');
		checkedIds=[];

		break;

		//richiamata da sottoProcesso.jsp per eliminare un sotto processo
	case 'eliminaSottoProcesso':
		var checkedIds = $(".chkFaseProcesso:checked").map(function() {
			return this.value;

		}).toArray();

		// controlla checkbox selezionati
		if(!checkSelection(checkedIds)){
			return
		}else{

			$('#deleteSottoProcessoModal').modal();

			break;

		}

	case'sottoProcessoGenerale':
		var checkedIds = $(".chkFaseProcesso:checked").map(function() {
			return this.value;

		}).toArray();
		// controlla checkbox selezionati
		if(!checkSelection(checkedIds)){
			return
		}


		var idSottoProcesso = checkedIds[0];

		loadDiv('/CruscottoAuditAtpoWebWeb/modificaSottoProcesso?sottoProcesso.idSottoProcesso='+idSottoProcesso+'&idClasse=1&idTipo=1', 'appView', null, null);
		break;

	case 'modificaCampagna':
		var checkedIds = $(".chkCampagna:checked").map(function() {
			return this.value;

		}).toArray();
		// controlla checkbox selezionati
		if(!checkSelection(checkedIds)){
			return;
		}

		var idCampagna= checkedIds[0];
		var tipoCmp=(document.getElementById("chkCampagnaTipo_"+idCampagna).getAttribute('value'));
		if(tipoCmp != 'atpo'){
			loadDiv('/CruscottoAuditAtpoWebWeb/modificaCampagnaGenerale?idCampagna='+idCampagna+'&tipoCmp='+tipoCmp, 'appView', null, 'initTablesGeneraleModifica' );
		}else{
			loadDiv('/CruscottoAuditAtpoWebWeb/modificaCampagnaGenerale?idCampagna='+idCampagna+'&tipoCmp='+tipoCmp, 'appView', null, 'initTablesGeneraleModificaAtpo' );
		}
		break;

	case 'eliminaCampagna':
		var checkedIds = $(".chkCampagna:checked").map(function() {
			return this.value;

		}).toArray();
		if(!checkSelection(checkedIds)){
			return;
		}else{
			if(checkIfDataFineHasValue()) return;
			checkIfCampagnaHasSessioni();
		}
		break;

		//richiamata da attivitaComponente.jsp per eliminare un' attivita componente
	case 'eliminaAttivitaComponente':
		var checkedIds = $(".chkAttivitaComponente:checked").map(function() {
			return this.value;

		}).toArray();

		// controlla checkbox selezionati
		if(!checkSelection(checkedIds)){
			return;
		}else{

			$('#deleteAttivitaComponenteModal').modal();

			break;

		}

		//richiamata da attivitaComponente.jsp per attivare un'attivita componente
	case 'attivaAttivitaComponente':
		var checkedIds = $(".chkAttivitaComponente:checked").map(function() {
			return this.value;

		}).toArray();
		// controlla checkbox selezionati
		if(!checkSelection(checkedIds)){
			return
		}


		var idAttivitaComponente = checkedIds[0];
		var values = new Array(); 

		$.each($("#checkboxTable_"+idAttivitaComponente).closest("td").siblings("td"), 
				function () {
			values.push($(this).text());
		});

		var stato =  values[5];

		gestisciAttivitaComponente(idAttivitaComponente, stato, 'Attiva');
		checkedIds=[];

		break;

		//richiamata da attivitaComponente.jsp per disattivare un'attivita componente
	case 'disattivaAttivitaComponente':
		var checkedIds = $(".chkAttivitaComponente:checked").map(function() {
			return this.value;

		}).toArray();
		// controlla checkbox selezionati
		if(!checkSelection(checkedIds)){
			return
		}


		var idAttivitaComponente = checkedIds[0];
		var values = new Array(); 

		$.each($("#checkboxTable_"+idAttivitaComponente).closest("td").siblings("td"), 
				function () {
			values.push($(this).text());
		});

		var stato =  values[5];

		gestisciAttivitaComponente(idAttivitaComponente, stato, 'Disattiva');
		checkedIds=[];

		break;

	case 'attivitaComponenteGenerale':
		var checkedIds = $(".chkAttivitaComponente:checked").map(function() {
			return this.value;

		}).toArray();
		// controlla checkbox selezionati
		if(!checkSelection(checkedIds)){
			return
		}


		var idAttivitaComponente = checkedIds[0];

		loadDiv('/CruscottoAuditAtpoWebWeb/modificaAttivitaComponente?attivitaComponente.idAttivitaComponente='+idAttivitaComponente+'&idClasse=1&idTipo=1', 'appView', null, null);
		break;

	case 'attivaRegolaCampagna':

		var checkedIds = $(".chkRegole:checked").map(function() {
			return this.value;

		}).toArray();
		// controlla checkbox selezionati
		if(!checkSelection(checkedIds)){
			return
		}
		Metronic.startPageLoading();
		var idRegola = checkedIds[0];

		$.ajax( {
			type : 'GET',
			url : '/CruscottoAuditAtpoWebWeb/attivaRegolaCampagna',
			data : { 	'idRegola' : idRegola
		},

		success : function(data) {

			Metronic.stopPageLoading();
			$('#campagnaRegoleTable').DataTable().ajax.reload();

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
			$.notific8("Errore durante l'aggiornamento dello stato", settings);
			return; 
		}
		});

		break;

	case 'disattivaRegolaCampagna':

		var checkedIds = $(".chkRegole:checked").map(function() {
			return this.value;

		}).toArray();
		// controlla checkbox selezionati
		if(!checkSelection(checkedIds)){
			return
		}
		Metronic.startPageLoading();
		var idRegola = checkedIds[0];

		$.ajax( {
			type : 'GET',
			url : '/CruscottoAuditAtpoWebWeb/disattivaRegolaCampagna',
			data : { 	'idRegola' : idRegola
		},

		success : function(data) {
			Metronic.stopPageLoading();
			$('#campagnaRegoleTable').DataTable().ajax.reload();

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
			$.notific8("Errore durante l'aggiornamento dello stato", settings);
			return; 
		}
		});

		break;


		//richiamata da attivitaDettaglio.jsp per eliminare un' attivita dettaglio
	case 'eliminaAttivitaDettaglio':
		var checkedIds = $(".chkAttivitaDettaglio:checked").map(function() {
			return this.value;

		}).toArray();

		// controlla checkbox selezionati
		if(!checkSelection(checkedIds)){
			return
		}else{

			$('#deleteAttivitaDettaglioModal').modal();

			break;

		}

		//richiamata da attivitaDettaglio.jsp per attivare un'attivita dettaglio
	case 'attivaAttivitaDettaglio':
		var checkedIds = $(".chkAttivitaDettaglio:checked").map(function() {
			return this.value;

		}).toArray();
		// controlla checkbox selezionati
		if(!checkSelection(checkedIds)){
			return
		}


		var idAttivitaDettaglio = checkedIds[0];
		var values = new Array(); 

		$.each($("#checkboxTable_"+idAttivitaDettaglio).closest("td").siblings("td"), 
				function () {
			values.push($(this).text());
		});

		var stato =  values[4];

		gestisciAttivitaDettaglio(idAttivitaDettaglio, stato, 'Attiva');
		checkedIds=[];

		break;

		//richiamata da attivitaDettaglio.jsp per disattivare un'attivita dettaglio
	case 'disattivaAttivitaDettaglio':
		var checkedIds = $(".chkAttivitaDettaglio:checked").map(function() {
			return this.value;

		}).toArray();
		// controlla checkbox selezionati
		if(!checkSelection(checkedIds)){
			return
		}


		var idAttivitaDettaglio = checkedIds[0];
		var values = new Array(); 

		$.each($("#checkboxTable_"+idAttivitaDettaglio).closest("td").siblings("td"), 
				function () {
			values.push($(this).text());
		});

		var stato =  values[4];

		gestisciAttivitaDettaglio(idAttivitaDettaglio, stato, 'Disattiva');
		checkedIds=[];

		break;

	case 'attivitaDettaglioGenerale':
		var checkedIds = $(".chkAttivitaDettaglio:checked").map(function() {
			return this.value;

		}).toArray();
		// controlla checkbox selezionati
		if(!checkSelection(checkedIds)){
			return
		}


		var idAttivitaDettaglio = checkedIds[0];

		loadDiv('/CruscottoAuditAtpoWebWeb/modificaAttivitaDettaglio?attivitaDettaglio.idAttivitaDettaglio='+idAttivitaDettaglio+'&idClasse=1&idTipo=1', 'appView', null, null);
		break;

	case 'modificaAccessoAudit' :
		var checkedIds = $(".chkAcc:checked").map(function() {
			return this.value;

		}).toArray();
		// controlla checkbox selezionati
		if(!checkSelection(checkedIds)){
			return
		}


		var idSessione = checkedIds[0];

		loadDiv('/CruscottoAuditAtpoWebWeb/modificaAuditAccesso?idSessione='+idSessione, 'appView', null, 'initModificaAccessoAudit');
		break;

	case 'eliminaAccessoAudit' :
		var checkedIds = $(".chkAcc:checked").map(function() {
			return this.value;

		}).toArray();
		// controlla checkbox selezionati
		if(!checkSelection(checkedIds)){
			return;
		}else{

			$('#deleteAccessoModal').modal();
		}
		break;
	case 'addPraticaToCampione' :
		Metronic.startPageLoading();
		var checkedIds = $(".chkPratSess:checked").map(function() {
			return this.value;

		}).toArray();
		// controlla checkbox selezionati
		if(!checkSelection(checkedIds)){
			Metronic.stopPageLoading();
			return;
		}else{
			var idPratica = checkedIds[0];
			$.ajax( {
				type : 'GET',
				url : '/CruscottoAuditAtpoWebWeb/json/aggiungiPraticaAlCampione',
				data : { 	'idPratica' : idPratica
			},

			success : function(data) {
				Metronic.stopPageLoading();
				$('#verbaliCampione').DataTable().ajax.reload();
				$('#praticheSessioneTable').DataTable().ajax.reload();

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
				$.notific8("Errore durante l'aggiornamento dello stato", settings);
				return; 
			}
			});



		}
		break;
	case 'eliminaPraticaCampione' :
		var checkedIds = $(".chkSessCamp:checked").map(function() {
			return this.value;

		}).toArray();
		// controlla checkbox selezionati
		if(!checkSelection(checkedIds)){
			return;
		}else{

			$('#deleteVerbaliCampione').modal();
		}
		break;

		//richiamata da normativa.jsp per modificare una normativa
	case'modificaCircolariInps':

		var checkedIds = $(".chkCircolariInps:checked").map(function() {
			return this.value;

		}).toArray();
		// controlla checkbox selezionati
		if(!checkSelection(checkedIds)){
			return;
		}else{
			var id= checkedIds[0];

			loadDiv('/CruscottoAuditAtpoWebWeb/getCircolariInpsById?idNormativa='+id, 'appView', null, 'initDatePicker' );

		}

		break;
	case'modificaNoteDecreti':
		var checkedIds = $(".chkNoteDecreti:checked").map(function() {
			return this.value;

		}).toArray();
		// controlla checkbox selezionati
		if(!checkSelection(checkedIds)){
			return;
		}else{
			var id= checkedIds[0];

			loadDiv('/CruscottoAuditAtpoWebWeb/getNoteDecretiById?idNormativa='+id, 'appView', null, 'initDatePicker' );

		}
		break;
	case'modificaMessaggiInps':
		var checkedIds = $(".chkMessaggiInps:checked").map(function() {
			return this.value;

		}).toArray();
		// controlla checkbox selezionati
		if(!checkSelection(checkedIds)){
			return;
		}else{
			var id= checkedIds[0];

			loadDiv('/CruscottoAuditAtpoWebWeb/getMessaggiInpsById?idNormativa='+id, 'appView', null, 'initDatePicker' );

		}
		break;
	case'modificaLeggiDecreti':
		var checkedIds = $(".chkLeggiDecreti:checked").map(function() {
			return this.value;

		}).toArray();
		// controlla checkbox selezionati
		if(!checkSelection(checkedIds)){
			return;
		}else{
			var id= checkedIds[0];

			loadDiv('/CruscottoAuditAtpoWebWeb/getLeggiDecretiById?idNormativa='+id, 'appView', null, 'initDatePicker' );

		}
		break;

		//richiamata da normativa.jsp per eliminare una normativa
	case'eliminaNormativa':

		var checkedIds;
		var idTipo = $('#comboTipoNormativa option:selected').val();

		if(idTipo == 1){
			checkedIds = $(".chkCircolariInps:checked").map(function() {
				return this.value;

			}).toArray();
		}
		if(idTipo == 2){
			checkedIds = $(".chkNoteDecreti:checked").map(function() {
				return this.value;

			}).toArray();
		}
		if(idTipo == 3){
			checkedIds = $(".chkMessaggiInps:checked").map(function() {
				return this.value;

			}).toArray();
		}
		if(idTipo == 4){
			checkedIds = $(".chkLeggiDecreti:checked").map(function() {
				return this.value;

			}).toArray();
		}


		// controlla checkbox selezionati
		if(!checkSelection(checkedIds)){
			return
		}else{

			$('#deleteNormativaModal').modal();

			break;
		}
	case 'modificaAccessoATPO':

		var checkedIds = $(".chkPraticheATPO:checked").map(function() {
			return this.value;

		}).toArray();
		// controlla checkbox selezionati
		if(!checkSelection(checkedIds)){
			return
		}
		var idPraticaATPO = checkedIds[0];

		loadDiv('/CruscottoAuditAtpoWebWeb/modificaAccessoPraticaATPO?idPraticaATPO='+idPraticaATPO, 'appView', null, 'initTablesModificaAuditor' );

		break;
	case 'modificaRischio':
		var checkedIds = $(".chkRischio:checked").map(function() {
			return this.value;

		}).toArray();
		if(!checkSelection(checkedIds)){
			return
		}else{
			var idRischio = checkedIds[0];
			var values = new Array(); 

			$.each($("#checkboxTableRis_"+idRischio).closest("td").siblings("td"), 
					function () {
				values.push($(this).text());
				// console.log(i++ +" "+ $(this).text());
			});

			// scrive nell'aside i valori da modificare

			$('#idRiskAside').val(idRischio);
			$('#descrizioneRischioAside').val(values[0]);
			$('#codiceRischioAside').val(values[1]);
			$('#pesoRischioAside').val(values[2]);
			$('#mediaAside').val(values[3]);
			$('#dataInizioAside').val(values[4]);
			$('#dataFineAside').val(values[5]);

			slideAsideInps('update-risk');
			checkedIds=[];


		}

		break;  

	case 'eliminaRischio':

		var checkedIds = $(".chkRischio:checked").map(function() {
			return this.value;

		}).toArray();
		// controlla checkbox selezionati
		if(!checkAtLeastOneSelection(checkedIds)){
			return
		}else{
			$('#deleteRischioModal').modal();
		}

		break;
	case 'modificaEsprRischio':
		var checkedIds = $(".chkEspRischio:checked").map(function() {
			return this.value;

		}).toArray();
		if(!checkSelection(checkedIds)){
			return
		}else{
			var idEsprRischioCodRischio = checkedIds[0];
			var a= idEsprRischioCodRischio.split("-");
			var idEsprRischio= a[0];
			var idMRischio=a[1];
			var codiceRischio=a[2];
			var values = new Array(); 

			$.each($("#checkboxT_"+idEsprRischio).closest("td").siblings("td"), 
					function () {
				values.push($(this).text());

			});

			// scrive nell'aside i valori da modificare
			var optionDropRischio=codiceRischio.trim()+" - "+values[1].trim();

			$('#idEsprRiskAside').val(idEsprRischio);
			$('#descrizioneEspr').val(values[0]);
			$('#optRischio').val(idMRischio);
			document.getElementById('optRischio').innerHTML = optionDropRischio;

			$('#codiceE').val(values[2]);
			$('#percentualeT').val(values[3]);
			$('#raggruppamento').val(values[4]);
			$('#dataInizioE').val(values[5]);
			$('#dataFineE').val(values[6]);


			slideAsideInps('update-expressRisk');
		}

		break;

	case 'eliminaEsprRischio':
		var checkedIds = $(".chkEspRischio:checked").map(function() {
			return this.value;

		}).toArray();
		// controlla checkbox selezionati
		if(!checkAtLeastOneSelection(checkedIds)){
			return
		}else{
			$('#deleteEsprRischioModal').modal();
		}
		break;

	case 'updateNonConform':
		var checkedIds = $(".chkNonC:checked").map(function() {
			return this.value;

		}).toArray();
		// controlla checkbox selezionati
		if(!checkSelection(checkedIds)){
			return;
		}else{

			var idNonConformitaIdFase = checkedIds[0];
			var a= idNonConformitaIdFase.split("-");
			var idNonConform= a[0];
			var idFase=a[1];

			var values = new Array(); 
			$.each($("#checkTable_"+idNonConform).closest("td").siblings("td"), 
					function () {
				values.push($(this).text());

			});

			$('#optFaseAssociata').val(idFase);

			$('#idNonConfAside').val(idNonConform);

			$('#codiceNcUp').val(values[0]);
			$('#descrizioneFUp').val(values[1]);
			document.getElementById('optFaseAssociata').innerHTML = values[3].trim();
			$('#pesofUp').val(values[4]);
			$('#dataInizioFup').val(values[5]);
			$('#dataFineFup').val(values[6]);




			slideAsideInps('update-notConf');
		}
		break;

	case 'eliminaNC':

		var checkedIds = $(".chkNonC:checked").map(function() {
			return this.value;

		}).toArray();
		// controlla checkbox selezionati
		if(!checkAtLeastOneSelection(checkedIds)){
			return;
		}else{
			$('#deleteNCModal').modal();
		}

		break;
	case 'varComportamentale':

		var checkedIds = $(".chkVC:checked").map(function() {
			return this.value;

		}).toArray();
		// controlla checkbox selezionati
		if(!checkSelection(checkedIds)){
			return;
		}else{

			var idVCidNC = checkedIds[0];
			var a= idVCidNC.split("-");
			var idVC= a[0];
			var idNC=a[1];

			var values = new Array(); 
			$.each($("#checkVCTable_"+idVC).closest("td").siblings("td"), 
					function () {
				values.push($(this).text());

			});
			//id option select da mostrare
			$('#optNCAssociata').val(idNC);
			document.getElementById('optNCAssociata').innerHTML = values[1];
			//hidden con id vc
			$('#idVC').val(idVC);

			$('#descrizioneVCup').val(values[0]);
			$('#codiceVCup').val(values[2]);
			$('#pesoVCup').val(values[3]);
			$('#dataInizioVCup').val(values[4]);
			$('#dataFineVCup').val(values[5]);






			slideAsideInps('update-varianteNot');
		}
		break;
	case 'deleteVarComportamentale':
		var checkedIds = $(".chkVC:checked").map(function() {
			return this.value;

		}).toArray();
		// controlla checkbox selezionati
		if(!checkAtLeastOneSelection(checkedIds)){
			return
		}else{
			$('#deleteVcModal').modal();
		}
		break;

	case 'modifica-domanda':
		var checkedIds = $(".chkQuest:checked").map(function() {
			return this.value;

		}).toArray();
		// controlla checkbox selezionati
		if(!checkSelection(checkedIds)){
			return
		}else{
			var idDomanda= checkedIds[0];
			loadDiv('/CruscottoAuditAtpoWebWeb/modificaDomanda?idDomanda='+idDomanda, 'appView', null, 'initRisposte');
			
		}
		break;
	case 'modificaRisposta':

		var checkedIds = $(".chkRsp:checked").map(function() {
			return this.value;

		}).toArray();
		// controlla checkbox selezionati
		if(!checkSelection(checkedIds)){
			return
		}else{

			var idRisposta = checkedIds[0];
			var values = new Array(); 

			$.each($("#checkboxRsp_"+idRisposta).closest("td").siblings("td"), 
					function () {
				values.push($(this).text());
				// console.log(i++ +" "+ $(this).text());
			});

			// scrive nell'aside i valori da modificare
			$("#idRispostaUp").val(idRisposta);

			if(values.length>2){
				$("#upDescrizioneRsp").val(values[0]);
				$("#upvaloreRsp").val(values[1]);
				$("#updirsp").val(values[2]);
				$("#updfrsp").val(values[3]);
			}else{
				$("#upDescrizioneRsp").val(values[0]);
				$("#upvaloreRsp").val(values[1]);
			}




			slideAsideInps('update-answer');
			checkedIds=[];

		}
		break;
	case 'eliminaRisposta':
		var checkedIds = $(".chkRsp:checked").map(function() {
			return this.value;

		}).toArray();
		// controlla checkbox selezionati
		if(!checkAtLeastOneSelection(checkedIds)){
			return
		}else{
			$('#deleteRispostaModal').modal();
		}
		break;
	case 'eliminaDomanda':
		var checkedIds = $(".chkQuest:checked").map(function() {
			return this.value;

		}).toArray();
		// controlla checkbox selezionati
		if(!checkAtLeastOneSelection(checkedIds)){
			return
		}else{
			$('#deleteDomandaModal').modal();
		}
		break;

	case 'dettagliNonConfAccessi':
		var checkedIds = $(".chkNcA:checked").map(function() {
			return this.value;

		}).toArray();
		// controlla checkbox selezionati
		if(!checkSelection(checkedIds)){
			return;
		}else{
			var id= checkedIds[0];
			$("#modificaNonConfAcces").html("");
			loadDiv('/CruscottoAuditAtpoWebWeb/getDettagliNonConfAccessi?id='+id, 'tabNonConfAccess', null, null);

		}
		break;
	case 'statoVarComp':
		var checkedIds = $(".chkNcA:checked").map(function() {
			return this.value;

		}).toArray();
		// controlla checkbox selezionati
		if(!checkSelection(checkedIds)){
			return;
		}else{
			var id= checkedIds[0];
			$("#modificaNonConfAcces").html("");
			loadDiv('/CruscottoAuditAtpoWebWeb/getPageVarCompNonConfAccessi?id='+id, 'tabNonConfAccess', null, 'initNonConfAccVarComp');

		}
		break;
	case 'getDettagliRischiAccessi':
		var checkedIds = $(".chkRiskAc:checked").map(function() {
			return this.value;

		}).toArray();
		// controlla checkbox selezionati
		if(!checkSelection(checkedIds)){
			return;
		}else{
			var id= checkedIds[0];
			$("#tabRiskAccess").html("")
			loadDiv('/CruscottoAuditAtpoWebWeb/getDettagliRischiAccessi?id='+id, 'tabRiskAccess', null, null);

		}
		break;
	case 'statoEsprRischio':
		var checkedIds = $(".chkRiskAc:checked").map(function() {
			return this.value;

		}).toArray();
		// controlla checkbox selezionati
		if(!checkSelection(checkedIds)){
			return;
		}else{
			var id= checkedIds[0];
			$("#tabRiskAccess").html("")
			loadDiv('/CruscottoAuditAtpoWebWeb/getStatoEspRischiAccessi?id='+id, 'tabRiskAccess', null, 'initStatoEsprRsichio');

		}
		break;
	case 'showUpDocAside':
		var checkedIds = $(".chkDocM:checked").map(function() {
			return this.value;

		}).toArray();
		// controlla checkbox selezionati
		if(!checkSelection(checkedIds)){
			return;
		}else{
			var idCod= checkedIds[0].split("-");
			var id=idCod[0];
			var codice=idCod[1]

			                 $.ajax( {
			                	 type : 'GET',
			                	 //AuditorsAction.java
			                	 url : '/CruscottoAuditAtpoWebWeb/jsonATPO/getDocMediaUpd',
			                	 data :{ 'idDocumento' : id,


			                 },


			                 success : function(data) {

			                	 var doc=data.docMedia;
			                	 var opt= data.optionSelected;
			                	 $("#upIdDoc").val(doc.id);
			                	 $("#upCodFile").val(doc.codice);
			                	 $("#upDescrFile").val(doc.descrizione);
			                	 $("#upDataInizioDoc").val(formatDateT(doc.dataInizio));
			                	 $("#upDataFineDoc").val(formatDateT(doc.dataFine));
			                	 $("#upTitolo").val(doc.titolo);
			                	 $("#upAutore").val(doc.autore);
			                	 $("#upVersione").val(doc.versione);
			                	 $("#upDataPubb").val(formatDateT(doc.dataPubblicazione));
			                	
			                	 $("#selectTipoDocumentoUp option[id='"+opt.id+"']").attr("selected", "selected");
			                	 slideAsideNew('update');
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
			                	 $.notific8('Errore durante il recupero del documento ', settings);
			                	 return; 
			                 }
			                 });
			

		}
		break;
	case 'eliminaDocumento':
		var checkedIds = $(".chkDocM:checked").map(function() {
			return this.value;

		}).toArray();
		// controlla checkbox selezionati
		if(!checkAtLeastOneSelection(checkedIds)){
			return
		}else{
			$('#deleteDocModal').modal();
		}
		break;
		
	case 'visualizzaDominiProcesso':
		var checkedIds = $(".chkDomProcesso:checked").map(function() {
			return this.value;

		}).toArray();
		// controlla checkbox selezionati
		if(!checkSelection(checkedIds)){
			return
		}else{
			
			var id= checkedIds[0];
			var associativa="processo";
			loadDiv('/CruscottoAuditAtpoWebWeb/getDominio?idAssociativa='+id+' &tabellaAssociativa='+associativa, 'dominiProcesso', null, 'initAllegatiTables' );
			
		}
		break;
	case 'visualizzaDominiFase':
		var checkedIds = $(".chkDomSottoProcesso:checked").map(function() {
			return this.value;

		}).toArray();
		// controlla checkbox selezionati
		if(!checkSelection(checkedIds)){
			return
		}else{
			
			var id= checkedIds[0].split("_");
			
			var associativa="sottoprocesso";
			loadDiv('/CruscottoAuditAtpoWebWeb/getDominio?idAssociativa='+id+' &tabellaAssociativa='+associativa, 'dominiSottoProcesso', null, 'initAllegatiTables' );
			
		}
		break;
	case 'visualizzaDominiAttComponente':
		var checkedIds = $(".chkDomAttComp:checked").map(function() {
			return this.value;

		}).toArray();
		// controlla checkbox selezionati
		if(!checkSelection(checkedIds)){
			return
		}else{
			
			var id= checkedIds[0];
			
			var associativa="attComponente";
			loadDiv('/CruscottoAuditAtpoWebWeb/getDominio?idAssociativa='+id+' &tabellaAssociativa='+associativa, 'dominiAC', null, 'initAllegatiTables' );
			
		}
		break;
	case 'visualizzaDominiAttDett':
		var checkedIds = $(".chkDomAttivitaDettaglio:checked").map(function() {
			return this.value;

		}).toArray();
		// controlla checkbox selezionati
		if(!checkSelection(checkedIds)){
			return
		}else{
			
			var id= checkedIds[0].split("_");
			
			var associativa="attDettaglio";
			loadDiv('/CruscottoAuditAtpoWebWeb/getDominio?idAssociativa='+id+' &tabellaAssociativa='+associativa, 'dominiAttivitaDettaglio', null, 'initAllegatiTables' );
			
		}
		break;
	case 'visualizzaCompTecProcesso':
		var checkedIds = $(".chkCompTecProcesso:checked").map(function() {
			return this.value;

		}).toArray();
		// controlla checkbox selezionati
		if(!checkSelection(checkedIds)){
			return
		}else{
			
			var id= checkedIds[0];
			
			var associativa="processo";
			loadDiv('/CruscottoAuditAtpoWebWeb/getCompTecnico?idAssociativa='+id+' &tabellaAssociativa='+associativa, 'compTecniciProcesso', null, 'initAllegatiCtTables' );
			
		}
		break;
	case 'visualizzaCompTecSottoProcesso':
		var checkedIds = $(".chkCompTecSottoProcesso:checked").map(function() {
			return this.value;

		}).toArray();
		// controlla checkbox selezionati
		if(!checkSelection(checkedIds)){
			return
		}else{
			
			var id= checkedIds[0];
			
			var associativa="sottoprocesso";
			loadDiv('/CruscottoAuditAtpoWebWeb/getCompTecnico?idAssociativa='+id+' &tabellaAssociativa='+associativa, 'compTecniciSottoProcesso', null, 'initAllegatiCtTables' );
			
		}
		break;
	case 'visualizzaCompTecAttComp':
		var checkedIds = $(".chkCompTecAttComp:checked").map(function() {
			return this.value;

		}).toArray();
		// controlla checkbox selezionati
		if(!checkSelection(checkedIds)){
			return
		}else{
			
			var id= checkedIds[0];
			
			var associativa="attComponente";
			loadDiv('/CruscottoAuditAtpoWebWeb/getCompTecnico?idAssociativa='+id+' &tabellaAssociativa='+associativa, 'compTecniciAc', null, 'initAllegatiCtTables' );
			
		}
		break;
	case 'visualizzaCompTecAttDett':
		var checkedIds = $(".chkCompTecAttivitaDettaglio:checked").map(function() {
			return this.value;

		}).toArray();
		// controlla checkbox selezionati
		if(!checkSelection(checkedIds)){
			return
		}else{
			
			var id= checkedIds[0];
			
			var associativa="attDettaglio";
			loadDiv('/CruscottoAuditAtpoWebWeb/getCompTecnico?idAssociativa='+id+' &tabellaAssociativa='+associativa, 'compTecniciAttivitaDettaglio', null, 'initAllegatiCtTables' );
			
		}
		break;
	case 'visualizzaNormativaProcesso':
		
		var idTipo = $('#comboTipoNormativa option:selected').val();
		if(idTipo == 1){
			var checkedIds = $(".chkCircolariInps:checked").map(function() {
				return this.value;
			}).toArray();
			// controlla checkbox selezionati
			if(!checkSelection(checkedIds)){
				return
			}else{
				var id= checkedIds[0];
				loadDiv('/CruscottoAuditAtpoWebWeb/getNormativaCircolare?idAssociativa='+id, 'processoNormativaDiv', null, 'initAllegatiCircolariInps' );
				$("#buttonPortletTab3").hide();
			}
		}
		
		if(idTipo == 2){
			var checkedIds = $(".chkNoteDecreti:checked").map(function() {
				return this.value;
			}).toArray();
			// controlla checkbox selezionati
			if(!checkSelection(checkedIds)){
				return
			}else{
				var id= checkedIds[0];
				loadDiv('/CruscottoAuditAtpoWebWeb/getNormativaNoteDecreti?idAssociativa='+id, 'processoNormativaDiv', null, 'initAllegatiNoteDecreti' );
				$("#buttonPortletTab3").hide();
			}
		}
		
		if(idTipo == 3){
			var checkedIds = $(".chkMessaggiInps:checked").map(function() {
				return this.value;
			}).toArray();
			// controlla checkbox selezionati
			if(!checkSelection(checkedIds)){
				return
			}else{
				var id= checkedIds[0];
				loadDiv('/CruscottoAuditAtpoWebWeb/getNormativaMessaggi?idAssociativa='+id, 'processoNormativaDiv', null, 'initAllegatiMessaggiInps' );
				$("#buttonPortletTab3").hide();
			}
		}
		
		if(idTipo == 4){
			var checkedIds = $(".chkLeggiDecreti:checked").map(function() {
				return this.value;
			}).toArray();
			// controlla checkbox selezionati
			if(!checkSelection(checkedIds)){
				return
			}else{
				var id= checkedIds[0];
				loadDiv('/CruscottoAuditAtpoWebWeb/getLeggiDecreti?idAssociativa='+id, 'processoNormativaDiv', null, 'initAllegatiLeggiDecreti' );
				$("#buttonPortletTab3").hide();
			}
		}
		
		break;
	case 'visualizzaNormativaSottoProcesso':
		var idTipo = $('#comboTipoNormativa option:selected').val();
		
		if(idTipo == 1){
			var checkedIds = $(".chkCircolariInps:checked").map(function() {
				return this.value;

			}).toArray();
			// controlla checkbox selezionati
			if(!checkSelection(checkedIds)){
				return
			}else{
				var id= checkedIds[0];
				loadDiv('/CruscottoAuditAtpoWebWeb/getNormativaCircolare?idAssociativa='+id, 'sottoProcessoNormativaDiv', null, 'initAllegatiCircolariInps' );
				$("#sottoprocessoNormativaButton").hide();
			}
		
		}
		
		if(idTipo == 2){
			var checkedIds = $(".chkNoteDecreti:checked").map(function() {
				return this.value;

			}).toArray();
			// controlla checkbox selezionati
			if(!checkSelection(checkedIds)){
				return
			}else{
				var id= checkedIds[0];
				loadDiv('/CruscottoAuditAtpoWebWeb/getNormativaNoteDecreti?idAssociativa='+id, 'sottoProcessoNormativaDiv', null, 'initAllegatiNoteDecreti' );
				$("#sottoprocessoNormativaButton").hide();
			}
			
		}
		
		if(idTipo == 3){
			var checkedIds = $(".chkMessaggiInps:checked").map(function() {
				return this.value;
			}).toArray();
			// controlla checkbox selezionati
			if(!checkSelection(checkedIds)){
				return
			}else{
				var id= checkedIds[0];
				loadDiv('/CruscottoAuditAtpoWebWeb/getNormativaMessaggi?idAssociativa='+id, 'sottoProcessoNormativaDiv', null, 'initAllegatiMessaggiInps' );
				$("#sottoprocessoNormativaButton").hide();
			}
		
		}
		
		if(idTipo == 4){
			var checkedIds = $(".chkLeggiDecreti:checked").map(function() {
				return this.value;
			}).toArray();
			// controlla checkbox selezionati
			if(!checkSelection(checkedIds)){
				return
			}else{
				var id= checkedIds[0];
				loadDiv('/CruscottoAuditAtpoWebWeb/getLeggiDecreti?idAssociativa='+id, 'sottoProcessoNormativaDiv', null, 'initAllegatiLeggiDecreti' );
				$("#sottoprocessoNormativaButton").hide();
			}
		
		}
		
		break;
	case 'visualizzaNormativaAttivitaComponente':
		var idTipo = $('#comboTipoNormativa option:selected').val();
		
		if(idTipo == 1){
			var checkedIds = $(".chkCircolariInps:checked").map(function() {
				return this.value;

			}).toArray();
			// controlla checkbox selezionati
			if(!checkSelection(checkedIds)){
				return
			}else{
				var id= checkedIds[0];
				loadDiv('/CruscottoAuditAtpoWebWeb/getNormativaCircolare?idAssociativa='+id, 'attivitaComponenteNormativaDiv', null, 'initAllegatiCircolariInps' );
				$("#attivitaCompTabButton").hide();
			}
		
		}
		
		if(idTipo == 2){
			var checkedIds = $(".chkNoteDecreti:checked").map(function() {
				return this.value;

			}).toArray();
			// controlla checkbox selezionati
			if(!checkSelection(checkedIds)){
				return
			}else{
				var id= checkedIds[0];
				loadDiv('/CruscottoAuditAtpoWebWeb/getNormativaNoteDecreti?idAssociativa='+id, 'attivitaComponenteNormativaDiv', null, 'initAllegatiNoteDecreti' );
				$("#attivitaCompTabButton").hide();
			}
			
		}
		
		if(idTipo == 3){
			var checkedIds = $(".chkMessaggiInps:checked").map(function() {
				return this.value;
			}).toArray();
			// controlla checkbox selezionati
			if(!checkSelection(checkedIds)){
				return
			}else{
				var id= checkedIds[0];
				loadDiv('/CruscottoAuditAtpoWebWeb/getNormativaMessaggi?idAssociativa='+id, 'attivitaComponenteNormativaDiv', null, 'initAllegatiMessaggiInps' );
				$("#attivitaCompTabButton").hide();
			}
		}
		
		if(idTipo == 4){
			var checkedIds = $(".chkLeggiDecreti:checked").map(function() {
				return this.value;
			}).toArray();
			// controlla checkbox selezionati
			if(!checkSelection(checkedIds)){
				return
			}else{
				var id= checkedIds[0];
				loadDiv('/CruscottoAuditAtpoWebWeb/getLeggiDecreti?idAssociativa='+id, 'attivitaComponenteNormativaDiv', null, 'initAllegatiLeggiDecreti' );
				$("#attivitaCompTabButton").hide();
			}
		}
		
		break;
	case 'visualizzaNormativaAttDett':
		var idTipo = $('#comboTipoNormativa option:selected').val();
		
		if(idTipo == 1){
			var checkedIds = $(".chkCircolariInps:checked").map(function() {
				return this.value;

			}).toArray();
			// controlla checkbox selezionati
			if(!checkSelection(checkedIds)){
				return
			}else{
				var id= checkedIds[0];
				loadDiv('/CruscottoAuditAtpoWebWeb/getNormativaCircolare?idAssociativa='+id, 'attivitaDettaglioNormativaDiv', null, 'initAllegatiCircolariInps' );
				$("#attDettTabButt").hide();
			}
		
		}
		
		if(idTipo == 2){
			var checkedIds = $(".chkNoteDecreti:checked").map(function() {
				return this.value;

			}).toArray();
			// controlla checkbox selezionati
			if(!checkSelection(checkedIds)){
				return
			}else{
				var id= checkedIds[0];
				loadDiv('/CruscottoAuditAtpoWebWeb/getNormativaNoteDecreti?idAssociativa='+id, 'attivitaDettaglioNormativaDiv', null, 'initAllegatiNoteDecreti' );
				$("#attDettTabButt").hide();
			}
			
		}
		
		if(idTipo == 3){
			var checkedIds = $(".chkMessaggiInps:checked").map(function() {
				return this.value;
			}).toArray();
			// controlla checkbox selezionati
			if(!checkSelection(checkedIds)){
				return
			}else{
				var id= checkedIds[0];
				loadDiv('/CruscottoAuditAtpoWebWeb/getNormativaMessaggi?idAssociativa='+id, 'attivitaDettaglioNormativaDiv', null, 'initAllegatiMessaggiInps' );
				$("#attDettTabButt").hide();
			}
		}
		
		if(idTipo == 4){
			var checkedIds = $(".chkLeggiDecreti:checked").map(function() {
				return this.value;
			}).toArray();
			// controlla checkbox selezionati
			if(!checkSelection(checkedIds)){
				return
			}else{
				var id= checkedIds[0];
				loadDiv('/CruscottoAuditAtpoWebWeb/getLeggiDecreti?idAssociativa='+id, 'attivitaDettaglioNormativaDiv', null, 'initAllegatiLeggiDecreti' );
				$("#attDettTabButt").hide();
			}
		}
		
		break;
	}



}		





//apre e chiude l' aside
function showAsideIndicatori(){
	$('.modalAside').css({
		width: jQuery(document).width(),
		height: jQuery(document).height()

	});
	$('.modalAside').css("display", "block");
	jQuery('.menu-sx-levelone-facility.update').animate({marginRight:'0'}, 400);
	if(contOne){
		jQuery('.menu-sx-levelone-facility.update').animate({marginRight:'0'}, 400);
		contOne = false;
	}else{

		$('.modalAside').css("display", "none");
		contOne = true;
	}

}



//controlla quanti checkbox vengono premuti
function checkSelection(checkBoxSelected){
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

	else if(checkBoxSelected.length > 1){
		var settings = {
				theme: 'teal',
				sticky: false,
				horizontalEdge: 'top',
				verticalEdge: 'right',
				life: 5000
		};
		$.notific8('zindex', 11500);
		$.notific8('Selezionare un solo elemento ', settings);
		return false; 
	}
	return true;
}


//controlla quanti checkbox vengono premuti
function checkAtLeastOneSelection(checkBoxSelected){
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

//serve a ricaricare un tab dopo eventuali modifiche
//tipo risposta questionario cambia punteggio in tab indicatori
function reloadTabMenu(menu){
	switch(menu){
	case 'datiGenerali':

		var sessionId=$("#sessionID").val();
		// AuditorsAction.java
		//loadDiv('/CruscottoAuditAtpoWebWeb/modificaAccesso?idSessione='+sessionId, 'appView', null, 'initTablesModificaAuditor' );
		loadDiv('/CruscottoAuditAtpoWebWeb/modificaAccessoATPO?idSessione='+sessionId, 'appView', null, null );

		break;
	}

}

//refresh tabelle
function reloadTable(table){
	Metronic.startPageLoading();
	switch(table){
	case 'accessi':
		$('#accessi_table').DataTable().ajax.reload();

		break;

	case 'pratiche':
		$('#pratiche').DataTable().ajax.reload();

		break;

	case 'praticheConRischi':
		$('#pratiche_con_rischi').DataTable().ajax.reload();

		break;

	case 'generaleAudit':
		$('#generale_table').DataTable().ajax.reload();
		break;
	case 'generaleUsers':
		$('#users_table').DataTable().ajax.reload();
		break;
	case 'generaleIsnc':
		$('#isnc_table').DataTable().ajax.reload();
		break;
	case 'generaleTipologica':
		$('#tipologiche_table').DataTable().ajax.reload();
		break;
	case 'generaleCaricamentoPratiche':
		$('#caricamentopratiche_table').DataTable().ajax.reload();
		break;	
	case 'fasiTable':
		$('#fasi_table').DataTable().ajax.reload();
		break;

	case 'praticheSessioneTable':
		$('#praticheSessioneTable').DataTable().ajax.reload();
		break;
	case 'praticheATPO':
		$('#praticheATPO').DataTable().ajax.reload();

		break;
	case 'rischiEnonConformitaRischioTable':
		$('#rischiEnonConformitaRischioTable').DataTable().ajax.reload();
		$("#checkAll").prop("checked", false);
		break;
	case 'esprRischioTab':
		$('#espressRischioTable').DataTable().ajax.reload();
		$("#checkAllEspr").prop("checked", false);
		break;

	case 'rischiEnonConformitaNonConformTable':
		$('#rischiEnonConformitaNonConformTable').DataTable().ajax.reload();
		$("#selectAllNC").prop("checked", false);
		break;
	}
	Metronic.stopPageLoading();

}

//controllo date
function checkDate(dataInizio, dataFine){
	var di= dataInizio.split("/");
	var df= dataFine.split("/");
	var ggDi=di[0];
	var mmDi=di[1];
	var yyyyDi=di[2];
	var ggDf=df[0];
	var mmDf=df[1];
	var yyyyDf=df[2];

	if(yyyyDf < yyyyDi){
		showAlertDate();
		return;
	}
	if(yyyyDf == yyyyDi && mmDf < mmDi){
		showAlertDate();
		return;
	}
	if(yyyyDf == yyyyDi && mmDf == mmDi && ggDf < ggDi){
		showAlertDate();
		return;
	}


	return true;
}
//controllo inserimento decimali

function checkDecimal(number){
	if(number.indexOf(",")>=0){
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
//inizializza calendario
function initDataT(idData){
	var date = $('#'+idData).val();
	$('#'+idData).datepicker( {
		format : 'dd/mm/yyyy',
		autoclose : true
	}).focus();
}
function showAlertDate(){
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

//controlla che il valore in input sia un numerico intero
function isInteger(n){
	return $.isNumeric(n.replace('.', ','));
}


function getListaRuoliUtente(){
	$.ajax({
		"url":"/CruscottoAuditAtpoWebWeb/json/getRuoli",
		"dataType": "json",
		"success": function( json ) {
		var listaRuoli=json.roles;
		var ruoloAttivo=json.ruoloAttivo;
		listaRuoli.forEach(function(element) {
			var roleName=element.roleName;
			if(roleName==ruoloAttivo){
				$('#ruoloAttivo').text(" - "+roleName);
			}else{
				$('#listaRuoli').append(
						"<li>"+
						"<a href='#' onclick='switchActiveRole(this.id);' id='"+roleName+"'>"+ 
						"<i class='icon-user'></i> "+roleName+
						"</a>"+
						"</li>"
				);
			}
		});
//		$('#listaRuoli').append(
//				"<li>"+
//				"<a href='/CruscottoAuditAtpoWebWeb/idm.jsp' onclick='logout()'>"+
//				"<i class='icon-key'></i> Log Out"+
//				"</a>"+
//				"</li>"
//		)
	}
	});
}


function setSelectedTab(which){
	if(which=="audit"){
		$('#audit').addClass("start active");
		$('#generale').addClass("active");
	}
	if(which=="auditors"){
		$('#auditors').addClass("start active");
		$('#accessi').addClass("active");
	}
	if(which=="areaamministrativa"){
		$('#areaamministrativa').addClass("start active");
		$('#gestioneutenze').addClass("active");
	}
}

function switchActiveRole(selectedRole){
	$.ajax({
		"url":"/CruscottoAuditAtpoWebWeb/json/switchActiveRole",
		"data" :{ 
		'ruoloAttivo' : selectedRole
	},
	"success": function( json ) {
		window.location = "/CruscottoAuditAtpoWebWeb/index";
	}
	});

}

function checkDifferentDate(dataInizio, dataFine){
	var di= dataInizio.split("/");
	var df= dataFine.split("/");
	var ggDi=di[0];
	var mmDi=di[1];
	var yyyyDi=di[2];
	var ggDf=df[0];
	var mmDf=df[1];
	var yyyyDf=df[2];

	if(yyyyDf != yyyyDi){
		return;
	}
	if(yyyyDf == yyyyDi && mmDf != mmDi){
		return;
	}
	if(yyyyDf == yyyyDi && mmDf == mmDi && ggDf != ggDi){
		return;
	}


	return true;
}

function formatDateT(data){
	var dateDef="";
	if(data != null){
		var dateArray=data.split('T');
		var date= dateArray[0];
		
		var dateArr= date.split('-');
		var year= dateArr[0];
		var mounth= dateArr[1];
		var dd= dateArr[2];
		
		dateDef= dd+"/"+mounth+"/"+year
			}
	
	return dateDef;
	
	
}

