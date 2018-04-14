/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package lucesfuera;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.util.Random;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JToolBar;
import javax.swing.JToolBar.Separator;
import javax.swing.UIManager;

/**
 *
 * @author Galvez
 */
public class LucesFuera extends JFrame{

    /**
     * @param args the command line arguments
     */
    private JPanel panelPrincipal;
    private JToolBar toolbar;
    private final int M_asignado, N_asignado;
    private Foco[][] foquitos;
    private JSpinner spN, spM;
    private JButton btnReiniciar, btnResolver, btnRandomizar;
    private JLabel  encendidos, movimientos;
    private int numEncendidos;
    
    public LucesFuera(int m, int n){
        M_asignado=m;
        N_asignado=n;
        numEncendidos=0;
        setTitle("Luces fuera v 1.0");
        setLayout(new BorderLayout());
        
        crearComponentes();
        
        setSize(700, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
    }
    private void crearComponentes(){
        panelPrincipal = new JPanel(new GridLayout(M_asignado, N_asignado, 5, 5));
        
        generarMatrizNxM(M_asignado, N_asignado);
        
        add(panelPrincipal, BorderLayout.CENTER);
        
        toolbar = new JToolBar();
        toolbar.setBorder(javax.swing.BorderFactory.createLineBorder(Color.white)); 
        
        JLabel lblM = new JLabel(" M: ");
        toolbar.add(lblM);
        spM= new JSpinner();
        spM.setModel(new javax.swing.SpinnerNumberModel(M_asignado, 1, 16, 1));//inicial, minimo, maximo, de cuanto en cuanto sube
        toolbar.add(spM);
        
        JLabel lblN = new JLabel(" N: ");
        toolbar.add(lblN);
        spN= new JSpinner();
        spN.setModel(new javax.swing.SpinnerNumberModel(N_asignado, 1, 16, 1));
        toolbar.add(spN);
        
        btnReiniciar = new JButton("REINICIAR");
        btnReiniciar.setForeground(new java.awt.Color(0, 0, 102));
        btnReiniciar.setPreferredSize(new java.awt.Dimension(85, 30));
        btnReiniciar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReiniciarActionPerformed(evt);
            }
        });
        toolbar.add(btnReiniciar);
        
        Separator separador = new Separator();
        toolbar.add(separador);
        btnResolver = new JButton("RESOLVER");
        btnResolver.setForeground(new java.awt.Color(52, 79, 33));
        btnResolver.setPreferredSize(new java.awt.Dimension(85, 30));
        btnResolver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResolverActionPerformed(evt);
            }
        });
        toolbar.add(btnResolver);
        
        btnRandomizar = new JButton("RANDOMIZAR");
        btnRandomizar.setForeground(new java.awt.Color(168, 0, 0));
        btnRandomizar.setPreferredSize(new java.awt.Dimension(90, 30));
        btnRandomizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRandomizarActionPerformed(evt);
            }
        });
        toolbar.add(btnRandomizar);
        
        Separator separador2 = new Separator();
        toolbar.add(separador2);
        encendidos = new JLabel();
        toolbar.add(encendidos);
        Separator separador3 = new Separator();
        toolbar.add(separador3);
        movimientos = new JLabel();
        toolbar.add(movimientos);
        
        add(toolbar, BorderLayout.NORTH);
        
        int m=(int)spM.getValue();
        int n=(int)spN.getValue();
        int datoNum=(m*n)/2;
        randomizarLuces(datoNum);
        encendidos.setText("ENCENDIDOS "+datoNum+" ");
        movimientos.setText("MOVIMIENTOS 0");
    }
    private void generarMatrizNxM(int m, int n){
        foquitos= new Foco[m][n];
        for (int i = 0; i < foquitos.length; i++) {
            for (int j = 0; j < foquitos[i].length; j++) {
                foquitos[i][j]= new Foco(this,i,j);
                panelPrincipal.add(foquitos[i][j]);
            }
        }
    }
    
    private void btnReiniciarActionPerformed(java.awt.event.ActionEvent evt) {                                            
        // TODO add your handling code here:
        int m=(int)spM.getValue();
        int n=(int)spN.getValue();

        LucesFuera lf=new LucesFuera(m,n);
        lf.setVisible(true);
        dispose();
    }
    private void btnRandomizarActionPerformed(java.awt.event.ActionEvent evt) {                                            
        // TODO add your handling code here:
        Random rdm=new Random();
        int m=rdm.nextInt(12)+4;
        int n=rdm.nextInt(12)+4;

        LucesFuera lf=new LucesFuera(m,n);
        lf.setVisible(true);
        dispose();
    }
    private void btnResolverActionPerformed(java.awt.event.ActionEvent evt) {                                            
        // TODO add your handling code here:
    }
    
    public void encenderAdyacentes(int i, int j){
        if(validarI(i-1)){
            foquitos[i-1][j].hagoClick();//arriba
        }
        if(validarI(i+1)){
            foquitos[i+1][j].hagoClick();//abajo
        }
        if(validarJ(j-1)){
            foquitos[i][j-1].hagoClick();//izquierda
        }
        if(validarJ(j+1)){
            foquitos[i][j+1].hagoClick();//derecha
        }
        actualizarEncendidos(); 
    }
    private boolean validarI(int n){
        boolean res=false;
        if(n>=0 && n<foquitos.length)
            res=true;
        return res;
    }
    private boolean validarJ(int n){
        boolean res=false;
        if(n>=0 && n<foquitos[0].length)
            res=true;
        return res;
    }
    private void randomizarLuces(int nFocosEncender){
        System.out.println("nro "+nFocosEncender);
        Random rdm=new Random();
        int cont=0;
        while (cont<nFocosEncender) {            
            int numRandomEnI=rdm.nextInt((int)spM.getValue()+1);
            int numRandomEnJ=rdm.nextInt((int)spN.getValue()+1);
            if(validarI(numRandomEnI) && validarJ(numRandomEnJ)){
                if(!foquitos[numRandomEnI][numRandomEnJ].estaEncendido()){
                    foquitos[numRandomEnI][numRandomEnJ].encender();
                    cont++;
                    System.out.println("cont "+cont);
                }
            }
        }
    }
    
    private void actualizarEncendidos(){
        int cont=0;
        for (int i = 0; i < foquitos.length; i++) {
            for (int j = 0; j < foquitos[i].length; j++) {
                if(foquitos[i][j].estaEncendido())
                    cont++;
            }
        }
        encendidos.setText("ENCENDIDOS "+cont+" ");
        numEncendidos++;
        movimientos.setText("MOVIMIENTOS "+numEncendidos);
        if(cont==0){
            JOptionPane.showMessageDialog(panelPrincipal,
                    "Felicitaciones! lograste apagar todos los focos..","Mesaje",
                        JOptionPane.PLAIN_MESSAGE);
        }
    }
    
    public static void main(String[] args) {
        // TODO code application logic here
        try{
            JFrame.setDefaultLookAndFeelDecorated(true);
            JDialog.setDefaultLookAndFeelDecorated(true);
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
            //UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
            //UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
            //UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");
        }
        catch (Exception e)
        {
          e.printStackTrace();
        }
        LucesFuera lf=new LucesFuera(5,6);
        lf.setVisible(true);
    }
    
}
