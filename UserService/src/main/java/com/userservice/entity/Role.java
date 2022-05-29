package com.userservice.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.userservice.enums.RoleStatus;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table
@Getter
@Setter
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long role_id;

    @Column(columnDefinition = "varchar(255) default ''")
    private String role;

    @Column(columnDefinition = "varchar(32) default 'ACTIVE'")
    @Enumerated(EnumType.STRING)
    private RoleStatus role_status;

    private String description;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date created_at;

    private Long created_by;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date updated_at;

    private Long updated_by;

    @OneToMany(mappedBy = "role", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<User> users;
}
