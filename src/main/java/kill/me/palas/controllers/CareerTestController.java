package kill.me.palas.controllers;

import kill.me.palas.classes.CategoryCheck;
import kill.me.palas.models.*;
import kill.me.palas.services.CareerAnswerService;
import kill.me.palas.services.CareerQuestionService;
import kill.me.palas.services.CategoryService;
import kill.me.palas.services.CourseService;
import kill.me.palas.validators.TestValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/career_test")
public class CareerTestController {

    private final CareerAnswerService careerAnswerService;
    private final CareerQuestionService careerQuestionService;
    private final CategoryService categoryService;
    private final TestValidator testValidator;
    private final CourseService courseService;

    private List<CategoryCheck> categoryChecks = new ArrayList<>();

    @Autowired
    public CareerTestController(CareerQuestionService careerQuestionService, CareerAnswerService careerAnswerService,
                                CategoryService categoryService, TestValidator testValidator, CourseService courseService) {
        this.careerAnswerService = careerAnswerService;
        this.careerQuestionService = careerQuestionService;
        this.categoryService = categoryService;
        this.testValidator = testValidator;
        this.courseService = courseService;
    }

    @GetMapping("/index")
    public String index(Model model) {
        model.addAttribute("size", careerQuestionService.findAll().size());
        return "career_test/index";
    }

    @GetMapping("/start/{id}")
    public String careerExecute(@PathVariable int id, Model model, @ModelAttribute("answer") CareerAnswer answer) {
        CareerQuestion question = careerQuestionService.findOne(id);
        if (question != null) {
            List<CareerAnswer> answers = careerAnswerService.findByQuestion(question);
            if (answers == null) return "error/test";
            model.addAttribute("question", question);
            model.addAttribute("answers", answers);
            if (id == 1) initCategoryCheck();
            int next = id + 1;
            model.addAttribute("next", next);
            model.addAttribute("size",careerQuestionService.findAll().size());
            return "career_test/execute";
        }
        return "error/test";
    }


    @PostMapping("/execute/{num}")
    public String careerExecute(@ModelAttribute("answer") CareerAnswer answer, BindingResult bindingResult,
                                @PathVariable int num) {
        testValidator.car_answers_validate(answer, bindingResult);
        if (bindingResult.hasErrors())
            return "error/execute_test";
        int id_answer = answer.getId();
        if (careerAnswerService.findOne(id_answer) == null) {
            return "error/execute_test";
        }
        int categoryId = careerAnswerService.findOne(id_answer).getCategory().getId();
        for (CategoryCheck categoryCheck : categoryChecks)
            if (categoryCheck.getCategory().getId() == categoryId) categoryCheck.setSum(categoryCheck.getSum() + 1);
        if (careerQuestionService.findAll().size() < num) {
            int id = 0;
            int max = 0;
            for (CategoryCheck categoryCheck : categoryChecks)
                if (categoryCheck.getSum() > max) {
                    max = categoryCheck.getSum();
                    id = categoryCheck.getCategory().getId();
                }
            restartCategoryCheck();
            return "redirect:/career_test/result/" + id;
        }
        return "redirect:/career_test/start/" + num;
    }

    @GetMapping("/result/{id}")
    public String result(@PathVariable int id, Model model) {
        model.addAttribute("courses", courseService.findRandomByCategory(id));
        model.addAttribute("category",categoryService.findOne(id));
        return "career_test/result";
    }

    public void restartCategoryCheck() {
        for (CategoryCheck categoryCheck : categoryChecks)
            categoryCheck.setSum(0);
    }

    public void initCategoryCheck() {
        List<Category> categories = categoryService.findAll();
        for (Category category : categories)
            categoryChecks.add(new CategoryCheck(category, 0));
    }

}
