public class Dice {

    private static Dice dice;
    private Dice(){

    }

    public static Dice getDice(){
        if(dice == null){
            dice = new Dice();
        }
        return dice;
    }

    public int roll(){
        int number =(int) (Math.random() * 6) + 1;
        return number;
    }


}
