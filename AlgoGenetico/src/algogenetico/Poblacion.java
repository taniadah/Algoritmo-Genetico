/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package algogenetico;
import java.util.*;
/**
 *
 * @author tania
 */
public class Poblacion {
  ArrayList<Individuo> individuos;
  int tamPob, tamInd;

  public void setTamPob(int tam){
    this.tamPob = tam;
  }

  public int getTamPob(){
    return this.tamPob;
  }

  public void setTamInd(int tam){
    this.tamInd = tam;
  }

  public int getTamInd(){
    return this.tamInd;
  }

  public Poblacion(int tamPob, int tamInd){
    setTamPob(tamPob);
    setTamInd(tamInd);
    generaPoblacion();
  }

  public void generaPoblacion(){
    individuos = new ArrayList<Individuo>();
    for(int i = 0; i< getTamPob(); i++){
      individuos.add(new Individuo(getTamInd()));
    }
  }

  public String toString(int tam){
    String texto="";
    int cont = 0;
    Individuo ind;
    for(int i=0; i<tam; i++){
        ind = individuos.get(i);
      texto+=""+cont+ ") "+ind.toString() +"\n";
      cont+=1;
    }
    return texto;
  }
}
