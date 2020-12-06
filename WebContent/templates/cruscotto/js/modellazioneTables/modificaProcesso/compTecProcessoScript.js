function showAsideAddCompTecniciProcesso(){
	$('.modalAddCompTecProcesso').css("display", "block");
	jQuery('.menu-sx-levelone-facility.addDoc').animate({marginRight:'0'}, 400);
	 if(contOne){
		 jQuery('.menu-sx-levelone-facility.addDoc').animate({marginRight:'0'}, 400);
		 contOne = false;
	 }else{
	       
		 $('.modalAddCompTecProcesso').css("display", "none");
	        contOne = true;
	    }
}


function associaCompTecniciToProcesso(){
	
	var idProcesso = jQuery("#idProcesso").val();
	
	var checkedIds = $(".chkCompTecProcessoAl:checked").map(function() {
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
			url : '/CruscottoAuditAtpoWebWeb/jsonATPO/saveCompTecniciProcesso',
			data :{ 
			'idCompTec' : id,
			'idProcesso' : idProcesso
		},
		success : function(data) {

			$('#addCompTecProcessoTable').DataTable().ajax.reload();
			$('#compTecProcessoTable').DataTable().ajax.reload();
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

function selectAllCompTecniciProcesso(){
	
	var checkAll = $("#checkAllCompTecProcesso").prop('checked');
	if (checkAll) {
		$(".chkCompTecProcesso").prop("checked", true);
	} else {
		$(".chkCompTecProcesso").prop("checked", false);
	}
	return;
}

function showConfirmRemoveCompTecniciProcesso(){
	var checkedIds = $(".chkCompTecProcesso:checked").map(function() {
		return this.value;

	}).toArray();
	// controlla checkbox selezionati
	if(!checkAtLeastOneSelection(checkedIds)){
		return;
	}else{
		$('#deleteCompTecModalProcesso').modal();
	}

	return;
}

function eliminaAssociazioneCompTecniciProcesso(){
	var checkedIds = $(".chkCompTecProcesso:checked").map(function() {
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
		url : '/CruscottoAuditAtpoWebWeb/jsonATPO/eliminaAssCompTecniciProcesso',
		data :{ 
		'listId' : d.toString()


	},


	success : function(data) {

		$('#compTecProcessoTable').DataTable().ajax.reload();
		$('#addCompTecProcessoTable').DataTable().ajax.reload();
		
		$("#checkAllCompTecProcesso").prop("checked", false);
		$('#deleteCompTecModalProcesso').modal('hide');
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
