/*
 * TABELLA INDICATORI DELLA FASE DEFINIZIONE
 * (modifica_pratiche_accessi)
 */

var IndicatoriFaseDefinizione = function () {

    var initTable1 = function () {
       
    	var table = $('#indicatoriFaseDefinizione');
  
        var oTable = table.dataTable({

            // Internationalisation. For more info refer to http://datatables.net/manual/i18n
            "language": {
						"sEmptyTable":     "Nessun dato presente nella tabella",
						"sInfo":           "Vista da _START_ a _END_ di _TOTAL_ elementi",
						"sInfoEmpty":      "Vista da 0 a 0 di 0 elementi",
						"sInfoFiltered":   "(filtrati da _MAX_ elementi totali)",
						"sInfoPostFix":    "",
						"sInfoThousands":  ",",
						"sLoadingRecords": "Caricamento...",
						"sProcessing":     "Elaborazione...",
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

            // Or you can use remote translation file
            //"language": {
            //   url: '//cdn.datatables.net/plug-ins/3cfcc339e89/i18n/Portuguese.json'
            //},

            "order": [
                [0, 'asc']
            ],
            
            "lengthMenu": [
                [5, 15, 20, -1],
                [5, 15, 20, "All"] // change per page values here
            ],
            // set the initial value
            "pageLength": 5,
          
            "dom": "<'row' <'col-md-12'T>><'row'<'col-md-6 col-sm-12'l><'col-md-6 col-sm-12'f>r><'table-scrollable't><'row'<'col-md-5 col-sm-12'i><'col-md-7 col-sm-12'p>>", // horizobtal scrollable datatable
            
            "processing" : false,
			"serverSide" : true,
			"sAjaxSource" : "/CruscottoAuditAtpoWebWeb/json/searchIndicatoriFaseDefinizione",
         
			"aoColumns" : [
							{
								"mData" : "idNCV",
								"bSearchable" : false,
								"bSortable" : false,
								"fnCreatedCell" : function(nTd, sData, oData,
										iRow, iCol) {
									$(nTd).css('text-align', 'center');
								},

								// `data` refers to the data for the cell
								// (defined by `mData`, which
								// defaults to the column being worked with, in
								// this case is the first
								// Using `row[0]` is equivalent.
								"mRender" : function(data, type, row) {

									var content = '<div class="form-group form-md-checkboxes">';
									content += '<div class="md-checkbox-inline">';
									content += '<div class="md-checkbox">';
									content += '<input type="checkbox"  name="ids" id="checkboxDef_'
											+ row.idNCV
											+ '" class="md-check chkDef" value="'
											+ row.idNCV + '">';
									content += '<label for="checkboxDef_' + row.idNCV + '">';
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
								"mData" : "VComp",
								"bSearchable" : true,
								"bSortable" : true
							}, {
								"mData" : "rischio",
								"bSearchable" : true,
								"bSortable" : true
							}, {
								"mData" : "valoreCalcolato",
								"bSearchable" : true,
								"bSortable" : true
							}, {
								"mData" : "valoreReale",
								"bSearchable" : true,
								"bSortable" : true
							}, {
								"mData" : "note",
								"bSearchable" : true,
								"bSortable" : true
							},
        

					]
				
			
			
			
			
          
        });

        var tableWrapper = $('#sample_1_wrapper'); // datatable creates the table wrapper by adding with id {your_table_jd}_wrapper

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
