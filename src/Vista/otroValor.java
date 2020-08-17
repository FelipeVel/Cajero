/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import Logica.Cliente;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import static javax.swing.JFrame.EXIT_ON_CLOSE;

/**
 *
 * @author LENOVO
 */
public class otroValor extends JFrame implements ActionListener {
    
    JPanel cp;
    JTextField valor = new JTextField();
    JButton continuar = new JButton("Continuar");
    Cliente cliente;
    String operacion;
    
    public otroValor(Cliente cliente, String operacion){
        super("Seleccionar otro valor");
        this.cliente=cliente;
        this.operacion=operacion;
        this.setSize(700, 200);
        this.setLocationRelativeTo(null);
        cp = new JPanel();
        
        this.setContentPane(cp);
        cp.setLayout(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        cp.setBackground(new Color(165, 234, 70));
        
        JLabel l1 = new JLabel ("Ingrese el monto a "+operacion, SwingConstants.CENTER);
        l1.setBounds(0, 30, 700, 30);
        l1.setFont(new Font(Font.SANS_SERIF, Font.ROMAN_BASELINE, 20));
        cp.add(l1);
        
        valor.setBounds(250, 70, 150, 30);
        valor.setFont(new Font(Font.SANS_SERIF, Font.ROMAN_BASELINE, 20));
        cp.add(valor); 
        
        continuar.setBounds(250,110,150,30);
        continuar.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
        continuar.setBackground(Color.WHITE);
        cp.add(continuar);
        continuar.addActionListener(this);
        
        this.setVisible(true);
        
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        this.setVisible(false);
        new Clave(cliente,operacion,Integer.parseInt(valor.getText()));
    }
    
}
