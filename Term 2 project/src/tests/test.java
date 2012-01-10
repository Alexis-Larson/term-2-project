package tests;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JInternalFrame;
import javax.swing.JProgressBar;
import net.miginfocom.swing.MigLayout;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JToggleButton;
import javax.swing.ButtonGroup;
import javax.swing.SwingConstants;


public class test extends JFrame
{
	private JTextField nametxt;
	private JTextField numtxt;
	private JTextField combo;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JTextField height;
	private JTextField width;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args)
	{
		test frame = new test();
		frame.setVisible(true);
	}

	/**
	 * Create the frame.
	 */
	public test()
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 160, 126);
		getContentPane().setLayout(new MigLayout("", "[][grow]", "[][][]"));
		
		JLabel lblHeight = new JLabel("Height");
		getContentPane().add(lblHeight, "cell 0 0,alignx trailing");
		
		height = new JTextField();
		height.setEditable(false);
		getContentPane().add(height, "cell 1 0,growx");
		height.setColumns(10);
		
		JLabel lblWidth = new JLabel("Width");
		getContentPane().add(lblWidth, "cell 0 1,alignx trailing");
		
		width = new JTextField();
		width.setEditable(false);
		getContentPane().add(width, "cell 1 1,growx");
		width.setColumns(10);
		
		JButton btnNewButton = new JButton("Get Screen Size");
		btnNewButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
				GraphicsDevice[] gs = ge.getScreenDevices();
				height.setText(""+ge.getMaximumWindowBounds().getHeight());
				width.setText(""+ge.getMaximumWindowBounds().getWidth());
			}
		});
		getContentPane().add(btnNewButton, "cell 0 2 2 1,growx");
	}

}
