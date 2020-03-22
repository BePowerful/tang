package com.wcq.tang.service.admin;

import com.github.pagehelper.Page;
import com.wcq.tang.dto.UserMangerDto;
import com.wcq.tang.model.User;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;

/**
 * @author wcq
 * @version 1.0
 * @date 2020/3/1 13:52
 */
public interface UserMangerService {
    UserMangerDto getUserMangerDtoById(Long id);
    Page<User> getUserMangerDtoByName(String username,Integer page, Integer limit);
    UserMangerDto getUserMangerDtoByEmail(String email);
    Page<User> getDefaultUser(Integer page, Integer limit);

    int updateUser(User user);
    List<UserMangerDto> userListToUMD(Page<User> userPage);
}
