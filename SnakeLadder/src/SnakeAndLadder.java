import Exceptions.InvalidInputException;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

public class SnakeAndLadder {
    private static Map<Integer,Integer> snakeMap = new HashMap<>();
    private static Map<Integer,Integer> ladderMap = new HashMap<>();

    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Scanner sc = new Scanner(System.in);

        snakeMap.put(40,23);
        snakeMap.put(50,35);
        snakeMap.put(62,24);
        snakeMap.put(80,60);

        ladderMap.put(5,39);
        ladderMap.put(30,98);
        ladderMap.put(60,87);
        ladderMap.put(39,88);
        ladderMap.put(12,75);
        ladderMap.put(58,97);
        //input for ladder
//        List<Ladder> ladders = inputLadders();
        //input for snake
//        List<Snake> snakes = inputSnakes();
        //input for players
//        List<Player> players = inputPlayers();
         Player player1 = new Player("anand");
         Player player2 = new Player("manish");
         Player player3 = new Player("Amit");
         List<Player> players= new ArrayList<>();;
         players.add(player2);
         players.add(player3);
         players.add(player1);

         //Singleton Just For Learning
        Singleton singleton = Singleton.getInstance();
        //To Break The Singleton Class
        Constructor<Singleton> s = Singleton.class.getDeclaredConstructor();
        s.setAccessible(true);
        Singleton c2 = s.newInstance();




        System.out.println("******************** Game Start *********************************");
        final int FINAL_POS = 100;
        int position = 1;
        int numberOfPlayersPlaying = players.size();
        Dice dice = Dice.getDice();
        while(position <= players.size() - 1){
            int i = 0;
            while(i < players.size() && numberOfPlayersPlaying > 1){

                Player player = players.get(i);
                if(!player.isPlaying()){
                    i ++;
                    continue;
                }
                int currentPos = player.getPosition();
                System.out.println("Current positon for the player " + player.getName() + " is " + currentPos );
                int diceValue = dice.roll();
                System.out.println("Dice value is " + diceValue + " for player " + player.getName());

                int finalPos = currentPos + diceValue;
                if(finalPos > FINAL_POS){
                    System.out.println("Final position of the player is " + finalPos + " which exceeds "+ FINAL_POS);
                    i ++;
                    continue;
                }

                if(diceValue == 6){
                    System.out.println("Dice value is 6 so, "+ player.getName() + "  get one more chance to roll the die");
                    players.get(i).setPosition(finalPos);
                    continue;
                }

                if(finalPos == FINAL_POS){
                    System.out.println("Winner of the game is " + player.getName() + " and the position of the player is " + position);
                    players.get(i).setPlaying(false);
                    numberOfPlayersPlaying --;
                    players.get(i).setRank(position ++);
                    i ++;
                    continue;
                }



                if(ladderMap.containsKey(finalPos)){
                    finalPos = ladderMap.get(finalPos);
                    System.out.println("Final position of the player " + player.getName() +  " after climbing the ladder is " + finalPos);
                }


                if(snakeMap.containsKey(finalPos)){
                    finalPos = snakeMap.get(finalPos);
                    System.out.println("Final position of the player " + player.getName() +  " after snake bite is " + finalPos);
                    if(ladderMap.containsKey(finalPos)){
                        finalPos = ladderMap.get(finalPos);
                    }
                }

                player.setPosition(finalPos);
                System.out.println("Current position of the player " + player.getName() + " is " + finalPos);
                i ++;
            }
        }
    }

    private static List<Player> inputPlayers(){
        try{
            Scanner sc = new Scanner(System.in);
            List<Player> players = new ArrayList<>();
            System.out.println("Please enter the number of players");
            int numberOfPlayers = sc.nextInt();
            for(int i = 0; i < numberOfPlayers; i ++){
                System.out.println("Please enter the name of the player number " + i + 1);
                String playerName = sc.next();
                Player player = new Player(playerName);
                players.add(player);
            }
            return players;
        } catch (Exception e){
            throw new InvalidInputException("Invalid input");
        }
    }

    private static List<Snake> inputSnakes(){
        Scanner sc = new Scanner(System.in);
        List<Snake> snakes = new ArrayList<>();
        System.out.println("Please enter the number of snakes");
        int numberOfSnakes = sc.nextInt();
        for(int i = 0; i < numberOfSnakes; i ++){
            System.out.println("Please enter the starting and end point of the snake " + i + 1);
            int startPos = sc.nextInt();
            int endPos = sc.nextInt();
            snakeMap.put(startPos,endPos);
            Snake snake = new Snake(startPos,endPos);
            snakes.add(snake);
        }
        return snakes;
    }

    private static List<Ladder> inputLadders(){
        Scanner sc = new Scanner(System.in);
        List<Ladder> ladders = new ArrayList<>();
        System.out.println("Please enter the number of ladders");
        int numberOfLadders = sc.nextInt();
        for(int i = 0; i < numberOfLadders; i ++){
            System.out.println("Please enter the starting and end point of the ladder " + i + 1);
            int startPos = sc.nextInt();
            int endPos = sc.nextInt();
            ladderMap.put(startPos,endPos);
            Ladder ladder = new Ladder(startPos,endPos);
            ladders.add(ladder);
        }
        return ladders;
    }
}

/*
1] Number of player
2] Map for snake positions
3] Map ladder positions
4] Dice
5] Validation + logics for game
 */