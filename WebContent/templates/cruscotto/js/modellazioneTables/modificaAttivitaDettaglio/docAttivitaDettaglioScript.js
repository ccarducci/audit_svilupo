function showAsideAddDocAttivitaDettaglio(){
	$('.modalAddDocAttivitaDettaglio').css("display", "block");
	jQuery('.menu-sx-levelone-facility.addDoc').animate({marginRight:'0'}, 400);
	 if(contOne){
		 jQuery('.menu-sx-levelone-facility.addDoc').animate({marginRight:'0'}, 400);
		 contOne = false;
	 }else{
	       
		 $('.modalAddDocAttivitaDettaglio').css("display", "none");
	        contOne = true;
	    }
}


function associaDocToAttivitaDettaglio(){
	
	var idAttivitaDettaglio = jQuery("#idAttivitaDettaglio").val();
	
	var checkedIds = $(".chkDocAttivitaDettaglioAl:checked").map(function() {
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
			url : contextApp + '/jsonATPO/saveDocAttivitaDettaglio',
			data :{ 
			'idFile' : id,
			'idAttivitaDettaglio' : idAttivitaDettaglio
		},
		success : function(data) {

			$('#addAllegatiAttivitaDettaglioTable').DataTable().ajax.reload();
			$('#docMediaAttivitaDettaglioTable').DataTable().ajax.reload();
			var settings = {
					theme: 'teal',
					sticky: false,
					horizontalEdge: 'top',
					verticalEdge: 'right',
					life: 3000
			};
			$.notific8('zindex', 11500);
			$.notific8('Documento allegato correttamente ', settings);
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

function selectAllDocAttivitaDettaglio(){
	
	var checkAll = $("#checkAllDocAttivitaDettaglio").prop('checked');
	if (checkAll) {
		$(".chkDocAttivitaDettaglio").prop("checked", true);
	} else {
		$(".chkDocAttivitaDettaglio").prop("checked", false);
	}
	return;
}

function showConfirmRemoveDocAttivitaDettaglio(){
	var checkedIds = $(".chkDocAttivitaDettaglio:checked").map(function() {
		return this.value;

	}).toArray();
	// controlla checkbox selezionati
	if(!checkAtLeastOneSelection(checkedIds)){
		return;
	}else{
		$('#deleteDocModalAttivitaDettaglio').modal();
	}

	return;
}

function eliminaAssociazioneDocAttivitaDettaglio(){
	var checkedIds = $(".chkDocAttivitaDettaglio:checked").map(function() {
		return this.value;

	}).toArray();
	var d=[];
	var arrayLength=checkedIds.length;
	for (var i = 0; i < arrayLength; i++) {
		var idAssDoc= checkedIds[i].split("-");
		var id=idAssDoc[0];//0 = id associazione doc 
		d.push(id);
	}

	$.ajax( {
		type : 'POST',
		dataType:'json',
		//AuAuditAction.java
		url : contextApp + '/jsonATPO/eliminaAssDocAttivitaDettaglio',
		data :{ 
		'listId' : d.toString()


	},


	success : function(data) {

		$('#docMediaAttivitaDettaglioTable').DataTable().ajax.reload();
		$('#addAllegatiAttivitaDettaglioTable').DataTable().ajax.reload();
		
		$("#checkAllDocAttivitaDettaglio").prop("checked", false);
		$('#deleteDocModalAttivitaDettaglio').modal('hide');
		var settings = {
				theme: 'teal',
				sticky: false,
				horizontalEdge: 'top',
				verticalEdge: 'right',
				life: 3000
		};
		$.notific8('zindex', 11500);
		$.notific8('Allegati cancellati correttamente ', settings);
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


function scaricaDocAttivitaDettaglio(){
	var checkedIds = $(".chkDocAttivitaDettaglio:checked").map(function() {
		return this.value;
	}).toArray();

	// controlla checkbox selezionati
	if(!checkSelection(checkedIds)){
		return;
	}

	var idCod= checkedIds[0].split("-");
	var idDoc=idCod[1];

	$.ajax( {
		type : 'POST',
		dataType:'json',
		//AuAuditAction.java
		url : contextApp + '/jsonATPO/downloadDocumento',
		data :{ 
		'idDocumento' : idDoc
	},
	success : function(data) {
		
		$("input[name='ids']:checkbox").removeAttr('checked');
		
		var prefix=data.prefix.trim();
		var name=data.nomeDocumento.trim();
		var b64=data.base64File.trim();

		var type=base64MimeType(prefix);


		//es: download("data:text/plain;base64,"+data.base64File, "test.txt");
		download(prefix+b64, name, data.toBlob, type);
		return;


	},error: function(data){
		
		$("input[name='ids']:checkbox").removeAttr('checked');
		
		var settings = {
				theme: 'teal',
				sticky: false,
				horizontalEdge: 'top',
				verticalEdge: 'right',
				life: 3000
		};
		$.notific8('zindex', 11500);
		$.notific8('Errore durante il download ', settings);
		return; 
	}
	});

	
	return;
}