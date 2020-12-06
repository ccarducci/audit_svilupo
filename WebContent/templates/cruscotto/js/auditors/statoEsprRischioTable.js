var SEsprRischioTable = function() {

	var initTable1 = function() {
		var table = $('#statoEsprRischio');

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

			"lengthMenu" :  [ 5, 10, 15, 20 ], 
			
			
			// set the initial value
			"pageLength" : 5,

			
			"sAjaxSource" : "/CruscottoAuditAtpoWebWeb/json/searchStatoEsprRischio",
			"processing" : false,
			"serverSide" : true,

			"aoColumns" : [{"mData" : "idSRisepr",
							"bSearchable" : true,
							"bSortable" : true,
							"bVisible":false
				
			},
			               
							{
								"mData" : "descrizione",
								"bSearchable" : true,
								"bSortable" : true
							}, {
								"mData" : "num",
								"bSearchable" : true,
								"bSortable" : true
							}, {
								"mData" : "percSuTot",
								"bSearchable" : true,
								"bSortable" : true
							}, {
								"mData" : "percSuPS",
								"bSearchable" : true,
								"bSortable" : true
								
							}, {
								"mData" : "totImporto",
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


	 
	