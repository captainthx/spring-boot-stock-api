package com.yutsuki.telegram;

import com.yutsuki.telegram.service.TokenService;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class devapplication implements ApplicationRunner {
        @Resource
        private TokenService tokenService;
    @Override
    public void run(ApplicationArguments args) throws Exception {
        String token = "eyJhbGciOiJSUzI1NiJ9.eyJpc3MiOiJ5dXRzdWtpIiwibmFtZSI6Inl1dHN1a2kiLCJleHAiOjE2OTk2MDI0NzEsImlhdCI6MTY5OTU5ODg3MSwianRpIjoiOTQwZWVmNjgtMGVlNi00YjllLTg1MjEtOGI5ZTQ5ZGI3NTk3In0.MqaneQuGJrXz1BcYSRvH5Cu7nJQfwH3wQqm2Uah71tB1rjh-uxxLZAM2WPTulpiLyIvxXUCXhi2uHHSDxmlJCOymsV6jFMVdWz5YGYC4am9S3zret3F9i_RbXsmgd2ToRlcfPn3roibFRHEcqcF7sMZpKAu8t9KMvSS2JA0kT2LNB2tHQxZ3dAURfn4wlDTrPgaidYonw5SpXxVhBBCgTNaCu9eSbHUxQsJxrd8OpN4yzNH0vKqPTU9CR4KP4C4uHadkb-9b7XJsm2DNySBAHUKk8FFqJSb75LgKtQcUPWP8rSVGCSGozQ1nhtcBG6J4XpDdVMRiOaC7XUTkz26AoQ";
    }
}
