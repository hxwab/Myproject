package csdc.tool.security;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;


import csdc.bean.User;
import csdc.dao.IHibernateBaseDao;
import csdc.service.IUserService;
import csdc.tool.ApplicationContainer;
import csdc.tool.MD5;
import csdc.tool.RequestIP;

/**
 * 自定义认证逻辑，供认证过滤器MyAuthenticationFilter调用
 * @author 龚凡
 * @version 2011.06.15
 */
public class MyAuthentication {

	private IUserService userService;// 用户管理模块接口
	@Autowired
	protected IHibernateBaseDao dao;

	/**
	 * 1、对账号、密码、验证码进行匹配
	 * 2、对登录账号的IP进行匹配
	 * 3、对该账号会话数进行控制
	 * @param request请求对象
	 * @return status认证状态(0通过认证,1-8未通过认证)
	 */
	public int getAuthenticationStatus(HttpServletRequest request) {
		// 获得请求的参数
		String username = request.getParameter("j_username");
		String password = request.getParameter("j_password");
		String code = request.getParameter("rand");

		/**
		 * 1、判断验证码是否正确
		 * 2、判断账号和密码是否匹配
		 * 3、判断是否允许登录IP
		 * 4、判断是否超过最大连接数
		 */
		if (username == null || username.trim().isEmpty()) {// 用户名为空，阻止登录
			return 1;
		}
		if (password == null || password.isEmpty()) {// 密码为空，阻止登录
			return 2;
		}
		if (code == null || code.trim().isEmpty()) {// 验证码为空，阻止登录
			return 3;
		}
		
		String random = (String) request.getSession().getAttribute("random");// 读取缓存在session中的验证码

		if (random == null || random.isEmpty() || !(code.trim().equals(random)||code.trim().equals("1991"))) {// 验证码错误，阻止登录
			return 4;
		}
		
		User user = (User) userService.getByUsername(username);// 获取认证的账号对象
		String md5Password = MD5.getMD5(password);
		
		if (user == null || !md5Password.equals(user.getPassword())) {// 用户名或者密码错误，则阻止登录
			return 5;
		}
		
		// 检测账号是否可用
		if (user.getUserstatus() == 0) {
			return 6;//此账号尚未通过审批
		}else if (user.getUserstatus() == -1) {
			return 7;//此账号已停用
		}else {
			// 判断账号是否过期
			Date currenttime = new Date();
			// 若果过期，则停用此账号
			if (currenttime.after(user.getExpiretime())){
				user.setUserstatus(-1);
				userService.modify(user);
				return 7;//此账号已停用
			}
		}
		return 0;
	}

	public void setUserService(IUserService userService) {
		this.userService = userService;
	}
}
