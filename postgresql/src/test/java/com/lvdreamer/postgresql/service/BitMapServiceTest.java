package com.lvdreamer.postgresql.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.roaringbitmap.RoaringBitmap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.support.AbstractLobCreatingPreparedStatementCallback;
import org.springframework.jdbc.support.lob.DefaultLobHandler;
import org.springframework.jdbc.support.lob.LobCreator;
import org.springframework.jdbc.support.lob.LobHandler;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.*;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BitMapServiceTest {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    public void query() {
        String sql = "select bitmap::bytea from user_tag";
        Object[] obj = new Object[]{""};
        final LobHandler lobHandler = new DefaultLobHandler();
        List<RoaringBitmap> list = jdbcTemplate.query(sql, (rs, rowNum) -> {
            //以二进制的数组方式获得Blob数据
            InputStream is = lobHandler.getBlobAsBinaryStream(rs, 1);
            if (is != null) {
                RoaringBitmap rb = new RoaringBitmap();
                DataInputStream dis = new DataInputStream(is);
                try {
                    rb.deserialize(dis);
                    dis.close();
                    return rb;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return null;
        });
        System.out.println(list);
    }

    public static int getNum(int start, int end) {
        return (int) (Math.random() * (end - start + 1) + start);
    }

    @Test
    public void insert() throws IOException {

        String sql = "insert into  user_tag(id,bitmap) values (?,?::bytea::roaringbitmap)";
        LobHandler lobHandler = new DefaultLobHandler();
        RoaringBitmap rb = RoaringBitmap.bitmapOf();
        for (int i = 50; i < 100; i++) {
            rb.add(i);
        }
        ByteArrayOutputStream bos = new ByteArrayOutputStream(rb.serializedSizeInBytes());
        rb.serialize(new DataOutputStream(bos));
        jdbcTemplate.execute(sql, new AbstractLobCreatingPreparedStatementCallback(lobHandler) {
            @Override
            protected void setValues(PreparedStatement ps, LobCreator lobCreator) throws SQLException, DataAccessException {
                ps.setInt(1, 2);
                byte bz[] = bos.toByteArray();
                lobCreator.setBlobAsBytes(ps, 2, bz);
            }
        });
    }

    @Test
    public void insert2() throws IOException {

        String sql = "insert into  user_tag(id,bitmap) values (?,?::bytea::roaringbitmap)";
        LobHandler lobHandler = new DefaultLobHandler();
        RoaringBitmap rb = this.createBitMap();
        ByteArrayOutputStream bos = new ByteArrayOutputStream(rb.serializedSizeInBytes());
        rb.serialize(new DataOutputStream(bos));
        jdbcTemplate.execute(sql, new AbstractLobCreatingPreparedStatementCallback(lobHandler) {
            @Override
            protected void setValues(PreparedStatement ps, LobCreator lobCreator) throws SQLException, DataAccessException {
                ps.setInt(1, 201);
                byte bz[] = bos.toByteArray();
                lobCreator.setBlobAsBytes(ps, 2, bz);
            }
        });
    }

    public RoaringBitmap createBitMap() {
        int i = 1;
        int j = 1000;

        RoaringBitmap roaringBitmap = RoaringBitmap.bitmapOf();
        while (i++ < j || ((j = 1000000 - roaringBitmap.getCardinality()) > 0)) {
            String middle = String.valueOf(getNum(1, 999) + 10000).substring(1);
            String last = String.valueOf(getNum(1, 9999) + 10000).substring(1);
            String num = middle + last;
            roaringBitmap.add(Integer.parseInt(num));
            if (i > j) {
                i = 1;
            }
        }
        return roaringBitmap;
    }
}