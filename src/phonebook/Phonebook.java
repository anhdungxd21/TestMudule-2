package phonebook;

import java.util.ArrayList;

public final class Phonebook {
    private static volatile Phonebook instance;
    private ArrayList<Phone> phones = new ArrayList<Phone>();

    private Phonebook(){

    }

    public static Phonebook getInstance(){
        if(instance == null){
            synchronized (Phonebook.class){
                if(instance == null){
                    instance = new Phonebook();
                }
            }
        }
        return instance;
    }
    public ArrayList<Phone> getPhones(){
        return phones;
    }
}
