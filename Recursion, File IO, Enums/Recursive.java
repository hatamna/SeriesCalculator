class Recursive {
  private static int sVal;
  private static int inc;
  private static int numTerms;
  private static int term = 1;

  public Recursive(){
    sVal = 0;
    inc = 0;
    numTerms = 0;
  }

  public Recursive(int s, int i, int n){
    sVal = s;
    inc = i;
    numTerms = n;
  }

  public static void arithmeticSeries() {
    if (numTerms == 0){
      return;
    }
    System.out.print(sVal + " ");
    Main.writeToFile(sVal + " ");
    Main.count += sVal;
    sVal += inc;
    numTerms -= 1;
    arithmeticSeries();
  }

  public static void geometricSeries(){
    if (numTerms == 0){
      return;
    }
    System.out.print(sVal + " ");
    Main.count += sVal;
    sVal*=inc;
    numTerms -= 1;
    geometricSeries();
  }

  public static int fibonacciGenerate(int[] array, int i){
    if (i==0||i==1){
      array[i] = i;
    } else {
      array[i] = array[i-1] + array[i-2];
    }
    if (i == 19){
      return -1;
    }
    fibonacciGenerate(array, i+1);
    return -1;
  }

  public static void fibonacciSeries(){
    boolean onList = isOnFibo();
    if (onList){
      for (int i = 0; i < numTerms; i++){
        System.out.print(Main.fibonacciList[sVal] + " ");
        Main.count += Main.fibonacciList[sVal];
        sVal++;
      }
    }
  }

  public static boolean isOnFibo(){
    for (int i = 0; i < Main.fibonacciList.length; i++){
      if (Main.fibonacciList[i] == sVal){
        return true;
      }
    }
    Main.writeToFile("Error. Number not on Fibonacci Sequence.");
    System.out.println("Element not present in Fibonacci Sequence. Please enter a number found on this list: ");
    for (int x = 0; x < Main.fibonacciList.length; x++){
      System.out.print(Main.fibonacciList[x] + ", ");
    }
    return false;
  }

  public static void expSeries(){
    if (numTerms == 0){
      return;
    }
    int thisTerm = (int) Math.pow(sVal, term);
    System.out.print(thisTerm + " ");
    Main.count += thisTerm;
    term+=1;
    numTerms-=1;
    expSeries();
  }

  public int getSVal(){
    return sVal;
  }

  public int getInc(){
    return inc;
  }

  public int getNumTerms(){
    return numTerms;
  }

  public void setSVal(int s){
    sVal = s;
  }

  public void setInc(int i){
    inc = i;
  }

  public void setNumTerms(int n){
    numTerms = n;
  }

  public String toString(){
    return "Starting Value: " + sVal + ", Increment: " + inc + ", Number of Terms: " + numTerms;
  }
}