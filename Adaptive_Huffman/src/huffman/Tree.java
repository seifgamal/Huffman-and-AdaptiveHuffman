package huffman;

import java.util.LinkedList;
import java.util.Queue;

public class Tree {
	private final Integer N = 129;
	private String Paths[];
	private Node root;
	private Node NYT;
	Tree() {
		Paths = new String[N];
		for (int i = 0; i < 128; i++) {
			Paths[i] = '0' + Integer.toBinaryString(0x100 + i).substring(2);
		}
		Paths[128] = ""; // NYT index
		root = new Node();
		NYT = new Node();
	}
	public void add(char c) {
		Node n = new Node(c);
		if (root.getLeft() == null) { // first Symbol
			root.setRight(n);
			root.setLeft(NYT);
			
			NYT.setParent(root);
			n.setParent(root);
		} else if (Paths[(int) c].length() == 8) { // first occurrence			
			Node newNode = new Node();
			NYT.getParent().setLeft(newNode);
			newNode.setLeft(NYT);
			newNode.setRight(n);
			newNode.setParent(NYT.getParent());
			
			n.setParent(newNode);
			
			NYT.setParent(newNode);
			NYT.setLeft(null);
			NYT.setRight(null);
			
		} else {			
			n = root;
			String path = Paths[(int) c];
			int idx = 0;
			while (idx < path.length()) {
				if (path.charAt(idx) == '0'){
					n = n.getLeft();
				}
				else
					n = n.getRight();
				++idx;
			}
			n.setFreq(n.getFreq() + 1);
		}
		fixNum();
		fixFreqAndPaths(root, "");
		update(n);
	}
	private void fixNum() { // BFS
		Node n = new Node();
		n = root;
		Queue<Node> q = new LinkedList<Node>();
		q.add(n);
		int number = 100;
		while (!q.isEmpty()) {
			Node top = q.peek();
			top.setNum(number--);
			q.remove();
			if (top.getRight() != null)
				q.add(top.getRight());
			if (top.getLeft() != null)
				q.add(top.getLeft());
			
		}
	}

	private Integer fixFreqAndPaths(Node n, String path) {
		if (n == null) // it's not a node
			return 0;
		if (n.getLeft() == null) {// it's a leaf node
			int idx = 128;
			if (n.getSymbol() != 0){
				idx = n.getSymbol();
			}
				
			Paths[idx] = path;
			return n.getFreq();
		}
		int children = fixFreqAndPaths(n.getLeft(), path + '0') + fixFreqAndPaths(n.getRight(), path + '1');
		n.setFreq(children);
		return children;
	}
	private void update(Node n) {
		if (n == root)
			return;
		Node to = toSwapWith(n);
		if (to.getNum() == 0) { // no node to swap with
			update(n.getParent());
		} else {
			// System.out.println("A problem " + n.getNum() + ' ' + to.getNum() + ' ' + n.getFreq() + ' ' + to.getFreq());
			Node.swap(n, to);
			fixNum();
			fixFreqAndPaths(root, "");
		}
	}
	private Node toSwapWith(Node prev) {
		Node cur = new Node();
		cur = root;
		Queue<Node> q = new LinkedList<Node>();
		q.add(cur);
		int number = prev.getNum();
		while (!q.isEmpty()) {
			cur = q.peek();
			if (cur.getNum() <= number)
				break;
			if (cur != root && cur != prev.getParent() && cur.getFreq() < prev.getFreq())
				return cur;
			
			q.remove();
			if (cur.getRight() != null)
				q.add(cur.getRight());
			if (cur.getLeft() != null)
				q.add(cur.getLeft());
			
		}
		return new Node();
	}
	public String getPath(char c) {
		return Paths[c];
	}
	public Node begin() {
		return this.root;
	}
	public Node end() {
		return this.NYT;
	}
}