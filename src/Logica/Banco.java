package Logica;

import java.sql.*;
import java.time.LocalDate;
import javax.swing.JOptionPane;

public class Banco {

    private String idCliente;
    public Cajero m_Cajero;
    private final String nombreBanco = "Banco UD";
    private int maxDiario=2000000;

    private static Connection con;
    private static final String driver = "com.mysql.cj.jdbc.Driver";
    private static final String user = "root";
    private static final String pass = "root";
    private static final String url = "jdbc:mysql://localhost:3306/cajero";
    private static String timezone = "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    Statement s;

    public Banco() throws SQLException {
        conectar();
    }

    public void setClienteId(String id) {
        this.idCliente = id;
    }

    public String getNombre() {
        return nombreBanco;
    }

    public static void conectar() {
        con = null;
        try {
            Class.forName(driver);
            con = DriverManager.getConnection(url + timezone, user, pass);
            if (con != null) {
                System.out.println("Conexion establecida");
            }
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Error de conexion: " + e);
        }
    }
    
    public boolean validaMaximoRetiro(String id, int valor){
        try {
            ResultSet rs = consulta(id,"cuenta");
            rs.next();
            Date ultOperacion = rs.getDate("FechaUltRetiro");
            if(ultOperacion.compareTo(Date.valueOf(LocalDate.now()))!=0){
                return true;
            }
            else{
                int cantidad = rs.getInt("cantidadUltRetiro");
                if(cantidad+Math.abs(valor)>=maxDiario){              
                    JOptionPane.showMessageDialog(null, "Cantidad maxima superada", "Error", 0);
                    return false;
                }
                return true;
            }
        } catch (SQLException e) {
            System.out.println("Error al validar la cantidad máxima de retiro: "+e);
        }
        return true;
    }
    
    public void actualizarFechaYValor(String id, int valor){
        try{
            ResultSet rs = consulta(id,"cuenta");
            rs.next();
            Date ultOperacion = rs.getDate("FechaUltRetiro");
            if(ultOperacion.compareTo(Date.valueOf(LocalDate.now()))==0){
                s = (Statement) con.createStatement();
                s.executeUpdate("UPDATE cuenta SET CantidadUltRetiro=" + (rs.getInt("CantidadUltRetiro")+Math.abs(valor)) + " WHERE idcuenta=" + id);              
            }
            else{                
                s = (Statement) con.createStatement();
                s.executeUpdate("UPDATE cuenta SET FechaUltRetiro='" + Date.valueOf(LocalDate.now()) + "' WHERE idcuenta=" + id);
                s.executeUpdate("UPDATE cuenta SET CantidadUltRetiro=" + Math.abs(valor) + " WHERE idcuenta=" + id);  
            }
        } catch (SQLException e) {
            System.out.println("Error al actualizar la cantidad de retiro diaria: "+e);
        }
    }

    public ResultSet consulta(String id, String tabla) {
        try {
            s = (Statement) con.createStatement();
            ResultSet rs = s.executeQuery("SELECT * FROM " + tabla + " WHERE id" + tabla + "='" + id + "'");
            return rs;
        } catch (SQLException ex) {
            System.out.println("Error consultando el id" + tabla + " " + id + " en " + tabla + ": " + ex);
        }
        return null;
    }

    public void actualiza(String id, String campo, int valor) {
        try {
            s = (Statement) con.createStatement();
            s.executeUpdate("UPDATE cuenta SET " + campo + "=" + valor + " WHERE idcuenta=" + id);
        } catch (SQLException ex) {
            System.out.println("Error actualizando " + campo + " en el id de la cuenta " + id);
        }
    }

    public ResultSet llenarCliente(String serial) {
        ResultSet cuenta;
        ResultSet cliente=null;
        try {
            s = (Statement) con.createStatement();
            cuenta = s.executeQuery("SELECT * FROM cuenta WHERE SerialTarjeta='" + serial + "'");
            cuenta.next();
            cliente = s.executeQuery("SELECT * FROM cliente WHERE idcuenta='" + cuenta.getString("idcuenta") + "'");
            cliente.next();
            this.idCliente=cliente.getString("idcliente");
        } catch (SQLException ex) {
            System.out.println("Error llenando el cliente: " + ex);
        }
        return cliente;
    }

    public ResultSet llenarCuenta() {
        String idCuenta = "";
        try {
            ResultSet rs = consulta(idCliente, "cliente");
            while (rs.next()) {
                idCuenta = rs.getString("idcuenta");
            }
        } catch (SQLException ex) {
            System.out.println("Error obteniendo datos de cuenta");
        }
        return consulta(idCuenta, "cuenta");
    }

    public void actualizarSaldoCuenta(String id, int valor) {
        actualiza(id, "Saldo", valor);
    }

    public void bloquearTarjeta(String id) {
        actualiza(id, "TarjetaValida", 0);
        JOptionPane.showMessageDialog(null, "Numero de intentos superado", "Tarjeta bloqueada", 0);
    }

    public boolean validarSerialTarjeta(String id, String serial) {
        try {
            ResultSet rs = consulta(id, "cuenta");
            rs.next();
            return rs.getString("SerialTarjeta").equals(serial) && rs.getInt("TarjetaValida") == 1;
        } catch (SQLException ex) {
            System.out.println("Error validando serial: " + ex);
        }
        return false;
    }

    public boolean validarCuenta(String id) {
        try {
            ResultSet rs = consulta(id, "cuenta");
            rs.next();
            return rs.getInt("TarjetaValida") == 1;
        } catch (SQLException ex) {
            System.out.println("Error validando cuenta");
        }
        return false;
    }
}//end Banco
