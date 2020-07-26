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
        
        public void ejecutarTransaccion(){
            if (tipoTransaccion.equals("consignar"))
                ingresarDinero();
            else if(tipoTransaccion.equals("retirar"))
                retirarDinero();
            if(!m_Cajero.solicitarTransaccion(valorTransaccion)){
                JOptionPane.showMessageDialog(null, "Saldo insuficiente", "Error", 0);
                return;
            }                
            System.out.println("Valor de transaccion: "+valorTransaccion);
            m_Cajero.realizarTransaccion(this);
        }
        
	private void ingresarDinero(){
            if(m_Cajero.solicitarTransaccion(valorTransaccion))
                m_Cajero.actualizarSaldo(valorTransaccion);
	}

	private void retirarDinero(){
            valorTransaccion*=-1;
            if(m_Cajero.solicitarTransaccion(valorTransaccion))
                m_Cajero.actualizarSaldo(valorTransaccion);
	}
}//end Transaccion