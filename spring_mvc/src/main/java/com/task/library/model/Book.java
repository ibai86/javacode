package com.task.library.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = false)
@ToString(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "book")
public class Book extends Auditable {

    public enum Genre {
        FICTION, NON_FICTION, FANTASY, SCIENCE, HISTORY, BIOGRAPHY, POETRY, ROMANCE, THRILLER, OTHER
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ToString.Include
    private Long id;

    @NotBlank
    @Column(name = "title", nullable = false, length = 300)
    @ToString.Include
    private String title;

    @Size(max = 20)
    @Column(name = "isbn", unique = true, length = 20)
    @ToString.Include
    private String isbn;

    @Enumerated(EnumType.STRING)
    @Column(name = "genre", length = 30)
    private Genre genre;

    @Positive
    @Column(name = "page_count")
    private Integer pageCount;

    @PastOrPresent
    @Column(name = "publication_date")
    private LocalDate publicationDate;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "author_id", nullable = false)
    @ToString.Exclude
    private Author author;

}

