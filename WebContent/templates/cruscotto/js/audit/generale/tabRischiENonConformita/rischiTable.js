/*
 * TABELLA FASI AUDIT GENERALE
 */

var RischioTable = function() {

	var initTable1 = function() {
		var table = $('#rischiEnonConformitaRischioTable');
		
		

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

					
					"sAjaxSource" : "/CruscottoAuditAtpoWebWeb/json/searchRischiEnonConformitaRischio",
					"processing" : false,
					"serverSide" : true,

					"aoColumns" : [
							{
								"mData" : "idMRischio",
								"bSearchable" : false,
								"bSortable" : false,
								"fnCreatedCell": function(nTd, sData, oData, iRow, iCol)
								  {
   									$(nTd).css('text-align', 'center');
   									$(nTd).css('width', '22px');
								  },
								  
								  /*
								   * `data` refers to the data for the cell (defined by `mData`, which
								   * defaults to the column being worked with, in this case is the first
								   *  Using `row[0]` is equivalent.
								   */
								"mRender" : function(data, type, row) {

									
									var content='<div class="form-group form-md-checkboxes">';
									content += '<div class="md-checkbox-inline">';
									content += '<div class="md-checkbox">';
									content += '<input type="checkbox"  name="chkR" id="checkboxTableRis_'
											+ row.idMRischio 
											+ '" class="md-check chkRischio" value="'
											+ row.idMRischio + '">';
									content += '<label for="checkboxTableRis_' + row.idMRischio + '">';
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
								"mData" : "descrizioneRischio",
								"bSearchable" : true,
								"bSortable" : true
							},
							
							{
								"mData" : "codiceRischio",
								"bSearchable" : true,
								"bSortable" : true
							},
							
							{
								"mData" : "pesoRischio",
								"bSearchable" : true,
								"bSortable" : true
							}, 
							
							{
								"mData" : "valoreDecrRischio",
								"bSearchable" : true,
								"bSortable" : true
							}, 
							
							{
								"mData" : "dataInizio",
								"bSearchable" : true,
								"bSortable" : true,
								"defaultContent": "",
								"mRender" : function(data) {
								try {
									if(data != null){
										var dateArray=data.split('T');
										var date= dateArray[0];
										
										var dateArr= date.split('-');
										var year= dateArr[0];
										var mounth= dateArr[1];
										var dd= dateArr[2];
										
										var dateDef= dd+"/"+mounth+"/"+year;
										return dateDef;
										}
									
								} catch (e) {
									  console.log(e.name + ': ' + e.message);
								}
								
							}
							},{
								"mData" : "dataFine",
								"bSearchable" : true,
								"bSortable" : true,
								"defaultContent": "",
								"mRender" : function(data) {
								try {
									if(data != null || data !=""){
										var dateArray=data.split('T');
										var date= dateArray[0];
										
										var dateArr= date.split('-');
										var year= dateArr[0];
										var mounth= dateArr[1];
										var dd= dateArr[2];
										
										var dateDef= dd+"/"+mounth+"/"+year;
										return dateDef;
									}
									} catch (e) {
									  console.log(e.name + ': ' + e.message);
									}
								
								
							}
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


	 
