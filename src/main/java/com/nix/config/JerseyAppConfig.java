package com.nix.config;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.ServerProperties;

import javax.ws.rs.ApplicationPath;

@ApplicationPath("/api/rest/**")
public class JerseyAppConfig extends ResourceConfig {

    public JerseyAppConfig() {
        packages("com.nix.api.rest");

        property(ServerProperties.BV_SEND_ERROR_IN_RESPONSE, true);

    }


}