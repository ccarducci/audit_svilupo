function showAsideAddCompTecniciAttivitaDettaglio(){
	$('.modalAddCompTecAttivitaDettaglio').css("display", "block");
	jQuery('.menu-sx-levelone-facility.addComTec').animate({marginRight:'0'}, 400);
	 if(contOne){
		 jQuery('.menu-sx-levelone-facility.addComTec').animate({marginRight:'0'}, 400);
		 contOne = false;
	 }else{
	       
		 $('.modalAddCompTecAttivitaDettaglio').css("display", "none");
	        contOne = true;
	    }
}


function associaCompTecniciToAttivitaDettaglio(){
	
	var idAttivitaDettaglio = jQuery("#idAttivitaDettaglio").val();
	
	var checkedIds = $(".chkCompTecAttivitaDettaglioAl:checked").map(function() {
		return this.value;

	}).toArray();
	// controlla checkbox selezionati
	if(!checkSelection(checkedIds)){
		return;
	}else{
		var id= checkedIds[0];

		$.ajax( {
			type : 'POST',
			dataType:'json',
			//AuAuditAction.java
			url : contextApp + '/jsonATPO/saveCompTecniciAttivitaDettaglio',
			data :{ 
			'idCompTec' : id,
			'idAttivitaDettaglio' : idAttivitaDettaglio
		},
		success : function(data) {

			$('#addCompTecAttivitaDettaglioTable').DataTable().ajax.reload();
			$('#compTecAttivitaDettaglioTable').DataTable().ajax.reload();
			var settings = {
					theme: 'teal',
					sticky: false,
					horizontalEdge: 'top',
					verticalEdge: 'right',
					life: 3000
			};
			$.notific8('zindex', 11500);
			$.notific8('Componente Tecnico associato correttamente ', settings);
			return; 

		},error: function(data){

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
	return;

}

function selectAllCompTecniciAttivitaDettaglio(){
	
	var checkAll = $("#checkAllCompTecAttivitaDettaglio").prop('checked');
	if (checkAll) {
		$(".chkCompTecAttivitaDettaglio").prop("checked", true);
	} else {
		$(".chkCompTecAttivitaDettaglio").prop("checked", false);
	}
	return;
}

function showConfirmRemoveCompTecniciAttivitaDettaglio(){
	var checkedIds = $(".chkCompTecAttivitaDettaglio:checked").map(function() {
		return this.value;

	}).toArray();
	// controlla checkbox selezionati
	if(!checkAtLeastOneSelection(checkedIds)){
		return;
	}else{
		$('#deleteCompTecModalAttivitaDettaglio').modal();
	}

	return;
}

function eliminaAssociazioneCompTecniciAttivitaDettaglio(){
	var checkedIds = $(".chkCompTecAttivitaDettaglio:checked").map(function() {
		return this.value;

	}).toArray();
	var d=[];
	var arrayLength=checkedIds.length;
	for (var i = 0; i < arrayLength; i++) {
		var idAss= checkedIds[i];
		
		d.push(idAss);
	}

	$.ajax( {
		type : 'POST',
		dataType:'json',
		//AuAuditAction.java
		url : contextApp + '/jsonATPO/eliminaAssCompTecniciAttivitaDettaglio',
		data :{ 
		'listId' : d.toString()

	},

	success : function(data) {

		$('#compTecAttivitaDettaglioTable').DataTable().ajax.reload();
		$('#addCompTecAttivitaDettaglioTable').DataTable().ajax.reload();
		
		$("#checkAllCompTecAttivitaDettaglio").prop("checked", false);
		$('#deleteCompTecModalAttivitaDettaglio').modal('hide');
		var settings = {
				theme: 'teal',
				sticky: false,
				horizontalEdge: 'top',
				verticalEdge: 'right',
				life: 3000
		};
		$.notific8('zindex', 11500);
		$.notific8('Componenti Tecnici cancellati correttamente ', settings);
		return; 

	},error: function(data){

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
	return;
}
