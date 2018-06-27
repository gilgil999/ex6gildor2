package oop.ex6;

import oop.ex6.parser.MainParser;
import oop.ex6.parser.TypeOneException;
import oop.ex6.validator.GlobalSegment;
import oop.ex6.validator.ScopeObj;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Sjavac {
    /**
     * this is the main class of the project
     * @param args
     */
    public static void main(String[] args) {

        if (args.length != 1) {
            System.out.println("2");
            System.err.println("Please specify the path");
            return;
        }
        String path = args[0];
        try{
            ArrayList<String> lines = fileToText(path);
            lines = MainParser.primaryParsing(lines);
            GlobalSegment globalSegment = MainParser.parse(lines);
            globalSegment.isValid(new ScopeObj());
            System.out.println("0");

        }catch (IOException e ){
            System.out.println("2");
            System.err.println("Failed to read file, wrong path");
        }catch (TypeOneException e){
            System.out.println("1");
        }

    }

    /**
     * this function convert text file to String's array.
     *
     * @param path the absolute/relative path of the file.
     * @return array of strings that consist of the text lines.
     * @throws IOException - if the given path did not match an actual file to read
     */
    public static ArrayList<String> fileToText(String path) throws IOException {
        FileReader reader = new FileReader(path); // File not found exception if not file - Type 2 error
        BufferedReader in = new BufferedReader(reader);
        String line;
        ArrayList<String> list = new ArrayList<>();
        while ((line = in.readLine()) != null) { // if there are new lines - add them.
            list.add(line);
        }
        return list;
    }
}
