function showAsideAddCompTecniciSottoProcesso(){
	$('.modalAddCompTecSottoProcesso').css("display", "block");
	jQuery('.menu-sx-levelone-facility.addComTec').animate({marginRight:'0'}, 400);
	 if(contOne){
		 jQuery('.menu-sx-levelone-facility.addComTec').animate({marginRight:'0'}, 400);
		 contOne = false;
	 }else{
	       
		 $('.modalAddCompTecSottoProcesso').css("display", "none");
	        contOne = true;
	    }
}


function associaCompTecniciToSottoProcesso(){
	
	var idSottoProcesso = jQuery("#idSottoProcesso").val();
	
	var checkedIds = $(".chkCompTecSottoProcessoAl:checked").map(function() {
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
			url : contextApp + '/jsonATPO/saveCompTecniciSottoProcesso',
			data :{ 
			'idCompTec' : id,
			'idSottoProcesso' : idSottoProcesso
		},
		success : function(data) {

			$('#addCompTecSottoProcessoTable').DataTable().ajax.reload();
			$('#compTecSottoProcessoTable').DataTable().ajax.reload();
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

function selectAllCompTecniciSottoProcesso(){
	
	var checkAll = $("#checkAllCompTecSottoProcesso").prop('checked');
	if (checkAll) {
		$(".chkCompTecSottoProcesso").prop("checked", true);
	} else {
		$(".chkCompTecSottoProcesso").prop("checked", false);
	}
	return;
}

function showConfirmRemoveCompTecniciSottoProcesso(){
	var checkedIds = $(".chkCompTecSottoProcesso:checked").map(function() {
		return this.value;

	}).toArray();
	// controlla checkbox selezionati
	if(!checkAtLeastOneSelection(checkedIds)){
		return;
	}else{
		$('#deleteCompTecModalSottoProcesso').modal();
	}

	return;
}

function eliminaAssociazioneCompTecniciSottoProcesso(){
	var checkedIds = $(".chkCompTecSottoProcesso:checked").map(function() {
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
		url : contextApp + '/jsonATPO/eliminaAssCompTecniciSottoProcesso',
		data :{ 
		'listId' : d.toString()


	},


	success : function(data) {

		$('#compTecSottoProcessoTable').DataTable().ajax.reload();
		$('#addCompTecSottoProcessoTable').DataTable().ajax.reload();
		
		$("#checkAllCompTecSottoProcesso").prop("checked", false);
		$('#deleteCompTecModalSottoProcesso').modal('hide');
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
