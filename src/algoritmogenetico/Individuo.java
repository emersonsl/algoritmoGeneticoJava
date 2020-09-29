/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algoritmogenetico;

import java.util.Random;

/**
 *
 * @author Emers
 */
public class Individuo implements Comparable<Object>{
    
    private String cromossomo;
    private float fitness;
    private float fitnessPercent;
    private float [] faixaRoleta;

    public Individuo(){
        faixaRoleta = new float[2];
    }
    
    public Individuo(String cromossomo){
        faixaRoleta = new float[2];
        this.cromossomo = cromossomo;
    }

    public Individuo(int valor, int qntBits){
        faixaRoleta = new float[2];
        this.cromossomo = Integer.toBinaryString(valor);
        ajusteCromossomo(qntBits);
    }
    
    public String getCromossomo() {
        return cromossomo;
    }

    public void setCromossomo(String cromossomo) {
        this.cromossomo = cromossomo;
    }

    public float getFitness() {
        return fitness;
    }

    public void setFitness(float fitness) {
        this.fitness = fitness;
    }

    public float getFitnessPercent() {
        return fitnessPercent;
    }

    public void setFitnessPercent(float fitnessPercent) {
        this.fitnessPercent = fitnessPercent;
    }

    public float[] getFaixaRoleta() {
        return faixaRoleta;
    }

    public void setFaixaRoleta(float[] faixaRoleta) {
        this.faixaRoleta = faixaRoleta;
    }
    
    public int getFenotipo(){
        return Integer.parseInt(cromossomo, 2);
    }
    public void mutarBit(){
        int i = new Random().nextInt(cromossomo.length());
        mutarBit(i);
    }
    
    public void mutarBit(int i){
        char [] v = cromossomo.toCharArray();
        v[i] = (v[i] == '0') ? '1' : '0';
        cromossomo = String.valueOf(v);
    }
    
    private void ajusteCromossomo(int qntBits){
        int diferenca = qntBits - cromossomo.length();
        StringBuilder zeros = new StringBuilder();
        for(int i=0; i<diferenca; i++)
            zeros.append("0");
        cromossomo = zeros.toString()+cromossomo;
    }
    
    @Override
    public String toString(){
        return "b"+cromossomo+"(d"+Integer.parseInt(cromossomo, 2)+")"+" apt.: "+fitness+" apt %.: "+fitnessPercent*100
                +" FR ["+faixaRoleta[0]+", "+faixaRoleta[1]+"]";
    }
    
    @Override
    public int compareTo(Object obj){
        if(obj instanceof Individuo){
            float f =((Individuo) obj).getFitness();
            if(fitness==f)
                return 0;
            return (fitness>f)? -1 : 1;
        }
        return 0;
    }
    
}
