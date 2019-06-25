/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PDA;

import java.io.FileNotFoundException;

/**
 *
 * @author giandoso
 */
public class Main {

    static boolean[] palavras_aceitas;

    public static void main(String[] args) throws FileNotFoundException {
        Reader r = new Reader("src/PDA/automato3.input");
        // Automato 1 = (a^n)(b^n) | n>0
        // Automato 2 = Quantidade igual de a e b
        // Automato 3 = Palindromo
        // Automato 4 = (b^n)(a^n) | n>0
        Automato a = new Automato();
        a.set_estados(r.get_estados());

        palavras_aceitas = new boolean[Reader.palavras.size()];

        Estado estado_inicial = null;
        for (Estado e : a.estados) {
            if (e.estado_inicial == true) {
                estado_inicial = e;
            }
        }
        for (int i = 0; i < Reader.palavras.size(); i++) {
            String palavra = Reader.palavras.get(i);
            a.verifica_cadeia(palavra, palavra, estado_inicial, r.get_inicio_da_pilha(), r.get_inicio_da_pilha(), true, i);
        }
        for (int i = 0; i < Reader.palavras.size(); i++) {
            System.out.print("Palavra: " + Reader.palavras.get(i) + "\t\t\t| ");
            System.out.println(palavras_aceitas[i] == true ? "ACEITA" : "NÃO ACEITA");
        }

        System.out.println("");
    }
}
