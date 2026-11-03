# TP1 - Algoritmos de Ordenação em Java

Trabalho Prático 1 da disciplina de Algoritmos e Classificação de Dados.

O projeto implementa e compara dez algoritmos de ordenação em Java, medindo o tempo de execução com `System.nanoTime()`.

## Algoritmos implementados

1. Bubble Sort
2. Insertion Sort
3. Selection Sort
4. Shell Sort
5. Heap Sort
6. Merge Sort
7. Quick Sort
8. Counting Sort
9. Radix Sort
10. Bucket Sort

## Estrutura

- `Ordenadores.java` - implementação dos algoritmos.
- `Testes.java` - testes simples de corretude.
- `Benchmark.java` - execução dos experimentos e geração do CSV.
- `resultados/resultados.csv` - dados da coleta experimental.
- `graficos/` - gráficos gerados a partir dos resultados.

## Compilação

No terminal, dentro da pasta do projeto:

```bash
javac Ordenadores.java Testes.java Benchmark.java
```

## Testes de corretude

```bash
java Testes
```

Os dez algoritmos devem apresentar `OK`.

## Benchmark

```bash
java Benchmark
```

O programa mede somente a execução do método de ordenação. A criação do vetor e a cópia utilizada por cada algoritmo são realizadas antes do início do cronômetro.

O benchmark utiliza:

- tamanhos: 1.000, 10.000, 100.000 e 1.000.000;
- entradas: aleatória, crescente e decrescente;
- 10 repetições por configuração;
- 3 execuções preliminares para aquecimento da JVM.

Bubble Sort, Insertion Sort e Selection Sort são executados até 10.000 elementos para evitar tempos impraticáveis devido ao comportamento quadrático.

## Formato dos resultados

O arquivo CSV contém:

```text
algoritmo,tamanho,configuracao,repeticao,tempo_ns,tempo_ms
```

Os valores decimais são gravados com ponto (`.`), usando `Locale.US`.
