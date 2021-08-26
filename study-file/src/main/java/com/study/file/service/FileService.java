package com.study.file.service;

import com.study.vo.User;

public interface FileService {

    String getFileHost(String name);

    String postFileHost(User user);
}
