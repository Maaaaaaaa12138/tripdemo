<template>
	<view class="container">
		<view class="news">
			<text v-if="infoList.length==0">暂无新消息</text>
		</view>
		<view class="infos">
			<view v-for="(info, index) in infoList" :key="index" class="item">
				<view class="info_box" @tap="show_info_modal(index)">
					<view class="mes-image">
						<image :src="info.type?img1:img2"></image>
					</view>
					<view class="mes-body">
						<view class="title">{{info.title}}</view>
						<view class="content">{{info.content}}</view>
					</view>
				</view>
			</view>
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
				img1: "../../../static/information/已读消息.png",
				img2: "../../../static/information/未读消息.png",
			}
		},
		onLoad() {
			let self = this;
			uni.request({
				url: getApp().globalData.domain + "/messages",
				method: "GET",
				header: {
					token: uni.getStorageSync("token")
				},
				success: (res) => { //获取成功
					// console.log("获取消息列表");
					console.log(res.data);
					self.infoList = res.data.data;
					console.log(self.infoList);
				},
				fail: (res) => {
					// console.log("获取失败");
					// console / log(res.data);
				}
			})
		},
		onshow() {

		},
		methods: {
			show_info_modal: function(index) {
				let self = this;
				this.show = true;
				console.log(index);
				this.msgTitle = this.infoList[index]['title'];
				this.msgContent = this.infoList[index]['content'];
				uni.request({
					url: getApp().globalData.domain + "/messages/" + self.infoList[index]['id'],
					method: "PUT",
					header: {
						token: uni.getStorageSync("token")
					},
					success: (res) => {
						console.log(res.data);
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
		box-shadow: 2px 2px 6px rgba(0,0,0, .2), -2px -2px 6px rgba(0,0,0, .2);

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
		flex: 10%;
		max-width: 10%;
		padding: 8px;
		display: flex;
		align-items: center;
	}

	.mes-body {
		margin-top: 8px;
		flex: 90%;
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
		max-width: 100%;
		height: 45%;
		max-height: 100%;
	}
</style>
