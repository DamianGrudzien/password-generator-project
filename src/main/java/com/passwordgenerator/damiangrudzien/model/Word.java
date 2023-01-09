package com.passwordgenerator.damiangrudzien.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;


import java.util.Objects;

@Entity
@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Word {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;
    private String word;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) {
            return false;
        }
        Word word = (Word) o;
        return id != null && Objects.equals(id, word.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
