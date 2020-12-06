function saveNewNonConf(){
	
	var codice=$('#codiceNonConf').val().trim();
	var fasiNonC=$("#fasiNonC").find('option:selected').attr("id");
	var descrizioneNonConf =$('#descrizioneNonConf').val().trim();
	var pesoNonC=$('#pesoNonC').val().trim();
	var dataInizioNC=$('#dataInizioNC').val().trim();
	var dataFineNC=$('#dataFineNC').val().trim();
	

	
	//controllo campi obbligatori
	if(codice =="" || fasiNonC == "" || descrizioneNonConf =="" || pesoNonC=="" || dataInizioNC=="" || 
			dataFineNC=="" ){
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
if(!checkDate(dataInizioNC, dataFineNC)){
	return;
}




 if(!isNumeric(pesoNonC)) {
	 var settings = {
				theme: 'teal',
				sticky: false,
				horizontalEdge: 'top',
				verticalEdge: 'right',
				life: 5000
		};
		$.notific8('zindex', 11500);
		$.notific8('Peso non conformita è un numero intero', settings);
		return; 
	 } 

	//controllo codice
	$.ajax( {
		type : 'POST',
		dataType: 'json',
		//AuAuditAction.java
		url : '/CruscottoAuditAtpoWebWeb/json/checkCodiceNC',
		

	success : function(data) {
		
		
		
		var nc= data.nc;
		for (var i in nc){
		      if(nc[i].codiceNc.trim() == codice.trim()){
		    	  var settings = {
		  				theme: 'teal',
		  				sticky: false,
		  				horizontalEdge: 'top',
		  				verticalEdge: 'right',
		  				life: 3000
		  		};
		  		$.notific8('zindex', 11500);
		  		$.notific8('Codice Non Conformità già utilizzato', settings);
		  		return;
		      }
		     }
		
		
		$.ajax( {
			type : 'POST',
			//AuAuditAction.java
			url : '/CruscottoAuditAtpoWebWeb/json/salvaNuovaNonConf',
			data :{ 
			'c.idFase' : fasiNonC	,
			'c.codiceNc':codice,
			'c.descrizione' : descrizioneNonConf	,
			'c.pesoNonConf' : pesoNonC	,
			'c.dataInizio' : dataInizioNC	,
			'c.dataFine' : dataFineNC	
			
			
			

		},


		success : function(data) {
			
			$("#codiceNonConf").val("");
			$("#fasiNonC").val("");
			$('#descrizioneNonConf').val("");
			$('#pesoNonC').val("");
			$('#dataInizioNC').val("");
			$('#dataFineNC').val("");
			$('#rischiEnonConformitaNonConformTable').DataTable().ajax.reload();
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
		$.notific8('Errore durante il controllo del codice ', settings);
		return; 
	}
	});
		
	}

 
	
	



//***


	


function saveModificaNonConf(){
	var idNonConf=$("#idNonConfAside").val().trim();
	var idfaseNonC=$("#faseUp").find('option:selected').attr("value");
	var codice= $('#codiceNcUp').val().trim();
    var descrizione=$('#descrizioneFUp').val().trim();
    var peso= $('#pesofUp').val().trim().replace(",",".");
    var dataInizio=$('#dataInizioFup').val().trim();
	var dataFine=$('#dataFineFup').val().trim();
	
	//controllo campi obbligatori
	if(codice =="" || idfaseNonC == "" || descrizione =="" || peso=="" || dataInizio=="" || 
			dataFine=="" ){
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
	return;
}




 if(!isNumericDecimal(peso)) {
	 var settings = {
				theme: 'teal',
				sticky: false,
				horizontalEdge: 'top',
				verticalEdge: 'right',
				life: 5000
		};
		$.notific8('zindex', 11500);
		$.notific8('Peso non conformita è un numero', settings);
		return; 
	 } 

//controllo codice
	$.ajax( {
		type : 'POST',
		dataType: 'json',
		//AuAuditAction.java
		url : '/CruscottoAuditAtpoWebWeb/json/checkCodiceNC',
		

	success : function(data) {
		
		var nc= data.nc;
		for (var i in nc){
			if(nc[i].codiceNc.trim() == codice.trim() && idNonConf.trim() != nc[i].idMNonConf ){
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
		url : '/CruscottoAuditAtpoWebWeb/json/salvaNuovaNonConf',
		data :{ 
		'c.idMNonConf':idNonConf,
		'c.codiceNc':codice,
		'c.idFase' : idfaseNonC	,
		'c.descrizione' : descrizione	,
		'c.pesoNonConf' : peso	,
		'c.dataInizio' : dataInizio	,
		'c.dataFine' : dataFine
	},


	success : function(data) {
		
		$("#fasiNonC").val("");
		$('#descrizioneFUp').val("");
		$('#codiceNcUp').val("");
		$('#pesofUp').val("");
		$('#dataInizioFup').val("");
		$('#dataFine').val("");
		$('#rischiEnonConformitaNonConformTable').DataTable().ajax.reload();
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


	


	
function selectAllNC(){
	
	
    var checkAll = $("#selectAllNC").prop('checked');
        if (checkAll) {
            $(".chkNonC").prop("checked", true);
        } else {
            $(".chkNonC").prop("checked", false);
        }
    

}

function eliminaNonConf(){
	var checkedIds = $(".chkNonC:checked").map(function() {
	    return this.value;
	
	  }).toArray();
 
	var idNC=[];
	var arrayLength=checkedIds.length;
	for (var i = 0; i < arrayLength; i++) {
	   idNC.push(checkedIds[i].trim());
	}
	
	$.ajax( {
		type : 'POST',
		dataType:'json',
		//AuAuditAction.java
		url : '/CruscottoAuditAtpoWebWeb/json/eliminaNonConform',
		data :{ 
			'listaIdNonConf' : idNC.toString()
		

	},


	success : function(data) {
		$('#deleteNCModal').modal('hide');
		
		
		var descrizioneNC="";
		var ncDaNonEliminare= data.ncDaNonEliminare;
		
		var count=1;
		if(ncDaNonEliminare != "" && ncDaNonEliminare.length!=0){
			for (var i in ncDaNonEliminare){
				var count= count++;
				descrizioneNC=descrizioneNC +ncDaNonEliminare[i].descrizione.trim() +",";
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
		  		$.notific8('La non conformita "'+ descrizioneNC +'" non puo’ essere eliminata - presenta un legame con una variante comportamentale  ', settings);
			}else{
				var settings = {
		  				theme: 'teal',
		  				sticky: false,
		  				horizontalEdge: 'top',
		  				verticalEdge: 'right',
		  				life: 5000
		  		};
		  		$.notific8('zindex', 11500);
		  		$.notific8('Le non conformita '+ cod +' non possono essere eliminate - presentano un legame con una variante comportamentale ', settings);
				
			}
			 
		
		
		}
		$('#rischiEnonConformitaNonConformTable').DataTable().ajax.reload();
		$("#selectAllNC").prop("checked", false);

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
	
	
