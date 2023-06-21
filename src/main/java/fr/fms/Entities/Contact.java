package fr.fms.Entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Contact implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String firstname;
    private String address;
    private String phone;
    private String email;

    @ManyToOne
    private Category category;
}
