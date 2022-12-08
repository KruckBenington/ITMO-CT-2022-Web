package ru.itmo.wp.service;

import org.springframework.stereotype.Service;
import ru.itmo.wp.domain.Notice;
import ru.itmo.wp.domain.User;
import ru.itmo.wp.form.NoticeInformation;
import ru.itmo.wp.form.UserCredentials;
import ru.itmo.wp.repository.NoticeRepository;
import ru.itmo.wp.repository.UserRepository;

import java.util.List;

@Service
public class NoticeService {
    private final NoticeRepository noticeRepository;

    public NoticeService(NoticeRepository noticeRepository) {
        this.noticeRepository = noticeRepository;
    }


    public void addNotice(NoticeInformation noticeInformation) {
        Notice notice = new Notice();
        notice.setContent(noticeInformation.getContent());
        noticeRepository.save(notice);
    }

    public List<Notice> findAll() {
        return noticeRepository.findAllByOrderByCreationTimeDesc();
    }
}
