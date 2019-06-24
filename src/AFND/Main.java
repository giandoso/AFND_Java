/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AFND;

import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author giandoso
 */
public class Main {

    static boolean[] palavras_aceitas;

    public static void main(String[] args) throws FileNotFoundException {
        Automato a = new Automato();
        ReaderPalavras r = new ReaderPalavras("src/automato/palavras2.input"); //  <---- DEFINIR PATH AQUI HARD CODED
        List<String> palavras = ReaderPalavras.palavras;
        palavras_aceitas = new boolean[palavras.size()];

        for (int i = 0; i < palavras.size(); i++) {
            String palavra = palavras.get(i);
            a.verifica_cadeia(palavra, palavra, a.getEstado("q0"), true, i); // <---- DEFINIR ESTADO INICIAL AQUI HARD CODED
        }
        
        
        for (int i = 0; i < palavras.size(); i++) {
            System.out.print("Palavra: "+ palavras.get(i) +"\t\t\t| ");
            System.out.println(palavras_aceitas[i] == true ? "ACEITA" : "NÃO ACEITA");            
        }
        
        
    }
    
}
