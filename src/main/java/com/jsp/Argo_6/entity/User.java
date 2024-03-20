package com.jsp.Argo_6.entity;

import java.util.List;

import com.jsp.Argo_6.enums.UserType;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@NotBlank(message = "user name can't be null")
	@NotNull(message = "firstname can't be null")
	private String firstName;
	@NotBlank(message = "user name can't be null")
	@NotNull(message = "lastname can't be null")
	private String lastName;
	@Column(unique = true)
	@Email(regexp = "[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+\\.[a-z]{2,}", message = "invalid email ")
	private String email;
	@NotBlank(message = "Password is required")
	@Size(min = 6, max = 20, message = "Password must be between 6 and 20 characters")
	@Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$", message = "must contain at least one letter, one number, one special character")
	private String password;
	private int age;
	@Min(value = 6000000000l, message = " phone number must be valid")
	@Max(value = 9999999999l, message = " phone number must be valid")
	private long phone;
	@Enumerated(EnumType.STRING)
	private UserType type;
	@OneToOne(cascade = CascadeType.ALL)
	private Address address;

	@OneToMany(cascade = CascadeType.ALL)
	private List<Post> posts;
	@OneToOne(cascade = CascadeType.ALL)
	private Image image;

}
