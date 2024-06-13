package JavaEjs;

import java.util.Random;
import java.util.Scanner;

public class BuscaminasSencillo {

    private static boolean fin = false;

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        boolean ganaste = false;

        //debemos crear una matriz de 2*6
        int[][] matriz = new int[2][6];
        //vamos a poner las bombas:
        System.out.println("La posición actual se indica con *. Para moverte pulsa: \nD para moverte hacia la derecha (unico sentido) \nW para moverte hacia arriba \nS para moverte hacia abajo");
        creacionTablero(matriz);

        while (!fin) {
            System.out.print("Movimiento seleccionado: ");
            char movimiento = sc.next().charAt(0);
            jugar(matriz, movimiento);
        }
        System.out.println("Fin del juego! HAS GANADO!!!");

    }

    public static void creacionTablero(int[][] matriz) {

        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                matriz[i][j] = 0;
            }
        }

        Random r = new Random();
        int contador = 0;
        while (contador < 3) {

            int monFila = r.nextInt(0, 2);
            int monCol = r.nextInt(0, 6);

            while (matriz[monFila][monCol] == 1) {
                monFila = r.nextInt(0, 2);
                monCol = r.nextInt(0, 6);
            }

            matriz[monFila][monCol] = 1;

            if (monFila == 0 && matriz[1][monCol] == 1) {
                matriz[monFila][monCol] = 0;
                contador--;
            } else if (monFila == 1 && matriz[0][monCol] == 1) {
                matriz[monFila][monCol] = 0;
                contador--;
            } else if (monCol != 0 && monCol != 5) {
                if (monFila == 0 && (matriz[1][monCol - 1] == 1 || matriz[1][monCol + 1] == 1)) {
                    matriz[monFila][monCol] = 0;
                    contador--;
                } else if (monFila == 1 && (matriz[0][monCol - 1] == 1 || matriz[0][monCol + 1] == 1)) {
                    matriz[monFila][monCol] = 0;
                    contador--;
                }
            }
            contador++;
        }

        matriz[0][0] = 8;

        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                if (matriz[i][j] == 8) {
                    System.out.print("[*]" + " ");
                } else {
                    System.out.print("[ ]" + " ");
                }
            }
            System.out.println();
        }
    }

    public static void jugar(int[][] matriz, char mov) {
        boolean encontrado = false;

        if (pisasteBomba(matriz, mov)){
            for (int i = 0; i < matriz.length; i++) {
                for (int j = 0; j < matriz[i].length; j++) {
                    if (matriz[i][j] == 1) {
                        System.out.print("[X]" + " ");
                    } else {
                        System.out.print("[ ]" + " ");
                    }
                }
                System.out.println();
            }
            System.out.println("Has perdido :(");
            return;
        }

        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                if (mov == 'D' && matriz[i][j] == 8 && !encontrado) {
                    if (j < 4) {
                        matriz[i][j] = 0;
                        matriz[i][j + 1] = 8;
                        encontrado = true;
                    } else if (j == 4) {
                        matriz[i][j] = 0;
                        matriz[i][j + 1] = 8;
                        encontrado = true;
                        fin = true;
                    }
                }
                if (mov == 'W' && matriz[i][j] == 8 && !encontrado) {
                    if (i == 1) {
                        matriz[1][j] = 0;
                        matriz[0][j] = 8;
                        encontrado = true;
                    } else if (i == 0) {
                        System.out.println("Ya se encuentra en el límite superior.");
                    }
                }
                if (mov == 'S' && matriz[i][j] == 8 && !encontrado) {
                    if (i == 0) {
                        matriz[0][j] = 0;
                        matriz[1][j] = 8;
                        encontrado = true;
                    } else if (i == 1) {
                        System.out.println("Ya se encuentra en el límite inferior.");
                    }
                }

            }
        }

        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                if (matriz[i][j] == 8) {
                    System.out.print("[*]" + " ");
                } else {
                    System.out.print("[ ]" + " ");
                }
            }
            System.out.println();
        }
    }

    public static boolean pisasteBomba(int[][] matriz, char mov) {

        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                if (mov == 'D' && matriz[i][j] == 8 && matriz[i][j+1] == 1 && j< matriz[i].length) return true;
                else if (mov == 'W' && matriz[i][j] == 8 && matriz[i-1][j] == 1 && i==1) return true;
                else if (mov == 'S' && matriz[i][j] == 8 && matriz[i+1][j] == 1 && i==0) return true;
            }
        }
        return false;

    }

}
