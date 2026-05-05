package com.vti.entity;

public class Student {
    private int id;
    private String name;
    private String hometown;
    private float score;

    public Student(String name, String hometown) {
        this.name = name;
        this.hometown = hometown;
        this.score = 0;
    }

    public void setScore(float score) {
        if (score < 0) {
            this.score = 0;
        } else if (score > 10) {
            this.score = 10;
        } else {
            this.score = score;
        }
    }

    public void addScore(float bonusScore) {
        float newScore = this.score + bonusScore;

        setScore(newScore);
    }

    public void printInfo() {
        String rank = "";
        if (this.score < 4.0) {
            rank = "Yếu";
        } else if (this.score < 6.0) {
            rank = "Trung bình";
        } else if (this.score < 8.0) {
            rank = "Khá";
        } else {
            rank = "Giỏi";
        }

        System.out.println("Tên: " + name + " | Điểm: " + score + " | Xếp loại: " + rank);
    }
}
