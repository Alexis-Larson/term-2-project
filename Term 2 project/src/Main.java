
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics2D;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.RenderingHints;
import java.awt.image.*;
import java.util.ArrayList;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.Timer;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.io.File;
import java.io.IOException;

public class Main {
	
	public static Random rand = new Random();
	
	public static Graphics2D pane;
	public static JFrame frame;
	public static BufferStrategy buffer;
	public static Timer timer;
	public static int width, height;
	public static ArrayList<Bullet> bullets = new ArrayList<Bullet>();
	public static ArrayList<Zombie> zombies = new ArrayList<Zombie>();
	public static Player player = new Player();
	
	private static BufferedImage playerimg = new BufferedImage(player.width+2,player.height+2,BufferedImage.TYPE_4BYTE_ABGR);
	private static BufferedImage selectedimg;
	
	private static int selectedweapon = Player.Pistol;
	public static Lazer lazer;
	protected static int waittime = 0;
	public static int hurttime = 0;
	
	public static int numOFzombieskilled = 0;
	private static boolean paused = false;
	
	//movement related
	public static boolean 
		moveup = false,
		movedown = false,
		moveleft = false,
		moveright = false,
		upleftkey = false,
		uprightkey = false,
		downleftkey = false,
		downrightkey = false,
		sprintkey = false;
	
	//control related
	public static final int 
		keyup = KeyEvent.VK_E,
		keydown = KeyEvent.VK_D,
		keyleft = KeyEvent.VK_S,
		keyright = KeyEvent.VK_F,
		keylevelup = KeyEvent.VK_T,
		keyleveldown = KeyEvent.VK_G;
	public static boolean keypress = false;
	
	//mouse:
	public static int mousex, mousey;
	public static final int
		leftmouse = MouseEvent.BUTTON1,
		middlemouse = MouseEvent.BUTTON2,
		rightmouse = MouseEvent.BUTTON3;
	public static boolean shoot = false;
	
	
	public static Boolean 
		Zombie_Regular,
		Zombie_Fast,
		Zombie_Large,
		Zombie_Poison,
		Zombie_Brute,
		Zombie_Witch;

	private static BufferedImage map;
	public static int zombiehealthmodify;

	private static BufferedImage GUI;

	public static int numOFzombiesonfield = 0;
	private static int maxnumOFzombies = 10;


	
	public Main(int newhealthmodifier, int mapnum, Boolean newzombie_Regular,
			Boolean newzombie_Fast, Boolean newzombie_Large,Boolean newzombie_Poison,
			Boolean newzombie_Brute, Boolean newzombie_Witch)
	{		
		zombiehealthmodify = newhealthmodifier;
		
		Zombie_Regular = newzombie_Regular;
		Zombie_Fast = newzombie_Fast;
		Zombie_Large = newzombie_Large;
		Zombie_Poison = newzombie_Poison;
		Zombie_Brute = newzombie_Brute;
		Zombie_Witch = newzombie_Witch;
		
		try
		{
			switch(mapnum)
			{
				case 0:
					map = ImageIO.read(new File("Baseball.png"));
					break;
				case 1:
					map = ImageIO.read(new File("Terminal.png"));
					break;
				case 2:
					map = ImageIO.read(new File("road.png"));
					break;
				case 3:
					map = ImageIO.read(new File("Field.png"));
					break;
			}
		}
		catch (IOException e){e.printStackTrace();}
		
		try{GUI = ImageIO.read(new File("GUI.png"));}catch (IOException e){e.printStackTrace();}
		
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		GraphicsDevice[] gs = ge.getScreenDevices();
		frame = new JFrame(gs[0].getDefaultConfiguration());
		frame.setUndecorated(true);
		gs[0].setFullScreenWindow(frame);
		addlisteners();
		width = frame.getWidth();
		height = frame.getHeight();
		frame.setVisible(true);
		frame.setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
		frame.createBufferStrategy(3);
		buffer = frame.getBufferStrategy();
		setplayerimg(1);
		pane = (Graphics2D) buffer.getDrawGraphics();
		lazer = new Lazer(player.x, player.y, player, 0);
		timer = new Timer(15, new ActionListener()
		{

			public void actionPerformed(ActionEvent arg0)
			{
				draw();				
			}
			
		});
		timer.start();

	}

	public static void main(String[] args) 
	{
		new Main(0, 0, true, true, true, true, true, true);
	}

	protected static void draw()
	{
		waittime++;
		hurttime++;
		
		while(numOFzombiesonfield<maxnumOFzombies)
			zombies.add(newzombie());
		
		
		pane = (Graphics2D) buffer.getDrawGraphics();
		pane.setColor(Color.white);
		pane.drawImage(map, null, 0, 0);
		
		movement();
		
		
		player.centerx = player.x+(player.width/2);
		player.centery = player.y+(player.height/2);
		player.rotate = Math.toDegrees(Math.atan2(mousex-player.centerx, player.centery-mousey));	

		int size = 0;
		if(shoot)
		{
			if(waittime>Lazer.waittime)
			{
				lazer = new Lazer(mousex, mousey, player, selectedweapon);
				pane.setColor(Color.red);
				size = (((int) lazer.distance/lazer.accuracymodifier)+1)*2;
				pane.drawRect(mousex-(size/2), mousey-(size/2), size, size);
				pane.drawLine(lazer.startx, lazer.starty, lazer.endx, lazer.endy);
				waittime = 0;
			}
		}
		    	
		updatezombies();
     	pane.drawImage(rotateImage(player.rotate, playerimg), player.x, player.y, null);
     	
     	player.update();
     	updateGUI();
     	
     	buffer.show();
  		
		player.previousx = player.x;
		player.previousy = player.y;
		pane.dispose();
		lazer = null;
	}
	private static void updateGUI()
	{
		pane.drawImage(GUI, null, 0, 0);
    	pane.setColor(Color.LIGHT_GRAY);
    	String weapon = "";
    	switch(selectedweapon)
    	{
    		case Bullet.Pistol:
    			weapon = "Pistol";
    			break;
    		case Bullet.Assault_rifle:
    			weapon = "Assault rifle";
    			break;
    		case Bullet.Bolt_action_rifle:
    			weapon = "Bolt action rifle";
    			break;
    		case Bullet.Machine_gun:
    			weapon = "Machine gun";
    			break;
    		case Bullet.Semi_auto_sniper:
    			weapon = "Semi auto sniper";
    			break;
    		case Bullet.SMG:
    			weapon = "SMG";
    			break;
    	}
    	pane.drawString("Selected weapon: "+weapon, 0, height-10);
    	
    	pane.setColor(Color.red);
    	pane.fillRect(0, height-20, player.health, 20);
    	pane.drawString("X: "+mousex, 500,height-10);
    	pane.drawString("Y: "+mousey, 500,height);
    	
	}

	public static BufferedImage rotateImage(double angle, BufferedImage image) 
	{
		try{playerimg = ImageIO.read(new File("transparent.png"));}
		catch (IOException e){e.printStackTrace();}
		Graphics2D g = playerimg.createGraphics();
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
 		g.rotate(Math.toRadians(angle), player.width/2, player.height/2);
 		g.drawImage(selectedimg, null,0, 0);
		g.dispose();
		return playerimg;
	}

	
	private static void addlisteners()
	{
		frame.addMouseListener(new MouseListener()
		{
			@Override
			public void mousePressed(MouseEvent e)
			{
				int btn = e.getButton();
				if(btn == leftmouse)
					shoot = true;

			}

			@Override
			public void mouseReleased(MouseEvent e)
			{
				int btn = e.getButton();
				if(btn == leftmouse)
					shoot = false;
			}
			
			@Override public void mouseClicked(MouseEvent e){}
			@Override public void mouseEntered(MouseEvent e){}
			@Override public void mouseExited(MouseEvent e){}
		});
		frame.addMouseWheelListener(new MouseWheelListener()
		{
			@Override
			public void mouseWheelMoved(MouseWheelEvent evt)
			{
				if(selectedweapon + evt.getWheelRotation()>5)
					selectedweapon = 0;
				else if(selectedweapon + evt.getWheelRotation()<0)
					selectedweapon = 5;
				else
					selectedweapon += evt.getWheelRotation();
				
				setplayerimg(selectedweapon);
			}
			
		});
		frame.addMouseMotionListener(new MouseMotionListener()
		{
			@Override
			public void mouseMoved(MouseEvent evt)
			{
				mousex = evt.getX();
				mousey = evt.getY();
			}
			@Override public void mouseDragged(MouseEvent evt)
			{
				mousex = evt.getX();
				mousey = evt.getY();
			}
			
		});
		frame.addKeyListener(new KeyListener()
		{
			public void keyPressed(KeyEvent evt) 
			{
			      int key = evt.getKeyCode();
			      if(key == KeyEvent.VK_ESCAPE) System.exit(0);
			      if(key == keyup)moveup = true;
			      if(key == keydown)movedown = true;
			      if(key == keyleft)moveleft = true;
			      if(key == keyright)moveright = true;
			      if(key == KeyEvent.VK_SHIFT)sprintkey=true;
			      if(key == KeyEvent.VK_T)zombies.add(newzombie());
			      if(key == KeyEvent.VK_Q)pausegame();				
			      
			}
			public void keyTyped(KeyEvent evt){}
			public void keyReleased(KeyEvent evt)
			{
			      int key = evt.getKeyCode();
			      if(key == keyup)moveup = false;
			      if(key == keydown)movedown = false;
			      if(key == keyleft)moveleft = false;
			      if(key == keyright)moveright = false;
			      if(key == KeyEvent.VK_SHIFT)sprintkey = false;			      
			}
		});
	}

	private static Zombie newzombie()
	{
		int x = rand.nextInt(width-Zombie.width);
		int y = rand.nextInt(height-Zombie.height);
		int zombietype = 0;
		boolean notfound = true;
		if(!Zombie_Regular && !Zombie_Fast && !Zombie_Brute && !Zombie_Large && !Zombie_Poison && Zombie_Witch)
		{
			zombietype = Zombie.Zombie_Witch;
		}
		else 
		while(notfound)
		{
			int type = rand.nextInt(5);
			switch(type)
			{
				case Zombie.Zombie_Regular:
					if(Zombie_Regular)
					{
						zombietype = Zombie.Zombie_Regular;
						notfound = false;
					}
					break;
				case Zombie.Zombie_Fast:
					if(Zombie_Fast)
					{
						zombietype = Zombie.Zombie_Fast;
						notfound = false;
					}
					break;
				case Zombie.Zombie_Large:
					if(Zombie_Large)
					{
						zombietype = Zombie.Zombie_Large;
						notfound = false;
					}
					break;
				case Zombie.Zombie_Poison:
					if(Zombie_Poison)
					{
						zombietype = Zombie.Zombie_Poison;
						notfound = false;
					}
					break;
				case Zombie.Zombie_Brute:
					if(Zombie_Brute)
					{
						zombietype = Zombie.Zombie_Brute;
						notfound = false;
					}
					break;
			}
		}
		Zombie zombie = new Zombie(x, y, zombietype);
  	  	pane.drawImage(zombie.rotateImage(zombie.rotate), zombie.x, zombie.y, null);
  	  
  	  	System.out.println("zombie added");
  	  	numOFzombiesonfield++;
  	  	return zombie;
	}
	private static void updatezombies()
	{
		for(int x = 0; x<zombies.size(); x++)
		{
			Zombie zombie = zombies.get(x);
			zombie.update(x);
	     	pane.drawImage(zombie.rotateImage(zombie.rotate), zombie.x, zombie.y, null);
		}
		
	}
	private static void movement()
	{
		int speed;
		if(sprintkey)speed = player.movespeed_sprint;
		else speed = player.movespeed;
		int
			uppenalty = 1,
			downpenalty = 1,
			leftpenalty = 1,
			rightpenalty = 1;
		double modify = 1;	
		
		if(player.rotate > -45 && player.rotate < 45)
		{
			//forward = up;
			uppenalty = 1;
			leftpenalty = 1;
			rightpenalty = 1;
			downpenalty = 0;
		}
		else if(player.rotate > 135 || player.rotate < -135)
		{
			//forward = down;
			uppenalty = 0;
			leftpenalty = 1;
			rightpenalty = 1;
			downpenalty = 1;
		}
		else if(player.rotate > -135 && player.rotate < -45)
		{
			//forward = left;
			uppenalty = 1;
			leftpenalty = 0;
			rightpenalty = 1;
			downpenalty = 1;
		}
		else if(player.rotate > 45 && player.rotate < 135)
		{
			//forward = right;
			uppenalty = 1;
			leftpenalty = 1;
			rightpenalty = 0;
			downpenalty = 1;
		}

		if(moveup&&moveleft&&player.y-((speed/modify)-uppenalty) > 0&& player.x -((speed/modify)-leftpenalty) >0 )
		{
			player.y -= (speed/modify)-uppenalty;
			player.x -= (speed/modify)-leftpenalty;
		}
		else if(moveup&&moveright&&player.y-((speed/modify)-uppenalty) > 0&& player.x -((speed/modify)-leftpenalty) <width-player.width)
		{
			player.y -= (speed/modify)-uppenalty;
			player.x += (speed/modify)-rightpenalty;					
		}
		else if(movedown&&moveleft&&player.y+((speed/modify)-uppenalty) < height-player.height&& player.x -((speed/modify)-leftpenalty) >0)
		{
			player.y += (speed/modify)-downpenalty;
			player.x -= (speed/modify)-leftpenalty;										
		}
		else if(movedown&&moveright&&player.y+((speed/modify)-uppenalty) < height-player.height&& player.x -((speed/modify)-leftpenalty) <width-player.width)
		{
			player.y += (speed/modify)-downpenalty;
			player.x += (speed/modify)-rightpenalty;										
		}
		else
		{
			if(moveup&&player.y-((speed/modify)-uppenalty) > 0)player.y -= speed-uppenalty;
			if(movedown&&player.y+((speed/modify)-uppenalty) < height-player.height)player.y += speed-downpenalty;
			if(moveleft&& player.x -((speed/modify)-leftpenalty) >0)player.x -= speed-leftpenalty;
			if(moveright&& player.x -((speed/modify)-leftpenalty) <width-player.width)player.x += speed-rightpenalty;					
		}		
	}
	
	public static void setplayerimg(int x)
	{
		if(x == 0)selectedimg = player.Pistolimg;
		if(x == 1)selectedimg = player.SMGimg;
		if(x == 2)selectedimg = player.Assault_rifleimg;
		if(x == 3)selectedimg = player.Machine_gunimg;
		if(x == 4)selectedimg = player.Bolt_action_rifleimg;
		if(x == 5)selectedimg = player.Semi_Auto_Sniperimg;		
	}

	protected static void pausegame()
	{
		BufferedImage controls = new BufferedImage(width, height, BufferedImage.TYPE_4BYTE_ABGR);
		try{controls = ImageIO.read(new File("Controls.png"));}
		catch (IOException e){e.printStackTrace();}
		pane = (Graphics2D) buffer.getDrawGraphics();
		pane.drawImage(controls, null, 0, 0);
		buffer.show();
		buffer.show();
		if(paused)
		{
			paused = false;
			timer.start();
		}
		else
		{
			paused = true;
			timer.stop();
		}
	}
	public static void gameover()
	{
		timer.stop();
		BufferedImage gameover = new BufferedImage(width, height, BufferedImage.TYPE_4BYTE_ABGR);
		try{gameover = ImageIO.read(new File("GAME OVER.png"));}
		catch (IOException e){e.printStackTrace();}
		pane = (Graphics2D) buffer.getDrawGraphics();
		pane.drawImage(gameover, null, 0, 0);
		buffer.show();
	}
}
