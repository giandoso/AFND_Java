/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PDA;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author giandoso
 */
public class Automato {

    public List<Estado> estados;

    public Automato() {
    }

    public void verifica_cadeia(String palavra, String subcadeia, Estado estado_atual, String pilha, String fundo_pilha, boolean debug, int index) {
        if (Main.palavras_aceitas[index] != true) {
            if (estado_atual.estado_final == true && subcadeia.length() == 0) {
                Main.palavras_aceitas[index] = true;
            }
            for (Transicao transicao_atual : estado_atual.lista_transicoes) {
                Estado next = getEstado(transicao_atual.destino);
                //logica automato de pilha
                if ("@".equals(transicao_atual.valor)) {
                    String nova_pilha = pilha;
                    String topo = Character.toString(pilha.charAt(0));
                    if (topo.equals(transicao_atual.topo)) {
                        if (!"@".equals(transicao_atual.novo_topo)) {
                            nova_pilha = transicao_atual.novo_topo + pilha.substring(1);
                        } else {
                            nova_pilha = pilha.substring(1);
                        }
                        if (debug) {
                            System.out.println("Palavra: " + palavra);
                            System.out.println("Subcadeia atual: " + subcadeia);
                            System.out.println("Pilha atual: " + nova_pilha);
                            System.out.println("(" + estado_atual.id + ") ----> (" + next.id + ")");
                            System.out.println("       " + transicao_atual.valor + "\n");
                        }
                        verifica_cadeia(palavra, subcadeia, next, nova_pilha, fundo_pilha, debug, index);
                    }
                } else {
                    if (subcadeia.length() != 0) {
                        String current_valor = Character.toString(subcadeia.charAt(0));
                        if (current_valor.equals(transicao_atual.valor)) {
                            String nova_pilha = pilha;
                            String topo = Character.toString(pilha.charAt(0));
                            if (topo.equals(transicao_atual.topo)) {
                                if (!"@".equals(transicao_atual.novo_topo)) {
                                    nova_pilha = transicao_atual.novo_topo + pilha.substring(1);
                                } else {
                                    nova_pilha = pilha.substring(1);
                                }
                                if (debug) {
                                    System.out.println("Palavra: " + palavra);
                                    System.out.println("Subcadeia atual: " + subcadeia);
                                    System.out.println("Pilha atual: " + nova_pilha);
                                    System.out.println("(" + estado_atual.id + ") ----> (" + next.id + ")");
                                    System.out.println("       " + transicao_atual.valor + "\n");
                                }
                                verifica_cadeia(palavra, subcadeia.substring(1), next, nova_pilha, fundo_pilha, debug, index);
                            }
                        }
                    }
                }
            }
        }
    }

    public void set_estados(List<Estado> estados) {
        this.estados = estados;
    }

    private Estado getEstado(int destino) {
        for (Estado e : this.estados) {
            if (destino == e.id) {
                return e;
            }
        }
        System.out.println("Deu bosta! Estado não existente");
        return null;
    }

}
