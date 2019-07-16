package com.codegym.formatter;

import com.codegym.model.Type;
import com.codegym.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;

import java.text.ParseException;
import java.util.Locale;

public class TypeFormatter implements Formatter<Type> {
    private TypeService typeService;

    @Autowired
    public TypeFormatter(TypeService typeService){
        this.typeService=typeService;
    }
    @Override
    public Type parse(String text, Locale locale) throws ParseException {
        return typeService.findById(Long.parseLong(text));
    }

    @Override
    public String print(Type object, Locale locale) {
        return "[" + object.getId() + ", " + object.getName() + "]";
    }
}
