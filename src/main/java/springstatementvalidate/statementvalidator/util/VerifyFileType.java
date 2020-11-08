package springstatementvalidate.statementvalidator.util;

public class VerifyFileType {
	
	enum Level {
	    CSV,
	    XML
	  }
	
	public static boolean isCorrectFileType(String fileName) {
		return isCsvFileType(fileName) || isXmlFileType(fileName);
	}
	
	public static boolean isCsvFileType(String fileName) {
		Level lvl = Level.CSV;
		return fileName.toUpperCase().endsWith(lvl.name());
	}
	
	public static boolean isXmlFileType(String fileName) {
		Level lvl = Level.XML;
		return fileName.toUpperCase().endsWith(lvl.name());
	}

}
