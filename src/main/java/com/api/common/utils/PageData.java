package com.api.common.utils;

import java.util.List;

import lombok.Data;

@Data
public class PageData<T> {
	private int totalPages;
	private long totalElements;
	private int number;
	private int size;
	private int numberOfElements;

	private List<T> content;
}
