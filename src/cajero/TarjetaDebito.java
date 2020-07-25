package cajero;

import javax.swing.JOptionPane;

public class TarjetaDebito {

	private int contraseña;
	private int idCliente;
	private String idTarjeta;
	public GUI m_GUI;
	private String SerialTarjeta;
        private Cajero m_Cajero;
        private Cuenta cuenta;

	public TarjetaDebito(int contraseña, int idCliente, String idTarjeta, String SerialTarjeta, GUI m_GUI, Cajero cajero, Cuenta cuenta){
            this.contraseña=contraseña;
            this.idCliente=idCliente;
            this.idTarjeta=idTarjeta;
            this.SerialTarjeta=SerialTarjeta;
            this.m_GUI=m_GUI;
            this.m_Cajero=cajero;
            this.cuenta=cuenta;
	}
        
	public void finalize()
	  throws Throwable{

	}
        
        public String getSerial(){
            return SerialTarjeta;
        }
        
        public String getId(){
            return idTarjeta;
        }
        
        public Cuenta getCuenta(){
            return cuenta;
        }
        
        public int getClave(){
            return contraseña;
        }
        
        public void validarSerial(){
            if(m_Cajero.validarSerial()==false){
                JOptionPane.showMessageDialog(null, "Serial invalido", "Error", 3);
                System.exit(0);
            }
        }
        
	public void indicarMonto(int monto){

	}

	public void ingresarClave(int clave){

	}

	public void seleccionarOperacion(String op){

	}
}//end TarjetaDebito