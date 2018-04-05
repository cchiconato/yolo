package br.com.yolo.user;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.validator.constraints.Email;

import br.com.yolo.utils.BaseEntity;

/**
* @author  Chrisitan Chiconato
* @since   2018-03-15
*/

@Entity
@Table(name = "user_verification")
public class UserVerificationEntity extends BaseEntity<Long>{
	
	private static final long serialVersionUID = 1L;

	@Email
	@Column(name = "user_name", length = 100, nullable = false, unique = true)
	private String userName;
	
	@Column(name = "verificiaton_key", length = 255, nullable = false, unique = true)
	private String verificationKey;
	
	public UserVerificationEntity(){}
	
	public UserVerificationEntity(String userName, String verificationKey) {
		this.userName = userName;
		this.verificationKey = verificationKey;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getVerificationKey() {
		return verificationKey;
	}

	public void setVerificationKey(String verificationKey) {
		this.verificationKey = verificationKey;
	}
	
	
}
