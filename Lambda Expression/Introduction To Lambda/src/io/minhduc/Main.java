package io.minhduc;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Hello From Runnable");
            }
        }).start();

        new Thread(()-> System.out.println("Hello world! From Lambda Expression")).start();

        new Thread(()-> {
            System.out.println("Print line 1");
            System.out.println("Print line 2");
        }).start();

        Employee john = new Employee("John Doe", 19);
        Employee minhtri = new Employee("Minh tri cao", 18);
        Employee chicuong = new Employee("Chi Cuong Lu", 20);
        Employee minhduc = new Employee("Minh duc cao", 20);

        List<Employee> employeeList = new ArrayList<>();
        employeeList.add(john);
        employeeList.add(minhduc);
        employeeList.add(minhtri);
        employeeList.add(chicuong);

        Collections.sort(employeeList, (emp1, emp2)
                -> emp1.getName().compareTo(emp2.getName()));

        Collections.sort(employeeList, new Comparator<Employee>() {
            @Override
            public int compare(Employee o1, Employee o2) {
                return o1.getName().compareTo(o2.getName());
            }
        });

        for (int i = 0; i < employeeList.size() - 1; i++) {
            for (int j = i + 1; j < employeeList.size(); j++) {
                if (employeeList.get(i).getName().compareTo(employeeList.get(j).getName()) == 1) {
                    Employee tempEmp = employeeList.get(j);
                    employeeList.set(j, employeeList.get(i));
                    employeeList.set(i, tempEmp);
                }
            }
        }

        for(Employee emp : employeeList) {
            System.out.println(emp.getName());
        }

        String sillyString = doStringStuff(new Upperconcat() {
            @Override
            public String upperAndConcat(String s1, String s2) {
                return s1.toUpperCase() + " " + s2.toUpperCase();
            }
        }, employeeList.get(0).getName(), employeeList.get(1).getName());
        System.out.println(sillyString);

        Upperconcat uc = (s1, s2) -> s1.toUpperCase() + s2.toUpperCase();
        String sillyStringLambda = doStringStuff(uc, employeeList.get(0).getName(), employeeList.get(1).getName());
        System.out.println(sillyStringLambda);
    }

    public final static String doStringStuff(Upperconcat uc, String s1, String s2) {
        return uc.upperAndConcat(s1, s2);
    }
}

interface Upperconcat {
    public String upperAndConcat(String s1, String s2);
}
