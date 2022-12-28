package algogenetico;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author tania
 */

import java.util.*;
public class Individuo {
  public String cad;
  public String letras = "abcdefghijklmnñopqrstuvwxyzABCDEFGHIJKLMNÑOPQRSTUVWXYZ|°¡!#$%&/()=¿?\'¨´*+~[]{}^`;,:._- \"";


   int tam;

   public void setCadena(String cadena){
       this.cad = cadena;
   }

   public void setTam(int tam){
       this.tam = tam;
   }
   public int getTam(){
       return this.tam;
   }
   public String getCadena(){
       return this.cad;
   }

   public Individuo(int tam){
       String cadena = "";
       setTam(tam);
       Random r = new Random();
       int aux;
       for(int i=0; i<tam; i++){
           aux = r.nextInt(letras.length());
           cadena+= letras.charAt(aux);
       }
       setCadena(cadena);
   }
   public Individuo(String padre, String madre, int tam){
       setTam(tam);
       cruza(padre, madre);
   }

   public String toString(){
       return this.cad;
   }

   public void cruza(String padre, String madre){
       int mit = tam/2;


       String combina = padre.substring(0,mit) + madre.substring(mit,getTam());
      // String subMad = madre.substring(mit, getTam());
       //String cadena = subPad + subMad;
       this.setCadena(combina);
   }

  public void mutar(){
    Random r = new Random();
   // System.out.println("ANTES");
    //System.out.println(getCadena());
    int ilet = r.nextInt(getTam());
    char[] separa = getCadena().toCharArray();
    int iletLe = r.nextInt(letras.length());
    separa[ilet] = letras.charAt(iletLe);
    setCadena(String.valueOf(separa));

  }

}
