package org.example.htmxspringjpa.controller;

import io.github.wimdeblauwe.hsbt.mvc.HtmxResponse;
import org.example.htmxspringjpa.domain.Student;
import org.example.htmxspringjpa.repository.StudentRepo;
import org.example.htmxspringjpa.repository.spec.StudentSpec;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.repository.query.Param;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Pageable;

@Controller
@RequestMapping("/student")
public class StudentController {
    private final StudentRepo studentRepo;

    public StudentController(StudentRepo studentRepo) {
        this.studentRepo = studentRepo;
    }

    @GetMapping
    String allStudent(Model model) {
        model.addAttribute("students", studentRepo.findAll());
        return "student";
    }

    @GetMapping("/search")
    String allStudent(@Param("name") String name, Model model) {
        Specification<Student> specification = Specification.where(null);
        specification = specification.and(StudentSpec.fNameLike(name))
                .or(StudentSpec.lNameLike(name));
        model.addAttribute("students", studentRepo.findAll(specification));
        return ("student::student-table");
    }

    @PostMapping
    HtmxResponse addStudent(Model model, @RequestParam("fname") String firstName,
                            @RequestParam("lname") String lastName,
                            @RequestParam("email") String email,
                            @RequestParam("phone") String phone) {
        Student student = new Student();
        student.setfName(firstName);
        student.setlName(lastName);
        student.setEmail(email);
        student.setPhone(phone);
        studentRepo.save(student);
        model.addAttribute("students", studentRepo.findAll());
        return new HtmxResponse().addTemplate("add-student::student-table");
    }

    @ResponseBody
    @DeleteMapping(value = "{id}", produces = MediaType.TEXT_HTML_VALUE)
    String deleteById(@PathVariable("id") Long id) {
        studentRepo.deleteById(id);
        return "";
    }

}
