<template>
	<view class="news">
		<text>暂无新消息</text>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				help:''
			}
		},
		methods: {
			
		}
	}
</script>

<style>

.news{
	position: fixed;
	display: flex;
	width: 100%;
	height: 100%;
	justify-content: center;
	align-items: center;
}
.news text{
	font-family: kaiti;
	color: #b8b8b8;
}
</style>
