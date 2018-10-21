package com.queue.db.dao;

import com.queue.db.entity.RequestDB;
import com.queue.db.entity.RequestRowMapper;
import com.queue.q.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Repository
public class RequestDAO implements IRequestDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Request> getAll() {
        String sql = "SELECT * FROM requests";
        RowMapper<Request> rowMapper = new RequestRowMapper();
        return this.jdbcTemplate.query(sql, rowMapper);
    }

    @Override
    public List<Request> getAllUnexecuted() {
        String sql = "SELECT * from requests WHERE executed = FALSE";
        RowMapper<Request> rowMapper = new RequestRowMapper();
        return this.jdbcTemplate.query(sql, rowMapper);
    }

    @Override
    public void add(RequestDB r) {
        String sql = "INSERT INTO requests(service_id, priority, time_lock, body, executed) VALUES(?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, r.getServiceId(), r.getPriority(), r.getTimeLock(), r.getBody(), r.getStatus());
    }

    @Override
    public void updateToExecuted(int requestId) {
        String sql = "UPDATE requests SET executed=TRUE WHERE id=?";
        jdbcTemplate.update(sql, requestId);
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
