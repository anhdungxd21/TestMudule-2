import io.file.IOReader;
import io.file.IOWriter;
import phonebook.Phone;

import java.util.ArrayList;

public class Display {
    private IOReader ioReader = IOReader.getInstance();
    private IOWriter ioWriter = IOWriter.getInstance();
    private String path = "data/contacts.csv";
    private ArrayList<Phone> phonebook = ioReader.readfile(path);
    private static volatile Display instance;


    private Display(){
        phonebook = ioReader.readfile(path);
    }

    public static Display getInstance(){
        if(instance == null){
            synchronized (Display.class){
                if(instance == null){
                    instance = new Display();
                }
            }
        }
        return instance;
    }

    public void bigMenu(){
        System.out.println("---- CHƯƠNG TRÌNH QUẢN LÝ DANH BẠ ----");
        System.out.println("Chọn chứ năng theo số (để tiếp tục):");
        System.out.println("1. Xen danh sách");
        System.out.println("2. Thêm mới");
        System.out.println("3. Cập nhật");
        System.out.println("4. Xóa");
        System.out.println("5. Tim kiến");
        System.out.println("6. Đọc từ file");
        System.out.println("7. Ghi vào file");
        System.out.println("8. Thoát");
        System.out.println("Chọn chức năng: ");
    }
    public void clearScreen(){
        for (int i = 0; i < 40; i++) {
            System.out.println();
        }
    }
    public int getArraySize(){
        return phonebook.size();
    }
    public void addToList(String number,String group,String name, String gender, String address,String birthday,String email){
        phonebook.add(new Phone(number,group,name,gender,address,birthday,email));
    }
    public ArrayList<Phone> getPhonebook(){
        return phonebook;
    }
    public void setPhonebook(ArrayList<Phone> phonebook) {
        this.phonebook = phonebook;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public void showList(){
        for (int i = 1; i <phonebook.size(); i++) {
            System.out.println(phonebook.get(i).toString());
        }
    }
    public void showList(int first, int last){
        IOReader ioReader = IOReader.getInstance();
        setPhonebook(ioReader.readfile(path));
        for (int i = first; i <= last; i++) {
            System.out.println(phonebook.get(i).toString());
        }
    }

    public void printFileList(){
        StringBuilder stringBuilder =  ioReader.printListFile("data");
        System.out.println(stringBuilder.toString());
    }
    public void changeFile(String path){

        phonebook = ioReader.readfile(path);
    }
    public void saveFile(String path){
        ioWriter.writeObject(phonebook, path);
    }
}
