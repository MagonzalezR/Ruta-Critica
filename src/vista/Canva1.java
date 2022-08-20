/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JOptionPane;
import listas.*;

/**
 *
 * @author Miguel
 */
public class Canva1 extends Canvas implements MouseListener {

    private ColaNodo nodos;
    private ColaLinea lineas;
    private int cont;
    private boolean yaRecorrio;
    private Ventana miventana;
    private String[][] miMatriz, matrizAct, matrizDias, matrizInversa, matrizActInversa, matrizDiasInversa;

    int[] ordenOrdenado, ordenInverso;

    public Canva1() {
        this.setBounds(30, 60, 670, 327);
        this.setBackground(Color.white);
        cont = 1;
        addMouseListener(this);
        nodos = new ColaNodo();
        lineas = new ColaLinea();
        miventana = new Ventana(this);
        miventana.setVisible(true);
        yaRecorrio = false;
    }

    @Override
    public void paint(Graphics g) {     //funcion para dibujar en el canvas
        g.setFont(new java.awt.Font("Verdana", 1, 10));
        Nodo auxN = nodos.getCabeza();
        while (auxN != null) {          //recorre la cola de nodos y los dibuja en pantalla
            g.setColor(Color.red);
            g.drawString(auxN.getNumero(), auxN.getPosX(), auxN.getPosY());
            if (yaRecorrio) {
                g.setColor(new java.awt.Color(116,100,170));      //dibuja los tiempos tempranos
                g.drawString(auxN.getTiempoTem() + "", auxN.getPosX(), auxN.getPosY() - 12);
                g.setColor(Color.MAGENTA);         //dibuja los tiempos tarde
                g.drawString(auxN.getTiempoTard() + "", auxN.getPosX(), auxN.getPosY() + 12);
            }
            auxN = auxN.getSig();
        }
        Flecha flecha;
        Linea auxL = lineas.getCabeza();
        while (auxL != null) {          //recorre la cola de caminos y los dibuja en pantalla
            if (!auxL.isEsCritico()) {
                g.setColor(Color.black);
            } else {
                g.setColor(Color.green);
            }
            flecha = new Flecha(new Point(auxL.getNodo2x(), auxL.getNodo2y()), new Point(auxL.getNodo1x(), auxL.getNodo1y()));
            //g.drawLine(auxL.getNodo1x(), auxL.getNodo1y(), auxL.getNodo2x(), auxL.getNodo2y());
            flecha.calcularFlecha();
            g.drawLine(flecha.getLineaP1().x, flecha.getLineaP1().y, flecha.getLineaP2().x, flecha.getLineaP2().y);
            g.drawLine(flecha.getLineaP1().x, flecha.getLineaP1().y, flecha.getArista1P().x, flecha.getArista1P().y);
            g.drawLine(flecha.getLineaP1().x, flecha.getLineaP1().y, flecha.getArista2P().x, flecha.getArista2P().y);
            g.setColor(Color.blue);
            g.drawString("act" + auxL.getActv() + ": " + auxL.getDias(), (flecha.getLineaP1().x + flecha.getLineaP2().x - 10) / 2, (flecha.getLineaP1().y + flecha.getLineaP2().y) / 2);
            auxL = auxL.getSig();
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {       //Funcion para generar los nodos en el canvas en la posición en la que se suelta el click isz del mouse
        if (cont < 20) {        //se pueden poner hasta 19 nodos
            Nodo nuevo = new Nodo(e.getX(), e.getY(), "" + cont++);
            nodos.agregar(nuevo);
            repaint();
            miventana.activarBotones();
        } else {
            JOptionPane.showMessageDialog(miventana, "Numero de nodos excedido", "Atencion", 2);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    public void borrar() {
        nodos = new ColaNodo();
        lineas = new ColaLinea();
        cont = 1;
        yaRecorrio = false;
        repaint();
    }

    public void generarTabla() {
        miventana.generarTablas(cont - 1, 99);
    }

    public void generarTabla(int column, int opc) {
        miventana.generarTablas(column, opc);
    }

    private void lineasGrafo() {        //funcion para agregar las lineas por la posición de los nodos
        lineas = new ColaLinea();
        for (int i = 0; i < miMatriz.length; i++) {
            for (int j = 0; j < miMatriz[0].length; j++) {
                try {
                    Nodo nodo1 = nodos.buscar(String.valueOf(i + 1));
                    Nodo nodo2 = nodos.buscar(miMatriz[i][j]);
                    if (nodo1 != null && nodo2 != null) {
                        Linea nueva = new Linea(nodo1.getPosX() + 7,
                                nodo1.getPosY() - 5,
                                nodo2.getPosX(),
                                nodo2.getPosY() - 5 + (nodo1.getPosY() - nodo2.getPosY()) / 8,
                                matrizAct[i][j],
                                Integer.parseInt(matrizDias[i][j]),
                                nodo1,
                                nodo2);
                        lineas.agregar(nueva);
                    }
                } catch (Error e) {

                }
            }
        }
        repaint();
    }

    public int[] ordenT(int[] ordenOrdenado, String[][] miMatriz) {
        int tam = miMatriz.length;
        int[][] matrizTopo, cola;
        matrizTopo = new int[tam + 1][tam];
        cola = new int[2][tam];
        for (int i = 1; i < tam + 1; i++) {
            for (int j = 0; j < tam; j++) {
                matrizTopo[i][j] = 0;
            }
        }
        for (int i = 0; i < tam; i++) {
            cola[0][i] = 0;
            cola[1][i] = 0;
            for (int j = 0; j < 4; j++) {
                if ((miMatriz[i][j]) != null && !"".equals(miMatriz[i][j])) {
                    matrizTopo[0][Integer.parseInt(miMatriz[i][j]) - 1]++;
                }
            }

        }
        actCola(0, cola, matrizTopo);
        for (int i = 1; i < tam; i++) {
            System.arraycopy(matrizTopo[i - 1], 0, matrizTopo[i], 0, tam);
            int proxCola = getSigCola(cola);
            ordenOrdenado[i - 1] = proxCola;
            if (proxCola == 0) {
                return null;
            }
            for (int j = 0; j < 4; j++) {
                if ((miMatriz[proxCola - 1][j]) != null && !"".equals(miMatriz[proxCola - 1][j])) {
                    matrizTopo[i][Integer.parseInt(miMatriz[proxCola - 1][j]) - 1]--;
                }
            }
            actCola(i, cola, matrizTopo);
        }
        ordenOrdenado[tam - 1] = getSigCola(cola);
        return ordenOrdenado;

    }

    private String adyInvertida() {
        String ret = "";
        int tam = miMatriz.length;
        matrizInversa = new String[tam][4];
        matrizActInversa = new String[tam][4];
        matrizDiasInversa = new String[tam][4];
        boolean hayAnt;
        for (int i = 0; i < tam; i++) {
            ret += "Nodo" + String.valueOf(i + 1) + ": ";
            hayAnt = false;
            for (int j = 0; j < tam; j++) {
                for (int k = 0; k < 4; k++) {
                    if (String.valueOf(i + 1).equals(miMatriz[j][k])) {
                        if (hayAnt) {
                            ret += " -> ";
                        }
                        hayAnt = true;
                        ret += (j + 1);
                        for (int m = 0; m < 4; m++) {
                            if (matrizInversa[i][m] == null || "".equals(matrizInversa[i][m])) {
                                matrizInversa[i][m] = (j + 1) + "";
                                matrizActInversa[i][m] = matrizAct[j][k];
                                matrizDiasInversa[i][m] = matrizDias[j][k];
                                ret +=","+matrizActInversa[i][m]+","+matrizDiasInversa[i][m];
                                break;
                            }
                        }
                        
                        break;
                    }
                }
            }
            ret += "\n";
        }
        for (int i = 0; i < tam; i++) {
            for (int j = 0; j < 4; j++) {
                if (matrizInversa[i][j] == null) {
                    matrizInversa[i][j] = "";
                    matrizActInversa[i][j] = "";
                    matrizDiasInversa[i][j] = "";
                }
            }
        }
        return ret;
    }

    private void agregarACola(int num, int[][] cola) {
        int i = 0;
        while (i < cola[0].length && cola[0][i] != 0) {
            if (cola[0][i] == num) {
                return;
            }
            i++;
        }
        if (i >= cola[0].length) {
            return;
        }
        if (cola[1][i] != 0) {
            return;
        }
        cola[0][i] = num;
    }

    private void actCola(int fila, int[][] cola, int[][] matrizTopo) {
        for (int i = 0; i < cola[0].length; i++) {
            if (matrizTopo[fila][i] == 0) {
                agregarACola(i + 1, cola);
            }
        }
    }

    private int getSigCola(int[][] cola) {
        for (int i = 0; i < cola[0].length; i++) {
            if (cola[0][i] != 0 && cola[1][i] == 0) {
                cola[1][i] = 1;
                return cola[0][i];
            }
        }
        return 0;
    }

    public void rutaCritica(String[][] matrizAdy, String[][] matrizAct, String[][] matrizDias) {
        String[] tiempos;
        miMatriz = matrizAdy;
        this.matrizAct = matrizAct;
        this.matrizDias = matrizDias;
        this.ordenOrdenado = new int[miMatriz.length];
        miventana.textoAdyInv(adyInvertida());
        this.ordenOrdenado = ordenT(this.ordenOrdenado, this.miMatriz);
        if (ordenOrdenado == null) {
            return;
        }
        lineasGrafo();
        if (!lineas.verificarLineas()) {
            JOptionPane.showMessageDialog(miventana, "Error en las actividades, no pueden ser repetidas ni omitidas");
            return;
        }
        yaRecorrio = true;
        Nodo nodo = null;
        for (int i = 0; i < miMatriz.length; i++) {
            for (int j = 0; j < 4; j++) {
                if (!"".equals(miMatriz[ordenOrdenado[i] - 1][j])) {
                    nodo = nodos.buscar(miMatriz[ordenOrdenado[i] - 1][j]);
                    Nodo anterior = nodos.buscar(String.valueOf(ordenOrdenado[i]));
                    if (nodo.getTiempoTem() < (Integer.parseInt(this.matrizDias[ordenOrdenado[i] - 1][j]) + anterior.getTiempoTem())) {
                        nodo.setTiempoTem(Integer.parseInt(this.matrizDias[ordenOrdenado[i] - 1][j]) + anterior.getTiempoTem());
                    }
                }
            }
        }
        if (nodo != null) {
            nodo.setTiempoTard(nodo.getTiempoTem());
        }
        tiempos = nodos.tiemposTemp();
        generarTabla(tiempos.length, 1);
        matrizVista(1, tiempos);
        this.ordenInverso = new int[matrizInversa.length];
        this.ordenInverso = ordenT(this.ordenInverso, this.matrizInversa);
        if (ordenInverso == null) {
            return;
        }

        for (int i = 0; i < matrizInversa.length; i++) {

            for (int j = 0; j < 4; j++) {
                if (!"".equals(matrizInversa[ordenInverso[i] - 1][j])) {
                    nodo = nodos.buscar(matrizInversa[ordenInverso[i] - 1][j]);
                    Nodo anterior = nodos.buscar(String.valueOf(ordenInverso[i]));
                    if (nodo.getTiempoTard() > anterior.getTiempoTard() - (Integer.parseInt(this.matrizDiasInversa[ordenInverso[i] - 1][j]))) {
                        nodo.setTiempoTard(anterior.getTiempoTard() - (Integer.parseInt(this.matrizDiasInversa[ordenInverso[i] - 1][j])));
                    }
                }
            }
        }
        tiempos = nodos.tiemposTarde();
        generarTabla(tiempos.length, 2);
        matrizVista(2, tiempos);
//        String out = "";
//        for (int i = 0; i < miMatriz.length; i++) {
//            for (int j = 0; j < 4; j++) {
//                out += matrizInversa[i][j] + ", ";
//            }
//            out += "\n";
//        }
//        System.out.println(out);
        tiempos = lineas.actividadesTemp();
        generarTabla(tiempos.length, 4);
        matrizVista(4, tiempos);
        tiempos = lineas.actividadesTarde();
        generarTabla(tiempos.length, 3);
        matrizVista(3, tiempos);
        lineas.lineasCriticas();
        repaint();
    }

    private void matrizVista(int opc, String[] matriz) {
        switch (opc) {
            case 1:
                miventana.setMatriz(1, matriz);
                break;
            case 2:
                miventana.setMatriz(2, matriz);
                break;
            case 3:
                miventana.setMatriz(3, matriz);
                break;
            case 4:
                miventana.setMatriz(4, matriz);
                break;
        }
    }
}
