package cn.edu.jpathteam.jpath.node;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;

import cn.edu.jpathteam.jpath.utils.StringUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

public class JSONNode extends JNode {
	private static final Logger logger = org.slf4j.LoggerFactory.getLogger(JSONNode.class);

	public JSONNode(String raw, String path) {
		super(raw, path);
	}

	public void parse() {

		List<String> selected = new ArrayList<String>();
		List<String> filtered = new ArrayList<String>();
		if (StringUtils.allEmpty(key, raw)) {
			logger.warn("JSONNode parse failed because of" + (StringUtils.isEmpty(raw) ? "raw String is empty" : "regex String is empty"));
			selected.add(raw);
			filtered.add(raw);
		} else {
			try {
				JSONObject jsonObject = JSON.parseObject(raw);
				String s = jsonObject.getString(key);
				if (s != null) {
					selected.add(s);
					filtered.add(s);
				} else {
					logger.warn("JSONNode no such key: " + key);
				}
			} catch (Exception e) {
				logger.error("JSONNode parse failed" + e.getMessage(), e);
			}
		}
		setSelectedResults(selected);
		setFilteredResult(filtered);
	}

}
