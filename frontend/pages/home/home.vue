<template>
	<view>
		<uni-nav-bar :fixed="false" color="#333333" background-color="#FFFFFF" @clickRight="showUserInfo">
			<block slot="left">
				<view class="city" style="width: 120rpx;">
					<!--
					<view><text class="uni-nav-bar-text">{{ city }}</text></view>
					<uni-icons type="arrowdown" color="#333333" size="22" />
					-->
					<view>
						<view>
							<view>
								<picker @change="bindPickerChange" :value="index" :range="array">
									<view class="uni-input">{{array[index]}}</view>
								</picker>
							</view>
						</view>
					</view>
				</view>
			</block>
			<view class="input-view">
				<uni-icons class="input-uni-icon" type="search" size="22" color="#666666" />
				<input confirm-type="search" class="nav-bar-input" type="text" placeholder="输入旅游目的地" @confirm="confirm">
			</view>
		</uni-nav-bar>

		<br>
		<swiper-bar :imgList="hotItems" class="novelItem"></swiper-bar>
		<view class="uni-product-list">
			<view class="uni-product" v-for="(product,index) in productList" :key="index">
				<view class="image-view">
					<image v-if="renderImage" class="uni-product-image" :src="product.imageUrl" @tap="goToDetail(product.id, product.name, product.detail, product.imageUrl)"></image>
				</view>
				<view class="uni-product-title">{{product.name}}</view>
				<view class="uni-product-price">
					<text class="uni-product-price-favour">￥{{product.originalCost}}</text>
					<text class="uni-product-price-original">￥{{product.currentCost}}</text>
					<text class="uni-product-tip">{{product.type}}</text>
				</view>
			</view>
		</view>
	</view>
</template>

<script>
	// console.log(uni.getSystemInfoSync());
	import uniIcons from '@/components/uni-icons/uni-icons.vue'
	import uniNavBar from '@/components/uni-nav-bar/uni-nav-bar.vue'
	import uniSection from '@/components/uni-section/uni-section.vue'
	import swiperBar from "@/components/lps-swiper/lps-swiper.vue"


	var dateUtils = require('../../common/util.js').dateUtils;


	export default {
		components: {
			uniIcons,
			uniNavBar,
			uniSection,
			swiperBar
		},
		data() {
			return {
				city: '北京',
				array: ['北京', '上海', '广州', '西安', '成都', '重庆', '厦门', '黄山', '青岛'],
				index: 0,
				title: 'product-list',
				productList: [],
				renderImage: false,
				banner: {},
				listData: [],
				last_id: "",
				reload: false,
				hotItems: []
			}
		},
		onLoad() {

			// console.log(getApp(	).globalData.domain)
			this.loadData();
			this.getHotItems();
			setTimeout(() => {
				this.renderImage = true;
			}, 300);
		},
		methods: {

			loadData(action = 'refresh') {

				if (action === 'refresh') {
					this.productList = [];
				}
				let self = this;
				uni.request({
					url: getApp().globalData.domain + "/items",

					success: (res) => {
						// console.log(res)
						self.productList = res.data.data;
						uni.stopPullDownRefresh();
					},
					fail: (res) => {
						// console.log(res)
						console.log(res)
					}
				})

				// data.forEach(item => {
				// 	this.productList.push(item);
				// });
			},

			getHotItems: function() {
				let self = this;
				uni.request({
					url: getApp().globalData.domain + "/items/hot",
					success: (res) => {
						self.hotItems = res.data.data;
					}
				})
			},

			bindPickerChange: function(e) {
				console.log('picker发送选择改变，携带值为', e.target.value)
				this.index = e.target.value
			},

			onPullDownRefresh() {
				this.loadData('refresh');
				// 实际开发中通常是网络请求，加载完数据后就停止。这里仅做演示，加延迟为了体现出效果。
				setTimeout(() => {
					uni.stopPullDownRefresh();
				}, 2000);
			},


			clickLeft() {

				uni.showToast({
					title: '左侧按钮'
				})
			},
			search() {
				uni.showToast({
					title: '搜索没写'
				})
			},

			showUserInfo() {
				uni.showToast({
					title: '显示用户信息'
				})
			},
			confirm() {
				uni.showToast({
					title: '搜索没写'
				})
			},

			goToDetail: function(id, name, detail, imageUrl) {
				uni.navigateTo({
					url: "/pages/detail/detail?id=" + id + "&name=" + name + "&detail=" + detail + "&imageUrl=" +
						encodeURIComponent(imageUrl),
					fail: (res) => {
						console.log(res)
					}
				})
			}
		},
	}
</script>

<style>
	/* 头条小程序组件内不能引入字体 */
	/* #ifdef MP-TOUTIAO */
	@font-face {
		font-family: uniicons;
		font-weight: normal;
		font-style: normal;
		src: url('~@/static/uni.ttf') format('truetype');
	}

	/* #endif */

	/* #ifndef APP-NVUE */
	page {
		display: flex;
		flex-direction: column;
		box-sizing: border-box;
		background-color: #efeff4;
		min-height: 100%;
		height: auto;
	}

	view {
		font-size: 14px;
		line-height: inherit;
	}

	.example {
		padding: 0 15px 15px;
	}

	.example-info {
		padding: 15px;
		color: #3b4144;
		background: #ffffff;
	}

	.example-body {
		flex-direction: row;
		flex-wrap: wrap;
		justify-content: center;
		padding: 0;
		font-size: 14px;
		background-color: #ffffff;
	}

	/* #endif */
	.example {
		padding: 0 15px;
	}

	.example-info {
		/* #ifndef APP-NVUE */
		display: block;
		/* #endif */
		padding: 15px;
		color: #3b4144;
		background-color: #ffffff;
		font-size: 14px;
		line-height: 20px;
	}

	.example-info-text {
		font-size: 14px;
		line-height: 20px;
		color: #3b4144;
	}


	.example-body {
		flex-direction: column;
		padding: 15px;
		background-color: #ffffff;
	}

	.word-btn-white {
		font-size: 18px;
		color: #FFFFFF;
	}

	.word-btn {
		/* #ifndef APP-NVUE */
		display: flex;
		/* #endif */
		flex-direction: row;
		align-items: center;
		justify-content: center;
		border-radius: 6px;
		height: 48px;
		margin: 15px;
		background-color: #007AFF;
	}

	.word-btn--hover {
		background-color: #4ca2ff;
	}

	.uni-nav-bar-text {
		font-size: 28rpx;
	}

	.city {
		/* #ifndef APP-PLUS-NVUE */
		display: flex;
		/* #endif */
		flex-direction: row;
		align-items: center;
		justify-content: flex-start;
		width: 120rpx;

		margin-left: 0px;
	}

	.input-view {
		/* #ifndef APP-PLUS-NVUE */
		display: flex;
		/* #endif */
		flex-direction: row;
		width: 480rpx;
		flex: 1;
		background-color: #f8f8f8;
		height: 30px;
		border-radius: 15px;
		padding: 0 15px;
		flex-wrap: nowrap;
		margin: 7px 0;
		line-height: 30px;
	}

	.input-uni-icon {
		line-height: 30px;
	}

	.nav-bar-input {
		height: 30px;
		line-height: 30px;
		/* #ifdef APP-PLUS-NVUE */
		width: 480rpx;
		/* #endif */
		padding: 0 5px;
		font-size: 28rpx;
		background-color: #f8f8f8;
	}

	.example-body {
		padding: 0;
	}


	view {
		font-size: 24upx;
	}

	.uni-product-list {
		display: flex;
		width: 100%;
		flex-wrap: wrap;
		flex-direction: row;
	}

	.uni-product {
		padding: 15upx;
		display: flex;
		flex-direction: column;
	}

	.image-view {
		height: 240upx;
		width: 330upx;
		margin: 12upx 0;
	}

	.uni-product-image {
		border-radius: 4px;
		height: 240upx;
		width: 330upx;
	}

	.uni-product-title {
		width: 300upx;
		word-break: break-all;
		display: -webkit-box;
		overflow: hidden;
		line-height: 1.5;
		text-overflow: ellipsis;
		-webkit-box-orient: vertical;
		-webkit-line-clamp: 2;
	}

	.uni-product-price {
		margin-top: 10upx;
		font-size: 28upx;
		line-height: 1.5;
		position: relative;
	}

	.uni-product-price-original {
		color: #e80080;
	}

	.uni-product-price-favour {
		color: #888888;
		text-decoration: line-through;
		margin-left: 10upx;
	}

	.uni-product-tip {
		position: absolute;
		right: 10upx;
		background-color: #ff3333;
		color: #ffffff;
		padding: 0 10upx;
		border-radius: 5upx;
	}
</style>
