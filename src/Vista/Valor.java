package Vista;

import Logica.Cliente;
import Logica.Transaccion;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JPanel;

public class Valor extends JFrame implements ActionListener{
    
    Cliente cliente;
    String operacion;
    JButton volver = new JButton("Volver");
    JPanel cp;
    JButton[] valores= new JButton [6];
    
    public Valor(Cliente cliente, String operacion){
        super("Seleccionar valor");
        this.cliente=cliente;
        this.operacion=operacion;
        this.setSize(700, 500);
        this.setLocationRelativeTo(null);
        cp = new JPanel();
        this.setContentPane(cp);
        cp.setLayout(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        cp.setBackground(new Color(255,22,22));
        
        valores[0]=new JButton("10.000");
        valores[1]=new JButton("25.000");
        valores[2]=new JButton("50.000");
        valores[3]=new JButton("100.000");
        valores[4]=new JButton("500.000");
        valores[5]=new JButton("1'000.000");
        
        for (int i=0;i<3;i++){
            valores[i].setBounds(10,100+(i*110),200,100);
            valores[i].setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
            valores[i].setBackground(new Color(255,255,43));
            cp.add(valores[i]);
            valores[i].addActionListener(this);            
        }
        for (int i=0;i<3;i++){
            valores[i+3].setBounds(480,100+(i*110),200,100);
            valores[i+3].setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
            valores[i+3].setBackground(new Color(255,255,43));
            cp.add(valores[i+3]);
            valores[i+3].addActionListener(this);            
        }
        
        volver.setBounds(480,10,200,30);
        volver.setBackground(new Color(255,255,43));
        cp.add(volver);
        volver.addActionListener(this);
        
        this.setVisible(true);
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==volver){
            this.setVisible(false);
            new Operacion(cliente);
        }
        if(e.getSource()==valores[0]){
            //this.setVisible(false);
            cliente.seleccionarOperacion(operacion, 10000);
            //ventana de clave
            this.setVisible(false);
            new Operacion(cliente);
        }
        if(e.getSource()==valores[1]){
            //this.setVisible(false);
            cliente.seleccionarOperacion(operacion, 25000);
            //ventana de clave
            this.setVisible(false);
            new Operacion(cliente);
        }
        if(e.getSource()==valores[2]){
            //this.setVisible(false);
            cliente.seleccionarOperacion(operacion, 50000);
            //ventana de clave
            this.setVisible(false);
            new Operacion(cliente);
        }
        if(e.getSource()==valores[3]){
            //this.setVisible(false);
            cliente.seleccionarOperacion(operacion, 100000);
            //ventana de clave
            this.setVisible(false);
            new Operacion(cliente);
        }
        if(e.getSource()==valores[4]){
            //this.setVisible(false);
            cliente.seleccionarOperacion(operacion, 500000);
            //ventana de clave
            this.setVisible(false);
            new Operacion(cliente);
        }
        if(e.getSource()==valores[5]){
            //this.setVisible(false);
            cliente.seleccionarOperacion(operacion, 1000000);
            //ventana de clave
            this.setVisible(false);
            new Operacion(cliente);
        }
    }
    
}