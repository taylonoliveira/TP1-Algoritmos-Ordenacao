import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;
import java.util.Locale;

public class Benchmark {

    // Tamanhos sugeridos no trabalho.
    private static final int[] TAMANHOS = {
        1_000,
        10_000,
        100_000,
        1_000_000
    };

    // Tipos de entrada.
    private static final String[] CONFIGURACOES = {
        "aleatorio",
        "crescente",
        "decrescente"
    };

    // Pelo menos 10 execuções são exigidas pelo enunciado.
    private static final int REPETICOES = 10;

    // Execuções preliminares para aquecer a JVM.
    private static final int AQUECIMENTOS = 3;

    // Evita testes impraticáveis com algoritmos O(n²).
    // Altere este valor se quiser permitir tamanhos maiores.
    private static final int LIMITE_QUADRATICOS = 10_000;

    // Valores de 0 até este limite.
    // Mantém Counting Sort viável em memória.
    private static final int VALOR_MAXIMO = 100_000;

    private static final long SEED = 123456789L;

    private static final String[] ALGORITMOS = {
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

    public static void main(String[] args) {
        System.out.println("Iniciando benchmark...");
        System.out.println("Resultados serão salvos em resultados.csv\n");

        try (PrintWriter arquivo = new PrintWriter(new FileWriter("resultados.csv"))) {

            arquivo.println(
                "algoritmo,tamanho,configuracao,repeticao,tempo_ns,tempo_ms"
            );

            for (int tamanho : TAMANHOS) {
                for (String configuracao : CONFIGURACOES) {

                    System.out.println(
                        "\nTamanho: " + tamanho +
                        " | Entrada: " + configuracao
                    );

                    // Aquecimento da JVM
                    aquecerJVM(tamanho, configuracao);

                    for (int repeticao = 1; repeticao <= REPETICOES; repeticao++) {

                        // Cada repetição cria uma entrada-base.
                        // Todos os algoritmos recebem cópias exatamente iguais.
                        int[] original = gerarVetor(
                            tamanho,
                            configuracao,
                            SEED + tamanho + repeticao
                        );

                        for (String algoritmo : ALGORITMOS) {

                            if (devePular(algoritmo, tamanho)) {
                                if (repeticao == 1) {
                                    System.out.println(
                                        algoritmo +
                                        " ignorado para n=" + tamanho +
                                        " por ser O(n²)."
                                    );
                                }
                                continue;
                            }

                            // A cópia é feita ANTES do cronômetro.
                            int[] vetor = original.clone();

                            long inicio = System.nanoTime();

                            ordenar(algoritmo, vetor);

                            long fim = System.nanoTime();

                            long tempoNs = fim - inicio;
                            double tempoMs = tempoNs / 1_000_000.0;

                            if (!estaOrdenado(vetor)) {
                                throw new RuntimeException(
                                    "Falha de ordenação em " + algoritmo
                                );
                            }

                            arquivo.printf(
                                Locale.US,
                                "%s,%d,%s,%d,%d,%.6f%n",
                                algoritmo,
                                tamanho,
                                configuracao,
                                repeticao,
                                tempoNs,
                                tempoMs
                            );

                            arquivo.flush();

                            System.out.printf(
                                Locale.US,
                                "%-15s | repetição %2d | %12.3f ms%n",
                                algoritmo,
                                repeticao,
                                tempoMs
                            );
                        }
                    }
                }
            }

            System.out.println("\nBenchmark concluído.");
            System.out.println("Arquivo criado: resultados.csv");

        } catch (IOException e) {
            System.out.println("Erro ao salvar o CSV: " + e.getMessage());
        }
    }

    private static void aquecerJVM(int tamanho, String configuracao) {
        // Usa um tamanho pequeno para o aquecimento não demorar.
        int tamanhoAquecimento = Math.min(tamanho, 5_000);

        for (int i = 0; i < AQUECIMENTOS; i++) {
            int[] original = gerarVetor(
                tamanhoAquecimento,
                configuracao,
                SEED + 999 + i
            );

            for (String algoritmo : ALGORITMOS) {
                if (devePular(algoritmo, tamanhoAquecimento)) {
                    continue;
                }

                int[] copia = original.clone();
                ordenar(algoritmo, copia);
            }
        }
    }

    private static boolean devePular(String algoritmo, int tamanho) {
        boolean quadratico =
            algoritmo.equals("Bubble Sort") ||
            algoritmo.equals("Insertion Sort") ||
            algoritmo.equals("Selection Sort");

        return quadratico && tamanho > LIMITE_QUADRATICOS;
    }

    private static int[] gerarVetor(
        int tamanho,
        String configuracao,
        long seed
    ) {
        int[] v = new int[tamanho];

        if (configuracao.equals("crescente")) {
            // Mantém os valores dentro do limite usado no Counting Sort.
            for (int i = 0; i < tamanho; i++) {
                v[i] = (int) ((long) i * VALOR_MAXIMO / Math.max(1, tamanho));
            }

        } else if (configuracao.equals("decrescente")) {
            for (int i = 0; i < tamanho; i++) {
                v[i] = (int) ((long) (tamanho - 1 - i) * VALOR_MAXIMO
                    / Math.max(1, tamanho));
            }

        } else {
            Random random = new Random(seed);

            for (int i = 0; i < tamanho; i++) {
                v[i] = random.nextInt(VALOR_MAXIMO + 1);
            }
        }

        return v;
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
            default:
                throw new IllegalArgumentException(
                    "Algoritmo desconhecido: " + algoritmo
                );
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
