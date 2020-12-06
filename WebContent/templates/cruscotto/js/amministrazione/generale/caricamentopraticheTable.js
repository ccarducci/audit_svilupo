/*
 * TABELLA AUDITOR/GENERALE
 */

var CaricamentoPraticheTable = function() {

	var initTable1 = function() {
		var table = $('#caricamentopratiche_table');
		
		

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
					"sAjaxSource" : "/CruscottoAuditAtpoWebWeb/json/searchCaricamentoPratiche",
					"processing" : false,
					"serverSide" : true,

					"aoColumns" : [
							{
								"mData" : "idBcp",
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
											+ row.idBcp
											+ '" class="md-check chkGen" value="'
											+ row.idBcp + '">';
									content += '<label for="checkboxTable_' + row.idBcp + '">';
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
								"mData" : "nomeCampagna",
								"bSearchable" : false,
								"bSortable" : false
							}, {
								"mData" : "nomeSede",
								"bSearchable" : false,
								"bSortable" : false
							}, {
								"mData" : "annoConclusione",
								"bSearchable" : true,
								"bSortable" : true
							}
							, {
								"mData" : "dataInizio",
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
								
							}
							, {
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
								
							}, {
								"mData" : "esito",
								"bSearchable" : true,
								"bSortable" : true
								
							}, {
								"mData" : "descErrore",
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
function saveNewCaricamentoPratiche(){
	
	Metronic.startPageLoading();
	var codiceSede=$('#codiceSede').val();
	var idCampagna=$('#idCampagna').val();
	var annoConclusione=$('#annoConclusione').val();
	var idBcp=$('#idBcp').val();
	
	
	
	if(!codiceSede || !idCampagna || !annoConclusione ){
		$('#info').show();
		Metronic.stopPageLoading();
		return;
	}else{
		
		$('#info').hide();
		$.ajax( {
			type : 'GET',
			url : '/CruscottoAuditAtpoWebWeb/salvaNuovoCaricamentoPratica',
			data :{ 
			'caricamentopratica.codiceSede' : codiceSede	,
			'caricamentopratica.idCampagna' : idCampagna	,
			'caricamentopratica.annoConclusione' : annoConclusione	,
			'caricamentopratica.idBcp' : idBcp

		},


		success : function(data) {
			Metronic.stopPageLoading();
			loadDiv('/CruscottoAuditAtpoWebWeb/caricamentopraticheList', 'appView', null, 'initCaricamentoPraticheGenerale' );

			$('#codiceSede').val("");
			$('#idCampagna').val("");
			$('#annoConclusione').val("");
			return; 


		},error: function(data){
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


	 
	