package Domini;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Planxa extends AbsObserver{

	private List<Unitat> bestSolution;
	private int width,heigth;
	
	private Unitat bestUnitat;
	
	public static int voltes = 0;
	
	public Planxa(int width, int height){
		this.width = width;
		this.heigth = height;
		this.bestSolution = new LinkedList<Unitat>();
	}
	
	//función de optimización que inicia la funcion recursiva de calculo bidimensional
	public List<Unitat> optimitzar(List<Solicitud> listaPiezas) throws Exception{
		Unitat unitat;
		List<Unitat> unitatsOptimitzades = new ArrayList<Unitat>();
		int initialSize = listaPiezas.size();
		while(listaPiezas.size() > 0){
			unitat = new Unitat(width, heigth);
			optimitzarRecursiu(unitat,listaPiezas);
			treurePeces(listaPiezas, bestUnitat);
			bestUnitat.tancarUnitat(true);
			unitatsOptimitzades.add(new Unitat(bestUnitat));
			bestUnitat = null;
			//TODO: Quitar esta linea cuando el programa este finalizado
			System.out.println("Planxa acabada, queden "+listaPiezas.size());
			super.notificar(String.valueOf(initialSize-listaPiezas.size()));
		}
		return unitatsOptimitzades;
	}
	//función especial para calcular cuando las piezas solo tienen una medida de corte.
	public int optimitzarUnic(List<Solicitud> listaPiezas) throws Exception{
		Unitat unitat;
		unitat = new Unitat(width, heigth);
		optimitzarRecursiu(unitat,listaPiezas);
		unitat.tancarUnitat(true);
		int pecesH, pecesV, unitats;
		pecesH = width/listaPiezas.get(0).getWidth();	
		pecesV = heigth/listaPiezas.get(0).getHeigth();
		unitats = listaPiezas.size()/(pecesH*pecesV);
		if(listaPiezas.size()%(pecesH*pecesV) != 0)
			unitats++;
		return unitats;
	}
	//optimizara de forma recursiva y buscara la solución mas optima
	private void optimitzarRecursiu(Unitat unitat, List<Solicitud> listaPiezas) throws Exception {
		Solicitud solicitud;
		boolean acabat;
		for(int i=0; i<listaPiezas.size() && i<50; i++){
			solicitud = listaPiezas.get(i);
			acabat = !unitat.afegirSolicitud(solicitud);
			if(acabat || listaPiezas.size() == 1){				
				checkbestUnitat(unitat);
				return;
			}
			List<Solicitud> enviament = new ArrayList<Solicitud>(listaPiezas);
			enviament.remove(i);
			optimitzarRecursiu(unitat,enviament);			
			if(unitat.getTancada()){
				return;
			}				
			unitat.treureSolicitud(solicitud);
		}			
	}

	//una vez encontrada una solución se comprueba si es la mas optima
	private boolean checkbestUnitat(Unitat unitat) {
		if(bestUnitat == null){
			bestUnitat = new Unitat(unitat);
			bestUnitat.tancarUnitat(false);
			unitat.setTancada(bestUnitat.getTancada());
			return true;
		}
		else{
			if(unitat.calcularDesperdici() < bestUnitat.getDesperdici()){
				bestUnitat = new Unitat(unitat);
				bestUnitat.tancarUnitat(false);
				return true;
			}
		}
		return false;
	}

	public synchronized String getCoordenades(){
		String coordenades = "";
		int cont = 1;
		for(Unitat u: bestSolution){
			coordenades += "Planxa nº "+cont+"\n"+u.getCooredenades()+"\n";
			cont++;
		}
		return coordenades;
	}
	private void treurePeces(List<Solicitud> listaPiezas,Unitat unitat){
		for(Solicitud s: unitat.getPeces()){
			listaPiezas.remove(s);
		}
	}
}
