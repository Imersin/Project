package kz.aitu.oop.Project; //package name
import java.util.Scanner; //importing Scanner library


public class Main {
    public static void main(String[] args) {
        Db my_database = new Db();
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter username:"); //printing this on console
        String username = scan.nextLine(); //input the username
        my_database.connect("jdbc:postgresql://localhost:5432/postgres", "postgres", "password"); //connecting to the Database
        while(true){ //declaring the "infinite" loop (menu)
            System.out.println("1 - Write random text"); //first button of menu to write random text from Database
            System.out.println("2 - Write concrete text"); //second button of menu to write concrete text from Database
            System.out.println("3 - Record table"); //third button of menu to view the record table
            System.out.println("4 - Add new text"); //fourth button of menu to add user's new text to Database
            System.out.println("5 - Exit"); //fifth button of menu to close connection/ close loop/ terminate the program
            String option = scan.nextLine();
            if(option.charAt(0) == '5'){ //if User choose 5
                System.out.println("Goodbye!"); //print the farewell message
                break; //close the loop
            }
            else if(option.charAt(0) == '1'){ //if user choose 1
                my_database.write_random_text(username, scan); //output the random text from Database
            }
            else if(option.charAt(0) == '2'){ //if user choose 2
                System.out.println("What text you want to write?"); //printing the question
                int x = Integer.parseInt(scan.nextLine()); //user writes the number of text
                my_database.write_concrete_text(username, x, scan); //output the chosen text from Database
            }
            else if(option.charAt(0) == '4'){ //if user choose 4
                System.out.println("Requerments for text: at least 100 symbols"); //printing the message for requirements of the new adding text
                String new_text = scan.nextLine(); //user writes the text
                my_database.add_text(new_text); //adding written text to Database
            }
            else if(option.charAt(0) == '3'){ //if user choose 3
                my_database.records(); //printing the rcords on the console
            }
        }
        scan.close(); //user exits
        my_database.close_connection(); //closing connection with Database
    }
}