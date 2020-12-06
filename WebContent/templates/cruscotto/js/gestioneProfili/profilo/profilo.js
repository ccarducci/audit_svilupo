function addNewProfilo(){
	Metronic.startPageLoading();
	var nome=$('#nomeProfilo').val();
	var cognome=$('#cognomeProfilo').val();
	var username =$('#usernameProfilo').val();
	var email=$('#emailProfilo').val();
	var ruolo=$('#selectRuoloProfilo').val();
	var audit =$('#selectAuditProfilo').val();
	
	if(nome==""|cognome==""|username==""|email==""|ruolo==""|audit==""){
		$('#info').show();
		Metronic.stopPageLoading();
		return;
	}else{
		$('#info').hide();
		$.ajax( {
			type : 'GET',
			url : '/CruscottoAuditAtpoWebWeb/json/addNewProfilo',
			data :{ 
			'profilo.nome' : nome	,
			'profilo.cognome' : cognome	,
			'profilo.username' : username,
			'profilo.email' : email,
			'profilo.tipo' : ruolo	,
			'profilo.audit' : audit
		},
		success : function(data) {
			Metronic.stopPageLoading();

			$('#nomeProfilo').val("");
			$('#cognomeProfilo').val("");
			$('#usernameProfilo').val("");
			$('#emailProfilo').val("");
			$('#selectRuoloProfilo').val("");
			$('#selectAuditProfilo').val("");
			$('#profilo_table').DataTable().ajax.reload();
			slideAsideNew('');
			
			return; 
		},error: function(data){
			Metronic.stopPageLoading();
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
}

function getUpdateProfilo(){
	var checkedIds = $(".chkProf:checked").map(function() {
	    return this.value;
	  }).toArray();
	
	if (checkedIds.length>1){
		var settings = {
				theme: 'teal',
				sticky: false,
				horizontalEdge: 'top',
				verticalEdge: 'right',
				life: 3000
		};
		$.notific8('zindex', 11500);
		$.notific8('Selezionare un solo elemento ', settings);
		return; 
		
	}else if(checkedIds.length==0){
		var settings = {
				theme: 'teal',
				sticky: false,
				horizontalEdge: 'top',
				verticalEdge: 'right',
				life: 3000
		};
		$.notific8('zindex', 11500);
		$.notific8('Nessun elemento selezionato ', settings);
		return; 
		
	}else{
		var idUtente=checkedIds[0];		
		$.ajax( {
			type : 'GET',
			url : '/CruscottoAuditAtpoWebWeb/json/getUpdateProfilo',
			data :{ 
			'profilo.idUtente' : idUtente 
		},success : function(data){
			slideAsideNew('updateProfilo');
			$('#modificaNomeProfilo').val(data.profilo.nome);
			$('#modificaCognomeProfilo').val(data.profilo.cognome);
			$('#modificaUsernameProfilo').val(data.profilo.username);
			$('#modificaEmailProfilo').val(data.profilo.email);
			var ruolo=data.profilo.tipo;
			$('#selectModificaRuoloProfilo').val(ruolo.trim());
			$('#selectModificaRuoloProfilo').trigger('change');
			var audit=data.profilo.audit;
			$('#selectModificaAuditProfilo').val(audit.trim());
			$('#selectModificaAuditProfilo').trigger('change');
			
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
			$.notific8('Impossibile recuperare il profilo ', settings);
			return; 
		}
		});
		
	}
}

function updateProfilo(){
	Metronic.startPageLoading();

	var checkedIds = $(".chkProf:checked").map(function() {
		return this.value;
	}).toArray();

	var id=checkedIds[0];
	var nome=$('#modificaNomeProfilo').val();
	var cognome=$('#modificaCognomeProfilo').val();
	var username=$('#modificaUsernameProfilo').val();
	var email=$('#modificaEmailProfilo').val();
	var ruolo=$('#selectModificaRuoloProfilo').val();
	var audit=$('#selectModificaAuditProfilo').val();

	if(nome==""|cognome==""|username==""|email==""|ruolo==""|audit==""){
		$('#infoModifca').show();
		Metronic.stopPageLoading();
		return;
	}else{
		$.ajax( {
			type : 'GET',
			url : '/CruscottoAuditAtpoWebWeb/json/updateProfilo',
			data :{ 
			'profilo.idUtente' : id,
			'profilo.nome' : nome	,
			'profilo.cognome' : cognome	,
			'profilo.username' : username,
			'profilo.email' : email,
			'profilo.tipo' : ruolo	,
			'profilo.audit' : audit
		},
		success : function(data) {
			Metronic.stopPageLoading();
			$('#modificaNomeProfilo').val("");
			$('#modificaCognomeProfilo').val("");
			$('#modificaUsernameProfilo').val("");
			$('#modificaEmailProfilo').val("");
			$('#selectModificaRuoloProfilo').val("");
			$('#selectModificaAuditProfilo').val("");
			$('#profilo_table').DataTable().ajax.reload();
			$('#infoModifca').hide();
			slideAsideNew('');

			return; 
		},error: function(data){
			Metronic.stopPageLoading();
			var settings = {
					theme: 'teal',
					sticky: false,
					horizontalEdge: 'top',
					verticalEdge: 'right',
					life: 3000
			};
			$.notific8('zindex', 11500);
			$.notific8('Errore durante il salvataggio della modifica', settings);
			return; 
		}
		});
	}
}



