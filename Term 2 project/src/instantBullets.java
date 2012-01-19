import java.awt.Color;
import java.util.Random;

public class instantBullets
{
	public static int waittime;
	public int damage;
	public int startx, starty;
	public int length;
	public double angle;
	public int endx, endy;
	private Random rand = new Random();
	private int accuracy;
	public int speed = 0;
	public double x;
	public double y;
	public double previousx;
	public double previousy;
	
	public instantBullets(int mousex, int mousey, Player player, int selectedweapon)
	{
		switch(selectedweapon)
		{
			case Bullet.Pistol:
				waittime = 15;
				damage = 5;
				accuracy = 5;
				break;
			case Bullet.SMG:
				waittime = 4;
				damage = 3;
				accuracy = 20;
				break;
			case Bullet.Assault_rifle:
				waittime = 8;
				damage = 8;
				accuracy = 10;
				break;
			case Bullet.Machine_gun:
				waittime = 2;
				damage = 15;
				accuracy = 40;
				break;
			case Bullet.Bolt_action_rifle:
				waittime = 25;
				damage = 100;
				accuracy = 1;
				break;
			case Bullet.Semi_auto_sniper:
				waittime = 18;
				damage = 60;
				accuracy = 5;
				break;
		}
		
		
		startx = player.centerx;
		starty = player.centery;
		previousx = startx;
		previousy = starty;
		int xchange = startx - mousex;
		int ychange = starty - mousey;
		double distance = Math.sqrt(Math.pow(Math.abs(xchange), 2.0)+Math.pow(Math.abs(ychange), 2.0));
		if(distance < 30)
			accuracy--;
		if(distance < 20)
			accuracy--;
		if(distance < 10)
			accuracy--;
		int xerror = rand.nextInt(accuracy);
		int yerror = rand.nextInt(accuracy);
		if(rand.nextBoolean())
			endx = mousex + xerror;
		else
			endx = mousex - xerror;			
		if(rand.nextBoolean())
			endy = mousey + yerror;
		else
			endy = mousey - yerror;
		
		angle = Math.atan2(endy-starty, endx-startx);
		x = ((speed)*Math.cos(angle));
		y = ((speed)*Math.sin(angle));	
	}
}
