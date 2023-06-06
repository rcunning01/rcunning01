/* Word Search Generator
 * By Ryan Cunningham
 * For Computer Science II
 * Whatcom Community College
 * Taught by Jeremiah Ramsey
 */

import java.util.Arrays;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;
import java.util.TreeMap;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;

public class RCWordSearch {
    public static void main(String[] args) throws IOException {
        Random rd = new Random();
        Scanner s = new Scanner(System.in);

        printIntro();
        String arg = s.nextLine();

        switch (arg) {
            case "g":
                System.out.println(
                    "Please specify the name of a file to which to save the "
                    + "word search"
                );
                String saveName = s.nextLine();
                PrintStream fileStream = new PrintStream(new File(saveName));
                System.out.println(
                    "Please specify the words to be searched for - type a "
                    + "period on a line by itself to finish - max. 25 words"
                );
                String[] words = new String[25];
                ArrayList<String> removedNull = new ArrayList<String>();
                for (int word = 0; word <= 25; word++) {
                    if (word == 25) {
                        System.out.println("Reached maximum limit");
                        break;
                    }
                    words[word] = s.nextLine();
                    if (words[word].equals(".")) {
                        break;
                    }
                    if (words[word] != null) {
                        removedNull.add(words[word]);
                    }
                }
                String[] newWords = removedNull.toArray(new String[0]);
                System.setOut(fileStream);
                generate(saveName, newWords, rd);
                s.close();
                break;
            case "p":
                System.out.println(
                    "Please specify a file that contains a word search to print"
                );
                String printFileName = s.nextLine();
                Scanner printFile = new Scanner(new File(printFileName));
                print(printFile);
                printFile.close();
                s.close();
                break;
            case "s":
                System.out.println(
                    "Please specify a file that contains a word search to solve"
                );
                String solveFileName = s.nextLine();
                Scanner solveFile = new Scanner(new File(solveFileName));
                showSolution(solveFile);
                solveFile.close();
                s.close();
                break;
            default:
                s.close();
                throw new IllegalArgumentException("Invalid command");
        } // End switch (arg)
    } // End public static void main(String[] args) throws IOException

    private static void printIntro() {
        System.out.println(
            "Ryan Cunningham's Java Word Search Generator version "
            + "1\nGenerates, prints, and solves word searches using given "
            + "input\nType 'g' to generate new word search,\n's' to solve an "
            + "existing word search,\nor 'p' to print an existing word search "
            + "unsolved"
        );
    }

    private static String randomLetter(Random rd) {
        // print a completely random letter
        int random = rd.nextInt(26);
        String letter = String.valueOf((char) (random + 65));
        // Assume ASCII encoding
        return letter;
    }

    private static String[][] generate(
        String genFileName,
        String[] genInput,
        Random rd
    ) {
        String[][] wordSearch = new String[25][25];

        // Ensure no two words appear on the same row - else they might collide
        TreeMap<Integer, String> wordRows = new TreeMap<Integer, String>();
        Integer rowNumber = 0;
        for (String word: genInput) {
            while (true) {
                rowNumber = rd.nextInt(25);
                if (wordRows.get(rowNumber) == null) {
                    wordRows.put(rowNumber, word);
                    break;
                }
            }
        }

        int wordIndex = 0;
        ArrayList<String> wordsPrinted = new ArrayList<String>();

        boolean wordsInThisRow = false;
        for (int row = 1; row <= 25; row++) {
            boolean inAWord = false;
            int wordChar = 0;
            String word = "";

            if (wordRows.get(row) != null) {
                word = wordRows.get(row);
                wordsInThisRow = true;
            }
            if (word.length() > 25) {
                throw new IllegalArgumentException(
                    "Word '" + word + "' cannot be longer than 25 characters"
                );
            }
            char[] wordChars = word.toCharArray();

            for (int col = 1; col <= 25; col++) {
                if (!inAWord) {
                    if (rd.nextBoolean() && !wordsPrinted.contains(word)) {
                        String letter = randomLetter(rd);
                        System.out.print(letter + " ");
                        wordSearch[row - 1][col - 1] = letter;
                    } else if (wordsPrinted.contains(word)) {
                        String letter = randomLetter(rd);
                        System.out.print(letter + " ");
                        wordSearch[row - 1][col - 1] = letter;
                    } else {
                        String letter = randomLetter(rd);
                        System.out.print(letter + " ");
                        wordSearch[row - 1][col - 1] = letter;
                        if (wordsInThisRow) {
                            inAWord = true;
                        }
                    }
                }
                if (inAWord) {
                    if (
                        wordChar > word.length() - 1 ||
                        wordIndex > genInput.length - 1
                    ) {
                        inAWord = false;
                        wordsPrinted.add(word);
                        continue;
                    }
                    System.out.print(
                        "." + String.valueOf(wordChars[wordChar]).toUpperCase()
                    );
                    wordChar++;
                    System.out.print(" ");
                }
            } // End for (int col = 1; col <= 25; col++)

            wordsInThisRow = false;
            if (
                wordsPrinted.contains(word)
                && wordIndex < genInput.length - 1
            ) {
                wordIndex++;
            }
            System.out.println();
        } // End for (int row = 1; row <= 25; row++)

        /* showSolution() depends on the following output starting with '['
           and ending with ']' */
        System.out.println(Arrays.toString(genInput));
        return wordSearch;
    } /* End private static String[][]
         generate(String genFileName, String[] genInput, Random rd) */

    private static void print(Scanner s) {
        while (s.hasNextLine()) {
            System.out.println(s.nextLine().replace(".", ""));
        }
    }

    private static void showSolution(Scanner s) {
        while (s.hasNextLine()) {
            String line = s.nextLine();
            if (!line.startsWith("[")) {
                if (!line.contains(".")) {
                    System.out.println(
                        "X X X X X X X X X X X X X X X X X X X X X X X X X"
                    );
                } else {
                    String[] subStrings = line.split(" ");
                    for (String subString: subStrings) {
                        if (subString.contains(".")) {
                            System.out.print(subString.replace(".", ""));
                            System.out.print(" ");
                        } else {
                            System.out.print("X ");
                        }
                    }
                    System.out.println();
                }
            } else {
                System.out.println(line);
            }
        }
    }
}