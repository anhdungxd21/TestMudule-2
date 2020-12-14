package io.file;

import phonebook.Phone;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class IOWriter {
    public static volatile IOWriter ioWriter;

    private IOWriter(){}

    public static IOWriter getInstance(){
        if(ioWriter == null){
            synchronized (IOWriter.class){
                if(ioWriter == null){
                    ioWriter = new IOWriter();
                }
            }
        }
        return ioWriter;
    }
    public void writeObject(ArrayList<Phone> arrayList, String path){
        File file = new File(path);
        BufferedWriter bufferedWriter = null;
        try{
            if(!file.exists()){
                file.createNewFile();
            }
            bufferedWriter = new BufferedWriter(new FileWriter(file));
            for(Phone phone: arrayList){
                bufferedWriter.append(phone.toString());
                bufferedWriter.append("\n");
            }
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            if(bufferedWriter != null){
                try {
                    bufferedWriter.close();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
    }
}
