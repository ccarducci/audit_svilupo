function formatCustomDate(data){
	var dateArray=data.split('T');
	var d = new Date(data);
	var month = d.getMonth()+1;
	if(month < 10){
		month = "0"+month;
	}
	var day = d.getDate();
	if(day < 10){
		day = "0"+day;
	}
	return day+"/"+month+"/"+d.getFullYear();
}

function initTableAccessiPraticheATPO(){
	
	//tabella pratiche atpo
	var praticheATPO=PraticheATPO.init();

}


function initTablesModificaAuditor(){
	//ci caricherò la tabella del tab fasi
}

function initTableRischiPraticaAtpo(){
	
	var rischi= RischiPraticheAtpo.init();
}

function initTableNonConfPraticaAtpo(){
	var nonConf= NonConfPraticheAtpo.init();
}

function initDocMancanteTable(){
	var c= DocMancanteTable.init();
}

function initReportVarCompT(){
	var c= VarCNonConf.init();
}

function initReportEsprRischiTablePM(){
	var r= esprRischiPMTable.init();
}

function initTableMediaTempi(){
	var c= mediaTempi.init();
}