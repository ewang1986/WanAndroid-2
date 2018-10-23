package com.sqsong.wanandroid.data

abstract class BaseBean<T : Any> {
    val errorCode: Int = 0
    val errorMsg: String? = null
    abstract var data: T
}

data class BaseData(override var data: Any) : BaseBean<Any>()

/********************* Begin: Login Data ***********************/

data class LoginBean(override var data: LoginData) : BaseBean<LoginData>()

data class LoginData(val id: Int,
                     val username: String)

/********************* End: Login Data *************************/

/********************* Begin: Home banner bean ***********************/
data class HomeBannerBean(override var data: MutableList<HomeBannerData>) : BaseBean<MutableList<HomeBannerData>>()

data class HomeBannerData(val desc: String,
                          val id: Int,
                          val imagePath: String,
                          val isVisible: Int,
                          val order: Int,
                          val title: String,
                          val type: Int,
                          val url: String)
/********************* End: Home banner bean ***********************/

/********************* Begin: Home list bean **********************/
data class HomeItemBean(override var data: HomeItemData) : BaseBean<HomeItemData>()

data class HomeItemData(val curPage: Int,
                        var datas: MutableList<HomeItem>,
                        val offset: Int,
                        val over: Boolean,
                        val pageCount: Int,
                        val size: Int,
                        val total: Int)

data class HomeItem(val apkLink: String,
                    val author: String,
                    val chapterId: Int,
                    val chapterName: String,
                    var collect: Boolean,
                    val courseId: Int,
                    val desc: String,
                    val envelopePic: String,
                    val fresh: Boolean,
                    val id: Int,
                    val link: String,
                    val niceDate: String,
                    val origin: String,
                    val projectLink: String,
                    val publishTime: Long,
                    val superChapterId: Int,
                    val superChapterName: String,
                    val title: String,
                    val userId: Int,
                    val visible: Int,
                    var zan: Int)
/********************* End: Home list bean **********************/