/*
 * Gestisce le funzioni CRUD della fase/sotto processo.
 */


// mostra la modale per inserimento nuova fase.
function showAsideSottoProcesso(){
	
	// sbianco i campi della modale
	$('#comboArea').val('');
	$('#comboProcesso').val('');
	$('#descrizione').val('');
	$('#dataInizio').val('');
	$('#dataFine').val('');
	$('#input').val('');
	$('#output').val('');
	$('#ordinamento').val('');
	$('#statoAttivo').attr('checked', 'checked');
	
	sizeModalCustom();
	$('.modalSottoProcesso').css("display", "block");
	jQuery('.menu-sx-levelone-facility.add').animate({marginRight:'0'}, 400);
	 if(contOne){
		 jQuery('.menu-sx-levelone-facility.add').animate({marginRight:'0'}, 400);
		 contOne = false;
	 }else{
	       
		 $('.modalSottoProcesso').css("display", "none");
	        contOne = true;
	    }
	
}


function salvaSottoProcesso(operation){
	
	// attivazione delle modali in base all'operazione
	// inserimento/modifica
	var messageDiv = '#messageSottoProcessoDiv';
	var modalDiv = '#sottoProcessoModal';
	if(operation != 'I'){
		messageDiv = '#modificaSottoProcessoDiv';
		modalDiv = '#modificaSottoProcessoModal';
	}
	
	var to = '';
	var dataFine = '';
	var dataFineAsString = '';
	
	var idSottoProcesso = $('#idSottoProcesso').val();
	var idProcesso = $('#comboProcesso option:selected').val();
	var from = $('#dataInizio').val().split('/');
	var dataInizio = new Date(from[2], from[1] - 1, from[0]);
	
	if($('#dataFine').val() != undefined && $('#dataFine').val() != ''){
		to = $('#dataFine').val().split('/');
		dataFine = new Date(to[2], to[1] - 1, to[0]);
	}
	
	var descrizione = $('#descrizione').val();
	var input = $('#input').val();
	var output = $('#output').val();
	var ordinamento = $('#ordinamento').val();
	
	if(idProcesso == '' || idProcesso == undefined){
		$(''+ messageDiv + '').html("<b>Attenzione!</b><br>Il campo Processo è obbligatorio");
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
		url : '/CruscottoAuditAtpoWebWeb/json/salvaSottoProcesso',
		data : { 	'sottoProcesso.idSottoProcesso' : idSottoProcesso,
					'sottoProcesso.processo.idProcesso' : idProcesso,
					'sottoProcesso.dataInizio' : dataInizio.toLocaleDateString(),
					'sottoProcesso.dataFine' : dataFineAsString,
					'sottoProcesso.descrizione' : descrizione,
					'sottoProcesso.input' : input,
					'sottoProcesso.output' : output,
					'sottoProcesso.stato' : stato,
					'sottoProcesso.ordinamento' : ordinamento
					
		   },
		
		success : function(data) {
			Metronic.stopPageLoading();
			
				if(data.error){
					
					$(''+ messageDiv + '').html("<b>Attenzione!</b><br>Le date selezionate non sono congruenti con il periodo di validità del Processo associato");
					$(''+modalDiv + '').modal();
				}else if(data.errorOrdinamento){
				
					$(''+ messageDiv + '').html("<b>Attenzione!</b><br>Campo Ordinamento già presente in archivio");
					$(''+modalDiv + '').modal();
					
				}else{	
					$('#faseprocesso_table').DataTable().ajax.reload();
				
					if(operation == 'I'){
						
						slideAsideNew('');
					}else{
						$(''+ messageDiv + '').html("La Fase è stata aggiornata con successo");
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


function eliminaSottoProcesso(){
	
	var checkedIds = $(".chkFaseProcesso:checked").map(function() {
	    return this.value;
	  
	  }).toArray();
	
	var idSottoProcesso = checkedIds[0];

	checkedIds=[];
	
	
		$.ajax( {
			type : 'GET',
			url : '/CruscottoAuditAtpoWebWeb/eliminaSottoProcesso',
			data : { 	'sottoProcesso.idSottoProcesso' : idSottoProcesso
			   },
			
			success : function(data) {
				Metronic.stopPageLoading();
				
				$('#faseprocesso_table').DataTable().ajax.reload();
			
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
		
		$('#deleteSottoProcessoModal').modal('toggle');
}


// gestisce il cambia stato attiva/disattiva del sotto processo
function gestisciSottoProcesso(idSottoProcesso, stato, callerButton){
	
	var cambiaStato = true;
	
	switch(stato){
	
		case 'Attiva' :
			
			if(callerButton == 'Attiva'){
				$('#messageSottoProcessoDiv').html("<b>Attenzione!</b><br>La Fase selezionata è già in stato Attiva");
				$('#sottoProcessoModal').modal();
				
				cambiaStato = false;
			}
			
			break;
			
		case 'Disattiva' :
			
			if(callerButton == 'Disattiva'){
				$('#messageSottoProcessoDiv').html("<b>Attenzione!</b><br>La Fase selezionata è già in stato Disattiva");
				$('#sottoProcessoModal').modal();
				
				cambiaStato = false;
			}
			
			break;
	
	}
	
	if(cambiaStato){
		
	
		$.ajax( {
			type : 'GET',
			url : '/CruscottoAuditAtpoWebWeb/gestisciSottoProcesso',
			data : { 	'sottoProcesso.idSottoProcesso' : idSottoProcesso,
						'sottoProcesso.stato' : callerButton
					},
			
			success : function(data) {
				Metronic.stopPageLoading();
	
				$('#faseprocesso_table').DataTable().ajax.reload();
				
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


function resetSottoProcessoForm(){
	$("#sottoProcessoForm").trigger('reset');
	
}


//ricarica la tabella nel Tab Normativa
function ricaricaSottoProcessoNormativa(){
	
	
	var idTipo = $('#comboTipoNormativa option:selected').val();
	if(idTipo == undefined){
		
		idTipo=$('#tipoNormativa').val();
	}
	if(idTipo == 1){
		
		$("#sottoprocessoNormativaButton").show();
		loadDiv('/CruscottoAuditAtpoWebWeb/getPageCircolariInps', 'sottoProcessoNormativaDiv', null, 'initCircolariINps');
	}
	
	if(idTipo == 2){
		$("#sottoprocessoNormativaButton").show();
		loadDiv('/CruscottoAuditAtpoWebWeb/getPageNoteDecreti', 'sottoProcessoNormativaDiv', null, 'initNoteDecreti');
	}
	
	if(idTipo == 3){
		$("#sottoprocessoNormativaButton").show();
		loadDiv('/CruscottoAuditAtpoWebWeb/getPageMessaggiInps', 'sottoProcessoNormativaDiv', null, 'initMessaggiInps');
	}
	
	if(idTipo == 4){
		$("#sottoprocessoNormativaButton").show();
		loadDiv('/CruscottoAuditAtpoWebWeb/getPageLeggiDecreti', 'sottoProcessoNormativaDiv', null, 'initLeggiDecreti');
	}
	
	
}

//mostra la modale per associazione sottoprocesso/normativa (Tab Normativa).
function showAsideSottoProcessoNormativa(){
	
	var idTipo = $('#comboTipoNormativa option:selected').val();
	var idTipoDescrizione = $('#comboTipoNormativa option:selected').text();
	
	if(idTipo == 1){
		loadDiv('/CruscottoAuditAtpoWebWeb/getCircolariInpsAssociateAsideList', 'sottoProcessoNormativaAsideDiv', null, 'initProcessoCircolariInps');
	}
	if(idTipo == 2){
		loadDiv('/CruscottoAuditAtpoWebWeb/getNoteDecretiAssociateAsideList', 'sottoProcessoNormativaAsideDiv', null, 'initProcessoNoteDecreti');
	}
	if(idTipo == 3){
		loadDiv('/CruscottoAuditAtpoWebWeb/getMessaggiInpsAssociateAsideList', 'sottoProcessoNormativaAsideDiv', null, 'initProcessoMessaggiInps');
	}
	if(idTipo == 4){
		loadDiv('/CruscottoAuditAtpoWebWeb/getLeggiDecretiAssociateAsideList', 'sottoProcessoNormativaAsideDiv', null, 'initProcessoLeggiDecreti');
	}
	
	
	$('.modalSottoProcessoNormativa').css("display", "block");
	jQuery('.menu-sx-levelone-facility.add').animate({marginRight:'0'}, 400);
	 if(contOne){
		 jQuery('.menu-sx-levelone-facility.add').animate({marginRight:'0'}, 400);
		 contOne = false;
	 }else{
	       
		 $('.modalSottoProcessoNormativa').css("display", "none");
	        contOne = true;
	    }
	 
	 $('#titoloFaseAside').text('ASSOCIAZIONE FASE - NORMATIVA (' + idTipoDescrizione + ')');
	
}


function associaSottoProcessoNormativa(){
	
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
	var idSottoProcesso = $('#idSottoProcesso').val();
	
	$.ajax( {
		type : 'GET',
		url : '/CruscottoAuditAtpoWebWeb/json/associaSottoProcessoNormativa',
		data : { 	'assSottoProcessoClasse.idSottoProcesso' : idSottoProcesso,
					'assSottoProcessoClasse.idNormativa' : idNormativa,
					'assSottoProcessoClasse.idTipo' : idTipo
					
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

function rimuoviAssociazioneSottoProcessoNormativa(){
	
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
	var idSottoProcesso = $('#idSottoProcesso').val();
	
	$.ajax( {
		type : 'GET',
		url : '/CruscottoAuditAtpoWebWeb/json/rimuoviAssociazioneSottoProcessoNormativa',
		data : { 	'assSottoProcessoClasse.idSottoProcesso' : idSottoProcesso,
					'assSottoProcessoClasse.idNormativa' : idNormativa,
					'assSottoProcessoClasse.idTipo' : idTipo
					
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

// Popola la combo Processi in base all'area selezionata.
function caricaComboProcesso(){

	var idArea = $('#comboArea option:selected').val();
	
	if(idArea != ''){
		
	$.ajax({
	       type: 'POST',
	       url: 'json/getComboProcessoByIdArea?' ,
	       data: ( idArea != undefined && idArea != null)? "idArea=" + idArea : null,
	       async: false,
	       success: function(data){
		
		        jQuery("select[name='comboProcesso']").find('option').remove();
		        
		        jQuery("select[name='comboProcesso']").append('<option value="">' + '' + '</option>');
		        jQuery("select[name='comboProcesso']").select2('val', '');
		        
		        $.each(data.processoList,function(index, value) {
		        	jQuery("select[name='comboProcesso']").append('<option value=' + value.idProcesso + '>' + value.descrizione + '</option>');
	            });           

			}
	    	 
	        });
	
	}else{
		jQuery("select[name='comboProcesso']").find('option').remove();
		jQuery("select[name='comboProcesso']").select2('val', '');
	}
	
}