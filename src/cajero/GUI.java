package cajero;

public class GUI {

	private String descripcionSolicitud;
	private String IdGUI;
	public Cajero m_Cajero;
	private int montoMaximo;
	private int montoMinimo;
	private int retiroMaxDia;
	private String tipoSolicitud;
        Cliente cliente = new Cliente();
        int clave;

	public GUI(){

	}

	/**
	 * 
	 * @exception Throwable
	 */
	public void finalize()
	  throws Throwable{

	}

	public void cambiarContraseña(){

	}

	public void consultarSaldo(){

	}
        
        public void ingresarClave(){
            if(!cliente.ingresarClave(clave))
                this.ingresarClave();
        }
}//end GUI