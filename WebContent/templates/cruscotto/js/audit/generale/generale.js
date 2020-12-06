/****************
 *AUDIT GENERALE* 			
 ****************/

//SALVA NUOVO AUDIT
function saveNewAudit(){
	Metronic.startPageLoading();
	var nome=$('#nomeAudit').val().trim();
	var descrizione=$('#descrizioneAudit').val().trim();
	var dataInizio=$('#dataInizioAudit').val().trim();
	var dataFine=$('#dataFineAudit').val().trim();
	var input =$('#inputAudit').val().trim();
	var output=$('#outputAudit').val().trim();
	var obiettivi=$('#obiettiviAudit').val().trim();

	if(!nome || !dataInizio){
		$('#info').show();
		Metronic.stopPageLoading();
		return;
	}else{
		$('#info').hide();
		$.ajax( {
			type : 'GET',
			//AuAuditAction.java
			url : '/CruscottoAuditAtpoWebWeb/salvaNuovoAudit',
			data :{ 
			'audit.nome' : nome	,
			'audit.descrizione' : descrizione	,
			'audit.dataInizio' : dataInizio	,
			'audit.dataFine' : dataFine	,
			'audit.input' : input	,
			'audit.output' : output	,
			'audit.obiettivo' : obiettivi	

		},


		success : function(data) {
			Metronic.stopPageLoading();
			loadDiv('/CruscottoAuditAtpoWebWeb/auAudit', 'appView', null, 'initAuditGenerale' );

			$('#nomeAudit').val("");
			$('#descrizioneAudit').val("");
			$('#versioneAudit').val("");
			$('#dataInizioAudit').val("");
			$('#dataFineAudit').val("");
			$('#inputAudit').val("");
			$('#outputAudit').val("");
			$('#obiettiviAudit').val("");
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

//ELIMINA AUDIT
function checkIfAuditHasChildren(){
	var checkedIds = $(".chkGen:checked").map(function() {
	    return this.value;
	  }).toArray();
	
	var idAudit = checkedIds[0];
	checkedIds=[];
	
	$.ajax( {
		type : 'GET',
		url : '/CruscottoAuditAtpoWebWeb/json/checkIfAuditHasChildren',
		data : {'idAudit':idAudit},		
		success : function(data) {
			   var isAuditReferenced=data.isAuditReferenced;
			   var numeroQuestionari=data.questionariAssociatiAudit;
			   
			   if(isAuditReferenced!=0){
				   var settings = {
							theme: 'teal',
							sticky: false,
							horizontalEdge: 'top',
							verticalEdge: 'right',
							life: 3000
						};
				   $.notific8('zindex', 11500);
				   $.notific8('Presenti dati associati a questo audit, impossibile eliminare', settings);
			   }else if(isAuditReferenced==0 && numeroQuestionari!=0){
				   $('#deleteQuestionarioModal').modal();
			   }else{
				   $('#deleteAuditModal').modal();
			   }
		}
	});
}



function eliminaAudit(){
	
	var checkedIds = $(".chkGen:checked").map(function() {
	    return this.value;
	  
	  }).toArray();
	
	var idAudit = checkedIds[0];

	checkedIds=[];
	
	
		$.ajax( {
			type : 'GET',
			url : '/CruscottoAuditAtpoWebWeb/eliminaAudit',
			data : { 	'idAudit' : idAudit
			   },
			
			success : function(data) {
				Metronic.stopPageLoading();
				//$('#deleteAuditModal').modal('toggle');isAuditReferenced
				
				$('#generale_table').DataTable().ajax.reload();
			
				return; 
			},error: function(data){
				var settings = {
						theme: 'teal',
						sticky: false,
						horizontalEdge: 'top',
						verticalEdge: 'right',
						life: 3000
					};
				 Metronic.stopPageLoading();
						$.notific8('zindex', 11500);
						$.notific8('Errore durante la cencellazione ', settings);
						return; 
			}
		});
	}
function eliminaIsnc(){ 
	var checkedIds = $(".chkGen:checked").map(function() {
	    return this.value;
	  
	  }).toArray();
	
	var idAudit = checkedIds[0];
	checkedIds=[];
	
	
		$.ajax( {
			type : 'GET',
			url : '/CruscottoAuditAtpoWebWeb/eliminaIsnc',
			data : { 	'idTplIsnc' : idAudit
			   },
			
			success : function(data) {
				Metronic.stopPageLoading();
				//$('#deleteAuditModal').modal('toggle');isAuditReferenced
				$('#deleteIsncModal').modal('toggle');
				$('#isnc_table').DataTable().ajax.reload();
			
				return; 
			},error: function(data){
				var settings = {
						theme: 'teal',
						sticky: false,
						horizontalEdge: 'top',
						verticalEdge: 'right',
						life: 3000
					};
				 Metronic.stopPageLoading();
						$.notific8('zindex', 11500);
						$.notific8('Errore durante la cancellazione ', settings);
						return; 
			}
		});
	}
function eliminaTipologica(){ 
	var checkedIds = $(".chkGen:checked").map(function() {
	    return this.value;
	  
	  }).toArray();
	
	var idAudit = checkedIds[0];
	checkedIds=[];
	
	
		$.ajax( {
			type : 'GET',
			url : '/CruscottoAuditAtpoWebWeb/eliminaTipologica',
			data : { 	'idTplTipologica' : idAudit
			   },
			
			success : function(data) {
				Metronic.stopPageLoading();
				//$('#deleteAuditModal').modal('toggle');isAuditReferenced
				$('#deleteTipologicaModal').modal('toggle');
				$('#tipologiche_table').DataTable().ajax.reload();
			
				return; 
			},error: function(data){
				var settings = {
						theme: 'teal',
						sticky: false,
						horizontalEdge: 'top',
						verticalEdge: 'right',
						life: 3000
					};
				 Metronic.stopPageLoading();
						$.notific8('zindex', 11500);
						$.notific8('Errore durante la cancellazione ', settings);
						return; 
			}
		});
	}
function eliminaCaricamentoPratiche(){ 
	var checkedIds = $(".chkGen:checked").map(function() {
	    return this.value;
	  
	  }).toArray(); 
	
	var idAudit = checkedIds[0];
	checkedIds=[];
	
	
		$.ajax( {
			type : 'GET',
			url : '/CruscottoAuditAtpoWebWeb/eliminaCaricamentoPratiche',
			data : { 	'idBcp' : idAudit
			   },
			
			success : function(data) {
				Metronic.stopPageLoading();
				//$('#deleteAuditModal').modal('toggle');isAuditReferenced
				$('#deleteCaricamentoPraticheModal').modal('toggle');
				$('#caricamentopratiche_table').DataTable().ajax.reload();
			
				return; 
			},error: function(data){
				var settings = {
						theme: 'teal',
						sticky: false,
						horizontalEdge: 'top',
						verticalEdge: 'right',
						life: 3000
					};
				 Metronic.stopPageLoading();
						$.notific8('zindex', 11500);
						$.notific8('Errore durante la cancellazione ', settings);
						return; 
			}
		});
	}
function eliminaUtente(){ 
	
	var checkedIds = $(".chkGen:checked").map(function() {
	    return this.value;
	  
	  }).toArray();
	
	var idAudit = checkedIds[0];

	checkedIds=[];
	
	
		$.ajax( {
			type : 'GET',
			url : '/CruscottoAuditAtpoWebWeb/eliminaUtente',
			data : { 	'idUtente' : idAudit
			   },
			
			success : function(data) {
				Metronic.stopPageLoading();
				//$('#deleteAuditModal').modal('toggle');isAuditReferenced
				$('#deleteUtenteModal').modal('toggle');
				$('#users_table').DataTable().ajax.reload();
			
				return; 
			},error: function(data){
				var settings = {
						theme: 'teal',
						sticky: false,
						horizontalEdge: 'top',
						verticalEdge: 'right',
						life: 3000
					};
				 Metronic.stopPageLoading();
						$.notific8('zindex', 11500);
						$.notific8('Errore durante la cancellazione ', settings);
						return; 
			}
		});
	}
function salvaModificheAudit(){
	Metronic.startPageLoading();
	var nome=$('#nomeAuditUp').val().trim();
	var dataInizio=$('#dataInizioAuditUp').val().trim();
	var dataFine=$('#dataFineAuditUp').val().trim();
	var descrizione=$('#descrizioneAuditUp').val().trim();
	var input =$('#inputAuditUp').val().trim();
	var output=$('#outputAuditUp').val().trim();
	var obiettivi=$('#obiettiviAuditUp').val().trim();
	
	if(!nome || !dataInizio){
		$('#info').show();
		Metronic.stopPageLoading();
		return;
	}else{
	$.ajax( {
		type : 'GET',
		//AuAuditAction.java
		url : '/CruscottoAuditAtpoWebWeb/salvaModificheAudit',
		data :{ 
			'audit.nome' : nome	,
			'audit.dataInizio' : dataInizio	,
			'audit.dataFine' : dataFine	,
			'audit.descrizione' : descrizione	,
			'audit.input' : input	,
			'audit.output' : output	,
			'audit.obiettivo' : obiettivi	
			   
		},
					 
	
		success : function(data) {
			Metronic.stopPageLoading();
			loadDiv('/CruscottoAuditAtpoWebWeb/auAudit', 'appView', null, 'initAuditGenerale' );
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

//inizializza datepicker 
function showDatePicker(pointer){
	if(pointer=="dataInizioAuditUp"){
		var date=$('#dataInizioAuditUp').val();
		$('#dataInizioAuditUp').datepicker({
			format: 'dd/mm/yyyy',
			autoclose: true
		}).focus();		
	}
	if(pointer=="dataFineAuditUp"){
		var date=$('#dataFineAuditUp').val();
		$('#dataFineAuditUp').datepicker({
			format: 'dd/mm/yyyy',
			autoclose: true
		}).focus();	
	}
	

	
}



/**************
 *	 FASI     * 			
 **************/

//aggiunge le fasi selezionate nell aside all'audit
function addFasiAllAudit(){
	 var checkedIds = $(".chkFasi:checked").map(function() {
		    return this.value;
		  
		  }).toArray();
	 if(!checkedIds[0]){
		    var settings = {
		            theme: 'teal',
		            sticky: false,
		            horizontalEdge: 'top',
		            verticalEdge: 'right',
		            life: 5000
		        };
		    $.notific8('zindex', 11500);
		    $.notific8('Nessuna fase selezionata ', settings);
	 } 
	 var idFasiToAdd="";	
	 for(var i=0; i< checkedIds.length; i++){
		idFasiToAdd=idFasiToAdd + checkedIds[i] +"-";
	 }
	 
	 $.ajax( {
			type : 'get',
			//AuAuditAction.java
			url : '/CruscottoAuditAtpoWebWeb/addFasiToAudit',
			data : {'idFaseToAdd' :idFasiToAdd },
		 
						 
		
			success : function(data) {
					console.log("success");
				$('#fasi_da_aggiungere').DataTable().ajax.reload();
				$('#fasi_table').DataTable().ajax.reload();
				
				
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

function eliminaFase(){
	var checkedIds = $(".chkFasiAssegnate:checked").map(function() {
	    return this.value;
	  
	  }).toArray();
	
	var idFase = checkedIds[0];

	checkedIds=[];
	
	
		$.ajax( {
			type : 'GET',
			url : '/CruscottoAuditAtpoWebWeb/eliminaFase',
			data : { 	'idSottoprocesso' : idFase
			   },
			
			success : function(data) {
				Metronic.stopPageLoading();
				
				$('#fasi_table').DataTable().ajax.reload();
			
				return; 
			},error: function(data){
				var settings = {
						theme: 'teal',
						sticky: false,
						horizontalEdge: 'top',
						verticalEdge: 'right',
						life: 3000
					};
				 Metronic.stopPageLoading();
						$.notific8('zindex', 11500);
						$.notific8('Errore durante la cencellazione ', settings);
						return; 
			}
		});
		
		$('#deleteFasiModal').modal('toggle');
	
}

/**************
 *QUESTIONARIO* 			
 **************/

/******* ABOUT "crudOperationIdentifier" 
INDICE DEI VALORI:
save=0
update=1
delete=2
**********/
 function deleteQuestionario(){

		var checkedIds = $(".chkGen:checked").map(function() {
			return this.value;
		}).toArray();

		var idAudit = checkedIds[0];
		checkedIds=[];

		$.ajax( {
			type : 'GET',
			url : '/CruscottoAuditAtpoWebWeb/json/deleteQuestionario',
			data : {'idAudit' : idAudit},		
			success : function(data){
				$('#deleteQuestionarioModal').modal('toggle');
				eliminaAudit();
				
			}
		});
	}







function hideInfo(){
	$('#info').hide();
	$('#infoModifca').hide();
}

function associaDelegato(){
	var checkedIds = $(".chkDelegato:checked").map(function() {
		return this.value;
	}).toArray();

	var idDelegato = checkedIds[0];

	if(checkedIds.length==0){
		var settings = {
				theme: 'teal',
				sticky: false,
				horizontalEdge: 'top',
				verticalEdge: 'right',
				life: 3000
		};
		$.notific8('zindex', 11500);
		$.notific8('Selezionare almeno un delegato', settings);
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
		$.notific8('Selezionare solo un delegato', settings);
	}

	if(checkedIds.length==1){
		$.ajax( {
			type : 'GET',
			url : '/CruscottoAuditAtpoWebWeb/json/associaDelegato',
			data : {'idDelegato':idDelegato},		
			success : function(data) {
				$('#delegati_table').DataTable().ajax.reload();
			}
		});
	}
	checkedIds=[];
}

function dissociaDelegato(){
	var checkedIds = $(".chkDelegato:checked").map(function() {
		return this.value;
	}).toArray();

	var idDelegato = checkedIds[0];

	if(checkedIds.length==0){
		var settings = {
				theme: 'teal',
				sticky: false,
				horizontalEdge: 'top',
				verticalEdge: 'right',
				life: 3000
		};
		$.notific8('zindex', 11500);
		$.notific8('Selezionare almeno un delegato', settings);
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
		$.notific8('Selezionare solo un delegato', settings);
	}

	if(checkedIds.length==1){
		$.ajax( {
			type : 'GET',
			url : '/CruscottoAuditAtpoWebWeb/json/dissociaDelegato',
			data : {'idDelegato':idDelegato},		
			success : function(data) {
				$('#delegati_table').DataTable().ajax.reload();
			}
		});
	}
	checkedIds=[];
}

//mostra le tab in organizzazione audit -> generale > modifica > rischi e non conformita
//il primo caller sarà sarà il tab 'rischi e non conformita' perche carica la pag del tab 'rischi'
//(primo tab ad essere visualizzato)
function showtab(caller){
	switch(caller) {
	//il primo
	case 'tabRischio':
		 	loadDiv('/CruscottoAuditAtpoWebWeb/getPageTabRischio', 'portlet_tab1a', null, 'initRischiTable');
	    break;
	case 'tabEsprRischio':
			loadDiv('/CruscottoAuditAtpoWebWeb/getPageTabEsprRischio', 'portlet_tab2a', null, 'initEsprRischioTable');
		break;
	case 'tabNonConformita':
			loadDiv('/CruscottoAuditAtpoWebWeb/getPageTabNonConformita', 'portlet_tab4a', null, 'initNonConformTable');
		break;
	case 'tabVarComportamentale':
			loadDiv('/CruscottoAuditAtpoWebWeb/getPageTabVarComportamentale', 'portlet_tab5a', null, 'initVarCompTable');
		break;
	case 'questionario':
		  loadDiv('/CruscottoAuditAtpoWebWeb/questionario', 'portlet_tab5_q', null, 'initQuestionario' );
		  break;
		  
	
	}
	
}

