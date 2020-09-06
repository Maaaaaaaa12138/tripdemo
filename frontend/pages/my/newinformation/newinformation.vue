<template>
	<view class="container">
		<view class="news">
			<text v-if="infoList.length==0">暂无新消息</text>
		</view>
		<view class="infos">
			<template v-for="(info, index) in infoList">
				<view class="item info_box" @longtap="DELETE(info.id)" :class="{'notRead': info.type==0}" @tap="show_info_modal(index)">
					<view class="mes-image">
						<image :src="domain + info.fromAvatar"></image>
					</view>
					<view class="mes-body">
						<view class="title">{{info.title}}</view>
						<view class="content">{{info.content}}</view>
					</view>
				</view>
			</template>
		</view>
		<Copyright />

		<neil-modal :show="show" @close="bindClose" :title="msgTitle" :content="msgContent" @confirm="bindBtn" :show-cancel="false">

		</neil-modal>
	</view>

</template>

<script>
	import Copyright from '@/components/Copyright/Copyright.vue';
	import neilModal from '@/components/neil-modal/neil-modal.vue';
	export default {
		components: {
			Copyright,
			neilModal
		},
		data() {
			return {
				infoList: [],
				show: false,
				msgTitle: "暂无消息",
				msgContent: "",
				avatarList:[],
				domain: getApp().globalData.domain,
				img1: "../../../static/information/已读消息.png",
				img2: "../../../static/information/未读消息.png",
			}
		},
		onLoad() {
			this.loadData();
		},
		onshow() {

		},
		onPullDownRefresh:function(){
			this.loadData() 
		},
		methods: {
			DELETE: function(index){
				let self = this;
				uni.showModal({
					title:"删除",
					content:"是否删除本条消息？",
					success: (res) => {
						// 确定删除
						if (res.confirm){
							uni.request({
								url: getApp().globalData.domain + "/messages/" + index,
								header:{
									token: uni.getStorageSync("token")
								},
								method: "DELETE",
								success:function(res){
									// 删除失败
									if(res.data.mes){
										uni.showToast({
											title: res.data.mes,
											icon: "none"
										})
									}
									// 删除成功
									else{
										uni.showModal({
											content: "删除成功！！！",
											showCancel: false
										})
										self.loadData()
									}
								}
							})
						}
					},
					fail:function(res){
						// console.log(res)
					}
				})
			},
			show_info_modal: function(index) {
				let self = this;
				this.show = true;
				this.msgTitle = this.infoList[index]['title'];
				this.msgContent = this.infoList[index]['content'];
				uni.request({
					url: getApp().globalData.domain + "/messages/" + self.infoList[index]['id'],
					method: "PUT",
					header: {
						token: uni.getStorageSync("token")
					},
					success: (res) => {
						if (res.data.mes) { //修改失败

						} else { //已阅读
							self.infoList[index]['type'] = 1;
						}
					}
				})
			},
			bindOpen: function() {
				this.show = true;
			},
			bindClose: function() {
				this.show = false;
			},
			bindBtn: function() { //点击确认

			},
			loadData: function(){
				let self = this;
				this.infoList = []
				uni.request({
					url: getApp().globalData.domain + "/messages",
					method: "GET",
					header: {
						token: uni.getStorageSync("token")
					},
					success: (res) => { //获取成功
						uni.stopPullDownRefresh();
						self.infoList = res.data.data;
					},
					fail: (res) => {
						// console.log("获取失败");
						// console / log(res.data);
					}
				})
			}
			
		}
	}
</script>

<style>
	.news {
		position: fixed;
		display: flex;
		width: 100%;
		height: 100%;
		justify-content: center;
		/*垂直居中好帮手*/
		align-items: center;
	}

	.news text {
		font-family: kaiti;
		color: #b8b8b8;
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

	.info_box {
		border-radius: 20upx;
		width: 90%;
		height: 180upx;
		background-color: #ffffff;
		margin-left: 5%;
		margin-right: 5%;
		/* margin-top: 40upx; */
		margin-bottom: 35upx;
		display: flex;
		padding-right: 5upx;
		padding-bottom: 5upx;
		box-shadow: 2px 2px 6px rgba(0,0,0, .125), -2px -2px 6px rgba(0,0,0, .125);
	}
	
	.notRead{
		box-shadow: 2px 2px 6px rgba(255, 0,0, .2), -2px -2px 6px rgba(255, 0,0, .2);
	}

	.container {
		position: absolute;
		top: 0;
		left: 0;
		height: 100%;
		width: 100%;
		overflow-y: scroll;
		background-color: #f2f5fa;
		padding-top: 30upx;
		box-sizing: border-box;
	}

	

	.mes-image {
		flex: 20%;
		max-width: 20%;
		padding: 8px;
		display: flex;
		align-items: center;
		justify-content: center;
	}

	.mes-body {
		margin-top: 8px;
		flex: 80%;
		z-index: 1;
		/* height: 50upx; */
	}
	.title{
		padding: 8px;
		font-weight: 600;
		font-size: 17px;
		font-family: kaiti;
	}
	.content {
		height: 39px;
		font-size: 12px;
		padding-left: 8px;
		padding-right: 8px;
		overflow-y: hidden;
		font-family: kaiti;
	}

	image {
		max-width: 75%;
		max-height: 75%;
		border-radius: 50%;
	}
</style>
