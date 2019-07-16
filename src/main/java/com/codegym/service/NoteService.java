package com.codegym.service;

import com.codegym.model.NoteDB;
import com.codegym.model.Type;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface NoteService {
    Page<NoteDB> findAll(Pageable pageable);

    NoteDB findById(Long id);

    Iterable<NoteDB> findAllBytypeContaining(Type type);

    Page<NoteDB> findAllByTitleContaining(String title, Pageable pageable);

    Page<NoteDB> findAllByTitleContainingOrContentContaining(String title,String content, Pageable pageable);

    void save(NoteDB noteDB);

    void remove(Long id);

}
