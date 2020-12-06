/*
 * TABELLA AUDITOR/GENERALE
 */

var UsersTable = function() {

	var initTable1 = function() {
		var table = $('#users_table');
		
		

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

					"order" : [ [ 0, 'asc' ] ],

					"lengthMenu" :  [ 5, 10, 15, 20 ], 
					                
					
					
					// set the initial value
					"pageLength" : 5,

					"dom" : "<'row' <'col-md-12'T>><'row'<'col-md-6 col-sm-12'l><'col-md-6 col-sm-12'f>r><'table-scrollable't><'row'<'col-md-5 col-sm-12'i><'col-md-7 col-sm-12'p>>", // horizobtal
					"sAjaxSource" : "/CruscottoAuditAtpoWebWeb/json/searchUsers",
					"processing" : false,
					"serverSide" : true,

					"aoColumns" : [
							{
								"mData" : "idUtente",
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
									content += '<input type="checkbox"  name="ids" id="checkboxTable_'
											+ row.idUtente
											+ '" class="md-check chkGen" value="'
											+ row.idUtente + '">';
									content += '<label for="checkboxTable_' + row.idUtente + '">';
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
								"mData" : "nome",
								"bSearchable" : false,
								"bSortable" : false
							}, {
								"mData" : "cognome",
								"bSearchable" : false,
								"bSortable" : false
							}, {
								"mData" : "username",
								"bSearchable" : true,
								"bSortable" : true
								
							},{
								"mData" : "email",
								"bSearchable" : true,
								"bSortable" : true
								
							}, {
								"mData" : "attivo",
								"bSearchable" : true,
								"bSortable" : true,
								"mRender" : function(data) {
									if(data==null){
										var voidDate="";
										return voidDate;
									}else{
										if(data=='A'){return 'SI';}
										if(data=='N'){return 'NO';}
									}
								}
							},

							{
								"mData" : "dataUltimoAccesso",
								"bSearchable" : true,
								"bSortable" : true,
								"mRender" : function(data) {
									if(data==null){
										var voidDate="";
										return voidDate;
									}else{
										return formatCustomDate(data);
										
									}
								}
							},

							

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






//SALVA NUOVO AUDIT
function saveNewUser(){
	
	Metronic.startPageLoading();
	var nome=$('#nome').val();
	var cognome=$('#cognome').val();
	var email=$('#email').val();
	var username =$('#username').val();
	var attivo=$('#attivo').val();
	var idUtente=$('#idUtente').val();
	
	var ruoli = "";
	var ruoliDescr = [];
	$('#rolechecks input:checked').each(function() {
		ruoli=ruoli+this.id+",";
		ruoliDescr.push(this.getAttribute('descr'));
		
    });
	
	var isdirigente = ruoliDescr.indexOf("dirigente") != -1;
	var isdelegato01 = ruoliDescr.indexOf("delegato01")!= -1;
	var isamministratore = ruoliDescr.indexOf("amministratore")!= -1;
	var isauditors01 = ruoliDescr.indexOf("auditors01")!= -1;
	var isdelegato01 = ruoliDescr.indexOf("delegato01")!= -1;
	
	//alert("isdirigente "+isdirigente+" isdelegato01 "+isdelegato01+" isamministratore "+isamministratore);
	if(isdirigente && isdelegato01){
		Metronic.stopPageLoading();
		var settings = {
				theme: 'teal',
				sticky: false,
				horizontalEdge: 'top',
				verticalEdge: 'right',
				life: 3000
		};
		$.notific8('zindex', 11500);
		$.notific8('Ruolo dirigente e delegato01 incompatibili', settings);
		return;	
	}
	if(isamministratore && isauditors01){
		Metronic.stopPageLoading();
		var settings = {
				theme: 'teal',
				sticky: false,
				horizontalEdge: 'top',
				verticalEdge: 'right',
				life: 3000
		};
		$.notific8('zindex', 11500);
		$.notific8('Ruolo amministratore non può essere associato al ruolo auditors01', settings);
		return;	
	}
	
	if(!nome || !cognome || !username || ruoli.length==0){
		$('#info').show();
		Metronic.stopPageLoading();
		return;
	}else{
		ruoli=ruoli.substring(0, ruoli.length - 1);
		$('#info').hide();
		$.ajax( {
			type : 'GET',
			url : '/CruscottoAuditAtpoWebWeb/salvaNuovoUtente',
			data :{ 
			'utente.nome' : nome	,
			'utente.cognome' : cognome	,
			'utente.email' : email	,
			'utente.username' : username	,
			'utente.attivo' : attivo,
			'utente.idUtente' : idUtente,
			'ruoli' : ruoli

		},


		success : function(data) {
			Metronic.stopPageLoading();
			loadDiv('/CruscottoAuditAtpoWebWeb/usersList', 'appView', null, 'initUserGenerale' );

			$('#nome').val("");
			$('#cognome').val("");
			$('#email').val("");
			$('#username').val("");
			$('#attivo').val("");
			return; 


		},error: function(data){
			Metronic.stopPageLoading();
			var settings = {
					theme: 'teal',
					sticky: false,
					horizontalEdge: 'top',
					verticalEdge: 'right',
					life: 3000
			};
			$.notific8('zindex', 11500);
			$.notific8('Errore durante il salvataggio ', settings);
			return; 
		}
		});
	}
}


	 
	