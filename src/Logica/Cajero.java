package Logica;

import java.sql.*;
import java.io.PrintWriter;
import javax.swing.JOptionPane;

public class Cajero {

    private int capacidadDesignada = 5000000;
    private String idCajero;
    private int saldoCajero = 3500000;
    private TarjetaDebito tarjeta;
    Banco banco;
    int contador = 0;
    Cliente cliente;
    Cuenta cuenta;
    private String sucursal = "Principal";

    public Cajero(Cliente cliente) throws SQLException {
        this.banco = new Banco();
        this.cliente = cliente;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setTarjeta(TarjetaDebito tarjeta) {
        this.tarjeta = tarjeta;
        banco.setClienteId(cliente.getId());
        tarjeta.validarSerial();
    }

    public void setCuenta() {
        this.cuenta = cliente.getCuenta();
    }

    public void setCuenta(Cuenta cuenta) {
        this.cuenta = cuenta;
    }

    public ResultSet llenarCliente() {
        return banco.llenarCliente();
    }

    public ResultSet llenarCuenta() {
        return banco.llenarCuenta();
    }

    public boolean ingresarClave(String clave) {
        if (!(clave.equals(tarjeta.getClave())) && contador < 2) {
            contador++;
            System.out.println("Intentos fallidos: " + contador);
            return false;
        } else if (clave.equals(tarjeta.getClave())) {
            return true;
        } else if (contador >= 2) {
            banco.bloquearTarjeta(cuenta.getId());
            System.exit(0);
        }
        return false;
    }

    public boolean validarSerial() {
        return banco.validarSerialTarjeta(cliente.getId(), tarjeta.getSerial());
    }

    public boolean solicitarTransaccion(int valor) {
        boolean saldo = validarSaldoCajero(valor);
        boolean capacidad = validarCapacidadCajero(valor);
        if (!saldo) {
            JOptionPane.showMessageDialog(null, "Saldo de cajero insuficiente", "Error", 0);
        }
        if(!capacidad){
            JOptionPane.showMessageDialog(null, "Capacidad del cajero insuficiente", "Error", 0);            
        }
        return saldo && capacidad && tarjeta.getCuenta().aprobarTransaccion(valor);
    }

    public String getId() {
        return idCajero;
    }

    public void imprimirReciboTransaccion(Transaccion transaccion, Cliente cliente, Banco banco) {
        try {
            PrintWriter writer = new PrintWriter("C:\\Users\\LENOVO\\Documents\\NetBeansProjects\\Cajero\\src\\Logica\\Recursos\\Recibo.txt", "UTF-8");
            writer.println(banco.getNombre());
            writer.println("Sucursal: " + sucursal);
            writer.println("Fecha: " + transaccion.getFecha());
            writer.println();
            writer.println("Cliente: " + cliente.getNombre() + " " + cliente.getApellido());
            writer.println("Operacion: " + transaccion.getOperacion());
            writer.println("Valor: " + Math.abs(transaccion.getValor()));
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void realizarTransaccion(Transaccion transaccion) {
        int valor = cuenta.getSaldo() + transaccion.getValor();
        banco.actualizarSaldoCuenta(cuenta.getId(), valor);
        cuenta.actualizarDinero(transaccion.getValor());
        saldoCajero += transaccion.getValor();
        imprimirReciboTransaccion(transaccion, cliente, banco);
    }

    public boolean validarSaldoCajero(int valor) {
        return saldoCajero + valor > (capacidadDesignada * 0.1);
    }
    
    public boolean validarCapacidadCajero(int valor){
        return saldoCajero + valor < capacidadDesignada;
    }
}//end Cajero
