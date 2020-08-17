/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import Logica.Cliente;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 *
 * @author LENOVO
 */
public class Consulta extends JFrame implements ActionListener {
    
    Cliente cliente;
    JLabel saldo;
    JPanel cp;
    JButton salir = new JButton("Salir");
    
    public Consulta(Cliente cliente){
        super("Consultar saldo");
        this.cliente=cliente;
        this.setSize(600, 300);
        this.setLocationRelativeTo(null);
        cp = new JPanel();
        this.setContentPane(cp);
        cp.setLayout(null);
        cp.setBackground(new Color(165, 234, 70));
        
        saldo=new JLabel("Saldo: "+cliente.getCuenta().getSaldo());
        saldo.setBounds(10,30,550,40);
        saldo.setFont(new Font(Font.SANS_SERIF, Font.ROMAN_BASELINE, 30));
        cp.add(saldo);
        
        for(int i =0;i<2;i++){
        }
        
        salir.setBounds(20,140,200,30);
        salir.setBackground(Color.WHITE);
        cp.add(salir);
        salir.addActionListener(this);
        
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.setVisible(false);
        new Operacion(cliente);
    }
    
}
