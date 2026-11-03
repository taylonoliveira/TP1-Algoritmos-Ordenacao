public class Ordenadores {

    // Troca dois elementos do vetor
    private static void trocar(int[] v, int i, int j) {
        int temp = v[i];
        v[i] = v[j];
        v[j] = temp;
    }

    // 1. Bubble Sort
    public static void bubbleSort(int[] v) {
        for (int i = 0; i < v.length - 1; i++) {
            boolean trocou = false;

            for (int j = 0; j < v.length - 1 - i; j++) {
                if (v[j] > v[j + 1]) {
                    trocar(v, j, j + 1);
                    trocou = true;
                }
            }

            if (!trocou) {
                break;
            }
        }
    }

    // 2. Insertion Sort
    public static void insertionSort(int[] v) {
        for (int i = 1; i < v.length; i++) {
            int valor = v[i];
            int j = i - 1;

            while (j >= 0 && v[j] > valor) {
                v[j + 1] = v[j];
                j--;
            }

            v[j + 1] = valor;
        }
    }

    // 3. Selection Sort
    public static void selectionSort(int[] v) {
        for (int i = 0; i < v.length - 1; i++) {
            int menor = i;

            for (int j = i + 1; j < v.length; j++) {
                if (v[j] < v[menor]) {
                    menor = j;
                }
            }

            trocar(v, i, menor);
        }
    }

    // 4. Shell Sort
    public static void shellSort(int[] v) {
        for (int gap = v.length / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < v.length; i++) {
                int valor = v[i];
                int j = i;

                while (j >= gap && v[j - gap] > valor) {
                    v[j] = v[j - gap];
                    j -= gap;
                }

                v[j] = valor;
            }
        }
    }

    // 5. Heap Sort
    public static void heapSort(int[] v) {
        int n = v.length;

        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(v, n, i);
        }

        for (int i = n - 1; i > 0; i--) {
            trocar(v, 0, i);
            heapify(v, i, 0);
        }
    }

    private static void heapify(int[] v, int n, int i) {
        int maior = i;
        int esquerda = 2 * i + 1;
        int direita = 2 * i + 2;

        if (esquerda < n && v[esquerda] > v[maior]) {
            maior = esquerda;
        }

        if (direita < n && v[direita] > v[maior]) {
            maior = direita;
        }

        if (maior != i) {
            trocar(v, i, maior);
            heapify(v, n, maior);
        }
    }

    // 6. Merge Sort
    public static void mergeSort(int[] v) {
        if (v.length <= 1) {
            return;
        }

        int[] auxiliar = new int[v.length];
        mergeSort(v, auxiliar, 0, v.length - 1);
    }

    private static void mergeSort(int[] v, int[] auxiliar, int inicio, int fim) {
        if (inicio >= fim) {
            return;
        }

        int meio = (inicio + fim) / 2;

        mergeSort(v, auxiliar, inicio, meio);
        mergeSort(v, auxiliar, meio + 1, fim);
        merge(v, auxiliar, inicio, meio, fim);
    }

    private static void merge(int[] v, int[] auxiliar, int inicio, int meio, int fim) {
        int i = inicio;
        int j = meio + 1;
        int k = inicio;

        while (i <= meio && j <= fim) {
            if (v[i] <= v[j]) {
                auxiliar[k++] = v[i++];
            } else {
                auxiliar[k++] = v[j++];
            }
        }

        while (i <= meio) {
            auxiliar[k++] = v[i++];
        }

        while (j <= fim) {
            auxiliar[k++] = v[j++];
        }

        for (int p = inicio; p <= fim; p++) {
            v[p] = auxiliar[p];
        }
    }

    // 7. Quick Sort
    // Usa o elemento do meio como pivô para evitar um comportamento
    // muito ruim em vetores que já estão ordenados.
    public static void quickSort(int[] v) {
        quickSort(v, 0, v.length - 1);
    }

    private static void quickSort(int[] v, int inicio, int fim) {
        int i = inicio;
        int j = fim;
        int pivo = v[(inicio + fim) / 2];

        while (i <= j) {
            while (v[i] < pivo) {
                i++;
            }

            while (v[j] > pivo) {
                j--;
            }

            if (i <= j) {
                trocar(v, i, j);
                i++;
                j--;
            }
        }

        if (inicio < j) {
            quickSort(v, inicio, j);
        }

        if (i < fim) {
            quickSort(v, i, fim);
        }
    }

    // 8. Counting Sort
    // Esta versão funciona com inteiros não negativos.
    public static void countingSort(int[] v) {
        if (v.length == 0) {
            return;
        }

        int maior = v[0];

        for (int valor : v) {
            if (valor < 0) {
                throw new IllegalArgumentException(
                    "Counting Sort desta implementação aceita apenas valores não negativos."
                );
            }

            if (valor > maior) {
                maior = valor;
            }
        }

        int[] contagem = new int[maior + 1];

        for (int valor : v) {
            contagem[valor]++;
        }

        int indice = 0;

        for (int valor = 0; valor < contagem.length; valor++) {
            while (contagem[valor] > 0) {
                v[indice++] = valor;
                contagem[valor]--;
            }
        }
    }

    // 9. Radix Sort
    // Esta versão funciona com inteiros não negativos.
    public static void radixSort(int[] v) {
        if (v.length == 0) {
            return;
        }

        int maior = v[0];

        for (int valor : v) {
            if (valor < 0) {
                throw new IllegalArgumentException(
                    "Radix Sort desta implementação aceita apenas valores não negativos."
                );
            }

            if (valor > maior) {
                maior = valor;
            }
        }

        for (int exp = 1; maior / exp > 0; exp *= 10) {
            countingPorDigito(v, exp);

            // Evita overflow quando exp crescer demais
            if (exp > Integer.MAX_VALUE / 10) {
                break;
            }
        }
    }

    private static void countingPorDigito(int[] v, int exp) {
        int[] saida = new int[v.length];
        int[] contagem = new int[10];

        for (int valor : v) {
            int digito = (valor / exp) % 10;
            contagem[digito]++;
        }

        for (int i = 1; i < 10; i++) {
            contagem[i] += contagem[i - 1];
        }

        for (int i = v.length - 1; i >= 0; i--) {
            int digito = (v[i] / exp) % 10;
            saida[contagem[digito] - 1] = v[i];
            contagem[digito]--;
        }

        System.arraycopy(saida, 0, v, 0, v.length);
    }

    // 10. Bucket Sort
    // Versão simples para inteiros não negativos.
    public static void bucketSort(int[] v) {
        if (v.length <= 1) {
            return;
        }

        int maior = v[0];

        for (int valor : v) {
            if (valor < 0) {
                throw new IllegalArgumentException(
                    "Bucket Sort desta implementação aceita apenas valores não negativos."
                );
            }

            if (valor > maior) {
                maior = valor;
            }
        }

        int quantidadeBuckets = Math.min(10_000, v.length);
        if (quantidadeBuckets < 1) {
            quantidadeBuckets = 1;
        }

        int[][] buckets = new int[quantidadeBuckets][];
        int[] tamanhos = new int[quantidadeBuckets];

        // Primeiro conta quantos elementos irão para cada bucket.
        for (int valor : v) {
            int indice = (int) ((long) valor * quantidadeBuckets / ((long) maior + 1));
            if (indice >= quantidadeBuckets) {
                indice = quantidadeBuckets - 1;
            }
            tamanhos[indice]++;
        }

        // Cria cada bucket com o tamanho necessário.
        for (int i = 0; i < quantidadeBuckets; i++) {
            buckets[i] = new int[tamanhos[i]];
            tamanhos[i] = 0;
        }

        // Distribui os valores.
        for (int valor : v) {
            int indice = (int) ((long) valor * quantidadeBuckets / ((long) maior + 1));
            if (indice >= quantidadeBuckets) {
                indice = quantidadeBuckets - 1;
            }

            buckets[indice][tamanhos[indice]++] = valor;
        }

        // Ordena cada bucket usando Insertion Sort.
        for (int[] bucket : buckets) {
            insertionSort(bucket);
        }

        // Junta os buckets no vetor original.
        int posicao = 0;

        for (int[] bucket : buckets) {
            for (int valor : bucket) {
                v[posicao++] = valor;
            }
        }
    }
}
