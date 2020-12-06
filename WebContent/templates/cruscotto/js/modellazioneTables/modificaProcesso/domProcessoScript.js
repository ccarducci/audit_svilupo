function showAsideAddDominiProcesso(){
	$('.modalAddDomProcesso').css("display", "block");
	jQuery('.menu-sx-levelone-facility.addDoc').animate({marginRight:'0'}, 400);
	 if(contOne){
		 jQuery('.menu-sx-levelone-facility.addDoc').animate({marginRight:'0'}, 400);
		 contOne = false;
	 }else{
	       
		 $('.modalAddDomProcesso').css("display", "none");
	        contOne = true;
	    }
}



function associaDomToProcesso(){
	
	var idProcesso = jQuery("#idProcesso").val();
	
	var checkedIds = $(".chkDomProcessoAl:checked").map(function() {
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
			url : '/CruscottoAuditAtpoWebWeb/jsonATPO/saveDominiProcesso',
			data :{ 
			'idDomini' : id,
			'idProcesso' : idProcesso
		},
		success : function(data) {

			$('#addDominiProcessoTable').DataTable().ajax.reload();
			$('#dominiProcessoTable').DataTable().ajax.reload();
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

function selectAllDominiProcesso(){
	
	var checkAll = $("#checkAllDominiProcesso").prop('checked');
	if (checkAll) {
		$(".chkDomProcesso").prop("checked", true);
	} else {
		$(".chkDomProcesso").prop("checked", false);
	}
	return;
}

function showConfirmRemoveDominiProcesso(){
	var checkedIds = $(".chkDomProcesso:checked").map(function() {
		return this.value;

	}).toArray();
	// controlla checkbox selezionati
	if(!checkAtLeastOneSelection(checkedIds)){
		return;
	}else{
		$('#deleteDomModalProcesso').modal();
	}

	return;
}

function eliminaAssociazioneDomProcesso(){
	var checkedIds = $(".chkDomProcesso:checked").map(function() {
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
		url : '/CruscottoAuditAtpoWebWeb/jsonATPO/eliminaAssDominiProcesso',
		data :{ 
		'listId' : d.toString()


	},


	success : function(data) {

		$('#dominiProcessoTable').DataTable().ajax.reload();
		$('#addDominiProcessoTable').DataTable().ajax.reload();
		
		$("#checkAllDominiProcesso").prop("checked", false);
		$('#deleteDomModalProcesso').modal('hide');
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
