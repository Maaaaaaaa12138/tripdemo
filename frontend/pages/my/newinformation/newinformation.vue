<template>
	<view>
		<view class="news">
			<text>暂无新消息</text>
		</view>
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
