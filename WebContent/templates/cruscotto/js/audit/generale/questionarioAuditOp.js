function saveNewDomanda(){
	
	var descrizione=$('#descrizioneD').val().trim(); 
	var peso=$('#pesoD').val().trim();
	var valoreMaxRisp=$('#vMaxR').val().trim(); 
	var rischio =$("#rischioA").find('option:selected').attr("id");
	var varPesoPercentuale =$('#pesoP').val().trim();
	var controlloProcesso=$('#cp').val().trim();
	var dataI=$('#diq').val().trim();
	var dataF=$('#dfq').val().trim();
	
	//controllo campi obbligatori
	if(descrizione == "" || peso =="" || valoreMaxRisp=="" || rischio=="" || 
			varPesoPercentuale=="" || controlloProcesso=="" || dataI=="" || dataF==""){
		var settings = {
			theme: 'teal',
			sticky: false,
			horizontalEdge: 'top',
			verticalEdge: 'right',
			life: 3000
	};
	$.notific8('zindex', 11500);
	$.notific8('Tutti i campi sono obbligatori ', settings);
	return; 
	
}
//controllo date
if(!checkDate(dataI, dataF)){
	return
}

var varPesoPercentuale=checkDecimal(varPesoPercentuale);


 if(!isNumeric(varPesoPercentuale)) {
	 var settings = {
				theme: 'teal',
				sticky: false,
				horizontalEdge: 'top',
				verticalEdge: 'right',
				life: 5000
		};
		$.notific8('zindex', 11500);
		$.notific8('Peso percentuale deve essere un numero intero o decimale', settings);
		return; 
	 } 
 if(!isNumeric(peso)) {
	 var settings = {
				theme: 'teal',
				sticky: false,
				horizontalEdge: 'top',
				verticalEdge: 'right',
				life: 5000
		};
		$.notific8('zindex', 11500);
		$.notific8('Peso è un numero intero', settings);
		return; 
	 } 
    	
 // Il campo codice espressione rischio non deve essere presente 
 // nella tabella AU_M_RISESPR altrimenti errore bloccante “Codice rischio già utilizzato”  
    

	//se il codice è disponibile salvo in DB
	$.ajax( {
		type : 'POST',
		//AuAuditAction.java
		url : '/CruscottoAuditAtpoWebWeb/json/salvaNuovaDomanda',
		data :{ 
		
		'd.descrizione' : descrizione,
		 'd.pesoDomanda' :peso ,
		 'd.valoreMaxRisposta':valoreMaxRisp ,
		 'd.idMRischio':rischio ,
		 'd.pesoPercentuale':varPesoPercentuale ,
		 'd.controlloProcesso':controlloProcesso,
		 'd.dataInizio':dataI,
		 'd.dataFine':dataF
		
		

	},


	success : function(data) {
		
		$('#descrizioneD').val(""); 
		$('#pesoD').val("");
		$('#vMaxR').val(""); 
		$("#rischioA").val("");
		$('#pesoP').val("");
		$('#cp').val("");
		$('#diq').val("");
		$('#dfq').val("");
		
		$('#auditQuestionarioTable').DataTable().ajax.reload();
		slideAsideNew('');
		
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
	
	
	return; 



//***
	
}


function selectAllDomande(){
	
    var checkAll = $("#checkAllDom").prop('checked');
    if (checkAll) {
        $(".chkQuest").prop("checked", true);
    } else {
        $(".chkQuest").prop("checked", false);
    }
}

function salvaModificaDomanda(){
	var descrizione=$('#descrizioneUpd').val().trim(); 
	var peso=$('#pesoUpD').val().trim();
	var valoreMaxRisp=$('#valoreMaxRUpD').val().trim(); 
	var rischio =$("#rischioAUp").find('option:selected').attr("id");
	var varPesoPercentuale =$('#pesoPercupD').val().trim();
	var controlloProcesso=$('#controlloProcessoUpD').val().trim();
	var dataI=$('#dataInizioUpD').val().trim();
	var dataF=$('#dataFineUpD').val().trim();
	
	//controllo campi obbligatori
	if(descrizione == "" || peso =="" || valoreMaxRisp=="" || rischio=="" || 
			varPesoPercentuale=="" || controlloProcesso=="" || dataI=="" || dataF==""){
		var settings = {
			theme: 'teal',
			sticky: false,
			horizontalEdge: 'top',
			verticalEdge: 'right',
			life: 3000
	};
	$.notific8('zindex', 11500);
	$.notific8('Tutti i campi sono obbligatori ', settings);
	return; 
	
}
//controllo date
if(!checkDate(dataI, dataF)){
	return
}

var varPesoPercentuale=checkDecimal(varPesoPercentuale);


 if(!isNumeric(varPesoPercentuale)) {
	 var settings = {
				theme: 'teal',
				sticky: false,
				horizontalEdge: 'top',
				verticalEdge: 'right',
				life: 5000
		};
		$.notific8('zindex', 11500);
		$.notific8('Peso percentuale deve essere un numero intero o decimale', settings);
		return; 
	 } 
 if(!isNumeric(peso)) {
	 var settings = {
				theme: 'teal',
				sticky: false,
				horizontalEdge: 'top',
				verticalEdge: 'right',
				life: 5000
		};
		$.notific8('zindex', 11500);
		$.notific8('Peso è un numero intero', settings);
		return; 
	 } 
    	
    

	
	$.ajax( {
		type : 'POST',
		//AuAuditAction.java
		url : '/CruscottoAuditAtpoWebWeb/json/salvaDomandaModificata',
		data :{ 
		
		'd.descrizione' : descrizione,
		 'd.pesoDomanda' :peso ,
		 'd.valoreMaxRisposta':valoreMaxRisp ,
		 'd.idMRischio':rischio ,
		 'd.pesoPercentuale':varPesoPercentuale ,
		 'd.controlloProcesso':controlloProcesso,
		 'd.dataInizio':dataI,
		 'd.dataFine':dataF
		
		

	},


	success : function(data) {
		
		Metronic.stopPageLoading();
		var settings = {
				theme: 'teal',
				sticky: false,
				horizontalEdge: 'top',
				verticalEdge: 'right',
				life: 3000
		};
		$.notific8('zindex', 11500);
		$.notific8('La domanda è stata modificata ', settings);
		
		
		
		
		$('#auditQuestionarioTable').DataTable().ajax.reload();
		
		
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
		$.notific8('Errore durante la modifica ', settings);
		return; 
	}
	});
	
	
	return; 
	
}



function salvaNuovaRisposta(){
	var descrizione=$('#descrizioneRisp').val().trim(); 
	var valRisposta=$('#valRisposta').val().trim();
	var dataInizio=$('#dataInizioRsp').val().trim(); 
	var dataFine=$('#dataFineRsp').val().trim(); 

	
	//controllo campi obbligatori
	if(descrizione == "" || valRisposta =="" ){
		var settings = {
			theme: 'teal',
			sticky: false,
			horizontalEdge: 'top',
			verticalEdge: 'right',
			life: 3000
	};
	$.notific8('zindex', 11500);
	$.notific8('I campi con * sono obbligatori', settings);
	return; 
	
}
//controllo date
if(!checkDate(dataInizio, dataFine)){
	return
}

if(!isInteger(valRisposta)){
	var settings = {
			theme: 'teal',
			sticky: false,
			horizontalEdge: 'top',
			verticalEdge: 'right',
			life: 3000
	};
	$.notific8('zindex', 11500);
	$.notific8('Il valore della risposta deve essere un numero intero', settings);
	return; 
}
	
	$.ajax( {
		type : 'POST',
		//AuAuditAction.java
		url : '/CruscottoAuditAtpoWebWeb/json/salvaNuovaRisposta',
		data :{ 
		
		 'r.descrizione' : descrizione,
		 'r.valoreRisposta' :valRisposta ,
		 'r.dataInizio':dataInizio,
		 'r.dataFine':dataFine

	},


	success : function(data) {
		
		Metronic.stopPageLoading();
		var settings = {
				theme: 'teal',
				sticky: false,
				horizontalEdge: 'top',
				verticalEdge: 'right',
				life: 3000
		};
		$.notific8('zindex', 11500);
		$.notific8('La risposta è stata salvata ', settings);
		slideAsideNew('');
		
		$('#descrizioneRisp').val("");
		$('#valRisposta').val("");
		$('#dataInizioRsp').val("");
		$('#dataFineRsp').val("");
		
		$('#risposteDomandaTable').DataTable().ajax.reload();
		
		
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
	
	
	return; 
	
}

function salvaModificaRisposta(){
	
	var idRisposta=$('#idRispostaUp').val().trim(); 
	var descrizione=$('#upDescrizioneRsp').val().trim(); 
	var valRisposta=$('#upvaloreRsp').val().trim();
	var dataInizio=$('#updirsp').val().trim(); 
	var dataFine=$('#updfrsp').val().trim(); 

	
	//controllo campi obbligatori
	if(descrizione == "" || valRisposta =="" ){
		var settings = {
			theme: 'teal',
			sticky: false,
			horizontalEdge: 'top',
			verticalEdge: 'right',
			life: 3000
	};
	$.notific8('zindex', 11500);
	$.notific8('I campi con * sono obbligatori', settings);
	return; 
	
}
//controllo date
if(!checkDate(dataInizio, dataFine)){
	return
}

if(!isInteger(valRisposta)){
	var settings = {
			theme: 'teal',
			sticky: false,
			horizontalEdge: 'top',
			verticalEdge: 'right',
			life: 3000
	};
	$.notific8('zindex', 11500);
	$.notific8('Il valore della risposta deve essere un numero intero', settings);
	return; 
}
	
	$.ajax( {
		type : 'POST',
		//AuAuditAction.java
		url : '/CruscottoAuditAtpoWebWeb/json/salvaModificaRisposta',
		data :{ 
		 'r.idRisposta' :idRisposta,
		 'r.descrizione' : descrizione,
		 'r.valoreRisposta' :valRisposta ,
		 'r.dataInizio':dataInizio,
		 'r.dataFine':dataFine

	},


	success : function(data) {
		
		Metronic.stopPageLoading();
		var settings = {
				theme: 'teal',
				sticky: false,
				horizontalEdge: 'top',
				verticalEdge: 'right',
				life: 3000
		};
		$.notific8('zindex', 11500);
		$.notific8('La risposta è stata aggiornata ', settings);
		slideAsideNew('');
		
		$('#idRispostaUp').val("");
		$('#upDescrizioneRsp').val("");
		$('#upvaloreRsp').val("");
		$('#updirsp').val("");
		$('#updfrsp').val("");
		
		
		$('#risposteDomandaTable').DataTable().ajax.reload();
		
		
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
		$.notific8('Errore durante la modifica ', settings);
		return; 
	}
	});
	
	
	return; 
}

function checkAllRisp(){
	  var checkAll = $("#checkAllRsp").prop('checked');
	    if (checkAll) {
	        $(".chkRsp").prop("checked", true);
	    } else {
	        $(".chkRsp").prop("checked", false);
	    }
	
}

function eliminaRsp(){
	var checkedIds = $(".chkRsp:checked").map(function() {
	    return this.value;
	
	  }).toArray();
 
	var idRisposte=[];
	var arrayLength=checkedIds.length;
	for (var i = 0; i < arrayLength; i++) {
	   idRisposte.push(checkedIds[i].trim());
	}
	
	$.ajax( {
		type : 'POST',
		dataType:'json',
		//AuAuditAction.java
		url : '/CruscottoAuditAtpoWebWeb/json/eliminaRisposte',
		data :{ 
			'listaIdRisposte' : idRisposte.toString()
		

	},


	success : function(data) {
		$('#deleteRispostaModal').modal('hide');
		//dismiss modal
		
		
		$('#risposteDomandaTable').DataTable().ajax.reload();
		$("#checkAllRsp").prop("checked", false);

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
		$.notific8('Errore durante la cancellazione ', settings);
		return; 
	}
	});
	
	
}

function eliminaDmd(){
	var checkedIds = $(".chkQuest:checked").map(function() {
	    return this.value;
	
	  }).toArray();
 
	var idDomande=[];
	var arrayLength=checkedIds.length;
	for (var i = 0; i < arrayLength; i++) {
	   idDomande.push(checkedIds[i].trim());
	}
	
	$.ajax( {
		type : 'POST',
		dataType:'json',
		//AuAuditAction.java
		url : '/CruscottoAuditAtpoWebWeb/json/eliminaDomande',
		data :{ 
			'listaIdDomande' : idDomande.toString()
		

	},


	success : function(data) {
		
		var domandeDaNonEliminare= data.domandeDaNonEliminare;
		var count=1;
		
		if(domandeDaNonEliminare.length!=0){
			for (var i in domandeDaNonEliminare){
				var count= count++;
				
			}
			if(count == 1){
				var settings = {
		  				theme: 'teal',
		  				sticky: false,
		  				horizontalEdge: 'top',
		  				verticalEdge: 'right',
		  				life: 5000
		  		};
		  		$.notific8('zindex', 11500);
		  		$.notific8('La domanda che si vuole eliminare è legata ad una risposta  ', settings);
			}else{
				var settings = {
		  				theme: 'teal',
		  				sticky: false,
		  				horizontalEdge: 'top',
		  				verticalEdge: 'right',
		  				life: 5000
		  		};
		  		$.notific8('zindex', 11500);
		  		$.notific8('Le domande non eliminate sono legate ad una risposta ', settings);
				
			}
			 
		
		$('#deleteDomandaModal').modal('hide');
		//dismiss modal
		
		
		$('#auditQuestionarioTable').DataTable().ajax.reload();
		$("#checkAllDom").prop("checked", false);
		}

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
		$.notific8('Errore durante la cancellazione ', settings);
		return; 
	}
	});
	
}


function checkRischiAssociati(){
	initDatePicker();
	$.ajax( {
		type : 'POST',
		dataType:'json',
		//AuAuditAction.java
		url : '/CruscottoAuditAtpoWebWeb/json/checkRischiAssociatiAllaDomanda',

		success : function(data) {
		var rischi= data.rischiAssociati;
		
		
		if(rischi.length!=0){
			slideAsideInps('add-question');
			}else{
				
				var settings = {
						theme: 'teal',
						sticky: false,
						horizontalEdge: 'top',
						verticalEdge: 'right',
						life: 3000
				};
				$.notific8('zindex', 11500);
				$.notific8("Non è possibile creare una nuova domanda. Rischi associati assenti ", settings);
				return; 
			}
			
		
		
		

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
		$.notific8('Errore durante il controllo dei rischi associati ', settings);
		return; 
	}
	});
	
}

