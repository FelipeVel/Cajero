package Logica;

import java.sql.*;
import Vista.Recibo;

public class Cajero {

	private int capacidadDesignada=5000000;
	private String idCajero;
	private int saldoCajero=3500000;
        private TarjetaDebito tarjeta;
        Banco banco;
        int contador=0;
        Cliente cliente;
        Cuenta cuenta;

	public Cajero(Cliente cliente) throws SQLException{
            this.banco= new Banco();
            this.cliente=cliente;
	}
        
        public Cliente getCliente(){
            return cliente;
        }
        
        public void setTarjeta(TarjetaDebito tarjeta){
            this.tarjeta=tarjeta;
            banco.setClienteId(cliente.getId());
            tarjeta.validarSerial();
        }
        
        public void setCuenta(Cuenta cuenta){
            this.cuenta=cliente.getCuenta();
        }
        
        public ResultSet llenarCliente(){
            return banco.llenarCliente();
        }
        
        public ResultSet llenarCuenta(){
            return banco.llenarCuenta();
        }
        
        public boolean ingresarClave(String clave){
            if(!(clave.equals(tarjeta.getClave())) && contador<3){
                return false;
            }
            else if(clave.equals(tarjeta.getClave())){
                return true;
            }
            else if(contador>=3){
                banco.bloquearTarjeta(cuenta.getId());
                System.exit(0);                
            }
            return false;
        }
        
        public boolean validarSerial(){
            return banco.validarSerialTarjeta(cliente.getId(), tarjeta.getSerial());
        }
        
        public boolean solicitarTransaccion(int valor){
            boolean saldo = validarSaldoCajero(valor);
            System.out.println("solicitarTransaccion: "+(saldo && tarjeta.getCuenta().aprobarTransaccion(valor)));
            return saldo && tarjeta.getCuenta().aprobarTransaccion(valor);
        }
        
        public String getId(){
            return idCajero;
        }
        
        public void actualizarSaldo(double monto){
            saldoCajero+=monto;
        }

	public void imprimirReciboTransaccion(Transaccion transaccion, Cliente cliente, Banco banco){
            //new Recibo();
	}

	public void realizarTransaccion(Transaccion transaccion){
            int valor=cuenta.getSaldo()+transaccion.getValor();
            banco.actualizarSaldoCuenta(cuenta.getId(),valor);
            cuenta.actualizarDinero(transaccion.getValor());
            saldoCajero+=transaccion.getValor();
            imprimirReciboTransaccion(transaccion, cliente, banco);
	}

	public boolean validarSaldoCajero(int valor){
            return saldoCajero+valor>(capacidadDesignada*0.1);
	}
}//end Cajero