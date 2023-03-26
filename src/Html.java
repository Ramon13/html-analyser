import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.LinkedList;
import java.util.Queue;

public class Html {

	private Queue<String> lines = new LinkedList<>();
	
	private int maxDepth = 0;
	private String deepestText;
	private boolean valid = true;
	
	public Html(URL url) throws IOException {
		try(BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()))) {
			String s = null;
			while((s = reader.readLine()) != null) {
				lines.add(s);
			}
		}
		
		processHTML("root", 0);
		if (isValidDocument() == false) {
			throw new MalformedHTMLException();
		}
	}
	
	public void processHTML(String openTag, int depth) {
		String line = null;
		while ((line = lines.poll()) != null) {
			line = line.trim();
			if (depth > maxDepth) {
				maxDepth = depth;
				deepestText = line;
			}
			
			if (line.contains("</")) {
				valid = isValidCloseTag(openTag, line);
				return;
			}
			
			if (line.charAt(0) == '<') {
				valid = false;
				processHTML(line, depth + 1);
			}
		}
	}
	
	public String getDeepestText() {
		return deepestText;
	}
	
	private boolean isValidDocument() {
		return this.valid;
	}
	
	private boolean isValidCloseTag(String openTag, String closeTag) {
		return openTag.substring(1).equals(closeTag.substring(2));
	}
}
