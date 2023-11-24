package edu.nau.cs.meta.service.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(setterPrefix = "with")
@EqualsAndHashCode(exclude = {"user", "tag", "parentFolder", "currentFolderChildFolders", "currentFolderChildFiles"})
public class FolderObject {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    @Column(name = "folder_name", nullable = false)
    private String folderName;

    @Column(name = "folder_path")
    private String folderPath;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tag_id")
    private Tag tag;

    @ManyToOne(fetch = FetchType.LAZY)
    private FolderObject parentFolder;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "parentFolder", fetch = FetchType.LAZY, targetEntity = FolderObject.class)
    private List<FolderObject> currentFolderChildFolders;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "parentFolderObject", fetch = FetchType.LAZY, targetEntity = FileObject.class)
    private List<FileObject> currentFolderChildFiles;

}
