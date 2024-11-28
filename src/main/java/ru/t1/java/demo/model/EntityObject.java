package ru.t1.java.demo.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@MappedSuperclass
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class EntityObject {
    @Id
    @SequenceGenerator(name = "ID_GEN", sequenceName = "ID_SEQ", allocationSize = 1)
    @GeneratedValue(generator = "ID_GEN", strategy = GenerationType.SEQUENCE)
    private Long id;

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !getClass().isAssignableFrom(obj.getClass()) || !getClass().isInstance(obj)) {
            return false;
        }
        EntityObject that = (EntityObject) obj;
        return getId() != null && getId().equals(that.getId());
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}

