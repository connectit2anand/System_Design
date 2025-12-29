import java.lang.reflect.Constructor;

public class Singleton {

    private static Singleton instance;

    private Singleton(){

    }

    // First way

//    public static Singleton  getInstance(){
//        if(instance == null){
//            instance=  new Singleton();
//        }
//        return instance;
//    }


    // Second way using synchronized (Thread safe)


//    public static synchronized Singleton getInstance(){
//        if(instance == null){
//            instance = new Singleton();
//        }
//        return instance;
//    }


    public static Singleton getInstance(){
        if(instance == null){
            synchronized (Singleton.class){
                if(instance == null){
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }
}
