package node.criteria;

import java.util.List;

import utils.StringUtils;

public class RegexCriteria {
	private boolean withCount = false;
	private int count = -1;
	private int start = -1;
	private int end = -1;

	public RegexCriteria(String path) {
		List<String> criterias = StringUtils.match(path, "\\[(.*?)\\]");
		for (String criteria : criterias) {
			withCount = true;
			if (criteria.contains("-")) {
				String[] split = criteria.split("-");
				start = Integer.parseInt(split[0]);
				end = Integer.parseInt(split[1]);
			} else {
				end = start = Integer.parseInt(criteria);
			}
		}
	}

	public boolean isValid(String str) {
		count++;
		if (withCount && (count < start || count > end)) {
			return false;
		}
		return true;
	}

}
