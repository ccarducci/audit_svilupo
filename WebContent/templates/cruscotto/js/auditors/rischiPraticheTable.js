/*
 * TABELLA FASI AUDIT GENERALE
 */

var RischiPraticheTable = function() {

	var initTable1 = function() {
		var table = $('#rischiPraticheTable');
		
		

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

					"lengthMenu" : [ [ 5, 15, 20, -1 ], [ 5, 15, 20, "All" ] // change
					
					],
					// set the initial value
					"pageLength" : 5,

					
					"sAjaxSource" : "/CruscottoAuditAtpoWebWeb/json/searchRischiPratiche",
					"processing" : false,
					"serverSide" : true,

					"aoColumns" : [
							
							{
								"mData" : "descrizione",
								"bSearchable" : true,
								"bSortable" : true
							},
							
							{
								"mData" : "importoContrNonIncass",
								"bSearchable" : true,
								"bSortable" : true
							},
							
							{
								"mData" : "importoIndebSospeso",
								"bSearchable" : true,
								"bSortable" : true
							}, 
							
							{
								"mData" : "importoPrescritto",
								"bSearchable" : true,
								"bSortable" : true
							}, 
							{
								"mData" : "manifestazioneRischio",
								"bSearchable" : true,
								"bSortable" : true
							},
							{
								"mData" : "rm",
								"bSearchable" : true,
								"bSortable" : true
							},
							{
								"mData" : "edu",
								"bSearchable" : true,
								"bSortable" : true
							},
							{
								"mData" : "dataAttribuzione",
								"bSearchable" : true,
								"bSortable" : true
							}
							

					]
				});

		var tableWrapper = $('#sample_1_wrapper');

		tableWrapper.find('.dataTables_length select').select2();
		return oTable;
		
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


	 
