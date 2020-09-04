<template>
	<view>
		<view class="news">
			<text>暂无新消息</text>
		</view>
		<view class="Copyright">
			<text>Copyright © 2020 901梦之队 荣誉出品</text>
		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				help: ''
			}
		},
		methods: {

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
</style>
