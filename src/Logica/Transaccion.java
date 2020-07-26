package Logica;

import java.util.Date;

public class Transaccion {

	private String fechaTransaccion;
	private String idCajero;
	public Cajero m_Cajero;
	private String tipoTransaccion;
	private int valorTransaccion;

	public Transaccion(String operacion, int valor, Cajero m_Cajero){
            this.tipoTransaccion=operacion;
            this.valorTransaccion=valor;
            this.m_Cajero=m_Cajero;
            this.idCajero=m_Cajero.getId();
            fechaTransaccion = new Date().toString();
	}
        
        public int getValor(){
            return valorTransaccion;
        }
        
        public void ejecutarTransaccion(){
            if (tipoTransaccion.equals("ingreso"))
                ingresarDinero();
            else if(tipoTransaccion.equals("retiro"))
                retirarDinero();
            registrarTransaccion();
        }
        
	private void ingresarDinero(){
            if(m_Cajero.solicitarTransaccion(valorTransaccion))
                m_Cajero.actualizarSaldo(valorTransaccion);
	}

	public void registrarTransaccion(){
            m_Cajero.realizarTransaccion(this);
	}

	private void retirarDinero(){
            valorTransaccion*=-1;
            if(m_Cajero.solicitarTransaccion(valorTransaccion))
                m_Cajero.actualizarSaldo(valorTransaccion);
	}
}//end Transaccion