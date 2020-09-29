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
public class AlgoritmoGenetico {
    private float taxaCrossover;
    private float taxaMutacao;
    private int qntGeracoes;
    private List <Populacao> geracoes;
    private int tamanhoPopulacao;
    private Populacao populacaoAtual;

    public AlgoritmoGenetico() {
        populacaoAtual = new Populacao();
        geracoes = new ArrayList<>();
    }

    public AlgoritmoGenetico(float taxaCrossover, float taxaMutacao, int qntGeracoes, int tamanhoPopulacao) {
        populacaoAtual = new Populacao();
        geracoes = new ArrayList<>();
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
        return populacaoAtual;
    }

    public void setPopulacao(Populacao populacao) {
        this.populacaoAtual = populacao;
    }
    
    
    
    public void executarAG(){ //executa o algoritmo genético
        populacaoAtual.gerar(tamanhoPopulacao); //gera população com valores aleatórios
        
        for(int i=0; i<qntGeracoes-1; i++){ //itera a quantidade de gerações definida
            executarRoleta(); //avalia a população
            
            geracoes.add(populacaoAtual.clone()); //adiciona uma cópia da população atual a lista de gerações
            Populacao populacaoNova = new Populacao();
            
            for(int k=0; k<(tamanhoPopulacao/2); k++){ //realizando o cruzamento ou não
                Individuo pai = populacaoAtual.popRandomIndividuo();
                Individuo mae = populacaoAtual.popRandomIndividuo();
                executarCrossover(pai, mae, populacaoNova);
            }
            
            populacaoAtual=populacaoNova; //população é atualizada
            executarMutacao(); //é feita a mutação de alguns individuos selecionados aleatóriamente
            executarRoleta(); //avalia a nova população
        }
        geracoes.add(populacaoAtual.clone()); //adiciona a ultima população na lista de gerações
    }
        
    private void executarRoleta(){ //calcula os parametros de uma população baseado-se na função de ativação
        populacaoAtual.calcularFitness();
        populacaoAtual.calcularFitnessPercent();
        populacaoAtual.calcularRangeRoleta();
    }
    
    private void executarCrossover(Individuo pai, Individuo mae, Populacao populacaoNova){ //executa o cruzamento ou não de individuos
        Random r = new Random();
        if(r.nextFloat()<taxaCrossover){ //se o número sorteado for menor que a taxa de cruzamento
            int qntBits = Integer.toBinaryString(tamanhoPopulacao-1).length(); 
            int pc = r.nextInt(qntBits); //sorteio do ponto de corte
            
            String p1 = pai.getCromossomo().substring(0,pc); 
            String p2 = pai.getCromossomo().substring(pc);
            
            String m1 = mae.getCromossomo().substring(0,pc);
            String m2 = mae.getCromossomo().substring(pc);

            Individuo filho1 = new Individuo(p1+m2);
            Individuo filho2 = new Individuo(m1+p2);
            
            populacaoNova.getIndividuos().add(filho1); //adicionando filhos a nova população
            populacaoNova.getIndividuos().add(filho2);

        }else{ //caso não seja realizado o cruzamento, os pais são adicionados a nova população
            populacaoNova.getIndividuos().add(pai); 
            populacaoNova.getIndividuos().add(mae);
        }
        
    }
    
    private void executarMutacao(){ //itera a lista realizando a mutação os individuos caso o sorteio favoreça 
        populacaoAtual.getIndividuos().stream().filter((ind) -> (new Random().nextFloat()<taxaMutacao)).forEachOrdered((Individuo ind) -> {
            ind.mutarBit();
        });
    }
    
    @Override
    public String toString(){
        StringBuilder s = new StringBuilder();
        
        s.append("melhor individuo: ").append(populacaoAtual.getIndividuos().get(0)).append("\n\nmedia: ");
        s.append(populacaoAtual.getMediaPopulacao()).append("\n");
        
        return s.toString();
    }

    public List <Populacao> getGeracoes() {
        return geracoes;
    }
}
