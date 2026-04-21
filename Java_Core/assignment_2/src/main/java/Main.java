import java.text.DateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Main {
    public static void main(String[] args)
    {
        // 1.Department
        Department dep1 = new Department();
        dep1.id = 1;
        dep1.name = "Sale";

        Department dep2 = new Department();
        dep2.id = 2;
        dep2.name = "Marketing";

        Department dep3 = new Department();
        dep3.id = 3;
        dep3.name = "IT";

        // 2.Position
        Position pos1 = new Position();
        pos1.id = 1;
        pos1.name = Position.PositionName.DEV;

        Position pos2 = new Position();
        pos2.id = 2;
        pos2.name = Position.PositionName.TEST;

        Position pos3 = new Position();
        pos3.id = 3;
        pos3.name = Position.PositionName.PM;

        // 3. Account
        Account acc1 = new Account();
        acc1.id = 1;
        acc1.username = "vana";
        acc1.email = "vana@gmail.com";
        acc1.fullName = "Nguyen Van A";
        acc1.department = dep3;
        acc1.position = pos1;
        acc1.createDate = LocalDate.now();

        Account acc2 = new Account();
        acc2.id = 2;
        acc2.username = "vanb";
        acc2.email = "vanb@gmail.com";
        acc2.fullName = "Tran Van B";
        acc2.department = dep1;
        acc2.position = pos2;
        acc2.createDate = LocalDate.of(2021, 3, 15);

        Account acc3 = new Account();
        acc3.id = 3;
        acc3.username = "thic";
        acc3.email = "thic@gmail.com";
        acc3.fullName = "Le Thi C";
        acc3.department = dep2;
        acc3.position = pos3;
        acc3.createDate = LocalDate.of(2022, 5, 20);

        // 4. Group
        Group group1 = new Group();
        group1.id = 1;
        group1.name = "A";
        group1.creator = acc1;
        group1.createDate = LocalDate.now();

        Group group2 = new Group();
        group2.id = 2;
        group2.name = "B";
        group2.creator = acc1;
        group2.createDate = LocalDate.now();

        Group group3 = new Group();
        group3.id = 3;
        group3.name = "C";
        group3.creator = acc3;
        group3.createDate = LocalDate.now();

        // 5. Group Account
        GroupAccount ga1 = new GroupAccount();
        ga1.group = group1;
        ga1.account = acc3;
        ga1.joinDate = LocalDate.now();

        GroupAccount ga2 = new GroupAccount();
        ga2.group = group1;
        ga2.account = acc3;
        ga2.joinDate = LocalDate.now();

        GroupAccount ga3 = new GroupAccount();
        ga3.group = group2;
        ga3.account = acc3;
        ga3.joinDate = LocalDate.now();

        // 6. Type Question
        TypeQuestion type1 = new TypeQuestion();
        type1.id = 1;
        type1.name = TypeQuestion.TypeQuestionName.ESSAY;

        TypeQuestion type2 = new TypeQuestion();
        type2.id = 2;
        type2.name = TypeQuestion.TypeQuestionName.MULTIPLE_CHOICE;

        // 7. CategoryQuestion
        CategoryQuestion cat1 = new CategoryQuestion();
        cat1.id = 1;
        cat1.name = "Java";

        CategoryQuestion cat2 = new CategoryQuestion();
        cat2.id = 2;
        cat2.name = "SQL";

        CategoryQuestion cat3 = new CategoryQuestion();
        cat3.id = 3;
        cat3.name = "Ruby";

        // 8. Question
        Question q1 = new Question();
        q1.id = 1;
        q1.content = "Java là gì?";
        q1.category = cat1;
        q1.type = type1;
        q1.creator = acc1;
        q1.createDate = LocalDate.now();

        Question q2 = new Question();
        q2.id = 2;
        q2.content = "SQL là gì?";
        q2.category = cat2;
        q2.type = type2;
        q2.creator = acc2;
        q2.createDate = LocalDate.now();

        Question q3 = new Question();
        q3.id = 3;
        q3.content = "Ruby là gì?";
        q3.category = cat3;
        q3.type = type1;
        q3.creator = acc3;
        q3.createDate = LocalDate.now();

        // 9.Answer
        Answer ans1 = new Answer();
        ans1.id = 1;
        ans1.content = "A";
        ans1.question = q1;
        ans1.isCorrect = true;

        Answer ans2 = new Answer();
        ans2.id = 2;
        ans2.content = "B";
        ans2.question = q2;
        ans2.isCorrect = true;

        Answer ans3 = new Answer();
        ans3.id = 3;
        ans3.content = "C";
        ans3.question = q3;
        ans3.isCorrect = false;

        // 10. Exam
        Exam ex1 = new Exam();
        ex1.id = 1;
        ex1.code = "A1B2C";
        ex1.title = "Java";
        ex1.category = cat1;
        ex1.duration = 60;
        ex1.creator = acc1;
        ex1.createDate = LocalDate.now();

        Exam ex2 = new Exam();
        ex2.id = 2;
        ex2.code = "XYZ99";
        ex2.title = "SQL";
        ex2.category = cat2;
        ex2.duration = 45;
        ex2.creator = acc2;
        ex2.createDate = LocalDate.now();

        Exam ex3 = new Exam();
        ex3.id = 3;
        ex3.code = "R4B7K1";
        ex3.title = "Ruby";
        ex3.category = cat3;
        ex3.duration = 90;
        ex3.creator = acc3;
        ex3.createDate = LocalDate.now();

        // 11. Exam Question
        ExamQuestion eq1 = new ExamQuestion();
        eq1.exam = ex1;
        eq1.question = q1;

        ExamQuestion eq2 = new ExamQuestion();
        eq2.exam = ex2;
        eq2.question = q2;

        ExamQuestion eq3 = new ExamQuestion();
        eq3.exam = ex3;
        eq3.question = q3;

        Department[] departments = {dep1, dep2, dep3};
        Position[] positions = {pos1, pos2, pos3};
        Account[] accounts = {acc1, acc2, acc3};
        GroupAccount[] groupAccounts = {ga1, ga2, ga3};
        Group[] groups = {group1, group2, group3};
        TypeQuestion[] typeQuestions = {type1, type2};
        CategoryQuestion[] categoryQuestions = {cat1, cat2, cat3};
        Question[] questions = {q1, q2, q3};
        Answer[] answers = {ans1, ans2, ans3};
        Exam[] exams = {ex1, ex2, ex3};
        ExamQuestion[] examQuestions = { eq1, eq2, eq3};

        /* 1. Kiểm tra account thứ 2
            Nếu không có phòng ban (tức là department == null) thì sẽ in ra text
            "Nhân viên này chưa có phòng ban"
            Nếu không thì sẽ in ra text "Phòng ban của nhân viên này là …"  */
        if(accounts[1].department == null)
        {
            System.out.println("Nhân viên này chưa có phòng ban");
        }
        else
        {
            System.out.println("Phòng ban của nhân viên này là " + accounts[1].department.name);
        }

        /* 2. Kiểm tra account thứ 2
            Nếu không có group thì sẽ in ra text "Nhân viên này chưa có group"
            Nếu có mặt trong 1 hoặc 2 group thì sẽ in ra text "Group của nhân viên này là Java Fresher, C# Fresher"
            Nếu có mặt trong 3 Group thì sẽ in ra text "Nhân viên này là người quan trọng, tham gia nhiều group"
            Nếu có mặt trong 4 group trở lên thì sẽ in ra text "Nhân viên này là người hóng chuyện, tham gia tất cả các group"
        */
        {
            int countGroup = 0;
            for(GroupAccount ga : groupAccounts)
            {
                if(ga.account == accounts[1])
                    countGroup++;
            }

            if (countGroup == 0)
                System.out.println("Nhân viên này chưa có group");

            else if (countGroup == 1 || countGroup == 2)
                System.out.println("Group của nhân viên này là Java Fresher, C# Fresher");

            else if (countGroup == 3)
                System.out.println("Nhân viên này là người quan trọng, tham gia nhiều group");
            else
                System.out.println("Nhân viên này là người hóng chuyện, tham gia tất cả các group");
        }

        // 3.Sử dụng toán tử ternary để làm Question 1
        String message = (accounts[1].department == null)
                ? "Nhân viên này chưa có phòng ban"
                : "Phòng ban của nhân viên này là " + accounts[1].department.name;

        System.out.println(message);

        /* 4.Sử dụng toán tử ternary để làm yêu cầu sau:
        Kiểm tra Position của account thứ 1
        Nếu Position = Dev thì in ra text "Đây là Developer"
        Nếu không phải thì in ra text "Người này không phải là Developer"
        */
        // Lấy account thứ nhất từ mảng

        String result = (accounts[0].position.name == Position.PositionName.DEV)
                ? "Đây là Developer"
                : "Người này không phải là Developer";
        System.out.println(result);

        // 5.Lấy ra số lượng account trong nhóm thứ 1 và in ra theo format sau: Nếu số lượng account = 1 thì in ra "Nhóm có một thành viên"
        //Nếu số lượng account = 2 thì in ra "Nhóm có hai thành viên"
        //Nếu số lượng account = 3 thì in ra "Nhóm có ba thành viên"
        //Còn lại in ra "Nhóm có nhiều thành viên"
        {
            int countAccInGroup = 0;

            for (GroupAccount ga : groupAccounts) {
                if (ga.group == groups[0]) {
                    countAccInGroup++;
                }
            }

            switch (countAccInGroup) {
                case 1:
                    System.out.println("Nhóm có một thành viên");
                    break;
                case 2:
                    System.out.println("Nhóm có hai thành viên");
                    break;
                case 3:
                    System.out.println("Nhóm có ba thành viên");
                    break;
                default:
                    System.out.println("Nhóm có nhiều thành viên");
                    break;
            }
        }

        // 6. Sử dụng switch case để làm lại Question 2
        {
            int countGroup = 0;
            for(GroupAccount ga : groupAccounts)
            {
                if(ga.account == accounts[1])
                    countGroup++;
            }
            switch (countGroup) {
                case 0:
                    System.out.println("Nhân viên này chưa có group");
                    break;
                case 1:
                case 2:
                    System.out.println("Group của nhân viên này là Java Fresher, C# Fresher");
                    break;
                case 3:
                    System.out.println("Nhân viên này là người quan trọng, tham gia nhiều group");
                    break;
                default:
                    System.out.println("Nhân viên này là người hóng chuyện, tham gia tất cả các group");
                    break;
            }
        }

        // 7.Sử dụng switch case để làm lại Question 4
        Position.PositionName posName = accounts[0].position.name;

        switch (posName) {
            case DEV:
                System.out.println("Đây là Developer");
                break;
            default:
                System.out.println("Người này không phải là Developer");
                break;
        }

        // 8.In ra thông tin các account bao gồm: Email, FullName và tên phòng ban của họ
        for (Account acc : accounts) {
            String depName = (acc.department == null) ? "Chưa có phòng ban" : acc.department.name;
            System.out.println("Email: " + acc.email + " | FullName: " + acc.fullName + " | Phòng ban: " + depName);
        }

        // 9. In ra thông tin các phòng ban bao gồm: id và name
        for (Department dep : departments) {
            System.out.println("ID: " + dep.id + " | Name: " + dep.name);
        }

        // 10.
        for (int i = 0; i < accounts.length; i++){
            System.out.println("Thông tin account thứ " + (i + 1) + " là:");
            System.out.println("Email: " + accounts[i].email);
            System.out.println("Full name: " + accounts[i].fullName);

            String depName = (accounts[i].department == null) ? "Chưa có phòng ban" : accounts[i].department.name;
            System.out.println("Phòng ban: " + depName);

            System.out.println("");
        }

        // 11.
        for (int i = 0; i < departments.length; i++) {
            System.out.println("Thông tin department thứ " + (i + 1) + " là:");
            System.out.println("Id: " + departments[i].id);
            System.out.println("Name: " + departments[i].name);
        }

        // 12.
        for (int i = 0; i < departments.length; i++) {
            if (i == 2) {
                break;
            }
            System.out.println("Thông tin department thứ " + (i + 1) + " là:");
            System.out.println("Id: " + departments[i].id);
            System.out.println("Name: " + departments[i].name);
            System.out.println("");
        }

        // 13.
        for (int i = 0; i < accounts.length; i++) {
            if (i == 1) {
                i++;
                continue;
            }
            System.out.println("Thông tin account thứ " + (i + 1) + " là:");
            System.out.println("Email: " + accounts[i].email);
            System.out.println("Full name: " + accounts[i].fullName);
            System.out.println("");
        }

        // 14.
        for (int i = 0; i < accounts.length; i++) {
            if (accounts[i].id < 4) {
                System.out.println("Thông tin account thứ " + (i + 1) + " là:");
                System.out.println("Email: " + accounts[i].email);
                System.out.println("Full name: " + accounts[i].fullName);
                System.out.println("");
            }
        }

        // 16.Làm lại các Question ở phần FOR bằng cách sử dụng WHILE kết hợp với lệnh break, continue
        {
            System.out.println("16.");
            {
                //. Làm lại câu 10
                int i = 0;
                while (i < accounts.length) {
                    System.out.println("Thông tin account thứ " + (i + 1) + " là:");
                    System.out.println("Email: " + accounts[i].email);
                    System.out.println("Full name: " + accounts[i].fullName);
                    System.out.println("");
                    i++;
                }
            }
            {
                //. Làm lại câu 11
                int i = 0;
                while(i < departments.length){
                    System.out.println("Thông tin department thứ " + (i + 1) + " là:");
                    System.out.println("Id: " + departments[i].id);
                    System.out.println("Name: " + departments[i].name);
                    i++;
                }
            }
            {
                //. Làm lại câu 12
                int i = 0;
                while(i < departments.length) {
                    if (i == 2) {
                        break;
                    }
                    System.out.println("Thông tin department thứ " + (i + 1) + " là:");
                    System.out.println("Id: " + departments[i].id);
                    System.out.println("Name: " + departments[i].name);
                    System.out.println("");
                    i++;
                }
            }
            {
                //. Làm lại câu 13
                int i = 0;
                while(i < departments.length) {
                    if (i == 2) {
                        i++;
                        continue;
                    }
                    System.out.println("Thông tin department thứ " + (i + 1) + " là:");
                    System.out.println("Id: " + departments[i].id);
                    System.out.println("Name: " + departments[i].name);
                    System.out.println("");
                    i++;
                }
            }
            {
                // Làm lại câu 14
                int i = 0;
                while(i < accounts.length) {
                    if (accounts[i].id < 4) {
                        System.out.println("Thông tin account thứ " + (i + 1) + " là:");
                        System.out.println("Email: " + accounts[i].email);
                        System.out.println("Full name: " + accounts[i].fullName);
                        System.out.println("");
                        i++;
                    }
                }
            }
        }

        // 17.Làm lại các Question ở phần FOR bằng cách sử dụng DO-WHILE kết hợp với lệnh break, continue
        System.out.println("17.");
        {
            {
                //. Làm lại câu 10
                int i = 0;
                do {
                    System.out.println("Thông tin account thứ " + (i + 1) + " là:");
                    System.out.println("Email: " + accounts[i].email);
                    System.out.println("Full name: " + accounts[i].fullName);
                    System.out.println("");
                    i++;
                } while (i < accounts.length);
            }
            {
                //. Làm lại câu 11
                int i = 0;
                do {
                    System.out.println("Thông tin department thứ " + (i + 1) + " là:");
                    System.out.println("Id: " + departments[i].id);
                    System.out.println("Name: " + departments[i].name);
                    i++;
                }while(i < departments.length);
            }
            {
                //. Làm lại câu 12
                int i = 0;
                do {
                    if (i == 2) {
                        break;
                    }
                    System.out.println("Thông tin department thứ " + (i + 1) + " là:");
                    System.out.println("Id: " + departments[i].id);
                    System.out.println("Name: " + departments[i].name);
                    System.out.println("");
                    i++;
                }while(i < departments.length);
            }
            {
                //. Làm lại câu 13
                int i = 0;
                do {
                    if (i == 2) {
                        i++;
                        continue;
                    }
                    System.out.println("Thông tin department thứ " + (i + 1) + " là:");
                    System.out.println("Id: " + departments[i].id);
                    System.out.println("Name: " + departments[i].name);
                    System.out.println("");
                    i++;
                }while(i < departments.length);
            }
            {
                // Làm lại câu 14
                int i = 0;
                do {
                    if (accounts[i].id < 4) {
                        System.out.println("Thông tin account thứ " + (i + 1) + " là:");
                        System.out.println("Email: " + accounts[i].email);
                        System.out.println("Full name: " + accounts[i].fullName);
                        System.out.println("");
                        i++;
                    }
                } while(i < accounts.length);
            }
        }

        // ---------- Exercise 2 -----------------
        int a = 5;
        System.out.printf("%d%n", a);

        int b = 100000000;
        System.out.printf("%,d%n", b);

        float c = 5.567098f;
        System.out.printf("%.4f%n", c);

        String name = "Nguyễn Văn A";
        System.out.printf("Tên tôi là \"%s\" và tôi đang độc thân.%n", name);

        // 5. Lấy thời gian bây giờ và in ra theo định dạng sau:
        //24/04/2020 11h:16p:20s
        {
            LocalDateTime now = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH'h':mm'p':ss's'");
            System.out.println(now.format(formatter));
        }

        //6. In ra thông tin account (như Question 8 phần FOREACH) theo định dạng table (giống trong Database)
        System.out.printf("%-20s | %-20s | %-20s%n", "Email", "Full Name", "Department");
        System.out.println("-----------------------------------------------------------------------");

        for (Account acc : accounts) {
            String depName = (acc.department == null) ? "N/A" : acc.department.name;
            System.out.printf("%-20s | %-20s | %-20s%n", acc.email, acc.fullName, depName);
        }

        // ---------- Exercise 3 -----------------
        {
            // 1.In ra thông tin Exam thứ 1 và property create date sẽ được format theo định dạng vietnamese
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy", new Locale("vi", "VN"));
            System.out.println("Thông tin Exam 1:");
            System.out.println("ID: " + ex1.id);

            System.out.println("Ngày tạo: " + ex1.createDate.format(formatter));
        }
        {
            // In ra thông tin: Exam đã tạo ngày nào theo định dạng
            // Năm – tháng – ngày – giờ – phút – giây
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy – MM – dd – HH – mm – ss");
            for(Exam ex : exams)
            {
                System.out.println("Exam ID: " + ex.id);
                System.out.println("Ngày tạo: " + ex.createDate.atStartOfDay().format(formatter));

                System.out.println("");
            }
        }
        {
            // 3.Chỉ in ra năm của create date
            DateTimeFormatter yearFormatter = DateTimeFormatter.ofPattern("yyyy");
            for(Exam ex : exams)
            {
                System.out.println("Exam ID: " + ex.id);
                System.out.println("Năm tạo: " + ex.createDate.format(yearFormatter));

                System.out.println("");
            }
        }
        {
            DateTimeFormatter monthYearFormatter = DateTimeFormatter.ofPattern("MM/yyyy");
            // 4.Chỉ in ra tháng và năm của create date
            for(Exam ex : exams) {
                System.out.println("Tháng/Năm tạo: " + ex.createDate.format(monthYearFormatter));
                System.out.println("");
            }
        }
        {
            // 5.
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd");
            for(Exam ex : exams)
            {
                System.out.println("Tháng-Ngày tạo: " + ex.createDate.format(formatter));
                System.out.println("");
            }
        }
    }
}
