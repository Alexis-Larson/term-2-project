import java.awt.Color;

public class Lazer
{
	public static int waittime;
	public int damage;
	public int startx, starty;
	public int size;
	public double angle;
	public int endx, endy;
	
	public Lazer(int mousex, int mousey, Player player, int selectedweapon)
	{
		switch(selectedweapon)
		{
			case Player.Pistol:
				damage = 5;
				waittime = 25;
				break;
		}
		
		
		startx = player.centerx;
		starty = player.centery;
		endx = mousex;
		endy = mousey;
		Main.pane.setColor(Color.red);
		Main.pane.drawLine(startx, starty, endx, endy);		
	}
}
