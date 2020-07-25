package cajero;

import java.util.Date;

public class Transaccion {

	private String fechaTransaccion;
	private String idCajero;
	private String idTarjeta;
	public Cajero m_Cajero;
	private String tipoTransaccion;
	private int valorTransaccion;

	public Transaccion(String operacion, int valor, Cajero m_Cajero, String idTarjeta){
            this.tipoTransaccion=operacion;
            this.valorTransaccion=valor;
            this.m_Cajero=m_Cajero;
            this.idCajero=m_Cajero.getId();
            this.idTarjeta=idTarjeta;
            fechaTransaccion = new Date().toString();
	}
        
	public void finalize()
	  throws Throwable{

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