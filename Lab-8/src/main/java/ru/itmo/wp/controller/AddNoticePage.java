package ru.itmo.wp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import ru.itmo.wp.form.NoticeInformation;
import ru.itmo.wp.form.UserCredentials;
import ru.itmo.wp.form.validator.NoticeInformationValidator;
import ru.itmo.wp.form.validator.UserCredentialsRegisterValidator;
import ru.itmo.wp.service.NoticeService;
import ru.itmo.wp.service.UserService;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

@Controller
public class AddNoticePage extends Page {
    private final NoticeService noticeService;
    private final NoticeInformationValidator noticeInformationValidator;

    public AddNoticePage(NoticeService noticeService, NoticeInformationValidator noticeInformationValidator) {
        this.noticeService = noticeService;
        this.noticeInformationValidator = noticeInformationValidator;
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.addValidators(noticeInformationValidator);
    }

    @GetMapping("/notice/add")
    public String addNoticeGet(Model model) {
        model.addAttribute("noticeForm", new NoticeInformation());
        return "AddNoticePage";
    }

    @PostMapping("/notice/add")
    public String addNoticePost(@Valid @ModelAttribute("noticeForm") NoticeInformation noticeForm,
                               BindingResult bindingResult,
                               HttpSession httpSession) {
        if (bindingResult.hasErrors()) {
            return "AddNoticePage";
        }

        noticeService.addNotice(noticeForm);
        setMessage(httpSession, "Congrats, you have added new notice!");

        return "redirect:/";
    }
}
