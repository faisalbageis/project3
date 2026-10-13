package org.example.project3.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Employee {

    @Id
    private Integer id;

    @Column(nullable = false)

    private String position;

    @Column(nullable = false)

    private Double salary;

    @OneToOne
    @MapsId
    @PrimaryKeyJoinColumn
    private User user;
}
