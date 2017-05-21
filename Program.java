import java.util.ArrayList;
import java.util.Scanner;

public class Program {
    public static void main(String[] args){

        Scanner entrada = new Scanner(System.in); // A leitura do arquivo é feita através da classe Scanner por desvio de fluxo
        ArrayList<Integer> lista = new ArrayList<Integer>(); //lista de páginas
        int aux = entrada.nextInt(); // Número de quadros
        int aux1 = 0;

        while(entrada.hasNextInt()){
            aux1 = entrada.nextInt();
            lista.add(aux1);
        }

        FIFO(lista, aux);
        OTM(lista, aux);
        //LRU(lista, aux);
    }


    public static void FIFO(ArrayList<Integer> lista, int aux){
        
        int [] quadros = new int[aux]; //Array com a quantidade de quadros livres
        int faltaPag = 0;
        int i = 0;
        int primeiroAEntrar = 0;
        boolean cont = true; // Variavel utilizada para verificar se há falta de páginas

        /*Executa até percorrer toda a lista de páginas */
        while(i < lista.size()){

            /*Insere as primeiras faltas de páginas nos quadros*/
            while(i < aux){
                quadros[i] = lista.get(i);
                i++;
                faltaPag++;
            }

            /*Verifica a falta de página para habilitar a troca nos quadros caso haja falta de página*/
                int x = 0;
                while(x < aux){
                    if(quadros[x] == lista.get(i)){
                        cont = false;
                        break;
                    }else{
                        cont = true;
                    }
                    x++;
                }

                /*Realiza a troca nos quadros*/
                if(cont){
                    quadros[primeiroAEntrar] = lista.get(i);

                    if(primeiroAEntrar == aux - 1)
                        primeiroAEntrar = 0;
                    else
                        primeiroAEntrar++;

                    faltaPag++;
                }

            i++;
        }

        System.out.println("FIFO " + faltaPag);
    }


    public static void OTM(ArrayList<Integer> lista, int aux){
        int [] quadros = new int[aux]; //Array com a quantidade de quadros livres
        int faltaPag = 0;
        int i = 0;
        boolean cont = true; // Variavel utilizada para verificar se há falta de páginas

        /*Executa até percorrer toda a lista de páginas */
        while(i < lista.size()){

            /*Insere as primeiras faltas de páginas nos quadros*/
            while(i < aux){
                quadros[i] = lista.get(i);
                i++;
                faltaPag++;
            }

            /*Verifica a falta de página para habilitar a troca nos quadros caso haja falta de página*/
            int x = 0;
            while(x < aux){
                if(quadros[x] == lista.get(i)){
                   cont = false;
                   break;
                }else{
                    cont = true;
                }
                x++;
            }

            if(cont){

                int j = i + 1;
                int maisDist = 0; // Variável que contém a página mais distante, ou seja, a que será substituída
                int [] dist = new int[aux];
                /*Verifica qual o elemento mais distante entre os elementos da lista que está em um dos quadros */
                ArrayList<Integer> elementosDaLista = new ArrayList<Integer>();
                while(j < lista.size()){
                    int jj = 0;
                    while(jj < aux){
                        if(lista.get(j) == quadros[jj] && !elementosDaLista.contains(lista.get(j))){
                            maisDist = lista.get(j);
                            dist[jj]++;
                            elementosDaLista.add(lista.get(j));
                            //break;
                        }
                        jj++;
                    }
                    j++;
                }

                /* Verifica se a variável maisDist realmente contém a página mais distante*/
                for(int z = 0; z < aux; z++){
                    if(dist[z] == 0)
                        maisDist = quadros[z];
                }

                /*Realiza a troca nos quadros*/
                int troca = 0;
                while(troca < aux){
                    if(maisDist == quadros[troca]){
                        quadros[troca] = lista.get(i);
                        faltaPag++;
                        break;
                    }
                    troca++;
                }

            }

            i++;
        }

        System.out.println("OTM " + faltaPag);
    }




}
