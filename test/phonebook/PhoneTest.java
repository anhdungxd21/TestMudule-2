package phonebook;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PhoneTest {
    @Test
    void toStringBook(){
        Phone phone = new Phone("0971234567","CodeGym","Nguyễn Văn A","Nam","Mỹ Đình - Hà Nội","1989-01-01","vana@codegym.vn");

    }
}