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
	public double previousx = x;
	public double previousy = y;
	
	public final int 
	Pistol = 0,
	SMG	= 1,
	Assault_rifle = 2,
	Machine_gun = 3,
	Bolt_action_rifle = 4,
	Semi_Auto_Sniper = 5;
	
	public Bullet(int mousex, int mousey, Player player, int selectedweapon)
	{
		startx = player.centerx;
		starty = player.centery;
		angle = Math.atan2(mousey-starty, mousex-startx);
		x = ((speed)*Math.cos(angle));
		y = ((speed)*Math.sin(angle));
		switch(selectedweapon)
		{
			case Pistol:
				damage = 5;
				break;
			case SMG:
				break;
			case Assault_rifle:
				break;
		}
		size = 4;

		
	}
	public void update()
	{
		speed += 5;
		previousx = x;
		previousy = y;
		x = ((speed)*Math.cos(angle)) + startx;
		y = ((speed)*Math.sin(angle)) + starty;
	}
	public static int getWait(int selectedweapon)
	{
		switch(selectedweapon)
		{
			case 0:
				
				break;
		}
		return selectedweapon;
	}

}
