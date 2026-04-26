package Exercise;

import Table.*;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Exercise5 {
    private static Scanner scanner = new Scanner(System.in);
    static Random random = new Random();

    // 1. Viết lệnh cho phép người dùng nhập 3 số nguyên vào chương trình
    public static void question1()
    {
        System.out.println("Table.Question 1: Nhập 3 số nguyên");

        System.out.println("Nhập số thứ 1:");
        int a = scanner.nextInt();

        System.out.println("Nhập số thứ 2:");
        int b = scanner.nextInt();

        System.out.println("Nhập số thứ 3:");
        int c = scanner.nextInt();

        System.out.println("3 số vừa nhập là: " + a + ", " + b + ", " + c);
    }

    // 2. Viết lệnh cho phép người dùng nhập 2 số thực vào chương trình
    public static void question2()
    {
        System.out.println("Table.Question 2: Nhập 2 số thực");

        System.out.println("Nhập số thứ 1:");
        float a = scanner.nextFloat();

        System.out.println("Nhập số thứ 2:");
        float b = scanner.nextFloat();

        System.out.println("2 số vừa nhập là: " + a + ", " + b);
    }

    // 3.Viết lệnh cho phép người dùng nhập họ và tên
    public static void question3()
    {
        System.out.println("Table.Question 3: Nhập họ và tên");
        scanner.nextLine();

        String name = scanner.nextLine();
        System.out.println("Họ và tên: " + name);
    }

    // 4. Nhập ngày tháng năm sinh
    public static void question4()
    {
        System.out.println("Table.Question 4: Nhập ngày tháng năm sinh");

        System.out.println("Nhập ngày sinh: ");
        int day = scanner.nextInt();

        System.out.println("Nhập tháng sinh: ");
        int month = scanner.nextInt();

        System.out.println("Nhập năm sinh: ");
        int year = scanner.nextInt();

        System.out.println("Ngày tháng năm sinh là: " + day + "-" + month + "-" + year);
    }

    // Table.Question 5: Viết lệnh cho phép người dùng tạo account
    public static void question5(List<Account> accounts)
    {
        System.out.println("Question 5 : Tạo account");
        Account account = new Account();

        System.out.println("Nhập ID");
        int id = scanner.nextInt();

        System.out.println("Nhập username");
        String username = scanner.next();

        System.out.println("Nhập họ và tên: ");
        scanner.nextLine();
        String fullName = scanner.nextLine();

        account.position = new Position();
        int posId;
        do {
            System.out.println("Nhập ID position: ");
            posId = scanner.nextInt();

            if (posId == 1)
            {
                account.position.id = 1;
                account.position.name = Position.PositionName.DEV;
            }
            else if (posId == 2)
            {
                account.position.id = 2;
                account.position.name = Position.PositionName.TEST;
            }
            else if (posId == 3)
            {
                account.position.id = 3;
                account.position.name = Position.PositionName.SCRUM_MASTER;
            }
            else if (posId == 4)
            {
                account.position.id = 4;
                account.position.name = Position.PositionName.PM;
            }
            else System.out.println("Nhập lại");

        } while (posId < 1 || posId > 4);

        account.id = id;
        account.username = username;
        account.email = username + "@gmail.com";
        account.fullName = fullName;
        accounts.add(account);

        System.out.println("Tạo account với ID : " + account.id + ", username: " + account.username + ", email: " +
                account.email + ", fullName: " + account.fullName + ", Position: " + account.position.name);
    }

    // 6. Viết lệnh cho phép người dùng tạo department (viết thành method)
    public static void question6(List<Department> departments)
    {
        System.out.println("Tạo department: ");
        Department dept = new Department();

        System.out.println("Nhập department ID: ");
        dept.id = scanner.nextInt();

        System.out.println("Nhập tên phòng ban: ");
        scanner.nextLine();
        dept.name = scanner.nextLine();
        departments.add(dept);

        System.out.println("Department ID: " + dept.id + ", name: " + dept.name);
    }

    // 7. Nhập số chẵn
    public static void question7()
    {
        int n;
        while(true)
        {
            System.out.println("Nhập số chẵn: ");
            n = scanner.nextInt();
            if(n % 2 == 0)
            {
                System.out.println("Số bạn vừa nhập là: " + n);
                break;
            }
            else
            {
                System.out.println("Không phải số chẵn, nhập lại!");
            }
        }
    }

    // 8.
    public static void question8(List<Account> accounts, List<Department> departments)
    {
        System.out.println("mời bạn nhập vào chức năng muốn sử dụng");
        System.out.println("1. Tạo account");
        System.out.println("2. Tạo department");

        while(true) {
            int choice = scanner.nextInt();
            if (choice == 1) {
                question5(accounts);
                System.out.println("Đã tạo Account thành công!");
                break;
            } else if (choice == 2) {
                question6(departments);
                System.out.println("Đã tạo Department thành công!");
                break;
            }
            else
            {
                System.out.println("Nhập lại chức năng sử dụng!");
            }
        }
    }

    public static void question9(List<Account> accounts, List<Group> groups, List<GroupAccount> groupAccounts)
    {
        System.out.println("\n--- Danh sách Usernames ---");
        for (Account a : accounts) {
            System.out.println(a.username);
        }
        System.out.print("Nhập vào username của account: ");
        String inputUser = scanner.next();

        System.out.println("\n--- Danh sách Groups ---");
        for (Group g : groups) {
            System.out.println(g.name);
        }
        System.out.print("Nhập vào tên của group: ");
        scanner.nextLine();
        String inputGroup = scanner.nextLine();

        Account selectedAcc = null;
        for (Account a : accounts) {
            if (a.username.equals(inputUser)) {
                selectedAcc = a;
                break;
            }
        }

        Group selectedGroup = null;
        for (Group g : groups) {
            if (g.name.equals(inputGroup)) {
                selectedGroup = g;
                break;
            }
        }

        if (selectedAcc != null && selectedGroup != null) {
            GroupAccount ga = new GroupAccount();
            ga.account = selectedAcc;
            ga.group = selectedGroup;

            groupAccounts.add(ga); // Thêm vào list được truyền từ Main vào
            System.out.println("=> Đã thêm account [" + inputUser + "] vào group [" + inputGroup + "] thành công!");
        } else {
            System.out.println("Không tìm thấy thông tin phù hợp, vui lòng kiểm tra lại!");
        }
    }

    public static void question10(List<Account> accounts, List<Department> departments,
                                  List<Group> groups, List<GroupAccount> groupAccounts)
    {

        while(true)
        {
            System.out.println("mời bạn nhập vào chức năng muốn sử dụng");
            System.out.println("1. Tạo account");
            System.out.println("2. Tạo department");
            System.out.println("3. Thêm group vào account");
            int choice = scanner.nextInt();
            scanner.nextLine();

            if (choice == 1) {
                question5(accounts);
                System.out.println("Đã tạo Account thành công!");

            }
            else if (choice == 2) {
                question6(departments);
                System.out.println("Đã tạo Department thành công!");
            }
            else if (choice == 3)
            {
                question9(accounts, groups, groupAccounts);
            }
            else
            {
                System.out.println("Vui lòng chọn 1, 2 hoặc 3");
                continue;
            }

            System.out.print("\nBạn có muốn thực hiện chức năng khác không? (Y/n): ");
            String confirm = scanner.next();
            if(!confirm.equalsIgnoreCase("y"))
            {
                System.out.println("Thoát chương trình!");
                return;
            }
        }
    }

    public static void question11(List<Account> accounts, List<Department> departments,
                                  List<Group> groups, List<GroupAccount> groupAccounts)
    {

        while(true)
        {
            System.out.println("mời bạn nhập vào chức năng muốn sử dụng");
            System.out.println("1. Tạo account");
            System.out.println("2. Tạo department");
            System.out.println("3. Thêm group vào account");
            int choice = scanner.nextInt();
            scanner.nextLine();

            if (choice == 1) {
                question5(accounts);
                System.out.println("Đã tạo Account thành công!");

            }
            else if (choice == 2) {
                question6(departments);
                System.out.println("Đã tạo Department thành công!");
            }
            else if (choice == 3)
            {
                question9(accounts, groups, groupAccounts);
            }
            else if (choice == 4)
            {
                System.out.println("\n--- Danh sách Usernames ---");
                for (Account acc : accounts) System.out.println(acc.username);

                System.out.print("Nhập username của account: ");
                String inputUser = scanner.next();

                // Tìm account theo username
                Account selectedAcc = null;
                for (Account a : accounts) {
                    if (a.username.equals(inputUser)) {
                        selectedAcc = a;
                        break;
                    }
                }
                if (selectedAcc != null) {
                    // Bước 3: Chọn ngẫu nhiên 1 group
                    // random.nextInt(n) trả về giá trị từ 0 đến n-1 (giống rand() % n)
                    int randomIndex = random.nextInt(groups.size());
                    Group randomGroup = groups.get(randomIndex);

                    // Bước 4: Thêm vào groupAccount
                    GroupAccount ga = new GroupAccount();
                    ga.account = selectedAcc;
                    ga.group = randomGroup;
                    groupAccounts.add(ga);

                    System.out.println("=> Đã thêm user [" + inputUser + "] vào group ngẫu nhiên: [" + randomGroup.name + "]");
                } else {
                    System.out.println("Không tìm thấy Account!");
                }
            }
            else
            {
                System.out.println("Vui lòng chọn 1, 2, 3 hoặc 4");
                continue;
            }

            System.out.print("\nBạn có muốn thực hiện chức năng khác không? (Y/n): ");
            String confirm = scanner.next();
            if(!confirm.equalsIgnoreCase("y"))
            {
                System.out.println("Thoát chương trình!");
                return;
            }
        }
    }
}
