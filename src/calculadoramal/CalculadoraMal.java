package calculadoramal;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.regex.Pattern;

public class CalculadoraMal extends JFrame {

    private Panel panelprincipal;
    private Panel panelAbajo;
    private Label text;
    private Button botonArray[];
    private Calculos calc;

    public CalculadoraMal() {
    	calc = new Calculos();
        botonArray= new Button[20];
        panelprincipal = new Panel(new BorderLayout());
        GridLayout grid = new GridLayout(5, 4);
        grid.setHgap(2);
        grid.setVgap(2);
        Panel panelArriba = new Panel(new GridLayout(1, 1));
        panelAbajo = new Panel(grid);
        text = new Label("0");
        

        panelprincipal.add(panelArriba, BorderLayout.NORTH);
        panelprincipal.add(panelAbajo, BorderLayout.CENTER);

        initBotones();

        this.setTitle("Calculadora Mal");
        this.setSize(300, 350);
        this.add(panelprincipal);

        panelArriba.add(text);

        panelAbajo.setBackground(Color.black);
        panelArriba.setBackground(new Color(30, 30, 30));
        text.setForeground(Color.white);
        text.setFont(new Font("Helvetica", Font.PLAIN, 30));
               
        botonPulsado();

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
           javax.swing.UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        }
        catch(Exception e) {
            System.out.println(e);
        }
        CalculadoraMal frame = new CalculadoraMal();

        frame.setVisible(true);
        frame.setResizable(false);
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                System.exit(0);
            }
        });
    }
    
    /**
     * Crea Los botones y los añade a un Array
     */
    public void initBotones() {
    	String array[] = new String[] {"Limpiar","borrar","ANT","*","1","2","3"
    			,"-","4","5","6","+","7","8","9","/",".","0","%","="};
    	for (int i=0; i<array.length; i++) {
			String string = array[i];
			botonArray[i] = new Button(string);
		}
        addBotones();
    }

    /**
     * AÃ±ade los botones numericos a un array
     */
    public void addBotones() {
        for (int i = 0; i < botonArray.length; i++) {
            panelAbajo.add(botonArray[i]);
        }
    }

    public String borrarUnCaracter(String cadena) {
        String aDevolver = "";
        if (cadena.length() > 1) {
            aDevolver = cadena.substring(0, cadena.length() - 1);
        }
        return aDevolver;
    }
    
    public void botonPulsado() {
        for (int i = 0; i < botonArray.length; i++) {
            Button btn = botonArray[i];
 
            btn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent ae) {
                    if (btn.getLabel() == "Limpiar") {
                    	calc.limpiar();
                        text.setText(calc.getTexto());
                    } else if (btn.getLabel() == "=") {  
                    	calc.calcular();
                    	Float fl = new Float(calc.getResultado());
                    	String string = fl.toString();
                        text.setText(string);
                        calc.limpiar();
                    } else if(btn.getLabel() == "borrar") {
                        text.setText(calc.borrar());
                    } else if(btn.getLabel().equals("ANT")) {
                    	calc.numAnterior();
                    } else {
                    	calc.concatenarTexto(btn.getLabel());
	    				if(calc.operadorPulsado(btn.getLabel())) {
	        				calc.calcular();
	                        calc.isOperador(btn.getLabel());
	    					text.setText(btn.getLabel());
	    					calc.setTexto("");
	    				} else {
	    					text.setText(calc.getTexto());
	    					try {
	    						calc.setNum();
	    					}
	    					catch(Exception e) {
	    						System.out.println(e);
	    					}
	    				}
                    }
                }
            });
        }
    }
}