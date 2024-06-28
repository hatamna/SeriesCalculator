import java.io.*;
import java.util.*;
import java.lang.Math;
public class Main {

  static String RESET = "\u001B[0m";
  static String RED = "\u001B[31m";
  static String GREEN = "\u001B[32m";
  static String YELLOW = "\u001B[33m";

  static int count = 0;
  static boolean isFibo = false;

  static int[] fibonacciList = new int[20];

  static File saveSeries = new File("saveSeries.txt");
  static Scanner input = new Scanner(System.in);

  enum SeriesCategory {
    ARITHMETIC,
    GEOMETRIC,
    FIBONACCI,
    EXPONENTIAL
  }
  
  public static void createFile(){
    try{
      if (saveSeries.createNewFile()){
        System.out.println(GREEN + "File created: " + saveSeries.getName() + "\n" + RESET);
      } else {
        System.out.println(RED + "File already exists.\n" + RESET);
      }
    } catch (IOException e){
      System.out.println(RED + "An error occurred." + RESET);
    }
  }

  public static void writeToFile(String str){
    try{
      FileWriter writer = new FileWriter("saveSeries.txt", true);
      writer.write(str);
      writer.close();
    } catch (IOException e){
      System.out.println(RED + "An error occured while writing to the file." + RESET);
    }
  }

  public static int checkInt() {
    boolean intFound = false;
    int num = 0;
    outer: do {
      try {
        num = input.nextInt();
        input.nextLine();
        break outer;
      } catch (InputMismatchException e) {
        System.out.print(RED + "Please enter a valid integer: \n-> " + RESET);
        input.nextLine();
      }
    } while (!intFound);
    return num;
  }

  public static void mainQuestions(int type) {
    count = 0;
    System.out.print("\nStarting value of series: ");
    int sVal = checkInt();
    int inc = 0;
    if (!isFibo){
      System.out.print("Increment: ");
      inc = checkInt();
    }
    System.out.print("Number of terms: ");
    int numTerms = checkInt();
    System.out.print("\n");
    writeToFile("Staring Value : " + String.valueOf(sVal) + ", Increment : " + String.valueOf(inc) + ", Number of Terms : " + String.valueOf(numTerms) + "\nResult: ");
    switch (type){
      case 1: 
        Recursive arithmetic = new Recursive(sVal, inc, numTerms);
        arithmetic.arithmeticSeries();
        break;
      case 2: 
        Recursive geometric = new Recursive(sVal, inc, numTerms);
        geometric.geometricSeries();
        break;
      case 3:
        Recursive fibonacci = new Recursive(sVal, 0, numTerms);
        fibonacci.fibonacciSeries();
        break;
      case 4:
        Recursive exp = new Recursive(sVal, 1, numTerms);
        exp.expSeries();
        break;
      default:
        System.out.println(RED + "Invalid input." + RESET);
    }
    writeToFile("\nSum of series: " + String.valueOf(count));
    System.out.print(GREEN + "\n\nThe sum of the series is: ");
    if (count == 0){
      System.out.println("N/A" + RESET);
    } else {
      System.out.println(count + RESET);
    }
    
    System.out.println("\n");
  }

  public static void clearFile(){
    try{
      FileWriter eraseWriter = new FileWriter("saveSeries.txt");
      eraseWriter.write("");
      eraseWriter.close();
    } catch (IOException e){
      System.out.println(RED + "Unable to erase file." + RESET);
    }
  }

  public static void displayFile(){
    try{
      File file = new File("saveSeries.txt");
      Scanner reader = new Scanner(file);
      while (reader.hasNextLine()){
        String data = reader.nextLine();
        System.out.println(data);
      }
      reader.close();
    } catch (FileNotFoundException e){
      System.out.println(RED + "An error occurred." + RESET);
    }
  }

  // START OF MAIN
  public static void main(String[] args) {
    createFile();
    writeToFile("\n\nNEW RUN ----------------------------------\n");
    Recursive.fibonacciGenerate(fibonacciList, 0);
    System.out.println(YELLOW + "Welcome to the Series Calculator!" + RESET);

    SeriesCategory cat = SeriesCategory.ARITHMETIC;

    do {
      isFibo = false;
      System.out.print(
          "What type of series would you like to calculate?\n\n1. Arithmetic\n2. Geometric\n3. Fibonacci\n4. Exponential\n5. Erase File and Exit\n6. View File Contents and Exit\n7. Keep File and Exit\n-> ");
      int choice = checkInt();

      switch (choice) {
        case 1:
          writeToFile("\n-> Arithmetic Series: ");
          System.out.println(YELLOW + "\nAn Arithmetic Series is defined as a series of numbers increasing by the same constant:\n" + RESET);
          cat = SeriesCategory.ARITHMETIC;
          break;
        case 2:
          writeToFile("\n-> Geometric Series: ");
          System.out.println(YELLOW + "\nA Geometric Series is defined as a series of numbers increasing by the same multiple:\n" + RESET);
          cat = SeriesCategory.GEOMETRIC;
          break;
        case 3:
          writeToFile("\n-> Fibonacci Series: ");
          System.out.println(YELLOW + "\nA Fibonacci Series is defined as a series of numbers in which each term is the sum of the 2 preceding ones:\n\n*Note: The starting value must exist in the fibonacci sequence*" + RESET);
          isFibo = true;
          cat = SeriesCategory.FIBONACCI;
          break;
        case 4:
          writeToFile("\n-> Exponential Series: ");
          System.out.println(YELLOW + "\nAn exponential Series is defined as a series of numbers in which each term is the original term to the the term number's power." + RESET);
          isFibo = true;
          cat = SeriesCategory.EXPONENTIAL;
          break;
        case 5:
          System.out.println("\nErasing...");
          clearFile();
        case 6:
          displayFile();
        case 7:
          System.out.println(GREEN + "\nThank you for using the Series Calculator!" + RESET);
          System.exit(0);
          break;
        default:
          System.out.println(RED + "Please enter a valid option." + RESET);
          break;
      }

      switch (cat) {
        case ARITHMETIC:
          mainQuestions(1);
          break;
        case GEOMETRIC:
          mainQuestions(2);
          break;
        case FIBONACCI:
          mainQuestions(3);
          break;
        case EXPONENTIAL:
          mainQuestions(4);
          break;
        default:
          System.out.println(RED + "Unexpected Error Occured..." + RESET);
          break;
      }
    } while (true);
  }

}