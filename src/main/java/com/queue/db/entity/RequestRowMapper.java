package com.queue.db.entity;

import com.queue.q.Request;
import org.springframework.jdbc.core.RowMapper;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RequestRowMapper implements RowMapper<Request> {
    @Override
    public Request mapRow(ResultSet row, int rowNum) throws SQLException {
        RequestDB request = new RequestDB();
        request.setRequestDBId(row.getInt("id"));
        request.setServiceId(row.getByte("service_id"));
        request.setPriority(row.getByte("priority"));
        request.setTimeLock(row.getInt("time_lock"));
        request.setExecuted(row.getBoolean("executed"));
        request.setBody(deserialization(row, "body"));
        request.setCreationTime(row.getTime("creation_time"));
        request.setCompletionTime(row.getTime("completion_time"));
        return request;
    }

    private Object deserialization(ResultSet row, String columnLabel) {
        try {
            InputStream is = row.getBlob(columnLabel).getBinaryStream();
            ObjectInputStream objectIn = new ObjectInputStream(is);
            Object result = objectIn.readObject();
            return result;
        } catch (IOException | ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } return null;
    }
}
