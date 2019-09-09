package domain;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class TwitterUser {

    private final String email;
    private final String password;
    private final List<TwitterUser> following;

}