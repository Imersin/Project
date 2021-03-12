package kz.aitu.oop.Project; //package name

interface BaseConnect { //declaration of interface
    public void connect(String url, String user, String password); //connection to DBMS
    public void execSQL(String sql); //Executing SQL query
}