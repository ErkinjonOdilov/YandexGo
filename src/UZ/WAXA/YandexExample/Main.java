package UZ.WAXA.YandexExample;

import java.util.Scanner;


public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        YandexTaxi yandexTaxi = new YandexTaxi();
        boolean a = true;
        while (a) {
            System.out.println("Menyulardan birini tanlang: ");
            System.out.println("1 ->Driver");
            System.out.println("2 ->Passenger");

            UserType[] values = UserType.values();
            for (int i = 0; i < values.length; i++) {
                System.out.println((i + 1) + "->" + values[i]);
            }
            System.out.println("3 ->Monitoring \n0 ->Tizimdan chiqish");
            int n = scanner.nextInt();
            switch (n) {
                case 0 -> {
                    a = false;
                }
                case 1 -> {
                    yandexTaxi.driverMenu();
                }
                case 2 -> {
                    yandexTaxi.passengeMenu();
                }
                case 3 -> {
                    yandexTaxi.monitoring();
                }

            }
        }
    }
}
