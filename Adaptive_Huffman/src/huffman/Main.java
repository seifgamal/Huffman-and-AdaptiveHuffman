package huffman;

public class Main {
	public static void main(String[] args) {
		Encoder e = new Encoder();
		String test = "some string to be TESTED";

		for (int i = 0; i < test.length(); i++) {
			e.add(test.charAt(i));
		}
		System.out.println(e.get());
		String s = e.get();
		Decoder d = new Decoder();
		d.add(s);
		System.out.println(d.get());
	}

}
