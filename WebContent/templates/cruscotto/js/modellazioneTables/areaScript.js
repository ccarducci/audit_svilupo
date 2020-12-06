/*
 * Gestisce le funzioni CRUD dell'area.
 */


// mostra la modale per inserimento/modifica area.
// in modifica è richiamato dalla funzione customScript.getCheckBoxID().
function showAsideArea(operation){
	
	if(operation == 'INS'){
		$('#idArea').val('');
		$('#descrizione').val('');
		$('#dataInizio').val('');
		$('#dataFine').val('');
		
		// nascondo il div con l'eventule dTable dei processi associati
		// (si visualizza solo in modifica area)
		$('#divProcessiTable').hide();
	}else{
		$('#divProcessiTable').show();
	}
	
	$('.modalArea').css({
	        width: jQuery(document).width(),
	        height: jQuery(document).height()
	        
	    });
	sizeModalCustom();
	$('.modalArea').css("display", "block");
	jQuery('.menu-sx-levelone-facility.update').animate({marginRight:'0'}, 400);
	 if(contOne){
		 jQuery('.menu-sx-levelone-facility.update').animate({marginRight:'0'}, 400);
		 contOne = false;
	 }else{
	       
		 $('.modalArea').css("display", "none");
	        contOne = true;
	    }
	
}

function checkValiditaArea(){

	var to = '';
	var dataFine = '';
	if($('#dataFine').val() != undefined && $('#dataFine').val() != ''){
		to = $('#dataFine').val().split('/');
		dataFine = new Date(to[2], to[1] - 1, to[0]);
	}
	
	var toOrig = '';
	var dataFineOrig = '';
	if($('#dataFineOrig').val() != undefined && $('#dataFineOrig').val() != ''){
		toOrig = $('#dataFineOrig').val().split('/');
		dataFineOrig = new Date(to[2], to[1] - 1, to[0]);
	}
	
	// controllo se la dataFine impostata è diversa da quella iniziale
	if(!checkDifferentDate($('#dataFine').val(), $('#dataFineOrig').val())){
		$('#validitaModal').modal();
	}else{
		salvaArea();
	}
	
}



function salvaArea(){
	
	$('#validitaModal').modal('hide');
	
	var idArea = $('#idArea').val();
	var from = $('#dataInizio').val().split('/');
	var dataInizio = new Date(from[2], from[1] - 1, from[0]);
	var descrizione = $('#descrizione').val();
	
	var to = '';
	var dataFine = '';
	var dataFineAsString = '';
	if($('#dataFine').val() != undefined && $('#dataFine').val() != ''){
		to = $('#dataFine').val().split('/');
		dataFine = new Date(to[2], to[1] - 1, to[0]);
	}
	
	if(descrizione == '' || descrizione == undefined){
		$('#messageDiv').html("<b>Attenzione!</b><br>Il campo Descrizione è obbligatorio");
		$('#activeModal').modal();
		return;
	}
	
	if($('#dataInizio').val() == '' || $('#dataInizio').val() == undefined){
		$('#messageDiv').html("<b>Attenzione!</b><br>Il campo Data Inizio è obbligatorio");
		$('#activeModal').modal();
		return;
	}
	
	// controllo dataFine maggiore di dataInizio
	if(dataFine != ''){
		
		if(dataFine <= dataInizio){
			$('#messageDiv').html("<b>Attenzione!</b><br>La Data Fine deve essere maggiore della Data Inizio");
			$('#activeModal').modal();
			return;
		}
		dataFineAsString = dataFine.toLocaleDateString();
		
	}
	
	
	$.ajax( {
		type : 'GET',
		url : '/CruscottoAuditAtpoWebWeb/salvaArea',
		data : { 	'idArea' : idArea,
					'dataInizio' : dataInizio.toLocaleDateString(),
					'dataFine' : dataFineAsString,
					'descrizione' : descrizione
					
		   },
		
		success : function(data) {
			Metronic.stopPageLoading();
			
			if(data.errorChiusuraValiditaArea){
				
				$('#messageDiv').html("<b>Attenzione!</b><br>Errore durante la chiusura validità dell' Area");
				$('#activeModal').modal();
			}else{
			
				$('#area_table').DataTable().ajax.reload();
			}
			
			slideAsideNew('');
			
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
					$.notific8('Errore durante il salvataggio ', settings);
					return; 
		}
	});

}


function eliminaArea(){
	
	var checkedIds = $(".chkArea:checked").map(function() {
	    return this.value;
	  
	  }).toArray();
	
	var idArea = checkedIds[0];

	checkedIds=[];
	
	
		$.ajax( {
			type : 'GET',
			url : '/CruscottoAuditAtpoWebWeb/eliminaArea',
			data : { 	'idArea' : idArea
			   },
			
			success : function(data) {
				Metronic.stopPageLoading();
				
				$('#area_table').DataTable().ajax.reload();
			
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
						$.notific8('Errore durante il salvataggio ', settings);
						return; 
			}
		});
		
		$('#deleteModal').modal('toggle');
}


function attivaArea(idArea, stato){
	
	if(stato == 'Attiva'){
		$('#messageDiv').html("<b>Attenzione!</b><br>L' Area selezionata è già in stato Attiva");
		$('#processoModal').modal();
	}else{
		
		$.ajax( {
			type : 'GET',
			url : '/CruscottoAuditAtpoWebWeb/attivaArea',
			data : { 	'idArea' : idArea,
						'stato' : 'A'
					},
			
			success : function(data) {
				Metronic.stopPageLoading();

				$('#area_table').DataTable().ajax.reload();
				
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
						$.notific8('Errore durante il salvataggio ', settings);
						return; 
			}
		});
	}
	
}

function aggiornaAreaTable(){

	$('#area_table').DataTable().ajax.reload();
	
}