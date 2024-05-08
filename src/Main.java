import java.util.Random;

public class Main {

    private static Random rand = new Random();

    public static void main(String[] args) {
        //This is unneeded as the Random object is seeded in its constructor
        //but I can use this to repeat sequences with pre-chosen seeds
        //rand.setSeed(System.currentTimeMillis());

        System.out.println("Random Number: " + rand.nextInt(5));
    }

//    private int estimate(){
//        int v = startingNode;//REPLACE
//        int numnodes = 1,
//                m = 1,
//                mprod = 1;
//
//        while (m != 0){//NO MORE PROMISING CHILDREN
//            int t = v.getNumTotalChildren();
//            mprod *= m; //Multiply value by m
//            numnodes += mprod * t; //Add value by mprod times t
//            m = v.getNumPromisingChildren();
//
//            if(m != 0){
//                //Create index from 0 to the number of promising children minus 1
//                int randomNum = rand.nextInt(v.getNumTotalChildren());
//                //Get array of promising children of current v, set v to the random index of that array
//                v = v.getArrayPromisingChildren()[randomNum];
//            }
//        }
//        return numnodes;
//    }
}