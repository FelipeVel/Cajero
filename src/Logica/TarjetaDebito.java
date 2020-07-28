package Logica;

import javax.swing.JOptionPane;

public class TarjetaDebito {

	private String contraseña;
	private String SerialTarjeta;
        private Cajero m_Cajero;
        private Cuenta cuenta;

	public TarjetaDebito(String SerialTarjeta, String contraseña, Cajero cajero){
            this.contraseña=contraseña;
            this.SerialTarjeta=SerialTarjeta;
            this.m_Cajero=cajero;
	}
        
        public void setCuenta(Cuenta cuenta){
            this.cuenta=cuenta;
        }
        
        public String getSerial(){
            return SerialTarjeta;
        }
        
        public Cuenta getCuenta(){
            return cuenta;
        }
        
        public String getClave(){
            return contraseña;
        }
        
        public void validarSerial(){
            if(m_Cajero.validarSerial()==false){
                JOptionPane.showMessageDialog(null, "Serial invalido", "Error", 0);
                System.exit(0);
            }
        }
}//end TarjetaDebito