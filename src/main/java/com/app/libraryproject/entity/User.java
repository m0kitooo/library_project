package com.app.libraryproject.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "users")
@Getter
@Setter
@AllArgsConstructor
@SuperBuilder
public class User extends AbstractPerson {
}