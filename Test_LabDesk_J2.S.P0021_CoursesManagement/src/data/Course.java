/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

/**
 *
 * @author Admin
 */
public class Course implements Comparable<Course>{

    private String code;
    private String name;
    private String credit;

    public Course() {
    }

    public Course(String code, String name, String credit) {
        this.code = code;
        this.name = name;
        this.credit = credit;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCredit() {
        return credit;
    }

    public void setCredit(String credit) {
        this.credit = credit;
    }

    @Override
    public String toString() {
        return code + " | " + name + " | " + credit;
    }

    @Override
    public int compareTo(Course o) {
        return this.credit.compareTo(o.credit);
    }

}
