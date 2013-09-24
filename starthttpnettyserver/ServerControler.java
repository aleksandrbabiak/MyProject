package starthttpnettyserver;

public class ServerControler {
    
   private static int numberAllActive = 0;
   
   public static  void incrementConnection(){   
   numberAllActive++;
   }
   public static  void decrementConnection(){   
   --numberAllActive;
   }
    public static int getNumberAllActiveConnection() {
        return numberAllActive;
    }
}
