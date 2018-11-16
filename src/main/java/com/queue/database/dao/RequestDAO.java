package com.queue.database.dao;

import com.queue.database.entity.RequestEntity;
import com.queue.database.entity.RequestRowMapper;
import com.queue.queue.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static com.queue.constants.StatusID.RECEIVED;

@Transactional
@Repository
public class RequestDAO implements IRequestDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<RequestEntity> getAllWithTheSameStatus(byte statusId){
        String sql = "SELECT * from requests as r WHERE status_id = ?";
        RowMapper<RequestEntity> rowMapper = new RequestRowMapper();
        return this.jdbcTemplate.query(sql, rowMapper, statusId);
    }

    @Override
    public void add(Request r) {
        String sql = "INSERT INTO requests(service_id, priority, time_lock, body, status_id) VALUES(?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, r.getServiceId(), r.getPriority(), r.getTimeLock(), r.getBody(), RECEIVED);
    }

    @Override
    public void updateStatus(String guid, byte statusId){
        String sql = "UPDATE requests SET status_id=? WHERE guid=?";
        jdbcTemplate.update(sql, statusId, guid);
    }

    @Override
    public void updateCompletionTime(String guid) {
        String sql = "UPDATE requests SET completion_time=? WHERE guid=?";
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String completionTime = sdf.format(date);
        jdbcTemplate.update(sql, completionTime, guid);
    }

    @Override
    public boolean isExists(String guid) {
        String sql = "SELECT * FROM requests WHERE guid=?";
        SqlRowSet set = jdbcTemplate.queryForRowSet(sql, guid);
        return set.next();
    }
}
