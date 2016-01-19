package csdc.service;


import java.util.List;

import csdc.model.SolicitPapers;



public interface ISolicitPapersService extends IBaseService {
	
	/**
	 * 根据文件名获取文件
	 * @param documentName
	 * @return
	 */
	public SolicitPapers getSolicitPapers(String entityId);
	
	/**
	 * 添加文件
	 * @param document
	 * @return
	 */
    public String addDocument(SolicitPapers document);
    
    /**
     * 修改文件
     * @param document
     * @param oldDocument
     * @return
     */
    public String modifyDocument(SolicitPapers solicitPapers ,SolicitPapers oSolicitPapers);
    
    public void delete(List<String> entityIds);

	

}
