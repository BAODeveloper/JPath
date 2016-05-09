package cn.edu.jpathteam.jpath;

import java.util.ArrayList;
import java.util.List;

import cn.edu.jpathteam.jpath.parser.ExpParser;
import cn.edu.jpathteam.jpath.parser.JTree;
import cn.edu.jpathteam.jpath.parser.JTreeNode;

public class JPath {

	/**
	 * 原始内容
	 */
	private String content;

	public JPath() {
	}

	public JPath(String content) {
		this.content = content;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public static List<String> jpath(String raw, String jpath) {
		JTree tree = ExpParser.build(raw, jpath);
		List<String> ans = new ArrayList<String>();
		for (JTreeNode node : tree.getLeafs()) {
			ans.addAll(node.getjNode().getFilteredResult());
		}
		return ans;
	}
}
