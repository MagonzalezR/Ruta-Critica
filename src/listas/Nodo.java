/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package listas;

/**
 *
 * @author Miguel
 */
public class Nodo {
    private String numero;
    private int posX;
    private int posY;
    private int tiempoTem;
    private int tiempoTard;
    private Nodo sig;
    
    public Nodo(int x, int y, String numero){
        sig=null;
        this.posX=x;
        this.posY=y;
        this.numero=numero;
        tiempoTard=99;
        tiempoTem=0;
    }

    public void setSig(Nodo sig) {
        this.sig = sig;
    }

    public String getNumero() {
        return numero;
    }

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }

    public Nodo getSig() {
        return sig;
    }

    public int getTiempoTem() {
        return tiempoTem;
    }

    public void setTiempoTem(int tiempoTem) {
        this.tiempoTem = tiempoTem;
    }

    public int getTiempoTard() {
        return tiempoTard;
    }

    public void setTiempoTard(int tiempoTard) {
        this.tiempoTard = tiempoTard;
    }
    
}
