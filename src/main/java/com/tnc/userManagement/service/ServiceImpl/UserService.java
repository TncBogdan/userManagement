package com.tnc.userManagement.service.ServiceImpl;

import com.tnc.userManagement.repository.UserRepository;
import com.tnc.userManagement.repository.entity.RoleEnum;
import com.tnc.userManagement.repository.entity.RoleEnum.*;
import com.tnc.userManagement.service.IUserService;
import com.tnc.userManagement.service.mapper.UserDomainMapper;
import com.tnc.userManagement.service.model.UserDomain;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.spi.LoggerRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
public class UserService implements IUserService {
    private final Logger LOGGER = LoggerFactory.getLogger(getClass()); //getClass = this class

    private final UserRepository userRepository;
    private final UserDomainMapper userDomainMapper;

    @Override
    public UserDomain login(UserDomain userDomain) {
        return null;
    }

    @Override
    public UserDomain register(String firstName, String lastName, String email) {
        var userDomain = new UserDomain();
        userDomain.setUserId(generateUserId());
        String password = generatePassword();
        userDomain.setPassword(password);
        userDomain.setFirstName(firstName);
        userDomain.setLastName(lastName);
        userDomain.setEmail(email);
        userDomain.setRole(RoleEnum.ROLE_USER.name());
        userDomain.setAuthorities(RoleEnum.ROLE_USER.getAuthorities());
        userDomain.setActive(true);
        userDomain.setNotLocked(true);
        userDomain.setJoinDate(new Date());
        userRepository.save(userDomainMapper.toEntity(userDomain));
        LOGGER.info("Password is " + password);
        return userDomain;
    }

    private String generatePassword() {
        return RandomStringUtils.randomAlphanumeric(10);

    }

    private String generateUserId() {
        return RandomStringUtils.randomNumeric(10);
    }

    @Override
    public UserDomain findByEmail(String email) {
        return null;
    }

    @Override
    public UserDomain addNewUserWithSpecificRole(String firstName, String laseName, String email, String role, boolean isActive, boolean isNotActive) {
        return null;
    }

    @Override
    public UserDomain updateUser(String newFirstName, String newLaseName, String newEmail, String role, boolean isActive, boolean isNotActive) {
        return null;
    }

    @Override
    public void deleteUser(Long id) {

    }

    @Override
    public List<UserDomain> getAll() {
        return userDomainMapper.toDomainList(userRepository.findAll());
    }
}