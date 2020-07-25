package cajero;

public class Cajero {

	private int capacidadDesignada;
	private String idBanco;
	private String idCajero;
	private double saldoCajero;
        private TarjetaDebito tarjeta;
        Banco banco;
        int contador=0;

	public Cajero(TarjetaDebito tarjeta, int idCliente){
            this.tarjeta=tarjeta;
            this.banco=new Banco(idCliente);
            tarjeta.validarSerial();
	}

	/**
	 * 
	 * @exception Throwable
	 */
	public void finalize()
	  throws Throwable{

	}
        
        public boolean ingresarClave(int clave){
            if(clave!=tarjeta.getClave()&&contador<3){
                return false;
            }
            else if(clave==tarjeta.getClave()){
                return true;
            }
            else if(contador>=3){
                banco.bloquearTarjeta();
                System.exit(0);                
            }
            return false;
        }
        
        public boolean validarSerial(){
            return banco.validarSerialTarjeta(tarjeta.getSerial());
        }
        
        public boolean solicitarTransaccion(int valor){
            return validarSaldoCajero(valor) && tarjeta.getCuenta().aprobarTransaccion(valor,banco);
        }
        
        public String getId(){
            return idCajero;
        }
        
        public void actualizarSaldo(double monto){
            saldoCajero+=monto;
        }
        
	public void confirmarTransaccion(){

	}

	public void imprimirReciboTransaccion(Transaccion transaccion){

	}

	public void realizarTransaccion(Transaccion transaccion){
            banco.actualizarSaldoCuenta(transaccion.getValor());
            imprimirReciboTransaccion(transaccion);
	}

	public boolean validarSaldoCajero(int valor){
            return saldoCajero+valor>(capacidadDesignada*0.1);
	}
}//end Cajero