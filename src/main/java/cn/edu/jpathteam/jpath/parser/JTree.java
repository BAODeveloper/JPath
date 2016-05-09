package cn.edu.jpathteam.jpath.parser;

import java.util.ArrayList;
import java.util.List;

public class JTree {

	private JTreeNode root;

	private boolean leafGenerated = false;;

	private List<JTreeNode> leafs = new ArrayList<JTreeNode>();

	public JTreeNode getRoot() {
		return root;
	}

	public void setRoot(JTreeNode root) {
		this.root = root;
	}

	private void generateLeaf(JTreeNode node) {
		if (node.getChilds().size() == 0 && node.getjNode().getFilteredResult().size() > 0) {
			leafs.add(node);
			return;
		}
		for (JTreeNode child : node.getChilds()) {
			generateLeaf(child);
		}
	}

	public List<JTreeNode> getLeafs() {
		if (!leafGenerated) {
			generateLeaf(root);
			leafGenerated = true;
		}
		return leafs;
	}

	public void setLeafs(List<JTreeNode> leafs) {
		this.leafs = leafs;
	}

	public void addLeaf(JTreeNode leaf) {
		this.leafs.add(leaf);
	}

}
