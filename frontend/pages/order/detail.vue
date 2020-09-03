<template>
	<view class="content">
		<view class="logo">
			<image class="logo-img" :src="avatar"></image>
		</view>
		<view class="info" :style="{'background-image': 'url('+itemImg+')'}">
			<view class="info-item">用户信息：{{userInfo}}</view>
			<view class="info-item">订单信息：{{itemInfo}}</view>
			<view class="info-item">订单号：{{indentNo}}</view>
		</view>
		<view class="option">
			<text @tap="delOrder">删除</text>
			<!-- <text @tap='test'>测试</text> -->
		</view>
		<view class="Copyright">
			<text >Copyright © 2020 901梦之队 荣誉出品</text>
		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				indentNo: -1,
				userInfo: "",
				itemInfo: "",
				itemImg: "",
				avatar: "/static/logo.jpg"
			}
		},
		onLoad(option) {
			this.indentNo = option.indentNo;
			this.userInfo = option.userInfo;
			this.itemInfo = option.itemInfo;
			this.getItemImg();
			this.loadUserInfo();
		},
		methods: {
			//删除订单
			delOrder: function() {
				let self = this;
				uni.showModal({
					title: "警告",
					confirmText: "确认删除",
					content: "确认删除订单？",
					success: function(res) {
						if (res.cancel) { //取消删除
							console.log("取消删除");
						} else {
							uni.request({
								url: getApp().globalData.domain + "/indents/" + self.indentNo,
								method:"DELETE",
								header:{
									token: uni.getStorageSync("token")
								},
								success: function(res1) {
									if (res1.data.mes) { //删除失败
										uni.showToast({
											title: "订单不存在"
										})
									}else{
										uni.showToast({
											title:"删除成功！",
											duration:300,
										})
										setTimeout(()=>{
											uni.navigateBack()
										},300)
									}
								}
							})
							console.log("确认删除");
						}
					}
				})
			},
			test: function(){
				console.log("test")
			},
			loadUserInfo(){
				let self = this;
				uni.request({
					url: getApp().globalData.domain + "/users/" + uni.getStorageSync("userId"),
					success: (res) => {
						self.avatar = getApp().globalData.domain + res.data.data.avatar;
					}
				})
			},
			getItemImg: function(){
				let self = this;
				uni.request({
					url: getApp().globalData.domain+ "/items/",
					data:{
						name: self.itemInfo
					},
					success: (res) => {
						self.itemImg = res.data.data.imageUrl
					}
				})
			}
		}
	}
</script>

<style>
	.logo {
		margin-top: 50px;
		margin-bottom: 50px;
		width: 100%;
		height: 180upx;
		padding: 20upx;
		text-align: center;
	}
	.logo-img {
		width: 150upx;
		height: 150upx;
		border-radius: 150upx;
	}
	.content{
		box-sizing: border-box;
		position: absolute;
		top: 0;
		left: 0;
		width: 100%;
		height: 100%;
		background-color: #f1f2f1;
		display: flex;
		align-items: center;
		flex-direction: column;
	}
	.info{
		position: relative;
		text-align: center;
		background-color: #FFFFFF;
		width: 80%;
		border-radius: 32px;
		box-shadow: 4px 4px 12px rgba(0,0,0,.2), -4px -4px 12px rgba(0,0,0,.2);
		background-image: url("https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=3394705973,4193626292&fm=26&gp=0.jpg");
		background-position: center, center;
		background-size: cover;
		display: flex;
		flex-direction: column;
		padding: 15px;
	}
	.info::before{
		content: "";
		position: absolute;
		top: 0;
		left: 0;
		width: 100%;
		height: 100%;
		background-color: rgba(0,0,0,.5);
		border-radius: 32px;
	}
	.info-item{
		z-index: 1;
		width: 100%;
		color: #FFFFFF;
		margin-top: 15px;
		margin-bottom: 15px;
		font-family: kaiti;
	}
	.option{
		margin-top:40px;
	}
	.option text{
		color: #007AFF;
		margin-left: 15px;
		margin-right: 15px;
	}
	.Copyright{
		position: fixed;
		bottom: 10px;
		font-family: kaiti;
		color: #888888;
		font-size: 13px;
	}
</style>
