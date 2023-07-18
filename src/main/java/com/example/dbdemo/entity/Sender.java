package com.example.dbdemo.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Nationalized;
import org.springframework.data.domain.Persistable;

import java.util.Set;

// Niemals @Data an Entitäten machen; hashCode und equals wird sonst überschrieben; den lombok-default wollen wir nicht;
// wir implementieren das selbst
@Getter
@Setter


@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(indexes = {
        @Index(name = "IDX_name", columnList = "name", unique = false)
})
public class Sender implements Persistable<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(insertable = true, updatable = false, nullable = false)
    private Long id;

    @Nationalized
    @Column(insertable = true, updatable = true, nullable = false, length = 255)
    private String name;

    @OneToMany(mappedBy = "sender")
    private Set<Message> message;

    // das später auch noch erklären
    @Override
    public boolean isNew() {
        return id == null;
    }

}
