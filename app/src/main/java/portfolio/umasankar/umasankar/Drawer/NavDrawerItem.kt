package portfolio.umasankar.umasankar.Drawer

class NavDrawerItem {
    var isShowNotify: Boolean = false
    var title: String? = null

    var icon: String? = null


    constructor() {

    }

    constructor(showNotify: Boolean, title: String) {
        this.isShowNotify = showNotify
        this.title = title
    }
}