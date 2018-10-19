package de.tgl.cube.service.impl;

import de.tgl.cube.entity.Label;
import de.tgl.cube.repository.DashboardRepository;
import de.tgl.cube.service.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DashboardServiceImpl implements DashboardService {

    @Autowired
    private DashboardRepository dashboardRepository;

    /**
     * <p>This method will execute all associated methods</p>
     *
     * @param name
     * @return
     */
    @Cacheable("Stats")
    @Override
    public List<Label> checkResults(String name) {
        List<Label> labelList = new ArrayList<>();
        if ("all".equalsIgnoreCase(name)) {
            labelList.add(statusUpdate());
        } else if ("statusupdate".equalsIgnoreCase(name)) {
            labelList.add(statusUpdate());
        } else {
            return new ArrayList<>();
        }
        return labelList;
    }

    private Label statusUpdate() {
        return setupQuery("SELECT 'A_Column_Value' as \"Name\", COUNT(*) as \"Value\" from t_table_name where name is not NULL;");
    }

    /**
     * <p>Use this method when executing "SELECT" statements</p>
     *
     * @param sql
     * @return
     */
    private Label setupQuery(String sql) {
        Label label = dashboardRepository.queryForCount(sql);
        if (label.getValue() > 0) {
            label.setPass(false);
        } else {
            label.setPass(true);
        }
        return label;
    }

    private List<Label> setupResultWithSql(String sql) {
        return dashboardRepository.queryProcedure(sql);
    }
}
