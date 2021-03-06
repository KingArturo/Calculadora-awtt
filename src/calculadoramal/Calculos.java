package calculadoramal;

public class Calculos {
	
    private String texto;
    
    private float resultado;
    private float num;
    private float resulAnt;
    
    private static enum Operadores {SUMA, RESTA, DIVISION, MULTI, PORCENT, NADA};
    private Operadores operador;
    
    public Calculos() {
    	num = 0;
    	resultado = 0;
    	texto = "";
    	resulAnt = 0;
    	operador = Operadores.NADA;
    }
	
    /**
     * Comprueva si el boton pulsado es un operador
     */    
    public boolean operadorPulsado(String tx) {
    	boolean pulsado = false;
    	String[] array = new String[] {"+","-","*","/","=","%"};
    	for (int i=0; i < array.length; i++) {
			String string = array[i];
			if(string.equals(tx)) {
				pulsado = true;
			}
		}
    	return pulsado;
    }
    
    /**
     *	Realiza la operacion pertinente con el numero que le pasas
     *	por parametro.
     */
    public void calcular() {
        if (operador.name().equals("SUMA")) {
        	resultado += num;
        } else if (operador.name().equals("RESTA")) {
        	resultado -= num;
        } else if (operador.name().equals("DIVISION")) {
        	resultado /= num;
        } else if (operador.name().equals("MULTI")) {
        	resultado *= num;
        } else if (operador.name().equals("PORCENT")) {
        	resultado = (resultado/100)*num;
        } else {
        	resultado = num;
        }
    }
    
    /**
     * Comprueba si es un operador y le da un valor a
     * la variable operador en caso de que lo sea
     */
    public void isOperador(String str) {
		switch (str) {
		case "+":
			operador = Operadores.SUMA;
			break;
		case "-":
			operador = Operadores.RESTA;
			break;
		case "*":
			operador = Operadores.MULTI;
			break;
		case "/":
			operador = Operadores.DIVISION;
			break;
		case "%":
			operador = Operadores.PORCENT;
			break;
		case "=":
			operador = Operadores.NADA;
			break;
		default:
			break;
		}
	}

    /**
     * Borra un caracter 
     */    
    public String borrarUnCaracter(String cadena) {
        String aDevolver = "0";
        if (cadena.length() > 1) {
            aDevolver = cadena.substring(0, cadena.length() - 1);
        }
        return aDevolver;
    }
        
    /**
     * Devualve a su valor inicial a las variables texto, resultado
     * y operador y le da valor a la variable resulAnt 
     */
    public void limpiar() {
        texto = "";
        resulAnt = resultado;
        resultado = 0;
        operador = Operadores.NADA;
    }
    
    /**
     * Borra un caracter de la variable text, operacion y num
     * y devuelve la variable texto
     */    
    public String borrar() {
    	texto = borrarUnCaracter(texto);
    	Integer fl = new Integer(Math.round(num));
        num = Float.parseFloat(borrarUnCaracter(fl.toString()));
    	return texto;
    }
    
    /**
     * Le da a la variable num el resultado de la operacion anterior 
     */
    public void numAnterior() {
    	num = resulAnt;
    }
    
    /**
     * Metodos Setter 
     */    
    public void setTexto(String tx) {texto = tx;}
    
    public void concatenarTexto(String tx) {texto += tx;}
    
    public void setResultado(float fl) {resultado = fl;}
    
    public void setNum() {num = Float.parseFloat(texto);}
    
    public void setResulAnt() {resulAnt = resultado;}
   
    /**
     * Metodos Getter 
     */
    public String getTexto() {return texto;}
    
    public float getResultado() {return resultado;}
    
    public float getNum() {return num;}
    
    public String getOperador() {return operador.name();}
    
    public float getNumAnt() {return resulAnt;}
}
