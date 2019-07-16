package com.codegym.controller;


import com.codegym.model.NoteDB;
import com.codegym.model.Type;
import com.codegym.service.NoteService;
import com.codegym.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import org.springframework.data.domain.Pageable;

import java.util.Optional;


@Controller
@RequestMapping("/notes")
public class NoteController {

    @Autowired
    private NoteService noteService;

    @Autowired
    private TypeService typeService;

    @ModelAttribute("types")
    public Iterable<Type> types(){
        return typeService.findAll();
    }


    @GetMapping("")
    public ModelAndView list(@RequestParam("search") Optional<String> keyword, @PageableDefault(size = 3,page = 0) Pageable pageable){
        Page<NoteDB> noteDBs;
        if (keyword.isPresent()){
//            noteDBs=noteService.findAllByTitleContaining(s.get(),pageable);
            noteDBs=noteService.findAllByTitleContainingOrContentContaining(keyword.get(),keyword.get(),pageable);
        } else {
            noteDBs=noteService.findAll(pageable);
        }
        ModelAndView modelAndView=new ModelAndView("/note/list");
        modelAndView.addObject("notes",noteDBs);
        return modelAndView;
    }

    @GetMapping("/create")
    public ModelAndView showCreate(){
        ModelAndView modelAndView=new ModelAndView("/note/create");
        modelAndView.addObject("note",new NoteDB());
        return modelAndView;
    }

    @PostMapping("/create")
    public ModelAndView create(@ModelAttribute("note") NoteDB noteDB){
        noteService.save(noteDB);
        ModelAndView modelAndView=new ModelAndView("/note/create");
        modelAndView.addObject("note",new NoteDB());
        modelAndView.addObject("message","Created note successfully");
        return modelAndView;
    }

    @GetMapping("/view/{id}")
    public ModelAndView view(@PathVariable("id") Long id){
        NoteDB noteDB=noteService.findById(id);
        ModelAndView modelAndView=new ModelAndView("/note/view");
        modelAndView.addObject("note",noteDB);
        return modelAndView;
    }

    @GetMapping("/delete/{id}")
    public ModelAndView showDelete(@PathVariable("id") Long id){
        NoteDB noteDB=noteService.findById(id);
        if (noteDB!=null){
            ModelAndView modelAndView=new ModelAndView("/note/delete");
            modelAndView.addObject("note",noteDB);
            return modelAndView;
        } else {
            ModelAndView modelAndView=new ModelAndView("/note/error404");
            return modelAndView;
        }
    }

    @PostMapping("/delete")
    public String delete(@ModelAttribute("note")NoteDB noteDB){
        noteService.remove(noteDB.getId());
        return "redirect:/notes";
    }

    @GetMapping("/edit/{id}")
    public ModelAndView showEdit(@PathVariable("id") Long id){
        NoteDB noteDB=noteService.findById(id);
        if (noteDB!=null){
            ModelAndView modelAndView=new ModelAndView("/note/edit");
            modelAndView.addObject("note",noteDB);
            return modelAndView;
        } else {
            ModelAndView modelAndView=new ModelAndView("/note/error404");
            return modelAndView;
        }
    }

    @PostMapping("/edit")
    public String edit(@ModelAttribute("note") NoteDB noteDB, Model model){
        noteService.save(noteDB);
        model.addAttribute("message","Updated note successfully");
        return "/note/edit";
    }

}
