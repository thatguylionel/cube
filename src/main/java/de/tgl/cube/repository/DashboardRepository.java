package de.tgl.cube.repository;


import de.tgl.cube.entity.Label;

import java.util.List;

public interface DashboardRepository {

    List<Label> queryForLabelList(String sql);

    Label queryForCount(String sql);

    List<Label> queryProcedure(String sql);
}
