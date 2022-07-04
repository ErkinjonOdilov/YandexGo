package UZ.WAXA.Tasks;

public class Car {
   boolean remen;


    public void remen(boolean a) {
        if (a) {

        } else {

            if (a) {
                System.out.println("Kamarni taqib mashinani xaydashingiz mumkun");
            } else {
                System.out.println("Mashinani o't oldir");
            }
        }


        class engine {
            boolean benzin;
            boolean start;

            public engine() {
            }

            public engine(boolean benzin, boolean start) {
                this.benzin = benzin;
                this.start = start;
            }

            public void benzin(boolean b) {
                if (b) {
                    System.out.println("Benzin bor");
                } else {
                    System.out.println("Benzin yetarli emas");
                }
            }

            public void start(boolean a) {
                if (a) {
                    System.out.println("Mashina o't oldi!");
                } else {
                    System.out.println("Mashinani o't oldiring!");
                }
            }


        }

    }
}

