package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.ConcurrentHashMap;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import database.DB;
import domain.logic.Container;

/**
 * The main GUI frame for the application, serving as the entry point for user interaction.
 * It includes functionality to add and edit container names, and navigate between different views.
 */
public class HomeView implements ActionListener {

	/**
	 * Frame window of the application
	 */
	static JFrame frame;

	static DB data = new DB();

	/**
	 * home panel of the application, will hold all components pertaining to home
	 * screen
	 */
	private static JPanel homePanel;

	/**
	 * Title part of home screen
	 */
	private JPanel titlePanel;

	/**
	 * Title name
	 */
	private JLabel titleLabel;

	/**
	 * Hold all buttons pertaining to the home screen
	 */
	private JPanel homeButtonsPanel;

	/**
	 * Button that will add new container, apart of homeButtonsPanel
	 */
	private JButton addNewContainerButton;
	private JButton editContainerNameButton;

	/**
	 * Hold buttons pertaining to its containers
	 */
	private JPanel containerButtonsPanel;

	/**
	 * Map of button and its corresponding Container object
	 */
	private ConcurrentHashMap<JButton, Container> containerMap;

	/**
	 * Stages of the home screen 0 = home 1 = edit screen
	 */
	private int stage;

	/**
	 * main components of edit view
	 */
	private JPanel editPanel;

	/**
	 * Hold components of editView
	 */
	private JPanel editNameOfContainerPanel;

	/**
	 * Title panel of edit view
	 */
	private JPanel editNameOfContainerTitlePanel;

	/**
	 * Text of edit view
	 */
	private JLabel editNameLabel;

	/**
	 * Holds buttons of edit view
	 */
	private JPanel editGUIButtonsPanel;

	/**
	 * Button to go back from edit screen to home screen
	 */
	private JButton editBackToHome;

	/**
	 * Panel to hold container buttons
	 */
	private JPanel editContainerButtonsPanel = new JPanel();

	public static void main(String[] args) {
		HomeView m = new HomeView();
	}

	/**
	 * Launches the application and initializes the main GUI components.
	 */
	public HomeView() {
		stage = 0;

		// Initialize frame
		frame = new JFrame("Perfect Pantry");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Close on exit
		frame.setVisible(true);
		frame.setResizable(false); // stop resize
		frame.setMinimumSize(new Dimension(800, 800));
		frame.getContentPane().setBackground(new Color(245, 223, 162));

		// Initilize containerMap
		containerMap = new ConcurrentHashMap<>();

		initializeHomeGUI();
	}

	/**
	 * Initializes and displays the home screen GUI components.
	 */
	public void initializeHomeGUI() {
		// Initialse mainMenuPanel
		homePanel = new JPanel(); // will hold all the components of mainMenuGUI
		homePanel.setLayout(null);
		frame.getContentPane().add(homePanel);
		frame.setLocationRelativeTo(null);

		// Initialize TitlePanel
		titlePanel = new JPanel();
		titlePanel.setSize(800, 90);
		titlePanel.setLocation(0, 0);
		titlePanel.setBackground(new Color(255, 223, 162));
		homePanel.add(titlePanel);

		// Initialize titleLabel
		titleLabel = new JLabel("PERFECT PANTRY");
		titleLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 40));
		titlePanel.add(titleLabel);

		// initialize mainMenuButtonsPanel
		homeButtonsPanel = new JPanel();
		homeButtonsPanel.setBackground(new Color(192, 237, 203));
		homeButtonsPanel.setBounds(0, 680, 800, 90);
		homePanel.add(homeButtonsPanel);

		// Components of mainMenuButtonsPanel
		// initialize addNewContainerButton
		addNewContainerButton = new JButton("Add New Container");
		addNewContainerButton.setFont(new Font("Lucida Grande", Font.PLAIN, 17));
		addNewContainerButton.addActionListener(this);
		homeButtonsPanel.add(addNewContainerButton);

		// Initialize editContainerNameButton
		editContainerNameButton = new JButton("Edit Container Name");
		editContainerNameButton.setFont(new Font("Lucida Grande", Font.PLAIN, 17));
		editContainerNameButton.addActionListener(this);
		homeButtonsPanel.add(editContainerNameButton);

		// initialize containerButtonsPanel
		containerButtonsPanel = new JPanel();
		containerButtonsPanel.setBounds(0, 90, getFrame().getWidth(), getFrame().getHeight() - 120);
		containerButtonsPanel.setBackground(new Color(245, 213, 152));
		containerButtonsPanel.setLayout(new FlowLayout());
		addContainerButtons(containerButtonsPanel);

		homePanel.add(containerButtonsPanel);
		homePanel.setVisible(true);
	}

	/**
	 * Changes the stage of the home screen between the main view and the edit view.
	 */
	private void changeStageOfHome() {
		if (stage == 0) { // Home screen
			if (editPanel != null) {
				editPanel.setVisible(false);
			}
			homePanel.setVisible(true);
			addContainerButtons(containerButtonsPanel);
		} else if (stage == 1) { // Edit name of container screen
			homePanel.setVisible(false);
			editPanel = new JPanel();
			editPanel.setLayout(null);
			frame.getContentPane().add(editPanel);

			editNameOfContainerPanel = new JPanel();
			editNameOfContainerPanel.setSize(800, 90);
			editNameOfContainerPanel.setBackground(new Color(179, 245, 223));
			editPanel.add(editNameOfContainerPanel);

			editNameLabel = new JLabel("Click the Container Button you wish to rename");
			editNameLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 30));
			editNameOfContainerPanel.add(editNameLabel);

			editGUIButtonsPanel = new JPanel();
			editGUIButtonsPanel.setBackground(new Color(179, 245, 223));
			editGUIButtonsPanel.setBounds(0, 680, 800, 90);
			editPanel.add(editGUIButtonsPanel);

			editBackToHome = new JButton("Back to Home");
			editBackToHome.addActionListener(this);
			editGUIButtonsPanel.add(editBackToHome);

			editContainerButtonsPanel = new JPanel();
			editContainerButtonsPanel.setBounds(0, 90, frame.getWidth(), frame.getHeight() - 120);
			editContainerButtonsPanel.setBackground(new Color(149, 245, 203));
			editContainerButtonsPanel.setLayout(new FlowLayout());
			addContainerButtons(editContainerButtonsPanel);

			editPanel.add(editContainerButtonsPanel);
			editPanel.setVisible(true);
		}
	}

	/**
	 * Adds a new container and its corresponding button to the GUI.
	 */
	private void addNewContainer() {
		String nameOfContainer = JOptionPane.showInputDialog("Please enter a name for your Container:");
		if (nameOfContainer != null && !nameOfContainer.trim().isEmpty()) { // if not cancelled nor empty

			Container c = new Container(nameOfContainer, this);
			JButton b = new JButton(c.getName());
			b.setFont(new Font("Lucida Grande", Font.PLAIN, 17));
			b.addActionListener(this);
			containerMap.put(b, c);
			data.addContainer(nameOfContainer, c);
			addContainerButtons(containerButtonsPanel);
		}
	}

	/**
	 * Dynamically adds container buttons to the specified panel based on the current containerMap state.
	 * @param p The panel to which container buttons will be added.
	 */
	private void addContainerButtons(JPanel p) {
		p.removeAll();
		containerMap.forEach((button, container) -> {
			p.add(button);
		});
		p.revalidate(); // refresh panel
	}

	/**
	 * Initiates the container renaming process for a given container button.
	 * @param b The button corresponding to the container to be renamed.
	 */
	private void renameContainerButton(JButton b) {
		Container c = containerMap.get(b);
		String nameOfContainer = JOptionPane
				.showInputDialog("What would you like to rename container " + c.getName() + " to?");
		if (nameOfContainer != null && !nameOfContainer.trim().isEmpty()) {
			c.setName(nameOfContainer);
			b.setText(c.getName()); // Update the button text directly instead of replacing the button in the map
			stage = 0;
			changeStageOfHome();
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();

		if (stage == 0) { // home screen
			if (source == addNewContainerButton) {
				addNewContainer();
			} else if (source == editContainerNameButton) {
				stage = 1;
				changeStageOfHome();
			} else {
				Container c = containerMap.get(source); // This will return null if the button is not found
				if (c != null) {
					c.getGUI();
				}
			}
		} else if (stage == 1) {
			if (source == editBackToHome) {
				stage = 0;
				changeStageOfHome();
			} else {
				Container c = containerMap.get(source); // This will return null if the button is not found
				if (c != null) {
					renameContainerButton((JButton) source);
				}
			}
		}
	}

	/**
	 * Provides access to the main application frame.
	 * @return The main JFrame of the application.
	 */
	public static JFrame getFrame() {
		return frame;
	}
}