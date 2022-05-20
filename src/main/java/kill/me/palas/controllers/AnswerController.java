package kill.me.palas.controllers;

import kill.me.palas.models.Answer;
import kill.me.palas.models.Question;
import kill.me.palas.services.AnswerService;
import kill.me.palas.services.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/answer")
public class AnswerController {
    private final AnswerService answerService;

    @Autowired
    public AnswerController(AnswerService answerService) {
        this.answerService = answerService;
    }

    @GetMapping("/{question_id}")
    public String index(Model model, @PathVariable("question_id") int id){
        List<Answer> answers = answerService.findAnswerByQuestion(id);
        model.addAttribute("answers", answers);
        return "answer/index";
    }

    @GetMapping("/create/{id}")
    public String create(@ModelAttribute("answer") Answer answer, @PathVariable("id") int id, Model model) {
        model.addAttribute("question",id);
        return "answer/create";
    }

    @PostMapping("/create/{question_id}")
    public String create(@ModelAttribute("answer") @Valid Answer answer,
                         BindingResult bindingResult, @PathVariable("question_id") int id_question) {
        if (bindingResult.hasErrors())
            return "answer/create";
        answerService.save(answer, id_question);
        return "redirect:/answer/" + answer.getQuestion().getId();
    }

    @GetMapping("/update/{id}")
    public String update(@PathVariable("id") int id, Model model){
        model.addAttribute("answer", answerService.findOne(id));
        return "answer/edit";
    }

    @PostMapping("/update/{id}")
    public String update(@ModelAttribute("answer") @Valid Answer answer, BindingResult bindingResult,
                         @PathVariable("id") int id) {
        if (bindingResult.hasErrors())
            return "answer/edit";
        Question question = answerService.findQuestion(id);
        answerService.update(id, answer ,question);
        return "redirect:/answer/" + question.getId();
    }

    @PostMapping("/delete/{answer_id}")
    public String delete(@PathVariable int answer_id) {
        int question_id = answerService.findQuestion(answer_id).getId();
        answerService.delete(answer_id);
        return "redirect:/answer/" + question_id;
    }

}
