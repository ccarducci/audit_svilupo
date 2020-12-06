/*
 * TABELLA AUDITORS/ACCESSI
 */

var NcPraticheTable = function() {

	var initTable1 = function() {
		var table = $('#nc_pratiche');

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

			
			"sAjaxSource" : "/CruscottoAuditAtpoWebWeb/json/searchNonConformita",
			"processing" : false,
			"serverSide" : true,

			"aoColumns" : [
							{
								"mData" : "descFase",
								"bSearchable" : true,
								"bSortable" : true
							}, {
								"mData" : "codNonConf",
								"bSearchable" : true,
								"bSortable" : true
							}, {
								"mData" : "descNonConf",
								"bSearchable" : true,
								"bSortable" : true
							}, {
								"mData" : "codVarComp",
								"bSearchable" : true,
								"bSortable" : true
								
							}, {
								"mData" : "descVarComp",
								"bSearchable" : true,
								"bSortable" : true
								
							},{
								"mData" : "pesoVarComp",
								"bSearchable" : true,
								"bSortable" : true
								
							}    
							
							,{
								"mData" : "colore",
								"bSearchable" : true,
								"bSortable" : true
								
							},{
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


	 
	