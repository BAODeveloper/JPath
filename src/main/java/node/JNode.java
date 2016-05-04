package node;

import java.util.List;

public abstract class JNode {

	protected String raw;
	// 完整路径 例如 bookstore[@name]
	protected String path;
	// bookstore
	protected String key;

	protected List<String> selectedResults;
	protected List<String> filteredResults;

	protected JNode(String raw, String path) {
		this.raw = raw;
		this.path = path;
		int index = path.indexOf("[");
		if (index != -1) {
			key = path.substring(0, index);
		} else {
			key = path;
		}
	}

	protected abstract void parse();

	public List<String> getSelectedResults() {
		if (selectedResults == null) {
			parse();
		}
		return selectedResults;
	}

	public String getSelectedResults(int index) {
		if (selectedResults == null) {
			parse();
		}
		return selectedResults.get(index);
	}

	public void setSelectedResults(List<String> selectedResults) {
		this.selectedResults = selectedResults;
	}

	public List<String> getFilteredResult() {
		if (filteredResults == null) {
			parse();
		}
		return filteredResults;
	}

	public String getFilteredResult(int index) {
		if (filteredResults == null) {
			parse();
		}
		return filteredResults.get(index);
	}

	public void setFilteredResult(List<String> filteredResult) {
		this.filteredResults = filteredResult;
	}

}
