//本页面由uniapp切片工具生成，uni-app切片-可视化设计工具(一套代码编译到7个平台iOS、Android、H5、小程序)，软件下载地址：http://www.ymznkf.com/new_view_669.htm
import app from "../../App.vue"


// 定义全局参数,控制数据加载
var _self, page = 1,
	timer = null;

export default {
	data() {
		return {
			loadingText: '',
			list_orders: [],
			null_orders:[],
			page: 0, //当前分页页码
			apiUrl: '', //后端接口地址
			id: '', //传值使用,方便存在本地的locakStorage
		}
	},
	components: {

	},
	onLoad(options) {
		_self = this;
		//检查是否登录参考代码,需要用的时候，可以把注释取掉
		//if(this.checkLogin()==false){
		//	return;
		//}

		//this.getLaction();//得到gps

		this.page = 0;

		//检测有没有传入id参数
		if (options.id != null && options.id != "") {
			this.id = options.id;
		}
		//执行初始化,需要用的时候，可以把注释取掉
		//this.Refresh("init");

	},
	onShow() {
		// console.log("on show");
		//if(this.checkLogin()==false){
		//	return;
		//}

		this.refresh();
		//执行初始化,需要用的时候，可以把注释取掉
		//this.Refresh("init");
	},
	onPullDownRefresh: function() {
		//下拉刷新的时候请求一次数据
		setTimeout(() => {
			uni.stopPullDownRefresh();
			this.refresh();
		}, 600);
	},
	methods: {
		gotoOrderDetail: function(index) {
			// console.log(index);
			// console.log(JSON.stringify(this.list_orders[index]));
			uni.navigateTo({
				url: "/pages/order/detail?userInfo=" + this.list_orders[index]["userInfo"] +"&indentNo=" + this.list_orders[index]["indentNo"] + "&itemInfo=" +this.list_orders[index]["itemInfo"]
			})
		},
		refresh: function() {
			// 判断登录状态
			this.list_orders = [];
			if (!getApp().globalData.isLogin) {
				uni.showToast({
					title: "请登录",
					icon: "none"
				})
				return;
			}
			let self = this;
			uni.request({
				url: getApp().globalData.domain + "/indents",
				header: {
					token: uni.getStorageSync("token")
				},
				data: {
					userId: uni.getStorageSync("userId")
				},
				success: (res) => {
					uni.stopPullDownRefresh();
					let data = res.data;
					if (data == "request error") {
						uni.showToast({
							title: "请检查登录状态",
							icon: "none"
						})
						return;
					}
					if (!data.mes) {
						self.list_orders = res.data.data
						return;
					}
					uni.showToast({
						title: data.mes,
						icon: "none"
					})
				}
			})
		},

		//刷新数据
		Refresh: function(_action) {

			uni.showLoading();

			//提交到服务器
			var that = this
			var url = that.apiUrl + '{server_code_file_path}';
			console.log(url);
			uni.request({
				url: url, //后端接口地址，需要改成自己的接口地址
				data: {
					action: _action, //上传动作，如按钮点击会产生click动作，分页也会产生，在后端根据具体的动作来判断事件
					uid: that.getUid(), //上传用户id,在登录中获得
					//上传页面中的变量
					//定义变量---start

					//定义变量---end
					id: that.id, //上传id值，后端可以根据此值来操作当前id
					del_id: that.del_id, //在列表中删除按钮选中时，上传到有后端，进行删除操作
					page: that.page
				},
				method: 'GET',
				success: function(res) { //后端返回数据

					// 隐藏导航栏加载框  
					uni.hideNavigationBarLoading();
					// 停止下拉动作  
					uni.stopPullDownRefresh();

					// 隐藏加载框  
					uni.hideLoading();

					var tmp = res.data;

					//初始化，对页面上的控件进行赋值操作
					if (_action == "init") {

					}

					{
						deal_listpages
					}

					that.address_from = tmp.address_from;
					that.address_to = tmp.address_to;
					that.orderNo = tmp.orderNo;



					//如果后端有返回消息，则弹出消息提示
					if (tmp.message != null && tmp.message != "") {
						uni.showToast({
							title: tmp.message,
							icon: 'none',
							duration: 2000
						})
					}



					//如果后端有返回页码，则更改当前页码
					if (tmp.page != null && tmp.page != "") {
						page = tmp.page;
					}

				},
				fail: function(res) {
					uni.showToast({
						title: "服务器访问失败",
						icon: 'none',
						duration: 2000
					})
					console.log(res.data);
					console.log('is failed')
				}
			})
		},
	}
}
