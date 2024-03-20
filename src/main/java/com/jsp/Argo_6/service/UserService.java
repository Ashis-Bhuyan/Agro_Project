package com.jsp.Argo_6.service;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.jsp.Argo_6.dao.UserDao;
import com.jsp.Argo_6.entity.User;

import com.jsp.Argo_6.exception.EmailNotSendException;

import com.jsp.Argo_6.exception.UserNotFoundException;
import com.jsp.Argo_6.util.ResponseStructure;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class UserService {
	@Autowired
	private UserDao dao;

	@Autowired
	private JavaMailSender javaMailSender;

	public String sendSimpleMail(User user) {
		try {
			SimpleMailMessage mailMessage = new SimpleMailMessage();
			mailMessage.setFrom("argo0624@gmail.com");
			mailMessage.setTo(user.getEmail());
			mailMessage.setSubject("Successfully Registered !");
			mailMessage.setText("Thankyou For Your Registration!" + user);
			javaMailSender.send(mailMessage);
			return "Registration Successfully...";
		} catch (Exception e) {
			return "Error while Sending Mail";
		}
	}

	public ResponseEntity<ResponseStructure<User>> saveUser(User user) {
		ResponseStructure<User> m = new ResponseStructure<User>();
		User db = dao.saveUser(user);
		m.setData(db);
		m.setMessage("User Registered Successfully !");
		m.setStatus(HttpStatus.CREATED.value());
		System.out.println(sendSimpleMail(user));
		return new ResponseEntity<ResponseStructure<User>>(m, HttpStatus.CREATED);
	}

	public ResponseEntity<ResponseStructure<User>> loginStudent(String email, String password) {
		User db = dao.fetchUserbyEmail(email);
		if (db != null) {
			ResponseStructure<User> m = new ResponseStructure<User>();
			if (db.getPassword().equals(password)) {
				m.setData(db);
				m.setMessage("Login Successfull!");
				m.setStatus(HttpStatus.ACCEPTED.value());
				return new ResponseEntity<ResponseStructure<User>>(m, HttpStatus.ACCEPTED);
			} else {
				m.setData(null);
				m.setMessage("Password is Wrong!");
				m.setStatus(HttpStatus.NOT_ACCEPTABLE.value());
				return new ResponseEntity<ResponseStructure<User>>(m, HttpStatus.NOT_ACCEPTABLE);
			}
		} else {
			throw new EmailNotSendException("User Not Found with E-mail = " + email);
		}

	}
	
	public ResponseEntity<ResponseStructure<User>> fetchUserById(int id) {
		User db = dao.fetchUserById(id);
		if (db != null) {

			ResponseStructure<User> m = new ResponseStructure<User>();
			m.setData(db);
			m.setMessage("User found successfully......!");
			m.setStatus(HttpStatus.FOUND.value());
			return new ResponseEntity<ResponseStructure<User>>(m, HttpStatus.FOUND);
		} else {
			throw new EmailNotSendException("movie not found for this id = " + id);
		}

	}
	

	public ResponseEntity<ResponseStructure<User>> deleteUserById(int id) {
		User db = dao.deleteUserById(id);
		if (db != null) {

			ResponseStructure<User> m = new ResponseStructure<User>();
			m.setData(db);
			m.setMessage("User deleted deleted successfully......!");
			m.setStatus(HttpStatus.FOUND.value());
			return new ResponseEntity<ResponseStructure<User>>(m, HttpStatus.FOUND);
		} else {
			throw new EmailNotSendException("movie not found for this id = " + id);
		}

	}

	public ResponseEntity<ResponseStructure<User>> updateUser(User user) {

		User s = dao.fetchUserById(user.getId());
		if (s != null) {
			ResponseStructure<User> m = new ResponseStructure<User>();
			m.setData(dao.updateUser(user));
			m.setMessage("Movie updated successfully......!");
			m.setStatus(HttpStatus.FOUND.value());
			return new ResponseEntity<ResponseStructure<User>>(m, HttpStatus.FOUND);
		} else {
			throw new EmailNotSendException("movie not found for this id = " + user.getId());
		}

	}

	public List<User> getAllUsers(){
	
		return dao.fetchAll();
	}

	public ResponseEntity<ResponseStructure<Integer>> sendOtp(String email) {
		User db = dao.fetchUserbyEmail(email);
		if (db != null) {
			Random random = new Random();
			int otp = 100000 + random.nextInt(900000);
			ResponseStructure<Integer> rs = new ResponseStructure<Integer>();
			try {
				MimeMessage message = javaMailSender.createMimeMessage();
				MimeMessageHelper helper = new MimeMessageHelper(message, true);
				helper.setFrom("argo0624@gmail.com");
				helper.setTo(email);
				helper.setSubject("Forgot Password OTP Verification!");
				String emailContent =  db.getFirstName() + " " + db.getLastName()+" "+"your new otp for reset password is"+ ":"+otp;
						
				helper.setText(emailContent, true);
//			ClassPathResource banner = new ClassPathResource();
//				helper.addInline("fpBanner", banner);
				javaMailSender.send(message);
				
				rs.setData(otp);
				rs.setMessage("OTP Sent Successfully");
				rs.setStatus(HttpStatus.OK.value());
				return new ResponseEntity<ResponseStructure<Integer>>(rs, HttpStatus.OK);
			} catch (MessagingException e) {
				System.out.println(e);
				throw new EmailNotSendException("Failed to Send the Otp Mail!");
			}
		}
		throw new UserNotFoundException(email+" "+"this email is not available");
	}
}
