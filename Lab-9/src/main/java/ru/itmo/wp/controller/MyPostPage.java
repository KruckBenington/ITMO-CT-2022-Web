package ru.itmo.wp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.itmo.wp.domain.Comment;
import ru.itmo.wp.domain.Post;
import ru.itmo.wp.domain.Role;
import ru.itmo.wp.repository.PostRepository;
import ru.itmo.wp.security.AnyRole;
import ru.itmo.wp.security.Guest;
import ru.itmo.wp.service.PostService;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

@Controller
public class MyPostPage extends Page {

    private final PostService postService;

    public MyPostPage(PostService postService) {
        this.postService = postService;
    }

    @Guest
    @GetMapping("/post/{Id}")
    public String post(@NotEmpty @PathVariable String Id, Model model, HttpSession httpSession) {
        Post post = postService.findById(postService.getLongId(Id));
        model.addAttribute("post", post);
        httpSession.setAttribute("post", post);
        model.addAttribute("comment", new Comment());
        return "MyPostPage";
    }

    @AnyRole({Role.Name.WRITER, Role.Name.ADMIN})
    @PostMapping("/post/{Id}")
    public String writeComment(@Valid @ModelAttribute("comment") Comment comment,
                                BindingResult bindingResult,
                                HttpSession httpSession) {
        if (bindingResult.hasErrors()) {
            return "MyPostPage";
        }

        postService.writeComment((Post) httpSession.getAttribute("post"), comment, getUser(httpSession));
        putMessage(httpSession, "You comment a post");

        return "redirect:/post/{Id}";
    }
}
