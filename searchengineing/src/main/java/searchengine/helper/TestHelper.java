package searchengine.helper;

import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.Field.Index;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;

public class TestHelper {
	 public void test() throws IOException {  
	        File file = new File("d://lucene//index");
	        FSDirectory directory = FSDirectory.open(file);  
	        //用来创建索引  
	        IndexWriterConfig conf = new IndexWriterConfig(Version.LUCENE_36,  
	                new StandardAnalyzer(Version.LUCENE_36));  
	        IndexWriter writer = new IndexWriter(directory, conf);  
	          
	        Document doc1 = new Document();  
	        Document doc2 = new Document();  
	        Document doc3 = new Document();  
	        Document doc4 = new Document();  
	        Document doc5 = new Document();  
	        Document doc6 = new Document();  
	        Document doc7 = new Document();  
	        Document doc8 = new Document();  
	          
	        Field f1 = new Field("bookname", "钢铁是怎样炼成的", Store.YES, Index.ANALYZED);  
	        Field f2 = new Field("bookname", "英雄儿女", Store.YES, Index.ANALYZED);  
	        Field f3 = new Field("bookname", "篱笆女人和狗", Store.YES, Index.ANALYZED);  
	        Field f4 = new Field("bookname", "格林童话", Store.YES, Index.ANALYZED);  
	        Field f5 = new Field("bookname", "钢和铁是两种金属", Store.YES, Index.ANALYZED);  
	        Field f6 = new Field("bookname", "白毛女", Store.YES, Index.ANALYZED);  
	        Field f7 = new Field("bookname", "钢的世界", Store.YES, Index.ANALYZED);  
	        Field f8 = new Field("bookname", "钢铁战士", Store.YES, Index.ANALYZED);  
	          
	        doc1.add(f1);  
	        doc2.add(f2);  
	        doc3.add(f3);  
	        doc4.add(f4);  
	        doc5.add(f5);  
	        doc6.add(f6);  
	        doc7.add(f7);  
	        doc8.add(f8);  
	          
	        writer.addDocument(doc1);  
	        writer.addDocument(doc2);  
	        writer.addDocument(doc3);  
	        writer.addDocument(doc4);  
	        writer.addDocument(doc5);  
	        writer.addDocument(doc6);  
	        writer.addDocument(doc7);  
	        writer.addDocument(doc8);  
	          
	        writer.close();//一定要关闭writer，这样索引才能写到磁盘上  
	          
	        IndexReader reader = IndexReader.open(directory);  
	        //简单索引  
	        IndexSearcher searcher = new IndexSearcher(reader);  
	        Term t = new Term("bookname", "钢");  
	        TermQuery quenry = new TermQuery(t);  
	        //获得得分靠前的两个匹配记录  
	        ScoreDoc[] docs = searcher.search(quenry, 2).scoreDocs;  
	        System.out.println(searcher.search(quenry, 2).totalHits);
	        System.out.println(searcher.search(quenry, 2).getMaxScore());
	        for(int i = 0; i < docs.length; i++) {  
	            Date start = new Date();  
	            String bookname = searcher.doc(docs[i].doc).get("bookname");  
	            Date end = new Date();  
	            System.out.println(bookname + (end.getTime() - start.getTime()) + "ms");  
	        }  
	        searcher.close();
	     }  
	      
	    public static void main(String[] args) throws IOException, NoSuchAlgorithmException {
	    	
	    	String pwd = HexHelper.getEncryptedPwd("123456");
	    	boolean b = HexHelper.validPasswd("123456", "E698BEB191D519E668A2882FD0D5B9EB577AC245878AEC70FADDE75E");
	    	System.out.println(pwd);
	    	System.out.println(b);
	    	
	    }  
}
