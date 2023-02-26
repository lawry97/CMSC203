/*
    Class: CMSC203 CRN 30376
    Program: Assignment #2
    Instructor: Grinberg, Grigoriy
    Summary of Description:	Build an application that will receive a guess and report if
    your guess is the random number that was generated.
    Your application will narrow down the choices according to your previous guesses
    and continue to prompt you to enter a guess until you guess correctly. Notice the user can guess at most 7 times.
    Due Date: 02/20/2023
    Integrity Pledge: I pledge that I have completed the programming assignment independently.
    I have not copied the code from a student or any source.

    Student Name: Lawrence Aryeh
*/



import java.util.*;
public class RandomNumberGuesser {


    public static void main(String[] args) {

        Scanner input = new Scanner(System.in); // input variable
        int targetNum, guessNum = 0; // targetNum to hold the correct number, guessNum to hold user's guess input
        int numOfGuess = 0; // to hold number of guesses
        int lowGuess =1, highGuess =100; // lowGuess to hold low previous guess and vice versa
        String userContinue; // to hold user's input on whether to continue playing or not



        System.out.println("This application generates a random integer between 0 and 100" +
                " and asks the user to guess repeatedly until they guess correctly"); //program's description

        targetNum = RNG.rand(); // get a randomized number and assign it to targetNum


        do { // repeat until the user does not want to continue the game

                RNG.resetCount(); // reset count
                numOfGuess = RNG.getCount(); // get number of guess

            do { // repeat until the guess number is correct/equal to targetNum

                checkTries(numOfGuess, RNG.getMaxGuessCount()); // check if number of tries is exceeded before proceeding

                if (numOfGuess == 0) { // if first time
                    System.out.println("Enter your first guess: ");
                    guessNum = input.nextInt();


                    if (guessNum == targetNum) { // if guess is correct let the user know and quit the while loop

                        System.out.println("Congratulations, you guessed correctly");
                        continue;


                    } else if (guessNum < targetNum) { //increment the count, let user know, assign guess to low guess
                        RNG.incrementCount();
                        lowGuess = guessNum;
                        System.out.println("Your guess is too low");
                    } else if (guessNum > targetNum) { // increment the count, let user know, assign guess to high guess
                        RNG.incrementCount();
                        System.out.println("Your guess is too high");
                        highGuess = guessNum;
                    }
                } else if (numOfGuess > 0) { // if not first time

                    System.out.println("Enter your next guess between " + lowGuess + " and " + highGuess + ": ");
                    guessNum = input.nextInt(); // ask user for guess between previous low and high guess


                    if (guessNum == targetNum) { // if guess is correct, ask if to do the game again
                        System.out.println("Congratulations, you guessed correctly");
                        continue;

                    } else if (guessNum < targetNum) { //if guess is lower than the target
                        RNG.incrementCount();

                        if (!RNG.inputValidation(guessNum, lowGuess, highGuess)) {
                            guessNum = input.nextInt();
                        } //a value lower or higher than low guess or high guess respectively, then let user try again

                        System.out.println("Your guess is too low");
                        lowGuess = guessNum;  // assign the guess value to lowGuess

                    } else if (guessNum > targetNum) { // if the user's guess is higher than the target

                        RNG.incrementCount(); // increment count

                        if (!RNG.inputValidation(guessNum, lowGuess, highGuess)) {
                            guessNum = input.nextInt();
                        }//a value lower or higher than low guess or high guess respectively, then let user try again

                        System.out.println("Your guess is too high");
                        highGuess = guessNum; // assign guess value to highGuess

                    }
                }


                numOfGuess = RNG.getCount(); // get the number of tries after user answer question

                if (guessNum != targetNum) // as long as value is incorrect display number of guess after
                    System.out.println("Number of guesses is: " + numOfGuess);

            } while (guessNum != targetNum);

            input.nextLine(); //allows the user to be able to answer by consuming the entire input line before asking
            System.out.println("Try again? (yes or no): ");
            userContinue = input.nextLine();


        }while(userContinue.equals("yes") || userContinue.equals("Yes"));



        System.out.println("Programmer: Lawrence Aryeh");
    }



    public static void checkTries(int num, int max) // method for evaluating tries
    {
        if(num>=max){ // if the user exceed 7 tries, exit the program.
            System.out.println("You have exceeded the maximum number of guesses, 7. Try again.");
            System.exit(0);
        }
    }




}