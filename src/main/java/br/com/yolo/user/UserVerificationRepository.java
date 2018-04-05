package br.com.yolo.user;

import org.springframework.data.jpa.repository.JpaRepository;

/**
* @author  Chrisitan Chiconato
* @since   2018-03-15
*/

public interface UserVerificationRepository extends JpaRepository<UserVerificationEntity, Long>{
	
	public UserVerificationEntity findByVerificationKey(String verificationKey);

	public UserEntity findById(Long id);
}
