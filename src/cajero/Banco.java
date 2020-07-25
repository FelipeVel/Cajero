package cajero;

public class Banco {

	private int idCliente;
	public Cajero m_Cajero;
	public Cuenta m_Cuenta;
	private String nombreBanco;
	private String sucursal;

	public Banco(int idCliente){
            this.idCliente=idCliente;
	}

	public void actualizarSaldoCuenta(int valor){
            //Actualizar valor en BD, el valor ya viene con el signo positivo o negativo
	}

	public void bloquearTarjeta(){
            //Bloquear tarjeta en BD
	}

	public boolean validarSerialTarjeta(String serial){
            boolean validacion=false;
            //validar en BD
            return validacion;
	}
        
        public boolean validarCuenta(){
            boolean validacion=false;
            //Validar en BD
            return validacion;
        }
}//end Banco