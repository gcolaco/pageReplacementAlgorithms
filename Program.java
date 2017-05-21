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
        LRU(lista, aux);
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