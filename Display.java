
/**
 * Display contains the state of the calculator and its display
 * including user input and output.  Has methods for all calculator's
 * mathematical functions.
 * 
 * @author Cara Eppes, Sean Rowan, Zaina King, Reese Watson
 * @version 2/11/2019
 */

import java.io.InputStream; 
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Scanner;
import java.util.InputMismatchException;

public class Display {

    private final Scanner scanner; 
    private final PrintStream out;
    private String currentDisplay = "0";
    private String memoryValue = "0";
    private String currentDisplayMode = "decimal";
    private String currentUnitsMode = "degrees";

    /**
     * Display is a constructor for a Display object
     */
    public Display() {// Displaying 
        this(System.in, System.out);
    }

    /**
     * Display is a constructor for a Display object
     * 
     * @param inputStream : user inputStream
     * @param outputStream : program outputStream
     */
    public Display(InputStream inputStream, OutputStream outputStream) {
        this(new Scanner(inputStream), new PrintStream(outputStream));
    }

    /**
     * Display is a constructor for a Display object
     * 
     * @param scanner : a Scanner for user input
     * @param printStream : a PrintStream for program output
     */
    public Display(Scanner scanner, PrintStream printStream) {
        this.scanner = scanner;
        this.out = printStream;
    }

    /**
     * @param val  : text to display on console
     * @param args : optional arguments to send for string formatting
     */
    public void print(String val, Object... args) {
        out.format(val, args); // 
    }

    /**
     * @param val  : text to display on console
     * @param args : optional arguments to send for string formatting
     */
    // This prints out a line
    public void println(String val, Object... args) {
        print(val + "\n", args); 
    }

    /**
     * getScanner is a getter for scanner
     * 
     * @return the display's scanner
     */
    public Scanner getScanner(){
        return this.scanner;
    }

    /**
     * getPrintStream is a getter for out
     * 
     * @return the display's PrintStream
     */
    public PrintStream getPrintStream(){
        return this.out;
    }

    /**
     * @param prompt : text to display to user
     * @param args   : optional arguments to send for string formatting
     * 
     * @return user's input as double
     */

    public Double getDoubleInput(String prompt, Object... args) {
        println(prompt);
        Double d = new Double(0);

        try {
            d = this.scanner.nextDouble();
        } catch(InputMismatchException ex){
            println("Error: not a numerical value");
        }
        return d;
    }

    /**
     * @param prompt : text to display to user
     * @param args   : optional arguments to send for string formatting
     * @return user's input as String
     */
    public String getStringInput(String prompt, Object... args) {
        println(prompt);

        return this.scanner.next();
    }

    /**
     * getCurrentDisplay gets the currentDisplay variable
     * 
     * @return the display's currentDisplay
     */
    public String getCurrentDisplay(){
        return this.currentDisplay;
    }

    /**
     * clearDisplay clears the display back to zero
     */
    public void clearDisplay(){
        this.currentDisplay = "0";
    }

    /**
     * changeDisplay changes the display from the current display
     * 
     * @param s : a String that will be the currentDisplay
     */
    public void changeDisplay(String s){
        this.currentDisplay = s;
    }

    /** add calculates the sum of two numbers
     * 
     * @param x : a double to be added
     * @param y : a double to be added
     * 
     * @return a double that is the sum of x and y
     */
    public double add(double x, double y){
        return x + y;

    }

    /**
     * switchDisplayMode() toggles currentDisplay value through binary, octal, 
     * hexadecimal, and decimal
     */

    public void switchDisplayMode(){
        switch(currentDisplayMode){
            case "decimal" : currentDisplayMode = "binary";
            Integer intValueBinary = (int)Math.round(Double.valueOf(currentDisplay));
            currentDisplay = Integer.toBinaryString(intValueBinary);
            print("Binary: ");
            break;

            case "binary" : currentDisplayMode = "octal";
            Integer intValueOctal = (int)Math.round(Double.valueOf(currentDisplay));
            currentDisplay = Integer.toOctalString(intValueOctal);
            print("Octal: ");
            break;

            case "octal" : currentDisplayMode = "hexadecimal";
            Integer intValueHex = (int)Math.round(Double.valueOf(currentDisplay));
            currentDisplay = Integer.toHexString(intValueHex);
            print("Hexadecimal: ");
            break;

            case "hexadecimal" : currentDisplayMode = "decimal";
            print("Decimal: ");

            break;
        }
    }

    /**
     * switchDisplayMode switches current Display to specified mode
     * 
     * @param mode : a String "binary", "octal", or "hex" that indicates which mode to switch to
     */
    public void switchDisplayMode(String mode){
        switch(mode){
            case "binary" : this.currentDisplayMode = "decimal";
            this.switchDisplayMode();
            break;

            case "octal" : this.currentDisplayMode = "binary";
            switchDisplayMode();
            break;

            case "hex" : this.currentDisplayMode = "octal";
            switchDisplayMode();
            break;
        }
    }

    /**
     * setCurrentDisplayMode is a setter for currentDisplayMode
     * 
     * @param mode : String "decimal", "octal", "binary", or "hex"
     * 
     */
    public void setCurrentDisplayMode(String mode){
        this.currentDisplayMode = mode;
    }

    /**
     * setUnitsMode is a setter for currentUnitsMode
     * 
     * @param mode : String "radians" or "degrees"
     */
    public void setUnitsMode(String mode){
        this.currentUnitsMode = mode;
    }

    /**
     * setMemoryValue saves previous value
     * 
     * @param s : a String that is the value to be saved as memoryValue
     */
    public void setMemoryValue(String s){
        this.memoryValue = s;
    }

    /**
     * getMemoryValue gives back the value that was saved
     * 
     * @return the Display's current memory value
     */
    public String getMemoryValue(){
        return this.memoryValue;
    }

    /**
     * switchUnitsMode toggle display value between radians and degrees
     */
    public void switchUnitsMode(){
        Double doubleValue = Double.valueOf(currentDisplay);
        switch(currentUnitsMode){
            case "degrees" : this.setUnitsMode("radians");
            Double doubleRadians = Math.toRadians(doubleValue);
            currentDisplay = doubleRadians.toString();
            print("\nRadian value: ");
            break;

            case "radians" : this.setUnitsMode("degrees");
            Double doubleDegrees = doubleValue + Math.PI/180;
            currentDisplay = doubleDegrees.toString();
            print("\nDegree value: ");
            break;
        }

    }

    /**
     * switchUnitsMode set units mode to radians or degrees
     * 
     * @param mode : String "radians" or "degrees"
     */
    public void switchUnitsMode(String mode){
        switch(mode){
            case "degrees" : this.setUnitsMode("radians");
            this.switchUnitsMode();
            break;

            case "radians" : this.setUnitsMode("degrees");
            this.switchUnitsMode();
            break;

        }

    }

    /**
     * getUnitsMode is a getter for currentUnitsMode
     * 
     * @return the display's currentUnitsMode
     */
    public String getUnitsMode(){
        return this.currentUnitsMode;
    }
    // This is all the operations

    /**
     * subtract calculates the difference between two numbers
     * 
     * @param x : a number
     * @param y : a number to subtract from x
     * 
     * @return the difference between x and y
     */
    public double subtract(double x, double y){
        return x - y;
    }

    /**
     * multiply calculates the product of two numbers
     * 
     * @param x : a number
     * @param y : a number to multiply by x
     * 
     * @return the product of x and y
     */
    public double multiply(double x, double y){
        return x * y;
    } 

    /**
     * divide finds the quotient of two numbers
     * 
     * @param x : a number that is the dividend
     * @param y : a number that is the divisor
     * 
     * @return the quotient of x and y
     */
    public double divide(double x, double y){
        return x / y;
    }

    /**
     * modulus calculates the remainder when two numbers are divided
     * 
     * @param x : a number that is the dividend
     * @param y : a number that is the divisor
     * 
     * @return the remainder when x is divided by y
     */
    public double modulus(double x, double y){
        return x % y;
    }

    /**
     * exponent calculates the value of a number raised to an exponent
     * 
     * @param x : a number
     * @param y : a number that is the exponent value
     * 
     * @return x raised to the power y
     */
    public double exponent(double x, double y){
        return Math.pow(x, y);
    }

    /**
     * squareRoot calculates the square root of a numbre
     * 
     * @param x : a number
     * 
     * @return the square root of x
     */
    public double squareRoot(double x){
        return Math.sqrt(x);
    }

    /**
     * sin calculates the sine of a number
     * 
     * @param x : a number
     * 
     * @return the sin of x
     */
    public double sin(double x){
        return Math.sin(x);
    }

    /**
     * asin calculates the inverse sine of a number
     * 
     * @param x : a number
     * 
     * @return the inverse sin of x
     */
    public double asin(double x){
        return Math.asin(x);
    }

    /**
     * cos calculates the cosine of a number
     * 
     * @param x : a number
     * 
     * @return the cos of x
     */
    public double cos(double x){
        return Math.cos(x);
    }

    /**
     * cosh calculates the hyperbolic cosine of a number
     * 
     * @param x : a number
     * 
     * @return the hyberbolic cos of x
     */
    public double cosh(double x){
        return Math.cosh(x);
    }

    /**
     * sinh calculates the hyperbolic sine of a number
     * 
     * @param x : a number
     * 
     * @return the hyberbolic sin of x
     */
    public double sinh(double x){
        return Math.sinh(x);
    }

    /**
     * acos calculates the inverse cosine of a number
     * 
     * @param x : a number
     * 
     * @return the inverse cos of x
     */
    public double acos(double x){
        return Math.acos(x);
    }

    /**
     * tan calculates the tangent of a number
     * 
     * @param x : a number
     * 
     * @return the tan of x
     */
    public double tan(double x){
        return Math.tan(x);
    }

    /**
     * atan calculates the inverse tangent of a number
     * 
     * @param x : a number
     * 
     * @return the inverse tan of x
     */
    public double atan(double x){
        return Math.atan(x);
    }

    /**
     * tanh calculates the hyperbolic tangent of a number
     * 
     * @param x : a number
     * 
     * @return the tanh of x
     */
    public double tanh(double x){
        return Math.tanh(x);
    }

    /**
     * theta calculates the angle of a point (x, y)
     * 
     * @param x : a number that is the x coordinate
     * @param y : a number that is the y coordinate
     * 
     * @return the angle theta between the x axis and (x, y)
     */
    public double theta(double x, double y){
        return Math.atan2(y,x);
    }

    /**
     * cubicRoot calculates the cubic root of a number
     * 
     * @param x : a number
     * 
     * @return the cubic root of x
     */
    public double cubicRoot(double x){
        return Math.cbrt(x);
    }

    /**
     * inverse calculates the inverse of a number 
     *
     * @param  x : number to calculate inverse of
     * @return inverse of x
     */
    public double inverse(double x){
        return 1 / x;
    }

    /**
     * invertSign changes the sign of the input number
     *
     * @param  x : number to change sign of
     * @return x with inverted sign
     */
    public double invertSign(double x){
        return -x;
    }

    /**
     * factorial calculates factorial of a number
     *
     * @param  x : number to calculate factorial of
     * @return factorial of x
     */
    public double factorial(double x){
        double f = 1;
        for (int i = 1; i <= x; i++){
            f = f * i;
        }
        return f;
    }

    /**
     * gcm calculates the greatest common divisor of two numbers
     *
     * @param  x : one number for gcd calculation
     * @param  y : other number for gcd calculation
     * @return gcd of x an y
     */
    public double gcd(double x, double y){
        if (y == 0){
            return x;
        }
        else{
            return gcd(y, x % y);
        }
    }

    /**
     * lcm calculates the least common multiple of two numbers
     *
     * @param  x : one number for lcm calculation
     * @param  y : other number for lcm calculation
     * @return lcm of x an y
     */

    public double lcm(double x, double y){
        return x * (y / gcd(x, y));
    }

    /**
     * SuperCalc runs the program
     */
    public void superCalc(){
        boolean run = true;
        Display display = new Display();
        display.println("TIME TO CALCULATE!\n");
        Double x = display.getDoubleInput("Enter a number");

        while (run){
            display.println("\n[+]  [-]  [*]  [/]  [%%]  [^2]  [^x]  [sqrt]  [cbrt]  [!]");
            display.println("[sin]  [cos]  [tan]  [sinh]  [cosh]  [tanh]  [asin]  [acos]  [atan]");
            display.println("[changebase]  [binary]  [octal]  [hex]  [changeunits]  [radians]  [degrees]");
            display.println("[theta]  [inverse]  [invertsign]  [gcd]  [lcm]\n");
            String s = display.getStringInput("Enter an operator: ").toLowerCase();
            Double result = 0.0;

            switch(s){
                case "+" : 
                case "add" : 
                case "sum" :
                case "plus": 
                double addY = display.getDoubleInput("\nEnter another number");
                result = display.add(x, addY);
                display.println("\n%s + %s = %s", String.format("%s", x), String.format("%s", addY), String.format("%s", result));
                display.changeDisplay(result.toString());

                break;

                case "-": 
                case "subtract": 
                case "minus":
                double subY = display.getDoubleInput("\nEnter another number");
                result = display.subtract(x, subY);
                display.println("\n%s - %s = %s", String.format("%s", x), String.format("%s", subY), String.format("%s", result));
                display.changeDisplay(result.toString());

                break;

                case "/":
                case "divide":
                case "divided by":
                double divY = display.getDoubleInput("\nEnter another number");
                if (divY == 0){
                    display.changeDisplay("Error");
                    display.println("Error.  Cannot divide by 0.");
                }
                else{
                    result = display.divide(x, divY);
                    display.println("\n%s / %s = %s", String.format("%s", x), String.format("%s", divY), String.format("%s", result));
                    display.changeDisplay(result.toString());
                }

                break;

                case "*" :
                case "times":
                case "multiply":
                double multiY = display.getDoubleInput("\nEnter another number");
                result = display.multiply(x, multiY);
                display.println("\n%s * %s = %s", String.format("%s", x), String.format("%s", multiY), String.format("%s", result));
                display.changeDisplay(result.toString());

                break;

                case "%" :
                case "mod":
                case "remainder":
                double modY = display.getDoubleInput("\nEnter another number");
                result = display.modulus(x, modY);
                display.println("\n%s %% %s = %s", String.format("%s", x), String.format("%s", modY), String.format("%s", result));
                display.changeDisplay(result.toString());

                break;

                case "^x" : 
                case "^" :
                double expoY = display.getDoubleInput("\nEnter another number");
                result = display.exponent(x, expoY);
                display.println("\n%s ^ %s = %s", String.format("%s", x), String.format("%s", expoY), String.format("%s", result));
                display.changeDisplay(result.toString());

                break;

                case "^2" :
                result = display.exponent(x, 2);
                display.println("\n%s ^2 = %s", String.format("%s", x), String.format("%s", result));
                display.changeDisplay(result.toString());

                break;

                case "sin": 
                result = display.sin(x);
                display.println("\n%s %s = %s", s, String.format("%s", x), String.format("%s", result));
                display.changeDisplay(result.toString());

                break;

                case "asin": 
                result = display.asin(x);
                display.println("\n%s %s = %s", s, String.format("%s", x), String.format("%s", result));
                display.changeDisplay(result.toString());

                break;

                case "sinh": 
                result = display.sinh(x);
                display.println("\n%s %s = %s", s, String.format("%s", x), String.format("%s", result));
                display.changeDisplay(result.toString());

                break;

                case "cos": 
                result = display.cos(x);
                display.println("\n%s %s = %s", s, String.format("%s", x), String.format("%s", result));
                display.changeDisplay(result.toString());

                break;

                case "acos": 
                result = display.acos(x);
                display.println("\n%s %s = %s", s, String.format("%s", x), String.format("%s", result));
                display.changeDisplay(result.toString());

                break;

                case "cosh": 
                result = display.cosh(x);
                display.println("\n%s %s = %s", s, String.format("%s", x), String.format("%s", result));
                display.changeDisplay(result.toString());

                break;

                case "tan": 
                result = display.tan(x);
                display.println("\n%s %s = %s", s, String.format("%s", x), String.format("%s", result));
                display.changeDisplay(result.toString());

                break;

                case "atan": 
                result = display.atan(x);
                display.println("\n%s %s = %s", s, String.format("%s", x), String.format("%s", result));
                display.changeDisplay(result.toString());

                break;

                case "tanh": 
                result = display.tanh(x);
                display.println("\n%s %s = %s", s, String.format("%s", x), String.format("%s", result));
                display.changeDisplay(result.toString());

                break;

                case "theta":
                double thetaY = display.getDoubleInput("\nEnter another number");
                result = display.theta(thetaY, x);
                display.println("\n%s theta %s = %s", String.format("%s", x), String.format("%s", thetaY), String.format("%s", result));
                display.changeDisplay(result.toString());

                break;

                case "sqrt":
                case "root":
                case "square root":
                result = display.squareRoot(x);
                display.println("\n%s %s = %s", s, String.format("%s", x), String.format("%s", result));
                display.changeDisplay(result.toString());

                break;

                case "cbrt":
                result = display.cubicRoot(x);
                display.println("\n%s %s = %s", s, String.format("%s", x), String.format("%s", result));
                display.changeDisplay(result.toString());

                break;

                case "changebase" : 
                while(s.equals("changebase")){
                    display.changeDisplay("" + x);
                    display.switchDisplayMode();
                    display.println(display.getCurrentDisplay() + "\n");
                    s = display.getStringInput("Enter \"changebase\" to switch mode again or enter \"back\" to go back to calculator\n");
                }
                break;

                case "binary" : 
                display.changeDisplay("" + x);
                display.switchDisplayMode(s);
                display.println(display.getCurrentDisplay());

                break;

                case "octal" : 
                display.changeDisplay("" + x);
                display.switchDisplayMode(s);
                display.println(display.getCurrentDisplay());

                break;

                case "hex" : 
                display.changeDisplay("" + x);
                display.switchDisplayMode(s);
                display.println(display.getCurrentDisplay());

                break;

                case "m+" :
                display.changeDisplay("" + x);
                display.setMemoryValue(display.getCurrentDisplay());
                display.println("%s has been saved to memory.", display.getMemoryValue()); 

                break;

                case "m-" :
                display.setMemoryValue("0");
                display.println("The memory value has been reset to %s", display.getMemoryValue()); 

                break; 

                case "mrc" :
                display.changeDisplay(display.getMemoryValue());
                display.println(display.getCurrentDisplay());

                break;

                case "changeunits" :
                while (s.equals("changeunits")){
                    display.changeDisplay("" + x);
                    display.switchUnitsMode();
                    display.println(display.getCurrentDisplay() + "\n");
                    s = display.getStringInput("Enter \"changeunits\" to switch mode again or enter \"back\" to go back.\n");
                }
                break;

                case "degrees" :
                display.changeDisplay("" + x);
                display.switchUnitsMode(s);
                display.println(display.getCurrentDisplay());

                break;

                case "radians" :
                display.changeDisplay("" + x);
                display.switchUnitsMode(s);
                display.println(display.getCurrentDisplay());

                break;

                case "inverse" :
                case "1/x" :
                result = inverse(x);
                display.println("\n1 / %s = %s", String.format("%s", x), String.format("%s", result));
                display.changeDisplay(result.toString());

                break;

                case "invertsign" :
                result = invertSign(x);
                display.println("\n%s %s = %s", s, String.format("%s", x), String.format("%s", result));
                display.changeDisplay(result.toString());

                break;

                case "factorial" :
                case "!" :
                result = factorial(x);
                display.println("\n%s! = %s", String.format("%s", x), String.format("%s", result));
                display.changeDisplay(result.toString());

                break;

                case "gcd":
                double gcdY = display.getDoubleInput("\nEnter another number");
                result = display.gcd(x, gcdY);
                display.println("\ngcd(%s, %s) = %s", String.format("%s", x), String.format("%s", gcdY), String.format("%s", result));
                display.changeDisplay(result.toString());

                break;

                case "lcm":
                double lcmY = display.getDoubleInput("\nEnter another number");
                result = display.lcm(x, lcmY);
                display.println("\nlcm(%s, %s) = %s", String.format("%s", x), String.format("%s", lcmY), String.format("%s", result));
                display.changeDisplay(result.toString());

                break;

                case "clear" :
                case "c" :
                display.clearDisplay();
                display.println("\n0.0");

                break;

                default : 
                display.println("Not a proper operator");
                result = 0.0;
            }

            String m = display.getStringInput("\nEnter 'm+' to save the value.  Enter 'c' to clear");

            // if user enters m+, sets memoryValue to currentDisplay
            if(m.equals("m+")){
                if (display.getCurrentDisplay().equals("Infinity")){
                    // if user tries to save Infinity, prints error and sets display to 0
                    println("\nError.  Memory is not infinite...");
                    display.changeDisplay("0");
                }
                else{
                    display.setMemoryValue(display.getCurrentDisplay());
                    display.println("\n%s has been saved to memory.", display.getMemoryValue()); 
                }
            }
            // if user enter c, sets display to 0
            else if (m.equals("c")){
                display.changeDisplay("0");
            }

            String quit = display.getStringInput("\nEnter 'quit' to stop, 'mrc' to use a saved value, or enter another number.");

            // if user enters quit, run = false to stop program
            if (quit.equals("quit")){
                run = false; 
                println("\n(╯°□°）╯︵ ┻━┻  I'm done with this calculator!");
            }

            if(run){
                // if user enters mrc, display is set to memoryValue 
                if(quit.equals("mrc")){
                    x = Double.valueOf(display.getMemoryValue());
                    display.println("\n" + display.getMemoryValue());
                    display.changeDisplay(display.getMemoryValue());
                }
                else{
                    // if user enters a number, display is changed to number
                    try{
                        x = Double.valueOf(quit);
                        display.changeDisplay(quit);
                        // if user does not enter a proper value, prints an error
                    } catch(NumberFormatException ex) {
                        x = display.getDoubleInput("\nError.  Enter a numerical value.");
                    }
                }
                // resets mode to decimal and degrees
                display.setCurrentDisplayMode("decimal");
                display.setUnitsMode("degrees");
            }
        }

    }
}