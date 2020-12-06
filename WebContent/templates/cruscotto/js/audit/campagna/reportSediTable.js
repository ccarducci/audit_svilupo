/*
 * TABELLA AUDITOR/CAMPAGNA
 */

var ReportSediTable = function() {

	var initTable1 = function() {
		var table = $('#reportSediTable');
//		var numeroColonne=numberCol;
//		
//	
//		if(numeroColonne!=0){
//			var column='';
//			var indicatore=1;
//			for (var i = 0; i < numeroColonne; i++) {
//				if(i===0){
//					
//					
//					 column='[{ "mData" : "idSede", "bVisible": false,  "bSearchable": true,  "bSortable" : true },'+
//						'{ "mData" : "sede", "bSearchable": true,  "bSortable" : true },{ "mData" : "indicatore'+indicatore+'", "bSearchable": true,  "bSortable" : true }';
//					 indicatore++;
//				}else{
//					column=column+',{ "mData" : "indicatore'+indicatore+'", "bSearchable": true,  "bSortable" : true }';
//					indicatore++;
//				}
//				
//			
//			}
//			
//			var colonneDaNascondere=10-numberCol;
//			if(colonneDaNascondere != 0){
//				for (var i = 0; i < colonneDaNascondere; i++) {
//					column=column+',{ "mData" : "indicatore'+indicatore+'", "bVisible": false,  "bSearchable": true,  "bSortable" : true }';
//					indicatore++;
//					}
//					
//				
//				}
//			
//			column=column+']';
//
//			console.log(column);
//			
//		}else{
//			
//			var column='';
//			var indicatore=1;
//			for (var i = 0; i < 5; i++) {
//				if(i===0){
//					 column='[{ "mData" : "sede", "bSearchable": true,  "bSortable" : true },{ "mData" : "indicatore'+indicatore+'", "bSearchable": true,  "bSortable" : true }';
//					 indicatore++;
//				}else{
//					column=column+',{ "mData" : "indicatore'+indicatore+'", "bSearchable": true,  "bSortable" : true }';
//					indicatore++;
//				}
//				
//			
//			}
//			
//			var colonneDaNascondere=5;
//			if(colonneDaNascondere != 0){
//				for (var i = 0; i < colonneDaNascondere; i++) {
//					column=column+',{ "mData" : "indicatore'+indicatore+'", "bVisible": false,  "bSearchable": true,  "bSortable" : true }';
//					indicatore++;
//					}
//					
//				
//				}
//			
//			column=column+']';
//
//			console.log(column);
//			
//		}
		

		var oTable = table.dataTable( {

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

					"aaSorting" : [ [ 1, 'asc' ] ],

					"lengthMenu" : [ 5, 10, 15, 20 ], 
					
	
					// set the initial value
					"pageLength" : 5,
					
					"dom" : "<'row' <'col-md-12'T>><'row'<'col-md-6 col-sm-12'l><'col-md-6 col-sm-12'f>r><'table-scrollable't><'row'<'col-md-5 col-sm-12'i><'col-md-7 col-sm-12'p>>", // horizobtal
					"sAjaxSource" : "/CruscottoAuditAtpoWebWeb/json/searchReportSedi",
					"processing" : false,
					"serverSide" : true,
					
					"aoColumns" : [
					               {
										"mData" : "id_sedi_critiche",
										"bSearchable" : false,
										"bSortable" : false,
										"bVisible" : false
									},
																		
									{
										"mData" : "sede",
										"bSearchable" : true,
										"bSortable" : true
									}, {
										"mData" : "anno",
										"bSearchable" : true,
										"bSortable" : true
									}, {
										"mData" : "numeroIstanze",
										"bSearchable" : true,
										"bSortable" : true
										
									}, {
										"mData" : "tempoMedioAperturaPratica",
										"bSearchable" : true,
										"bSortable" : true
										
									}, {
										"mData" : "tempoMedioChiusuraPratica",
										"bSearchable" : true,
										"bSortable" : true
										
									},
									 {
										"mData" : "sfavorevoli",
										"bSearchable" : true,
										"bSortable" : true									
									},
									 {
										"mData" : "percSfavorevoli",
										"bSearchable" : true,
										"bSortable" : true										
									},
									 {
										"mData" : "conFascicoliApertiSenzaDocumenti",
										"bSearchable" : true,
										"bSortable" : true										
									},
									 {
										"mData" : "percConFascicoliApertiSenzaDocumenti",
										"bSearchable" : true,
										"bSortable" : true										
									},
									 {
										"mData" : "conVisitePeritali",
										"bSearchable" : true,
										"bSortable" : true										
									},
									 {
										"mData" : "percConVisitePeritali",
										"bSearchable" : true,
										"bSortable" : true										
									},
									 {
										"mData" : "conPresenzaAttoCostituzione",
										"bSearchable" : true,
										"bSortable" : true										
									},
									 {
										"mData" : "conPresenzaParereAutotutela",
										"bSearchable" : true,
										"bSortable" : true										
									},
									 {
										"mData" : "conPresenzaParereSanitario",
										"bSearchable" : true,
										"bSortable" : true										
									},
									 {
										"mData" : "conPresenzaParereSanitarioBozza",
										"bSearchable" : true,
										"bSortable" : true										
									},
									 {
										"mData" : "conPresenzaParereSanitarioDefinitiva",
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


	 
	