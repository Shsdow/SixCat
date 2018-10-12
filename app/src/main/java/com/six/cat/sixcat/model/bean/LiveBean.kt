package com.six.cat.sixcat.model.bean

import com.google.gson.annotations.SerializedName

/**
 * @author liguoying
 * @date 2018/1/12.
 */

class LiveBean {

    /**
     * count : 1
     * start : 0
     * total : 30
     * subjects : [{"rating":{"max":10,"average":7.4,"details":{"1":8,"3":454,"2":60,"5":192,"4":628},"stars":"40","min":0},"genres":["动作","奇幻","冒险"],"title":"勇敢者游戏：决战丛林","casts":[{"avatars":{"small":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p196.jpg","large":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p196.jpg","medium":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p196.jpg"},"name_en":"Dwayne Johnson","name":"道恩·强森","alt":"https://movie.douban.com/celebrity/1044707/","id":"1044707"},{"avatars":{"small":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p56350.jpg","large":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p56350.jpg","medium":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p56350.jpg"},"name_en":"Kevin Hart","name":"凯文·哈特","alt":"https://movie.douban.com/celebrity/1286162/","id":"1286162"},{"avatars":{"small":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p35722.jpg","large":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p35722.jpg","medium":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p35722.jpg"},"name_en":"Jack Black","name":"杰克·布莱克","alt":"https://movie.douban.com/celebrity/1049492/","id":"1049492"}],"durations":["119分钟"],"collect_count":3726,"mainland_pubdate":"2018-01-12","has_video":false,"original_title":"Jumanji: Welcome to the Jungle","subtype":"movie","directors":[{"avatars":{"small":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1515078777.46.jpg","large":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1515078777.46.jpg","medium":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1515078777.46.jpg"},"name_en":"Jake Kasdan","name":"杰克·卡斯丹","alt":"https://movie.douban.com/celebrity/1040862/","id":"1040862"}],"pubdates":["2017-12-20(美国)","2018-01-12(中国大陆)"],"year":"2017","images":{"small":"https://img3.doubanio.com/view/photo/s_ratio_poster/public/p2506258944.jpg","large":"https://img3.doubanio.com/view/photo/s_ratio_poster/public/p2506258944.jpg","medium":"https://img3.doubanio.com/view/photo/s_ratio_poster/public/p2506258944.jpg"},"alt":"https://movie.douban.com/subject/26586766/","id":"26586766"}]
     * title : 正在上映的电影-北京
     */

    var count: Int = 0
    var start: Int = 0
    var total: Int = 0
    var title: String? = null
    var subjects: List<SubjectsBean>? = null

    override fun toString(): String {
        return "LiveBean{" +
                "count=" + count +
                ", start=" + start +
                ", total=" + total +
                ", title='" + title + '\''.toString() +
                ", subjects=" + subjects +
                '}'.toString()
    }

    open class SubjectsBean {
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

        var rating: RatingBean? = null
        var title: String? = null
        var collect_count: Int = 0
        var mainland_pubdate: String? = null
        var isHas_video: Boolean = false
        var original_title: String? = null
        var subtype: String? = null
        var year: String? = null
        var images: ImagesBean? = null
        var alt: String? = null
        var id: String? = null
        var genres: List<String>? = null
        var casts: List<CastsBean>? = null
        var durations: List<String>? = null
        var directors: List<DirectorsBean>? = null
        var pubdates: List<String>? = null

        override fun toString(): String {
            return "SubjectsBean{" +
                    "rating=" + rating +
                    ", title='" + title + '\''.toString() +
                    ", collect_count=" + collect_count +
                    ", mainland_pubdate='" + mainland_pubdate + '\''.toString() +
                    ", has_video=" + isHas_video +
                    ", original_title='" + original_title + '\''.toString() +
                    ", subtype='" + subtype + '\''.toString() +
                    ", year='" + year + '\''.toString() +
                    ", images=" + images +
                    ", alt='" + alt + '\''.toString() +
                    ", id='" + id + '\''.toString() +
                    ", genres=" + genres +
                    ", casts=" + casts +
                    ", durations=" + durations +
                    ", directors=" + directors +
                    ", pubdates=" + pubdates +
                    '}'.toString()
        }

        class RatingBean {
            /**
             * max : 10
             * average : 7.4
             * details : {"1":8,"3":454,"2":60,"5":192,"4":628}
             * stars : 40
             * min : 0
             */

            var max: Int = 0
            var average: Double = 0.toDouble()
            var details: DetailsBean? = null
            var stars: String? = null
            var min: Int = 0

            class DetailsBean {
                /**
                 * 1 : 8
                 * 3 : 454
                 * 2 : 60
                 * 5 : 192
                 * 4 : 628
                 */

                @SerializedName("1")
                var `_$1`: Int = 0
                @SerializedName("3")
                var `_$3`: Int = 0
                @SerializedName("2")
                var `_$2`: Int = 0
                @SerializedName("5")
                var `_$5`: Int = 0
                @SerializedName("4")
                var `_$4`: Int = 0
            }
        }

        class ImagesBean {
            /**
             * small : https://img3.doubanio.com/view/photo/s_ratio_poster/public/p2506258944.jpg
             * large : https://img3.doubanio.com/view/photo/s_ratio_poster/public/p2506258944.jpg
             * medium : https://img3.doubanio.com/view/photo/s_ratio_poster/public/p2506258944.jpg
             */

            var small: String? = null
            var large: String? = null
            var medium: String? = null
        }

        class CastsBean {
            /**
             * avatars : {"small":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p196.jpg","large":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p196.jpg","medium":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p196.jpg"}
             * name_en : Dwayne Johnson
             * name : 道恩·强森
             * alt : https://movie.douban.com/celebrity/1044707/
             * id : 1044707
             */

            var avatars: AvatarsBean? = null
            var name_en: String? = null
            var name: String? = null
            var alt: String? = null
            var id: String? = null

            class AvatarsBean {
                /**
                 * small : https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p196.jpg
                 * large : https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p196.jpg
                 * medium : https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p196.jpg
                 */

                var small: String? = null
                var large: String? = null
                var medium: String? = null
            }
        }

        class DirectorsBean {
            /**
             * avatars : {"small":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1515078777.46.jpg","large":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1515078777.46.jpg","medium":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1515078777.46.jpg"}
             * name_en : Jake Kasdan
             * name : 杰克·卡斯丹
             * alt : https://movie.douban.com/celebrity/1040862/
             * id : 1040862
             */

            var avatars: AvatarsBeanX? = null
            var name_en: String? = null
            var name: String? = null
            var alt: String? = null
            var id: String? = null

            class AvatarsBeanX {
                /**
                 * small : https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1515078777.46.jpg
                 * large : https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1515078777.46.jpg
                 * medium : https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1515078777.46.jpg
                 */

                var small: String? = null
                var large: String? = null
                var medium: String? = null
            }
        }
    }
}
