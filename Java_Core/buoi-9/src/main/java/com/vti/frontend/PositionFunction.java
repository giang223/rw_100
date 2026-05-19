package com.vti.frontend;

import com.vti.backend.controller.PositionController;
import com.vti.entity.Position;
import com.vti.enums.PositionName;

import java.util.List;
import java.util.Scanner;

public class PositionFunction {
    private static Scanner scanner = new Scanner(System.in);
    private PositionController controller = new PositionController();

    public void run()
    {
        while (true)
        {
            System.out.println("\n=== QUẢN LÝ CHỨC VỤ (POSITION) ===");
            System.out.println("1. Xem danh sách chức vụ");
            System.out.println("2. Thêm mới chức vụ");
            System.out.println("3. Update chức vụ");
            System.out.println("4. Xóa chức vụ");
            System.out.println("5. Thoát");
            String choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    List<Position> list = controller.findAll();
                    showsPosition(list);
                    break;
                case "2":
                    insertPosition();
                    break;
                case "3":
                    updatePosition();
                    break;
                case "4":
                    deletePosition();
                    break;
                case "5":
                    return;
                default:
                    System.out.println("Chọn sai, vui lòng chọn lại (1-6)!");
            }
        }
    }

    public static void showsPosition(List<Position> positions)
    {
        System.out.println("+-----+--------------------+");
        System.out.printf("|%5s|%20s|\n", "ID", "Tên Position");
        System.out.println("+-----+--------------------+");

        for (Position position : positions)
        {
            System.out.printf("|%5s|%20s|\n", position.getId(), position.getName());
        }

        if (positions.size() == 0)
        {
            System.out.println("Không tìm thấy");
        }
        System.out.println("+-----+--------------------+");
    }

    public void insertPosition() {
        System.out.println("Chọn tên chức vụ muốn thêm:");
        System.out.println("1. DEV  2. TEST  3. SCRUM_MASTER  4. PM");
        PositionName name = null;
        String input;
        while (true)
        {
            input = scanner.nextLine();
            switch (input) {
                case "1":
                    name = PositionName.DEV;
                    break;
                case "2":
                    name = PositionName.TEST;
                    break;
                case "3":
                    name = PositionName.SCRUM_MASTER;
                    break;
                case "4":
                    name = PositionName.PM;
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ!");
                    continue;
            }

            if (controller.checkExistNameAndIdNot(name, null))
            {
                System.out.println("Tên này đã được sử dụng, Nhập lại: ");
                continue;
            }

            break;
        }

        boolean check = controller.create(name.name());
        if (check) {
            System.out.println("Thêm mới thành công");
        }
    }

    private void deletePosition()
    {
        System.out.print("Nhập ID chức vụ cần xóa: ");
        int id;
        while(true)
        {
            id = scanner.nextInt();
            scanner.nextLine();

            if (id <= 0 ) {
                System.out.println("Nhập lai ID: ");
                continue;
            }

            if(!controller.checkExistID(id))
            {
                System.out.println("ID nay ko ton tai, nhap lai: ");
                continue;
            }
            break;
        }

        boolean check = controller.delete(id);
        if (check) {
            System.out.println("Xóa thành công");
        }
    }

    private void updatePosition()
    {
        System.out.print("Nhập ID chức vụ cần sửa: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Chọn chức vụ mới:");
        System.out.println("1. DEV  2. TEST  3. SCRUM_MASTER  4. PM");

        PositionName name = null;
        String input;
        while(true)
        {
            input = scanner.nextLine();

            switch (input) {
                case "1":
                    name = PositionName.DEV;
                    break;
                case "2":
                    name = PositionName.TEST;
                    break;
                case "3":
                    name = PositionName.SCRUM_MASTER;
                    break;
                case "4":
                    name = PositionName.PM;
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ!");
                    continue;
            }

            if (controller.checkExistNameAndIdNot(name, id))
            {
                System.out.println("Tên này đã được sử dụng, Nhập lại: ");
                continue;
            }
            break;
        }

        boolean check = controller.update(id, name.name());
        if (check) {
            System.out.println("Update thành công");
        }
    }
}
