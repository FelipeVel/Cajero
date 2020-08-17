/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import Logica.*;
import java.awt.Color;
import java.awt.Font;
import javax.swing.*;

/**
 *
 * @author LENOVO
 */
public class Recibo extends JFrame {
    
    JLabel[] etiquetas = new JLabel[5];
    JPanel cp;
    
    public Recibo(Transaccion transaccion, Cliente cliente, Banco banco){
        super("Recibo");
        this.setSize(600, 400);
        this.setLocationRelativeTo(null);
        cp = new JPanel();
        this.setContentPane(cp);
        cp.setLayout(null);
        cp.setBackground(Color.LIGHT_GRAY);
        
        etiquetas[0]=new JLabel("Banco: "+banco.getNombre());
        etiquetas[1]=new JLabel("Fecha: "+transaccion.getFecha());
        etiquetas[2]=new JLabel("Cliente: " + cliente.getNombre() + " " + cliente.getApellido());
        etiquetas[3]= new JLabel("Operacion: " + transaccion.getOperacion());
        etiquetas[4]= new JLabel("Valor: " + Math.abs(transaccion.getValor()));
        
        for(int i =0;i<5;i++){
            etiquetas[i].setBounds(10,30+(i*50),550,40);
            etiquetas[i].setFont(new Font(Font.SANS_SERIF, Font.ROMAN_BASELINE, 30));
            cp.add(etiquetas[i]);
        }
        
        this.setVisible(true);
        
    }
    
}
