
// lots of imports to make my life easier. 
import java.io.File;
import javax.swing.JOptionPane;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileReader;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class FileTest extends JFrame implements ActionListener {

	// private variables used to make the GUI and store text.
	private JTextArea userField;
	private JMenu menu;
	private JMenuItem item, item2, item3, item4, item5, item6;
	private JMenuBar jBar;
	private JTextArea tArea;
	private JLabel labelField;
	private JPanel panel;
	private String text;
	private File file;

	public static void main(String[] args) {
		// creates our application window
		new FileTest();

	}

	public FileTest() {
		// create our local objects
		instantiateFields();
		instantiateLayout();
		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension dim = tk.getScreenSize();
		MenuWriter mw = new MenuWriter();
		MenuReader mr = new MenuReader();
		MenuCloser mq = new MenuCloser();
		MenuPurger mp = new MenuPurger();
		Helper hp = new Helper();
		FileDeleter fd = new FileDeleter();
		int xPos = (dim.width / 2) - (this.getWidth() / 2);
		int yPos = (dim.height / 2) - (this.getHeight() / 2);
		// important methods for establishing the application's window
		this.setSize(dim.width / 2, dim.height / 2);
		this.setLocation(xPos, yPos);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("FileMaster");

		// add the relevant components to the JPanel, JMenuBar, and JMenu
		panel.add(userField, BorderLayout.CENTER);
		jBar.add(menu, BorderLayout.PAGE_START);
		panel.add(jBar, BorderLayout.PAGE_START);
		panel.add(tArea, BorderLayout.LINE_END);

		menu.add(item6);
		menu.add(item);
		menu.add(item2);
		menu.add(item4);
		menu.add(item5);
		menu.add(item3);
		// add ActionListeners to the menu objects to react to user clicks
		item.addActionListener(mw);
		item2.addActionListener(mr);
		item3.addActionListener(mq);
		item4.addActionListener(mp);
		item5.addActionListener(fd);
		item6.addActionListener(hp);
		// add all of the components to the JPanel
		this.add(panel);

		// display everything
		setVisible(true);

	}

	public void instantiateFields() {
		// instantiate all of our private variables
		file = new File("testfile.txt");
		item = new JMenuItem("Write To File");
		item2 = new JMenuItem("Read From File");
		item3 = new JMenuItem("Quit Program");
		item4 = new JMenuItem("Clear All Read");
		item5 = new JMenuItem("Delete File");
		item6 = new JMenuItem("Read Me");
		menu = new JMenu("menu");
		jBar = new JMenuBar();
		labelField = new JLabel(
				"<html>Enter a line to save and click on \"Write to File\" to save to that file, and create one if it doesn't exist. <br> <br> Click on \"Read From File\" to read what you wrote from that file.<br> <br> Click on \"Clear All Read\" to remove all text stored in the read area. <br> <br> Click on Delete File to delete the file created by this program <br> <br> Click on Quit to exit the program</html> ");
		panel = new JPanel();
		tArea = new JTextArea(30, 30);
		userField = new JTextArea(30, 30);
	}

	public void instantiateLayout() {
		// code that "pretties up" our GUI, such as adding linewraps and borders
		tArea.setLineWrap(true);
		tArea.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder("OUTPUT"),
				BorderFactory.createEmptyBorder(10, 10, 10, 10)));
		tArea.setEditable(false);
		userField.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder("INPUT"),
				BorderFactory.createEmptyBorder(10, 10, 10, 10)));
		userField.setLineWrap(true);
		panel.setLayout(new BorderLayout());
	}

	public void actionPerformed(ActionEvent e) {
	}

	// getters and setters 
	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	// make private classes to handle each of the menu events
	private class MenuWriter implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			// populated the string with the user's text
			setText(userField.getText());
			try {
				// create a new file if not done so and make a BufferedWriter chained to a
				// FileWriter that will get the text that a user enters.
				// flush and close when the user is done.
				FileWriter fWrite = new FileWriter(file);
				BufferedWriter bWrite = new BufferedWriter(fWrite);
				bWrite.write(userField.getText() + "\n");
				System.out.println("userField's value is: " + userField.getText() + "\n");
				if (userField.getText() != "") {
					bWrite.flush();
					bWrite.close();
				}

			} catch (IOException ex) {
				System.out.println("ERROR: Cannot write to file!");
				ex.printStackTrace();
			}

		}

	}

	private class MenuReader implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			{
				try {
					// create a BufferedReader chained to a FileWriter that gets the text from the
					// MenuWriter.
					// NOTE: If the user hasn't written anything to the file, an
					// exception will be thrown, as MenuWriter and MenuReader rely on a different
					// value than what the file is instantiated with.
					FileReader fRead = new FileReader(file);
					BufferedReader bRead = new BufferedReader(fRead);
					String line;

					System.out.println(file.getPath() + file.getName());
					while ((line = bRead.readLine()) != null) {
						tArea.append(line + "\n");
						System.out.println(line);
					}

					bRead.close();
				} catch (IOException ex) {
					System.out.println("ERROR: File not found!");
					ex.printStackTrace();
				}

			}

		}

	}

	private class MenuCloser implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			// closes the Java application when called. 
			System.exit(0);

		}

	}

	private class MenuPurger implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			// clears all text displayed in the display area.
			tArea.setText("");

		}

	}

	private class FileDeleter implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			boolean isDeleted = false;
			if (file.getName() == "testfile.txt")
				isDeleted = file.delete();
			System.out.println("Has file been deleted? " + isDeleted);

		}

	}

	private class Helper implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			JOptionPane.showMessageDialog(null, labelField, "Help", JOptionPane.INFORMATION_MESSAGE);

		}

	}
}
