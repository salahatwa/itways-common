package com.api.common.utils;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class PageRq {
	@NotBlank
	private int initPage;

	@NotBlank
	private int pageSize;
}
