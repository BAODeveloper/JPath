import parser.ExpParser;
import parser.JTree;
import parser.JTreeNode;
import utils.ResourceUtils;

public class Test {
	public static void main(String[] args) {
		String jpath = "/x:bookstore[@name]/x:book";
		String raw = ResourceUtils.readResouce("test.xml");
		JTree tree = ExpParser.build(raw, jpath);

		for (JTreeNode node : tree.getLeafs()) {
			System.out.println(node.getjNode().getFilteredResult());
		}
	}
}
