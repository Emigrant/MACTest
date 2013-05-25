package com.user.mactest.utils;

import com.user.mactest.models.Course;
import com.user.mactest.models.Gruppa;
import com.user.mactest.models.Student;

import android.content.Context;
import android.util.Log;

public class Util {
    

    public static void writeData(Context context){
        final DatabaseHandler db = new DatabaseHandler(context, DatabaseHandler.TABLE_ATTND);

        Log.d("@@@", "Writing...");
        db.addElement(new Course(1515, "CSI"));
        db.addElement(new Course(1516, "JAVA"));

        db.addElement(new Gruppa(1411, "A04", 1515));
        db.addElement(new Gruppa(1421, "B04", 1515));
        db.addElement(new Gruppa(1431, "A04", 1516));
        db.addElement(new Gruppa(1441, "B04", 1516));

        db.addElement(new Student(1111, "Abdusaitova Nazugum", 1411));
        db.addElement(new Student(1224, "Abdusaitova Nazugum", 1431));
        
       

    }
}
