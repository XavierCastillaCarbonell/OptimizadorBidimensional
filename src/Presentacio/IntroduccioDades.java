package Presentacio;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JOptionPane;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JTextField;

import Domini.Solicitud;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.LinkedList;
import java.util.List;

@SuppressWarnings("serial")
public class IntroduccioDades extends JFrame {

	private JPanel contentPane;
	
	private JTextField txtAmplada;
	private JTextField txtAlcada;
	private JTextField txtQuantitat;
	
	private JLabel lblAmplada;
	private JLabel lblAlcada;
	private JLabel lblQuantitat;
	
	private JButton btnAfegir;
	private JButton btnContinuar;
	
	//TODO: "Solicituds" must be removed from here and put into "ctlPresentacio"
	private List<Solicitud> solicituds;
	private int id;
	private int introduides;

	/**
	 * Create the frame.
	 */
	public IntroduccioDades() {
		solicituds = new LinkedList<Solicitud>();		
		this.id = 0;
		introduides = 0;
		inicialitzar();
	}
	
	//Inicializador de los componentes de la pantalla
	private void inicialitzar(){
		
		JFrame.setDefaultLookAndFeelDecorated(true);

		setTitle("Calculador Nombre Planxes");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 578, 356);
		contentPane = new JPanel();		
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblAmplada = new JLabel("Amplada:");
		lblAmplada.setFont(new Font("Arial", Font.BOLD, 13));
		lblAmplada.setBounds(35, 35, 79, 28);
		contentPane.add(lblAmplada);
		
		lblAlcada = new JLabel("Al\u00E7ada:");
		lblAlcada.setFont(new Font("Arial", Font.BOLD, 13));
		lblAlcada.setBounds(35, 100, 79, 28);
		contentPane.add(lblAlcada);
		
		lblQuantitat = new JLabel("Quantitat:");
		lblQuantitat.setFont(new Font("Arial", Font.BOLD, 13));
		lblQuantitat.setBounds(35, 165, 79, 28);
		contentPane.add(lblQuantitat);
		
		btnAfegir = new JButton("Afegir");
		btnAfegir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnAfegir();				
			}
		});
		btnAfegir.setFont(new Font("Arial", Font.BOLD, 14));
		btnAfegir.setBounds(10, 253, 104, 53);
		contentPane.add(btnAfegir);
		
		btnContinuar = new JButton("Continuar");
		btnContinuar.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 14));
		btnContinuar.setBounds(448, 253, 104, 53);
		contentPane.add(btnContinuar);
		
		txtAmplada = new JTextField();
		txtAmplada.setText("");
		txtAmplada.setBounds(124, 35, 86, 20);
		contentPane.add(txtAmplada);
		txtAmplada.setColumns(10);
		
		txtAlcada = new JTextField();
		txtAlcada.setText("");
		txtAlcada.setBounds(124, 100, 86, 20);
		contentPane.add(txtAlcada);
		txtAlcada.setColumns(10);
		
		txtQuantitat = new JTextField();
		txtQuantitat.setText("1");
		txtQuantitat.setBounds(124, 165, 86, 20);
		contentPane.add(txtQuantitat);
		txtQuantitat.setColumns(10);		
	}
	//Accion que se llevara a cabo al pulsar el boton (NO ES EL LISTENER)
	private void btnAfegir(){		
		if(this.txtAmplada == null || this.txtAlcada == null || this.txtAmplada.getText().equals("") || this.txtAlcada.getText().equals("")){
			this.showMessageOK("Error d'entrada",
					"No ha introduït una alçada o Amplada vàlides", 
					JOptionPane.ERROR_MESSAGE, 
					JOptionPane.ERROR_MESSAGE);
		}
		else{
			this.desactivar();
			introduides++;		
			this.txtAmplada.setText(this.txtAmplada.getText().replace(" ", ""));
			this.txtAlcada.setText(this.txtAlcada.getText().replace(" ", ""));
			int[] mides = getMides(this.txtAmplada.getText(),this.txtAlcada.getText());
			
			for(int i = 0; i<Integer.parseInt(this.txtQuantitat.getText()); i++){
				id++;
				this.solicituds.add(new Solicitud(mides[0],mides[1],id));
			}
		}
		this.activar();
	}
	
	
	public void showMessage(int unitats){
		this.showMessageOK("Finalitzat", 
				"Seran necesaries "+unitats+" planxes", 
				JOptionPane.INFORMATION_MESSAGE, 
				JOptionPane.INFORMATION_MESSAGE);		
	}

	public void showMessageOK(String title,String body, int typeMessage, int icon){
		Object[] options = {"OK"};
		JOptionPane.showOptionDialog(this,
			    body,
			    title,
			    typeMessage,
			    icon,
			    null,
			    options,
			    options[0]);
	}

	private void desactivar(){
		this.txtAlcada.setEnabled(false);
		this.txtAmplada.setEnabled(false);
		this.txtQuantitat.setEnabled(false);
		
		this.btnAfegir.setEnabled(false);
	}
	private void activar(){
		this.txtAlcada.setEnabled(true);
		this.txtAmplada.setEnabled(true);
		this.txtQuantitat.setEnabled(true);		
		this.btnAfegir.setEnabled(true);
	}

	// Convierte las medidas del usuario a tipo int.
	private int[] getMides(String mida1, String mida2) {
		int amplada= Integer.parseInt(mida1)
				, alcada=Integer.parseInt(mida2)
				, pivot;
		if(amplada < alcada){
			pivot = amplada;
			amplada = alcada;
			alcada = pivot;
		}
		int [] mides = {amplada, alcada};
		return mides;
	}

	//GETTERS	
	public JButton getButtonContinuar() {
		// TODO Auto-generated method stub
		return this.btnContinuar;
	}

	public int getIntroduides() {
		// TODO Auto-generated method stub
		return this.introduides;
	}

	public List<Solicitud> getSolicituds() {
		// TODO Auto-generated method stub
		return this.solicituds;
	}
}
