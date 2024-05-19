package com.nodian.entity.folder;

import com.nodian.entity.user.User;
import com.nodian.entity.note.Note;
import com.nodian.entity.depository.Depository;
import com.nodian.entity.shared.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.annotation.Nullable;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "folders")
@SQLDelete(sql = "UPDATE folders SET is_active = false")
@Where(clause = "is_active = true")
public class Folder extends BaseEntity {

    @Column(name = "name")
    private String name;

    @Column(name = "parent_id")
    private Long parentId;

    @ManyToOne
    @JoinColumn(name = "parent_folder_id")
    @Nullable
    private Folder parentFolder;

    @OneToMany(mappedBy = "parentFolder", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Folder> childFolders;

    @OneToMany(mappedBy = "parentFolder", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Note> notes;

    @ManyToOne
    @JoinColumn(name = "depository_id")
    private Depository depository;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private User user;

}
