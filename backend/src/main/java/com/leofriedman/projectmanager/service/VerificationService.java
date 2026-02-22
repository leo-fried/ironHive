package com.leofriedman.projectmanager.service;

import org.springframework.stereotype.Service;

@Service
public class VerificationService {

    // The api path
    private final String path;

    // Whether or not the link has expired
    private boolean expired;

    public VerificationService()
    {
        path = "http://127.0.0.1:5500/frontend/src/verify.html?uuid=";
        expired = false;
    }


    public String generateLink(String hashedID)
    {
        return path + hashedID;
    }
    public boolean getExpired()
    {
        return expired;
    }

    public void setExpired(boolean expired)
    {
        this.expired = expired;
    }
}
