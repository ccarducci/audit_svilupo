package it.tecnet.crs.audit.web.beans;

import it.tecnet.crs.audit.web.dto.RisultatoRegolaCampagnaCampioneDto;

import java.util.HashMap;
import java.util.List;

public class ModelRisultatiTab {
	


	// key=idRegola , value=coppia RisultatoRegolaCampagnaCampioneDto
    public HashMap<Long, List<RisultatoRegolaCampagnaCampioneDto>> mapRisultati = new HashMap<Long,List<RisultatoRegolaCampagnaCampioneDto>>();


	public HashMap<Long, List<RisultatoRegolaCampagnaCampioneDto>> getMapRisultati() {
		return mapRisultati;
	}


	public void setMapRisultati(
			HashMap<Long, List<RisultatoRegolaCampagnaCampioneDto>> mapRisultati) {
		this.mapRisultati = mapRisultati;
	}



}
