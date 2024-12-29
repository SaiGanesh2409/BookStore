package com.onlineBookStore.Entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="book")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Book {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;
	
	@Column(name="title", nullable = false,unique = true)
	private String title;
	
	@Column(name="author",nullable = false)
	private String author;
	
	@Column(name="price",nullable = false)
	private BigDecimal price;
	
	@Column(name="stock",nullable = false)
	private BigDecimal stock;
	
	@Column(name="created_at", updatable = false)
	private LocalDateTime createdAt;
	
	@Column(name="updated_at")
	private LocalDateTime updatedAt;
	
	@PrePersist
	public void prePersist()
	{
		this.createdAt=this.updatedAt=LocalDateTime.now();
	}
	
	@PreUpdate
	public void preUpdate()
	{
		this.updatedAt=LocalDateTime.now();
	}
	
	
	
	

}
