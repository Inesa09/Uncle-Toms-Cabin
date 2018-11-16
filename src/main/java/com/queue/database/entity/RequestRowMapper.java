package com.queue.database.entity;

import com.queue.queue.Request;
import org.springframework.jdbc.core.RowMapper;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RequestRowMapper implements RowMapper<RequestEntity> {
    @Override
    public RequestEntity mapRow(ResultSet row, int rowNum) throws SQLException {
        RequestEntity request = new RequestEntity();
        request.setGuid(row.getString("guid"));
        request.setServiceId(row.getByte("service_id"));
        request.setPriority(row.getByte("priority"));
        request.setTimeLock(row.getInt("time_lock"));
        request.setStatusId(row.getByte("status_id"));
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