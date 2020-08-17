package Logica;

import javax.swing.JOptionPane;

public class Cuenta {

	private String idCuenta;
	public Cliente m_Cliente;
	public TarjetaDebito m_TarjetaDebito;
	private int saldoCuenta;

	public Cuenta(String idCuenta, int saldo, TarjetaDebito tarjeta, Cliente cliente){
            this.idCuenta=idCuenta;
            this.saldoCuenta=saldo;
            this.m_TarjetaDebito=tarjeta;
            this.m_Cliente=cliente;
	}
        
        public String getId(){
            return idCuenta;
        }
        
        public int getSaldo(){
            return saldoCuenta;
        }
        
        public Cliente getCliente(){
            return m_Cliente;
        }

	public void actualizarDinero(int valor){
            saldoCuenta+=valor;
	}

	public boolean aprobarTransaccion(int valor){
            boolean aprobacion = -valor <= saldoCuenta;
            if(!aprobacion)
                JOptionPane.showMessageDialog(null, "Saldo de cuenta insuficiente", "Error", 0);
            return aprobacion;
	}

	public boolean validarEstadoCuenta(Banco banco){
            return banco.validarCuenta(idCuenta);
	}
}//end Cuenta