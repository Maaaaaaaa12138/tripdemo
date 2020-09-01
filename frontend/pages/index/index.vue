<template>
	<view class="content">
		<!-- <image src="../../static/index.jpg"></image> -->
	</view>
</template>

<script>
	export default {
		data() {
			return {
			}
		},
		onLoad() {
			setTimeout(()=>{
				uni.switchTab({
					url: "../home/home",
				})
			}, 1900)
		},
		methods: {
		}
	}
</script>

<style>
	page,.content {
		position: absolute;
		width: 100%;
		height: 100%;
		background-image: url("../../static/index.jpg");
		background-position: center center;
		background-size: cover;
	}
	
</style>
