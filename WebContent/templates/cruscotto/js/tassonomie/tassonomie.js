var debugAttivo = 1;
var debugNonAttivo = 0;
var debugTassonomie = debugAttivo;
var currentTassonomia = '';

// LINK DATATABLES
var urlTableGlossario = contextApp + '/jsonATPO/getTassonomiaGlossarioTable';
var urlTableSinonimo = contextApp + '/jsonATPO/getTassonomiaSinonimoTable';
var urlTableAmbito = contextApp + '/jsonATPO/getTassonomiaAmbitoTable';
var urlTableEntita = contextApp + '/jsonATPO/getTassonomiaEntitaTable';
var urlGetSinonimo = contextApp + '/jsonATPO/getSinonimo'; 
var urlGetAmbito = contextApp + '/jsonATPO/getAmbito'; 

// URL PAGINA TAB
var urlTableGlossarioDiv = contextApp + '/getTassonomieGlossario';
var urlTableSinonimoDiv = contextApp + '/getTassonomieSinonimo';
var urlTableAmbitoDiv = contextApp + '/getTassonomieAmbito';
var urlTableEntitaDiv = contextApp + '/getTassonomieEntita';

var urlTableEntitaDetail = contextApp + '/getTassonomieSinonimoInserisci';
var urlTableAmbitoDetail = contextApp + '/getTassonomieAmbitoInserisci';

// URL CRUD
var urlSinonimoCrud =  contextApp + '/jsonATPO/<operazione>Tassonomie';
var urlGlossarioCrud =  contextApp + '/jsonATPO/<operazione>TassonomieGlossario';
var urlEntitaCrud =  contextApp + '/jsonATPO/<operazione>Tassonomie';
var urlAmbitoCrud =  contextApp + '/jsonATPO/<operazione>Tassonomie';

var chechedTassonomiaID = '';

var actualTableRendered;
var tipoOrerazioneCrud;

var idSinonimo;
var idAmbito;

var notificaTassonomiasettings = {
		theme: 'teal',
		sticky: false,
		horizontalEdge: 'top',
		verticalEdge: 'right',
		life: 3000
};

// TABLE 
var glossarioTable;
var sinonimoTable;
var ambitoTable;
var entitaTable;

var glossarioSinonimoTable;

function tdb(messaggio){
	if (debugTassonomie == debugAttivo)console.log(messaggio);
}

function caricaTipoTassonomie(combo){
	tdb(combo);
	currentTassonomia = combo[combo.selectedIndex].value;
	tdb("Tassonomia selezionata: " + currentTassonomia);
		
	if(currentTassonomia == 'sinonimo'){
		loadDiv(urlTableSinonimoDiv , 'tassonomieBody', null, 'initSinonimo');
		actualTableRendered = 'sinonimo';
	}else if(currentTassonomia == 'glossario'){
		loadDiv(urlTableGlossarioDiv, 'tassonomieBody', null, 'initGlossario');
		actualTableRendered = 'glossario';
	}else if(currentTassonomia == 'ambito'){
		loadDiv(urlTableAmbitoDiv, 'tassonomieBody', null, 'initAmbito');
		actualTableRendered = 'ambito';
	}else  if(currentTassonomia == 'entita'){
		loadDiv(urlTableEntitaDiv, 'tassonomieBody', null, 'initEntita');
		actualTableRendered = 'entita';
	}else{
		$('#tassonomieBody').empty();
		actualTableRendered = '';
	}
}

function formatDateForDataTable(data){
	if (data == null) {
		tdb('Format(undefined)');
		return '';
	}else{
		var anno = data.substring(0, 4);
		var mese = data.substring(5,7);
		var giorno = data.substring(8,10);
		tdb('Format(' + data + ')');
		return giorno+'/'+mese+'/'+anno;
	};
}

function initTassonomie(){
	tdb('called --> initTassonomie');
	tdb('urlTableGlossario --> ' + urlTableGlossario);
}

function initSinonimo(){
	tdb('called --> initSinonimo');
	sinonimoTable = $('#sinonimoTable').DataTable(
			{		"language" : {
						"sEmptyTable" : "Nessun dato presente nella tabella",
						"sInfo" : "Vista da _START_ a _END_ di _TOTAL_ elementi",
						"sInfoEmpty" : "Vista da 0 a 0 di 0 elementi",
						"sInfoFiltered" : "(filtrati da _MAX_ elementi totali)",
						"sInfoPostFix" : "",
						"sInfoThousands" : ",",
						"sLengthMenu" : "Visualizza _MENU_ elementi",
						"sLoadingRecords" : "Caricamento...",
						"sProcessing" : "Elaborazione...",
						"sSearch" : "Cerca:",
						"sZeroRecords" : "La ricerca non ha portato alcun risultato.",
						"oPaginate" : {
							"sFirst" : "Inizio",
							"sPrevious" : "Precedente",
							"sNext" : "Successivo",
							"sLast" : "Fine"
						},
						"Aria" : {
							"sSortAscending" : ": attiva per ordinare la colonna in ordine crescente",
							"sSortDescending" : ": attiva per ordinare la colonna in ordine decrescente"
						}
					},
					"order" : [ [ 0, 'asc' ] ],
					"lengthMenu" : [ 5, 10, 15, 20 ],
					"pageLength" : 5,
					"dom" : "<'row' <'col-md-12'T>><'row'<'col-md-6 col-sm-12'l><'col-md-6 col-sm-12'f>r><'table-scrollable't><'row'<'col-md-5 col-sm-12'i><'col-md-7 col-sm-12'p>>", // horizobtal
					"sAjaxSource" : urlTableSinonimo,  
					"fnServerParams": function ( aoData ) {						
						// ESEMPIO DI SEND AGGIUNTIVO
						//aoData.push({"name": "AdditionalParameterSearch_1", "value": {"name": "ID_S_SESSIONE", "value": "2"}} );
						//aoData.push({"name": "AdditionalParameterSearch_2", "value": {"name": "ID_S_RISCHIO", "value": "3"}} );
			        },
					"processing" : false,
					"serverSide" : true,
					"aoColumns" : [
									{	"mData" : "idSinonimo",
										"bSearchable" : false,
										"bSortable" : false,
										"fnCreatedCell": function(nTd, sData, oData, iRow, iCol)
										  {
		   									$(nTd).css('text-align', 'center');
		   									//$(nTd).css('display', 'none');
										  },										
										"mRender" : function(data, type, row) {
											var content='<div class="form-group form-md-checkboxes">';
											content += '<div class="md-checkbox-inline">';
											content += '<div class="md-checkbox">';
											content += '<input type="checkbox"  name="ids" id="chkSessCamp_'
													+ row.idSinonimo
													+ '" class="md-check chkSessCamp" value="'
													+ row.idSinonimo + '">';
											content += '<label for="chkSessCamp_' + row.idSinonimo + '">';
											content += '<span></span>';
											content += '<span class="check"></span>';
											content += '<span class="box"></span>';
											content += '</label>';
											content += '</div>';
											content += '</div>';
											content += '</div>';
											return content;
										}
									},
									{	"mData" : "sinonimo",
										"bSearchable" : false,
										"bSortable" : true
									},
									{	"mData" : "descSinonimo",
										"bSearchable" : false,
										"bSortable" : true
									},
									{	"mData" : "dataInizio",
										"bSearchable" : false,
										"bSortable" : true,										
										"mRender" : function(data, type, row) {
										return formatDateForDataTable(data);
										}
									},
									{	"mData" : "dataFine",
										"bSearchable" : false,
										"bSortable" : true,
										"mRender" : function(data, type, row) {
										return formatDateForDataTable(data);
										}
									}
					]
				}	
	);
}

function initGlossario(tableName){
	tdb('called --> initGlossario');
	let table = 'glossarioTable';
	if ( tableName != '' && tableName != undefined ){ table = tableName;}
	glossarioTable = $('#' + table).DataTable(
													{		"language" : {
																"sEmptyTable" : "Nessun dato presente nella tabella",
																"sInfo" : "Vista da _START_ a _END_ di _TOTAL_ elementi",
																"sInfoEmpty" : "Vista da 0 a 0 di 0 elementi",
																"sInfoFiltered" : "(filtrati da _MAX_ elementi totali)",
																"sInfoPostFix" : "",
																"sInfoThousands" : ",",
																"sLengthMenu" : "Visualizza _MENU_ elementi",
																"sLoadingRecords" : "Caricamento...",
																"sProcessing" : "Elaborazione...",
																"sSearch" : "Cerca:",
																"sZeroRecords" : "La ricerca non ha portato alcun risultato.",
																"oPaginate" : {
																	"sFirst" : "Inizio",
																	"sPrevious" : "Precedente",
																	"sNext" : "Successivo",
																	"sLast" : "Fine"
																},
																"Aria" : {
																	"sSortAscending" : ": attiva per ordinare la colonna in ordine crescente",
																	"sSortDescending" : ": attiva per ordinare la colonna in ordine decrescente"
																}
															},
															"order" : [ [ 0, 'asc' ] ],
															"lengthMenu" : [ 5, 10, 15, 20 ],
															"pageLength" : 5,
															"dom" : "<'row' <'col-md-12'T>><'row'<'col-md-6 col-sm-12'l><'col-md-6 col-sm-12'f>r><'table-scrollable't><'row'<'col-md-5 col-sm-12'i><'col-md-7 col-sm-12'p>>", // horizobtal
															"sAjaxSource" : urlTableGlossario,
															"processing" : false,
															"serverSide" : true,
															"aoColumns" : [
																			{	"mData" : "idGlossario",
																				"bSearchable" : false,
																				"bSortable" : false,
																				"fnCreatedCell": function(nTd, sData, oData, iRow, iCol)
																				  {
												   									$(nTd).css('text-align', 'center');
												   									//$(nTd).css('display', 'none');
																				  },										
																				"mRender" : function(data, type, row) {
																					var content='<div class="form-group form-md-checkboxes">';
																					content += '<div class="md-checkbox-inline">';
																					content += '<div class="md-checkbox">';
																					content += '<input type="checkbox"  name="ids" id="chkSessCamp_'
																							+ row.idGlossario
																							+ '" class="md-check chkSessCamp" value="'
																							+ row.idGlossario + '">';
																					content += '<label for="chkSessCamp_' + row.idGlossario + '">';
																					content += '<span></span>';
																					content += '<span class="check"></span>';
																					content += '<span class="box"></span>';
																					content += '</label>';
																					content += '</div>';
																					content += '</div>';
																					content += '</div>';
																					return content;
																				}
																			},
																			{	"mData" : "parola",
																				"bSearchable" : false,
																				"bSortable" : true
																			},
																			{	"mData" : "descParola",
																				"bSearchable" : false,
																				"bSortable" : true
																			},
																			{	"mData" : "dataInizio",
																				"bSearchable" : false,
																				"bSortable" : true,										
																				"mRender" : function(data, type, row) {
																				return formatDateForDataTable(data);
																				}
																			},
																			{	"mData" : "dataFine",
																				"bSearchable" : false,
																				"bSortable" : true,
																				"mRender" : function(data, type, row) {
																				return formatDateForDataTable(data);
																				}
																			}
															]
														}	
											);
}

function initAmbito(){
	tdb('called --> initAmbito');
	ambitoTable = $('#ambitoTable').DataTable(
			{		"language" : {
						"sEmptyTable" : "Nessun dato presente nella tabella",
						"sInfo" : "Vista da _START_ a _END_ di _TOTAL_ elementi",
						"sInfoEmpty" : "Vista da 0 a 0 di 0 elementi",
						"sInfoFiltered" : "(filtrati da _MAX_ elementi totali)",
						"sInfoPostFix" : "",
						"sInfoThousands" : ",",
						"sLengthMenu" : "Visualizza _MENU_ elementi",
						"sLoadingRecords" : "Caricamento...",
						"sProcessing" : "Elaborazione...",
						"sSearch" : "Cerca:",
						"sZeroRecords" : "La ricerca non ha portato alcun risultato.",
						"oPaginate" : {
							"sFirst" : "Inizio",
							"sPrevious" : "Precedente",
							"sNext" : "Successivo",
							"sLast" : "Fine"
						},
						"Aria" : {
							"sSortAscending" : ": attiva per ordinare la colonna in ordine crescente",
							"sSortDescending" : ": attiva per ordinare la colonna in ordine decrescente"
						}
					},
					"order" : [ [ 0, 'asc' ] ],
					"lengthMenu" : [ 5, 10, 15, 20 ],
					"pageLength" : 5,
					"dom" : "<'row' <'col-md-12'T>><'row'<'col-md-6 col-sm-12'l><'col-md-6 col-sm-12'f>r><'table-scrollable't><'row'<'col-md-5 col-sm-12'i><'col-md-7 col-sm-12'p>>", // horizobtal
					"sAjaxSource" : urlTableAmbito,
					"processing" : false,
					"serverSide" : true,
					"aoColumns" : [
									{	"mData" : "idAmbito",
										"bSearchable" : false,
										"bSortable" : false,
										"fnCreatedCell": function(nTd, sData, oData, iRow, iCol)
										  {
		   									$(nTd).css('text-align', 'center');
		   									//$(nTd).css('display', 'none');
										  },										
										"mRender" : function(data, type, row) {
											var content='<div class="form-group form-md-checkboxes">';
											content += '<div class="md-checkbox-inline">';
											content += '<div class="md-checkbox">';
											content += '<input type="checkbox" data-ambitoTipo=\'' + row.tipoAmbito + '\'  name="ids" id="chkSessCamp_'
													+ row.idAmbito
													+ '" class="md-check chkSessCamp" value="'
													+ row.idAmbito + '">';
											content += '<label for="chkSessCamp_' + row.idAmbito + '">';
											content += '<span></span>';
											content += '<span class="check"></span>';
											content += '<span class="box"></span>';
											content += '</label>';
											content += '</div>';
											content += '</div>';
											content += '</div>';
											return content;
										}
									},
									{	"mData" : "descAmbito",
										"bSearchable" : false,
										"bSortable" : true
									},
									{	"mData" : "dataInizio",
										"bSearchable" : false,
										"bSortable" : true,										
										"mRender" : function(data, type, row) {
										return formatDateForDataTable(data);
										}
									},
									{	"mData" : "dataFine",
										"bSearchable" : false,
										"bSortable" : true,
										"mRender" : function(data, type, row) {
										return formatDateForDataTable(data);
										}
									}
					]
				}	
	);
}

function initSinonimoGlossario(){
	tdb('called --> initGlossario');
	if (glossarioSinonimoTable === undefined){
		glossarioSinonimoTable = $('#glossarioSinonimoTable').DataTable(
													{		"language" : {
																"sEmptyTable" : "Nessun dato presente nella tabella",
																"sInfo" : "Vista da _START_ a _END_ di _TOTAL_ elementi",
																"sInfoEmpty" : "Vista da 0 a 0 di 0 elementi",
																"sInfoFiltered" : "(filtrati da _MAX_ elementi totali)",
																"sInfoPostFix" : "",
																"sInfoThousands" : ",",
																"sLengthMenu" : "Visualizza _MENU_ elementi",
																"sLoadingRecords" : "Caricamento...",
																"sProcessing" : "Elaborazione...",
																"sSearch" : "Cerca:",
																"sZeroRecords" : "La ricerca non ha portato alcun risultato.",
																"oPaginate" : {
																	"sFirst" : "Inizio",
																	"sPrevious" : "Precedente",
																	"sNext" : "Successivo",
																	"sLast" : "Fine"
																},
																"Aria" : {
																	"sSortAscending" : ": attiva per ordinare la colonna in ordine crescente",
																	"sSortDescending" : ": attiva per ordinare la colonna in ordine decrescente"
																}
															},
															"order" : [ [ 0, 'asc' ] ],
															"lengthMenu" : [ 5, 10, 15, 20 ],
															"pageLength" : 5,
															"dom" : "<'row' <'col-md-12'T>><'row'<'col-md-6 col-sm-12'l><'col-md-6 col-sm-12'f>r><'table-scrollable't><'row'<'col-md-5 col-sm-12'i><'col-md-7 col-sm-12'p>>", // horizobtal
															"sAjaxSource" : urlTableGlossario,
															"processing" : false,
															"serverSide" : true,
															"aoColumns" : [
																			{	"mData" : "idGlossario",
																				"bSearchable" : false,
																				"bSortable" : false,
																				"fnCreatedCell": function(nTd, sData, oData, iRow, iCol)
																				  {
												   									$(nTd).css('text-align', 'center');
												   									//$(nTd).css('display', 'none');
																				  },										
																				"mRender" : function(data, type, row) {
																					var content='<div class="form-group form-md-checkboxes">';
																					content += '<div class="md-checkbox-inline">';
																					content += '<div class="md-checkbox">';
																					content += '<input type="checkbox"  name="ids" id="chkSessCamp_'
																							+ row.idGlossario
																							+ '" class="md-check chkSessCamp" value="'
																							+ row.idGlossario + '">';
																					content += '<label for="chkSessCamp_' + row.idGlossario + '">';
																					content += '<span></span>';
																					content += '<span class="check"></span>';
																					content += '<span class="box"></span>';
																					content += '</label>';
																					content += '</div>';
																					content += '</div>';
																					content += '</div>';
																					return content;
																				}
																			},
																			{	"mData" : "parola",
																				"bSearchable" : false,
																				"bSortable" : true
																			},
																			{	"mData" : "descParola",
																				"bSearchable" : false,
																				"bSortable" : true
																			},
																			{	"mData" : "dataInizio",
																				"bSearchable" : false,
																				"bSortable" : true,										
																				"mRender" : function(data, type, row) {
																				return formatDateForDataTable(data);
																				}
																			},
																			{	"mData" : "dataFine",
																				"bSearchable" : false,
																				"bSortable" : true,
																				"mRender" : function(data, type, row) {
																				return formatDateForDataTable(data);
																				}
																			}
															]
														}	
											);
	}else{
		glossarioSinonimoTable.DataTable().ajax.reload();
	}
}

function initEntita(){
	tdb('called --> initEntita');
	entitaTable = $('#entitaTable').DataTable(
			{		"language" : {
						"sEmptyTable" : "Nessun dato presente nella tabella",
						"sInfo" : "Vista da _START_ a _END_ di _TOTAL_ elementi",
						"sInfoEmpty" : "Vista da 0 a 0 di 0 elementi",
						"sInfoFiltered" : "(filtrati da _MAX_ elementi totali)",
						"sInfoPostFix" : "",
						"sInfoThousands" : ",",
						"sLengthMenu" : "Visualizza _MENU_ elementi",
						"sLoadingRecords" : "Caricamento...",
						"sProcessing" : "Elaborazione...",
						"sSearch" : "Cerca:",
						"sZeroRecords" : "La ricerca non ha portato alcun risultato.",
						"oPaginate" : {
							"sFirst" : "Inizio",
							"sPrevious" : "Precedente",
							"sNext" : "Successivo",
							"sLast" : "Fine"
						},
						"Aria" : {
							"sSortAscending" : ": attiva per ordinare la colonna in ordine crescente",
							"sSortDescending" : ": attiva per ordinare la colonna in ordine decrescente"
						}
					},
					"order" : [ [ 0, 'asc' ] ],
					"lengthMenu" : [ 5, 10, 15, 20 ],
					"pageLength" : 5,
					"dom" : "<'row' <'col-md-12'T>><'row'<'col-md-6 col-sm-12'l><'col-md-6 col-sm-12'f>r><'table-scrollable't><'row'<'col-md-5 col-sm-12'i><'col-md-7 col-sm-12'p>>", // horizobtal
					"sAjaxSource" : urlTableEntita,
					"processing" : false,
					"serverSide" : true,
					"aoColumns" : [
									{	"mData" : "idEntita",
										"bSearchable" : false,
										"bSortable" : false,
										"fnCreatedCell": function(nTd, sData, oData, iRow, iCol)
										  {
		   									$(nTd).css('text-align', 'center');
		   									//$(nTd).css('display', 'none');
										  },										
										"mRender" : function(data, type, row) {
											var content='<div class="form-group form-md-checkboxes">';
											content += '<div class="md-checkbox-inline">';
											content += '<div class="md-checkbox">';
											content += '<input type="checkbox" data-entitaTipo="' +  row.tipoEntita + '" name="ids" id="chkSessCamp_'
													+ row.idEntita
													+ '" class="md-check chkSessCamp" value="'
													+ row.idEntita + '">';
											content += '<label for="chkSessCamp_' + row.idEntita + '">';
											content += '<span></span>';
											content += '<span class="check"></span>';
											content += '<span class="box"></span>';
											content += '</label>';
											content += '</div>';
											content += '</div>';
											content += '</div>';
											return content;
										}
									},
									{	"mData" : "entita",
										"bSearchable" : false,
										"bSortable" : true
									},
									{	"mData" : "descEntita",
										"bSearchable" : false,
										"bSortable" : true
									},
									{	"mData" : "dataInizio",
										"bSearchable" : false,
										"bSortable" : true,										
										"mRender" : function(data, type, row) {
										return formatDateForDataTable(data);
										}
									},
									{	"mData" : "dataFine",
										"bSearchable" : false,
										"bSortable" : true,
										"mRender" : function(data, type, row) {
										return formatDateForDataTable(data);
										}
									}
					]
				}	
	);
	

	
}

function getTassonomieCheckBoxID(tipoOperazione){
	tdb('Table rendered: ' + actualTableRendered );
		
	if (tipoOperazione === 'Aggiona'){
		tipoOrerazioneCrud = 'Aggiona';
		let tableToRefresh;
		if(currentTassonomia == 'glossario'){
			tableToRefresh = $('#glossarioTable');		
		}else if(currentTassonomia == 'sinonimo'){
			tableToRefresh = $('#sinonimoTable');	
		}else if(currentTassonomia == 'ambito'){
			tableToRefresh = $('#ambitoTable');	
		}else if(currentTassonomia == 'entita'){
			tableToRefresh = $('#entitaTable');	
		}
		Metronic.startPageLoading();
		tableToRefresh.DataTable().ajax.reload();
		Metronic.stopPageLoading();
		return;
	}
	
	if(currentTassonomia == 'glossario'){
		chechedTassonomiaID = $('#glossarioTable input[type="checkbox"]:checked');		
	}else if(currentTassonomia == 'sinonimo'){
		chechedTassonomiaID = $('#sinonimoTable input[type="checkbox"]:checked');
	}else if(currentTassonomia == 'ambito'){
		chechedTassonomiaID = $('#ambitoTable input[type="checkbox"]:checked');
	}else if(currentTassonomia == 'entita'){
		chechedTassonomiaID = $('#entitaTable input[type="checkbox"]:checked');
	}
		
	chechedTassonomiaID = $(chechedTassonomiaID);
	let idTassonomiaChecked = null;
	
	if (tipoOperazione != 'Inserisci' ){
		if (chechedTassonomiaID.length != 1) {
			if (chechedTassonomiaID.length === 0) {
				$.notific8('zindex', 11500);
				$.notific8('Selezionare un elemento ', notificaTassonomiasettings);
			}else{
				$.notific8('zindex', 11500);
				$.notific8('Selezionare un solo elemento ', notificaTassonomiasettings);				
			}
			return 	
		}
		idTassonomiaChecked = chechedTassonomiaID[0].value;	
	}

	if (tipoOperazione === 'Elimina') {
		tipoOrerazioneCrud = 'Elimina';
		// CASO DI ELIMINA
		let url = getTassonomiaEliminaCheckBoxID(currentTassonomia);
		let dati = null;
		if(currentTassonomia == 'glossario')dati = { 'glossarioID' : idTassonomiaChecked };
		if(currentTassonomia == 'entita')dati = { 'entitaID' : idTassonomiaChecked };
		if(currentTassonomia == 'ambito')dati = { 'ambitoID' : idTassonomiaChecked };
		if(currentTassonomia == 'sinonimo')dati = { 'sinonimoID' : idTassonomiaChecked };
		let messaggioSuccess = "Glossario inserito.";
		let messaggioError = "Errore nell'inserimento del glossario";
		if(currentTassonomia == 'glossario') tassonomiaPost(url, dati, "Eliminazione effettuata.", "Eliminazione non effettuata",'glossarioTable');
		if(currentTassonomia == 'entita') tassonomiaPost(url, dati, "Eliminazione effettuata.", "Eliminazione non effettuata",'entitaTable');
		if(currentTassonomia == 'ambito') tassonomiaPost(url, dati, "Eliminazione effettuata.", "Eliminazione non effettuata",'ambitoTable');
		if(currentTassonomia == 'sinonimo') tassonomiaPost(url, dati, "Eliminazione effettuata.", "Eliminazione non effettuata",'sinonimoTable');

	} else if (tipoOperazione === 'Modifica' || tipoOperazione === 'Inserisci' ){
		if(currentTassonomia == 'glossario'){
			slideAsideTassonomiaNew('glossario');
			if (tipoOperazione === 'Inserisci'){
				tipoOrerazioneCrud = 'Inserisci';
				// $('#tipoTassonomiaCombo')[0][$('#tipoTassonomiaCombo')[0].selectedIndex].value;
				$('#parolaGlossario').val('');
				$('#dataInizioGlossario').val('');
				$('#dataFineGlossario').val('');
				$('#descrizioneParolaGlossario').val('');
				$('#glossarioID').val('');
			}else{
				tipoOrerazioneCrud = 'Modifica';
				$('#glossarioID').val(idTassonomiaChecked); 
				$('#parolaGlossario').val($('#glossarioTable input[type="checkbox"]:checked').closest("TR")[0].cells[1].textContent);
				$('#dataInizioGlossario').val($('#glossarioTable input[type="checkbox"]:checked').closest("TR")[0].cells[3].textContent);
				$('#dataFineGlossario').val($('#glossarioTable input[type="checkbox"]:checked').closest("TR")[0].cells[4].textContent);
				$('#descrizioneParolaGlossario').val($('#glossarioTable input[type="checkbox"]:checked').closest("TR")[0].cells[2].textContent);
			}
		}else if(currentTassonomia == 'entita'){
			slideAsideTassonomiaNew('entita');
			if (tipoOperazione === 'Inserisci'){
				tipoOrerazioneCrud = 'Inserisci';
				$('#entitaID').val('');
				$('#entitaDesc').val('');
				$('#dataInizioEntita').val('');
				$('#dataFineEntita').val('');
				$('#descrizioneEntita').val('');
				$('#comboTipoEntita').val('');
			}else{
				tipoOrerazioneCrud = 'Modifica';
				// new Date(data.ambitoDataFine).toLocaleDateString()
				$('#entitaID').val(idTassonomiaChecked); 
				$('#entitaDesc').val($('#entitaTable input[type="checkbox"]:checked').closest("TR")[0].cells[1].textContent);
				$('#dataInizioEntita').val($('#entitaTable input[type="checkbox"]:checked').closest("TR")[0].cells[3].textContent);
				$('#dataFineEntita').val($('#entitaTable input[type="checkbox"]:checked').closest("TR")[0].cells[4].textContent);
				$('#descrizioneEntita').val($('#entitaTable input[type="checkbox"]:checked').closest("TR")[0].cells[2].textContent);
				$('#comboTipoEntita').val($('#entitaTable input[type="checkbox"]:checked')[0].getAttribute( "data-entitatipo" ));
			}
		}else if(currentTassonomia === 'sinonimo'){
			console.log('idTassonomiaChecked: ' + idTassonomiaChecked);
			idSinonimo = idTassonomiaChecked;
			loadDiv(urlTableEntitaDetail + '?id=' + idTassonomiaChecked  , 'appView', null, 'initSinonimoForInsertOrModify' );
		}else if(currentTassonomia === 'ambito'){
			idAmbito = idTassonomiaChecked;
			loadDiv(urlTableAmbitoDetail + '?id=' + idTassonomiaChecked  , 'appView', null, 'initAmbitoForInsertOrModify' );
		}
	}
}

function sizeModalTassonomiaCustom(){
    jQuery('.modal-custom').css({
        width: jQuery(document).width(),
        height: jQuery(document).height()
    });
}

function slideAsideTassonomiaNewChiudi(){
	var modal = $('#tipoTassonomiaCombo')[0][$('#tipoTassonomiaCombo')[0].selectedIndex].value;
	sizeModalCustom();
    jQuery('.menu-sx-levelone-facility.tassonomie.' + modal).animate( {marginRight: '-105%' }, 400);
    jQuery('.modal-custom').css("display", "none");
    contOne = true;
}

function slideAsideTassonomiaNew(e){
	Abilita('SI');
	if(currentTassonomia == 'glossario' || currentTassonomia == 'entita' ){
		sizeModalCustom();
		let modal = $('#tipoTassonomiaCombo')[0][$('#tipoTassonomiaCombo')[0].selectedIndex].value;

		//$('#tipoTassonomiaCombo')[0][$('#tipoTassonomiaCombo')[0].selectedIndex].value;
		
	    jQuery('.menu-sx-levelone-facility.tassonomie.' + modal).animate({marginRight:'0'}, 400);
	    jQuery('.modal-custom').css("display", "block");
	    contOne = false;	
	       
	}else if(currentTassonomia == 'sinonimo'){
		loadDiv(urlTableSinonimoDiv , 'appView', null, '');
	}else if(currentTassonomia == 'ambito'){
	    loadDiv(urlTableSinonimoDiv , 'appView', null, '');
	}
	
};

function getTassonomiaVisualizzaCheckBoxID(tipoTassonomia, id){
	tdb('getTassonomiaVisualizzaCheckBoxID');
}

function getTassonomiaModificaCheckBoxID(tipoTassonomia, id){
	tdb('getTassonomiaModificaCheckBoxID');
	return false;
}

function getTassonomiaEliminaCheckBoxID(tipoTassonomia){
	let url = '';
	if(tipoTassonomia == 'glossario'){
		url = urlGlossarioCrud.replace("<operazione>", "EliminaParola");
	} else if(tipoTassonomia == 'sinonimo'){
		url = urlSinonimoCrud.replace("<operazione>", "EliminaSinonimo");
	}else if(tipoTassonomia == 'ambito'){
		url = urlAmbitoCrud.replace("<operazione>", "EliminaAmbito");
	}else if(tipoTassonomia == 'entita'){
		url = urlEntitaCrud.replace("<operazione>", "EliminaEntita");
	}
	return url;
}

function Abilita(tipo){
	/*
	if (tipo === 'NO'){
		$('#salvaEntita').attr("disabled","disabled");
		$('#annullaEntita').attr("disabled","disabled");

		$('#salvaParola').attr("disabled","disabled");
		$('#annullaParola').attr("disabled","disabled");
	}else{

		$('#salvaEntita').removeAttr("disabled");
		$('#annullaEntita').removeAttr("disabled");

		$('#salvaParola').removeAttr("disabled");
		$('#annullaParola').removeAttr("disabled");
	}
	*/
}

function salvaTassonomia(){
	let modal = $('#tipoTassonomiaCombo')[0][$('#tipoTassonomiaCombo')[0].selectedIndex].value;
	Abilita('NO');
	if (check(modal)){
		// INSERIMENTO GLOSSSARIO
		if (modal === 'glossario'){
			let messaggioSuccess = "Glossario inserito correttamente.";
			let messaggioError = "Errore nell'inserimento del glossario";
			let url = null;
			
			if (tipoOrerazioneCrud == 'Inserisci'){
				url = urlGlossarioCrud.replace("<operazione>", "InserisciParola");	
			}else{
				url = urlGlossarioCrud.replace("<operazione>", "ModificaParola");
				messaggioSuccess = "Glossario modificato correttamente.";
				messaggioError = "Errore nella modifica del glossario";
			}
			
			let id = $('#glossarioID').val();
			let parola = $('#parolaGlossario').val();
			let dataInizio = $('#dataInizioGlossario').val();
			let dataFine = $('#dataFineGlossario').val();
			let descrizione = $('#descrizioneParolaGlossario').val();
			let dati = { glossarioID : id , 'glossarioParola' : parola , 'glossarioDescrizioneParola' : descrizione, 'glossarioDataInizio' : dataInizio, 'glossarioDataFine' : dataFine };
	
			tassonomiaPost(url, dati, messaggioSuccess, messaggioError,'glossarioTable');		
		}else if (modal === 'entita'){
			
			let messaggioSuccess = "Entità inserito correttamente.";
			let messaggioError = "Errore nell'inserimento dell'entità";
			let url = null;
			
			if (tipoOrerazioneCrud == 'Inserisci'){
				url = urlEntitaCrud.replace("<operazione>", "InserisciEntita");	
			}else{
				url = urlEntitaCrud.replace("<operazione>", "ModificaEntita");
				messaggioSuccess = "Entità modificato correttamente.";
				messaggioError = "Errore nella modifica dell'entità";
			}
			
			let id = $('#entitaID').val();
			let entita = $('#entitaDesc').val();
			let dataInizio =   $('#dataInizioEntita').val();
			let dataFine = $('#dataFineEntita').val();
			let descrizione =   $('#descrizioneEntita').val();
			let entitaTipo = $('#comboTipoEntita').val();
			let dati = { entitaID : id , 'entita' : entita , 'entitaDescrizione' : descrizione, 'entitaDataInizio' : dataInizio, 'entitaDataFine' : dataFine , 'entitaTipo' : entitaTipo};
			console.log('send: ' + dati);
			tassonomiaPost(url, dati, messaggioSuccess, messaggioError,'entitaTable');
		}
	}
}

function check(modal){
	if (modal === 'glossario'){
		if ($('#parolaGlossario').val() === '' || $('#parolaGlossario').val() === undefined){
			$.notific8('zindex', 11500);
			$.notific8('La parola è obbligatoria', notificaTassonomiasettings);
			return false;
		}
		
		if ($('#dataInizioGlossario').val() === '' || $('#dataInizioGlossario').val() === undefined){
			$.notific8('zindex', 11500);
			$.notific8('La data inizio è obbligatoria', notificaTassonomiasettings);
			return false;
		}
		
		let mess = checkDateInizioFine($('#dataInizioGlossario').val(), $('#dataFineGlossario').val());
		if(mess != ''){
			$.notific8('zindex', 11500);
			$.notific8(mess, notificaTassonomiasettings);
			return false;			
		}
		return true;
	}else if (modal === 'entita'){
		if ($('#entitaDesc').val() === '' || $('#entitaDesc').val() === undefined){
			$.notific8('zindex', 11500);
			$.notific8('L\'entità è obbligatoria', notificaTassonomiasettings);
			return false;
		}
		if ($('#comboTipoEntita').val() === '' || $('#comboTipoEntita').val() === undefined){
			$.notific8('zindex', 11500);
			$.notific8('L\'entità tipo è obbligatoria', notificaTassonomiasettings);
			return false;
		}

		if ($('#dataInizioEntita').val() === '' || $('#dataInizioEntita').val() === undefined){
			$.notific8('zindex', 11500);
			$.notific8('La data inizio è obbligatoria', notificaTassonomiasettings);
			return false;
		}
		
		let mess = checkDateInizioFine($('#dataInizioEntita').val(), $('#dataFineEntita').val());
		if(mess != ''){
			$.notific8('zindex', 11500);
			$.notific8(mess, notificaTassonomiasettings);
			return false;			
		}
		return true;
	}
	
	
	return false;
}

function checkDateInizioFine(dataInizio, dataFine){
	if (dataInizio === '' && dataFine === '') return '';
	
	if (isNotNull(dataInizio) && isNotNull(dataFine) ){
		
		let checkDataInizio = checkData(dataInizio);
		let checkDataFine = checkData(dataFine);
		
		if (checkDataInizio != '' && checkDataFine != ''){
			
			return 'Le date non sono valide';
		}else if (checkDataInizio != ''){
			return 'Le data inizio non è valida';
		}else if (checkDataFine != ''){
			return 'Le data fine non è valida';
		}else{
			let diff = checkDataBefore(dataInizio, dataFine );
			if (diff != ''){
				return 'La data fine e antecedente alla data fine data inizio.';		
			}
		}
	}else if (isNotNull(dataInizio)){
		let checkDataInizio = checkData(dataInizio);
		if (checkDataInizio != '') return checkDataInizio;
	}else{
		return 'Non è possibile valorizzare la data fine senza valorizzare la data inizio.';
	}
	return '';
}

function normalizeDate(data){
	let res = data.split("/");
	let dateParsed = res[1] + '/' + res[0]+ '/' + res[2];
	return dateParsed;
}

function checkData(data){
	let dateParsed = normalizeDate(data);
	let dt = Date.parse(dateParsed);
	if (isNaN(dt)){
		return 'Non è una data valida.';
	}
	return '';
}

function checkDataBefore(dataInizio, datafine){
	let dataInizioParsed = normalizeDate(dataInizio);
	let datafineParsed = normalizeDate(datafine);
	let dbegin = Date.parse(dataInizioParsed);
	let dEnd = Date.parse(datafineParsed);
	if (dbegin > dEnd )return 'Data fine più grande';
	return '';
}

function isNotNull(valore){
	if (valore === undefined || valore === '' || valore === null) return false;
	return true;
}

function tassonomiaPost(url, dati, messaggioSuccess, messaggioError, tableRefresh ){
	$.ajax( {
		type : 'POST',
		url : url,
		data :dati,
		success : function(data){
			if ( data.status === 0){
				slideAsideTassonomiaNewChiudi();
				$.notific8('zindex', 11500);
				$.notific8(messaggioSuccess, notificaTassonomiasettings);
				if(tableRefresh != ''){
					let tableToRefresh = $('#' + tableRefresh);
					tableToRefresh.DataTable().ajax.reload();
				}
				Abilita('SI');
			}else{
				$.notific8('zindex', 11500);
				$.notific8(data.errorDescription, notificaTassonomiasettings);
			}
			return;
		},error: function(data){
			$.notific8('zindex', 11500);
			$.notific8(messaggioError, notificaTassonomiasettings);
			Abilita('SI');
			return; 
		}
	});
}

function salvaSinonimo(from){
	
	let glossarioID = $('#glossarioID').val();
	let sinonimoID = $('#sinonimoID').val();
	let sinonimo = $('#sinonimo').val();
	let sinonimoDesr = $('#descrizioneSinonimo').val();
	let sinonimoDataInizio = $('#dataInizioSinonimo').val();
	let sinonimoDataFine = $('#dataFineSinonimo').val();
	let messaggioSuccess  = 'Inserimento sinonimo correttamente.';
	let messaggioError  = '';
	
	// QUI METTO I CHECK
	if (sinonimo === '' || sinonimo === undefined){
		$.notific8('zindex', 11500);
		$.notific8('Il sinonimo è obbligatoria', notificaTassonomiasettings);
		return false;
	}
	
	if (glossarioID === '' || glossarioID === undefined){
		$.notific8('zindex', 11500);
		$.notific8('La parola è obbligatoria', notificaTassonomiasettings);
		return false;
	}
	
	if (sinonimoDataInizio === '' || sinonimoDataInizio === undefined){
		$.notific8('zindex', 11500);
		$.notific8('La data inizio è obbligatoria', notificaTassonomiasettings);
		return false;
	}
	
	let mess = checkDateInizioFine( sinonimoDataInizio, sinonimoDataFine );
	if(mess != ''){
		$.notific8('zindex', 11500);
		$.notific8(mess, notificaTassonomiasettings);
		return false;			
	}
	
	let url = '';
	if( sinonimoID == ''){
		url = urlSinonimoCrud.replace("<operazione>", "InserisciSinonimo");
	}else{
		url = urlSinonimoCrud.replace("<operazione>", "ModificaSinonimo");
	}
	
	let dati = { 'glossarioID' : glossarioID , 
				 'sinonimoID' : sinonimoID , 
				 'sinonimo' : sinonimo, 
				 'sinonimoDesr' : sinonimoDesr, 
				 'sinonimoDataInizio' : sinonimoDataInizio , 
				 'sinonimoDataFine' : sinonimoDataFine};

	$.ajax( {
		type : 'POST',
		url : url,
		data :dati,
		success : function(data){
			if ( data.status === 0){
				$.notific8('zindex', 11500);
				$.notific8(messaggioSuccess, notificaTassonomiasettings);
				idSinonimo = '';
				buttonIndietro(from);
			}else{
				$.notific8('zindex', 11500);
				$.notific8(messaggioError, notificaTassonomiasettings);
			}
			return;
		},error: function(data){
			$.notific8('zindex', 11500);
			$.notific8(messaggioError, notificaTassonomiasettings);
			return; 
		}
	});
	
}

function salvaAmbito(from){
	/*
	$('#ambitoID').val(data.ambitoID);
	$('#ambitoEntitaID').val(data.entitaID);
	$('#ambitoEntita').val(data.entita);
	$('#ambitoGlossarioID').val(data.glossarioID);
	$('#ambitoParola').val(data.glossarioParola);
	$('#dataInizioAmbito').val(data.ambitoDataInizio);
	$('#dataFineAmbito').val(data.ambitoDataFine);
	$('#comboTipoAmbito').val(data.ambitoTipo);
	$('#descrizioneAmbito').val(data.ambitoDescrizione);
	*/
	let ambitoID = $('#ambitoID').val();
	let entitaID = $('#ambitoEntitaID').val();
	let entita = $('#ambitoEntita').val();
	let glossarioID = $('#ambitoGlossarioID').val();
	let parola = $('#ambitoParola').val();
	let dataInizioAmbito = $('#dataInizioAmbito').val();
	let dataFineAmbito = $('#dataFineAmbito').val();
	let comboTipoAmbito = $('#comboTipoAmbito').val();
	let descrizioneAmbito = $('#descrizioneAmbito').val();
	let messaggioSuccess  = 'Inserimento ambito correttamente.';
	let messaggioError  = 'Errore nell\'operazione';
	
	// QUI METTO I CHECK
	if (entita === '' || entita === undefined){
		$.notific8('zindex', 11500);
		$.notific8('I\'entita\' è obbligatorio', notificaTassonomiasettings);
		return false;
	}
	
	if (glossarioID === '' || glossarioID === undefined){
		$.notific8('zindex', 11500);
		$.notific8('La parola è obbligatoria.', notificaTassonomiasettings);
		return false;
	}
	
	if (comboTipoAmbito === '' || comboTipoAmbito === undefined){
		$.notific8('zindex', 11500);
		$.notific8('Il tipo ambito è obbligatorio', notificaTassonomiasettings);
		return false;
	}
	
	if (dataInizioAmbito === '' || dataInizioAmbito === undefined){
		$.notific8('zindex', 11500);
		$.notific8('La data inizio è obbligatoria', notificaTassonomiasettings);
		return false;
	}
	
	let mess = checkDateInizioFine( dataInizioAmbito, dataFineAmbito );
	if(mess != ''){
		$.notific8('zindex', 11500);
		$.notific8(mess, notificaTassonomiasettings);
		return false;			
	}
	
	let url = '';
	if( ambitoID == ''){
		url = urlAmbitoCrud.replace("<operazione>", "InserisciAmbito");
	}else{
		url = urlAmbitoCrud.replace("<operazione>", "ModificaAmbito");
	}
	
	let dati = { 'ambitoID' : ambitoID ,
				 'entitaID' : entitaID , 
				 'entita' : entita , 
				 'glossarioID' : glossarioID, 
				 'glossarioParola' : parola, 
				 'ambitoDataInizio' : dataInizioAmbito , 
				 'ambitoDataFine' : dataFineAmbito,
				 'ambitoDescrizione' : descrizioneAmbito,
				 'ambitoTipo' : comboTipoAmbito
				};
	
	$.ajax( {
		type : 'POST',
		url : url,
		data :dati,
		success : function(data){
			if ( data.status === 0){
				$.notific8('zindex', 11500);
				$.notific8(messaggioSuccess, notificaTassonomiasettings);
				idSinonimo = '';
				buttonIndietro(from);
			}else{
				$.notific8('zindex', 11500);
				$.notific8(messaggioError, notificaTassonomiasettings);
			}
			return;
		},error: function(data){
			$.notific8('zindex', 11500);
			$.notific8(messaggioError, notificaTassonomiasettings);
			return; 
		}
	});
	
}

function buttonIndietro(from){
	currentTassonomia = from;
	loadDiv('/CruscottoAuditAtpoWebWeb/getTassonomie', 'appView', null, 'initTassonomieFrom' );
}

function initTassonomieFrom(){
	$('#tipoTassonomiaCombo').val(currentTassonomia);
	$('#tipoTassonomiaCombo').change();
}

function initSinonimoForInsertOrModify(id){
	glossarioTable = $('#glossarioSinonimoTable').DataTable(
				{		"language" : {
							"sEmptyTable" : "Nessun dato presente nella tabella",
							"sInfo" : "Vista da _START_ a _END_ di _TOTAL_ elementi",
							"sInfoEmpty" : "Vista da 0 a 0 di 0 elementi",
							"sInfoFiltered" : "(filtrati da _MAX_ elementi totali)",
							"sInfoPostFix" : "",
							"sInfoThousands" : ",",
							"sLengthMenu" : "Visualizza _MENU_ elementi",
							"sLoadingRecords" : "Caricamento...",
							"sProcessing" : "Elaborazione...",
							"sSearch" : "Cerca Glossario:",
							"sZeroRecords" : "La ricerca non ha portato alcun risultato.",
							"oPaginate" : {
								"sFirst" : "Inizio",
								"sPrevious" : "Precedente",
								"sNext" : "Successivo",
								"sLast" : "Fine"
							},
							"Aria" : {
								"sSortAscending" : ": attiva per ordinare la colonna in ordine crescente",
								"sSortDescending" : ": attiva per ordinare la colonna in ordine decrescente"
							}
						},
						"order" : [ [ 0, 'asc' ] ],
						"lengthMenu" : [ 5, 10, 15, 20 ],
						"pageLength" : 5,
						"dom" : "<'row' <'col-md-12'T>><'row'<'col-md-6 col-sm-12'l><'col-md-6 col-sm-12'f>r><'table-scrollable't><'row'<'col-md-5 col-sm-12'i><'col-md-7 col-sm-12'p>>", // horizobtal
						"sAjaxSource" : urlTableGlossario,
						"processing" : false,
						"serverSide" : true,
						"aoColumns" : [
										{	"mData" : "idGlossario",
											"bSearchable" : false,
											"bSortable" : false,
											"fnCreatedCell": function(nTd, sData, oData, iRow, iCol)
											  {
			   									$(nTd).css('text-align', 'center');
			   									//$(nTd).css('display', 'none');
											  },										
											"mRender" : function(data, type, row) {
												var content='<div class="form-group form-md-checkboxes">';
												content += '<div class="md-checkbox-inline">';
												content += '<div class="md-checkbox">';
												content += '<input type="checkbox" data-parola=\''+ row.parola +'\' onclick=\'SinonimoTablehandleClick(this);\' name="ids" id="chkSessCamp_'
														+ row.idGlossario
														+ '" class="md-check chkSessCamp" value="'
														+ row.idGlossario + '">';
												content += '<label for="chkSessCamp_' + row.idGlossario + '">';
												content += '<span></span>';
												content += '<span class="check"></span>';
												content += '<span class="box"></span>';
												content += '</label>';
												content += '</div>';
												content += '</div>';
												content += '</div>';
												return content;
											}
										},
										{	"mData" : "parola",
											"bSearchable" : false,
											"bSortable" : true
										},
										{	"mData" : "descParola",
											"bSearchable" : false,
											"bSortable" : true
										},
										{	"mData" : "dataInizio",
											"bSearchable" : false,
											"bSortable" : true,										
											"mRender" : function(data, type, row) {
											return formatDateForDataTable(data);
											}
										},
										{	"mData" : "dataFine",
											"bSearchable" : false,
											"bSortable" : true,
											"mRender" : function(data, type, row) {
											return formatDateForDataTable(data);
											}
										}
						]
					}	
		);
		if (idSinonimo === '' || idSinonimo === null ){
			console.log('-------------------------------> Sinonimo inserimento');
		}else{
			console.log('-------------------------------> Sinonimo modificare id: ' + idSinonimo );
			// getSinonimo
			$.ajax( {
				type : 'POST',
				url : urlGetSinonimo,
				data : { 'sinonimoID': idSinonimo},
				success : function(data){
					console.log("getSinonimo OK");
					popolaCampi('sinonimo', data);
					return;
				},error: function(data){
					console.log("getSinonimo NON OK");
					return; 
				}
			});
		}
		
}

function initAmbitoForInsertOrModify(){
	let tableGlossario = $('#glossarioAmbitoTable').DataTable(
			{		"language" : {
						"sEmptyTable" : "Nessun dato presente nella tabella",
						"sInfo" : "Vista da _START_ a _END_ di _TOTAL_ elementi",
						"sInfoEmpty" : "Vista da 0 a 0 di 0 elementi",
						"sInfoFiltered" : "(filtrati da _MAX_ elementi totali)",
						"sInfoPostFix" : "",
						"sInfoThousands" : ",",
						"sLengthMenu" : "Visualizza _MENU_ elementi",
						"sLoadingRecords" : "Caricamento...",
						"sProcessing" : "Elaborazione...",
						"sSearch" : "Cerca parola:",
						"sZeroRecords" : "La ricerca non ha portato alcun risultato.",
						"oPaginate" : {
							"sFirst" : "Inizio",
							"sPrevious" : "Precedente",
							"sNext" : "Successivo",
							"sLast" : "Fine"
						},
						"Aria" : {
							"sSortAscending" : ": attiva per ordinare la colonna in ordine crescente",
							"sSortDescending" : ": attiva per ordinare la colonna in ordine decrescente"
						}
					},
					"order" : [ [ 0, 'asc' ] ],
					"lengthMenu" : [ 5, 10, 15, 20 ],
					"pageLength" : 5,
					"dom" : "<'row' <'col-md-12'T>><'row'<'col-md-6 col-sm-12'l><'col-md-6 col-sm-12'f>r><'table-scrollable't><'row'<'col-md-5 col-sm-12'i><'col-md-7 col-sm-12'p>>", // horizobtal
					"sAjaxSource" : urlTableGlossario,
					"processing" : false,
					"serverSide" : true,
					"aoColumns" : [
									{	"mData" : "idGlossario",
										"bSearchable" : false,
										"bSortable" : false,
										"fnCreatedCell": function(nTd, sData, oData, iRow, iCol)
										  {
		   									$(nTd).css('text-align', 'center');
		   									//$(nTd).css('display', 'none');
										  },										
										"mRender" : function(data, type, row) {
											var content='<div class="form-group form-md-checkboxes">';
											content += '<div class="md-checkbox-inline">';
											content += '<div class="md-checkbox">';
											content += '<input type="checkbox" data-parola=\''+ row.parola +'\' onclick=\'AmbitoParolaTablehandleClick(this);\' name="ids" id="chkSessCamp_'
													+ row.idGlossario
													+ '" class="md-check chkSessCamp" value="'
													+ row.idGlossario + '">';
											content += '<label for="chkSessCamp_' + row.idGlossario + '">';
											content += '<span></span>';
											content += '<span class="check"></span>';
											content += '<span class="box"></span>';
											content += '</label>';
											content += '</div>';
											content += '</div>';
											content += '</div>';
											return content;
										}
									},
									{	"mData" : "parola",
										"bSearchable" : false,
										"bSortable" : true
									}
					]
				}	
	);
	
	
	let entitaAmbitoTable =  $('#entitaAmbitoTable').DataTable(
						{		"language" : {
							"sEmptyTable" : "Nessun dato presente nella tabella",
							"sInfo" : "Vista da _START_ a _END_ di _TOTAL_ elementi",
							"sInfoEmpty" : "Vista da 0 a 0 di 0 elementi",
							"sInfoFiltered" : "(filtrati da _MAX_ elementi totali)",
							"sInfoPostFix" : "",
							"sInfoThousands" : ",",
							"sLengthMenu" : "Visualizza _MENU_ elementi",
							"sLoadingRecords" : "Caricamento...",
							"sProcessing" : "Elaborazione...",
							"sSearch" : "Cerca Entità:",
							"sZeroRecords" : "La ricerca non ha portato alcun risultato.",
							"oPaginate" : {
								"sFirst" : "Inizio",
								"sPrevious" : "Precedente",
								"sNext" : "Successivo",
								"sLast" : "Fine"
							},
							"Aria" : {
								"sSortAscending" : ": attiva per ordinare la colonna in ordine crescente",
								"sSortDescending" : ": attiva per ordinare la colonna in ordine decrescente"
							}
						},
						"order" : [ [ 0, 'asc' ] ],
						"lengthMenu" : [ 5, 10, 15, 20 ],
						"pageLength" : 5,
						"dom" : "<'row' <'col-md-12'T>><'row'<'col-md-6 col-sm-12'l><'col-md-6 col-sm-12'f>r><'table-scrollable't><'row'<'col-md-5 col-sm-12'i><'col-md-7 col-sm-12'p>>", // horizobtal
						"sAjaxSource" : urlTableEntita,
						"processing" : false,
						"serverSide" : true,
						"aoColumns" : [
										{	"mData" : "idEntita",
											"bSearchable" : false,
											"bSortable" : false,
											"fnCreatedCell": function(nTd, sData, oData, iRow, iCol)
											  {
			   									$(nTd).css('text-align', 'center');
			   									//$(nTd).css('display', 'none');
											  },										
											"mRender" : function(data, type, row) {
												var content='<div class="form-group form-md-checkboxes">';
												content += '<div class="md-checkbox-inline">';
												content += '<div class="md-checkbox">';
												content += '<input type="checkbox" data-entita="' +  row.entita + '" onclick=\'AmbitoEntitaTablehandleClick(this);\' name="ids" id="chkSessCamp_'
														+ row.idEntita
														+ '" class="md-check chkSessCamp" value="'
														+ row.idEntita + '">';
												content += '<label for="chkSessCamp_' + row.idEntita + '">';
												content += '<span></span>';
												content += '<span class="check"></span>';
												content += '<span class="box"></span>';
												content += '</label>';
												content += '</div>';
												content += '</div>';
												content += '</div>';
												return content;
											}
										},
										{	"mData" : "entita",
											"bSearchable" : false,
											"bSortable" : true
										}
						]
					}	
			);
	
	
	if (idAmbito === '' || idAmbito === null ){
		console.log('-------------------------------> Ambito inserimento');
	}else{
		console.log('-------------------------------> Ambito modificare id: ' + idAmbito );

		$.ajax( {
			type : 'POST',
			url : urlGetAmbito,
			data : { 'ambitoID': idAmbito},
			success : function(data){
				console.log("getAmbito OK");
				popolaCampi('ambito', data);
				return;
			},error: function(data){
				console.log("getAmbito NON OK");
				return; 
			}
		});
	}
}

function SinonimoTablehandleClick(check){
	
	let chechedTassonomiaID = $('#glossarioSinonimoTable input[type="checkbox"]:checked');		
	if (chechedTassonomiaID.length == 1) {
		let chechedTassonomiaID = $('#glossarioSinonimoTable input[type="checkbox"]:checked');		
		$('#glossarioID').val(chechedTassonomiaID[0].value);
		$('#parola').val(chechedTassonomiaID[0].getAttribute( "data-parola" )); 	
	}else if(chechedTassonomiaID.length == 0){
		$('#glossarioID').val('');
		$('#parola').val(''); 
	}
	
}

function AmbitoEntitaTablehandleClick(check){
	
	let chechedTassonomiaID = $('#entitaAmbitoTable input[type="checkbox"]:checked');		
	if (chechedTassonomiaID.length == 1) {
		let chechedTassonomiaID = $('#entitaAmbitoTable input[type="checkbox"]:checked');		
		$('#ambitoEntitaID').val(chechedTassonomiaID[0].value);
		$('#ambitoEntita').val(chechedTassonomiaID[0].getAttribute( "data-entita" )); 	
	}else if(chechedTassonomiaID.length == 0){
		$('#ambitoEntitaID').val('');
		$('#ambitoEntita').val(''); 
	}
	console.log('AmbitoEntitaTablehandleClick(check)');
	
}

function AmbitoParolaTablehandleClick(check){

	let chechedTassonomiaID = $('#glossarioAmbitoTable input[type="checkbox"]:checked');		
	if (chechedTassonomiaID.length == 1) {
		let chechedTassonomiaID = $('#glossarioAmbitoTable input[type="checkbox"]:checked');		
		$('#ambitoGlossarioID').val(chechedTassonomiaID[0].value);
		$('#ambitoParola').val(chechedTassonomiaID[0].getAttribute( "data-parola" )); 	
	}else if(chechedTassonomiaID.length == 0){
		$('#ambitoGlossarioID').val('');
		$('#ambitoParola').val(''); 
	}
	console.log('AmbitoParolaTablehandleClick(check)');
}


function popolaCampi(tipoMaschera, data){
	
	if (tipoMaschera === 'sinonimo'){
		$('#glossarioID').val(data.glossarioID);
		$('#parola').val(data.glossarioParola);
		$('#sinonimoID').val(data.sinonimoID);
		$('#sinonimo').val(data.sinonimo);
		$('#descrizioneSinonimo').val(data.sinonimoDesr);
		$('#dataInizioSinonimo').val(new Date(data.sinonimoDataInizio).toLocaleDateString());
		$('#dataFineSinonimo').val(new Date(data.sinonimoDataFine).toLocaleDateString());
	}
	
	if (tipoMaschera === 'ambito'){
		$('#ambitoID').val(data.ambitoID);
		$('#ambitoEntitaID').val(data.entitaID);
		$('#ambitoEntita').val(data.entita);
		$('#ambitoGlossarioID').val(data.glossarioID);
		$('#ambitoParola').val(data.glossarioParola);
		$('#dataInizioAmbito').val(new Date(data.ambitoDataInizio).toLocaleDateString());
		$('#dataFineAmbito').val(new Date(data.ambitoDataFine).toLocaleDateString());
		$('#comboTipoAmbito').val(data.ambitoTipo);
		$('#descrizioneAmbito').val(data.ambitoDescrizione);
	}
	
}

