package cn.edu.jpathteam.jpath.parser;

import java.util.ArrayList;
import java.util.List;

import cn.edu.jpathteam.jpath.node.JNode;

public class JTreeNode {

	private JNode jNode;
	private JTreeNode parent;
	private List<JTreeNode> childs = new ArrayList<JTreeNode>();

	public JNode getjNode() {
		return jNode;
	}

	public void setjNode(JNode jNode) {
		this.jNode = jNode;
	}

	public JTreeNode getParent() {
		return parent;
	}

	public void setParent(JTreeNode parent) {
		this.parent = parent;
	}

	public List<JTreeNode> getChilds() {
		return childs;
	}

	public void addChild(JTreeNode child) {
		this.childs.add(child);
	}

}
