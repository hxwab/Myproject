package csdc.action.system;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;
import csdc.model.SystemOption;
import org.dom4j.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.opensymphony.xwork2.ActionContext;

import csdc.action.BaseAction;
import csdc.service.IJournalService;

@Component
@Scope(value="prototype")

public class JournalAction extends BaseAction{
	int soId = 0;
	private String node;
	
	@Autowired
	private IJournalService journalService;

	@Override
	public String toAdd() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String add() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String delete() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String toModify() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String modify() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String view() {
		return SUCCESS;
	}

	@Override
	public String toView() {
		return SUCCESS;
	}
	
	
	/**
	 * 创建期刊树
	 * @throws UnsupportedEncodingException 
	 */
	public String createJournalTree() throws UnsupportedEncodingException {
	
		Document doc = journalService.createRightXML();
		String content = doc.asXML();
		HttpServletResponse response = ServletActionContext.getResponse();
		// 使用utf-8
		response.setContentType("text/xml;charset=UTF-8");
		PrintWriter out = null;
		try {
			out = response.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}
		out.print(content); // 通过Io流写到页面上去了
		// 必须返回空
		return null;
	}

	/**
	 * 更新期刊树 
	 */
    public String update() {
    	Map map = new HashMap();
    	if (null != node && !node.isEmpty()) {
    		List<SystemOption> systemOptions = dao.query("select sysOption from SystemOption sysOption where sysOption.standard = 'qikan' order by sysOption.code asc");
    		for (int l = 0; l < systemOptions.size(); l++) {
				dao.delete(systemOptions.get(l));// 在sqlDevelop中设置删除方式为cascade，这里就自动删除子节点
			}
    		JSONArray array = JSONArray.fromObject(node);
			SystemOption systemOption = null;
			for (int i = 0; i < array.size(); i++) {
				JSONObject object = (JSONObject) array.get(i);
				setter(object);
			}
			jsonMap.put("tag", "1");
			return SUCCESS;
		}
    	else return ERROR;    	   	
	}
    
    private void setter(JSONObject object) {
    	SystemOption so = new SystemOption();
    	so.setName(object.getString("name"));
    	so.setCode(object.getString("code"));
    	so.setIsAvailable(1);
    	so.setStandard("qikan");
    	so.setId(""+soId);
    	soId ++;
    	dao.add(so);		
	}
	@Override
	public Object[] simpleSearchCondition() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object[] advSearchCondition() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String pageName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String pageBufferId() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String[] sortColumn() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String dateFormat() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getNode() {
		return node;
	}

	public void setNode(String node) {
		this.node = node;
	}
}
