import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JMenuItem;
import javax.swing.JMenuBar;
import javax.swing.JSlider;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JToggleButton;
import javax.swing.JRadioButton;
import javax.swing.JCheckBox;
import java.awt.Color;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Panel;
import java.awt.Choice;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JMenu;


public class Start extends JFrame {
	public Boolean Zombie_Regular = false;
	public Boolean Zombie_Normal = false;
	public Boolean Zombie_Fast = false;
	public Boolean Zombie_Large = false;
	public Boolean Zombie_Poison = false;
	public Boolean Zombie_Brute = false;
	public Boolean Zombie_Witch = false;
	
	public Boolean Parking_Lot = false;
	public Boolean Baseball = false;
	public Boolean Field = false;
	public Boolean Terminal = false;
	public Boolean Roadside = false;

	public int difficulty = 50;
	
	public Boolean alreadychosen = false;
	public Boolean errortime = false;
	
	private JPanel contentPane;
	private Panel panel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Start frame = new Start();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Start() {
		setTitle("Project Urban Undead");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 583, 449);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(240, 240, 240));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnStart = new JButton("Start");
		btnStart.addActionListener(new ActionListener() {
			
			
			public void actionPerformed(ActionEvent e) {
				if(!Zombie_Regular && !Zombie_Fast && !Zombie_Brute && !Zombie_Witch && !Zombie_Large){
					//show error
					ErrorZombies EZ = new ErrorZombies();
					EZ.setVisible(true);
				}else{
					if(errortime = true){
						//show error again
						ErrorMaps em = new ErrorMaps();
						em.setVisible(true);
						
						
					}
					//Code for start Needs to be here!!!!!
					
					
					
					
					//start game
					
					
					
					
					//
				}
				
			}
		});
		
		
		
		
		
		btnStart.setBounds(468, 377, 89, 23);
		contentPane.add(btnStart);
		
		final JLabel lblComment = new JLabel("Normal");
		lblComment.setBounds(20, 246, 133, 38);
		contentPane.add(lblComment);
		
		final JSlider slider = new JSlider();
		slider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				if(slider.getValue() > 90){
					lblComment.setText("You're Insane");
					difficulty = slider.getValue();
				}else{
					if(slider.getValue()>80){
						lblComment.setText("Nightmare");
						difficulty = slider.getValue();
					}else{
						if(slider.getValue()>60){
							lblComment.setText("Hard");
							difficulty = slider.getValue();
						}else{
							
						
						if(slider.getValue()>=40){
							lblComment.setText("Normal");
							difficulty = slider.getValue();
						}else{
							if(slider.getValue()>30){
								lblComment.setText("Easy");
								difficulty = slider.getValue();
							}else{
								if(slider.getValue()>10){
									lblComment.setText("Super Easy");
									difficulty = slider.getValue();
								}else{
									lblComment.setText("You're Kidding, Right?");
									difficulty = slider.getValue();
									//prevent 0
									difficulty++;
								}
							}
						}
					}
					
				}
			}	
				
			}
		});
		slider.setBounds(10, 217, 143, 23);
		contentPane.add(slider);
		
		JLabel lblDiffuculty = new JLabel("Diffuculty Select");
		lblDiffuculty.setFont(new Font("Arial", Font.PLAIN, 18));
		lblDiffuculty.setBounds(10, 180, 143, 38);
		contentPane.add(lblDiffuculty);
		
		JCheckBox chkRegular = new JCheckBox("Regular");
		chkRegular.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(Zombie_Regular){
				Zombie_Regular = false;
				}else{
					Zombie_Regular = true;
				}
			}
		});
		chkRegular.setBounds(191, 219, 80, 23);
		contentPane.add(chkRegular);
		
		JCheckBox chkFast = new JCheckBox("Fast");
		chkFast.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				if(Zombie_Fast){
					Zombie_Fast = false;
					}else{
						Zombie_Fast = true;
					}
			}
		});
		chkFast.setBounds(282, 219, 60, 23);
		contentPane.add(chkFast);
		
		JCheckBox chkLarge = new JCheckBox("Large");
		chkLarge.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(Zombie_Large){
					Zombie_Large = false;
					}else{
						Zombie_Large = true;
					}
			}
		});
		chkLarge.setBounds(191, 278, 89, 23);
		contentPane.add(chkLarge);
		
		JCheckBox chkPoison = new JCheckBox("Poison");
		chkPoison.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(Zombie_Poison){
					Zombie_Poison = false;
					}else{
						Zombie_Poison = true;
					}
			}
		});
		chkPoison.setBounds(191, 249, 67, 23);
		contentPane.add(chkPoison);
		
		JCheckBox chkBrute = new JCheckBox("Brute");
		chkBrute.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(Zombie_Brute){
					Zombie_Brute = false;
					}else{
						Zombie_Brute = true;
					}
			}
		});
		chkBrute.setBounds(282, 249, 60, 23);
		contentPane.add(chkBrute);
		
		JCheckBox chkWitch = new JCheckBox("Witch");
		chkWitch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(Zombie_Witch){
					Zombie_Witch = false;
					}else{
						Zombie_Witch= true;
					}
			}
		});
		chkWitch.setBounds(282, 278, 73, 23);
		contentPane.add(chkWitch);
		
		JLabel lblZombieTypes = new JLabel("Zombie Types");
		lblZombieTypes.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblZombieTypes.setBounds(195, 185, 151, 26);
		contentPane.add(lblZombieTypes);
		
		JLabel lblNewLabel = new JLabel("Project Urban Undead");
		lblNewLabel.setFont(new Font("MS UI Gothic", Font.PLAIN, 28));
		lblNewLabel.setBounds(156, 11, 272, 53);
		contentPane.add(lblNewLabel);
		
		JLabel lblCreatedByBen = new JLabel("Created By Peter Larson and Ben Zhang");
		lblCreatedByBen.setFont(new Font("Century Gothic", Font.PLAIN, 15));
		lblCreatedByBen.setBounds(132, 75, 312, 38);
		contentPane.add(lblCreatedByBen);
		
		JLabel lblArea = new JLabel("Area");
		lblArea.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblArea.setBounds(411, 185, 80, 26);
		contentPane.add(lblArea);
		
		panel = new Panel();
		panel.setBounds(378, 206, 143, 152);
		contentPane.add(panel);
		
		JRadioButton rdbtnParkingLot = new JRadioButton("Parking Lot");
		rdbtnParkingLot.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(Parking_Lot){
					Parking_Lot = false;
					}else{
						Parking_Lot= true;
					}
				if(alreadychosen){
					errortime  = true;
				}
			alreadychosen=true;
			}
		});
		
		JRadioButton rdbtnBaseballStadium = new JRadioButton("Baseball Stadium");
		rdbtnBaseballStadium.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(Baseball){
					Baseball = false;
					}else{
						Baseball = true;
					}
				if(alreadychosen){
					errortime  = true;
				}
			alreadychosen=true;
			}
		});
		
		JRadioButton rdbtnTerminal = new JRadioButton("Terminal");
		rdbtnTerminal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(Terminal){
					Terminal = false;
					}else{
						Terminal = true;
					}
				if(alreadychosen){
					errortime  = true;
				}
			alreadychosen=true;
			}
		});
		
		JRadioButton rdbtnRoadSide = new JRadioButton("Road Side");
		rdbtnRoadSide.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(Roadside){
					Roadside = false;
				}else{
					Roadside = true;
				}
					
				if(alreadychosen){
					errortime  = true;
					}
				alreadychosen=true;
			}
		});
		
		JRadioButton rdbtnNewRadioButton = new JRadioButton("Field");
		rdbtnNewRadioButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(Field){
					Field = false;
					}else{
						Field = true;
					}
				if(alreadychosen){
					
					errortime  = true;
					}
				alreadychosen=true;
			}
		});
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(19)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(rdbtnRoadSide)
						.addComponent(rdbtnParkingLot)
						.addComponent(rdbtnBaseballStadium)
						.addComponent(rdbtnTerminal)
						.addComponent(rdbtnNewRadioButton)))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(14)
					.addComponent(rdbtnRoadSide)
					.addGap(3)
					.addComponent(rdbtnParkingLot)
					.addGap(3)
					.addComponent(rdbtnBaseballStadium)
					.addGap(3)
					.addComponent(rdbtnTerminal)
					.addGap(3)
					.addComponent(rdbtnNewRadioButton))
		);
		panel.setLayout(gl_panel);
	}
	
}
