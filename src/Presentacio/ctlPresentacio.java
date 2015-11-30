package Presentacio;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import Aplicacio.ControladorSolicituds;
import Aplicacio.IObserver;
import Domini.AbsObserver;
import Domini.Solicitud;
import Domini.Unitat;

public class ctlPresentacio implements IObserver {
	
	private barraProgreso bp;
	private IntroduccioDades pantalla;
	private JButton btnContinuar;
	private ControladorSolicituds ctlSolicituds;

	public ctlPresentacio(IntroduccioDades frame, barraProgreso bp) {
		// TODO Auto-generated constructor stub
		pantalla = frame;
		this.bp = bp;
		this.btnContinuar = frame.getButtonContinuar();
		
		btnContinuar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					btnContinuar();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					pantalla.showMessageOK("Error d'execucio", 
							e1.getMessage(), 
							JOptionPane.ERROR_MESSAGE ,
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
	}
	
	@Override
	public void notificar(AbsObserver absObserver, String propietat) {
		// TODO Auto-generated method stub
			this.bp.actualitzar(Integer.parseInt(propietat));				
	}
	//Envia los datos recogidos por la pantalla al controlador y muestra los resultados al acabar.
	private void btnContinuar() throws Exception{		
		
		List<Solicitud> solicituds = pantalla.getSolicituds();
		ctlSolicituds = new ControladorSolicituds(solicituds,this);
		int introduides = pantalla.getIntroduides();
		int[] args = {0,solicituds.size(),0};
		this.bp.generalSet(args);
		
		if(introduides == 1){
			this.introduirUnaSolicitud();
		}
		else{
			
//TODO: When the display results window is finished delete this part, only for control now.
			List<Unitat> unitats = ctlSolicituds.optimitzar();			
			int id=0;
			for(Unitat u: unitats){
				System.out.println("Unitat: "+id);
				u.setId(id);
				System.out.println(u.getCooredenades());
				id++;
			}		
					
			this.pantalla.showMessage(unitats.size());			
			new RepresantacioGrafica(60,40,unitats).setVisible(true);
		}
	}
	
	private void introduirUnaSolicitud() throws Exception {
		int unitats = ctlSolicituds.optimitzarUnic();
		this.pantalla.showMessage(unitats);
		
	}

}
