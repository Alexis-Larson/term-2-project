import java.awt.Color;
import java.util.Random;

public class Lazer
{
	public static int waittime;
	public int damage;
	public int startx, starty;
	public int size;
	public double angle;
	public int endx, endy;
	private Random rand = new Random();
	
	public Lazer(int mousex, int mousey, Player player, int selectedweapon)
	{
		switch(selectedweapon)
		{
			case Bullet.Pistol:
				waittime = 15;
				damage = 5;
				break;
			case Bullet.SMG:
				waittime = 4;
				damage = 3;
				break;
			case Bullet.Assault_rifle:
				waittime = 8;
				damage = 8;
				break;
			case Bullet.Machine_gun:
				waittime = 2;
				damage = 15;
				break;
			case Bullet.Bolt_action_rifle:
				waittime = 25;
				damage = 100;
				break;
			case Bullet.Semi_auto_sniper:
				waittime = 18;
				damage = 60;
				break;
		}
		
		
		startx = player.centerx;
		starty = player.centery;
		int xerror = rand.nextInt(5);
		int yerror = rand.nextInt(5);
		if(rand.nextBoolean())
			endx = mousex + xerror;
		else
			endx = mousex - xerror;			
		if(rand.nextBoolean())
			endy = mousey + yerror;
		else
			endy = mousey - yerror;			
		Main.pane.setColor(Color.red);
		Main.pane.drawLine(startx, starty, endx, endy);		
	}
}
