package csdc.tool.security;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import csdc.bean.User;
import csdc.service.IUserService;
import csdc.tool.MD5;

/**
 * 实现UserDetailsService接口
 * @author 龚凡
 */
public class MyUserDeitailsService implements UserDetailsService {

    private IUserService userService;// 账号管理接口
    private List<GrantedAuthority> AUTHORITIES = new ArrayList<GrantedAuthority>();

    /**
     * 根据账号名生成权限对象
     */
    @SuppressWarnings({})
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException, DataAccessException {
    	// 根据账号名查找账号对象
    	User user = userService.getByUsername(username);// 获取认证的账号对象
    	GrantedAuthority[] grantedAuthArray;// 账号权限
    	if (user == null) {// 如果用户不存在，则抛异常
    		throw new UsernameNotFoundException("User name is not found.");
    	} else {// 如果账号存在，则查找其拥有的权限，并打包成security需要的形式
    		List<String> userRight = null;
    		userRight = (List<String>) userService.getUserRight(user.getId());
    		grantedAuthArray = new GrantedAuthority[userRight.size()];
    		Iterator iterator = userRight.iterator();
			int i = 0;
			while (iterator.hasNext()) {// 遍历用户权限，生成security需要的权限对象GrantedAuthority
				grantedAuthArray[i] = new GrantedAuthorityImpl(((String) iterator.next()).toUpperCase());
				i++;
			}
   		return new org.springframework.security.core.userdetails.User(username, user.getPassword(), true, true, true, true, grantedAuthArray);
    	}

    }

    public void setUserService(IUserService userService) {
		this.userService = userService;
	}
}
