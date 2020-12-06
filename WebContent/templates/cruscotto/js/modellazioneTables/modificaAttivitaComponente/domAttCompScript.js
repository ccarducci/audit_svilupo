function showAsideAddDom(){
	$('.modalAddDomAttComp').css("display", "block");
	jQuery('.menu-sx-levelone-facility.addDom').animate({marginRight:'0'}, 400);
	 if(contOne){
		 jQuery('.menu-sx-levelone-facility.addDom').animate({marginRight:'0'}, 400);
		 contOne = false;
	 }else{
	       
		 $('.modalAddDomAttComp').css("display", "none");
	        contOne = true;
	    }
	return;
}

function associaDomToAttComp(){
	
	var checkedIds = $(".chkDomDaAssociare:checked").map(function() {
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
			url : '/CruscottoAuditAtpoWebWeb/jsonATPO/saveDomAttComp',
			data :{ 
			'idDominio' : id
		},
		success : function(data) {

			$('#dominiAttCompTableDaAssociare').DataTable().ajax.reload();
			$('#dominiAttCompTable').DataTable().ajax.reload();
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

function selectAllDomAttComp(){
	
	var checkAll = $("#checkAllDomAttComp").prop('checked');
	if (checkAll) {
		$(".chkDomAttComp").prop("checked", true);
	} else {
		$(".chkDomAttComp").prop("checked", false);
	}
	return;
}

function showModalEliminaDominio(){
	var checkedIds = $(".chkDomAttComp:checked").map(function() {
		return this.value;

	}).toArray();
	// controlla checkbox selezionati
	if(!checkAtLeastOneSelection(checkedIds)){
		return;
	}else{
		$('#deleteDominioAttCompModal').modal();
	}

	return;
}

function confirmEliminaDominioAttComp(){
	var checkedIds = $(".chkDomAttComp:checked").map(function() {
		return this.value;

	}).toArray();
	var d=[];
	var arrayLength=checkedIds.length;
	for (var i = 0; i < arrayLength; i++) {
		var idAssDom= checkedIds[i];
		
		d.push(idAssDom);
	}

	$.ajax( {
		type : 'POST',
		dataType:'json',
		//AuAuditAction.java
		url : '/CruscottoAuditAtpoWebWeb/jsonATPO/eliminaAssDomAttComp',
		data :{ 
		'listId' : d.toString()


	},


	success : function(data) {

		$('#dominiAttCompTable').DataTable().ajax.reload();
		$('#dominiAttCompTableDaAssociare').DataTable().ajax.reload();
		
		$("#checkAllDomAttComp").prop("checked", false);
		$('#deleteDominioAttCompModal').modal('hide');
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

