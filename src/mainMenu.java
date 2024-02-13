import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class mainMenu implements ActionListener, ComponentListener{
	
	static JFrame frame;
	private JPanel mainMenuPanel;
	private JPanel titlePanel;
	private JLabel titleLabel;
	private JPanel mainMenuButtonsPanel;
	
	
	public static void main(String[] args) {
		mainMenu m = new mainMenu(); //calls constructer
	}
	
	public mainMenu(){
		frame = new JFrame();
		frame.setTitle("Perfect Pantry");
		frame.setDefaultCloseOperation(3);
		frame.setVisible(true);
		frame.setMinimumSize(new Dimension(800, 800));
		frame.getContentPane().setBackground(new Color(245, 223, 162));
		frame.addComponentListener(this);
		initializeMainMenuGUI();
		
	}
	
	public void initializeMainMenuGUI() {
		//Initialse mainMenuPanel
		mainMenuPanel = new JPanel(); //will hold all the components of mainMenuGUI
		mainMenuPanel.setLayout(null);
		frame.getContentPane().add(mainMenuPanel);
		
		//Initialize TitlePanel 
		titlePanel = new JPanel();
		titlePanel.setSize(800, 90);
		titlePanel.setLocation(0, 0);
		titlePanel.setBackground(new Color (245, 223, 162));
		mainMenuPanel.add(titlePanel);
		
		//Initialize titleLabel
		titleLabel = new JLabel("PERFECT PANTRY");
		titleLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 40));
		titlePanel.add(titleLabel);
		
		//initialize mainMenuButtonsPanel 
		mainMenuButtonsPanel = new JPanel();
		mainMenuButtonsPanel.setBackground(new Color(192, 237, 203));
		mainMenuButtonsPanel.setBounds(0, 680, 800, 90);
		mainMenuPanel.add(mainMenuButtonsPanel);
		
		
		
		
	}

	@Override
	public void componentResized(ComponentEvent e) {
		// TODO Auto-generated method stub
		titlePanel.setSize(frame.getWidth(), 90);
		mainMenuButtonsPanel.setBounds(0, frame.getHeight() - 120, frame.getWidth(), 120);
	}

	@Override
	public void componentMoved(ComponentEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void componentShown(ComponentEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void componentHidden(ComponentEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
