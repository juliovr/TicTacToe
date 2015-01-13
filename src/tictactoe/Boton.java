/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe;

import javax.swing.JButton;

/**
 *
 * @author Julio
 */
public class Boton extends JButton {
    
    private boolean marcado;
    private char foto;

    public Boton() {
        marcado = false;
    }

    public boolean isMarcado() {
        return marcado;
    }

    public void setMarcado(boolean marcado) {
        this.marcado = marcado;
    }

    public char getFoto() {
        return foto;
    }

    public void setFoto(char foto) {
        this.foto = foto;
    }
    
}
