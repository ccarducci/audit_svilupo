/*
 * Gestisce le funzioni CRUD della attivita componente.
 */


// mostra la modale per inserimento nuova fase.
function showAsideAttivitaComponente(){
	
	// sbianco i campi della modale
	$('#comboArea').val('');
	$('#comboProcesso').val('');
	$('#comboSottoProcesso').val('');
	$('#descrizione').val('');
	$('#dataInizio').val('');
	$('#dataFine').val('');
	$('#vincolo').val('');
	$('#ordinamento').val('');
	$('#statoAttivo').attr('checked', 'checked');
	
	sizeModalCustom();
	$('.modalAttivitaComponente').css("display", "block");
	jQuery('.menu-sx-levelone-facility.add').animate({marginRight:'0'}, 400);
	 if(contOne){
		 jQuery('.menu-sx-levelone-facility.add').animate({marginRight:'0'}, 400);
		 contOne = false;
	 }else{
	       
		 $('.modalAttivitaComponente').css("display", "none");
	        contOne = true;
	    }
	
}


function salvaAttivitaComponente(operation){
	
	// attivazione delle modali in base all'operazione
	// inserimento/modifica
	var messageDiv = '#messageAttivitaComponenteDiv';
	var modalDiv = '#attivitaComponenteModal';
	if(operation != 'I'){
		messageDiv = '#modificaAttivitaComponenteDiv';
		modalDiv = '#modificaAttivitaComponenteModal';
	}
	
	var to = '';
	var dataFine = '';
	var dataFineAsString = '';
	
	var idAttivitaComponente = $('#idAttivitaComponente').val();
	var idSottoProcesso = $('#comboSottoProcesso option:selected').val();
	var from = $('#dataInizio').val().split('/');
	var dataInizio = new Date(from[2], from[1] - 1, from[0]);
	
	if($('#dataFine').val() != undefined && $('#dataFine').val() != ''){
		to = $('#dataFine').val().split('/');
		dataFine = new Date(to[2], to[1] - 1, to[0]);
	}
	
	var descrizione = $('#descrizione').val();
	var vincolo = $('#vincolo').val();
	var ordinamento = $('#ordinamento').val();
	
	if(idSottoProcesso == '' || idSottoProcesso == undefined){
		$(''+ messageDiv + '').html("<b>Attenzione!</b><br>Il campo Fase è obbligatorio");
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
	
	// Per il momento lo stato parte Attivo di default.
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
		url : '/CruscottoAuditAtpoWebWeb/json/salvaAttivitaComponente',
		data : { 	'attivitaComponente.idAttivitaComponente' : idAttivitaComponente,
					'attivitaComponente.sottoProcesso.idSottoProcesso' : idSottoProcesso,
					'attivitaComponente.dataInizio' : dataInizio.toLocaleDateString(),
					'attivitaComponente.dataFine' : dataFineAsString,
					'attivitaComponente.descrizione' : descrizione,
					'attivitaComponente.vincolo' : vincolo,
					'attivitaComponente.stato' : stato,
					'attivitaComponente.ordinamento' : ordinamento
					
		   },
		
		success : function(data) {
			Metronic.stopPageLoading();
			
				if(data.error){
					
					$(''+ messageDiv + '').html("<b>Attenzione!</b><br>Le date selezionate non sono congruenti con il periodo di validità della Fase associata");
					$(''+modalDiv + '').modal();
				}else if(data.errorOrdinamento){
				
					$(''+ messageDiv + '').html("<b>Attenzione!</b><br>Campo Ordinamento già presente in archivio");
					$(''+modalDiv + '').modal();
					
				}else{
				
					$('#attivitacomponente_table').DataTable().ajax.reload();
				
					if(operation == 'I'){
						
						slideAsideNew('');
					}else{
						$(''+ messageDiv + '').html("Attivita Componente aggiornata con successo");
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


function eliminaAttivitaComponente(){
	
	var checkedIds = $(".chkAttivitaComponente:checked").map(function() {
	    return this.value;
	  
	  }).toArray();
	
	var idAttivitaComponente = checkedIds[0];

	checkedIds=[];
	
	
		$.ajax( {
			type : 'GET',
			url : '/CruscottoAuditAtpoWebWeb/eliminaAttivitaComponente',
			data : { 	'attivitaComponente.idAttivitaComponente' : idAttivitaComponente
			   },
			
			success : function(data) {
				Metronic.stopPageLoading();
				
				$('#attivitacomponente_table').DataTable().ajax.reload();
			
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
		
		$('#deleteAttivitaComponenteModal').modal('toggle');
}


// gestisce il cambia stato attiva/disattiva dell' attivita componente
function gestisciAttivitaComponente(idAttivitaComponente, stato, callerButton){
	
	var cambiaStato = true;
	
	switch(stato){
	
		case 'Attiva' :
			
			if(callerButton == 'Attiva'){
				$('#messageAttivitaComponenteDiv').html("<b>Attenzione!</b><br>L' Attivita Componente selezionata è già in stato Attiva");
				$('#attivitaComponenteModal').modal();
				
				cambiaStato = false;
			}
			
			break;
			
		case 'Disattiva' :
			
			if(callerButton == 'Disattiva'){
				$('#messageAttivitaComponenteDiv').html("<b>Attenzione!</b><br>L' Attivita Componente selezionata è già in stato Disattiva");
				$('#attivitaComponenteModal').modal();
				
				cambiaStato = false;
			}
			
			break;
	
	}
	
	if(cambiaStato){
		
	
		$.ajax( {
			type : 'GET',
			url : '/CruscottoAuditAtpoWebWeb/gestisciAttivitaComponente',
			data : { 	'attivitaComponente.idAttivitaComponente' : idAttivitaComponente,
						'attivitaComponente.stato' : callerButton
					},
			
			success : function(data) {
				Metronic.stopPageLoading();
	
				$('#attivitacomponente_table').DataTable().ajax.reload();
				
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


function resetAttivitaComponenteForm(){
	$("#attivitaComponenteForm").trigger('reset');
	
}

//ricarica la tabella nel Tab Normativa
function ricaricaAttivitaComponenteNormativa(){
	
	var idTipo = $('#comboTipoNormativa option:selected').val();
	if(idTipo == undefined){
		
		idTipo=$('#tipoNormativa').val();
	}
	if(idTipo == 1){
		$("#attivitaCompTabButton").show();
		loadDiv('/CruscottoAuditAtpoWebWeb/getPageCircolariInps', 'attivitaComponenteNormativaDiv', null, 'initCircolariINps');
	}
	
	if(idTipo == 2){
		$("#attivitaCompTabButton").show();
		loadDiv('/CruscottoAuditAtpoWebWeb/getPageNoteDecreti', 'attivitaComponenteNormativaDiv', null, 'initNoteDecreti');
	}
	
	if(idTipo == 3){
		$("#attivitaCompTabButton").show();
		loadDiv('/CruscottoAuditAtpoWebWeb/getPageMessaggiInps', 'attivitaComponenteNormativaDiv', null, 'initMessaggiInps');
	}
	
	if(idTipo == 4){
		$("#attivitaCompTabButton").show();
		loadDiv('/CruscottoAuditAtpoWebWeb/getPageLeggiDecreti', 'attivitaComponenteNormativaDiv', null, 'initLeggiDecreti');
	}
	
	
}


//mostra la modale per associazione attivitacomponente/normativa (Tab Normativa).
function showAsideAttivitaComponenteNormativa(){
	
	var idTipo = $('#comboTipoNormativa option:selected').val();
	var idTipoDescrizione = $('#comboTipoNormativa option:selected').text();
	
	if(idTipo == 1){
		loadDiv('/CruscottoAuditAtpoWebWeb/getCircolariInpsAssociateAsideList', 'acNormativaAsideDiv', null, 'initProcessoCircolariInps');
	}
	if(idTipo == 2){
		loadDiv('/CruscottoAuditAtpoWebWeb/getNoteDecretiAssociateAsideList', 'acNormativaAsideDiv', null, 'initProcessoNoteDecreti');
	}
	if(idTipo == 3){
		loadDiv('/CruscottoAuditAtpoWebWeb/getMessaggiInpsAssociateAsideList', 'acNormativaAsideDiv', null, 'initProcessoMessaggiInps');
	}
	if(idTipo == 4){
		loadDiv('/CruscottoAuditAtpoWebWeb/getLeggiDecretiAssociateAsideList', 'acNormativaAsideDiv', null, 'initProcessoLeggiDecreti');
	}
	
	
	//loadDiv('/CruscottoAuditAtpoWebWeb/getNormativeACAssociateAsideList?idClasse=1&idTipo='+idTipo, 'acNormativaAsideDiv', null, 'initAttivitaComponenteNormativa');
	
	$('.modalAttivitaComponenteNormativa').css("display", "block");
	jQuery('.menu-sx-levelone-facility.add').animate({marginRight:'0'}, 400);
	 if(contOne){
		 jQuery('.menu-sx-levelone-facility.add').animate({marginRight:'0'}, 400);
		 contOne = false;
	 }else{
	       
		 $('.modalAttivitaComponenteNormativa').css("display", "none");
	        contOne = true;
	    }
	 
	 $('#titoloACAside').text('ASSOCIAZIONE ATTIVITA COMPONENTE - NORMATIVA (' + idTipoDescrizione + ')');
	
}


function associaAttivitaComponenteNormativa(){
	
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
	var idAttivitaComponente = $('#idAttivitaComponente').val();
	
	$.ajax( {
		type : 'GET',
		url : '/CruscottoAuditAtpoWebWeb/json/associaAttivitaComponenteNormativa',
		data : { 	'assAcClasse.idAttivitaComponente' : idAttivitaComponente,
					'assAcClasse.idNormativa' : idNormativa,
					'assAcClasse.idTipo' : idTipo
					
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

function rimuoviAssociazioneAttivitaComponenteNormativa(){
	
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
	var idAttivitaComponente = $('#idAttivitaComponente').val();
	
	$.ajax( {
		type : 'GET',
		url : '/CruscottoAuditAtpoWebWeb/json/rimuoviAssociazioneAttivitaComponenteNormativa',
		data : { 	'assAcClasse.idAttivitaComponente' : idAttivitaComponente,
					'assAcClasse.idNormativa' : idNormativa,
					'assAcClasse.idTipo' : idTipo
					
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


//Popola la combo Fase/SottoProcesso in base al processo in input.
function caricaComboSottoProcesso(){

	var idProcesso = $('#comboProcesso option:selected').val();
	
	if(idProcesso != ''){
		
	$.ajax({
	       type: 'POST',
	       url: 'json/getComboSottoProcessoByIdProcesso?' ,
	       data: ( idProcesso != undefined && idProcesso != null)? "processo.idProcesso=" + idProcesso : null,
	       async: false,
	       success: function(data){
		
		        jQuery("select[name='comboSottoProcesso']").find('option').remove();
		        
		        jQuery("select[name='comboSottoProcesso']").append('<option value="">' + '' + '</option>');
		        jQuery("select[name='comboSottoProcesso']").select2('val', '');
		        
		        $.each(data.sottoProcessoList,function(index, value) {
		        	jQuery("select[name='comboSottoProcesso']").append('<option value=' + value.idSottoProcesso + '>' + value.descrizione + '</option>');
	            });           

			}
	    	 
	        });
	
	}else{
		jQuery("select[name='comboSottoProcesso']").find('option').remove();
		jQuery("select[name='comboSottoProcesso']").select2('val', '');
	}
	
}