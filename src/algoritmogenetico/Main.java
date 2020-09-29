/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algoritmogenetico;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 *
 * @author Emers
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        AlgoritmoGenetico ag;
        
        ag = new AlgoritmoGenetico(1, 1, 100, 100); //teste 1: taxa de mutação e de cruzamento iguais a 1
        System.out.println("TC = 1 e TM = 1" );
        
        ag.executarAG();
        
        System.out.println(ag);
        
        ag = new AlgoritmoGenetico(1, 0, 100, 100); //teste 2: taxa de mutação igual a 1 e de cruzamento igual a 0
        
        System.out.println("TC = 1 e TM = 0" );
        
        ag.executarAG();
        System.out.println(ag);
        
        
        ag = new AlgoritmoGenetico(0, 1, 100, 100); //teste 3: taxa de mutação igual a 0 e de cruzamento igual a 1
        
        System.out.println("TC = 0 e TM = 1" );
        
        ag.executarAG();
        System.out.println(ag);
    }
    
}
