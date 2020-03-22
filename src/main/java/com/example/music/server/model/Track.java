package com.example.music.server.model;

import com.example.music.server.model.enums.Genre;
import lombok.*;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "tracks")
public class Track {

    @Id
    @GeneratedValue
    private Long id;

    private String title;
    private String artist;

    @Enumerated(EnumType.STRING)
    private Genre genre;

    @OneToMany(mappedBy = "track")
    @ToString.Exclude
    @Cascade(value = org.hibernate.annotations.CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    private List<Vote> votes;

}
