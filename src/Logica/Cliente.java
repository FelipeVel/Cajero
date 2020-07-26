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
            System.out.println("Cajero vinculado");
            crearTarjeta(nombreTarjeta);
            System.out.println("Tarjeta local creada");
            m_Cajero.setTarjeta(tarjeta);
            System.out.println("Tarjeta vinculada con cajero");
            ResultSet rs = m_Cajero.llenarCliente();
            rs.next();
            this.nombreCliente=rs.getString("Nombre");
            this.apellidoCliente=rs.getString("Apellido");
            this.direccionCliente=rs.getString("Direccion");
            this.telefonoCliente=rs.getString("Telefono");
            this.cedula=rs.getString("Cedula");
            rs=m_Cajero.llenarCuenta();
            rs.next();
            this.cuenta=new Cuenta(rs.getString("idCuenta"),rs.getInt("saldo"),tarjeta);
            tarjeta.setCuenta(cuenta);
            m_Cajero.setCuenta(cuenta);
            System.out.println("Cliente local creado");
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
        
        public Cajero getCajero(){
            return m_Cajero;
        }
        
        private void crearTarjeta(String nombreTarjeta){
            System.out.println("Asociando tarjeta");
            BufferedReader br = null;
            try {
               br = new BufferedReader(new FileReader("C:\\Users\\LENOVO\\Documents\\NetBeansProjects\\Cajero\\src\\Logica\\Recursos\\"+nombreTarjeta+".txt"));
               this.idCliente = br.readLine();
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
        
        public void seleccionarOperacion(String operacion, int valor){
            new Transaccion(operacion,valor,m_Cajero);
        }
        
        public boolean ingresarClave(String clave){
            return m_Cajero.ingresarClave(clave);
        }
}//end Cliente