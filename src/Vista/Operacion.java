package Vista;

import Logica.Cliente;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Operacion extends JFrame implements ActionListener{
    
    JPanel cp;
    JLabel bienvenida = new JLabel("Bienvenido/a, ");
    JButton consignar = new JButton("Consignar");
    JButton retirar = new JButton("Retirar");
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
        cp.setBackground(new Color(255,22,22));
        
        bienvenida.setBounds(10, 10, 700, 30);
        bienvenida.setText(bienvenida.getText()+cliente.getNombre());
        bienvenida.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
        cp.add(bienvenida);
        
        consignar.setBounds(10,225,200,100);
        consignar.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
        consignar.setBackground(new Color(255,255,43));
        cp.add(consignar);
        consignar.addActionListener(this);
        
        retirar.setBounds(480,225,200,100);
        retirar.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
        retirar.setBackground(new Color(255,255,43));
        cp.add(retirar);
        retirar.addActionListener(this);
        
        salir.setBounds(480,10,200,30);
        salir.setBackground(new Color(255,255,43));
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
    }
}
