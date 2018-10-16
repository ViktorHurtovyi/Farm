import com.google.gson.GsonBuilder;
import entity.Vegetables;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Scanner;

public class Farm {
    private static final com.google.gson.Gson Gson = new GsonBuilder().setPrettyPrinting().create();
    static   Scanner sc = new Scanner(System.in);
    static String[] ferm = null;
    static int money = 0;
    static Vegetables veg[];
    public static void main(String[] args) throws Exception {
        mysql mysql = new mysql();
        FileReader fr = new FileReader("data.json");
        veg = Gson.fromJson(fr, Vegetables[].class);
        fr.close();
        ferm = mysql.get();
        money = Integer.parseInt(ferm[8]);
        System.out.println("Ваши деньги: " + money);
        String request = null;
        int checkans = 0;
        while (checkans == 0) {
            System.out.println("1 для посадки растений, 2 для сбора урожая, 3 для выхода");
            request = sc.nextLine();
            if ((request.equals("1"))||(request.equals("2"))||(request.equals("3")))
                checkans = 1;
        }

        int a = 1;
        while(a==1) {
            if (request.equals("1")) {
                ferm();
                String check = "play";
                while (check.equals("play")) {
                    game();
                    System.out.println("1 для посадки растений, 2 для сбора урожая, 3 для выхода");
                    request = sc.nextLine();
                    break;
                }
            } else if (request.equals("2")) {
                ferm();
                String check = "play";
                while (check.equals("play")) {
                    harvesting();
                    System.out.println("1 для посадки растений, 2 для сбора урожая, 3 для выхода");
                    request = sc.nextLine();
                    break;
                }
            }

            else if (request.equals("3")) {
                mysql.save(money, ferm[0], ferm[1], ferm[2], ferm[3], ferm[4], ferm[5], ferm[6], ferm[7]);
                break;
            }
            }
        }
    public static void ferm(){
        System.out.println(ferm[0] + " | " +ferm[1] + " | " +ferm[2] + " | " +ferm[3] + " | " );
        System.out.println("-------------------");
        System.out.println(ferm[4] + " | " +ferm[5] + " | " +ferm[6] + " | " +ferm[7] + " | " );
        System.out.println(money);
    }
    public static void game() throws ParseException {
        MysqlTime mysqlTime = new MysqlTime();
        GregorianCalendar calendar = new GregorianCalendar();
        String pleace = null;
        String flower = null;
        int a =0;
        while(a==0) {
            System.out.println("введите клетку для растения");
             pleace = sc.nextLine();
            if ((pleace.equals("A1"))||(pleace.equals("A2"))||(pleace.equals("A3"))||(pleace.equals("A4"))||
                    (pleace.equals("B1"))||(pleace.equals("B2"))||(pleace.equals("B3"))||(pleace.equals("B4"))) {
                System.out.println("Что посадить? 1 - "+veg[0].getName()+", 2 - "+veg[1].getName()+", 3 - "+veg[2].getName()+"");
                String flowercheck = sc.nextLine();
                if (flowercheck.equals("1"))flower = veg[0].getName();
                else if (flowercheck.equals("2"))flower = veg[1].getName();
                else if (flowercheck.equals("3"))flower = veg[2].getName();
                if (flower != null) {
                    switch (flower) {
                        case ("cucumber"):
                            if (money >= 5) {
                                money = money - veg[0].getPrice();
                                calendar.add(Calendar.SECOND, + veg[0].getTime());
                            }
                            else {
                                System.out.println("Не хватает денег");
                                pleace = "err";
                            }
                            break;
                        case ("tomato"):
                            if (money >= 10) {
                                money = money - veg[1].getPrice();
                                calendar.add(Calendar.SECOND, + veg[1].getTime());
                            }
                            else {
                                System.out.println("Не хватает денег");
                                pleace = "err";
                            }
                            break;
                        case ("eggplant"):
                            if (money >= 15) {
                                money = money - veg[2].getPrice();
                                calendar.add(Calendar.SECOND, + veg[2].getTime());
                            }
                            else {
                                System.out.println("Не хватает денег");
                                pleace = "err";
                            }
                            break;
                    }
                a = 1;
            }
        }

        }
        if (flower != null) {
            switch (pleace) {
                case ("A1"):
                    if (ferm[0].equals("A1")){
                    ferm[0] = flower;
                    mysqlTime.save("A1", calendar);}else
                        System.out.println("Клетка занята");
                    break;
                case ("A2"):
                    if (ferm[1].equals("A2")){
                        ferm[1] = flower;
                    mysqlTime.save("A2", calendar);}else
                        System.out.println("Клетка занята");
                    break;
                case ("A3"):
                    if (ferm[2].equals("A3")){
                        ferm[2] = flower;
                    mysqlTime.save("A3", calendar);}else
                        System.out.println("Клетка занята");
                    break;
                case ("A4"):
                    if (ferm[3].equals("A4")){
                        ferm[3] = flower;
                    mysqlTime.save("A4", calendar);}else
                        System.out.println("Клетка занята");
                    break;
                case ("B1"):
                    if (ferm[4].equals("B1")){
                        ferm[4] = flower;
                    mysqlTime.save("B1", calendar);}else
                        System.out.println("Клетка занята");
                    break;
                case ("B2"):
                    if (ferm[5].equals("B2")){
                        ferm[5] = flower;
                    mysqlTime.save("B2", calendar);}else
                        System.out.println("Клетка занята");
                    break;
                case ("B3"):
                    if (ferm[6].equals("B3")){
                        ferm[6] = flower;
                    mysqlTime.save("B3", calendar);}else
                        System.out.println("Клетка занята");
                    break;
                case ("B4"):
                    if (ferm[7].equals("B4")){
                        ferm[7] = flower;
                    mysqlTime.save("B4", calendar);}else
                        System.out.println("Клетка занята");
                    break;
            }
        }
        ferm();
    }
    public static void harvesting(){
        MysqlTime mysqlTime = new MysqlTime();
        GregorianCalendar calendar = new GregorianCalendar();
        System.out.println("введите клетку для растения");
        String pleace = sc.nextLine();
        switch (pleace){
            case ("A1"):
                if (calendar.getTime().after(mysqlTime.get("A1").getTime())){
                    System.out.println(mysqlTime.get("A1").getTime());
                switch (ferm[0]){
                    case ("cucumber"):
                        ferm[0] = "A1";
                        money = money + veg[0].getFullPrice();
                        break;
                    case ("tomato"):
                        ferm[0] = "A1";
                        money = money + veg[1].getFullPrice();
                        break;
                    case ("eggplant"):
                        ferm[0] = "A1";
                        money = money + veg[2].getFullPrice();
                        break;
                }

                    mysqlTime.delete("A1");}else
                System.out.println("Растение не созрело");
                break;
            case ("A2"):
                if (calendar.after(mysqlTime.get("A2"))){
                switch (ferm[1]){
                    case ("cucumber"):
                        ferm[1] = "A2";
                        money = money + veg[0].getFullPrice();
                        break;
                    case ("tomato"):
                        ferm[1] = "A2";
                        money = money + veg[1].getFullPrice();
                        break;
                    case ("eggplant"):
                        ferm[1] = "A2";
                        money = money + veg[2].getFullPrice();
                        break;
                }
                    mysqlTime.delete("A2");}else
                    System.out.println("Растение не созрело");
                break;
            case ("A3"):
                if (calendar.after(mysqlTime.get("A3"))){
                switch (ferm[2]){
                    case ("cucumber"):
                        ferm[2] = "A3";
                        money = money + veg[0].getFullPrice();
                        break;
                    case ("tomato"):
                        ferm[2] = "A3";
                        money = money + veg[1].getFullPrice();
                        break;
                    case ("eggplant"):
                        ferm[2] = "A3";
                        money = money + veg[2].getFullPrice();
                        break;
                }
                mysqlTime.delete("A3");}else
                System.out.println("Растение не созрело");
                break;
            case ("A4"):
                if (calendar.after(mysqlTime.get("A4"))){
                switch (ferm[3]){
                    case ("cucumber"):
                        ferm[3] = "A4";
                        money = money + veg[0].getFullPrice();
                        break;
                    case ("tomato"):
                        ferm[3] = "A4";
                        money = money + veg[1].getFullPrice();
                        break;
                    case ("eggplant"):
                        ferm[3] = "A4";
                        money = money + veg[2].getFullPrice();
                        break;
                }
                    mysqlTime.delete("A4");}else
                    System.out.println("Растение не созрело");
                    break;
            case ("B1"):
                if (calendar.after(mysqlTime.get("B1"))){
                switch (ferm[4]){
                    case ("cucumber"):
                        ferm[4] = "B1";
                        money = money + veg[0].getFullPrice();
                        break;
                    case ("tomato"):
                        ferm[4] = "B1";
                        money = money + veg[1].getFullPrice();
                        break;
                    case ("eggplant"):
                        ferm[4] = "B1";
                        money = money + veg[2].getFullPrice();
                        break;
                }
                    mysqlTime.delete("B1");}else
                    System.out.println("Растение не созрело");
                break;
            case ("B2"):
                if (calendar.after(mysqlTime.get("B2"))){
                switch (ferm[5]){
                    case ("cucumber"):
                        ferm[5] = "B2";
                        money = money + veg[0].getFullPrice();
                        break;
                    case ("tomato"):
                        ferm[5] = "B2";
                        money = money + veg[1].getFullPrice();
                        break;
                    case ("eggplant"):
                        ferm[5] = "B2";
                        money = money + veg[2].getFullPrice();
                        break;
                }
                    mysqlTime.delete("B2");}else
                    System.out.println("Растение не созрело");
                break;
            case ("B3"):
                if (calendar.after(mysqlTime.get("B3"))){
                switch (ferm[6]){
                    case ("cucumber"):
                        ferm[6] = "B3";
                        money = money + veg[0].getFullPrice();
                        break;
                    case ("tomato"):
                        ferm[6] = "B3";
                        money = money + veg[1].getFullPrice();
                        break;
                    case ("eggplant"):
                        ferm[6] = "B3";
                        money = money + veg[2].getFullPrice();
                        break;
                }
                    mysqlTime.delete("B3");}else
                    System.out.println("Растение не созрело");
                break;
            case ("B4"):
                if (calendar.after(mysqlTime.get("B4"))){
                switch (ferm[7]){
                    case ("cucumber"):
                        ferm[7] = "B4";
                        money = money + veg[0].getFullPrice();
                        break;
                    case ("tomato"):
                        ferm[7] = "B4";
                        money = money + veg[1].getFullPrice();
                        break;
                    case ("eggplant"):
                        ferm[7] = "B4";
                        money = money + veg[2].getFullPrice();
                        break;
                }
                    mysqlTime.delete("B4");}else
                    System.out.println("Растение не созрело");
                break;
        }
        ferm();
    }
}
