import Types.DeLuxe;
import Types.Economy;
import Types.Standard;
import Types.Wash;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by julieglasdam on 12/07/2017.
 *
 * Admin adgangskode: 9876
 */
public class Main {
    public static void initMenu()
            throws FileNotFoundException {
        System.out.println("Tast 1 for administrator menu");
        System.out.println("Tast 2 for kunde menu");
        System.out.println("Tast 3 for at afbryde");
        System.out.println();

        Scanner input = new Scanner(System.in);
        int input2 = input.nextInt();

        if (input2 == 1){
            administratorLogin();
        }
        else if (input2 == 2){
            user();
        }
        else if (input2 == 3){
            initMenu();
        }
        else {
            System.out.println(input2 + " er ikke en mulighed");
            System.out.println();
            initMenu();
        }
    }

    public static void administratorLogin()
            throws FileNotFoundException {
        Scanner input = new Scanner(new File("src/administrator.txt"));
        int password = input.nextInt();

        System.out.print("Indtast adgangskode: ");
        Scanner inputPassword = new Scanner(System.in);
        int checkPassword = inputPassword.nextInt();

        if (password == checkPassword){
            administrator();
        }
        else {
            System.out.println("Forkert adgangskode");
            initMenu();
        }
    }

    public static void administrator()
            throws FileNotFoundException {
        System.out.println("Tast 1 for at se statistik");
        System.out.println("Tast 2 for at ændre kodeord");
        System.out.println("Tast 3 for at ændre priserne på vasketyperne");
        System.out.println("Tast 4 for at ændre prisloftet på vaskekortene");
        System.out.println("Tast 5 for at gå tilbage til hovedmenuen");

        Scanner inputAdminOptions = new Scanner(System.in);
        int adminChoise = inputAdminOptions.nextInt();

        if (adminChoise == 1) {
            initStatistics();
        }
        else if (adminChoise == 2) {
            changePassword();
        }
        else if (adminChoise == 3) {
            changeWashPrice();
        }
        else if (adminChoise == 4) {
            changeMaxCash();
        }
        else if (adminChoise == 5) {
            initMenu();
        }
        else {
            System.out.println(adminChoise + " er ikke en mulighed");
            administrator();
        }
    }

    public static void initStatistics()
            throws FileNotFoundException {
        int countCards = 0;
        int count = 0;
        int saldo = 0;
        int economy = 0;
        int standard = 0;
        int deluxe = 0;
        Scanner stats = new Scanner(new File("src/stats.txt"));
        Scanner cards = new Scanner(new File("src/cardDatabase.txt"));


        while (stats.hasNextInt()) {
            int token = stats.nextInt();
            count++;
            if (count%3 == 0) {
                saldo = saldo + token;
            }
            else if (count == 1 || count%4 == 0) {
                if (token == 1) {
                    economy++;
                }
                else if (token == 2) {
                    standard++;
                }
                else {
                    deluxe++;
                }
            }

        }
        if (count%3 != 0) {
            System.out.print("Fejl: Se administrator.txt");
            initMenu();
        }
        System.out.println("Antal vaske købt i alt: "+(count/3));

        System.out.println("Inkomst: "+saldo+" kr.");
        System.out.println("Antal Economy vaske købt: "+economy);
        System.out.println("Antal Standard vaske købt: "+standard);
        System.out.println("Antal De Luxe Vaske købt: "+deluxe);

        while (cards.hasNextInt()) {
            cards.nextInt();
            countCards++;
        }
        System.out.println("Antal kort købt: "+countCards);

    }

    public static void changePassword()
            throws FileNotFoundException {
        Scanner admin01 = new Scanner(new File("src/administrator.txt"));
        int password = admin01.nextInt();
        int max = admin01.nextInt();
        int economy = admin01.nextInt();
        int standard = admin01.nextInt();
        int deluxe = admin01.nextInt();

        System.out.print("Indtast nyt kodeord: ");
        Scanner inputNewPassword = new Scanner(System.in);
        int newPassword = inputNewPassword.nextInt();

        updateAdmin(newPassword, max, economy, standard, deluxe);

        System.out.println("Kodeord ændret");
        System.out.println();
        administrator();
    }


    public static void changeWashPrice()
            throws FileNotFoundException {
        Scanner admin01 = new Scanner(new File("src/administrator.txt"));
        int password = admin01.nextInt();
        int max = admin01.nextInt();
        int economy = admin01.nextInt();
        int standard = admin01.nextInt();
        int deluxe = admin01.nextInt();

        int newEconomy = 0;
        int newStandard = 0;
        int newDeluxe = 0;

        Wash wash01 = new Economy(economy);
        Wash wash02 = new Standard(standard);
        Wash wash03 = new DeLuxe(deluxe);

        System.out.println("Pris for Economy vask: "+wash01.getPrice()+" (Tast 1 for at ændre)");
        System.out.println("Pris for Standard vask: "+wash02.getPrice()+" (Tast 2 for at ændre)");
        System.out.println("Pris for De luxe vask: "+wash03.getPrice()+" (Tast 3 for at ændre)");
        System.out.println("Tast 4 for at vende tilbage til menuen");

        Scanner inputWashToChange = new Scanner(System.in);
        int washToChange = inputWashToChange.nextInt();

        if (washToChange == 1) {
            System.out.print("Ny pris: ");
            Scanner inputEconomy = new Scanner(System.in);
            newEconomy = inputEconomy.nextInt();

            updateAdmin(password, max, newEconomy, standard, deluxe);
        }

        else if (washToChange == 2) {
            System.out.print("Ny pris: ");
            Scanner inputStandard = new Scanner(System.in);
            newStandard = inputStandard.nextInt();

            updateAdmin(password, max, economy, newStandard, deluxe);
        }

        else if (washToChange == 3) {
            System.out.print("Ny pris: ");
            Scanner inputDeluxe = new Scanner(System.in);
            newDeluxe = inputDeluxe.nextInt();

            updateAdmin(password, max, economy, standard, newDeluxe);
        }

        else if (washToChange == 4) {
            administrator();
        }

        else {
            System.out.print(washToChange+" er ikke en mulighed");
            changeWashPrice();
        }

        System.out.println("ændringerne er gemt. Tast 1 for at gå tilbage til menuen");
        Scanner inputBackToMenu = new Scanner(System.in);
        int backToMenu = inputBackToMenu.nextInt();

        if (backToMenu == 1) {
            administrator();
        }
        else {
            System.out.println(backToMenu+" er ikke en mulighed");
            changeWashPrice();
        }
    }




    public static void changeMaxCash()
            throws FileNotFoundException {
        Scanner admin01 = new Scanner(new File("src/administrator.txt"));
        int password = admin01.nextInt();
        int max = admin01.nextInt();
        int economy = admin01.nextInt();
        int standard = admin01.nextInt();
        int deluxe = admin01.nextInt();

        System.out.println("Det nuværende loft er på "+max+" kr.");
        System.out.print("Indtast det ønskede loft: ");

        Scanner inputNewMax = new Scanner(System.in);
        int newMax = inputNewMax.nextInt();

        updateAdmin(password, newMax, economy, standard, deluxe);

        System.out.println("ændringerne er gemt");
        System.out.println();
        System.out.println("Tast 1 for at gå tilbage til menuen");

        Scanner inputBackToMenu = new Scanner(System.in);
        int backToMenu = inputBackToMenu.nextInt();

        if (backToMenu == 1) {
            administrator();
        }
        else {
            System.out.println(backToMenu + " er ikke en mulighed");
        }
    }


    public static void user()
            throws FileNotFoundException {
        System.out.println("Tast 1 for at købe en vask");
        System.out.println("Tast 2 for at tjekke saldo");
        System.out.println("Tast 3 for at tanke op");
        System.out.println("Tast 4 for at købe et nyt vaskekort");
        System.out.println("Tast 5 for at afbryde");

        Scanner inputUserChoise = new Scanner(System.in);
        int userChoise = inputUserChoise.nextInt();

        if (userChoise == 1){
            buyWash();
        }
        else if (userChoise == 2){
            saldo();
        }
        else if (userChoise == 3){
            refill();
        }
        else if (userChoise == 4){
            buyWashcard();
        }
        else if (userChoise == 5){
            initMenu();
        }
        else {
            System.out.println(userChoise + " er ikke en mulighed");
            user();
        }
    }

    public static void buyWash()
            throws FileNotFoundException {
        Scanner admin01 = new Scanner(new File("src/administrator.txt"));
        int password = admin01.nextInt();
        int max = admin01.nextInt();
        int economy = admin01.nextInt();
        int standard = admin01.nextInt();
        int deluxe = admin01.nextInt();

        System.out.println("Venligst indsæt vaskekort");

        Scanner card = new Scanner(new File("src/card.txt"));
        int id = card.nextInt();
        int saldo = card.nextInt();

        User user01 = new User(id, saldo);
        Wash showPrice01 = new Economy(economy);
        Wash showPrice02 = new Standard(standard);
        Wash showPrice03 = new DeLuxe(deluxe);

        System.out.println();
        System.out.println("Vælg vask:");
        System.out.println("------------------");
        System.out.println();
        System.out.println("Tast 1 for Economy ("+showPrice01.getPrice()+")");
        System.out.println("Tast 2 for Standard ("+showPrice02.getPrice()+")");
        System.out.println("Tast 3 for De Luxe ("+showPrice03.getPrice()+")");

        Scanner inputWashChoise = new Scanner(System.in);
        int washType = inputWashChoise.nextInt();
        Wash wash01;

        if (washType == 1){
            wash01 = new Economy(economy);
        }
        else if (washType == 2){
            wash01 = new Standard(standard);
        }
        else if (washType == 3) {
            wash01 = new DeLuxe(deluxe);
        }
        else {
            System.out.println(washType + " er ikke en mulighed");
            wash01 = new Wash();
            user();
        }

        if (user01.getSaldo()<wash01.getPrice()){
            System.out.println("Der er ikke penge nok på kortet");
            System.out.println("Ønsker du at tanke op? (y/n)");

            Scanner inputRefill = new Scanner(System.in);
            String refill = inputRefill.next();

            if (refill.equals("y")){
                refill();
            }
            else if (refill.equals("n")){
                user();
            }
            else {
                System.out.println(refill + " er ikke en mulighed");
                buyWash();
            }
        }
        else {
            user01.setSaldo(wash01.getPrice());
            saldo = user01.getSaldo();
            System.out.println("Købet er gennemført");
            System.out.println("Ønsker du en kvittering?(y/n)");

            updateCard(id, saldo);
            updateStats(washType, id, wash01.getPrice());

            Scanner inputReciept = new Scanner(System.in);
            String reciept = inputReciept.next();

            if (reciept.equals("y")){
                System.out.println();
                System.out.println("Kvittering");
                System.out.println("------------------");
                System.out.println("Køb: " + wash01.getName());
                System.out.println("Pris: " + wash01.getPrice());
                System.out.println("Ny saldo: " + user01.getSaldo());
            }

            else if (reciept.equals("n")){
                user();
            }

            else {
                System.out.println(reciept + " er ikke en mulighed");
            }

            System.out.println();
            System.out.println("Tast 1 for at vende tilbage til menuen");

            Scanner inputBackToMenu = new Scanner(System.in);
            int backToMenu = inputBackToMenu.nextInt();

            if (backToMenu == 1) {
                user();
            }
            else {
                System.out.println(backToMenu + " er ikke en mulighed");
            }
        }
    }

    public static void saldo()
            throws FileNotFoundException {
        Scanner card = new Scanner(new File("src/card.txt"));
        int id = card.nextInt();
        int saldo = card.nextInt();

        User user01 = new User(id, saldo);
        System.out.println("Saldo: " + user01.getSaldo()+ " kr.");

        System.out.println();
        System.out.println("Tast 1 for at gå tilbage til menuen");

        Scanner inputBackToMenu = new Scanner(System.in);
        int backToMenu = inputBackToMenu.nextInt();
        if (backToMenu == 1){
            user();
        }
        else {
            System.out.println(backToMenu + " er ikke en mulighed");
            saldo();
        }
    }


    public static void refill()
            throws FileNotFoundException {
        Scanner card = new Scanner(new File("src/card.txt"));
        int id = card.nextInt();
        int saldo = card.nextInt();

        Scanner admin = new Scanner(new File("src/administrator.txt"));
        int password = admin.nextInt();
        int max = admin.nextInt();

        User user01 = new User(id, saldo);
        System.out.println("Nuværende saldo: " + user01.getSaldo() + " kr.");
        System.out.print("Indtast beløb du ønsker at indsåtte (Husk at du kan have max. "+max+" kr. på kortet): ");

        Scanner inputAmount = new Scanner(System.in);
        int amount = inputAmount.nextInt();

        if (amount + user01.getSaldo()>max){
            System.out.println("Du kan højst indsætte " + (max-user01.getSaldo()) + " kr");
            System.out.println();
            refill();
        }

        else {
            System.out.println(amount + " kr. er blevet sat ind på dit kort");
            saldo = saldo + amount;

            updateCard(id, saldo);

            System.out.println("Tast 1 for at gå tilbage til menuen");
            Scanner inputBackToMenu = new Scanner(System.in);
            int backToMenu = inputBackToMenu.nextInt();

            if (backToMenu == 1){
                user();
            }
            else {
                System.out.println(backToMenu + " er ikke en mulighed");
            }
        }
    }


    public static void buyWashcard()
            throws FileNotFoundException {
        int id = 0;
        int count = 1;

        Scanner admin = new Scanner(new File("src/administrator.txt"));
        int password = admin.nextInt();
        int max = admin.nextInt();

        Scanner cards = new Scanner(new File("src/cardDatabase.txt"));

        while (cards.hasNextInt()){
            cards.nextInt();
            count++;
        }
        id = count;

        System.out.print("Hvor meget ønsker du at indsætte på kortet(max. "+max+" kr)? ");

        Scanner inputAmount = new Scanner(System.in);
        int amount = inputAmount.nextInt();
        if (amount > max) {
            System.out.println("Du kan max. indsætte "+max+" kr.");
            buyWashcard();
        }
        else {
            updateCard(id, amount);
        }

        System.out.println();
        System.out.println("Dit kort er oprettet. Tast 1 for at gå tilbage til menuen");

        System.out.println("Tast 1 for at gå tilbage til menuen");

        Scanner inputBackToMenu = new Scanner(System.in);
        int backToMenu = inputBackToMenu.nextInt();

        if (backToMenu == 1){
            user();
        }
        else {
            System.out.println(backToMenu + " er ikke en mulighed");
        }
    }

    public static void updateStats(int washtype, int id, int price)
            throws FileNotFoundException {
        ArrayList<Integer> stats = new ArrayList<Integer>();

        Scanner statList = new Scanner(new File("src/stats.txt"));

        while (statList.hasNextInt()) {
            int i = statList.nextInt();
            stats.add(i);
        }
        stats.add(washtype);
        stats.add(id);
        stats.add(price);

        PrintStream outputList = new PrintStream(new File("src/stats.txt"));

        for (int i = 0; i < stats.size(); i++) {
            int token = stats.get(i);
            outputList.print(token+" ");
        }
    }


    public static void updateAdmin(int password, int max, int economy, int standard, int deluxe)
            throws FileNotFoundException {
        PrintStream output = new PrintStream(new File("src/administrator.txt"));
        output.print(password+" ");
        output.print(max+" ");
        output.print(economy+" ");
        output.print(standard+" ");
        output.print(deluxe);
    }

    public static void updateCard (int id, int saldo)
            throws FileNotFoundException {
        PrintStream output = new PrintStream(new File("src/card.txt"));
        output.print(id + " ");
        output.print(saldo);
    }

    public static void main(String[] args)
            throws FileNotFoundException {
        initMenu();
    }
}
