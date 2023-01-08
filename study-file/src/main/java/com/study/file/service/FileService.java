package com.study.file.service;

import com.study.common.entity.User;

public interface FileService {

    String getFileHost(String name);

    String postFileHost(User user);
}
