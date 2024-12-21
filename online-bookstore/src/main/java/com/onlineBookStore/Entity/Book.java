package com.onlineBookStore.Entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Book {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String title;
	private String author;
	private Double price;
	private Integer stock;

}
