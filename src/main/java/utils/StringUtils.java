package utils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;

public class StringUtils {
	private static final Logger logger = org.slf4j.LoggerFactory.getLogger(StringUtils.class);

	public static boolean isEmpty(String str) {
		return str == null || str.equals("");
	}

	public static boolean allEmpty(String... strs) {
		for (int i = 0; i < strs.length; i++) {
			if (!isEmpty(strs[i])) {
				return false;
			}
		}
		return true;
	}

	public static List<String> match(String raw, String regex) {
		List<String> ans = new ArrayList<String>();
		try {
			Pattern p = Pattern.compile(regex);
			Matcher m = p.matcher(raw);
			while (m.find()) {
				String res = m.group(1);
				ans.add(res);
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return ans;
	}
	
}
