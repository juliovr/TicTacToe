/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author julio
 */
public class TicTacToe extends javax.swing.JFrame implements ActionListener {

    private int cont;
    private boolean terminado;
    private final Boton[][] tablero;
    private final URL O;
    private final URL X;
    
    /**
     * Creates new form TicTacToe.
     * Inicializa el tablero de 3x3, asigna los bordes a cada botón y su método 
     * para cuando se seleccionen.
     */
    public TicTacToe() {
        initComponents();
        setResizable(false);
        setLocationRelativeTo(null);
        cont = 0;
        terminado = false;
        tablero = new Boton[3][3];
        O = getClass().getResource("/fotos/o.jpg");
        X = getClass().getResource("/fotos/x.jpg");
        
        for (int i = 0; i < tablero.length; i++) {
            for (int j = 0; j < tablero[i].length; j++) {
                tablero[i][j] = new Boton();
                tablero[i][j].setBounds((i * 80) + 5, (j * 80) + 5, 80, 80);
                tablero[i][j].addActionListener(this);
                add(tablero[i][j]);
            }
        }
    }
   
    /**
     * Acción para todos los botones que se encuentran en el tablero.
     * Recorre todos los botones para saber cual es el que se presionó, si el 
     * botón nunca ha sido presionado y el juego aún no termina, llama al 
     * método marcar.
     * @param e 
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        for (int i = 0; i < tablero.length; i++) {
            for (int j = 0; j < tablero[i].length; j++) {
                if (e.getSource() == tablero[i][j]) {
                    if (tablero[i][j].isMarcado() == false && terminado == false) {
                        marcar(tablero[i][j]);
                    }
                }
            }
        }
        
        evaluar(tablero);
        terminar();
        jugarDenuevo();
        cont++;
    }
    
    /**
     * Realizar el marcado del botón colocando una O / X según corresponda y 
     * luego remueve el ActionListener para que no haga nada si el botón se 
     * vuelve a presionar.
     * @param boton objeto boton que se selecciona proveniente del tablero
     */
    private void marcar(Boton boton) {
        ImageIcon icono;
        char foto;
        boton.setMarcado(true);
        if (cont % 2 == 0) {
            icono = new ImageIcon(O);
            foto = 'o';
        } else {
            icono = new ImageIcon(X);
            foto = 'x';
        }
        boton.setIcon(icono);
        boton.setFoto(foto);
        boton.removeActionListener(this);
    }
    
    /**
     * Evalúa cada los botones para ver si algún jugador ganó la partida.
     * @param tablero el conjunto de botones para evaluar las combinaciones
     */
    private void evaluar(Boton[][] tablero) {
        //fila
        for (int i = 0; i < tablero.length; i++) {
            if (tablero[i][0].getFoto() == tablero[i][1].getFoto() && 
                    tablero[i][0].getFoto() == tablero[i][2].getFoto()) {
                if (tablero[i][0].isMarcado()&& tablero[i][1].isMarcado() && 
                        tablero[i][2].isMarcado()) {
                    terminado = true;
                    JOptionPane.showMessageDialog(null, "Gana " + tablero[i][0].getFoto());
                    break;
                }
            }
        }
        
        //columna
        for (int i = 0; i < tablero.length; i++) {
            if (tablero[0][i].getFoto() == tablero[1][i].getFoto() && 
                    tablero[0][i].getFoto() == tablero[2][i].getFoto()) {
                if (tablero[0][i].isMarcado()&& tablero[1][i].isMarcado() && 
                        tablero[2][i].isMarcado()) {
                    terminado = true;
                    JOptionPane.showMessageDialog(null, "Gana " + tablero[0][i].getFoto());
                    break;
                }
            }
        }
        
        //diagonal 1
        if (tablero[0][0].getFoto() == tablero[1][1].getFoto() && 
                tablero[0][0].getFoto() == tablero[2][2].getFoto()) {
            if (tablero[0][0].isMarcado()&& tablero[1][1].isMarcado() && 
                        tablero[2][2].isMarcado()) {
                terminado = true;
                JOptionPane.showMessageDialog(null, "Gana " + tablero[0][0].getFoto());
            }
        }
        
        //diagonal 2
        if (tablero[0][2].getFoto() == tablero[1][1].getFoto() && 
                tablero[0][2].getFoto() == tablero[2][0].getFoto()) {
            if (tablero[0][2].isMarcado()&& tablero[1][1].isMarcado() && 
                        tablero[2][0].isMarcado()) {
                terminado = true;
                JOptionPane.showMessageDialog(null, "Gana " + tablero[0][2].getFoto());
            }
        }
    }
    
    /**
     * Si todos los botones fueron marcados y no hay premio el juego termina.
     */
    private void terminar() {
        boolean marcados = true;
        for (int i = 0; i < tablero.length; i++) {
            for (int j = 0; j < tablero[i].length; j++) {
                if (!tablero[i][j].isMarcado()) {
                    marcados = false;
                }
            }
        }
        
        if (marcados == true && terminado == false) {
            JOptionPane.showMessageDialog(null, "Juego terminado: no hay ganador");
            terminado = true;
        }
    }
    
    /**
     * Da la opción de volver a jugar.
     */
    private void jugarDenuevo() {
        if (terminado) {
            int opcion = JOptionPane.showConfirmDialog(null, "¿Jugar denuevo?", 
                    "¿Jugar denuevo=", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (opcion == JOptionPane.YES_OPTION) {
                dispose();
                main(null);
            } else {
                System.exit(0);
            }
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 242, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 244, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TicTacToe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TicTacToe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TicTacToe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TicTacToe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TicTacToe().setVisible(true);
            }
        });
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
