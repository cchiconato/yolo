package br.com.yolo.user;

import org.springframework.data.jpa.repository.JpaRepository;

/**
* @author  Chrisitan Chiconato
* @since   2018-03-15
*/

public interface UserRepository extends JpaRepository<UserEntity, Long>{
	
	public UserEntity findByUserName(String username);

}

