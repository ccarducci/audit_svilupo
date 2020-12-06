/*
 * SALVA NUOVO RISCHIO
 */
function salvaNuovoRischio(){
	
	
	var descrizione=$('#descrizioneRischio').val().trim();
	var peso =$('#pesoRischio').val().trim();
	var decrMedia=$('#decrMedia').val().trim();
	var codRischio=$('#codiceRischio').val().trim();
	var dataInizio=$('#dataInizio').val().trim();
	var dataFine=$('#dataFine').val().trim();
	
	//controllo campi obbligatori
	if(descrizione == "" || peso =="" || decrMedia=="" || codRischio=="" || dataInizio==""){
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
	
	var decrMedia=checkDecimal(decrMedia);

	
	 if(!isNumeric(peso) || !isNumeric(decrMedia)) {
		 var settings = {
					theme: 'teal',
					sticky: false,
					horizontalEdge: 'top',
					verticalEdge: 'right',
					life: 5000
			};
			$.notific8('zindex', 11500);
			$.notific8('Peso rischio e valore decremento media devono essere un numero', settings);
			return; 
		 } 
	
	    	
	    	
	    
	
	//controllo codice
	$.ajax( {
		type : 'POST',
		dataType: 'json',
		//AuAuditAction.java
		url : '/CruscottoAuditAtpoWebWeb/json/checkCodiceRischio',
		

	success : function(data) {
		console.log(data);
		var rischi= data.rischi;
		for (var i in rischi){
		      if(rischi[i].codiceRischio.trim() == codRischio.trim()){
		    	  var settings = {
		  				theme: 'teal',
		  				sticky: false,
		  				horizontalEdge: 'top',
		  				verticalEdge: 'right',
		  				life: 3000
		  		};
		  		$.notific8('zindex', 11500);
		  		$.notific8('Codice rischio già utilizzato', settings);
		  		return
		      }
		     }
		//se il codice è disponibile salvo in DB
		$.ajax( {
			type : 'POST',
			//AuAuditAction.java
			url : '/CruscottoAuditAtpoWebWeb/json/salvaNuovoRischio',
			data :{ 
			'rischio.descrizioneRischio' : descrizione	,
			'rischio.pesoRischio' : peso	,
			'rischio.valoreDecrRischio' : decrMedia	,
			'rischio.codiceRischio' : codRischio	,
			'rischio.dataInizio' : dataInizio	,
			'rischio.dataFine' : dataFine	

		},


		success : function(data) {
			$('#descrizioneRischio').val("");
			$('#pesoRischio').val("");
			$('#decrMedia').val("");
			$('#codiceRischio').val("");
			$('#dataInizio').val("");
			$('#dataFine').val("");
			$('#rischiEnonConformitaRischioTable').DataTable().ajax.reload();
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
/*
 * 	MODIFICA RISCHIO 
 */
function salvaModificaRischio(){
	
	  var idRischio= $("#idRiskAside").val();
      var descrizione=$('#descrizioneRischioAside').val().trim();
	  var codice=$('#codiceRischioAside').val().trim();
	  var peso=$('#pesoRischioAside').val().trim();
	  var media=$('#mediaAside').val().trim();
	  var di=$('#dataInizioAside').val().trim();
	  var df=$('#dataFineAside').val().trim();
		
		if(descrizione == "" || peso =="" || media=="" || codice=="" || di==""){
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
		
		if(!checkDate(di, df)){

			return; 

		}
		//controllo codice
		$.ajax( {
			type : 'POST',
			dataType: 'json',
			//AuAuditAction.java
			url : '/CruscottoAuditAtpoWebWeb/json/checkCodiceRischio',
			

		success : function(data) {
			console.log(data);
			var rischi= data.rischi;
			for (var i in rischi){
			      if(rischi[i].codiceRischio.trim() == codice.trim() && idRischio.trim() != rischi[i].idMRischio ){
			    	  var settings = {
			  				theme: 'teal',
			  				sticky: false,
			  				horizontalEdge: 'top',
			  				verticalEdge: 'right',
			  				life: 3000
			  		};
			  		$.notific8('zindex', 11500);
			  		$.notific8('Codice rischio già utilizzato ', settings);
			  		return
			      }
			     }
			//se il codice è disponibile salvo modifiche in DB
		
		
			$.ajax( {
				type : 'POST',
				//AuAuditAction.java
				url : '/CruscottoAuditAtpoWebWeb/json/salvaNuovoRischio',
				data :{ 
				'rischio.idMRischio' : idRischio,
				'rischio.descrizioneRischio' : descrizione,
				'rischio.pesoRischio' : peso,
				'rischio.valoreDecrRischio' : media,
				'rischio.codiceRischio' : codice,
				'rischio.dataInizio' : di,
				'rischio.dataFine' : df	

			},


			success : function(data) {
				$('#descrizioneRischio').val("");
				$('#pesoRischio').val("");
				$('#decrMedia').val("");
				$('#codiceRischio').val("");
				$('#dataInizio').val("");
				$('#dataFine').val("");
				$('#rischiEnonConformitaRischioTable').DataTable().ajax.reload();
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

function eliminaRischio(){
	
	var checkedIds = $(".chkRischio:checked").map(function() {
	    return this.value;
	
	  }).toArray();
 
	var idRischio=[];
	var arrayLength=checkedIds.length;
	for (var i = 0; i < arrayLength; i++) {
	   idRischio.push(checkedIds[i].trim());
	}
	
	$.ajax( {
		type : 'POST',
		dataType:'json',
		//AuAuditAction.java
		url : '/CruscottoAuditAtpoWebWeb/json/eliminaRischio',
		data :{ 
			'listaIdRischio' : idRischio.toString()
		

	},


	success : function(data) {
		$('#deleteRischioModal').modal('hide');
		//dismiss modal
		console.log(data);
		var cod="";
		var rischiDaNonEliminare= data.rischiDaNonEliminare;
		var count=1;
		if(rischiDaNonEliminare.length!=0){
			for (var i in rischiDaNonEliminare){
				var count= count++;
				cod=cod +rischiDaNonEliminare[i].codiceRischio.trim() +",";
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
		  		$.notific8('Il rischio con codice '+ cod +' non puo’ essere eliminato - presenta un legame con un espressione di rischio e/o una domanda ', settings);
			}else{
				var settings = {
		  				theme: 'teal',
		  				sticky: false,
		  				horizontalEdge: 'top',
		  				verticalEdge: 'right',
		  				life: 5000
		  		};
		  		$.notific8('zindex', 11500);
		  		$.notific8('I rischi con codice '+ cod +' non possono essere eliminati - presentano un legame con un espressione di rischio e/o una domanda ', settings);
				
			}
			 
		
		
		}
		$('#rischiEnonConformitaRischioTable').DataTable().ajax.reload();
		$("#checkAll").prop("checked", false);

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
function selectAll(){
	
	
         var checkAll = $("#checkAll").prop('checked');
             if (checkAll) {
                 $(".chkRischio").prop("checked", true);
             } else {
                 $(".chkRischio").prop("checked", false);
             }
         

}
	
	
	




	
