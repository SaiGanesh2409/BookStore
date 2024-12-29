package com.onlineBookStore.dto;



import java.math.BigDecimal;

import lombok.Data;

@Data
public class BookDTO {
	
	private Long id;
	private String title;
	private String author;
	private BigDecimal price;
	private BigDecimal stock;
	
}
