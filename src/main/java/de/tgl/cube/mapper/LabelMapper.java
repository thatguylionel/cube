package de.tgl.cube.mapper;

import de.tgl.cube.entity.Label;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LabelMapper implements RowMapper<Label> {

    public Label mapRow(ResultSet rs, int rowNum) throws SQLException {
        Label label = new Label();
        try {
            label.setName(rs.getString("Name"));
        } catch (Exception e) {
            label.setName("");
        }
        label.setValue(rs.getInt("Value"));
        return label;
    }
}
