package Exercise;

public class Exercise3 {
    // 1.Khởi tạo lương có datatype là Integer có giá trị bằng 5000.
    // Sau đó convert lương ra float và hiển thị lương lên màn hình (với số float có 2 số sau dấu thập phân)
    public static void question1()
    {
        Integer salary = 5000;
        float floatSalary = (float)salary;

        System.out.printf("Lương sau khi convert: %.2f\n", floatSalary);
    }

    // 2.Khai báo 1 String có value = "1234567"
    // Hãy convert String đó ra số int
    public static void question2()
    {
        String s = "1234567";
        int num = Integer.parseInt(s);

        System.out.println("Số int sau khi convert từ String:" + num);
    }

    // 3.Khởi tạo 1 số Integer có value là chữ "1234567"
    // Sau đó convert số trên thành datatype int
    public static void question3()
    {
        Integer numObj = Integer.valueOf("1234567");

        int num = numObj.intValue();
        System.out.println("Số int từ Integer Object: " + num);
    }
}
