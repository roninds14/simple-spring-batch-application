package com.roninds.java.batch.batch;

import com.roninds.java.batch.model.User;
import com.roninds.java.batch.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class DBWriter implements ItemWriter<User> {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void write(List<? extends User> users) throws Exception {
        log.info("Data Saved for Users:" + users);

        userRepository.saveAll(users);
    }
}
