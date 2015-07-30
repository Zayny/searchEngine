package searchengine.helper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.lucene.document.Document;
import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryParser.MultiFieldQueryParser;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.search.highlight.Highlighter;
import org.apache.lucene.search.highlight.QueryScorer;
import org.apache.lucene.search.highlight.SimpleFragmenter;
import org.apache.lucene.search.highlight.SimpleHTMLFormatter;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.wltea.analyzer.lucene.IKAnalyzer;

import searchengine.model.ShortModel;

public class SerachHelper{
	
	private static Logger log  = LoggerFactory.getLogger(SerachHelper.class);
	
	
	public static List<ShortModel> searchMultiField(String key) throws Exception{
		List<ShortModel> ls = new ArrayList<ShortModel>();
		IKAnalyzer analyzer = new IKAnalyzer();
		IndexReader indexReader = IndexReader.open(FSDirectory.open(new File(getIndexPath())));
		IndexSearcher indexSearcher = new IndexSearcher(indexReader);
		String[] fields = { "name", "shortcontent" };
		Map<String, Float> boosts = new HashMap<String, Float>();
		boosts.put("name", 3f);
		QueryParser queryParser = new MultiFieldQueryParser(Version.LUCENE_36, fields,analyzer,boosts);
		Query query = queryParser.parse(key);
		SimpleHTMLFormatter formatter = new SimpleHTMLFormatter("<font color='#CC0000'>", "</font>");
		QueryScorer queryScorer = new QueryScorer(query);
		Highlighter highlighter = new Highlighter(formatter, queryScorer);
		SimpleFragmenter simpleFragmenter = new SimpleFragmenter(200);
		highlighter.setTextFragmenter(simpleFragmenter);
		
		TopDocs topDocs = indexSearcher.search(query, 10);
		for (ScoreDoc sc : topDocs.scoreDocs) {
			Document document =indexSearcher.doc(sc.doc);
			ShortModel model = getModelFromDocument(document);
			String name = highlighter.getBestFragment(analyzer, "name", document.get("name"));
			String shortContent = highlighter.getBestFragment(analyzer, "shortcontent", document.get("shortcontent"));
			if(!"".equals(name)&&null!=name) model.setName(name);
			if(!"".equals(shortContent)&&null!=shortContent) model.setShortContent(shortContent);
			ls.add(model);
		}
		indexSearcher.close();
		return ls;
	}
	/**
	 * 对关键字进行索引
	 * @param keyWord
	 * @throws IOException 
	 * @throws CorruptIndexException 
	 */
	public static List<ShortModel> serarchKeyWord(String keyWord) {
		IndexReader indexReader = null;
		IndexSearcher indexSearcher = null;
		List<ShortModel> ls = new ArrayList<ShortModel>();
		try {
			indexReader = IndexReader.open(FSDirectory.open(new File(getIndexPath())));
			indexSearcher = new IndexSearcher(indexReader);
			Term term = new Term("shortcontent", keyWord);
			Query query = new TermQuery(term);
			TopDocs topDocs = indexSearcher.search(query, 10);
			for (ScoreDoc sc : topDocs.scoreDocs) {
				Document document =indexSearcher.doc(sc.doc);
				ls.add(getModelFromDocument(document));
			
			}
		} catch (Exception e) {
			log.error(e.getMessage());
			e.printStackTrace();
		}finally{
			try {
				indexSearcher.close();
			} catch (IOException e) {
				log.error(e.getMessage());
				e.printStackTrace();
			}
		}
		return ls;
	}
	
	private static ShortModel getModelFromDocument(Document document){
		ShortModel  model = new ShortModel();
					model.setName(document.get("name"));
					model.setUri(document.get("uri"));
					model.setShortContent(document.get("shortcontent"));
		log.debug(document.get("name"));
		log.debug(document.get("uri"));
		log.debug(document.get("shortcontent"));
		return model;
	}
	public static String getIndexPath(){
		return "src"+File.separator+"main"+File.separator+"resources"+File.separator+"index";
	}
	public static void main(String[] args) throws Exception {
		searchMultiField("爱奇艺");
	}
}
