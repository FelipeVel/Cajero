package Vista;

import Logica.Cliente;
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
        cp.setBackground(new Color(165, 234, 70));
        
        valores[0]=new JButton("Otro");
        valores[1]=new JButton("25.000");
        valores[2]=new JButton("50.000");
        valores[3]=new JButton("100.000");
        valores[4]=new JButton("500.000");
        valores[5]=new JButton("1'000.000");
        
        for (int i=0;i<3;i++){
            valores[i].setBounds(10,100+(i*110),200,100);
            valores[i].setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
            valores[i].setBackground(Color.WHITE);
            cp.add(valores[i]);
            valores[i].addActionListener(this);            
        }
        for (int i=0;i<3;i++){
            valores[i+3].setBounds(480,100+(i*110),200,100);
            valores[i+3].setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
            valores[i+3].setBackground(Color.WHITE);
            cp.add(valores[i+3]);
            valores[i+3].addActionListener(this);            
        }
        
        volver.setBounds(480,10,200,30);
        volver.setBackground(Color.WHITE);
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
            this.setVisible(false);
            new otroValor(cliente,operacion);
        }
        if(e.getSource()==valores[1]){
            this.setVisible(false);
            new Clave(cliente,operacion,25000);
        }
        if(e.getSource()==valores[2]){
            this.setVisible(false);
            new Clave(cliente,operacion,50000);
        }
        if(e.getSource()==valores[3]){
            this.setVisible(false);
            new Clave(cliente,operacion,100000);
        }
        if(e.getSource()==valores[4]){
            this.setVisible(false);
            new Clave(cliente,operacion,500000);
        }
        if(e.getSource()==valores[5]){
            this.setVisible(false);
            new Clave(cliente,operacion,1000000);
        }
    }
    
}
