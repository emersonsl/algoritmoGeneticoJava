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
public class Populacao {
    
    private List <Individuo> individuos;
    
    public Populacao(){
        individuos = new ArrayList<>();
    }
    
    public Populacao(List <Individuo> individuos){
        this.individuos = individuos;
    }

    public List<Individuo> getIndividuos() {
        return individuos;
    }

    public void setIndividuos(List<Individuo> individuos) {
        this.individuos = individuos;
    }
    
    public void calcularFitness(){
        int x;
        float y;
        
        for(Individuo ind: individuos){
            x = ind.getFenotipo();
            y = (float) (100 + Math.abs(x*Math.sin(Math.sqrt(Math.abs(x))))); //função de ativação
            ind.setFitness(y);
        }
    }
    
    public Individuo popRandomIndividuo(){
        float f = new Random().nextFloat();
        for(Individuo ind: individuos){
            if(f>=ind.getFaixaRoleta()[0] && f<=ind.getFaixaRoleta()[1]){
                individuos.remove(ind); //remove o individuo sorteado da população
                return ind; //retorna o individuo sorteado
            }
        }
        return individuos.get(size()-1);
    }
    
    public void calcularFitnessPercent(){
        float soma=0;
        for(Individuo ind: individuos){ //somando fitness
            soma += ind.getFitness();
        }
        for(Individuo ind: individuos){ //calculando o fitness relativo
            ind.setFitnessPercent((ind.getFitness()/soma));
        }
    }
    
    public void calcularRangeRoleta(){
        Collections.sort(individuos); //ordenando a lista
        float ant = 0, atual;
        for(Individuo ind: individuos){ //setando posição na roleta
            atual = ind.getFitnessPercent()+ant;
            ind.setFaixaRoleta(new float[]{ant,atual});
            ant = atual+Float.MIN_VALUE;
        }
    }
    
    public float getMediaPopulacao(){
        float soma=0;
        for(Individuo ind: individuos){ //somando todos os fitness
            soma += ind.getFitness();
        }
        return soma/size();
    }
    
    public void gerar(int tamanho){
        int qntBits = Integer.toBinaryString(tamanho-1).length();
        for(int i=0; i<tamanho; i++){ //gerando população a partir de valores aleatórios dentro do intervalo do tamanho da mesma
            individuos.add(new Individuo(new Random().nextInt(tamanho), qntBits));
        }
    }
    
    public int size(){ //retorna o tamanho da população
        return individuos.size();
    }
    
    @Override
    public String toString(){
        StringBuilder s = new StringBuilder();
        
        individuos.forEach((ind) -> {
            s.append(ind.toString()).append("\n");
        });
        return s.toString().substring(0, s.length()-1);
    }
    
    @Override
    public Populacao clone() { //realiza uma cópia da população
        List <Individuo> copia = new ArrayList<>();
        copia.addAll(individuos);
        return new Populacao(copia);
    }
    
}
