package Logica;

import java.io.*;
import java.sql.*;

public class Cliente {

	private String apellidoCliente;
	private String cedula;
	private String direccionCliente;
	private String idCliente;
	private String nombreCliente;
	private String telefonoCliente;
        private TarjetaDebito tarjeta;
        private Cuenta cuenta;
        Cajero m_Cajero;

	public Cliente(String nombreTarjeta) throws SQLException{
            this.m_Cajero = new Cajero(this);
            initComp(nombreTarjeta);
            System.out.println("Saldo del cajero: "+m_Cajero.getSaldo());
	}
        
	public Cliente(String nombreTarjeta, Cajero cajero) throws SQLException{
            this.m_Cajero=cajero;
            m_Cajero.setCliente(this);
            initComp(nombreTarjeta);
            System.out.println("Saldo del cajero: "+m_Cajero.getSaldo());
	}
        
        private void initComp(String nombreTarjeta) throws SQLException{
            crearTarjeta(nombreTarjeta);
            ResultSet rs = m_Cajero.llenarCliente(tarjeta.getSerial());
            this.idCliente=rs.getString("idcliente");
            System.out.println(idCliente);
            this.nombreCliente=rs.getString("Nombre");
            this.apellidoCliente=rs.getString("Apellido");
            this.direccionCliente=rs.getString("Direccion");
            this.telefonoCliente=rs.getString("Telefono");
            this.cedula=rs.getString("Cedula");
            rs=m_Cajero.llenarCuenta();
            rs.next();
            this.cuenta=new Cuenta(rs.getString("idCuenta"),rs.getInt("saldo"),tarjeta,this);
            tarjeta.setCuenta(cuenta);
            m_Cajero.setCuenta();
            m_Cajero.setTarjeta(tarjeta);            
        }
        
        public String getId(){
            return idCliente;
        }
        
        public Cuenta getCuenta(){
            return cuenta;
        }
        
        public String getNombre(){
            return nombreCliente;
        }
        
        public String getApellido(){
            return apellidoCliente;
        }
        
        public Cajero getCajero(){
            return m_Cajero;
        }
        
        public TarjetaDebito getTarjeta(){
            return tarjeta;
        }
        
        private void crearTarjeta(String nombreTarjeta){
            System.out.println("Asociando tarjeta");
            BufferedReader br = null;
            try {
               br = new BufferedReader(new FileReader("C:\\Users\\LENOVO\\Documents\\NetBeansProjects\\Cajero\\src\\Logica\\Recursos\\"+nombreTarjeta+".txt"));
               //this.idCliente = br.readLine();
               String serial = br.readLine();
               String clave = br.readLine();
               this.tarjeta= new TarjetaDebito(serial,clave,m_Cajero);
            }
            catch (FileNotFoundException e) {
                System.out.println("Error: Fichero no encontrado");
                System.out.println(e.getMessage());
            }
            catch(Exception e) {
                System.out.println("Error de lectura del fichero");
                System.out.println(e.getMessage());
            }
            finally {
                try {
                    if(br != null)
                        br.close();
                }
                catch (Exception e) {
                    System.out.println("Error al cerrar el fichero");
                    System.out.println(e.getMessage());
                }
            }
        }
        
        public boolean seleccionarOperacion(String operacion, int valor){
            Transaccion t = new Transaccion(operacion,valor,m_Cajero);
            return t.ejecutarTransaccion();
        }
        
        public boolean ingresarClave(String clave){
            return m_Cajero.validarClave(clave);
        }
}//end Cliente