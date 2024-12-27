package com.onlineBookStore.Entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Cart {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Long userId;
	private Long bookId;
	private Integer quantity;
}
