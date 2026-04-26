package Exercise;

import Table.Group;

import java.util.Scanner;

public class Exercise4 {
    private static Scanner scanner = new Scanner(System.in);

    // 1.Nhập một xâu kí tự, đếm số lượng các từ trong xâu kí tự đó (các từ có thể cách nhau bằng nhiều khoảng trắng );
    public static void question1()
    {
        System.out.print("Nhập 1 xâu ký tự: ");
        String s = scanner.nextLine().trim();

        String[] words = s.split("\\s+");
        System.out.println("Số lượng từ trong xâu là: " + words.length);
    }

    // 2. Nhập hai xâu kí tự s1, s2 nối xâu kí tự s2 vào sau xâu s1;
    public static void question2() {
        System.out.print("Nhập xâu s1: ");
        String s1 = scanner.nextLine();
        System.out.print("Nhập xâu s2: ");
        String s2 = scanner.nextLine();

        // Trong Java dùng toán tử + cực kỳ tiện
        String result = s1 + s2;
        System.out.println("Kết quả sau khi nối: " + result);
    }

    // 3.Viết chương trình để người dùng nhập vào tên và kiểm tra, nếu tên chưa viết hoa chữ cái đầu thì viết hoa lên
    public static void question3()
    {
        System.out.println("Nhập tên");
        String name = scanner.nextLine().trim();

        if (name.isEmpty())
        {
            System.out.println("Chưa điền tên!");
            return;
        }

        String result = name.substring(0, 1).toUpperCase() + name.substring(1);
        System.out.println("Tên vừa nhập là: " + result);
    }

    // 4.
    public static void question4()
    {
        System.out.print("Nhập tên: ");
        String name = scanner.nextLine().trim();

        for (int i = 0; i < name.length(); i++)
        {
            char c = name.toUpperCase().charAt(i);
            System.out.println("Ký tự thứ " + (i + 1) + " là: " + c);
        }
    }

    // 5.
    public static void question5()
    {
        System.out.print("Nhập họ: ");
        String firstName = scanner.nextLine().trim();

        System.out.print("Nhập tên: ");
        String lastName = scanner.nextLine().trim();

        System.out.println("Họ và tên đầy đủ: " + firstName + " " + lastName);
    }

    public static void question6()
    {
        System.out.print("Nhập họ tên đầy đủ: ");
        String fullName = scanner.nextLine().trim();

        String[] words = fullName.split("\\s+");

        if (words.length < 2) {
            System.out.println("Vui lòng nhập đầy đủ ít nhất Họ và Tên!");
            return;
        }

        System.out.println("Họ là: " + words[0]);

        if (words.length > 2) {
            System.out.print("Tên đệm là: ");
            for (int i = 1; i < words.length - 1; i++) {
                System.out.print(words[i] + " ");
            }
            System.out.println();
        }

        System.out.println("Tên là: " + words[words.length - 1]);
    }

    public static void question7()
    {
        System.out.print("Nhập họ tên cần chuẩn hóa: ");
        String input = scanner.nextLine().trim();

        {
            String result = input.replaceAll("\\s+", " ");
            System.out.println("Tên: " + result);
        }

        {
            String[] words = input.trim().split("\\s+");
            String result = "";
            for (String word : words)
            {
                String capitalized = word.substring(0, 1).toUpperCase()
                        + word.substring(1).toLowerCase();
                result += capitalized + " ";
            }

            System.out.println("Tên sau khi viết hoa: " + result);
        }
    }

    // 8. In ra tất cả các group có chứa chữ "Java"
    public static void question8()
    {
        System.out.println("In ra tất cả các group có chứa chữ \"Java\" \n");

        Group group1 = new Group();
        group1.name = "Java Freshers";

        Group group2 = new Group();
        group2.name = "C++ Advanced";

        Group group3 = new Group();
        group3.name = "Fullstack Java Web";

        Group[] groups = { group1, group2, group3 };

        for (Group group : groups) {
            // Dùng contains để check chuỗi con
            if (group.name.contains("Java")) {
                System.out.println(group.name);
            }
        }
    }

    // 9.In ra tất cả các group "Java"
    public static void question9()
    {
        System.out.println("In ra tất cả các group \"Java\"");

        Group g1 = new Group(); g1.name = "Java";
        Group g2 = new Group(); g2.name = "Python";
        Group g3 = new Group(); g3.name = "Java";

        Group[] groups = { g1, g2, g3 };

        for(int i = 0; i < groups.length; i++)
        {
            if(groups[i].name.equals("Java"))
            {
                System.out.println("Group thứ " + (i + 1));
            }
        }
    }
}
