package Logica;

import java.util.Date;

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
        
        public boolean ejecutarTransaccion(){
            if(tipoTransaccion.equals("retirar"))
                valorTransaccion*=(-1);
            if(!m_Cajero.solicitarTransaccion(valorTransaccion)){
                return false;
            }
            m_Cajero.realizarTransaccion(this);
            return true;
        }
}//end Transaccion