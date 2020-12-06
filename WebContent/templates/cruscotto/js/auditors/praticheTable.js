/*
 * TABELLA PRATICHE
 * (modifica-accessi tab2)
 */

var Pratiche = function() {

	var initTable1 = function() {

		var table = $('#pratiche');

		var oTable = table
				.dataTable( {

					// Internationalisation. For more info refer to
					// http://datatables.net/manual/i18n
					"language" : {
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
				
					// set the initial value
					"pageLength" : 5,

					"dom" : "<'row' <'col-md-12'T>><'row'<'col-md-6 col-sm-12'l><'col-md-6 col-sm-12'f>r><'table-scrollable't><'row'<'col-md-5 col-sm-12'i><'col-md-7 col-sm-12'p>>", // horizobtal
					
					"processing" : false,
					"serverSide" : true,
					//PraticaTableAction.java
					"sAjaxSource" : "/CruscottoAuditAtpoWebWeb/json/searchPratiche",
					"fnServerData" : function(sSource, aoData, fnCallback) {

						// aggiunge ai dati l idSessione
						var idSessione = $("#sessionID").val();
						aoData.push( {
							"name" : "sessionId",
							"value" : idSessione
						});

						$.ajax( {
							"url" : sSource,
							"dataType" : "json",
							"data" : aoData,
							"success" : fnCallback

						});

					},

					"aoColumns" : [
							{
								"mData" : "idSessione",
								"bSearchable" : false,
								"bSortable" : false,
								"fnCreatedCell" : function(nTd, sData, oData,
										iRow, iCol) {
									$(nTd).css('text-align', 'center');
								},

								// `data` refers to the data for the cell
								// (defined by `mData`, which
								// defaults to the column being worked with, in
								// this case is the first
								// Using `row[0]` is equivalent.
								"mRender" : function(data, type, row) {

									var content = '<div class="form-group form-md-checkboxes">';
									content += '<div class="md-checkbox-inline">';
									content += '<div class="md-checkbox">';
									content += '<input type="checkbox"  name="ids" id="checkboxTable_'
											+ row.idVerbale
											+ '" class="md-check chk" value="'
											+ row.idVerbale + '">';
									
									content += '<label for="checkboxTable_' + row.idVerbale + '">';
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

							{
								"mData" : "protocollo",
								"bSearchable" : true,
								"bSortable" : true
							}, {
								"mData" : "azienda",
								"bSearchable" : true,
								"bSortable" : true
							}, {
								"mData" : "codiceFiscale",
								"bSearchable" : true,
								"bSortable" : true
							}, {
								"mData" : "dataFineIspezione",
								"bSearchable" : true,
								"bSortable" : true
							}, {
								"mData" : "dataNotifica",
								"bSearchable" : true,
								"bSortable" : true
							},

							{
								"mData" : "tipoNotifica",
								"bSearchable" : true,
								"bSortable" : true
							
							},

							{
								"mData" : "importo",
								"bSearchable" : true,
								"bSortable" : true
							}, 
					]
				});

	}

	return {

		// main function to initiate the module
		init : function() {
			if (!jQuery().dataTable) {
				return;
			}
			initTable1();
		}

	};

}();
