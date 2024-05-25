package com.api.common.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

public final class PaginationUtil {
	private PaginationUtil() {
	}

	public static <T> PageData<T> paginateList(final Pageable pageable, List<T> list) {
		PageData<T> data = new PageData<T>();

		if (Objects.isNull(list) || list.size() <= 0) {
			data.setNumber(0);
			data.setNumberOfElements(0);
			data.setSize(0);
			data.setTotalElements(0);
			data.setTotalPages(0);
			data.setContent(new ArrayList<T>());

			return data;
		}

		int first = Math.min(new Long(pageable.getOffset()).intValue(), list.size());
		int last = Math.min(first + pageable.getPageSize(), list.size());
		Page<T> page = new PageImpl<>(list.subList(first, last), pageable, list.size());

		data.setNumber(page.getNumber());
		data.setNumberOfElements(page.getNumberOfElements());
		data.setSize(page.getSize());
		data.setTotalElements(page.getTotalElements());
		data.setTotalPages(page.getTotalPages());
		data.setContent(page.getContent());
		return data;
	}
}