/**
 * @Author Kushal Paudyal
 * www.sanjaal.com/java
 * Last Modified On 2009-10-08
 *
 * Using Graphics 2D To Rotate An Image
 */
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
 
import javax.imageio.ImageIO;
 
public class ImageRotator {
	
	private static BufferedImage 
		dimg,
		img;
	public ImageRotator(BufferedImage img)
	{
		dimg = new BufferedImage(img.getWidth(),img.getHeight(),BufferedImage.TYPE_4BYTE_ABGR);
	}
	public BufferedImage rotateImage(double angle) 
	{
		Graphics2D g = dimg.createGraphics();
		g.setColor(Color.white);
		g.fillRect(0, 0, img.getWidth(), img.getHeight());
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
 		g.rotate(Math.toRadians(angle), img.getWidth()/2, img.getHeight()/2);
 		g.drawImage(img, null, 0, 0);
		g.dispose();
		return dimg;
	}

}