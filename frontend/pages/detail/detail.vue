<template>
	<view>
		<view class="banner">
			<image class="banner-img" :src="banner.cover"></image>
			<view class="banner-title">{{banner.title}}</view>
		</view>

		<!-- start -->
		<view class="swiperMain">
			<view class="swiperHead">

				<!--组件-->
				<swiperNavBar :scrollIntoView="scrollIntoView" :swiperTabList='swiperTabList' :swiperTabIdx='swiperTabIdx'
				 :currentSwiperWidth='currentSwiperWidth' :currentSwiperHeight='currentSwiperHeight' :swiperCurrentSize='swiperCurrentSize'
				 :swiperColor='swiperColor' :swiperCurrentColor='swiperCurrentColor' :currentSwiperLineShow="currentSwiperLineShow"
				 :currentSwiperLineActiveWidth="currentSwiperLineActiveWidth" :currentSwiperLineActiveHeight="currentSwiperLineActiveHeight"
				 :currentSwiperLineActiveBg="currentSwiperLineActiveBg" :currentSwiperLineAnimatie="currentSwiperLineAnimatie" v-if=" swiperTabList.length > 1 "
				 @change="CurrentTab">
				</swiperNavBar>
				<!--组件-->
			</view>

			<!--内容-->
			<view class="swiperCont" style="{ paddingTop:currentSwiperHeight + 'rpx' }">
				<swiper class="swiper" :style="{ height:mainHeight + 'px' }" :current="swiperTabIdx" @change="SwiperChange">
					<swiper-item class="swiperItem" v-for="(item,index) in swiperTabList" :key='index'>
						<!-- 简介 -->
						<view v-if="index==0" class="swiper-content">
							<view class="info-title">景区简介</view>
							<view class="info-content">{{htmlString}}</view>
						</view>
						<!-- 评论列表与添加评论 -->
						<view v-if="index==1" class="swiper-comment">
							<button @tap="writeComment">我要评论</button>
							<br>
							<view v-if="comments.length==0">还没有评论哦，快来添加吧！</view>
							<view v-for="(info,flag) in comments" class="all-comments" :key='flag'>
								<view class="col-left">
									<image class="user-head" :src="g_url+info['userAvatar']" @tap="leaveMessage(flag)"></image>
								</view>
								<view class="col-right" @tap="viewComment(flag)">
									<view class="c-title">{{getLocalTime(info['createTime'])}} {{info['username']}}: </view>
									<view class="c-content">{{info['content']}}</view>
								</view>
							</view>
						</view>
					</swiper-item>
				</swiper>
			</view>
			<!--内容-->
		</view>
		<!-- end -->
		<neil-modal :show="show" @close="bindClose(1)" title="添加评论" @confirm="bindBtn(1)" cancelText="取消" confirmText="提交">
			<textarea style="padding: 15px;" v-model="commentText" type="text" placeholder="请填写你的评论内容" />
		</neil-modal>
		<neil-modal :show="show1" v-if="comments.length!=0" @close="bindClose(2)" title="评论详情" @confirm="bindBtn(2)"
		 :showCancel="false">
			<view class="view-content">{{comments[commentFlag].content}}</view>
		</neil-modal>
		<neil-modal :show="show2" @close="bindClose(3)" title="留言" @confirm="bindBtn(3)" cancelText="取消" confirmText="提交">
			<input v-model="leaveContent" type="text" placeholder="对ta说点什么吧" />
		</neil-modal>
		<br>

		<view>
			<hr>
			<br>
			<view class="buttonBox" @click="submit">
				提交订单
			</view>
		</view>
		<Copyright></Copyright>
	</view>
</template>

<script>
	const valid = require('../../util/valid.js'); //校验规则文件
	const util = require('../../util/util.js'); //防重点击函数
	import Copyright from '@/components/Copyright/Copyright.vue';
	import swiperNavBar from "../../components/swiperNavBar.vue";
	import neilModal from '@/components/neil-modal/neil-modal.vue';
	export default {
		components: {
			Copyright,
			swiperNavBar,
			neilModal
		},
		data() {
			return {
				g_url: getApp().globalData.domain,
				show: false,
				show1: false,
				show2: false,
				id: 0,
				title: 'list-triplex-row',
				banner: {
					cover: '/static/spots/shaolin.jpg',
					title: '少林寺一日游',
					author_name: '西蒙牛',
					published_at: '2020-07-14'
				},
				htmlString: "",

				form: {
					region: ['广东省', '广州市', '海珠区'] //默认参数
				},
				/*************/
				//导航栏用
				scrollIntoView: 0, //设置哪个方向可滚动，则在哪个方向滚动到该元素
				swiperTabList: ['简介', '评论'], //导航列表
				swiperTabIdx: 0,
				swiperCurrentSize: '26rpx', //导航的字体大小
				swiperColor: '#333333', //导航栏字体未选中前颜色
				swiperCurrentColor: '#1D63FF', //选中当前导航栏字体颜色
				currentSwiperWidth: '50%', //当前导航的宽度 % upx rpx px  （导航超出可左右滑动 ）
				currentSwiperHeight: 70, //当前导航的高度度 rpx px
				mainHeight: 360, //全屏或者局部滑动设置的高度
				currentSwiperLineShow: true, //是否显示导航栏的线条 （线条距离标题太近的话可自行修改.swiperLine的css）
				currentSwiperLineActiveBg: '#1D63FF', //当前选中的导航栏线条颜色
				currentSwiperLineActiveWidth: '30rpx', //当前选中的导航栏线条的宽度 upx rpx px
				currentSwiperLineActiveHeight: '3rpx', //当前选中的导航栏线条的高度度 upx rpx px
				currentSwiperLineAnimatie: 300, //当前选中的导航栏线条过渡效果
				/*****************/
				comments: [], //评论列表
				commentText: "",
				commentContent: "",
				commentFlag: 0,
				leaveContent: "",
				leaveFlag: 0,
			}
		},
		onShareAppMessage() {
			return {
				title: this.banner.title,
				path: '/pages/detail/detail?detailDate=' + JSON.stringify(this.banner)
			}
		},
		onShow() {
			this.getComments();
		},
		onLoad(option) {
			this.banner.title = option.name
			this.banner.cover = option.imageUrl
			this.htmlString = option.detail
			this.id = option.id
			uni.setNavigationBarTitle({
				title: option.name
			});
		},
		methods: {
			//留言
			leaveMessage: function(flag) {
				this.show2 = true;
				this.leaveFlag = flag;
			},
			//浏览评论
			viewComment: function(flag) {
				this.show1 = true;
				this.commentFlag = flag;
			},
			getLocalTime: function(nS) {
				let time = new Date(nS*1000)
				return time.getFullYear() + "-" + (time.getMonth() - 0 + 1) + "-" + time.getDate() + " " + time.getHours() + ":" + time.getMinutes() + ":" + time.getSeconds()
			},
			//添加评论
			writeComment: function() {
				this.show = true;
			},
			bindClose: function(f) {
				if (f == 1) {
					this.show = false;
				}
				if (f == 2) {
					this.show1 = false;
				}
				if (f == 3) {
					this.show2 = false;
				}
			},
			bindBtn: function(f) { //成功添加评论
				let self = this;
				if (f == 1) {
					this.show = false;
					uni.request({
						url: getApp().globalData.domain + "/comments/",
						method: "POST",
						header: {
							token: uni.getStorageSync("token"),
							"content-type": "application/x-www-form-urlencoded"
						},
						data: {
							itemId: self.id,
							content: self.commentText
						},
						success: (res) => {
							// console.log(res.data);
							self.getComments();
							setTimeout(function() {
								uni.showToast({
									title: "添加成功！",
								}, 1000);
							});
							self.commentText = "";
						}
					})
				}
				if (f == 3) { //给该用户发送消息
					this.show2 = false;
					console.log(self.comments[self.leaveFlag]['user']);
					uni.request({
						url: getApp().globalData.domain + "/messages/",
						method: "POST",
						header: {
							token: uni.getStorageSync("token"),
							"content-type": "application/x-www-form-urlencoded"
						},
						data: {
							toId: self.comments[self.leaveFlag]['user'],
							content: self.leaveContent
						},
						success: (res) => {
							console.log(res.data);
							// self.getComments();
							setTimeout(function() {
								uni.showToast({
									title: "留言成功！",
								}, 1000);
							});
							self.leaveContent = "";
						}
					})
				}

			},
			//获取评论列表
			getComments: function() {
				let self = this;
				// console.log(self.id)
				uni.request({
					url: getApp().globalData.domain + "/comments",
					method: "GET",
					data: {
						itemId: self.id
					},
					header: {
						token: uni.getStorageSync("token"),
					},
					success: (res) => {
						// console.log(res);
						self.comments = res.data.data;
					},
					fail: (res) => {
						console.log("fail")
						self.comments = [];
					}
				})
			},
			// 输入框或者picker事件方法
			change(e) {
				let name = e.currentTarget.dataset.name;
				let tempVal = e.target.value || e.detail.value;
				if (this.form[name] === undefined) {
					console.log('首次添加属性名')
					this.$set(this.form, name, tempVal)
				} else {
					// 若存在则直接赋值
					this.form[name] = tempVal
				}
			},

			// 验证方法
			toVaild(tempList) {
				// valid(value,type)为引入的校验方法,49行可见
				return tempList.every((item, index) => {
					let isVal = this.form[item.paramName]
					if (!isVal || !valid(isVal, item.rules)) {
						uni.showModal({
							title: '错误',
							content: `${item.failPass}`,
							showCancel: false
						})
						return false;
					}
					return true;
				})
			},

			// 提交
			submit: function() {
				// 判断登录状态
				if (!getApp().globalData.isLogin) {
					uni.showToast({
						title: "请登录",
						icon: "none"
					})
					return;
				}
				this.addIndent(uni.getStorageSync("userId"), this.id)
			},

			addIndent: function(userId, itemId) { //添加订单
				uni.request({
					url: getApp().globalData.domain + "/indents/",
					header: {
						token: uni.getStorageSync("token"),
						"content-type": "application/x-www-form-urlencoded",
					},
					method: "POST",
					data: {
						itemId: itemId,
					},
					success: (res) => {
						// console.log(res)
						let data = res.data;
						if (data == "request error") {
							uni.showToast({
								title: "token验证失败",
								icon: "none"
							})
							return;
						}
						if (!data.mes) {
							uni.showToast({
								title: "添加成功",
								duration: 300,
								success: () => {
									setTimeout(uni.navigateBack, 300)
								}
							})
						}
					}
				})
			},

			getDetail() {
				uni.request({
					url: 'https://unidemo.dcloud.net.cn/api/news/36kr/' + this.banner.post_id,
					success: (data) => {
						if (data.statusCode == 200) {
							this.htmlString = data.data.content.replace(/\\/g, "").replace(/<img/g,
								"<img style=\"display:none;\"");
						}
					},
					fail: () => {
						console.log('fail');
					}
				})
			},

			//tab点击事件 自行完善需要的代码
			CurrentTab: function(index, item) {
				this.swiperTabIdx = index;
				this.scrollIntoView = Math.max(0, index - 1);
				//console.log(index + '----' + item)
			},
			//滑动事件  自行完善需要的代码
			SwiperChange: function(e) {
				//console.log(e)
				//console.log(e.detail)
				//console.log(e.detail.current);
				this.swiperTabIdx = e.detail.current;
				this.scrollIntoView = Math.max(0, e.detail.current - 1);
			},
		},
	}
</script>

<style lang="scss">
	view {
		font-size: 28upx;
		line-height: 1.8;
	}


	.line {
		margin-top: 30rpx;
		height: 100rpx;
		width: 92%;
		margin: 0 auto;
		border-bottom: 1px solid #f5f5f5;
		display: flex;

		.lineRight {
			.tips {
				color: #9a9a9c;
				font-weight: bold;
			}

			flex: 1;
			height: 100%;
			display: flex;
			align-items: center;
			justify-content: space-between;
			position: relative;
		}

		.lineLeft {
			display: flex;
			width: 20%;
			align-items: center;
			height: 100%;
			font-weight: bold;
			color: #000000;
		}

		.input {
			padding-right: 20rpx;
			height: 100%;
			width: 70%;
			text-align: left;
			font-size: 28rpx;
			color: #545151;
		}

		.picker {
			height: 100%;
			justify-content: flex-start;
			display: flex;
			align-content: center;
			width: 500rpx;
		}

		picker {
			height: 84rpx;
			line-height: 84rpx;
		}
	}


	.buttonBox {
		width: 91%;
		margin: 0 auto;
		height: 84rpx;
		border-radius: 84rpx;
		color: white;
		display: flex;
		align-items: center;
		justify-content: center;
		/*position: fixed;*/
		top: 10rpx;
		bottom: 100rpx;
		left: 0;
		right: 0;
		background-color: #1da31b;
		margin-top: 4px;
	}

	.banner {
		height: 360upx;
		overflow: hidden;
		position: relative;
		background-color: #ccc;
	}

	.banner-img {
		width: 100%;
	}

	.banner-title {
		max-height: 84upx;
		overflow: hidden;
		position: absolute;
		left: 30upx;
		bottom: 30upx;
		width: 90%;
		font-size: 32upx;
		font-weight: 400;
		line-height: 42upx;
		color: white;
		z-index: 11;
	}

	.article-meta {
		padding: 20upx 40upx;
		display: flex;
		flex-direction: row;
		justify-content: flex-start;
		color: gray;
	}

	.article-text {
		font-size: 26upx;
		line-height: 50upx;
		margin: 0 20upx;
	}

	.article-author,
	.article-time {
		font-size: 30upx;
	}

	.article-content {
		padding: 20 40upx;
		overflow: hidden;
		font-size: 30upx;
		margin: 30upx 50upx;
	}

	.content {
		white-space: pre-wrap;
	}

	.Copyright {
		position: fixed;
		bottom: 10px;
		font-family: kaiti;
		color: #888888;
		font-size: 13px;
		text-align: center;
		width: 100%;
	}

	/***/
	.swiperMain {
		width: 100%;
	}

	.swiperHead {
		// position: fixed;
		top: 50;
		z-index: 10;
		width: 100%;
		background: #FFFFFF;
		margin-top: 1px;
	}

	.swiperTab {
		// display: inline-flex;
		flex-direction: column;
		text-align: center;
	}

	.swiperCont {
		width: 100%;
		// padding-top: 70rpx;
	}

	/* #endif */

	.swiper {
		width: 100%;
		height: 400rpx;
	}

	.swiperItem {
		height: 400rpx;
		line-height: 400rpx;
		overflow: auto;
		background: rgba(0, 0, 0, 0);
		color: #FFFFFF;
		font-size: 30upx;
	}

	.info-title{
		font-size: 45upx;
		text-align: center;
		font-weight: 700;
		margin-bottom: 10px;
		
	}
	.info-content{
		white-space: pre-wrap;
	}
	.swiperItem image {
		width: 85%;
		height: 72%;
		display: block;
		border-radius: 50%;
	}

	/*自定义*/
	.swiper-content {
		padding: 20 40upx;
		overflow-y: scroll;
		font-size: 30upx;
		margin: 30upx 50upx;
		color: black;
	}

	.swiper-user {
		padding: 20 40upx;
		overflow: scroll;
		font-size: 30upx;
		margin: 30upx 50upx;
		color: black;
	}

	.swiper-comment {
		white-space: pre-wrap;
		text-align: left;
		padding: 25px;
		// padding: 20 40upx;
		overflow: scroll;
		font-size: 30upx;
		// margin: 30upx 50upx;
		color: black;
	}

	.all-comments {
		border-radius: 20upx;
		width: 100%;
		height: 180upx;
		background-color: #ffffff;
		margin-left: 1px;
		margin-right: 2px;
		// margin-top: 40upx; 
		margin-bottom: 35upx;
		display: flex;
		padding-right: 5upx;
		padding-bottom: 5upx;
		box-shadow: 2px 2px 6px rgba(0, 0, 0, .125), -2px -2px 6px rgba(0, 0, 0, .125);
	}

	.col-left {
		flex: 20%;
		max-width: 20%;
		padding: 8px;
		display: flex;
		align-items: center;
		justify-content: center;
	}

	.col-right {
		margin-top: 8px;
		flex: 80%;
		max-width: 75%;
		z-index: 1;
		/* height: 50upx; */
	}

	.c-title {
		padding: 8px;
		font-weight: 600;
		font-size: 12px;
		font-family: kaiti;
		text-align: left;
	}

	.c-content {
		margin-top: 8px;
		// flex: 80%;
		z-index: 1;
		width: 90%;
		text-overflow: ellipsis;
		overflow-x: hidden;
		/* height: 50upx; */
	}

	.view-content {
		word-wrap:break-word; 
		word-break:break-all;
		padding: 15px;
		overflow-y: scroll;
	}
</style>
