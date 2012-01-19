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
	public static int 
		width = 48, 
		height = 48;
	public int 
		x = 100, 
		y = 100;
	public int 
		previousx = 100, 
		previousy = 100;
	public int 
		centerx = x+(width/2), 
		centery = y+(height/2);
	public Rectangle2D rect;
	public BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_4BYTE_ABGR);
	public double rotate;
	public double angle;
	public int speed = 1;
	
	public static final int 
	Zombie_Regular = 0,
	Zombie_Fast = 1,
	Zombie_Large = 2,
	Zombie_Poison = 3,
	Zombie_Brute = 4,
	Zombie_Witch = 5;

	
	public Zombie(int newx, int newy, Player newplayer, int zombietype)
	{
		x = newx;
		y = newy;
		rect = new Rectangle(x, y, width, height);
		try
		{
			switch(zombietype)
			{
				case Zombie_Regular:
					img = ImageIO.read(new File("player.png"));
					
					break;
				case Zombie_Fast:
					img = ImageIO.read(new File("player.png"));
					
					break;
				case Zombie_Large:
					img = ImageIO.read(new File("player.png"));
					
					break;
				case Zombie_Poison:
					img = ImageIO.read(new File("player.png"));
					
					break;
				case Zombie_Brute:
					img = ImageIO.read(new File("player.png"));
					
					break;
				case Zombie_Witch:
					img = ImageIO.read(new File("player.png"));
					
					break;
			}
		}
		catch (IOException e){e.printStackTrace();}
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
		//Main.pane.drawString("health: "+previoushealth, previousx, previousy);
		//Main.pane.fillRect(x, y-20, width+20, 20);
		Main.pane.setColor(Color.green);
		Main.pane.drawString("health: "+health,x, y);
		
		
		if(Main.lazer != null)
			if(rect.intersectsLine(Main.lazer.startx, Main.lazer.starty, Main.lazer.endx, Main.lazer.endy))
			{
				previoushealth = health;
				health -= Main.lazer.damage;
				System.out.println(""+health);
			}					
		for(int z = 0; z<Main.bullets.size()-1; z++)
		{
			Bullet bullet = Main.bullets.get(z);
			if(rect.intersectsLine(bullet.x, bullet.y, bullet.previousx, bullet.previousy) || rect.contains(bullet.x, bullet.y))
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
