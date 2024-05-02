package tfg.apitfg.service;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import tfg.apitfg.model.entity.User;
import tfg.apitfg.repository.UserRepository;

@Slf4j
@Service
@RequiredArgsConstructor
public class OAuth2UserService extends DefaultOAuth2UserService {

    private final UserRepository userRepository;

    @Override
    @SneakyThrows
    public OAuth2User loadUser(OAuth2UserRequest oAuth2UserRequest) {
        log.trace("Load user {}", oAuth2UserRequest);
        OAuth2User oAuth2User = super.loadUser(oAuth2UserRequest);
        var oldUser = userRepository.findById(oAuth2User.getAttribute("email"));
        if (oldUser.isEmpty()){
            var user = User.builder()
                    .email(oAuth2User.getAttribute("email"))
                    .name(oAuth2User.getAttribute("given_name"))
                    .surname(oAuth2User.getAttribute("family_name"))
                    .documentNumber("000000000")
                    .build();

            userRepository.save(user);
        }
        return oAuth2User;
    }

}