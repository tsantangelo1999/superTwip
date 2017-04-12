package com.company;

import java.util.ArrayList;

public class Student implements Comparable<Student>
{
    public int num;
    public String name = "";

    public ArrayList<Double> gradesQ1 = new ArrayList<>();
    public ArrayList<Double> gradesQ2 = new ArrayList<>();

    public Student(int n)
    {
        num = n;
    }

    public void assignName(String s)
    {
        name = s;
    }

    public int compareTo(Student s)
    {
        if(this.name.compareTo(s.name) > 0)
            return 1;
        else if(this.name.compareTo(s.name) < 0)
            return -1;
        else
            return 0;
    }
}
