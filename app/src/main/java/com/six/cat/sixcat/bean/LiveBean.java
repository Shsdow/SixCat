package com.six.cat.sixcat.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * @author liguoying
 * @date 2018/1/12.
 */

public class LiveBean {

    /**
     * count : 1
     * start : 0
     * total : 30
     * subjects : [{"rating":{"max":10,"average":7.4,"details":{"1":8,"3":454,"2":60,"5":192,"4":628},"stars":"40","min":0},"genres":["动作","奇幻","冒险"],"title":"勇敢者游戏：决战丛林","casts":[{"avatars":{"small":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p196.jpg","large":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p196.jpg","medium":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p196.jpg"},"name_en":"Dwayne Johnson","name":"道恩·强森","alt":"https://movie.douban.com/celebrity/1044707/","id":"1044707"},{"avatars":{"small":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p56350.jpg","large":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p56350.jpg","medium":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p56350.jpg"},"name_en":"Kevin Hart","name":"凯文·哈特","alt":"https://movie.douban.com/celebrity/1286162/","id":"1286162"},{"avatars":{"small":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p35722.jpg","large":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p35722.jpg","medium":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p35722.jpg"},"name_en":"Jack Black","name":"杰克·布莱克","alt":"https://movie.douban.com/celebrity/1049492/","id":"1049492"}],"durations":["119分钟"],"collect_count":3726,"mainland_pubdate":"2018-01-12","has_video":false,"original_title":"Jumanji: Welcome to the Jungle","subtype":"movie","directors":[{"avatars":{"small":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1515078777.46.jpg","large":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1515078777.46.jpg","medium":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1515078777.46.jpg"},"name_en":"Jake Kasdan","name":"杰克·卡斯丹","alt":"https://movie.douban.com/celebrity/1040862/","id":"1040862"}],"pubdates":["2017-12-20(美国)","2018-01-12(中国大陆)"],"year":"2017","images":{"small":"https://img3.doubanio.com/view/photo/s_ratio_poster/public/p2506258944.jpg","large":"https://img3.doubanio.com/view/photo/s_ratio_poster/public/p2506258944.jpg","medium":"https://img3.doubanio.com/view/photo/s_ratio_poster/public/p2506258944.jpg"},"alt":"https://movie.douban.com/subject/26586766/","id":"26586766"}]
     * title : 正在上映的电影-北京
     */

    private int count;
    private int start;
    private int total;
    private String title;
    private List<SubjectsBean> subjects;

    @Override
    public String toString() {
        return "LiveBean{" +
                "count=" + count +
                ", start=" + start +
                ", total=" + total +
                ", title='" + title + '\'' +
                ", subjects=" + subjects +
                '}';
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<SubjectsBean> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<SubjectsBean> subjects) {
        this.subjects = subjects;
    }

    public static class SubjectsBean {
        /**
         * rating : {"max":10,"average":7.4,"details":{"1":8,"3":454,"2":60,"5":192,"4":628},"stars":"40","min":0}
         * genres : ["动作","奇幻","冒险"]
         * title : 勇敢者游戏：决战丛林
         * casts : [{"avatars":{"small":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p196.jpg","large":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p196.jpg","medium":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p196.jpg"},"name_en":"Dwayne Johnson","name":"道恩·强森","alt":"https://movie.douban.com/celebrity/1044707/","id":"1044707"},{"avatars":{"small":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p56350.jpg","large":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p56350.jpg","medium":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p56350.jpg"},"name_en":"Kevin Hart","name":"凯文·哈特","alt":"https://movie.douban.com/celebrity/1286162/","id":"1286162"},{"avatars":{"small":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p35722.jpg","large":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p35722.jpg","medium":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p35722.jpg"},"name_en":"Jack Black","name":"杰克·布莱克","alt":"https://movie.douban.com/celebrity/1049492/","id":"1049492"}]
         * durations : ["119分钟"]
         * collect_count : 3726
         * mainland_pubdate : 2018-01-12
         * has_video : false
         * original_title : Jumanji: Welcome to the Jungle
         * subtype : movie
         * directors : [{"avatars":{"small":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1515078777.46.jpg","large":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1515078777.46.jpg","medium":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1515078777.46.jpg"},"name_en":"Jake Kasdan","name":"杰克·卡斯丹","alt":"https://movie.douban.com/celebrity/1040862/","id":"1040862"}]
         * pubdates : ["2017-12-20(美国)","2018-01-12(中国大陆)"]
         * year : 2017
         * images : {"small":"https://img3.doubanio.com/view/photo/s_ratio_poster/public/p2506258944.jpg","large":"https://img3.doubanio.com/view/photo/s_ratio_poster/public/p2506258944.jpg","medium":"https://img3.doubanio.com/view/photo/s_ratio_poster/public/p2506258944.jpg"}
         * alt : https://movie.douban.com/subject/26586766/
         * id : 26586766
         */

        private RatingBean rating;
        private String title;
        private int collect_count;
        private String mainland_pubdate;
        private boolean has_video;
        private String original_title;
        private String subtype;
        private String year;
        private ImagesBean images;
        private String alt;
        private String id;
        private List<String> genres;
        private List<CastsBean> casts;
        private List<String> durations;
        private List<DirectorsBean> directors;
        private List<String> pubdates;

        public RatingBean getRating() {
            return rating;
        }

        public void setRating(RatingBean rating) {
            this.rating = rating;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getCollect_count() {
            return collect_count;
        }

        public void setCollect_count(int collect_count) {
            this.collect_count = collect_count;
        }

        public String getMainland_pubdate() {
            return mainland_pubdate;
        }

        public void setMainland_pubdate(String mainland_pubdate) {
            this.mainland_pubdate = mainland_pubdate;
        }

        public boolean isHas_video() {
            return has_video;
        }

        public void setHas_video(boolean has_video) {
            this.has_video = has_video;
        }

        public String getOriginal_title() {
            return original_title;
        }

        public void setOriginal_title(String original_title) {
            this.original_title = original_title;
        }

        public String getSubtype() {
            return subtype;
        }

        public void setSubtype(String subtype) {
            this.subtype = subtype;
        }

        public String getYear() {
            return year;
        }

        public void setYear(String year) {
            this.year = year;
        }

        public ImagesBean getImages() {
            return images;
        }

        public void setImages(ImagesBean images) {
            this.images = images;
        }

        public String getAlt() {
            return alt;
        }

        public void setAlt(String alt) {
            this.alt = alt;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public List<String> getGenres() {
            return genres;
        }

        public void setGenres(List<String> genres) {
            this.genres = genres;
        }

        public List<CastsBean> getCasts() {
            return casts;
        }

        public void setCasts(List<CastsBean> casts) {
            this.casts = casts;
        }

        public List<String> getDurations() {
            return durations;
        }

        public void setDurations(List<String> durations) {
            this.durations = durations;
        }

        public List<DirectorsBean> getDirectors() {
            return directors;
        }

        public void setDirectors(List<DirectorsBean> directors) {
            this.directors = directors;
        }

        public List<String> getPubdates() {
            return pubdates;
        }

        public void setPubdates(List<String> pubdates) {
            this.pubdates = pubdates;
        }

        public static class RatingBean {
            /**
             * max : 10
             * average : 7.4
             * details : {"1":8,"3":454,"2":60,"5":192,"4":628}
             * stars : 40
             * min : 0
             */

            private int max;
            private double average;
            private DetailsBean details;
            private String stars;
            private int min;

            public int getMax() {
                return max;
            }

            public void setMax(int max) {
                this.max = max;
            }

            public double getAverage() {
                return average;
            }

            public void setAverage(double average) {
                this.average = average;
            }

            public DetailsBean getDetails() {
                return details;
            }

            public void setDetails(DetailsBean details) {
                this.details = details;
            }

            public String getStars() {
                return stars;
            }

            public void setStars(String stars) {
                this.stars = stars;
            }

            public int getMin() {
                return min;
            }

            public void setMin(int min) {
                this.min = min;
            }

            public static class DetailsBean {
                /**
                 * 1 : 8
                 * 3 : 454
                 * 2 : 60
                 * 5 : 192
                 * 4 : 628
                 */

                @SerializedName("1")
                private int _$1;
                @SerializedName("3")
                private int _$3;
                @SerializedName("2")
                private int _$2;
                @SerializedName("5")
                private int _$5;
                @SerializedName("4")
                private int _$4;

                public int get_$1() {
                    return _$1;
                }

                public void set_$1(int _$1) {
                    this._$1 = _$1;
                }

                public int get_$3() {
                    return _$3;
                }

                public void set_$3(int _$3) {
                    this._$3 = _$3;
                }

                public int get_$2() {
                    return _$2;
                }

                public void set_$2(int _$2) {
                    this._$2 = _$2;
                }

                public int get_$5() {
                    return _$5;
                }

                public void set_$5(int _$5) {
                    this._$5 = _$5;
                }

                public int get_$4() {
                    return _$4;
                }

                public void set_$4(int _$4) {
                    this._$4 = _$4;
                }
            }
        }

        public static class ImagesBean {
            /**
             * small : https://img3.doubanio.com/view/photo/s_ratio_poster/public/p2506258944.jpg
             * large : https://img3.doubanio.com/view/photo/s_ratio_poster/public/p2506258944.jpg
             * medium : https://img3.doubanio.com/view/photo/s_ratio_poster/public/p2506258944.jpg
             */

            private String small;
            private String large;
            private String medium;

            public String getSmall() {
                return small;
            }

            public void setSmall(String small) {
                this.small = small;
            }

            public String getLarge() {
                return large;
            }

            public void setLarge(String large) {
                this.large = large;
            }

            public String getMedium() {
                return medium;
            }

            public void setMedium(String medium) {
                this.medium = medium;
            }
        }

        public static class CastsBean {
            /**
             * avatars : {"small":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p196.jpg","large":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p196.jpg","medium":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p196.jpg"}
             * name_en : Dwayne Johnson
             * name : 道恩·强森
             * alt : https://movie.douban.com/celebrity/1044707/
             * id : 1044707
             */

            private AvatarsBean avatars;
            private String name_en;
            private String name;
            private String alt;
            private String id;

            public AvatarsBean getAvatars() {
                return avatars;
            }

            public void setAvatars(AvatarsBean avatars) {
                this.avatars = avatars;
            }

            public String getName_en() {
                return name_en;
            }

            public void setName_en(String name_en) {
                this.name_en = name_en;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getAlt() {
                return alt;
            }

            public void setAlt(String alt) {
                this.alt = alt;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public static class AvatarsBean {
                /**
                 * small : https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p196.jpg
                 * large : https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p196.jpg
                 * medium : https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p196.jpg
                 */

                private String small;
                private String large;
                private String medium;

                public String getSmall() {
                    return small;
                }

                public void setSmall(String small) {
                    this.small = small;
                }

                public String getLarge() {
                    return large;
                }

                public void setLarge(String large) {
                    this.large = large;
                }

                public String getMedium() {
                    return medium;
                }

                public void setMedium(String medium) {
                    this.medium = medium;
                }
            }
        }

        public static class DirectorsBean {
            /**
             * avatars : {"small":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1515078777.46.jpg","large":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1515078777.46.jpg","medium":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1515078777.46.jpg"}
             * name_en : Jake Kasdan
             * name : 杰克·卡斯丹
             * alt : https://movie.douban.com/celebrity/1040862/
             * id : 1040862
             */

            private AvatarsBeanX avatars;
            private String name_en;
            private String name;
            private String alt;
            private String id;

            public AvatarsBeanX getAvatars() {
                return avatars;
            }

            public void setAvatars(AvatarsBeanX avatars) {
                this.avatars = avatars;
            }

            public String getName_en() {
                return name_en;
            }

            public void setName_en(String name_en) {
                this.name_en = name_en;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getAlt() {
                return alt;
            }

            public void setAlt(String alt) {
                this.alt = alt;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public static class AvatarsBeanX {
                /**
                 * small : https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1515078777.46.jpg
                 * large : https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1515078777.46.jpg
                 * medium : https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1515078777.46.jpg
                 */

                private String small;
                private String large;
                private String medium;

                public String getSmall() {
                    return small;
                }

                public void setSmall(String small) {
                    this.small = small;
                }

                public String getLarge() {
                    return large;
                }

                public void setLarge(String large) {
                    this.large = large;
                }

                public String getMedium() {
                    return medium;
                }

                public void setMedium(String medium) {
                    this.medium = medium;
                }
            }
        }
    }
}
