package com.example.web;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

@Service
@Validated
public class WebService {

    public void addUser(@Valid User user) {

    }
}
