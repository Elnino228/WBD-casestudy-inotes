package com.codegym.controller;

import com.codegym.model.Type;
import com.codegym.service.TypeService;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping("/types")
public class TypeController {

    @Autowired
    private TypeService typeService;


    @GetMapping("")
    public ModelAndView list(){
        ModelAndView modelAndView=new ModelAndView("/type/list");
        modelAndView.addObject("types",typeService.findAll());
        return modelAndView;
    }

    @GetMapping("/create")
    public ModelAndView showCreate(){
        ModelAndView modelAndView=new ModelAndView("/type/create");
        modelAndView.addObject("type",new Type());
        return modelAndView;
    }

    @PostMapping("/create")
    public ModelAndView create(@ModelAttribute("type") Type type){
        typeService.save(type);
        ModelAndView modelAndView=new ModelAndView("/type/create");
        modelAndView.addObject("type",new Type());
        modelAndView.addObject("message","New note is created successfully");
        return modelAndView;
    }


    @GetMapping("/delete/{id}")
    public ModelAndView showDelete(@PathVariable("id") Long id){
        Type type=typeService.findById(id);
        if (type!=null){
            ModelAndView modelAndView=new ModelAndView("/type/delete");
            modelAndView.addObject("type",type);
            return modelAndView;
        } else {
            ModelAndView modelAndView=new ModelAndView("/type/error404");
            return modelAndView;
        }
    }

    @PostMapping("/delete")
    public String delete(@ModelAttribute("type") Type type){
        typeService.remove(type.getId());
        return "redirect:/types";
    }

    @GetMapping("/edit/{id}")
    public ModelAndView showEdit(@PathVariable("id") Long id){
        Type type=typeService.findById(id);
        if (type!=null){
            ModelAndView modelAndView=new ModelAndView("/type/edit");
            modelAndView.addObject("type",type);
            return modelAndView;
        } else {
            ModelAndView modelAndView=new ModelAndView("/type/error404");
            return modelAndView;
        }
    }

    @PostMapping("/edit")
    public String edit(@ModelAttribute("type") Type type, Model model){
        typeService.save(type);
        model.addAttribute("message","Note is updated successfully");
        return "redirect:/types";
    }

}

