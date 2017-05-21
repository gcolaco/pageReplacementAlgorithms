import java.util.ArrayList;
import java.util.Scanner;

public class Program {
    public static void main(String[] args){

        Scanner entrada = new Scanner(System.in); 
        ArrayList<Integer> lista = new ArrayList<Integer>(); 
        int numQdr = entrada.nextInt(); 
        int numQdr1 = 0;

        while(entrada.hasNextInt()){
            numQdr1 = entrada.nextInt();
            lista.add(numQdr1);
        }

        Fifo(lista, numQdr);
        Otm(lista, numQdr);
        Lru(lista, numQdr);
    }


    public static void Fifo(ArrayList<Integer> lista, int numQdr){
        
        int [] quadrosLivres = new int[numQdr]; 
        int faltaPag = 0;
        int i = 0;
        int auxFifo = 0;
        boolean isLackPag = true; 

        while(i < lista.size()){
            for(; i < numQdr; i++){
                quadrosLivres[i] = lista.get(i);
                faltaPag++;
            }
            
            for(int x = 0; x < numQdr; x++){
                if(quadrosLivres[x] == lista.get(i)){
                    isLackPag = false;
                    break;
                }else{
                    isLackPag = true;
                }
                
            }
            
            if(isLackPag){
                quadrosLivres[auxFifo] = lista.get(i);
                if(auxFifo == numQdr - 1){
                    auxFifo = 0;
                }else{
                    auxFifo++;
                }

                faltaPag++;
            }

        i++;
    }

        System.out.println("FIFO " + faltaPag);
    }


    public static void Otm(ArrayList<Integer> lista, int numQdr){
        int [] quadrosLivres = new int[numQdr]; 
        int faltaPag = 0;
        int i = 0;
        boolean isLackPag = true; 

        while(i < lista.size()){

            for(; i < numQdr; i++){
                quadrosLivres[i] = lista.get(i);
                faltaPag++;
            }
            
            for(int x = 0; x < numQdr; x++){
                if(quadrosLivres[x] == lista.get(i)){
                    isLackPag = false;
                    break;
                }else{
                    isLackPag = true;
                }
                
            }

            if(isLackPag){

                int j = i + 1;
                int pagDistante = 0; 
                int [] dist = new int[numQdr];
               
                ArrayList<Integer> elementosDaLista = new ArrayList<Integer>();
                
                while(j < lista.size()){
                    
                    for(int k = 0;k < numQdr; k++){
                        if(lista.get(j) == quadrosLivres[k] && !elementosDaLista.contains(lista.get(j))){
                            pagDistante = lista.get(j);
                            dist[k]++;
                            elementosDaLista.add(lista.get(j));
                        }
                    }
                    j++;
                }
                for(int z = 0; z < numQdr; z++){
                    if(dist[z] == 0)
                        pagDistante = quadrosLivres[z];
                }

                
                for(int trocaQuad = 0;trocaQuad < numQdr;trocaQuad++){
                    if(pagDistante == quadrosLivres[trocaQuad]){
                        quadrosLivres[trocaQuad] = lista.get(i);
                        faltaPag++;
                        break;
                    }

                }

            }

            i++;
        }

        System.out.println("OTM " + faltaPag);
    }

    public static void Lru(ArrayList<Integer> lista, int numQdr){
        int [] quadrosLivres = new int[numQdr]; 
        int faltaPag = 0;
        int i = 0;
        boolean isLackPag = true; 

        while(i < lista.size()){

            for(; i < numQdr; i++){
                quadrosLivres[i] = lista.get(i);
                faltaPag++;
            }
            
            for(int x = 0; x < numQdr; x++){
                if(quadrosLivres[x] == lista.get(i)){
                    isLackPag = false;
                    break;
                }else{
                    isLackPag = true;
                }
                
            }

            if(isLackPag){
                int j = i - 1;
                int pagDistante = 0;
                ArrayList<Integer> elementosDaLista = new ArrayList<Integer>();
                
                while(j >= 0){
                    
                    for(int k = 0; k < numQdr; k++){
                        if(lista.get(j) == quadrosLivres[k] && !elementosDaLista.contains(lista.get(j))){
                            pagDistante = lista.get(j);
                            elementosDaLista.add(lista.get(j));
                            break;
                        }
                    }
                    j--;
                }
                
                for(int trocaQuad = 0; trocaQuad < numQdr; trocaQuad++){
                    if(pagDistante == quadrosLivres[trocaQuad]){
                        quadrosLivres[trocaQuad] = lista.get(i);
                        faltaPag++;
                        break;
                    }
                }
            }

            i++;
        
        }

        System.out.println("LRU " + faltaPag);
    }
}
