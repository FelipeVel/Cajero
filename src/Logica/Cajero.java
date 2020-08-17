package Logica;

import Vista.*;
import java.sql.*;
import java.io.PrintWriter;
import javax.swing.JOptionPane;

public class Cajero {

    private int capacidadDesignada = 5000000;
    private int saldoCajero = 3500000;
    private TarjetaDebito tarjeta;
    private Banco banco;
    private int contador = 0;
    private Cliente cliente;
    private Cuenta cuenta;
    private String sucursal = "Principal";

    public Cajero(Cliente cliente) throws SQLException {
        this.banco = new Banco();
        this.cliente = cliente;
    }

    public Cliente getCliente() {
        return cliente;
    }
    
    public void setCliente(Cliente cliente){
        this.cliente=cliente;
    }
    
    public int getContador(){
        return this.contador;
    }

    public void setTarjeta(TarjetaDebito tarjeta) {
        this.tarjeta = tarjeta;
        this.cliente = tarjeta.getCliente();
        if(tarjeta.validarSerial())
            banco.setClienteId(cliente.getId());
        else
            new Inicial(this);
    }

    public void setCuenta() {
        this.cuenta = cliente.getCuenta();
    }

    public void setCuenta(Cuenta cuenta) {
        this.cuenta = cuenta;
    }
    
    public int getSaldo(){
        return saldoCajero;
    }

    public ResultSet llenarCliente(String serial) {
        return banco.llenarCliente(serial);
    }

    public ResultSet llenarCuenta() {
        return banco.llenarCuenta();
    }

    public boolean validarClave(String clave) {
        if (!(clave.equals(tarjeta.getClave())) && contador < 2) {
            contador++;
            System.out.println("Intentos fallidos: " + contador);
            return false;
        } else if (clave.equals(tarjeta.getClave())) {
            return true;
        } else if (contador >= 2) {
            contador++;
            banco.bloquearTarjeta(cuenta.getId());
            new Inicial(this);
        }
        return false;
    }

    public boolean validarSerial() {
        return banco.validarSerialTarjeta(cuenta.getId(), tarjeta.getSerial());
    }

    public boolean solicitarTransaccion(int valor) {
        boolean saldoCuenta = tarjeta.getCuenta().aprobarTransaccion(valor);
        boolean maximo=true;
        boolean capacidad = validarCapacidadCajero(valor);
        if(!capacidad){
            JOptionPane.showMessageDialog(null, "Capacidad del cajero insuficiente", "Error", 0);    
            new Operacion(cliente);        
        }
        else if(valor<0){
            maximo = banco.validaMaximoRetiro(cuenta.getId(),valor);
        }
        boolean saldo = validarSaldoCajero(valor);
        if (!saldo) {
            JOptionPane.showMessageDialog(null, "Saldo de cajero insuficiente", "Error", 0);
        }
        return maximo && saldo && capacidad && saldoCuenta;
    }

    public void imprimirRecibo(Transaccion transaccion, Cliente cliente, Banco banco) {
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
        String[] recibos = {"Impreso","En pantalla"};
        banco.actualizarSaldoCuenta(cuenta.getId(), valor);
        if(transaccion.getValor()<0)
            banco.actualizarFechaYValor(cuenta.getId(),transaccion.getValor());
        cuenta.actualizarDinero(transaccion.getValor());
        saldoCajero += transaccion.getValor();
        String seleccion = (String) JOptionPane.showInputDialog(null,
                "¿De que forma quiere ver su recibo?",
                "Recibo",
                JOptionPane.QUESTION_MESSAGE,
                null,
                recibos,
                recibos[0]);
        if(seleccion.equals("Impreso")){
            imprimirRecibo(transaccion, cliente, banco);
        }
        else if(seleccion.equals("En pantalla")){
            new Recibo(transaccion, cliente, banco);
        }
        JOptionPane.showMessageDialog(null, "Operacion realizada", "Hecho", 3);
    }

    public boolean validarSaldoCajero(int valor) {
        boolean validacion=saldoCajero + valor > (capacidadDesignada * 0.1);
        if(!validacion)
            new Operador(this);
        return validacion;
    }
    
    public boolean validarCapacidadCajero(int valor){
        return saldoCajero + valor < capacidadDesignada;
    }
    
    public boolean recargar(int recarga){
        if(saldoCajero+recarga<=capacidadDesignada){
            this.saldoCajero+=recarga;
            JOptionPane.showMessageDialog(null, "Recarga realizada\nNuevo saldo: "+saldoCajero, "Hecho", 3);
            new Inicial(this);
            return true;
        }
        else{            
            JOptionPane.showMessageDialog(null, "El valor supera la capacidad maxima de "+capacidadDesignada, "Error", 0);
        }
        return false;
    }
}//end Cajero
