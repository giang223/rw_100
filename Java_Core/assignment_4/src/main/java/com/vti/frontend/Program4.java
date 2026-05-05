package com.vti.frontend;

import com.vti.entity.Student;

public class Program4 {
    public static void main(String[] args) {
        Student student1 = new Student("Nguyễn Văn A", "Hà Nội");
        System.out.println("Khởi tạo");
        student1.printInfo();

        student1.setScore(5.5f);
        System.out.println("Sau khi set điểm: ");
        student1.printInfo();

        student1.addScore(3.0f); // 5.5 + 3.0 = 8.5
        System.out.println("Sau khi cộng điểm: ");
        student1.printInfo();
    }
}
