package com.dtrung.chatapp.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "`groups`")
public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID groupId;

    @Column(name = "group_name")
    private String groupName;

    private String groupAvatar;

    @ManyToOne
    @JoinColumn(name = "create_by", nullable = false)
    private User createBy;

    private LocalDateTime createAt;

    @OneToMany(mappedBy = "group")
    private List<GroupMember> members = new ArrayList<>();

}
