/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PDA;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author giandoso
 */
public class Reader {

    private File f;
    private Scanner s;
    private List<String> string_transicoes = new ArrayList<>();
    private List<Estado> estados = new ArrayList<>();
    static List<String> palavras = new ArrayList<>();
    private String inicio_da_pilha;

    public Reader(String path) throws FileNotFoundException {
        this.f = new File(path);
        this.s = new Scanner(f);
        //leitura dos parametros iniciais
        int tamanho_alfabeto = this.s.nextInt();
        int quantidade_transicoes = this.s.nextInt();
        int estado_inicial = this.s.nextInt();
        int estado_final = this.s.nextInt();
        //leitura das transicoes
        this.s.nextLine();
        this.s.nextLine();
        for (int i = 0; i < quantidade_transicoes; i++) {
            string_transicoes.add(this.s.nextLine());
        }
        //leitura inicio da pilha
        this.s.nextLine();
        inicio_da_pilha = this.s.nextLine();
        //leitura palavras
        this.s.nextLine();
        while (s.hasNext()) {
            palavras.add(this.s.nextLine());
        }
        //parse transicoes
        List<Transicao> transicoes = new ArrayList<>();
        for (String string_transicao : this.string_transicoes) {
            String[] transicao_split = string_transicao.split(" ");

            Transicao curr = new Transicao(Integer.parseInt(transicao_split[0]), transicao_split[1], transicao_split[2], Integer.parseInt(transicao_split[3]), transicao_split[4]);
            transicoes.add(curr);
        }
        //parse maior_estado
        int maior_estado = 0;
        for (Transicao t : transicoes) {
            maior_estado = t.origem > maior_estado ? t.origem : maior_estado;
            maior_estado = t.destino > maior_estado ? t.destino : maior_estado;
        }
        //instancia estados
        for (int i = 0; i <= maior_estado; i++) {
            Estado e = new Estado(i, false, false);
            estados.add(e);
            if (i == estado_inicial) {
                e.set_inicial(true);
            }
            if (i == estado_final) {
                e.set_final(true);
            }
        }
        //atribui transicoes a estados
        for (int i = 0; i < transicoes.size(); i++) {
            Transicao curr = transicoes.get(i);
            Estado e = estados.get(curr.origem);
            e.lista_transicoes.add(curr);
            estados.set(curr.origem, e);
        }
    }
    
    public List<Estado> get_estados(){
        return this.estados;
    }
    
    public List<String> get_palavras(){
        return this.palavras;
    }
    
    public String get_inicio_da_pilha(){
        return this.inicio_da_pilha;
    }
}
