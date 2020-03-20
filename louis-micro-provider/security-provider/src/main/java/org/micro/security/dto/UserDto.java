package org.micro.security.dto;

import lombok.Data;

/**
 * @author John·Louis
 *  created at 2019/12/1
 * description:
 */
@Data
public class UserDto {

    private Long id;
    private String username;

    private String password;

}
