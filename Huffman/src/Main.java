import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Vector;

public class Main {

	public static void main(String[] args) throws IOException {
	}

	public static void writeStream(String str, String filePath) {
		try (PrintWriter out = new PrintWriter(filePath)) {
			out.print(str);
		} catch (IOException e) {
			System.out.println("file not found");
		}
	}

	public static void writeTable(String filePath, Vector<Tag> v) {
		try (PrintWriter out = new PrintWriter(filePath)) {
			for (int i = 0; i < v.size(); i++) {
				out.print(v.get(i).sybmol + "" + v.get(i).code + ',');
			}
		} catch (IOException e) {
			System.out.println("file not found");
		}
	}

	public static String readStream(String filePath) throws IOException {
		FileInputStream in = null;
		String out = "";
		try {
			in = new FileInputStream(filePath);
			int c;
			while ((c = in.read()) != -1) {
				out += (char) c;
			}
		} finally {
			if (in != null)
				in.close();
		}
		return out;
	}

	public static Vector<Tag> readTable(String filePath) throws IOException {
		FileInputStream in = null;
		Vector<Tag> out = new Vector<Tag>();
		try {
			in = new FileInputStream(filePath);
			int c = 0;
			while (true) {
				c = in.read();
				if (c == -1)
					break;
				char sybmol = (char) c;
				String path = "";
				while((c = in.read()) != -1) {
					if ((char)c == ',') {
						break;
					}
					path += (char)c;
					
				}
				out.add(new Tag(sybmol, path));
			}
		} finally{
			if (in != null)
				in.close();
		}
		return out;
	}
	public static String decompress(Vector<Tag> tags, String stream) {
		HashMap<String, Character> mp = new HashMap<String, Character>();
		for (int i = 0; i < tags.size(); i++) {
			mp.put(tags.get(i).code, tags.get(i).sybmol);
		}
		String output = "";
		for (int i = 0; i < (int)stream.length();) {
			String sub = "";
			while(i < (int)stream.length() && !mp.containsKey(sub)) {
				sub += stream.charAt(i);
				++i;
			}
			if (sub.equals(""))
				break;
			output += mp.get(sub);
		}
		return output;
	}

}