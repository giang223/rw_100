package com.vti.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "`group`")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "group_id")
    private Integer id;

    @Column(name = "group_name", nullable = false, unique = true, length = 100)
    private String groupName;

    @Column(name = "create_date", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime createdDate;

    @Override
    public String toString() {
        return "Group{" +
                "id=" + id +
                ", groupName='" + groupName + '\'' +
                ", createdDate=" + createdDate +
                '}';
    }
}
