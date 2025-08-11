package amazon.com.genericutility;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertyFile {

	public String getDataFromPropertyFile(String key) throws IOException {
		FileInputStream fis = new FileInputStream("./testappdata/propertyfile.properties");
		Properties pfile = new Properties();
		pfile.load(fis);
		String data = pfile.getProperty(key);
		return data;
	}

}
