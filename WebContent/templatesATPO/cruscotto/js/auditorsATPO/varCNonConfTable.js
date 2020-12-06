var VarCNonConf  = function() {

	var initTable1 = function() {
		var table = $('#variantComp');



		var oTable = table
		.dataTable( {

			// Internationalisation. For more info refer to
			// http://datatables.net/manual/i18n
			"language" : {
			"sEmptyTable" : "Nessun dato presente nella tabella o Data aggiornamento dati sessione assente",
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

		"sAjaxSource" : "/CruscottoAuditAtpoWebWeb/jsonATPO/searchVComp",
		"processing" : false,
		"serverSide" : true,

		"aoColumns" : [{
			"mData" : "idSVarComp", 
			"bVisible": false,  
			"bSearchable": true, 
			"bSortable" : true 
		},

		{
			"mData" : "descrizioneVarComp",
			"bSearchable" : true,
			"bSortable" : true,
			"defaultContent": " descrizione variante comportamentale assente"
		}, {
			"mData" : "percSuPs",
			"bSearchable" : true,
			"bSortable" : true
		},
		{
			"mData" : "criticita",
			"bSearchable" : true,
			"bSortable" : true,
			"mRender" : function(data, type, row) {
			if(row.criticita == null || row.criticita == ""){
				var content = '<input type="text" id= crit_'+ row.idSVarComp+' class="form-control"'
				+ 'value="">';
				return content;
			}else{
				var content = '<input type="text" id= crit_'+ row.idSVarComp+' class="form-control"'
				+ ' value='+row.criticita+'>';
				return content;
			}

		}
		},{
			"mData" : "azioniCorrettive",
			"bSearchable" : true,
			"bSortable" : true,
			"mRender" : function(data, type, row) {
				if(row.azioniCorrettive == null || row.azioniCorrettive == ""){
					var content = '<input type="text" id= azCor_'+row.idSVarComp+' class="form-control"'
					+ ' value="">';
					return content;
				}else{
					var content = '<input type="text" id= azCor_'+row.idSVarComp+' class="form-control"'
					+ ' value="'+ row.azioniCorrettive + '">';
					return content;
				}

			}
		},{
			"mData" : null,
			"orderable": false,
			"mRender" : function(data, type, row) {
			var content ='<button id="saveVarCompTable" type="button" onclick="salvaVarCompTable('+row.idSVarComp+')" class="btn btn-sm">Salva</button>';
			return content;
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

function salvaVarCompTable(idSVarComp){
	var criticita =document.getElementById("crit_"+idSVarComp).value;
	var azioniCorrett= document.getElementById("azCor_"+idSVarComp).value;
	var idSVarC= idSVarComp;
	
	
	$.ajax( {
		type : 'GET',
		url : '/CruscottoAuditAtpoWebWeb/jsonATPO/saveVarCompInputTextTable',
		data : {
			'criticitaVarComp' : criticita,
			'azioniCorrettVarComp' : azioniCorrett,
			'idSVarComp' : idSVarC
			
			
		},

	success : function(data) {
		
			var settings = {
					theme : 'teal',
					sticky : false,
					horizontalEdge : 'top',
					verticalEdge : 'right',
					life : 3000
			};
			$.notific8('zindex', 11500);
			$.notific8('Salvato con successo', settings);
			return;
		

	},
	error : function(data) {
		

		var settings = {
				theme : 'teal',
				sticky : false,
				horizontalEdge : 'top',
				verticalEdge : 'right',
				life : 3000
		};
		$.notific8('zindex', 11500);
		$.notific8('Errore durante il salvataggio ', settings);
		return;
	}
	});
	
	
}
