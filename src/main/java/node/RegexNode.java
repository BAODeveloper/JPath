package node;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import node.criteria.RegexCriteria;

import org.slf4j.Logger;

import utils.StringUtils;

public class RegexNode extends JNode {

	private static final Logger logger = org.slf4j.LoggerFactory.getLogger(RegexNode.class);

	private String regex;

	public RegexNode(String raw, String path) {
		super(raw, path);
		this.regex = key;
	}

	public void parse() {
		List<String> selected = new ArrayList<String>();
		List<String> filtered = new ArrayList<String>();
		RegexCriteria criteria = new RegexCriteria(this.path);
		if (StringUtils.allEmpty(raw, regex)) {
			selected.add(raw);
			filtered.add(raw);
			logger.warn("RegexNode parse failure, because of" + (StringUtils.isEmpty(raw) ? "raw String is empty" : "regex String is empty"));
		} else {
			try {
				Pattern p = Pattern.compile(this.regex);
				Matcher m = p.matcher(raw);
				while (m.find()) {
					String res = m.group(1);
					selected.add(res);
					if (criteria.isValid(res)) {
						filtered.add(res);
					}
				}
			} catch (Exception e) {
				logger.error("RegexNode parse failed!" + e.getMessage(), e);
			}
		}
		setSelectedResults(selected);
		setFilteredResult(filtered);
	}

	public static void main(String[] args) {
		String raw = "abcd, abccd, abcccd";
		String regex = "ab(.*?)d[1]";
		RegexNode jp = new RegexNode(raw, regex);
		System.out.println(jp.getSelectedResults());
		System.out.println(jp.getFilteredResult());
	}
}
