package com.icwd.user.service.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "micro_users")
public class User {

    @Id
    @Column(name="ID")
    private String userId;

    @Column(name="NAME", length = 20)
    private String name;

    private String email;

    @Column(name="ABOUT")
    private String about;

    @Transient //Not saving ratings info in this DB,that's why added this annotation. will add this info in Rating DB.
    private List<Rating> ratings = new ArrayList<>();
}
