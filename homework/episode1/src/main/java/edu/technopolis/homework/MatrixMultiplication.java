package edu.technopolis.homework;

/**
 * Matrix multiplication home task.
 * <br/>
 * Matrix dimension and elements are passed as CLI arguments.
 */
public class MatrixMultiplication {
    public static void main(String[] args) {
        if (!validateArgs(args)){
            return;
        }

        int a_n = Integer.parseInt(args[0]);
        int a_m = Integer.parseInt(args[1]);
        int b_x = Integer.parseInt(args[2]);
        int b_y = Integer.parseInt(args[3]);

        int[][] a = readInArray(args, 4, a_n, a_m);
        int[][] b = readInArray(args, 4 + a_n * a_m, b_x, b_y);

        int[][] result = calculate(a, b);

        print(result);
    }

    private static boolean validateArgs(String[] args){
        if (args.length < 4){
            System.out.print("Enter the dimension of the matrix");
            return false;
        }

        int a_n = Integer.parseInt(args[0]);
        int a_m = Integer.parseInt(args[1]);
        int b_x = Integer.parseInt(args[2]);
        int b_y = Integer.parseInt(args[3]);

        if (a_n <= 0 || a_m <= 0 || b_x <= 0 || b_y <= 0){
            System.out.print("The dimension of the matrix must be positive");
            return false;
        }

        if (args.length - 4 < a_n * a_m + b_x * b_y){
            System.out.print("Not enough arguments to fill in the matrix");
            return false;
        }

        if (a_m != b_x){
            System.out.print("Matrices of this dimension are not multiplied");
            return false;
        }

        return true;
    }

    private static int[][] readInArray(String[] args, int start, int n, int m){
        int[][] out = new int[n][m];
        for (int i = 0; i < n; i++){
            for (int j = 0; j < m; j++){
                out[i][j] = Integer.parseInt(args[start++]);
            }
        }
        return out;
    }

    private static int[][] calculate(int[][] a, int[][] b){
        int a_n = a.length;
        int a_m = a[0].length;
        int b_y = b[0].length;

        int[][] c = new int[a_n][b_y];

        for (int i = 0; i < a_n; i++){
            for (int j = 0; j < b_y; j++){
                int sum = 0;
                for (int k = 0; k < a_m; k++){
                    sum += a[i][k] * b[k][j];
                }
                c[i][j] = sum;
            }
        }

        return c;
    }

    private static void print(int[][] a){
        int a_n = a.length;
        int a_m = a[0].length;

        for (int i = 0; i < a_n; i++){
            for (int j = 0; j < a_m; j++){
                System.out.print(a[i][j] + " ");
            }
            System.out.println();
        }
    }
}