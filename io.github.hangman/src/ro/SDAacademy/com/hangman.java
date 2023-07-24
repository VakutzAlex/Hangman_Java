package ro.SDAacademy.com;

import java.util.Scanner;

public class hangman {

    public static void main(String[] args) {

        //Scanner
        Scanner sc = new Scanner(System.in);

        //Variables
        String wordToBeGuessed;
        char letter;

        int lettersGuessed = 0;
        int tries = 0;
        int totalTries = 0;
        int indexFound = 0;

        //Input
        System.out.print("Introduce a word: ");
        wordToBeGuessed = sc.nextLine();

        char guess[] = new char[wordToBeGuessed.length()];

        for (int i = 0; i < wordToBeGuessed.length(); i++) {
            guess[i] = '_';
        }

        if (palindrome(wordToBeGuessed.toLowerCase())) {
            System.out.println("Word introduced is a palindrome => Game too easy => Shutting down");
            return;
        } else {
            System.out.println("Word introduced is not a palindrome => Continuing");
        }

        System.out.println("The word that needs to be guessed: ");
        display(wordToBeGuessed.length(), guess);

        //Start game
        System.out.println("\nStart game!");
        while (lettersGuessed < wordToBeGuessed.length()) {
            System.out.println("Introduce a letter: ");
            letter = sc.nextLine().charAt(0);

            /*      check if letter is in word      */

            //wrong letter
            if (checkLetter(letter, wordToBeGuessed, indexFound) < 0) {
                tries++;
                drawBodyPart(tries);
                if (tries == 7) {
                    return;
                }
            } else {  //correct letter
                lettersGuessed++;
                indexFound = checkLetter(letter, wordToBeGuessed, indexFound);
                replaceUnderline(indexFound, letter, guess);
                //display(wordToBeGuessed.length(),guess);
                indexFound++;
                while (indexFound < wordToBeGuessed.length()) {
                    if (checkLetter(letter, wordToBeGuessed, indexFound) >= 0) {
                        indexFound = checkLetter(letter, wordToBeGuessed, indexFound);
                        replaceUnderline(indexFound, letter, guess);
                        lettersGuessed++;
                    }
                    indexFound++;
                }
                display(wordToBeGuessed.length(), guess);
            }

            indexFound = 0;

            System.out.println();
            /*      check if letter is in word      */
        }
        //End game
        totalTries = tries + lettersGuessed;

        if (lettersGuessed == wordToBeGuessed.length()) {
            System.out.println("\nCongratulations, you won the game after " + totalTries + " tries");
        }
        System.out.println();

    }

    public static int checkLetter(char c, String word, int indexOfLetter) {

        return word.indexOf(c, indexOfLetter);
    }

    public static void drawBodyPart(int num) {
        switch (num) {
            case 1: {
                System.out.println("*Draws head*");
                break;
            }
            case 2: {
                System.out.println("*Draws torso*");
                break;
            }
            case 3: {
                System.out.println("*Draws right arm*");
                break;
            }
            case 4: {
                System.out.println("*Draws left arm*");
                break;
            }
            case 5: {
                System.out.println("*Draws right leg*");
                break;
            }
            case 6: {
                System.out.println("*Draws left leg*");
                break;
            }
            default: {
                System.out.println("Game lost");
                break;
            }
        }
    }

    public static void replaceUnderline(int index, char letter, char l[]) {
        l[index] = letter;
    }

    public static void display(int length, char l[]) {
        for (int i = 0; i < length; i++) {
            System.out.print(l[i]);
        }
    }

    public static boolean palindrome(String word) {
        int first = 0;
        int last = word.length() - 1;

        while (first < last) {
            if (word.charAt(first) != word.charAt(last)) {
                return false;
            }
            first++;
            last--;
        }
        return true;
    }
}

