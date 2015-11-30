package Domini;


import java.util.LinkedList;
import java.util.List;

public class Unitat {
	
	public static final String ANSI_BLACK = "\u001B[30m";
	public static final String ANSI_RED = "\u001B[31m";
	public static final String ANSI_GREEN = "\u001B[32m";
	public static final String ANSI_YELLOW = "\u001B[33m";
	public static final String ANSI_BLUE = "\u001B[34m";
	public static final String ANSI_PURPLE = "\u001B[35m";
	public static final String ANSI_CYAN = "\u001B[36m";
	public static final String BACKGROUND_YELLOW = "\u001B[43m";
	
	public static final int VALOR_DEFECTE = -5;

	private int [][] superficie;
	
	private int width,heigth;
	
	private int xToStart;
	private int yToStart;
	
	private List<Solicitud> peces;
	
	private int id;
	private boolean tancada;
	private float desperdici;
	
	public Unitat(int width, int heigth){
		superficie = new int[width][heigth];
		for(int i = 0; i<width; i++){
			for(int j = 0; j<heigth; j++){
				superficie[i][j] = VALOR_DEFECTE;
			}
		}		
		this.width = width;
		this.heigth = heigth;		
		this.peces = new LinkedList<Solicitud>();
		this.xToStart = -1;
		this.yToStart = -1;
		this.tancada = false;
	}	
	
	public Unitat(Unitat u) {
		// TODO Auto-generated constructor stub
		this.superficie = u.getSuperficie();
		this.xToStart = u.getxToStart();
		this.yToStart = u.getyToStart();
		this.id = u.getId();
		this.desperdici = u.getDesperdici();
		this.width = u.getWidth();
		this.heigth = u.getHeigth();
		this.peces = new LinkedList<Solicitud>();
		this.tancada = u.getTancada();
		for(Solicitud s: u.getPeces()){
			this.peces.add(new Solicitud(s));
		}		
	}
	//comprobamos si la plancha ya esta finalizada y aliberamos parte de su espacio de la memoria del ordenador.
	public void tancarUnitat(boolean tancar){	
		if(!tancada){
			float desperdici = this.calcularDesperdici();
			desperdici = (desperdici/(this.width*this.heigth))*100;
			if(desperdici <= 10 || tancar){
				this.superficie = null;
				this.tancada = true;			
			}
			this.desperdici = desperdici;
		}
	}

	//Añade la solicitud a la plancha.
	public synchronized boolean afegirSolicitud(Solicitud solicitud) throws Exception {
		if(solicitud.getWidth() > this.width && solicitud.getWidth() > this.heigth
				|| solicitud.getHeigth() > this.width && solicitud.getHeigth() > this.heigth){
			throw new Exception ("El vidre a tallar és més gran que la planxa");
		}
			
		if(canGoIn(solicitud.getWidth(),solicitud.getHeigth())){
			addToMatrix(solicitud);
			return true;					
		}
		else{
			if(canGoIn(solicitud.getHeigth(),solicitud.getWidth())){
				int difHeigth = solicitud.getWidth();
				int difWith = solicitud.getHeigth();
				solicitud.setWidth(difWith);
				solicitud.setHeigth(difHeigth);
				addToMatrix(solicitud);
				return true;								
			}
			else{
				return false;
			}
		}
	}
	
	//Quita la solicitud de la plancha
	public synchronized void treureSolicitud(Solicitud solicitud) {
		if(!peces.remove(solicitud)){
			return;
		}
		int valor = solicitud.getId();
		for(int[] i: superficie){
			for(int num: i){
				if(num == valor){
					i[num] = VALOR_DEFECTE;
				}
			}
		}
	}
	
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return this.peces.isEmpty();
	}

	//Calcula el valor de desperdicio de la plancha en su estado actual.
	public int calcularDesperdici() {
		int desperdici=0;		
		for(int[] i: superficie){
			for(int num: i){
				if(num == VALOR_DEFECTE){
					desperdici++;
				}
			}
		}
		return desperdici;
	}
	
	
	//Private methods
	
	private synchronized void addToMatrix(Solicitud solicitud) {
		solicitud.setCoordenates(xToStart, yToStart);
		peces.add(solicitud);
		
		for (int i = xToStart; i < solicitud.getWidth()+xToStart && i<this.width; i++) {
		    for (int j = yToStart; j < solicitud.getHeigth()+yToStart && j<this.heigth; j++) {
		        this.superficie[i][j]= solicitud.getId();
		    }
		}
	}
	
	//Comprueba si hay espacio suficiente espacio disponible
	private synchronized boolean canGoIn(int widthSol, int heigthSol) {
		int x,y=0;
		
		for(x = 0;x<=this.width-widthSol;x++){
			if(checkDistance(x, y, heigthSol, widthSol)){
				this.xToStart = x;
				this.yToStart = y;
				return true;
			}
			if(x+1 > this.width-widthSol){
				x = -1;
				y++;
				if(y >= this.heigth){
					return false;
				}
			}			
		}
		return false;
	}
	private synchronized boolean checkDistance(int xInici, int yInici, int heightSol, int widthSol){
		try{
			for(int x = xInici; x<widthSol+xInici; x++){
				if(superficie[x][yInici] != VALOR_DEFECTE || superficie[x][yInici+heightSol-1] != VALOR_DEFECTE){
					return false;
				}			
			}
			for(int y = yInici; y<heightSol+yInici; y++){
				if(superficie[xInici][y] != VALOR_DEFECTE || superficie[xInici+widthSol-1][y] != VALOR_DEFECTE){
					return false;
				}			
			}
			return true;
		}
		catch(java.lang.ArrayIndexOutOfBoundsException e){
			return false;
		}
	}

	//Devuelve las coordenadas de las solicitudes introducidas a la plancha.
	public synchronized String getCooredenades(){
		String coordenada="";
		for(Solicitud s: peces){
			coordenada += s.getId()+" x: "+ s.getPosicioX()+" y: "+ s.getPosicioY()+" ... amplada: "+s.getWidth()+ " alçada: "+s.getHeigth()+"\n"; 
		}
		return coordenada;
	}
	
	// GETTERS Y SETTERS
	
	public List<Solicitud> getPeces(){
		return this.peces;
	}

	public void setId(int id) {
		// TODO Auto-generated method stub
		this.id = id;
	}

	public int[][] getSuperficie() {
		return superficie;
	}

	public int getWidth() {
		return width;
	}

	public int getHeigth() {
		return heigth;
	}

	public int getxToStart() {
		return xToStart;
	}

	public int getyToStart() {
		return yToStart;
	}

	public int getId() {
		return id;
	}
	public boolean getTancada(){
		return tancada;
	}
	public float getDesperdici(){
		return this.desperdici;
	}

	public void setTancada(boolean tancada) {
		this.tancada = tancada;		
	}
	
	//OTROS
	
	//Prueba de escritura por pantalla
//	public String toString(){
//		String output="";
//		for (int i = 0; i < this.width; i++) {
//		    for (int j = 0; j < this.heigth; j++) {
//		    	if(superficie[i][j] != VALOR_DEFECTE){	
//		    		switch (superficie[i][j]%6) {
//			    		case 1: output += selectColor(6)+superficie[i][j]+"\u001b[0m"; break;
//			    		case 2: output += selectColor(1)+superficie[i][j]+"\u001b[0m"; break;
//			    		case 3: output += selectColor(2)+superficie[i][j]+"\u001b[0m"; break;
//			    		case 4: output += selectColor(3)+superficie[i][j]+"\u001b[0m"; break;
//			    		case 5: output += selectColor(4)+superficie[i][j]+"\u001b[0m"; break;
//			    		case 6: output += selectColor(5)+superficie[i][j]+"\u001b[0m"; break;
//			    		default: 
//			    			output += selectColor(0)+superficie[i][j]+"\u001b[0m"; break;
//		    		}
//		    	}
//		    	else{
//		    		output += this.BACKGROUND_YELLOW+superficie[i][j]+"\u001b[0m";
//		    	}		        
//		    }
//		    output += "\n";
//		}
//		return output;
//	}
	
//	private String selectColor(int id){
//		switch (id) {
//			case 0: return this.ANSI_BLACK;
//			case 1: return this.ANSI_BLUE;
//			case 2: return this.ANSI_CYAN;
//			case 3: return this.ANSI_GREEN;
//			case 4: return this.ANSI_PURPLE;
//			case 5: return this.ANSI_RED;
//			case 6: return this.ANSI_YELLOW;
//		}
//		return this.ANSI_BLACK;
//	}
	
}
