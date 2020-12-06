var ADNormativaTable = function (numColDate, numColText, numColNumber) {

    var initTable1 = function (numColDate, numColText, numColNumber) {

    	var table = $('#adnormativa_table');
    	
    	var colonneDaNascondere = 0;
    	var index = 1;
    	var column = '[';
    	
    	column = column + '{ },';
		
		// SEZIONE COLONNE DATE
    	colonneDaNascondere = 2 - numColDate;
		if(numColDate != 0){
			// calcolo le colonne Date da visualizzare
			for (var i = 1; i <= numColDate; i++) {
				column = column + '{ "mData" : "date'+index+'AsString", "bSearchable": true,  "bSortable" : true },';
				index++;
			}
		}
		if(colonneDaNascondere != 0){
			// rendo invisibili le colonne Date (2)
			for (var i = 1; i <= colonneDaNascondere; i++) {
				column = column + '{ "mData" : "date'+index+'AsString", "bVisible": false,  "bSearchable": true,  "bSortable" : true },';
				index++;
			}
		}
		
		colonneDaNascondere = 0;
		index = 1;
		// SEZIONE COLONNE TEXT
		colonneDaNascondere = 10 - numColText;
		if(numColText != 0){
			// calcolo le colonne Text da visualizzare
			for (var i = 1; i <= numColText; i++) {
				column = column + '{ "mData" : "text'+index+'", "bSearchable": true,  "bSortable" : true },';
				index++;
			}
		}
		if(colonneDaNascondere != 0){
			// rendo invisibili le colonne Text (10)
			for (var i = 1; i <= colonneDaNascondere; i++) {
				column = column + '{ "mData" : "text'+index+'", "bVisible": false,  "bSearchable": true,  "bSortable" : true },';
				index++;
			}
		}
		
		
		colonneDaNascondere = 0;
		index = 1;
		// SEZIONE COLONNE NUMBER
		colonneDaNascondere = 10 - numColNumber;
		if(numColNumber != 0){
			// calcolo le colonne Text da visualizzare
			for (var i = 1; i <= numColNumber; i++) {
				column = column +  '{ "mData" : "number'+index+'", "bSearchable": true,  "bSortable" : true },';
				index++;
			}
		}
		if(colonneDaNascondere != 0){
			// rendo invisibili le colonne Number (10)
			for (var i = 1; i <= colonneDaNascondere; i++) {
				column = column + '{ "mData" : "number'+index+'", "bVisible": false,  "bSearchable": true,  "bSortable" : true },';
				index++;
			}
		}
		
		column = column.substring(0, column.length-1) + ']';

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

					"aaSorting" : [ [ 0, 'asc' ] ],

					"lengthMenu" : [ 5, 10, 15, 20 ], 
					
	
					// set the initial value
					"pageLength" : 5,

					"dom" : "<'row' <'col-md-12'T>><'row'<'col-md-6 col-sm-12'l><'col-md-6 col-sm-12'f>r><'table-scrollable't><'row'<'col-md-5 col-sm-12'i><'col-md-7 col-sm-12'p>>", // horizobtal
					"sAjaxSource" : "/CruscottoAuditAtpoWebWeb/json/getAttivitaDettaglioNormativaTable",
					"processing" : false,
					"serverSide" : true,
					"fnServerData": function (sSource, aoData, fnCallback) {
						
							var idTipo = $('#comboTipoNormativa option:selected').val();
							var idAttivitaDettaglio = $('#idAttivitaDettaglio').val();
							
							if(idTipo == undefined || idTipo == ''){
								idTipo = 1;
							}
			              
							aoData.push({ "name": "idTipo", "value": idTipo },
										{ "name": "idAttivitaDettaglio", "value": idAttivitaDettaglio });
			   
			              $.ajax({
			                  "url": sSource,
			                  "dataType": "json",
			                  "data": aoData,
			                  "success": fnCallback
			              })
			          },
					
					"aoColumns": 
						JSON.parse(column),  // elenco delle colonne calcolate sopra
						            
			           "columnDefs": [{   // definisco la prima colonna checkBox assegnandogli id e value
			        	   "bSearchable": false,  
			        	   "bSortable" : false,
			               "targets": 0,
			               "fnCreatedCell": function(nTd, sData, oData, iRow, iCol)
							  {
									$(nTd).css('text-align', 'center');
									$(nTd).css('width', '30px');
							  },
			               "mRender" : function(data, type, row) {
								
								var content='<div class="form-group form-md-checkboxes">';
								content += '<div class="md-checkbox-inline">';
								content += '<div class="md-checkbox">';
								content += '<input type="checkbox"  name="ids" id="checkboxTable_'
										+ row.idDatiTipo
										+ '" class="md-check chkADNormativa" value="'
										+ row.idDatiTipo + '">';
								content += '<label for="checkboxTable_' + row.idDatiTipo + '">';
								content += '<span></span>';
								content += '<span class="check"></span>';
								content += '<span class="box"></span>';
								content += '</label>';
								content += '</div>';
								content += '</div>';
								content += '</div>';
								
								return content;
							}
			           }]
						
        });

       
    }
    return {

        //main function to initiate the module
        init: function (numColDate, numColText, numColNumber) {

            if (!jQuery().dataTable) {
                return;
            }
            initTable1(numColDate, numColText, numColNumber);
        }

    };

}();
