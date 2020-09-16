public class TimeStamp {
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        counter(Long.MAX_VALUE);
        long stop = System.currentTimeMillis();
        System.out.println((stop-start)+" ms");
    }
    
    public static void counter(long x){
        System.out.println("Loop Started");
        for (long i = 0; i < x;i++);
        System.out.println("Loop Ended");
    }
    
}