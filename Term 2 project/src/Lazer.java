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
	public double distance;
	public static int
	accuracymodifier,
	pistolaccuracymodifier = 100,
	smgaccuracymodifier = 10,
	assaultriflepistolaccuracymodifier = 15,
	machinegunaccuracymodifier = 8,
	boltactionrifleaccuracymodifier = 50,
	semiautosniperaccuracymodifier = 25;
		
	public Lazer(int mousex, int mousey, Player player, int selectedweapon)
	{
		switch(selectedweapon)
		{
			case Bullet.Pistol:
				waittime = 13;
				damage = 5;
				accuracymodifier = pistolaccuracymodifier;
				break;
			case Bullet.SMG:
				waittime = 4;
				damage = 3;
				accuracymodifier = smgaccuracymodifier;
				break;
			case Bullet.Assault_rifle:
				waittime = 8;
				damage = 8;
				accuracymodifier = assaultriflepistolaccuracymodifier;
				break;
			case Bullet.Machine_gun:
				waittime = 2;
				damage = 15;
				accuracymodifier = 	machinegunaccuracymodifier;
				break;
			case Bullet.Bolt_action_rifle:
				waittime = 100;
				damage = 100;
				accuracymodifier = boltactionrifleaccuracymodifier;
				break;
			case Bullet.Semi_auto_sniper:
				waittime =  18;
				damage = 60;
				accuracymodifier = semiautosniperaccuracymodifier;
				break;
		}
		
		
		startx = player.centerx;
		starty = player.centery;
		int xchange = startx - mousex;
		int ychange = starty - mousey;
		distance = Math.sqrt(Math.pow(Math.abs(xchange), 2.0)+Math.pow(Math.abs(ychange), 2.0));
		int xerror = rand.nextInt(((int) distance/accuracymodifier)+1);
		int yerror = rand.nextInt(((int) distance/accuracymodifier)+1);
		if(rand.nextBoolean())
			endx = mousex + xerror;
		else
			endx = mousex - xerror;			
		if(rand.nextBoolean())
			endy = mousey + yerror;
		else
			endy = mousey - yerror;			
	}
	public static void changeaccuracy(int selectedweapon, int change)
	{
		switch(selectedweapon)
		{
			case Bullet.Pistol:
				if(pistolaccuracymodifier + change > 0 && pistolaccuracymodifier + change < 100)
					pistolaccuracymodifier += change;			
				break;
			case Bullet.SMG:
				if(smgaccuracymodifier + change > 0 && smgaccuracymodifier + change < 100)
					smgaccuracymodifier += change;
				break;
			case Bullet.Assault_rifle:
				if(assaultriflepistolaccuracymodifier + change > 0 && assaultriflepistolaccuracymodifier + change < 100)
					assaultriflepistolaccuracymodifier += change;
				break;
			case Bullet.Machine_gun:
				if(machinegunaccuracymodifier + change > 0 && machinegunaccuracymodifier + change < 100)
					machinegunaccuracymodifier += change;
				break;
			case Bullet.Bolt_action_rifle:
				if(boltactionrifleaccuracymodifier + change > 0 && boltactionrifleaccuracymodifier + change < 100)
					boltactionrifleaccuracymodifier += change;
				break;
			case Bullet.Semi_auto_sniper:
				if(semiautosniperaccuracymodifier + change > 0 && semiautosniperaccuracymodifier + change < 100)
					semiautosniperaccuracymodifier += change;
				break;
		}		
	}
	public static int getaccuracy(int selectedweapon)
	{
		int accuracy = 0;
		switch(selectedweapon)
		{
			case Bullet.Pistol:
				accuracy = pistolaccuracymodifier;
			case Bullet.SMG:
				accuracy = smgaccuracymodifier;
			case Bullet.Assault_rifle:
				accuracy = assaultriflepistolaccuracymodifier;
			case Bullet.Machine_gun:
				accuracy = machinegunaccuracymodifier;
			case Bullet.Bolt_action_rifle:
				accuracy = boltactionrifleaccuracymodifier;
			case Bullet.Semi_auto_sniper:
				accuracy = semiautosniperaccuracymodifier;
		}
		return accuracy;		
		
	}
}
