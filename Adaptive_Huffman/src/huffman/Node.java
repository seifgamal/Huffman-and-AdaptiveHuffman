package huffman;

public class Node {
	private int freq;
	private Node left;
	private Node right;
	private Node parent;
	private char symbol; // public just for now;
	private int num;

	Node() {
		this.left = this.right = this.parent = null;
		this.symbol = 0;
		num = 0;
		freq = 0;
	}

	Node(char c) {
		this.left = this.right = this.parent = null;
		symbol = c;
		freq = 1;
		num = 0;
	}

	public void setLeft(Node n) {
		this.left = n;
	}

	public void setRight(Node n) {
		this.right = n;
	}

	public void setParent(Node n) {
		this.parent = n;
	}

	public void setFreq(int f) {
		this.freq = f;
	}

	public void setNum(int n) {
		this.num = n;
	}
	public char getSymbol() {
		return symbol;
	}

	public void setSymbol(char symbol) {
		this.symbol = symbol;
	}

	public Node getLeft() {
		return this.left;
	}

	public Node getRight() {
		return this.right;
	}

	public Node getParent() {
		return this.parent;
	}

	public Integer getNum() {
		return this.num;
	}

	public Integer getFreq() {
		return this.freq;
	}
	public static void equal(Node n1, Node n2) { // n1(new node) = n2;
		
		// equal freq & num & symbol 
		n1.setFreq(n2.getFreq());
		n1.setNum(n2.getNum());
		n1.symbol = n2.symbol;

		// left & right of n1
		n1.setParent(n2.getParent());
		
		n1.setLeft(n2.getLeft());
		n1.setRight(n2.getRight());

		// parent of left & right for n1
		if (n2.getLeft() != null) {
			n2.getLeft().setParent(n1);
		}
		if (n2.getRight() != null) {
			n2.getRight().setParent(n1);
		}
	}
	public static void swap(Node n1, Node n2) {
		Node n3 = new Node();		
		
		Node n1parent = n1.getParent();
		Node n2parent = n2.getParent();
		
		Node.equal(n3, n1); // n3 = n1;
		Node.equal(n1, n2); // n1 = n2;
		Node.equal(n2, n3); // n2 = n3;
		
		n1.setParent(n1parent);
		n2.setParent(n2parent);
	}
}
