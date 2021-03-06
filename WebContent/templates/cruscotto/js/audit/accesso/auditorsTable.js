var AuditorsTable = function() {

	var initTable1 = function() {
		var table = $('#auditors_table');
		
		

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

					"order" : [ [ 1, 'asc' ] ],
					
					"lengthMenu" :  [ 5, 10, 15, 20 ], 
					
					
					// set the initial value
					"pageLength" : 5,

					"dom" : "<'row' <'col-md-12'T>><'row'<'col-md-6 col-sm-12'l><'col-md-6 col-sm-12'f>r><'table-scrollable't><'row'<'col-md-5 col-sm-12'i><'col-md-7 col-sm-12'p>>", // horizobtal
					"sAjaxSource" : "/CruscottoAuditAtpoWebWeb/json/getAuditors",
					"processing" : false,
					"serverSide" : true,

					"aoColumns" : [
							{
								"mData" : "idUtente",
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
									content += '<input type="checkbox"  name="ids" id="chkAuditors'
											+ row.idUtente
											+ '" class="md-check chkAuditors" value="'
											+ row.idUtente + '">';
									content += '<label for="chkAuditors' + row.idUtente + '">';
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
								"mData" : "nome",
								"bSearchable" : true,
								"bSortable" : true
							}, {
								"mData" : "cognome",
								"bSearchable" : true,
								"bSortable" : true
							}, {
								"mData" : "username",
								"bSearchable" : true,
								"bSortable" : true
							}
							, {
								"mData" : "associazione",
								"bSearchable" : true,
								"bSortable" : true,
								"fnCreatedCell": function(nTd, sData, oData, iRow, iCol)
								  {
										$(nTd).css('text-align', 'center');
								  },
							
								"mRender" : function(data, type, row) {
							
									if(data == 'attivo'){
										var content = '<span><i class="fa fa-check"    style="color: green;"></i></span>';
									}else{
										var content = '<span><i class="fa fa-times" style="color: red;"></i></span>';
									}
									
									return content;
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