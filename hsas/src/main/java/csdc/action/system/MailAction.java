package csdc.action.system;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.opensymphony.xwork2.ActionContext;

import csdc.action.BaseAction;
import csdc.model.Account;
import csdc.model.Person;
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
	private final static String HQL4SELECT = "select mail.id, mail.subject, mail.sended, mail.body, mail.isHtml, " +
				"mail.createDate, mail.sendTimes, mail.finishDate from Mail mail where 1=1" ;
	private static final String PAGE_BUFFER_ID = "mail.id";
	private final static String[] SORT_COLUMNS = new String[] {
		"mail.subject",
		"mail.createDate"
	};
	private Mail mail;
	@Autowired
	private IMailService mailService;
	private String mailId;
	private String[] mailids;


	private	int sending;
	private boolean sendNow = true;
	
	
	public String createMail() throws Exception {
		Map session = ActionContext.getContext().getSession();
    	loginer = (LoginInfo) session.get(GlobalInfo.LOGINER);
		Account account = loginer.getAccount();
		//Visitor visitor = (Visitor)session.get("visitor");
		//System.out.println("visitor: " + (visitor == null ? null :visitor.toString()));
		mail.setAccountId(account.getId());
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
		String hql = "select mail.id, mail.sendTo, mail.sended, mail.subject, mail.body, mail.isHtml, mail.createDate, mail.sendTimes, mail.finishDate from Mail mail, User user where 1=1 by mail.createDate desc, mail.id desc";
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
	
	//	mail.setAccountId((Account)baseService.query(Account.class, mail.getAccount().getId()));
		mail.setAccountId(mail.getAccountId());

		sending = Mailer.curSendTo.contains(mailId) ? 1 : 0;
		if (mail.getSended() != null){
			mail.setSended(mail.getSended().replaceAll(";", "; "));
		}
		if (mail.getSendTo() != null){
			mail.setSendTo(mail.getSendTo().replaceAll(";", "; "));
		}
		return SUCCESS;
	}
	
	/**
	 * 发送所有还未完全发送成功的邮件
	 * @return 跳转成功
	 */
	@SuppressWarnings("unchecked")
	public String sendAllMails() throws Exception{
		try {
			List lm = dao.query("select mail from Mail mail where mail.sendTo is not null order by mail.sendTimes asc, mail.createTime asc");
			//hql语句待调整
			for (int i = 0; i < lm.size(); i++){
				mail = (Mail) lm.get(i);
				Mailer.send(mail);
			}
			return SUCCESS;
		} catch (Exception e) {
			return ERROR;
		}
	}
	
	/**
	 * 在邮件列表中发送多条邮件。
	 * @return 跳转成功
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String groupSendMail() throws Exception {
		
		for(int i = 0; i < mailids.length; i++) {
			mail = (Mail) dao.query(Mail.class, mailids[i]);
			Mailer.send(mail);
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
		jsonMap.clear();
		mail = mailService.getMailById(mailId);
		jsonMap.put("mail", mail);

		return null;
	}

	@Override
	public String toView() {
		return SUCCESS;
	}

	@Override
	public Object[] simpleSearchCondition() {
		StringBuffer hql = new StringBuffer();
		Map map = new HashMap();
		hql.append(HQL4SELECT);
		if (keyword != null && !keyword.isEmpty()) {
			hql.append(" and ");
			if (searchType == 1) {
				hql.append("LOWER(mail.subject) like :keyword");
				map.put("keyword", "%" + keyword + "%");
			} else if (searchType == 2) {
				hql.append("LOWER(mail.body) like :keyword");
				map.put("keyword", "%" + keyword + "%");
			} else {
				hql.append("(LOWER(mail.subject) like :keyword or LOWER(mail.accountId) like :keyword)");
				map.put("keyword", "%" + keyword + "%");
			}
		}
		return  new Object[]{
				hql.toString(),
				map,
				0,
				null	//4个元素
			};
	}



	@Override
	public Object[] advSearchCondition() {
		// TODO Auto-generated method stub
		return null;
	}




	@Override
	public String toEntity() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public String pageName() {
		return PAGE_NAME;
	}

	@Override
	public String pageBufferId() {
		return PAGE_BUFFER_ID;
	}

	@Override
	public String[] sortColumn() {
		return SORT_COLUMNS;
	}

	@Override
	public String dateFormat() {
		return DATE_FORMAT;
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
	
	public IMailService getMailService() {
		return mailService;
	}

	public void setMailService(IMailService mailService) {
		this.mailService = mailService;
	}

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

	public String[] getMailids() {
		return mailids;
	}

	public void setMailids(String[] mailids) {
		this.mailids = mailids;
	}

	public static String[] getSortColumns() {
		return SORT_COLUMNS;
	}

	public static String getPageBufferId() {
		return PAGE_BUFFER_ID;
	}
}