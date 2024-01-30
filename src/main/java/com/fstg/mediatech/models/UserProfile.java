package com.fstg.mediatech.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@Entity
@Table(name = "profiles")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserProfile implements Serializable {
    @Id
    @GeneratedValue
    private Integer id;

    private String avatar;

    private String telephone;
    @OneToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;
}
