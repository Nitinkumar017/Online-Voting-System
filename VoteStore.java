package com.voting;

import java.util.LinkedHashMap;
import java.util.Map;

public class VoteStore {
    private static final Map<String, Integer> votes = new LinkedHashMap<>();
    static {
        votes.put("Python Programming", 0);
        votes.put("Java Programming", 0);
        votes.put("Machine Learning", 0);
        votes.put("Data Structures", 0);
    }

    public static synchronized boolean addVote(String option) {
        if (!votes.containsKey(option)) return false;
        votes.put(option, votes.get(option) + 1);
        return true;
    }

    public static synchronized Map<String, Integer> getVotes() {
        return new LinkedHashMap<>(votes);
    }

    public static synchronized int getTotal() {
        int t = 0;
        for (int v : votes.values()) t += v;
        return t;
    }
}