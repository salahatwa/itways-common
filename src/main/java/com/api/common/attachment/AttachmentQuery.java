package com.api.common.attachment;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

/**
 * Attachment query params.
 *
 * @author ssatwa
 * @date 2019/04/18
 */
@Data
public class AttachmentQuery {

	/**
	 * Keyword.
	 */
	private String keyword;

	private String mediaType;

	private AttachmentType attachmentType;

	@JsonIgnore
	private Long createdBy;
}
