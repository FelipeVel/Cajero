package Logica;

public class Cuenta {

	private String idCuenta;
	public Cliente m_Cliente;
	public TarjetaDebito m_TarjetaDebito;
	private double saldoCuenta;

	public Cuenta(String idCuenta, double saldo, TarjetaDebito tarjeta){
            this.idCuenta=idCuenta;
            this.saldoCuenta=saldo;
            this.m_TarjetaDebito=tarjeta;
	}
        
        public String getId(){
            return idCuenta;
        }

	public void actualizarDinero(int valor){
            saldoCuenta+=valor;
	}

	public boolean aprobarTransaccion(int valor, Banco banco){
            return validarSaldoCuenta(valor)&&validarEstadoCuenta(banco);
	}

	public boolean validarEstadoCuenta(Banco banco){
            return banco.validarCuenta(idCuenta);
	}

	public boolean validarSaldoCuenta(int valor){
            return -valor <= saldoCuenta;
	}
}//end Cuenta