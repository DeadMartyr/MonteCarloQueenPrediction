import java.util.ArrayList;
import java.util.Random;

public class Main {

    private static Random rand;
    private static int[] posQueens;

    public static void main(String[] args) {

        int numOfQueens = 12;

        init(numOfQueens);
//        nQueens(0,numOfQueens); //DO NOT UNCOMMENT THIS, THIS WILL ATTEMPT TO TRAVERSE EVERY PROMISING NODE, CAN BE SLOW FOR LARGE VALUES OF N

        ArrayList<Integer> results = new ArrayList<>();
        int cycles = 300;

        for(int i=0; i<cycles; i++){
            results.add(nQueensEstimate(numOfQueens));
        }

        long temp = 0;

        for(Integer a: results){
            temp += a;
        }

        double average = ((double) temp)/(results.size());

        System.out.println("The computed number of 'nodes' checked by backtracking for the " + numOfQueens + "-Queens Problem is estimated with " + cycles + " cycles as: " + average + " nodes");

    }

    /**
     * nQueens, Utilizing "promising" but only going through 1 promising child at each level,
     * this is not exact and will return different numbers each iteration.
     * <p>
     * This is intended to be run multiple times to be averaged and get an idea of what it actually would be,
     * as for large sizes of n, you cannot compute every single permutation, even with "pruning"
     *
     * @param n - max dimension of board
     */
    private static int nQueensEstimate(int n){
        int k = 0;

        int numnodes = 1, //this is the result
                m = 1, //this is the number of promising children at any given step
                mprod = 1;

        ArrayList<Integer> promisingAtK = new ArrayList<>();

        while (m != 0) {//NO MORE PROMISING CHILDREN
            int t = n;//Total possible "children" is n, though not all promising
            mprod *= m; //Multiply value by m

            for (int i = 0; i < n; i++) { //find all promising children, add indexes to list to select from
                if (isPromising(k, i)) {
                    promisingAtK.add(i);
                }
            }

            m = promisingAtK.toArray().length;

            if (m != 0 && k<n) {
                //Create index from 0 to the number of promising children (not including that number, "<" not "<=")
                int randomNum = rand.nextInt(promisingAtK.size());
                //Get array of promising children of current v, set v to the random index of that array
                posQueens[k] = promisingAtK.get(randomNum);
                k++;
            }
            promisingAtK.clear();

            numnodes += mprod * t; //Add value by mprod times t
        }
        return numnodes;
    }

    /**
     * nQueens recursively call, Utilizing "promising" but computing ENTIRE Tree,
     * not for sizes of n that are excessively large
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

    /**
     * Initialize global variables
     *
     * @param n - max dimension of board
     */
    private static void init(int n){
        posQueens = new int[n];
        rand = new Random();
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
        for(int x=0; x<posQueens.length; x++){
            System.out.print(String.format(format, "---"));
        }
    }
}
