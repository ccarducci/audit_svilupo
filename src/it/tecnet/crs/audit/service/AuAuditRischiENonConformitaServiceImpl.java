package it.tecnet.crs.audit.service;

import java.util.ArrayList;
import java.util.List;

import it.tecnet.crs.audit.jpa.dao.AuAuditRischiENonConformitaDao;
import it.tecnet.crs.audit.web.dto.AuAuditDto;
import it.tecnet.crs.audit.web.dto.AuAuditMRisesprDto;
import it.tecnet.crs.audit.web.dto.AuAuditNonConfDto;
import it.tecnet.crs.audit.web.dto.AuAuditVarCompDto;
import it.tecnet.crs.jpa.model.AuAudit;
import it.tecnet.crs.jpa.model.AuMNonConf;
import it.tecnet.crs.jpa.model.AuMRischio;
import it.tecnet.crs.jpa.model.AuMRisepr;
import it.tecnet.crs.jpa.model.AuMVarcomp;
import it.tecnet.crs.mod.jpa.model.CrsSottoprocesso;
import it.tecnet.crs.util.ModelToDto;

public class AuAuditRischiENonConformitaServiceImpl implements AuAuditRischiENonConformitaService{
	AuAuditRischiENonConformitaDao auAuditRischiENonConformitaDao;


	/*
	 * TABELLA RISCHI___________________________________________________________________________
	 */
	public List<AuMRischio> getRischi(long idAudit, Integer pageNumber,
			Integer pageSize, int columnNameToOrder, String orderType,
			String search) {

		return auAuditRischiENonConformitaDao.getRischi( idAudit,  pageNumber,
				pageSize,  columnNameToOrder,  orderType,
				search);
	}


	public int countListaRischi(long idAudit, int pageNumber,int pageSize, int columnNameToOrder, String orderType,String search) {
		return auAuditRischiENonConformitaDao.countListaRischi( idAudit,  pageNumber, pageSize,  columnNameToOrder,  orderType,search) ;
	}

	//serve quando elimino per vedere se ha delle associazioni
	public AuMRischio checkRischioInAuDomandaAuRisepr(AuMRischio i) {
		return auAuditRischiENonConformitaDao.checkRischioInAuDomandaAuRisepr(i);
	}

	//controlla inserimento duplicati
	public List<AuMRischio> checkCodiceRischio() {
		return auAuditRischiENonConformitaDao.checkCodiceRischio();
	}

	@Override
	public void deleteRischio(AuMRischio i) {
		auAuditRischiENonConformitaDao.delete(i);

	}

	/*
	 * TABELLA ESPRESSIONE RISCHIO_______________________________________________________________________
	 */
	public List<AuAuditMRisesprDto> getEsprRischi(long idAudit,
			Integer pageNumber, Integer pageSize, int columnNameToOrder,
			String orderType, String search) {
		List<Object[]> esprRischi= auAuditRischiENonConformitaDao.getEsprRischi( idAudit,pageNumber,pageSize,columnNameToOrder,orderType,search);

		List<AuAuditMRisesprDto> esprRischiList= new ArrayList<AuAuditMRisesprDto>();
		for(int i=0;i<esprRischi.size();i++){
			esprRischiList.add(ModelToDto.modelToEsprRischioDto(esprRischi.get(i)));
		}
		return esprRischiList;

	}


	public int countListaEsprRischi(long idAudit, int pageNumber,int pageSize, int columnNameToOrder, String orderType,
			String search) {
		// TODO Auto-generated method stub
		return auAuditRischiENonConformitaDao.countListaEsprRischi( idAudit,  pageNumber, pageSize,  columnNameToOrder,  orderType,
				search) ;
	}


	public List<AuMRisepr> checkCodiceEsprRischio() {
		return auAuditRischiENonConformitaDao.checkCodiceEsprRischio();
	}

	/*
	 * TABELLA NON CONFORMITA_______________________________________________________________________
	 */
	public List<AuAuditNonConfDto> getNonConf(long idAudit,
			Integer pageNumber, Integer pageSize, int columnNameToOrder,
			String orderType, String search) {
		List<Object[]> nonConf= auAuditRischiENonConformitaDao.getNonConf( idAudit,pageNumber,pageSize,columnNameToOrder,orderType,search);

		List<AuAuditNonConfDto> nonConfList= new ArrayList<AuAuditNonConfDto>();
		for(int i=0;i<nonConf.size();i++){
			nonConfList.add(ModelToDto.modelToNonConfDto(nonConf.get(i)));
		}
		return nonConfList;
	}

	public int countListaNonConf(long idAudit, int pageNumber,int pageSize, int columnNameToOrder, String orderType,String search) {
		return auAuditRischiENonConformitaDao.countListaNonConf( idAudit,  pageNumber,pageSize,  columnNameToOrder,  orderType,search) ;
	}

	public AuMNonConf checkNcInVarComportamentale(AuMNonConf i) {
		return auAuditRischiENonConformitaDao.checkNcInVarComportamentale(i);
	}
	
	@Override
	public List<AuMNonConf> checkCodiceNC() {
		// TODO Auto-generated method stub
		return auAuditRischiENonConformitaDao.checkCodiceNC();
	}


	
	
	/*
	 * TABELLA VARIANTE COMPORTAMENTALE_________________________________________________________________________________________
	 */
	public List<AuAuditVarCompDto> getVarComp(long idAudit, Integer pageNumber,
			Integer pageSize, int columnNameToOrder, String orderType,
			String search){
		
		List<Object[]> vc= auAuditRischiENonConformitaDao.getVarComp( idAudit,pageNumber,pageSize,columnNameToOrder,orderType,search);

		List<AuAuditVarCompDto> vcList= new ArrayList<AuAuditVarCompDto>();
		for(int i=0;i<vc.size();i++){
			vcList.add(ModelToDto.modelToVarCompDto(vc.get(i)));
		}
		return vcList;
	}
	
	@Override
	public int countListaVarComp(long idAudit, int pageNumber,int pageSize, int columnNameToOrder, String orderType,String search) {
		return auAuditRischiENonConformitaDao.countListaVarComp( idAudit,  pageNumber,pageSize,  columnNameToOrder,  orderType,search) ;
	}
	
	@Override
	public List<AuMNonConf> getNC(long idAudit) {
		// TODO Auto-generated method stub
		return auAuditRischiENonConformitaDao.getNC(idAudit);
	}
	


	//codice variante comp
	public List<AuMVarcomp> checkCodice() {
		// TODO Auto-generated method stub
		return auAuditRischiENonConformitaDao.checkCodice();
	}


	/*
	 * DROPDOWN_________________________________________________________________________________________
	 */

	//rischi da mostrare in aside nuovo/modifica esprRischio
	public List<AuMRischio> getRischiPerDropDown(long idAudit) {

		return auAuditRischiENonConformitaDao.getRischiPerDropDown(idAudit);
	}


	public AuMRischio getRischioOptionDropModifica(long idEsprRischio) {
		// TODO Auto-generated method stub
		return auAuditRischiENonConformitaDao.getRischioOptionDropModifica(idEsprRischio);
	}

	@Override
	public List<CrsSottoprocesso> getFasi(long idAudit) {
		// TODO Auto-generated method stub
		return auAuditRischiENonConformitaDao.getFasi(idAudit);
	}

	/*
	 *  FUNZIONI COMUNI_________________________________________________________________________________________
	 */


	@Override
	public <T> T cerca(Class<T> obj, Object pk) {
		// TODO Auto-generated method stub
		return auAuditRischiENonConformitaDao.cerca(obj , pk);
	}

	@Override
	public <T> T salva(T obj) {
		// TODO Auto-generated method stub
		return auAuditRischiENonConformitaDao.save(obj);
	}



	@Override
	public void remove(Object obj) {
		auAuditRischiENonConformitaDao.remove(obj);

	}

	/*
	 * DAO_________________________________________________________________________________________
	 */
	public AuAuditRischiENonConformitaDao getAuAuditRischiENonConformitaDao() {
		return auAuditRischiENonConformitaDao;
	}

	public void setAuAuditRischiENonConformitaDao(
			AuAuditRischiENonConformitaDao auAuditRischiENonConformitaDao) {
		this.auAuditRischiENonConformitaDao = auAuditRischiENonConformitaDao;
	}







	










}
