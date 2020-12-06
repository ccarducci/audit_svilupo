function showAsideAddDominiSottoProcesso(){
	$('.modalAddDomSottoProcesso').css("display", "block");
	jQuery('.menu-sx-levelone-facility.addDom').animate({marginRight:'0'}, 400);
	 if(contOne){
		 jQuery('.menu-sx-levelone-facility.addDom').animate({marginRight:'0'}, 400);
		 contOne = false;
	 }else{
	       
		 $('.modalAddDomSottoProcesso').css("display", "none");
	        contOne = true;
	    }
}


function associaDomToSottoProcesso(){
	
	var idSottoProcesso = jQuery("#idSottoProcesso").val();
	
	var checkedIds = $(".chkDomSottoProcessoAl:checked").map(function() {
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
			url : contextApp + '/jsonATPO/saveDominiSottoProcesso',
			data :{ 
			'idDomini' : id,
			'idSottoProcesso' : idSottoProcesso
		},
		success : function(data) {

			$('#addDominiSottoProcessoTable').DataTable().ajax.reload();
			$('#dominiSottoProcessoTable').DataTable().ajax.reload();
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

function selectAllDominiSottoProcesso(){
	
	var checkAll = $("#checkAllDominiSottoProcesso").prop('checked');
	if (checkAll) {
		$(".chkDomSottoProcesso").prop("checked", true);
	} else {
		$(".chkDomSottoProcesso").prop("checked", false);
	}
	return;
}

function showConfirmRemoveDominiSottoProcesso(){
	var checkedIds = $(".chkDomSottoProcesso:checked").map(function() {
		return this.value;

	}).toArray();
	// controlla checkbox selezionati
	if(!checkAtLeastOneSelection(checkedIds)){
		return;
	}else{
		$('#deleteDomModalSottoProcesso').modal();
	}

	return;
}

function eliminaAssociazioneDomSottoProcesso(){
	var checkedIds = $(".chkDomSottoProcesso:checked").map(function() {
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
		url : contextApp + '/jsonATPO/eliminaAssDominiSottoProcesso',
		data :{ 
		'listId' : d.toString()


	},


	success : function(data) {

		$('#dominiSottoProcessoTable').DataTable().ajax.reload();
		$('#addDominiSottoProcessoTable').DataTable().ajax.reload();
		
		$("#checkAllDominiSottoProcesso").prop("checked", false);
		$('#deleteDomModalSottoProcesso').modal('hide');
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
