
public class Node {
	private int freq;
	private Node left;
	private Node right;
	private char symbol;

	Node() {
		this.left = this.right = null;
		this.symbol = 0;
		freq = 0;
	}

	public void setLeft(Node n) {
		this.left = n;
	}

	public void setRight(Node n) {
		this.right = n;
	}

	public void setFreq(int f) {
		this.freq = f;
	}

	public Node getLeft() {
		return this.left;
	}

	public Node getRight() {
		return this.right;
	}

	public Integer getFreq() {
		return this.freq;
	}

	public char getSymbol() {
		return symbol;
	}
	public void setSymbol(char symbol) {
		this.symbol = symbol;
	}
}
