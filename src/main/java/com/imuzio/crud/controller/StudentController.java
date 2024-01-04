package com.imuzio.crud.controller;

import com.imuzio.crud.entity.Student;
import com.imuzio.crud.entity.Subject;
import com.imuzio.crud.entity.Test;
import com.imuzio.crud.service.IStudentInSubjectService;
import com.imuzio.crud.service.IStudentService;
import com.imuzio.crud.service.ITestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import jakarta.validation.Valid;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@SessionAttributes("student")
public class StudentController {

    @Autowired
    private IStudentService studentService;
    @Autowired
    private ITestService testService;

    @Autowired
    private IStudentInSubjectService sisService;

    @RequestMapping(value = "/index")
    public String index(Model model){
        model.addAttribute("title","Crud de Alumnos y cursos");
        return "index";
    }

    @RequestMapping(value = "/students/list", method = RequestMethod.GET)
    public String listStudents (Model model){
        model.addAttribute("title","Listado de Alumnos");
        model.addAttribute("students",studentService.findAll());
        return "studentList";
    }

    @RequestMapping(value = "/students/studentForm")
    public String create(Map<String,Object> model){
        Student student = new Student();
        model.put("student",student);
        model.put("title","Formulario de Alumno");
        return "studentForm";
    }

    @RequestMapping(value="/students/studentForm/{id}")
    public String edit (@PathVariable(value="id") Integer id, Map<String, Object> model){
        Student student = null;
        if(id>0){
            student = studentService.findOne(id);
        }else{
            return "redirect:/students/list";
        }
        model.put("student",student);
        model.put("title", "Editar Alumno");
        return "studentForm";
    }

    @RequestMapping(value ="/students/view/{id}")
    public String view (@PathVariable(value="id") Integer id, Model model){
        Student student = null;
        List <Test> tests = null;
        List <Subject> subjectsTest = null;
        List <Subject> activeSubjects = null;
        if(id>0){
            student = studentService.findOne(id);
            tests = testService.getTestsByStudentId(id);
            subjectsTest = testService.getSubjectByTestStudentId(id);
            activeSubjects = sisService.getSubjectsBySiS(id);
        }else{
            return "redirect:/students/list";
        }
        model.addAttribute("student",student);
        model.addAttribute("tests",tests);
        model.addAttribute("subjectsTest",subjectsTest);
        model.addAttribute("activeSubjects",activeSubjects);
        return "viewStudent";
    }

    @RequestMapping(value = "/students/studentForm", method = RequestMethod.POST)
    public String save (@Valid Student student, BindingResult result, Model model, SessionStatus status){
        if(result.hasErrors()){
            model.addAttribute("title","Formulario de Alumno");
            return "studentForm";
        }
        studentService.save(student);
        status.setComplete();
        return "redirect:/students/list";
    }

    @RequestMapping(value = "/students/delete/{id}")
    public String eliminar (@PathVariable(value="id") Integer id){
        if(id>0){
            studentService.delete(id);
        }
        return "redirect:/students/list";
    }
}
