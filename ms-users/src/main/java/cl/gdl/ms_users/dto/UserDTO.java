package cl.gdl.ms_users.dto;


import java.io.Serializable;
import java.util.Set;

import lombok.AllArgsConstructor;

import lombok.Data;
import lombok.NoArgsConstructor;




@Data
@NoArgsConstructor
@AllArgsConstructor

public class UserDTO implements Serializable{
    private String username;
    private String email;
    private String firstName;
    private String lastName;
    private String password;
    private Set<String> roles;
}
