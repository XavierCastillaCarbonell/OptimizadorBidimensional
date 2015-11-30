package Presentacio;

import java.awt.Color;
import java.awt.Graphics;
import java.util.List;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Domini.Solicitud;
import Domini.Unitat;

//TODO: 1. Show in a window the number of glass plates needed.
//TODO: 2. Show in a window the disposal of the cuts performed by the program in the diferents plates.
@SuppressWarnings("serial")
public class RepresantacioGrafica extends JFrame {
	
	private JPanel contentPane;
	
	private List<Unitat> unitats;
	private int amplada,alcada;

	 /**
     * Create the frame.
     */
    public RepresantacioGrafica(int amplada, int alcada, List<Unitat> unitats) {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        setBounds(0,0,750+amplada,500+alcada);
        this.unitats = unitats;
        this.amplada = amplada;
        this.alcada = alcada;
    }
    
    public void paint (Graphics g)
    {
        super.paint(g);
        g.setColor(Color.black);
        g.drawRect (19, 19, 6000/10, 3210/10);
//        g.setColor (Color.yellow);
//        g.fillRect(s.getX()+150, s.getY()+270, s.getWidth(), s.getHeigth());
        
        Random rand = new Random();
        for(Solicitud sol: this.unitats.get(0).getPeces()){
        	float r = rand.nextFloat();
        	float gr = rand.nextFloat();
        	float b = rand.nextFloat();
        	Color randomColor = new Color(r, gr, b);
        	
        	g.setColor (randomColor);
            g.fillRect(sol.getPosicioX()/10+20, sol.getPosicioY()/10+20, sol.getWidth()/10, sol.getHeigth()/10);
        }
    }
}