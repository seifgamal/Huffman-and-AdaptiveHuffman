package huffman;

public class Decoder {
	private Node iterator;
	private Tree tree;
	private String text;
	private String Code;

	public Decoder() {
		tree = new Tree();
		text = Code = "";
		iterator = new Node();
		iterator = tree.begin(); // root
	}
	void add(char c) {
		if (text.equals("") || iterator == tree.end()) { 
			Code += c;
			if (Code.length() == 8) {
				int ch = Integer.parseInt(Code, 2);
				if (ch > 127){
					System.out.println(ch);
					return;
				}
					
				text += (char)ch;
				Code = "";
				tree.add((char)ch);
				iterator = tree.begin();
			}
		} else if (c == '0') {
			iterator = iterator.getLeft();
		} else
			iterator = iterator.getRight();

		if (iterator.getSymbol() != 0) {
			text += (iterator.getSymbol());
			tree.add(iterator.getSymbol());
			iterator = tree.begin();
		}
	}
	public void add(String s) {
		for (int i = 0; i < s.length(); i++)
			add(s.charAt(i));
	}
	public String get() {
		return text;
	}
}