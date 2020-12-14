import phonebook.Phone;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InputController {
    private static Scanner scanner = new Scanner(System.in);
    private static volatile InputController instance;
    private Display display = Display.getInstance();


    private InputController(){}

    public static InputController getInstance(){
        if(instance == null){
            synchronized (InputController.class){
                if(instance == null){
                    instance = new InputController();
                }
            }
        }
        return instance;
    }
//    public void smallMenuControl(){
//        int first = 1;
//        int last = 5;
//        String choice;
//        int listSize = display.getArraySize();
//        if(listSize > last){
//            while(last < listSize){
//                display.showList(first, last);
//                choice = scanner.nextLine();
//                if(choice.length() > 0){
//
//                }else {
//                    display.showList(first, last);
//                    first = last;
//                    if (listSize > last + 5) {
//                        last += 5;
//                    } else {
//                        last = listSize - 1;
//                    }
//                }
//            }
//        }
//    }

    public void smallMenuControl(){
        display.showList();

    }
    public void addPhone(){
        String number = checkInput("0[0-9]{9}","Nhập số điện thoại: ");
        String group = checkInput("[a-zA-Z0-9 ]{1,}","Nhập nhóm: ");
        String name = checkInput("[a-zA-Z0-9 ]{1,}","Nhập tên: ");
        String gender = checkInput("([nN][aA][mM])|([nN][uU])","Nhập giới tính: ");
        String address = checkInput("[a-zA-Z0-9 ]{1,}","Nhập Địa chỉ: ");
        String dateRegex = "^(?:(?:31(\\/|-|\\.)(?:0?[13578]|1[02]))\\1|(?:(?:29|30)(\\/|-|\\.)(?:0?[13-9]|1[0-2])\\2))(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$|^(?:29(\\/|-|\\.)0?2\\3(?:(?:(?:1[6-9]|[2-9]\\d)?(?:0[48]|[2468][048]|[13579][26])|(?:(?:16|[2468][048]|[3579][26])00))))$|^(?:0?[1-9]|1\\d|2[0-8])(\\/|-|\\.)(?:(?:0?[1-9])|(?:1[0-2]))\\4(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$";
        String birthday = checkInput(dateRegex,"Nhập ngày sinh: ");
        String email = checkInput("^[a-z][a-z0-9_\\.]{5,32}@[a-z0-9]{2,}(\\.[a-z0-9]{2,4}){1,2}$","Nhập email: ");
        display.addToList(number,group,name,gender,address,birthday,email);
        bigMenuControl();

    }

    public void update(){
        System.out.print("Nhập số điện thoại cần sửa: ");
        String phoneNumber = scanner.nextLine();
        ArrayList<Phone> phones = Display.getInstance().getPhonebook();
        int index = -1;
        for (int i = 0; i < phones.size(); i++) {
            if(phoneNumber.equals(phones.get(i).getPhoneNumber())){
                index = i;
            }
        }
        if(index > 0){
            System.out.println(phones.get(index).toString());
            String number = checkInput("0[0-9]{9}","Nhập số điện thoại: ");
            String group = checkInput("[a-zA-Z0-9 ]{1,}","Nhập nhóm: ");
            String name = checkInput("[a-zA-Z0-9 ]{1,}","Nhập tên: ");
            String gender = checkInput("([nN][aA][mM])|([nN][uU])","Nhập giới tính: ");
            String address = checkInput("[a-zA-Z0-9 ]{1,}","Nhập Địa chỉ: ");
            String dateRegex = "^(?:(?:31(\\/|-|\\.)(?:0?[13578]|1[02]))\\1|(?:(?:29|30)(\\/|-|\\.)(?:0?[13-9]|1[0-2])\\2))(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$|^(?:29(\\/|-|\\.)0?2\\3(?:(?:(?:1[6-9]|[2-9]\\d)?(?:0[48]|[2468][048]|[13579][26])|(?:(?:16|[2468][048]|[3579][26])00))))$|^(?:0?[1-9]|1\\d|2[0-8])(\\/|-|\\.)(?:(?:0?[1-9])|(?:1[0-2]))\\4(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$";
            String birthday = checkInput(dateRegex,"Nhập ngày sinh: ");
            String email = checkInput("^[a-z][a-z0-9_\\.]{5,32}@[a-z0-9]{2,}(\\.[a-z0-9]{2,4}){1,2}$","Nhập email: ");
            phones.get(index).setPhoneNumber(number);
            phones.get(index).setGroup(group);
            phones.get(index).setName(name);
            phones.get(index).setGender(gender);
            phones.get(index).setAddress(address);
            phones.get(index).setBirthday(birthday);
            phones.get(index).setEmail(email);
        }else{
            System.out.println("Số điện thoại không tồn tại.");
        }
        bigMenuControl();
    }
    public void deleteNumber(){
        System.out.print("Nhập số điện thoại cần xóa: ");
        String phoneNumber = scanner.nextLine();
        ArrayList<Phone> phones = Display.getInstance().getPhonebook();
        int index = -1;
        for (int i = 0; i < phones.size(); i++) {
            if(phoneNumber.equals(phones.get(i).getPhoneNumber())){
                index = i;
            }
        }
        if(index > 0){
            System.out.println(phones.get(index).toString());
            System.out.println("Bạn có muốn xóa không?");
            System.out.println("1. Có");
            System.out.println("2. chọn bất kỳ để hủy");
            String choice = scanner.nextLine();
            switch (choice){
                case "1":
                    phones.remove(index);
                    break;
                default:
                    bigMenuControl();
            }
        }
        bigMenuControl();
    }
    public void searhNumeber(){
        System.out.print("Nhập số điện thoại cần xóa: ");
        String phoneNumber = scanner.nextLine();
        ArrayList<Phone> phones = Display.getInstance().getPhonebook();
        int index = -1;
        for (int i = 0; i < phones.size(); i++) {
            if(phoneNumber.equals(phones.get(i).getPhoneNumber())){
                index = i;
            }
        }
        if(index > 0){
            System.out.println(phones.get(index).toString());
        }else{
            System.out.println("Số điện thoại không tồn tại.");
        }
        bigMenuControl();
    }
    public void changeFile(){
        display.printFileList();
        System.out.println("Nhập đường dẫn file:");
        String path = scanner.nextLine();
        display.changeFile(path);
        bigMenuControl();
    }
    public void saveFile(){
        System.out.print("Nhập đường dẫn file: ");
        String path = scanner.nextLine();
        display.saveFile("data/" + path);
        bigMenuControl();
    }

    public void bigMenuControl(){
        display.bigMenu();
        String choice = scanner.nextLine();
        switch (choice){
            case "1":
                smallMenuControl();
                bigMenuControl();
                break;
            case "2":
                addPhone();
                break;
            case "3":
                update();
                break;
            case "4":
                deleteNumber();
                break;
            case "5":
                searhNumeber();
                break;
            case "6":
                changeFile();
                break;
            case "7":
                //save file
                saveFile();
                break;
            case "8":
                System.exit(0);
                break;
            default:
                bigMenuControl();
        }
    }
    // Private
    private String checkInput(String regex,String wanted){
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher;
        String someString;
        do {
            System.out.print(wanted);
            someString = scanner.nextLine();
            matcher = pattern.matcher(someString);
            if(!matcher.matches()){
                System.out.println("Bạn đã nhập sai, vui lòng nhập lại");
            }
        } while (!matcher.matches());

        return someString;
    }
}
