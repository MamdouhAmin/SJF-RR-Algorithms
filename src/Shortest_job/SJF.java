package Shortest_job;

import java.util.Scanner;

public class SJF {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of processes: ");
        int n = scanner.nextInt();

        int[] burstTimes = new int[n];
        int[] waitingTimes = new int[n];
        int[] turnaroundTimes = new int[n];

        for (int i = 0; i < n; i++) {
            System.out.print("Enter the burst time for process " + (i+1) + ": ");
            burstTimes[i] = scanner.nextInt();
        }

        // Sort processes by their burst time in ascending order
        for (int i = 0; i < n-1; i++) {
            for (int j = i+1; j < n; j++) {
                if (burstTimes[i] > burstTimes[j]) {
                    int temp = burstTimes[i];
                    burstTimes[i] = burstTimes[j];
                    burstTimes[j] = temp;
                }
            }
        }

        // Calculate waiting times and turnaround times
        int totalWaitingTime = 0;
        int totalTurnaroundTime = 0;
        for (int i = 0; i < n; i++) {
            if (i == 0) {
                waitingTimes[i] = 0;
            } else {
                waitingTimes[i] = waitingTimes[i-1] + burstTimes[i-1];
            }
            turnaroundTimes[i] = waitingTimes[i] + burstTimes[i];
            totalWaitingTime += waitingTimes[i];
            totalTurnaroundTime += turnaroundTimes[i];
        }

        // Print the results
        System.out.println("Process\tBurst Time\tWaiting Time\tTurnaround Time");
        for (int i = 0; i < n; i++) {
            System.out.println("P" + (i+1) + "\t\t" + burstTimes[i] + "\t\t" + waitingTimes[i] + "\t\t" + turnaroundTimes[i]);
        }

        double avgWaitingTime = (double) totalWaitingTime / n;
        double avgTurnaroundTime = (double) totalTurnaroundTime / n;
        System.out.println("\nAverage waiting time: " + avgWaitingTime);
        System.out.println("Average turnaround time: " + avgTurnaroundTime);
    }
}
