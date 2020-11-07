package calculadoramal;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.regex.Pattern;

public class CalculadoraMal extends JFrame {

    private JPanel panelprincipal;
    private JPanel panelAbajo;
    private JPanel panelAction;
    private JTextField text;
    private JButton botonArray[];
    private JButton botonAcction[];
    private String texto;

    public CalculadoraMal() {
        texto = "";
        panelprincipal = new JPanel(new BorderLayout());
        GridLayout grid = new GridLayout(5, 3);
        grid.setHgap(2);
        grid.setVgap(2);
        Panel panelArriba = new Panel(new GridLayout(2, 1));
        panelAbajo = new JPanel(grid);
        panelAction = new JPanel(new GridLayout(1, 3));
        text = new JTextField("0");
        

        panelprincipal.add(panelArriba, BorderLayout.NORTH);
        panelprincipal.add(panelAbajo, BorderLayout.CENTER);

        initBotones();
        botonesBarraAccion();

        this.setTitle("Calculadora Mal");
        this.setSize(200, 250);
        this.add(panelprincipal);

        panelArriba.add(text);
        panelArriba.add(panelAction);

        panelAbajo.setBackground(Color.red);
        panelArriba.setBackground(Color.yellow);
               


        for (int i = 0; i < botonArray.length; i++) {
            JButton btn = botonArray[i];
            btn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent ae) {
                    texto += btn.getLabel();
                    text.setText(texto);
                }
            });
        }

        for (int i = 0; i < botonAcction.length; i++) {
            JButton btn = botonAcction[i];
            btn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent ae) {
                    if (btn.getLabel() == "Limpiar") {
                        texto = "";
                        text.setText(texto);
                    } else if (btn.getLabel() == "=") {
                        texto = calcular(texto);
                        text.setText(texto);
                    } else {
                        texto = borrarUnCaracter(texto);
                        text.setText(texto);
                    }
                }
            });
        }
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
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                System.exit(0);
            }
        });
    }

    public void initBotones() {
        botonArray = new JButton[]{new JButton("1"),new JButton("2"),new JButton("3"),new JButton("4"),new JButton("5"),
            new JButton("6"),new JButton("7"),new JButton("8"),new JButton("9"),new JButton("."),new JButton("0"),
            new JButton("*"),new JButton("-"),new JButton("+"),new JButton("/")};
        addBotones();
    }

    /**
     * Añade los botones numericos a un array
     */
    public void addBotones() {
        for (int i = 0; i < botonArray.length; i++) {
            panelAbajo.add(botonArray[i]);
        }
    }

    /**
     * Añade los botones de borrar limpiar y calcular aun array
     */
    public void botonesBarraAccion() {
        botonAcction = new JButton[]{
            new JButton("Limpiar"),
            new JButton("borrar"),
            new JButton("=")
        };
        for (JButton btn : botonAcction) {
            panelAction.add(btn);
        }
    }

    public String calcular(String tx) {
        String resultado = "";
        if (tx.contains("*")) {
            resultado = multiplicar(tx);
        }
        if (tx.contains("+")) {
            resultado = sumar(tx);
        }
        if (tx.contains("-")) {
            resultado = restar(tx);
        }
        if (tx.contains("/")) {
            resultado = dividir(tx);
        }
        return resultado;
    }

    public String sumar(String tx) {
        float num = 0;
        String prueba = "";
        try {
            prueba = Pattern.quote("+");
            String[] partes = tx.split(prueba);
            for (String stringNumn : partes) {
                num += Float.parseFloat(stringNumn);
            }
            prueba = "";
        } catch (Exception e) {
            prueba = "SYNTAX ERROR";
        }
 
        return prueba + num;
    }

    public String restar(String tx) {
        String prueba = "";
        float num = 0;
        try {
            prueba = Pattern.quote("-");
            String[] partes = tx.split(prueba);
            num = Float.parseFloat(partes[0]);
    
            for (int i = 1; i < partes.length; i++) {
                num -= Float.parseFloat(partes[i]);
            }
            prueba = "";
        } catch (Exception e) {
            prueba = "SYNTAX ERROR";
        }

        return prueba + num;
    }
    
    private String dividir(String tx) {
        String prueba = "";
        float num = 0;
        try {
            prueba = Pattern.quote("/");
            String[] partes = tx.split(prueba);
            num = Float.parseFloat(partes[0]);
            
            for (int i = 1; i < partes.length; i++) {
                num /= Float.parseFloat(partes[i]);
            }
            prueba = "";
        } catch (Exception e) {
            prueba = "SYNTAX ERROR";
        }
        
        return prueba + num;
    }

    public String multiplicar(String tx) {
        float num = 1;
        String prueba = "";
        try {
            prueba = Pattern.quote("*");
            String[] partes = tx.split(prueba);
            for (String stringNumn : partes) {
                num *= Float.parseFloat(stringNumn);
            }
            prueba = "";
        } catch (Exception e) {
            prueba = "SYNTAX ERROR";
        }

        return prueba + num;
    }

    public String borrarUnCaracter(String cadena) {
        String aDevolver = "";
        if (cadena.length() > 1) {
            aDevolver = cadena.substring(0, cadena.length() - 1);
        }
        return aDevolver;
    }
}