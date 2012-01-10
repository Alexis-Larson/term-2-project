import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;


public class Player 
{
	public int x = 100, y = 100;
	public int previousx = 100, previousy = 100;
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
	public final int movespeed = 3;
	protected final int movespeed_sprint = 5;
	
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
			Pistolimg = ImageIO.read(new File("player.png"));
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		
	}

}
