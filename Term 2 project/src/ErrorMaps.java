import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JLabel;
import java.awt.Font;


public class ErrorMaps extends JDialog {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ErrorMaps dialog = new ErrorMaps();
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the dialog.
	 */
	public ErrorMaps() {
		setBounds(100, 100, 228, 112);
		getContentPane().setLayout(null);
		
		JLabel label = new JLabel("Error!");
		label.setFont(new Font("Tahoma", Font.BOLD, 23));
		label.setBounds(10, 0, 127, 48);
		getContentPane().add(label);
		
		JLabel lblSelectOneMap = new JLabel("Select one map.");
		lblSelectOneMap.setBounds(75, 49, 127, 14);
		getContentPane().add(lblSelectOneMap);

	}

}
