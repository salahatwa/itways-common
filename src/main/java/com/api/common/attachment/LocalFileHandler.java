package com.api.common.attachment;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.web.multipart.MultipartFile;

import com.api.common.ApiProperties;
import com.api.common.exception.BadRequestException;
import com.api.common.utils.ImageUtils;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import net.coobird.thumbnailator.Thumbnails;

/**
 * Local file handler.
 *
 * @author ssatwa
 * @date 2019-03-27
 */
@Slf4j
@Component
public class LocalFileHandler implements FileHandler {

	/**
	 * Upload sub directory.
	 */
	private final static String UPLOAD_SUB_DIR = "upload/";

	private final static String THUMBNAIL_SUFFIX = "-thumbnail";

	/**
	 * Thumbnail width.
	 */
	private final static int THUMB_WIDTH = 256;

	/**
	 * Thumbnail height.
	 */
	private final static int THUMB_HEIGHT = 256;

	private String workDir = "";

	@Autowired
	private ApiProperties haloProperties;

	/**
	 * Check work directory.
	 */
	private void checkWorkDir() {
		// Get work path
		Path workPath = Paths.get(workDir);

		File file = new File(workDir);
		if (!file.exists())
			file.mkdirs();

		// Check file type
		Assert.isTrue(Files.isDirectory(workPath), workDir + " isn't a directory");

		// Check readable
		Assert.isTrue(Files.isReadable(workPath), workDir + " isn't readable");

		// Check writable
		Assert.isTrue(Files.isWritable(workPath), workDir + " isn't writable");
	}

	private File convertMultiPartToFile(MultipartFile file) throws IOException {
		File convFile = new File(file.getOriginalFilename());
		FileOutputStream fos = new FileOutputStream(convFile);
		fos.write(file.getBytes());
		fos.close();
		return convFile;
	}

	@Override
	public UploadResult upload(MultipartFile file) {

		Assert.notNull(file, "Multipart file must not be null");

		// Get work dir
		workDir = FileHandler.normalizeDirectory(haloProperties.getWorkDir());

		// Check work directory
		checkWorkDir();

		// Build directory

		String originalBasename = FilenameUtils.getBasename(Objects.requireNonNull(file.getOriginalFilename()));

		// Get basename
		String basename = originalBasename + '-' + randomUUIDWithoutDash();

		// Get extension
		String extension = FilenameUtils.getExtension(file.getOriginalFilename());

		log.debug("Base name: [{}], extension: [{}] of original filename: [{}]", basename, extension,
				file.getOriginalFilename());

		// Build sub file path
		String subFilePath = UPLOAD_SUB_DIR + basename + '.' + extension;

		// Get upload path
		Path uploadPath = Paths.get(workDir, subFilePath);

		log.info("Uploading file: [{}]to directory: [{}]", file.getOriginalFilename(), uploadPath.toString());

		try {
			// TODO Synchronize here
			// Create directory
			Files.createDirectories(uploadPath.getParent());
			Files.createFile(uploadPath);

			// Upload this file
			file.transferTo(uploadPath);

			// Build upload result
			UploadResult uploadResult = new UploadResult();
			uploadResult.setFilename(originalBasename);
			uploadResult.setFilePath(haloProperties.getImageHost() + subFilePath);
			uploadResult.setKey(subFilePath);
			uploadResult.setSuffix(extension);
			uploadResult.setMediaType(MediaType.valueOf(Objects.requireNonNull(file.getContentType())));
			uploadResult.setSize(file.getSize());

			// TODO refactor this: if image is svg ext. extension
			handleImageMetadata(file, uploadResult, () -> {
				// Upload a thumbnail
				final String thumbnailBasename = basename + THUMBNAIL_SUFFIX;
				final String thumbnailSubFilePath = UPLOAD_SUB_DIR + thumbnailBasename + '.' + extension;
				final Path thumbnailPath = Paths.get(workDir + thumbnailSubFilePath);
				try (InputStream is = file.getInputStream()) {
					// Generate thumbnail
					BufferedImage originalImage = ImageUtils.getImageFromFile(is, extension);
					boolean result = generateThumbnail(originalImage, thumbnailPath, extension);
					if (result) {
						// Set thumb path
						return haloProperties.getImageHost() + thumbnailSubFilePath;
					}
				} catch (IOException e) {
					log.warn("Failed to open image file.", e);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return haloProperties.getImageHost() + subFilePath;
			});

			log.info("Uploaded file: [{}] to directory: [{}] successfully", file.getOriginalFilename(),
					uploadPath.toString());
			return uploadResult;
		} catch (Exception e) {
			throw new BadRequestException("Failed to upload attachment");
		}
	}

	/**
	 * Gets random uuid without dash.
	 *
	 * @return random uuid without dash
	 */
	@NonNull
	public static String randomUUIDWithoutDash() {
		return StringUtils.remove(UUID.randomUUID().toString(), '-');
	}

	@Override
	public void delete(String key) {
		Assert.hasText(key, "File key must not be blank");
		// Get path
		Path path = Paths.get(workDir, key);

		// Delete the file key
		try {
			Files.deleteIfExists(path);
		} catch (IOException e) {
			throw new BadRequestException("appendix " + key + " failed to delete", e);
		}

		// Delete thumb if necessary
		String basename = FilenameUtils.getBasename(key);
		String extension = FilenameUtils.getExtension(key);

		// Get thumbnail name
		String thumbnailName = basename + THUMBNAIL_SUFFIX + '.' + extension;

		// Get thumbnail path
		Path thumbnailPath = Paths.get(path.getParent().toString(), thumbnailName);

		// Delete thumbnail file
		try {
			boolean deleteResult = Files.deleteIfExists(thumbnailPath);
			if (!deleteResult) {
				log.warn("Thumbnail: [{}] may not exist", thumbnailPath.toString());
			}
		} catch (IOException e) {
			throw new BadRequestException("Attachment thumbnail " + thumbnailName + " failed to delete");
		}
	}

	@Override
	public AttachmentType getAttachmentType() {
		return AttachmentType.LOCAL;
	}

	private boolean generateThumbnail(BufferedImage originalImage, Path thumbPath, String extension) {
		Assert.notNull(originalImage, "Image must not be null");
		Assert.notNull(thumbPath, "Thumb path must not be null");

		boolean result = false;
		// Create the thumbnail
		try {
			Files.createFile(thumbPath);
			// Convert to thumbnail and copy the thumbnail
			log.debug("Trying to generate thumbnail: [{}]", thumbPath.toString());
			Thumbnails.of(originalImage).size(THUMB_WIDTH, THUMB_HEIGHT).keepAspectRatio(true)
					.toFile(thumbPath.toFile());
			log.info("Generated thumbnail image, and wrote the thumbnail to [{}]", thumbPath.toString());
			result = true;
		} catch (Throwable t) {
			// Ignore the error
			log.warn("Failed to generate thumbnail: " + thumbPath, t);
		} finally {
			// Disposes of this graphics context and releases any system resources that it
			// is using.
			originalImage.getGraphics().dispose();
		}
		return result;
	}
}
