package Domini;

import java.util.ArrayList;
import java.util.List;

import Aplicacio.IObserver;

public abstract class AbsObserver {

	List<IObserver> observadors = new ArrayList<IObserver>();
	//informa a todos los elementos registrados de los canvios profocados
	public void notificar(String propietat){
		for(IObserver io: observadors){
			io.notificar(this, propietat);
		}
	}
	//registra un elemento
	public void add(IObserver io){
		this.observadors.add(io);
	}
}
