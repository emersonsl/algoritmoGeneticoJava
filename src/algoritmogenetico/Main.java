/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algoritmogenetico;

import java.util.Collections;
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
        /*Populacao p = new Populacao();
        
        p.gerar(100);
        p.calcularFitness();
        p.calcularFitnessPercent();
        p.calcularRangeRoleta();
        System.out.println("População:\n"+p+"\ntamanho: "+p.getIndividuos().size());
        
        System.out.println("Sorteio:\n"+p.sorteio()+"\n"+p.sorteio()+"\n"+p.sorteio());
        */
        
        AlgoritmoGenetico ag;
        
        ag = new AlgoritmoGenetico(1, 1, 100, 100);
        System.out.println("TC = 1 e TM = 1" );
        
        ag.executarAG();
        System.out.println(ag);
        
        ag = new AlgoritmoGenetico(1, 0, 100, 100);
        
        System.out.println("TC = 1 e TM = 0" );
        
        ag.executarAG();
        System.out.println(ag);
        
        
        ag = new AlgoritmoGenetico(0, 1, 100, 100);
        
        System.out.println("TC = 0 e TM = 1" );
        
        ag.executarAG();
        System.out.println(ag);
    }
    
}
