var FaseProcessoTable = function () {

    var initTable1 = function () {
        
    	var table = $('#faseprocesso_table');
        
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

            "sAjaxSource" : "/CruscottoAuditAtpoWebWeb/json/getSottoProcessiTable",
			"processing" : false,
			"serverSide" : true,
			"fnServerData": function (sSource, aoData, fnCallback) {
	        	  
	              var idProcesso = jQuery("#idProcesso").val();
	              aoData.push({ "name": "sottoProcesso.processo.idProcesso", "value": idProcesso });
	   
	              $.ajax({
	                  "url": sSource,
	                  "dataType": "json",
	                  "data": aoData,
	                  "success": fnCallback
	              })
	          },

			"aoColumns" : [
					{
						"mData" : "idSottoProcesso",
						"bSearchable" : false,
						"bSortable" : false,
						"fnCreatedCell": function(nTd, sData, oData, iRow, iCol)
						  {
								$(nTd).css('text-align', 'center');
								$(nTd).css('width', '30px');
						  },
					
						// `data` refers to the data for the cell (defined by `mData`, which
					    // defaults to the column being worked with, in this case is the first
					    // Using `row[0]` is equivalent.
						"mRender" : function(data, type, row) {
					
							
							var content='<div class="form-group form-md-checkboxes">';
							content += '<div class="md-checkbox-inline">';
							content += '<div class="md-checkbox">';
							content += '<input type="checkbox"  name="ids" id="checkboxTable_'
									+ row.idSottoProcesso
									+ '" class="md-check chkFaseProcesso" value="'
									+ row.idSottoProcesso +'">';
							content += '<label for="checkboxTable_' + row.idSottoProcesso + '">';
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
						"mData" : "ordinamento",
						"bSearchable" : true,
						"bSortable" : true
					},
					{
						"mData" : "descrizione",
						"bSearchable" : true,
						"bSortable" : false
					},
					{
						"mData" : "processo.area.descrizione",
						"bSearchable" : true,
						"bSortable" : false
					},
					{
						"mData" : "processo.descrizione",
						"bSearchable" : true,
						"bSortable" : false
					},
					{
						"mData" : "input",
						"bSearchable" : true,
						"bSortable" : false
					},
					 {
						"mData" : "output",
						"bSearchable" : true,
						"bSortable" : false
					}, 
					{
						"mData" : "uoCoinvolte",
						"bSearchable" : true,
						"bSortable" : false
					}
			]
			
			
			
		});

        var tableWrapper = $('#faseprocesso_table_wrapper'); // datatable creates the table wrapper by adding with id {your_table_jd}_wrapper

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
