package kz.aitu.oop.assignment4;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Db my_database = new Db();
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter username:");
        String username = scan.nextLine();
        my_database.connect("jdbc:postgresql://localhost:5432/postgres", "postgres", "password");
        while(true){
            System.out.println("1 - Write random text");
            System.out.println("2 - Write concrete text");
            System.out.println("3 - Record table");
            System.out.println("4 - Add new text");
            System.out.println("5 - Exit");
            String option = scan.nextLine();
            if(option.charAt(0) == '5'){
                System.out.println("Goodbye!");
                break;
            }
            else if(option.charAt(0) == '1'){
                my_database.write_random_text(username, scan);
            }
            else if(option.charAt(0) == '2'){
                System.out.println("What text you want to write?");
                int x = Integer.parseInt(scan.nextLine());
                my_database.write_concrete_text(username, x, scan);
            }
            else if(option.charAt(0) == '4'){
                System.out.println("Requerments for text: at least 100 symbols");
                String new_text = scan.nextLine();
                my_database.add_text(new_text);
            }
            else if(option.charAt(0) == '3'){
                my_database.records();
            }
        }
        scan.close();
        my_database.close_connection();
    }
}