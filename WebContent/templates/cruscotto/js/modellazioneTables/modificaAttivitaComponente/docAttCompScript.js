function showAsideAddDoc(){
	$('.modalAddDocAttComp').css("display", "block");
	jQuery('.menu-sx-levelone-facility.addDoc').animate({marginRight:'0'}, 400);
	 if(contOne){
		 jQuery('.menu-sx-levelone-facility.addDoc').animate({marginRight:'0'}, 400);
		 contOne = false;
	 }else{
	       
		 $('.modalAddDocAttComp').css("display", "none");
	        contOne = true;
	    }
}


function associaDocToAttComp(){
	var checkedIds = $(".chkDocAttCompAl:checked").map(function() {
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
			url : '/CruscottoAuditAtpoWebWeb/jsonATPO/saveDocAttComp',
			data :{ 
			'idFile' : id
		},
		success : function(data) {

			$('#addAllegatiAttCompTable').DataTable().ajax.reload();
			$('#docMediaAttCompTable').DataTable().ajax.reload();
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

function selectAllDocAttComp(){
	
	var checkAll = $("#checkAllDocAttComp").prop('checked');
	if (checkAll) {
		$(".chkDocAttComp").prop("checked", true);
	} else {
		$(".chkDocAttComp").prop("checked", false);
	}
	return;
}

function showConfirmRemoveDocAttComp(){
	var checkedIds = $(".chkDocAttComp:checked").map(function() {
		return this.value;

	}).toArray();
	// controlla checkbox selezionati
	if(!checkAtLeastOneSelection(checkedIds)){
		return;
	}else{
		$('#deleteDocModalAttComp').modal();
	}

	return;
}

function eliminaAssociazioneDocAttComp(){
	var checkedIds = $(".chkDocAttComp:checked").map(function() {
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
		url : '/CruscottoAuditAtpoWebWeb/jsonATPO/eliminaAssDocAttComp',
		data :{ 
		'listId' : d.toString()


	},


	success : function(data) {

		$('#docMediaAttCompTable').DataTable().ajax.reload();
		$('#addAllegatiAttCompTable').DataTable().ajax.reload();
		
		$("#checkAllDocAttComp").prop("checked", false);
		$('#deleteDocModalAttComp').modal('hide');
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


function scaricaDocAttComp(){
	var checkedIds = $(".chkDocAttComp:checked").map(function() {
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
		url : '/CruscottoAuditAtpoWebWeb/jsonATPO/downloadDocumento',
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