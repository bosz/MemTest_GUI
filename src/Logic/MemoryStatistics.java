/* Fongoh Martin Tayong.
 * fongohmartin@gmail.com
 * University of Buea, Cameroon.
*/
package Logic;
public class MemoryStatistics 
{
    private int points;
    /* there will be two cells for each of the 3 question int array variables bellow. */
    private int[] easyQuestions;        /*  the first cells will hold the number of questions(easy/normal/difficult)*/
    private int[] normalQuestions;      /*  that have been answered wrongly while the second cell, cell 1 will hold*/
    private int [] difficultQuestions;  /*  the number of questions that have been answered correctly */    
   
    public MemoryStatistics()
    {
        /*Initialise the scores and number of questions answered.*/
        easyQuestions = new int[2];
        normalQuestions = new int[2];
        difficultQuestions = new int[2];
        this.points = 0;
    }
    
    public void addQuestionsAndPoints(int[] correctWrong, int stage, int stageLevel)
    {
        switch(stage)
        {
            case 3:
                addEasyQuestionsCorrect(correctWrong[0]);
                addEasyQuestionsWrong(correctWrong[1]);
                break;
            case 5:
                addNornalQuestionsCorrect(correctWrong[0]);
                addNornalQuestionsWrong(correctWrong[1]);
                break;
            case 7:
                addDifficultQuestionsCorrect(correctWrong[0]);
                addDifficultQuestionsWrong(correctWrong[1]);
                break;
            default:
                System.exit(stage);
        }
        increasePoints(correctWrong[0]*stageLevel);
    }
    
    /*--------set-----------EASY QUESTIONS SECTION ------------------------*/
    private void addEasyQuestionsCorrect(int numberCorrect)
    {
        easyQuestions[0] += numberCorrect;
    }
    private void addEasyQuestionsWrong(int numberWrong)
    {
        easyQuestions[1] += numberWrong;
    }
    
    /*--------set-----------NORMAL QUESTIONS SECTION ------------------------*/
    private void addNornalQuestionsCorrect(int numberCorrect)
    {
        normalQuestions[0] += numberCorrect;
    }
    private void addNornalQuestionsWrong(int numberWrong)
    {
        normalQuestions[1] += numberWrong;
    }
    
    
    /*--------set-----------DIFFICULT QUESTIONS SECTION ------------------------*/
    private void addDifficultQuestionsCorrect(int numberCorrect)
    {
        difficultQuestions[0] += numberCorrect;
    }
    private void addDifficultQuestionsWrong(int numberWrong)
    {
        difficultQuestions[1] += numberWrong;
    }
    
    private void increasePoints(int plusPoints)
    {
        points +=plusPoints;
    }
    
    public int getPoints()
    {
        return points;
    }
    
    /*methods to get the easy questions answered correctly and wrongly
     *These are going to be declared public so that other objects of different classes can call this method
     *to know the number of correct and wrong answers for the various categories.*/
    
    /*-------get--------------EASY QUESTIONS SECTION ------------------------*/
    public int getEasyQuestionsCorrect()
    {
        return easyQuestions[0];
    }
    public int getEasyQuestionsWrong()
    {
        return easyQuestions[1];
    }
    
    /*-------get--------------NORMAL QUESTIONS SECTION ------------------------*/
    public int getNormalQuestionsCorrect()
    {
        return normalQuestions[0];
    }
    public int getNormalQuestionsWrong()
    {
        return normalQuestions[1];
    }
    
    /*-------get--------------DIFFICULT QUESTIONS SECTION ------------------------*/
    public int getDifficultQuestionsCorrect()
    {
        return difficultQuestions[0];
    }
    public int getDifficultQuestionsWrong()
    {
        return difficultQuestions[1];
    }
    
    public void generateStatistics()
    {
        System.out.println();
        System.out.println("Easy");
        System.out.println("\tCorrect ------> " + getEasyQuestionsCorrect());
        System.out.println("\tWrong   ------> " + getEasyQuestionsWrong());
        
        System.out.println("Normal");
        System.out.println("\tCorrect ------> " + getNormalQuestionsCorrect());
        System.out.println("\tWrong   ------> " + getNormalQuestionsWrong());
        
        System.out.println("Difficult");
        System.out.println("\tCorrect ------> " + getDifficultQuestionsCorrect());
        System.out.println("\tWrong   ------> " + getDifficultQuestionsWrong());
        System.out.println();
    }
    
}
