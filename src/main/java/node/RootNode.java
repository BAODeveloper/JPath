package node;

import java.util.ArrayList;
import java.util.List;

public class RootNode extends JNode {

	public RootNode(String raw) {
		super(raw, "/");
	}

	public void parse() {
		List<String> ans = new ArrayList<String>();
		ans.add(raw);
		setFilteredResult(ans);
		setSelectedResults(ans);
	}
}
