package parser;

import node.JNode;

public class JTreeBuilder {

	public static JTreeNode buildRoot(JNode node) {
		JTreeNode root = buildJTreeNode(node);
		return root;
	}

	public static JTreeNode buildJTreeNode(JNode node) {
		JTreeNode n = new JTreeNode();
		n.setjNode(node);
		return n;
	}

	public static JTreeNode buildChild(JNode node, JTreeNode parent) {
		JTreeNode n = buildJTreeNode(node);
		n.setParent(parent);
		parent.addChild(n);
		return n;
	}

}
