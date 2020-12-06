/*
 * TABELLA AUDITOR/GENERALE
 */

var TipologicheTable = function() {

	var initTable1 = function() {
		var table = $('#tipologiche_table');
		
		

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
					"sAjaxSource" : "/CruscottoAuditAtpoWebWeb/json/searchTipologiche",
					"processing" : false,
					"serverSide" : true,

					"aoColumns" : [
							{
								"mData" : "idTplTipologica",
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
											+ row.idTplTipologica
											+ '" class="md-check chkGen" value="'
											+ row.idTplTipologica + '">';
									content += '<label for="checkboxTable_' + row.idTplTipologica + '">';
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
								"mData" : "tipo",
								"bSearchable" : false,
								"bSortable" : false
							}, {
								"mData" : "codifica",
								"bSearchable" : false,
								"bSortable" : false
							}, {
								"mData" : "descrizione",
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
function saveNewTipologica(){
	
	Metronic.startPageLoading();
	var tipo=$('#tipo').val();
	var codifica=$('#codifica').val();
	var descrizione=$('#descrizione').val();
	var idTplTipologica=$('#idTplTipologica').val();
	
	var ruoli = "";
	
	if(!tipo || !codifica || !descrizione ){
		$('#info').show();
		Metronic.stopPageLoading();
		return;
	}else{
		ruoli=ruoli.substring(0, ruoli.length - 1);
		$('#info').hide();
		$.ajax( {
			type : 'GET',
			url : '/CruscottoAuditAtpoWebWeb/salvaNuovaTipologica',
			data :{ 
			'tipologica.tipo' : tipo	,
			'tipologica.codifica' : codifica	,
			'tipologica.descrizione' : descrizione	,
			'tipologica.idTplTipologica' : idTplTipologica

		},


		success : function(data) {
			Metronic.stopPageLoading();
			loadDiv('/CruscottoAuditAtpoWebWeb/tipologicheList', 'appView', null, 'initTipologicheGenerale' );

			$('#tipo').val("");
			$('#codifica').val("");
			$('#descrizione').val("");
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


	 
	