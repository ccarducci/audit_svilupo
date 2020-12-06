
/*
 
 */

var RischiPraticheAtpo = function() {

	var initTable1 = function() {
		var table = $('#rischiPraticheAtpo');
		
		

		var oTable = table
				.dataTable( {

					// Internationalisation. For more info refer to
					// http://datatables.net/manual/i18n
					"language" : {
						"sEmptyTable" : "Nessun dato presente nella tabella o stato esame pratica diverso da 'C'",
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

					"lengthMenu" :[ 5, 10, 15, 20],
					                
					
					
					// set the initial value
					"pageLength" : 5,

					"dom" : "<'row' <'col-md-12'T>><'row'<'col-md-6 col-sm-12'l><'col-md-6 col-sm-12'f>r><'table-scrollable't><'row'<'col-md-5 col-sm-12'i><'col-md-7 col-sm-12'p>>", // horizobtal
					"sAjaxSource" : "/CruscottoAuditAtpoWebWeb/jsonATPO/searchRischiPraticaAtpo",
					"processing" : false,
					"serverSide" : true,

					"aoColumns" : [
							{
								"mData" : "idRischio",
								"bSearchable" : false,
								"bSortable" : false,
								"bVisible":false
							},

							{
								"mData" : "codiceRischio",
								"bSearchable" : true,
								"bSortable" : true,
								"defaultContent": "non presente"
							}, {
								"mData" : "descrizione",
								"bSearchable" : true,
								"bSortable" : true,
								"defaultContent": "non presente"
							},{
								"mData" : "codiceEsprRischio",
								"bSearchable" : true,
								"bSortable" : true,
								"defaultContent": "non presente"
							},

							{
								"mData" : "descrizioneEpsrRischio",
								"bSearchable" : true,
								"bSortable" : true,
								"defaultContent": "non presente"
							},
							
							{
								"mData" : "dataAttribuzione",
								"bSearchable" : true,
								"bSortable" : true,
								"defaultContent": "non presente",
								"mRender" : function(data) {
								if(data != null){
									var dateArray=data.split('T');
									var date= dateArray[0];
									
									var dateArr= date.split('-');
									var year= dateArr[0];
									var mounth= dateArr[1];
									var dd= dateArr[2];
									
									var dateDef= dd+"/"+mounth+"/"+year
									return dateDef;
								}
							}
							},
							{
								"mData" : "importo",
								"bSearchable" : true,
								"bSortable" : true,
								"defaultContent": "non presente"
							},
							


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


	 
	