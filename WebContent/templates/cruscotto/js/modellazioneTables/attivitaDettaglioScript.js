/*
 * Gestisce le funzioni CRUD della attivita dettaglio.
 */


// mostra la modale per inserimento nuova attivita dettaglio.
function showAsideAttivitaDettaglio(){
	
	// sbianco i campi della modale
	$('#comboArea').val('');
	$('#comboProcesso').val('');
	$('#comboSottoProcesso').val('');
	$('#comboAttivitaComponente').val('');
	$('#descrizione').val('');
	$('#dataInizio').val('');
	$('#dataFine').val('');
	$('#statoAttivo').attr('checked', 'checked');
	$('#ordinamento').val('');
	
	sizeModalCustom();
	$('.modalAttivitaDettaglio').css("display", "block");
	jQuery('.menu-sx-levelone-facility.add').animate({marginRight:'0'}, 400);
	 if(contOne){
		 jQuery('.menu-sx-levelone-facility.add').animate({marginRight:'0'}, 400);
		 contOne = false;
	 }else{
	       
		 $('.modalAttivitaDettaglio').css("display", "none");
	        contOne = true;
	    }
	
}


function salvaAttivitaDettaglio(operation){
	
	// attivazione delle modali in base all'operazione
	// inserimento/modifica
	var messageDiv = '#messageAttivitaDettaglioDiv';
	var modalDiv = '#attivitaDettaglioModal';
	if(operation != 'I'){
		messageDiv = '#modificaAttivitaDettaglioDiv';
		modalDiv = '#modificaAttivitaDettaglioModal';
	}
	
	var to = '';
	var dataFine = '';
	var dataFineAsString = '';
	
	var idAttivitaDettaglio = $('#idAttivitaDettaglio').val();
	var idAttivitaComponente = $('#comboAttivitaComponente option:selected').val();
	var from = $('#dataInizio').val().split('/');
	var dataInizio = new Date(from[2], from[1] - 1, from[0]);
	
	if($('#dataFine').val() != undefined && $('#dataFine').val() != ''){
		to = $('#dataFine').val().split('/');
		dataFine = new Date(to[2], to[1] - 1, to[0]);
	}
	
	var descrizione = $('#descrizione').val();
	var ordinamento = $('#ordinamento').val();
	
	if(idAttivitaComponente == '' || idAttivitaComponente == undefined){
		$(''+ messageDiv + '').html("<b>Attenzione!</b><br>Il campo Attivita Componente è obbligatorio");
		$(''+modalDiv + '').modal();
		return;
	}
	if(descrizione == '' || descrizione == undefined){
		$(''+ messageDiv + '').html("<b>Attenzione!</b><br>Il campo Descrizione è obbligatorio");
		$(''+modalDiv + '').modal();
		return;
	}
	if(ordinamento == '' || ordinamento == undefined){
		$(''+ messageDiv + '').html("<b>Attenzione!</b><br>Il campo Ordinamento è obbligatorio");
		$(''+modalDiv + '').modal();
		return;
	}
	if($('#dataInizio').val() == '' || $('#dataInizio').val() == undefined){
		$(''+ messageDiv + '').html("<b>Attenzione!</b><br>Il campo Data Inizio è obbligatorio");
		$(''+modalDiv + '').modal();
		return;
	}
	if(!isInteger(ordinamento.replace('.', ','))){
		$(''+ messageDiv + '').html("<b>Attenzione!</b><br>Il campo Ordinamento richiede valori di tipo numerico");
		$(''+modalDiv + '').modal();
		return;
	}
	
	// Per il momento lo Stato parte Attivo di default.
//	if(document.querySelector('input[name=statoRB]:checked') != null){
//		
//		var stato= document.querySelector('input[name=statoRB]:checked').value;
//		
//	}
	var stato = 'A';
	
	// controllo dataFine maggiore di dataInizio
	if(dataFine != ''){
		
		if(dataFine <= dataInizio){
			$(''+ messageDiv + '').html("<b>Attenzione!</b><br>La Data Fine deve essere maggiore della Data Inizio");
			$(''+modalDiv + '').modal();
			return;
		}
		dataFineAsString = dataFine.toLocaleDateString();
		
	}
	
	
	$.ajax( {
		type : 'GET',
		url : '/CruscottoAuditAtpoWebWeb/json/salvaAttivitaDettaglio',
		data : { 	'attivitaDettaglio.idAttivitaDettaglio' : idAttivitaDettaglio,
					'attivitaDettaglio.attivitaComponente.idAttivitaComponente' : idAttivitaComponente,
					'attivitaDettaglio.dataInizio' : dataInizio.toLocaleDateString(),
					'attivitaDettaglio.dataFine' : dataFineAsString,
					'attivitaDettaglio.descrizione' : descrizione,
					'attivitaDettaglio.stato' : stato,
					'attivitaDettaglio.ordinamento' : ordinamento
					
		   },
		
		success : function(data) {
			Metronic.stopPageLoading();
			
				if(data.error){
					$(''+ messageDiv + '').html("<b>Attenzione!</b><br>Le date selezionate non sono congruenti con il periodo di validità dell' Attivita Componente associata");
					$(''+modalDiv + '').modal();
					
				}else if(data.errorOrdinamento){
				
					$(''+ messageDiv + '').html("<b>Attenzione!</b><br>Campo Ordinamento già presente in archivio");
					$(''+modalDiv + '').modal();
				
				}else{
				
					if(operation == 'I'){
						
						$('#attivitadettaglio_table').DataTable().ajax.reload();
						slideAsideNew('');
					}else{
						$(''+ messageDiv + '').html("Attivita Dettaglio aggiornata con successo");
						$(''+modalDiv + '').modal();
					}
				}
			
			
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


function eliminaAttivitaDettaglio(){
	
	var checkedIds = $(".chkAttivitaDettaglio:checked").map(function() {
	    return this.value;
	  
	  }).toArray();
	
	var idAttivitaDettaglio = checkedIds[0];

	checkedIds=[];
	
	
		$.ajax( {
			type : 'GET',
			url : '/CruscottoAuditAtpoWebWeb/eliminaAttivitaDettaglio',
			data : { 	'attivitaDettaglio.idAttivitaDettaglio' : idAttivitaDettaglio
			   },
			
			success : function(data) {
				Metronic.stopPageLoading();
				
				$('#attivitadettaglio_table').DataTable().ajax.reload();
			
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
		
		$('#deleteAttivitaDettaglioModal').modal('toggle');
}


// gestisce il cambia stato attiva/disattiva dell' attivita dettaglio
function gestisciAttivitaDettaglio(idAttivitaDettaglio, stato, callerButton){
	
	var cambiaStato = true;
	
	switch(stato){
	
		case 'Attiva' :
			
			if(callerButton == 'Attiva'){
				$('#messageAttivitaDettaglioDiv').html("<b>Attenzione!</b><br>L' Attivita Dettaglio selezionata è già in stato Attiva");
				$('#attivitaDettaglioModal').modal();
				
				cambiaStato = false;
			}
			
			break;
			
		case 'Disattiva' :
			
			if(callerButton == 'Disattiva'){
				$('#messageAttivitaDettaglioDiv').html("<b>Attenzione!</b><br>L' Attivita Dettaglio selezionata è già in stato Disattiva");
				$('#attivitaDettaglioModal').modal();
				
				cambiaStato = false;
			}
			
			break;
	
	}
	
	if(cambiaStato){
		
	
		$.ajax( {
			type : 'GET',
			url : '/CruscottoAuditAtpoWebWeb/gestisciAttivitaDettaglio',
			data : { 	'attivitaDettaglio.idAttivitaDettaglio' : idAttivitaDettaglio,
						'attivitaDettaglio.stato' : callerButton
					},
			
			success : function(data) {
				Metronic.stopPageLoading();
	
				$('#attivitadettaglio_table').DataTable().ajax.reload();
				
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


function resetAttivitaDettaglioForm(){
	$("#attivitaDettaglioForm").trigger('reset');
	
}

//ricarica la tabella nel Tab Normativa
function ricaricaAttivitaDettaglioNormativa(){
	
	var idTipo = $('#comboTipoNormativa option:selected').val();
	if(idTipo == undefined){
		
		idTipo=$('#tipoNormativa').val();
	}
	if(idTipo == 1){
		$("#attDettTabButt").show();
		loadDiv('/CruscottoAuditAtpoWebWeb/getPageCircolariInps', 'attivitaDettaglioNormativaDiv', null, 'initCircolariINps');
	}
	
	if(idTipo == 2){
		$("#attDettTabButt").show();
		loadDiv('/CruscottoAuditAtpoWebWeb/getPageNoteDecreti', 'attivitaDettaglioNormativaDiv', null, 'initNoteDecreti');
	}
	
	if(idTipo == 3){
		$("#attDettTabButt").show();
		loadDiv('/CruscottoAuditAtpoWebWeb/getPageMessaggiInps', 'attivitaDettaglioNormativaDiv', null, 'initMessaggiInps');
	}
	
	if(idTipo == 4){
		$("#attDettTabButt").show();
		loadDiv('/CruscottoAuditAtpoWebWeb/getPageLeggiDecreti', 'attivitaDettaglioNormativaDiv', null, 'initLeggiDecreti');
	}
	
}

//mostra la modale per associazione attivitadettaglio/normativa (Tab Normativa).
function showAsideAttivitaDettaglioNormativa(){
	
	var idTipo = $('#comboTipoNormativa option:selected').val();
	var idTipoDescrizione = $('#comboTipoNormativa option:selected').text();
	
	if(idTipo == 1){
		loadDiv('/CruscottoAuditAtpoWebWeb/getCircolariInpsAssociateAsideList', 'adNormativaAsideDiv', null, 'initProcessoCircolariInps');
	}
	if(idTipo == 2){
		loadDiv('/CruscottoAuditAtpoWebWeb/getNoteDecretiAssociateAsideList', 'adNormativaAsideDiv', null, 'initProcessoNoteDecreti');
	}
	if(idTipo == 3){
		loadDiv('/CruscottoAuditAtpoWebWeb/getMessaggiInpsAssociateAsideList', 'adNormativaAsideDiv', null, 'initProcessoMessaggiInps');
	}
	if(idTipo == 4){
		loadDiv('/CruscottoAuditAtpoWebWeb/getLeggiDecretiAssociateAsideList', 'adNormativaAsideDiv', null, 'initProcessoLeggiDecreti');
	}
	
	//loadDiv('/CruscottoAuditAtpoWebWeb/getNormativeADAssociateAsideList?idClasse=1&idTipo='+idTipo, 'adNormativaAsideDiv', null, 'initAttivitaDettaglioNormativa');
	
	$('.modalAttivitaDettaglioNormativa').css("display", "block");
	jQuery('.menu-sx-levelone-facility.add').animate({marginRight:'0'}, 400);
	 if(contOne){
		 jQuery('.menu-sx-levelone-facility.add').animate({marginRight:'0'}, 400);
		 contOne = false;
	 }else{
	       
		 $('.modalAttivitaDettaglioNormativa').css("display", "none");
	        contOne = true;
	    }
	 
	 $('#titoloADAside').text('ASSOCIAZIONE ATTIVITA DETTAGLIO - NORMATIVA (' + idTipoDescrizione + ')');
	
}


function associaAttivitDettaglioNormativa(){
	
	var idTipo = $('#comboTipoNormativa option:selected').val();

	var checkedIds;
	
	
	if(idTipo == 1){
		 checkedIds = $(".chkCircolariInps:checked").map(function() {
			    return this.value;
			
			  }).toArray();
	}
	if(idTipo == 2){
		 checkedIds = $(".chkNoteDecreti:checked").map(function() {
			    return this.value;
			
			  }).toArray();
	}
	if(idTipo == 3){
		 checkedIds = $(".chkMessaggiInps:checked").map(function() {
			    return this.value;
			
			  }).toArray();
	}
	if(idTipo == 4){
		 checkedIds = $(".chkLeggiDecreti:checked").map(function() {
			    return this.value;
			
			  }).toArray();
	}
	
 // controlla checkbox selezionati
	if(!checkSelection(checkedIds)){
		return
	}

	var idNormativa = checkedIds[0];
	var idAttivitaDettaglio = $('#idAttivitaDettaglio').val();
	
	$.ajax( {
		type : 'GET',
		url : '/CruscottoAuditAtpoWebWeb/json/associaAttivitaDettaglioNormativa',
		data : { 	'assAdClasse.idAttivitaDettaglio' : idAttivitaDettaglio,
					'assAdClasse.idNormativa' : idNormativa,
					'assAdClasse.idTipo' : idTipo
					
		   },
		
		success : function(data) {
			Metronic.stopPageLoading();

			slideAsideNew('');
			if(idTipo == 1){
				$('#circolariInpsTable').DataTable().ajax.reload();
				$(".chkCircolariInps").removeAttr('checked');
			}
			if(idTipo == 2){
				$('#noteDecretiTable').DataTable().ajax.reload();
				$(".chkNoteDecreti").removeAttr('checked');
			}
			if(idTipo == 3){
				$('#messaggiInpsTable').DataTable().ajax.reload();
				$(".chkMessaggiInps").removeAttr('checked');
			}
			if(idTipo == 4){
				$('#leggiDecretiTable').DataTable().ajax.reload();
				$(".chkLeggiDecreti").removeAttr('checked');
			}
			
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

function rimuoviAssociazioneAttivitaDettaglioNormativa(){
	
	var idTipo = $('#comboTipoNormativa option:selected').val();

	var checkedIds;
	
	if(idTipo == 1){
		 checkedIds = $(".chkCircolariInps:checked").map(function() {
			    return this.value;
			
			  }).toArray();
	}
	if(idTipo == 2){
		 checkedIds = $(".chkNoteDecreti:checked").map(function() {
			    return this.value;
			
			  }).toArray();
	}
	if(idTipo == 3){
		 checkedIds = $(".chkMessaggiInps:checked").map(function() {
			    return this.value;
			
			  }).toArray();
	}
	if(idTipo == 4){
		 checkedIds = $(".chkLeggiDecreti:checked").map(function() {
			    return this.value;
			
			  }).toArray();
	}
	
 // controlla checkbox selezionati
	if(!checkSelection(checkedIds)){
		return
	}

	var idNormativa = checkedIds[0];
	var idAttivitaDettaglio = $('#idAttivitaDettaglio').val();
	
	$.ajax( {
		type : 'GET',
		url : '/CruscottoAuditAtpoWebWeb/json/rimuoviAssociazioneAttivitaDettaglioNormativa',
		data : { 	'assAdClasse.idAttivitaDettaglio' : idAttivitaDettaglio,
					'assAdClasse.idNormativa' : idNormativa,
					'assAdClasse.idTipo' : idTipo
					
		   },
		
		success : function(data) {
			Metronic.stopPageLoading();

			if(idTipo == 1){
				$('#circolariInpsTable').DataTable().ajax.reload();
			}
			if(idTipo == 2){
				$('#noteDecretiTable').DataTable().ajax.reload();
			}
			if(idTipo == 3){
				$('#messaggiInpsTable').DataTable().ajax.reload();
			}
			if(idTipo == 4){
				$('#leggiDecretiTable').DataTable().ajax.reload();
			}
			
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


//Popola la combo Attivita Componente in base al sottoprocesso in input.
function caricaComboAttivitaComponente(){

	var idSottoProcesso = $('#comboSottoProcesso option:selected').val();
	
	if(idSottoProcesso != ''){
		
	$.ajax({
	       type: 'POST',
	       url: 'json/getComboAttivitaComponenteByIdSottoProcesso?' ,
	       data: ( idSottoProcesso != undefined && idSottoProcesso != null)? "sottoProcesso.idSottoProcesso=" + idSottoProcesso : null,
	       async: false,
	       success: function(data){
		
		        jQuery("select[name='comboAttivitaComponente']").find('option').remove();
		        
		        jQuery("select[name='comboAttivitaComponente']").append('<option value="">' + '' + '</option>');
		        jQuery("select[name='comboAttivitaComponente']").select2('val', '');
		        
		        $.each(data.attivitaComponenteList,function(index, value) {
		        	jQuery("select[name='comboAttivitaComponente']").append('<option value=' + value.idAttivitaComponente + '>' + value.descrizione + '</option>');
	            });           

			}
	    	 
	        });
	
	}else{
		jQuery("select[name='comboAttivitaComponente']").find('option').remove();
		jQuery("select[name='comboAttivitaComponente']").select2('val', '');
	}
	
}