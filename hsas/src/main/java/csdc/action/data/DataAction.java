package csdc.action.data;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.map.JsonMappingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.opensymphony.xwork2.ActionSupport;
import com.sun.net.httpserver.Authenticator.Success;

import csdc.action.BaseAction;
import csdc.service.IDataService;

@Component
@Scope(value = "prototype")
public class DataAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6155743774300271538L;
	
	
	private int type;  //查询类型 ：1申报情况、2初评成果、3复评、4单位申报5、作者6、奖项
	private String year; //查询年份
	private Map  jsonMap = new HashMap();
	
	@Autowired
	private IDataService dataService;
	
	public String  toCaclResult(){
		
		return SUCCESS;
	}
	
	public String  caclResult(){
		
		List list = dataService.calcService(type, year);
		jsonMap.put("result", list);
		return SUCCESS;
	}
	
	public void validateCalcResult(){
		
		if(type==0){
			this.addFieldError("error", "请选择查询类型！");
		}
		   
		if (year==null){
			this.addFieldError("error", "请选择查询年份");
		}
	}
	
	
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public Map getJsonMap() {
		return jsonMap;
	}
	public void setJsonMap(Map jsonMap) {
		this.jsonMap = jsonMap;
	}
	
}
