package Presentacio;

public class llencador {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		barraProgreso bp = new barraProgreso();
				
		IntroduccioDades frame = new IntroduccioDades();
		frame.setVisible(true);	
		
		ctlPresentacio ctlPantalla = new ctlPresentacio(frame,bp);
	//TODO: SwingUtilities can be used to launch the progress bar and update it correctly
//					SwingUtilities.invokeLater(new Runnable() {					
//						@Override
//						public void run() {
//												
//						}
//					});				
				
	}
}
