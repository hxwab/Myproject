package csdc.service.imp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.stereotype.Service;


import csdc.model.SystemOption;
import csdc.service.IJournalService;

/**
 * 
 * 期刊管理接口实现
 * @author fengzj
 *
 */
@Service
public class JournalService extends BaseService implements IJournalService {

	@Override
	public Document createRightXML() {
		List<SystemOption> allJournal = null;
		List<String> nodevalue = null;
		
		Map map = new HashMap();
		
		allJournal = dao.query("select sysOption from SystemOption sysOption where sysOption.standard = 'qikan' order by sysOption.code asc",map);
		nodevalue = dao.query("select sysOption.code from SystemOption sysOption where sysOption.standard = 'qikan' order by sysOption.code asc",map);
		Map<String, String> journalMap = new HashMap<String, String>();
		int MAXlength = 0;
		for(int i = 0; i < allJournal.size(); i++) {
			//获取所有权限后生成权限的节点值和描述的键值对
			if( allJournal.get(i).getCode().length() / 2 > MAXlength ) {
				MAXlength = allJournal.get(i).getCode().length() / 2;
			}
			journalMap.put(allJournal.get(i).getCode(), allJournal.get(i).getName());
		}
		
		//将长度为2,4,6,8...的权限节点值分别放入对应的数组
		List<List<String>> journalNodes = new ArrayList();
		List<List<String>> journalNames = new ArrayList();
		for(int i = 0; i <= MAXlength; i++) {
			List<String> tmp1 = new ArrayList<String>();
			List<String> tmp2 = new ArrayList<String>();
			journalNodes.add(tmp1);
			journalNames.add(tmp2);
		}
		//根据需要生成树的权限的list，将其放入其节点长度对应的数组
		for(int i = 0; i < allJournal.size(); i++) {
			String idString = allJournal.get(i).getCode();
			for(int j = 1; j <= MAXlength; j++) {
				if(idString.length() / 2 >= j && ( !journalNodes.get(j).contains(idString.substring(0, 2 * j)) )) {
					journalNodes.get(j).add(idString.substring(0, 2 * j));
					journalNames.get(j).add(journalMap.get(idString.substring(0, 2 * j)) == null ? "未定义期刊" : journalMap.get(idString.substring(0, 2 * j)));
				}
			}
		}
		
		//根节点的建立z
		Document document = DocumentHelper.createDocument();
		document.setXMLEncoding("utf-8");
		Element root = document.addElement("tree");
		root.addAttribute("id", "0");
		Element item0 = root.addElement("item");
		item0.addAttribute("text", "所有期刊");
		item0.addAttribute("id", "all");
		item0.addAttribute("im0", "folder_closed.gif");
		item0.addAttribute("im1", "folder_open.gif");
		item0.addAttribute("im2", "folder_closed.gif");
		item0.addAttribute("open", "1");
		item0.addAttribute("select", "1");
		
		//根节点的节点值长度为2*0，第一层节点值长度为2*1...
		journalNodes.get(0).add("");
		
		//递归添加节点的叶子节点
		addTreeItem(journalNodes.get(0).get(0), item0, journalMap, journalNodes, journalNames);

		return document;
	}
	/**
	 * 递归生成某节点的子节点
	 * @param nodevalue某节点的node值
	 * @param e父节点
	 * @param rightMap用于方便查询生成的权限节点值与描述的键值对
	 * @param str需要勾选中的节点的节点值串，逗号分隔开
	 * @param rightNodes权限节点集合
	 * @param rightNames权限名称集合
	 */
	private void addTreeItem(String nodevalue, Element e, Map<String, String> rightMap,List<List<String>> rightNodes, List<List<String>> rightNames) {
		int len = nodevalue.length() / 2;
		//如果该节点没有子节点就返回
		if(rightNodes.size() < len + 2 || rightNodes.get(len + 1) == null || rightNodes.get(len + 1).size() == 0) {
			return ;
		}
		for(int i = 0; i < rightNodes.get(len + 1).size(); i++) {
			if( rightNodes.get(len + 1).get(i).substring(0, len * 2).equals(nodevalue) ) {
				Element t = e.addElement("item");
				t.addAttribute("text", rightMap.get(rightNodes.get(len + 1).get(i)) == null ? "未定义期刊" : rightMap.get(rightNodes.get(len + 1).get(i)));
				t.addAttribute("id", rightNodes.get(len + 1).get(i));
				t.addAttribute("im0", "folder_closed.gif");
				t.addAttribute("im1", "folder_open.gif");
				t.addAttribute("im2", "folder_closed.gif");
				//递归添加此节点的叶子节点
				addTreeItem(rightNodes.get(len + 1).get(i), t, rightMap,rightNodes, rightNames);
			}
		}
	}
}
