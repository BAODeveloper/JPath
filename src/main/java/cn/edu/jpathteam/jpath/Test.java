package cn.edu.jpathteam.jpath;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import cn.edu.jpathteam.jpath.parser.ExpParser;
import cn.edu.jpathteam.jpath.parser.JTree;
import cn.edu.jpathteam.jpath.parser.JTreeNode;
import cn.edu.jpathteam.jpath.utils.ResourceUtils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class Test {

	public static String generateJSONStr() {

		JSONObject obj = new JSONObject();
		obj.put("foo", "bar");
		JSONArray array = new JSONArray();
		array.add("hello");
		array.add("world");
		array.add("!");
		obj.put("array", array);

		return obj.toJSONString();
	}

	public static void test1() {
		String jpath = "/x:bookstore[@name=shudian1]/x:book[0-1]/x:text()";
		String raw = ResourceUtils.readResouce("test.xml");
		System.out.println(JPath.jpath(raw, jpath));
//		JTree tree = ExpParser.build(raw, jpath);

//		for (JTreeNode node : tree.getLeafs()) {
//			for (String str : node.getjNode().getFilteredResult()) {
//				Document doc = Jsoup.parse(str);
//				System.out.println(doc.text());
//			}
//			// System.out.println(node.getjNode().getFilteredResult());
//		}
	}

	public static void main(String[] args) {
		// System.out.println(generateJSONStr());
		//
		// String jpath = "/j:array/ja:[0-2]";
		// String raw = generateJSONStr();
		// JTree tree = ExpParser.build(raw, jpath);
		//
		//
		//
		// System.out.println(JPath.jpath(raw, jpath));
		test1();
	}
}