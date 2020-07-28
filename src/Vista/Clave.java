package Vista;

import Logica.Cliente;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.SwingConstants;

public class Clave extends JFrame implements ActionListener {
    
    Cliente cliente;
    String operacion;
    int valor;
    JPanel cp;
    JLabel l1 = new JLabel("Ingrese la contraseña de la tarjeta:", SwingConstants.CENTER);
    JTextField clave = new JTextField();
    JButton ingresar = new JButton("Ingresar");
    
    public Clave(Cliente cliente, String operacion, int valor){
        super("Ingrese la clave");
        this.cliente=cliente;
        this.operacion=operacion;
        this.valor=valor;
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
        
        clave.setBounds(250, 70, 150, 30);
        clave.setFont(new Font(Font.SANS_SERIF, Font.ROMAN_BASELINE, 20));
        cp.add(clave); 
        
        ingresar.setBounds(250,110,150,30);
        ingresar.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
        ingresar.setBackground(new Color(255,255,43));
        cp.add(ingresar);
        ingresar.addActionListener(this);
        
        this.setVisible(true);  
    }  
    
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==ingresar){
            if(cliente.ingresarClave(clave.getText())){                
                cliente.seleccionarOperacion(operacion, valor);
                this.setVisible(false);
                new Operacion(cliente);
            }
            else{
                clave.setText("");
            }
        }
    }
    
}
