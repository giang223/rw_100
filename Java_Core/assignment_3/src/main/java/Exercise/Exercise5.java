package Exercise;

import Table.Department;

import java.util.List;

public class Exercise5 {

    // 1.In ra thông tin của phòng ban thứ 1 (sử dụng toString())
    public static void question1(Department[] departments)
    {
        System.out.println(departments[0].toString());
    }

    // 2.In ra thông tin của tất cả phòng ban (sử dụng toString())
    public static void question2(Department[] departments)
    {
        for(Department dep : departments)
        {
            System.out.println(dep.toString());
        }
    }

    // 3.In ra thông tin của tất cả phòng ban (sử dụng toString())
    public static void question3(Department[] departments)
    {
        System.out.println("Hashcode: " + departments[0].hashCode());
    }

    // 4.Kiểm tra xem phòng ban thứ 1 có tên là "Phòng A" không?
    public static void question4(Department[] departments)
    {
        String check = departments[0].name.equals("Phòng A") ? "Phòng A" : "Không phải phòng A";

        System.out.println("Tên phòng ban 1: " + check);
    }

    // 5.So sánh 2 phòng ban thứ 1 và phòng ban thứ 2 xem có bằng nhau không (bằng nhau khi tên của 2 phòng ban đó bằng nhau)
    public static void question5(Department[] departments)
    {
        String check = departments[0].equals(departments[1]) ? "Bằng" : "Không bằng";

        System.out.println("Department 1 " + check + " Department 2");
    }

    public static void question6(Department[] departments)
    {
        for(int i = 0; i < departments.length; i++)
        {
            for(int j = 0; j < departments.length; j++)
            {
                if (departments[i].name.compareTo(departments[j].name) < 0)
                {
                    Department temp = departments[i];
                    departments[i] = departments[j];
                    departments[j] = temp;
                }
            }
        }

        for (Department dep : departments)
        {
            System.out.println(dep);
        }
    }
}
