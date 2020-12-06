function showAsideAddDocSottoProcesso(){
	$('.modalAddDocSottoProcesso').css("display", "block");
	jQuery('.menu-sx-levelone-facility.addDoc').animate({marginRight:'0'}, 400);
	 if(contOne){
		 jQuery('.menu-sx-levelone-facility.addDoc').animate({marginRight:'0'}, 400);
		 contOne = false;
	 }else{
	       
		 $('.modalAddDocSottoProcesso').css("display", "none");
	        contOne = true;
	    }
}


function associaDocToSottoProcesso(){
	
	var idSottoProcesso = jQuery("#idSottoProcesso").val();
	
	var checkedIds = $(".chkDocSottoProcessoAl:checked").map(function() {
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
			url : contextApp + '/jsonATPO/saveDocSottoProcesso',
			data :{ 
			'idFile' : id,
			'idSottoProcesso' : idSottoProcesso
		},
		success : function(data) {

			$('#addAllegatiSottoProcessoTable').DataTable().ajax.reload();
			$('#docMediaSottoProcessoTable').DataTable().ajax.reload();
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

function selectAllDocSottoProcesso(){
	
	var checkAll = $("#checkAllDocSottoProcesso").prop('checked');
	if (checkAll) {
		$(".chkDocSottoProcesso").prop("checked", true);
	} else {
		$(".chkDocSottoProcesso").prop("checked", false);
	}
	return;
}

function showConfirmRemoveDocSottoProcesso(){
	var checkedIds = $(".chkDocSottoProcesso:checked").map(function() {
		return this.value;

	}).toArray();
	// controlla checkbox selezionati
	if(!checkAtLeastOneSelection(checkedIds)){
		return;
	}else{
		$('#deleteDocModalSottoProcesso').modal();
	}

	return;
}

function eliminaAssociazioneDocSottoProcesso(){
	var checkedIds = $(".chkDocSottoProcesso:checked").map(function() {
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
		url : contextApp + '/jsonATPO/eliminaAssDocSottoProcesso',
		data :{ 
		'listId' : d.toString()


	},


	success : function(data) {

		$('#docMediaSottoProcessoTable').DataTable().ajax.reload();
		$('#addAllegatiSottoProcessoTable').DataTable().ajax.reload();
		
		$("#checkAllDocSottoProcesso").prop("checked", false);
		$('#deleteDocModalSottoProcesso').modal('hide');
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


function scaricaDocSottoProcesso(){
	var checkedIds = $(".chkDocSottoProcesso:checked").map(function() {
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