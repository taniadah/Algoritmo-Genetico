/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package algogenetico;
import java.util.*;
import javax.swing.JFrame;
/**
 *
 * @author tania
 */
public class AlgoritmoGenetico {
  private Poblacion pob, siguientePob;
  private int tamPob, tamInd, numGen, itMost, itMostI, itActual;
  private String cadEncontrar, primPob, ultiPob, textoSel;
  private ArrayList<Double> aptitudes, ruleta;
  private ArrayList<Individuo> padre, madre, cruzas;
  private ArrayList<Double> probabilidades;
  private ArrayList<Integer> indices;
  public Seleccion seleccion = new Seleccion();
  String elMejor;
  public Poblaciones poblaciones;

  public void setTamPob(int tam){
    this.tamPob = tam;
  }
  public int getTamPob(){
    return this.tamPob;
  }
   public void setNumG(int ng){
    this.numGen = ng;
  }
  public int getNumG(){
    return this.numGen;
  }
  public void setTamInd(int tam){
    this.tamInd = tam;
  }
  public int getTamInd(){
    return this.tamInd;
  }
  public void setCadenaEncon(String cadena){
    this.cadEncontrar = cadena;
  }
  public String getCadenaEncon(){
    return this.cadEncontrar;
  }
  public AlgoritmoGenetico(int tamP, String cad, int nGen){
    setTamPob(tamP);
    setTamInd(cad.length());
    setCadenaEncon(cad);
    setNumG(nGen);
  }

  public void inicializa(){
    defineLim();
    pob = new Poblacion(getTamPob(), getTamInd());
    primPob = pob.toString(getTamPob());
    
    
    for(int i = 0; i<getNumG(); i++){
      itActual = i;
      evolucion();
      //Interfaz.lblGenActual.setText(""+i);
      //
      //pob.individuos = cruzas;
      try{
        Thread.sleep(500);
       // System.out.println("Esperando");
      }catch(InterruptedException e ) {
        System.out.println("Thread Interrupted");
      }
    }
    ultiPob = pob.toString(getTamPob());
    Interfaz.ultPob = ultiPob;
    Interfaz.primPob = primPob;
    Interfaz.btnMostEv.setEnabled(true);
    poblaciones = new Poblaciones(new JFrame(), true, primPob, ultiPob);
    poblaciones.setVisible(true);
    
  }
  
  public void defineLim(){
   // if(getNumG()>=5000 && getNumG()<=10000){
     //   itMost = 100;
    //}else if(getNumG()>10000 && getNumG()<=1000000){
      //  itMost = 1000;
    //}else
    itMost = 1;
    
    
    if(getTamPob()>=5000){
        itMostI = 1000;    
        System.out.println("Si");
    }else
        itMostI = getTamPob();
  }

  public void FuncionAptitud(){
    aptitudes = new ArrayList<Double>();
    String indAux;
    int cont = 0;
    for(Individuo ind: pob.individuos){
      indAux = ind.toString();
      for(int i=0; i < getTamInd(); i++){
        if(cadEncontrar.charAt(i) == indAux.charAt(i)){
          cont+=1;
        }
      }
      aptitudes.add((double) cont);
      cont = 0;
    }
    //System.out.println("FITNESS FUNCTION");
    //System.out.println("Poblacion SIZE: "+ pob.individuos.size());
    //System.out.println("Aptitudes SIZE: "+ aptitudes.size());
  }

  public void seleccionar(){
    //System.out.println("SELECCIONAR ");
    //System.out.println("Poblacion SIZE: "+ pob.individuos.size());
    //System.out.println("Aptitudes SIZE: "+ aptitudes.size());
    cruzas = new ArrayList<Individuo>();
    int mit = getTamPob()/2;
    indices = seleccion.seleccion(aptitudes, mit);

  }

  public void cruzar(){
    String texto = "";
    ArrayList<Individuo> padres = new ArrayList<>();
    int cont = 0;

    // System.out.println("Indices: ");
    // for(int i: indices){
    //     System.out.println(i);
    // }
    //
    // System.out.println("Termina");
    //
    // for(int i = 0; i<indices.size(); i++){
    //
    //   padres.add(pob.individuos.get(indices.get(i)));
    // }

    for(int i = 0; i<indices.size()-1; i+=2){
      int idxPapa = indices.get(i);
      int idxMama = indices.get(i+1);
      //System.out.println("indice "+i);
      //System.out.println("idxM "+idxPapa);
      //System.out.println("idxP "+idxMama);
      

      Individuo papa = pob.individuos.get(idxMama);
      Individuo mama = pob.individuos.get(idxPapa);

      cruzas.add(new Individuo(papa.toString(), mama.toString(), getTamInd()));
      cruzas.add(new Individuo(mama.toString(), papa.toString(), getTamInd()));
      
      if(i<=(itMostI/2)){
        texto += cont+") "+papa.toString() +" + "+mama.toString()+"\n";
      }
      cont+=1;
    }



    // for(int i = 0; i<indices.size(); i+=2){
    //     padre = pob.individuos.get(indices.get(i)).toString();
    //     madre = pob.individuos.get(indices.get(i+1)).toString();
    //     cruzas.add(new Individuo(padre, madre, getTamInd()));
    //     cruzas.add(new Individuo(madre, padre, getTamInd()));
    //     texto += cont+") "+ padre +" + "+madre+"\n";
    //     cont+=1;
    //     texto += cont+") "+madre +" + "+padre+"\n";
    //     cont+=1;
    // }
    textoSel = texto;

    
  }

  public void mutar(){
    int totalMutar = (int)(Math.ceil(cruzas.size()*0.1));

    //System.out.println("Mutar:"+totalMutar);
    int idx;
    Random r = new Random();
    for(int i = 0; i<totalMutar; i++){
      idx = r.nextInt(cruzas.size());
      cruzas.get(idx).mutar();
    }
  }

  public void evolucion(){
    FuncionAptitud();
    seleccionar();
    cruzar();
    mutar();
    if(itActual%itMost==0){
        plasmarInterfaz(itActual);
    }
    
    pob = this.pob;

    for(int i = 0; i<cruzas.size(); i++){
      pob.individuos.add(cruzas.get(i));
    }
  // System.out.println("SIZE: "+ pob.individuos.size());
    FuncionAptitud();

    ArrayList<Individuo> siguientePob = new ArrayList<Individuo>();

    double maximaAp = Collections.max(aptitudes);
    int idxMejor = aptitudes.indexOf(maximaAp);
    siguientePob.add(pob.individuos.get(idxMejor));
    elMejor = pob.individuos.get(idxMejor).toString();

    indices = seleccion.seleccion(aptitudes, getTamPob()-1);
    //System.out.println("SIZE INDICES: "+ indices.size());
    for(int ind: indices){
      //System.out.println("IND "+ind);
      //System.out.println("SIZE: "+ pob.individuos.size());
      //System.out.println("SIZE2: "+indices.size());
      siguientePob.add(pob.individuos.get(ind));
    }

    pob.individuos = siguientePob;
    //System.out.println("SIZE: "+ pob.individuos.size());

  }

  public void plasmarInterfaz(int it){
    //
    Interfaz.jtaPoblacion.setText(pob.toString(itMostI));
    Interfaz.jtaAptitud.setText(alToString());
    Interfaz.lblGenActual.setText(""+it);
    String textoCruzas ="";
    int cont=0;
    Individuo ind;
      
    if(itActual%itMost ==0){
        Interfaz.jtaSeleccion.setText(textoSel);
    }
      
    for(int i = 0; i<itMostI/2; i++){
        ind = cruzas.get(i);
        textoCruzas += cont+") "+ind.toString()+"\n";
        cont+=1;
    }
    Interfaz.jtaCruza.setText(textoCruzas);
    Interfaz.lblElBest.setText(elMejor);      
  }

  public String alToString(){
    String texto = "";
    for(int i = 0; i<itMostI; i++){
      texto +=""+i+") " + Double.toString(aptitudes.get(i))+"\n";
    }
    return texto;
  }
}
