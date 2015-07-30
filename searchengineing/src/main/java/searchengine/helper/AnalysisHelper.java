package searchengine.helper;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.Field.Index;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.IndexWriterConfig.OpenMode;
import org.apache.lucene.index.LogByteSizeMergePolicy;
import org.apache.lucene.index.LogMergePolicy;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.wltea.analyzer.core.IKSegmenter;
import org.wltea.analyzer.core.Lexeme;
import org.wltea.analyzer.lucene.IKAnalyzer;

import searchengine.model.BaseModel;

public class AnalysisHelper {

	private static final Logger log = LoggerFactory.getLogger(AnalysisHelper.class);

	/**
	 * 字符串分词
	 * 
	 * @param content
	 * @return 分完词的list
	 */
	public static List<String> splitKeywords(String content) {
		List<String> keys = new ArrayList<String>();
		if (StringUtils.isNotBlank(content)) {
			StringReader reader = new StringReader(content);
			IKSegmenter ikseg = new IKSegmenter(reader, true);
			try {
				while (true) {
					Lexeme lexeme = ikseg.next();
					if (null == lexeme) {
						break;
					}
					String term = lexeme.getLexemeText();
					if (StringUtils.isNumeric(StringUtils.remove(term, "."))) {
						continue;
					}
					keys.add(term);
				}
			} catch (IOException e) {
				log.error(e.getMessage());
				e.printStackTrace();
			}
		}
		return keys;
	}

	/**
	 * 对文档构建索引 使用对象：file文件
	 * 
	 * @param file
	 *            ： file文件
	 */
	public static void createIndexFromFile(File file) {
		Analyzer ikAnalyzer = new IKAnalyzer(true);
		IndexWriterConfig config = new IndexWriterConfig(Version.LUCENE_36, ikAnalyzer);// 设置lucene的版本和分词器
		LogMergePolicy logMergePolicy = new LogByteSizeMergePolicy();
		logMergePolicy.setMergeFactor(50);
		logMergePolicy.setUseCompoundFile(true);// 启用复合式索引文件格式,合并多个segment
		config.setOpenMode(OpenMode.CREATE_OR_APPEND);// 设置索引打开模式
		Directory directory = null;
		IndexWriter indexWriter = null;
		try {
			directory = FSDirectory.open(new File(getIndexPath()));
			indexWriter = new IndexWriter(directory, config);
			if (file.isDirectory()) {
				for (File text : file.listFiles()) {
					if (text.isFile()) {
						indexWriter.addDocument(createDocument(text));
						indexWriter.commit();
					}
				}
			} else if (file.isFile()) {
				indexWriter.addDocument(createDocument(file));
				indexWriter.commit();
			}
		} catch (IOException e) {
			log.error("create fail");
			log.error(e.getMessage());
			e.printStackTrace();
		} finally {
			closeWriter(indexWriter);
		}
	}

	public static void createIndexFromFile(InputStream in) {

	}

	/**
	 * 路径下有多个文件的时候
	 * 
	 * @param file
	 */
	public static void createIndexFromFiles(File file) {
		Analyzer ikAnalyzer = new IKAnalyzer(true);
		IndexWriterConfig config = new IndexWriterConfig(Version.LUCENE_36, ikAnalyzer);// 设置lucene的版本和分词器
		LogMergePolicy logMergePolicy = new LogByteSizeMergePolicy();
		logMergePolicy.setMergeFactor(50);
		logMergePolicy.setUseCompoundFile(true);// 启用复合式索引文件格式,合并多个segment
		config.setOpenMode(OpenMode.CREATE_OR_APPEND);// 设置索引打开模式
		Directory directory = null;
		IndexWriter indexWriter = null;
		try {
			directory = FSDirectory.open(new File(getIndexPath()));
			indexWriter = new IndexWriter(directory, config);
			List<File> files = FileHelper.getFiles(file);
			if (!files.isEmpty()) {
				for (File f : files) {
					indexWriter.addDocument(createDocument(f));
				}
			} else {
				log.error("【" + file.getName() + "】不存在，请校验");
			}
		} catch (IOException e) {
			log.error(e.getMessage());
			e.printStackTrace();
		} finally {
			closeWriter(indexWriter);
		}
	}

	/**
	 * 获取默认index存放路径
	 * 
	 * @return
	 */
	public static String getIndexPath() {
		return "src" + File.separator + "main" + File.separator + "resources" + File.separator + "index";
	}

	/**
	 * 
	 * @param model
	 */
	public static void createIndexFromModel(BaseModel model) {
		Analyzer ikAnalyzer = new IKAnalyzer(true);
		IndexWriterConfig config = new IndexWriterConfig(Version.LUCENE_36, ikAnalyzer);// 设置lucene的版本和分词器
		LogMergePolicy logMergePolicy = new LogByteSizeMergePolicy();
		logMergePolicy.setMergeFactor(50);
		logMergePolicy.setUseCompoundFile(true);// 启用复合式索引文件格式,合并多个segment
		config.setOpenMode(OpenMode.CREATE_OR_APPEND);// 设置索引打开模式
		IndexWriter indexWriter = null;
		Class<? extends BaseModel> clazz = model.getClass();
		java.lang.reflect.Field[] fields = clazz.getDeclaredFields();
		try {
			Document document = new Document();
			indexWriter = new IndexWriter(FSDirectory.open(new File(getIndexPath())), config);
			BeanInfo beanInfo = Introspector.getBeanInfo(model.getClass());
			PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
			for (PropertyDescriptor propertyDescriptor : propertyDescriptors) {
				Method readMethod = propertyDescriptor.getReadMethod();
				String name = readMethod.getName();
				if (!name.equals("getClass")) {
					for (int j = 0; j < fields.length; j++) {
						String fieldName = fields[j].toString();
						String mname = name.substring(3,name.length()).toLowerCase();
						String sname = fieldName.substring(fieldName.lastIndexOf(".")+1,fieldName.length()).toLowerCase();
						if (mname.equals(sname)) {
							Object result = readMethod.invoke(model, new Object[]{});
							if(null==result||"".equals(result)){
								break;
							}
							if (mname.equals("uri")) {
								System.out.println(mname);
								document.add(new Field(mname, result.toString(), Store.YES, Index.NOT_ANALYZED));
							} else {
								System.out.println(mname);
								document.add(new Field(mname, result.toString(), Store.YES, Index.ANALYZED));
							}
						}
					}
				}
			}
			indexWriter.addDocument(document);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			logMergePolicy.close();
			closeWriter(indexWriter);
		}
	}

	private static Document createDocument(File text) {
		Document doc = new Document();
		doc.add(new Field("name", FileHelper.getFilename(text), Store.YES, Index.ANALYZED));
		doc.add(new Field("path", text.getAbsolutePath(), Store.YES, Index.NOT_ANALYZED));
		doc.add(new Field("content", FileHelper.getContent(text), Store.YES, Index.ANALYZED));
		log.debug("fileContent  :" + FileHelper.getContent(text));
		return doc;
	}

	private static void closeWriter(IndexWriter writer) {
		if (null != writer) {
			try {
				writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		String text = "中国小号文档实验的测试商旅卡批发移动免网费hello mani 卡号用户高端电话;";
		for (String word : AnalysisHelper.splitKeywords(text)) {
			System.out.println(word);
		}
		System.out.println("======================================================");
		// Class文件所在路径

		System.out.println("1 :" + Thread.currentThread().getContextClassLoader().getResource("index")); // file:/D:/workspace/searchengineing/target/classes/index
		System.out.println("2 :" + AnalysisHelper.class.getClassLoader().getResource("index")); // file:/D:/workspace/searchengineing/target/classes/index
		System.out.println("3 :" + ClassLoader.getSystemResource("index")); // file:/D:/workspace/searchengineing/target/classes/index
		System.out.println("4 :" + AnalysisHelper.class.getResource("/index")); // file:/D:/workspace/searchengineing/target/classes/index
		System.out.println("5 :" + Class.class.getResource("/index"));// file:/D:/workspace/searchengineing/target/classes/index
		System.out.println(AnalysisHelper.getIndexPath());
		System.out.println(System.getProperty("user.dir"));

		// 测试建立索引
		AnalysisHelper.createIndexFromFiles(new File("D:\\test\\by.txt"));
	}
}
