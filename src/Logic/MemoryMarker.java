/* Fongoh Martin Tayong.
 * fongohmartin@gmail.com
 * University of Buea, Cameroon.
*/
package Logic;

import java.io.IOException;
import java.util.Scanner;
public class MemoryMarker 
{
    private int[] userInput;
    public MemoryMarker(int size)
    {
        userInput = new int[size];
    }
    
    public int[] getArrayOfUserInput()
    {
        return userInput;
    }
    
    public int[] markQuestions(int[] correctAns, int[] randNum)
    {
        int[] correctWrong = new int[2];
        for(int i = 0 ; i < correctAns.length ; i++ )
        {
            if(correctAns[i] == randNum[i])
                correctWrong[0]++;
            else
                correctWrong[1]++;
        }
        return correctWrong;
    }
    
    
}
