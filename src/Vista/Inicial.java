package Vista;

import Logica.Cliente;
import java.awt.*;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import javax.swing.*;

public class Inicial extends JFrame implements ActionListener{
    
    JPanel cp;
    JLabel l1 = new JLabel("Bienvenido, ingrese el nombre de la tarjeta:", SwingConstants.CENTER);
    JTextField nombre = new JTextField();
    JButton ingresar = new JButton("Ingresar");
    
    public Inicial() throws HeadlessException {
        super("Ingresar tarjeta");
        this.setSize(700, 200);
        this.setLocationRelativeTo(null);
        cp = new JPanel();
        
        this.setContentPane(cp);
        cp.setLayout(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        cp.setBackground(new Color(255,22,22));
        
        l1.setBounds(0, 30, 700, 30);
        l1.setFont(new Font(Font.SANS_SERIF, Font.ROMAN_BASELINE, 20));
        cp.add(l1);
        
        nombre.setBounds(250, 70, 150, 30);
        nombre.setFont(new Font(Font.SANS_SERIF, Font.ROMAN_BASELINE, 20));
        cp.add(nombre); 
        
        ingresar.setBounds(250,110,150,30);
        ingresar.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
        ingresar.setBackground(new Color(255,255,43));
        cp.add(ingresar);
        ingresar.addActionListener(this);
        
        this.setVisible(true);        
    }
    
    public static void main (String[] args){
        new Inicial();
    }

    @Override
    public void actionPerformed(java.awt.event.ActionEvent e){
        if(e.getSource()==ingresar){
            try {
                this.setVisible(false);
                System.out.println("Ingresando");
                Cliente cliente = new Cliente(nombre.getText());
                new Operacion(cliente);
            } catch (SQLException ex) {
                System.out.println("No se pudo ingresar: "+ex);
            }
        }
    }
    
}
