package com.imuzio.crud.controller;

import com.imuzio.crud.entity.Student;
import com.imuzio.crud.entity.StudentInSubject;
import com.imuzio.crud.entity.Subject;
import com.imuzio.crud.entity.Test;
import com.imuzio.crud.service.IStudentInSubjectService;
import com.imuzio.crud.service.IStudentService;
import com.imuzio.crud.service.ISubjectService;
import com.imuzio.crud.service.ITestService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@SessionAttributes("subject")
public class SubjectController {

    @Autowired
    private ISubjectService subjectService;
    @Autowired
    private IStudentInSubjectService sisService;

    @Autowired
    private IStudentService studentService;

    @Autowired
    private ITestService testService;

    @RequestMapping(value = "/subjects/list", method = RequestMethod.GET)
    public String listSubjects (Model model){
        model.addAttribute("title","Listado de Cursos");
        model.addAttribute("subjects",subjectService.findAll());
        return "subjectList";
    }

    @RequestMapping(value = "/subjects/subjectForm")
    public String create(Map<String,Object> model){
        Subject subject = new Subject();
        model.put("subject",subject);
        model.put("title","Formulario de Curso");
        return "subjectForm";
    }

    @RequestMapping(value="/subjects/subjectForm/{id}")
    public String edit (@PathVariable(value="id") Integer id, Map<String, Object> model){
        Subject subject = null;
        if(id>0){
            subject = subjectService.findOne(id);
        }else{
            return "redirect:/subjects/list";
        }
        model.put("subject",subject);
        model.put("title", "Editar Curso");
        return "subjectForm";
    }

    @RequestMapping(value = "subjects/view/{id}")
    public String viewStudentsList (@PathVariable(value="id") Integer id, Map<String, Object> model){
        List <Student> in = new ArrayList<>();
        List <Student> out = new ArrayList<>();
        Subject subject = null;
        if(id>0){
            in = sisService.getStudentsBySiS(id);
            out = sisService.getNotStudentsBySiS(id);
            subject = subjectService.findOne(id);
        }else{
            return "redirect:/subjects/list";
        }
        model.put("in",in);
        model.put("out",out);
        model.put("subject",subject);
        return "viewSubject";
    }

    @RequestMapping(value="/subjects/addStudent/{idSub}/{idStu}")
    public String addStudent(@PathVariable(value="idSub") Integer idSub,@PathVariable(value="idStu") Integer idStu){
        if(idStu > 0 && idSub>0){
            StudentInSubject sis = new StudentInSubject();
            Subject sub = subjectService.findOne(idSub);
            Student student = studentService.findOne(idStu);
            sis.setStudent(student);
            sis.setSubject(sub);
            sisService.save(sis);
        }
        return "redirect:/subjects/view/"+idSub;
    }

    @RequestMapping(value="/subjects/addTest/{idSub}/{idStu}")
    public String createTest(@PathVariable(value="idSub") Integer idSub,@PathVariable(value="idStu") Integer idStu, Model model){
        Test test = new Test();
        model.addAttribute("test",test);
        model.addAttribute("idSub",idSub);
        model.addAttribute("idStu",idStu);
        model.addAttribute("title","Formulario de Parcial");
        return "testForm";
    }

    @RequestMapping(value="/subjects/addTest/{idSub}/{idStu}",method= RequestMethod.POST)
    public String addTest(@Valid Test test,BindingResult result, @PathVariable(value="idSub") Integer idSub,@PathVariable(value="idStu") Integer idStu, Model model){
        if(result.hasErrors()){
            model.addAttribute("title","Formulario de Parcial");
            return "testForm";
        }
        test.setSubject(subjectService.findOne(idSub));
        test.setStudent(studentService.findOne(idStu));
        testService.save(test);
        return "redirect:/subjects/view/" + idSub;
    }



    @RequestMapping(value="/subjects/delete/{idSub}/{idStu}")
    public String deleteStudent(@PathVariable(value="idSub") Integer idSub,@PathVariable(value="idStu") Integer idStu){
        if(idStu > 0 && idSub>0){
            StudentInSubject sis = sisService.getByStudentNSubject(idStu,idSub);
            sisService.delete(sis);
        }
        return "redirect:/subjects/view/"+idSub;
    }

    @RequestMapping(value = "/subjects/subjectForm", method = RequestMethod.POST)
    public String save (@Valid Subject subject, BindingResult result, Model model, SessionStatus status){
        if(result.hasErrors()){
            model.addAttribute("title","Formulario de Curso");
            return "subjectForm";
        }
        subjectService.save(subject);
        status.setComplete();
        return "redirect:/subjects/list";
    }

    @RequestMapping(value = "/subjects/delete/{id}")
    public String eliminar (@PathVariable(value="id") Integer id){
        if(id>0){
            subjectService.delete(id);
        }
        return "redirect:/subjects/list";
    }
}
