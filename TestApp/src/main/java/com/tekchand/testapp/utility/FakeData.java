package com.tekchand.testapp.utility;

import com.tekchand.testapp.ui.main.tab1.Human;

import java.util.ArrayList;
import java.util.List;

public class FakeData {
    private List<Human> humans = new ArrayList<>();
    public List<Human> getFakeData(){
        Human human1 = new Human("Tek", "Sikar","tekchand@iitk.ac.in");
        Human human2 = new Human ("Ayush", "Kanpur", "ayushg@iitk.ac.in");
        Human human3 = new Human("Atul", "BHU", "atul@iitbhu.ac.in");
        humans.add(human1);
        humans.add(human2);
        humans.add(human3);
        return humans;
    }

}
