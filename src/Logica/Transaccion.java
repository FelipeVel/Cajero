package Logica;

import java.util.Date;
import javax.swing.JOptionPane;

public class Transaccion {

	private String fechaTransaccion;
	public Cajero m_Cajero;
	private String tipoTransaccion;
	private int valorTransaccion;

	public Transaccion(String operacion, int valor, Cajero m_Cajero){
            this.tipoTransaccion=operacion;
            this.valorTransaccion=valor;
            this.m_Cajero=m_Cajero;
            fechaTransaccion = new Date().toString();
            this.ejecutarTransaccion();
	}
        
        public int getValor(){
            return valorTransaccion;
        }
        
        public String getOperacion(){
            return tipoTransaccion;
        }
        
        public String getFecha(){
            return fechaTransaccion;
        }
        
        public void ejecutarTransaccion(){
            if(tipoTransaccion.equals("retirar"))
                valorTransaccion*=-1;
            if(!m_Cajero.solicitarTransaccion(valorTransaccion)){
                return;
            }
            m_Cajero.realizarTransaccion(this);
        }
}//end Transaccion