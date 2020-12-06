
var AllegatiNoteDecretiTable = function() {
	
	var initTable1 = function() {
		
		
		var table = $('#allegatiNoteDecretiTable');
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

					"lengthMenu" : [ 5, 10, 15, 20 ], 
					// set the initial value
					"pageLength" : 5,

					"dom" : "<'row' <'col-md-12'T>><'row'<'col-md-6 col-sm-12'l><'col-md-6 col-sm-12'f>r><'table-scrollable't><'row'<'col-md-5 col-sm-12'i><'col-md-7 col-sm-12'p>>", // horizobtal
					"sAjaxSource" : "/CruscottoAuditAtpoWebWeb/json/getAllegatiNoteDecreti",
					"processing" : false,
					"serverSide" : true,
					"fnServerData": function (sSource, aoData, fnCallback) {
						
						var idTipo = $('#comboTipoNormativa option:selected').val();
						var idNoteDecreti = $('#idNoteDecreti').val();
						
						
						if(idTipo == undefined || idTipo == ''){
							idTipo = 1;
						}
		              
						aoData.push({ "name": "idTipo", "value": idTipo },
								{ "name": "idNoteDecreti", "value": idNoteDecreti });   // valorizzato se arrivo dal tab Allegati di modificaCircolariInps.
		   
		              $.ajax({
		                  "url": sSource,
		                  "dataType": "json",
		                  "data": aoData,
		                  "success": fnCallback
		              })
		          },

					"aoColumns" : [
							{
								"mData" : "idAssociazione",
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
									content += '<input type="checkbox"  name="ids" id='+row.idAssociazione+' class="md-check chkDocNoteDecreti" value="'+row.idAssociazione+"-"+row.idDoc+'">';
									content += '<label for='+row.idAssociazione+'>';
									content += '<span></span>';
									content += '<span class="check"></span>';
									content += '<span class="box"></span>';
									content += '</label>';
									content += '</div>';
									content += '</div>';
									content += '</div>';
									
									return content;
								}
							},{
								"mData" : "nomeFile",
								"bSearchable" : true,
								"bSortable" : true
							},

							{
								"mData" : "descrizione",
								"bSearchable" : true,
								"bSortable" : true
							}, {
								"mData" : "dataInizio",
								"bSearchable" : true,
								"bSortable" : true,
								"mRender" : function(data) {
									if(data==null){
										var voidDate="";
										return voidDate;
									}else{	
										var dateArray=data.split('T');
										var format= dateArray[0].split("-");
										var yyyy=format[0];
										var mm= format[1];
										var dd= format[2];
										var date= dd+"/"+mm+"/"+yyyy;
										return date;
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

