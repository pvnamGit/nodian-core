package com.nodian.entity.depository;

import com.nodian.entity.folder.Folder;
import com.nodian.entity.note.Note;
import com.nodian.entity.shared.BaseEntity;
import com.nodian.entity.user.User;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "depositories")
@SQLDelete(sql = "UPDATE repositories SET is_active = false")
@Where(clause = "is_active = true")
public class Depository extends BaseEntity {
    @OneToMany(mappedBy = "depository", cascade = CascadeType.ALL, orphanRemoval = true)
    List<Folder> folders;
    @Column(name = "name")
    private String name;
    @ManyToOne
    @JoinColumn(name = "owner_id")
    private User owner;
    @OneToMany(mappedBy = "depository", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Note> notes;

}
