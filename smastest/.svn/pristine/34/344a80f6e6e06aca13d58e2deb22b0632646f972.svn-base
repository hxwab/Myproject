package csdc.bean.common;

import java.util.HashSet;
import java.util.Set;

import csdc.bean.User;

public class Visitor {
	
	private User user;
	private int isSuperUser;// 记录登录账号是否系统管理员，供切换账号使用（切换账号之后保留切换回来的入口）
	private Set<String> userRight = new HashSet<String>();

	public Set<String> getUserRight() {
		return userRight;
	}
	public void setUserRight(Set<String> userRight) {
		this.userRight = userRight;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public int getIsSuperUser() {
		return isSuperUser;
	}
	public void setIsSuperUser(int isSuperUser) {
		this.isSuperUser = isSuperUser;
	}	
}