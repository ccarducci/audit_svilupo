/*
 * TABELLA AUDITOR/GENERALE
 */

var IsncTable = function() {

	var initTable1 = function() {
		var table = $('#isnc_table');
		
		

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

					"dom" : "<'row' <'col-md-12'T>><'row'<'col-md-6 col-sm-12'l><'col-md-6 col-sm-12'f>r><'table-scrollable't><'row'<'col-md-5 col-sm-12'i><'col-md-7 col-sm-12'p>>", // horizobtal
					"sAjaxSource" : "/CruscottoAuditAtpoWebWeb/json/searchSoglieColori",
					"processing" : false,
					"serverSide" : true,

					"aoColumns" : [
							{
								"mData" : "idTplIsnc",
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
									content += '<input type="checkbox"  name="ids" id="checkboxTable_'
											+ row.idTplIsnc
											+ '" class="md-check chkGen" value="'
											+ row.idTplIsnc + '">';
									content += '<label for="checkboxTable_' + row.idTplIsnc + '">';
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
								"mData" : "soglia",
								"bSearchable" : false,
								"bSortable" : false
							}, {
								"mData" : "dataInizio",
								"bSearchable" : false,
								"bSortable" : false,
								"mRender" : function(data) {
									if(data==null){
										var voidDate="";
										return voidDate;
									}else{
										return formatCustomDate(data);
										
									}
								}
							}, {
								"mData" : "dataFine",
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
								
							},{
								"mData" : "colore",
								"bSearchable" : true,
								"bSortable" : true
								
							},
							{
								"mData" : "nomeaudit",
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






//SALVA NUOVO AUDIT
function saveNewIsnc(){
	
	Metronic.startPageLoading();
	var soglia=$('#soglia').val();
	var dataInizio=$('#dataInizio').val();
	var dataFine=$('#dataFine').val();
	var colore =$('#colore').val();
	var idAudit=$('#idAudit').val();
	var idTplIsnc=$('#idTplIsnc').val();
	
	
	
	if(!soglia || !dataInizio || !colore || !idAudit){
		$('#info').show();
		Metronic.stopPageLoading();
		return;
	}else{
		$('#info').hide();
		$.ajax( {
			type : 'GET',
			url : '/CruscottoAuditAtpoWebWeb/salvaNuovaIsnc',
			data :{ 
			'isnc.soglia' : soglia	,
			'isnc.dataInizio' : dataInizio	,
			'isnc.dataFine' : dataFine	,
			'isnc.colore' : colore	,
			'isnc.idAudit' : idAudit,
			'isnc.idTplIsnc' : idTplIsnc

		},


		success : function(data) {
			
			Metronic.stopPageLoading();
			loadDiv('/CruscottoAuditAtpoWebWeb/sogliecoloriList', 'appView', null, 'initIsncGenerale' );

			$('#soglia').val("");
			$('#dataInizio').val("");
			$('#dataFine').val("");
			$('#colore').val("");
			
			return; 


		},error: function(data){
			console.log(data);
			Metronic.stopPageLoading();
			var settings = {
					theme: 'teal',
					sticky: false,
					horizontalEdge: 'top',
					verticalEdge: 'right',
					life: 3000
			};
			$.notific8('zindex', 11500);
			$.notific8('Errore durante il salvataggio ', settings);
			return; 
		}
		});
	}
}


	 
	