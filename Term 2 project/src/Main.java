
import java.awt.BufferCapabilities;
import java.awt.BufferCapabilities.FlipContents;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.ImageCapabilities;
import java.awt.RenderingHints;
import java.awt.RenderingHints.Key;
import java.awt.geom.AffineTransform;
import java.awt.image.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.JButton;

import com.sun.xml.internal.stream.util.BufferAllocator;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

public class Main {
	
	public static Graphics2D pane;
	public static JFrame frame;
	public static BufferStrategy buffer;
	public static Timer timer;
	public static int width, height;
	public static ArrayList<Bullet> bullets = new ArrayList<Bullet>();
	public static ArrayList<Zombie> zombies = new ArrayList<Zombie>();
	public static Player player = new Player();
	
	private static BufferedImage playerimg = new BufferedImage(player.width,player.height,BufferedImage.TYPE_4BYTE_ABGR);
	private static BufferedImage selectedimg;
	
	private static int selectedweapon = Player.Pistol;
	public static Lazer laser;
	protected static int waittime = 31;
	
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
	public static double rotate = 0;
	public static final int
		leftmouse = MouseEvent.BUTTON1,
		middlemouse = MouseEvent.BUTTON2,
		rightmouse = MouseEvent.BUTTON3;
	public static boolean shoot = false;
	
	

	
	public static void main(String[] args) 
	{
		
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		GraphicsDevice[] gs = ge.getScreenDevices();
		frame = new JFrame(gs[0].getDefaultConfiguration());
		frame.setUndecorated(true);
		gs[0].setFullScreenWindow(frame);
		//frame.setSize(720,405);
		addlisteners();
		width = frame.getWidth();
		height = frame.getHeight();
		frame.setVisible(true);
		frame.createBufferStrategy(3);
		buffer = frame.getBufferStrategy();
		setplayerimg(1);
		timer = new Timer(25, new ActionListener()
		{

			public void actionPerformed(ActionEvent arg0)
			{
				draw();				
			}
			
		});
		timer.start();

	}

	protected static void draw()
	{
		waittime++;
		
		pane = (Graphics2D) buffer.getDrawGraphics();
		pane.setBackground(Color.white);
		frame.setBackground(Color.white);
		frame.setForeground(Color.white);
		//pane.setColor(Color.white);
		//pane.fillRect(player.previousx, player.previousy, player.width, player.height);
		movement();
		
		checkcollision();
		
		player.centerx = player.x+(player.width/2);
		player.centery = player.y+(player.height/2);
		rotate = Math.toDegrees(Math.atan2(mousex-player.centerx, player.centery-mousey));	

		if(shoot)
		{
			
			if(waittime>Lazer.waittime)
			{
				laser = new Lazer(mousex, mousey, player, selectedweapon);
				waittime = 0;
			}
			if(waittime>Bullet.getWait(selectedweapon))
			{
				bullets.add(new Bullet(mousex, mousey, player, selectedweapon));
				waittime = 0;
				
			}
			updatebullets();
		}
		
		
     	//pane.setColor(Color.white);
     	//pane.fillRect(player.previousx, -player.previousy, player.width, player.width);
		//try{pane.drawImage(ImageIO.read(new File("test map.png")), 0, -1080, null);}catch (IOException e){e.printStackTrace();}
    	
    	//pane.setColor(Color.black);
    	//pane.fillRect(0, -20, width, 20);
    	//pane.setColor(Color.LIGHT_GRAY);
    	//pane.drawString("Angle: "+rotate, 0, 0);
    	//pane.drawString("Angle2: "+Math.atan2(mousex-player.centerx, mousey-player.centery), 0, -10);
    	//pane.drawString("X: "+Math.cos(Math.atan2(mousex-player.centerx, mousey-player.centery)), 0, 0);
    	//pane.drawString("Y: "+Math.sin(Math.atan2(mousex-player.centerx, mousey-player.centery)), 0, -10);
    	//pane.drawString("Selected weapon: "+player.selectedweapon, 0, -10);
    	//pane.drawString("shoot: "+shoot, 120, -10);
    	
		updatezombies();
     	pane.drawImage(rotateImage(rotate, playerimg), player.x, player.y, null);
     	buffer.show();
  		
		player.previousx = player.x;
		player.previousy = player.y;
		pane.dispose();
		laser = null;
	}
	private static void updatebullets()
	{
		for(int z = 0; z<bullets.size(); z++)
		{
			Bullet bullet = bullets.get(z);
			pane.setColor(Color.blue);
			pane.fillOval((int) bullet.x,(int) bullet.y, bullet.size, bullet.size);
			pane.setColor(Color.white);
			pane.fillOval((int) bullet.previousx,(int) bullet.previousy, bullet.size, bullet.size);
 			bullets.get(z).update();
 			if(bullet.x>width+10 || bullet.x<0-10 || bullet.y<0-10 || bullet.y>height+10)
 				Main.bullets.remove(z);
 			System.out.println(bullets.size());
		}
	}

	public static BufferedImage rotateImage(double angle, BufferedImage image) 
	{
		Graphics2D g = playerimg.createGraphics();
		g.setColor(Color.white);
		g.fillRect(0, 0, player.width, player.height);
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
 		g.rotate(Math.toRadians(angle), player.width/2, player.height/2);
 		g.drawImage(selectedimg, null, 0, 0);
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
				{
					shoot = true;
				}
			}

			@Override
			public void mouseReleased(MouseEvent e)
			{
				int btn = e.getButton();
				if(btn == leftmouse)
					shoot = false;
			}
			
			@Override public void mouseClicked(MouseEvent e)
			{
			}
			@Override public void mouseEntered(MouseEvent e){}
			@Override public void mouseExited(MouseEvent e){}
		});
		frame.addMouseWheelListener(new MouseWheelListener()
		{
			@Override
			public void mouseWheelMoved(MouseWheelEvent evt)
			{
				if(player.selectedweapon + evt.getWheelRotation()>5)
					player.selectedweapon = 0;
				else if(player.selectedweapon + evt.getWheelRotation()<0)
					player.selectedweapon = 5;
				else
					player.selectedweapon += evt.getWheelRotation();
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
			}
			public void keyTyped(KeyEvent evt)
			{
			      int key = evt.getKeyCode();
			      if(key == KeyEvent.VK_T)zombies.add(newzombie());
			}
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

  	  Zombie zombie = new Zombie(100, 100, player);
  	  pane.drawImage(rotateImage(zombie.rotate,zombie.img), zombie.x, zombie.y, null);
  	  
  	  System.out.println("zombie added");
  	  return zombie;
	}
	private static void updatezombies()
	{
		for(int x = 0; x<zombies.size(); x++)
		{
			Zombie zombie = zombies.get(x);
			zombie.update(x);
	     	pane.drawImage(rotateImage(zombie.rotate,zombie.img), zombie.x, zombie.y, null);
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
		
		if(rotate > -45 && rotate < 45)
		{
			//forward = up;
			uppenalty = 1;
			leftpenalty = 1;
			rightpenalty = 1;
			downpenalty = 0;
		}
		else if(rotate > 135 || rotate < -135)
		{
			//forward = down;
			uppenalty = 0;
			leftpenalty = 1;
			rightpenalty = 1;
			downpenalty = 1;
		}
		else if(rotate > -135 && rotate < -45)
		{
			//forward = left;
			uppenalty = 1;
			leftpenalty = 0;
			rightpenalty = 1;
			downpenalty = 1;
		}
		else if(rotate > 45 && rotate < 135)
		{
			//forward = right;
			uppenalty = 1;
			leftpenalty = 1;
			rightpenalty = 0;
			downpenalty = 1;
		}

		if(moveup&&moveleft)
		{
			player.y -= (speed/modify)-uppenalty;
			player.x -= (speed/modify)-leftpenalty;
		}
		else if(moveup&&moveright)
		{
			player.y -= (speed/modify)-uppenalty;
			player.x += (speed/modify)-rightpenalty;					
		}
		else if(movedown&&moveleft)
		{
			player.y += (speed/modify)-downpenalty;
			player.x -= (speed/modify)-leftpenalty;										
		}
		else if(movedown&&moveright)
		{
			player.y += (speed/modify)-downpenalty;
			player.x += (speed/modify)-rightpenalty;										
		}
		else
		{
			if(moveup)player.y -= speed-uppenalty;
			if(movedown)player.y += speed-downpenalty;
			if(moveleft)player.x -= speed-leftpenalty;
			if(moveright)player.x += speed-rightpenalty;					
		}		
	}
	
	protected static void checkcollision()
	{
		
	}
	public static void setplayerimg(int x)
	{
		if(x == 1)selectedimg = player.Pistolimg;
		if(x == 2)selectedimg = player.SMGimg;
		if(x == 3)selectedimg = player.Assault_rifleimg;
		if(x == 4)selectedimg = player.Machine_gunimg;
		if(x == 5)selectedimg = player.Bolt_action_rifleimg;
		if(x == 6)selectedimg = player.Semi_Auto_Sniperimg;		
	}

}
