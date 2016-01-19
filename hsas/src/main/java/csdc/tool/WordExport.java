package csdc.tool;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public class WordExport {
	
    private static Configuration configuration = null;  
  
    private static String ZipPath ;
    private long number;
    private   String realPath ;
    
    static{  
        configuration = new Configuration();  
        configuration.setDefaultEncoding("UTF-8");  
         ZipPath = ApplicationContainer.sc.getRealPath("upload/temp/zip");
        File f = new File(ZipPath);
        if(!f.exists()){
        	f.mkdirs();
        }
    }  
	
    public WordExport(long number) {
    	this.number = number;
	}
    
    
    public  String createWord(String fileName,Map<String,Object> dataMap){  
       // Map<String,Object> dataMap=new HashMap<String,Object>();  
       // getData(dataMap);  
    	//String docRealPath = ApplicationContainer.sc.getRealPath("/jsp/product");
    	
    	///hsas(new)/src/main/webapp/jsp/product/apply.ftl
    	//hsas(new)/src/main/resources/apply.ftl
        //configuration.setClassForTemplateLoading(this.getClass(), "/src/main/resources");  //FTL文件所存在的位置  
        configuration.setClassForTemplateLoading(this.getClass(), "/csdc/tool");  //FTL文件所存在的位置  
        Template t=null;  
        try {  
            t = configuration.getTemplate("apply.ftl"); //文件名  
            t.setEncoding("utf-8");
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
        
        //String path = (String) ApplicationContainer.sc.getAttribute(UploadPath.TEMP);
        
       
        realPath = ApplicationContainer.sc.getRealPath("upload/temp/"+number);
        File f = new File(realPath);
        if(!f.exists()){
        	f.mkdirs();
        }
        File outFile = new File(realPath+"/"+fileName+".doc"); 
       
       
        Writer out = null;  
        try {  
            out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outFile),"utf-8"));  
        } catch (FileNotFoundException e1) {  
            e1.printStackTrace();  
        } catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
           
        try {  
            t.process(dataMap, out);  
        } catch (TemplateException e) {  
            e.printStackTrace();  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
        
        return realPath;
    }  
    
    
    private  void getData(Map<String, Object> dataMap) {  
        dataMap.put("title", "标题");  
        dataMap.put("nian", "2012");  
        dataMap.put("yue", "2");  
        dataMap.put("ri", "13");  
       /* dataMap.put("ren", "唐鑫");  
        dataMap.put("xuhao", "10");  
        dataMap.put("neirong", "内容"); */ 
      
       List<Map> list = new ArrayList<Map>();  
        for (int i = 0; i < 10; i++) {  
           /* Map<String,Object> map = new HashMap<String,Object>();  
            map.put("ren", "唐鑫"+i);
            map.put("xuhao", i);  
            map.put("neirong", "内容"+i);  
            list.add(map);*/  
        	
        	
        }  
          
        dataMap.put("WBlist", list);
     
    }


	public String getRealPath() {
		return realPath;
	}


	public void setRealPath(String realPath) {
		this.realPath = realPath;
	}


	public static String getZipPath() {
		return ZipPath;
	}


	public static void setZipPath(String zipPath) {
		ZipPath = zipPath;
	}

    
	

}
