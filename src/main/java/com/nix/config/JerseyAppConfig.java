package com.nix.config;

import org.glassfish.jersey.server.ResourceConfig;

import javax.ws.rs.ApplicationPath;

@ApplicationPath("/api/rest")
public class JerseyAppConfig extends ResourceConfig {

    public JerseyAppConfig() {
        packages("com.nix.api.rest");
    }

}