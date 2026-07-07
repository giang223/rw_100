package com.vti.entity;

import com.vti.enums.PositionName;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "position")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class Position {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "position_id")
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column(name = "position_name", nullable = false)
    private PositionName name;
}
