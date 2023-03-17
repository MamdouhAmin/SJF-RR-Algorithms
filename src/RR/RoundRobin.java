import java.util.Scanner;

public class RoundRobin {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter the number of processes: ");
        int n = sc.nextInt();

        int[] bt = new int[n];
        int[] wt = new int[n];
        int[] tat = new int[n];
        int[] rt = new int[n];

        System.out.println("Enter the burst time for each process: ");
        for (int i = 0; i < n; i++) {
            System.out.print("P" + (i+1) + ": ");
            bt[i] = sc.nextInt();
            rt[i] = bt[i];
        }

        System.out.print("Enter the time quantum: ");
        int quantum = sc.nextInt();

        int time = 0;
        boolean flag = false;

        while (true) {
            for (int i = 0; i < n; i++) {
                if (rt[i] > 0) {
                    flag = true;

                    if (rt[i] > quantum) {
                        time += quantum;
                        rt[i] -= quantum;
                    } else {
                        time += rt[i];
                        wt[i] = time - bt[i];
                        rt[i] = 0;
                    }
                }
            }

            if (!flag) {
                break;
            }
            flag = false;
        }

        for (int i = 0; i < n; i++) {
            tat[i] = bt[i] + wt[i];
        }

        System.out.println("\nProcess\tBurst Time\tWaiting Time\tTurnaround Time");
        for (int i = 0; i < n; i++) {
            System.out.println("P" + (i+1) + "\t\t" + bt[i] + "\t\t" + wt[i] + "\t\t" + tat[i]);
        }
        float avgwt = 0, avgtat = 0;
        for (int i = 0; i < n; i++) {
            avgwt += wt[i];
            avgtat += tat[i];
        }
        System.out.println("\nAverage waiting time: " + (avgwt/n));
        System.out.println("Average turnaround time: " + (avgtat/n));
    }
}
