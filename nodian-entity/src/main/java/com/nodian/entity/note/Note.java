package com.nodian.entity.note;

import com.nodian.entity.shared.BaseEntity;
import com.nodian.entity.user.User;
import com.nodian.entity.folder.Folder;
import com.nodian.entity.depository.Depository;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "notes")
@SQLDelete(sql = "UPDATE notes SET is_active = false")
@Where(clause = "is_active = true")
public class Note extends BaseEntity {
    @Column(name = "name")
    private String name;

    @Nullable
    @Column(columnDefinition = "TEXT", name = "content")
    private String content;

    @Nullable
    @Column(name = "parent_id")
    private Long parentId;

    @ManyToOne
    @JoinColumn(name = "parent_folder_id")
    @Nullable
    private Folder parentFolder;


    @ManyToOne
    @JoinColumn(name = "repo_id")
    private Depository depository;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private User user;
}