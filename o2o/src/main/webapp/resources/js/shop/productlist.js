$(function(){
	//获取商品列表URL
	var listUrl = '/o2o/shopadmin/getproductlistbyshop?pageIndex=1&pageSize=99';
	//商品下架url
	var statusUrl='/o2o/shopadmin/modifyproduct';
	getlist();
	function getlist(){
		$.getJSON(listUrl,function(data){
			if(data.success){
				var productList = data.productList;
				var tempHtml = '';
				//拼接每条信息
				productList.map(function(item,index){
					var textOp="下架";
					var contraryStatus = 0;
					if(item.enableStatus == 0){
						//若状态为0，表明是已经下架的商品，操作变为上架
						textOp = "上架";
						contraryStatus = 1;
					}else{
						contraryStatus = 0;
					}
					//拼接每件商品的行信息
					tempHtml += '' + '<div class="row row-product">'
						+ '<div class="col-33">'
						+ item.productName
						+'</div>'
						+'<div class="col-20">'
						+ item.priority
						+ '</div>'
						+ '<div class="col-40">'
						+ '<a href="#" class="edit" data-id="'
						+ item.productId
						+ '" data-status="'
						+ item.enableStatus
						+ '">编辑</a>'
						+ '<a href="#" class="status" data-id="'
						+ item.productId
						+ '" data-status="'
						+ contraryStatus
						+ '">'
						+ textOp
						+'</a>'
						+ '<a href="#" class="preview" data-id="'
						+ item.productId
						+ '" data-status="'
						+ item.enableStatus
						+ '">预览</a>'
						+ '</div>'
						+ '</div>';
				});
				//赋值进控件中
				$('.product-wrap').html(tempHtml);
			}
		});
	}
	
	//将product-wrap类下的a标签绑定事件
	$('.product-wrap').on('click','a',function(e){
		var target = $(e.currentTarget);
		if(target.hasClass('edit')){
			window.location.href='/o2o/shopadmin/productoperation?productId='
				+ e.currentTarget.dataset.id;
		}else if(target.hasClass('status')){
			changeItemStatus(e.currentTarget.dataset.id,
					e.currentTarget.dataset.status);
		}else if(target.hasClass('preview')){
			window.location.href='/o2o/frontend/productdetail?productId='
				+ e.currentTarget.dataset.id;
		}
	});
	function changeItemStatus(id,enableStatus){
		var product = {};
		product.productId = id;
		product.enableStatus = enableStatus;
		$.confirm('确定吗？',function(){
			$.ajax({
				url:statusUrl,
				type:'POST',
				data:{
					productStr:JSON.stringify(product),
					statusChange:true
				},
				success:function(data){
					if(data.success){
						$.toast('操作成功！');
						getlist();
					}else{
						$.toast('操作失败！');
					}
				}
			});
		});
	}
});