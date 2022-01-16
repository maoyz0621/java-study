package com.myz.thread.stack.profile;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 堆栈跟踪
 * 开始时间
 * 结束时间
 * 当前栈级别
 *
 * @author maoyz
 * @version V1.0
 * @date 2021/9/27 14:31
 */
public class StackTrace {
    private List<ProfileEntry> profileEntryList = new ArrayList<>(32);
    private Stack<ProfileEntry> enterStack = new Stack<>();
    private long startTime;
    private long endTime;
    private int currentStackLevel;
    private String message;

    public StackTrace(String message) {
        this.message = message;
        this.startTime = System.currentTimeMillis();
        this.endTime = startTime;
        this.currentStackLevel = 0;
    }

    public void enter(ProfileEntry profileEntry) {
        profileEntry.setLevel(++this.currentStackLevel);
        profileEntry.setEnterTime(System.currentTimeMillis());
        profileEntryList.add(profileEntry);
        enterStack.push(profileEntry);
    }

    public void exit() {
        --this.currentStackLevel;
        ProfileEntry entry = enterStack.pop();
        if (entry != null) {
            entry.setExitTime(System.currentTimeMillis());
        }
    }

    public void end() {
        this.endTime = System.currentTimeMillis();
    }

    /**
     * 清除
     */
    public void clear() {
        if (profileEntryList.size() != 0) {
            profileEntryList.clear();
        }

        if (enterStack.size() != 0) {
            enterStack.clear();
        }

    }

    public List<ProfileEntry> getProfileEntryList() {
        return profileEntryList;
    }

    public void setProfileEntryList(List<ProfileEntry> profileEntryList) {
        this.profileEntryList = profileEntryList;
    }
}
