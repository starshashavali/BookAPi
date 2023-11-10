package com.org.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name="Book_Tbl")
@AllArgsConstructor
@NoArgsConstructor
public class Book {
	@Id
	@GeneratedValue
	private Integer bookId;

	private String bookName;

	private Double bookPrice;

}
