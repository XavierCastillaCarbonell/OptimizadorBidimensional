package Presentacio;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JProgressBar;

//TODO: It dosn't update because the GUI is blocked during execution, need to work on that.
@SuppressWarnings("serial")
public class barraProgreso extends JFrame{

	private JPanel contentPane;
	private JProgressBar progressBar;

	/**
	 * Create the frame.
	 */
	public barraProgreso() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 102);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		progressBar = new JProgressBar();	
		progressBar.setBounds(5, 5, 424, 47);		
		progressBar.setStringPainted(true);
		contentPane.add(progressBar);		
		
		this.setVisible(true);
	}

	public void actualitzar(int value) {
		this.progressBar.setValue(value);	
		this.progressBar.repaint();
	}
	
	public void generalSet(int[] args){
		progressBar.setMinimum(args[0]);
		progressBar.setMaximum(args[1]);
		progressBar.setValue(args[2]);
	}
}
