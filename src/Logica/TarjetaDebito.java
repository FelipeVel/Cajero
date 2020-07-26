package Logica;

import javax.swing.JOptionPane;

public class TarjetaDebito {

	private String contraseña;
	private String SerialTarjeta;
        private Cajero m_Cajero;
        private Cuenta cuenta;

	public TarjetaDebito(String SerialTarjeta, String contraseña, Cajero cajero, Cuenta cuenta){
            System.out.println("Serial: "+SerialTarjeta);
            System.out.println("Contraseña: "+contraseña);
            this.contraseña=contraseña;
            this.SerialTarjeta=SerialTarjeta;
            this.m_Cajero=cajero;
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
            System.out.println("Serial valido");
        }
}//end TarjetaDebito