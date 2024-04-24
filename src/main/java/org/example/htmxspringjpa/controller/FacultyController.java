package org.example.htmxspringjpa.controller;

import io.github.wimdeblauwe.hsbt.mvc.HtmxResponse;
import org.example.htmxspringjpa.domain.Faculty;
import org.example.htmxspringjpa.repository.FacultyRepo;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/faculty")
public class FacultyController {
    private final FacultyRepo facultyRepo;

    public FacultyController(FacultyRepo facultyRepo) {
        this.facultyRepo = facultyRepo;
    }

    @GetMapping
    String getFaculty(Model model) {
        model.addAttribute("faculty", facultyRepo.findAll());
        return "faculty";
    }

    @PostMapping
    HtmxResponse addFaculty(@RequestParam("new-faculty") String facultyName, Model model) {
        Faculty faculty = new Faculty();
        faculty.setName(facultyName);
        facultyRepo.save(faculty);
        model.addAttribute("faculty", facultyRepo.findAll());
        return new HtmxResponse().addTemplate("faculty::faculty-table");
    }

    @ResponseBody
    @DeleteMapping(value = "{id}",produces = MediaType.TEXT_HTML_VALUE)
    String deleteById(@PathVariable("id") Long id){
        facultyRepo.deleteById(id);
        return "";
    }
}
