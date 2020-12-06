var NonConfAccVarComp = function () {

	var initTable1 = function () {

		var table = $('#nonConfAccVarComp');

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


		"order": [[0, 'asc']],

		"lengthMenu":  [5, 10, 15, 20],

		// set the initial value
		"pageLength": 5,

		"dom": "<'row' <'col-md-12'T>><'row'<'col-md-6 col-sm-12'l><'col-md-6 col-sm-12'f>r><'table-scrollable't><'row'<'col-md-5 col-sm-12'i><'col-md-7 col-sm-12'p>>", // horizobtal scrollable datatable

		"processing" : false,
		"serverSide" : true,
		//MediaNonConformitaFaseNotifica.java
		"sAjaxSource" : "/CruscottoAuditAtpoWebWeb/json/getVarCompNonConfAccessi",

		"aoColumns" : [{
			"mData" : "idMVarComp",
			"bSearchable" : false,
			"bSortable" : false,
			"bVisible":false
		},{
			"mData" : "descrizioneVarComp",
			"bSearchable" : true,
			"bSortable" : true
		},

		{
			"mData" : "num",
			"bSearchable" : true,
			"bSortable" : true
		},  {
			"mData" : "percSuPs",
			"bSearchable" : true,
			"bSortable" : true
		}, {
			"mData" : "perPesata",
			"bSearchable" : true,
			"bSortable" : true
		},{
			"mData" : "colore",
			"bSearchable" : true,
			"bSortable" : true,
			"fnCreatedCell": function(nTd, sData, oData, iRow, iCol)
			{
			if(sData=='Giallo'){
				$(nTd).css('background-color', 'yellow');
			}else if(sData=='Bianco'){
				$(nTd).css('background-color', 'white');
			}else if(sData=='Rosso'){
				$(nTd).css('background-color', 'red');
			}else if(sData=='Verde'){
				$(nTd).css('background-color', 'green');
			}else if(sData=='Arancio'){
				$(nTd).css('background-color', 'orange');
			}

			$(nTd).css('text-align', 'center');
//			$(nTd).css('width', '22px');
			},
			"defaultContent": "non presente"
		}

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
