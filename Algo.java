

import java.util.Arrays;
import java.util.Scanner;

public class Algo {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("enter the no of companies");
        int N = scanner.nextInt();
        double[] stockPrices = new double[N];

        int countPricesUp = 0;
        int pricesDown = 0;

        for (int i = 0; i < N; i++) {
            System.out.println("Enter current stock price of the company " + (i + 1));
            double sharePrice = scanner.nextDouble();
            stockPrices[i] = sharePrice;

            System.out.println("Whether company's stock price rose today compare to yesterday?");
            boolean compare = scanner.nextBoolean();

            if (compare)
                countPricesUp += 1;
            else
                pricesDown += 1;
        }

        printMenu();

        String command = scanner.next().trim();
        do {
            switch (command.trim()) {
                case "1":
                    ascendingOrder(stockPrices);
                    break;
                case "2":
                    descendingOrder(stockPrices);
                    break;
                case "3":
                    priceRaiseToday(countPricesUp);
                    break;
                case "4":
                    priceDeclined(pricesDown);
                    break;
                case "5":
                    System.out.println("enter the key value\n");
                    double price = scanner.nextDouble();
                    searchStockPrice(stockPrices,price);
                    break;
                case "0":
                    System.exit(0);
                    break;
            }
            printMenu();
            command = scanner.next().trim();
        } while (!command.equals("0"));
    }

    public static void ascendingOrder(double[] stockPrices) {
        double[] copy = Arrays.copyOf(stockPrices, stockPrices.length);
        sort(copy, 0, stockPrices.length - 1, true);
        for (double price : copy) {
            System.out.print(price+" ");
        }
        System.out.println();
    }

   public static void descendingOrder(double[] stockPrices) {
        sort(stockPrices, 0, stockPrices.length - 1, false);
        for (double price : stockPrices) {
            System.out.print(price+" ");
        }
       System.out.println();
    }

    public static void priceRaiseToday(int countPricesUp) {
        System.out.println("Total no of companies whose stock price rose today : "+countPricesUp);
    }

    public static void priceDeclined(int pricesDown) {
        System.out.println("Total no of companies whose stock price declined today : "+pricesDown);
    }

    public static void searchStockPrice(double[] stockPrices, double price) {
        boolean found = false;
        for (double sPrice : stockPrices) {
            if (price == sPrice) {
                System.out.println(sPrice);
                found = true;
                break;
            }
        }

        if(found)
            System.out.println("Stock of value "+price+" is present");
        else
            System.out.println("Value not found");
    }

    private static void sort(double[] arr, int low, int high, boolean ascending) {
        if (low < high) {
            int pivot = partition(arr, low, high, ascending);
            sort(arr, low, pivot - 1, true);
            sort(arr, pivot + 1, high, true);
        }
    }

    private static int partition(double[] arr, int low, int high, boolean ascending) {
        double pivot = arr[high];
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (ascending ? arr[j] <= pivot : arr[j] > pivot) {
                i++;
                swap(arr, i, j);
            }
        }
        swap(arr, i + 1, high);
        return i + 1;
    }

    private static void swap(double[] arr, int i, int j) {
        double temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    private static void printMenu() {
        System.out.println("-----------------------------------------------\n");
        System.out.println("Enter the operation that you want to perform\n");
        System.out.println("1. Display the companies stock prices in ascending order\n" +
                "2. Display the companies stock prices in descending order\n" +
                "3. Display the total no of companies for which stock prices rose today\n" +
                "4. Display the total no of companies for which stock prices declined today\n" +
                "5. Search a specific stock price\n" +
                "6. press 0 to exit");
    }
}