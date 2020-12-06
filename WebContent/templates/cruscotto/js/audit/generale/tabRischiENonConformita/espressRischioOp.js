function saveNewEsprRisk(){

	var codiceRischio=$("#scegliRischio").find('option:selected').attr("id");
	var descrizioneEsprRischio =$('#descrizioneEsprRischio').val().trim();
	var codiceEsprRischio=$('#codiceEsprRischio').val().trim();
	var percentualeEsprRischio=$('#percentualeEsprRischio').val().trim();
	var raggruppamentoEsprRischio=$('#raggruppamentoEsprRischio').val().trim();
	var dataInizio=$('#dataInizioEspr').val().trim();
	var dataFine=$('#dataFineEspr').val().trim();

//controllo campi obbligatori
	if(codiceRischio == "" || descrizioneEsprRischio =="" || codiceEsprRischio=="" || percentualeEsprRischio=="" || 
		raggruppamentoEsprRischio=="" || dataInizio=="" ){
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
if(!checkDate(dataInizio, dataFine)){
	return
}

var percentualeEsprRischio=checkDecimal(percentualeEsprRischio);


 if(!isNumeric(percentualeEsprRischio)) {
	 var settings = {
				theme: 'teal',
				sticky: false,
				horizontalEdge: 'top',
				verticalEdge: 'right',
				life: 5000
		};
		$.notific8('zindex', 11500);
		$.notific8('Percentuale rischio deve essere un numero intero o decimale', settings);
		return; 
	 } 

    	
 // Il campo codice espressione rischio non deve essere presente 
 // nella tabella AU_M_RISESPR altrimenti errore bloccante “Codice rischio già utilizzato”  
    
$.ajax( {
	type : 'POST',
	dataType: 'json',
	//AuAuditAction.java
	url : '/CruscottoAuditAtpoWebWeb/json/checkCodiceEsprRischio',
	

success : function(data) {
	console.log(data);
	var esprRischi= data.auMRisepr;
	for (var i in esprRischi){
	      if(esprRischi[i].codice.trim() == codiceEsprRischio.trim()){
	    	  var settings = {
	  				theme: 'teal',
	  				sticky: false,
	  				horizontalEdge: 'top',
	  				verticalEdge: 'right',
	  				life: 3000
	  		};
	  		$.notific8('zindex', 11500);
	  		$.notific8('Codice già utilizzato', settings);
	  		return
	      }
	     }
	//se il codice è disponibile salvo in DB
	$.ajax( {
		type : 'POST',
		//AuAuditAction.java
		url : '/CruscottoAuditAtpoWebWeb/json/salvaNuovoEsprRischio',
		data :{ 
		'esprRischio.idMRischio' : codiceRischio	,
		'esprRischio.descrizione' : descrizioneEsprRischio	,
		'esprRischio.codice' : codiceEsprRischio	,
		'esprRischio.percTotaleRischio' : percentualeEsprRischio	,
		'esprRischio.raggruppamentoRischio' : raggruppamentoEsprRischio	,
		'esprRischio.dataInizio' : dataInizio	,
		'esprRischio.dataFine' : dataFine
		
		

	},


	success : function(data) {
		
		$('#descrizioneEsprRischio').val("");
		$('#codiceEsprRischio').val("");
		$('#percentualeEsprRischio').val("");
		$('#raggruppamentoEsprRischio').val("");
		$('#dataInizioEspr').val("");
		$('#dataFineEspr').val("");
		$('#espressRischioTable').DataTable().ajax.reload();
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
	$.notific8('Errore durante il controllo del codice rischio ', settings);
	return; 
}
});
//***


	
}



	
function salvaModificaEsprRischio(){
	
	var idEsprRischio=$("#idEsprRiskAside").val().trim();
	
	var idRischio=$("#scegliRischioUp").find('option:selected').attr("value");
	var descrizioneEsprRischio =$('#descrizioneEspr').val().trim();
	var codiceEsprRischio=$('#codiceE').val().trim();
	var percentualeEsprRischio=$('#percentualeT').val().trim();
	var raggruppamentoEsprRischio=$('#raggruppamento').val().trim();
	var dataInizio=$('#dataInizioE').val().trim();
	var dataFine=$('#dataFineE').val().trim();

	//controllo campi obbligatori
	if(idRischio == "" || descrizioneEsprRischio =="" || codiceEsprRischio=="" || percentualeEsprRischio=="" || 
			raggruppamentoEsprRischio=="" || dataInizio=="" ){
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
	if(!checkDate(dataInizio, dataFine)){
		return
	}

	var percentualeEsprRischio=checkDecimal(percentualeEsprRischio);


	 if(!isNumeric(percentualeEsprRischio)) {
		 var settings = {
					theme: 'teal',
					sticky: false,
					horizontalEdge: 'top',
					verticalEdge: 'right',
					life: 5000
			};
			$.notific8('zindex', 11500);
			$.notific8('Percentuale rischio deve essere un numero intero o decimale', settings);
			return; 
		 } 

	    	
	 // Il campo codice espressione rischio non deve essere presente 
	 // nella tabella AU_M_RISESPR altrimenti errore bloccante “Codice rischio già utilizzato”  
	    
	$.ajax( {
		type : 'POST',
		dataType: 'json',
		//AuAuditAction.java
		url : '/CruscottoAuditAtpoWebWeb/json/checkCodiceEsprRischio',
		

	success : function(data) {
			console.log(data);
			var esprRischi= data.auMRisepr;
			for (var i in esprRischi){
			      if(esprRischi[i].codice.trim() == codiceEsprRischio.trim() && idEsprRischio.trim() != esprRischi[i].idMRisepr ){
			    	  var settings = {
			  				theme: 'teal',
			  				sticky: false,
			  				horizontalEdge: 'top',
			  				verticalEdge: 'right',
			  				life: 3000
			  		};
			  		$.notific8('zindex', 11500);
			  		$.notific8('Codice espressione rischio già utilizzato ', settings);
			  		return
			      }
			     }
			//se il codice è disponibile salvo modifiche in DB
		
		
			$.ajax( {
				type : 'POST',
				//AuAuditAction.java
				url : '/CruscottoAuditAtpoWebWeb/json/salvaNuovoEsprRischio',
				data :{ 
				'esprRischio.idMRisepr' : idEsprRischio	,
				'esprRischio.idMRischio' : idRischio,
				'esprRischio.descrizione' : descrizioneEsprRischio	,
				'esprRischio.codice' : codiceEsprRischio	,
				'esprRischio.percTotaleRischio' : percentualeEsprRischio	,
				'esprRischio.raggruppamentoRischio' : raggruppamentoEsprRischio	,
				'esprRischio.dataInizio' : dataInizio	,
				'esprRischio.dataFine' : dataFine

			},


			success : function(data) {
				$('#descrizioneRischio').val("");
				$('#pesoRischio').val("");
				$('#decrMedia').val("");
				$('#codiceRischio').val("");
				$('#dataInizio').val("");
				$('#dataFine').val("");
				$('#espressRischioTable').DataTable().ajax.reload();
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
			$.notific8('Errore durante il controllo del codice rischio ', settings);
			return; 
		}
		});
		}


//premendo il checkbox sull head vengono selezionate tutte le righe
function selectAllEspr(){
	
	
    var checkAll = $("#checkAllEspr").prop('checked');
        if (checkAll) {
            $(".chkEspRischio").prop("checked", true);
        } else {
            $(".chkEspRischio").prop("checked", false);
        }
    

}

function eliminaEsprRischio(){
	
	var checkedIds = $(".chkEspRischio:checked").map(function() {
	    return this.value;
	
	  }).toArray();
 
	
	
	
	var idEsprRischio=[];
	var arrayLength=checkedIds.length;
	for (var i = 0; i < arrayLength; i++) {
		var stringCheck= checkedIds[i].split('-');
		var idEspRischio= stringCheck[0];
	   idEsprRischio.push(idEspRischio.trim());
	}
	
	$.ajax( {
		type : 'POST',
		dataType:'json',
		//AuAuditAction.java
		url : '/CruscottoAuditAtpoWebWeb/json/eliminaEsprRischio',
		data :{ 
			'listaIdEsprRischio' : idEsprRischio.toString()
		

	},


	success : function(data) {
		
	
		
		$('#espressRischioTable').DataTable().ajax.reload();
		$("#checkAllEspr").prop("checked", false);
		$('#deleteEsprRischioModal').modal('hide');

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

