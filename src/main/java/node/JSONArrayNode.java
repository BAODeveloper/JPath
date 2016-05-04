package node;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;

import utils.StringUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

public class JSONArrayNode extends JNode {

	private static final Logger logger = org.slf4j.LoggerFactory.getLogger(JSONArrayNode.class);

	public JSONArrayNode(String raw, String path) {
		super(raw, path);
	}

	public void parse() {

		List<String> selected = new ArrayList<String>();
		List<String> filtered = new ArrayList<String>();

		if (StringUtils.allEmpty(key, raw)) {
			logger.warn("JSONArrayNode parse failed because of" + (StringUtils.isEmpty(raw) ? "raw String is empty" : "regex String is empty"));
			selected.add(raw);
			filtered.add(raw);
		} else {
			try {
				JSONObject jsonObject = JSON.parseObject(raw);
				String s = jsonObject.getString(key);
				if (s != null) {
					filtered.add(s);
					selected.add(s);
					logger.warn("JSONArrayNode no such key");
				}
			} catch (Exception e) {
				logger.error("JSONArrayNode parse failed" + e.getMessage(), e);
			}
		}

		setSelectedResults(selected);
		setFilteredResult(filtered);
	}
}
