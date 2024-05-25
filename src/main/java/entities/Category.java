package entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Category")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Category {
    @Id
    @GeneratedValue
    private int id;

@Column(name = "name")
    private String name;

@Column(name = "description")
    private String description;

}
