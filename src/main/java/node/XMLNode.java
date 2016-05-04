package node;

import java.util.ArrayList;
import java.util.List;

import node.criteria.XmlCriteria;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;
import org.slf4j.Logger;

import utils.ResourceUtils;
import utils.StringUtils;

/**
 * 支持 [@attr=bookstore], [@id=id] ,[@attrName=attrValue], [@attr], [1], [1-3]
 * 
 * @author BAO
 * 
 */
public class XMLNode extends JNode {

	private static final Logger logger = org.slf4j.LoggerFactory.getLogger(XMLNode.class);

	public XMLNode(String raw, String path) {
		super(raw, path);
	}

	public void parse() {
		List<String> selected = new ArrayList<String>();
		List<String> filtered = new ArrayList<String>();
		if (StringUtils.allEmpty(key, raw)) {
			logger.warn("XMLNode parse failed because of" + (StringUtils.isEmpty(raw) ? "raw String is empty" : "regex String is empty"));
			selected.add(raw);
			filtered.add(raw);
		} else {
			Document doc = Jsoup.parse(raw);
			XmlCriteria criteria = new XmlCriteria(this.path);

			if (key.startsWith("*")) {
				for (Node node : doc.body().childNodes()) {
					selected.add(node.toString());
					if (criteria.isValid(node)) {
						filtered.add(node.toString());
					}
				}
			} else {

				Elements elements = doc.select(key);
				for (Element element : elements) {
					selected.add(element.toString());
					if (criteria.isValid(element)) {
						filtered.add(element.toString());
					}
				}
			}
		}
		setSelectedResults(selected);
		setFilteredResult(filtered);
	}

	public static void main(String[] args) {
		String raw = ResourceUtils.readResouce("test.xml");
		String key = "bookstore[@name][0-1]";
		XMLNode j = new XMLNode(raw, key);
		System.out.println(j.getSelectedResults());
		System.out.println(j.getFilteredResult());
	}
}
