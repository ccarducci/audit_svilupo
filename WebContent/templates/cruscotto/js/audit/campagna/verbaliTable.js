var CampagnaVerbaleTable = function() {

	var initTable1 = function() {
		var table = $('#campagnaVerbaleTable');
		
		

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
					"sAjaxSource" : "/CruscottoAuditAtpoWebWeb/json/getVerbaliCampagna",
					"processing" : false,
					"serverSide" : true,

					"aoColumns" : [
									{
										"mData" : "fascicolo",
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
													+ row.fascicolo
													+ '" class="md-check chkSessCamp" value="'
													+ row.fascicolo + '">';
											content += '<label for="chkSessCamp_' + row.fascicolo + '">';
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
								"mData" : "nomeSede",
								"bSearchable" : true,
								"bSortable" : true
							}, {
								"mData" : "fascicolo",
								"bSearchable" : true,
								"bSortable" : true
							}, {
								"mData" : "notifica",
								"bSearchable" : true,
								"bSortable" : true,
								"mRender" : function(data) {
									if(data==null){
										var voidDate="";
										return voidDate;
									}else{
										return formatCustomDate(data);
										
									}
								}
							}, {
								"mData" : "apertura",
								"bSearchable" : true,
								"bSortable" : true,
								"mRender" : function(data) {
									if(data==null){
										var voidDate="";
										return voidDate;
									}else{
										return formatCustomDate(data);
										
									}
								}
							}, {
								"mData" : "rg",
								"bSearchable" : true,
								"bSortable" : true
							}, {
								"mData" : "parte",
								"bSearchable" : true,
								"bSortable" : true
							},{
								"mData" : "visitaPeritale",
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
