package com.vti.frontend;

import com.vti.backend.controller.PositionController;
import com.vti.entity.Position;

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
        String name = "";
        String input = scanner.nextLine();
        switch (input) {
            case "1":
                name = "DEV";
                break;
            case "2":
                name = "TEST";
                break;
            case "3":
                name = "SCRUM_MASTER";
                break;
            case "4":
                name = "PM";
                break;
            default:
                System.out.println("Lựa chọn không hợp lệ!");
                return;
        }

        boolean check = controller.create(name);
        if (check) {
            System.out.println("Thêm chức vụ thành công!");
        } else {
            System.out.println("Thêm thất bại!");
        }
    }

    private void deletePosition()
    {
        System.out.print("Nhập ID chức vụ cần xóa: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        boolean check = controller.delete(id);
        if (check) {
            System.out.println("Xóa thành công!");
        } else {
            System.out.println("Xóa thất bại!");
        }
    }

    private void updatePosition()
    {
        System.out.print("Nhập ID chức vụ cần sửa: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Chọn chức vụ mới:");
        System.out.println("1. DEV  2. TEST  3. SCRUM_MASTER  4. PM");
        String newName = "";
        String choice = scanner.nextLine();
        switch (choice) {
            case "1": newName = "DEV"; break;
            case "2": newName = "TEST"; break;
            case "3": newName = "SCRUM_MASTER"; break;
            case "4": newName = "PM"; break;
            default:
                System.out.println("Lựa chọn không hợp lệ!");
                return;
        }

        boolean isSuccess = controller.update(id, newName);
        if (isSuccess) {
            System.out.println("Cập nhật thành công!");
        } else {
            System.out.println("Cập nhật thất bại!");
        }
    }
}
