package com.simplon.mower;


import java.io.*;

public class Mower {
    private int X = 0;
    private int Y = 0;
    private int X_MAX;
    private int Y_MAX;
    private Orientation orientation = Orientation.N;


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
            char lastOrientation = ' ';
            Orientation orientation = Orientation.N;

            while ((myData = bufferedReader.readLine()) != null) {
                if (!peer(getLine)) {
                    X = (int)(myData.charAt(0));
                    Y = (int)(myData.charAt(1));
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
                                        X--;
                                    } else if (lastOrientation == 'E') {
                                        X++;
                                    } else if (lastOrientation == 'N') {
                                        Y++;
                                    } else if (lastOrientation == 'S') {
                                        Y--;
                                    }
                                    break;

                                default:
                            }

                        } //!for
                        result += String.format("%s%s%s", X, Y,orientation) + " ";
                    }
                }
                getLine++;
            }

            System.out.println(result);   }
            catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        Mower mower = new Mower();
        //System.out.println(mower.moveToLeft() + mower.moveToRight());
        mower.executeCommand();
    }
}
