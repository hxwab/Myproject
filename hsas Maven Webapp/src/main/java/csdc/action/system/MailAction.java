package csdc.action.system;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.opensymphony.xwork2.ActionContext;

import csdc.action.BaseAction;
import csdc.model.Account;
import csdc.model.Person;
//import csdc.bean.common.Visitor;
import csdc.model.Mail;
import csdc.service.IMailService;
import csdc.tool.bean.LoginInfo;
import csdc.tool.info.GlobalInfo;
import csdc.tool.mail.Mailer;

@Component
@Scope(value="prototype")

public class MailAction extends BaseAction {
	
	private final static String DATE_FORMAT = "yyyy-MM-dd";
	private final static String PAGE_NAME = "mailListPage";
	private final static String HQL4SELECT = "select mail.id, mail.sendTo, mail.sended, mail.subject, mail.body, mail.isHtml, " +
				"mail.createTime, mail.sendTimes, mail.finishTime from Mail mail where mail.user.id = user.id" ;
	private Mail mail;
	@Autowired
	private IMailService mailService;
	private String mailId;


	private	int sending;
	private boolean sendNow = true;
	
	
	public String createMail() throws Exception {
		Map session = ActionContext.getContext().getSession();
    	loginer = (LoginInfo) session.get(GlobalInfo.LOGINER);
		Account account = loginer.getAccount();
		//Visitor visitor = (Visitor)session.get("visitor");
		//System.out.println("visitor: " + (visitor == null ? null :visitor.toString()));
		mail.setAccount(account);
		//mail.acc(visitor == null ? (User)(baseservice.query(User.class, "admin")) : visitor.getUser());
		mail.setCreateDate(new Date());
		mail.setSendTimes(0);
		mail.setIsHtml(0);
		mail.setSendTo(Mailer.getUniqueList(mail.getSendTo()));
		mail.setSended("");
		mailId = mailService.addMail(mail);
		if (sendNow){ 
			Mailer.send(mail);
		}
		return SUCCESS;
	}
	
	/**
	 * 保存新邮件
	 * @return 跳转成功
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String addMail() throws Exception {
		sendNow = true;
		createMail();
		String hql = "select mail.id, mail.sendTo, mail.sended, mail.subject, mail.body, mail.isHtml, mail.createTime, mail.sendTimes, mail.finishTime, user.chinesename from Mail mail, User user where mail.user.id = user.id order by mail.createTime desc, mail.id desc";
		Map session = ActionContext.getContext().getSession();
		session.put("hql", hql);
		return SUCCESS;
	}
	
	/**
	 * 查看ID为mailId的邮件详情
	 * @return 跳转成功
	 */
	@SuppressWarnings("unchecked")
	public String viewMail() {
		ActionContext context = ActionContext.getContext();
		Map session = context.getSession();
		mail = (Mail)baseService.query(Mail.class, mailId);
		// 若果没有邮件管理权限，就判断该邮件是否属于自己
	
		mail.setAccount((Account)baseService.query(Account.class, mail.getAccount().getId()));
		sending = Mailer.curSendTo.contains(mailId) ? 1 : 0;
		if (mail.getSended() != null){
			mail.setSended(mail.getSended().replaceAll(";", "; "));
		}
		if (mail.getSendTo() != null){
			mail.setSendTo(mail.getSendTo().replaceAll(";", "; "));
		}
		return SUCCESS;
	}
	
	@Override
	public String toAdd() {
		// TODO Auto-generated method stub
		return SUCCESS;
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String toView() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String toList() {
		// TODO Auto-generated method stub
		return SUCCESS;
	}

	@Override
	public String list() {
		return SUCCESS;
	}

	@Override
	public Object[] simpleSearchCondition() {
		StringBuffer hql = new StringBuffer();
		Map map = new HashMap();
		hql.append(HQL4SELECT);
		return  new Object[]{
				hql.toString(),
				map,
				0,
				null	//4个元素
			};
	}

	@Override
	public String simpleSearch() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object[] advSearchCondition() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String advSearch() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String changePageSize() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String toPage() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String toEntity() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String sort() {
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

	public int getSending() {
		return sending;
	}

	public void setSending(int sending) {
		this.sending = sending;
	}

	public boolean isSendNow() {
		return sendNow;
	}

	public void setSendNow(boolean sendNow) {
		this.sendNow = sendNow;
	}

	public Mail getMail() {
		return mail;
	}

	public void setMail(Mail mail) {
		this.mail = mail;
	}
/*
	public IMailService getMailService() {
		return mailService;
	}

	public void setMailService(IMailService mailService) {
		this.mailService = mailService;
	}*/
	public String getMailId() {
		return mailId;
	}

	public void setMailId(String mailId) {
		this.mailId = mailId;
	}

	public static String getDateFormat() {
		return DATE_FORMAT;
	}

	public static String getPageName() {
		return PAGE_NAME;
	}

	public static String getHql4select() {
		return HQL4SELECT;
	}
}
