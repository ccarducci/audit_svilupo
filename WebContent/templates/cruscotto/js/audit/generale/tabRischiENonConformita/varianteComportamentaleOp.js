function saveNewVR(){
	
	var idfase=$("#faseVC").find('option:selected').attr("id");
	var descrizioneVC =$('#descrizioneVC').val().trim();
	var codiceVC=$('#codiceVC').val().trim();
	var pesoVC=$('#pesoVC').val().trim();
	var diVC=$('#diVC').val().trim();
	var dfVC=$('#dfVC').val().trim();
	
	
	
	//controllo campi obbligatori
	if(idfase == "" || descrizioneVC =="" || codiceVC=="" || pesoVC=="" || 
			diVC=="" || dfVC=="" ){
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
if(!checkDate(diVC, dfVC)){
	return;
}




 if(!isNumeric(pesoVC)) {
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

//controllo codice
	$.ajax( {
		type : 'POST',
		dataType: 'json',
		//AuAuditAction.java
		url : '/CruscottoAuditAtpoWebWeb/json/checkCodiceVarComp',
		

	success : function(data) {
		console.log(data);
		var vcList= data.vcList;
		for (var i in vcList){
		      if(vcList[i].codicevc.trim() == codiceVC.trim()){
		    	  
		    	  var settings = {
		  				theme: 'teal',
		  				sticky: false,
		  				horizontalEdge: 'top',
		  				verticalEdge: 'right',
		  				life: 3000
		  		};
		  		$.notific8('zindex', 11500);
		  		$.notific8('Codice  già utilizzato', settings);
		  		return;
		      }
		     }
		//se il codice è disponibile salvo in DB
		$.ajax( {
			type : 'POST',
			//AuAuditAction.java
			url : '/CruscottoAuditAtpoWebWeb/json/salvaNuovaVarComp',
			data :{ 
			'vc.idMNonConf' : idfase	,
			'vc.descrizione' : descrizioneVC	,
			'vc.codicevc' : codiceVC	,
			'vc.pesoVc' : pesoVC	,
			'vc.dataInizio' : diVC	,
			'vc.dataFine' : dfVC	

		},


		success : function(data) {
			
			$("#faseVC").val("");
			$('#descrizioneVC').val("");
			$('#codiceVC').val("");
			$('#pesoVC').val("");
			$('#diVC').val("");
			$('#dfVC').val("");
			$('#rischiEnonConformitaVarCompTable').DataTable().ajax.reload();
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
    	
 
	
	
	
	
	return; 
}

function salvaModificaVC(){
	
	var idVC=$("#idVC").val();
	var idNC=$("#scegliNCup").find('option:selected').attr("value");	
	var descrizione=$('#descrizioneVCup').val().trim();
    var codice=$('#codiceVCup').val().trim();
    var peso=$('#pesoVCup').val().trim();
    var dataI=$('#dataInizioVCup').val().trim();
	var dataF=$('#dataFineVCup').val().trim();
	
	//controllo campi obbligatori
	if(idNC == "" || descrizione =="" || codice=="" || peso=="" || 
			dataI=="" || dataF=="" ){
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

//controllo codice
	$.ajax( {
		type : 'POST',
		dataType: 'json',
		//AuAuditAction.java
		url : '/CruscottoAuditAtpoWebWeb/json/checkCodiceVarComp',
		

	success : function(data) {
		console.log(data);
		var vcList= data.vcList;
		for (var i in vcList){
		      if(vcList[i].codicevc.trim() == codice.trim() && vcList[i].idMComp !=  idVC ){
		    	  
		    	  var settings = {
		  				theme: 'teal',
		  				sticky: false,
		  				horizontalEdge: 'top',
		  				verticalEdge: 'right',
		  				life: 3000
		  		};
		  		$.notific8('zindex', 11500);
		  		$.notific8('Codice  già utilizzato', settings);
		  		return;
		      }
		     }
		//se il codice è disponibile salvo in DB
		$.ajax( {
			type : 'POST',
			//AuAuditAction.java
			url : '/CruscottoAuditAtpoWebWeb/json/salvaNuovaVarComp',
			data :{ 
			'vc.idMComp' : idVC,
			'vc.idMNonConf' : idNC	,
			'vc.descrizione' : descrizione	,
			'vc.codicevc' : codice	,
			'vc.pesoVc' : peso	,
			'vc.dataInizio' : dataI	,
			'vc.dataFine' : dataF	
			
		},


		success : function(data) {
			
			$("#faseVC").val("");
			$('#descrizione').val("");
			$('#codice').val("");
			$('#peso').val("");
			$('#dataI').val("");
			$('#dataF').val("");
			$('#rischiEnonConformitaVarCompTable').DataTable().ajax.reload();
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
	return; 
}



function eliminaVC(){
	var checkedIds = $(".chkVC:checked").map(function() {
	    return this.value;
	
	  }).toArray();
 
	var idVC=[];
	var arrayLength=checkedIds.length;
	for (var i = 0; i < arrayLength; i++) {
		var stringCheck= checkedIds[i].split('-');
		var idCv= stringCheck[0];
		idVC.push(idCv.trim());
	}
	
	$.ajax( {
		type : 'POST',
		dataType:'json',
		//AuAuditAction.java
		url : '/CruscottoAuditAtpoWebWeb/json/eliminaVarComp',
		data :{ 
			'idVarianteComportamentale' : idVC.toString()
		

	},


	success : function(data) {
		
		$('#rischiEnonConformitaVarCompTable').DataTable().ajax.reload();
		$("#checkAllvarComp").prop("checked", false);
		
		$('#deleteVcModal').modal('hide');

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

function selectAllVC(){
	
	   var checkAll = $("#checkAllvarComp").prop('checked');
       if (checkAll) {
           $(".chkVC").prop("checked", true);
       } else {
           $(".chkVC").prop("checked", false);
       }
}

