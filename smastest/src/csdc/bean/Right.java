package csdc.bean;

import java.util.Set;

import org.apache.struts2.json.annotations.JSON;

public class Right {

	private String id;
	private String name;	//权限
	private String description;	//权限描述
	private String code;	//权限代码
	private Set<RoleRight> role_right;	//角色权限
	private Set<RightUrl> right_action;	//权限action
	
	@JSON(serialize=false)
	public Set<RoleRight> getRole_right() {
		return role_right;
	}
	public void setRole_right(Set<RoleRight> role_right) {
		this.role_right = role_right;
	}
	@JSON(serialize=false)
	public Set<RightUrl> getRight_action() {
		return right_action;
	}
	public void setRight_action(Set<RightUrl> right_action) {
		this.right_action = right_action;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	
}