package com.pse.hjss;
public class AddLearnersLessons {
    public static void addLearners(){
        int id = Manager.generateLearnerID();
        Manager.learnersHashMap.put(id, new Learner(id, "John", "Grisham", 8, "male", 2, "07009090904"));
        id = Manager.generateLearnerID();
        Manager.learnersHashMap.put(id, new Learner(id, "Sara", "Cox",5, "female", 2, "07009090604"));
        id = Manager.generateLearnerID();
        Manager.learnersHashMap.put(id, new Learner(id, "Louis", "Culling", 6, "male", 4, "07009090804"));
        id = Manager.generateLearnerID();
        Manager.learnersHashMap.put(id, new Learner(id, "Haris", "Taylor", 4, "male", 1, "07009095904"));
        id = Manager.generateLearnerID();
        Manager.learnersHashMap.put(id, new Learner(id, "Chloe", "Kelly", 9, "female", 5, "07009490904"));
        id = Manager.generateLearnerID();
        Manager.learnersHashMap.put(id, new Learner(id, "Liara", "Govender", 11, "female", 3, "07009094904"));
        id = Manager.generateLearnerID();
        Manager.learnersHashMap.put(id, new Learner(id, "Sanju", "Isaac", 7, "male", 4, "07009090304"));
        id = Manager.generateLearnerID();
        Manager.learnersHashMap.put(id, new Learner(id, "Katherine", "Blake", 10, "female", 4, "07002090904"));
        id = Manager.generateLearnerID();
        Manager.learnersHashMap.put(id, new Learner(id, "Mohammad", "Ali", 8, "male", 2, "07009091904"));
        id = Manager.generateLearnerID();
        Manager.learnersHashMap.put(id, new Learner(id, "Stephen", "Curry", 9, "male", 5, "07009000904"));
        id = Manager.generateLearnerID();
        Manager.learnersHashMap.put(id, new Learner(id, "Paul", "Pogba", 6, "male", 1, "07009098904"));
        id = Manager.generateLearnerID();
        Manager.learnersHashMap.put(id, new Learner(id, "Leonardo", "Santoro", 7, "male", 3, "07019090904"));
        id = Manager.generateLearnerID();
        Manager.learnersHashMap.put(id, new Learner(id, "Alina", "Fernandez", 5, "female", 1, "07229090904"));
    }

    public static void addCoaches(){
        Manager.coachesNamesArrayList.add("James");
        Manager.coachesNamesArrayList.add("Ela");
        Manager.coachesNamesArrayList.add("Dave");
        Manager.coachesNamesArrayList.add("Sara");
    }
    public static void addLessons(){
        //first week
        int lessonID = Manager.generateLessonID();
        Manager.lessonsHashMap.put(lessonID, new Lesson(lessonID, 2, "James", "Mon, Apr 01 2024 04:00 pm"));
        lessonID = Manager.generateLessonID();
        Manager.lessonsHashMap.put(lessonID, new Lesson(lessonID, 4, "Ela", "Mon, Apr 01 2024 05:00 pm"));
        lessonID = Manager.generateLessonID();
        Manager.lessonsHashMap.put(lessonID, new Lesson(lessonID, 5, "Dave", "Mon, Apr 01 2024 06:00 pm"));
        lessonID = Manager.generateLessonID();
        Manager.lessonsHashMap.put(lessonID, new Lesson(lessonID, 1, "Sara", "Wed, Apr 03 2024 04:00 pm"));
        lessonID = Manager.generateLessonID();
        Manager.lessonsHashMap.put(lessonID, new Lesson(lessonID, 3, "James", "Wed, Apr 03 2024 05:00 pm"));
        lessonID = Manager.generateLessonID();
        Manager.lessonsHashMap.put(lessonID, new Lesson(lessonID, 5, "Ela", "Wed, Apr 03 2024 06:00 pm"));
        lessonID = Manager.generateLessonID();
        Manager.lessonsHashMap.put(lessonID, new Lesson(lessonID, 3, "Dave", "Fri, Apr 05 2024 04:00 pm"));
        lessonID = Manager.generateLessonID();
        Manager.lessonsHashMap.put(lessonID, new Lesson(lessonID, 1, "Sara", "Fri, Apr 05 2024 05:00 pm"));
        lessonID = Manager.generateLessonID();
        Manager.lessonsHashMap.put(lessonID, new Lesson(lessonID, 4, "James", "Fri, Apr 05 2024 06:00 pm"));
        lessonID = Manager.generateLessonID();
        Manager.lessonsHashMap.put(lessonID, new Lesson(lessonID, 2, "Ela", "Sat, Apr 06 2024 02:00 pm"));
        lessonID = Manager.generateLessonID();
        Manager.lessonsHashMap.put(lessonID, new Lesson(lessonID, 4, "Dave", "Sat, Apr 06 2024 03:00 pm"));

        //second week
        lessonID = Manager.generateLessonID();
        Manager.lessonsHashMap.put(lessonID, new Lesson(lessonID, 2, "James", "Mon, Apr 08 2024 04:00 pm"));
        lessonID = Manager.generateLessonID();
        Manager.lessonsHashMap.put(lessonID, new Lesson(lessonID, 4, "Ela", "Mon, Apr 08 2024 05:00 pm"));
        lessonID = Manager.generateLessonID();
        Manager.lessonsHashMap.put(lessonID, new Lesson(lessonID, 5, "Dave", "Mon, Apr 08 2024 06:00 pm"));
        lessonID = Manager.generateLessonID();
        Manager.lessonsHashMap.put(lessonID, new Lesson(lessonID, 1, "Sara", "Wed, Apr 10 2024 04:00 pm"));
        lessonID = Manager.generateLessonID();
        Manager.lessonsHashMap.put(lessonID, new Lesson(lessonID, 3, "James", "Wed, Apr 10 2024 05:00 pm"));
        lessonID = Manager.generateLessonID();
        Manager.lessonsHashMap.put(lessonID, new Lesson(lessonID, 5, "Ela", "Wed, Apr 10 2024 06:00 pm"));
        lessonID = Manager.generateLessonID();
        Manager.lessonsHashMap.put(lessonID, new Lesson(lessonID, 3, "Dave", "Fri, Apr 12 2024 04:00 pm"));
        lessonID = Manager.generateLessonID();
        Manager.lessonsHashMap.put(lessonID, new Lesson(lessonID, 1, "Sara", "Fri, Apr 12 2024 05:00 pm"));
        lessonID = Manager.generateLessonID();
        Manager.lessonsHashMap.put(lessonID, new Lesson(lessonID, 4, "James", "Fri, Apr 12 2024 06:00 pm"));
        lessonID = Manager.generateLessonID();
        Manager.lessonsHashMap.put(lessonID, new Lesson(lessonID, 2, "Ela", "Sat, Apr 13 2024 02:00 pm"));
        lessonID = Manager.generateLessonID();
        Manager.lessonsHashMap.put(lessonID, new Lesson(lessonID, 4, "Dave", "Sat, Apr 13 2024 03:00 pm"));

        //third week
        lessonID = Manager.generateLessonID();
        Manager.lessonsHashMap.put(lessonID, new Lesson(lessonID, 2, "James", "Mon, Apr 15 2024 04:00 pm"));
        lessonID = Manager.generateLessonID();
        Manager.lessonsHashMap.put(lessonID, new Lesson(lessonID, 4, "Ela", "Mon, Apr 15 2024 05:00 pm"));
        lessonID = Manager.generateLessonID();
        Manager.lessonsHashMap.put(lessonID, new Lesson(lessonID, 5, "Dave", "Mon, Apr 15 2024 06:00 pm"));
        lessonID = Manager.generateLessonID();
        Manager.lessonsHashMap.put(lessonID, new Lesson(lessonID, 1, "Sara", "Wed, Apr 17 2024 04:00 pm"));
        lessonID = Manager.generateLessonID();
        Manager.lessonsHashMap.put(lessonID, new Lesson(lessonID, 3, "James", "Wed, Apr 17 2024 05:00 pm"));
        lessonID = Manager.generateLessonID();
        Manager.lessonsHashMap.put(lessonID, new Lesson(lessonID, 5, "Ela", "Wed, Apr 17 2024 06:00 pm"));
        lessonID = Manager.generateLessonID();
        Manager.lessonsHashMap.put(lessonID, new Lesson(lessonID, 3, "Dave", "Fri, Apr 19 2024 04:00 pm"));
        lessonID = Manager.generateLessonID();
        Manager.lessonsHashMap.put(lessonID, new Lesson(lessonID, 1, "Sara", "Fri, Apr 19 2024 05:00 pm"));
        lessonID = Manager.generateLessonID();
        Manager.lessonsHashMap.put(lessonID, new Lesson(lessonID, 4, "James", "Fri, Apr 19 2024 06:00 pm"));
        lessonID = Manager.generateLessonID();
        Manager.lessonsHashMap.put(lessonID, new Lesson(lessonID, 2, "Ela", "Sat, Apr 20 2024 02:00 pm"));
        lessonID = Manager.generateLessonID();
        Manager.lessonsHashMap.put(lessonID, new Lesson(lessonID, 4, "Dave", "Sat, Apr 20 2024 03:00 pm"));

        //fourth week
        lessonID = Manager.generateLessonID();
        Manager.lessonsHashMap.put(lessonID, new Lesson(lessonID, 2, "James", "Mon, Apr 22 2024 04:00 pm"));
        lessonID = Manager.generateLessonID();
        Manager.lessonsHashMap.put(lessonID, new Lesson(lessonID, 4, "Ela", "Mon, Apr 22 2024 05:00 pm"));
        lessonID = Manager.generateLessonID();
        Manager.lessonsHashMap.put(lessonID, new Lesson(lessonID, 5, "Dave", "Mon, Apr 22 2024 06:00 pm"));
        lessonID = Manager.generateLessonID();
        Manager.lessonsHashMap.put(lessonID, new Lesson(lessonID, 1, "Sara", "Wed, Apr 24 2024 04:00 pm"));
        lessonID = Manager.generateLessonID();
        Manager.lessonsHashMap.put(lessonID, new Lesson(lessonID, 3, "James", "Wed, Apr 24 2024 05:00 pm"));
        lessonID = Manager.generateLessonID();
        Manager.lessonsHashMap.put(lessonID, new Lesson(lessonID, 5, "Ela", "Wed, Apr 24 2024 06:00 pm"));
        lessonID = Manager.generateLessonID();
        Manager.lessonsHashMap.put(lessonID, new Lesson(lessonID, 3, "Dave", "Fri, Apr 26 2024 04:00 pm"));
        lessonID = Manager.generateLessonID();
        Manager.lessonsHashMap.put(lessonID, new Lesson(lessonID, 1, "Sara", "Fri, Apr 26 2024 05:00 pm"));
        lessonID = Manager.generateLessonID();
        Manager.lessonsHashMap.put(lessonID, new Lesson(lessonID, 4, "James", "Fri, Apr 26 2024 06:00 pm"));
        lessonID = Manager.generateLessonID();
        Manager.lessonsHashMap.put(lessonID, new Lesson(lessonID, 2, "Ela", "Sat, Apr 27 2024 02:00 pm"));
        lessonID = Manager.generateLessonID();
        Manager.lessonsHashMap.put(lessonID, new Lesson(lessonID, 4, "Dave", "Sat, Apr 27 2024 03:00 pm"));

        //fifth week
        lessonID = Manager.generateLessonID();
        Manager.lessonsHashMap.put(lessonID, new Lesson(lessonID, 2, "James", "Mon, Apr 29 2024 04:00 pm"));
        lessonID = Manager.generateLessonID();
        Manager.lessonsHashMap.put(lessonID, new Lesson(lessonID, 4, "Ela", "Mon, Apr 29 2024 05:00 pm"));
        lessonID = Manager.generateLessonID();
        Manager.lessonsHashMap.put(lessonID, new Lesson(lessonID, 5, "Dave", "Mon, Apr 29 2024 06:00 pm"));
    }
}
