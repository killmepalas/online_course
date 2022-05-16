package kill.me.palas.controllers;

import kill.me.palas.models.Question;
import kill.me.palas.models.Test;
import kill.me.palas.services.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/question")
public class QuestionController {

    private final QuestionService questionService;

    @Autowired
    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @GetMapping("/find")
    public String find(@RequestParam(value = "question") String name, Model model){
        model.addAttribute("questions", questionService.findByText(name));
        return "question/index";
    }

    @GetMapping("/{test_id}")
    public String index(Model model, @PathVariable("test_id") int id){
        List<Question> questions = questionService.findQuestionByTest(id);
        model.addAttribute("questions", questions);
        model.addAttribute("test", id);
        return "question/index";
    }

    @GetMapping("/create/{id}")
    public String create(@ModelAttribute("question") Question question, @PathVariable("id") int id, Model model) {
        model.addAttribute("test",id);
        return "question/create";
    }

    @PostMapping("/create/{test_id}")
    public String create(@ModelAttribute("question") @Valid Question question,
                         BindingResult bindingResult, @PathVariable("test_id") int id_test) {
        if (bindingResult.hasErrors())
            return "question/create";
        questionService.save(question, id_test);
        return "redirect:/question/" + question.getTest().getId();
    }

    @GetMapping("/update/{id}")
    public String update(@PathVariable("id") int id, Model model){
        model.addAttribute("question", questionService.findOne(id));
        return "question/edit";
    }

    @PostMapping("/update/{id}")
    public String update(@ModelAttribute("question") @Valid Question question, BindingResult bindingResult,
                         @PathVariable("id") int id) {
        if (bindingResult.hasErrors())
            return "question/edit";
        Test test = questionService.findTest(id);
        questionService.update(id, question ,test);
        return "redirect:/question/" + test.getId();
    }

    @PostMapping("/delete/{question_id}/{test_id}")
    public String delete(@PathVariable int question_id, @PathVariable int test_id) {
        questionService.delete(question_id);
        return "redirect:/question/" + test_id;
    }
}
