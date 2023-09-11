package com.drewdev.libraryms.dao;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import com.drewdev.libraryms.base.AbstractJpaDao;
import com.drewdev.libraryms.base.ConnHandler;
import com.drewdev.libraryms.model.File;
import com.drewdev.libraryms.model.Roles;
import com.drewdev.libraryms.model.Users;

@Repository
public class UsersDao extends AbstractJpaDao  {
	
	private EntityManager em() {
		return ConnHandler.getManager();
	}

	public Users getByEmail(String email) {
		final StringBuilder sql = new StringBuilder();
			sql.append("SELECT ")
			.append(" mu.id, ")
			.append(" mu.user_fullname, ")
			.append(" mu.user_password, ")
			.append(" mu.role_id, ")
			.append(" mu.file_id ")
			.append("FROM ")
			.append(" m_users mu ")
			.append("WHERE ")
			.append(" mu.user_email = :email");
		
		final Object userObj = em().createNativeQuery(sql.toString()).setParameter("email", email).getSingleResult();
		
		final Object[] userArr = (Object[]) userObj;
		Users user = null;
		
		if (userArr.length>0) {
			user = new Users();
			user.setId(userArr[0].toString());
			user.setUserFullname(userArr[1].toString());
			user.setUserPassword(userArr[2].toString());
			
			final Roles role = new Roles();
			role.setId(userArr[3].toString());
			user.setRole(role);
			
			final File file = new File();
			file.setId(userArr[4].toString());
			user.setFile(file);
		}
		
		return user;
	}
	
}
