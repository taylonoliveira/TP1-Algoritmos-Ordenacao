import java.util.Arrays;

public class Testes {

    public static void main(String[] args) {
        int[][] casos = {
            {1, 2, 3, 4, 5, 6, 7, 8, 9, 10},
            {10, 9, 8, 7, 6, 5, 4, 3, 2, 1},
            {7, 2, 9, 1, 5, 3, 8, 4, 6, 0, 11},
            {5, 2, 5, 1, 2, 9, 9, 3, 5, 1, 0, 0}
        };

        String[] nomes = {
            "Bubble Sort",
            "Insertion Sort",
            "Selection Sort",
            "Shell Sort",
            "Heap Sort",
            "Merge Sort",
            "Quick Sort",
            "Counting Sort",
            "Radix Sort",
            "Bucket Sort"
        };

        boolean tudoCerto = true;

        for (String algoritmo : nomes) {
            for (int[] caso : casos) {
                int[] teste = caso.clone();
                ordenar(algoritmo, teste);

                if (!estaOrdenado(teste)) {
                    tudoCerto = false;
                    System.out.println("ERRO em " + algoritmo);
                    System.out.println("Resultado: " + Arrays.toString(teste));
                }
            }

            System.out.println(algoritmo + ": OK");
        }

        if (tudoCerto) {
            System.out.println("\nTodos os algoritmos passaram nos testes.");
        }
    }

    private static void ordenar(String algoritmo, int[] v) {
        switch (algoritmo) {
            case "Bubble Sort":
                Ordenadores.bubbleSort(v);
                break;
            case "Insertion Sort":
                Ordenadores.insertionSort(v);
                break;
            case "Selection Sort":
                Ordenadores.selectionSort(v);
                break;
            case "Shell Sort":
                Ordenadores.shellSort(v);
                break;
            case "Heap Sort":
                Ordenadores.heapSort(v);
                break;
            case "Merge Sort":
                Ordenadores.mergeSort(v);
                break;
            case "Quick Sort":
                Ordenadores.quickSort(v);
                break;
            case "Counting Sort":
                Ordenadores.countingSort(v);
                break;
            case "Radix Sort":
                Ordenadores.radixSort(v);
                break;
            case "Bucket Sort":
                Ordenadores.bucketSort(v);
                break;
        }
    }

    private static boolean estaOrdenado(int[] v) {
        for (int i = 1; i < v.length; i++) {
            if (v[i - 1] > v[i]) {
                return false;
            }
        }

        return true;
    }
}
