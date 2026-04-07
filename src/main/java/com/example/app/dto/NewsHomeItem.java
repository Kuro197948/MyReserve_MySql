package com.example.app.dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class NewsHomeItem {
	private Integer id;
	private String title;
	private String author;
	private LocalDate postDate;
	private String article;
}
