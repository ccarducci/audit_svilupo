


function salvaCampagna(){
	
	var audit = $("#select").find('option:selected').attr('id');
	
	var campagna=$('#nomeCampagna').val();
	var dataInizio=$('#dataInizio').val();
	var dataFine =$('#dataFine').val();
	var dataInizioOsservazione=$('#inizioOsservazione').val();
	var dataFineOsservazione=$('#fineOsservazione').val();
	
	if(!campagna || !dataInizio /*&& dataFine */ || !dataInizioOsservazione || !dataFineOsservazione){
		var settings = {
				theme: 'teal',
				sticky: false,
				horizontalEdge: 'top',
				verticalEdge: 'right',
				life: 3000
		};
		$.notific8('zindex', 11500);
		$.notific8('Questi* campi sono obbligatori', settings);
		return;
	}
	
	$.ajax( {
		type : 'GET',
		//AuAuditAction.java
		url : '/CruscottoAuditAtpoWebWeb/salvaNuovaCampagna',
		data :{ 
			'campagna.nome' : campagna	,
			'campagna.idAudit' : audit	,
			'campagna.dataInizio' : dataInizio	,
			'campagna.dataFine' : dataFine	,
			'campagna.dataFineOsservazione' : dataFineOsservazione	,
			'campagna.dataInizioOsservazione' : dataInizioOsservazione	
			
			   
					   },
					 
	
		success : function(data) {
			
			
			$('#campagna_table').DataTable().ajax.reload();
			
			
			$('#nomeCampagna').val("");
			$('#dataInizio').val("");
			$('#dataFine').val("");
			$('#inizioOsservazione').val("");
			$('#fineOsservazione').val("");
			document.getElementById("info").style.visibility = "hidden";
			slideAside('');
			return; 
			

		},error: function(data){
			
			document.getElementById("info").style.visibility = "hidden";
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

function checkIfDataFineHasValue(){
    var data = $(".chkCampagna:checked").closest("TR").find("TD:nth-child(5) ").text();
    if ( data !== null && data !== undefined && data.length > 0 ){
		var settings = {
				theme: 'teal',
				sticky: false,
				horizontalEdge: 'top',
				verticalEdge: 'right',
				life: 3000
		};
		$.notific8('zindex', 11500);
		$.notific8('La Data Fine Campagna è valorizzata, impossibile eliminare', settings);
		return true;
    }
    return false;
}

function checkIfCampagnaHasSessioni(){
	var checkedIds = $(".chkCampagna:checked").map(function() {
		return this.value;
	}).toArray();
	var idCampagna = checkedIds[0];
	checkedIds=[];

	$.ajax( {
		type : 'GET',
		url : '/CruscottoAuditAtpoWebWeb/json/checkIfCampagnaHasSessioni',
		data : {'idCampagna':idCampagna},		
		success : function(data) {
			var numSessioni=data.numeroSessioniAssociate;
			if(numSessioni!=0){
				var settings = {
						theme: 'teal',
						sticky: false,
						horizontalEdge: 'top',
						verticalEdge: 'right',
						life: 3000
				};
				$.notific8('zindex', 11500);
				$.notific8('Presenti sessioni associate a questo campagna, impossibile eliminare', settings);
			}else{
				$('#deleteCampagnaModal').modal();
			}
		}
	});
}

function eliminaCampagna(){
	var checkedIds = $(".chkCampagna:checked").map(function() {
	    return this.value;
	
	  }).toArray();
	var idCampagna = checkedIds[0];
	$.ajax( {
		type : 'GET',
		url : '/CruscottoAuditAtpoWebWeb/eliminaCampagna',
		data : { 	'idCampagna' : idCampagna
		   },
		
		success : function(data) {
		
			
			$('#campagna_table').DataTable().ajax.reload();
		
			return; 
		},error: function(data){
			var settings = {
					theme: 'teal',
					sticky: false,
					horizontalEdge: 'top',
					verticalEdge: 'right',
					life: 3000
				};
			 Metronic.stopPageLoading();
					$.notific8('zindex', 11500);
					$.notific8('Errore durante la cencellazione ', settings);
					return; 
		}
	});
	
	$('#deleteCampagnaModal').modal('toggle');
	
}


//inizializza datepicker 
function openDatePicker(caller){
	var date=$('#'+caller).val();
	$('#'+caller).datepicker({
		format: 'dd/mm/yy',
        autoclose: true
    }).focus();
	
	
	
}

function  salvaModificheCampagnaWithCheckDataFine(){
	
	if ($('#dataFineModifica')[0].value != null && $('#dataFineModifica')[0].value.length > 0 )
	{
		$('#modificaDataFineModal').modal();
	}else{
		salvaModificheCampagna();
	}
}

function salvaModificheCampagna(){
	
	var idCampagna=$("#idCampagnaDaModificare").val();
	var audit = $("#selectModifica").find('option:selected').attr('id');
	var nomeCampagna=$('#nomeCampagna').val();
	var dataInizioModifica =$('#dataInizioModifica').val();
	var dataFineModifica=$('#dataFineModifica').val();
	var dataInizioOsservazione=$('#dataInizioOsservazione').val();
	var dataFineOsservazione=$('#dataFineOsservazione').val();
	if(!audit || !nomeCampagna || !dataInizioModifica || !dataInizioOsservazione || !dataFineOsservazione ){
		var settings = {
				theme: 'teal',
				sticky: false,
				horizontalEdge: 'top',
				verticalEdge: 'right',
				life: 3000
		};
		$.notific8('zindex', 11500);
		$.notific8('Questi* campi sono obbligatori ', settings);
		return; 
		
	}
		Metronic.startPageLoading();
		$.ajax( {
			type : 'GET',
			//AuAuditAction.java
			url : '/CruscottoAuditAtpoWebWeb/salvaModificaCampagna',
			data :{ 
				'campagna.idCampagna': idCampagna,
				'campagna.nome' : nomeCampagna	,
				'campagna.idAudit' : audit	,
				'campagna.dataInizio' : dataInizioModifica	,
				'campagna.dataFine' : dataFineModifica	,
				'campagna.dataFineOsservazione' : dataFineOsservazione	,
				'campagna.dataInizioOsservazione' : dataInizioOsservazione	
			},
	
			success : function(data) {
				Metronic.stopPageLoading();
				loadDiv('/CruscottoAuditAtpoWebWeb/initCampagna', 'appView', null, 'initAuditCampagna' );
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


function aggiornaTable(){
	$('#campagna_table').DataTable().ajax.reload();
}
function annullaSalvataggio(){
	Metronic.stopPageLoading();
	document.getElementById("info").style.visibility = "hidden";
	slideAside('');
}

