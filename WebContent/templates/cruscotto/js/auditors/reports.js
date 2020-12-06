function showGReport(){ 

	var selectBoxRep = document.getElementById("comboReports");


	if (selectBoxRep.options[selectBoxRep.selectedIndex] != undefined){
		var selectedRepValue = selectBoxRep.options[selectBoxRep.selectedIndex].value;
		console.log('initG'+selectedRepValue);
		loadDiv('/CruscottoAuditAtpoWebWeb/getDetailGrafFase?titleGrafico='+selectedRepValue, 'graficoFase', null, 'initReports');


	}
}

function initReports(){
	console.log("INIT REPORT 2");

	$.ajax( {
		type : 'GET',
		url : '/CruscottoAuditAtpoWebWeb/json/initReportsfasi',
		
		success : function(dataIn) {
		console.log(dataIn.toReturn);
		let jsonData = JSON.parse(dataIn.toReturn);
		
			new Chart(document.getElementById("bar-chart"), {
				type: 'bar',
				responsive: true, 
				data: jsonData,
				options: {
					maintainAspectRatio: false,
					responsive:true,
					title: {
						display: true
					},
					
					tooltips: {
			            callbacks: {
			                label: function(tooltipItems, data) {
							let label2; 
							try {
								var total=0;
								var xIndecCol =tooltipItems.index; 
								for (var i in data.datasets){
									var indata =data.datasets[i]; 
									total=total+indata.data[xIndecCol];
								}
								 var dataset = data.datasets[tooltipItems.datasetIndex];
								  var currentValue = dataset.data[tooltipItems.index];
							      var percentage = Math.floor(((currentValue/total) * 100));
							   } catch (error) {
					          console.log(error);
					        }
						
						
						
			                    return data.datasets[tooltipItems.datasetIndex].label +' - ' + tooltipItems.yLabel + ' su '+total + ' ('+percentage+"%)";
			                }
			            }
		
			        }
					
					
					
				}
			});
			
			
		//esecuzione provvedimenti	
		if(dataIn.thisFase == 1){
			let jsonDataTwo = JSON.parse(dataIn.toReturnTwo);
			new Chart(document.getElementById("bar-chart-two"), {
				type: 'bar',
				responsive: true, 
				
				data: jsonDataTwo,
				options: {
					maintainAspectRatio: false,
					responsive:true,
					title: {
						display: true
					},
					
					tooltips: {
			            callbacks: {
			                label: function(tooltipItems, data) {
							let label2; 
							try {
								var total=0;
								var xIndecCol =tooltipItems.index; 
								for (var i in data.datasets){
									var indata =data.datasets[i]; 
									total=total+indata.data[xIndecCol];
								}
								 var dataset = data.datasets[tooltipItems.datasetIndex];
								  var currentValue = dataset.data[tooltipItems.index];
							      var percentage = Math.floor(((currentValue/total) * 100));
							   } catch (error) {
					          console.log(error);
					        }
						
						
						
			                    return data.datasets[tooltipItems.datasetIndex].label +' - ' + tooltipItems.yLabel + ' su '+total + ' ('+percentage+"%)";
			                }
			            }
		
			        }
				}
			});
			
			
			let jsonDataPrecetto = JSON.parse(dataIn.toReturnThree);
			new Chart(document.getElementById("bar-chart-three"), {
				type: 'bar',
				responsive: true, 
				
				data: jsonDataPrecetto,
				options: {
					maintainAspectRatio: false,
					responsive:true,
					title: {
						display: true,
						text: 'PRECETTO'
					},
					
					tooltips: {
			            callbacks: {
			                label: function(tooltipItems, data) {
							let label2; 
							try {
								var total=0;
								var xIndecCol =tooltipItems.index; 
								for (var i in data.datasets){
									var indata =data.datasets[i]; 
									total=total+indata.data[xIndecCol];
								}
								 var dataset = data.datasets[tooltipItems.datasetIndex];
								  var currentValue = dataset.data[tooltipItems.index];
							      var percentage = Math.floor(((currentValue/total) * 100));
							   } catch (error) {
					          console.log(error);
					        }
						
						
						
			                    return data.datasets[tooltipItems.datasetIndex].label +' - ' + tooltipItems.yLabel + ' su '+total + ' ('+percentage+"%)";
			                }
			            }
		
			        }
				}
			});
			
			let jsonDataPignoramento = JSON.parse(dataIn.toReturnFour);
			new Chart(document.getElementById("bar-chart-four"), {
				type: 'bar',
				responsive: true, 
				
				data: jsonDataPignoramento,
				options: {
					maintainAspectRatio: false,
					responsive:true,
					title: {
						display: true,
						text: 'PIGNORAMENTO'
					},
					
					tooltips: {
			            callbacks: {
			                label: function(tooltipItems, data) {
							let label2; 
							try {
								var total=0;
								var xIndecCol =tooltipItems.index; 
								for (var i in data.datasets){
									var indata =data.datasets[i]; 
									total=total+indata.data[xIndecCol];
								}
								 var dataset = data.datasets[tooltipItems.datasetIndex];
								  var currentValue = dataset.data[tooltipItems.index];
							      var percentage = Math.floor(((currentValue/total) * 100));
							   } catch (error) {
					          console.log(error);
					        }
						
						
						
			                    return data.datasets[tooltipItems.datasetIndex].label +' - ' + tooltipItems.yLabel + ' su '+total + ' ('+percentage+"%)";
			                }
			            }
		
			        }
				}
			});
			
			
			
		}	
			
			

		return;


	},
	error : function(data) {
		document.getElementById("bar-chart-two").value ='Dati non presenti';
		console.log('ERRORE');
		console.log(data);
//		var settings = {
//				theme : 'teal',
//				sticky : false,
//				horizontalEdge : 'top',
//				verticalEdge : 'right',
//				life : 3000
//		};
//		$.notific8('zindex', 11500);
//		$.notific8('Errore durante il recupero dei dati ', settings);
		return;
	}
	});







} 