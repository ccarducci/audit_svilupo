package it.tecnet.crs.util.datatables;

import java.util.Map;

public class DataTablesParamUtility {

	
	public static String getStringParameter(String param, Map<String, Object> parametersBody){
		Object[] ret = (Object[]) parametersBody.get(param);
		return (String)ret[0];
	}
	
	public static Integer getIntegerParameter(String param, Map<String, Object> parametersBody){
		Object[] ret = (Object[]) parametersBody.get(param);
		return Integer.parseInt((String)ret[0]);
	}
	
	public static Boolean getBoolParameter(String param, Map<String, Object> parametersBody){
		Object[] ret = (Object[]) parametersBody.get(param);
		return Boolean.parseBoolean((String)ret[0]);
	}
	
	public static void loadParameters(DataTablesModel model,Map<String, Object> parameters) {
		model.setBodyParameters(parameters);
		
	    if( parameters.containsKey("sEcho") 
	    		&&  parameters.get("sEcho") != null
	    		&& getStringParameter("sEcho", parameters).length() > 0)
	    {
	    	try{
	    		Integer iDisplayLength = getIntegerParameter("iDisplayLength", parameters);
	    		model.setiDisplayLength(iDisplayLength);
	    		
	    		Integer iDisplayStart = getIntegerParameter("iDisplayStart", parameters);
	    		model.setiDisplayStart(iDisplayStart);
	    		
		    	if (parameters.containsKey("iColumns") && parameters.get("iColumns") != null ){
		    		int iColumns = Integer.parseInt( getStringParameter("iColumns", parameters));
		    		for(int i=0; i < iColumns; i++){
		                String sSearchTmp = "sSearch_" + i;
		                String bSearchableTmp  = "bSearchable_" + i;
		               	String bSortableTmp = "bSortable_" + i;
		               	String bRegexTmp = "bRegex_" + i;
		                String mDataPropTmp = "mDataProp_" + i;
		                
		                String sSearch = getStringParameter(sSearchTmp, parameters);
		                Boolean bSearchable = getBoolParameter(bSearchableTmp, parameters);
		                Boolean bSortable = getBoolParameter(bSortableTmp, parameters);
		                Boolean bRegex = getBoolParameter(bRegexTmp, parameters);
		                String mDataProp = getStringParameter(mDataPropTmp, parameters);
		                
		                model.getSSearchArray().add(sSearch);
		                model.getBSearchableArray().add(bSearchable);
		                model.getBSortableArray().add(bSortable);
		                model.getBRegexArray().add(bRegex);
		                model.getMDataPropArray().add(mDataProp);
		    		}
		    	}
		    	
		    	if ( getStringParameter("iSortingCols", parameters) != null 
		    			&& getStringParameter("iSortingCols", parameters).length() > 0 ){
		    		int iSortingCols =  Integer.parseInt( getStringParameter("iSortingCols", parameters));
		    		for(int i=0; i < iSortingCols; i++){
		                String sSortDirTmp = "sSortDir_" + i;
		                String iSortColTmp = "iSortCol_" + i;
		                String sSortDir = getStringParameter(sSortDirTmp, parameters);
		                String iSortCol = getStringParameter(iSortColTmp, parameters);
		                model.getSSortDirArray().add(sSortDir);
		                model.getISortColArray().add(iSortCol);
		    		}
		    	}
	    	}catch(Exception e){
	    		System.out.print(e.getMessage());
	    	}
	    }
	}
	
	
}
