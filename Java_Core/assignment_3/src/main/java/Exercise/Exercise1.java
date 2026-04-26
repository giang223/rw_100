package Exercise;

import Table.Account;

import java.util.Random;

public class Exercise1 {
    // 1.
    public static  void question1(Account acc1, Account acc2)
    {
        acc1.salary =  5240.5f;
        acc2.salary = 10970.055f;

        int roundSalary1 = (int) acc1.salary;
        int roundSalary2 = (int) acc2.salary;

        System.out.println("Lương Account 1 làm tròn: " + roundSalary1);
        System.out.println("Lương Account 2 làm tròn: " + roundSalary2);
    }

    // 2.Lấy ngẫu nhiên 1 số có 5 chữ số (những số dưới 5 chữ số thì sẽ thêm có số 0 ở đầu cho đủ 5 chữ số)
    public static int question2()
    {
        Random random = new Random();
        int num = random.nextInt(100000);

        System.out.printf("Số ngẫu nhiên: %05d\n", num);

        return num;
    }

    public static void question3()
    {
        int num = question2();
        int last2 = num % 100;
        System.out.printf("2 số cuối là: %02d\n", last2);
    }

    // 4.Viết 1 method nhập vào 2 số nguyên a và b và trả về thương của chúng
    public static void question4(int a, int b)
    {
        if(b == 0)
        {
            System.out.println("Phần mẫu không thể là 0");
        }
        double result = (double) a / b;

        System.out.printf("Thương của %d và %d là: %.2f\n", a, b, result);
    }
}
