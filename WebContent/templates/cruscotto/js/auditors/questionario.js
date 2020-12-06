/*
 * SALVA LE RISPOSTE DEL QUESTIONARIO
 */
function  salvaRispostaQuestionario(idDomanda){
	var idSessione= $("#sessionID").val();
	var idDomanda= idDomanda;
	var idRisposta= document.querySelector('input[name="'+idDomanda+'"]:checked');
	
	if(!idRisposta){
		var settings = {
				theme: 'teal',
				sticky: false,
				horizontalEdge: 'top',
				verticalEdge: 'right',
				life: 5000
			};
				$.notific8('zindex', 11500);
				$.notific8('Selezionare almeno una risposta ', settings);
				return; 
		
	}else{
	Metronic.startPageLoading();
	var idRisposta= document.querySelector('input[name="'+idDomanda+'"]:checked').value;
	$.ajax( {
		type : 'GET',
		//AuditorsAction.java
		url : '/CruscottoAuditAtpoWebWeb/salvaRispostaQuestionario',
		data :{ 'idSessione' : idSessione,
				'idRisposta' : idRisposta,
			    'idDomanda': idDomanda
					   },
					 
	
		success : function(data) {
			Metronic.stopPageLoading();
			//$('#indicatori').DataTable().ajax.reload();
			
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
