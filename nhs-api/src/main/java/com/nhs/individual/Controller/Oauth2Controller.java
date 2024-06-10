//package com.nhs.individual.Controller;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
//import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
//import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.servlet.view.RedirectView;
//
//@RestController
//public class Oauth2Controller {
//    private final OAuth2AuthorizedClientService clientService;
//    @Autowired
//    public Oauth2Controller(OAuth2AuthorizedClientService clientService) {
//        this.clientService = clientService;
//    }
//    @RequestMapping(value ="/login/oauth2/code/{provider}" ,method = RequestMethod.GET)
//    public RedirectView loginSuccess(@PathVariable String provider, OAuth2AuthenticationToken authenticationToken){
//        OAuth2AuthorizedClient client = clientService.loadAuthorizedClient(
//                authenticationToken.getAuthorizedClientRegistrationId(),
//                authenticationToken.getName()
//        );
//        client.get
//        return new RedirectView("/");
//    }
//
//}
