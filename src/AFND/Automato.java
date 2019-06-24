/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AFND;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author giandoso
 */
public class Automato {

    static List<Estado> automato = new ArrayList<>();

    public Automato() throws FileNotFoundException {
        ReaderAutomato reader_automato = new ReaderAutomato("src/automato/automato2.input");

        for (int i = 0; i < reader_automato.range(); i++) {
            String first_element = Character.toString(ReaderAutomato.estados.get(i).charAt(0));
            boolean eh_final = "_".equals(first_element);
            Estado e = new Estado(ReaderAutomato.estados.get(i), false, eh_final);
            e.set_lista_transicao(ReaderAutomato.transicoes_parsed.get(i));
            automato.add(e);
        }
    }

    public void verifica_cadeia(String palavra, String subcadeia, Estado estado_atual, boolean debug, int index) {

        if (subcadeia.length() == 0) {
            if (estado_atual.estado_final == true) {
//                System.out.println("PALAVRA ACEITA");
//                System.out.println(palavra + "\n\n");
                Main.palavras_aceitas[index] = true;
            }
        } else {
            for (Transicao transicao_atual : estado_atual.lista_transicoes) {
                Estado next = getEstado(transicao_atual.destino);
                if ("vazio".equals(transicao_atual.valor)) {
                    if (debug) {
                        System.out.println("Palavra: " + palavra);
                        System.out.println("Subcadeia atual: " + subcadeia);
                        System.out.println("(" + estado_atual.id + ") ----> (" + next.id + ")");
                        System.out.println("       " + transicao_atual.valor + "\n");
                    }
                    verifica_cadeia(palavra, subcadeia, next, debug, index);
                }
                String current_valor = Character.toString(subcadeia.charAt(0));
                if (current_valor.equals(transicao_atual.valor)) {
                    if (debug) {
                        System.out.println("Palavra: " + palavra);
                        System.out.println("Subcadeia atual: " + subcadeia);
                        System.out.println("(" + estado_atual.id + ") ----> (" + next.id + ")");
                        System.out.println("       " + transicao_atual.valor + "\n");
                    }
                    verifica_cadeia(palavra, subcadeia.substring(1), next, debug, index);
                }
            }
        }
        if (debug) {
            System.out.println("\nDesempilhando pilha de execução\n");
        }
    }

    public Estado getEstado(String estado) {
        String possible_final_estado = ("_" + estado);
        for (Estado e : automato) {
            if (estado.equals(e.id) || possible_final_estado.equals(e.id)) { // compara string estado com id de cada estado
                return e;
            }
        }
        System.out.println("Deu bosta! Estado não existente");
        return null;
    }
}
