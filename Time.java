package kz.aitu.oop.Project; //package name

public class Time { //declaring the class Time
    private long start_time; //Time in miliseconds where we start counting
    private long finish_time; //Time in miliseconds where we finish counting
    public void start(){
        start_time = System.currentTimeMillis();
    } //calculating the real time by miliseconds
    public double finish(){ //finishing timer
        finish_time = System.currentTimeMillis();
        return (double)(finish_time - start_time)/1000; //returning time value calculated in seconds
    }
    public long time_in_miliseconds(){ //typing time in miliseconds
        long time_in_mil = finish_time - start_time; //to find typing time, we need to remove started time from finished time in miliseconds
        return time_in_mil; //returning the value
    }
}