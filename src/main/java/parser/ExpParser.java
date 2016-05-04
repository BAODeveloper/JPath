package parser;

import java.util.ArrayList;

import node.JNode;
import node.JSONArrayNode;
import node.JSONNode;
import node.RegexNode;
import node.RootNode;
import node.XMLNode;

import org.slf4j.Logger;

import utils.ResourceUtils;

/**
 * 
 * @author BAO
 * 
 */
public class ExpParser {
	private static final Logger logger = org.slf4j.LoggerFactory.getLogger(ExpParser.class);

	public static RootNode createRootNode(String raw) {
		return new RootNode(raw);
	}

	// x:bookstore[@name]
	private static JNode buildJNode(String jpathWithPrefix, String raw) throws Exception {
		JNode node = null;
		if (jpathWithPrefix.startsWith("j:")) {// JSONNode
			node = new JSONNode(raw, jpathWithPrefix.substring(2));
		} else if (jpathWithPrefix.startsWith("ja:")) {// JSONArrayNode
			node = new JSONArrayNode(raw, jpathWithPrefix.substring(3));
		} else if (jpathWithPrefix.startsWith("r:")) {// RegexNode
			node = new RegexNode(raw, jpathWithPrefix.substring(2));
		} else if (jpathWithPrefix.startsWith("x:")) {// XMLNode
			node = new XMLNode(raw, jpathWithPrefix.substring(2));
		} else {
			throw new Exception("unknown node");
		}
		return node;
	}

	public static JTree build(String raw, String jpath) {
		JTreeNode root = JTreeBuilder.buildRoot(createRootNode(raw));
		JTree tree = new JTree();
		tree.setRoot(root);
		StringBuilder sb = new StringBuilder();
		ArrayList<JTreeNode> nodeList = new ArrayList<JTreeNode>();
		nodeList.add(root);
		int k = 0;
		try {
			while (k < jpath.length()) {
				while (jpath.charAt(k) == '/') {
					k++;
				}
				if (k >= jpath.length()) {
					break;
				}
				while (k < jpath.length() && jpath.charAt(k) != '/') {
					if (jpath.charAt(k) == '\\') {
						k++;
						sb.append(jpath.charAt(k));
					} else {
						sb.append(jpath.charAt(k));
					}
					k++;
				}
				String tmp = sb.toString();
				sb.delete(0, sb.length());

				int size = nodeList.size();
				for (int i = 0; i < size; i++) {
					JTreeNode treeNode = nodeList.get(0);
					nodeList.remove(0);
					for (String str : treeNode.getjNode().getFilteredResult()) {
						JNode node = buildJNode(tmp, str);
						JTreeNode child = JTreeBuilder.buildChild(node, treeNode);
						nodeList.add(child);
					}
				}
			}
			return tree;
		} catch (Exception e) {
			logger.error("parse error for jpath: " + jpath + e.getMessage(), e);
		}
		return null;
	}

	

}
