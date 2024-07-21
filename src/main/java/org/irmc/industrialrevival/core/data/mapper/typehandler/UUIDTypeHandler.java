package org.irmc.industrialrevival.core.data.mapper.typehandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

public class UUIDTypeHandler extends BaseTypeHandler<UUID> {
    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, UUID parameter, JdbcType jdbcType)
            throws SQLException {
        ps.setString(i, parameter.toString());
    }

    @Override
    public UUID getNullableResult(ResultSet rs, String columnName) {
        try {
            return UUID.fromString(rs.getString(columnName));
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public UUID getNullableResult(ResultSet rs, int columnIndex) {
        try {
            return UUID.fromString(rs.getString(columnIndex));
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public UUID getNullableResult(CallableStatement cs, int columnIndex) {
        try {
            return UUID.fromString(cs.getString(columnIndex));
        } catch (Exception e) {
            return null;
        }
    }
}
