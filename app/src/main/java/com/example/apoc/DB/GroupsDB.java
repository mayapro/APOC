package com.example.apoc.DB;

import com.example.apoc.types.Group;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class GroupsDB extends DBWrapper {
    protected static String DOC_NAME = "groups";
    protected static String NAME = "name";
    protected static String LEADER = "leader";
    protected static String GROUPIES = "groupies";
    protected static String FEARS = "fears";


    @Override
    void updateItem(DBItem updateItem) {
        Group item = (Group)updateItem;
        Map<String, Object> newItem = new HashMap<>();
        newItem.put(ID, item.getId());
        newItem.put(NAME, item.getGroupName());
        newItem.put(LEADER, toGson(item.getLeader()));
        newItem.put(GROUPIES, toGson(item.getGroupies()));
        newItem.put(FEARS, toGson(item.getFears()));

        db.collection(DOC_NAME).document(String.valueOf(item.getId())).set(newItem);
    }

    @Override
    protected DBItem parseItem(Map<String, Object> item) {
        return new Group((String) item.get(NAME),
                (String) item.get(LEADER),
                fromGson((String) item.get(GROUPIES),ArrayList.class),
                fromGson((String) item.get(FEARS),ArrayList.class));
    }
}