<template>
	<view>
		<view class="item">
			<input v-model="Ftitle" class="main-input" type="text" maxlength="30" placeholder="请输入您问题的标题"></input>
		</view>
		<textarea v-model="Ftext" placeholder="请输入您问题的详细情况后提交反馈按键" class="text_area"></textarea>
		
		<view class="btn_login" @click="feedback">反馈</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				Ftitle:'',
				Ftext:'',
			}
		},
		methods: {
			feedback:function(){
				if (!getApp().globalData.isLogin){
					uni.showToast({
						title:"请登录",
						icon: "none"
					})
					return;
					
				}
				let self = this;
				if(!self.Ftitle||!self.Ftext){
					uni.showToast({
						title:'请输入标题或正文',
						icon:'none'
					})
					return;
				}
				
				//登录后则进行反馈发送操作，暂时没写
				uni.request({
					url:getApp().globalData.domain+"/feedbacks/",
					method:"POST",
					data:{
						userId:uni.getStorageSync("userId"),
						title:self.Ftitle,
						content:self.Ftext
					},
					header:{
						token:uni.getStorageSync("token")
					},
					success:(res)=>{
						console.log(res.data);
					}
				})
			}
		}
	}
</script>

<style>
	.item {
		line-height: 40px;
		display: flex;
		justify-content:center;
		position: fixed;
	}
	.main-input {
		font-size: 14px;
		margin-left: 100px;
		border-bottom: 1px solid #eeeeee;
		height: 40px;
		line-height: 40px;
	}
	.btn_login {
		position: fixed;
		color: #ffffff;
		font-size: 16px;
		width: 260px;
		height: 40px;
		background: #3399ff;
		border-radius: 8px;
		line-height: 40px;
		text-align: center;
		bottom:100px;
		left: 75px;
	}
	.text_area{
		height: 500px;
		top:70px;
		font-size: 16px;
		background: #efefef;
		border: 1px #0055B8;
		margin: auto;
	}
</style>
