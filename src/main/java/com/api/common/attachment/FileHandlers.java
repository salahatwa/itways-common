package com.api.common.attachment;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.context.ApplicationContext;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import com.api.common.exception.BadRequestException;

import lombok.extern.slf4j.Slf4j;

/**
 * File handler manager.
 *
 * @author ssatwa
 * @date 2019-03-27
 */
@Slf4j
@Component
public class FileHandlers {

	/**
	 * File handler container.
	 */
	private final ConcurrentHashMap<AttachmentType, FileHandler> fileHandlers = new ConcurrentHashMap<>(16);

	public FileHandlers(ApplicationContext applicationContext) {
		// Add all file handler
		addFileHandlers(applicationContext.getBeansOfType(FileHandler.class).values());
		log.info("Registered {} file handler(s)", fileHandlers.size());
	}

	/**
	 * Uploads files.
	 *
	 * @param file           multipart file must not be null
	 * @param attachmentType attachment type must not be null
	 * @return upload result
	 * @throws FileOperationException throws when fail to delete attachment or no
	 *                                available file handler to upload it
	 */
	@NonNull
	public UploadResult upload(@NonNull MultipartFile file, @NonNull AttachmentType attachmentType) {
		return getSupportedType(attachmentType).upload(file);
	}

	/**
	 * Deletes attachment.
	 *
	 * @param attachment attachment detail must not be null
	 * @throws FileOperationException throws when fail to delete attachment or no
	 *                                available file handler to delete it
	 */
	public void delete(AttachmentType type,String key) {
		Assert.notNull(type, "Attachment typemust not be null");
		getSupportedType(type).delete(key);
	}

	/**
	 * Adds file handlers.
	 *
	 * @param fileHandlers file handler collection
	 * @return current file handlers
	 */
	@NonNull
	public FileHandlers addFileHandlers(@Nullable Collection<FileHandler> fileHandlers) {
		if (!CollectionUtils.isEmpty(fileHandlers)) {
			for (FileHandler handler : fileHandlers) {
				if (this.fileHandlers.containsKey(handler.getAttachmentType())) {
					throw new BadRequestException("Same attachment type implements must be unique");
				}
				this.fileHandlers.put(handler.getAttachmentType(), handler);
			}
		}
		return this;
	}

	private FileHandler getSupportedType(AttachmentType type) {
		FileHandler handler = fileHandlers.getOrDefault(type, fileHandlers.get(AttachmentType.LOCAL));
		if (handler == null) {
			throw new BadRequestException("No available file handlers to operate the file");
		}
		return handler;
	}
}
