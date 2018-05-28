package com.example.demo;

import java.util.Map;

public class Request {

    public enum Action {
        ADD,
        UPDATE,
        MOVE;
    }

    public Request() {
    }

    private Action action;

    private String table;

    private Map<String, String> keys;

    private Map<String, String> columns;

    private String from;

    private String to;

    public Action getAction() {
        return action;
    }

    public void setAction(Action action) {
        this.action = action;
    }

    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
    }

    public Map<String, String> getKeys() {
        return keys;
    }

    public void setKeys(Map<String, String> keys) {
        this.keys = keys;
    }

    public Map<String, String> getColumns() {
        return columns;
    }

    public void setColumns(Map<String, String> columns) {
        this.columns = columns;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    @Override
    public String toString() {
        return "Request{" +
                "action=" + action +
                ", table='" + table + '\'' +
                ", keys=" + keys +
                ", columns=" + columns +
                ", from='" + from + '\'' +
                ", to='" + to + '\'' +
                '}';
    }
}
