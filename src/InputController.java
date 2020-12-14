import phonebook.Phone;

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
        String email = checkInput("^[a-z][a-z0-9_\\.]{5,32}@[a-z0-9]{2,}(\\.[a-z0-9]{2,4}){1,2}$","Nhập email");
        display.getPhonebook().add(new Phone(number,group,name,gender,address,birthday,email));
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
                break;
            case "4":
                break;
            case "5":
                break;
            case "6":
                break;
            case "7":
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
