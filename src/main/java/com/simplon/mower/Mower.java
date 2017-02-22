package com.simplon.mower;


import java.io.*;

public class Mower {
    private int X ;
    private int Y ;
    private int X_MAX = 5;
    private int Y_MAX = 5;
    private Orientation orientation = Orientation.N;
    private char lastOrientation ;
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
            String myData;
            int getLine = 0;
            String result = "";

            while ((myData = bufferedReader.readLine()) != null) {
                if (!peer(getLine)) {
                    lastX = myData.charAt(0);
                    lastY = myData.charAt(1);
                    lastOrientation = myData.charAt(2);
                }
                else {
                    if(getLine!=0){
                        for (int i = 0; i < myData.length() - 1; i++) {
                            char instruction = myData.charAt(i);
                            switch (instruction) {
                                case 'L':
                                    if (lastOrientation == 'W') {
                                        orientation = Orientation.S;
                                    } else if (lastOrientation == 'E') {
                                        orientation = Orientation.N;
                                    } else if (lastOrientation == 'N') {
                                        orientation = Orientation.W;
                                    } else if (lastOrientation == 'S') {
                                        orientation = Orientation.E;
                                    }
                                    break;


                                case 'R':
                                    if (lastOrientation == 'W') {
                                        orientation = Orientation.N;
                                    } else if (lastOrientation == 'E') {
                                        orientation = Orientation.S;
                                    } else if (lastOrientation == 'N') {
                                        orientation = Orientation.E;
                                    } else if (lastOrientation == 'S') {
                                        orientation = Orientation.W;
                                    }
                                    break;


                                case 'F':
                                    if (lastOrientation == 'W') {
                                        X = lastX--;
                                    } else if (lastOrientation == 'E') {
                                        X = lastX++;
                                    } else if (lastOrientation == 'N') {
                                        Y = lastY++;
                                    } else if (lastOrientation == 'S') {
                                        Y = lastY--;
                                    }
                                    break;

                                default: result += String.format("%s%s%s%s", lastX, lastY, lastOrientation);
                            }

                        } //!for
                        result += String.format("%s%s%s%s", X, Y,orientation , " ");
                    }
                }
                getLine++;
            }
            System.out.println(result);
        }
            catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        Mower mower = new Mower();
        mower.executeCommand();
    }
}
