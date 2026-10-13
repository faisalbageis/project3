package org.example.project3.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
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
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    @NotEmpty(message = "AccountNumber can not be empty")
    @Pattern(regexp = "^\\d{18}$", message = "Account number must contain exactly 18 digits")
    private String AccountNumber;

    @Column(nullable = false)
    @NotNull(message = "balance can not be null")
    @Positive(message = "balance must be positive")
    private Double balance;

    @Column
    private Boolean isActive;

    @ManyToOne
    @JoinColumn
    @JsonIgnore
    private Customer customer;
}
