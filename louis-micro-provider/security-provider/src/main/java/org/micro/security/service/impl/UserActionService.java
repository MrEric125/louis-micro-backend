package org.micro.security.service.impl;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import org.micro.security.entity.SysUser;
import org.micro.security.entity.UserAction;
import org.micro.security.repository.UserActionRepository;
import org.micro.security.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Optional;

/**
 * @author louis
 * <p>
 * Date: 2019/11/20
 * Description:
 */
@Service
public class UserActionService {

    @Autowired
    private UserActionRepository userActionRepository;

    @Autowired
    private UserRepository userRepository;

    Cache<Long, List<UserAction>> userActionCache = CacheBuilder.newBuilder().build();


    public List<UserAction> getOwnActionListByUserId(Long userID) {
        List<UserAction> userActionList = userActionRepository.findByUser(userID);
        userActionCache.put(userID, userActionList);
        return userActionList;
    }

    public List<UserAction> loadActionUrl(String userName) {
        Optional<SysUser> userOptional = userRepository.findByUsername(userName);
        SysUser user = userOptional.orElse(null);
        if (user != null) {
            return Optional.ofNullable(userActionCache.getIfPresent(user.getId())).orElseGet(() -> userActionRepository.findByUser(user.getId()));
        }
        return null;


    }


}
