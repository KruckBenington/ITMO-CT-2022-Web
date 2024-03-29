package ru.itmo.wp.service;

import org.springframework.stereotype.Service;
import ru.itmo.wp.domain.Comment;
import ru.itmo.wp.domain.Post;
import ru.itmo.wp.domain.User;
import ru.itmo.wp.repository.PostRepository;

import java.util.List;

@Service
public class PostService {
    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public List<Post> findAll() {
        return postRepository.findAllByOrderByCreationTimeDesc();
    }


    public Post findById(long id) {
        return postRepository.findById(id);
    }

    public Long getLongId(String id) {
        final long zero = 0;
        try {
            if (id.isEmpty()) {
                return zero;
            }
            return Long.valueOf(id);
        } catch (NumberFormatException ignored) {
            return zero;
        }
    }

    public void writeComment(Post post, Comment comment, User user) {
        comment.setUser(user);
        post.addComment(comment);
        comment.setPost(post);
        postRepository.save(post);
    }
}
