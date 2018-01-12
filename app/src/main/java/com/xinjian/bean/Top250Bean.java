package com.xinjian.bean;

import java.util.List;

/**
 * Created by lxj on 2018/1/4 0004.
 */

public class Top250Bean {
    public int count;
    public int start;
    public int total;
    public List<Subject> subjects;

    public class Subject {
        public Rating rating;
        public String[] genres;
        public String title;
        public List<Cast> casts;
        public long collect_count;
        public String original_title;
        public String subtype;
        public List<Director> directors;
        public String year;
        public Image images;
        public String alt;
        public String id;

        public class Rating {
            public int max;
            public double average;
            public String stars;
            public int min;
        }

        public class Cast {
            public String alt;
            public Avatars avatars;
            public String name;
            public String id;

            public class Avatars {
                public String small;
                public String large;
                public String medium;
            }
        }

        public class Director {
            public String alt;
            public Cast.Avatars avatars;
            public String name;
            public String id;
        }

        public class Image {
            public String small;
            public String large;
            public String medium;
        }
    }
}
