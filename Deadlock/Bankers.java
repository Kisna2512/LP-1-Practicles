import java.util.Scanner;

public class Bankers {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter No Of Processes : ");
        int p = sc.nextInt();

        System.out.println("Enter No Of Resources : ");
        int r = sc.nextInt();

        int[][] maxi = new int[p][r];
        int[][] alloc = new int[p][r];
        int[] safeSequence = new int[p];
        int[][] need = new int[p][r];
        int[] avail = new int[r];

        System.out.println("Enter Resources for Maximum resources Table");

        for (int i = 0; i < p; i++) {
            for (int j = 0; j < r; j++) {
                maxi[i][j] = sc.nextInt();
            }
        }

        System.out.println("Enter Resources for Allocated resources Table");

        for (int i = 0; i < p; i++) {
            for (int j = 0; j < r; j++) {
                alloc[i][j] = sc.nextInt();
            }
        }

        System.out.println("Enter Resources for Available resources Table");

        for (int i = 0; i < r; i++) {
            avail[i] = sc.nextInt();
        }

        for (int i = 0; i < p; i++) {
            for (int j = 0; j < r; j++) {
                need[i][j] = maxi[i][j] - alloc[i][j];
            }
        }

        // Displaying Maximum Table
        System.out.println("Maximum Table");

        for (int i = 0; i < p; i++) {
            for (int j = 0; j < r; j++) {
                System.out.print(maxi[i][j] + " ");
            }
            System.out.println();
        }

        System.out.println("Allocated Table");

        for (int i = 0; i < p; i++) {
            for (int j = 0; j < r; j++) {
                System.out.print(alloc[i][j] + " ");
            }
            System.out.println();
        }

        System.out.println("Need Table");

        for (int i = 0; i < p; i++) {
            for (int j = 0; j < r; j++) {
                System.out.print(need[i][j] + " ");
            }
            System.out.println();
        }

        boolean visited[] = new boolean[p];
        for (int i = 0; i < p; i++) {
            visited[i] = false;
        }

        int work[] = new int[r];
        for (int i = 0; i < r; i++) {
            work[i] = avail[i];
        }

        int count = 0;

        while (count < p) {
            boolean flag = false;
            for (int i = 0; i < p; i++) {
                if (visited[i] == false) {
                    int j;
                    for (j = 0; j < r; j++) {
                        if (need[i][j] > work[j])
                            break;
                    }
                    if (j == r) {
                        safeSequence[count++] = i + 1;
                        visited[i] = true;
                        flag = true;

                        for (j = 0; j < r; j++) {
                            work[j] = work[j] + alloc[i][j];
                        }
                        i = -1;
                    }
                }
            }
            if (flag == false) {
                break;
            }
        }
        if (count < p) {
            System.out.println("Deadlock in system, System Unsafe!");
        } else {
            System.out.println("Available Table");

            for (int i = 0; i < r; i++) {
                System.out.print(work[i] + " ");
            }
            System.out.println();

            System.out.println("The SAFE Sequence is :- ");
            for (int i = 0; i < p; i++) {
                System.out.print("P" + (safeSequence[i]));
                if (i != p - 1)
                    System.out.print(" -> ");
            }
        }
        sc.close();
    }
}