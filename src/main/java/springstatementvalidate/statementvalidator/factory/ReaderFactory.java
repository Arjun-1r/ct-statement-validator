package springstatementvalidate.statementvalidator.factory;

import lombok.NonNull;
import springstatementvalidate.statementvalidator.dataextract.CsvReader;
import springstatementvalidate.statementvalidator.dataextract.DataReader;
import springstatementvalidate.statementvalidator.dataextract.XmlReader;
import springstatementvalidate.statementvalidator.util.VerifyFileType;

public class ReaderFactory {
	
public static DataReader getFactory(@NonNull String filename) {
	    DataReader reader = null;
		if(VerifyFileType.isCsvFileType(filename)) {
			reader = new CsvReader();
		} else if(VerifyFileType.isXmlFileType(filename)) {
			reader = new XmlReader();
		}
		return reader;
	}

}
