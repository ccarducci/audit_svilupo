var AuditQuestionarioTable = function() {
	var initTable1 = function() {
		var table = $('#auditQuestionarioTable');
		
		

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

					
					"sAjaxSource" : "/CruscottoAuditAtpoWebWeb/json/searchDomandeQuestionario",
					"processing" : false,
					"serverSide" : true,

					"aoColumns" : [
							{
								"mData" : "idDomanda",
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
										content += '<input type="checkbox"  name="chkQuest" id="checkboxQT_'
												+ row.idDomanda 
												+ '" class="md-check chkQuest" value="'
												+ row.idDomanda+ '">';
										content += '<label for="checkboxQT_' + row.idDomanda + '">';
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
								"mData" : "descrizione",
								"bSearchable" : true,
								"bSortable" : true
							},
							
							{
								"mData" : "peso",
								"bSearchable" : true,
								"bSortable" : true
							},
							
							{
								"mData" : "valMaxRsp",
								"bSearchable" : true,
								"bSortable" : true
							}, 
							
							{
								"mData" : "descrizioneRischio",
								"bSearchable" : true,
								"bSortable" : true
							}, 
							{
								"mData" : "pesoPErcentuale",
								"bSearchable" : true,
								"bSortable" : true
							},
							{
								"mData" : "controlloProcesso",
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
	