package com.vti.entity;

public class VietnamesePhone extends Phone{
    @Override
    public void insertContact(String name, String phone) {
        boolean isExisted = false;

        for (Contact c : contacts) {
            if (c.getName().equalsIgnoreCase(name)) {
                c.setNumber(phone);
                isExisted = true;
                System.out.println("Đã cập nhật số mới cho tên: " + name);
                break;
            }
        }

        if (!isExisted) {
            contacts.add(new Contact(name, phone));
            System.out.println("Đã thêm liên lạc mới: " + name);
        }
    }

    @Override
    public void removeContact(String name) {
        for(Contact contact : contacts)
        {
            if(contact.getName().equalsIgnoreCase(name))
            {
                contacts.remove(contact);
                System.out.println("Đã xóa liên lạc: " + name);
                return;
            }
        }
    }

    @Override
    public void updateContact(String name, String newPhone) {
        for (Contact contact : contacts) {
            if (contact.getName().equalsIgnoreCase(name)) {
                contact.setNumber(newPhone);
                System.out.println("Cập nhật thành công.");
                return;
            }
        }
        System.out.println("Không tìm thấy tên để cập nhật.");
    }

    @Override
    public void searchContact(String name) {
        for (Contact contact : contacts)
        {
            if (contact.getName().equalsIgnoreCase(name)) {
                System.out.println(contact);
                return;
            }
        }
        System.out.println("Không tìm thấy kết quả.");
    }
}
