package csdc.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import sun.print.resources.serviceui;

@Entity
@Table(name = "T_ACCOUNT_ROLE")
public class AccountRole {
	
	

	    private String id; //权限id（PK）
		private Account  account;//账户
		private Role  role;	//角色

		@Id	
		@Column(name="C_ID", length=40)  
		@GeneratedValue(generator="idGenerator")
	    @GenericGenerator(name="idGenerator", strategy="uuid")
		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}
		
		@ManyToOne
		@JoinColumn(name="C_ACCOUNT_ID")
		public Account getAccount() {
			return account;
		}

		public void setAccount(Account account) {
			this.account = account;
		}
		
		@ManyToOne
		@JoinColumn(name="C_ROLE_ID")
		public Role getRole() {
			return role;
		}

		public void setRole(Role role) {
			this.role = role;
		}

		


}
