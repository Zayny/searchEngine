package searchengine.helper;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FileHelper {
	
	
	private final static Logger log = LoggerFactory.getLogger(FileHelper.class);
	public static String getFilename(File file){
		return file.getName().substring(0, file.getName().lastIndexOf("."));
	}
	
	public static List<File> getFiles(File rootfile){
		List<File> fileList = new ArrayList<File>();
		if(null!=rootfile){
			if(rootfile.isDirectory()){
				File[] listFiles = rootfile.listFiles();
				for (File file : listFiles) {
					if(file.isFile()){
						fileList.add(file);
					}else{
						getFiles(file);
					}
				}
			}else if(rootfile.isFile()){
				fileList.add(rootfile);
			}
		}
		return fileList;
	}
	public static String getContent(File file){
		try {
			log.debug("reading file...");
			return IOUtils.readLines(new InputStreamReader(new FileInputStream(file), "UTF-8")).toString();
		} catch (FileNotFoundException e) {
			log.error(e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			log.error(e.getMessage());
			e.printStackTrace();
		}
		return "";
	}
	public static void main(String[] args) {
		String content = getContent(new File("D:\\test\\by.txt"));
		System.out.println(content);
	}
}
