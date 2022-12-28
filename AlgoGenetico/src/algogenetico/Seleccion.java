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
public class Seleccion {
    //public ArrayList<Integer> elegidos = new ArrayList<Integer>();
    //public ArrayList<ParteRuleta> ruleta = new ArrayList<ParteRuleta>();
    public Random r = new Random();

      public ArrayList<Integer> seleccion(ArrayList<Double> aptitudes, int k){

        ArrayList<Integer> seleccionados = new ArrayList<>();
        ArrayList<Integer> indices = new ArrayList<>();


        for(int i=0 ; i<aptitudes.size() ; i++){
            aptitudes.set(i, aptitudes.get(i) + 0.01f);
            indices.add(i);
        }

        int cont = 0;

        while(cont<k){
            Collections.shuffle(indices);
            for(int i=0 ; i<k ; i+=2){
                if( aptitudes.get(indices.get(i)) > aptitudes.get(indices.get(i+1)) )
                    seleccionados.add(indices.get(i));

                else if( aptitudes.get(indices.get(i)) < aptitudes.get(indices.get(i+1)) )
                    seleccionados.add(indices.get(i+1));

                else{
                    ArrayList<Integer> igual = new ArrayList<>();
                    igual.add(indices.get(i));
                    igual.add(indices.get(i+1));
                    Collections.shuffle(igual);
                    seleccionados.add(igual.get(0));
                }
                cont++;
                if(cont==k)
                    return seleccionados;
            }

        }
        return seleccionados;
    }
}
