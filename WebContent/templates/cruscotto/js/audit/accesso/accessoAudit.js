function salvaAccessoAudit() {

	Metronic.startPageLoading();

	var campagna = $("#selectCampagna").find('option:selected').attr('id');
	var sede = $('#selectSede').find('option:selected').attr('id');
	var dataInizio = $('#AccessoDataInizio').val();
	var dataFine = $('#AccessoDataFine').val();
	

	if (!campagna || !sede || !dataInizio  ) {
		var settings = {
				theme : 'teal',
				sticky : false,
				horizontalEdge : 'top',
				verticalEdge : 'right',
				life : 3000
			};
			$.notific8('zindex', 11500);
			$.notific8('Questi* campi sono obbligatori', settings);
			Metronic.stopPageLoading();
			return;
		}
	

		$.ajax( {
			type : 'GET',
			// AuAuditAction.java
			url : '/CruscottoAuditAtpoWebWeb/json/salvaNuovoAccessoAudit',
			data : {
				'accesso.idCampagna' : campagna,
				'accesso.sede' : sede,
				'accesso.dataInizio' : dataInizio,
				'accesso.dataFine' : dataFine
				

			},

			success : function(data) {
				Metronic.stopPageLoading();

				$('#accesso_table').DataTable().ajax.reload();

				$('#accessoSede').val("");
				$('#AccessoDataInizio').val("");
				$('#AccessoDataFine').val("");
				
				//document.getElementById("info").style.visibility = "hidden";
				slideAside('');
				return;

			},
			error : function(data) {
				Metronic.stopPageLoading();
				//document.getElementById("info").style.visibility = "hidden";
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

		//document.getElementById("info").style.visibility = "visible";
		
		
		
}

function salvaModificheAccesso() {

	var idCampagna = $("#selectCamp").find('option:selected').attr('id');
	var sede = $("#selectSede").find('option:selected').attr('id');
	var dataInizio = $('#modificaDataInizio').val();
	var dataFine = $('#modificaDataFine').val();


	if (!idCampagna || !sede || !dataInizio ) {
		Metronic.stopPageLoading();
		var settings = {
			theme : 'teal',
			sticky : false,
			horizontalEdge : 'top',
			verticalEdge : 'right',
			life : 3000
		};
		$.notific8('zindex', 11500);
		$.notific8('Questi* campi sono obbligatori ', settings);
		return;
		
	}

		$.ajax( {
			type : 'GET',
			// AuAuditAction.java
			url : '/CruscottoAuditAtpoWebWeb/json/modificaAccessoAudit',
			data : {
				'accesso.idCampagna' : idCampagna,
				'accesso.sede' : sede,
				'accesso.dataInizio' : dataInizio,
				'accesso.dataFine' : dataFine
				
			},

			success : function(data) {
				Metronic.stopPageLoading();
				loadDiv('/CruscottoAuditAtpoWebWeb/initAccessoAudit', 'appView', null, 'initAuditAccesso');
				var settings = {
						theme : 'teal',
						sticky : false,
						horizontalEdge : 'top',
						verticalEdge : 'right',
						life : 3000
					};
					$.notific8('zindex', 11500);
					$.notific8('Modifiche salvate ', settings);
				return;

			},
			error : function(data) {
				Metronic.stopPageLoading();
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



function eliminaAccessoAudit() {
	var checkedIds = $(".chkAcc:checked").map( function() {
		return this.value;

	}).toArray();
	var idSessione = checkedIds[0];
	$.ajax( {
		type : 'GET',
		url : '/CruscottoAuditAtpoWebWeb/eliminaAccessoAudit',
		data : {
			'idSessione' : idSessione
		},

		success : function(data) {

			$('#accesso_table').DataTable().ajax.reload();

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
			Metronic.stopPageLoading();
			$.notific8('zindex', 11500);
			$.notific8('Errore durante la cencellazione ', settings);
			return;
		}
	});

	$('#deleteAccessoModal').modal('toggle');

}

function calcolaDatiDiContesto() {
	Metronic.startPageLoading();
	$.ajax( {
		type : 'GET',
		url : '/CruscottoAuditAtpoWebWeb/json/calcolaDatiDiContesto',
		

		success : function(data) {
			
			var settings = {
					theme: 'teal',
					sticky: false,
					horizontalEdge: 'top',
					verticalEdge: 'right',
					life: 3000
			};
			Metronic.stopPageLoading();
			$.notific8('zindex', 11500);
			$.notific8('Dati di contesto calcolati correttamente!', settings);
			
			
			
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
			Metronic.stopPageLoading();
			$.notific8('zindex', 11500);
			$.notific8('Errore durante il calcolo ', settings);
			return;
		}
	});
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
function salvaNuovoCampioneSessione() {
	Metronic.startPageLoading();
	var nomeCampione = $('#nomeCampione').val();
	$
			.ajax( {
				type : 'GET',
				// AuAuditAction.java
				url : '/CruscottoAuditAtpoWebWeb/json/salvaNuovoCampioneSessione',
				data : {
					'nomeCampione' : nomeCampione

				},

				success : function(data) {

					// mostro le componenti
					document.getElementById("addPratica").style.visibility = "visible";
					document.getElementById("removePratica").style.visibility = "visible";
					document.getElementById("verbaliCampione").style.visibility = "visible";
					Metronic.stopPageLoading();
					//var verbali = VerbaliCampione.init();
					return;

				},
				error : function(data) {
					Metronic.stopPageLoading();
					document.getElementById("info").style.visibility = "hidden";
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

function eliminaPraticaCampione() {

	var checkedIds = $(".chkSessCamp:checked").map( function() {
		return this.value;

	}).toArray();
	var idVerbale = checkedIds[0];
	
	// nel caso di rimozione di una pratica va prima verificato se lato
	//auditors la pratica e la sessione sono aperte. 
	//Se lo sono vanno cancellati anche i dati calcolati con gli indicatori (mettere popup di avviso), altrimenti la pratica non puo essere rimossa.
	$.ajax( {
		type : 'GET',
		url : '/CruscottoAuditAtpoWebWeb/json/checkIfPraticaIsOpen',
		data : {
			'idVerbale' : idVerbale
		},

		success : function(data) {
			if(data.info == 'C'){
				$('#deleteVerbaliCampione').modal('toggle');
				var settings = {
						theme : 'teal',
						sticky : false,
						horizontalEdge : 'top',
						verticalEdge : 'right',
						life : 3000
					};
					Metronic.stopPageLoading();
					$.notific8('zindex', 11500);
					$.notific8('La pratica non può essere cancellata perchè è chiusa ', settings);
					return;
				
			}else{
				$('#verbaliCampione').DataTable().ajax.reload();
				$('#praticheSessioneTable').DataTable().ajax.reload();
				$('#deleteVerbaliCampione').modal('toggle');
			}
			
			
			
			
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
			Metronic.stopPageLoading();
			$.notific8('zindex', 11500);
			$.notific8('Errore durante la cencellazione ', settings);
			return;
		}
	});

}
/*
 * function salvaCampione(){ Metronic.startPageLoading(); var
 * nomeCampione=$('#nomeCampione').val(); $.ajax( { type : 'GET',
 * //AuAuditAction.java url : '/CruscottoAuditAtpoWebWeb/json/salvaNuovoCampione', data :{
 * 'nomeCampione' : nomeCampione },
 * 
 * 
 * success : function(data) { Metronic.stopPageLoading();
 * 
 * $('#campagna_table').DataTable().ajax.reload();
 * 
 * 
 * $('#nomeCampagna').val(""); $('#dataInizio').val(""); $('#dataFine').val("");
 * $('#inizioOsservazione').val(""); $('#fineOsservazione').val("");
 * document.getElementById("info").style.visibility = "hidden"; slideAside('');
 * return;
 * 
 * 
 * },error: function(data){ Metronic.stopPageLoading();
 * document.getElementById("info").style.visibility = "hidden"; var settings = {
 * theme: 'teal', sticky: false, horizontalEdge: 'top', verticalEdge: 'right',
 * life: 3000 }; $.notific8('zindex', 11500); $.notific8('Errore durante il
 * salvataggio ', settings); return; } }); }
 */

// inizializza datepicker
function datePickerAccessoI() {
	var date = $('#modificaDataInizio').val();

	$('#modificaDataInizio').datepicker( {
		format : 'dd/mm/yyyy',
		autoclose : true
	}).focus();

}
function initTabRisultati(){
	
	loadDiv('/CruscottoAuditAtpoWebWeb/risultatiTabJsp', 'risultatiTabPage', null, null);

}
// inizializza datepicker
function datePickerAccessoF() {
	var date = $('#modificaDataFine').val();

	$('#modificaDataFine').datepicker( {
		format : 'dd/mm/yyyy',
		autoclose : true
	}).focus();

}

function stopLoading(){
	Metronic.stopPageLoading();
}

function associaAuditors(){
	var checkedIds = $(".chkAuditors:checked").map(function() {
		return this.value;
	}).toArray();

	var idAuditors = checkedIds[0];

	if(checkedIds.length==0){
		var settings = {
				theme: 'teal',
				sticky: false,
				horizontalEdge: 'top',
				verticalEdge: 'right',
				life: 3000
		};
		$.notific8('zindex', 11500);
		$.notific8('Selezionare almeno un auditor', settings);
	}
	if(checkedIds.length>1){
		var settings = {
				theme: 'teal',
				sticky: false,
				horizontalEdge: 'top',
				verticalEdge: 'right',
				life: 3000
		};
		$.notific8('zindex', 11500);
		$.notific8('Selezionare solo un auditor', settings);
	}

	if(checkedIds.length==1){
		$.ajax( {
			type : 'GET',
			url : '/CruscottoAuditAtpoWebWeb/json/associaAuditors',
			data : {'idAuditors':idAuditors},		
			success : function(data) {
				$('#auditors_table').DataTable().ajax.reload();
			}
		});
	}
	checkedIds=[];
}

function dissociaAuditors(){
	var checkedIds = $(".chkAuditors:checked").map(function() {
		return this.value;
	}).toArray();

	var idAuditors = checkedIds[0];

	if(checkedIds.length==0){
		var settings = {
				theme: 'teal',
				sticky: false,
				horizontalEdge: 'top',
				verticalEdge: 'right',
				life: 3000
		};
		$.notific8('zindex', 11500);
		$.notific8('Selezionare almeno un auditor', settings);
	}
	if(checkedIds.length>1){
		var settings = {
				theme: 'teal',
				sticky: false,
				horizontalEdge: 'top',
				verticalEdge: 'right',
				life: 3000
		};
		$.notific8('zindex', 11500);
		$.notific8('Selezionare solo un auditor', settings);
	}

	if(checkedIds.length==1){
		$.ajax( {
			type : 'GET',
			url : '/CruscottoAuditAtpoWebWeb/json/dissociaAuditors',
			data : {'idAuditors':idAuditors},		
			success : function(data) {
				$('#auditors_table').DataTable().ajax.reload();
			}
		});
	}
	checkedIds=[];
}
	
