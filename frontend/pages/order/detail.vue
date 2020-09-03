<template>
	<view>
		<view class="userinfo">用户信息：{{userInfo}}</view>
		<view class="iteminfo">订单信息：{{itemInfo}}</view>
		<view class="indent">订单号：{{indentNo}}</view>
		<button @tap="payOrder" type="primary">支付订单</button>
		<button @tap="delOrder" type="warn">删除订单</button>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				indentNo: -1,
				userInfo: "",
				itemInfo: ""
			}
		},
		onLoad(option) {
			this.indentNo = option.indentNo;
			this.userInfo = option.userInfo;
			this.itemInfo = option.itemInfo;
		},
		methods: {
			//删除订单
			delOrder: function() {
				uni.showModal({
					title: "警告",
					confirmText: "确认删除",
					content: "确认删除订单？",
					success: function(res) {
						if (res.cancel) { //取消删除
							console.log("取消删除");
						} else {
							uni.request({
								url: getApp().globalData.domain + "/indents/" + this.indentNo,
								method:"DELETE",
								header:{
									token: uni.getStorageSync("token")
								},
								success: function(res1) {
									console.log(res1.data)
									if (res1.data.mes) { //删除失败
										uni.showToast({
											title: "订单不存在"
										})
									}else{
										uni.showToast({
											title:"删除成功！"
										})
									}
								}
							})
							console.log("确认删除");
						}
					}
				})
			}
		}
	}
</script>

<style>
	.userinfo,.iteminfo,.indent{
		text-align: center;
		margin: 0 auto;
		height: 40px;
		margin-bottom: 10px;
		margin-top:10px;

	}

</style>
