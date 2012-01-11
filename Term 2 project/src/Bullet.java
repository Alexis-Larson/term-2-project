import java.util.Random;

public class Bullet
{
	public int damage;
	public int startx;
	public int starty;
	public int size;
	public int speed = 0;
	public double angle;
	public double x;
	public double y;
	public double previousx;
	public double previousy;
	public static int waittime;
	private Random rand = new Random();
	private int accuracy;
	private int endx, endy;

	public static final int 
	Pistol = 0,
	SMG	= 1,
	Assault_rifle = 2,
	Machine_gun = 3,
	Bolt_action_rifle = 4,
	Semi_auto_sniper = 5;
	
	public Bullet(int mousex, int mousey, Player player, int selectedweapon)
	{
		startx = player.centerx;
		starty = player.centery;
		previousx = startx;
		previousy = starty;
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
		size = 4;

		
	}
	public void update()
	{
		speed += 50;
		previousx = x;
		previousy = y;
		x = ((speed)*Math.cos(angle)) + startx;
		y = ((speed)*Math.sin(angle)) + starty;
	}
	public static int getWait(int selectedweapon)
	{
		return waittime;
	}

}
