package ra.student.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ra.student.dto.request.StudentRequestDto;
import ra.student.model.Student;
import ra.student.service.student.IStudentService;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private IStudentService studentService;
    @RequestMapping({"","/dashboard"})
    public String dashboard(){
      return "admin/index";
    }
    @RequestMapping("/user")
    public String user(){
      return "admin/user";
    }
    @RequestMapping("/category")
    public String category(){
      return "admin/category";
    }
    @RequestMapping("/student")
    public String student(Model model){
        model.addAttribute("students",studentService.findAll());
      return "admin/student";
    }
    @RequestMapping(value = "/student/add",method = RequestMethod.POST)
    public String doAddStudent(@ModelAttribute StudentRequestDto studentRequestDto){
        studentService.save(studentRequestDto);
        return "redirect:/admin/student";
    }
    @RequestMapping("/student/edit")
    public String edit(@RequestParam Integer id,Model model){
        Student studentEdit   = studentService.findById(id);
        model.addAttribute("student",studentEdit);
        return "admin/student-edit";
    }
    @RequestMapping(value = "/student/update", method = RequestMethod.POST)
    public String update(@ModelAttribute StudentRequestDto studentRequestDto){
        studentService.save(studentRequestDto);
        return "redirect:/admin/student";
    }

    @RequestMapping("/student/delete")
    public String delete(@RequestParam Integer id){
        studentService.deleteById(id);
        return "redirect:/admin/student";
    }


}
