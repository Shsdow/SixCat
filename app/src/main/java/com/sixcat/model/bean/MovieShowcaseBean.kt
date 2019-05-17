package com.sixcat.model.bean

import com.google.gson.annotations.SerializedName

/**
 * @author liguoying
 * @date 2018/1/27.
 */
class MovieShowcaseBean{
    /**
     * rating : {"max":10,"average":2.4,"details":{"1":70,"3":3,"2":5,"5":1,"4":0},"stars":"15","min":0}
     * reviews_count : 11
     * videos : [{"source":{"literal":"qq","pic":"http://img7.doubanio.com/f/movie/38764466321ab88dfa19a1f826570367a19ce116/pics/movie/video-qq.png","name":"腾讯视频"},"sample_link":"http://v.qq.com/x/cover/xzvr5axh7r6u524.html?ptag=douban.movie","video_id":"xzvr5axh7r6u524","need_pay":false},{"source":{"literal":"iqiyi","pic":"http://img3.doubanio.com/f/movie/e59bf8556426206eab3591f88a6c8ef8bf371a56/pics/movie/video_icon_new.png","name":"爱奇艺视频"},"sample_link":"http://www.iqiyi.com/v_19rr7csgsk.html?vfm=m_331_dbdy","video_id":"19rr7csgsk","need_pay":false},{"source":{"literal":"youku","pic":"http://img3.doubanio.com/f/movie/3bb15010bb66a89962a5faf0f7f071fec8aaa763/pics/movie/logo_youku_small.png","name":"优酷视频"},"sample_link":"http://v.youku.com/v_show/id_XMjc4ODU0NzA4MA==.html?tpa=dW5pb25faWQ9MTAzNTY1XzEwMDAwMV8wMV8wMQ","video_id":"XMjc4ODU0NzA4MA==","need_pay":false}]
     * wish_count : 235
     * original_title : 恐怖理发店
     * blooper_urls : []
     * collect_count : 642
     * images : {"small":"http://img7.doubanio.com/view/photo/s_ratio_poster/public/p2406903891.jpg","large":"http://img7.doubanio.com/view/photo/s_ratio_poster/public/p2406903891.jpg","medium":"http://img7.doubanio.com/view/photo/s_ratio_poster/public/p2406903891.jpg"}
     * douban_site :
     * year : 2017
     * popular_comments : [{"rating":{"max":5,"value":1,"min":0},"useful_count":3,"author":{"uid":"1091411","avatar":"http://img3.doubanio.com/icon/u1091411-8.jpg","signature":"Hello WANKER","alt":"http://www.douban.com/people/1091411/","id":"1091411","name":"金福珠！"},"subject_id":"26865690","content":"89分钟\u2026\u2026毫无内涵","created_at":"2017-01-23 15:33:10","id":"1140974290"},{"rating":{"max":5,"value":1,"min":0},"useful_count":0,"author":{"uid":"41782473","avatar":"http://img7.doubanio.com/icon/u41782473-43.jpg","signature":"我爱电影 电影爱我～","alt":"http://www.douban.com/people/41782473/","id":"41782473","name":"Summer 璇"},"subject_id":"26865690","content":"忙别的爱奇艺自动播下一个电影播的，还好我没怎么看","created_at":"2017-10-20 15:46:26","id":"1259468187"},{"rating":{"max":5,"value":1,"min":0},"useful_count":3,"author":{"uid":"140868745","avatar":"http://img7.doubanio.com/icon/u140868745-1.jpg","signature":"","alt":"http://www.douban.com/people/140868745/","id":"140868745","name":"RoccoW"},"subject_id":"26865690","content":"太烂了。。见所未见。。。","created_at":"2017-01-13 14:55:18","id":"1136392540"},{"rating":{"max":5,"value":0,"min":0},"useful_count":4,"author":{"uid":"144871552","avatar":"http://img7.doubanio.com/icon/u144871552-3.jpg","signature":"","alt":"http://www.douban.com/people/144871552/","id":"144871552","name":"初次珑"},"subject_id":"26865690","content":"可以不给分吗 熬了个通宵 就他妈看这鬼玩应","created_at":"2017-01-14 12:05:56","id":"1136757537"}]
     * alt : https://movie.douban.com/subject/26865690/
     * id : 26865690
     * mobile_url : https://movie.douban.com/subject/26865690/mobile
     * photos_count : 27
     * pubdate : 2017-01-06
     * title : 恐怖理发店
     * do_count : null
     * has_video : true
     * share_url : http://m.douban.com/movie/subject/26865690
     * seasons_count : null
     * languages : ["汉语普通话"]
     * schedule_url :
     * writers : [{"avatars":{"small":"http://img3.doubanio.com/f/movie/ca527386eb8c4e325611e22dfcb04cc116d6b423/pics/movie/celebrity-default-small.png","large":"http://img7.doubanio.com/f/movie/63acc16ca6309ef191f0378faf793d1096a3e606/pics/movie/celebrity-default-large.png","medium":"http://img3.doubanio.com/f/movie/8dd0c794499fe925ae2ae89ee30cd225750457b4/pics/movie/celebrity-default-medium.png"},"name_en":"","name":"纪然","alt":"https://movie.douban.com/celebrity/1366595/","id":"1366595"},{"avatars":{"small":"http://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1490348628.29.jpg","large":"http://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1490348628.29.jpg","medium":"http://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1490348628.29.jpg"},"name_en":"Shilei Lu","name":"陆诗雷","alt":"https://movie.douban.com/celebrity/1360707/","id":"1360707"}]
     * pubdates : ["2017-01-06(中国大陆)"]
     * website :
     * tags : ["惊悚","烂片","一个星都不想给！","烂片之中的烂片啊~","垃圾","呵呵","中国","狗屎","真的好恐怖啊！","烂透了"]
     * has_schedule : false
     * durations : ["89分钟"]
     * genres : ["爱情","悬疑","惊悚"]
     * collection : null
     * trailers : [{"medium":"http://img3.doubanio.com/img/trailer/medium/2395934439.jpg?","title":"预告片：正式版 (中文字幕)","subject_id":"26865690","alt":"https://movie.douban.com/trailer/206905/","small":"http://img3.doubanio.com/img/trailer/small/2395934439.jpg?","resource_url":"http://vt1.doubanio.com/201801271414/f7ea9b57d6159090adc1a4ba512d6e4f/view/movie/M/302060905.mp4","id":"206905"},{"medium":"http://img3.doubanio.com/img/trailer/medium/2408079427.jpg?","title":"预告片：终极版 (中文字幕)","subject_id":"26865690","alt":"https://movie.douban.com/trailer/209536/","small":"http://img3.doubanio.com/img/trailer/small/2408079427.jpg?","resource_url":"http://vt1.doubanio.com/201801271414/1b581050a2ba933d7bd10e7d1e067cd5/view/movie/M/302090536.mp4","id":"209536"},{"medium":"http://img7.doubanio.com/img/trailer/medium/2406384532.jpg?","title":"预告片：激情版 (中文字幕)","subject_id":"26865690","alt":"https://movie.douban.com/trailer/209076/","small":"http://img7.doubanio.com/img/trailer/small/2406384532.jpg?","resource_url":"http://vt1.doubanio.com/201801271414/b2a63dcad333f2ec9a1d623819b681ce/view/movie/M/302090076.mp4","id":"209076"}]
     * episodes_count : null
     * trailer_urls : ["http://vt1.doubanio.com/201801271414/f7ea9b57d6159090adc1a4ba512d6e4f/view/movie/M/302060905.mp4","http://vt1.doubanio.com/201801271414/1b581050a2ba933d7bd10e7d1e067cd5/view/movie/M/302090536.mp4","http://vt1.doubanio.com/201801271414/b2a63dcad333f2ec9a1d623819b681ce/view/movie/M/302090076.mp4"]
     * has_ticket : false
     * bloopers : []
     * clip_urls : []
     * current_season : null
     * casts : [{"avatars":{"small":"http://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1403756298.69.jpg","large":"http://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1403756298.69.jpg","medium":"http://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1403756298.69.jpg"},"name_en":"Guoer Yin","name":"殷果儿","alt":"https://movie.douban.com/celebrity/1340984/","id":"1340984"},{"avatars":{"small":"http://img3.doubanio.com/f/movie/ca527386eb8c4e325611e22dfcb04cc116d6b423/pics/movie/celebrity-default-small.png","large":"http://img7.doubanio.com/f/movie/63acc16ca6309ef191f0378faf793d1096a3e606/pics/movie/celebrity-default-large.png","medium":"http://img3.doubanio.com/f/movie/8dd0c794499fe925ae2ae89ee30cd225750457b4/pics/movie/celebrity-default-medium.png"},"name_en":"Qingan Ren","name":"任青安","alt":"https://movie.douban.com/celebrity/1359164/","id":"1359164"},{"avatars":{"small":"http://img7.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1451209491.55.jpg","large":"http://img7.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1451209491.55.jpg","medium":"http://img7.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1451209491.55.jpg"},"name_en":"SungGoo Kang","name":"姜星丘","alt":"https://movie.douban.com/celebrity/1353667/","id":"1353667"},{"avatars":{"small":"http://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1478601324.49.jpg","large":"http://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1478601324.49.jpg","medium":"http://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1478601324.49.jpg"},"name_en":"Jiamin Chen","name":"陈嘉敏","alt":"https://movie.douban.com/celebrity/1340988/","id":"1340988"}]
     * countries : ["中国大陆"]
     * mainland_pubdate : 2017-01-06
     * photos : [{"thumb":"http://img7.doubanio.com/view/photo/thumb/public/p2411789693.jpg","image":"http://img7.doubanio.com/view/photo/photo/public/p2411789693.jpg","cover":"http://img7.doubanio.com/view/photo/albumcover/public/p2411789693.jpg","alt":"https://movie.douban.com/photos/photo/2411789693/","id":"2411789693","icon":"http://img7.doubanio.com/view/photo/icon/public/p2411789693.jpg"},{"thumb":"http://img7.doubanio.com/view/photo/thumb/public/p2406383762.jpg","image":"http://img7.doubanio.com/view/photo/photo/public/p2406383762.jpg","cover":"http://img7.doubanio.com/view/photo/albumcover/public/p2406383762.jpg","alt":"https://movie.douban.com/photos/photo/2406383762/","id":"2406383762","icon":"http://img7.doubanio.com/view/photo/icon/public/p2406383762.jpg"},{"thumb":"http://img3.doubanio.com/view/photo/thumb/public/p2411789707.jpg","image":"http://img3.doubanio.com/view/photo/photo/public/p2411789707.jpg","cover":"http://img3.doubanio.com/view/photo/albumcover/public/p2411789707.jpg","alt":"https://movie.douban.com/photos/photo/2411789707/","id":"2411789707","icon":"http://img3.doubanio.com/view/photo/icon/public/p2411789707.jpg"},{"thumb":"http://img7.doubanio.com/view/photo/thumb/public/p2411789702.jpg","image":"http://img7.doubanio.com/view/photo/photo/public/p2411789702.jpg","cover":"http://img7.doubanio.com/view/photo/albumcover/public/p2411789702.jpg","alt":"https://movie.douban.com/photos/photo/2411789702/","id":"2411789702","icon":"http://img7.doubanio.com/view/photo/icon/public/p2411789702.jpg"},{"thumb":"http://img7.doubanio.com/view/photo/thumb/public/p2408074732.jpg","image":"http://img7.doubanio.com/view/photo/photo/public/p2408074732.jpg","cover":"http://img7.doubanio.com/view/photo/albumcover/public/p2408074732.jpg","alt":"https://movie.douban.com/photos/photo/2408074732/","id":"2408074732","icon":"http://img7.doubanio.com/view/photo/icon/public/p2408074732.jpg"},{"thumb":"http://img7.doubanio.com/view/photo/thumb/public/p2408074723.jpg","image":"http://img7.doubanio.com/view/photo/photo/public/p2408074723.jpg","cover":"http://img7.doubanio.com/view/photo/albumcover/public/p2408074723.jpg","alt":"https://movie.douban.com/photos/photo/2408074723/","id":"2408074723","icon":"http://img7.doubanio.com/view/photo/icon/public/p2408074723.jpg"},{"thumb":"http://img7.doubanio.com/view/photo/thumb/public/p2408074715.jpg","image":"http://img7.doubanio.com/view/photo/photo/public/p2408074715.jpg","cover":"http://img7.doubanio.com/view/photo/albumcover/public/p2408074715.jpg","alt":"https://movie.douban.com/photos/photo/2408074715/","id":"2408074715","icon":"http://img7.doubanio.com/view/photo/icon/public/p2408074715.jpg"},{"thumb":"http://img7.doubanio.com/view/photo/thumb/public/p2406383761.jpg","image":"http://img7.doubanio.com/view/photo/photo/public/p2406383761.jpg","cover":"http://img7.doubanio.com/view/photo/albumcover/public/p2406383761.jpg","alt":"https://movie.douban.com/photos/photo/2406383761/","id":"2406383761","icon":"http://img7.doubanio.com/view/photo/icon/public/p2406383761.jpg"},{"thumb":"http://img3.doubanio.com/view/photo/thumb/public/p2406383759.jpg","image":"http://img3.doubanio.com/view/photo/photo/public/p2406383759.jpg","cover":"http://img3.doubanio.com/view/photo/albumcover/public/p2406383759.jpg","alt":"https://movie.douban.com/photos/photo/2406383759/","id":"2406383759","icon":"http://img3.doubanio.com/view/photo/icon/public/p2406383759.jpg"},{"thumb":"http://img7.doubanio.com/view/photo/thumb/public/p2395927790.jpg","image":"http://img7.doubanio.com/view/photo/photo/public/p2395927790.jpg","cover":"http://img7.doubanio.com/view/photo/albumcover/public/p2395927790.jpg","alt":"https://movie.douban.com/photos/photo/2395927790/","id":"2395927790","icon":"http://img7.doubanio.com/view/photo/icon/public/p2395927790.jpg"}]
     * summary : 位于深山小镇的理发店发生的一系列灵异奇闻，殷果儿、任青安、姜星丘等人陷入危难绝境中无法脱身，和理发店有关联的人物接连被惨绝杀害，血腥残暴引来人心惶惶，而抽丝剥茧之后的真相更加令人心惊胆战。
     * clips : []
     * subtype : movie
     * directors : [{"avatars":{"small":"http://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1490348628.29.jpg","large":"http://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1490348628.29.jpg","medium":"http://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1490348628.29.jpg"},"name_en":"Shilei Lu","name":"陆诗雷","alt":"https://movie.douban.com/celebrity/1360707/","id":"1360707"}]
     * comments_count : 290
     * popular_reviews : [{"rating":{"max":5,"value":1,"min":0},"title":"国产恐怖片，注定成烂片？","subject_id":"26865690","author":{"uid":"123404248","avatar":"http://img7.doubanio.com/icon/u123404248-3.jpg","signature":"","alt":"http://www.douban.com/people/123404248/","id":"123404248","name":"世界奇妙物语"},"summary":"这一系列国产恐怖片太多，现在总结下国产电影拍摄门槛为什么这么低\u2026\u2026 1.找个导演，内地导演优先考虑(省钱)。 2.去网上热搜榜（也是经纪公司）上挑几个网红明星（省钱）。网红明星就像木偶一样被装扮上了。 3.去...","alt":"https://movie.douban.com/review/8301338/","id":"8301338"},{"rating":{"max":5,"value":1,"min":0},"title":"2017年1月14日","subject_id":"26865690","author":{"uid":"106658069","avatar":"http://img7.doubanio.com/icon/u106658069-3.jpg","signature":"","alt":"http://www.douban.com/people/106658069/","id":"106658069","name":"白安"},"summary":"小萌说要去看，从头到尾全是槽点，这剧本无论怎么拍都不会好了\u2026怪不得邓sir对我写的鬼故事如此有信心，因为大家都是这水平吗\u2026 不过老实说，这个编剧犯的错误我也犯过：故事和线索不集中。写《杀人犯》的时候，...","alt":"https://movie.douban.com/review/8823383/","id":"8823383"},{"rating":{"max":5,"value":1,"min":0},"title":"导演别拍电影了，快回家陪你父母，不然小心他们扮鬼吓你！","subject_id":"26865690","author":{"uid":"BIANJU20170418","avatar":"http://img7.doubanio.com/icon/u82851721-3.jpg","signature":"","alt":"http://www.douban.com/people/BIANJU20170418/","id":"82851721","name":"游侠一笑"},"summary":"《恐怖游泳馆》、《恐怖电影院》，恐怖厕所、恐怖你妈隔壁，继\u201c诡\u201d、\u201c惊魂\u201d、\u201c灵\u201d、\u201c怨\u201d后，国产可怕片的片名誓要在\u201c恐怖\u201d路上走到底。  一连看了三部菲尔幕出品的国产恐怖片，这也够恐怖的，还是那句...","alt":"https://movie.douban.com/review/8578229/","id":"8578229"},{"rating":{"max":5,"value":1,"min":0},"title":"差到不行","subject_id":"26865690","author":{"uid":"158559795","avatar":"http://img3.doubanio.com/icon/user_normal.jpg","signature":"","alt":"http://www.douban.com/people/158559795/","id":"158559795","name":"依旧箜絔"},"summary":"真的很烂 很烂 成了喜剧 如果评论涉及电影和小说的结局和关键情节，请勾选「有关键情节透露」，豆瓣将显示提示，以免没有看过的人扫兴。  为了尊重创作者的劳动，请不要转载他人文章或提供下载信息。豆瓣鼓励有益...","alt":"https://movie.douban.com/review/8394178/","id":"8394178"},{"rating":{"max":5,"value":1,"min":0},"title":"老套路没创意","subject_id":"26865690","author":{"uid":"149343489","avatar":"http://img7.doubanio.com/icon/u149343489-1.jpg","signature":"","alt":"http://www.douban.com/people/149343489/","id":"149343489","name":"🗿"},"summary":"烂片 嘈点太多了好吗 前面刚开始有鬼出现 后面大部分都是情感戏 最后结果又是人为扮鬼 很多现象也是无法解释的 电为什么说停就停 为什么里面的人可以轻松找到模特厘米的代号？ 每个人那么容易认出自己的手掌印 ？...","alt":"https://movie.douban.com/review/8278482/","id":"8278482"},{"rating":{"max":5,"value":1,"min":0},"title":"?","subject_id":"26865690","author":{"uid":"154276285","avatar":"http://img7.doubanio.com/icon/u154276285-1.jpg","signature":"","alt":"http://www.douban.com/people/154276285/","id":"154276285","name":"👧"},"summary":"超级烂片，让她爹玩一宿，预告片剪辑不错，此片看完预告片即可，看了多余，漏洞百出，穿帮镜头无数，无厘头到了极致。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。...","alt":"https://movie.douban.com/review/8278145/","id":"8278145"},{"rating":{"max":5,"value":4,"min":0},"title":"《恐怖理发店》：青丝犹在，魂魄已飞","subject_id":"26865690","author":{"uid":"41576647","avatar":"http://img3.doubanio.com/icon/user_normal.jpg","signature":"","alt":"http://www.douban.com/people/41576647/","id":"41576647","name":"丑鱼尼莫"},"summary":"《恐怖理发店》讲述的是一个发生在理发店的灵异事件，而灵异的背后，总有一些说不清道不明的真相在作祟。但是，当真相一点点水落石出的时候，又总会叫人心悸、惊厥，毛骨悚然，不寒而栗的感觉也悄上心头。  荒山...","alt":"https://movie.douban.com/review/8239886/","id":"8239886"},{"rating":{"max":5,"value":4,"min":0},"title":"Word天呀！以后再也不敢去理发店了","subject_id":"26865690","author":{"uid":"70359207","avatar":"http://img3.doubanio.com/icon/u70359207-8.jpg","signature":"百度百家、今日头条作家、影评人","alt":"http://www.douban.com/people/70359207/","id":"70359207","name":"大侃"},"summary":"  惊悚、恐怖类的影片，每周都在影院里现身，不但有固定的消费群体和受众，还时不时灵光一闪在票房上创出佳绩，《恐怖游泳馆》、《床下有人》、《枕边有张脸》等都是其中的代表。当下，观众的欣赏口味不断提升，...","alt":"https://movie.douban.com/review/8239440/","id":"8239440"},{"rating":{"max":5,"value":4,"min":0},"title":"美发洗浴杀人一条龙服务","subject_id":"26865690","author":{"uid":"2466058","avatar":"http://img7.doubanio.com/icon/u2466058-2.jpg","signature":"","alt":"http://www.douban.com/people/2466058/","id":"2466058","name":"乌鸦电影"},"summary":"理发店能出什么幺蛾子？还能搞出点旁门左道不成？这部《恐怖理发店》实则让人产生好奇。   理发店如今是泛泛地称谓，客人到里面可不止理发，还包括美容美发、运气好了还可以遇到一条龙服务。当然，无论你是什么人...","alt":"https://movie.douban.com/review/8240666/","id":"8240666"},{"rating":{"max":5,"value":4,"min":0},"title":"乡村人皮客栈玩转猎杀游戏","subject_id":"26865690","author":{"uid":"renlyuan","avatar":"http://img7.doubanio.com/icon/u63738807-3.jpg","signature":"爱看电影","alt":"http://www.douban.com/people/renlyuan/","id":"63738807","name":"铁任的电影笔记"},"summary":"贺岁大战已经打响，媒体都在聚焦《长城》《西游伏妖篇》这样的大阵仗，而历史的经验告诉我们，期待越高往往会失望越大，雷声大的未必下雨点，，反倒是在并不起眼的地方，往往潜伏着未预料的宝藏。果然，我们等到...","alt":"https://movie.douban.com/review/8239441/","id":"8239441"}]
     * ratings_count : 596
     * aka : ["Ghost in Barber's"]
     */

    var rating: RatingBean? = null
    var reviews_count: Int = 0
    var wish_count: Int = 0
    var original_title: String? = null
    var collect_count: Int = 0
    var images: ImagesBean? = null
    var douban_site: String? = null
    var year: String? = null
    var alt: String? = null
    var id: String? = null
    var mobile_url: String? = null
    var photos_count: Int = 0
    var pubdate: String? = null
    var title: String? = null
    var do_count: Any? = null
    var isHas_video: Boolean = false
    var share_url: String? = null
    var seasons_count: Any? = null
    var schedule_url: String? = null
    var website: String? = null
    var isHas_schedule: Boolean = false
    var collection: Any? = null
    var episodes_count: Any? = null
    var isHas_ticket: Boolean = false
    var current_season: Any? = null
    var mainland_pubdate: String? = null
    var summary: String? = null
    var subtype: String? = null
    var comments_count: Int = 0
    var ratings_count: Int = 0
    var videos: List<VideosBean>? = null
    var blooper_urls: List<*>? = null
    var popular_comments: List<PopularCommentsBean>? = null
    var languages: List<String>? = null
    var writers: List<WritersBean>? = null
    var pubdates: List<String>? = null
    var tags: List<String>? = null
    var durations: List<String>? = null
    var genres: List<String>? = null
    var trailers: List<TrailersBean>? = null
    var trailer_urls: List<String>? = null
    var bloopers: List<*>? = null
    var clip_urls: List<*>? = null
    var casts: List<CastsBean>? = null
    var countries: List<String>? = null
    var photos: List<PhotosBean>? = null
    var clips: List<*>? = null
    var directors: List<DirectorsBean>? = null
    var popular_reviews: List<PopularReviewsBean>? = null
    var aka: List<String>? = null

    class RatingBean {
        /**
         * max : 10
         * average : 2.4
         * details : {"1":70,"3":3,"2":5,"5":1,"4":0}
         * stars : 15
         * min : 0
         */

        var max: Int = 0
        var average: Double = 0.toDouble()
        var details: DetailsBean? = null
        var stars: String? = null
        var min: Int = 0

        class DetailsBean {
            /**
             * 1 : 70.0
             * 3 : 3.0
             * 2 : 5.0
             * 5 : 1.0
             * 4 : 0.0
             */

            @SerializedName("1")
            var `_$1`: Double = 0.toDouble()
            @SerializedName("3")
            var `_$3`: Double = 0.toDouble()
            @SerializedName("2")
            var `_$2`: Double = 0.toDouble()
            @SerializedName("5")
            var `_$5`: Double = 0.toDouble()
            @SerializedName("4")
            var `_$4`: Double = 0.toDouble()
        }
    }

    class ImagesBean {
        /**
         * small : http://img7.doubanio.com/view/photo/s_ratio_poster/public/p2406903891.jpg
         * large : http://img7.doubanio.com/view/photo/s_ratio_poster/public/p2406903891.jpg
         * medium : http://img7.doubanio.com/view/photo/s_ratio_poster/public/p2406903891.jpg
         */

        var small: String? = null
        var large: String? = null
        var medium: String? = null
    }

    class VideosBean {
        /**
         * source : {"literal":"qq","pic":"http://img7.doubanio.com/f/movie/38764466321ab88dfa19a1f826570367a19ce116/pics/movie/video-qq.png","name":"腾讯视频"}
         * sample_link : http://v.qq.com/x/cover/xzvr5axh7r6u524.html?ptag=douban.movie
         * video_id : xzvr5axh7r6u524
         * need_pay : false
         */

        var source: SourceBean? = null
        var sample_link: String? = null
        var video_id: String? = null
        var isNeed_pay: Boolean = false

        class SourceBean {
            /**
             * literal : qq
             * pic : http://img7.doubanio.com/f/movie/38764466321ab88dfa19a1f826570367a19ce116/pics/movie/video-qq.png
             * name : 腾讯视频
             */

            var literal: String? = null
            var pic: String? = null
            var name: String? = null
        }
    }

    class PopularCommentsBean {
        /**
         * rating : {"max":5,"value":1,"min":0}
         * useful_count : 3
         * author : {"uid":"1091411","avatar":"http://img3.doubanio.com/icon/u1091411-8.jpg","signature":"Hello WANKER","alt":"http://www.douban.com/people/1091411/","id":"1091411","name":"金福珠！"}
         * subject_id : 26865690
         * content : 89分钟……毫无内涵
         * created_at : 2017-01-23 15:33:10
         * id : 1140974290
         */

        var rating: RatingBeanX? = null
        var useful_count: Int = 0
        var author: AuthorBean? = null
        var subject_id: String? = null
        var content: String? = null
        var created_at: String? = null
        var id: String? = null

        class RatingBeanX {
            /**
             * max : 5
             * value : 1.0
             * min : 0
             */

            var max: Int = 0
            var value: Double = 0.toDouble()
            var min: Int = 0
        }

        class AuthorBean {
            /**
             * uid : 1091411
             * avatar : http://img3.doubanio.com/icon/u1091411-8.jpg
             * signature : Hello WANKER
             * alt : http://www.douban.com/people/1091411/
             * id : 1091411
             * name : 金福珠！
             */

            var uid: String? = null
            var avatar: String? = null
            var signature: String? = null
            var alt: String? = null
            var id: String? = null
            var name: String? = null
        }
    }

    class WritersBean {
        /**
         * avatars : {"small":"http://img3.doubanio.com/f/movie/ca527386eb8c4e325611e22dfcb04cc116d6b423/pics/movie/celebrity-default-small.png","large":"http://img7.doubanio.com/f/movie/63acc16ca6309ef191f0378faf793d1096a3e606/pics/movie/celebrity-default-large.png","medium":"http://img3.doubanio.com/f/movie/8dd0c794499fe925ae2ae89ee30cd225750457b4/pics/movie/celebrity-default-medium.png"}
         * name_en :
         * name : 纪然
         * alt : https://movie.douban.com/celebrity/1366595/
         * id : 1366595
         */

        var avatars: AvatarsBean? = null
        var name_en: String? = null
        var name: String? = null
        var alt: String? = null
        var id: String? = null

        class AvatarsBean {
            /**
             * small : http://img3.doubanio.com/f/movie/ca527386eb8c4e325611e22dfcb04cc116d6b423/pics/movie/celebrity-default-small.png
             * large : http://img7.doubanio.com/f/movie/63acc16ca6309ef191f0378faf793d1096a3e606/pics/movie/celebrity-default-large.png
             * medium : http://img3.doubanio.com/f/movie/8dd0c794499fe925ae2ae89ee30cd225750457b4/pics/movie/celebrity-default-medium.png
             */

            var small: String? = null
            var large: String? = null
            var medium: String? = null
        }
    }

    class TrailersBean {
        /**
         * medium : http://img3.doubanio.com/img/trailer/medium/2395934439.jpg?
         * title : 预告片：正式版 (中文字幕)
         * subject_id : 26865690
         * alt : https://movie.douban.com/trailer/206905/
         * small : http://img3.doubanio.com/img/trailer/small/2395934439.jpg?
         * resource_url : http://vt1.doubanio.com/201801271414/f7ea9b57d6159090adc1a4ba512d6e4f/view/movie/M/302060905.mp4
         * id : 206905
         */

        var medium: String? = null
        var title: String? = null
        var subject_id: String? = null
        var alt: String? = null
        var small: String? = null
        var resource_url: String? = null
        var id: String? = null
    }

    class CastsBean {
        /**
         * avatars : {"small":"http://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1403756298.69.jpg","large":"http://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1403756298.69.jpg","medium":"http://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1403756298.69.jpg"}
         * name_en : Guoer Yin
         * name : 殷果儿
         * alt : https://movie.douban.com/celebrity/1340984/
         * id : 1340984
         */

        var avatars: AvatarsBeanX? = null
        var name_en: String? = null
        var name: String? = null
        var alt: String? = null
        var id: String? = null

        class AvatarsBeanX {
            /**
             * small : http://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1403756298.69.jpg
             * large : http://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1403756298.69.jpg
             * medium : http://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1403756298.69.jpg
             */

            var small: String? = null
            var large: String? = null
            var medium: String? = null
        }
    }

    class PhotosBean {
        /**
         * thumb : http://img7.doubanio.com/view/photo/thumb/public/p2411789693.jpg
         * image : http://img7.doubanio.com/view/photo/photo/public/p2411789693.jpg
         * cover : http://img7.doubanio.com/view/photo/albumcover/public/p2411789693.jpg
         * alt : https://movie.douban.com/photos/photo/2411789693/
         * id : 2411789693
         * icon : http://img7.doubanio.com/view/photo/icon/public/p2411789693.jpg
         */

        var thumb: String? = null
        var image: String? = null
        var cover: String? = null
        var alt: String? = null
        var id: String? = null
        var icon: String? = null
    }

    class DirectorsBean {
        /**
         * avatars : {"small":"http://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1490348628.29.jpg","large":"http://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1490348628.29.jpg","medium":"http://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1490348628.29.jpg"}
         * name_en : Shilei Lu
         * name : 陆诗雷
         * alt : https://movie.douban.com/celebrity/1360707/
         * id : 1360707
         */

        var avatars: AvatarsBeanXX? = null
        var name_en: String? = null
        var name: String? = null
        var alt: String? = null
        var id: String? = null

        class AvatarsBeanXX {
            /**
             * small : http://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1490348628.29.jpg
             * large : http://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1490348628.29.jpg
             * medium : http://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1490348628.29.jpg
             */

            var small: String? = null
            var large: String? = null
            var medium: String? = null
        }
    }

    class PopularReviewsBean {
        /**
         * rating : {"max":5,"value":1,"min":0}
         * title : 国产恐怖片，注定成烂片？
         * subject_id : 26865690
         * author : {"uid":"123404248","avatar":"http://img7.doubanio.com/icon/u123404248-3.jpg","signature":"","alt":"http://www.douban.com/people/123404248/","id":"123404248","name":"世界奇妙物语"}
         * summary : 这一系列国产恐怖片太多，现在总结下国产电影拍摄门槛为什么这么低…… 1.找个导演，内地导演优先考虑(省钱)。 2.去网上热搜榜（也是经纪公司）上挑几个网红明星（省钱）。网红明星就像木偶一样被装扮上了。 3.去...
         * alt : https://movie.douban.com/review/8301338/
         * id : 8301338
         */

        var rating: RatingBeanXX? = null
        var title: String? = null
        var subject_id: String? = null
        var author: AuthorBeanX? = null
        var summary: String? = null
        var alt: String? = null
        var id: String? = null

        class RatingBeanXX {
            /**
             * max : 5
             * value : 1.0
             * min : 0
             */

            var max: Int = 0
            var value: Double = 0.toDouble()
            var min: Int = 0
        }

        class AuthorBeanX {
            /**
             * uid : 123404248
             * avatar : http://img7.doubanio.com/icon/u123404248-3.jpg
             * signature :
             * alt : http://www.douban.com/people/123404248/
             * id : 123404248
             * name : 世界奇妙物语
             */

            var uid: String? = null
            var avatar: String? = null
            var signature: String? = null
            var alt: String? = null
            var id: String? = null
            var name: String? = null
        }
    }
}