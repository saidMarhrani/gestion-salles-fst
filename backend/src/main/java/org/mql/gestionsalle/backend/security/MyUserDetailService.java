package org.mql.gestionsalle.backend.security;

import org.mql.gestionsalle.backend.entities.Student;
import org.mql.gestionsalle.backend.exceptions.ResourceNotFoundException;
import org.mql.gestionsalle.backend.services.StudentService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class MyUserDetailService implements UserDetailsService {

    private StudentService studentService;

    public MyUserDetailService(StudentService studentService) {
        this.studentService = studentService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Student student = studentService.findByUsername(username);
        if(student != null)
            return new User(student.getUsername(), student.getPassword(), new ArrayList<>());

        throw new ResourceNotFoundException("User with username " + username + " not found !");
    }
}
