/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package algogenetico;

/**
 *
 * @author tania
 */
public class ParteRuleta {
    int indR;
    double porc;
    double porcSuma;
   
    public void setIndReal(int indR) {
        this.indR = indR;
    }
    public void setPorcentaje(double por) {
        this.porc = por;
    }
    public void setPorcentajeSuma(double porS) {
        this.porcSuma = porS;
    }
   
    public int getIndiceReal(){
        return this.indR;
    }
   
    public double getPorcentaje(){
        return this.porc;
    }
   
    public double getSumaPor(){
        return this.porcSuma;
    }
    
    public ParteRuleta(int indR, double porc, double porcSum){
        setIndReal(indR);
        setPorcentaje(porc);
        setPorcentajeSuma(porcSum);
    }
    
    public String toString(){
        String texto = "";
        texto+= "indR: "+getIndiceReal()+ " porc: "+getPorcentaje()+" sumP: "+getSumaPor();
        return texto;
    }
}
