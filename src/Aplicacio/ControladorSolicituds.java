package Aplicacio;

import java.util.List;

import Domini.Planxa;
import Domini.Solicitud;
import Domini.Unitat;

public class ControladorSolicituds{
	
	private List<Solicitud> solicituds;
	private Planxa planxa;
	
	public ControladorSolicituds(List<Solicitud> solicituds, IObserver pantalla) {
		this.solicituds = solicituds;
		this.planxa = new Planxa(6000,3210);
		planxa.add(pantalla);
	}
		
	public List<Unitat> optimitzar() throws Exception{
		return planxa.optimitzar(solicituds);
	}
	public int optimitzarUnic() throws Exception{
		return planxa.optimitzarUnic(solicituds);
	}

}
