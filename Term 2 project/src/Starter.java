import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JSlider;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JCheckBox;
import java.awt.Color;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;


@SuppressWarnings("serial")
public class Starter extends JFrame {
	public Boolean 
		Zombie_Regular = false,
		Zombie_Fast = false,
		Zombie_Large = false,
		Zombie_Poison = false,
		Zombie_Brute = false,
		Zombie_Witch = false;
	
	public int zombiehealthmodifier = 0;
	
	public Boolean alreadychosen = false;
	public Boolean errortime = false;
	
	private JPanel contentPane;
	private static Starter frame = new Starter();
	private JComboBox areaselect = new JComboBox();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		frame.setVisible(true);
	}

	/**
	 * Create the frame.
	 */
	public Starter() {
		setTitle("Project Urban Undead");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 583, 373);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(240, 240, 240));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnStart = new JButton("Start");
		btnStart.addActionListener(new ActionListener() {
			
			
			public void actionPerformed(ActionEvent e) {
				if(!Zombie_Regular && !Zombie_Fast && !Zombie_Brute && !Zombie_Witch && !Zombie_Large && !Zombie_Poison){
					//show error
					ErrorZombies EZ = new ErrorZombies();
					EZ.setVisible(true);
				}else{
					//start game
					int mapnum = areaselect.getSelectedIndex();
					frame.dispose();
					new Main(zombiehealthmodifier, mapnum, Zombie_Regular, Zombie_Fast, Zombie_Large, Zombie_Poison, Zombie_Brute, Zombie_Witch);
				}
				
			}
		});
		
		btnStart.setBounds(468, 302, 89, 23);
		contentPane.add(btnStart);
		
		final JLabel lblComment = new JLabel("Normal");
		lblComment.setBounds(20, 246, 133, 38);
		contentPane.add(lblComment);
		
		final JSlider slider = new JSlider();
		slider.setToolTipText("Sets the zombiehealthmodifier");
		slider.setMinimum(-3);
		slider.setValue(0);
		slider.setMaximum(3);
		slider.setPaintTicks(true);
		slider.setSnapToTicks(true);
		slider.setMinorTickSpacing(1);
		slider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				int modifier = 20;
				if(slider.getValue() == 3)
				{
					lblComment.setText("You're Insane");
					zombiehealthmodifier = slider.getValue()*modifier;
				}
				if(slider.getValue() == 2)
				{
					lblComment.setText("Nightmare");
					zombiehealthmodifier = slider.getValue()*modifier;
				}
				if(slider.getValue() == 1)
				{
					lblComment.setText("Hard");
					zombiehealthmodifier = slider.getValue()*modifier;
				}
				if(slider.getValue() == 0)
				{
					lblComment.setText("Normal");
					zombiehealthmodifier = slider.getValue()*modifier;
				}
				if(slider.getValue() == -1)
				{
					lblComment.setText("Easy");
					zombiehealthmodifier = slider.getValue()*modifier;
				}
				if(slider.getValue() == -2)
				{
					lblComment.setText("Super Easy");
					zombiehealthmodifier = slider.getValue()*modifier;
				}
				if(slider.getValue() == -3)
				{
					lblComment.setText("You're Kidding, Right?");
					zombiehealthmodifier = slider.getValue()*modifier;
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
		lblNewLabel.setBounds(125, 11, 276, 53);
		contentPane.add(lblNewLabel);
		
		JLabel lblCreatedByBen = new JLabel("Created By Peter Larson and Ben Zhang");
		lblCreatedByBen.setFont(new Font("Century Gothic", Font.PLAIN, 15));
		lblCreatedByBen.setBounds(132, 75, 263, 38);
		contentPane.add(lblCreatedByBen);
		
		JLabel lblArea = new JLabel("Area");
		lblArea.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblArea.setBounds(411, 185, 80, 26);
		contentPane.add(lblArea);
		
		areaselect.setBounds(367, 217, 124, 20);
		contentPane.add(areaselect);
		areaselect.addItem("Baseball Stadium");
		areaselect.addItem("Terminal");
		areaselect.addItem("Road Side");
		areaselect.addItem("Field");		
	}
}
