package utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.slf4j.Logger;

public class ResourceUtils {
	private static final Logger logger = org.slf4j.LoggerFactory.getLogger(ResourceUtils.class);

	public static String readResouce(String filename) {
		StringBuilder sb = new StringBuilder();
		if (!filename.startsWith("/")) {
			filename = "/" + filename;
		}
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(ResourceUtils.class.getResourceAsStream(filename)));

			String tmp;
			while ((tmp = in.readLine()) != null) {
				sb.append(tmp);
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return sb.toString();
	}

}
