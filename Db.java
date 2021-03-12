package kz.aitu.oop.Project; //package name
import java.util.Random; //importing Random library
import java.util.Scanner; //importing Scanner library
import java.sql.*; //importing SQL library


public class Db implements BaseConnect{ //class Db has interface BaseConnect
    Db(){
    }
    Connection con; //connection
    Statement stmt; //statement
    ResultSet rs; //results

    @Override
    public void connect(String url, String user, String password){ //connecting with Database
        try{
            Class.forName("org.postgresql.Driver"); //using JDBC driver
            con = DriverManager.getConnection(url, user, password); //using special class DriverManager for Driver
        }
        catch(Exception e){
            System.out.println(e); //catching errors
        }
    }

    @Override
    public void execSQL(String sql){ //using ExecSQL method to Execute SQL query
        try{
            Class.forName("org.postgresql.Driver"); //using JDBC driver
            stmt = con.createStatement(); //creating Statement
            rs = stmt.executeQuery(sql); //executing SQL query
            //while(rs.next()){
            //    System.out.println(rs);
            //}
        }
        
        catch(Exception e){ //catching errors
            System.out.println(e);
        }
        
    }

    public int text_cnt(){ //declaring text_cnt method, which is Integer
        int y = 0;
        String text_cnt_str;
        try{
            Class.forName("org.postgresql.Driver"); //using JDBC Driver
            stmt = con.createStatement(); //creating Statement
            rs = stmt.executeQuery("select max(text_id) from texts"); //executing SQL query to count number of texts
            while(rs.next()){ //declaring the loop
                text_cnt_str = rs.getString("max"); //using definition getString
                y = Integer.parseInt(text_cnt_str); //using method parseInt to convert String to Integer
            }
            
            //while(rs.next()){
            //    System.out.println(rs);
            //}
        }
        
        catch(Exception e){ //catching errors
            System.out.println(e);
        }
        return y; //returning y value
    }

    public Object write_concrete_text(String username, int text, Scanner scan){ //declaring object with parameters
        String text_cnt_str;
        String txt = "";
        try{
            Class.forName("org.postgresql.Driver"); //using JDBC Driver
            stmt = con.createStatement(); //creating Statement
            rs = stmt.executeQuery("select text from texts where text_id = " + String.valueOf(text) +  ";"); //executing SQL query to select defined text by id
            while(rs.next()){ //declaring loop
                text_cnt_str = rs.getString("text"); //using definition getString
                txt += text_cnt_str;
            }
            System.out.println(txt); //output the current text
            Time x = new Time();
            x.start(); //starting timer
            int mistakecnt = 0;
            String input_text = scan.nextLine(); //Inputting data into the console by User
            double time = x.finish(); //finish the timer
            double speed = (double)txt.length()/time * 60.0; //equation for calculating Speed
            //input_text = scan.nextLine();
            //System.out.println(input_text);
            if(input_text.length() < txt.length()){ //checking the length of typed text (if less)
                System.out.println("You wrote less symbols than required:("); //printing the message
                return null; //returning nothing
            }
            else if(input_text.length() > txt.length()){ //checking the length of typed text (if more)
                System.out.println("You wrote more symbols than required:("); //printing th message
                return null; //returning nothing
            }
            for(int i = 0; i < txt.length(); i++){ //declaring the additional loop for checking third condition
                if(input_text.charAt(i) != txt.charAt(i)) //third condition (if texts aren't similar)
                    mistakecnt++;
            }
            //double time = x.finish();
            //double speed = (double)txt.length()/time * 60.0;
            final char dm = (char) 34; //the dash
            System.out.println("Your time for text " + String.valueOf(text) + " is " + String.valueOf(time) + " (" + String.valueOf(mistakecnt) + " mistakes)"); //typing final message, if user has some number of mistakes
            if(mistakecnt == 0){ //if it's not any mistakes there...
                System.out.println("Congratulations, you have 0 errors, so your result will be recorded in the highscore table. "); //printing the Congratulating message
                String KEKWquery = "INSERT INTO records(username, text_id," + dm + "time" + dm + ","+ " speed)VALUES ('" + username+"', "+ String.valueOf(text) +", " + String.valueOf(time) +"," + String.valueOf(speed) +");"; //adding users records to Database by using SQL query
                //System.out.println(KEKWquery);
                stmt.executeUpdate(KEKWquery); //updating the Database
            }
            else{
                System.out.println("Your run wasn't perfect, so your score wouldn't be added to highscore table"); //if user has mistakes, print this message (not adding to Database)
            }
            //while(rs.next()){
            //    System.out.println(rs);
            //}
        }
        
        catch(Exception e){ //catching errors
            System.out.println(e);
        }
        return null;
    }

    public void write_random_text(String username, Scanner scan){ //if the user is going to type random text from Database
        Random rand = new Random();
        int x  = rand.nextInt(this.text_cnt()) + 1; //x is the random number from 1 to amount of texts+1
        write_concrete_text(username, x, scan); //user inputs the username and number
        //System.out.printf("%d%d", x, y);
    }

    public Object add_text(String new_text){ //adding text to the Database
        if(new_text.length() < 100){ //checking the length of the new text
            System.out.println("Text doesn't meat the requirments therefore won't be added"); //if text is less than 100 symbols, it wouldn't be added to Database
            return null;
        }
        try{
            String KEKWquery = "INSERT INTO texts(text_id, text)VALUES (" + String.valueOf(this.text_cnt() + 1) + ", '" + new_text +"');"; //adding the text to Database
            stmt.executeUpdate(KEKWquery); //updating the Database
        }
        catch(Exception e){ //catching errors
            System.out.println(e);
        }
        System.out.println("Text successfully added"); //"successfull" message, if adding text was completed
        return null;
        
    }

    public void records(){ //records method to view the records table
        try{
            Class.forName("org.postgresql.Driver"); //using JDBC Driver
            stmt = con.createStatement(); //creating Statement
            rs = stmt.executeQuery("Select * from records order by speed DESC"); //executing SQL query to show the table
            while(rs.next()){ //declaring the loop
                String username = rs.getString("username"); //declaring the getString method for username
                int text_id = rs.getInt("text_id"); //declaring the id of text
                double time = rs.getDouble("time"); //showing the results of time spend
                double speed = rs.getDouble("speed"); //showing results of calculated speed
                System.out.println("Username: "+ username + " Text id: " + text_id + " Time: " + time + " Speed: " + speed); //printing the results of User
            }
        }
        
        catch(Exception e){ //catching errors
            System.out.println(e);
        }
    }
    
    public void close_connection(){ //declaring method to Closing Connection with Database
            if(stmt != null){ //first condition to close connection
                try{
                    stmt.close(); //closing
                }
                catch(SQLException e){ //catching errors
                    System.out.println(e);
                }
            } 
            if(rs != null){ //second condition to close connection
                try{
                    rs.close(); //closing
                }
                catch(SQLException e){ //catching errors
                    System.out.println(e);
                }
            }
            if(con != null){ //third condition to close connection
                try{
                    con.close(); //closing
                }
                catch(SQLException e){ //catching errors
                    System.out.println(e);
                }
            }
    }
}