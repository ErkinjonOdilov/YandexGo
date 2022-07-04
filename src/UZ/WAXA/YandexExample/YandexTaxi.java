package UZ.WAXA.YandexExample;

import javax.imageio.metadata.IIOMetadataFormatImpl;
import java.security.spec.PSSParameterSpec;
import java.util.Arrays;
import java.util.Scanner;

public class YandexTaxi {
    private Driver[] drivers = new Driver[100];
    private int dCount = 0;
    private Passenger[] passengers = new Passenger[100];
    private int pCount = 0;
    private Monitor[] monitors = new Monitor[100];
    private int mCount = 0;
    private Scanner scanner = new Scanner(System.in);

    public void driverMenu() {
        boolean a = true;
        while (a) {
            System.out.println("1->Ro'yxatdan o'tish: 2->Tizimga kirish; 3->Chqish;");
            int n = scanner.nextInt();
            switch (n) {
                case 1 -> {
                    System.out.print("Car number: ");
                    String number = scanner.next();
                    System.out.print("Car model: ");
                    String model = scanner.next();
                    System.out.println("Choose tarif type: ");
                    TarifType[] values = TarifType.values();
                    for (int i = 0; i < values.length; i++) {
                        System.out.println(i + 1 + " ->" + values[i]);
                    }
                    int chooseTarifType = scanner.nextInt() - 1;
                    TarifType value = values[chooseTarifType];
                    System.out.print("Password: ");
                    String password = scanner.next();
                    System.out.print("Phone number: ");
                    String phoneNumber = scanner.next();
                    Driver driver = new Driver(number, model, value, password, phoneNumber);
                    drivers[dCount++] = driver;
                }
                case 2 -> {
                    System.out.print("Phone number: ");
                    String phoneNumber = scanner.next();
                    System.out.print("Password: ");
                    String password = scanner.next();

                    int driverIndex = -1;
                    for (int i = 0; i < dCount; i++) {
                        if (drivers[i].phoneNumber.equals(phoneNumber) && drivers[i].password.equals(password)) {
                            driverIndex = i;
                            break;
                        }
                    }
                    if (driverIndex == -1) {
                        System.out.println("Driver not found");
                    } else {
                        System.out.println(drivers[driverIndex].number + " tzimga xush kelipsiz!!");
                        driverTaxi(driverIndex);
                    }


                }
                case 3 -> {
                    a = false;
                }
            }
        }
    }

    public void driverTaxi(int index) {
        Driver driver = drivers[index];
        boolean a = true;
        while (a) {
            System.out.println("1->Yo'lovchi manzilga elitish; 2->Hozirgi yo'lovchi malumotlari; 3->Chqish;");
            int n = scanner.nextInt();
            switch (n) {
                case 1 -> {
                    for (int i = 0; i < mCount; i++) {
                        if (monitors[i].driver==driver && !monitors[i].isFinished){
                            monitors[i].isFinished=true;
                        }
                    }

                }
                case 2 -> {
                    for (int i = 0; i < mCount; i++) {
                        if (monitors[i].driver== driver && !monitors[i].isFinished){
                            System.out.println(monitors[i].passenger);
                        }
                    }
                }
                case 3->{
                    a = false;
                }
            }
        }
    }
    public void  monitoring(){
        for (int i = 0; i <mCount; i++) {
            Monitor monitor=monitors[i];
            System.out.println(monitor);

        }
    }

    public void passengeMenu() {
        boolean a = true;
        while (a) {
            System.out.println("1->Ro'yxatdan o'tish; 2->Tzimga kirish; 3->Chqish;");
            int n = scanner.nextInt();
            switch (n) {
                case 1 -> {
                    System.out.print("Phone number: ");
                    String phoneNumber = scanner.next();
                    System.out.print("Name: ");
                    String name = scanner.next();
                    System.out.print("Password: ");
                    String password = scanner.next();
                    Passenger passenger = new Passenger(phoneNumber, name, password);
                    passengers[pCount++] = passenger;

                }
                case 2 -> {
                    System.out.print("Phone number: ");
                    String phoneNumber = scanner.next();
                    System.out.print("Password: ");
                    String password = scanner.next();
                    int passengerIndex = -1;
                    for (int i = 0; i < pCount; i++) {
                        if (passengers[i].phoneNumber.equals(phoneNumber) && passengers[i].password.equals(password)) {
                            passengerIndex = i;
                            break;
                        }
                    }
                    if (passengerIndex == -1) {
                        System.out.println("Passenger not found");
                    } else {
                        System.out.println(passengers[passengerIndex].name + "xush kelipsiz!!!");
                        passengerTaxi(passengerIndex);
                    }

                }
                case 3 -> {
                    a = false;
                }
            }
        }
    }

    public void passengerTaxi(int index) {
        boolean a = true;
        while (a) {
            System.out.println("1->Taxi chaqirish; 2->Chqish;");
            int n = scanner.nextInt();
            switch (n) {
                case 1 -> {
                    Passenger passenger = passengers[index];
                    TarifType[] values = TarifType.values();
                    for (int i = 0; i < values.length; i++) {
                        System.out.println((i + 1) + " ->" + values[i]);
                    }
                    int n1 = scanner.nextInt()-1;
                    TarifType value = values[n1];
                    Object[] freeDrivers = Arrays.stream(drivers, 0, dCount).filter(driver -> driver.tarifType == value && !driver.isFree).toArray();
                    for (int i = 0; i < freeDrivers.length; i++) {
                        Driver driver = (Driver) freeDrivers[i];
                        System.out.println((i + 1) + " ->" + driver.number + " " + driver.model);
                    }
                    int n2 = scanner.nextInt()-1;
                    Driver driver = (Driver) freeDrivers[n2];
                    driver.isFree=true;
                    System.out.print("Turgan joyi:");
                    String currentPlace = scanner.next();
                    System.out.print("Boradigan joyi:");
                    String targetPlace = scanner.next();
                    Monitor monitor = new Monitor(passenger, driver, currentPlace, targetPlace);
                    monitors[mCount++] = monitor;
                }
                case 2 -> {
                    a = false;
                }
            }

        }
    }


    private class Driver {
        private String number;
        private String model;
        private TarifType tarifType;
        private String password;
        private String phoneNumber;
        private boolean isFree;

        public Driver(String number, String model, TarifType tarifType, String password, String phoneNumber) {
            this.number = number;
            this.model = model;
            this.tarifType = tarifType;
            this.password = password;
            this.phoneNumber = phoneNumber;
        }
    }


    private class Passenger {
        private String phoneNumber;
        private String name;
        private String password;

        public Passenger(String phoneNumber, String name, String password) {
            this.phoneNumber = phoneNumber;
            this.name = name;
            this.password = password;
        }

        @Override
        public String toString() {
            return "Passenger{" +
                    "phoneNumber='" + phoneNumber + '\'' +
                    ", name='" + name + '\'' +
                    ", password='" + password + '\'' +
                    '}';
        }
    }

    private class Monitor {
        private Passenger passenger;
        public Driver driver;
        private String currentPlace;
        private String targetPlace;
        private boolean isFinished;

        public Monitor(Passenger passenger, Driver driver, String currentPlace, String targetPlace) {
            this.passenger = passenger;
            this.driver = driver;
            this.currentPlace = currentPlace;
            this.targetPlace = targetPlace;
        }

        @Override
        public String toString() {
            return "Monitor{" +
                    "passenger=" + passenger.name +
                    ", driver=" + driver.number +
                    ", currentPlace='" + currentPlace + '\'' +
                    ", targetPlace='" + targetPlace + '\'' +
                    ", isFinished=" + isFinished +
                    '}';
        }
    }
}

