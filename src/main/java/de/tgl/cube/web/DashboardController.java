package de.tgl.cube.web;


import de.tgl.cube.entity.Label;
import de.tgl.cube.service.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DashboardController {

    @Autowired
    private DashboardService dashboardService;

    @RequestMapping(method = RequestMethod.GET, path = "/checks/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
    private List<Label> checksResults(@PathVariable("name") String name) {
        return dashboardService.checkResults(name);
    }
}
