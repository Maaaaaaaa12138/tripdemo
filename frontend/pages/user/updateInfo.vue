<template>
	<view>
		<!-- 主体表单 -->
		<view class="main">
			<view class="logo">
				<image class="logo-img" :src="domain+avatar" @tap="choose_img"></image>
			</view>
			<view class="item">
				<image class="login_image" src="/static/login/email.png"></image>
				<input class="main-input email-input" type="text" v-model="email" placeholder="请输入邮箱" disabled="disabled" />
			</view>
			<view style="margin-top: 8px"></view>
			<view class="item">
				<image class="login_image" src="/static/login/userName.png"></image>
				<input class="main-input" type="number" v-model="phoneData" placeholder="请输入手机号" />
			</view>
			<view style="margin-top: 8px"></view>
			<view class="item">
				<image class="login_image" src="/static/login/type.png"></image>
				<input class="main-input" type="text" v-model="username" placeholder="请输入用户名" />
			</view>
			<view class="btn_login" @click="startUpdate">确认修改</view>
			<Copyright />
		</view>

		<neil-modal :show="show" @close="bindClose" title="头像预览" @confirm="bindBtn('confirm')" @cancel="bindBtn('cancel')">
			<center>
				<image class="logo-img1" :src="preImage"></image>
			</center>
		</neil-modal>

	</view>
</template>

<script>
	var _this;
	import neilModal from '@/components/neil-modal/neil-modal.vue';
	import Copyright from '@/components/Copyright/Copyright.vue'
	export default {
		components: {
			Copyright,
			neilModal
		},
		data() {
			return {
				//logo图片 base64
				domain: getApp().globalData.domain,
				avatar: '/static/avatar/logo.jpg',
				phoneData: '', //电话
				showAgree: false,
				email: '', //邮箱
				username: "", //用户名
				show: false,
				preImage: "",
				choose_type: false
			};
		},
		onLoad() {
			this.loadData()
		},
		destroyed() {
			uni.hideKeyboard()
		},
		methods: {
			bindOpen: function() {
				this.show = true;
			},
			bindClose: function() {
				this.show = false;
			},
			bindBtn: function(type) { //选择确认还是取消
				if (type == "confirm") {
					this.choose_type = true;
					let self = this;
					if (self.choose_type == true) { //用户选择上传
						self.choose_type = false;
						uni.uploadFile({
							url: getApp().globalData.domain + "/users/avatar/",
							method: "POST",
							filePath:self.preImage,
							header:{
								token: uni.getStorageSync("token")
							},
							name:"file",
							success: (res) => {
								if (res.data.mes){
									uni.showToast({
										title: res.data.mes,
										icon: "none"
									})
									return;
								}
								self.avatar = JSON.parse(res.data).data;
								// console.log(self.avatar)
							},
							fail(res) {
								// console.log(res);
							}
						})
					}
				}
			},
			choose_img: function() {
				let self = this;
				uni.chooseImage({
					sizeType: ['compressed'], //压缩图
					count: 1, //只可选择一张
					sourceType: ['album', 'camera'],
					success: (res) => {
						// console.log(res.tempFilePaths);
						self.preImage = res.tempFilePaths[0];
						self.bindOpen();
					}
				})
			},
			showMsg: function(title) {
				uni.showToast({
					title: title,
					icon: "none"
				})
			},
			navBack() {
				uni.navigateBack();
			},
			loadData() {
				// 判断是否登录
				if (!getApp().globalData.isLogin) {
					uni.showToast({
						title: "请登录后再操作",
						icon: "none",
						duration: 500
					})
					setTimeout(() => {
						uni.navigateBack()
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
			toUpdate() {
				let self = this;
				uni.request({
					url: getApp().globalData.domain + "/users/",
					method: "PUT",
					header: {
						"token": uni.getStorageSync("token")
					},
					data: {
						id: uni.getStorageSync("userId"),
						email: self.email,
						phoneNumber: self.phoneData,
						username: self.username,
						avatar: self.avatar
					},
					success: (res) => {
						let data = res.data;
						if (data.mes) {
							uni.showToast({
								title: data.mes,
								icon: "none"
							})
						} else {
							uni.showToast({
								title: "修改成功"
							})
						}
						setTimeout(() => {
							uni.navigateBack();
						}, 500)
					}
				})
			},
			startUpdate() {
				if (!this.email) {
					this.showMsg("请输入邮箱");
					return;
				}
				if (this.phoneData.length != 11) {
					this.showMsg("请输入正确的手机号");
					return;
				}
				if (!this.username) {
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

	.main {
		display: flex;
		flex-direction: column;
		align-items: center;
		padding-top: 32upx;
		padding-left: 70upx;
		padding-right: 70upx;
		background-color: #f2f5fa;
		position: absolute;
		height: 100%;
		width: 100%;
		box-sizing: border-box;
	}

	.senCode {
		font-size: 14px;
		color: #3399ff;
	}

	.email-input {
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
		height: 180upx;
		padding: 20upx;

	}

	.logo-img {
		width: 150upx;
		height: 150upx;
		border-radius: 150upx;
	}

	.logo-img1 {
		margin-top: 20upx;
		margin-bottom: 20upx;
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
		width: 100%;
	}

	.login_image {
		margin-top: 8px;
		width: 20px;
		height: 25px;
	}

	.main-input {
		font-size: 14px;
		margin-left: 16px;
		border-bottom: 1px solid #acacac;
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
		margin-top: 70px;
	}

	.main-input {
		flex: 1;
		text-align: left;
		font-size: 28 upx;
		padding-right: 10 upx;
		margin-left: 20 upx;
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
</style>
