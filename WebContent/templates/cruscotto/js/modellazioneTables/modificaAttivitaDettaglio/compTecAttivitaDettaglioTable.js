var ComponentiTecniciAttivitaDettaglioTable = function() {

	var initTable1 = function() {
		
		
		var table = $('#compTecAttivitaDettaglioTable');
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
					"sAjaxSource" : contextApp + "/jsonATPO/searchCompTecniciAttivitaDettaglio",
					"processing" : false,
					"serverSide" : true,
					"fnServerData": function (sSource, aoData, fnCallback) {
			        	  
			              var idAttivitaDettaglio = jQuery("#idAttivitaDettaglio").val();
			              aoData.push({ "name": "idAttivitaDettaglio", "value": idAttivitaDettaglio });
			   
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
									content += '<input type="checkbox"  name="ids" id="compTecAD_'+row.idAssociazione+'" class="md-check chkCompTecAttivitaDettaglio" value="'+row.idAssociazione+'">';
									content += '<label for="compTecAD_'+row.idAssociazione+'">';
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
							},{
								"mData" : "codice",
								"bSearchable" : true,
								"bSortable" : true
							},{
								"mData" : "autore",
								"bSearchable" : true,
								"bSortable" : true
							}, {
								"mData" : "dataPubblicazione",
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
							},{
								"mData" : "versione",
								"bSearchable" : true,
								"bSortable" : true
							},
							{
								"mData" : "tipoCompTecnico",
								"bSearchable" : true,
								"bSortable" : true
							}
					]
							
					
				});

		var tableWrapper = $('#compTecAttivitaDettaglioTable_wrapper');

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

