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
public class AlgoritmoGenetico {
    private float taxaCrossover;
    private float taxaMutacao;
    private int qntGeracoes;
    private int tamanhoPopulacao;
    private Populacao populacao;

    public AlgoritmoGenetico() {
        populacao = new Populacao();
    }

    public AlgoritmoGenetico(float taxaCrossover, float taxaMutacao, int qntGeracoes, int tamanhoPopulacao) {
        populacao = new Populacao();
        this.taxaCrossover = taxaCrossover;
        this.taxaMutacao = taxaMutacao;
        this.qntGeracoes = qntGeracoes;
        this.tamanhoPopulacao = tamanhoPopulacao;
    }

    public float getTaxaCrossover() {
        return taxaCrossover;
    }

    public void setTaxaCrossover(float taxaCrossover) {
        this.taxaCrossover = taxaCrossover;
    }

    public float getTaxaMutacao() {
        return taxaMutacao;
    }

    public void setTaxaMutacao(float taxaMutacao) {
        this.taxaMutacao = taxaMutacao;
    }

    public int getQntGeracoes() {
        return qntGeracoes;
    }

    public void setQntGeracoes(int qntGeracoes) {
        this.qntGeracoes = qntGeracoes;
    }

    public int getTamanhoPopulacao() {
        return tamanhoPopulacao;
    }

    public void setTamanhoPopulacao(int tamanhoPopulacao) {
        this.tamanhoPopulacao = tamanhoPopulacao;
    }

    public Populacao getPopulacao() {
        return populacao;
    }

    public void setPopulacao(Populacao populacao) {
        this.populacao = populacao;
    }
    
    public void executarAG(){
        for(int i=0; i<qntGeracoes; i++){
            populacao.gerar(tamanhoPopulacao);
            executarRoleta();

            Individuo pai = populacao.sorteio();
            Individuo mae = populacao.sorteio();

            executarCrossover(pai, mae);
            
            Individuo mult = populacao.sorteio();
            executarMutacao(mult);
            
            executarRoleta();
            removerExcesso();
        }
        
    }
    
    private void removerExcesso(){
        populacao.getIndividuos().remove(populacao.size()-1);
        populacao.getIndividuos().remove(populacao.size()-1);
    }
    
    private void executarRoleta(){
        populacao.calcularFitness();
        populacao.calcularFitnessPercent();
        populacao.calcularRangeRoleta();
    }
    
    private void executarCrossover(Individuo pai, Individuo mae){
        Random r = new Random();
        if(r.nextFloat()<taxaCrossover){
            int qntBits = Integer.toBinaryString(tamanhoPopulacao-1).length();
            int pc = r.nextInt(qntBits);
            
            String p1 = pai.getCromossomo().substring(0,pc);
            String p2 = pai.getCromossomo().substring(pc);
            
            String m1 = mae.getCromossomo().substring(0,pc);
            String m2 = mae.getCromossomo().substring(pc);

            Individuo filho1 = new Individuo(p1+m2);
            Individuo filho2 = new Individuo(m1+p2);
            
            populacao.getIndividuos().add(filho1);
            populacao.getIndividuos().add(filho2);

        }
        
    }
    
    private void executarMutacao(Individuo ind){
        if(new Random().nextFloat()<taxaMutacao){
            ind.mutarBit();
        }
    }
    
    @Override
    public String toString(){
        StringBuilder s = new StringBuilder();
        
        s.append("melhor individuo: ").append(populacao.getIndividuos().get(0)).append("\n\nmedia: ");
        s.append(populacao.getMediaPopulacao()).append("\n");
        
        return s.toString();
    }
}
