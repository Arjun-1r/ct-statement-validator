package springstatementvalidate.statementvalidator.util;

import java.util.regex.Pattern;

import springstatementvalidate.statementvalidator.exception.ValidationException;

public class DataValidatorUtl {

	private static final Pattern TRANSACTION_REF_PATTERN = Pattern.compile("\\d+");
	private static final Pattern ACCOUNT_NUMBER_PATTERN = Pattern.compile("[A-Z]{2}[0-9]{2}[A-Z]{4}[0-9]{10}");
	
	public static Long validateTransacNumber(String ref) {
		if(TRANSACTION_REF_PATTERN.matcher(ref).matches()) {
			return Long.parseLong(ref);
		} else {
			throw new ValidationException(String.format("Transaction Reference '%s' is not valid. Should only contains numeric value.", ref));
		}
	}
	
	public static String validateAndGetAccountNumber(String accountNumber) {
		if(ACCOUNT_NUMBER_PATTERN.matcher(accountNumber).matches()) {
			return accountNumber;
		} else {
			throw new ValidationException(String.format("Account Number '%s' is not valid. Should have this [A-Z]{2}[0-9]{2}[A-Z]{4}[0-9]{10} format.", accountNumber));
		}
	}

	public static Double getMutationValue(String mutationValue) {
		return Double.valueOf(mutationValue);
	}
}
