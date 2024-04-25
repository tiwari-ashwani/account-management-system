package demo.accountmanagementsystem.utils;

import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ApplicationUtility {

	private static final Logger logger = LoggerFactory.getLogger(ApplicationUtility.class);

	public static Boolean validateIbanNumber(String iban) {

		Boolean res = true;
		String ibanRegex = "^([A-Z]{2}[ '+'\\\\'+'-]?[0-9]{2})(?=(?:[ '+'\\\\'+'-]?[A-Z0-9]){9,30}$)((?:[ '+'\\\\'+'-]?[A-Z0-9]{3,5}){2,7})([ '+'\\\\'+'-]?[A-Z0-9]{1,3})?$";
		Pattern MyPattern = Pattern.compile(ibanRegex);
		if (iban != null) {
			Matcher MyMatcher = MyPattern.matcher(iban);
			if (!MyMatcher.matches()) {

				res = false;
			}

		}
		return res;
	}

	public static String generateUniqueIban() {
		StringBuilder iban = new StringBuilder();
		iban.append("NL");
		iban.append(generateRandomNumber(10, 99));
		iban.append("TEST");
		iban.append(generateRandomNumber(1000000000, 9999999999L));
		return iban.toString();
	}

	private static long generateRandomNumber(long min, long max) {
		Random random = new Random();
		return min + random.nextInt((int) (max - min));
	}

}