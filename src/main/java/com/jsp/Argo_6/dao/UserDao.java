package com.jsp.Argo_6.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jsp.Argo_6.entity.User;
import com.jsp.Argo_6.repo.UserRepo;

@Repository
public class UserDao {

	@Autowired
	private UserRepo repo;

	public User saveUser(User user) {
		return repo.save(user);

	}

	public User fetchUserbyEmail(String email) {
		return repo.findByEmail(email);
	}

	public User fetchUserById(int id) {
		Optional<User> db = repo.findById(id);
		if (!db.isEmpty()) {
			return db.get();
		} else {
			return null;
		}
	}

		public List<User> fetchAll(){
			
			return repo.findAll();
			
		}
	public User deleteUserById(int id) {
		Optional<User> db = repo.findById(id);
		if (db.isPresent()) {
			repo.deleteById(id);
			return db.get();
		} else {
			return null;
		}
	}

	public User updateUser(User user) {
		Optional<User> db = repo.findById(user.getId());

		if (!db.isEmpty()) {
			User d = db.get();

			if (user.getFirstName() != null)
				d.setFirstName(user.getFirstName());
			if (user.getLastName() != null)
				d.setLastName(user.getLastName());
			if (user.getEmail() != null)
				d.setEmail(user.getEmail());
			if (user.getPassword() != null)
				d.setPassword(user.getPassword());
			if (user.getPhone() != 0)
				d.setPhone(user.getPhone());
			if (user.getAge() != 0)
				d.setAge(user.getAge());

			return repo.save(d);

		} else {
			return null;
		}

	}
	
	

}
