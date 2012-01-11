import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;


public class Zombie
{
	public int health = 100;
	public int previoushealth = health;
	public int width = 48, height = 48;
	public int x = 100, y = 100;
	public int previousx = 100, previousy = 100;
	public int 
		centerx = x+(width/2), 
		centery = y+(height/2);
	public Rectangle2D rect;
	public BufferedImage 
		img = new BufferedImage(width, height, BufferedImage.TYPE_4BYTE_ABGR),
		selectedimg;
	public double rotate;
	public double angle;
	public int speed = 1;
	
	
	public Zombie(int newx, int newy, Player newplayer)
	{
		x = newx;
		y = newy;
		rect = new Rectangle(x, y, width, height);
		try
		{
			selectedimg = ImageIO.read(new File("player.png"));
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
	public BufferedImage rotateImage(double angle, BufferedImage image) 
	{
		Graphics2D g = img.createGraphics();
		g.setColor(Color.white);
		g.fillRect(0, 0, width, height);
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
 		g.rotate(Math.toRadians(angle), width/2, height/2);
 		g.drawImage(selectedimg, null, 0, 0);
		g.dispose();
		return img;
	}
	
	public void update(int zombienum)
	{
		centerx = x+(width/2);
		centery = y+(height/2);
		
		
		if(centery > Main.player.centery)
			y -= speed;
		if(centery < Main.player.centery)
			y += speed;
		if(centerx > Main.player.centerx)
			x -= speed;
		if(centerx < Main.player.centerx)
			x += speed;
		
		
		rotate = Math.toDegrees(Math.atan2(Main.player.centerx-centerx, centery-Main.player.centery));	
		previousx = x;
		previousy = y;
		
		//x = (int) (((speed)*Math.cos(rotate)) + x);
		//y = (int) (((speed)*Math.sin(rotate)) + y);

		rect.setRect(x, y, width, height);
		
		Main.pane.setColor(Color.white);
		Main.pane.drawString("health: "+previoushealth, previousx, previousy);
		Main.pane.fillRect(x, y-20, width+20, 20);
		Main.pane.setColor(Color.black);
		Main.pane.drawString("health: "+health,x, y);
		
		
		if(Main.laser != null)
			if(rect.intersectsLine(Main.laser.startx, Main.laser.starty, Main.laser.endx, Main.laser.endy))
			{
				previoushealth = health;
				health -= Main.laser.damage;
				System.out.println(""+health);
			}					
		for(int z = 0; z<Main.bullets.size()-1; z++)
		{
			Bullet bullet = Main.bullets.get(z);
			if(rect.intersectsLine(bullet.x, bullet.y, bullet.previousx, bullet.previousy))
			{
				previoushealth = health;
				health -= bullet.damage;
				System.out.println(""+health);
			}
		}
		if(health <= 0)
			Main.zombies.remove(zombienum);
	}
}
