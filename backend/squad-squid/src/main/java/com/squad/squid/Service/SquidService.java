package com.squad.squid.Service;

import com.squad.squid.Model.Squid;
import org.springframework.stereotype.Service;

@Service
public class SquidService {

    public Squid getSquidById(String id) {
        return new Squid();
    }

    public String getSquidNameById(String id) {
        return getSquidById(id).getFirstName();
    }

    public boolean updateSquidProfile(Squid user) {
        //find in DB user.getId() and updates it
        return true;
    }
}
