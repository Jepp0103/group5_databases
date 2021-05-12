package com.example.demo.controller.api;
import com.example.demo.JwtTokenUtil;
import com.example.demo.model.Class;
import com.example.demo.repository.ClassRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping(value = "/api")
public class ClassController {
    @Autowired
    JwtTokenUtil jtu;

    @Autowired
    private ClassRepository classRepository;

    @GetMapping("/classes")
    public Iterable<Class> getClasses()  {
        return classRepository.findAll();
    }

    @PostMapping("/averageclassattendancerate")
    public Integer getAverageClassAttendanceRate(@RequestBody Map<String, Integer> body)  {
        return classRepository.findAverageClassAttendanceRate(body.get("courseid"), body.get("classid"));
    }

    @GetMapping("/myclasses")
    public ResponseEntity<?> getTeachersClasses(HttpServletRequest request)  {
        String token = jtu.getCurrentToken(request);
        if (jtu.getClassIdFromToken(token)!=null){return ResponseEntity.ok(jtu.getClassIdFromToken(token));}
        Integer teacherid=jtu.getTeacherIdFromToken(token);
        System.out.println("My classes? " + classRepository.findTeacherClasses(jtu.getTeacherIdFromToken(token)));
        return teacherid!=null ? ResponseEntity.ok(classRepository.findTeacherClasses(jtu.getTeacherIdFromToken(token))) : null;
    }

}