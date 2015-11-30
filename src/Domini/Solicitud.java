package Domini;

public class Solicitud implements Comparable<Solicitud>{

	private int heigth;
	private int width;
	
	private int posicioX;
	private int posicioY;
	
	private int id;
	
	private boolean introduida;
	
	public Solicitud(int width, int height,int id){
		this.width = width;
		this.heigth = height;
		this.id = id;
		this.introduida = false;
	}
	
	public Solicitud(Solicitud s) {
		// TODO Auto-generated constructor stub
		this.heigth = s.getHeigth();
		this.width = s.getWidth();
		this.posicioX = s.getPosicioX();
		this.posicioY = s.getPosicioY();
		this.id = s.getId();
		this.introduida = s.getIntroduida();
	}
	
	public boolean equals(Object obj){
		if(!(obj instanceof Solicitud)){
			return false;
		}
		Solicitud sol = (Solicitud) obj;
		if(this.id == sol.getId()){
			return true;
		}
		return false;
		
	}
	
	@Override
	public int compareTo(Solicitud o) {
		// TODO Auto-generated method stub
		if(this.width > o.getWidth() || (this.width == o.getWidth() && this.heigth > o.getHeigth())){
			return 1;
		}
		else {
			if((this.width == o.getWidth() && this.heigth == o.getHeigth())){
				return 0;
			}
		}
		return -1;
	}
	
	//GETTERS Y SETTERS

	public int getHeigth() {
		// TODO Auto-generated method stub
		return this.heigth;
	}

	public int getWidth() {
		// TODO Auto-generated method stub
		return this.width;
	}
	
	public int getId(){
		return this.id;
	}
	
	public boolean getIntroduida(){
		return this.introduida;
	}

	public void setCoordenates(int x, int y){
		this.posicioX = x;
		this.posicioY = y;
	}
	
	public int getPosicioX(){
		return this.posicioX;
	}
	
	public int getPosicioY(){
		return this.posicioY;
	}	

	public void setWidth(int difWith) {
		this.width = difWith;
		
	}

	public void setHeigth(int difHeigth) {
		this.heigth = difHeigth;
	}
}
