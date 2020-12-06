function showAsideAddDominiAttivitaDettaglio(){
	$('.modalAddDomAttivitaDettaglio').css("display", "block");
	jQuery('.menu-sx-levelone-facility.addDom').animate({marginRight:'0'}, 400);
	 if(contOne){
		 jQuery('.menu-sx-levelone-facility.addDom').animate({marginRight:'0'}, 400);
		 contOne = false;
	 }else{
	       
		 $('.modalAddDomAttivitaDettaglio').css("display", "none");
	        contOne = true;
	    }
}


function associaDomToAttivitaDettaglio(){
	
	var idAttivitaDettaglio = jQuery("#idAttivitaDettaglio").val();
	
	var checkedIds = $(".chkDomAttivitaDettaglioAl:checked").map(function() {
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
			url : contextApp + '/jsonATPO/saveDominiAttivitaDettaglio',
			data :{ 
			'idDomini' : id,
			'idAttivitaDettaglio' : idAttivitaDettaglio
		},
		success : function(data) {

			$('#addDominiAttivitaDettaglioTable').DataTable().ajax.reload();
			$('#dominiAttivitaDettaglioTable').DataTable().ajax.reload();
			var settings = {
					theme: 'teal',
					sticky: false,
					horizontalEdge: 'top',
					verticalEdge: 'right',
					life: 3000
			};
			$.notific8('zindex', 11500);
			$.notific8('Dominio associato correttamente ', settings);
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

function selectAllDominiAttivitaDettaglio(){
	
	var checkAll = $("#checkAllDominiAttivitaDettaglio").prop('checked');
	if (checkAll) {
		$(".chkDomAttivitaDettaglio").prop("checked", true);
	} else {
		$(".chkDomAttivitaDettaglio").prop("checked", false);
	}
	return;
}

function showConfirmRemoveDominiAttivitaDettaglio(){
	var checkedIds = $(".chkDomAttivitaDettaglio:checked").map(function() {
		return this.value;

	}).toArray();
	// controlla checkbox selezionati
	if(!checkAtLeastOneSelection(checkedIds)){
		return;
	}else{
		$('#deleteDomModalAttivitaDettaglio').modal();
	}

	return;
}

function eliminaAssociazioneDomAttivitaDettaglio(){
	var checkedIds = $(".chkDomAttivitaDettaglio:checked").map(function() {
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
		url : contextApp + '/jsonATPO/eliminaAssDominiAttivitaDettaglio',
		data :{ 
		'listId' : d.toString()

	},

	success : function(data) {

		$('#dominiAttivitaDettaglioTable').DataTable().ajax.reload();
		$('#addDominiAttivitaDettaglioTable').DataTable().ajax.reload();
		
		$("#checkAllDominiAttivitaDettaglio").prop("checked", false);
		$('#deleteDomModalAttivitaDettaglio').modal('hide');
		var settings = {
				theme: 'teal',
				sticky: false,
				horizontalEdge: 'top',
				verticalEdge: 'right',
				life: 3000
		};
		$.notific8('zindex', 11500);
		$.notific8('Domini cancellati correttamente ', settings);
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
