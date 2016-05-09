package cn.edu.jpathteam.jpath.node;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;

import cn.edu.jpathteam.jpath.criteria.JSONArrayCriteria;
import cn.edu.jpathteam.jpath.utils.StringUtils;

import com.alibaba.fastjson.JSONArray;

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
				JSONArray array = JSONArray.parseArray(raw);
				JSONArrayCriteria criteria = new JSONArrayCriteria(path);

				for (int i = 0; i < array.size(); i++) {
					String str = array.getString(i);
					selected.add(str);
					if (criteria.isValid(str)) {
						filtered.add(str);
					}
				}
			} catch (Exception e) {
				logger.error("JSONArrayNode parse failed" + e.getMessage(), e);
			}
		}

		setSelectedResults(selected);
		setFilteredResult(filtered);
	}
}
