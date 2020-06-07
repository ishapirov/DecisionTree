
public class DecisionTreeNode {
	
	private DecisionTreeNode[] childNodes;
	private DecisionTreeNode parent;
	private int value;
	private int column;
	private boolean leafNode;
	private int outputLabel;
	private boolean variableChildren;
	private int guess;
	
	public static int maxNrOfChildren = 10;
	
	public DecisionTreeNode() {
		childNodes = new DecisionTreeNode[maxNrOfChildren];
		this.value = 0;
		this.column = 0;
		leafNode = false;
		outputLabel = -1;
		guess = -1;
	}
	
	public DecisionTreeNode(int value) {
		childNodes = new DecisionTreeNode[maxNrOfChildren];
		this.value = value;
		this.column = -1;
		leafNode = false;
		outputLabel = -1;
		guess = -1;
	}
	
	public DecisionTreeNode(int value,boolean varChildren) {
		this.value = value;
		this.column = -1;
		leafNode = false;
		outputLabel = -1;
		guess = -1;
	}
	
	public DecisionTreeNode(boolean varChildren) {
		this.column = -1;
		leafNode = false;
		outputLabel = -1;
		guess = -1;
	}
	
	public DecisionTreeNode(int value,int children) {
		childNodes = new DecisionTreeNode[children];
		this.value = value;
		this.column = -1;
		leafNode = false;
		outputLabel = -1;
		guess = -1;
	}
	public void setChildNodeLen(int len) {
		childNodes = new DecisionTreeNode[len];
	}
	
	public void setChildInd(DecisionTreeNode node,int k) {
		this.childNodes[k] = node;
	}
	
	public static void setMaxNrOfChildren(int numChil) {
		maxNrOfChildren = numChil;
	}
	
	public void setParent(DecisionTreeNode node) {
		this.parent = node;
	}
	
	public DecisionTreeNode getParent() {
		return this.parent;
	}
	
	public boolean getLeafNode() {
		return leafNode;
	}
	
	public void setLeafNode(boolean leaf) {
		leafNode = leaf;
	}
	
	public int getOutput() {
		return outputLabel;
	}
	
	public void setOutput(int out) {
		outputLabel = out;
	}
	
	public int getGuess() {
		return guess;
	}
	
	public void setGuess(int out) {
		guess = out;
	}
	
	public void setColumn(int col) {
		this.column = col;
	}
	
	public void setChildren(DecisionTreeNode[] nodes) {
		this.childNodes = nodes;
	}
	
	public DecisionTreeNode getChild(int k) {
		return childNodes[k];
	}
	
	public DecisionTreeNode[] getChildren() {
		return childNodes;
	}
	
	public int getValue() {
		return value;
	}
	
	public int getColumn() {
		return column;
	}
	
	public int getNumChildren() {
		return childNodes.length;
	}
	
	public int heightFromCurrentNode() {
		int counter = 1;
		DecisionTreeNode temp = this;
		while(temp.parent != null) {
			temp = temp.parent;
			counter++;
		}
		return counter;
	}
	
	public String toString() {
		String val = value + " ";
		
		for(int i=0;i<childNodes.length;i++) {
			if(childNodes[i] != null)
				val += childNodes[i].toString();
		}

		return val;
	}
	
	public String toStringCols() {
		String col = column + " ";
		
		for(int i=0;i<childNodes.length;i++) {
			if(childNodes[i] != null && childNodes[i].getColumn() != -1)
				col += childNodes[i].toStringCols();
		}

		return col;
	}

}
