import java.time.LocalDate;

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
        acc1.fullName = "Nguyen Van A";
        acc1.department = dep3;
        acc1.position = pos1;
        acc1.createDate = LocalDate.now();

        Account acc2 = new Account();
        acc2.id = 2;
        acc2.username = "vanb";
        acc2.fullName = "Tran Van B";
        acc2.department = dep1;
        acc2.position = pos2;
        acc2.createDate = LocalDate.of(2021, 3, 15);

        Account acc3 = new Account();
        acc3.id = 3;
        acc3.username = "thic";
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
        ga1.account = acc1;
        ga1.joinDate = LocalDate.now();

        GroupAccount ga2 = new GroupAccount();
        ga2.group = group1;
        ga2.account = acc2;
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

        // In Thông tin
        System.out.println("1. Department: ID = " + dep1.id + ", Name = " + dep1.name);
        System.out.println("2. Position: ID = " + pos1.id + ", Name = " + pos1.name);
        System.out.println("3. Account: ID = " + acc1.id + ", Username = " + acc1.username +
                ", FullName = " + acc1.fullName + ", Department = " + acc1.department.name +
                ", Position = " + acc1.position.name + ", CreatedDate = " + acc1.createDate);
        System.out.println("4. Group: ID = " + group1.id + ", Name = " + group1.name +
                ", Creator = " + group1.creator.fullName + ", CreatedDate = " + group1.createDate);
        System.out.println("5. GroupAccount: Group = " + ga1.group.name + ", Account = " + ga1.account.username +
                ", JoinDate = " + ga1.joinDate);
        System.out.println("6. TypeQuestion: ID = " + type1.id + ", Name = " + type1.name);
        System.out.println("7. CategoryQuestion: ID = " + cat1.id + ", Name = " + cat1.name);
        System.out.println("8. Question: ID = " + q1.id + ", Content = " + q1.content +
                ", Category = " + q1.category.name + ", Type = " + q1.type.name +
                ", Creator = " + q1.creator.fullName);
        System.out.println("9. Answer: ID = " + ans1.id + ", Content = " + ans1.content +
                ", Question = " + ans1.question.content + ", Correct = " + ans1.isCorrect);
        System.out.println("10. Exam: ID = " + ex1.id + ", Code = " + ex1.code + ", Title = " + ex1.title +
                ", Category = " + ex1.category.name + ", Duration = " + ex1.duration + "m" +
                ", Creator = " + ex1.creator.fullName);
        System.out.println("11. ExamQuestion: Exam Title = " + eq1.exam.title + ", Question Content = " + eq1.question.content);
    }
}
