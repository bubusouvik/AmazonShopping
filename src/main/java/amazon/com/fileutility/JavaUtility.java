package amazon.com.fileutility;

import java.util.Date;
import java.util.Random;

public class JavaUtility {

	public int generateRandomNumber() {

		Random random = new Random();
		int number = random.nextInt(5000);
		return number;
	}

	public String getSystemDate() {

		Date D = new Date();
		String time = D.toString().replace(" ", "_").replace(":", " ");
		return time;
	}
}
