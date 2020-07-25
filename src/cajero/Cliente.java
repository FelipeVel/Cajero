package cajero;

public class Cliente {

	private String apellidoCliente;
	private String cedula;
	private String direccionCliente;
	private int idCliente;
	private String nombreCliente;
	private int telefonoCliente;
        private TarjetaDebito tarjeta;
        private Cuenta cuenta;
        Cajero m_Cajero;

	public Cliente(){
            //Bajar datos de cliente de BD
            //Bajar datos de la cuenta del cliente de la BD
            //Bajar datos de tarjeta para crearla
            this.m_Cajero = new Cajero(tarjeta, idCliente);
	}

	/**
	 * 
	 * @exception Throwable
	 */
	public void finalize()
	  throws Throwable{

	}
        
        public void seleccionarOperacion(String operacion, int valor){
            Transaccion transaccion = new Transaccion(operacion,valor,m_Cajero, tarjeta.getId());
            transaccion.ejecutarTransaccion();
        }
        
        public boolean ingresarClave(int clave){
            return m_Cajero.ingresarClave(clave);
        }
}//end Cliente