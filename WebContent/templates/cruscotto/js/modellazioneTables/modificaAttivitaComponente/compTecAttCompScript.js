function showAsideAddCompTecAttComp(){
	$('.modalAddCompTecAttComp').css("display", "block");
	jQuery('.menu-sx-levelone-facility.addCompT').animate({marginRight:'0'}, 400);
	 if(contOne){
		 jQuery('.menu-sx-levelone-facility.addCompT').animate({marginRight:'0'}, 400);
		 contOne = false;
	 }else{
	       
		 $('.modalAddCompTecAttComp').css("display", "none");
	        contOne = true;
	    }
	return;
}

function associaCompTecToAttComp(){
	
	var checkedIds = $(".chkCompTecDaAssociare:checked").map(function() {
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
			url : '/CruscottoAuditAtpoWebWeb/jsonATPO/saveCompTecAttComp',
			data :{ 
			'idCompTec' : id
		},
		success : function(data) {

			$('#compTecAttCompTableDaAssociare').DataTable().ajax.reload();
			$('#compTecniciTable').DataTable().ajax.reload();
			var settings = {
					theme: 'teal',
					sticky: false,
					horizontalEdge: 'top',
					verticalEdge: 'right',
					life: 3000
			};
			$.notific8('zindex', 11500);
			$.notific8('Componente tecnico associato correttamente ', settings);
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

function selectAllCTAtC(){
	
	var checkAll = $("#checkAllCTAtc").prop('checked');
	if (checkAll) {
		$(".chkCompTecAttComp").prop("checked", true);
	} else {
		$(".chkCompTecAttComp").prop("checked", false);
	}
	return;
}

function showConfirmRemoveCompTecAttComp(){
	
	var checkedIds = $(".chkCompTecAttComp:checked").map(function() {
		return this.value;

	}).toArray();
	// controlla checkbox selezionati
	if(!checkAtLeastOneSelection(checkedIds)){
		return;
	}else{
		$('#deleteCtAcModal').modal();
	}

	return;
}

function eliminaCTAttComp(){
	var checkedIds = $(".chkCompTecAttComp:checked").map(function() {
		return this.value;

	}).toArray();
	var d=[];
	var arrayLength=checkedIds.length;
	for (var i = 0; i < arrayLength; i++) {
		var idAssCt= checkedIds[i];
		
		d.push(idAssCt);
	}

	$.ajax( {
		type : 'POST',
		dataType:'json',
		//AuAuditAction.java
		url : '/CruscottoAuditAtpoWebWeb/jsonATPO/eliminaAssCompTecAttComp',
		data :{ 
		'listId' : d.toString()


	},


	success : function(data) {
		$('#compTecniciTable').DataTable().ajax.reload();
		$('#compTecAttCompTableDaAssociare').DataTable().ajax.reload();
		
		$("#checkAllCTAtc").prop("checked", false);
		$('#deleteCtAcModal').modal('hide');
		var settings = {
				theme: 'teal',
				sticky: false,
				horizontalEdge: 'top',
				verticalEdge: 'right',
				life: 3000
		};
		$.notific8('zindex', 11500);
		$.notific8('Associazioni cancellate correttamente ', settings);
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

