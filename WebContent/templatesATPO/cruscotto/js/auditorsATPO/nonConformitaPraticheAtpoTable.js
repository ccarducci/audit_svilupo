var NonConfPraticheAtpo = function() {

	var initTable1 = function() {
		var table = $('#nonConfPraticheAtpo');
		
		

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

					"lengthMenu" :  [ 5, 10, 15, 20 ], 
					                
					
					
					// set the initial value
					"pageLength" : 5,

					"dom" : "<'row' <'col-md-12'T>><'row'<'col-md-6 col-sm-12'l><'col-md-6 col-sm-12'f>r><'table-scrollable't><'row'<'col-md-5 col-sm-12'i><'col-md-7 col-sm-12'p>>", // horizobtal
					"sAjaxSource" : "/CruscottoAuditAtpoWebWeb/jsonATPO/searchNonConfPraticaAtpo",
					"fnServerParams": function ( aoData ) {
						 var selectBox = document.getElementById("selectFasePratNonConf");
						
						 var selectedText = $("#selectFasePratNonConf option:selected").html();
						 aoData.push({"name": "filtroFase", "value": selectedText.trim()} );
					
			        },
			   
					"processing" : false,
					"serverSide" : true,

					"aoColumns" : [
							{
								"mData" : "idNonConf",
								"bSearchable" : false,
								"bSortable" : false,
								"bVisible":false
							},{
								"mData" : "ordinamento",
								"bSearchable" : true,
								"bSortable" : true,
								"defaultContent": "non presente"
							}, 

							{
								"mData" : "descrizioneFase",
								"bSearchable" : true,
								"bSortable" : true,
								"defaultContent": "non presente"
							}, {
								"mData" : "descrNonConformita",
								"bSearchable" : true,
								"bSortable" : true,
								"defaultContent": "non presente"
							},{
								"mData" : "varianteComportamentale",
								"bSearchable" : true,
								"bSortable" : true,
								"defaultContent": "non presente"
							},

							{
								"mData" : "descrizioneVarComp",
								"bSearchable" : true,
								"bSortable" : true,
								"defaultContent": "non presente"
							},{
								"mData" : "pesoVarComp",
								"bSearchable" : true,
								"bSortable" : true,
								"visible" : false,
								"defaultContent": "non presente"
							},{
								"mData" : "colore",
								"bSearchable" : true,
								"bSortable" : true,
								"fnCreatedCell": function(nTd, sData, oData, iRow, iCol)
								  {
									if(sData=='Giallo'){
										$(nTd).css('background-color', 'yellow');
									}else if(sData=='Bianco'){
										$(nTd).css('background-color', 'white');
									}else if(sData=='Rosso'){
										$(nTd).css('background-color', 'red');
									}else if(sData=='Verde'){
										$(nTd).css('background-color', 'green');
									}else if(sData=='Arancio'){
										$(nTd).css('background-color', 'orange');
									}
									
 									$(nTd).css('text-align', 'center');
// 									$(nTd).css('width', '22px');
								  },
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


	 
	