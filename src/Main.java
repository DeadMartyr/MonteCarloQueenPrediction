import java.util.Arrays;
import java.util.Random;

public class Main {

    private static Random rand = new Random();
    private static int[] posQueens;

    public static void main(String[] args) {
        //This is unneeded as the Random object is seeded in its constructor
        //but I can use this to repeat sequences with pre-chosen seeds
        //rand.setSeed(System.currentTimeMillis());

        System.out.println("Random Number: " + rand.nextInt(5));

        int NumOfQueens = 4;

        init(NumOfQueens);
        nQueens(0,NumOfQueens);

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

    /**
     * Initialize global variables
     *
     * @param n - max dimension of board
     */
    private static void init(int n){
        posQueens = new int[n];
    }

    /**
     * nQueens recursively call
     *
     * @param k - current loop
     * @param n - max dimension of board
     */
    private static void nQueens(int k, int n){
        for(int i=0; i<n; i++){
            if(isPromising(k,i)){
                posQueens[k]=i;
                if(k==n-1){
                    printPositions();
                    displayPositions();
                    System.out.println();
                    return;
                }
                else{
                    nQueens(k+1,n);
                }
            }
        }
    }

    /**
     * Checks if a given position at index "i" of the k-th row is worth continuing
     * <p>
     * NO OTHER QUEENS ON SAME COLUMN:
     * If on row "k" a Queen is on column "i", and there exists another row "j" in which
     * that row's queen is also on column "i". Then this permutation is NOT promising.
     * <p>
     * NO OTHER QUEENS ON DIAGONALS:
     * If the column index of a Queen on any row "j" is the same distance from index "i"
     * as the difference between the indexes "j" and "k". Then this permutation is NOT promising.
     * <br>(i.e. if the queen on row 4 is on column 6, then on row 3, there cannot be a queen on
     * column 5 or 7. All other positions irrelevant.)
     *
     * @param k - The row being filled
     * @param i - A possible index to fill row "k"
     * @return true if worth continuing, false if it would waste computing time
     */
    private static boolean isPromising(int k, int i){
        for(int j=0;j<k;j++){
            if((posQueens[j]==i) || (Math.abs(posQueens[j]-i) == Math.abs(j-k))){
                return false;
            }
        }
        //no conflicts found
        return true;
    }

    private static void printPositions(){
        for(int a=0; a<posQueens.length; a++){
            System.out.print("Row " + a + "'s Queen is on " + posQueens[a] + "\t");
        }
        System.out.println();
    }

    private static void displayPositions(){
        String format = "| %3s |";
        for(int y=0; y<posQueens.length; y++){

            for(int x=0; x<posQueens.length; x++){
                System.out.print(String.format(format, "---"));
            }
            System.out.print("\n");

            for(int x=0; x<posQueens.length; x++){

                String value;
                if(posQueens[y]==x){
                    value = "" + posQueens[y];
                }
                else{
                    value = "";
                }
                System.out.print(String.format(format,value));
            }
            System.out.print("\n");
        }
    }
}