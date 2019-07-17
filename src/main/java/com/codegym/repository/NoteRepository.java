package com.codegym.repository;

import com.codegym.model.NoteDB;
import com.codegym.model.Type;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface NoteRepository extends PagingAndSortingRepository<NoteDB,Long> {
    Iterable<NoteDB> findAllByType(Type type);

    Page<NoteDB> findAllByTitleContaining(String title, Pageable pageable);

    Page<NoteDB> findAllByTitleContainingOrContentContaining(String title,String content, Pageable pageable);

}
