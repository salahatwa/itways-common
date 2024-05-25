package com.api.common.utils;

public interface Constants {
	public final static String PRODUCT = "LE";
	public final static String SEC_PRODUCT_CODE = "LSH";

	public final static String DATE_DEFAULT = "0001-01-01";

	public static final String MAX_RECORDS = "50";
	public static final String OFFSET = "0";
	public static final int PAGE_SIZE = 10;
	public static final int PAGE_SIZE_15 = 15;
	public static final String T24_SYS_ID = "T24";
	public static final String SEPRATOR = "_";
	public static final String YES = "Y";
	public static final String NO = "N";
	public static final String DEFAULT_MAX_RECORDS = "100000";
	public static final String DEFAULT_OFFSET = "1";

	public final static String OVERRIDE_DATA_RESUBMISSION = "W001534";
	public final static String NOT_SYNCHRONIZED_T24 = "W001540";
	public final static String OVERRIDE_NO_DATA_RESUBMISSION = "W001535";

	public final static String STATUS_CODE_RECORD_NOT_FOUND = "E001502";

	public final static String[] WARNING_CODES = { "W001194", "W700125", NOT_SYNCHRONIZED_T24 };
	public final static String[] APPROVAL_CODES = { OVERRIDE_DATA_RESUBMISSION, OVERRIDE_NO_DATA_RESUBMISSION };
	/**
	 * Status Codes
	 */
	public final static String STATUS_CODE_SUCCESS = "I000000";
	// E000000 : temp value till set the right value
	public final static String STATUS_CODE_INVALID_RESPONSE = "E000000";
	public final static String STATUS_CODE_TIMEOUT_RESPONSE = "E010222";
	public final static String STATUS_CODE_NOT_FOUND = "E000003";
	public final static String STATUS_CODE_ACCESS_DENIED = "E000004";
	/*
	 * message keys
	 */
	public final static String VARIABLE_REQUIRED_MSG = "variable.required";
	public final static String PARAM_REQUIRED_MSG = "param.required";
//	public final static String INVALID_FURTURE_DT = "variable.required";
//	public final static String PARAM_REQUIRED_MSG = "param.required";

	public final static String INVALID_DATA = "data.invalid";
	public final static String UN_EXPECTED_ERROR = "error.unexpected";

	public final static String CURRENCY_SAR = "SAR";

	// Validation Modes
	public final static String HIJRI = "HIJRI";
	public final static String GREGORGIAN = "GREGORIAN";

	/**
	 * LOCAL
	 */
	public final static String LOCAL_AR = "ar";
	public final static String LOCAL_EN = "en";

	// lov type codes attributes
	public final static String LOV_CODE1 = "lovCode1";
	public final static String LOV_CODE2 = "lovCode2";
	public final static String LOV_CODE3 = "lovCode3";
	public final static String LOV_CODE4 = "lovCode4";

	public static final String KSA_COUNTRY_CODE = "SA";
	public static final String KSA_PHONE_COUNTRY_CODE = "966";
	public static final String USA_COUNTRY_CODE = "US";

	public static final String DATE_FORMAT = "yyyy-MM-dd";

	// Date formats
	String YYYY_MM_DD_T_HH_MM_SS_Z = "yyyy-MM-dd'T'HH:mm:ss'Z'";
	String YYYY_MM_DD = "yyyy-MM-dd";

	String CHAR_ENCODE_UTF_8 = "UTF-8";
	String ENCRYPT_TYPE_AES = "AES";
	String ENCRYPT_PADDING_TYPE = "AES/CBC/PKCS5PADDING";

}
