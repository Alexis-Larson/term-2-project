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
		angle = Math.atan2(mousey-starty, mousex-startx);
		x = ((speed)*Math.cos(angle));
		y = ((speed)*Math.sin(angle));
		switch(selectedweapon)
		{
			case Pistol:
				waittime = 20;
				damage = 5;
				break;
			case SMG:
				waittime = 6;
				damage = 3;
				break;
			case Assault_rifle:
				waittime = 10;
				damage = 8;
				break;
			case Machine_gun:
				waittime = 1;
				damage = 15;
				break;
			case Bolt_action_rifle:
				waittime = 1;
				damage = 100;
				break;
			case Semi_auto_sniper:
				waittime = 1;
				damage = 60;
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
