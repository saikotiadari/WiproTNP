package com.mile1.test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.mile1.bean.Student;
import com.mile1.exception.NullMarksArrayException;
import com.mile1.exception.NullNameException;
import com.mile1.exception.NullStudentObjectException;
import com.mile1.service.StudentReport;
import com.mile1.service.StudentService;

public class StudentTest {

    StudentReport studentReport = new StudentReport();
    StudentService studentService = new StudentService();

    @Test
    public void testTC1() {
        Student s = new Student("Sekar", new int[]{85, 75, 95});
        assertEquals("A+", studentReport.findGrades(s));
    }

    @Test
    public void testTC2() {
        Student s = new Student("Ram", new int[]{35, 35, 35});
        assertEquals("C", studentReport.findGrades(s));
    }

    @Test
    public void testTC3() {
        Student s = new Student("Kumar", new int[]{25, 60, 70});
        assertEquals("F", studentReport.findGrades(s));
    }

    @Test(expected = NullStudentObjectException.class)
    public void testTC4() throws NullNameException, NullMarksArrayException, NullStudentObjectException {
        Student s = null;
        studentReport.validate(s);
    }

    @Test(expected = NullNameException.class)
    public void testTC5() throws NullNameException, NullMarksArrayException, NullStudentObjectException {
        Student s = new Student(null, new int[]{80, 90, 85});
        studentReport.validate(s);
    }

    @Test(expected = NullMarksArrayException.class)
    public void testTC6() throws NullNameException, NullMarksArrayException, NullStudentObjectException {
        Student s = new Student("Manoj", null);
        studentReport.validate(s);
    }

    @Test
    public void testTC7() {
        Student[] data = new Student[3];
        data[0] = new Student("Sekar", new int[]{85, 75, 95});
        data[1] = new Student(null, new int[]{11, 22, 33});
        data[2] = new Student(null, new int[]{40, 50, 60});
        
        assertEquals(2, studentService.findNumberOfNullName(data));
    }

    @Test
    public void testTC8() {
        Student[] data = new Student[3];
        data[0] = new Student("Sekar", new int[]{85, 75, 95});
        data[1] = null;
        data[2] = null;

        assertEquals(2, studentService.findNumberOfNullObjects(data));
    }

    @Test
    public void testTC9() {
        Student[] data = new Student[3];
        data[0] = new Student("Sekar", new int[]{85, 75, 95});
        data[1] = new Student("Manoj", null);
        data[2] = new Student("Rahul", null);

        assertEquals(2, studentService.findNumberOfNullMarksArray(data));
    }
}