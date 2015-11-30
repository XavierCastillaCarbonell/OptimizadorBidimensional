package Aplicacio;

import Domini.AbsObserver;

public interface IObserver {
	
	void notificar(AbsObserver absObserver, String propietat);
}
