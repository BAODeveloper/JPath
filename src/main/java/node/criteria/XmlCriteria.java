package node.criteria;

import java.util.List;

import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;

import utils.StringUtils;

public class XmlCriteria {

	private boolean withAttr = false;
	private String attrName = "";
	private boolean withValue = false;
	private String attrValue = "";
	private boolean withCount = false;
	private int count = -1;
	private int start = -1;
	private int end = -1;

	public XmlCriteria(String path) {
		List<String> criterias = StringUtils.match(path, "\\[(.*?)\\]");
		for (String criteria : criterias) {
			if (criteria.startsWith("@")) {
				withAttr = true;
				if (criteria.contains("=")) {
					withValue = true;
					attrName = criteria.substring(1, criteria.indexOf("="));
					attrValue = criteria.substring(criteria.indexOf("=") + 1);
				} else {
					attrName = criteria.substring(1);
				}
			} else {// [1] [1-3]
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
	}

	public boolean isValid(Node node) {
		count++;
		if (withCount && (count < start || count > end)) {
			return false;
		}

		if (withAttr && !node.hasAttr(attrName)) {
			return false;
		}

		if (withValue && !node.attr(attrName).equals(attrValue)) {
			return false;
		}
		return true;
	}

	public boolean isValid(Element node) {
		count++;
		if (withCount && (count < start || count > end)) {
			return false;
		}
		
		if (withAttr && !node.hasAttr(attrName)) {
			return false;
		}

		if (withValue && !node.attr(attrName).equals(attrValue)) {
			return false;
		}
		return true;
	}
}
