/*
 * TAB1-TABELLA INDICATORI MEDIA
 */
var Indicatori = function () {

    var initTable1 = function () {
        //var table = $('#sample_1, #sample_2, #sample_3');
    	var table = $('#indicatori');
    
    	 var oTable = table.dataTable({

             // Internationalisation. For more info refer to http://datatables.net/manual/i18n
             "language": {
 						"sEmptyTable":     "Nessun dato presente nella tabella",
 						"sInfoFiltered":   "(filtrati da _MAX_ elementi totali)",
 						"sInfoPostFix":    "",
 						"sInfoThousands":  ",",
 						"sLengthMenu":     "Visualizza _MENU_ elementi",
 						"sLoadingRecords": "Caricamento...",
 						"sProcessing":     "Elaborazione...",
 						"sZeroRecords":    "La ricerca non ha portato alcun risultato.",
 						"Aria": {
 							"sSortAscending":  ": attiva per ordinare la colonna in ordine crescente",
 							"sSortDescending": ": attiva per ordinare la colonna in ordine decrescente"
 						}
 					},

     
             "order": [
                 [0, 'asc']
             ],
             "bInfo": false,
             "searching": false,
             "bPaginate": false,
             "dom": "<'row' <'col-md-12'T>><'row'<'col-md-6 col-sm-12'l><'col-md-6 col-sm-12'f>r><'table-scrollable't><'row'<'col-md-5 col-sm-12'i><'col-md-7 col-sm-12'p>>", // horizobtal scrollable datatable
            

 				

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
