package com.api.common.utils;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidationPatterns {

	public final static String hijriDate = "^(([1][1-6][0-9][0-9])-((0[1-9])|(1[0-2]))-((0[0-9])|(1[0-9])|(2[0-9])|(3[0])))*$";
	public final static String gregorgianDate = "^((((19)|([2][0-2]))[0-9][0-9])-((0[1-9])|(1[0-2]))-((0[0-9])|(1[0-9])|(2[0-9])|(3[0-1])))*$";
	public final static String phoneNo = "^([\\d]{1,10})*$";
	public final static String phoneExt = "^([\\d]{1,10})*$";
	public final static String phoneAreaCode = "^([\\d]{1,5})*$";
	public final static String MobileNumber = "^([\\d]{9,13})*$";
	public final static String saudiMobileNumberWitoutCountryCode = "^((5)[\\d]{8})*$";

	public final static String email = "^([\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4})*$";
	public final static String postalCode = "^([1-9][\\d]{0,4})*$";
	public final static String poBox = "^([1-9]\\d{0,6})*$";
	public final static String basicNo = "^([1-9][\\d]{0,3})*$";
	public final static String unitNo = "^([1-9][\\d]{0,2})*$";
	public final static String additionalNo = "^([1-9][\\d]{0,3})*$";
	public final static String idNo = "^[a-zA-Z0-9 ]*$";
	public final static String saudiMobileNumber = "^((9665)[\\d]{8})*$";
	public final static String freeText = "^(([a-zA-Z0-9_@\\s\\,\\.\\-])*|([(\\u0621-\\u064A)|(0-9)|(\\s)|(\\.)|(\\-)])*)$";
	public final static String alphaNumeric = "^[(a-zA-Z)|(\\u0621-\\u064A)|(0-9)*|(_)|(\\s)|(\\.)|(\\-)]*$";

	public final static String onlyEnglish = "^[a-zA-Z ]*$";
	public final static String onlyEnglishAndNum = "^[a-zA-Z0-9]*$";
	public final static String onlyArabic = "^[\\u0621-\\u064A ]*$";
	public final static String onlyArabicAndNum = "^[\\u0621-\\u064A0-9 ]*$";
	public final static String iban = "^([A-Z]{2}\\d{22})*$";
	public final static String saudiIban = "^((SA)[0-9]{2}[A-Z0-9]{18}[0-9]{2})*$";
	public final static String digits = "^[\\d]*$";
	public final static String incomeSourceAmount = "^(\\d{1,7}(\\.\\d{1,2})?)*$";
	public final static String numberWithDotFormatUnlimited = "^(\\d+(\\.\\d+)?)*$";
	public final static String numberWithTwoDecimalPoints = "^(\\d+(\\.\\d{1,2})?)*$";
	public final static String nonZerosString = "^((?![0]).+)*$";
    public final static String fullNameFourParts="^((^[A-Za-z\\u0621-\\u064A]{1,16})([ ]{1})([A-Za-z\\u0621-\\u064A]{1,16})([ ]{1})([A-Za-z\\u0621-\\u064A]{1,16})([ ]{1})([A-Za-z\\u0621-\\u064A]{1,16}))*$";

	// for testing patterns
	public static void main(String[] args) {

		System.out.println("....... testing regex pattern");
		Pattern pattern = Pattern.compile(nonZerosString);
		String str = "";
		Matcher matcher = pattern.matcher(str);
		boolean matched = matcher.find();
		if (matched) {
			System.out.println(str +" ....... Text Matched");
		} else {
			System.out.println("....... Text Not Matched");
		}

	}

	public static boolean isMatchedPattern(String patternStr, String value) {
		if (Objects.isNull(patternStr) || Objects.isNull(value)) {
			return false;
		}
		Pattern pattern = Pattern.compile(patternStr);
		Matcher matcher = pattern.matcher(value);
		return matcher.find();
	}
}
