/*
 * TABELLA PRATICHE CON RISCHI
 * (modifica-accessi tab2)
 */


var PraticheConRischi = function () {

    var initTable1 = function () {
        //var table = $('#sample_1, #sample_2, #sample_3');
    	var table = $('#pratiche_con_rischi');
        /* Table tools samples: https://www.datatables.net/release-datatables/extras/TableTools/ */

        /* Set tabletools buttons and button container */

        
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

            // Or you can use remote translation file
            //"language": {
            //   url: '//cdn.datatables.net/plug-ins/3cfcc339e89/i18n/Portuguese.json'
            //},

            "order": [
                [0, 'asc']
            ],
            
            "lengthMenu":  [5, 10, 15, 20],
                
           
            // set the initial value
            "pageLength": 5,

            "dom": "<'row' <'col-md-12'T>><'row'<'col-md-6 col-sm-12'l><'col-md-6 col-sm-12'f>r><'table-scrollable't><'row'<'col-md-5 col-sm-12'i><'col-md-7 col-sm-12'p>>", // horizobtal scrollable datatable
           
            "processing" : false,
			"serverSide" : true,
			//PraticaConRischioTableAction.java method="searchPraticheConRischio()
			"sAjaxSource" : "/CruscottoAuditAtpoWebWeb/json/searchPraticheConRischio",
			"fnServerData" : function(sSource, aoData, fnCallback) {

				// aggiunge ai dati l idSessione
				var idSessione = $("#sessionID").val();
				aoData.push( {
					"name" : "sessionId",
					"value" : idSessione
				});

				$.ajax( {
					"url" : sSource,
					"dataType" : "json",
					"data" : aoData,
					"success" : fnCallback

				});

			},

			"aoColumns" : [
					{
						"mData" : "idSessione",
						"bSearchable" : false,
						"bSortable" : false,
						"fnCreatedCell" : function(nTd, sData, oData,
								iRow, iCol) {
							$(nTd).css('text-align', 'center');
						},

					
						"mRender" : function(data, type, row) {

							var content = '<div class="form-group form-md-checkboxes">';
							content += '<div class="md-checkbox-inline">';
							content += '<div class="md-checkbox">';
							content += '<input type="checkbox"  name="ids" id="checkboxTableRischi_'
									+ row.idVerbale
									+ '" class="md-check chk" value="'
									+ row.idVerbale + '">';
							content += '<label for="checkboxTableRischi_' + row.idVerbale + '">';
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
						"mData" : "protocollo",
						"bSearchable" : true,
						"bSortable" : true
					}, {
						"mData" : "azienda",
						"bSearchable" : true,
						"bSortable" : true
					}, {
						"mData" : "partitaIva",
						"bSearchable" : true,
						"bSortable" : true
					}, {
						"mData" : "dataFineIspezione",
						"bSearchable" : true,
						"bSortable" : true
					}, {
						"mData" : "dataNotifica",
						"bSearchable" : true,
						"bSortable" : true
					},

					{
						"mData" : "tipoNotifica",
						"bSearchable" : true,
						"bSortable" : true
					
					},

					{
						"mData" : "importo",
						"bSearchable" : true,
						"bSortable" : true
					}, {
						"mData" : "rischi",
						"bSearchable" : true,
						"bSortable" : true
					},

			]
		});

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
