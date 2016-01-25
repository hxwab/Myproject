package csdc.tool.listener;

import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;



import csdc.dao.IBaseDao;
import csdc.model.SystemConfig;
import csdc.service.IBaseService;
import org.apache.commons.fileupload.UploadContext;
import csdc.tool.ApplicationContainer;
import csdc.tool.mail.Mailer;
import csdc.tool.SpringBean;
import csdc.tool.bean.UploadPath;


public class StartUpListener implements ServletContextListener{
	
	/**
	 * 上下文初始化
	 */
	@Override 
	public void contextInitialized(ServletContextEvent event) {	
	
		ServletContext sc = event.getServletContext();
		ApplicationContainer.sc = sc;
		IBaseService baseService = (IBaseService) SpringBean.getBean("baseService", sc);
		Mailer.setBaseservice(baseService);
		//TODO
		String mailerAccount = ((SystemConfig) baseService.query("select systemConfig from SystemConfig systemConfig where systemConfig = 'systemconfig0001'").get(0)).getValue();
		sc.setAttribute("mailerAccount", mailerAccount);
		
		sc.setAttribute("queryRows", "200");
		sc.setAttribute("rows", "10");
		sc.setAttribute(UploadPath.PAPER_FORUM, UploadPath.PAPER_FORUM_PATH);
		sc.setAttribute(UploadPath.PAPER_TOPIC, UploadPath.PAPER_TOPIC_PATH);
		sc.setAttribute(UploadPath.ARTICEL_ATTACHMENT, UploadPath.ARTICEL_ATTACHMENT_PATH);
		sc.setAttribute(UploadPath.AWARD_APPLY, UploadPath.AWARD_APPLY_PATH);
		sc.setAttribute(UploadPath.TEMP_UPLOAD, UploadPath.TEMP_UPLOAD_PATH);
		sc.setAttribute(UploadPath.TEMP, UploadPath.TEMP_PATH);

	}
	
	/**
	 * 上下文销毁
	 */
	@Override
	public void contextDestroyed(ServletContextEvent event) {		
	}

}
