package kz.aitu.oop.assignment4;

interface BaseConnect {
    public void connect(String url, String user, String password);
    public void execSQL(String sql);
}