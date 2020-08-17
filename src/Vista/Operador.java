/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import Logica.Cajero;
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
public class Operador extends JFrame implements ActionListener{
    
    Cajero cajero;
    JPanel cp;
    JLabel l1 = new JLabel ("Bienvenido, ingrese el valor a recargar", SwingConstants.CENTER);
    JTextField valor = new JTextField();
    JButton recargar = new JButton("Recargar");
    
    public Operador(Cajero cajero){
        super("Recarga de valores");
        this.cajero=cajero;
        this.setSize(700, 200);
        this.setLocationRelativeTo(null);
        cp = new JPanel();
        
        this.setContentPane(cp);
        cp.setLayout(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        cp.setBackground(new Color(165, 234, 70));
        
        l1.setBounds(0, 30, 700, 30);
        l1.setFont(new Font(Font.SANS_SERIF, Font.ROMAN_BASELINE, 20));
        cp.add(l1);
        
        valor.setBounds(250, 70, 150, 30);
        valor.setFont(new Font(Font.SANS_SERIF, Font.ROMAN_BASELINE, 20));
        cp.add(valor); 
        
        recargar.setBounds(250,110,150,30);
        recargar.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
        recargar.setBackground(Color.WHITE);
        cp.add(recargar);
        recargar.addActionListener(this);
        
        this.setVisible(true);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(cajero.recargar(Integer.parseInt(valor.getText()))){
            this.setVisible(false);
        }
    }
    
}
