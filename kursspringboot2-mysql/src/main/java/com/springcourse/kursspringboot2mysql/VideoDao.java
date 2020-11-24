package com.springcourse.kursspringboot2mysql;

import java.util.List;

//CRUD
public interface VideoDao {
    void saveVideo(long id, String title, String url);
    List<Video> findAll();
    void updateVideo(Video newVideo);
    void deleteVideo(long id);
    Video getOne(long id);
}
