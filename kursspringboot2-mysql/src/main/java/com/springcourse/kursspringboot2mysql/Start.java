package com.springcourse.kursspringboot2mysql;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Start {

    private VideoDao videoDao;

    @Autowired
    public Start(VideoDao videoDao) {
        this.videoDao = videoDao;
//        videoDao.saveVideo(2L, "Java", "bllll.pl/java");
//        videoDao.saveVideo(3L, "Spring", "bllll.pl/spring");
//        videoDao.saveVideo(4L, "Hibernate", "bllll.pl/hibernate");

       // videoDao.updateVideo(new Video(4L, "CI/CD", "blll.pl/cicd"));
        //videoDao.deleteVideo(2L);
        //videoDao.findAll().forEach(System.out::println);
        System.out.println(videoDao.getOne(4L));
    }
}
