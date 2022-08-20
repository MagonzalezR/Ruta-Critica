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
public class ColaNodo {
    private Nodo cabeza;
    public ColaNodo(){
        cabeza=null;
    }

    public Nodo getCabeza() {
        return cabeza;
    }

    public void setCabeza(Nodo cabeza) {
        this.cabeza = cabeza;
    }
    
    public void agregar(Nodo nuevo){
        if(cabeza==null){
            cabeza=nuevo;
            return;
        }
        Nodo aux=cabeza;
        while(aux.getSig() !=null){
            aux=aux.getSig();
        }
        aux.setSig(nuevo);
    }
    
    public Nodo buscar(String valor){
        Nodo aux=cabeza;
        while(aux!=null){
            if(aux.getNumero().equals(valor)) return aux;
            aux=aux.getSig();
        }
        return null;
    }
    private int contarNodos(){
        int ret=0;
        Nodo aux=cabeza;
        while(aux!=null){
            ret++;
            aux=aux.getSig();
        }
        return ret;
    }
    public String[] tiemposTemp(){
        String [] ret=new String[contarNodos()];
        Nodo aux=cabeza;
        int i=0;
        while(aux!=null){
            ret[i++]=String.valueOf(aux.getTiempoTem());
            aux=aux.getSig();
        }
        return ret;
    }
    public String[] tiemposTarde(){
        String [] ret=new String[contarNodos()];
        Nodo aux=cabeza;
        int i=0;
        while(aux!=null){
            ret[i++]=String.valueOf(aux.getTiempoTard());
            aux=aux.getSig();
        }
        return ret;
    }
}
