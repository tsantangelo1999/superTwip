package com.company;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;

public class Main
{
    public static ArrayList<Student> students = new ArrayList<>();

    public static void main(String[] args) throws IOException
    {
        File grades = new File("rawDataU6.txt");
        Scanner sc = new Scanner(grades);
        sc.useDelimiter("\t");
        while(sc.hasNextLine())
        {
            int num;
            try
            {
                num = sc.nextInt();
            }
            catch(Exception e)
            {
                sc.nextLine();
                continue;
            }
            double q1 = 0;
            q1 += getPoints(sc.next());
            q1 += getPoints(sc.next());
            q1 -= sc.nextInt() * 0.25;
            sc.next(); //skip comments
            double q2 = 0;
            q2 += getPoints(sc.next());
            q2 += getPoints(sc.next());
            q2 += getPoints(sc.next());
            q2 -= sc.nextInt() * 0.25;
            addScores(num, q1, q2);
            sc.nextLine();
        }
        File names = new File("names.txt");
        Scanner sc2 = new Scanner(names);
        sc2.useDelimiter("\t");
        while(sc2.hasNextLine())
        {
            String name = sc2.next();
            int num = sc2.nextInt();
            for(Student s : students)
            {
                if(num == s.num)
                {
                    s.assignName(name);
                    break;
                }
            }
            sc2.nextLine();
        }

        
        System.out.println("Name\tSecret Number\tAvg Total Grade\tFR Q1 Grade\tFR Q2 Grade");
        for(Student s : students)
        {
            System.out.println(s.name + "\t" + s.num + "\t" + );
        }
    }

    public static double getPoints(String s) //receives a comma separated score thingy
    {
        String[] points = s.split(", ");
        double score = 0;
        for(String t : points)
        {
            Scanner find = new Scanner(t);
            find.next(); //skip "+"
            score += Double.parseDouble(find.next());
        }
        return score;
    }

    public static void addScores(int num, double q1, double q2)
    {
        for(Student s : students)
        {
            if(num == s.num)
            {
                s.gradesQ1.add(q1);
                s.gradesQ2.add(q2);
                break;
            }
        }
        students.add(new Student(num));
        students.get(students.size() - 1).gradesQ1.add(q1);
        students.get(students.size() - 1).gradesQ1.add(q2);
    }
}
