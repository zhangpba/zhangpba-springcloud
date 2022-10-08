package com.study.file.service;

import com.study.starter.vo.user.User;

public interface FileService {

    String getFileHost(String name);

    String postFileHost(User user);
}
