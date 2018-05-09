package huffman;

public class Encoder {
	private String stream;
	private Tree tree;
	public Encoder() {
		tree = new Tree();
		stream = "";
	}
	public void add(char c) {
		String path = tree.getPath(c);
		if (path.length() == 8) 
			stream +=  tree.getPath((char)128);
		
		stream += path;
		tree.add(c);
	}
	public void add(String s) {
		for (int i = 0; i < s.length(); i++) 
			add(s.charAt(i));
	}
	public String get() {
		return stream;
	}
}