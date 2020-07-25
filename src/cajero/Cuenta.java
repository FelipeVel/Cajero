package cajero;

public class Cuenta {

	private int idCliente;
	private String idTarjeta;
	public Cliente m_Cliente;
	public TarjetaDebito m_TarjetaDebito;
	private int NumCuenta;
	private double saldoCuenta;

	public Cuenta(){
            
	}

	/**
	 * 
	 * @exception Throwable
	 */
	public void finalize()
	  throws Throwable{

	}

	public void añadirConsignacionDinero(Transaccion transaccion){
            //Subir consignación a BD
	}

	public boolean aprobarTransaccion(int valor, Banco banco){
            return validarSaldoCuenta(valor)&&validarEstadoCuenta(banco);
	}

	public void consultarMovimientos(){

	}

	public void descontarRetiroDinero(){

	}

	public boolean validarEstadoCuenta(Banco banco){
            boolean validacion=banco.validarCuenta();
            return validacion;
	}

	public boolean validarSaldoCuenta(int valor){
            return -valor <= saldoCuenta;
	}
}//end Cuenta