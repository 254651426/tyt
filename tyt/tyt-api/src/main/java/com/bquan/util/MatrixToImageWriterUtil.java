package com.bquan.util;

import javax.imageio.ImageIO; 
import javax.servlet.ServletContext;


import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;

import java.io.File; 
import java.io.OutputStream; 
import java.io.IOException; 
import java.util.Hashtable;
import java.util.UUID;
import java.awt.image.BufferedImage; 
 
//用于生成二维码的的工具类
public final class MatrixToImageWriterUtil { 
 
  private static final int BLACK = 0xFF000000; 
  private static final int WHITE = 0xFFFFFFFF; 
 
  private MatrixToImageWriterUtil() {} 
 
   
  public static BufferedImage toBufferedImage(BitMatrix matrix) { 
    int width = matrix.getWidth(); 
    int height = matrix.getHeight(); 
    BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB); 
    for (int x = 0; x < width; x++) { 
      for (int y = 0; y < height; y++) { 
        image.setRGB(x, y, matrix.get(x, y) ? BLACK : WHITE); 
      } 
    } 
    return image; 
  } 
 
   
  public static void writeToFile(BitMatrix matrix, String format, File file) 
      throws IOException { 
    BufferedImage image = toBufferedImage(matrix); 
    if (!ImageIO.write(image, format, file)) { 
      throw new IOException("Could not write an image of format " + format + " to " + file); 
    } 
  } 
 
   
  public static void writeToStream(BitMatrix matrix, String format, OutputStream stream) 
      throws IOException { 
    BufferedImage image = toBufferedImage(matrix); 
    if (!ImageIO.write(image, format, stream)) { 
      throw new IOException("Could not write an image of format " + format); 
    } 
  }
  
  /**
   * 生成二维码到指定的路径
   * @param text
   * @param width
   * @param height
   * @param path
   * @return
 * @throws WriterException 
 * @throws IOException 
   */
  public static String createCode(String text,int width,int height,ServletContext servletContext,String path) throws WriterException, IOException{
      String format = "png";   //生成的图片的格式
      Hashtable hints= new Hashtable();   
      hints.put(EncodeHintType.CHARACTER_SET, "utf-8");   
      BitMatrix bitMatrix = new MultiFormatWriter().encode(text, BarcodeFormat.QR_CODE, width, height,hints);  
      UUID uuid = UUID.randomUUID();
      File outputFile = new File(servletContext.getRealPath(path)+File.separator+uuid.toString()+"."+format);   
      outputFile.mkdirs();
      MatrixToImageWriterUtil.writeToFile(bitMatrix, format, outputFile);
	  return path+"/"+uuid.toString()+"."+format;
  }
 
  public static void main(String[] args) {
	try {
		String format = "png";   //生成的图片的格式
	      Hashtable hints= new Hashtable();   
	      hints.put(EncodeHintType.CHARACTER_SET, "utf-8");   
	      BitMatrix bitMatrix = new MultiFormatWriter().encode("http://zcr.hb189.cc/zcr.html", BarcodeFormat.QR_CODE, 600, 600,hints);  
	      UUID uuid = UUID.randomUUID();
	      File outputFile = new File("d://"+uuid.toString()+"."+format);   
	      MatrixToImageWriterUtil.writeToFile(bitMatrix, format, outputFile);
		  //return path+"/"+uuid.toString()+"."+format;
	} catch (Exception e) {
		// TODO: handle exception
	}
  }
} 
