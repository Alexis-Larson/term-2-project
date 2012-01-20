import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;


public class Player 
{
	public int x = 100, y = 100;
	public double rotate;
	public int health = 200;
	public final int width = 48, height = 48;
	public Rectangle clip = new Rectangle(x, y, width, height);
	public BufferedImage
		Pistolimg,
		SMGimg,
		Assault_rifleimg,
		Machine_gunimg,
		Bolt_action_rifleimg,
		Semi_Auto_Sniperimg,
		selectedimg;
	
	public int 
		centerx = x+(width/2), 
		centery = y+(height/2);
	public final int movespeed = 4;
	protected final int movespeed_sprint = 6;
	
	public int selectedweapon = 0;
	public static final int 
		Pistol = 0,
		SMG	= 1,
		Assault_rifle = 2,
		Machine_gun = 3,
		Bolt_action_rifle = 4,
		Semi_Auto_Sniper = 5;
	
	
	public Player()
	{
		try
		{
			Pistolimg = ImageIO.read(new File("Pistol.png"));
			SMGimg = ImageIO.read(new File("SMG.png"));
			Assault_rifleimg = ImageIO.read(new File("Assault.png"));
			Machine_gunimg = ImageIO.read(new File("Gatling.png"));
			Bolt_action_rifleimg = ImageIO.read(new File("Rifle.png"));
			Semi_Auto_Sniperimg = ImageIO.read(new File("Sniper.png"));
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	public void update()
	{
		clip.setRect(x, y, width, height);
		if(Main.hurttime>20)
		for(int z = 0; z<Main.zombies.size(); z++)
		{
			Zombie zombie = Main.zombies.get(z);
			if(		clip.intersectsLine(zombie.x, zombie.y, zombie.x+Zombie.width, zombie.y)||
					clip.intersectsLine(zombie.x, zombie.y, zombie.x, zombie.y+Zombie.height)||
					clip.intersectsLine(zombie.x+Zombie.width, zombie.y, zombie.x+width, zombie.y+height)||
					clip.intersectsLine(zombie.x+Zombie.width, zombie.y+Zombie.height, zombie.x, zombie.y+height))
			{
				health -= zombie.damage;
				Main.hurttime = 0;
			}
		}
		if(health<0)
			Main.gameover();
	}

}
