package com.damian.bodzioch;

import java.io.*;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ArrayList<String> strNoSpaceArray = new ArrayList<>();
        ArrayList<String> originalStrArray = new ArrayList<>();

        readFile(strNoSpaceArray, originalStrArray);
        writeToFile(strNoSpaceArray, originalStrArray);
    }

    public static void readFile(ArrayList<String> list, ArrayList<String> list2){
        try {
            BufferedReader reader = new BufferedReader(new FileReader("input.txt"));
            String str;
            while ((str = reader.readLine()) != null) {
                list2.add(str);
                String[] strTab = str.toLowerCase().split(" ");
                str = "";
                for (String element : strTab){
                    str += element;
                }
                list.add(str);
            }

        }catch (FileNotFoundException noFile){
            System.out.println("Nie znaleziono pliku!");
        }catch (IOException dataReadError){
            System.out.println("Nie udało się odczytać pliku!");
        }
    }

    public static void writeToFile(ArrayList<String> list, ArrayList<String> list2){
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter("wyniki.txt"));
        }catch (IOException noFile){
            System.out.println("Nie udało się zapisać pliku!");
        }

        boolean isPalindorme;
        for (int i = 0; i < list.size(); i++){
            isPalindorme = true;
            for (int j = 0; j < list.get(i).length() / 2; j++){
                if (list.get(i).charAt(j) != list.get(i).charAt(list.get(i).length() - 1 - j)){
                    isPalindorme = false;
                    break;
                }
            }
            try {
                if (isPalindorme){
                    writer.write(list2.get(i));
                    writer.newLine();
                }
            }catch (IOException noSaveLine){
                System.out.println("Nie udało się zapisać linii: " + list2.get(i));
            }catch (NullPointerException isNull){
                System.out.println("Błąd zapisu");
                System.exit(0);
            }
        }
        try {
            writer.close();
        }catch (IOException err){
            err.printStackTrace();
        }
    }
}

