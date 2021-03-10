package kz.aitu.oop.Project;

public class Time {
    private long start_time; //Time in miliseconds where we start counting
    private long finish_time;
    public void start(){
        start_time = System.currentTimeMillis();
    }
    public double finish(){
        finish_time = System.currentTimeMillis();
        return (double)(finish_time - start_time)/1000;
    }
    public long time_in_miliseconds(){
        long time_in_mil = finish_time - start_time;
        return time_in_mil;
    }
}