package searchengine.helper;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class ImageUtil {
	/**
	 * 给图片加水印
	 * @param iconPath
	 * @param imageFile
	 * @param targerPath
	 * @param degree
	 */
	public static void markImage(String iconPath, File imageFile,String targerPath, Integer degree) {   
        OutputStream os = null;   
        try {   
        	  BufferedImage buffImg = ImageIO.read(imageFile);   
            // 得到画笔对象   
            Graphics2D g = buffImg.createGraphics();   
            // 设置对线段的锯齿状边缘处理   
            g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,   
                    RenderingHints.VALUE_INTERPOLATION_BILINEAR);   
  
            g.drawImage(buffImg.getScaledInstance(buffImg.getWidth(), buffImg.getHeight(), Image.SCALE_SMOOTH), 0, 0, null);   
            if (null != degree) {   
                // 设置水印旋转   
                g.rotate(Math.toRadians(degree),   
                        (double) buffImg.getWidth() / 2, (double) buffImg   
                                .getHeight() / 2);   
            }   
            // 水印图象的路径 水印一般为gif或者png的，这样可设置透明度   
            ImageIcon imgIcon = new ImageIcon(iconPath);   
            // 得到Image对象。   
            Image img = imgIcon.getImage();   
            float alpha = 0.5f; // 透明度   
            g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP,   
                    alpha));   
  
            // 表示水印图片的位置   
            g.drawImage(img, 150, 300, null);   
  
            g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER));   
  
            g.dispose();   
  
            os = new FileOutputStream(targerPath);   
  
            // 生成图片   
            ImageIO.write(buffImg, "JPG", os);   
  
            System.out.println("图片完成添加Icon印章。。。。。。");   
        } catch (Exception e) {   
            e.printStackTrace();   
        } finally {   
            try {   
                if (null != os)   
                    os.close();   
            } catch (Exception e) {   
                e.printStackTrace();   
            }   
        }
	}
	public static void getcompressImage(File orginImg,File tinyImg,String format){
		//缩放的目标宽度
		int width=240;
		//缩放的目标高度
		int height=240;
		//获取到一个图片对象
		try {
			BufferedImage orgin01 =ImageIO.read(orginImg);
			int x = orgin01.getWidth();//原始图片的宽度
			int y = orgin01.getHeight();//原始图片的高度
			System.out.println("原始图片尺寸为："+x+"*"+y);
			
			int x1 = width;//获取到不失真的填充图片宽度
			int y1 = height ;//获取到不失真的填充图片高度
			if (width*y<height*x) {
				y1=width*y/x;//重算高度  高度不够
			}
			if(width*y>height*x){
				x1=height*x/y;//重算是宽度  宽度不够
			}
			BufferedImage bigImg = new BufferedImage(width, height, BufferedImage.SCALE_SMOOTH);
			Graphics2D graphics2d = bigImg.createGraphics();
			graphics2d.fillRect(0, 0, width, height);
			graphics2d.setColor(new Color(255, 255, 255));
			Image  tianc = orgin01.getScaledInstance(x1, y1, BufferedImage.SCALE_SMOOTH);
//			图像的左上角位于该图形上下文坐标空间的 (x, y)。
			graphics2d.drawImage(tianc, (width-x1)/2, (height-y1)/2, null);
			ImageIO.write(bigImg, format, tinyImg);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		getcompressImage(new File("d:/test/a.png"), new File("d:/test/hahah0002.png"),"png");
	}
	
}
