package com.example.dbdemo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Nationalized;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(indexes = {
        @Index(name = "IDX_message", columnList = "message", unique = false)
})
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(insertable = true, updatable = false, nullable = false)
    private Long id;

    @Nationalized
    @Column(insertable = true, updatable = true, nullable = false, length = 255)
    private String message;

}
