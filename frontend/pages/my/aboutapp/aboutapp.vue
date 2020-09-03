<template>
	<view class="content">
		<image class="logo" src="/static/logo.jpg"></image>
		<text class="content-item">作者：马亮、公维熙、韩旭</text>
		<text class="content-item">版本：3.2.1</text>
		<view class="info">
			<view class="info-item">
				<navigator url="/pages/my/xieyi/xieyi">服务协议</navigator>
				&emsp;&nbsp;|&nbsp;&nbsp;
				<navigator url="/pages/my/yingsi/yingsi">隐私政策</navigator>
			</view>
			<text class="info-item">Copyright © 2020 901梦之队 荣誉出品</text>
		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				
			}
		},
		methods: {
			
		}
	}
</script>

<style>
	.content{
		box-sizing: border-box;
		position: absolute;
		width: 100%;
		height: 100%;
		font-family: kaiti;
		display: flex;
		flex-direction: column;
		align-items: center;
		color: #888888;
	}
	.content-item{
		margin-top: 15px;
		margin-bottom: 15px;
		display: block;
	}
	.info{
		position: fixed;
		bottom: 10px;
		font-size: 13px;
	}
	.logo{
		margin-top: 100px;
		margin-bottom: 150px;
		width: 150upx;
		height: 150upx;
		border-radius: 150upx;
	}
	.info-item{
		display: block;
		text-align: center;
	}
	.info-item navigator{
		display: inline;
		color: #0055B8;
		max-width: fit-content;
	}
</style>
