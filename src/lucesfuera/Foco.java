package lucesfuera;


import java.awt.Color;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JToggleButton;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Galvez
 */
public class Foco extends JToggleButton implements ActionListener {
    
    private LucesFuera padre;
    private final int posI, posJ;
    private final ImageIcon iconApagado, iconEncendido;
    private boolean estaEncendido;
    
    public Foco(LucesFuera pad, int i, int j){
        padre=pad;
        posI=i;
        posJ=j;
        
        //setText(i+","+j);
        iconApagado  = new javax.swing.ImageIcon(getClass().getResource("/imagenes/apagado2.png"));
        iconEncendido= new javax.swing.ImageIcon(getClass().getResource("/imagenes/encendido2.png"));
        apagar();
        addActionListener(this);
    }
    
    public void encender(){
        setIcon(iconEncendido);
        estaEncendido=true;
        setSelected(false);
        setBackground(new Color(255, 255, 170));
    }
    public void apagar(){
       setIcon(iconApagado);
       estaEncendido=false;
       setSelected(true);
       setBackground(new Color(68, 84, 106));
    }
   
    public void hagoClick(){
        if(!estaEncendido)
            encender();
        else
            apagar();
    }
    
    public void actionPerformed(java.awt.event.ActionEvent evt) {
       //aqui escribo lo q ocurre si presiono el boton
       hagoClick();
       padre.encenderAdyacentes(posI, posJ);
    }
 
    public boolean estaEncendido(){return estaEncendido;}//metodo que nos dice si esta encendido
            
}
