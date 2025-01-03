package com.onlineBookStore.Entity;

import java.util.Date;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "login_history")
@Data
public class LoginHistory {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
	private User user;

	@Column(name = "action", nullable = false)
	private String action; // "LOGIN" or "LOGOUT"

	@Column(name = "role", nullable = false)
	private String role; // Role of the user at the time of login

	@Column(name = "timestamp", nullable = false)
	private Date timestamp; // Timestamp for login or logout event

}
