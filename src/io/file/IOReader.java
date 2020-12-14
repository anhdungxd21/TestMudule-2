package io.file;

import phonebook.Phone;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public final class IOReader {
    private static volatile IOReader instance;

    private IOReader(){}

    public static IOReader getInstance(){
        if(instance == null){
            synchronized (IOReader.class){
                if(instance == null){
                    instance = new IOReader();
                }
            }
        }
        return instance;
    }
    public ArrayList<Phone> readfile(String path){
        File file = new File(path);
        ArrayList<Phone> phones = null;
        BufferedReader bufferedReader = null;
        try{
            if(!file.exists()){
                System.out.println("file not found");
            }else{
                bufferedReader = new BufferedReader(new FileReader(file));
                phones = new ArrayList<Phone>();
                String[] values;
                String line;
                while ((line = bufferedReader.readLine())!=null){
                    values = line.split(",");
                    phones.add(new Phone(values[0],values[1],values[2],values[3],values[4],values[5],values[6]));
                }
            }

        }catch (IOException e){
            e.printStackTrace();
        }finally {
            if(bufferedReader != null){
                try{
                    bufferedReader.close();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }

        }
        return phones;
    }
    public StringBuilder printListFile(String path){
        File file = new File(path);
        StringBuilder stringBuilder = new StringBuilder();
        if(file.isDirectory()) {
            for (File file1 : file.listFiles()) {
                stringBuilder.append(file1.toString().substring(5)+",");
            }
        }
        return stringBuilder;
    }
}
