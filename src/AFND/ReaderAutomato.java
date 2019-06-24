/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package automato;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author giandoso
 */
public class ReaderAutomato {

    File f;
    Scanner s;
    static List<String> estados = new ArrayList<>();
    List<String> transicoes = new ArrayList<>();
    static List<List<Transicao>> transicoes_parsed = new ArrayList<>();

    public ReaderAutomato(String path) throws FileNotFoundException {
        this.f = new File(path);
        this.s = new Scanner(f);

        while (s.hasNext()) {
            String line = this.s.nextLine();
            String[] line_pieces = line.split(":");
            estados.add(line_pieces[0]);
            transicoes.add(line_pieces[1]);
        }
        parse_transicao();

    }

    public int range() {
        return estados.size();
    }

//    public List<String> getEstados() {
//        return estados;
//    }
//
//    public List<List<Transicao>> getTransicoes_parsed() {
//        return transicoes_parsed;
//    }
    public void getData() {
        for (int i = 0; i < this.range(); i++) {
            System.out.println("Estado: " + estados.get(i));
            System.out.println("Transições: " + transicoes.get(i));
            System.out.println("");
        }
    }

    public void parse_transicao() {
        for (String string_transicoes : this.transicoes) {
            List<Transicao> curr = new ArrayList<>();
            String[] string_transicoes_split = string_transicoes.split(";");

            for (int i = 0; i < string_transicoes_split.length; i++) {
                String transicao_sanitized = string_transicoes_split[i].replaceAll("[()]", "");

                if (!" ".equals(transicao_sanitized)) {
                    String[] transicao_split = transicao_sanitized.split(",");
                    if (transicao_split.length == 1) {
                        Transicao transicao = new Transicao(transicao_split[0], "vazio");
                        curr.add(transicao);
                    } else {
                        Transicao transicao = new Transicao(transicao_split[0], transicao_split[1]);
                        curr.add(transicao);
                    }
                }
            }
            transicoes_parsed.add(curr);
        }
    }
}
