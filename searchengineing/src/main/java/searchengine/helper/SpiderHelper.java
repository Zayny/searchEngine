package searchengine.helper;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import searchengine.model.ShortModel;

public class SpiderHelper {

	public static List<ShortModel> getMsg(String url) throws Exception {
		return paseMsgToModel(createDocument(url));
	}

	public static void getSingleHtml(String url) throws Exception {
//		 Connection conn = Jsoup.connect(url);
//					conn.header("(Request-Line)", "POST /cgi-bin/login?lang=zh_CN HTTP/1.1");
//					conn.header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
//					conn.header("Accept-Encoding", "gzip, deflate");
//					conn.header("Accept-Language", "zh-cn");
//					conn.header("scheme", "https");
//					conn.header("Cache-Control", "no-cache");
//					conn.header("Connection", "Keep-Alive");
//					conn.header("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
//		
		Document doc =createDocument(url);
		System.out.println(doc.text());
	}

	private static Document createDocument(String url) throws Exception {
		return Jsoup.connect(url)// .data("query", "Java")--发送数据
				.userAgent("Mozilla/4.0 (compatible; MSIE 9.0; Windows NT 6.1; Trident/5.0)").cookie("auth", "token").timeout(3000).post();
	}

	private static List<ShortModel> paseMsgToModel(Document document) {
		List<ShortModel> ls = new ArrayList<ShortModel>();
		Elements elements = document.getElementsByClass("hot_pos").select("li.clearfix");
		for (Element element : elements) {
			Elements hrefs = element.select("a[href]");
			ShortModel model = new ShortModel();
			model.setTitle(hrefs.get(2).text());
			model.setName(hrefs.get(2).text());
			model.setUri(hrefs.get(0).attr("href"));
			model.setShortContent(element.text());
			ls.add(model);
		}
		return ls;
	}

	// 暂时不用
	public static String getHtml(String url) {
		StringBuilder sb = new StringBuilder();
		try {
			URL u = new URL(url);
			InputStream openStream = u.openStream();
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(openStream));
			while (true) {
				String temp = bufferedReader.readLine();
				if (null == temp || "".equals(temp)) {
					break;
				}
				sb.append(bufferedReader.readLine());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(sb.toString());
		return sb.toString();
	}

	public static void main(String[] args) throws Exception {
		//getMsg("http://www.lagou.com/zhaopin/Java?labelWords=label");
//		getSingleHtml("http://www.open-open.com/jsoup/parse-body-fragment.htm");
//		getHtml("http://www.open-open.com/jsoup/parse-body-fragment.htm");
		Document doc = createDocument("http://www.lagou.com/zhaopin/iOS?labelWords=label");
		System.out.println(doc);
		System.out.println();
		Elements elements = doc.getElementsByClass("s_position_list").select("con_list_item");
		System.out.println(elements.size());
//		for(Element e :elements){
//			System.out.println(e.data());
//		}
	}
}
