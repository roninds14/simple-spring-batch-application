package com.roninds.java.batch.batch;

import com.roninds.java.batch.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
public class Processor implements ItemProcessor<User, User> {

    private static final Map<String, String> DEPT_NAMES = new HashMap<String, String>() {{
        put("001", "Technology");
        put("002", "Operations");
        put("003", "Accounts");
    }};

    @Override
    public User process(User user) throws Exception {
        String deptCode = user.getDept();
        String deptName = DEPT_NAMES.get(deptCode);
        user.setDept(deptName);

        log.info(String.format("Converted from [%s] to [%s].", deptCode, deptName));

        return user;
    }
}
