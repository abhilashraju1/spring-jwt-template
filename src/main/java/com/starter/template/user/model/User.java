package com.starter.template.user.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Cacheable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.starter.template.validation.util.DoesEmailIdExist;
import com.starter.template.validation.util.FieldMatch;

@Entity
@Table(name="USER_DETAILS")
@FieldMatch(first = "password", second = "confirmPassword")
@Cacheable @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class User {
	@Id @GeneratedValue
	private long userId;
	
	@NotEmpty @Pattern(regexp="^[^\\d].*")
	private String firstName;
	
	@NotEmpty @Pattern(regexp="^[^\\d].*")
	private String lastName;
	
	@NotEmpty @DoesEmailIdExist
	@Column(name = "username")
	private String userName;
	
	@NotEmpty @Pattern(regexp="^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$") 
	@Column(name = "password")
	private String password;
	
	@NotEmpty @Pattern(regexp="^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$")
	@Transient 
	private String confirmPassword;
	
	@Email
	@Column(name = "USER_EMAIL_ID")
	private String emailId;
	private String mobileNumber;
	
	@Transient
	private String phoneNumber;
	private char status;
	@Embedded  // this will be implied by default
	@Valid
	private Address address;
	
	@ManyToMany(cascade= {CascadeType.ALL})
	@JoinTable(name="USER_ROLE_MAPPER", joinColumns= {@JoinColumn(name="USER_ID")}, inverseJoinColumns = {@JoinColumn(name="ROLE_ID")})
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	private List<Role> roles = new ArrayList<>(0);
	
	public User() {}
	
	public User(User user) {
		this.firstName = user.firstName;
		this.lastName = user.lastName;
		this.userName = user.userName;
		this.emailId = user.lastName;
		this.mobileNumber = user.mobileNumber;
		this.password = user.password;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}
	
	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
	@Override
	public String toString() {
		return "User [userId=" + userId + ", firstName=" + firstName + ", lastName=" + lastName + ", userName="
				+ userName + ", emailId=" + emailId + ", address=" + address + "]";
	}

	public char getStatus() {
		return status;
	}

	public void setStatus(char status) {
		this.status = status;
	}

}
