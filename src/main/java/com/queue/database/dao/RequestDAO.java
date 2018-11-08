package com.queue.database.dao;

import com.queue.database.entity.RequestDB;
import com.queue.database.entity.RequestRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static com.queue.constants.StatusID.*;

@Transactional
@Repository
public class RequestDAO implements IRequestDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<RequestDB> getAll() {
        String sql = "SELECT * FROM requests";
        RowMapper<RequestDB> rowMapper = new RequestRowMapper();
        return this.jdbcTemplate.query(sql, rowMapper);
    }

    @Override
    public List<RequestDB> getAllUnexecuted() {
        return getAllWithTheSameStatus(RECEIVED);
    }

    @Override
    public List<RequestDB> getAllUnsent() {
        return getAllWithTheSameStatus(EXECUTED);
    }

    private List<RequestDB> getAllWithTheSameStatus(byte statusId){
        String sql = "SELECT * from requests as r WHERE status_id = ?";
        RowMapper<RequestDB> rowMapper = new RequestRowMapper();
        return this.jdbcTemplate.query(sql, rowMapper, statusId);
    }

    @Override
    public void add(RequestDB r) {
        String sql = "INSERT INTO requests(service_id, priority, time_lock, body, status_id) VALUES(?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, r.getServiceId(), r.getPriority(), r.getTimeLock(), r.getBody(), RECEIVED);
    }

    @Override
    public void updateToExecuted(int requestId) {
        String sql = "UPDATE requests SET completion_time=? WHERE id=?";
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String completionTime = sdf.format(date);
        jdbcTemplate.update(sql, completionTime, requestId);
        updateStatus(requestId, EXECUTED);
    }

    @Override
    public void updateToSent(int requestId) {
        updateStatus(requestId, SENT);
    }

    private void updateStatus(int requestId, byte statusId){
        String sql = "UPDATE requests SET status_id=? WHERE id=?";
        jdbcTemplate.update(sql, statusId, requestId);
    }

    @Override
    public int getLastId(){
        String sql = "SELECT MAX(id) FROM requests";
        return jdbcTemplate.queryForObject(sql, int.class);
    }

    @Override
    public boolean isExists(int requestId) {
        String sql = "SELECT * FROM requests WHERE id=?";
        SqlRowSet set = jdbcTemplate.queryForRowSet(sql, requestId);
        return set.next();
    }
}
