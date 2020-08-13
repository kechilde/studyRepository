$(function(){
	//
	var url='/o2o/frontend/listmainpageinfo';
	$.getJSON(url,function(data){
		if(data.success){
			//获取后台传过来的头条列表
			var headLineList = data.headLineList;
			var swiperHtml = '';
			headLineList.map(function(item,index){
				swiperHtml += '' + '<div class="swiper-slide img-wrap">'
					+'<a href="/' + item.lineLink
					+ '"external><img class="banner-img" src="/' + item.lineImg
					+ '"alt="' + item.lineName + '"></a>' + '</div>';
			});
			//将轮播组件加入前端
			$('.swiper-wrapper').html(swiperHtml);
			//设定轮播时间为三秒
			$(".swiper-container").swiper({
				autoplay:3000,
				//用户操作轮播时，轮播是否停止
				autoplayDisableOnInteraction:false
			});
			//获取后台传过来的类别列表
			var shopCategoryList = data.shopCategoryList;
			var categoryHtml = '';
			//遍历类别，拼接前端样式
			shopCategoryList.map(function(item,index){
				categoryHtml += ''
					+ '<div class="col-50 shop-classify" data-category='
					+ item.shopCategoryId + '>'
					+ '<div class="word">'
					+ '<p class="shop-title">'
					+ item.shopCategoryName
					+ '</p>'
					+ '<p class="shop-desc">'
					+ item.shopCategoryDesc
					+ '</p>'
					+ '</div>'
					+ '<div class="shop-classify-img-warp">'
					+ '<img class="shop-img" src="/'
					+ item.shopCategoryImg
					+ '">'+'</div>'+'</div>';
			});
			//将组件加入前端
			$('.row').html(categoryHtml);
			
			$('.row').on('click','.shop-classify',function(e){
				var shopCategoryId = e.currentTarget.dataset.category;
				var newUrl = '/o2o/frontend/shoplist?parentId=' + shopCategoryId;
				window.location.href = newUrl;
			});
		}
	});
});