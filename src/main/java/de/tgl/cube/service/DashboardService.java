package de.tgl.cube.service;


import de.tgl.cube.entity.Label;

import java.util.List;

public interface DashboardService {
    List<Label> checkResults(String name);
}
