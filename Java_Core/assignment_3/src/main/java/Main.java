import Exercise.Exercise1;
import Exercise.Exercise3;
import Exercise.Exercise4;
import Exercise.Exercise5;
import Table.Department;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
//        Exercise1.question4(3, 7);
//        Exercise3.question3();
//        Exercise4.question9();

        Department[] departments = new Department[5];
        departments[0] = new Department();
        departments[0].id = 1;
        departments[0].name = "Boss of director";

        departments[1] = new Department();
        departments[1].id = 2;
        departments[1].name = "Accounting";

        departments[2] = new Department();
        departments[2].id = 3;
        departments[2].name = "Waiting room";

        departments[3] = new Department();
        departments[3].id = 4;
        departments[3].name = "Marketing";

        departments[4] = new Department();
        departments[4].id = 5;
        departments[4].name = "Sale";

        //List<Department> departments = new ArrayList<>(List.of(dep1, dep2, dep3));
        // Exercise5.question5(departments);
        Exercise5.question6(departments);

    }


}
