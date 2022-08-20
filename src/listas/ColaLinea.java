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
public class ColaLinea {

    private Linea cabeza;

    public ColaLinea() {
        cabeza = null;
    }

    public Linea getCabeza() {
        return cabeza;
    }

    public void agregar(Linea nuevo) {
        if (cabeza == null) {
            cabeza = nuevo;
            return;
        }
        Linea aux = cabeza;
        while (aux.getSig() != null) {
            aux = aux.getSig();
        }
        aux.setSig(nuevo);
    }

    public void setCabeza(Linea cabeza) {
        this.cabeza = cabeza;
    }

    private int contarLineas() {
        int num = 0;
        Linea aux = cabeza;
        while (aux != null) {
            num++;
            aux = aux.getSig();
        }
        return num;
    }

    private Linea buscarAct(String act) {
        Linea aux = cabeza;
        while (aux != null) {
            if (aux.getActv().equals(act)) {
                return aux;
            }
            aux = aux.getSig();
        }
        return aux;
    }

    public String[] actividadesTemp() {
        String[] retorno = new String[contarLineas()];
        for (int i = 1; i < retorno.length + 1; i++) {
            Linea aux = buscarAct(i + "");
            retorno[i - 1] = String.valueOf(aux.getNAnt().getTiempoTem());
        }
        return retorno;
    }

    public String[] actividadesTarde() {
        String[] retorno = new String[contarLineas()];
        for (int i = 1; i < retorno.length + 1; i++) {
            Linea aux = buscarAct(i + "");
            retorno[i - 1] = String.valueOf(aux.getNSig().getTiempoTard() - aux.getDias());
        }
        return retorno;
    }

    public boolean verificarLineas() {
        String[] totalLineas = new String[contarLineas() + 1];
        Linea aux = cabeza;
        int i = 0;
        while (aux != null) {
            try {
                if (totalLineas[Integer.parseInt(aux.getActv())] == null) {
                    totalLineas[Integer.parseInt(aux.getActv())] = aux.getActv();
                } else {
                    return false;
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                return false;
            }
            aux = aux.getSig();
        }
        return true;
    }
    public void lineasCriticas(){
        Linea aux = cabeza;
        while (aux != null) {
            if(aux.getNAnt().getTiempoTard()==aux.getNAnt().getTiempoTem() && aux.getNSig().getTiempoTem()==aux.getNSig().getTiempoTard()){
                aux.setEsCritico(true);
            }
            aux=aux.getSig();
        }
    }
}
