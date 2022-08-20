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
public class Linea {

    private int nodo1x;
    private int nodo1y;
    private int nodo2x;
    private int nodo2y;
    private Nodo NAnt;
    private Nodo NSig;
    private String actv;
    private int dias;
    private Linea sig;
    private boolean esCritico;

    public Linea(int nodo1x, int nodo1y, int nodo2x, int nodo2y, String actv, int dias, Nodo ant, Nodo sig) {
        this.nodo1x = nodo1x;
        this.nodo1y = nodo1y;
        this.nodo2x = nodo2x;
        this.nodo2y = nodo2y;
        this.dias = dias;
        this.actv = actv;
        this.NAnt=ant;
        this.NSig=sig;
        sig = null;
        esCritico=false;
    }

    public int getNodo1x() {
        return nodo1x;
    }

    public int getNodo1y() {
        return nodo1y;
    }

    public int getNodo2x() {
        return nodo2x;
    }

    public int getNodo2y() {
        return nodo2y;
    }

    public Linea getSig() {
        return sig;
    }

    public void setSig(Linea sig) {
        this.sig = sig;
    }

    public String getActv() {
        return actv;
    }

    public void setActv(String actv) {
        this.actv = actv;
    }

    public int getDias() {
        return dias;
    }

    public void setDias(int dias) {
        this.dias = dias;
    }

    public boolean isEsCritico() {
        return esCritico;
    }

    public void setEsCritico(boolean esCritico) {
        this.esCritico = esCritico;
    }

    public Nodo getNAnt() {
        return NAnt;
    }

    public void setNAnt(Nodo NAnt) {
        this.NAnt = NAnt;
    }

    public Nodo getNSig() {
        return NSig;
    }

    public void setNSig(Nodo NSig) {
        this.NSig = NSig;
    }
    
}
