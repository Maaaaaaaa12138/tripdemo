<template>
	<view>
		<!-- 主体表单 -->
		<view class="main">
			<view class="logo">
				<image class="logo-img" :src="domain+avatar"></image>
			</view>
			<view class="item">
				<image class="login_image" src="/static/login/email.png"></image>
				<input class="main-input email-input" type="text" v-model="email" placeholder="请输入邮箱" disabled="disabled" />
			</view>
			<view style="margin-top: 8px"></view>
			<view class="item">
				<image class="login_image" src="/static/login/userName.png"></image>
				<input class="main-input" type="number" v-model="phoneData" placeholder="请输入手机号"  />
			</view>
			<view style="margin-top: 8px"></view>
			<view class="item">
				<image class="login_image" src="/static/login/type.png"></image>
				<input class="main-input" type="text" v-model="username" placeholder="请输入用户名"  />
			</view>
			
		</view>
		<view class="btn_login" @click="startUpdate">确认修改</view>
	</view>
</template>

<script>
	var _this;

	export default {
		data() {
			return {
				//logo图片 base64
				domain : "http://ftp.12138.site:666",
				avatar: '/static/avatar/logo.jpg',
				phoneData: '', //电话
				showAgree: false,
				email: '', //邮箱
				username: "", //用户名
			};
		},
		onLoad(){
			this.loadData()
		},
		destroyed() {
			uni.hideKeyboard()
		},
		methods: {
			showMsg: function(title){
				uni.showToast({
					title:title,
					icon:"none"
				})
			},
			navBack() {
				uni.navigateBack();
			},
			loadData(){
				// 判断是否登录
				if (!getApp().globalData.isLogin){
					uni.showToast({
						title: "请登录后再操作",
						icon: "none",
						duration:500
					})
					setTimeout(()=>{
						// uni.navigateBack()
					}, 500)
					return;
				}
				let self = this;
				uni.request({
					url: getApp().globalData.domain + "/users/" + uni.getStorageSync("userId"),
					success: (res) => {
						let data = res.data.data;
						self.avatar = data.avatar;
						self.email = data.email;
						self.phoneData = data.phoneNumber;
						self.username = data.username;
					}
				})
			},
			toUpdate(){
				let self = this;
				uni.request({
					url: getApp().globalData.domain + "/users/",
					method:"PUT",
					header:{
						"token": uni.getStorageSync("token")
					},
					data:{
						id: uni.getStorageSync("userId"),
						email: self.email,
						phoneNumber: self.phoneData,
						username: self.username,
						avatar: self.avatar
					},
					success: (res) => {
						let data = res.data;
						if (data.mes){
							uni.showToast({
								title: data.mes,
								icon: "none"
							})
						}
						else{
							uni.showToast({
								title: "修改成功"
							})
						}
						setTimeout(()=>{
							uni.navigateBack();
						}, 500)
					}
				})
			},
			startUpdate() {
				if (!this.email){
					this.showMsg("请输入邮箱");
					return;
				}
				if (this.phoneData.length !=11) {
					this.showMsg("请输入正确的手机号");
					return;
				}
				if (!this.username){
					this.showMsg("请输入用户名")
					return;
				}
				this.toUpdate();
			},
		}
	}
</script>

<style>
	@import url('/static/css/icon.css');
	@import url('/static/css/main.css');

	.senCode{
		font-size: 14px;
		color: #3399ff;
	}
	.email-input{
		color: #888888;
	}
	.title_des {
		color: #3399ff;
		font-size: 22px;
		margin-bottom: 32px;
	}
	.logo {
		margin-top: 50px;
		margin-bottom: 50px;
		width: 100%;
		height: 180upx;
		padding: 20upx;
		box-sizing: border-box;
		text-align: center;
	}
	.logo-img {
		width: 150upx;
		height: 150upx;
		border-radius: 150upx;
	}
	.login_icon_wx {
		color: #999999;
		border: none;
	}

	.type {
		display: flex;
		margin-left: 16px;
		border-bottom: 1px solid #EEEEEE;
		width: 100%;
	}

	.weixinLogin {
		color: #999999;
		text-align: center;
		font-size: 12px;
		margin-top: 60px;
	}

	.footer_des {
		color: #666666;
		text-align: center;
		font-size: 14px;
		margin-top: 40px;
	}

	.getCode {
		font-size: 14px;
		margin-left: 40px;
		color: #3399ff
	}

	.item {
		line-height: 40px;
		display: flex;
	}

	.login_image {
		margin-top: 8px;
		width: 20px;
		height: 25px;
	}

	.main-input {
		font-size: 14px;
		margin-left: 16px;
		border-bottom: 1px solid #EEEEEE;
		height: 40px;
		line-height: 40px;
	}

	.main-input-code {
		width: 70%;
		font-size: 14px;
		height: 40px;
		line-height: 40px;
	}

	.btn_login {
		color: #FFFFFF;
		font-size: 16px;
		width: 250px;
		height: 40px;
		background: #3399ff;
		border-radius: 8px;
		line-height: 40px;
		text-align: center;
		margin-left: auto;
		margin-right: auto;
		margin-top: 45px;
	}

	.main-input {
		flex: 1;
		text-align: left;
		font-size: 28 upx;
		padding-right: 10 upx;
		margin-left: 20 upx;
	}
</style>
