package com.vti.frontend;

import com.vti.entity.Phone;
import com.vti.entity.VietnamesePhone;

public class Program6 {
    public static void main(String[] args) {
        Phone vietnamesePhone = new VietnamesePhone();

        vietnamesePhone.insertContact("Hung", "0912345678");
        vietnamesePhone.insertContact("Lan", "0988888888");
        vietnamesePhone.insertContact("Hung", "0123456789");
        vietnamesePhone.insertContact("Ha", "0123654987");

        vietnamesePhone.searchContact("Hung");
        vietnamesePhone.removeContact("Hung");
        vietnamesePhone.searchContact("Hung");

        vietnamesePhone.updateContact("Lan", "0999999999");
        vietnamesePhone.searchContact("Lan");
        vietnamesePhone.updateContact("Test","1");
    }
}
