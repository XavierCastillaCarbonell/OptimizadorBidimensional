package Test;

import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import javax.swing.plaf.SliderUI;

import Domini.Planxa;
import Domini.Solicitud;
import Domini.Unitat;
import Presentacio.RepresantacioGrafica;

public class testOptimitzacio {
/*IDEA
 * Agafar totes les solicituds iguals i ajuntar-les. Postser surt una optimització no tant bona però és compensa amb una millora del rendiment.
 * 
 * Per fer una optimització més exacta s'hauria de fer una segona execució separant totes i cada una de les solicituds.
 * 
 * 29 solicituds mixtes temps mitja -> 13:58
 * 
 * 29 solicituds iguals -> 7:56
 */
	public static void main(String [] args) {
		
		int m2500 = 2500;
		int m2000 = 2000;
		int m1000 = 1000;
		int m500 = 500;
		int m250 = 250;
		int m200 = 200;
		int m150 = 150;
		int m100 = 100;
		
		int mida1 = 1000;
		int mida2 = 1000;
		
		Planxa ps = new Planxa(6000,3210);
		List<Solicitud> solicituds = new ArrayList<Solicitud>();
		
		Random rnd = new Random();
		Random rnd2 = new Random();
		Random rnd3 = new Random();
		
		int width, height, scale;
		
//		for(int i= 1; i<= 100; i++){
//			width = rnd.nextInt(5)+1;
//			height = rnd.nextInt(5)+1;
//			switch (rnd3.nextInt(3)) {
//				case 0: scale = 10;break;
//				case 1: scale = 100;break;
//				case 2: scale = 1000;break;	
//				default:
//					scale = 1;
//			}
//			
//			solicituds.add(new Solicitud(width*scale, height*scale, i));
//		}
		
		solicituds.add(new Solicitud(1000, 1000, 1));
		solicituds.add(new Solicitud(1000, 1000, 2));
		solicituds.add(new Solicitud(1000, 1000, 3));
		solicituds.add(new Solicitud(1000, 1000, 4));
		solicituds.add(new Solicitud(1000, 1000, 5));
		solicituds.add(new Solicitud(1000, 1000, 6));
		solicituds.add(new Solicitud(1000, 1000, 7));
		solicituds.add(new Solicitud(1000, 1000, 8));
		solicituds.add(new Solicitud(1000, 1000, 9));
		solicituds.add(new Solicitud(1000, 1000, 10));
		
//		solicituds.add(new Solicitud(1000, 1000, 11));
//		solicituds.add(new Solicitud(1000, 1000, 12));
//		solicituds.add(new Solicitud(1000, 1000, 13));
//		solicituds.add(new Solicitud(1000, 1000, 14));
//		solicituds.add(new Solicitud(1000, 1000, 15));
//		solicituds.add(new Solicitud(1000, 1000, 16));
//		solicituds.add(new Solicitud(1000, 1000, 17));
//		solicituds.add(new Solicitud(1000, 1000, 18));
//		solicituds.add(new Solicitud(1000, 1000, 19));
//		solicituds.add(new Solicitud(1000, 1000, 20));
//		
//		solicituds.add(new Solicitud(1000, 1000, 21));
//		solicituds.add(new Solicitud(1000, 1000, 22));
//		solicituds.add(new Solicitud(1000, 1000, 23));
//		solicituds.add(new Solicitud(mida2, mida1, 24));
//		solicituds.add(new Solicitud(mida2, mida1, 25));
//		solicituds.add(new Solicitud(mida2, mida1, 26));
//		solicituds.add(new Solicitud(mida2, mida1, 27));
//		solicituds.add(new Solicitud(mida2, mida1, 28));
//		solicituds.add(new Solicitud(mida2, mida1, 29));
//		solicituds.add(new Solicitud(mida2, mida1, 30));
		
		solicituds.add(new Solicitud(mida1, mida2, 31));
		solicituds.add(new Solicitud(mida1, mida2, 32));
		solicituds.add(new Solicitud(mida1, mida2, 33));
		solicituds.add(new Solicitud(mida1, mida2, 34));
		solicituds.add(new Solicitud(mida1, mida2, 35));
		solicituds.add(new Solicitud(mida2, mida1, 36));
		solicituds.add(new Solicitud(mida2, mida1, 37));
		solicituds.add(new Solicitud(mida2, mida1, 38));
		solicituds.add(new Solicitud(mida2, mida1, 39));
		solicituds.add(new Solicitud(mida2, mida1, 40));
		
//		solicituds = ajuntarPeces(solicituds);
//		System.out.println(solicituds.size());
		
		System.out.println(solicituds.size());
	
		List<Unitat> unitats = new ArrayList<Unitat>();
		
		try {
			Collections.sort(solicituds);
//			for(Solicitud s: solicituds){
//				System.out.println("id: "+s.getId()+" width: "+s.getWidth()+" heigth: "+s.getHeigth());
//			}
			unitats = ps.optimitzar(solicituds);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		int id=0;
		float superficie = 6000*3210;
		for(Unitat u: unitats){
			System.out.println();
			System.out.print("Unitat: "+id+" desperdici:"+u.getDesperdici());
			System.out.println();
			System.out.println("--------------------------------------");
			u.setId(id);
			System.out.println(u.getCooredenades());
			id++;
		}
//		
//		System.out.println(id);
//		System.out.println(ps.getCoordenades());
//		System.out.println(Planxa.voltes+" voltes");
//		System.out.println("End");
		
//		new RepresantacioGrafica(60,40,unitats).setVisible(true);
		

		
//		for(Solicitud s: solicituds){
//			System.out.println(s.getId()+": "+s.getIntroduida()+"--"+s.getHaEstatIntroduida()+"--");
//		}
		
//		/** FORMA 1 DE ESCRITURA **/
//		FileWriter fichero = null;
//		try {
//
//			fichero = new FileWriter("D:\\Cricursa\\prueba.txt");
//
//			// Escribimos linea a linea en el fichero
//			for(Unitat u: unitats){
//				fichero.write(u.toString() + "\n");
//			}
//
//			fichero.close();
//			
//			System.out.println("End");
//
//		} catch (Exception ex) {
//			System.out.println("Mensaje de la excepción: " + ex.getMessage());
//		}	
	}
	public static List<Solicitud> ajuntarPeces(List<Solicitud> solicituds){
		List<Solicitud> elements = new ArrayList<Solicitud>();
		for(int e = 0; e<solicituds.size(); e++){
			Solicitud s = solicituds.get(e);
			for(int ind = 0; ind<solicituds.size(); ind++){
				Solicitud temp = solicituds.get(ind);
				if(!s.equals(temp) && s.compareTo(temp) == 0){
					elements.add(new Solicitud(s.getWidth()+temp.getWidth(), s.getHeigth()+temp.getHeigth(), s.getId()));
					solicituds.remove(s);
					solicituds.remove(temp);
				}
			}
		}
		return elements;
	}
}
