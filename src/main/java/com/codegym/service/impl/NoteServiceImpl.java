package com.codegym.service.impl;

import com.codegym.model.NoteDB;
import com.codegym.model.Type;
import com.codegym.repository.NoteRepository;
import com.codegym.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public class NoteServiceImpl implements NoteService {

    @Autowired
    private NoteRepository noteRepository;

    @Override
    public Page<NoteDB> findAll(Pageable pageable) {
        return noteRepository.findAll(pageable);
    }

    @Override
    public NoteDB findById(Long id) {
        return noteRepository.findOne(id);
    }

    @Override
    public void save(NoteDB noteDB) {
        noteRepository.save(noteDB);
    }

    @Override
    public void remove(Long id) {
        noteRepository.delete(id);
    }

    @Override
    public Iterable<NoteDB> findAllBytypeContaining(Type type) {
        return noteRepository.findAllBytypeContaining(type);
    }

    @Override
    public Page<NoteDB> findAllByTitleContaining(String title, Pageable pageable) {
        return noteRepository.findAllByTitleContaining(title,pageable);
    }

    @Override
    public Page<NoteDB> findAllByTitleContainingOrContentContaining(String title,String content, Pageable pageable) {
        return noteRepository.findAllByTitleContainingOrContentContaining(title,content,pageable);
    }
}
