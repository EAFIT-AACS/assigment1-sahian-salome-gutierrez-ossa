import java.util.*;

// Clase que representa un Autómata Finito Determinista (DFA)
class DFA {
    int numEstados;
    Set<Integer> estadosFinales;
    int[][] tablaTransiciones;
    Map<String, Integer> indiceSimbolo; // Mapeo de símbolos del alfabeto a índices

    public DFA(int numEstados, Set<Integer> estadosFinales, int[][] tablaTransiciones, Map<String, Integer> indiceSimbolo) {
        this.numEstados = numEstados;
        this.estadosFinales = estadosFinales;
        this.tablaTransiciones = tablaTransiciones;
        this.indiceSimbolo = indiceSimbolo;
    }
}

// Clase que implementa la minimización de un DFA
class MinimizadorDFA {
    public List<String> minimizar(DFA dfa) {
        int n = dfa.numEstados;
        int numSimbolos = dfa.indiceSimbolo.size();
        boolean[][] marcados = new boolean[n][n]; // Matriz para marcar pares de estados no equivalentes

        // Paso 1: Marcar pares (p, q) donde uno es final y el otro no
        for (int p = 0; p < n; p++) {
            for (int q = p + 1; q < n; q++) {
                if (dfa.estadosFinales.contains(p) != dfa.estadosFinales.contains(q)) {
                    marcados[p][q] = true;
                }
            }
        }

        // Paso 2: Iterar hasta que no haya más cambios en la matriz de marcados
        boolean cambiado;
        do {
            cambiado = false;
            for (int p = 0; p < n; p++) {
                for (int q = p + 1; q < n; q++) {
                    if (!marcados[p][q]) {
                        for (int a = 0; a < numSimbolos; a++) {
                            int siguienteP = dfa.tablaTransiciones[p][a];
                            int siguienteQ = dfa.tablaTransiciones[q][a];
                            if (siguienteP != siguienteQ && marcados[Math.min(siguienteP, siguienteQ)][Math.max(siguienteP, siguienteQ)]) {
                                marcados[p][q] = true;
                                cambiado = true;
                                break;
                            }
                        }
                    }
                }
            }
        } while (cambiado);

        // Paso 3: Recolectar pares no marcados (son equivalentes)
        List<String> paresEquivalentes = new ArrayList<String>();
        for (int p = 0; p < n; p++) {
            for (int q = p + 1; q < n; q++) {
                if (!marcados[p][q]) {
                    paresEquivalentes.add("(" + p + ", " + q + ")");
                }
            }
        }
        return paresEquivalentes;
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Input:");

        int numCasos = Integer.parseInt(sc.nextLine());
        MinimizadorDFA minimizador = new MinimizadorDFA();
        List<String> resultados = new ArrayList<String>(); // Lista para almacenar los resultados

        // Procesar cada caso de prueba
        for (int t = 0; t < numCasos; t++) {
            int numEstados = Integer.parseInt(sc.nextLine());
            String[] alfabeto = sc.nextLine().toLowerCase().split(" ");

            // Validar que todos los símbolos estén entre 'a' y 'z'
            for (String simbolo : alfabeto) {
                if (simbolo.length() != 1 || simbolo.charAt(0) < 'a' || simbolo.charAt(0) > 'z') {
                    System.err.println("Error: El símbolo '" + simbolo + "' no es una letra (a-z).\n");
                    return;
                }
            }

            // Crear un mapa de símbolos a índices
            Map<String, Integer> indiceSimbolo = new HashMap<String, Integer>();
            for (int i = 0; i < alfabeto.length; i++) {
                indiceSimbolo.put(alfabeto[i], i);
            }

            Set<Integer> estadosFinales = new HashSet<Integer>();
            String[] estadosFinalesStr = sc.nextLine().split(" ");
            for (String estado : estadosFinalesStr) {
                estadosFinales.add(Integer.parseInt(estado));
            }

            // Leer la tabla de transiciones
            int[][] tablaTransiciones = new int[numEstados][alfabeto.length]; // Matriz de transiciones ajustada al tamaño del alfabeto
            for (int i = 0; i < numEstados; i++) {
                String[] transiciones = sc.nextLine().split(" ");
                int estado = Integer.parseInt(transiciones[0]); // Primer valor es el estado actual

                // Validar que tenga el número correcto de transiciones
                if (transiciones.length - 1 != alfabeto.length) {
                    System.err.println("Error: El estado " + estado + " tiene " + (transiciones.length - 1) + " transiciones, pero se esperaban " + alfabeto.length + "\n");
                    return;
                }

                // Guardar transiciones en la tabla
                for (int j = 0; j < alfabeto.length; j++) {
                    tablaTransiciones[estado][j] = Integer.parseInt(transiciones[j + 1]);
                }
            }

            DFA dfa = new DFA(numEstados, estadosFinales, tablaTransiciones, indiceSimbolo);
            List<String> resultado = minimizador.minimizar(dfa);
            resultados.add(String.join(" ", resultado)); // Guardar el resultado
        }

        // Imprimir todos los resultados al final
        System.out.println("Output:");
        for (String resultado : resultados) {
            System.out.println(resultado);
        }

        sc.close();
    }
}
