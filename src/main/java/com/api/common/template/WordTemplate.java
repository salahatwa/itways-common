//package com.api.common.template;
//
//import java.io.ByteArrayInputStream;
//import java.io.ByteArrayOutputStream;
//import java.io.File;
//import java.io.FileInputStream;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import org.apache.pdfbox.multipdf.PDFMergerUtility;
//
//import com.aspose.words.Document;
//import com.aspose.words.License;
//import com.aspose.words.SaveFormat;
//import com.deepoove.poi.XWPFTemplate;
//import com.deepoove.poi.config.Configure;
//import com.deepoove.poi.config.ConfigureBuilder;
//import com.deepoove.poi.plugin.table.LoopRowTableRenderPolicy;
//import com.deepoove.poi.util.PoitlIOUtils;
//import com.fasterxml.jackson.core.type.TypeReference;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.fasterxml.jackson.databind.SerializationFeature;
//
//import lombok.extern.slf4j.Slf4j;
//
////@Service
//@Slf4j
//public class WordTemplate {
//	private ObjectMapper objectMapper = new ObjectMapper();
//	private PDFMergerUtility pdfMerger;
//	private ConfigureBuilder wordConfig;
//	private Map<String, Object> map = new HashMap<>();
//
//	/**
//	 * bigdirs dirs
//	 * 
//	 */
//	public void customWordConfigRowTable(String listName) {
//		wordConfig = wordConfig.bind(listName, new LoopRowTableRenderPolicy());
//	}
//
//	public WordTemplate(Object data) {
//		wordConfig = Configure.builder().useSpringEL();
//		pdfMerger = new PDFMergerUtility();
//		objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
//		this.map = objectMapper.convertValue(data, new TypeReference<Map<String, Object>>() {
//		});
//	}
//
//	public void prepareDoc(List<String> paths) {
//		try {
//			log.info("Data:{}", map);
//
//			for (String template : paths) {
//				ByteArrayOutputStream out = new ByteArrayOutputStream();
//
//				if (template.endsWith("pdf")) {
//					pdfMerger.addSource(new FileInputStream(new File(template)));
//					continue;
//				} else if (template.endsWith("docx")) {
//					XWPFTemplate.compile(template, wordConfig.build()).render(map).write(out);
//
//					out.flush();
//					PoitlIOUtils.closeQuietlyMulti(out);
//					System.out.println("Done word file, start convert to pdf");
//
//					ByteArrayOutputStream pdfout = new ByteArrayOutputStream();
//
//					String s = "<License><Data><Products><Product>Aspose.Total for Java</Product><Product>Aspose.Words for Java</Product></Products><EditionType>Enterprise</EditionType><SubscriptionExpiry>20991231</SubscriptionExpiry><LicenseExpiry>20991231</LicenseExpiry><SerialNumber>8bfe198c-7f0c-4ef8-8ff0-acc3237bf0d7</SerialNumber></Data><Signature>sNLLKGMUdF0r8O1kKilWAGdgfs2BvJb/2Xp8p5iuDVfZXmhppo+d0Ran1P9TKdjV4ABwAgKXxJ3jcQTqE/2IRfqwnPf8itN8aFZlV3TJPYeD3yWE7IT55Gz6EijUpC7aKeoohTb4w2fpox58wWoF3SNp6sK6jDfiAUGEHYJ9pjU=</Signature></License>";
//					ByteArrayInputStream is = new ByteArrayInputStream(s.getBytes());
//					License license = new License();
//					license.setLicense(is);
//
//					Document doc = new Document(new ByteArrayInputStream(out.toByteArray())); // Address是将要被转化的word文档
//					doc.save(pdfout, SaveFormat.PDF);
//
//					pdfout.close();
//					pdfout.flush();
//
//					System.out.println("<<FILE CONVERTED SUCCESSFULLY>>");
//					pdfMerger.addSource(new ByteArrayInputStream(pdfout.toByteArray()));
//				}
//			}
//
//			pdfMerger.setDestinationFileName("test22.pdf");
////			pdfMerger.
//			pdfMerger.mergeDocuments();
////			convertToPdf.shutDown();
//
//			log.info("Success File");
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//
//}
