package csdc.service.imp;

import java.io.File;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import csdc.service.IMailService;
import csdc.model.Account;
import csdc.model.Mail;

@Service
@Transactional
public class MailService extends BaseService implements IMailService {

	@Override
	public String addMail(Mail mail) throws Exception {
		return dao.add(mail).toString();
	}

	@Override
	public Mail viewMail(String mailId) {
		return dao.query(Mail.class, mailId);

	}



	@Override
	public List<String> generateEmailList(List recieverType) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String renameFile(String id, File file) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List getFiles(String path) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String uploadToDmss(Mail mail) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Mail getMailById(String mailId) {
      if(mailId==null ||mailId.equals("")){
			
			return null;
		  } else {
			
			return dao.query(Mail.class, mailId);
		  }
	}

	@Override
	public Account getAccountById(String accountId) {
	      if(accountId==null ||accountId.equals("")){
				
				return null;
			  } else {
				
				return dao.query(Account.class, accountId);
			  }
	}



		
	}
	



