package com.test.entity;

import com.test.entity.User;
import com.test.enums.NoteType;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table
@Getter
@Setter
public class Note {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long note_id;

    @Column(columnDefinition = "varchar(255) charset utf8 default ''")
    private String title;

    @Column(columnDefinition = "varchar(9999) default ''")
    private String body;

    @Column(columnDefinition = "varchar(32) default 'NORMAL'")
    @Enumerated(EnumType.STRING)
    private NoteType note_type;

    private String description;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date created_at;

    private Long created_by;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date updated_at;

    private Long updated_by;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_user_id")
    private User user;
}
