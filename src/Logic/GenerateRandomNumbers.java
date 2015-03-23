/* Fongoh Martin Tayong.
 * fongohmartin@gmail.com
 * University of Buea, Cameroon.
*/
/*
 * This class will be incharge of generating n random numbers as specified by the user.
 *The n random numbers are going to be stored in an array and displayed to the user for a specifiec amount of time 
 *so that the user can memorise it and input the correct numbers in the correct sequence when asked.
*/

package Logic;
import java.util.Random;
public class GenerateRandomNumbers 
{
    private int[] randomNumbers;
    public GenerateRandomNumbers(int size, int STAGE)
    {
        randomNumbers = new int[size];
            for(int i = 0 ; i < size ; i++)
            {
                Random myran = new Random();
                randomNumbers[i] = myran.nextInt(STAGE) + 1;
            }
    }
    
    public int[] getArrayOfRandomNumbers()
    {
        return randomNumbers;
    }
    
    @SuppressWarnings("SleepWhileInLoop")
    public void displayRandomNumbers(int level) throws InterruptedException
    {
        for(int i=0; i<randomNumbers.length; i++)
        {
            Thread.sleep(500);
            System.out.print(randomNumbers[i] + " ");
        } 
        
        Thread.sleep(5000*level);
        for(int i=0; i<level*3 + 1; i++)
        {
            System.out.print("\b");
        }
    }
    
}
