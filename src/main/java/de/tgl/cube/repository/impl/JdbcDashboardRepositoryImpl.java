package de.tgl.cube.repository.impl;

import de.tgl.cube.entity.Label;
import de.tgl.cube.mapper.LabelMapper;
import de.tgl.cube.repository.DashboardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
public class JdbcDashboardRepositoryImpl implements DashboardRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Label> queryForLabelList(String sql) {
        List<Label> label;
        try {
            label = jdbcTemplate.query(sql, new LabelMapper());
        } catch (Exception se) {
            label = new ArrayList<>();
        }
        return label;
    }

    @Override
    public Label queryForCount(String sql) {
        Label label;
        try {
            label = jdbcTemplate.queryForObject(sql, new LabelMapper());
            if (label == null) {
                label = new Label();
                label.setValue(0);
            } else {
                if (label.getValue() == null) {
                    label.setValue(0);
                }
            }
        } catch (Exception se) {
            label = new Label();
            label.setName("");
            label.setValue(0);
        }
        return label;
    }

    @Override
    public List<Label> queryProcedure(String sql) {
        List<Label> labels = new ArrayList<>();
        Label label;
        try {
            List<Map<String, Object>> mapList = jdbcTemplate.queryForList(sql);
            if (!mapList.isEmpty()) {
                for (Map<String, Object> mapper : mapList) {
                    label = new Label();
                    for (Map.Entry<String, Object> entry : mapper.entrySet()) {
                        if (entry.getKey().equalsIgnoreCase("job_name")) {
                            label.setName(entry.getValue().toString());
                        } else if (entry.getKey().equalsIgnoreCase("last_run_status")) {
                            label.setPass(entry.getValue().toString().trim().equalsIgnoreCase("Succeeded"));
                        }
                        labels.add(label);
                    }

                }
                labels = labels.stream().distinct().collect(Collectors.toList());
            }
        } catch (Exception se) {
            labels = new ArrayList<>();
        }
        return labels;
    }
}
