import javax.swing.JDialog;
import javax.swing.JLabel;
import java.awt.Font;


@SuppressWarnings("serial")
public class ErrorZombies extends JDialog {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ErrorZombies dialog = new ErrorZombies();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ErrorZombies() {
		setTitle("Nice Try...");
		setBounds(100, 100, 287, 123);
		getContentPane().setLayout(null);
		
		JLabel lblError = new JLabel("Error!");
		lblError.setFont(new Font("Tahoma", Font.BOLD, 23));
		lblError.setBounds(10, 11, 127, 48);
		getContentPane().add(lblError);
		
		JLabel lblSelectAtLeast = new JLabel("Select at least 1 Zombie type.");
		lblSelectAtLeast.setBounds(70, 60, 166, 19);
		getContentPane().add(lblSelectAtLeast);
	}
}
