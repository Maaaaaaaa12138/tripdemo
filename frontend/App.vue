<script>
	export default {
		onLaunch:function(){
			// console.log("onload")
			if (!getApp().globalData.isLogin){
				getApp().globalData.Login()
			}
		},
		globalData:{
			domain: "http://ftp.12138.site:666",
			Login: function(thi){
				if (!uni.getStorageSync("userId")){
					return
				}
				let self = thi;
				uni.request({
					
					url: getApp().globalData.domain + "/users/" + uni.getStorageSync("userId"),
					success:function(res){
						// console.log(res)
						let data = res.data;
						if (data != "request error" && !data.mes){
							getApp().globalData.isLogin = true
							self.login = true
							self.userInfo.id = data.data.id;
							self.userInfo.username = data.data.username;
						}
					}
				})
			},
			isLogin: false
		},
	}
</script>

<style>
	
	@font-face {
		font-family: kaiti;
		src: url("/static/ttf/kaiti.ttf");
	}
</style>
