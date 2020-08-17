package Vista;

import Logica.Cliente;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Operacion extends JFrame implements ActionListener{
    
    JPanel cp;
    JButton consignar = new JButton("Consignar");
    JButton retirar = new JButton("Retirar");
    JButton consultar = new JButton("Consultar saldo");
    JButton salir = new JButton("Salir");
    Cliente cliente;
    
    public Operacion(Cliente cliente){
        super("Seleccionar operacion");
        this.cliente=cliente;
        this.setSize(700, 500);
        this.setLocationRelativeTo(null);
        cp = new JPanel();
        this.setContentPane(cp);
        cp.setLayout(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        cp.setBackground(new Color(165, 234, 70));
        
        consignar.setBounds(10,225,200,100);
        consignar.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
        consignar.setBackground(Color.WHITE);
        cp.add(consignar);
        consignar.addActionListener(this);
        
        consultar.setBounds(10,10,200,100);
        consultar.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
        consultar.setBackground(Color.WHITE);
        cp.add(consultar);
        consultar.addActionListener(this);
        
        retirar.setBounds(480,225,200,100);
        retirar.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
        retirar.setBackground(Color.WHITE);
        cp.add(retirar);
        retirar.addActionListener(this);
        
        salir.setBounds(480,10,200,30);
        salir.setBackground(Color.WHITE);
        cp.add(salir);
        salir.addActionListener(this);
        
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==retirar){
            new Valor(cliente,"retirar");
            this.setVisible(false);
        }
        if(e.getSource()==consignar){
            new Valor(cliente,"consignar");
            this.setVisible(false);
        }
        if(e.getSource()==salir){
            new Inicial(cliente.getCajero());
            this.setVisible(false);
        }
        if(e.getSource()==consultar){
            new Clave(cliente);
            this.setVisible(false);
        }
    }
}
