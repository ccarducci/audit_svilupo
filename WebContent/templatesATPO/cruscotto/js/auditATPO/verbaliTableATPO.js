var CampagnaVerbaleAtpoTable = function() {

	var initTable1 = function() {
		var table = $('#campagnaVerbaleAtpoTable');
		
		

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
					"sAjaxSource" : "/CruscottoAuditAtpoWebWeb/jsonATPO/getVerbaliCampagnaAtpo",
					"processing" : false,
					"serverSide" : true,

					"aoColumns" : [
									{
										"mData" : "idVerbale",
										"bSearchable" : false,
										"bSortable" : false,
										"fnCreatedCell": function(nTd, sData, oData, iRow, iCol)
										  {
		   									$(nTd).css('text-align', 'center');
		   									$(nTd).css('display', 'none');
										  },
										
										"mRender" : function(data, type, row) {

											
											var content='<div class="form-group form-md-checkboxes">';
											content += '<div class="md-checkbox-inline">';
											content += '<div class="md-checkbox">';
											content += '<input type="checkbox"  name="ids" id="chkSessCamp_'
													+ row.idverbale
													+ '" class="md-check chkSessCamp" value="'
													+ row.idVerbale + '">';
											content += '<label for="chkSessCamp_' + row.idVerbale + '">';
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
								"mData" : "ragioneSociale",
								"bSearchable" : true,
								"bSortable" : true
							}, {
								"mData" : "codiceFiscale",
								"bSearchable" : true,
								"bSortable" : true
							}, {
								"mData" : "indirizzo",
								"bSearchable" : true,
								"bSortable" : true
							}, {
								"mData" : "civico",
								"bSearchable" : true,
								"bSortable" : true
							}, {
								"mData" : "cap",
								"bSearchable" : true,
								"bSortable" : true
							}, {
								"mData" : "provincia",
								"bSearchable" : true,
								"bSortable" : true
							},{
								"mData" : "comune",
								"bSearchable" : true,
								"bSortable" : true
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
