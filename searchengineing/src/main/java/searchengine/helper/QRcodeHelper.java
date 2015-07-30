package searchengine.helper;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import net.glxn.qrgen.QRCode;
import net.glxn.qrgen.image.ImageType;


public class QRcodeHelper {
	
	/**
	 * 功能： 生成二维码到path
	 * @param fromStr   生成二维码的源
	 * @param width		宽度
	 * @param height	高度
	 * @param path		目的路径
	 */
	public static void createQrCode(String fromStr,int width,int height,String path){
		try {
			QRCode.from(fromStr).to(ImageType.PNG).withSize(width, height).writeTo(new FileOutputStream(new File(path)));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		};
	}
	public static void main(String[] args) {
		
		ByteArrayOutputStream out = QRCode.from("http://www.baidu.com").to(ImageType.PNG).withSize(250, 250).stream();
//		ByteArrayOutputStream t1 = QRCode.from("hello").to(ImageType.PNG).stream();
//		ByteArrayOutputStream t2 = QRCode.from("hello").stream();
		try {
			FileOutputStream outputStream = new FileOutputStream(new File("D:/test/fyn.png"));
			outputStream.write(out.toByteArray());
			outputStream.flush();
			outputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
