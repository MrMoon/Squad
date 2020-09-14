package com.squad.squid.API;

import com.squad.squid.Model.Squid;
import com.squad.squid.Service.SquidService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/squid")
public class SquidController {

    private final SquidService squidService;
    @Autowired
    public SquidController(SquidService squidService){
        this.squidService=squidService;
    }
    @GetMapping(value = "/{userId}")
    public Squid getSquid(@PathVariable String userId) {
        return squidService.getSquidById(userId);
    }

    @GetMapping(value = "/name/{userId}")
    public String getSquidName(@PathVariable String userId) {
        return squidService.getSquidNameById(userId);
    }

    @PutMapping("/{user}")
    public boolean updateSquid(@PathVariable Squid user) {
        return squidService.updateSquidProfile(user);
    }

}
