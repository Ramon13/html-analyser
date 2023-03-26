import java.io.IOException;
import java.net.URL;

public class HtmlAnalyzer {

	public static void main(String[] args) {
		try {
			URL url = new URL(args[0]);
			Html html = new Html(url);
			
			System.out.println(html.getDeepestText());
		} catch (IOException e) {
			System.out.println("URL connection error");
			
		} catch (MalformedHTMLException e) {
			System.out.println("malformed HTML");
		}
	}
}

