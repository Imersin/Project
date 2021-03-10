package kz.aitu.oop.assignment4;
import java.util.Random;
import java.util.Scanner;
import java.sql.*;

public class Db implements BaseConnect{
    Db(){
    }
    Connection con;
    Statement stmt;
    ResultSet rs;

    @Override
    public void connect(String url, String user, String password){
        try{
            Class.forName("org.postgresql.Driver");
            con = DriverManager.getConnection(url, user, password);
        }
        catch(Exception e){
            System.out.println(e);
        }
    }

    @Override
    public void execSQL(String sql){
        try{
            Class.forName("org.postgresql.Driver");
            stmt = con.createStatement(); 
            rs = stmt.executeQuery(sql);
            //while(rs.next()){
            //    System.out.println(rs);
            //}
        }
        
        catch(Exception e){
            System.out.println(e);
        }
        
    }

    public int text_cnt(){
        int y = 0;
        String text_cnt_str;
        try{
            Class.forName("org.postgresql.Driver");
            stmt = con.createStatement(); 
            rs = stmt.executeQuery("select max(text_id) from texts");
            while(rs.next()){
                text_cnt_str = rs.getString("max");
                y = Integer.parseInt(text_cnt_str);
            }
            
            //while(rs.next()){
            //    System.out.println(rs);
            //}
        }
        
        catch(Exception e){
            System.out.println(e);
        }
        return y;
    }

    public Object write_concrete_text(String username, int text, Scanner scan){
        String text_cnt_str;
        String txt = "";
        try{
            Class.forName("org.postgresql.Driver");
            stmt = con.createStatement(); 
            rs = stmt.executeQuery("select text from texts where text_id = " + String.valueOf(text) +  ";");
            while(rs.next()){
                text_cnt_str = rs.getString("text");
                txt += text_cnt_str;
            }
            System.out.println(txt);
            Time x = new Time();
            x.start();
            int mistakecnt = 0;
            String input_text = scan.nextLine();
            //input_text = scan.nextLine();
            //System.out.println(input_text);
            if(input_text.length() < txt.length()){
                System.out.println("You wrote less symbols than required:(");
                return null;
            }
            else if(input_text.length() > txt.length()){
                System.out.println("You wrote more symbols than required:(");
                return null;
            }
            for(int i = 0; i < txt.length(); i++){
                if(input_text.charAt(i) != txt.charAt(i))
                    mistakecnt++;
            }
            double time = x.finish();
            double speed = (double)txt.length()/time * 60.0;
            final char dm = (char) 34;
            System.out.println("Your time for text " + String.valueOf(text) + " is " + String.valueOf(time) + " (" + String.valueOf(mistakecnt) + " mistakes)");
            if(mistakecnt == 0){
                System.out.println("Congratulations, you have 0 errors, so your result will be recorded in the highscore table. ");
                String KEKWquery = "INSERT INTO records(username, text_id," + dm + "time" + dm + ","+ " speed)VALUES ('" + username+"', "+ String.valueOf(text) +", " + String.valueOf(time) +"," + String.valueOf(speed) +");";
                //System.out.println(KEKWquery);
                stmt.executeUpdate(KEKWquery);
            }
            else{
                System.out.println("Your run wasn't perfect, so your score wouldn't be added to highscore table");
            }
            //while(rs.next()){
            //    System.out.println(rs);
            //}
        }
        
        catch(Exception e){
            System.out.println(e);
        }
        return null;
    }

    public void write_random_text(String username, Scanner scan){
        Random rand = new Random();
        int x  = rand.nextInt(this.text_cnt()) + 1;
        write_concrete_text(username, x, scan);
        //System.out.printf("%d%d", x, y);
    }

    public Object add_text(String new_text){
        if(new_text.length() < 100){
            System.out.println("Text doesn't meat the requirments therefore won't be added");
            return null;
        }
        try{
            String KEKWquery = "INSERT INTO texts(text_id, text)VALUES (" + String.valueOf(this.text_cnt() + 1) + ", '" + new_text +"');";
            stmt.executeUpdate(KEKWquery);
        }
        catch(Exception e){
            System.out.println(e);
        }
        System.out.println("Text successfully added");
        return null;
        
    }

    public void records(){
        try{
            Class.forName("org.postgresql.Driver");
            stmt = con.createStatement();
            rs = stmt.executeQuery("Select * from records order by speed DESC");
            while(rs.next()){
                String username = rs.getString("username");
                int text_id = rs.getInt("text_id");
                double time = rs.getDouble("time");
                double speed = rs.getDouble("speed");
                System.out.println("Username: "+ username + " Text id: " + text_id + " Time: " + time + " Speed: " + speed);
            }
        }
        
        catch(Exception e){
            System.out.println(e);
        }
    }
    
    public void close_connection(){
            if(stmt != null){
                try{
                    stmt.close();
                }
                catch(SQLException e){
                    System.out.println(e);
                }
            } 
            if(rs != null){
                try{
                    rs.close();
                }
                catch(SQLException e){
                    System.out.println(e);
                }
            }
            if(con != null){
                try{
                    con.close();
                }
                catch(SQLException e){
                    System.out.println(e);
                }
            }
    }
}