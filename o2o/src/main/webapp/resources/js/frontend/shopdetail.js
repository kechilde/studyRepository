$(function() {
	// 加载flag
	var loading = false;
	// 最多可加载的条目
	var maxItems = 20;
	// 每次加载添加多少条目
	var pageSize = 10;
	//获取商品列表URL
	var listUrl = '/o2o/frontend/listproductsbyshop';
	//获取shopId
	var shopId = getQueryString('shopId');
	//获取店铺类别列表以及商品类别列表的URL
	var searchDivUrl = '/o2o/frontend/listshopdetailpageinfo?shopId=' + shopId;
	//页码
	var pageNum = 1;
	var productCategoryId = '';
	var productName = '';
	//渲染出店铺类别列表以及区域列表以供搜索
	getSearchDivData();
	//预先加载10条商品信息
	addItems(pageSize,pageNum);
	
	//为兑换礼品的a标签赋值兑换礼品的URL，2.0
	//$('#exchangelist').attr('href','/o2o/frontend/awardlist?shopId=' + shopId);
	/**
	 *  获取店铺类别列表以及商品列表信息
	 */
	function getSearchDivData(){
		//如果传入了parentId，则取出下级类别
		var url = searchDivUrl;
		$.getJSON(url,function(data){
				if(data.success){
					var shop = data.shop;
					$('#shop-cover-pic').attr('src','/' + shop.shopImg);
					$('#shop-update-time').html(
							new Date(shop.lastEditTime).Format("yyyy-MM-dd"));
					$('#shop-name').html(shop.shopName);
					$('#shop-desc').html(shop.shopDesc);
					$('#shop-addr').html(shop.shopAddr);
					$('#shop-phone').html(shop.phone);
					//获取后台返回的该店铺的商品列表
					var productCategoryList = data.productCategoryList;
					var html = '';
					//html += '<a href="#" class="button col-100" data-category-id="">全部类别 </a>';
					//遍历商品列表，拼接a标签集
					productCategoryList.map(function(item,index){
						html += '<a href="#" class="button col-33" data-category-id='
							 + item.productCategoryId
							 + '>'
							 + item.productCategoryName
							 + '</a>';
					});
					//将拼接好的类别标签嵌入前台的html组件里
					$('#shopdetail-button-div').html(html);
				}
			});
	}
	//选择新的店铺类别之后，重置页码，清空原先的店铺列表，按照新的类别去查询
	$('#shopdetail-button-div').on('click','.button',function(e){
		productCategoryId = e.target.dataset.categoryId;
		if(productCategoryId){
			//若之前已经选定了别的category，则移除其选定效果，改成选定新的
			if($(e.target).hasClass('button-fill')){
				$(e.target).removeClass('button-fill');
				productCategoryId='';
			}else{
				$(e.target).addClass('button-fill').siblings().removeClass('button-fill');
			}
			//由于查询条件改变，清空店铺列表进行查询
			$('.list-div').empty();
			//重置页码
			pageNum = 1;
			addItems(pageSize,pageNum);
		}
	});
	/**
	 * 获取分页展示的店铺列表
	 */
	function addItems(pageSize, pageIndex) {
		//拼接查询的URL，赋空值默认就去掉这个条件的限制，有值就代表按这个条件去查询
		var url = listUrl + '?' + 'pageIndex=' + pageIndex + '&pageSize='
			+ pageSize + '&productCategoryId=' + productCategoryId
			+ '&productName=' + productName + '&shopId=' + shopId;
		loading = true;
		//访问后台获取相应查询条件下的店铺列表
		$.getJSON(url,function(data){
			if(data.success){
				//获取当前查询条件下店铺的总数
				maxItems = data.count;
				var html = '';
				//遍历商品列表，拼接出卡片集合
				data.productList.map(function(item,index){
					html += '' + '<div class="card" data-product-id="'
						 + item.productId 
						 + '">'
						 + '<div class="card-header">'
						 + item.productName 
						 + '</div>'
						 + '<div class="card-content">'
						 + '<div class="list-block media-list">' 
						 + '<ul>'
						 + '<li class="item-content">'
						 + '<div class="item-media">'
						 + '<img src="/'
						 + item.imgAddr
						 + '"width="44">' 
						 + '</div>'
						 + '<div class="item-inner">'
						 + '<div class="item-subtitle">'
						 + item.productDesc
						 + '</div>' + '</div>' + '</li>' + '</ul>'
						 + '</div>' + '</div>' + '<div class="card-footer">'
						 + '<p class="color-gray">'
						 + new Date(item.lastEditTime).Format("yyyy-MM-dd")
						 + '更新</p>' + '<span>点击查看</span>' + '</div>'
						 + '</div>';
				});
				//将卡片添加到目标HTML组件里
				$('.list-div').append(html);
				//获取目前已经显示的卡片总数，包含之前已加载的
				var total = $('.list-div .card').length;
				//若数量达到，则停止后台加载
				if(total >= maxItems){
					//加载完毕，则注销无限加载事件，以防不必要的加载
					//$.detachInfiniteScroll($('.infinite-scroll'));
					//隐藏加载提示符
					$('.infinite-scroll-preloader').hide();
				}else{
					$('.infinite-scroll-preloader').show();
				}
				//否则页码加一，继续load出新的店铺
				pageNum += 1;
				//加载结束，可以再次加载了
				loading = false;
				//刷新页面，显示新加载的店铺
				$.refreshScroller();
			}
		});
	}
	// 注册'infinite'事件处理函数
	$(document).on('infinite','.infinite-scroll-bottom', function() {
		// 如果正在加载，则退出
		if (loading)
			return;
		addItems(pageSize,pageNum);
	});
	//点击店铺的卡片进入该店铺的详情页
	$('.product-list').on('click','.card',function(e){
		var shopId = e.currentTarget.dataset.shopId;
		window.location.href='/o2o/frontend/productdetail?productId=' + productId;
	});
	
	//需要查询的商品名字发生变化后，重置页码，清空原先的商品列表
	$('#search').on('change',function(e){
		productName = e.target.value;
		$('.list-div').empty();
		pageNum = 1;
		addItems(pageSize,pageNum);
	});
	//初始化界面
	$.init();
});