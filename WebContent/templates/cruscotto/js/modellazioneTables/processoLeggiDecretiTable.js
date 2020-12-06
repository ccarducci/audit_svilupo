var ProcessoLeggiDecretiTable = function () {

    var initTable1 = function () {
        
    	var table = $('#processoLeggiDecretiTable');
        
        var oTable = table.dataTable({

            // Internationalisation. For more info refer to http://datatables.net/manual/i18n
            "language": {
						"sEmptyTable":     "Nessun dato presente nella tabella",
						"sInfo":           "Vista da _START_ a _END_ di _TOTAL_ elementi",
						"sInfoEmpty":      "Vista da 0 a 0 di 0 elementi",
						"sInfoFiltered":   "(filtrati da _MAX_ elementi totali)",
						"sInfoPostFix":    "",
						"sInfoThousands":  ",",
						"sLengthMenu":     "Visualizza _MENU_ elementi",
						"sLoadingRecords": "Caricamento...",
						"sProcessing":     "Elaborazione...",
						"sSearch":         "Cerca:",
						"sZeroRecords":    "La ricerca non ha portato alcun risultato.",
						"oPaginate": {
							"sFirst":      "Inizio",
							"sPrevious":   "Precedente",
							"sNext":       "Successivo",
							"sLast":       "Fine"
						},
						"Aria": {
							"sSortAscending":  ": attiva per ordinare la colonna in ordine crescente",
							"sSortDescending": ": attiva per ordinare la colonna in ordine decrescente"
						}
					},

            "order": [
                [1, 'asc']
            ],
            
            "lengthMenu": [
               [5, 10, 15, 20],
               [5, 10, 15, 20] // change per page values here
            ],
            // set the initial value
            "pageLength": 5,

            "dom": "<'row' <'col-md-12'T>><'row'<'col-md-6 col-sm-12'l><'col-md-6 col-sm-12'f>r><'table-scrollable't><'row'<'col-md-5 col-sm-12'i><'col-md-7 col-sm-12'p>>", // horizobtal scrollable datatable

            "sAjaxSource" : "/CruscottoAuditAtpoWebWeb/json/getAssociabiliLeggiDecretiTable",
			"processing" : false,
			"serverSide" : true,
			"fnServerData": function (sSource, aoData, fnCallback) {
				
						var idTipo = $('#comboTipoNormativa option:selected').val();
						var idProcesso = $('#idProcesso').val();
						var idSottoProcesso = $('#idSottoProcesso').val();
						var idAttivitaComponente = $('#idAttivitaComponente').val();
						var idAttivitaDettaglio = $('#idAttivitaDettaglio').val();
					
					if(idTipo == undefined || idTipo == ''){
						idTipo = 1;
					}
	              
					aoData.push({ "name": "idTipo", "value": idTipo },
							{ "name": "idProcesso", "value": idProcesso },   // valorizzato se arrivo dal tab Normativa di modificaProcesso.
							{ "name": "idSottoProcesso", "value": idSottoProcesso }, // valorizzato se arrivo dal tab Normativa di modificaSottoProcesso./
							{ "name": "idAttivitaComponente", "value": idAttivitaComponente }, // valorizzato se arrivo dal tab Normativa di modificaAttivitaComponente./
							{ "name": "idAttivitaDettaglio", "value": idAttivitaDettaglio }); // valorizzato se arrivo dal tab Normativa di modificaAttivitaDettaglio./
	   
	              $.ajax({
	                  "url": sSource,
	                  "dataType": "json",
	                  "data": aoData,
	                  "success": fnCallback
	              })
	          },

			"aoColumns" : [
					{
						"mData" : "idLeggiDecreti",
						"bSearchable" : false,
						"bSortable" : false,
						"fnCreatedCell": function(nTd, sData, oData, iRow, iCol)
						  {
								$(nTd).css('text-align', 'center');
								$(nTd).css('width', '22px');
						  },
					
						// `data` refers to the data for the cell (defined by `mData`, which
					    // defaults to the column being worked with, in this case is the first
					    // Using `row[0]` is equivalent.
						"mRender" : function(data, type, row) {
					
							
							var content='<div class="form-group form-md-checkboxes">';
							content += '<div class="md-checkbox-inline">';
							content += '<div class="md-checkbox">';
							content += '<input type="checkbox"  name="ids" id="checkboxTable_'
									+ row.idLeggiDecreti
									+ '" class="md-check chkLeggiDecreti" value="'
									+ row.idLeggiDecreti + '">';
							content += '<label for="checkboxTable_' + row.idLeggiDecreti + '">';
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
						"mData" : "dataEmissione",
						"bSearchable" : true,
						"bSortable" : false,
						"mRender" : function(data) {
							if(data != null){
								var dateArray=data.split('T');
								var date= dateArray[0];
								
								var dateArr= date.split('-');
								var year= dateArr[0];
								var mounth= dateArr[1];
								var dd= dateArr[2];
								
								var dateDef= dd+"/"+mounth+"/"+year
								return dateDef;
							}
						}
					},
					 {
						"mData" : "codice",
						"bSearchable" : true,
						"bSortable" : false
					}, {
						"mData" : "oggetto",
						"bSearchable" : true,
						"bSortable" : false
					}, {
						"mData" : "descSintetica",
						"bSearchable" : true,
						"bSortable" : false
					}, {
						"mData" : "descDettaglio",
						"bSearchable" : true,
						"bSortable" : false
					}, {
						"mData" : "articolo",
						"bSearchable" : true,
						"bSortable" : false
					}, {
						"mData" : "annoGui",
						"bSearchable" : true,
						"bSortable" : false
					}, {
						"mData" : "numeroGui",
						"bSearchable" : true,
						"bSortable" : false
					}
			]
		});

        var tableWrapper = $('#leggiDecretiTable_wrapper'); // datatable creates the table wrapper by adding with id {your_table_jd}_wrapper

        tableWrapper.find('.dataTables_length select').select2(); // initialize select2 dropdown
    }

    
    

    return {

        //main function to initiate the module
        init: function () {

            if (!jQuery().dataTable) {
                return;
            }
            initTable1();
        }

    };

}();
