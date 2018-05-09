import java.io.FileInputStream;
import java.io.IOException;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Vector;

public class HuffmanTree {
	private final Integer N = 128;
	private String Paths[];
	private String stream; // 0s 1s
	private Node root;
	private PriorityQueue<Node> queue;
	private Integer Frequency[];
	private Vector<Tag>tags;

	HuffmanTree() {
		Paths = new String[N];
		Frequency = new Integer[N];
		for (int i = 0; i < N; i++) {
			Frequency[i] = 0;
			Paths[i] = "";
		}
		tags = new Vector<Tag>();
		root = new Node();
		stream = "";

		queue = new PriorityQueue<Node>(19, new Comparator<Node>() {
			public int compare(Node node1, Node node2) {
				if (node1.getFreq() > node2.getFreq())
					return 1;
				if (node1.getFreq() == node2.getFreq())
					return 0;
				return -1;
			}
		});

	}

	public String compressFile(String filePath) throws IOException {
		FileInputStream in = null;
		String data = "";
		try {
			in = new FileInputStream(filePath);
			int c;
			while ((c = in.read()) != -1) {
				data += (char) c;
			}
		} finally {
			if (in != null)
				in.close();
		}
		solve(data);
		return stream;
	} 
	private void solve(String data) {
		for (int i = 0; i < (int) data.length(); i++) { // calculate Frequency
			Frequency[(int) data.charAt(i)]++;
		}

		for (int i = 0; i < N; i++) { // put in queue
			if (Frequency[i] > 0) {
				Node n = new Node();
				n.setSymbol((char) i);
				n.setFreq(Frequency[i]);
				queue.add(n);
			}
		}
		while (queue.size() > 1) {
			Node node1 = queue.peek();
			queue.remove();
			Node node2 = queue.peek();
			queue.remove();

			Node n = new Node();
			n.setFreq(node1.getFreq() + node2.getFreq());
			n.setLeft(node2);
			n.setRight(node1);

			queue.add(n);
		}
		root = queue.peek();
		queue.remove();
		dfs(root, "");

		// 0s 1s
		for (int i = 0; i < (int) data.length(); i++)
			stream += Paths[data.charAt(i)];
		
		for (int i = 0; i < N; i++) {
			if (Frequency[i] > 0) {
				tags.addElement(new Tag((char)i, Paths[i]));
			}
		}
	}

	private void dfs(Node n, String path) {
		if (n == null) // invalid case - no string to compress 
			return;
		if (n.getLeft() == null) {// it's a leaf node
			Paths[n.getSymbol() + 0] = path;
			return;
		}
		dfs(n.getLeft(), path + '1');
		dfs(n.getRight(), path + '0');
	}
	
	public Vector<Tag>getTable() {
		return tags;
	}

}