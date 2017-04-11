package com.battle.navigable;

import java.util.concurrent.ConcurrentSkipListMap;

/**
 * Created by Bing.Z on 2017/4/5.
 */
public class Task implements Runnable {

    private ConcurrentSkipListMap<String, Contact> map;
    private String id;

    public Task(ConcurrentSkipListMap<String, Contact> map, String id) {
        this.map = map;
        this.id = id;
    }

    public ConcurrentSkipListMap<String, Contact> getMap() {
        return map;
    }

    public void setMap(ConcurrentSkipListMap<String, Contact> map) {
        this.map = map;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            Contact contact = new Contact(id, String.valueOf(i + 1000));
            map.put(id+contact.getPhone(),contact);
        }
    }
}
