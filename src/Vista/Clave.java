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
    String operacion=null;
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
        initComp();
    }  
    
    public Clave(Cliente cliente){
        super("Ingrese la clave");
        this.cliente=cliente;
        initComp();
    } 
    
    private void initComp(){
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
        
        clave.setBounds(250, 70, 150, 30);
        clave.setFont(new Font(Font.SANS_SERIF, Font.ROMAN_BASELINE, 20));
        cp.add(clave); 
        
        ingresar.setBounds(250,110,150,30);
        ingresar.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
        ingresar.setBackground(Color.WHITE);
        cp.add(ingresar);
        ingresar.addActionListener(this);
        
        this.setVisible(true);        
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==ingresar){
            if(cliente.ingresarClave(clave.getText())){
                this.setVisible(false);
                if(operacion==null){
                    new Consulta(cliente);
                    this.setVisible(false);
                }
                else if(cliente.seleccionarOperacion(operacion, valor))
                    new Operacion(cliente);
            }
            else if(cliente.getCajero().getContador()<=2){
                JOptionPane.showMessageDialog(null, "Clave incorrecta", "Error", 0);
                clave.setText("");
            }
            else{
                this.setVisible(false);
            }
        }
    }
    
}
