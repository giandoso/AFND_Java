/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PDA;

/**
 *
 * @author giandoso
 */
public class Transicao {
    int origem;
    String valor;
    String topo;
    int destino;
    String novo_topo;

    public Transicao(int origem, String valor, String topo, int destino, String novo_topo) {
        this.origem = origem;
        this.valor = valor;
        this.topo = topo;
        this.destino = destino;
        this.novo_topo = novo_topo;
    }

    
}
