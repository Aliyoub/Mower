package com.simplon.mower;


import java.io.*;

public class Mower {
    private int Y;
    private int X;
    private int X_MAX = 5;
    private int Y_MAX = 5;
    //private Orientation LastOrientation = Orientation.N;
    private Orientation lastOrientation;
    private int lastX ;
    private int lastY ;


    public boolean peer(int line) {
        if (line % 2 == 0)
            return true;
        else return false;
    }

    public void executeCommand() throws IOException {

        try {

            File inputFile = new File("inputFile.csv");
            FileReader fileReader = new FileReader(inputFile);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String instructions;
            String result ="";
            int getLine = 0;

            while ((instructions = bufferedReader.readLine()) != null) {

                if (!peer(getLine) && getLine != 0) {
                    String[] myArrayOfInstructions = instructions.split("");
                    lastX = Integer.parseInt(myArrayOfInstructions[0]);
                    lastY = Integer.parseInt(myArrayOfInstructions[1]);
                    lastOrientation = (Orientation.valueOf(myArrayOfInstructions[2]));
                    //lastOrientation = Orientation.N;
                }
                else {
                    if (getLine != 0) result += this.getPosition(instructions);
                }
                getLine++;
            }
            System.out.println(result);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getPosition(String instructions) {
        String result = "";
        X = 0; Y = 0; //lastOrientation = Orientation.N;
        for (int i = 0; i < instructions.length() - 1; i++) {
            char instruction = instructions.charAt(i);
            switch (instruction) {
                case 'L':
                    if (lastOrientation == Orientation.W) {
                        lastOrientation = Orientation.S;
                    } else if (lastOrientation == Orientation.E) {
                        lastOrientation = Orientation.N;
                    } else if (lastOrientation == Orientation.N) {
                        lastOrientation = Orientation.W;
                    } else if (lastOrientation == Orientation.S) {
                        lastOrientation = Orientation.E;
                    }
                    //break;


                case 'R':
                    if (lastOrientation == Orientation.W) {
                        lastOrientation = Orientation.N;
                    } else if (lastOrientation == Orientation.E) {
                        lastOrientation = Orientation.S;
                    } else if (lastOrientation == Orientation.N) {
                        lastOrientation = Orientation.E;
                    } else if (lastOrientation == Orientation.S) {
                        lastOrientation = Orientation.W;
                    }
                    //break;


                case 'F':
                    if (lastOrientation == Orientation.W) {
                        X = lastX--;
                    } else if (lastOrientation == Orientation.E) {
                        X = lastX++;
                    } else if (lastOrientation == Orientation.N) {
                        Y = lastY++;
                    } else if (lastOrientation == Orientation.S) {
                        Y = lastY--;
                    }
                   // break;

                default:
                    result =  String.format("%s%s%s%s", lastX, lastY, lastOrientation, " ");
            }
        }
        return String.format("%s%s%s%s", X, Y, lastOrientation, " ");
    }

    public static void main(String[] args) throws IOException {
       Mower mower = new Mower();
       mower.executeCommand();
    }
}
