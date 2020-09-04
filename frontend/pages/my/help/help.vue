<template>
	<view>
		<view class="item">
			<input v-model="Ftitle" class="main-input" type="text" maxlength="30" placeholder="请输入您的问题摘要"></input>
		</view>
		<textarea v-model="Ftext" :focus="test" placeholder="请输入问题的详细描述" class="text_area"></textarea>

		<view class="btn_submit" @click="feedback">提交</view>

		<Copyright/>
	</view>
</template>

<script>
	import Copyright from '@/components/Copyright/Copyright.vue'
	export default {
		components: {
			Copyright
		},
		data() {
			return {
				Ftitle: '',
				Ftext: '',
			}
		},
		methods: {
			test: function() {
				console.log("sss")
			},
			feedback: function() {
				if (!getApp().globalData.isLogin) {
					uni.showToast({
						title: "请登录",
						icon: "none"
					})
					return;

				}
				let self = this;
				if (!self.Ftitle || !self.Ftext) {
					uni.showToast({
						title: '请输入标题或正文',
						icon: 'none'
					})
					return;
				}

				//登录后则进行反馈发送操作，暂时没写
				uni.request({
					url: getApp().globalData.domain + "/feedbacks/",
					method: "POST",
					data: {
						userId: uni.getStorageSync("userId"),
						title: self.Ftitle,
						content: self.Ftext
					},
					header: {
						token: uni.getStorageSync("token")
					},
					success: (res) => {
						uni.showToast({
							title: "添加成功",
						})
						setTimeout(() => {
							uni.navigateBack()
						}, 500)
					}
				})
			}
		}
	}
</script>

<style>
	page {
		font-family: kaiti;
	}

	.item {
		margin-top: 50px;
		line-height: 40px;
	}

	.main-input {
		font-size: 20px;
		margin-left: 30px;
		margin-right: 15px;
		border-bottom: 1px solid #eeeeee;
		height: 40px;
		line-height: 40px;
		font-weight: 600;
	}

	.btn_submit {
		color: #ffffff;
		font-size: 16px;
		margin-left: 70px;
		margin-right: 70px;
		margin-top: 30px;
		background: #3399ff;
		border-radius: 8px;
		line-height: 40px;
		text-align: center;
	}

	.text_area {
		color: #495057;
		margin-top: 30px;
		height: 450px;
		padding: 30rpx;
		font-size: 16px;
		border: 1px solid #ced4da;
		border-radius: 16px;
		margin-left: 30px;
		margin-right: 15px;
		transition: border-color .15s ease-in-out, box-shadow .15s ease-in-out;
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

	/* 
	 */
</style>
