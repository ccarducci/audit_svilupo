package it.tecnet.crs.ATPO.auditors.web.dto;

import java.math.BigDecimal;

public class AuSMEsprRischioDTO {
	


		private long idSEsprRischio;
		private String descrizioneEsprRischio;
		private int num;
		private BigDecimal suPs;
		private String azioniCorrettive;
		private String possibiliMotivazioniRischio;
		
		public String getDescrizioneEsprRischio() {
			return descrizioneEsprRischio;
		}
		public void setDescrizioneEsprRischio(String descrizioneEsprRischio) {
			this.descrizioneEsprRischio = descrizioneEsprRischio;
		}
		public int getNum() {
			return num;
		}
		public void setNum(int num) {
			this.num = num;
		}
		
		public void setSuPs(BigDecimal suPs) {
			this.suPs = suPs;
		}
		public BigDecimal getSuPs() {
			return suPs;
		}
		public void setAzioniCorrettive(String azioniCorrettive) {
			this.azioniCorrettive = azioniCorrettive;
		}
		public String getAzioniCorrettive() {
			return azioniCorrettive;
		}
		public void setPossibiliMotivazioniRischio(
				String possibiliMotivazioniRischio) {
			this.possibiliMotivazioniRischio = possibiliMotivazioniRischio;
		}
		public String getPossibiliMotivazioniRischio() {
			return possibiliMotivazioniRischio;
		}
		public void setIdSEsprRischio(long idSEsprRischio) {
			this.idSEsprRischio = idSEsprRischio;
		}
		public long getIdSEsprRischio() {
			return idSEsprRischio;
		}
		
		
		
		
	}

