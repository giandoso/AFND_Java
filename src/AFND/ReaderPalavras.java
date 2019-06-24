/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AFND;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author giandoso
 */
public class ReaderPalavras {

    File f;
    Scanner s;

    static List<String> palavras = new ArrayList<>();

    public ReaderPalavras(String path) throws FileNotFoundException {
        this.f = new File(path);
        this.s = new Scanner(f);
        
        while(s.hasNext()){
            String line = this.s.nextLine();
            palavras.add(line);
        }
    }

}
