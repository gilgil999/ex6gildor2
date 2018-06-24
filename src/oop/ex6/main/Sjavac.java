package oop.ex6.main;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

public class Sjavac {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("2");
            //ioexception
        }
        String path = args[0];
        try {
            BufferedReader inputStream = new BufferedReader("sdaf");


            ArrayList<String> lines= new ArrayList<String>();
            while((c=inputStream.re))
        }catch (IOException e){
            System.out.println("2");
        }
    }
}
