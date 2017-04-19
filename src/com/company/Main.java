package com.company;

import java.io.File;
import java.io.IOException;
import java.text.FieldPosition;
import java.text.ParsePosition;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;
import java.text.NumberFormat;

public class Main
{
    public static ArrayList<Student> students = new ArrayList<>();

    public static void main(String[] args) throws IOException
    {
        NumberFormat nf = NumberFormat.getInstance();
        nf.setMaximumFractionDigits(3);
        nf.setMinimumFractionDigits(0);
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
            try
            {
                q1 -= sc.nextInt() * 0.25;
            }
            catch(InputMismatchException e)
            {

            }
            sc.next(); //skip comments
            double q2 = 0;
            q2 += getPoints(sc.next());
            q2 += getPoints(sc.next());
            q2 += getPoints(sc.next());
            try
            {
                q2 -= sc.nextInt() * 0.25;
            }
            catch(InputMismatchException e)
            {

            }
            addScores(num, q1, q2);
            sc.nextLine();
        }
        File names = new File("namesTest.tsv");
        Scanner sc2 = new Scanner(names);
        sc2.useDelimiter("\t|\r\n");
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
        Collections.sort(students);
        int maxTabs = 1;
        for(Student s : students)
        {
            if(s.name.length() / 4 > maxTabs)
                maxTabs = s.name.length() / 4;
        }
        System.out.println("Name" + getTabs(maxTabs) + "Secret Number\tAvg Total Grade\tFR Q1 Grade\tFR Q2 Grade");
        for(Student s : students)
        {
            System.out.println(s.name + getTabs(maxTabs + 1 - s.name.length() / 4) + s.num + "\t\t\t\t" + nf.format(s.avgGrade()) + "" + getTabs(maxTabs + 1 - nf.format(s.avgGrade()).length()) + "" + nf.format(s.avgQ1()) + "" + getTabs(maxTabs + 1 - nf.format(s.avgGrade()).length()) + "" + nf.format(s.avgQ2()));
        }
    }

    public static String getTabs(int i)
    {
        String tab = "";
        for(int j = 0; j < i; j++)
        {
            tab += "\t";
        }
        return tab;
    }

    public static double getPoints(String s) //receives a comma separated score thingy
    {
        if(s.equals(""))
            return 0;
        String[] points = s.split(", ");
        double score = 0;
        for(String t : points)
        {
            Scanner find = new Scanner(t);
            find.next(); //skip "+"
            try
            {
                score += Double.parseDouble(find.next());
            }
            catch(Exception e)
            {

            }
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
                return;
            }
        }
        students.add(new Student(num));
        students.get(students.size() - 1).gradesQ1.add(q1);
        students.get(students.size() - 1).gradesQ1.add(q2);
    }
}
